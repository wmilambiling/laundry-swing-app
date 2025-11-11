package team3.form;

 import javax.swing.*;
 import javax.swing.event.DocumentEvent;
 import javax.swing.event.DocumentListener;
 import javax.swing.table.DefaultTableModel;
 import javax.swing.table.TableRowSorter;
 import javax.swing.RowFilter;
 import java.sql.*;
 import team3.sql.CustomerCrud;
 import team3.sql.DbConnection;
 import team3.tabbed.TabbedForm;
 import javax.swing.JOptionPane;
 import raven.alerts.MessageAlerts;
 import raven.popup.component.PopupCallbackAction;
 import raven.popup.component.PopupController;
 import team3.tabbed.WindowsTabbed;
 import raven.toast.Notifications;
 import java.awt.Color;
 import java.awt.Font; import java.awt.GridLayout;
 import java.util.List;

public class CustomerForm extends TabbedForm {

	private CustomerCrud crud;
	private DefaultTableModel model;
	private TableRowSorter<DefaultTableModel> rowSorter;

   	public CustomerForm() {
    	initComponents();
    	crud = new CustomerCrud();
    	model = (DefaultTableModel) tblCustomer.getModel();
    	rowSorter = new TableRowSorter<>(model);
    	tblCustomer.setRowSorter(rowSorter);
    
    	setupTable();
    	initListeners();
	cmbShow.setSelectedItem("10");
    	loadTableData(10);
}
	private void setupTable() {
        tblCustomer.getTableHeader().setBackground(new Color(0, 102, 204));
        tblCustomer.getTableHeader().setForeground(Color.WHITE);
        tblCustomer.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tblCustomer.setRowHeight(30);
    }
	private void initListeners() {
    txtSearch.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) { filterTable(txtSearch.getText()); }
        public void removeUpdate(DocumentEvent e) { filterTable(txtSearch.getText()); }
        public void changedUpdate(DocumentEvent e) { filterTable(txtSearch.getText()); }
    });
}

	private void filterTable(String text) {
        if (text.trim().isEmpty()) rowSorter.setRowFilter(null);
        else rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
    }
	private void loadTableData() {
    loadTableData(0);
}

   private void loadTableData(int limit) {
    DefaultTableModel dbModel = crud.getCustomerData(limit);

    // Clear current table
    model.setRowCount(0);

    // Add rows from database
    for (int i = 0; i < dbModel.getRowCount(); i++) {
        Object[] row = new Object[dbModel.getColumnCount()];
        for (int j = 0; j < dbModel.getColumnCount(); j++) {
            row[j] = dbModel.getValueAt(i, j);
        }
        model.addRow(row);
    }

    // Optional: select first row and scroll to top
    if (tblCustomer.getRowCount() > 0) {
        tblCustomer.setRowSelectionInterval(0, 0);
        tblCustomer.scrollRectToVisible(tblCustomer.getCellRect(0, 0, true));
    }
}


    private JPanel createInputPanel(Object[] data) {
        JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));
        JTextField txtFirst = new JTextField(data != null ? data[1].toString() : "");
        JTextField txtLast = new JTextField(data != null ? data[2].toString() : "");
        JTextField txtHouse = new JTextField(data != null ? data[3].toString() : "");
        JTextField txtStreet = new JTextField(data != null ? data[4].toString() : "");
        JTextField txtBarangay = new JTextField(data != null ? data[5].toString() : "");
        JTextField txtCity = new JTextField(data != null ? data[6].toString() : "");
        JTextField txtPhone = new JTextField(data != null ? data[7].toString() : "");

        panel.add(new JLabel("First Name:")); panel.add(txtFirst);
        panel.add(new JLabel("Last Name:"));  panel.add(txtLast);
        panel.add(new JLabel("House #:"));    panel.add(txtHouse);
        panel.add(new JLabel("Street:"));     panel.add(txtStreet);
        panel.add(new JLabel("Barangay:"));   panel.add(txtBarangay);
        panel.add(new JLabel("City:"));       panel.add(txtCity);
        panel.add(new JLabel("Phone:"));      panel.add(txtPhone);

        return panel;
    }

	
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        cmbShow = new javax.swing.JComboBox<>();
        lblShow = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnClear = new javax.swing.JButton();
        lblFirstName = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        lblFirstName1 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        lblFirstName2 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        lblHouseNumber = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        lblStreet = new javax.swing.JLabel();
        lblBarangay = new javax.swing.JLabel();
        lblCity = new javax.swing.JLabel();
        txtHouseNumber1 = new javax.swing.JTextField();
        txtHouseNumber2 = new javax.swing.JTextField();
        txtHouseNumber3 = new javax.swing.JTextField();
        lblSearch = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();

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

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(950, 500));

        jPanel2.setBackground(new java.awt.Color(50, 115, 155));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(50, 115, 155));
        jLabel2.setText("Customers");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        btnAdd.setBackground(new java.awt.Color(153, 255, 153));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd.setText("ADD");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(153, 204, 255));
        btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        cmbShow.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "50", "100", "All" }));
        cmbShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbShowActionPerformed(evt);
            }
        });

        lblShow.setText("Show");

        jLabel4.setText("entries");

        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnClear.setBackground(new java.awt.Color(255, 153, 51));
        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClear.setText("CLEAR");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        lblFirstName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFirstName.setText("Last Name");

        txtLastName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblFirstName1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFirstName1.setText("First Name");

        txtFirstName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblFirstName2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblFirstName2.setText("Phone");

        txtPhone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblHouseNumber.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblHouseNumber.setText("House No.");

        txtCity.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblStreet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblStreet.setText("Street");

        lblBarangay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblBarangay.setText("Barangay");

        lblCity.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCity.setText("City");

        txtHouseNumber1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtHouseNumber2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtHouseNumber3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lblSearch.setText("Search");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(txtHouseNumber3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(lblFirstName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblHouseNumber)
                                            .addComponent(txtHouseNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblBarangay))
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(lblStreet)
                                                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtHouseNumber2, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblCity)
                                                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblFirstName1)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(lblShow)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(cmbShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel4)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(14, 14, 14)
                                .addComponent(lblSearch)
                                .addGap(34, 34, 34)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFirstName2)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFirstName)
                    .addComponent(lblFirstName1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblHouseNumber)
                            .addComponent(lblStreet))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHouseNumber1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHouseNumber2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblBarangay)
                            .addComponent(lblCity))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHouseNumber3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFirstName2)
                .addGap(1, 1, 1)
                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSearch))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblShow)
                        .addComponent(cmbShow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "First Name", "Last Name", "House No.", "Street", "Barangay", "City", "Phone"
            }
        ));
        tblCustomer.setMaximumSize(new java.awt.Dimension(1500, 1200));
        tblCustomer.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblCustomer.setShowHorizontalLines(true);
        tblCustomer.setShowVerticalLines(true);
        jScrollPane1.setViewportView(tblCustomer);
        if (tblCustomer.getColumnModel().getColumnCount() > 0) {
            tblCustomer.getColumnModel().getColumn(0).setPreferredWidth(40);
            tblCustomer.getColumnModel().getColumn(1).setPreferredWidth(120);
            tblCustomer.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblCustomer.getColumnModel().getColumn(3).setPreferredWidth(50);
            tblCustomer.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblCustomer.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblCustomer.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblCustomer.getColumnModel().getColumn(7).setPreferredWidth(100);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtFirstName.setText("");
        txtLastName.setText("");
        txtHouseNumber1.setText("");
        txtHouseNumber2.setText("");
        txtHouseNumber3.setText("");
        txtCity.setText("");
        txtPhone.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void cmbShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbShowActionPerformed
         try {
        String selected = cmbShow.getSelectedItem().toString();
        int limit = selected.equals("All") ? 0 : Integer.parseInt(selected);

        // Clear existing sorter/filter
        rowSorter.setRowFilter(null);

        // Reload table with limit
        loadTableData(limit);

        // Reapply the sorter
        tblCustomer.setRowSorter(rowSorter);

    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_cmbShowActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selView = tblCustomer.getSelectedRow();
        if (selView == -1) {
            JOptionPane.showMessageDialog(this, "Select a customer to delete.");
            return;
        }

        // convert view index to model index (important when sorter/filter is active)
        int selModel = tblCustomer.convertRowIndexToModel(selView);
        int id = (int) model.getValueAt(selModel, 0); // get ID from table model
        int confirm = JOptionPane.showConfirmDialog(this, "Delete customer ID: ?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Delete from database
                crud.deleteCustomer(id);

                // Remove from table model
                model.removeRow(selModel);

                JOptionPane.showMessageDialog(this, "Customer deleted successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        int selectedRow = tblCustomer.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a customer to update.");
            return;
        }

        Object[] currentData = new Object[8];
        for (int i = 0; i < 8; i++) {
            currentData[i] = model.getValueAt(selectedRow, i);
        }

        JPanel panel = createInputPanel(currentData);
        int result = JOptionPane.showConfirmDialog(this, panel, "Update Customer", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                JTextField[] fields = new JTextField[7];
                for (int i = 0; i < 7; i++) {
                    fields[i] = (JTextField) panel.getComponent(i * 2 + 1);
                }

                crud.updateCustomer(
                    (int) currentData[0],
                    fields[0].getText(),
                    fields[1].getText(),
                    fields[2].getText(),
                    fields[3].getText(),
                    fields[4].getText(),
                    fields[5].getText(),
                    fields[6].getText()
                );

                // 2️⃣ Update the table directly
                model.setValueAt(fields[0].getText(), selectedRow, 1);
                model.setValueAt(fields[1].getText(), selectedRow, 2);
                model.setValueAt(fields[2].getText(), selectedRow, 3);
                model.setValueAt(fields[3].getText(), selectedRow, 4);
                model.setValueAt(fields[4].getText(), selectedRow, 5);
                model.setValueAt(fields[5].getText(), selectedRow, 6);
                model.setValueAt(fields[6].getText(), selectedRow, 7);

                JOptionPane.showMessageDialog(this, "Customer updated successfully!");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String first = txtFirstName.getText().trim();
        String last  = txtLastName.getText().trim();
        String house = txtHouseNumber1.getText().trim(); // House No.
        String street = txtHouseNumber2.getText().trim(); // Street
        String barangay = txtHouseNumber3.getText().trim(); // Barangay
        String city = txtCity.getText().trim();
        String phone = txtPhone.getText().trim();

        // Basic validation
        if (first.isEmpty() || last.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Input Text Field!");
            return;
        }

        try {
            // Add to database
            crud.addCustomer(first, last, house, street, barangay, city, phone);

            // Get last inserted ID (assumes your CRUD provides this)
            int newId = crud.getLastInsertedId();

            // Add to table model
            model.addRow(new Object[]{
                newId,
                first,
                last,
                house,
                street,
                barangay,
                city,
                phone
            });

            JOptionPane.showMessageDialog(this, "Customer added successfully!");

            // Clear form fields after successful add
            btnClearActionPerformed(null);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error adding customer: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    @Override
    public boolean formClose() {
        
        int opt = JOptionPane.showConfirmDialog(this, "Data not yet saved. Do you want to close ?", "Close", JOptionPane.YES_NO_OPTION);
        return opt == JOptionPane.YES_OPTION;
    }

    @Override
    public void formOpen() {
        System.out.println("Form open");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbShow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBarangay;
    private javax.swing.JLabel lblCity;
    private javax.swing.JLabel lblFirstName;
    private javax.swing.JLabel lblFirstName1;
    private javax.swing.JLabel lblFirstName2;
    private javax.swing.JLabel lblHouseNumber;
    private javax.swing.JLabel lblSearch;
    private javax.swing.JLabel lblShow;
    private javax.swing.JLabel lblStreet;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtHouseNumber1;
    private javax.swing.JTextField txtHouseNumber2;
    private javax.swing.JTextField txtHouseNumber3;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(() -> {
        javax.swing.JFrame frame = new javax.swing.JFrame("Customer Form - Shayne");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null); // center on screen
        frame.setContentPane(new CustomerForm()); // add your form panel
        frame.setVisible(true);
    });
}
}