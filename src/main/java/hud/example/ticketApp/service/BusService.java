package hud.example.ticketApp.service;

import hud.example.ticketApp.model.dto.BookingRequest;
import hud.example.ticketApp.model.dto.BusRequestDto;
import hud.example.ticketApp.model.dto.JourneyDto;
import hud.example.ticketApp.model.dto.SeatsRequest;
import org.springframework.http.ResponseEntity;

public interface BusService {
    ResponseEntity<?> createJourney(JourneyDto journeyDto);


    ResponseEntity<?> getListOfBus(BusRequestDto requestDto);

    ResponseEntity<?> bookTicket(BookingRequest bookingRequest, Long journeyId);

    ResponseEntity<?> selectSeats(Long journeyId, SeatsRequest seatsRequest);

    ResponseEntity<?> viewAvailableSeats(Long journeyId);
}
