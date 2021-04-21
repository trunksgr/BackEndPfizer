package gr.codehub.pfizer.hibernate.repository;


import gr.codehub.pfizer.hibernate.model.Doctor;
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


    public List<Patient> getPatientByDoctor(Doctor doctor) {
        return entityManager.createQuery("SELECT p FROM Patient p WHERE p.Doctor=:doctor ",
                Patient.class)
                .setParameter("doctor", doctor)
                .getResultList();
    }





    public List<Patient> getPatientWithNoCon() {
        return entityManager.createNativeQuery("select Patient.* from Patient " +
                        "where not Patient.Id in (select distinct Consultation.Patient_Id " +
                        "from Consultation where Consultation.Date " +
                        "between dateadd(day, -30, getdate()) " +
                        "and getdate() )",
                Patient.class)
                .getResultList();
    }


    }





