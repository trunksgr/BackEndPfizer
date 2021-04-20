package gr.codehub.pfizer.hibernate.representation;


import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class LogInRepresentation {

    private String password;
    private String email;
    private int id;
    private String role;


}