/**
 * @apiNote This is the application layout filed where all the function is being build from the
 * "currency-exchanger-view.fxml" file.
 */
package com.example.currency_exchanger;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import org.controlsfx.control.textfield.TextFields;

import java.util.SortedSet;

public class CurrencyExchangerController {

    @FXML
    public Button convertBtn;
    @FXML
    public ProgressIndicator progressIndicator;
    @FXML
    public ComboBox<Object> currencyEntry2;
    @FXML
    public ComboBox<Object> currencyEntry1;
    @FXML
    public Label currency1Output, currency2Output;
    @FXML
    public TextField amount1TextArea, amount2TextArea;
    public ImageView switchArrows;


    private boolean currenciesEntryAdded;

    private ManipulateAPIData apiDataContainer;
    private final SortedSet<Object> currencies;

    public CurrencyExchangerController() {
        apiDataContainer = new ManipulateAPIData();
        this.currencies = apiDataContainer.getAllCurrenciesNames();
        currenciesEntryAdded = false;
    }

    @FXML
    private boolean loadProgressIndicator() {
        // Create a task that takes some time to complete
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(10); // Simulate work being done
                    updateProgress(i + 1, 100);
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
        return true;
    }


    @FXML
    public void convertCurrency() {
        this.convertBtn.setOnAction(e -> {
            loadProgressIndicator();
        });
    }

    @FXML
    public void fillBoxes(Event event) {
        if (currenciesEntryAdded)
            return;
        this.currencies.forEach(e -> {
            this.currencyEntry1.getItems().add(e);
            this.currencyEntry2.getItems().add(e);
        });
        new AutoCompleteComboBoxListener(currencyEntry1);
        new AutoCompleteComboBoxListener(currencyEntry2);
        this.currenciesEntryAdded = true;
    }

    public void switchCurries(MouseEvent mouseEvent) {
        System.out.println("Clicked");
    }
}

