package ru.kafpin.lr6_bzz.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ru.kafpin.lr6_bzz.dao.AutomobileDao;
import ru.kafpin.lr6_bzz.dao.ClientDao;
import ru.kafpin.lr6_bzz.dao.EmployerDao;
import ru.kafpin.lr6_bzz.dao.ServiceDao;
import ru.kafpin.lr6_bzz.domains.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditProvidedServiceController {
    private ProvidedService providedService;
    public void setEditStage(Stage editStage) {
        this.editStage = editStage;
    }
    private ResourceBundle bundle;
    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }
    public boolean isAction() {
        return action;
    }
    private boolean action=false;
    private Stage editStage;
    public void setProvidedService(ProvidedService providedService) {
        this.providedService = providedService;
    }
    public void setClientList(ObservableList<Client> clients){
        this.clients = clients;
    }
    protected ObservableList<Client> clients = FXCollections.observableArrayList();
    public void setEmployerList(ObservableList<Employer> employers){
        this.employers = employers;
    }
    public void setServiceList(ObservableList<Service> services){
        this.services = services;
    }
    protected ObservableList<Automobile> automobiles = FXCollections.observableArrayList();
    protected ObservableList<Service> services = FXCollections.observableArrayList();
    protected ObservableList<Employer> employers = FXCollections.observableArrayList();
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
        cbClient.getSelectionModel().select(clientDao.findById(automobileDao.findById(providedService.getIdautomobile()).getIdclient()));
        automobiles.addAll(automobileDao.findALlCarsOfOwner(cbClient.getSelectionModel().getSelectedItem().getClientID()));
        cbAutomobile.setItems(automobiles);
        cbAutomobile.getSelectionModel().select(automobileDao.findById(providedService.getIdautomobile()));
        cbService.getSelectionModel().select(serviceDao.findById(providedService.getIdservice()));
        cbEmployer.getSelectionModel().select(employerDao.findById(providedService.getIdemployer()));
        dpDone.setValue(providedService.getSqlDateTime().toLocalDate());
    }
    @FXML
    void onClientchanged(ActionEvent event) {
        automobiles.clear();
        cbAutomobile.setItems(automobiles);
        automobiles.addAll(automobileDao.findALlCarsOfOwner(cbClient.getSelectionModel().getSelectedItem().getClientID()));
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
            providedService.setIdautomobile(cbAutomobile.getSelectionModel().getSelectedItem().getAutomobileID());
            providedService.setIdservice(cbService.getSelectionModel().getSelectedItem().getServiceID());
            providedService.setIdemployer(cbEmployer.getSelectionModel().getSelectedItem().getEmployerID());
            providedService.setDatetime(new java.util.Date(java.sql.Date.valueOf(dpDone.getValue()).getTime()));
            action=true;
            editStage.close();
        }
    }
}