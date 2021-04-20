package gr.codehub.pfizer.hibernate.resource;


import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Patient;
import gr.codehub.pfizer.hibernate.repository.PatientRepository;
import gr.codehub.pfizer.hibernate.representation.PatientRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import javax.persistence.EntityManager;


public class RegisterPatientResource extends ServerResource {

    @Post("json")
    public ApiResult<PatientRepresentation> add(PatientRepresentation patientRepresentationIn) {


        if (patientRepresentationIn == null) return null;
//        if (patientRepresentationIn.getFullName()==null) return null;
//        if (patientRepresentationIn.getWeight()==null) return null;
//        if (patientRepresentationIn.getHeight()==null) return null;
//        if (patientRepresentationIn.getEmail()==null) return null;
//        if (patientRepresentationIn.getPassword()==null) return null;

        Patient patient = patientRepresentationIn.createPatient();
        patient.setRole("patient");
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);

        patientRepository.save(patient);
        PatientRepresentation p = new PatientRepresentation(patient);
        return new ApiResult<>(p, 200, "ok");
    }


}

