package gr.codehub.pfizer.hibernate.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class PatientsData {
    //the capitals in the fields was to accommodate the front ends needs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private int GlucoseLv;
    private int CarbsIntake;
    private Date Date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient Patient;

}
