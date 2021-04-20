package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.exception.AuthorizationException;
import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Patient;
import gr.codehub.pfizer.hibernate.repository.PatientRepository;
import gr.codehub.pfizer.hibernate.representation.PatientRepresentation;
import gr.codehub.pfizer.hibernate.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import javax.persistence.EntityManager;
import java.text.ParseException;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class PatientWithNoCoResource extends ServerResource {


    @Get("json")
    public ApiResult<List<PatientRepresentation>> getPatientWithNoCoResource() throws ParseException {

        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);

        } catch (AuthorizationException e) {
            try {
                ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
            } catch (AuthorizationException d) {
                return new ApiResult<>(null, 500, e.getMessage());
            }
        }


        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);


        List<Patient> patient = patientRepository.getPatientWithNoCon();
        em.close();

        List<PatientRepresentation> patientRepresentationList =
                patient.stream()
                        .map(p -> new PatientRepresentation(p))
                        .collect(toList());
        return new ApiResult<>(patientRepresentationList, 200, "ok");
    }
}
