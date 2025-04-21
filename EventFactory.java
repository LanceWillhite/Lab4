import java.time.LocalDateTime;
import java.time.Duration;

public class EventFactory {
    public static Event createEvent(String type, String name, LocalDateTime start, LocalDateTime end, String location) {
        switch (type.toLowerCase()) {
            case "deadline":
                return new Deadline(name, start);
            case "meeting":
                return new Meeting(name, start, end, location);
            default:
                throw new IllegalArgumentException("Unknown event type: " + type);
        }
    }
}