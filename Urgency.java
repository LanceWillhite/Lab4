import java.time.Duration;
import java.time.LocalDateTime;

public enum Urgency {
    DISTANT,
    IMMINENT,
    OVERDUE;

    private static Duration thresholdOfImminence = Duration.ofHours(6);

    public static void setThresholdOfImminence(Duration d) {
        thresholdOfImminence = d;
    }

    public static Urgency getUrgency(LocalDateTime time) {
        LocalDateTime now = LocalDateTime.now();
        if (time.isBefore(now)) {
            return OVERDUE;
        }
        Duration until = Duration.between(now, time);
        if (until.compareTo(thresholdOfImminence) <= 0) {
            return IMMINENT;
        }
        return DISTANT;
    }
}
