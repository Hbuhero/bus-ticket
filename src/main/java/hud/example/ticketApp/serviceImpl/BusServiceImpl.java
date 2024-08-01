package hud.example.ticketApp.serviceImpl;

import hud.example.ticketApp.model.*;
import hud.example.ticketApp.model.dto.BookingRequest;
import hud.example.ticketApp.model.dto.BusRequestDto;
import hud.example.ticketApp.model.dto.JourneyDto;
import hud.example.ticketApp.model.dto.SeatsRequest;
import hud.example.ticketApp.model.enums.BusState;
import hud.example.ticketApp.model.enums.SeatStatus;
import hud.example.ticketApp.model.enums.SeatType;
import hud.example.ticketApp.repo.BusRepository;
import hud.example.ticketApp.repo.JourneyRepository;
import hud.example.ticketApp.repo.SeatRepository;
import hud.example.ticketApp.repo.TicketRepository;
import hud.example.ticketApp.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RequiredArgsConstructor
@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private final JourneyRepository journeyRepository;
    @Autowired
    private final SeatRepository seatRepository;
    @Autowired
    private final TicketRepository ticketRepository;

    @Override
    public ResponseEntity<?> createJourney(JourneyDto journeyDto) {
        // check if the bus has completed the route
//        Bus bus = busRepository.findByIdAndState(journeyDto.getBusId(), BusState.COMPLETED);
//
//        if (bus == null){
//            return new ResponseEntity<>("No Bus Found", HttpStatusCode.valueOf(404));
//        }
//
//        bus = Bus.builder()
//                .arrivingLocation(journeyDto.getArrivingLocation())
//                .departingLocation(journeyDto.getDepartingLocation())
//                .arrivingTime(journeyDto.getArrivingTime())
//                .departingTime(journeyDto.getDepartTime())
//                .arrivingDate(journeyDto.getArrivingDate())
//                .departingDate(journeyDto.getDepartingDate())
//                .fee(journeyDto.getFee())
//                .build();
//
//        busRepository.save(bus);

        return new ResponseEntity<>("Added Successfully", HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<?> getListOfBus(BusRequestDto requestDto) {
        // check the time requested
        List<Journey> journeys = journeyRepository.findByFromAndToAndDestination(requestDto.getFrom(), requestDto.getTo(), requestDto.getDate());

        if (journeys == null){
            return new ResponseEntity<>("Not Bus Found", HttpStatusCode.valueOf(200));
        }

        return new ResponseEntity<>(journeys, HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<?> bookTicket(BookingRequest bookingRequest, Long journeyId) {
        Optional<Journey> journeyOptional = journeyRepository.findById(journeyId);

        if (journeyOptional.isEmpty()){
            return  new ResponseEntity<>("Not Found", HttpStatusCode.valueOf(404));
        }
        Journey journey = journeyOptional.get();

        System.out.println("ok");

        Ticket ticket = Ticket.builder() // new Ticket();
                .totalFee(journey.getFee())
                .age(bookingRequest.getAge())
                .departingDestination(journey.getDepartingLocation())
                .name(bookingRequest.getName())
                .busId(journey.getBusId().getBusId())
                .destination(journey.getArrivingLocation())
                .departingDate(journey.getDepartingDate())
                .departingTime(journey.getDepartingTime())
                .seatNumber(bookingRequest.getSeatNumber())
                .number(bookingRequest.getPhoneNumber())
                .gender(bookingRequest.getGender())
                .status(Status.PENDING)
                .arrivalDateAndTime(journey.getArrivingTime().toString()+" "+journey.getArrivingDate().toString())
                .build();
        System.out.println("ok");
        ticketRepository.save(ticket);
        return new ResponseEntity<>("Ok", HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<?> selectSeats(Long journeyId, SeatsRequest seatsRequest) {
        Long busId = journeyRepository.findBusIdByJourneyId(journeyId);
        System.out.println(busId);
        Seat seat = seatRepository.findSeatByBusIdAndSeatNumber(seatsRequest.getSeatNumbers(), busId);
        if (seat == null){
            return new ResponseEntity<>("Not Found", HttpStatusCode.valueOf(500));
        }
        if (seat.getSeatStatus() == SeatStatus.ON_REQUEST | seat.getSeatStatus() == SeatStatus.TAKEN){
            return new ResponseEntity<>("Seat taken", HttpStatusCode.valueOf(409));
        }
        seat.setSeatStatus(SeatStatus.ON_REQUEST);
        seatRepository.save(seat);
        return new ResponseEntity<>(seat.getSeatId(), HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<?> viewAvailableSeats(Long journeyId) {
        Long busId = journeyRepository.findBusIdByJourneyId(journeyId);
        List<Seat> allSeats = seatRepository.findAvailableSeats(busId);
        List<Seat> availableSeats = allSeats.stream()
                .filter(s -> s.getSeatStatus().equals(SeatStatus.EMPTY))
                .toList();
        List<Seat> onRequestSeats = allSeats.stream()
                .filter(s -> s.getSeatStatus().equals(SeatStatus.ON_REQUEST))
                .toList();
        List<Seat> takenSeats = allSeats.stream()
                .filter(s -> s.getSeatStatus().equals(SeatStatus.TAKEN))
                .toList();

        List<List<Seat>> listOfSeats = List.of(availableSeats,takenSeats, onRequestSeats);


        return new ResponseEntity<>(listOfSeats, HttpStatusCode.valueOf(200));
    }
}
