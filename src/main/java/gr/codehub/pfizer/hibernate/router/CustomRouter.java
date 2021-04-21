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


        //giatros
        router.attach("/PatientByDoctorResource", PatientByDoctorResource.class);//o giatros na briskei tous asthenis tou
        router.attach("/PatientWithNoCoResource", PatientWithNoCoResource.class);//astheneis xoris consltation meta apo 30 meres


        //asthenis
        router.attach("/PatientDataGlucoseLv", PatientDataGlucoseLvResource.class);//avg glikozi
        router.attach("/PatientDataCarbsIntake", PatientDataCarbsIntakeResource.class);//averege carbs
        router.attach("/consultation", ConsultationListResource.class);//ta kolsatation tou astheni se siggekrimeno ebros xronou


        //admin
        router.attach("/PatientsDataByDateResource", PatientsDataByDateResource.class);//data astheni se siggekrimeno xrono
        router.attach("/consultation", ConsultationListResource.class);//ta kolsatation tou astheni se siggekrimeno ebros xronou
        router.attach("/DoctorConsultationByDate", DoctorConsultationByDate.class);//ta kolsatation tou giatrou se siggekrimeno ebros xronou         // astheneis xoris consltation meta apo 30 meres(idio me tou giatrou)
        router.attach("/InactivePatientResource", InactivePatientResource.class);//anenergoi se siggekrimeno xrono asthenois
        router.attach("/InactiveDoctorResource", InactiveDoctorResource.class);//anenergoi se siggekrimeno xrono    giatroi

        return router;
    }
}
