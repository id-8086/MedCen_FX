package mainsrc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import javafx.scene.control.*;
import javafx.scene.layout.Region;


public class Controller {

    //[Composition]

    Main m = new Main();
    Main.MainClass mc = m.new MainClass();
    Main.MainClass.Doctor Doc = mc.new Doctor();
    Main.MainClass.Patient Pat = mc.new Patient();
    Main.MainClass.Consultation Con = mc.new Consultation();

    //[/Composition]


    public ComboBox<String> cbo_MedSpecialty = new ComboBox<>();
    public TextField txtField_DocLicNum;
    public TextField txtField_DocFirstNam;
    public TextField txtField_DocLastNam;
    public TextField txtField_DocPhoneNum;

    public TextField txtField_PatFirstNam;
    public TextField txtField_PatLastNam;
    public ComboBox<String> cbo_PatIdType = new ComboBox<>();
    public TextField txtField_PatIdNum;
    public DatePicker dtp_PatBirthDate;
    public RadioButton rbt_PatMale;
    public RadioButton rbt_PatFemale;
    public ComboBox<String> cbo_PatHealthInsurance = new ComboBox<>();
    public TextField txtField_PatPhoneNum;

    public DatePicker dtp_AppointDate;
    public CheckBox chkBox_FirstConsult;
    public Label lbl_InsuranceCost;
    public TextField txtField_ConsultationCost;
    public TextField txtField_InsuranceCost;
    public TextField txtField_FinalCost;
    public ComboBox<String> cbo_ApplyDiscount= new ComboBox<>();

    public Button btn_NewEntry;
    public Button btn_List;
    public Button btn_RecordData;
    public Button btn_Cancel;
    public Button btn_Stats;

    //[ComboBoxes Data]

    static ObservableList<String> MedSpecialties = FXCollections.observableArrayList(
            "Pediatrics",
            "Internal Medicine",
            "Traumatology",
            "Cardiology"
    );

    static ObservableList<String> DiscountOptions = FXCollections.observableArrayList(
            "No Discount",
            "10%",
            "15%",
            "20%",
            "25%"
    );

    static ObservableList<String> IdTypes = FXCollections.observableArrayList(
            "Social Security Card",
            "Driver's License",
            "Passport",
            "Department of Defense ID Card"
    );

    static ObservableList<String> HealthInsurances = FXCollections.observableArrayList(
            "No insurance",
            "Kaiser Foundation",
            "Caresource",
            "UnitedHealth",
            "Wellcare"
    );

    //[/ComboBoxes Data]


    private void calculateCost()
    {
        if (chkBox_FirstConsult.isSelected())
        {
            Con.pConsultType = 1;
        }
        else
            {
            Con.pConsultType = 2;
            }

        Pat.pHealthInsurance = cbo_PatHealthInsurance.getSelectionModel().getSelectedIndex() + 1;

        switch (cbo_ApplyDiscount.getSelectionModel().getSelectedIndex())
            {
                case 0:
                    Con.pDiscount = 1;
                    break;
                case 1:
                    Con.pDiscount = 1.10;
                    break;
                case 2:
                    Con.pDiscount = 1.15;
                    break;
                case 3:
                    Con.pDiscount = 1.20;
                    break;
                case 4:
                    Con.pDiscount = 1.25;
                    break;
            }



        Con.pDoctor = Doc;
        Con.pPatient = Pat;

        txtField_ConsultationCost.setText(Con.nDF.format(Con.pConsultationCost));

        if (Pat.pHealthInsurance != 1)
        {
            txtField_InsuranceCost.setText(Con.nDF.format(Con.consultationCost()));
        }
        else
        {
            txtField_InsuranceCost.setText("-");
        }

        txtField_FinalCost.setText(Con.nDF.format(Con.consultCalculateCost()));
    }

    private void clearFields()
    {
        txtField_PatFirstNam.setText("");
        txtField_PatLastNam.setText("");
        txtField_PatIdNum.setText("");
        txtField_PatPhoneNum.setText("");

        txtField_DocLicNum.setText("");
        txtField_DocFirstNam.setText("");
        txtField_DocLastNam.setText("");
        txtField_DocPhoneNum.setText("");

        txtField_ConsultationCost.setText("");
        txtField_InsuranceCost.setText("");
        txtField_FinalCost.setText("");

        rbt_PatMale.setSelected(false);
        rbt_PatFemale.setSelected(false);

        dtp_PatBirthDate.setValue(null);
        dtp_AppointDate.setValue(null);
    }



    @FXML
    private void initialize()
    {
        cbo_PatIdType.setItems(IdTypes);
        cbo_PatIdType.setValue("Social Security Card");
        cbo_MedSpecialty.setItems(MedSpecialties);
        cbo_MedSpecialty.setValue("Internal Medicine");
        cbo_ApplyDiscount.setItems(DiscountOptions);
        cbo_ApplyDiscount.setValue("No discount");
        cbo_PatHealthInsurance.setItems(HealthInsurances);
        cbo_PatHealthInsurance.setValue("No insurance");

        cbo_PatHealthInsurance.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) ->
                calculateCost()
        );

        cbo_ApplyDiscount.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) ->
                calculateCost()
        );

        chkBox_FirstConsult.selectedProperty().addListener((obs, wasChecked, isNowChecked) ->
                calculateCost()
        );


        controlsHabilitation(true);

        //txtField_ConsultationCost.setStyle(FxStyle.READ_ONLY);
    }


    public void controlsHabilitation(boolean x)
    {
        txtField_PatIdNum.setDisable(x);
        txtField_PatFirstNam.setDisable(x);
        txtField_PatLastNam.setDisable(x);
        txtField_PatPhoneNum.setDisable(x);

        txtField_DocLicNum.setDisable(x);
        txtField_DocFirstNam.setDisable(x);
        txtField_DocLastNam.setDisable(x);
        txtField_DocPhoneNum.setDisable(x);

        txtField_ConsultationCost.setDisable(x);
        txtField_InsuranceCost.setDisable(x);
        txtField_FinalCost.setDisable(x);

        rbt_PatMale.setDisable(x);
        rbt_PatFemale.setDisable(x);

        dtp_PatBirthDate.setDisable(x);
        dtp_AppointDate.setDisable(x);;

        cbo_PatIdType.setDisable(x);
        cbo_PatHealthInsurance.setDisable(x);
        cbo_MedSpecialty.setDisable(x);
        cbo_ApplyDiscount.setDisable(x);

        btn_NewEntry.setDisable(!x);
        btn_RecordData.setDisable(x);
        btn_Cancel.setDisable(x);

        chkBox_FirstConsult.setDisable(x);

    }


    @FXML
    private void btn_NewEntry_Click (ActionEvent event)
    {
        controlsHabilitation(false);
        chkBox_FirstConsult.setSelected(false);
        cbo_PatIdType.getSelectionModel().select(0);
        cbo_PatHealthInsurance.getSelectionModel().select(0);
        cbo_MedSpecialty.getSelectionModel().select(1);
        calculateCost();
    }

    @FXML
    private void btn_List_Click (ActionEvent event)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, Con.toStringConsultation(), ButtonType.OK);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.show();
    }

    @FXML
    private void btn_RecordData_Click (ActionEvent event)
    {


        Doc.pMedSpecialty = cbo_MedSpecialty.getSelectionModel().getSelectedIndex() + 1;
        Doc.pLicenseNumber = Integer.parseInt(txtField_DocLicNum.getText());
        Doc.pFirstName = txtField_DocFirstNam.getText();
        Doc.pLastName = txtField_DocLastNam.getText();
        Doc.pPhoneNumber = txtField_DocPhoneNum.getText();

        Pat.pFirstName = txtField_PatFirstNam.getText();
        Pat.pLastName = txtField_PatLastNam.getText();
        Pat.pIdType = cbo_PatIdType.getSelectionModel().getSelectedIndex() + 1;
        Pat.pIdNumber = Integer.parseInt(txtField_PatIdNum.getText());
        Pat.pBirthDate = dtp_PatBirthDate.getValue();
        Pat.pBiologicalSex = rbt_PatMale.isSelected();
        Pat.pHealthInsurance = cbo_PatHealthInsurance.getSelectionModel().getSelectedIndex() + 1;
        Pat.pPhoneNumber = txtField_PatPhoneNum.getText();



        Con.pConsultDate = dtp_AppointDate.getValue();
        Con.pPatient = Pat;
        Con.pDoctor = Doc;



        //System.out.println(Con.toStringConsultation());


        controlsHabilitation(true);
        btn_List.setDisable(false);
        btn_Stats.setDisable(false);
        clearFields();
        clearFields();
    }

    @FXML
    private void btn_Cancel_Click (ActionEvent event)
    {
        controlsHabilitation(true);
        clearFields();
        clearFields();
        cbo_ApplyDiscount.getSelectionModel().select(0);
        cbo_ApplyDiscount.getSelectionModel().select(0);
        if (Pat.pIdType >= 1)
        {
            btn_List.setDisable(false);
            btn_Stats.setDisable(false);
        }
        else
        {
            btn_List.setDisable(true);
            btn_Stats.setDisable(true);
        }

    }
}



