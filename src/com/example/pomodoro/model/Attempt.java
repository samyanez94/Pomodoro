package com.example.pomodoro.model;

public class Attempt {
    private AttemptKind kind;
    private String message;
    private int remainingSeconds;

    public Attempt(AttemptKind kind, String message) {
        this.kind = kind;
        this.message = message;
        remainingSeconds = kind.getTotalSeconds();
    }

    public AttemptKind getKind() {
        return kind;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public void tick() {
        remainingSeconds--;
    }

    public void save() {
        System.out.printf("Saving: %s, %n", this);
    }

    @Override
    public String toString() {
        return "Attempt {" + "kind = " + kind +
                ", message = '" + message + '\'' +
                ", remainingSeconds = " + remainingSeconds +
                '}';
    }
}
