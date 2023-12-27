/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package rentcalculator;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.text.DefaultFormatter;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 *
 * @author pesanth
 */
public class TenantDetails extends javax.swing.JFrame {
    DatabaseConnect connectionDB = new DatabaseConnect();
    Connection connection = connectionDB.getConnection();

    /**
     * Creates new form TenantDetails
     */
    public TenantDetails() {
        initComponents();
        centerFrameOnScreen();
        showTenants();
        
        rented.setText("YYYY-MM-DD");
        vacated.setText("YYYY-MM-DD");
        
        JComponent editor = currRented.getEditor();
        JComponent editorAgreement = agreement.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor) editor).getTextField().setEditable(false);
            ((JSpinner.DefaultEditor) editorAgreement).getTextField().setEditable(false);
        }
    }
    
    private void centerFrameOnScreen() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();

        int x = (screenSize.width - frameSize.width) / 2;
        int y = (screenSize.height - frameSize.height) / 2;

        setLocation(x, y);
    }
    
    
public class IndianCurrencyFormatter extends DefaultFormatter {
    @Override
    public Object stringToValue(String text) throws ParseException {
        try {
            // Remove commas from the existing text
            String cleanText = text.replaceAll(",", "");

            // Parse the text
            return Long.parseLong(cleanText);
        } catch (NumberFormatException e) {
            throw new ParseException("Invalid number format", 0);
        }
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value instanceof Number) {
            // Format the value with commas and according to the Indian numbering system
            return formatIndianCurrency((Number) value);
        }
        return super.valueToString(value);
    }

    private String formatIndianCurrency(Number number) {
        long value = number.longValue();

        // Format the value with commas for display in Indian currency format
        DecimalFormat indianFormat = new DecimalFormat("#,##,##0");
        return indianFormat.format(value);
    }
}
    
    private void showTenants(){
        int columnCount;
        
        try {
            PreparedStatement pst = connection.prepareStatement("select * from tenants");
            ResultSet set = pst.executeQuery();
            ResultSetMetaData meta = set.getMetaData();
            columnCount = meta.getColumnCount();
            DefaultTableModel model = (DefaultTableModel)tenants_table.getModel();
            Locale  locale = new Locale("en", "IN");
            NumberFormat indianFormat = NumberFormat.getInstance(locale);
            
            model.setRowCount(0);
            
            while(set.next()){
                Vector v2 = new Vector();           
                
                for(int i = 0; i <= columnCount; i++){
                    v2.add((set.getString("roomNumber")));
                    v2.add((set.getString("name")));
                    v2.add((set.getString("address")));
                    v2.add(indianFormat.format(Double.parseDouble(set.getString("rent"))));
                    v2.add(indianFormat.format(Double.parseDouble(set.getString("advance"))));
                    v2.add((set.getString("dateRented")));
                    v2.add((set.getString("dateVacated")));
                    v2.add((set.getString("currentlyRented")));
                    v2.add((set.getString("agreementValid")));
                    v2.add(indianFormat.format(Double.parseDouble(set.getString("dues"))));
                    v2.add((set.getString("notes")));
                }
                
                model.addRow(v2);
                 System.out.println("Tenants Successfully Loaded");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            System.out.println("Show tenants error: \n" + e);
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JToggleButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        searchNumber = new javax.swing.JTextField();
        delete = new javax.swing.JButton();
        searchName = new javax.swing.JTextField();
        search = new javax.swing.JToggleButton();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tenants_table = new javax.swing.JTable();
        name = new javax.swing.JTextField();
        address = new javax.swing.JTextField();
        notes = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        rent = new javax.swing.JFormattedTextField();
        dues = new javax.swing.JFormattedTextField();
        rented = new javax.swing.JFormattedTextField();
        vacated = new javax.swing.JFormattedTextField();
        currRented = new javax.swing.JSpinner();
        agreement = new javax.swing.JSpinner();
        room = new javax.swing.JFormattedTextField();
        advance = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        back.setText("Previous");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 18)); // NOI18N
        jLabel1.setText("TENANT INFORMATION SYSTEM");

        jLabel2.setText("Room Number:");

        jLabel3.setText("Tenant Name:");

        jLabel4.setText("Address:");

        jLabel5.setText("Rent");

        add.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        add.setText("Add");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        jLabel10.setText("Search by Room Number:");

        update.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        update.setText("Update");
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateMouseClicked(evt);
            }
        });

        jLabel11.setText("Search by Name:");

        jLabel7.setText("Date Rented");

        searchNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchNumberActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        delete.setText("Delete");
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
        });

        searchName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchNameActionPerformed(evt);
            }
        });

        search.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        search.setText("Search");
        search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchMouseClicked(evt);
            }
        });

        jLabel9.setText("Advance");

        jButton1.setFont(new java.awt.Font("Noto Sans", 1, 12)); // NOI18N
        jButton1.setText("Refresh Patients");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        tenants_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Room Number", "Name", "Address", "Rent", "Advance", "Date Rented ", "Date Vacated", "Currently Rented ?", "Is Agreement Valid?", "Dues ", "Notes "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tenants_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tenants_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tenants_table);

        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });

        notes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notesActionPerformed(evt);
            }
        });

        jLabel8.setText("Notes:");

        jLabel6.setText("Date Vacated:");

        jLabel13.setText("Currently Rented?");

        jLabel14.setText("Is Agreement Valid?");

        jLabel15.setText("Dues");

        rent.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.##"))));
        rent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rentActionPerformed(evt);
            }
        });

        dues.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.##"))));

        rented.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        rented.setText("YYYY-MM-DD");

        vacated.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        vacated.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vacatedActionPerformed(evt);
            }
        });

        currRented.setModel(new javax.swing.SpinnerListModel(new String[] {"True", "False"}));
        currRented.setNextFocusableComponent(currRented);

        agreement.setModel(new javax.swing.SpinnerListModel(new String[] {"True", "False"}));

        room.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        room.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomActionPerformed(evt);
            }
        });

        advance.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###.##"))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(notes, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                                .addComponent(address)
                                                .addComponent(room, javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addComponent(rent, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rented, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(vacated, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(currRented, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(agreement, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(dues, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(advance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(add)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(update)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(delete)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(search)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 590, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(568, 568, 568))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(room, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(advance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(rented, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(vacated, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(agreement, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(dues, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(notes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(currRented, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(searchNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(search)
                    .addComponent(add)
                    .addComponent(update)
                    .addComponent(delete)
                    .addComponent(jButton1))
                .addGap(58, 58, 58))
        );

        agreement.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        Home home = new Home();
        home.setVisible(true);
        close();
    }//GEN-LAST:event_backActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        String roomVal = room.getText();
        String nameVal = name.getText();
        String addressVal = address.getText();
        String rentVal = rent.getText().replaceAll(",", "").replaceAll("\\.", "");
        String advanceVal = advance.getText().replaceAll(",", "").replaceAll("\\.", "");
        String rentedVal = rented.getText();
        String vacatedVal = vacated.getText();
        String currRentedVal = currRented.getValue().toString();
        String agreementVal = agreement.getValue().toString();
        String duesVal = dues.getText().replaceAll(",", "");
        String notesVal = notes.getText();

        if(roomVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter patient's date of visit");
        } else if(nameVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter patient's name");
        }else if(addressVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter patient's mobile number");
        }else if(rentVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter patient's address");
        }else if(advanceVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter patient's age");
        }else if(rentedVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter patient's doctor");
        }else if(vacatedVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter patient's ailment");
        }else if(currRentedVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter patient's fees");
        }else if(agreementVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter patient's doctor");
        }else if(duesVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter patient's ailment");
        }else if(notesVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please enter patient's fees");
        }else{

            try{
                PreparedStatement pst = connection.prepareStatement("insert into tenants (roomNumber, name, address, rent, advance, dateRented, dateVacated, currentlyRented, agreementValid, dues, notes) values (?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, roomVal);
                pst.setString(2, nameVal);
                pst.setString(3, addressVal);
                pst.setString(4, rentVal);
                pst.setString(5, advanceVal);
                pst.setString(6, rentedVal);
                pst.setString(7, vacatedVal);
                pst.setString(8, currRentedVal);
                pst.setString(9, agreementVal);
                pst.setString(10, duesVal);
                pst.setString(11, notesVal);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Tenant " + nameVal + " added successfully");
                showTenants();

                room.setText("");
                name.setText("");
                address.setText("");
                rent.setText("");
                dues.setText("");
                rented.setText("");
                vacated.setText("");
                currRented.setValue("True");
                agreement.setValue("True");
                dues.setText("");
                notes.setText("");
            }catch(Exception ex){
                System.out.println("Insert Error: \n" + ex);
            }

        }
    }//GEN-LAST:event_addActionPerformed

    private void updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseClicked

        String roomVal = room.getText();
        String nameVal = name.getText();
        String addressVal = address.getText();
        String rentVal = rent.getText().replaceAll(",", "").replaceAll("\\.", "");
        String advanceVal = advance.getText().replaceAll(",", "").replaceAll("\\.", "");
        String rentedVal = rented.getText();
        String vacatedVal = vacated.getText();
        String currRentedVal = currRented.getValue().toString();
        String agreementVal = agreement.getValue().toString();
        String duesVal = dues.getText();
        String notesVal = notes.getText();

       if(roomVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to update");
        } else if(nameVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to update");
        }else if(addressVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to update");
        }else if(rentVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to update");
        }else if(advanceVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to update");
        }else if(rentedVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to update");
        }else if(vacatedVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to update");
        }else if(currRentedVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to update");
        }else if(agreementVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to update");
        }else if(duesVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to update");
        }else if(notesVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to update");
        }else{

            try{

                DefaultTableModel model = (DefaultTableModel)tenants_table.getModel();
                int selectedIndex = tenants_table.getSelectedRow();
                int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());

                PreparedStatement pst = connection.prepareStatement("update tenants set roomNumber =?, name =?, address =?, rent =?, advance =?, dateRented =?, dateVacated =?, currentlyRented =?, agreementValid =?, dues =?, notes =? where roomNumber=?");

                pst.setString(1, roomVal);
                pst.setString(2, nameVal);
                pst.setString(3, addressVal);
                pst.setString(4, rentVal);
                pst.setString(5, advanceVal);
                pst.setString(6, rentedVal);
                pst.setString(7, vacatedVal);
                pst.setString(8, currRentedVal);
                pst.setString(9, agreementVal);
                pst.setString(10, duesVal);
                pst.setString(11, notesVal);
                pst.setInt(12, id);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Tenant " + nameVal + " updated successfully");
                showTenants();

                room.setText("");
                name.setText("");
                address.setText("");
                rent.setText("");
                dues.setText("");
                rented.setText("");
                vacated.setText("");
                currRented.setValue("True");
                agreement.setValue("True");
                dues.setText("");
                notes.setText("");
            }catch(Exception ex){
                System.out.println("Update Error: \n" + ex);
            }

        }

    }//GEN-LAST:event_updateMouseClicked

    private void searchNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchNumberActionPerformed

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        // TODO add your handling code here:
        String dateVal = room.getText();
        String nameVal = name.getText();
        String addressVal = address.getText();
        String addressVal = rent.getText();
        String ageVal = dues.getText();
        String doctorVal = rented.getText();
        String ailmentVal = vacated.getText();
        String feesVal = currRented.getText();

        if(dateVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to delete");
        } else if(nameVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to delete");
        }else if(addressVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to delete");
        }else if(addressVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to delete");
        }else if(ageVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to delete");
        }else if(doctorVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to delete");
        }else if(ailmentVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to delete");
        }else if(feesVal.equals("")){
            JOptionPane.showMessageDialog(null, "Please select a patient from the table to delete");
        }else{

            try{

                DefaultTableModel model = (DefaultTableModel)tenants_table.getModel();
                int selectedIndex = tenants_table.getSelectedRow();
                int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());

                PreparedStatement pst = connection.prepareStatement("delete from patients where patient_id=?");
                pst.setInt(1, id);

                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Patient " + nameVal + " deleted successfully");
                showTenants();

                room.setText("");
                name.setText("");
                address.setText("");
                rent.setText("");
                dues.setText("");
                rented.setText("");
                vacated.setText("");
                currRented.setText("");
            }catch(Exception ex){
                System.out.println("Delete Error: \n" + ex);
                JOptionPane.showMessageDialog(null, "Update Error: \n" + ex);

            }
        }
    }//GEN-LAST:event_deleteMouseClicked

    private void searchNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchNameActionPerformed

    private void searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchMouseClicked
        // TODO add your handling code here:
        int columnCount;

        if(!searchName.getText().equals("") && !searchNumber.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Please search by either name or mobile number, not both");
        }else if(!searchName.getText().equals("") && searchNumber.getText().equals("")){
            try{

                PreparedStatement pst = connection.prepareStatement("select * from patients where name ilike  ?");
                pst.setString(1, "%" + searchName.getText() + "%");

                ResultSet set = pst.executeQuery();
                ResultSetMetaData meta = set.getMetaData();
                columnCount = meta.getColumnCount();
                DefaultTableModel model = (DefaultTableModel)tenants_table.getModel();

                model.setRowCount(0);

                while(set.next()){
                    Vector v2 = new Vector();

                    for(int i = 0; i <= columnCount; i++){
                        v2.add((set.getString("patient_id")));
                        v2.add((set.getString("date_of_visit")));
                        v2.add((set.getString("name")));
                        v2.add((set.getString("number")));
                        v2.add((set.getString("address")));
                        v2.add((set.getString("age")));
                        v2.add((set.getString("doctor_visited")));
                        v2.add((set.getString("ailment")));
                        v2.add((set.getString("fees")));
                    }

                    model.addRow(v2);
                }
            }catch(Exception ex){
                System.out.println("Search Error: \n" + ex);
                JOptionPane.showMessageDialog(null, "Search Error: \n" + ex);

            }

        }else if(searchName.getText().equals("") && !searchNumber.getText().equals("")){
            try{

                PreparedStatement pst = connection.prepareStatement("select * from patients where number like ?");
                pst.setString(1, "%" + searchNumber.getText() + "%");

                ResultSet set = pst.executeQuery();
                ResultSetMetaData meta = set.getMetaData();
                columnCount = meta.getColumnCount();
                DefaultTableModel model = (DefaultTableModel)tenants_table.getModel();

                model.setRowCount(0);

                while(set.next()){
                    Vector v2 = new Vector();

                    for(int i = 0; i <= columnCount; i++){
                        v2.add((set.getString("patient_id")));
                        v2.add((set.getString("date_of_visit")));
                        v2.add((set.getString("name")));
                        v2.add((set.getString("number")));
                        v2.add((set.getString("address")));
                        v2.add((set.getString("age")));
                        v2.add((set.getString("doctor_visited")));
                        v2.add((set.getString("ailment")));
                        v2.add((set.getString("fees")));
                    }

                    model.addRow(v2);
                }
            }catch(Exception ex){
                System.out.println("Search Error: \n" + ex);
                JOptionPane.showMessageDialog(null, "Search Error: \n" + ex);

            }
        }else{
            showTenants();
        }

    }//GEN-LAST:event_searchMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        showTenants();
    }//GEN-LAST:event_jButton1MouseClicked

    private void tenants_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tenants_tableMouseClicked
        // TODO add your handling code here:

        DefaultTableModel model = (DefaultTableModel)tenants_table.getModel();
        int selectedIndex = tenants_table.getSelectedRow();

        room.setText(model.getValueAt(selectedIndex, 0).toString());
        name.setText(model.getValueAt(selectedIndex, 1).toString());
        address.setText(model.getValueAt(selectedIndex, 2).toString());
        rent.setText(model.getValueAt(selectedIndex, 3).toString());
        advance.setText(model.getValueAt(selectedIndex, 4).toString());
        rented.setText(model.getValueAt(selectedIndex, 5).toString());
        vacated.setText(model.getValueAt(selectedIndex, 6).toString());
        currRented.setToolTipText(model.getValueAt(selectedIndex, 7).toString());
        agreement.setToolTipText(model.getValueAt(selectedIndex, 8).toString());
        dues.setText(model.getValueAt(selectedIndex, 9).toString());
        notes.setText(model.getValueAt(selectedIndex, 10).toString());
    }//GEN-LAST:event_tenants_tableMouseClicked

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void notesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_notesActionPerformed

    private void rentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rentActionPerformed

    private void vacatedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vacatedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vacatedActionPerformed

    private void roomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_roomActionPerformed

    public void close(){
        WindowEvent closeWindow = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(closeWindow);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TenantDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TenantDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TenantDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TenantDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TenantDetails().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JTextField address;
    private javax.swing.JFormattedTextField advance;
    private javax.swing.JSpinner agreement;
    private javax.swing.JToggleButton back;
    private javax.swing.JSpinner currRented;
    private javax.swing.JButton delete;
    private javax.swing.JFormattedTextField dues;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField name;
    private javax.swing.JTextField notes;
    private javax.swing.JFormattedTextField rent;
    private javax.swing.JFormattedTextField rented;
    private javax.swing.JFormattedTextField room;
    private javax.swing.JToggleButton search;
    private javax.swing.JTextField searchName;
    private javax.swing.JTextField searchNumber;
    private javax.swing.JTable tenants_table;
    private javax.swing.JButton update;
    private javax.swing.JFormattedTextField vacated;
    // End of variables declaration//GEN-END:variables
}
