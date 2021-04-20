package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.exception.AuthorizationException;
import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Patient;
import gr.codehub.pfizer.hibernate.model.PatientsData;
import gr.codehub.pfizer.hibernate.repository.PatientsDataRepository;
import gr.codehub.pfizer.hibernate.representation.PatientsDataRepresentation;
import gr.codehub.pfizer.hibernate.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PatientsDataByDateResource extends ServerResource {

    @Get("json")
    public ApiResult<List<PatientsDataRepresentation>> getPatientsDataByDateResource() throws ParseException {

        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);

        } catch (AuthorizationException e) {
            try {
                ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
            } catch (AuthorizationException d) {
                return new ApiResult<>(null, 500, e.getMessage());
            }
        }


        Date from1 = new SimpleDateFormat("dd-M-yyyy").parse(getQueryValue("from1"));
        Date to = new SimpleDateFormat("dd-M-yyyy").parse(getQueryValue("to"));

        int patient_Id = Integer.parseInt(getQueryValue("patient_Id"));


        EntityManager em = JpaUtil.getEntityManager();
        PatientsDataRepository patientsDataRepository = new PatientsDataRepository(em);
        Patient patient = new Patient();
        patient.setId(patient_Id);

        List<PatientsData> patientsData = patientsDataRepository.getPatientsDataByDate(patient, from1, to);
        em.close();

        List<PatientsDataRepresentation> patientsDataRepresentationList =
                patientsData.stream()
                        .map(p -> new PatientsDataRepresentation(p))
                        .collect(toList());
        return new ApiResult<>(patientsDataRepresentationList, 200, "ok");
    }
}