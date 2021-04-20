package gr.codehub.pfizer.hibernate.repository;
import gr.codehub.pfizer.hibernate.model.Patient;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

public class PatientRepository extends Repository<Patient, Integer> {
    private EntityManager entityManager;

    public PatientRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class getEntityClass() {
        return Patient.class;
    }

    @Override
    public String getClassName() {
        return Patient.class.getName();
    }

    public Patient getByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT b FROM Patient b " +
                    "WHERE b.Email = :username", Patient.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Integer getByUsernamePasswordPatient(String username, String pass) {
        try {
            Integer list = entityManager.createQuery("SELECT b.Id FROM Patient b " +
                    "WHERE b.Email = :username AND b.Password = :pass", Integer.class)
                    .setParameter("username", username)
                    .setParameter("pass", pass)
                    .getSingleResult();
            if (list == null) {
                return null;
            } else
                return list;
        } catch (Exception e) {
            return null;
        }
    }


    public List<Patient> getConsultationWithNo(Patient patient, Date from1, Date to) {
        return entityManager.createQuery("SELECT c FROM Patient c WHERE " +
                        "AND c.Date>=:from1 AND c.Date<=:to",
                Patient.class)
                .setParameter("patient", patient)
                .setParameter("from1", from1)
                .setParameter("to", to)
                .getResultList();
    }


    public List<Patient> getPatientWithNoCon() {
        return entityManager.createQuery("select p  from Patient" +
                        "where not p.Patient_id in (select distinct Patient_id" +
                        "from Consultation where Consultation.Date" +
                        "between Date add(day, -30, getDate())" +
                        "and getDate() )",
                Patient.class)
                .getResultList();
    }


}
