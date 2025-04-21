import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.Duration;

public class Deadline extends Event implements Completable {
    private boolean complete = false;
    private ArrayList<Reminder> reminders = new ArrayList<>();

    public Deadline(String name, LocalDateTime deadline) {
        super(name, deadline);
    }

    @Override
    public String getName() {
        return name;
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
