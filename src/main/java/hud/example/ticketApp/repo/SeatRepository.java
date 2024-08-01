package hud.example.ticketApp.repo;


import hud.example.ticketApp.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query(value = "SELECT * FROM seat WHERE seat_number = ?1 AND seat_id IN (SELECT seats_seat_id FROM bus_seats WHERE bus_bus_id = ?2)", nativeQuery = true)
    Seat findSeatByBusIdAndSeatNumber(Integer seatNumbers, Long busId);

    @Query(value = "SELECT * FROM bus_seats WHERE bus_bus_id = ?1", nativeQuery = true)
    List<Seat> findAvailableSeats(Long journeyId);
}
