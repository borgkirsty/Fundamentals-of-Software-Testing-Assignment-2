package javaPackage.alertLimit;

import javaPackage.alertLimit.enums.AlertLimitStateEnum;
import javaPackage.login.LoginModelTest;
import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;
import nz.ac.waikato.modeljunit.GreedyTester;
import nz.ac.waikato.modeljunit.StopOnFailureListener;
import nz.ac.waikato.modeljunit.coverage.ActionCoverage;
import nz.ac.waikato.modeljunit.coverage.StateCoverage;
import nz.ac.waikato.modeljunit.coverage.TransitionPairCoverage;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class AlertLimitModelTest implements FsmModel {

    AlertOperator sut = new AlertOperator();

    AlertLimitStateEnum stateEnum = AlertLimitStateEnum.NOALERTS;

    int alertCount = 0;
    int alertsReturned = 0;
    boolean validCredentials = false;

    @Override
    public AlertLimitStateEnum getState() {
        return stateEnum;
    }

    @Override
    public void reset(boolean testing) {
        if(testing) {
            sut = new AlertOperator();
        }
        stateEnum = AlertLimitStateEnum.NOALERTS;
    }

    public boolean countingGuard() {return true;}
    public @Action void counting() throws Exception {
        sut.counting();

        alertCount ++;
        if(getState().equals(AlertLimitStateEnum.NOALERTS)) {
            stateEnum = AlertLimitStateEnum.BELOWLIMIT;
            assertEquals(sut.getAlertCount(), alertCount);
        } else if(getState().equals(AlertLimitStateEnum.BELOWLIMIT) && alertCount < 5) {
            stateEnum = AlertLimitStateEnum.BELOWLIMIT;
            assertEquals(sut.getAlertCount(), alertCount);
        } else if(getState().equals(AlertLimitStateEnum.BELOWLIMIT) && alertCount >= 5) {
            stateEnum = AlertLimitStateEnum.ABOVELIMIT;
            assertEquals(sut.getAlertCount(), alertCount);
        }
    }

    public boolean deletedGuard() {return true;}
    public @Action void deleted() throws Exception {
        sut.deleted();

        alertCount = 0;
        stateEnum = AlertLimitStateEnum.NOALERTS;
        assertEquals(sut.getAlertCount(), alertCount);
    }

    public boolean returnedGuard() {return !(getState().equals(AlertLimitStateEnum.NOALERTS));}
    public @Action void returned() throws Exception {
        sut.returned();

        if(sut.getAlertsReturned() > 5){
            stateEnum = AlertLimitStateEnum.ABOVELIMITRETURNED;
            assertFalse(sut.getAlertsReturned() > 5);
        } else {
            stateEnum = AlertLimitStateEnum.BELOWLIMIT;
            assertTrue(sut.getAlertsReturned() <= 5);
        }
    }

    @Test
    public void AlertLimitModelRunner(){
        final GreedyTester tester = new GreedyTester(new AlertLimitModelTest());
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
