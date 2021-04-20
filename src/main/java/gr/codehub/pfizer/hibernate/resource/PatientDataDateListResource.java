package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.exception.AuthorizationException;
import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Patient;
import gr.codehub.pfizer.hibernate.repository.PatientsDataRepository;
import gr.codehub.pfizer.hibernate.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PatientDataDateListResource extends ServerResource {

    @Get("json")
    public ApiResult<List<Double>> getPatientsData() throws ParseException {

        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);

        } catch (AuthorizationException e) {
            try {
                ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
            } catch (AuthorizationException d) {
                return new ApiResult<>(null, 500, e.getMessage());
            }
        }


        Date from1a = new SimpleDateFormat("dd-M-yyyy").parse(getQueryValue("from1"));
        Date toa = new SimpleDateFormat("dd-M-yyyy").parse(getQueryValue("to"));

        int patient_Id = Integer.parseInt(getQueryValue("patient_Id"));


        EntityManager em = JpaUtil.getEntityManager();
        PatientsDataRepository patientsDataRepository = new PatientsDataRepository(em);
        Patient patient = new Patient();
        patient.setId(patient_Id);


        List<Double> patientsData = patientsDataRepository.getPatientsDataGlucoseLv(patient, from1a, toa);
        em.close();


        return new ApiResult<>(patientsData, 200, "ok");
    }


}