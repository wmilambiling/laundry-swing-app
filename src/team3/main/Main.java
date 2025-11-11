package team3.main;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import java.util.HashSet;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;
import raven.drawer.Drawer;
import team3.drawer.MyDrawerBuilder;
import team3.login.Login;
import raven.popup.GlassPanePopup;
import team3.tabbed.WindowsTabbed;
import raven.toast.Notifications;


public class Main extends javax.swing.JFrame {

    public static Main main;
    private Login loginForm;

    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        init();
    }

    private void init() {
        GlassPanePopup.install(this);
        Notifications.getInstance().setJFrame(this);
        MyDrawerBuilder myDrawerBuilder = new MyDrawerBuilder();
        Drawer.getInstance().setDrawerBuilder(myDrawerBuilder);
        //Drawer.getInstance().getDrawerPanel().setLayout());
        Drawer.getInstance().getDrawerPanel().setBackground(Color.decode("#acdfff"));
        
        WindowsTabbed.getInstance().install(this, body);
        // applyComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        login();
    }

    public void login() {
        if (loginForm == null) {
            loginForm = new Login();
        }
        WindowsTabbed.getInstance().showTabbed(false);
        loginForm.applyComponentOrientation(getComponentOrientation());
        setContentPane(loginForm);
        revalidate();
        repaint();
    }

    public void showMainForm() {
        WindowsTabbed.getInstance().showTabbed(true);
        WindowsTabbed.getInstance().removeAllTabbed();
        setContentPane(body);
        revalidate();
        repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        body.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 1188, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        // FlatRobotoFont.install();
        
        
//        try {
//            InputStream is = Main.class.getResourceAsStream("/team3/resources/PaytoneOne-Regular.ttf");     
//            Font paytoneOne = Font.createFont(Font.TRUETYPE_FONT, is);
//            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(paytoneOne);
//        } catch(Exception e){
//            System.out.println("Cannot load font");
//            e.printStackTrace();
//        }
//        //UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
//        UIManager.put("defaultFont", new Font("PaytoneOne-Regular", Font.PLAIN, 13));
        
        // 1. Load and Register the Custom Font
        Font paytoneOne = null;
        try (InputStream is = Main.class.getResourceAsStream(
	        "/team3/resources/PaytoneOne-Regular.ttf")) 
	{
            paytoneOne = Font.createFont(Font.TRUETYPE_FONT, is);            
            GraphicsEnvironment.getLocalGraphicsEnvironment()
	            .registerFont(paytoneOne);
            
            // OPTIONAL: Verify the actual font name
            System.out.println("Loaded Font Family Name: " + 
	            paytoneOne.getFamily());
            
        } catch(Exception e) {
            System.out.println("Cannot load Paytone One font.");
            e.printStackTrace();
            return; // Exit if font cannot be loaded
        }
        
        FlatRobotoFont.install();
        Font robotoBody = new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 14);

        // 1. Set the PRIMARY (Body) Font 
        UIManager.put("FlatLaf.globalTextFont", robotoBody);
        
        // Set a large size (40f) for the Paytone One font object
        float titleSize = 18f; 
        Font paytoneTitle = paytoneOne.deriveFont(Font.PLAIN, titleSize);        
        

        // Use Paytone One for buttons (if you want large, prominent buttons)
        UIManager.put("Button.font", robotoBody);        
        UIManager.put("Label.font", robotoBody);
        //UIManager.put("Label.font", paytoneTitle);
        
        // 3. Set the default font for components you want to use Roboto
        UIManager.put("TextField.font", robotoBody);
        UIManager.put("TextArea.font", robotoBody);        
        
        //FlatLaf.install();
        
        FlatLaf.registerCustomDefaultsSource("team3.themes");
        
        FlatLightLaf.setup();
        java.awt.EventQueue.invokeLater(() -> {
            main = new Main();
            main.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    // End of variables declaration//GEN-END:variables
}
