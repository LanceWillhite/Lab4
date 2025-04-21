import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.stream.Collectors;

public class EventListPanel extends JPanel {
    private ArrayList<Event> events = new ArrayList<>();
    private JPanel displayPanel;
    private JComboBox<String> sortDropDown;
    private JCheckBox filterComplete;
    private JCheckBox filterDeadlines;
    private JCheckBox filterMeetings;

    public EventListPanel() {
        setLayout(new BorderLayout());

        // Control Panel
        JPanel controlPanel = new JPanel();

        sortDropDown = new JComboBox<>(new String[]{"Sort by Date", "Sort by Name", "Reverse Date", "Reverse Name"});
        sortDropDown.addActionListener(e -> refreshEventList());
        controlPanel.add(sortDropDown);

        filterComplete = new JCheckBox("Hide Completed");
        filterDeadlines = new JCheckBox("Hide Deadlines");
        filterMeetings = new JCheckBox("Hide Meetings");

        ItemListener filterListener = e -> refreshEventList();
        filterComplete.addItemListener(filterListener);
        filterDeadlines.addItemListener(filterListener);
        filterMeetings.addItemListener(filterListener);

        controlPanel.add(filterComplete);
        controlPanel.add(filterDeadlines);
        controlPanel.add(filterMeetings);

        JButton addEventButton = new JButton("Add Event");
        addEventButton.addActionListener(e -> {
            AddEventModal modal = new AddEventModal((event) -> {
                addEvent(event);
                refreshEventList();
            });
            modal.setVisible(true);
        });
        controlPanel.add(addEventButton);

        add(controlPanel, BorderLayout.NORTH);

        // Display Panel
        displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
        add(new JScrollPane(displayPanel), BorderLayout.CENTER);
    }

    public void addEvent(Event event) {
        events.add(event);
        refreshEventList();
    }

    private void refreshEventList() {
        displayPanel.removeAll();

        List<Event> filtered = events.stream()
                .filter(e -> !(filterComplete.isSelected() && e instanceof Completable && ((Completable) e).isComplete()))
                .filter(e -> !(filterDeadlines.isSelected() && e instanceof Deadline))
                .filter(e -> !(filterMeetings.isSelected() && e instanceof Meeting))
                .collect(Collectors.toList());

        switch (Objects.requireNonNull(sortDropDown.getSelectedItem()).toString()) {
            case "Sort by Date" -> filtered.sort(Comparator.naturalOrder());
            case "Reverse Date" -> filtered.sort(Comparator.reverseOrder());
            case "Sort by Name" -> filtered.sort(Comparator.comparing(Event::getName));
            case "Reverse Name" -> filtered.sort(Comparator.comparing(Event::getName).reversed());
        }

        for (Event event : filtered) {
            EventPanel ep = new EventPanel(event, this::refreshEventList);
            displayPanel.add(ep);
        }

        revalidate();
        repaint();
    }
}
