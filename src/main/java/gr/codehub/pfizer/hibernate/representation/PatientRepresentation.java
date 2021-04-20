package gr.codehub.pfizer.hibernate.representation;

import gr.codehub.pfizer.hibernate.model.Doctor;
import gr.codehub.pfizer.hibernate.model.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class PatientRepresentation {

    private int Id;
    private String FullName;
    private String Password;
    private String Email;
    private boolean Gender;
    private Date DateOfBirth;
    private int DoctorId;
    private float Weight;
    private float Height;
    private boolean Active;
    private String role;
    private String uri;


    //mappers
    public PatientRepresentation(Patient patient) {
        if (patient != null) {
            Id = patient.getId();
            FullName = patient.getFullName();
            Password = patient.getPassword();
            Email = patient.getEmail();
            Gender = patient.isGender();
            DateOfBirth = patient.getDateOfBirth();
            Height = patient.getHeight();
            Weight = patient.getWeight();
            Active = patient.isActive();
            role = patient.getRole();

            if (patient.getDoctor() != null) {
                DoctorId = patient.getDoctor().getId();
            }


            uri = "http://localhost:9000/v1/patient/" + patient.getId();
        }
    }

    public Patient createPatient() {
        Patient patient = new Patient();
        patient.setFullName(FullName);
        patient.setPassword(Password);
        patient.setEmail(Email);
        patient.setDateOfBirth(DateOfBirth);
        patient.setWeight(Weight);
        patient.setHeight(Height);
        patient.setGender(Gender);
        patient.setActive(Active);
        patient.setRole(role);



        return patient;

    }

    public Doctor setPaDocId() {
        Doctor doctor = new Doctor();
        int x = this.DoctorId;
        doctor.setId(x);
        return doctor;
    }
}
