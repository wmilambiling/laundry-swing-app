
package team3.form;


import javax.swing.*;
import java.awt.*;

public class Testing extends JFrame {

    public Testing() {
        setTitle("GridBagLayout Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Button 1
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        panel.add(new JButton("Button 1"), gbc);

        // Button 2 (spans two columns)
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fills horizontally
        panel.add(new JButton("Button 2"), gbc);

        // Button 3
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset gridwidth
        gbc.fill = GridBagConstraints.NONE; // Reset fill
        panel.add(new JButton("Button 3"), gbc);

        // Text Area (fills both horizontally and vertically, takes extra space)
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; // Give extra horizontal space
        gbc.weighty = 1.0; // Give extra vertical space
        panel.add(new JTextArea("This is a text area."), gbc);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Testing::new);
    }
}
