package hud.example.ticketApp.controller;

import hud.example.ticketApp.model.Bus;
import hud.example.ticketApp.model.dto.BookingRequest;
import hud.example.ticketApp.model.dto.BusRequestDto;
import hud.example.ticketApp.model.dto.SeatsRequest;
import hud.example.ticketApp.repo.BusRepository;
import hud.example.ticketApp.service.BusService;
import hud.example.ticketApp.serviceImpl.BusServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import hud.example.ticketApp.model.dto.JourneyDto;

import java.util.List;

@RestController
@RequestMapping(path = "/api/bus")
@RequiredArgsConstructor
public class BusController {
    @Autowired
    private final BusService busService;

    @Autowired
    private final BusRepository busrepo;

    @PostMapping(name = "/create-journey")
    public ResponseEntity<?> createJourney(@RequestBody JourneyDto journeyDto){
        return busService.createJourney(journeyDto);
    }

    @PostMapping(path = "/get-journey")
    public ResponseEntity<?> getListOfBus(@RequestBody BusRequestDto requestDto){
        return busService.getListOfBus(requestDto);
    }

    @GetMapping(path = "/all")
    public List<Bus> getBuss(){
        return busrepo.findAll();
    }

    @PostMapping(path = "/select-seats/{journeyId}")
    public ResponseEntity<?> selectSeats(@PathVariable("journeyId") Long journeyId, @RequestBody SeatsRequest seatsRequest){
        return busService.selectSeats(journeyId, seatsRequest);
    }

    @PostMapping("/book-ticket/{journeyId}/")
    public ResponseEntity<?> bookTicket(@RequestBody BookingRequest bookingRequest, @PathVariable("journeyId") Long journeyId){
        return busService.bookTicket(bookingRequest, journeyId);
    }

    @GetMapping("/view-available-seat/{journeyId}")
    public ResponseEntity<?> viewAvailableSeat(@PathVariable Long journeyId){
        return busService.viewAvailableSeats(journeyId);
    }
}
