/**
 * Represents an Event, a task that start at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Creates an event object. This is the main constructor of the Event class.
     * @param description Description of the event.
     * @param isDone Whether the event has been done.
     * @param start Start date/time of event.
     * @param end End date/time of event.
     */
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Creates an event object. This calls the main constructor when the default for isDone is false.
     * @param description Description of the event.
     * @param start Start date/time of event.
     * @param end End date/time of event.
     */
    public Event(String description, String start, String end) {
        this(description, false, start, end);
    }

    /**
     * Returns the string representation of the event.
     * @return string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns a string representation of the event to be used in storage.
     * @return string representation of the event for storage.
     */
    @Override
    public String toStringStorage() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + start + " - " + end;
    }
}
