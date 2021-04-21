package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Consultation;
import gr.codehub.pfizer.hibernate.model.Patient;
import gr.codehub.pfizer.hibernate.repository.ConsultationRepository;
import gr.codehub.pfizer.hibernate.repository.PatientRepository;
import gr.codehub.pfizer.hibernate.representation.ConsultationRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;


public class ConsultationResource extends ServerResource {
    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
    }

    @Get("json")
    public ConsultationRepresentation getConsultation() {
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        Consultation consultation = consultationRepository.read(id);
        ConsultationRepresentation consultationRepresentation1 = new ConsultationRepresentation(consultation);
        em.close();
        return consultationRepresentation1;
    }


    @Put("json")
    public ConsultationRepresentation putConsultation(ConsultationRepresentation consultationRepresentation) {
        //diabasma
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        Consultation consultation = consultationRepository.read(id);
        consultation.setDescription(consultationRepresentation.getDescription());
        consultation.setDate(consultationRepresentation.getDate());
        consultationRepository.save(consultation);
        ConsultationRepresentation consultationRepresentation1 = new ConsultationRepresentation(consultation);
        em.close();
        return consultationRepresentation1;
    }


    @Delete("txt")
    public boolean deleteConsultation() {
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        return consultationRepository.delete(id);
    }
}