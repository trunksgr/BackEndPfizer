package gr.codehub.pfizer.hibernate.representation;

import gr.codehub.pfizer.hibernate.model.Chief;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
public class ChiefRepresentation {

    private int Id;
    private String FullName;
    private String Password;
    private String Email;
    private String role;
    private String uri;

    //mappers
    public ChiefRepresentation(Chief chief) {
        if (chief != null) {
            Id = chief.getId();
            FullName = chief.getFullName();
            Password = chief.getPassword();
            Email = chief.getEmail();
            role = chief.getRole();
            uri = "http://localhost:9000/v1/chief/" + chief.getId();
        }
    }

    public Chief createChief() {
        Chief chief = new Chief();
        chief.setFullName(FullName);
        chief.setPassword(Password);
        chief.setEmail(Email);
        chief.setRole(role);

        return chief;

    }

}
