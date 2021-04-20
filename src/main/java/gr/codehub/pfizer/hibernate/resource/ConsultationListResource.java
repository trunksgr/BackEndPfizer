package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.exception.AuthorizationException;
import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Consultation;
import gr.codehub.pfizer.hibernate.model.Patient;
import gr.codehub.pfizer.hibernate.repository.ConsultationRepository;
import gr.codehub.pfizer.hibernate.representation.ConsultationRepresentation;
import gr.codehub.pfizer.hibernate.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConsultationListResource extends ServerResource {

    @Get("json")
    public ApiResult<List<ConsultationRepresentation>> getConsultation() throws ParseException {

        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);

        } catch (AuthorizationException e) {
            try {
                ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
            } catch (AuthorizationException d) {
                return new ApiResult<>(null, 500, e.getMessage());
            }
        }


        Date from1 = new SimpleDateFormat("dd-M-yyyy").parse(getQueryValue("from1"));
        Date to = new SimpleDateFormat("dd-M-yyyy").parse(getQueryValue("to"));

        int patient_Id = Integer.parseInt(getQueryValue("patient_Id"));


        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);
        Patient patient = new Patient();
        patient.setId(patient_Id);

        List<Consultation> consultation = consultationRepository.getConsultationByDate(patient, from1, to);
        em.close();

        List<ConsultationRepresentation> consultationRepresentationList =
                consultation.stream()
                        .map(p -> new ConsultationRepresentation(p))
                        .collect(toList());
        return new ApiResult<>(consultationRepresentationList, 200, "ok");
    }

    @Post("json")
    public ApiResult<ConsultationRepresentation> add(ConsultationRepresentation consultationRepresentationIn) {

        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);

        } catch (AuthorizationException e) {
            return new ApiResult<>(null, 500, e.getMessage());
        }


        if (consultationRepresentationIn == null) return null;
        if (consultationRepresentationIn.getDate() == null) return null;
        if (consultationRepresentationIn.getDescription() == null) return null;


        Consultation consultation = consultationRepresentationIn.createConsultation();
        EntityManager em = JpaUtil.getEntityManager();
        ConsultationRepository consultationRepository = new ConsultationRepository(em);

        consultationRepository.save(consultation);
        ConsultationRepresentation cn = new ConsultationRepresentation(consultation);
        return new ApiResult<>(cn, 200, "ok");
    }

}


