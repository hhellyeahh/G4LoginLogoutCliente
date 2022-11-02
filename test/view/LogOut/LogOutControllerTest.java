/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.LogOut;

import classes.User;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import view.LogIn.LogInApplication;

/**
 *
 * @author Leire
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LogOutControllerTest extends ApplicationTest {

    private User user;

    @Override
    public void start(Stage stage) throws Exception {
        new LogInApplication().start(stage);
    }

    /**
     * Test of initialize method, of class LogOutController.
     */
    @Test
    public void testInitialize() {
        Parent root = null;
        LogOutController instance = new LogOutController();
        instance.initialize(root);

        verifyThat("#tfMessage", hasText("Welcome back: " + user.getLogin() + "."));
        verifyThat("#btnLogOut", isEnabled());

        fail("The test case is a prototype.");
    }

    /**
     * Test of handleLogOutButtonAction method, of class LogOutController.
     */
    @Test
    public void testHandleLogOutButtonAction() throws IOException {
        ActionEvent event = null;
        LogOutController instance = new LogOutController();
        instance.handleLogOutButtonAction(event);

        clickOn("#btnLogOut");
        verifyThat("OK_DONE", NodeMatchers.isVisible());
        verifyThat("CANCEL_CLOSE", NodeMatchers.isVisible());

        fail("The test case is a prototype.");
    }

    /**
     * Test of setStage method, of class LogOutController.
     */
    @Test
    public void testSetStage() {
        Stage stage = null;
        LogOutController instance = new LogOutController();
        instance.setStage(stage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initData method, of class LogOutController.
     */
    @Test
    public void testInitData() {
        User user = null;
        LogOutController instance = new LogOutController();
        instance.initData(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
