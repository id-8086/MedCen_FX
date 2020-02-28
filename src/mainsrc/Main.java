package mainsrc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("maingui.fxml"));
        primaryStage.setTitle("MedCen FX");
        primaryStage.setScene(new Scene(root, 736, 600));
        Application.setUserAgentStylesheet(STYLESHEET_MODENA);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public class MainClass {

        public class Person {

            public int pIdType = 0;
            public String pFirstName;
            public String pLastName;
            public LocalDate pBirthDate;
            public boolean pBiologicalSex = true;
            public String pPhoneNumber;
            public int pIdNumber = 0;


            public String IdType() {
                switch (pIdType) {
                    case 1:
                        return "Social Security Card";
                    case 2:
                        return "Driver's License";
                    case 3:
                        return "Passport";
                    default:
                        return "Department of Defense ID Card";
                }

            }

            public String BiologicalSex() {
                if (pBiologicalSex) {
                    return "Male";
                } else {
                    return "Female";
                }
            }


            public String toStringPerson() {
                return "First Name: " + pFirstName + "\nLast Name: " + pLastName +
                        "\nID Type: " + IdType() + "\nID Number: " + pIdNumber +
                        "\nBirth Date: " + pBirthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                        "\nBiological Sex: " + BiologicalSex() +
                        "\nPhone Number: " + pPhoneNumber;
            }

        }

        public class Doctor extends Person {

            public int pLicenseNumber = 0;
            public int pMedSpecialty = 0;


            public String MedSpecialty() {
                switch (pMedSpecialty) {
                    case 1:
                        return "Pediatrics";
                    case 2:
                        return "Internal Medicine";
                    case 3:
                        return "Traumatology";
                    default:
                        return "Cardiology";
                }

            }


            public String toStringDoctor() {
                return "License Number: " + pLicenseNumber + "\nMed Specialty: " + MedSpecialty() +
                        "\nFirst Name: " + pFirstName + "\nLast Name: " + pLastName + "\nPhone Number: " + pPhoneNumber;
            }

        }

        public class Patient extends Person {

            public int pHealthInsurance = 0;
            public int pInsuranceCost = 0;


            public String HealthInsurance() {
                switch (pHealthInsurance) {
                    case 1:
                        return "No insurance";
                    case 2:
                        return "Kaiser Foundation";
                    case 3:
                        return "Caresource";
                    case 4:
                        return "UnitedHealth";
                    default:
                        return "Wellcare";
                }

            }


            public String toStringPatient() {
                return "Patient's Data: " + "\n" + toStringPerson() + "\nHealth Insurance: " + HealthInsurance();
            }

        }

        public class Consultation {

            public LocalDate pConsultDate;
            public int pConsultType = 0;
            public double pFinalCost = 0.0;
            public double pConsultationCost = 850;
            public Doctor pDoctor;
            public Patient pPatient;
            public String pAdditional;
            DecimalFormat nDF = new DecimalFormat("###.##");
            public double pDiscount = 1.0;

            public String consultType() {
                if (pConsultType == 1) {
                    return "First Consult";
                } else {
                    return "Regular Patient";
                }
            }

            public String withDiscount() {
                if (pDiscount > 1.0) {
                    return "Discount: " + nDF.format(pDiscount - 1)  + "%";
                } else {
                    return "";
                }
            }

            public String withInsurance(){
                if (pPatient.pHealthInsurance != 1) {
                    return "$" + nDF.format(consultationCost());
                } else {
                    return "-";
                }
            }


            public String toStringConsultation() {

                pAdditional = "\nAdditional 5% Charge";

                            return pPatient.toStringPatient() + "\n" + "\n" +
                                    "Physician's Data: " +
                                    "\n" + pDoctor.toStringDoctor() + "\n" + "\n" +
                                    "Consultation Data: " + "\n" + consultType() + pAdditional +
                                    "\nAppointment Date: " + pConsultDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                                    "\nConsultation Cost: " + "$" + nDF.format(pConsultationCost) +
                                    "\nCost (with Insurance): " + withInsurance() +
                                    "\n" + withDiscount() +
                                    "\nFinal Cost: " + "$" + nDF.format(consultCalculateCost());
            }


            public double consultCalculateCost()
            {
                double _total = consultationCost();

                if (pConsultType == 1)
                {
                    _total = _total * 1.05;
                }

                _total = _total / pDiscount;

                return _total;
            }

            public double consultationCost()
            {
                double _total = 0;
                switch (pPatient.pHealthInsurance)
                {
                    case 1:
                        _total = 850;
                        break;
                    case 2:
                        _total = 250;
                        break;
                    case 3:
                        _total = 100;
                        break;
                    case 4:
                        _total = 300;
                        break;
                    case 5:
                        _total = 460;
                        break;
                }

                return _total;

            }

        }
    }

}





