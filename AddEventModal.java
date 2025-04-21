import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.function.Consumer;

public class AddEventModal extends JDialog {
    public AddEventModal(Consumer<Event> onAdd) {
        setTitle("Add Event");
        setSize(300, 300);
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(0, 1));
        JTextField nameField = new JTextField();
        JTextField dateTimeField = new JTextField("YYYY-MM-DDTHH:MM");
        String[] types = {"Deadline", "Meeting"};
        JComboBox<String> typeBox = new JComboBox<>(types);

        JTextField endTimeField = new JTextField("YYYY-MM-DDTHH:MM");
        JTextField locationField = new JTextField();

        form.add(new JLabel("Event Name:"));
        form.add(nameField);
        form.add(new JLabel("Start Time:"));
        form.add(dateTimeField);
        form.add(new JLabel("Type:"));
        form.add(typeBox);

        form.add(new JLabel("End Time (for Meeting):"));
        form.add(endTimeField);
        form.add(new JLabel("Location (for Meeting):"));
        form.add(locationField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                LocalDateTime start = LocalDateTime.parse(dateTimeField.getText());

                Event event;
                if (typeBox.getSelectedItem().equals("Meeting")) {
                    LocalDateTime end = LocalDateTime.parse(endTimeField.getText());
                    String location = locationField.getText();
                    event = new Meeting(name, start, end, location);
                } else {
                    event = new Deadline(name, start);
                }

                onAdd.accept(event);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        add(form, BorderLayout.CENTER);
        add(addButton, BorderLayout.SOUTH);
    }
}
