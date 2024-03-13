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
 * This is the user interface for the application. It is broken down into
 * methods that create the components of the UI and the corresponding event
 * handlers
 *
 * @author Ken Fogel
 */
public class FinancePane extends BorderPane {

    private final FinanceBean financeBean;
    private final FinanceCalculations calculator;

    // This JavaFX control holds text. It will add scroll bars if the 
    // text does not fit the size of the area
    private final TextArea textArea;

    // These are the input field controls
    private TextField amountValue;
    private TextField rateValue;
    private TextField termValue;
    private TextField resultValue;

    // These are two labels that change depending on the calculation you choose.
    private Label amountLabel;
    private Label title;

    // The calculation as determined by the menu choise
    private int calculationType;

    /**
     * The constructor that receives the references from the
     * FinancialCalculatorMain object as well is instantiating the TextArea and
     * setting the initial value for the calculationType
     *
     * @param financeBean
     * @param calculator
     */
    public FinancePane(FinanceBean financeBean, FinanceCalculations calculator) {
        this.financeBean = financeBean;
        this.calculator = calculator;
        textArea = new TextArea();
        calculationType = 0;
    }

    /**
     * This is method that carries out the initialization of the UI.
     *
     * @param primaryStage
     */
    public void start(Stage primaryStage) {

        setTop(buildMenuBar());
        setCenter(buildForm());
        setBottom(buildTextArea());

        /* While a primaryStage is the window, controls and containers are 
        placed into a Scene. As this class extends the BorderPane we use the 
        this reference to mean that this class is the container of controls in 
        this Scene */
        Scene scene = new Scene(this, 600, 600);

        // Give the window a title, put the Scene in the window, and show the window
        primaryStage.setTitle("Calculations");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // The build methods are used to construct the different components of the UI
    /**
     * This is the TextArea found at the bottom of the Scene that contains a
     * line of text for every calculation performed
     *
     * @return The configured TextArea
     */
    private TextArea buildTextArea() {
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setPrefSize(650, 150);
        textArea.setStyle("-fx-font-size:14pt");
        return textArea;
    }

    /**
     * This constructs the menu system consisting of Menu, such as File,
     * MenuItems that are displayed when a Menu is selected, and a Menu Bar that
     * contains Menu and Menu Items and can be placed anywhere in a pane though
     * the top of a pane is the usual location.
     *
     * @return The initialized MenuBar
     */
    private MenuBar buildMenuBar() {

        // The File menu
        Menu file = new Menu("File");
        // The Menu Item
        MenuItem exit = new MenuItem("Exit");
        // The event that occurs if this item is selected
        exit.setOnAction(event -> {
            Platform.exit();
        });

        // The Calculations menu
        Menu calculations = new Menu("Calculations");

        // These are the items in the Calculation Menu and their corresponding event handler
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

        // Add the Menu Items to their Menu 
        file.getItems().add(exit);
        calculations.getItems().add(loan);
        calculations.getItems().add(savings);
        calculations.getItems().add(future);

        // Create the MenuBar
        MenuBar menuBar = new MenuBar();
        menuBar.setStyle("-fx-font-size:14pt");

        // Add the Menus to the MenuBar 
        menuBar.getMenus().add(file);
        menuBar.getMenus().add(calculations);

        return menuBar;
    }

    /**
     * This method creates a Label that is centered inside an HBox. The text of
     * this title label can be changed depending on the menu item selection
     *
     * @return A horizontal box that contains the label centered within
     */
    private HBox createTitle() {
        title = new Label("Loan Calculations"); // default

        // Style the label using CSS
        // Possible fonts can be found at http://www.webdesigndev.com/16-gorgeous-web-safe-fonts-to-use-with-css/
        title.setStyle("-fx-font-size:18pt");

        // To center the title and give it padding, create an HBox, set the
        // padding and alignment, add the label and then add the HBox to the BorderPane.
        HBox hbox = new HBox();
        hbox.getChildren().add(title);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(20, 20, 20, 20));

        return hbox;
    }

    /**
     * A BorderPane has 5 areas into which a container or control can be placed.
     * This application only used the top, center, and bottom areas. This method
     * creates a BorderPane that has a title in the top and a GridPane form in
     * the center and a TextArea in the bottom.
     *
     * @return The BorderPane to add to the Scene
     */
    private BorderPane buildForm() {

        // Create the pane that will hold the controls
        BorderPane formPane = new BorderPane();

        // Add a Title
        formPane.setTop(createTitle());

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

        // HBox that will span 2 columns so that the button can be centered 
        // across the GridPane
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

        // Add the constriants to the the GridPane
        loanGrid.getColumnConstraints().addAll(col1, col2);

        // Add space around the outside of the GridPane
        loanGrid.setPadding(new Insets(20.0));

        // Add space between rows and columns of the GridPane
        loanGrid.setHgap(10.0);
        loanGrid.setVgap(10.0);

        // Load the GridPane into the pane
        formPane.setCenter(loanGrid);

        return formPane;
    }

    /**
     * This is the event handler when the button is being pressed. If the string
     * entered by the user cannot be converted to a BigDecimal then the
     * NumberFormatException is thrown. We catch it and show a popup box
     * informing us that we made a mistake and then it clears the TextField.
     *
     * @param e We do not use the ActionEvent object so we give it a single
     * letter name.
     */
    private void calculateButtonHandler(ActionEvent e) {
        boolean doCalculation = true;
        try {
            financeBean.setInputValue(new BigDecimal(amountValue.getText()));
        } catch (NumberFormatException nfe) {
            doCalculation = false;
            numberFormatAlert(amountValue.getText(), "Loan");
            amountValue.setText("");
        }
        try {
            financeBean.setRate(new BigDecimal(rateValue.getText()));
        } catch (NumberFormatException nfe) {
            doCalculation = false;
            numberFormatAlert(rateValue.getText(), "Rate");
            rateValue.setText("");
        }
        try {
            financeBean.setTerm(new BigDecimal(termValue.getText()));
        } catch (NumberFormatException nfe) {
            doCalculation = false;
            numberFormatAlert(termValue.getText(), "Term");
            termValue.setText("");
        }

        /* If there were no errors converting the Strings then the appropriate 
        calculation is called upon to generate the result. The result is then 
        displayed. */
        if (doCalculation == true) {
            switch (calculationType) {
                case 0 ->
                    calculator.loanCalculation(financeBean);
                case 1 ->
                    calculator.futureValueCalculation(financeBean);
                case 2 ->
                    calculator.savingsGoalCalculation(financeBean);
            }
            resultValue.setText(financeBean.getResult().toString());
            // A string with the result is created and appended to the TextArea
            textArea.appendText(buildStringResult());
            // Scroll to the bottom of the TextArea 
            textArea.positionCaret(textArea.getText().length());
        }
    }

    /**
     * The string with the result destined for the TextArea is created here
     *
     * @return The string to display
     */
    private String buildStringResult() {

        String resultString = "";
        switch (calculationType) {
            case 0 ->
                resultString = String.format("Loan: If you borrow $%s at an interest rate of %s for a period of %s months you will have to make monthly payments of $%s\n", financeBean.getInputValue(), financeBean.getRate(), financeBean.getTerm(), financeBean.getResult());
            case 1 ->
                resultString = String.format("Future Value: If you invest $%s at an interest rate of %s for a period of %s months you will have $%s at the end of the term\n", financeBean.getInputValue(), financeBean.getRate(), financeBean.getTerm(), financeBean.getResult());
            case 2 ->
                resultString = String.format("Savings Goal: If you wish to save $%s at an interest rate of %s over a period of %s months you will have need to save $%s monthly\n", financeBean.getInputValue(), financeBean.getRate(), financeBean.getTerm(), financeBean.getResult());
        }
        return resultString;
    }

    /**
     * Display an Alert box if there is a NumberFormatException detected in the
     * calculateButtonHandler
     *
     * @param badValue The mistake
     * @param textField The field the mistake was made in
     */
    private void numberFormatAlert(String badValue, String textField) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Number Format Error");
        alert.setHeaderText("The value \"" + badValue + "\" cannot be converted to a number for the " + textField);
        alert.setContentText("Number Format Error");
        // Display a modal dialog window that disables the application until you click on OK
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
