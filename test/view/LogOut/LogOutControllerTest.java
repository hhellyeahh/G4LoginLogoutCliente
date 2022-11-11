/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.LogOut;

import application.Application;
import java.util.concurrent.TimeoutException;
import javafx.stage.Stage;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

/**
 * Testing class for Logout view and controller
 * Tests logout view behavior using TestFX framework
 * @author Leire, Zulu
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogOutControllerTest extends ApplicationTest {

    /**
     * Starts application to be tested
     * @param stage Primary Stage object
     * @throws Exception  if there is any error
     */
    @Override
    public void start(Stage stage) throws Exception {
        new Application().start(stage);
    }

    /**
     * Set up Java FX fixture for tests. This is a general approach for using a 
     * unique instance of the application in the test.
     * @throws java.util.concurrent.TimeoutException
     */
    @BeforeClass
    public static void ClickApplicationTest() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Application.class);
    }

    /**
     * Test LogOut view is opened when button Log In is clicked
     * Test of initial state of LogOut view.
     * Test of the alert Log Out Button
     */
    @Test
    public void test1_LogOutOpenedLogInClick() {
        clickOn("#tfUsername");
        write("bbonllo");
        clickOn("#pfPassword");
        write("abcd*1234");
        clickOn("#btnLogIn");
        verifyThat("#pnLogOut", isVisible());
        
        verifyThat("Welcome back: bbonllo.", isVisible());
        verifyThat("#btnLogOut", isEnabled());
        
        clickOn("#btnLogOut");
        verifyThat("Do you want to log out or exit the application?", isVisible());
        clickOn("Log out");
    }
    
    /**
     * Test LogOut view is opened when button Log In is clicked
     * Test of initial state of LogOut view.
     * Test of the alert exit Button
     */
    @Test
    public void test2_LogOutOpenedLogInClick() {
        clickOn("#tfUsername");
        write("bbonllo");
        clickOn("#pfPassword");
        write("abcd*1234");
        clickOn("#btnLogIn");
        verifyThat("#pnLogOut", isVisible());
        
        verifyThat("Welcome back: bbonllo.", isVisible());
        verifyThat("#btnLogOut", isEnabled());
        
        clickOn("#btnLogOut");
        verifyThat("Do you want to log out or exit the application?", isVisible());
        clickOn("Exit");
    }
}
