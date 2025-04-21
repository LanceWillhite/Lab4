import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class EventPanel extends JPanel implements UrgencyObserver {
    private Event event;
    private JButton completeButton;

    public EventPanel(Event event) {
        this.event = event;
        setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel(event.getName());
        JLabel timeLabel = new JLabel("At: " + event.getDateTime());

        add(nameLabel, BorderLayout.NORTH);
        add(timeLabel, BorderLayout.CENTER);

        if (event instanceof Completable completable) {
            completeButton = new JButton("Complete");
            completeButton.addActionListener(e -> {
                completable.complete();
                updateUrgency();
            });
            add(completeButton, BorderLayout.SOUTH);
        }

        UrgencyManager.register(this);
        updateUrgency();
    }

    @Override
    public void updateUrgency() {
        Urgency urgency = Urgency.getUrgency(event.getDateTime());
        switch (urgency) {
            case OVERDUE -> setBackground(Color.RED);
            case IMMINENT -> setBackground(Color.YELLOW);
            case DISTANT -> setBackground(Color.GREEN);
        }
        repaint();
    }
}