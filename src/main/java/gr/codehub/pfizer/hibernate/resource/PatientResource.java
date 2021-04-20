package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Patient;
import gr.codehub.pfizer.hibernate.repository.PatientRepository;
import gr.codehub.pfizer.hibernate.representation.PatientRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class PatientResource extends ServerResource {
    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
    }

    @Get("json")
    public PatientRepresentation getPatient() {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(id);
        PatientRepresentation patientRepresentation1 = new PatientRepresentation(patient);
        em.close();
        return patientRepresentation1;
    }

    //update
    @Put("json")
    public PatientRepresentation putPatient(PatientRepresentation patientRepresentation) {
        //diabasma
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.read(id);
        //alagma timon
        patient.setActive(patientRepresentation.isActive());
        patient.setGender(patientRepresentation.isGender());
        patient.setHeight(patientRepresentation.getHeight());
        patient.setWeight(patientRepresentation.getWeight());
        patient.setFullName(patientRepresentation.getFullName());
        patient.setEmail(patientRepresentation.getEmail());
        patient.setPassword(patientRepresentation.getPassword());
        patient.setDateOfBirth(patientRepresentation.getDateOfBirth());

        //apothikefsi
        patientRepository.save(patient);


        PatientRepresentation patientRepresentation1 = new PatientRepresentation(patient);
        em.close();
        return patientRepresentation1;
    }


    @Delete("txt")
    public boolean deletePatient() {
        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        return patientRepository.delete(id);
    }
}
