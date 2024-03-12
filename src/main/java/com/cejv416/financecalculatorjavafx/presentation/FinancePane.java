/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cejv416.financecalculatorjavafx.presentation;

import com.cejv416.financecalculatorjavafx.calculator.FinanceCalculations;
import com.cejv416.financecalculatorjavafx.data.FinanceBean;
import java.math.BigDecimal;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author omniprof
 */
public class FinancePane extends BorderPane {

    private final FinanceBean fb;
    private final FinanceCalculations fc;
    private TextArea textArea;

    private TextField amountValue;
    private TextField rateValue;
    private TextField termValue;
    private TextField resultValue;
    private Label amountLabel;
    private Label title;

    private int calculationType;

    public FinancePane(FinanceBean fb, FinanceCalculations fc) {
        this.fb = fb;
        this.fc = fc;
        textArea = new TextArea();
        calculationType = 0;
    }

    public void start(Stage primaryStage) {

        setTop(buildMenuBar());
        setCenter(buildForm());
        setBottom(buildTextArea());

        Scene scene = new Scene(this, 600, 600);

        primaryStage.setTitle("Calculations");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextArea buildTextArea() {
        textArea.setWrapText(true);
        textArea.setPrefSize(650, 150);
        textArea.setStyle("-fx-font-size:14pt");
        return textArea;
    }

    private MenuBar buildMenuBar() {
        // create a menu 
        Menu file = new Menu("File");
        MenuItem exit = new MenuItem("Exit");

        Menu calculations = new Menu("Calculations");
        MenuItem loan = new MenuItem("Loan Payment");
        loan.setOnAction(event -> {
            calculationType = 0;
            clearTextFields();
            title.setText("Loan Calculation");
            amountLabel.setText("Loan Amount");
        });
        MenuItem savings = new MenuItem("Future Value of Savings");
        savings.setOnAction(event -> {
            calculationType = 1;
            clearTextFields();
            title.setText("Future Value of Savings Calculation");
            amountLabel.setText("Savings Amount");
        });
        MenuItem future = new MenuItem("Savings Goal");
        future.setOnAction(event -> {
            calculationType = 2;
            clearTextFields();
            title.setText("Savings Goal Calculation");
            amountLabel.setText("Goal Amount");
        });

        // add menu items to menu 
        file.getItems().add(exit);
        file.setOnAction(event -> {
            Platform.exit();
        });
        calculations.getItems().add(loan);
        calculations.getItems().add(savings);
        calculations.getItems().add(future);

        // create a menubar 
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-font-size:14pt");

        // add menu to menubar 
        menuBar.getMenus().add(file);
        menuBar.getMenus().add(calculations);

        return menuBar;
    }

    /**
     * This method creates a Label that is centered inside an HBox.
     *
     * @param text
     * @return
     */
    private HBox createTitle() {
        title = new Label("Loan Calculations"); // default

        // Style the label using CSS
        // Possible fonts can be found at http://www.webdesigndev.com/16-gorgeous-web-safe-fonts-to-use-with-css/
        title.setStyle("-fx-font-size:18pt");

        // To center the title and give it padding create an HBox, set the
        // padding and alignment, add the label and then add the HBox to the BorderPane.
        HBox hbox = new HBox();
        hbox.getChildren().add(title);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20, 20, 20, 20));

        return hbox;
    }

    /**
     * This method creates a BorderPane that has a title in the top and a
     * GridPane in the center. The GridPane contains a form.
     *
     * @return The BorderPane to add to the Scene
     */
    private BorderPane buildForm() {

        // Create the pane that will hold the controls
        BorderPane loanPane = new BorderPane();

        // Add a Title
        loanPane.setTop(createTitle());

        // Craete an empty GridPane
        GridPane loanGrid = new GridPane();

        // Column 0, Row 0
        amountLabel = new Label("Loan Amount");
        amountLabel.setStyle("-fx-font-size:14pt");
        loanGrid.add(amountLabel, 0, 0);

        // Column 1, Row 0
        amountValue = new TextField();
        amountValue.setStyle("-fx-font-size:14pt");
        amountValue.setAlignment(Pos.CENTER_RIGHT);
        loanGrid.add(amountValue, 1, 0);

        // Column 0, Row 1
        Label rateLabel = new Label("Interest Rate");
        rateLabel.setStyle("-fx-font-size:14pt");
        loanGrid.add(rateLabel, 0, 1);

        // Column 1, Row 1
        rateValue = new TextField();
        rateValue.setStyle("-fx-font-size:14pt");
        rateValue.setAlignment(Pos.CENTER_RIGHT);
        loanGrid.add(rateValue, 1, 1);

        // Column 0, Row 2
        Label termLabel = new Label("Term In Months");
        termLabel.setStyle("-fx-font-size:14pt");
        loanGrid.add(termLabel, 0, 2);

        // Column 1, Row 2
        termValue = new TextField();
        termValue.setStyle("-fx-font-size:14pt");
        termValue.setAlignment(Pos.CENTER_RIGHT);
        loanGrid.add(termValue, 1, 2);

        // Column 0, Row 3
        Label resultLabel = new Label("Result");
        resultLabel.setStyle("-fx-font-size:14pt");
        loanGrid.add(resultLabel, 0, 3);

        // Column 1, Row 3
        resultValue = new TextField();
        resultValue.setStyle("-fx-font-size:14pt");
        resultValue.setAlignment(Pos.CENTER_RIGHT);
        resultValue.setEditable(false);
        loanGrid.add(resultValue, 1, 3);

        // Create a button and attach an event handler
        Button calculate = new Button("Calculate");
        calculate.setStyle("-fx-font-size:14pt");
        calculate.setOnAction(this::calculateButtonHandler);

        // HBox that will span 2 columns so thatbutton can be centered across the GridPane
        HBox hbox = new HBox();
        hbox.getChildren().add(calculate);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20.0));
        loanGrid.add(hbox, 0, 4, 2, 1);

        // Set the column widths as a percentage
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(30.0);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(70.0);

        loanGrid.getColumnConstraints().addAll(col1, col2);

        // Add space around the outside of the GridPane
        loanGrid.setPadding(new Insets(20.0));
        // Add space between rows and columns of the GridPane
        loanGrid.setHgap(10.0);
        loanGrid.setVgap(10.0);

        // Load the GridPane into the pane
        loanPane.setCenter(loanGrid);

        return loanPane;
    }

    /**
     * This is the event handler when the button is being pressed. If there is a
     * NumberFormatException when any of the fields is converted to a BigDecimal
     * then an Alert box is displayed
     *
     * There is a better way to handle strings that cannot convert and you will
     * learn how in the Java Desktop course.
     *
     * @param e
     */
    private void calculateButtonHandler(ActionEvent e) {
        boolean doCalculation = true;
        try {
            fb.setInputValue(new BigDecimal(amountValue.getText()));
        } catch (NumberFormatException nfe) {
            doCalculation = false;
            numberFormatAlert(amountValue.getText(), "Loan");
            amountValue.setText("");
        }
        try {
            fb.setRate(new BigDecimal(rateValue.getText()));
        } catch (NumberFormatException nfe) {
            doCalculation = false;
            numberFormatAlert(rateValue.getText(), "Rate");
            rateValue.setText("");
        }
        try {
            fb.setTerm(new BigDecimal(termValue.getText()));
        } catch (NumberFormatException nfe) {
            doCalculation = false;
            numberFormatAlert(termValue.getText(), "Term");
            termValue.setText("");
        }

        if (doCalculation == true) {
            switch (calculationType) {
                case 0 ->
                    fc.loanCalculation(fb);
                case 1 ->
                    fc.futureValueCalculation(fb);
                case 2 ->
                    fc.savingsGoalCalculation(fb);
            }
            resultValue.setText(fb.getResult().toString());
            textArea.appendText(buildStringResult());

        }
    }
    
    private String buildStringResult() {
        
        String resultString = "";
            switch (calculationType) {
                case 0 ->
                    //resultString = "Loan: Borrow " + fb.getInputValue() + " at an interest rate of " + fb.getRate() + " for a period of " + fb.getTerm() + " will require payments of " + fb.getResult() + " monthly\n";
                    resultString = String.format("Loan: If you borrow $%s at an interest rate of %s for a period of %s months you will have to make monthly payments of $%s\n", fb.getInputValue(),fb.getRate(),fb.getTerm(),fb.getResult());
                case 1 ->
//                    resultString = "Future Value: If you invest " + fb.getInputValue() + " at an interest rate of " + fb.getRate() + " for a period of " + fb.getTerm() + " you will have " + fb.getResult() + " at the end of the term\n";
                    resultString = String.format("Future Value: If you invest $%s at an interest rate of %s for a period of %s months you will have $%s at the end of the term\n", fb.getInputValue(),fb.getRate(),fb.getTerm(),fb.getResult());
                case 2 ->
//                    resultString = "Savings Goal: If you wish to save " + fb.getInputValue() + " at an interest rate of " + fb.getRate() + " over a period of " + fb.getTerm() + " you will have need to save" + fb.getResult() + " monthly\n";
                    resultString = String.format("Savings Goal: If you wish to save $%s at an interest rate of %s over a period of %s months you will have need to save $%s monthly\n", fb.getInputValue(),fb.getRate(),fb.getTerm(),fb.getResult());
            }
        return resultString;
    }

    /**
     * Display an Alert box if there is a NumberFormatException detected in the
     * calculateButtonHandler
     *
     * @param badValue
     * @param textField
     */
    private void numberFormatAlert(String badValue, String textField) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Number Format Error");
        alert.setHeaderText("The value \"" + badValue + "\" cannot be converted to a number for the " + textField);
        alert.setContentText("Number Format Error");

        alert.showAndWait();
    }

    /**
     * This method clears all the text fields on the form
     */
    private void clearTextFields() {
        amountValue.setText("");
        rateValue.setText("");
        termValue.setText("");
        resultValue.setText("");
    }

}
