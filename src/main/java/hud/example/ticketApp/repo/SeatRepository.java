package hud.example.ticketApp.repo;


import hud.example.ticketApp.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}
