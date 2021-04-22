package gr.codehub.pfizer.hibernate.repository;


import gr.codehub.pfizer.hibernate.model.Consultation;
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








//    AND not Patient.Id in (select distinct PatientsData.Patient_Id "+
//            "from PatientsData where PatientsData.Date"+
//            "between dateadd(day, -30, getdate())
//    Consultation.Doctor_Id IS NOT  NULL

    public List<Patient> getPatientWithNoCon() {
        return entityManager.createNativeQuery("select Patient.* from Patient " +
                        "where not Patient.Id in (select distinct Consultation.Patient_Id " +
                        "from Consultation where Consultation.Date " +
                        "between dateadd(day, -30, getdate()) " +
                        "and getdate())",
                Patient.class)
                .getResultList();
    }



    public List<Patient> getInactivePatient(Date from1, Date to) {
        return entityManager.createNativeQuery("select Patient.* from Patient " +
                        "where not Patient.Id in (select  PatientsData.Patient_Id " +
                        "from PatientsData where PatientsData.Date " +
                        "between :from1 " +
                        "and :to )",
                Patient.class)
                .setParameter("from1", from1)
                .setParameter("to", to)
                .getResultList();
    }


    public List<Patient> getPatientDoctor(int id) {
        return entityManager.createNativeQuery("select Patient.* from Patient " +
                        "where  Patient.Id in (select distinct Consultation.Patient_Id " +
                        "from Consultation where Consultation.Doctor_Id=:id)",
                Patient.class)
                .setParameter("id", id)
                .getResultList();
    }




}





