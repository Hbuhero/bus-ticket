package hud.example.ticketApp.serviceImpl;

import hud.example.ticketApp.model.Bus;
import hud.example.ticketApp.model.Seat;
import hud.example.ticketApp.model.dto.BusRequestDto;
import hud.example.ticketApp.model.dto.JourneyDto;
import hud.example.ticketApp.model.enums.BusState;
import hud.example.ticketApp.model.enums.SeatType;
import hud.example.ticketApp.repo.BusRepository;
import hud.example.ticketApp.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.LongStream;

@RequiredArgsConstructor
@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private final BusRepository busRepository;

    @Override
    public ResponseEntity<?> createJourney(JourneyDto journeyDto) {
        // check if the bus has completed the route
        Bus bus = busRepository.findByIdAndState(journeyDto.getBusId(), BusState.COMPLETED);

        if (bus == null){
            return new ResponseEntity<>("No Bus Found", HttpStatusCode.valueOf(404));
        }

        bus = Bus.builder()
                .arrivingLocation(journeyDto.getArrivingLocation())
                .departingLocation(journeyDto.getDepartingLocation())
                .arrivingTime(journeyDto.getArrivingTime())
                .departingTime(journeyDto.getDepartTime())
                .arrivingDate(journeyDto.getArrivingDate())
                .departingDate(journeyDto.getDepartingDate())
                .fee(journeyDto.getFee())
                .build();

        busRepository.save(bus);

        return new ResponseEntity<>("Added Successfully", HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<?> getListOfBus(BusRequestDto requestDto) {
        // check the time requested
        List<Bus> buses = busRepository.findByDateAndLocation(requestDto.getFrom(), requestDto.getTo(), requestDto.getDate());

        return new ResponseEntity<>(buses, HttpStatusCode.valueOf(200));
    }
}
