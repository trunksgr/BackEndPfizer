package gr.codehub.pfizer.hibernate.resource;


import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Doctor;
import gr.codehub.pfizer.hibernate.repository.DoctorRepository;
import gr.codehub.pfizer.hibernate.representation.DoctorRepresentation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class RegisterDoctorResource extends ServerResource {


    @Post("json")
    public ApiResult<DoctorRepresentation> add(DoctorRepresentation doctorRepresentationIn) {


//
//        if (doctorRepresentationIn == null) return null;
//        if (doctorRepresentationIn.getFullName() == null) return null;
//        if (doctorRepresentationIn.getEmail() == null) return null;
//        if (doctorRepresentationIn.getPassword() == null) return null;
//        if (doctorRepresentationIn.getSpeciality() == null) return null;

        Doctor doctor = doctorRepresentationIn.createDoctor();
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        doctor.setRole("doctor");

        doctorRepository.save(doctor);
        DoctorRepresentation d = new DoctorRepresentation(doctor);
        return new ApiResult<>(d, 200, "ok");
    }
}
