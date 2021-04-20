package gr.codehub.pfizer.hibernate.resource;


import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.repository.ChiefRepository;
import gr.codehub.pfizer.hibernate.repository.DoctorRepository;
import gr.codehub.pfizer.hibernate.repository.PatientRepository;
import gr.codehub.pfizer.hibernate.representation.LogInRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;


public class LogInResource extends ServerResource {
    private DoctorRepository d;
    private ChiefRepository c;
    private PatientRepository p;

    @Post("json")
    public Integer add(LogInRepresentation logIn) {

        d = new DoctorRepository(JpaUtil.getEntityManager());
        c = new ChiefRepository(JpaUtil.getEntityManager());
        p = new PatientRepository(JpaUtil.getEntityManager());

        if (logIn == null)
            return null;
        Integer patient = p.getByUsernamePasswordPatient(logIn.getEmail(), logIn.getPassword());
        Integer doctor = d.getByUsernamePasswordDoctor(logIn.getEmail(), logIn.getPassword());
        Integer chief = c.getByUsernamePasswordChief(logIn.getEmail(), logIn.getPassword());


        if ((patient == null) && (doctor == null) && (chief == null)) {
            return null;
        } else if ((patient != null)) {
            return patient;
        } else if (doctor != null) {
            return doctor;
        } else
            return chief;

    }

}
