/**
 * @apiNote This is the application layout filed where all the function is being build from the
 * "currency-converter-view.fxml" file.
 */
package com.example.currency_converter;


import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.SortedSet;

public class CurrencyConverterController {

    @FXML
    public Button convertBtn;
    @FXML
    public ProgressIndicator progressIndicator;
    @FXML
    public ComboBox<Object> currencyEntry1, currencyEntry2;
    @FXML
    public TextField amount1TextArea, amount2TextArea;
    @FXML
    public ImageView switchArrows;
    @FXML
    public MenuItem menuDarkAndLight;
    @FXML
    public BorderPane borderPane;
    private boolean currenciesEntryAdded;
    private final ManipulateAPIData apiDataContainer;
    private final SortedSet<Object> currencies;

    public CurrencyConverterController() {
        apiDataContainer = new ManipulateAPIData();
        this.currencies = apiDataContainer.getAllCurrenciesNames();
        this.currenciesEntryAdded = false;
    }

    // Load the progress bar on the convert button clicked
    private boolean loadProgressIndicator() {
        // Create a task that takes some time to complete
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < 50; i++) {
                    Thread.sleep(10); // Simulate work being done
                    updateProgress(i + 1, 50);
                }
                return null;
            }
        };
        // Bind the progress indicator to the task's progress
        this.progressIndicator.progressProperty().bind(task.progressProperty());
        // Show the progress indicator and start the task
        this.progressIndicator.setVisible(true);
        new Thread(task).start();
        // Hide the progress indicator when the task is done
        task.setOnSucceeded(e -> this.progressIndicator.setVisible(false));
        try {
            Thread.sleep(150);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        return true;
    }

    private boolean checkValidCurrency() {
        return (this.currencies.contains(this.currencyEntry1.getValue())
                && this.currencies.contains(this.currencyEntry2.getValue()));
    }

    // Convert the currency on the convert button clicked
    public void convertCurrency() {
        this.convertBtn.setOnAction(e -> {
            if (!this.checkValidCurrency()) {
                return;
            }
            double conversion = this.apiDataContainer.calculateRates(this.amount1TextArea.getText(),
                    this.currencyEntry1.getValue().toString(), this.currencyEntry2.getValue().toString());
            if (this.loadProgressIndicator()) {
                this.amount2TextArea.setText(Double.toString(conversion));
            }
        });
    }

    // Fill the currency selector boxes
    public void fillBoxes(Event event) {
        if (this.currenciesEntryAdded)
            return;
        this.currencies.forEach(e -> {
            this.currencyEntry1.getItems().add(e);
            this.currencyEntry2.getItems().add(e);
        });
        new AutoCompleteComboBoxListener(currencyEntry1);
        new AutoCompleteComboBoxListener(currencyEntry2);
        this.currenciesEntryAdded = true;
    }

    // Swap the value of the all the boxes on swap button clicked
    public void switchCurrencies(MouseEvent mouseEvent) {
        // swap the currencies
        Object temp = this.currencyEntry1.getValue();
        this.currencyEntry1.setValue(this.currencyEntry2.getValue());
        this.currencyEntry2.setValue(temp);
        // swap the two text filed
        this.amount1TextArea.setText(this.amount2TextArea.getText());
        this.amount2TextArea.setText("");
    }

    // Clear the boxes on the "clear" button clicked
    public void clearAllBoxes() {
        this.amount1TextArea.clear();
        this.amount2TextArea.clear();
        this.currencyEntry1.setValue("");
        this.currencyEntry2.setValue("");
    }


    public void fileMenuBoxCloseItem(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void editMenuBoxClearEventHandler(ActionEvent actionEvent) {
        this.clearAllBoxes();
    }

    /**
     * Load the about currency converter page on the menu bar about item click
     */
    public void helpMenuBoxEventHandler(ActionEvent actionEvent) {
        Stage primaryStage = new Stage();
        ScrollPane root = new ScrollPane();
        Scene scene = new Scene(root, 550, 550);
        Text text = new Text("""
                Introduction:

                The Java GUI program is a user-friendly currency conversion tool that provides real-time exchange rates for over 100 currencies. It also offers a comprehensive range of resources to help users make informed decisions about money transfers. This documentation provides an overview of the features and benefits of the program.

                Features:

                Currency Conversion:
                The program allows users to easily convert currencies using real-time exchange rates for over 100 currencies Users can select the currency they wish to convert from and to, and the program will provide an accurate conversion rate. This feature ensures that users can make accurate and fast currency conversions without having to rely on manual calculations.

                Money Transfer Information:
                The program provides users with a wealth of resources to help them make informed decisions about money transfers. Users can access information on different money transfer platforms, their fees, transfer speed, and security features. This information enables users to compare and contrast different platforms and choose the one that best fits their needs.

                User-Friendly Interface:
                The program has a user-friendly interface that makes it easy to navigate and use. Users can easily select the currency they wish to convert from and to, and the program will provide an accurate conversion rate. The program's interface is also easy to understand, making it ideal for users who are not familiar with currency conversions.

                Fast and Accurate Conversions:
                Using real-time exchange rates, the program provides fast and accurate currency conversions. This ensures that users can make accurate and fast currency conversions without having to wait for updates.

                No Direct Money Transfers:
                It's important to note that the program does not have the ability to transfer money directly. However, it provides users with the information they need to make informed decisions about money transfers.

                Benefits:

                Efficient Financial Transactions:
                The program is a powerful and user-friendly tool that enables users to make efficient and accurate financial transactions. Users can quickly and easily convert currencies and find the best resources for money transfers.

                Comprehensive Resource:
                The program is a comprehensive resource that provides users with a range of information on different money transfer platforms. This enables users to make informed decisions about money transfers and choose the platform that best fits their needs.

                Real-Time Exchange Rates:
                Using real-time exchange rates, the program provides fast and accurate currency conversions. This ensures that users can make accurate and fast currency conversions without having to rely on outdated or inaccurate information.

                Conclusion:

                The Java GUI program is a powerful and user-friendly currency conversion tool that provides user with a wealth of resources to help them make informed decisions about money transfers. With its real-time exchange rates and comprehensive resources, the program is an essential tools for anyone who needs to convert currencies or find resources for money transfers.""");

        text.wrappingWidthProperty().bind(scene.widthProperty());
        root.setFitToWidth(true);
        root.setContent(text);
        primaryStage.setTitle("About Currency Converter");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * This function is a event handler for the switch between dark and light themes
     *
     * @param actionEvent Not Used
     */
    public void editMenuBoxDartAndLightEventHandler(ActionEvent actionEvent) {
        String cssFile = this.borderPane.getStylesheets().toString().
                substring(113, this.borderPane.getStylesheets().toString().length() - 1);
        if (cssFile.equals("darkStyles.css")) {
            this.borderPane.getStylesheets().clear();
            borderPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("lightStyles.css")).toExternalForm());
            switchArrows.setImage(new Image("https://cdn-icons-png.flaticon.com/512/5436/5436381.png"));
            menuDarkAndLight.setText("Dark Theme");
        } else {
            this.borderPane.getStylesheets().clear();
            borderPane.getStylesheets().add(Objects.requireNonNull(getClass().getResource("darkStyles.css")).toExternalForm());
            switchArrows.setImage(new Image("https://cdn-icons-png.flaticon.com/512/5436/5436307.png"));
            menuDarkAndLight.setText("Light Theme");
        }
    }


    public void MoneyTransferResourcesItemEH(ActionEvent actionEvent) {

    }

    public void currencyAmountEntry(KeyEvent actionEvent) {

    }
}