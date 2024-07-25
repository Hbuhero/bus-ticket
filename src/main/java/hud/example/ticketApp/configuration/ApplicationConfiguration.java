package hud.example.ticketApp.configuration;

import hud.example.ticketApp.model.Bus;
import hud.example.ticketApp.repo.BusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

    @Autowired
    private final BusRepository repo;

    @Bean
    public String just(){
        List<Bus> buses = repo.findAll();
        buses.forEach(b -> b.setSeats(Bus.createBusSeats()));

        repo.saveAll(buses);
        return "";
    }
}
