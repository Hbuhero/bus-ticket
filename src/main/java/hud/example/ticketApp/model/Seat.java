package hud.example.ticketApp.model;

import hud.example.ticketApp.model.enums.SeatStatus;
import hud.example.ticketApp.model.enums.SeatType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Table(name = "seat")
@Data
@RequiredArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seatId;
    private Long seatNumber;

    @Enumerated(value = EnumType.STRING)
    private SeatType type;

    @Enumerated(value = EnumType.STRING)
    private SeatStatus seatStatus;

    public Seat(Long seatNumber){
        this.seatNumber = seatNumber;
    }
}
