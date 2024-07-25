package hud.example.ticketApp;

import hud.example.ticketApp.model.Bus;
import hud.example.ticketApp.repo.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class TicketAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketAppApplication.class, args);
	}
}
// state of the bus seats, if full should not be visible
// check time for requested bus
// make the seat entity

