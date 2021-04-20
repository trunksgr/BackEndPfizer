package gr.codehub.pfizer.hibernate.resource;

import gr.codehub.pfizer.hibernate.jpautil.JpaUtil;
import gr.codehub.pfizer.hibernate.model.Doctor;
import gr.codehub.pfizer.hibernate.repository.DoctorRepository;
import gr.codehub.pfizer.hibernate.representation.DoctorRepresentation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

import javax.persistence.EntityManager;

public class DoctorResource extends ServerResource {
    private int id;

    @Override
    protected void doInit() {
        id = Integer.parseInt(getAttribute("id"));
    }

    @Get("json")
    public DoctorRepresentation getDoctor() {
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        Doctor doctor = doctorRepository.read(id);
        DoctorRepresentation doctorRepresentation1 = new DoctorRepresentation(doctor);
        em.close();
        return doctorRepresentation1;
    }


    //update
    @Put("json")
    public DoctorRepresentation putDoctor(DoctorRepresentation doctorRepresentation) {
        //diabasma
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        Doctor doctor = doctorRepository.read(id);
        //alagma timon
        doctor.setActive(doctorRepresentation.isActive());
        doctor.setFullName(doctorRepresentation.getFullName());
        doctor.setEmail(doctorRepresentation.getEmail());
        doctor.setPassword(doctorRepresentation.getPassword());
        doctor.setSpeciality(doctorRepresentation.getSpeciality());
        //apothikefsi
        doctorRepository.save(doctor);


        DoctorRepresentation doctorRepresentation1 = new DoctorRepresentation(doctor);
        em.close();
        return doctorRepresentation1;
    }


    @Delete("txt")
    public boolean deleteDoctor() {
        EntityManager em = JpaUtil.getEntityManager();
        DoctorRepository doctorRepository = new DoctorRepository(em);
        return doctorRepository.delete(id);
    }
}

