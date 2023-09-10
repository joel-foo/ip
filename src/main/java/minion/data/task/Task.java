package minion.data.task;

/**
 * Represents a task created by the user.
 */
public abstract class Task {
    private static final String TICK_SYMBOL = "\u2713";
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
        return isDone ? TICK_SYMBOL : "X";
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns whether the task contains the query.
     * @param query the query.
     * @return whether the task contains the query.
     */
    public abstract boolean contains(String query);

    /**
     * Returns the string representation of the task for output to user.
     * @return string representation of the task for output to user.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the string representation of the task for storage purposes.
     * @return string representation of the task for storage purposes.
     */
    public abstract String toStringStorage();
}
