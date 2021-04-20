package gr.codehub.pfizer.hibernate.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    private String FullName;
    private String Password;
    private String Email;
    private boolean Gender;
    private Date DateOfBirth;
    private float Weight;
    private float Height;
    private boolean Active;
    private String role;

    @OneToMany(mappedBy = "Patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consultation> Consultations;

    @OneToMany(mappedBy = "Patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PatientsData> PatientsData;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Doctor Doctor;

}
