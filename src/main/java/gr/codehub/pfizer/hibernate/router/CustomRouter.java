package gr.codehub.pfizer.hibernate.router;

import gr.codehub.pfizer.hibernate.resource.*;
import org.restlet.Application;
import org.restlet.routing.Router;

public class CustomRouter {
    private Application application;

    public CustomRouter(Application application) {
        this.application = application;
    }

    public Router publicResources() {
        Router router = new Router();
        router.attach("/login", LogInResource.class);
        router.attach("/registerDoctorResource", RegisterDoctorResource.class);
        router.attach("/RegisterPatientResource", RegisterPatientResource.class);

        return router;
    }

    public Router protectedResources() {
        Router router = new Router();
        router.attach("/patient", PatientListResource.class);
        router.attach("/doctor", DoctorListResource.class);
        router.attach("/patientsData", PatientsDataListResource.class);
        router.attach("/chief", ChiefListResource.class);

        router.attach("/patient/{id}", PatientResource.class);
        router.attach("/doctor/{id}", DoctorResource.class);
        router.attach("/consultation/{id}", ConsultationResource.class);
        router.attach("/chief/{id}", ChiefResource.class);


        //Doctor
        router.attach("/PatientByDoctorResource", PatientByDoctorResource.class);//Doctors search for his patients
        router.attach("/PatientWithNoCoResource", PatientWithNoCoResource.class);//Patients with no consultation the last 30 days
        router.attach("/PatientDoctorResource", PatientDoctorResource.class);//Doctors search for his patients


        //Patient
        router.attach("/PatientDataGlucoseLv", PatientDataGlucoseLvResource.class);//Avg GlucoseLv
        router.attach("/PatientDataCarbsIntake", PatientDataCarbsIntakeResource.class);//Avg carbs
        router.attach("/consultation", ConsultationListResource.class);//Patients  consultation over time range


        //Chief
        router.attach("/PatientsDataByDateResource", PatientsDataByDateResource.class);//Patients data for a specific time range
        router.attach("/consultation", ConsultationListResource.class);//Patients consultations for  a specific time range
        router.attach("/DoctorConsultationByDate", DoctorConsultationByDate.class);//Doctors consultations for  a specific time range
                                                                                               // Patients with no consultation the last 30 days
        router.attach("/InactivePatientResource", InactivePatientResource.class);//Inactive Patients for a specific time range
        router.attach("/InactiveDoctorResource", InactiveDoctorResource.class);//Inactive Doctors for a specific time range

        return router;
    }
}
