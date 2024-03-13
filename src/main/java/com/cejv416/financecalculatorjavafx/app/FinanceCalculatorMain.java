package com.cejv416.financecalculatorjavafx.app;

import com.cejv416.financecalculatorjavafx.calculator.FinanceCalculations;
import com.cejv416.financecalculatorjavafx.data.FinanceBean;
import com.cejv416.financecalculatorjavafx.presentation.FinancePane;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Here is the app class that does the initial set up and then transfers control
 * to the presentation class.
 *
 * @author Ken Fogel
 * @version 1.1
 */
public class FinanceCalculatorMain extends Application {

    private FinanceCalculations calculator;
    private FinanceBean financeBean;
    private FinancePane presentationPane;

    /**
     * As a constructor is executed before the object is created many frameworks
     * use an init method that is called after the constructor so that it can
     * interact withe the framework
     */
    @Override
    public void init() {
        calculator = new FinanceCalculations();
        financeBean = new FinanceBean();
        presentationPane = new FinancePane(financeBean, calculator);
    }

    /**
     * The class that extends Application in JavaFX requires a start method that
     * can receive a reference to the primaryStage, the applications window.
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        presentationPane.start(primaryStage);
    }

    /**
     * In JavaFX main calls the launch method in its super class Application. It
     * must not do anything else.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
