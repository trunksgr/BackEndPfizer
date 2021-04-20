package gr.codehub.pfizer.hibernate.representation;

import gr.codehub.pfizer.hibernate.model.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class LogInRepresentation {

    private String password;
    private String email;
    private int id;
    private String role;


}