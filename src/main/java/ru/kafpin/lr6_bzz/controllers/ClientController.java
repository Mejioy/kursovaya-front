package ru.kafpin.lr6_bzz.controllers;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.Setter;
import ru.kafpin.lr6_bzz.dao.AutomobileDao;
import ru.kafpin.lr6_bzz.dao.ProvidedServiceDao;
import ru.kafpin.lr6_bzz.dao.ServiceDao;
import ru.kafpin.lr6_bzz.domains.Automobile;
import ru.kafpin.lr6_bzz.domains.ProvidedService;
import ru.kafpin.lr6_bzz.domains.Service;

import java.util.Date;

import static ru.kafpin.lr6_bzz.MainApplication.login;

public class ClientController {
    @Setter
    private Stage clientStage;

    @FXML
    private TableColumn<Service, String> tcDescription;
    @FXML
    private TableColumn<Service, String> tcName;
    @FXML
    private TableColumn<Service, Integer> tcPrice;
    @FXML
    private TableView<Service> tvServices;

    @FXML
    private ComboBox<Automobile> cbAutomobile;
    @FXML
    private TableColumn<ProvidedService, Date> tcDatetime;
    @FXML
    private TableColumn<ProvidedService, String> tcEmployerFIO;
    @FXML
    private TableColumn<ProvidedService, String> tcNameOfProvided;
    @FXML
    private TableColumn<ProvidedService, Integer> tcPriceOfProvided;

    @FXML
    private TableView<ProvidedService> tvProvidedServices;

    private ServiceDao serviceDao;
    private AutomobileDao automobileDao;
    private ProvidedServiceDao providedServiceDao;
    public ClientController(){
        try {
            serviceDao = new ServiceDao();
            providedServiceDao = new ProvidedServiceDao();
            automobileDao = new AutomobileDao();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<Service> services = FXCollections.observableArrayList();

    private ObservableList<ProvidedService> providedServices = FXCollections.observableArrayList();

    private ObservableList<Automobile> automobilesOfClient = FXCollections.observableArrayList();


    @FXML
    void initialize() {
        services.addAll(serviceDao.findALl());
        tcName.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getName()));
        tcPrice.setCellValueFactory(s -> new SimpleObjectProperty<Integer>(s.getValue().getPrice()));
        tcDescription.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getDescription()));
        tvServices.setItems(services);
        tvServices.getSortOrder().add(tcName);
        tvServices.getSortOrder().add(tcPrice);

        automobilesOfClient.addAll(automobileDao.findALlCarsOfOwner(login));
        cbAutomobile.setItems(automobilesOfClient);

        tcNameOfProvided.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getService().getName()));
        tcPriceOfProvided.setCellValueFactory(s -> new SimpleObjectProperty<Integer>(s.getValue().getService().getPrice()));
        tcEmployerFIO.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getEmployer().getFio()));
        tcDatetime.setCellValueFactory(s -> new SimpleObjectProperty<Date>(s.getValue().getSqlDate()));
        tvProvidedServices.setItems(providedServices);
    }

    @FXML
    public void onAutomobileChanged(ActionEvent actionEvent) {
        providedServices.clear();
        providedServices.addAll(providedServiceDao.findALlOfAutomobile(cbAutomobile.getSelectionModel().getSelectedItem().getId()));
    }
}