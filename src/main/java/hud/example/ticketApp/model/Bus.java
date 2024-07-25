package hud.example.ticketApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hud.example.ticketApp.model.enums.BusState;
import hud.example.ticketApp.model.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.LongStream;
@Entity
@Table(name = "bus")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
    @Id
    private Long busId;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Seat> seats = createBusSeats();

    private LocalTime departingTime;
    private LocalTime arrivingTime;
    private LocalDate departingDate;
    private LocalDate arrivingDate;
    private String departingLocation;
    private String arrivingLocation;
    private Long fee;

    @Enumerated(value = EnumType.STRING)
    private BusState state;



    public static List<Seat> createBusSeats(){
        // initializing the seats and assigning seatNumbers
        List<Seat> seats = LongStream.rangeClosed(1, 53) // Generate numbers from 1 to 53 (inclusive)
                .mapToObj(Seat::new) // Create Seat objects
                .toList();

        // looping through the list
        for (int i =0; i<53; i++){
            // set the 3 seats to aisle in the last 5 seats
            if ((i+1)>49 && (i+1)<53){
                seats.get(i).setType(SeatType.AISLE);
            }else {
                // set types odd:window, even:aisle
                if ((i + 1) % 2 == 0) {
                    seats.get(i).setType(SeatType.AISLE);
                } else {
                    seats.get(i).setType(SeatType.WINDOW);
                }
            }
        }

        return seats;
    }

}
