/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.LogOut;

import classes.User;
import java.io.IOException;
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
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import view.LogIn.LogInApplication;

/**
 * Testing class for Logout view and controller
 * Tests logout view behavior using TestFX framework
 * @author Leire
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
        new LogInApplication().start(stage);
    }

    /**
     * Set up Java FX fixture for tests. This is a general approach for using a 
     * unique instance of the application in the test.
     * @throws java.util.concurrent.TimeoutException
     */
    @BeforeClass
    public static void ClickApplicationTest() throws TimeoutException {
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(LogInApplication.class);
    }

    /**
     * Test LogOut view is opened when button Log In is clicked
     * Test of initial state of LogOut view.
     * Test of the alert Log Out Button
     */
    @Test
    public void test1_LogOutOpenedLogInClick() {
        clickOn("#tfUsername");
        write("LeireCarrasco");
        clickOn("#pfPassword");
        write("abcd*1234");
        clickOn("#btnLogIn");
        verifyThat("#pnLogOut", isVisible());
        
        verifyThat("Welcome back: LeireCarrasco.", isVisible());
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
        write("LeireCarrasco");
        clickOn("#pfPassword");
        write("abcd*1234");
        clickOn("#btnLogIn");
        verifyThat("#pnLogOut", isVisible());
        
        verifyThat("Welcome back: LeireCarrasco.", isVisible());
        verifyThat("#btnLogOut", isEnabled());
        
        clickOn("#btnLogOut");
        verifyThat("Do you want to log out or exit the application?", isVisible());
        clickOn("Exit");
    }
    
    /**
     * Test of initial state of LogOut view.
     */
    @Test
    @Ignore
    public void test2_LogOutInitialState() {
        //verifyThat("#tfMessage", hasText("Welcome back: LeireCarrasco."));
        verifyThat("Welcome back: LeireCarrasco.", isVisible());
        verifyThat("#btnLogOut", isEnabled());
    }

    /**
     * Test of the alert Log Out Button
     */
    @Test
    @Ignore
    public void test3_HandleLogOutButtonAction(){
        clickOn("#btnLogOut");
        verifyThat("Do you want to log out or exit the application?", isVisible());
        clickOn("Log out");
    }
    
    /**
     * Test of the alert exit Button
     */
    @Test
    @Ignore
    public void test4_HandleExitButtonAction(){
        clickOn("#btnLogOut");
        verifyThat("Do you want to log out or exit the application?", isVisible());
        clickOn("Exit");
    }

    /**
     * Test of setStage method, of class LogOutController.
     *//*
    @Test
    public void testSetStage() {
        Stage stage = null;
        LogOutController instance = new LogOutController();
        instance.setStage(stage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    /**
     * Test of initData method, of class LogOutController.
     *//*
    @Test
    public void testInitData() {
        User user = null;
        LogOutController instance = new LogOutController();
        instance.initData(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
}
