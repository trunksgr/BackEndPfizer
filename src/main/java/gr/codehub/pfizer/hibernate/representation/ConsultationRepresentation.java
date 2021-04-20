package gr.codehub.pfizer.hibernate.representation;


import gr.codehub.pfizer.hibernate.model.Consultation;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
public class ConsultationRepresentation {
    private int Id;
    private java.util.Date Date;
    private String Description;
    private int DoctorId;
    private int PatientId;
    private String uri;

    //mappers
    public ConsultationRepresentation(Consultation consultation) {
        if (consultation != null) {
            Id = consultation.getId();
            Date = consultation.getDate();
            Description = consultation.getDescription();

            if (consultation.getDoctor() != null) {
                DoctorId = consultation.getDoctor().getId();
            }
            if (consultation.getPatient() != null) {
                PatientId = consultation.getPatient().getId();
            }


            uri = "http://localhost:9000/v1/consultation/" + consultation.getId();
        }
    }

    public Consultation createConsultation() {
        Consultation consultation = new Consultation();
        consultation.setDate(Date);
        consultation.setDescription(Description);


        return consultation;

    }

}

