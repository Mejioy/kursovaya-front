package ru.kafpin.lr6_bzz.controllers;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;
import ru.kafpin.lr6_bzz.MainApplication;
import ru.kafpin.lr6_bzz.dao.EmployerDao;
import ru.kafpin.lr6_bzz.dao.ServiceDao;
import ru.kafpin.lr6_bzz.domains.Automobile;
import ru.kafpin.lr6_bzz.domains.Client;
import ru.kafpin.lr6_bzz.domains.Employer;
import ru.kafpin.lr6_bzz.domains.Service;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdministratorController {
    @Setter
    private Stage administratorStage;
    private final ServiceDao serviceDao;
    private final EmployerDao employerDao;
    @FXML
    private TableColumn<Service, String> tcDescription;
    @FXML
    private TableColumn<Service, String> tcName;
    @FXML
    private TableColumn<Service, Integer> tcPrice;
    @FXML
    private TableView<Service> tvServices;
    @FXML
    private TableColumn<Employer, String> tcAddress;
    @FXML
    private TableColumn<Employer, String> tcFIO;
    @FXML
    private TableColumn<Employer, String> tcPhone;
    @FXML
    private TableView<Employer> tvEmployers;
    public AdministratorController(){
        try {
            serviceDao = new ServiceDao();
            employerDao = new EmployerDao();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    protected ObservableList<Service> services = FXCollections.observableArrayList();
    protected ObservableList<Employer> employers = FXCollections.observableArrayList();
    private ResourceBundle bundle = ResourceBundle.getBundle("administrator",Locale.getDefault());
    @FXML
    void initialize() {
        services.addAll(serviceDao.findALl());
        tcName.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getName()));
        tcPrice.setCellValueFactory(s -> new SimpleObjectProperty<Integer>(s.getValue().getPrice()));
        tcDescription.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getDescription()));
        tvServices.setItems(services);
        tvServices.getSortOrder().add(tcName);
        tvServices.getSortOrder().add(tcPrice);
        employers.addAll(employerDao.findALl());
        tcFIO.setCellValueFactory(s -> new SimpleStringProperty(
            s.getValue().getSurName() + ' ' +
               s.getValue().getName() + ' ' +
               s.getValue().getPatronym())
        );
        tcPhone.setCellValueFactory(s -> new SimpleStringProperty(s.getValue().getPhone()));
        tcAddress.setCellValueFactory(s ->{
            if (s.getValue().getAppartment()==null)
                return new SimpleStringProperty(
                    s.getValue().getCity() + ", " +
                            s.getValue().getStreet() + ", " +
                            s.getValue().getHouse());
            else
                return new SimpleStringProperty(
                        s.getValue().getCity() + ", " +
                                s.getValue().getStreet() + ", " +
                                s.getValue().getHouse() + ", " +
                                s.getValue().getAppartment());
        }
        );
        tvEmployers.setItems(employers);
        tvEmployers.getSortOrder().add(tcFIO);
        tvEmployers.getSortOrder().add(tcPhone);
        tvEmployers.getSortOrder().add(tcAddress);
    }
    private boolean showEmployerDialog(Employer employer) {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("employer-addEdit.fxml"),bundle);
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage addStage = new Stage();
            addStage.setTitle(bundle.getString("employerinfo"));
            addStage.setScene(scene);
            addStage.initModality(Modality.APPLICATION_MODAL);
            addStage.initOwner(MainApplication.getMainStage());
            EditEmployerController editEmployerController = loader.getController();
            editEmployerController.setEmployer(employer);
            editEmployerController.setEditStage(addStage);
            editEmployerController.setBundle(bundle);
            addStage.showAndWait();
            return editEmployerController.isAction();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    private boolean showServiceDialog(Service service) {
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("service-addEdit.fxml"),bundle);
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage addStage = new Stage();
            addStage.setTitle(bundle.getString("serviceinfo"));
            addStage.setScene(scene);
            addStage.initModality(Modality.APPLICATION_MODAL);
            addStage.initOwner(MainApplication.getMainStage());
            EditServiceController editServiceController = loader.getController();
            editServiceController.setService(service);
            editServiceController.setEditStage(addStage);
            editServiceController.setBundle(bundle);
            addStage.showAndWait();
            return editServiceController.isAction();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    @FXML
    void onAddEmployer(ActionEvent event) {
        Employer employer = new Employer();
        if(showEmployerDialog(employer)){
            Employer existsEmployer = employerDao.findByPhone(employer.getPhone());
            if(existsEmployer!=null){
                Error("Невозможно создать сотрудника с указанным номером телефона, т.к. номер занят другим сотрудником");
            }
            else{
                employerDao.save(employer);
                if(employer.getAppartment()==null)
                    employer.setAppartment(0);
                employers.clear();
                employers.addAll(employerDao.findALl());
                tvEmployers.sort();
            }
        }
    }
    @FXML
    void onAddService(ActionEvent event) {
        Service service = new Service();
        if(showServiceDialog(service)){
            Service existsService = serviceDao.findByName(service.getName());
            if(existsService!=null){
                Error("Невозможно создать услугу с указанным названием, т.к. услуга с таким названием уже существует");
            }
            else{
                serviceDao.save(service);
                services.clear();
                services.addAll(serviceDao.findALl());
                tvServices.sort();
            }
        }
    }

    @FXML
    void onEditEmployer(ActionEvent event) {
        Employer employer = tvEmployers.getSelectionModel().getSelectedItem();
        if (employer != null){
            if(showEmployerDialog(employer)){
                Employer existsEmployer = employerDao.findByPhone(employer.getPhone());
                if(existsEmployer!=null&& !Objects.equals(existsEmployer.getId(), employer.getId())){
                    Error("Невозможно изменить номер выбранного сотрудника, т.к. введённый номер занят другим сотрудником");
                }
                else{
                    employerDao.update(employer);
                }
                employers.clear();
                employers.addAll(employerDao.findALl());
                tvEmployers.sort();
            }
        }
        else
            Error(bundle.getString("employernotchoice"));
    }
    @FXML
    void onEditService(ActionEvent event) {
        Service service = tvServices.getSelectionModel().getSelectedItem();
        if (service != null){
            if(showServiceDialog(service)){
                Service existsService = serviceDao.findByName(service.getName());
                if(existsService!=null&& !Objects.equals(existsService.getId(), service.getId())){
                    Error("Невозможно изменить название выбранного сотрудника, т.к. введённое название соответствует названию другой услуги");
                }
                else{
                    serviceDao.update(service);
                }
                services.clear();
                services.addAll(serviceDao.findALl());
                tvServices.sort();
            }
        }
        else
            Error(bundle.getString("servicenotchoice"));
    }
    @FXML
    void onRemoveEmployer(ActionEvent event) {
        Employer employer = tvEmployers.getSelectionModel().getSelectedItem();
        if(employer!=null){
            if(showRemoveDialog("employer")){
                tvEmployers.getItems().remove(tvEmployers.getSelectionModel().getSelectedIndex());
                employerDao.deleteById(employer.getId());
                tvEmployers.sort();
            }
        }
        else
            Error(bundle.getString("employernotchoicefordel"));
    }
    @FXML
    void onRemoveService(ActionEvent event) {
        Service service = tvServices.getSelectionModel().getSelectedItem();
        if(service!=null){
            if(showRemoveDialog("service")){
                tvServices.getItems().remove(tvServices.getSelectionModel().getSelectedIndex());
                serviceDao.deleteById(service.getId());
                tvServices.sort();
            }
        }
        else
            Error(bundle.getString("servicenotchoicefordel"));
    }
    private void Error(String text){
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(bundle.getString("error"));
        alert.setContentText(null);
        alert.setHeaderText(text);
        alert.showAndWait();
    }
    private boolean showRemoveDialog(String removeditem) {

        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("removeform.fxml"),bundle);
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage remove = new Stage();
            remove.setResizable(false);
            remove.setTitle(bundle.getString("remove"+removeditem));
            remove.setScene(scene);
            remove.initModality(Modality.APPLICATION_MODAL);
            remove.initOwner(MainApplication.getMainStage());
            RemoveController removeController = loader.getController();
            removeController.setRemoveStage(remove);
            remove.showAndWait();
            return removeController.isRemove();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}