package gr.codehub.pfizer.hibernate.repository;

import gr.codehub.pfizer.hibernate.model.Consultation;
import gr.codehub.pfizer.hibernate.model.Patient;
import gr.codehub.pfizer.hibernate.model.PatientsData;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class PatientsDataRepository extends Repository<PatientsData, Integer> {
    private EntityManager entityManager;
    public PatientsDataRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class getEntityClass() {
        return PatientsData.class;
    }

    @Override
    public String getClassName() {
        return PatientsData.class.getName();
    }

//   Avg(GlucoseLv)
//    CarbsIntake;

//    public List<Consultation> getConsultationByDate(Patient patient, Date from1, Date to) {
//        return entityManager.createQuery("SELECT c FROM PatientsData c WHERE c.Patient=:patient " +
//                        "AND c.Date>=:from1 AND c.Date<=:to"+"AND ",
//                Consultation.class)
//                .setParameter("patient", patient)
//                .setParameter("from1", from1)
//                .setParameter("to", to)
//                .getResultList();
//    }
//Avg(GlucoseLv)

    public List<Double> getPatientsDataGlucoseLv(Patient patient, Date from1a, Date toa) {
        return entityManager.createQuery("SELECT Avg(c.GlucoseLv) FROM PatientsData c WHERE c.Patient=:patient " +
                        "AND c.Date>=:from1a AND c.Date<=:toa",
                Double.class)
                .setParameter("patient", patient)
                .setParameter("from1a", from1a)
                .setParameter("toa", toa)
                .getResultList();
    }
}
