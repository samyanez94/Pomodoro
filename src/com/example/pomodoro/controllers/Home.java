package com.example.pomodoro.controllers;

import com.example.pomodoro.model.Attemp;
import com.example.pomodoro.model.AttemptKind;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Home {
    @FXML private VBox container;
    @FXML private Label title;

    private Attemp currentAttemp;
    private StringProperty timerText;

    public Home() {
        timerText = new SimpleStringProperty();
        setTimerText(0);
    }

    public String getTimerText() {
        return timerText.get();
    }

    public StringProperty timerTextProperty() {
        return timerText;
    }

    public void setTimerText(String timerText) {
        this.timerText.set(timerText);
    }

    public void setTimerText(int remainingSeconds) {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        setTimerText(String.format("%02d:%02d", minutes, seconds));
    }

    private void prepareAttemp(AttemptKind kind) {
        currentAttemp = new Attemp(kind, "");
        clearAttemptStyles();
        addAttemptStyle(kind);
        title.setText(kind.getDisplayName());
        setTimerText(currentAttemp.getRemainingSeconds());
    }

    private void addAttemptStyle(AttemptKind kind) {
        container.getStyleClass().add(kind.toString().toLowerCase());
    }

    private void clearAttemptStyles() {
        for (AttemptKind kind : AttemptKind.values()) {
            container.getStyleClass().remove(kind.toString().toLowerCase());
        }
    }
}
