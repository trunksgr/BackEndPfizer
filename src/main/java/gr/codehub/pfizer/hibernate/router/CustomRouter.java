package gr.codehub.pfizer.hibernate.router;

import gr.codehub.pfizer.hibernate.representation.PatientRepresentation;
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
    public Router protectedResources(){
        Router router = new Router();
        router.attach("/patient", PatientListResource.class);
        router.attach("/doctor", DoctorListResource.class);
        router.attach("/consultation", ConsultationListResource.class);
        router.attach("/patientsData", PatientsDataListResource.class);
        router.attach("/chief", ChiefListResource.class);

        router.attach("/patient/{id}", PatientResource.class);
        router.attach("/doctor/{id}", DoctorResource.class);
        router.attach("/consultation/{id}", ConsultationResource.class);
        router.attach("/chief/{id}", ChiefResource.class);


        router.attach("/PatientWithNoCoResource", PatientWithNoCoResource.class);
        router.attach("/DoctorConsultationsResource", DoctorConsultationsResource.class);
        router.attach("/PatientDataGlucoseLv", PatientDataDateListResource.class);
        router.attach("/PatientDataGlucoseLv", PatientDataDateListResource.class);


     //   router.attach("/consultation/{PatientId}/{date1}/{date2}", ConsultationListResource.class);
       // router.attach("/patientData/{PatientId}/{date1}/{date2}", PatientsDataListResource.class);

        return router;
    }
}
