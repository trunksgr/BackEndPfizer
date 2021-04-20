package gr.codehub.pfizer.hibernate.repository;

import gr.codehub.pfizer.hibernate.model.Chief;
import gr.codehub.pfizer.hibernate.model.Doctor;
import gr.codehub.pfizer.hibernate.model.Patient;

import javax.persistence.EntityManager;
import java.util.List;

public class ChiefRepository extends Repository<Chief, Integer> {
    private EntityManager entityManager;

    public ChiefRepository(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Class getEntityClass() {
        return Chief.class;
    }

    @Override
    public String getClassName() {
        return Chief.class.getName();
    }

    public Chief getByUsername(String username) {
        try {
            return entityManager.createQuery("SELECT b FROM Chief b " +
                    "WHERE b.Email = :username", Chief.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }


    public Integer getByUsernamePasswordChief(String username,String pass) {
        try {
            Integer list=  entityManager.createQuery("SELECT b.Id FROM Chief b " +
                    "WHERE b.Email = :username AND b.Password = :pass", Integer.class)
                    .setParameter("username", username)
                    .setParameter("pass", pass)
                    .getSingleResult();
            if (list==null){
                return null;
            }
            else
                return list;
        }catch (Exception e){return  null;}
    }

}