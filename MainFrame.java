import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {

    private JTextField userIdField;
    private JTextArea resultArea;
    private RecommenderEngine engine;

    public MainFrame() {
        setTitle("Simple Movie Recommender");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        engine = new RecommenderEngine();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Enter User ID:"));
        userIdField = new JTextField();
        panel.add(userIdField);

        JButton button = new JButton("Get Recommendations");
        panel.add(button);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        panel.add(new JScrollPane(resultArea));

        add(panel);

        button.addActionListener((ActionEvent e) -> {
            try {
                long userId = Long.parseLong(userIdField.getText().trim());
                List<Map.Entry<Long, Double>> recs = engine.getRecommendations(userId, 5);

                if (recs.isEmpty()) {
                    resultArea.setText("No recommendations for user " + userId);
                } else {
                    StringBuilder sb = new StringBuilder("Top Recommendations:\n\n");
                    for (Map.Entry<Long, Double> entry : recs) {
                        sb.append("Item ID: ").append(entry.getKey())
                                .append(" (Score: ").append(String.format("%.2f", entry.getValue())).append(")\n");
                    }
                    resultArea.setText(sb.toString());
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid User ID");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
