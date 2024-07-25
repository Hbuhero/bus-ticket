package hud.example.ticketApp.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class JourneyDto {
    private Long busId;
    private LocalTime departTime;
    private LocalTime arrivingTime;
    private LocalDate departingDate;
    private LocalDate arrivingDate;
    private String departingLocation;
    private String arrivingLocation;
    private Long fee;

}
