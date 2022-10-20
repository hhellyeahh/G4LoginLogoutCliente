/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

/**
 *
 * @author unaib
 */
public class FXMLSignUpController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField btAceptar;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private PasswordField txtRepeatPassword;
    @FXML
    private Hyperlink hlLogin;
    @FXML
    private Button btnContinue;

    /**
     * Handle Action event on Aceptar button
     *
     * @param event The action event object
     */
    @FXML
    private void handleHyperlinkLogInAction(ActionEvent event) {
        
    }
    
    /**
     * Handle Action event on Continue button
     * 
     * @param event The action event object
     */
    @FXML
    private void handleButtonContinueAction(ActionEvent event) {
        
    }
    
    /**
     * Handle Action event on Continue button
     * 
     * @param event The input method event object
     */
    @FXML
    private void handlePasswordTextChangeAction(InputMethodEvent event) {
        
    }
    
    /**
     * Handle Action event on Continue button
     * 
     * @param event The input method event object
     */
    @FXML
    private void handleRepeatPasswordTextChangeAction(InputMethodEvent event) {
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
