package team3.initial.swing;

import java.awt.*;
import javax.swing.*;

public class MyTextField extends JTextField {

    private Icon prefixIcon;
    private Icon suffixIcon;
    private int cornerRadius = 15; // Rounded corner radius

    public MyTextField() {
        setOpaque(false); // Allows custom rounded background
        setBorder(BorderFactory.createEmptyBorder(7, 5, 7, 5));
    }

    public Icon getPrefixIcon() {
        return prefixIcon;
    }

    public void setPrefixIcon(Icon prefixIcon) {
        this.prefixIcon = prefixIcon;
        initBorder();
    }

    public Icon getSuffixIcon() {
        return suffixIcon;
    }

    public void setSuffixIcon(Icon suffixIcon) {
        this.suffixIcon = suffixIcon;
        initBorder();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Smooth edges
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill background (rounded shape)
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Paint prefix/suffix icons
        paintIcon(g2);

        // Let default text rendering happen
        super.paintComponent(g2);

        g2.dispose();
    }

    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (prefixIcon != null) {
            Image prefix = ((ImageIcon) prefixIcon).getImage();
            int y = (getHeight() - prefixIcon.getIconHeight()) / 2;
            g2.drawImage(prefix, 5, y, this);
        }
        if (suffixIcon != null) {
            Image suffix = ((ImageIcon) suffixIcon).getImage();
            int y = (getHeight() - suffixIcon.getIconHeight()) / 2;
            g2.drawImage(suffix, getWidth() - suffixIcon.getIconWidth() - 5, y, this);
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
        setBorder(BorderFactory.createEmptyBorder(7, left, 7, right));
    }

    // Optional: adjust corner roundness
    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    public int getCornerRadius() {
        return cornerRadius;
    }
}
