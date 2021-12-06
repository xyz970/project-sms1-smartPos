/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms1.pages.kasir;

import com.sms1.logic.MyFunction;
import com.formdev.flatlaf.FlatLightLaf;
import com.sms1.components.Login;
import com.sms1.logic.Config;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author adi_sptro
 */
public class Kasir extends javax.swing.JFrame {

    DefaultTableModel DataBarangModel;
    DefaultTableModel DataDetailTransaksiModel;
    String nomorFaktur;

    /**
     * Creates new form main
     */
    public Kasir() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        initComponents();
       this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        InitiateBarang();
        InitiateDetailTransaksi();
        renderFaktur();
        System.out.println(MyFunction.setUserID(username.getText()));
    }

    public void setUsername(String name) {
        username.setText(name);
    }

    final void InitiateTransaksi() {
//        String[] judulTransaksi = {"Kode Barang", "Nama Barang", "Harga Beli", "Harga Jual", "Supplier"};
    }

    final void InitiateGrandTotal(){
         try {
            String sql = "SELECT sum(total) as jumlah FROM transaction_detail \n"
                    + "WHERE transaction_detail.transaction_code = '" + noFaktur.getText() + "';";
            Connection cn = (Connection) Config.MyConfig();
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
//                String[] data = {rs.getString(1)};
                grandTotal.setText(rs.getString(1));
            }

        } catch (SQLException e) {
            MyFunction.showErrorMessage(e.getMessage(), "Error!");
        }
    }
    public final void renderFaktur() {
        int random_1 = MyFunction.randomFaktur();
        int random_2 = MyFunction.randomFaktur();
        int random_3 = MyFunction.random1Digit();
        LocalDate date = java.time.LocalDate.now();
        LocalTime time = java.time.LocalTime.now();
        String finalInt = "00." + random_1 + "." + random_2 + "." + random_3;
        nomorFaktur = finalInt;
        noFaktur.setText(finalInt);
        System.out.println(noFaktur.getText());
        String idUser = MyFunction.setUserID(username.getText());
        System.out.println(idUser);
        try {
            String sql = "INSERT INTO `transactions`(`code`, `user_id`, `date`, `time`, `total`) \n"
                    + "VALUES (\n"
                    + "'"+nomorFaktur+"','"+idUser+"','"+date+"','"+time+"','0'\n"
                    + ");";
            java.sql.Connection conn = (Connection) Config.MyConfig();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
        } catch (SQLException e) {
            MyFunction.showErrorMessage(e.getMessage(), "Error!");
        }
    }

    final void InitiateDetailTransaksi() {
        String[] judul = {"Kode Barang", "Nama Barang", "Kuantitas", "Harga", "Total"};
        DataDetailTransaksiModel = new DefaultTableModel(judul, 0);
        DetailTransaksi.setSelectionMode(0);
        DetailTransaksi.setModel(DataDetailTransaksiModel);
        int row = DetailTransaksi.getRowCount();
        for (int i = 0; i < row; i++) {
            DataDetailTransaksiModel.removeRow(0);
        }
        try {
            String sql = "SELECT items.code, items.name,transaction_detail.qty,items.sell_price, transaction_detail.total FROM transaction_detail \n"
                    + "JOIN items\n"
                    + "ON items.code = transaction_detail.item_code\n"
                    + "WHERE transaction_detail.transaction_code = '"+noFaktur.getText()+"';";
            Connection cn = (Connection) Config.MyConfig();
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String[] data = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                DataDetailTransaksiModel.addRow(data);
            }

        } catch (SQLException e) {
            MyFunction.showErrorMessage(e.getMessage(), "Error!");
        }
    }

    //Inisiasi table barang
    final void InitiateBarang() {
        String[] judul = {"Kode Barang", "Nama Barang", "Harga Beli", "Harga Jual", "Supplier"};
        DataBarangModel = new DefaultTableModel(judul, 0);
        DataBarang.setSelectionMode(0);
        DataBarang.setModel(DataBarangModel);
        int row = DataBarang.getRowCount();
        for (int i = 0; i < row; i++) {
            DataBarangModel.removeRow(0);
        }
        try {
            String sql = "SELECT items.code,items.name,items.buy_price,items.sell_price,suppliers.name from suppliers JOIN items on items.supplier_id = suppliers.id";
            Connection cn = (Connection) Config.MyConfig();
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String[] data = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                DataBarangModel.addRow(data);
            }

        } catch (SQLException e) {
            MyFunction.showErrorMessage(e.getMessage(), "Error!");
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

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        cariBarang = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        DetailTransaksi = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        grandTotal = new javax.swing.JLabel();
        bayar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        noFaktur = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tambah = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        qty_txt = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        username = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DataBarang = new javax.swing.JTable();
        kodeBarang = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Kasir");
        setFocusable(false);
        setSize(new java.awt.Dimension(1366, 768));

        jPanel1.setBackground(new java.awt.Color(48, 8, 136));

        jTextField3.setBorder(null);
        jTextField3.setEnabled(false);

        cariBarang.setBackground(new java.awt.Color(48, 8, 136));
        cariBarang.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cariBarang.setForeground(new java.awt.Color(255, 255, 255));
        cariBarang.setText("Cari..");
        cariBarang.setToolTipText("Cari Barang");
        cariBarang.setBorder(null);
        cariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariBarangActionPerformed(evt);
            }
        });
        cariBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariBarangKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cari Barang");

        jLabel2.setFont(new java.awt.Font("Ubuntu Mono", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Smart POS");

        DetailTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(DetailTransaksi);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel3.setText("Rp");

        grandTotal.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(grandTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(grandTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bayar.setBackground(new java.awt.Color(61, 21, 227));
        bayar.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        bayar.setForeground(new java.awt.Color(255, 255, 255));
        bayar.setText("Bayar");
        bayar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(61, 21, 227), 4, true));
        bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarActionPerformed(evt);
            }
        });

        noFaktur.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noFaktur, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noFaktur, javax.swing.GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("No Faktur");

        tambah.setBackground(new java.awt.Color(61, 21, 227));
        tambah.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        tambah.setForeground(new java.awt.Color(255, 255, 255));
        tambah.setText("Tambah");
        tambah.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(61, 21, 227), 4, true));
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Kode Barang");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Qty");

        qty_txt.setBackground(new java.awt.Color(48, 8, 136));
        qty_txt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        qty_txt.setForeground(new java.awt.Color(255, 255, 255));
        qty_txt.setToolTipText("Isi Kuantitas");
        qty_txt.setBorder(null);

        jTextField8.setBorder(null);
        jTextField8.setEnabled(false);

        username.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("Kasir");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/portrait.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/sign-out-32px.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        DataBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"xxxx", "xxxx", "Rp. xxxx", "Rp. xxxx", null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Harga Beli", "Harga Jual", "Supplier"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DataBarang.getTableHeader().setResizingAllowed(false);
        DataBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataBarangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(DataBarang);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 635, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kodeBarang.setBackground(new java.awt.Color(48, 8, 136));
        kodeBarang.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        kodeBarang.setForeground(new java.awt.Color(255, 255, 255));
        kodeBarang.setToolTipText("Kode Barang");
        kodeBarang.setBorder(null);

        jTextField9.setBorder(null);
        jTextField9.setEnabled(false);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1)
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(4, 4, 4)
                                .addComponent(cariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(410, 410, 410)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addGap(108, 108, 108)
                                                .addComponent(qty_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(75, 75, 75)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(kodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(260, 260, 260)))
                                        .addGap(39, 39, 39))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel10))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(kodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(qty_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarActionPerformed
        // TODO add your handling code here:
//        System.out.println(grandTotal.getText());
//        this.dispose();
int baris = DetailTransaksi.getRowCount();
        if (baris == 0) {
            MyFunction.showErrorMessage("Daftar belanjaan kosong. \nTidak ada yang perlu dibayar", "Error");
        } else {
            try {
            String sql = "UPDATE `transactions` SET `total`='"+grandTotal.getText()+"' WHERE `code`='"+noFaktur.getText()+"';";
            java.sql.Connection conn = (Connection) Config.MyConfig();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            InitiateDetailTransaksi();
            InitiateGrandTotal();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
            this.setVisible(false);
        bayar bayarForm = new bayar();
        bayarForm.setDetail(nomorFaktur);
        bayarForm.setLocationRelativeTo(null);
//        MyFunction.setNomorFaktur(nomorFaktur);
        bayarForm.setTitle("Total Transaksi");
        bayarForm.setVisible(true);
        }
    }//GEN-LAST:event_bayarActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        // TODO add your handling code here:
        int selectedData = DataBarang.getSelectedRow();
        String field_qty = qty_txt.getText();
        String field_price = DataBarang.getValueAt(selectedData, 3).toString();
        int qty = Integer.parseInt(field_qty);
        int price = Integer.parseInt(field_price); 
        int total = qty * price;
        if (selectedData > -1) {
            try {
            String sql = "INSERT INTO `transaction_detail`(`transaction_code`, `item_code`, `total`, `qty`) "
                    + "VALUES (\n"
                    + "'"+noFaktur.getText()+"','"+DataBarang.getValueAt(selectedData, 0)+"','"+total+"','"+qty+"');";
            java.sql.Connection conn = (Connection) Config.MyConfig();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            InitiateDetailTransaksi();
            InitiateGrandTotal();
        } catch (SQLException e) {
           MyFunction.showErrorMessage(e.getMessage(), "Error!");
        }
        }
    }//GEN-LAST:event_tambahActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        int dialog = MyFunction.confirmValue("Apakah anda yakin ingin logout?", "Anda Yakin?");
//        System.out.println(dialog);
        if (dialog == 0) {
            this.setVisible(false);
            Login Login = new Login();
            Login.setVisible(true);
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    private void DataBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataBarangMouseClicked
        // TODO add your handling code here:
        int selectedRow = DataBarang.getSelectedRow();
        if (selectedRow > -1) {
            kodeBarang.setEditable(false);
            kodeBarang.setText(DataBarangModel.getValueAt(selectedRow, 0).toString());
        }
    }//GEN-LAST:event_DataBarangMouseClicked

    private void cariBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariBarangKeyReleased
        // TODO add your handling code here:
        InitiateDetailTransaksi();
        int row = DataBarang.getRowCount();
        for (int i = 0; i < row; i++) {
            DataBarangModel.removeRow(0);
        }
        try {

            String sql = "SELECT items.code,items.name,items.buy_price,items.sell_price,suppliers.name from suppliers JOIN items on suppliers.id = items.supplier_id\n"
                    + "WHERE "
                    + //                    + "suppliers.name LIKE '%"+cariBarang.getText()+"%' OR\n" +
                    "items.name LIKE '%" + cariBarang.getText() + "%'; ";
//                         "items.sell_price LIKE '%"+cariBarang.getText()+"%' OR\n" +
//                         "items.buy_price LIKE '%"+cariBarang.getText()+"%';\n";
            Connection cn = (Connection) Config.MyConfig();
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String[] data = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                DataBarangModel.addRow(data);
            }
            cn.close();

        } catch (SQLException e) {
            MyFunction.showErrorMessage(e.getMessage(), "Error!");
        }
    }//GEN-LAST:event_cariBarangKeyReleased

    private void cariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariBarangActionPerformed

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
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Kasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Kasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DataBarang;
    private javax.swing.JTable DetailTransaksi;
    private javax.swing.JButton bayar;
    private javax.swing.JTextField cariBarang;
    private javax.swing.JLabel grandTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField kodeBarang;
    private javax.swing.JLabel noFaktur;
    private javax.swing.JTextField qty_txt;
    private javax.swing.JButton tambah;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
