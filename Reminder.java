import java.time.Duration;
import java.time.LocalDateTime;

public class Reminder extends Event {
    private Duration timeBefore;
    private Event event;

    public Reminder(Event event, Duration timeBefore) {
        super("Reminder: " + event.getName(), event.getDateTime().minus(timeBefore));
        this.event = event;
        this.timeBefore = timeBefore;
    }

    @Override
    public String getName() {
        return "Reminder: " + event.getName() + " at " + event.getDateTime();
    }

    @Override
    public LocalDateTime getDateTime() {
        return event.getDateTime().minus(timeBefore);
    }
}
