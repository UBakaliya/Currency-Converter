/**
 * @apiNote This is the application layout filed where all the function is being build from the
 * "currency-exchanger-view.fxml" file.
 */
package com.example.currency_exchanger;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;


public class CurrencyExchangerController {

    @FXML
    public Button convertBtn;
    @FXML
    public ProgressIndicator progressIndicator;

    private void loadProgressIndicator() {
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
    }

    public void convertCurrency() {
        this.convertBtn.setOnAction(e -> loadProgressIndicator());
    }
}

