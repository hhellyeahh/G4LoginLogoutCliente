/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.LogIn;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isDisabled;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

/**
 *
 * @author Leire
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogInControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        new LogInApplication().start(stage);
    }

    @BeforeClass
    public static void ClickApplicationTest() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(LogInApplication.class);
    }

    /**
     * Test of initial state of LogIn view.
     */
    @Test
    public void test1_LogInInitialState() {
        clickOn("#tfUsername");
        verifyThat("#tfUsername", hasText(""));
        verifyThat("#lblUsername", hasText(""));
        clickOn("#pfPassword");
        verifyThat("#pfPassword", hasText(""));
        verifyThat("#lblPassword", hasText(""));
        verifyThat("#btnLogIn", isDisabled());
    }

    /**
     * Test that button Log In is enabled.
     */
    @Test
    public void test2_LogInButtonIsEnabled() {
        clickOn("#tfUsername");
        write("LeireCarrasco");
        verifyThat("#btnLogIn", isDisabled());
        clickOn("#pfPassword");
        write("abcd*1234");
        verifyThat("#btnLogIn", isEnabled());
    }

    /**
     * Test that button Log In is disabled when user and password fields are not full.
     */
    @Test
    public void test3_LogInButtonIsDisabled() {
        clickOn("#tfUsername");
        write("LeireCarrasco");
        verifyThat("#btnLogIn", isDisabled());
        eraseText(13);
        clickOn("#pfPassword");
        write("abcd*1234");
        verifyThat("#btnLogIn", isDisabled());
        eraseText(9);
        verifyThat("#btnLogIn", isDisabled());
    }

    /**
     * Test that labels works.
     */
    @Test
    public void test4_LabelControl() {
        clickOn("#tfUsername");
        write("Leire ");
        verifyThat("#lblUsername", hasText("We don't allow spaces in this field."));
        write("Carrasco");

        verifyThat("#lblUsername", hasText(""));

        clickOn("#pfPassword");
        write("abcd* ");
        verifyThat("#lblPassword", hasText("We don't allow spaces in this field."));
        write("1234");

        verifyThat("#lblPassword", hasText(""));
    }

    /**
     * Test LogOut view is opened when button Log In is clicked
     */
    @Test
    public void test5_LogOutOpenedLogInClick() {
        clickOn("#tfUsername");
        write("LeireCarrasco");
        clickOn("#pfPassword");
        write("abcd*1234");
        clickOn("#btnLogIn");
        verifyThat("#pnLogOut", isVisible());
    }

    /**
     * Test LogOut view is opened when button Log In is clicked
     */
    @Test
    @Ignore
    public void test6_SignUpOpenedSignUpClick() {
        clickOn("#hlSignUp");
        verifyThat("#pnSignUp", isVisible());
    }

    /*
    
    @Test
    public void testShowErrorAlert() {
        String errorMsg = "";
        LogInController instance = new LogInController();
        instance.showErrorAlert(errorMsg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
     */
}
