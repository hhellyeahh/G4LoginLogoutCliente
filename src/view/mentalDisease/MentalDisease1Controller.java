/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.mentalDisease;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Leire
 */
public class MentalDisease1Controller {

    private static final Logger LOGGER = Logger.getLogger("view");
    private Stage stage;

    @FXML
    private TableView tbvMentalDiseases;
    @FXML
    private TableColumn tcID, tcAdmin, tcName, tcType, tcDescription, tcSymptoms, tcDate;
    @FXML
    private Button btnModify, btnSearch, btnSignOff, btnDelete, btnCreate, btnHome, btnPrint;
    @FXML
    private RadioButton rdbtnOrder;
    @FXML
    private ComboBox cmbSearch;
    @FXML
    private TextField txtfSearch;
    @FXML
    private Pane pnMentalDisease1;

    /**
     * Method for initializing MentalDisease1 Stage.
     *
     * @param root The Parent object representing root node of view graph.
     */
    public void initialize(Parent root) {
        LOGGER.info("Initializing the window");
        
        // The window will be modal.
        stage.initModality(Modality.APPLICATION_MODAL);

        //Tooltips
        btnCreate.setTooltip(new Tooltip("Create"));
        btnDelete.setTooltip(new Tooltip("Delete"));
        btnHome.setTooltip(new Tooltip("Home"));
        btnModify.setTooltip(new Tooltip("Modify"));
        btnPrint.setTooltip(new Tooltip("Print"));
        btnSearch.setTooltip(new Tooltip("Search"));
        btnSignOff.setTooltip(new Tooltip("Sign Off"));

        //The focus will be on the Search field.
        this.txtfSearch.requestFocus();

        // The Search, Modify, Delete and Print buttons are disabled.
        this.btnSearch.setDisable(true);
        this.btnModify.setDisable(true);
        this.btnDelete.setDisable(true);
        this.btnPrint.setDisable(true);

        LOGGER.info("window initialized");

    }

    /**
     * Handle Action event on Create Button The “Mental disease 2” window will
     * open.
     *
     * @param event The Action event object
     */
    @FXML
    private void handleCreateButtonAction(ActionEvent event) throws IOException, Exception {
        LOGGER.info("Probando a abrir ventana de registro");
        try {
            Stage stage = new Stage();
            FXMLLoader loader;
            loader = new FXMLLoader(getClass().getResource("/view/mentalDisease/MentalDisease2.fxml"));
            Parent root = (Parent) loader.load();
            MentalDisease2Controller controller = (MentalDisease2Controller) loader.getController();
            controller.setStage(stage);
            controller.initialize(root);

            LOGGER.info("Ventana de mentaldisease abierta");

        } catch (IOException ex) {
            showErrorAlert("No se ha podido abrir la ventana");
            LOGGER.log(Level.SEVERE,
                    ex.getMessage());
        }
    }

    /**
     * Show error alert
     *
     * @param errorMsg Receive error string
     */
    protected void showErrorAlert(String errorMsg) {
        //Shows error dialog.
        Alert alert = new Alert(Alert.AlertType.ERROR, errorMsg, ButtonType.OK);
        alert.showAndWait();
    }

    /**
     * Return the stage
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
