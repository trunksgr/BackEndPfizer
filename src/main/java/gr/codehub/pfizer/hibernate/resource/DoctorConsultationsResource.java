package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.exception.AuthorizationException;
import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Consultation;
import gr.codehub.pfizer.hibernate.model.Doctor;
import gr.codehub.pfizer.hibernate.repository.ConsultationRepository;
import gr.codehub.pfizer.hibernate.representation.ConsultationRepresentation;
import gr.codehub.pfizer.hibernate.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import javax.persistence.EntityManager;
import java.text.ParseException;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class DoctorConsultationsResource extends ServerResource {



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



            int doctor_Id = Integer.parseInt(getQueryValue("doctor_Id"));


            EntityManager em = JpaUtil.getEntityManager();
            ConsultationRepository consultationRepository = new ConsultationRepository(em);
            Doctor doctor = new Doctor();
            doctor.setId(doctor_Id);

            List<Consultation> consultation = consultationRepository.getConsultationByDoctor(doctor);
            em.close();

            List<ConsultationRepresentation> consultationRepresentationList =
                    consultation.stream()
                            .map(p -> new ConsultationRepresentation(p))
                            .collect(toList());
            return new ApiResult<>(consultationRepresentationList, 200, "ok");
        }


}
