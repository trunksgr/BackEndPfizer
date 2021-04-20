package gr.codehub.pfizer.hibernate.repository;


import gr.codehub.pfizer.hibernate.model.Doctor;
import javax.persistence.EntityManager;


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


}

