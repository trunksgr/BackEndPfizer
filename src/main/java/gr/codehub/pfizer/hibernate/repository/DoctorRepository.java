package gr.codehub.pfizer.hibernate.repository;


import gr.codehub.pfizer.hibernate.model.Doctor;
import gr.codehub.pfizer.hibernate.model.Patient;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;


public class DoctorRepository extends Repository<Doctor, Integer> {

    private EntityManager entityManager;

    public DoctorRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class getEntityClass() {
        return Doctor.class;
    }

    @Override
    public String getClassName() {
        return Doctor.class.getName();
    }


    public Doctor getByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT b FROM Doctor b " +
                    "WHERE b.Email = :username", Doctor.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Integer getByUsernamePasswordDoctor(String username, String pass) {
        try {
            Integer list = entityManager.createQuery("SELECT b.Id FROM Doctor b " +
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

    public List<Doctor> getInactiveDoctor(Date from1, Date to) {
        return entityManager.createNativeQuery("select Doctor.* from Doctor " +
                        "where not Doctor.Id in (select distinct Consultation.Doctor_Id " +
                        "from Consultation where Consultation.Date " +
                        "between :from1 " +
                        "and :to )",
                Doctor.class)
                .setParameter("from1", from1)
                .setParameter("to", to)
                .getResultList();
    }


}

