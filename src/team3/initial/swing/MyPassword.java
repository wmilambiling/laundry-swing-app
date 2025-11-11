package team3.initial.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MyPassword extends JPasswordField {

    private Icon prefixIcon;
    private Icon suffixIcon;
    private int cornerRadius = 15; // adjust roundness here

    public MyPassword() {
        setBorder(new EmptyBorder(7, 5, 7, 5));
        setOpaque(false); // allows custom background to show properly
        setBackground(new Color(255, 255, 255)); // default white background
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
        repaint();
    }

    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        initBorder();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Enable smooth edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill rounded background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        g2.dispose();

        // Paint text and icons
        super.paintComponent(g);
        paintIcon(g);
    }

    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 8, y, this);
        }
        if (suffixIcon != null) {
            Image suffix = ((ImageIcon) suffixIcon).getImage();
            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 8, y, this);
        }
    }

    private void initBorder() {
        int left = 5;
        int right = 5;
        if (prefixIcon != null) {
            left = prefixIcon.getIconWidth() + 10;
        }
        if (suffixIcon != null) {
            right = suffixIcon.getIconWidth() + 10;
        }
        setBorder(new EmptyBorder(7, left, 7, right));
    }
}
