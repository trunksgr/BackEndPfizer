package gr.codehub.pfizer.hibernate.repository;

import gr.codehub.pfizer.hibernate.model.Consultation;
import gr.codehub.pfizer.hibernate.model.Doctor;
import gr.codehub.pfizer.hibernate.model.Patient;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class ConsultationRepository extends Repository<Consultation, Integer> {
    private EntityManager entityManager;

    public ConsultationRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class getEntityClass() {
        return Consultation.class;
    }

    @Override
    public String getClassName() {
        return Consultation.class.getName();
    }

//    public List<Consultation> getConsultationByDate(int id, Date from, Date to) {
//        return entityManager.createQuery("SELECT c from Consultation c where " +
//                "c.Patient.Id= :id AND c.Date>=:from AND  c.Date<=:to", Consultation.class)
//                .setParameter("from", from)
//                .setParameter("to", to)
//                .setParameter("Patient.Id", id)
//                .getResultList();
//    }


    public List<Consultation> getConsultationByDate(Patient patient, Date from1, Date to) {
        return entityManager.createQuery("SELECT c FROM Consultation c WHERE c.Patient=:patient " +
                        "AND c.Date>=:from1 AND c.Date<=:to",
                Consultation.class)
                .setParameter("patient", patient)
                .setParameter("from1", from1)
                .setParameter("to", to)
                .getResultList();
    }


//    public List<Consultation> getConsultationByDoctor(Doctor doctor) {
//        return entityManager.createQuery("SELECT c FROM Consultation c WHERE c.Doctor=:doctor ",
//                Consultation.class)
//                .setParameter("doctor", doctor)
//
//                .getResultList();
//    }


    public List<Consultation> getConsultationWithNo(Patient patient, Date from1, Date to) {
        return entityManager.createQuery("SELECT c FROM Consultation c WHERE c.Patient=:patient " +
                        "AND c.Date>=:from1 AND c.Date<=:to",
                Consultation.class)
                .setParameter("patient", patient)
                .setParameter("from1", from1)
                .setParameter("to", to)
                .getResultList();
    }


}