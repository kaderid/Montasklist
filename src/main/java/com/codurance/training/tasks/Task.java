package com.codurance.training.tasks;

public final class Task {
    private final Id id;
    public final Description description;
    private Done done;

    public Task(Id id, Description description, Done done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    public Id getId() {
        return id;
    }

    public Description getDescription() {
        return description;
    }

    public Boolean isDone() {
        return done.isDone();
    }

    public void setDone(Done done) {
        this.done = done;
    }
    
}