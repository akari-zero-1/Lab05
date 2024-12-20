import javax.swing.*;
import java.awt.*;

public class NumberGrid extends JFrame {
    private JButton[] btnNumbers = new JButton[10];
    private JButton btnDelete, btnReset;
    private JTextField tfDisplay;

    public NumberGrid() {
        tfDisplay = new JTextField();
        tfDisplay.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        JPanel panelButtons = new JPanel(new GridLayout(4, 3));
        addButtons(panelButtons);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(tfDisplay, BorderLayout.NORTH);
        cp.add(panelButtons, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Number Grid");
        setSize(500, 500);
        setVisible(true);
    }

    private void addButtons(JPanel panelButtons) {
        for (int i = 1; i <= 9; i++) {
            btnNumbers[i] = new JButton(String.valueOf(i));
            panelButtons.add(btnNumbers[i]);
            final int num = i;
            btnNumbers[i].addActionListener(e -> tfDisplay.setText(tfDisplay.getText() + num));
        }

        btnDelete = new JButton("DEL");
        panelButtons.add(btnDelete);
        btnDelete.addActionListener(e -> {
            String currentText = tfDisplay.getText();
            if (currentText.length() > 0) {
                tfDisplay.setText(currentText.substring(0, currentText.length() - 1));
            }
        });

        btnNumbers[0] = new JButton("0");
        panelButtons.add(btnNumbers[0]);
        btnNumbers[0].addActionListener(e -> tfDisplay.setText(tfDisplay.getText() + "0"));

        btnReset = new JButton("C");
        panelButtons.add(btnReset);
        btnReset.addActionListener(e -> tfDisplay.setText(""));
    }

    public static void main(String[] args) {
        new NumberGrid();
    }
}
