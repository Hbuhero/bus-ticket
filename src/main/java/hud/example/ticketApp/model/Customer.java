package hud.example.ticketApp.model;

import hud.example.ticketApp.model.enums.Age;
import hud.example.ticketApp.model.enums.Gender;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Customer {
    private String name;
    private String number;
    private String paymentNumber;
    private Age age;
    private Gender gender;

}
