package hud.example.ticketApp.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BusRequestDto {
    private String from;
    private String to;
    private LocalDate date;
}
