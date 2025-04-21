import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Meeting extends Event implements Completable {
    private LocalDateTime endDateTime;
    private String location;
    private boolean complete = false;
    private ArrayList<Reminder> reminders = new ArrayList<>();

    public Meeting(String name, LocalDateTime start, LocalDateTime end, String location) {
        super(name, start);
        this.endDateTime = end;
        this.location = location;
    }

    @Override
    public String getName() {
        return name;
    }

    public LocalDateTime getEndTime() {
        return endDateTime;
    }

    public Duration getDuration() {
        return Duration.between(dateTime, endDateTime);
    }

    public String getLocation() {
        return location;
    }

    public void setEndTime(LocalDateTime end) {
        this.endDateTime = end;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void complete() {
        complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    public void addReminder(int daysBefore, int hoursBefore, int minutesBefore) {
        Duration timeBefore = Duration.ofDays(daysBefore).plusHours(hoursBefore).plusMinutes(minutesBefore);
        reminders.add(new Reminder(this, timeBefore));
    }

    public ArrayList<Reminder> getReminders() {
        return reminders;
    }
}
