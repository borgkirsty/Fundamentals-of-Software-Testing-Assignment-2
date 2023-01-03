package javaPackage.login;

import javaPackage.login.enums.LoginStateEnum;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginModelTest implements FsmModel {
    //Instance of System under Test
    LoginOperator sut = new LoginOperator();

    //Defining the starting state
    LoginStateEnum stateEnum = LoginStateEnum.LOGGEDOUT;

    @Override
    public LoginStateEnum getState() {
        return stateEnum;
    }

    @Override
    public void reset(boolean testing) {
        if(testing) {
            sut = new LoginOperator();
        }
        stateEnum = LoginStateEnum.LOGGEDOUT;
    }

    public boolean validLoginGuard(){return getState().equals(LoginStateEnum.LOGGEDOUT);}
    public @Action void validLogin() throws Exception {
        //Update the system under test by executing the relevant methods
        sut.validLogin();

        //Update the model's state variables
        stateEnum = LoginStateEnum.LOGGEDIN;

        //Check correspondence between the model and the system under test
        assertTrue(sut.isLoggedIn());
    }

    public boolean invalidLoginGuard(){return getState().equals(LoginStateEnum.LOGGEDOUT);}
    public @Action void invalidLogin() throws Exception {
        //Update the system under test by executing the relevant methods
        sut.invalidLogin();

        //Update the model's state variables
        stateEnum = LoginStateEnum.LOGGEDOUT;

        //Check correspondence between the model and the system under test
        assertFalse(sut.isLoggedIn());
    }

    public boolean logoutGuard(){return getState().equals(LoginStateEnum.LOGGEDIN);}
    public @Action void logout() throws Exception {
        //Update the system under test by executing the relevant methods
        sut.logout();

        //Update the model's state variables
        stateEnum = LoginStateEnum.LOGGEDOUT;

        //Check correspondence between the model and the system under test
        assertFalse(sut.isLoggedIn());
    }

    //Implement the test runner
    @Test
    public void LoginModelRunner(){
        final GreedyTester tester = new GreedyTester(new LoginModelTest());
        tester.setRandom(new Random());
        tester.buildGraph();
        tester.addListener(new StopOnFailureListener());
        tester.addListener("verbose");
        tester.addCoverageMetric(new TransitionPairCoverage());
        tester.addCoverageMetric(new StateCoverage());
        tester.addCoverageMetric(new ActionCoverage());
        tester.generate(50);
        tester.printCoverage();
    }
}
