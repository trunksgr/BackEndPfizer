package gr.codehub.pfizer.hibernate.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Consultation {
    //the capitals in the fields was to accommodate the front ends needs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private Date Date;
    private String Description;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient Patient;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Doctor Doctor;


}
