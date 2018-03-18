package com.example.pomodoro.controllers;

import com.example.pomodoro.model.Attemp;
import com.example.pomodoro.model.AttemptKind;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Home {
    @FXML private VBox container;
    @FXML private Label title;

    private Attemp currentAttemp;
    private StringProperty timerText;
    private Timeline timeline;

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
        timeline = new Timeline();
        timeline.setCycleCount(kind.getTotalSeconds());
        // TODO: Creating multiple timelines. Need to fix!
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            currentAttemp.tick();
            setTimerText(currentAttemp.getRemainingSeconds());
        }));
        clearAttemptStyles();
        addAttemptStyle(kind);
        title.setText(kind.getDisplayName());
        setTimerText(currentAttemp.getRemainingSeconds());
    }

    public void playTimer() {
        timeline.play();
    }

    public void pauseTimer() {
        timeline.pause();
    }

    private void addAttemptStyle(AttemptKind kind) {
        container.getStyleClass().add(kind.toString().toLowerCase());
    }

    private void clearAttemptStyles() {
        for (AttemptKind kind : AttemptKind.values()) {
            container.getStyleClass().remove(kind.toString().toLowerCase());
        }
    }

    public void handleRestart(ActionEvent actionEvent) {
        prepareAttemp(AttemptKind.FOCUS);
        playTimer();
    }
}
