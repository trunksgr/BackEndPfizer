package gr.codehub.pfizer.hibernate.security;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Chief;
import gr.codehub.pfizer.hibernate.model.Doctor;
import gr.codehub.pfizer.hibernate.model.Patient;
import gr.codehub.pfizer.hibernate.repository.ChiefRepository;
import gr.codehub.pfizer.hibernate.repository.DoctorRepository;
import gr.codehub.pfizer.hibernate.repository.PatientRepository;
import org.restlet.Request;
import org.restlet.security.Role;
import org.restlet.security.SecretVerifier;
import javax.persistence.EntityManager;



public class CustomVerifier extends SecretVerifier {


    @Override
    public int verify(String username, char[] password) {

        EntityManager em = JpaUtil.getEntityManager();
        PatientRepository patientRepository = new PatientRepository(em);
        Patient patient = patientRepository.getByUsername(username);

        EntityManager emD = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(emD);
        Doctor doctor = doctorRepository.getByUsername(username);


        EntityManager emC = JpaUtil.getEntityManager();
        ChiefRepository chiefRepository = new ChiefRepository(emC);
        Chief chief = chiefRepository.getByUsername(username);


        if ((patient == null) && (doctor == null) && (chief == null)) {
            return SecretVerifier.RESULT_INVALID;
        } else if (patient != null) {
            String passwordInDb = patient.getPassword();
            if (compare(passwordInDb.toCharArray(), password)) {
                Request request = Request.getCurrent();
                request.getClientInfo().getRoles().add
                        (new Role(patient.getRole()));
                return SecretVerifier.RESULT_VALID;
            }
            return SecretVerifier.RESULT_INVALID;
        } else if (doctor != null) {
            String passwordInDb = doctor.getPassword();
            if (compare(passwordInDb.toCharArray(), password)) {
                Request request = Request.getCurrent();
                request.getClientInfo().getRoles().add
                        (new Role(doctor.getRole()));
                return SecretVerifier.RESULT_VALID;
            }
            return SecretVerifier.RESULT_INVALID;
        } else {

            String passwordInDb = chief.getPassword();
            if (compare(passwordInDb.toCharArray(), password)) {
                Request request = Request.getCurrent();
                request.getClientInfo().getRoles().add
                        (new Role(chief.getRole()));
                return SecretVerifier.RESULT_VALID;
            }
            return SecretVerifier.RESULT_INVALID;
        }

    }
}
