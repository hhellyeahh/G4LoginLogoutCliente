/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.mentalDisease;

import java.util.logging.Logger;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 *
 * @author Leire
 */
public class MentalDisease2Controller {

    private Stage stage;
    private static final Logger LOGGER = Logger.getLogger("view");

    /**
     * Method for initializing MentalDisease1 Stage.
     *
     * @param root The Parent object representing root node of view graph.
     */
    public void initialize(Parent root) {
        LOGGER.info("Initializing the window");

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
