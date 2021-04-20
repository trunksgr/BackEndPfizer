package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.exception.AuthorizationException;
import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.PatientsData;
import gr.codehub.pfizer.hibernate.repository.PatientsDataRepository;
import gr.codehub.pfizer.hibernate.representation.PatientsDataRepresentation;
import gr.codehub.pfizer.hibernate.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


public class PatientsDataListResource extends ServerResource {

    @Get("json")
    public ApiResult<List<PatientsDataRepresentation>> getPatientsData() {

        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);

        } catch (AuthorizationException e) {
            try {
                ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
            } catch (AuthorizationException d) {
                return new ApiResult<>(null, 500, e.getMessage());
            }
        }


        EntityManager em = JpaUtil.getEntityManager();
        PatientsDataRepository patientsDataRepository = new PatientsDataRepository(em);
        List<PatientsData> patientsData = patientsDataRepository.findAll();
        em.close();


        List<PatientsDataRepresentation> patientsDataRepresentationList = new ArrayList<>();
        for (PatientsData p : patientsData)
            patientsDataRepresentationList.add(new PatientsDataRepresentation(p));
        return new ApiResult<>(patientsDataRepresentationList, 200, "ok");
    }

    @Post("json")
    public ApiResult<PatientsDataRepresentation> add(PatientsDataRepresentation patientsDataRepresentationIn) {


        PatientsData patientsData = patientsDataRepresentationIn.createPatientsData();
        EntityManager em = JpaUtil.getEntityManager();
        PatientsDataRepository patientsDataRepository = new PatientsDataRepository(em);

        patientsDataRepository.save(patientsData);
        PatientsDataRepresentation pd = new PatientsDataRepresentation(patientsData);
        return new ApiResult<>(pd, 200, "ok");
    }
}