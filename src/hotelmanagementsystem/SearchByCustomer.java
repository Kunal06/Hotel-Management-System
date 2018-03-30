/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotelmanagementsystem;

import static hotelmanagementsystem.Fieldsdata.autocapitalizefirstletter;
import static hotelmanagementsystem.Fieldsdata.onlyint;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class SearchByCustomer extends javax.swing.JPanel {

    Connection conn = new DBconnect().connect();

    /**
     * Creates new form SearchByCustomer
     */
    public SearchByCustomer() {
        initComponents();
       // disableAll();
        custid.setVisible(false);
        loadCustomer();
        load();
    }
    private void load() {

       DefaultTableModel Model;
        Model = (DefaultTableModel) reserve1.getModel();
        Model.setRowCount(0);
        //reset all fields
        fname.setText("First");
        lname.setText("Last");
        street1.setText("Street");
        city1.setText("City");
        prov1.setText("Province");
        pc1.setText("PC");
        phone1.setText("");
        email1.setText("");
        
    }
    //Add Customer to Customer combo

    private void loadCustomer() {
        String first = "";
        String last = "";
        customer.removeAllItems();
        customer.addItem("Select Customer");

        String getcustomer = "select First_Name, Last_Name from Customer order by first_Name";
        try {
            PreparedStatement pa = conn.prepareStatement(getcustomer);
            ResultSet rs = pa.executeQuery();
            while (rs.next()) {
                first = rs.getString("First_Name");
                last = rs.getString("Last_Name");
                customer.addItem(first + " " + last);
            }
        } catch (Exception c) {
        }
    }

    //Load Customer details to fields

    private void loadCustomerDetails() {
        String first = "";
        String last = "";
        String Street = "";
        String City = "";
        String Province = "";
        String PC = "";
        String Phone = "";
        String Email = "";
        int id = 0;

        String Customer = "" + customer.getSelectedItem();
        String[] name = Customer.split(" ");

        String getcustomer = "select * from Customer where First_Name= ? AND Last_Name= ? ";
        try {
            PreparedStatement pa = conn.prepareStatement(getcustomer);
            pa.setString(1, name[0]);
            pa.setString(2, name[1]);
            ResultSet rs = pa.executeQuery();
            while (rs.next()) {

                id = rs.getInt("ID");
                first = rs.getString("First_Name");
                last = rs.getString("Last_Name");
                Street = rs.getString("Street");
                City = rs.getString("City");
                Province = rs.getString("Province");
                PC = rs.getString("PC");
                Phone = rs.getString("Phone");
                Email = rs.getString("Email");
            }
            custid.setText("" + id);
            fname.setText(first);
            lname.setText(last);
            street1.setText(Street);
            city1.setText(City);
            prov1.setText(Province);
            pc1.setText(PC);
            phone1.setText(Phone);
            email1.setText(Email);

        } catch (Exception c) {
        }
    }

    //Disable all fields
    private void disableAll() {
        // custid.setVisible(false);
        fname.setEnabled(false);
        lname.setEnabled(false);
        street1.setEnabled(false);
        city1.setEnabled(false);
        prov1.setEnabled(false);
        pc1.setEnabled(false);
        phone1.setEnabled(false);
        email1.setEnabled(false);

    }

    private void readrequestsintotable() {
        DefaultTableModel Model;
        Model = (DefaultTableModel) reserve1.getModel();
        Model.setRowCount(0);
        //Model.insertRow(Model.getRowCount(), new Object[]{"", "", "","", "", "", "", "", "", "", "","", ""});
                int id= Integer.parseInt(custid.getText());

        String sqlor = "select * from Reservation where Customer= ? ";

        try {
            PreparedStatement pa = conn.prepareStatement(sqlor);
            pa.setInt(1,id);
            ResultSet rs = pa.executeQuery();

            while (rs.next()) {
                int ReservationID = rs.getInt("ReservationID");
                int Customer = rs.getInt("Customer");
                String Room_Type = rs.getString("Room_Type");
                String Room_Number = rs.getString("Room_Number");
                String CreditCard_Number = rs.getString("CreditCard_Number");
                String Expiry = rs.getString("Expiry");
                Date Date_From = rs.getDate("Date_From");
                Date Date_to = rs.getDate("Date_to");
                int cost = rs.getInt("Cost");
                
                Model.insertRow(Model.getRowCount(), new Object[]{ReservationID, Room_Type, Room_Number, CreditCard_Number, Date_From, Date_to, cost});
            }

        } catch (Exception c) {
            c.printStackTrace();
        }
    }
    private void readjoinrequestsintotable() {
        DefaultTableModel Model;
        Model = (DefaultTableModel) reserve1.getModel();
        Model.setRowCount(0);
        //Model.insertRow(Model.getRowCount(), new Object[]{"", "", "","", "", "", "", "", "", "", "","", ""});
                //int id= Integer.parseInt(custid.getText());

        String first = "";
        String last = "";
        String Street = "";
        String City = "";
        String Province = "";
        String PC = "";
        String Phone = "";
        String Email = "";
        int id = 0;
        
        String Customer = ""+ customer.getSelectedItem();
        String[] name = Customer.split(" ");

        String getcustomer = "select ID from Customer where First_Name= ? AND Last_Name= ? ";
        String sqlor = "select * from Reservation inner join Customer on Customer = ID  where Customer= ? ";
        try {
            
            
        } catch (Exception c) {
        }
        

        try {
            
            PreparedStatement pa = conn.prepareStatement(getcustomer);
            pa.setString(1, name[0]);
            pa.setString(2, name[1]);
            ResultSet rs = pa.executeQuery();
            while (rs.next()) {
                id = rs.getInt("ID");
                
            }
            custid.setText(""+id);
            
            PreparedStatement pa1 = conn.prepareStatement(sqlor);
            pa1.setInt(1,id);
            ResultSet rs1 = pa1.executeQuery();

            while (rs1.next()) {
                //CUSTOMER DATA
                id = rs1.getInt("ID");
                first = rs1.getString("First_Name");
                last = rs1.getString("Last_Name");
                Street = rs1.getString("Street");
                City = rs1.getString("City");
                Province = rs1.getString("Province");
                PC = rs1.getString("PC");
                Phone = rs1.getString("Phone");
                Email = rs1.getString("Email");
                
                // RESERVATIONS DATA
                int ReservationID = rs1.getInt("ReservationID");
                String Room_Type = rs1.getString("Room_Type");
                String Room_Number = rs1.getString("Room_Number");
                String CreditCard_Number = rs1.getString("CreditCard_Number");
                String Expiry = rs1.getString("Expiry");
                Date Date_From = rs1.getDate("Date_From");
                Date Date_to = rs1.getDate("Date_to");
                int cost = rs1.getInt("Cost");
                System.out.println(Room_Number);
                Model.insertRow(Model.getRowCount(), new Object[]{ReservationID, Room_Type, Room_Number, CreditCard_Number, Date_From, Date_to, cost});
            }
            custid.setText("" + id);
            fname.setText(first);
            lname.setText(last);
            street1.setText(Street);
            city1.setText(City);
            prov1.setText(Province);
            pc1.setText(PC);
            phone1.setText(Phone);
            email1.setText(Email);

        } catch (Exception c) {
            c.printStackTrace();
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        street1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        prov1 = new javax.swing.JTextField();
        city1 = new javax.swing.JTextField();
        lname = new javax.swing.JTextField();
        pc1 = new javax.swing.JTextField();
        phone1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        email1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        reserve1 = new javax.swing.JTable();
        customer = new javax.swing.JComboBox();
        custid = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel1.setText("Search By Customer");

        jLabel2.setText("Customer Details");

        jLabel3.setText("Bookings Made");

        street1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                street1MouseClicked(evt);
            }
        });
        street1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                street1KeyTyped(evt);
            }
        });

        jLabel11.setText("Address");

        prov1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prov1MouseClicked(evt);
            }
        });
        prov1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prov1KeyTyped(evt);
            }
        });

        city1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                city1MouseClicked(evt);
            }
        });
        city1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                city1KeyTyped(evt);
            }
        });

        lname.setText("Last");
        lname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                lnameFocusLost(evt);
            }
        });
        lname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lnameMouseClicked(evt);
            }
        });

        pc1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pc1MouseClicked(evt);
            }
        });
        pc1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pc1KeyTyped(evt);
            }
        });

        phone1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phone1KeyTyped(evt);
            }
        });

        jLabel7.setText("Phone No.");

        jLabel5.setText("Name");

        jLabel8.setText("Email Address");

        fname.setText("First");
        fname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fnameFocusLost(evt);
            }
        });
        fname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fnameMouseClicked(evt);
            }
        });

        jButton1.setText("Save Changes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        reserve1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Room Type", "Room No.", "Credit Card Used", "Date From", "Date To", "Cost"
            }
        ));
        jScrollPane1.setViewportView(reserve1);

        customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerActionPerformed(evt);
            }
        });

        custid.setText("jLabel4");

        jButton2.setText("Delete Customer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(187, 187, 187))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel11)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(street1, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(city1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prov1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pc1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(phone1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel5)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(custid)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(customer, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(custid)
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(street1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(city1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(prov1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(phone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(email1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(27, 27, 27)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(121, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void street1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_street1MouseClicked
        String street = street1.getText();
        if (street.equals("Street")) {
            street1.setText("");
        }     // TODO add your handling code here:
    }//GEN-LAST:event_street1MouseClicked

    private void street1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_street1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_street1KeyTyped

    private void prov1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prov1MouseClicked
        String prov = prov1.getText();
        if (prov.equals("City")) {
            prov1.setText("");
        }    // TODO add your handling code here:
    }//GEN-LAST:event_prov1MouseClicked

    private void prov1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prov1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_prov1KeyTyped

    private void city1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_city1MouseClicked
        String city = city1.getText();
        if (city.equals("City")) {
            city1.setText("");
        }   // TODO add your handling code here:
    }//GEN-LAST:event_city1MouseClicked

    private void city1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_city1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_city1KeyTyped

    private void lnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lnameFocusLost
        autocapitalizefirstletter(lname);
    }//GEN-LAST:event_lnameFocusLost

    private void lnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lnameMouseClicked
        String lastname = lname.getText();
        if (lastname.equals("Last")) {
            lname.setText("");
        }         // TODO add your handling code here:
    }//GEN-LAST:event_lnameMouseClicked

    private void pc1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pc1MouseClicked
        String pc = pc1.getText();
        if (pc.equals("City")) {
            pc1.setText("");
        }     // TODO add your handling code here:
    }//GEN-LAST:event_pc1MouseClicked

    private void pc1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pc1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_pc1KeyTyped

    private void phone1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phone1KeyTyped
        onlyint(evt);
    }//GEN-LAST:event_phone1KeyTyped

    private void fnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fnameFocusLost
        autocapitalizefirstletter(fname);
    }//GEN-LAST:event_fnameFocusLost

    private void fnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fnameMouseClicked
        String firstname = fname.getText();
        if (firstname.equals("First")) {
            fname.setText("");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_fnameMouseClicked

    private void customerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerActionPerformed
        int id=0 ;
        int count=0;
        String Customer = ""+ customer.getSelectedItem();
        String[] name = Customer.split(" ");

        String getcustomer = "select ID from Customer where First_Name= ? AND Last_Name= ? ";
        try {
            PreparedStatement pa = conn.prepareStatement(getcustomer);
            pa.setString(1, name[0]);
            pa.setString(2, name[1]);
            ResultSet rs = pa.executeQuery();
            while (rs.next()) {
                id = rs.getInt("ID");
                
            }
            custid.setText(""+id);
            
            String countquery= "select count(Customer) as total from Reservation where Customer = ?";
            PreparedStatement pa1 = conn.prepareStatement(countquery);
            pa1.setString(1, name[0]);
            pa1.setString(2, name[1]);
            ResultSet rs1 = pa.executeQuery();
            while (rs1.next()) {
                count = rs1.getInt("total");
                
            }            
        } catch (Exception c) {
        }
        
 
        
        if(count==0){
        loadCustomerDetails();
        readrequestsintotable();
        } else{
        readjoinrequestsintotable();
        }
// TODO add your handling code here:
    }//GEN-LAST:event_customerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String updatequery= "update Customer "
            + "set First_Name = ?, Last_Name= ?, Street = ?, City = ?, Province = ?, PC = ?, Phone = ?, Email = ?"
            + "where ID = ?";
    
    GetDatathenCheckandWriteDatabase(updatequery);
            
            
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    String deletequery= "delete from Customer where ID = ? ";
        String Customer = ""+ customer.getSelectedItem();
        String[] name = Customer.split(" ");

        try {
            PreparedStatement pa = conn.prepareStatement(deletequery);
            pa.setInt(1, Integer.parseInt(custid.getText()));
            int i = pa.executeUpdate();
            if (i > 0) {
                    JOptionPane.showMessageDialog((null), "DEleted");
                } else {
                    JOptionPane.showMessageDialog((null), "Nope");

                }
            
        } catch (Exception c) {
            c.printStackTrace();
        }
        loadCustomer();
        load();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

private void GetDatathenCheckandWriteDatabase(String query) {
        //Get DATA from fields
        String email = email1.getText();
        Email useremail = new Email(email);
        String valid = useremail.validateEmailAddress();
        String firstname = fname.getText();
        String lastname = lname.getText();
        String phone = phone1.getText();
        String street = street1.getText();
        String city = city1.getText();
        String prov = prov1.getText();
        String pc = pc1.getText();
        int id= Integer.parseInt(custid.getText());
        
        
        //Check Validity
        if (firstname.equals("") || firstname.equals("First")) {
            JOptionPane.showMessageDialog(null, "First name is required");
        }
        if (lastname.equals("") || lastname.equals("Last")) {
            JOptionPane.showMessageDialog(null, "Last name is required");
        } else if (phone.equals("")) {
            JOptionPane.showMessageDialog(null, "Phone no. is required");
        } else if (street.equals("") || street.equals("Street")) {
            JOptionPane.showMessageDialog(null, "Street is required");
        } else if (city.equals("") || city.equals("City")) {
            JOptionPane.showMessageDialog(null, "City is required");
        } else if (prov.equals("") || prov.equals("Province")) {
            JOptionPane.showMessageDialog(null, "Province is required");
        } else if (pc.equals("") || pc.equals("Postal Code")) {
            JOptionPane.showMessageDialog(null, "Postal Code is required");
        } else if (valid.equals("Invalid Email Address")) {
            JOptionPane.showMessageDialog(null, valid);
        } else {
            //Write
            try {
                PreparedStatement pa = conn.prepareStatement(query);

                pa.setString(1, firstname);
                pa.setString(2, lastname);
                pa.setString(3, street);
                pa.setString(4, city);
                pa.setString(5, prov);
                pa.setString(6, pc);
                pa.setString(7, phone);
                pa.setString(8, email);
                pa.setInt(9, id);

                int i = pa.executeUpdate();

                if (i > 0) {
                    JOptionPane.showMessageDialog((null), "saved");
                } else {
                    JOptionPane.showMessageDialog((null), "notsaved");

                }
            } catch (SQLException se) {
                //Handle errors for JDBC
                se.printStackTrace();
            } catch (Exception e) {
                //Handle errors for Class.forName
                e.printStackTrace();
            }
          

        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField city1;
    private javax.swing.JLabel custid;
    private javax.swing.JComboBox customer;
    private javax.swing.JTextField email1;
    private javax.swing.JTextField fname;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField lname;
    private javax.swing.JTextField pc1;
    private javax.swing.JTextField phone1;
    private javax.swing.JTextField prov1;
    private javax.swing.JTable reserve1;
    private javax.swing.JTextField street1;
    // End of variables declaration//GEN-END:variables
}
