package com.example.pomodoro.controllers;

import com.example.pomodoro.model.Attemp;
import com.example.pomodoro.model.AttemptKind;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Home {
    @FXML private VBox container;
    @FXML private Label title;

    private Attemp currentAttemp;

    private void prepareAttemp(AttemptKind kind) {
        currentAttemp = new Attemp(kind, "");
        clearAttemptStyles();
        addAttemptStyle(kind);
        title.setText(kind.getDisplayName());
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
