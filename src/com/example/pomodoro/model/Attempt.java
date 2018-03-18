package com.example.pomodoro.model;

public class Attempt {
    private String message;
    private int remainingSeconds;
    private AttemptKind kind;

    public Attempt(AttemptKind kind, String message) {
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

    public void tick() {
        remainingSeconds--;
    }

    public void save() {
        System.out.printf("Saving: %s, %n", this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Attempt{");
        sb.append("kind=").append(kind);
        sb.append(", message='").append(message).append('\'');
        sb.append(", remainingSeconds=").append(remainingSeconds);
        sb.append('}');
        return sb.toString();
    }
}
