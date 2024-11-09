import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator {

    // JFrame to hold the calculator components
    private JFrame frame;
    // JTextField to display the results
    private JTextField display;
    // Store the operator and previous value for calculation
    private double num1 = 0, num2 = 0, result = 0;
    private String operator = "";

    public SimpleCalculator() {
        // Initialize the frame
        frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        // Initialize the display (TextField)
        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        frame.add(display, BorderLayout.NORTH);

        // Panel to hold the buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        // Button text and their actions
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Action listener for the buttons
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.charAt(0) == '=') {
                // Perform the calculation
                num2 = Double.parseDouble(display.getText());

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                }

                display.setText(String.valueOf(result));
                operator = "";
            } else if (command.equals("C")) {
                // Clear the display
                display.setText("");
                num1 = num2 = result = 0;
                operator = "";
            } else if (command.equals(".")) {
                // Handle decimal point
                display.setText(display.getText() + ".");
            } else if ("0123456789".contains(command)) {
                // Handle numbers
                display.setText(display.getText() + command);
            } else {
                // Handle operators
                if (display.getText().isEmpty()) return;
                num1 = Double.parseDouble(display.getText());
                operator = command;
                display.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleCalculator());
    }
}
