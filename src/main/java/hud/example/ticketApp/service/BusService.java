package hud.example.ticketApp.service;

import hud.example.ticketApp.model.dto.BusRequestDto;
import hud.example.ticketApp.model.dto.JourneyDto;
import org.springframework.http.ResponseEntity;

public interface BusService {
    ResponseEntity<?> createJourney(JourneyDto journeyDto);


    ResponseEntity<?> getListOfBus(BusRequestDto requestDto);
}
