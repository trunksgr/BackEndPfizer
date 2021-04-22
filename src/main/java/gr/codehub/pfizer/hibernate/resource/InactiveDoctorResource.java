package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.exception.AuthorizationException;
import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Doctor;
import gr.codehub.pfizer.hibernate.model.Patient;
import gr.codehub.pfizer.hibernate.repository.DoctorRepository;
import gr.codehub.pfizer.hibernate.repository.PatientRepository;
import gr.codehub.pfizer.hibernate.representation.DoctorRepresentation;
import gr.codehub.pfizer.hibernate.representation.PatientRepresentation;
import gr.codehub.pfizer.hibernate.security.Shield;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class InactiveDoctorResource extends ServerResource {





    @Get("json")
    public ApiResult<List<DoctorRepresentation>> getInactiveDoctorResource() throws ParseException {

        try {
            ResourceUtils.checkRole(this, Shield.ROLE_DOCTOR);

        } catch (AuthorizationException e) {
            try {
                ResourceUtils.checkRole(this, Shield.ROLE_ADMIN);
            } catch (AuthorizationException d) {
                return new ApiResult<>(null, 500, e.getMessage());
            }
        }

        Date from1 = new SimpleDateFormat("yyyy-M-d").parse(getQueryValue("from1"));
        Date to = new SimpleDateFormat("yyyy-M-d").parse(getQueryValue("to"));

        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);


        List<Doctor> doctor = doctorRepository.getInactiveDoctor(from1,to);
        em.close();

        List<DoctorRepresentation> doctorRepresentationList =
                doctor.stream()
                        .map(p -> new DoctorRepresentation(p))
                        .collect(toList());
        return new ApiResult<>(doctorRepresentationList, 200, "ok");
    }
}
