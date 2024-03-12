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
 * @version 1.0
 */
public class FinanceCalculatorMain extends Application {

    private FinanceCalculations calc;
    private FinanceBean finance;
    private FinancePane mainPane;

    @Override
    public void init() {
        calc = new FinanceCalculations();
        finance = new FinanceBean();
        mainPane = new FinancePane(finance, calc);        
    }

    @Override
    public void start(Stage primaryStage) {
        mainPane.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
