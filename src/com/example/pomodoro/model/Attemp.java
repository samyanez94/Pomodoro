package com.example.pomodoro.model;

public class Attemp {
    private String message;
    private int remainingSeconds;
    private AttemptKind kind;

    public Attemp(AttemptKind kind, String message) {
        this.kind = kind;
        this.message = message;
        remainingSeconds = kind.getTotalSeconds();
    }

    public String getMessage() {
        return message;
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public AttemptKind getKind() {
        return kind;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
