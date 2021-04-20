package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.exception.AuthorizationException;
import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Patient;
import gr.codehub.pfizer.hibernate.repository.PatientRepository;
import gr.codehub.pfizer.hibernate.representation.PatientRepresentation;
import gr.codehub.pfizer.hibernate.security.Shield;
import org.restlet.Server;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Resource;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

public class PatientListResource extends ServerResource {

    @Get("json")
    public ApiResult<List<PatientRepresentation>> getPatient() {

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
        List<Patient> patient = patientRepository.findAll();
        em.close();


        List<PatientRepresentation> patientRepresentationList = new ArrayList<>();
        for (Patient p : patient)
            patientRepresentationList.add(new PatientRepresentation(p));
        return new ApiResult<>(patientRepresentationList, 200, "ok");
    }

    @Post("json")
    public ApiResult<PatientRepresentation> add(PatientRepresentation patientRepresentationIn) {
        try {
            ResourceUtils.checkRole(this, Shield.ROLE_PATIENT);

        } catch (AuthorizationException e) {
            try {
                ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
            } catch (AuthorizationException d) {
                return new ApiResult<>(null, 500, e.getMessage());
            }
        }

        if (patientRepresentationIn == null) return null;
//        if (patientRepresentationIn.getFullName()==null) return null;
//        if (patientRepresentationIn.getWeight()==null) return null;
//        if (patientRepresentationIn.getHeight()==null) return null;
//        if (patientRepresentationIn.getEmail()==null) return null;
//        if (patientRepresentationIn.getPassword()==null) return null;

        Patient patient = patientRepresentationIn.createPatient();
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);

        patientRepository.save(patient);
        PatientRepresentation p = new PatientRepresentation(patient);
        return new ApiResult<>(p, 200, "ok");
    }


}
