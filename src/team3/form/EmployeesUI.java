package team3.form;

import com.raven.datechooser.DateChooser;
import com.raven.datechooser.SelectedDate;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import raven.alerts.MessageAlerts;
import raven.popup.component.PopupCallbackAction;
import raven.popup.component.PopupController;
import team3.tabbed.TabbedForm;
import team3.tabbed.WindowsTabbed;
import raven.toast.Notifications;
import team3.sql.EmployeeCrud;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableRowSorter;


public class EmployeesUI extends TabbedForm {
    final int limit = 20;
    private EmployeeCrud crud;
    private DefaultTableModel model;
    private TableRowSorter<DefaultTableModel> rowSorter;

    public EmployeesUI() {
        initComponents();
        crud = new EmployeeCrud();  // initialize your CRUD
    	model = (DefaultTableModel) tblEmployees.getModel();
    	rowSorter = new TableRowSorter<>(model);
    	tblEmployees.setRowSorter(rowSorter);
    }   	
    
    private JPanel createInputPanel(Object[] data) {
        //JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        
        // setup the source of combobox
        EmployeeCrud employeeCrud = new EmployeeCrud();
        int roleCount = employeeCrud.getAllRoles().size();
        // pass the role array to the JComboBox constructor
        String[] allRoles = employeeCrud.getAllRoles().toArray(new String[roleCount]);        
        JComboBox<String> textRole = new JComboBox<String>(allRoles);  
        textRole.setSelectedItem(data != null ? (data[3].toString()) : "");        
        
        JTextField textFirstName    = new JTextField(data != null ? data[1].toString() : "");
        JTextField textLastName     = new JTextField(data != null ? data[2].toString() : "");
        JTextField textUsername     = new JTextField(data != null ? data[4].toString() : "");
        JTextField textPassword     = new JTextField(data != null ? data[5].toString() : "");
        JTextField textDateHired    = new JTextField(data != null ? data[6].toString() : "");
        
        // Calendar
        DateChooser objCal = new DateChooser();
        objCal.setDateFormat("yyyy-MM-dd");
        objCal.setTextRefernce(textDateHired);
        objCal.setBackground(Color.decode("#32739B"));
        objCal.setForeground(Color.decode("#ACDFFF"));
        
//        String dateString = data != null ? data[6].toString() : "";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-d-yyyy");
//        LocalDate date = LocalDate.parse(dateString, formatter);
        
        String dateString = textDateHired.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        try{
            Date parsedDate = dateFormat.parse(dateString);
            objCal.setSelectedDate(parsedDate);
        } catch(ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());        
        }
        
        //objCal.setVisible(false);
        // Button control of Calendar
        JButton btnCalendar = new JButton("Calendar");
        btnCalendar.addActionListener((ActionEvent e) -> {
            System.out.println("Calendar Button clicked!");
            //objCal.setVisible(true);
            objCal.showPopup();
        });	


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String[] labels = {"Role:","First Name:","Last Name:","Username:","Password:","Date Hired:"};
        List<JComponent> objNames = new ArrayList<JComponent>();
            objNames.add(textRole);
            objNames.add(textFirstName);
            objNames.add(textLastName);
            objNames.add(textUsername);
            objNames.add(textPassword);
            objNames.add(textDateHired);

        for (int i = 0; i < 6; i++){
            // Labels
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.insets = new Insets(5, 5, 5, 5); // Padding
            panel.add(new JLabel(labels[i]), gbc);

            // TextFields (spans two columns)
            gbc.gridx = 1;
            gbc.gridy = i;
            gbc.gridwidth = 2;
            //gbc.fill = GridBagConstraints.HORIZONTAL; // Fills horizontally
            panel.add(objNames.get(i),gbc);        
        }
        
        // Button
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        panel.add(new JButton("Calendar"), gbc);

        // Calendar
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fills horizontally
        panel.add(objCal,gbc);
                
        return panel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        dateChooser = new com.raven.datechooser.DateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel2 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        btnDeleteEmployee = new javax.swing.JButton();
        SPaneEmployees = new javax.swing.JScrollPane();
        tblEmployees = new javax.swing.JTable();
        btnAddEmployee = new javax.swing.JButton();
        btnUpdateEmployee = new javax.swing.JButton();
        txtLastName = new javax.swing.JTextField();
        txtUsername = new javax.swing.JTextField();
        cbxRole = new javax.swing.JComboBox<>();
        btnClearTextFields = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        txtDateHired = new javax.swing.JTextField();
        btnChooser = new com.raven.datechooser.Button();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jTextField1.setText("jTextField1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jToolBar1.setRollover(true);

        jLabel1.setText("jLabel1");

        jTextField3.setText("jTextField3");

        dateChooser.setForeground(new java.awt.Color(50, 115, 155));
        dateChooser.setDateFormat("MM/d/yyyy");
        dateChooser.setTextRefernce(txtDateHired);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(50, 115, 155));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Paytone One", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(50, 115, 155));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Employees Form");

        txtFirstName.setFont(txtFirstName.getFont().deriveFont(txtFirstName.getFont().getSize()+2f));
        txtFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFirstNameActionPerformed(evt);
            }
        });

        btnDeleteEmployee.setBackground(new java.awt.Color(255, 102, 102));
        btnDeleteEmployee.setFont(btnDeleteEmployee.getFont().deriveFont(btnDeleteEmployee.getFont().getSize()+2f));
        btnDeleteEmployee.setText("DELETE");
        btnDeleteEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteEmployeeActionPerformed(evt);
            }
        });

        tblEmployees.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "FIRST NAME", "LAST NAME", "ROLE", "USERNAME", "PASSWORD", "DATE HIRED"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblEmployees.setColumnSelectionAllowed(true);
        SPaneEmployees.setViewportView(tblEmployees);
        tblEmployees.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tblEmployees.getColumnModel().getColumnCount() > 0) {
            tblEmployees.getColumnModel().getColumn(0).setResizable(false);
            tblEmployees.getColumnModel().getColumn(1).setResizable(false);
            tblEmployees.getColumnModel().getColumn(3).setResizable(false);
            tblEmployees.getColumnModel().getColumn(5).setResizable(false);
            tblEmployees.getColumnModel().getColumn(6).setResizable(false);
        }

        btnAddEmployee.setBackground(new java.awt.Color(172, 249, 118));
        btnAddEmployee.setFont(btnAddEmployee.getFont().deriveFont(btnAddEmployee.getFont().getSize()+2f));
        btnAddEmployee.setText("ADD");
        btnAddEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEmployeeActionPerformed(evt);
            }
        });

        btnUpdateEmployee.setBackground(new java.awt.Color(172, 223, 255));
        btnUpdateEmployee.setFont(btnUpdateEmployee.getFont().deriveFont(btnUpdateEmployee.getFont().getSize()+2f));
        btnUpdateEmployee.setText("UPDATE");
        btnUpdateEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateEmployeeActionPerformed(evt);
            }
        });

        txtLastName.setFont(txtLastName.getFont().deriveFont(txtLastName.getFont().getSize()+2f));

        txtUsername.setFont(txtUsername.getFont().deriveFont(txtUsername.getFont().getSize()+2f));

        cbxRole.setFont(cbxRole.getFont().deriveFont(cbxRole.getFont().getSize()+6f));
        cbxRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "Laundry Staff", "Delivery Boys" }));
        cbxRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxRoleActionPerformed(evt);
            }
        });

        btnClearTextFields.setBackground(new java.awt.Color(255, 153, 0));
        btnClearTextFields.setFont(btnClearTextFields.getFont().deriveFont(btnClearTextFields.getFont().getSize()+2f));
        btnClearTextFields.setText("CLEAR");
        btnClearTextFields.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearTextFieldsActionPerformed(evt);
            }
        });

        jLabel3.setText("First Name");

        jLabel4.setText("Last Name");

        jLabel5.setText("Role");

        jLabel6.setText("Username");

        jLabel7.setText("Password");

        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        jLabel8.setText("Date Hired");

        txtDateHired.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDateHiredActionPerformed(evt);
            }
        });

        btnChooser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        btnChooser.setText("...");
        btnChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooserActionPerformed(evt);
            }
        });

        jLayeredPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtFirstName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnDeleteEmployee, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(SPaneEmployees, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnAddEmployee, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnUpdateEmployee, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtLastName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtUsername, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cbxRole, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnClearTextFields, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtPassword, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtDateHired, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnChooser, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                .addComponent(txtUsername))
                            .addComponent(jLabel3)))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                        .addComponent(txtLastName))
                    .addComponent(jLabel4)
                    .addComponent(jLabel7))
                .addGap(11, 11, 11)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(txtDateHired, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbxRole, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                            .addComponent(btnClearTextFields, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUpdateEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                            .addComponent(btnDeleteEmployee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(29, 29, 29))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel5))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SPaneEmployees))
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdateEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDeleteEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnClearTextFields, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbxRole, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDateHired, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                            .addComponent(btnChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addGap(18, 18, 18)
                .addComponent(SPaneEmployees, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jLayeredPane1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFirstNameActionPerformed

    private void btnDeleteEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteEmployeeActionPerformed
        int selectedRow = tblEmployees.getSelectedRow();

        if (selectedRow != -1) { // Check if a row is actually selected
            DefaultTableModel model = (DefaultTableModel) tblEmployees.getModel();

            // Assuming the unique ID is in the first column (index 0)
            Object idObject = model.getValueAt(selectedRow, 0); 
            int recordId = Integer.parseInt(idObject.toString()); // Convert ID to appropriate type

            try {
                // Delete from database
                EmployeeCrud employeeCrud = new EmployeeCrud();
                employeeCrud.deleteEmployee(recordId);

                // Delete from JTable
                model.removeRow(selectedRow);

                JOptionPane.showMessageDialog(null, "Record deleted successfully!");
                
                employeeCrud.getAllEmployees(tblEmployees);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error deleting record: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to delete.", "No Row Selected", JOptionPane.WARNING_MESSAGE);
        } //FINISHED!
    }//GEN-LAST:event_btnDeleteEmployeeActionPerformed

    private void btnAddEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEmployeeActionPerformed
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String userName = txtUsername.getText();
        
        char[] chArray = txtPassword.getPassword();
        String passWord = new String(chArray);
        
        String roleName = String.valueOf(cbxRole.getSelectedItem());
        System.out.println("Role Name");
        
        SelectedDate d = dateChooser.getSelectedDate();
        String dateHired = d.getYear() + "-" + d.getMonth() + "-" + d.getDay();
        
        EmployeeCrud employeeCrud = new EmployeeCrud();
        employeeCrud.addEmployee(roleName,firstName, lastName, userName, passWord, dateHired); // add to database
        employeeCrud.getAllEmployees(tblEmployees); // refresh the table
    }//GEN-LAST:event_btnAddEmployeeActionPerformed

    private void btnUpdateEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateEmployeeActionPerformed
                
        int selectedRow = tblEmployees.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select an Employee to update.");
            return;
        }

        Object[] currentData = new Object[7];
        for (int i = 0; i < 7; i++) {
            currentData[i] = model.getValueAt(selectedRow, i);
        }

        JPanel panel = createInputPanel(currentData); // pre-fill with current values
        
        int result = JOptionPane.showConfirmDialog(this, panel, 
                "Update Employee", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                
                List<String> texts = new ArrayList<>();
                 // List all immediate components in mainPanel using a for-each loop
                System.out.println("Components in InputPanel:");
                
                for (Component component : panel.getComponents()) {
                    // list all components
                    //System.out.println("  - " + component.getClass().getSimpleName());
                    
                    if (component instanceof JComboBox) {
                        // get the selected value in the Role combobox
                        JComboBox<String> cbx = (JComboBox<String>) panel.getComponent(1);
                        String roleName = String.valueOf(cbx.getSelectedItem());
                        texts.add(roleName);
                    } else if (component instanceof JTextField) {
                        JTextField textField = (JTextField) component;
                        // System.out.println("    Text field content: " + textField.getText());
                        texts.add(textField.getText());
                    }
                }                

                // 1️⃣ Update database
                crud.updateEmployee(
                    (int) currentData[0],   // employee ID                        
                    texts.get(0),    // role
                    texts.get(1),    // first name
                    texts.get(2),    // last name
                    texts.get(3),    // username
                    texts.get(4),    // password
                    texts.get(5)     // date hired
                );

                // 2️⃣ Update the table directly
                model.setValueAt(texts.get(1), selectedRow, 1);
                model.setValueAt(texts.get(2), selectedRow, 2);
                model.setValueAt(texts.get(0), selectedRow, 3);
                model.setValueAt(texts.get(3), selectedRow, 4);
                model.setValueAt(texts.get(4), selectedRow, 5);
                model.setValueAt(texts.get(5), selectedRow, 6);
                
                JOptionPane.showMessageDialog(this, "Employee updated successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
                                           
          
        
    }//GEN-LAST:event_btnUpdateEmployeeActionPerformed

    private void btnClearTextFieldsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearTextFieldsActionPerformed
        txtFirstName.setText("");
        txtLastName.setText("");
        txtUsername.setText("");
        txtPassword.setText("");
        txtDateHired.setText("");
        cbxRole.setSelectedIndex(-1);
    }//GEN-LAST:event_btnClearTextFieldsActionPerformed

    private void cbxRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxRoleActionPerformed
        String roleName = String.valueOf(cbxRole.getSelectedItem());
        if (roleName.equals("admin")){
            txtUsername.setEnabled(true);
            txtPassword.setEnabled(true);
        } else {
            txtUsername.setEnabled(false);
            txtPassword.setEnabled(false);
        }
    }//GEN-LAST:event_cbxRoleActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void txtDateHiredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDateHiredActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDateHiredActionPerformed

    private void btnChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooserActionPerformed
        dateChooser.showPopup();
    }//GEN-LAST:event_btnChooserActionPerformed

    void displayAlert(String strMessage){        
		
        MessageAlerts.getInstance().showMessage("Data Saving Failure", strMessage, 
        MessageAlerts.MessageType.ERROR, 
        MessageAlerts.OK_OPTION, 
        new PopupCallbackAction() {
            @Override
            public void action(PopupController pc, int i) {
                if (i == MessageAlerts.OK_OPTION) {
                    System.out.println("Click ok");
                }
            }
        });
    }
    
    @Override
    public boolean formClose() {        
        int opt = JOptionPane.showConfirmDialog(this, "Data not yet saved. Do you want to close ?", "Close", JOptionPane.YES_NO_OPTION);
        return opt == JOptionPane.YES_OPTION;
    }

    @Override
    public void formOpen() {
        EmployeeCrud employeeCrud = new EmployeeCrud();
        employeeCrud.getAllEmployees(tblEmployees);
        System.out.println("Employees Form open");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane SPaneEmployees;
    private javax.swing.JButton btnAddEmployee;
    private com.raven.datechooser.Button btnChooser;
    private javax.swing.JButton btnClearTextFields;
    private javax.swing.JButton btnDeleteEmployee;
    private javax.swing.JButton btnUpdateEmployee;
    private javax.swing.JComboBox<String> cbxRole;
    private com.raven.datechooser.DateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTable tblEmployees;
    private javax.swing.JTextField txtDateHired;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            javax.swing.JFrame frame = new javax.swing.JFrame("Employee Form");
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            //frame.setSize(1000, 600);
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null); // center on screen
            frame.setContentPane(new EmployeesUI()); // add your form panel
            frame.setVisible(true);
        });
    }
}
