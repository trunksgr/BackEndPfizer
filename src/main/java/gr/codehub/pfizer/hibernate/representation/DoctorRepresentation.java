package gr.codehub.pfizer.hibernate.representation;

import gr.codehub.pfizer.hibernate.model.Doctor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DoctorRepresentation {

    private int Id;

    private String FullName;
    private String Password;
    private String Email;
    private String Speciality;
    private boolean Active;
    private String role;
    private String uri;

    //mappers
    public DoctorRepresentation(Doctor doctor) {
        if (doctor != null) {
            Id = doctor.getId();
            FullName = doctor.getFullName();
            Password = doctor.getPassword();
            Email = doctor.getEmail();
            Speciality = doctor.getSpeciality();
            Active = doctor.isActive();
            role =doctor.getRole();

            uri = "http://localhost:9000/v1/doctor/" + doctor.getId();
        }
    }

    public Doctor createDoctor() {
        Doctor doctor = new Doctor();
        doctor.setFullName(FullName);
        doctor.setPassword(Password);
        doctor.setEmail(Email);
        doctor.setSpeciality(Speciality);
        doctor.setActive(Active);
        doctor.setRole(role);
        return doctor;

    }

}


