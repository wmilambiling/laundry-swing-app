
package team3.sql;

import java.awt.Component;
import java.awt.Image;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;


public class FilterUtil {
    private String lDatePattern = "MMM/d/yyyy"; // single d para di na kelangan ng leading zero
    // sample pwede yung Nov/3/2025 di kelangan maging Nov/03/2025
    // kung LocalDate, LocalTime or LocalDateTime
    
    public void setlDatePattern(String lDatePattern) {
        this.lDatePattern = lDatePattern;
    }
    
    public String getlDatePattern() {
        return lDatePattern;
    }
    
    boolean isNumber(String txtIn) {
        boolean isValid = true;
        try {
            double num = Double.parseDouble(txtIn);
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }
    
    void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +1 , width);
            }
            if(width > 300)
                width = 300;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

// --- Day 23 Additions
    // 3 things:
    // 1. check of date input is valid to the pattern (for user)
    // 2. String to LocalDate (for user)
    // 3. LocalDate to pattern date (for info from database)
    
    boolean isDateValid(String txtDateIn) {
        DateTimeFormatterBuilder dtfBuild = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().appendPattern(lDatePattern);
        DateTimeFormatter dtf = dtfBuild.toFormatter();
        boolean isValid = true;
        try {
            LocalDate lDate = LocalDate.parse(txtDateIn, dtf);
        } catch (Exception e) {
            isValid = false;
        }
        return isValid;
    }
    
    LocalDate convertStrDateToLocalDate(String txtDateIn) {
        DateTimeFormatterBuilder dtfBuild = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().appendPattern(lDatePattern);
        DateTimeFormatter dtf = dtfBuild.toFormatter(); 
        LocalDate lDate = LocalDate.parse(txtDateIn, dtf);
        return lDate;
    }
    // problem: need mo gamitin ang isDateValid method para mag check
    // bale may if statement ka pa

    String covertLocalDateToPatternDate(LocalDate lDateIn) {
        DateTimeFormatterBuilder dtfBuild = new DateTimeFormatterBuilder()
                .parseCaseInsensitive().appendPattern(lDatePattern);
        DateTimeFormatter dtf = dtfBuild.toFormatter(); 
        String convertedDate = dtf.format(lDateIn);
        return convertedDate;
    }
    
    LocalDate convertDateToLocalDate (Date oldDateIn) {
        Instant convDate = oldDateIn.toInstant();
        LocalDate lDate = convDate.atZone(ZoneId.systemDefault()).toLocalDate();
        return lDate;
    }
    
// Day 33 Additions
    ImageIcon resizedIcon1(ImageIcon iconIn, double widRatio, double heiRatio) {
        Image image = iconIn.getImage();
        int wid = (int) (iconIn.getIconWidth() * widRatio);
        int hei = (int) (iconIn.getIconHeight() * heiRatio);
        
        Image newImage = image.getScaledInstance(wid, hei, Image.SCALE_SMOOTH);
        ImageIcon resultIcon = new ImageIcon(newImage);
        return resultIcon;
    }
    
    ImageIcon resizedIcon1(ImageIcon iconIn, int wid, int hei) {
        Image image = iconIn.getImage();
        Image newImage = image.getScaledInstance(wid, hei, Image.SCALE_SMOOTH);
        
        ImageIcon resultIcon = new ImageIcon(newImage);
        return resultIcon;
    }
    
    boolean isFilled(String txtIn) { // true - may laman | false - wala laman
        boolean isFill = !txtIn.trim().isEmpty();
        return isFill;
    }
}
