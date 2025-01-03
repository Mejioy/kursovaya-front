package ru.kafpin.lr6_bzz.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import lombok.Data;
import lombok.Setter;
import ru.kafpin.lr6_bzz.dao.AutomobileDao;
import ru.kafpin.lr6_bzz.dao.ClientDao;
import ru.kafpin.lr6_bzz.dao.EmployerDao;
import ru.kafpin.lr6_bzz.dao.ServiceDao;
import ru.kafpin.lr6_bzz.domains.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Data
public class EditProvidedServiceController {

    private ProvidedService providedService;
    private ResourceBundle bundle;
    private boolean action=false;
    private Stage editStage;

    private ObservableList<Client> clients = FXCollections.observableArrayList();
    private ObservableList<Automobile> automobiles = FXCollections.observableArrayList();
    private ObservableList<Service> services = FXCollections.observableArrayList();
    private ObservableList<Employer> employers = FXCollections.observableArrayList();

    private ServiceDao serviceDao;
    private EmployerDao employerDao;
    private AutomobileDao automobileDao;
    private ClientDao clientDao;

    @FXML
    private ComboBox<Automobile> cbAutomobile;
    @FXML
    private ComboBox<Client> cbClient;
    @FXML
    private ComboBox<Employer> cbEmployer;
    @FXML
    private ComboBox<Service> cbService;
    @FXML
    private DatePicker dpDone;
    @FXML
    void initialize(){
        try {
            clientDao = new ClientDao();

            serviceDao = new ServiceDao();

            employerDao = new EmployerDao();

            automobileDao = new AutomobileDao();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        cbClient.setItems(clients);
        cbService.setItems(services);
        cbEmployer.setItems(employers);
        dpDone.setValue(LocalDate.now());
    }
    @FXML
    void update(){
        cbClient.getSelectionModel().select(providedService.getAutomobile().getClient());
        automobiles.addAll(automobileDao.findALlActualCarsOfOwner(cbClient.getSelectionModel().getSelectedItem().getPhone()));
        cbAutomobile.setItems(automobiles);
        cbAutomobile.getSelectionModel().select(providedService.getAutomobile());
        cbService.getSelectionModel().select(providedService.getService());
        cbEmployer.getSelectionModel().select(providedService.getEmployer());
        dpDone.setValue(providedService.getDateOfProvide());
    }
    @FXML
    void onClientchanged(ActionEvent event) {
        automobiles.clear();
        cbAutomobile.setItems(automobiles);
        automobiles.addAll(automobileDao.findALlActualCarsOfOwner(cbClient.getSelectionModel().getSelectedItem().getPhone()));
    }
    @FXML
    void onCancel(ActionEvent event) {
        editStage.close();
    }
    private void Error(String text){
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(bundle.getString("error"));
        alert.setContentText(null);
        alert.setHeaderText(text);
        alert.showAndWait();
    }
    @FXML
    void onOK(ActionEvent event) {
        if (cbClient.getSelectionModel().getSelectedItem()==null)
            Error(bundle.getString("clientfieldempty"));
        else if (cbAutomobile.getSelectionModel().getSelectedItem()==null)
            Error(bundle.getString("automobilefieldempty"));
        else if (cbService.getSelectionModel().getSelectedItem()==null)
            Error(bundle.getString("servicefieldempty"));
        else if (cbEmployer.getSelectionModel().getSelectedItem()==null)
            Error(bundle.getString("employerfieldempty"));
        else
        {
            providedService.setAutomobile(cbAutomobile.getSelectionModel().getSelectedItem());
            providedService.setService(cbService.getSelectionModel().getSelectedItem());
            providedService.setEmployer(cbEmployer.getSelectionModel().getSelectedItem());
            providedService.setDateOfProvide(LocalDate.parse(dpDone.getValue().toString()));
            action=true;
            editStage.close();
        }
    }
}