package hud.example.ticketApp.repo;

import hud.example.ticketApp.model.Bus;
import hud.example.ticketApp.model.enums.BusState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Long> {
    @Query(value = "SELECT * FROM bus WHERE id = ?1 AND state = ?2", nativeQuery = true)
    Bus findByIdAndState(Long busId, BusState busState);

    @Query(value = "SELECT * FROM bus WHERE (LOWER(departing_location) = LOWER(:from) AND LOWER(arriving_location) = LOWER(:to) AND departing_date = :date) ORDER BY departing_time ASC", nativeQuery = true)
    List<Bus> findByDateAndLocation(@Param("from") String from,@Param("to") String to, @Param("date") LocalDate departingDate);


}
