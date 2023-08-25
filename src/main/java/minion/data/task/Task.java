package minion.data.task;

/**
 * Represents a task created by the user.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task object.
     * @param description Description of task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon depending on whether the task is done.
     * @return the status icon.
     */
    protected String getStatusIcon() {
        return isDone ? "X": "";
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String toStringStorage();
}
