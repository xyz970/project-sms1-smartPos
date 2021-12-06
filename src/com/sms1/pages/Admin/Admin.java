/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms1.pages.Admin;

import com.sms1.components.userPanel;
import com.formdev.flatlaf.FlatLightLaf;
import com.sms1.components.Login;
import com.sms1.logic.Config;
import com.sms1.logic.MyFunction;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author adi_sptro
 */
public class Admin extends javax.swing.JFrame {

    Date tanggal = new Date();
    DefaultTableModel DataSupplierModel;
    DefaultTableModel DataBarangModel;
    DefaultTableModel DataTransaksiModel;
    DefaultTableModel DataDetailTransaksiModel;

    /**
     * Creates new form Admin
     */
    public Admin() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {

        }

        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        InitiateBarang();
        InitiateSupplier();
        InitiateTransaksi();
        InitiateDetailTransaksi();
        initiateDropdownSupplier();
        FlatLightLaf.install();
    }

    public void setUsername(String name) {
        username.setText(name);
        user.setText(name);
    }

    final void initiateDropdownSupplier() {
        try {
            String sql = "select name from suppliers";
            Connection cn = (Connection) Config.MyConfig();
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
//                supplier_txt.
                supplier_txt.addItem(rs.getString("name"));
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

    //Inisiasi table supplier
    final void InitiateSupplier() {
        String[] judul = {"Kode Supplier", "Nama Supplier", "Alamat", "Kontak Supplier"};
        DataSupplierModel = new DefaultTableModel(judul, 0);
        DataSupplier.setSelectionMode(0);
        DataSupplier.setModel(DataSupplierModel);
        int row = DataSupplier.getRowCount();
        for (int i = 0; i < row; i++) {
            DataSupplierModel.removeRow(0);
        }
        try {
            String sql = "select * from suppliers";
            Connection cn = (Connection) Config.MyConfig();
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String[] data = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
                DataSupplierModel.addRow(data);
            }
        } catch (SQLException e) {
            MyFunction.showErrorMessage(e.getMessage(), "Error!");
        }
    }

    //Inisiasi Table transaksi
    final void InitiateTransaksi() {
        String[] judul = {"No Faktur", "Tanggal", "Waktu", "Total"};
        DataTransaksiModel = new DefaultTableModel(judul, 0);
        DataTransaksi.setSelectionMode(0);
        DataTransaksi.setModel(DataTransaksiModel);
        int row = DataTransaksi.getRowCount();
        for (int i = 0; i < row; i++) {
            DataTransaksiModel.removeRow(0);
        }
        try {
            String sql = "SELECT * FROM `transactions` WHERE NOT total = 0;";
            Connection cn = (Connection) Config.MyConfig();
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String[] data = {rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5)};
                DataTransaksiModel.addRow(data);
            }
        } catch (SQLException e) {
            MyFunction.showErrorMessage(e.getMessage(), "Error!");
        }
    }

    //Inisiasi table detail transaksi
    final void InitiateDetailTransaksi() {
        int selectedData = DataTransaksi.getSelectedRow();
        String[] judul = {"Kode Transaksi", "Nama Barang", "Total", "Kuantitas"};
        DataDetailTransaksiModel = new DefaultTableModel(judul, 0);
        DataDetailTransaksi.setSelectionMode(0);
        DataDetailTransaksi.setModel(DataDetailTransaksiModel);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        Tab = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DataBarang = new javax.swing.JTable();
        cariBarang = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        tambahBarang = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        hapusBarang = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        kode_txt = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        nametxt = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        selltxt = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        buytxt = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        supplier_txt = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        updateBarang = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        DataTransaksi = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        DataDetailTransaksi = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        DataSupplier = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        ubahSupplier = new javax.swing.JButton();
        kontak = new javax.swing.JTextField();
        kodeSupplier_txt = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        hapusSupplier = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        tambahSupplier = new javax.swing.JButton();
        clearSupplier = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        alamattxt = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        nama_suppliertxt = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        user_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        exportBarang = new javax.swing.JMenuItem();
        exportTransaksi = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        user = new javax.swing.JMenu();
        logout = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jMenuItem5.setText("jMenuItem5");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(48, 8, 136));
        setSize(new java.awt.Dimension(1366, 768));

        Tab.setBackground(new java.awt.Color(48, 8, 136));
        Tab.setForeground(new java.awt.Color(255, 255, 255));
        Tab.setFocusable(false);

        jPanel2.setBackground(new java.awt.Color(62, 0, 174));

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
        ));
        DataBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(DataBarang);
        if (DataBarang.getColumnModel().getColumnCount() > 0) {
            DataBarang.getColumnModel().getColumn(0).setResizable(false);
            DataBarang.getColumnModel().getColumn(1).setResizable(false);
            DataBarang.getColumnModel().getColumn(2).setResizable(false);
            DataBarang.getColumnModel().getColumn(3).setResizable(false);
            DataBarang.getColumnModel().getColumn(4).setResizable(false);
        }

        cariBarang.setBackground(new java.awt.Color(62, 0, 174));
        cariBarang.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        cariBarang.setForeground(new java.awt.Color(255, 255, 255));
        cariBarang.setText("Cari..");
        cariBarang.setToolTipText("Cari Barang");
        cariBarang.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 0, 174), 5, true));
        cariBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cariBarangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariBarangKeyReleased(evt);
            }
        });

        jTextField3.setBorder(null);
        jTextField3.setEnabled(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cari Barang");

        jPanel6.setBackground(new java.awt.Color(62, 0, 174));

        tambahBarang.setBackground(new java.awt.Color(61, 21, 227));
        tambahBarang.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        tambahBarang.setForeground(new java.awt.Color(255, 255, 255));
        tambahBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/add.png"))); // NOI18N
        tambahBarang.setText(" Tambah");
        tambahBarang.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(61, 21, 227), 4, true));
        tambahBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahBarangActionPerformed(evt);
            }
        });

        clear.setBackground(new java.awt.Color(61, 21, 227));
        clear.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        clear.setForeground(new java.awt.Color(255, 255, 255));
        clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/clear.png"))); // NOI18N
        clear.setText("Clear");
        clear.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(61, 21, 227), 4, true));
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });

        hapusBarang.setBackground(new java.awt.Color(61, 21, 227));
        hapusBarang.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        hapusBarang.setForeground(new java.awt.Color(255, 255, 255));
        hapusBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/hapus.png"))); // NOI18N
        hapusBarang.setText("Hapus");
        hapusBarang.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(61, 21, 227), 4, true));
        hapusBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBarangActionPerformed(evt);
            }
        });

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Kode Barang");

        kode_txt.setBackground(new java.awt.Color(62, 0, 174));
        kode_txt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        kode_txt.setForeground(new java.awt.Color(255, 255, 255));
        kode_txt.setToolTipText("Kode Barang");
        kode_txt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 0, 174), 5, true));

        jTextField12.setBorder(null);
        jTextField12.setEnabled(false);

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Nama Barang");

        nametxt.setBackground(new java.awt.Color(62, 0, 174));
        nametxt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        nametxt.setForeground(new java.awt.Color(255, 255, 255));
        nametxt.setToolTipText("Nama Barang");
        nametxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 0, 174), 5, true));

        jTextField13.setBorder(null);
        jTextField13.setEnabled(false);

        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Harga Jual");

        selltxt.setBackground(new java.awt.Color(62, 0, 174));
        selltxt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        selltxt.setForeground(new java.awt.Color(255, 255, 255));
        selltxt.setToolTipText("Harga Jual");
        selltxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 0, 174), 5, true));

        jTextField14.setBorder(null);
        jTextField14.setEnabled(false);

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Harga Beli");
        jLabel16.setToolTipText("");

        buytxt.setBackground(new java.awt.Color(62, 0, 174));
        buytxt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        buytxt.setForeground(new java.awt.Color(255, 255, 255));
        buytxt.setToolTipText("Harga Beli");
        buytxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 0, 174), 5, true));

        jTextField15.setBorder(null);
        jTextField15.setEnabled(false);

        supplier_txt.setBackground(new java.awt.Color(62, 0, 174));
        supplier_txt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        supplier_txt.setForeground(new java.awt.Color(255, 255, 255));

        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Supplier ID");
        jLabel17.setToolTipText("Supplier ID");

        updateBarang.setBackground(new java.awt.Color(61, 21, 227));
        updateBarang.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        updateBarang.setForeground(new java.awt.Color(255, 255, 255));
        updateBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/update.png"))); // NOI18N
        updateBarang.setText("Ubah");
        updateBarang.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(61, 21, 227), 4, true));
        updateBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBarangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(90, 90, 90)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField12)
                            .addComponent(kode_txt)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(86, 86, 86)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField13)
                            .addComponent(nametxt)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15))
                        .addGap(104, 104, 104)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(selltxt)
                            .addComponent(supplier_txt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buytxt)
                            .addComponent(jTextField14)
                            .addComponent(jTextField15)))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(tambahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(updateBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kode_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buytxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplier_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambahBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clear, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapusBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(196, Short.MAX_VALUE))
        );

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/search.png"))); // NOI18N

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/search.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel18)
                                .addGap(1, 1, 1)
                                .addComponent(cariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(609, 609, 609)
                    .addComponent(jLabel12)
                    .addContainerGap(610, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(310, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(465, 465, 465)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(465, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Tab.addTab("Barang", jPanel1);

        jPanel3.setBackground(new java.awt.Color(62, 0, 174));

        DataTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"xx.xxx.xxx", "xxx", "xxxx-xx-xx", "Rp. xxxxx"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No Faktur", "Kasir", "Tanggal", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DataTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataTransaksiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(DataTransaksi);
        if (DataTransaksi.getColumnModel().getColumnCount() > 0) {
            DataTransaksi.getColumnModel().getColumn(0).setResizable(false);
            DataTransaksi.getColumnModel().getColumn(1).setResizable(false);
            DataTransaksi.getColumnModel().getColumn(3).setResizable(false);
        }

        DataDetailTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(DataDetailTransaksi);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane4))
                .addContainerGap(408, Short.MAX_VALUE))
        );

        Tab.addTab("Transaksi", jPanel3);

        jPanel4.setBackground(new java.awt.Color(62, 0, 174));

        DataSupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        ));
        DataSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DataSupplierMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(DataSupplier);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 658, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(267, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(62, 0, 174));

        ubahSupplier.setBackground(new java.awt.Color(61, 21, 227));
        ubahSupplier.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        ubahSupplier.setForeground(new java.awt.Color(255, 255, 255));
        ubahSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/update.png"))); // NOI18N
        ubahSupplier.setText("Ubah");
        ubahSupplier.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(61, 21, 227), 4, true));
        ubahSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ubahSupplierActionPerformed(evt);
            }
        });

        kontak.setBackground(new java.awt.Color(62, 0, 174));
        kontak.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        kontak.setForeground(new java.awt.Color(255, 255, 255));
        kontak.setToolTipText("Kontak Supplier");
        kontak.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 0, 174), 5, true));

        kodeSupplier_txt.setBackground(new java.awt.Color(62, 0, 174));
        kodeSupplier_txt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        kodeSupplier_txt.setForeground(new java.awt.Color(255, 255, 255));
        kodeSupplier_txt.setToolTipText("Kode Supplier");
        kodeSupplier_txt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 0, 174), 5, true));

        jTextField10.setBorder(null);
        jTextField10.setEnabled(false);

        hapusSupplier.setBackground(new java.awt.Color(61, 21, 227));
        hapusSupplier.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        hapusSupplier.setForeground(new java.awt.Color(255, 255, 255));
        hapusSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/hapus.png"))); // NOI18N
        hapusSupplier.setText("Hapus");
        hapusSupplier.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(61, 21, 227), 4, true));
        hapusSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusSupplierActionPerformed(evt);
            }
        });

        jTextField11.setBorder(null);
        jTextField11.setEnabled(false);

        tambahSupplier.setBackground(new java.awt.Color(61, 21, 227));
        tambahSupplier.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        tambahSupplier.setForeground(new java.awt.Color(255, 255, 255));
        tambahSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/add.png"))); // NOI18N
        tambahSupplier.setText("Tambah");
        tambahSupplier.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(61, 21, 227), 4, true));
        tambahSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahSupplierActionPerformed(evt);
            }
        });

        clearSupplier.setBackground(new java.awt.Color(61, 21, 227));
        clearSupplier.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        clearSupplier.setForeground(new java.awt.Color(255, 255, 255));
        clearSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/clear.png"))); // NOI18N
        clearSupplier.setText("Clear");
        clearSupplier.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(61, 21, 227), 4, true));
        clearSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearSupplierActionPerformed(evt);
            }
        });

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Alamat");

        alamattxt.setBackground(new java.awt.Color(62, 0, 174));
        alamattxt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        alamattxt.setForeground(new java.awt.Color(255, 255, 255));
        alamattxt.setToolTipText("Alamat Supplier");
        alamattxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 0, 174), 5, true));

        jTextField8.setBorder(null);
        jTextField8.setEnabled(false);

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Kontak");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Kode Supplier");

        jTextField9.setBorder(null);
        jTextField9.setEnabled(false);

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nama Supplier");

        nama_suppliertxt.setBackground(new java.awt.Color(62, 0, 174));
        nama_suppliertxt.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        nama_suppliertxt.setForeground(new java.awt.Color(255, 255, 255));
        nama_suppliertxt.setToolTipText("Nama Supplier");
        nama_suppliertxt.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(62, 0, 174), 5, true));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(51, 51, 51)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField11)
                            .addComponent(jTextField8)
                            .addComponent(jTextField9)
                            .addComponent(kontak, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(alamattxt, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(nama_suppliertxt, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(kodeSupplier_txt, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(1, 1, 1)))
                        .addContainerGap())
                    .addComponent(jTextField10)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(tambahSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(clearSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ubahSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(hapusSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kodeSupplier_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nama_suppliertxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alamattxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kontak, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ubahSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tambahSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hapusSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(127, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Tab.addTab("Supplier", jPanel4);

        jPanel8.setBackground(new java.awt.Color(62, 0, 174));

        jPanel10.setBackground(new java.awt.Color(62, 0, 174));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 447, Short.MAX_VALUE)
        );

        jPanel11.setBackground(new java.awt.Color(62, 0, 174));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(477, Short.MAX_VALUE))
        );

        Tab.addTab("User Setting", jPanel8);

        user_panel.setBackground(new java.awt.Color(48, 8, 136));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/man-head.png"))); // NOI18N

        username.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        username.setForeground(new java.awt.Color(255, 255, 255));
        username.setText("Admin");

        javax.swing.GroupLayout user_panelLayout = new javax.swing.GroupLayout(user_panel);
        user_panel.setLayout(user_panelLayout);
        user_panelLayout.setHorizontalGroup(
            user_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(21, 21, 21))
        );
        user_panelLayout.setVerticalGroup(
            user_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, user_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(username)
                .addGap(39, 39, 39))
        );

        jMenu1.setText("File");

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/document.png"))); // NOI18N
        jMenu3.setText("Export");

        exportBarang.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        exportBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/barang.png"))); // NOI18N
        exportBarang.setText("Data Barang");
        exportBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportBarangActionPerformed(evt);
            }
        });
        jMenu3.add(exportBarang);

        exportTransaksi.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        exportTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/transaksi.png"))); // NOI18N
        exportTransaksi.setText("Data Transaksi");
        exportTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportTransaksiActionPerformed(evt);
            }
        });
        jMenu3.add(exportTransaksi);

        jMenu1.add(jMenu3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Profile");

        user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/user hitam.png"))); // NOI18N

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/sms1/assets/images/sign-out-16px.png"))); // NOI18N
        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        user.add(logout);

        jMenu2.add(user);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Settings");

        jMenuItem1.setText("Admin");
        jMenu4.add(jMenuItem1);

        jMenuItem2.setText("Kasir");
        jMenu4.add(jMenuItem2);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tab)
            .addComponent(user_panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(user_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tab))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    final void clearInputSupplier() {
        kodeSupplier_txt.setText("");
        nama_suppliertxt.setText("");
        alamattxt.setText("");
        kontak.setText("");
    }

    final void clearInputBarang() {
//        supplier_txt.setEditable(true);
        kode_txt.setEditable(true);
        kode_txt.setText("");
        nametxt.setText("");
        selltxt.setText("");
        buytxt.setText("");
        supplier_txt.setEnabled(true);
        supplier_txt.setSelectedItem(" ");
    }

    private void cariBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariBarangKeyPressed
        // TODO add your handling code here:
//        System.out.println(evt.getKeyChar());


    }//GEN-LAST:event_cariBarangKeyPressed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        int dialog = MyFunction.confirmValue("Apakah anda yakin ingin logout?", "Anda Yakin?");
        System.out.println(dialog);
        if (dialog == 0) {
            this.setVisible(false);
            Login Login = new Login();
            Login.setVisible(true);
        }
    }//GEN-LAST:event_logoutActionPerformed


    private void DataSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataSupplierMouseClicked
        // TODO add your handling code here:
        int i = DataSupplier.getSelectedRow();

        if (i > -1) {
            kodeSupplier_txt.setText(DataSupplierModel.getValueAt(i, 0).toString());
            kodeSupplier_txt.setEditable(false);
            nama_suppliertxt.setText(DataSupplierModel.getValueAt(i, 1).toString());
            alamattxt.setText(DataSupplierModel.getValueAt(i, 2).toString());
            kontak.setText(DataSupplierModel.getValueAt(i, 3).toString());
        }
        kode_txt.setEditable(true);

    }//GEN-LAST:event_DataSupplierMouseClicked

    private void DataTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataTransaksiMouseClicked
        // TODO add your handling code here:
        int selectedData = DataTransaksi.getSelectedRow();
//        System.out.println(DataTransaksiModel.getValueAt(selectedData, 0));
        int row = DataDetailTransaksi.getRowCount();
        for (int i = 0; i < row; i++) {
            DataDetailTransaksiModel.removeRow(0);
        }
        try {
            String sql = "SELECT transactions.code, items.name, transaction_detail.total,qty \n"
                    + "FROM transaction_detail\n"
                    + "JOIN transactions\n"
                    + "ON transactions.code = transaction_detail.transaction_code\n"
                    + "JOIN items\n"
                    + "ON items.code = transaction_detail.item_code\n"
                    + "WHERE transaction_code = '" + DataTransaksiModel.getValueAt(selectedData, 0) + "'";
            Connection cn = (Connection) Config.MyConfig();
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String[] data = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),};
                DataDetailTransaksiModel.addRow(data);
            }
        } catch (SQLException e) {
            MyFunction.showErrorMessage(e.getMessage(), "Error!");
        }
    }//GEN-LAST:event_DataTransaksiMouseClicked

    private void cariBarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariBarangKeyReleased
        // TODO add your handling code here:
        String[] judul = {"Kode Barang", "Nama Barang", "Harga Beli", "Harga Jual", "Supplier"};
        DataBarangModel = new DefaultTableModel(judul, 0);
        DataBarang.setSelectionMode(0);
        DataBarang.setModel(DataBarangModel);
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

    private void exportBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBarangActionPerformed
        // TODO add your handling code here:
        setCursor(Cursor.WAIT_CURSOR);
        MyFunction.ExportBarang();
        setCursor(Cursor.DEFAULT_CURSOR);
        MyFunction.showMessage("Data berhasil di export\nData di simpan di direktori " + MyFunction.ExportBarang(), "Export Berhasil");
    }//GEN-LAST:event_exportBarangActionPerformed

    private void exportTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportTransaksiActionPerformed
        // TODO add your handling code here:
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ExportTransaksi ExDetail = new ExportTransaksi();
//        ExDetail.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        ExDetail.pack();
        ExDetail.setLocationRelativeTo(null);
        ExDetail.setVisible(true);
    }//GEN-LAST:event_exportTransaksiActionPerformed

    private void tambahBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahBarangActionPerformed
        // TODO add your handling code here:
        try {
            String sql1 = "select id from suppliers where name = '"+supplier_txt.getSelectedItem()+"';";
            Connection cn = (Connection) Config.MyConfig();
            PreparedStatement st1 = cn.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            rs.first();
            String supplier_id = rs.getString("id");
            
            String sql = "insert into items (code, name, sell_price, buy_price, supplier_id) values ('" + kode_txt.getText() + "','" + nametxt.getText()
                    + "','" + buytxt.getText() + "','" + selltxt.getText() + "','" + supplier_id + "')";
            PreparedStatement st = cn.prepareStatement(sql);
            st.execute();
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan", "Berhasil", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            MyFunction.showErrorMessage(e.getMessage(), "Error!");
        }
        clearInputBarang();
        InitiateBarang();
    }//GEN-LAST:event_tambahBarangActionPerformed

    private void updateBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBarangActionPerformed
        // TODO add your handling code here:

        if (kode_txt.getText().equals("")) {
            MyFunction.showErrorMessage("Harap memilih data yang akan dipilih..", "Error!");
        } else {
            int confirm = MyFunction.confirmValue("Apakah anda yakin ingin mengubah?", "Anda Yakin??");
            if (confirm == 0) {
                try {
                    String sql1 = "update items set name='" + nametxt.getText() + "',sell_price='" + selltxt.getText() + "',buy_price='" + buytxt.getText() + "' where code='" + kode_txt.getText() + "'";
                    Connection cn = (Connection) Config.MyConfig();
                    PreparedStatement pst = cn.prepareStatement(sql1);
                    pst.execute();
                    MyFunction.showMessage("Data Berhasil diubah", "Berhasil");
                } catch (SQLException e) {
                    MyFunction.showErrorMessage(e.getMessage(), "Error!");
                }
                InitiateBarang();
                clearInputBarang();
            }
        }
    }//GEN-LAST:event_updateBarangActionPerformed

    private void hapusBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBarangActionPerformed
        // TODO add your handling code here:
        int i = DataBarang.getSelectedRow();
        if (DataBarang.isRowSelected(i)) {
            int confirm = MyFunction.confirmValue("Apakah anda yakin ingin menghapus?", "Anda Yakin??");
            if (confirm == 0) {
                try {
                    String sql = "delete from items where code='" + DataBarangModel.getValueAt(i, 0) + "'";
                    Connection cn = (Connection) Config.MyConfig();
                    PreparedStatement pst = cn.prepareStatement(sql);
                    pst.execute();
                    InitiateBarang();
                } catch (SQLException e) {
                    MyFunction.showErrorMessage(e.getMessage(), "Error!");
                }
            }
        }
    }//GEN-LAST:event_hapusBarangActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        clearInputBarang();
    }//GEN-LAST:event_clearActionPerformed

    private void DataBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DataBarangMouseClicked
        // TODO add your handling code here:
        int selectedData = DataBarang.getSelectedRow();
        try {
            String sql = "SELECT id from suppliers where name = '" + DataBarangModel.getValueAt(selectedData, 4) + "'";
            Connection cn = (Connection) Config.MyConfig();
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
//            System.out.println(rs);
            while (rs.next()) {
                supplier_txt.getModel().setSelectedItem(rs.getString("id"));
            }

        } catch (SQLException e) {
            MyFunction.showErrorMessage(e.getMessage(), "Error!");
        }

        if (selectedData > -1) {
            kode_txt.setText(DataBarangModel.getValueAt(selectedData, 0).toString());
            kode_txt.setEditable(false);
            supplier_txt.setEnabled(false);
            supplier_txt.setSelectedItem(DataBarangModel.getValueAt(selectedData, 4).toString());
            nametxt.setText(DataBarangModel.getValueAt(selectedData, 1).toString());
            selltxt.setText(DataBarangModel.getValueAt(selectedData, 3).toString());
            buytxt.setText(DataBarangModel.getValueAt(selectedData, 2).toString());

            //            JOptionPane.showMessageDialog(null, getPrimaryData());
        }

    }//GEN-LAST:event_DataBarangMouseClicked

    private void ubahSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahSupplierActionPerformed
        //        UPDATE `suppliers` SET `id`=[value-1],`name`=[value-2],`address`=[value-3],`contact`=[value-4] WHERE 1
        if (kodeSupplier_txt.getText().equals("")) {
            MyFunction.showErrorMessage("Harap memilih data yang akan dipilih..", "Error!");
        } else {
            try {
                String sql = "UPDATE `suppliers` \n "
                        + " SET `name`='" + nama_suppliertxt.getText() + "', \n "
                        + " `address`='" + alamattxt.getText() + "', \n "
                        + " `contact`= '" + kontak.getText() + "' \n "
                        + " WHERE `id` = '" + kodeSupplier_txt.getText() + "'";
                Connection cn = (Connection) Config.MyConfig();
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(null, "Data berhasil Diubah", "Berhasil", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException e) {
                MyFunction.showErrorMessage(e.getMessage(), "Error!");
            }
            clearInputSupplier();
            InitiateSupplier();
            initiateDropdownSupplier();
        }

    }//GEN-LAST:event_ubahSupplierActionPerformed

    private void hapusSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusSupplierActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusSupplierActionPerformed

    private void tambahSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahSupplierActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "INSERT INTO `suppliers`(`id`, `name`, `address`, `contact`) VALUES ('" + kodeSupplier_txt.getText() + "','"
                    + nama_suppliertxt.getText() + "','"
                    + alamattxt.getText() + "','" + kontak.getText() + "')";
            java.sql.Connection conn = (Connection) Config.MyConfig();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            MyFunction.showMessage("Sukses", "Data Berhasil ditambahkan");
            clearInputSupplier();
            InitiateSupplier();
            initiateDropdownSupplier();
        } catch (SQLException e) {
            MyFunction.showErrorMessage(e.getMessage(), "Error!");
        }
    }//GEN-LAST:event_tambahSupplierActionPerformed

    private void clearSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearSupplierActionPerformed
        clearInputSupplier();
        kodeSupplier_txt.setEditable(true);
    }//GEN-LAST:event_clearSupplierActionPerformed

    public String getPrimaryData() {
        int selectedData = DataBarang.getSelectedRow();
        if (selectedData > -1) {
            String data = DataBarangModel.getValueAt(selectedData, 1).toString();
            return data;
        }
        return "null";
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
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DataBarang;
    private javax.swing.JTable DataDetailTransaksi;
    private javax.swing.JTable DataSupplier;
    private javax.swing.JTable DataTransaksi;
    private javax.swing.JTabbedPane Tab;
    private javax.swing.JTextField alamattxt;
    private javax.swing.JTextField buytxt;
    private javax.swing.JTextField cariBarang;
    private javax.swing.JButton clear;
    private javax.swing.JButton clearSupplier;
    private javax.swing.JMenuItem exportBarang;
    private javax.swing.JMenuItem exportTransaksi;
    private javax.swing.JButton hapusBarang;
    private javax.swing.JButton hapusSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField kodeSupplier_txt;
    private javax.swing.JTextField kode_txt;
    private javax.swing.JTextField kontak;
    private javax.swing.JMenuItem logout;
    private javax.swing.JTextField nama_suppliertxt;
    private javax.swing.JTextField nametxt;
    private javax.swing.JTextField selltxt;
    private javax.swing.JComboBox<String> supplier_txt;
    private javax.swing.JButton tambahBarang;
    private javax.swing.JButton tambahSupplier;
    private javax.swing.JButton ubahSupplier;
    private javax.swing.JButton updateBarang;
    private javax.swing.JMenu user;
    private javax.swing.JPanel user_panel;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
