import javax.swing.*;
import java.time.LocalDateTime;

public class EventPlanner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Event Planner");
        EventListPanel listPanel = new EventListPanel();

        addDefaultEvents(listPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(listPanel);
        frame.setSize(800, 600);
        frame.setVisible(true);

        // Periodically notify urgency changes
        new Timer(60000, e -> UrgencyManager.notifyObservers()).start(); // every minute
    }

    public static void addDefaultEvents(EventListPanel panel) {
        panel.addEvent(EventFactory.createEvent("deadline", "Submit Homework", LocalDateTime.now().plusDays(1), null, null));
        panel.addEvent(EventFactory.createEvent("meeting", "Project Meeting", LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(3), "Library"));
    }
}