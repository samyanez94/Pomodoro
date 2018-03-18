package com.example.pomodoro.controllers;

import com.example.pomodoro.model.Attempt;
import com.example.pomodoro.model.AttemptKind;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;

public class Home {
    @FXML private VBox container;
    @FXML private Label title;
    @FXML private TextArea message;

    private Attempt currentAttempt;
    private StringProperty timerText;
    private Timeline timeline;
    private AudioClip applause;

    public Home() {
        timerText = new SimpleStringProperty();
        setTimerText(0);
        applause = new AudioClip(getClass().getResource("/sounds/applause.mp3").toExternalForm());
    }

    private void prepareAttempt(AttemptKind kind) {
        reset();
        currentAttempt = new Attempt(kind, "");
        setUpTimeline(kind);
        addAttemptStyle(kind);
        title.setText(kind.getDisplayName());
        setTimerText(currentAttempt.getRemainingSeconds());
    }

    private void clearAttemptStyles() {
        container.getStyleClass().remove("playing");
        for (AttemptKind kind : AttemptKind.values()) {
            container.getStyleClass().remove(kind.toString().toLowerCase());
        }
    }

    private void reset() {
        clearAttemptStyles();
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.stop();
        }
    }

    private void setUpTimeline(AttemptKind kind) {
        timeline = new Timeline();
        timeline.setCycleCount(kind.getTotalSeconds());
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), e -> {
            currentAttempt.tick();
            setTimerText(currentAttempt.getRemainingSeconds());
        }));
        timeline.setOnFinished(e -> {
            saveCurrentAttempt();
            applause.play();
            prepareAttempt(currentAttempt.getKind() == AttemptKind.FOCUS ? AttemptKind.BREAK : AttemptKind.FOCUS);
        });
    }

    private void addAttemptStyle(AttemptKind kind) {
        container.getStyleClass().add(kind.toString().toLowerCase());
    }

    public String getTimerText() {
        return timerText.get();
    }

    public StringProperty timerTextProperty() {
        return timerText;
    }

    private void setTimerText(String timerText) {
        this.timerText.set(timerText);
    }

    private void setTimerText(int remainingSeconds) {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        setTimerText(String.format("%02d:%02d", minutes, seconds));
    }

    private void saveCurrentAttempt() {
        currentAttempt.setMessage(message.getText());
        currentAttempt.save();
    }

    private void playTimer() {
        container.getStyleClass().add("playing");
        timeline.play();
    }

    private void pauseTimer() {
        container.getStyleClass().remove("playing");
        timeline.pause();
    }

    public void handlePlay() {
        if (currentAttempt == null)
            handleRestart();
        else
            playTimer();
    }

    public void handlePause() {
        pauseTimer();
    }

    public void handleRestart() {
        prepareAttempt(AttemptKind.FOCUS);
        playTimer();
    }
}
