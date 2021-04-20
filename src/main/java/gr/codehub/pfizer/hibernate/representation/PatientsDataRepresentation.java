package gr.codehub.pfizer.hibernate.representation;

import gr.codehub.pfizer.hibernate.model.Chief;
import gr.codehub.pfizer.hibernate.model.PatientsData;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
public class PatientsDataRepresentation {

    private int Id;
    private int GlucoseLv;
    private int CarbsIntake;
    private java.util.Date Date;
    private int PatientId;
    private String uri;

    //mappers
    public PatientsDataRepresentation(PatientsData patientsData) {


        if (patientsData != null) {
            Id = patientsData.getId();
            GlucoseLv = patientsData.getGlucoseLv();
            CarbsIntake = patientsData.getCarbsIntake();
            Date = patientsData.getDate();
            uri = "http://localhost:9000/v1/patientsData/" + patientsData.getId();
            if (patientsData.getPatient() != null)
                PatientId = patientsData.getPatient().getId();


        }
    }

    public PatientsData createPatientsData() {
        PatientsData patientsData = new PatientsData();
        patientsData.setGlucoseLv(GlucoseLv);
        patientsData.setCarbsIntake(CarbsIntake);
        patientsData.setDate(Date);
        //patientsData.setPatientId(PatientId);

        return patientsData;

    }


}

