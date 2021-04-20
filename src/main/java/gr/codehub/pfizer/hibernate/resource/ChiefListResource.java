package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Chief;
import gr.codehub.pfizer.hibernate.repository.ChiefRepository;
import gr.codehub.pfizer.hibernate.representation.ChiefRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


public class ChiefListResource extends ServerResource {

    @Get("json")
    public List<ChiefRepresentation> getChief() {
        EntityManager em = JpaUtil.getEntityManager();
        ChiefRepository chiefRepository = new ChiefRepository(em);
        List<Chief> chief = chiefRepository.findAll();
        em.close();


        List<ChiefRepresentation> chiefRepresentationList = new ArrayList<>();
        for (Chief c : chief)
            chiefRepresentationList.add(new ChiefRepresentation(c));
        return chiefRepresentationList;
    }

    @Post("json")
    public ChiefRepresentation add(ChiefRepresentation chiefRepresentationIn) {

        if (chiefRepresentationIn == null) return null;
        if (chiefRepresentationIn.getEmail().indexOf("@") == -1) return null;
        if (chiefRepresentationIn.getFullName() == null) return null;
        if (chiefRepresentationIn.getPassword() == null) return null;


        Chief chief = chiefRepresentationIn.createChief();
        EntityManager em = JpaUtil.getEntityManager();
        ChiefRepository chiefRepository = new ChiefRepository(em);

        chiefRepository.save(chief);
        ChiefRepresentation c = new ChiefRepresentation(chief);
        return c;
    }

}

