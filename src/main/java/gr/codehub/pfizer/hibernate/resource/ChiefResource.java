package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Chief;
import gr.codehub.pfizer.hibernate.repository.ChiefRepository;
import gr.codehub.pfizer.hibernate.representation.ChiefRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class ChiefResource extends ServerResource {
    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
    }

    @Get("json")
    public ChiefRepresentation getChief() {
        EntityManager em = JpaUtil.getEntityManager();
        ChiefRepository chiefRepository = new ChiefRepository(em);
        Chief chief = chiefRepository.read(id);
        ChiefRepresentation chiefRepresentation1 = new ChiefRepresentation(chief);
        em.close();
        return chiefRepresentation1;
    }

    //update
    @Put("json")
    public ChiefRepresentation putChief(ChiefRepresentation chiefRepresentation) {
        //diabasma
        EntityManager em = JpaUtil.getEntityManager();
        ChiefRepository chiefRepository = new ChiefRepository(em);
        Chief chief = chiefRepository.read(id);
        //alagma timon

        chief.setFullName(chiefRepresentation.getFullName());
        chief.setEmail(chiefRepresentation.getEmail());
        chief.setPassword(chiefRepresentation.getPassword());

        chiefRepository.save(chief);


        ChiefRepresentation chiefRepresentation1 = new ChiefRepresentation(chief);
        em.close();
        return chiefRepresentation1;
    }


    @Delete("txt")
    public boolean deleteChief() {
        EntityManager em = JpaUtil.getEntityManager();
        ChiefRepository chiefRepository = new ChiefRepository(em);
        return chiefRepository.delete(id);
    }
}

