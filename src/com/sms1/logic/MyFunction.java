/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sms1.logic;

import com.sms1.pages.Admin.Admin;
import com.sms1.pages.kasir.Kasir;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

/**
 * Class MyFunction <code>MyFunction()</code>
 *
 * @author adi_sptro
 */
public class MyFunction {

    public static void showMessage(String pesan, String judul) {
        JOptionPane.showMessageDialog(null, pesan, judul, JOptionPane.QUESTION_MESSAGE);
    }

    public static void showErrorMessage(String pesan, String judul) {
        JOptionPane.showMessageDialog(null, pesan, judul, JOptionPane.ERROR_MESSAGE);
    }

    public static int confirmValue(String pesan, String judul) {
//        JOptionPane.showConfirmDialog(parentComponent, pesan, judul, 0, 0)
        int dialog = JOptionPane.showConfirmDialog(null, pesan, judul, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        return dialog;
    }

    public static String setUserID(String username) {
        try {
            String id;
            String sql = "SELECT id FROM `users` WHERE username = '" + username + "';";
            java.sql.Connection con = (Connection) Config.MyConfig();
            java.sql.PreparedStatement pst = con.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getString(1);
                return id;
            } else {
                MyFunction.showErrorMessage("Mohon cek kembali username dan password anda", "Erorr!");
            }
        } catch (SQLException e) {
            MyFunction.showErrorMessage("Error!", e.getMessage());
        }
        return "null";
    }

    public static int random1Digit() {
        int min = 00;
        int max = 99;
        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return random;
    }

    public static int randomFaktur() {
        int min = 100;
        int max = 999;
        int random = (int) Math.floor(Math.random() * (max - min + 1) + min);
        return random;
    }

    public static final String getOS() {
        String OS = System.getProperty("os.name");
        return OS;
    }

    public static String checkDirectory(String targetDir) {
        File dir = new File(targetDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return targetDir;
    }

    public static final String getHomeDir() {
        String dir;
//        dir = System.getProperty("user.home");
        dir = FileUtils.getUserDirectoryPath();
        return dir;
    }

    /**
     * Export Function (only for Barang) <code> ExportBarang()</code>
     *
     * @return path
     */
    public static final String ExportBarang() {
//        Logger.class;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Data Barang");
        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell;
        cell = row.createCell(1);
        cell.setCellValue("Kode Barang");
        cell = row.createCell(2);
        cell.setCellValue("Nama Barang");
        cell = row.createCell(3);
        cell.setCellValue("Harga Beli");
        cell = row.createCell(4);
        cell.setCellValue("Harga Jual");
        cell = row.createCell(5);
        cell.setCellValue("Nama Supplier");
        int i = 2;
        try {
            String sql = "SELECT items.code,items.name,items.buy_price,items.sell_price,suppliers.name from suppliers JOIN items on items.supplier_id = suppliers.id";
            Connection cn = (Connection) Config.MyConfig();
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(1);
                cell.setCellValue(rs.getString(1));
                cell = row.createCell(2);
                cell.setCellValue(rs.getString(2));
                cell = row.createCell(3);
                cell.setCellValue(rs.getString(3));
                cell = row.createCell(4);
                cell.setCellValue(rs.getString(4));
                cell = row.createCell(5);
                cell.setCellValue(rs.getString(5));
                i++;
            }
            checkDirectory(getHomeDir() + "/Smart Pos/Export/");
            String path = getHomeDir() + "/Smart Pos/Export/BarangExport.xlsx";
            FileOutputStream fileOut = new FileOutputStream(new File(path));
            workbook.write(fileOut);
            fileOut.close();
            return path;
        } catch (SQLException | FileNotFoundException e) {
            MyFunction.showErrorMessage(e.getMessage(), "Error!");
        } catch (IOException ex) {
            MyFunction.showErrorMessage(ex.getMessage(), "Error!");
        }
        return "";
    }

    /**
     * Export Function (only for Suppliers) <code> ExportSupplier()</code>
     *
     */
    public static final void ExportSupplier() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Data Supplier");

    }

    /**
     * Export Function (only for Transaksi) <code> ExportTransaksi()</code>
     *
     * @param date1
     * @param date2
     * @return path
     */
    public static final String ExportTransaksi(String date1, String date2) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFCellStyle cellstyle = workbook.createCellStyle();
        cellstyle.setDataFormat((short)8);
       
       XSSFSheet spreadsheet = workbook.createSheet("Data Transaksi(" + date1 + "-" + date2 + ")");
        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell;
        cell = row.createCell(1);
        cell.setCellValue("Nomor Faktur");
        cell = row.createCell(2);
        cell.setCellValue("Tanggal");
        cell = row.createCell(3);
        cell.setCellValue("Jam");
        cell = row.createCell(4);
        
        cell.setCellValue("total");
        int i = 2;
        try {
            String sql = "SELECT code,date,time,total FROM `transactions` \n"
                    + "WHERE date BETWEEN '" + date1 + "' AND '" + date2 + "' HAVING total != 0";
            Connection cn = (Connection) Config.MyConfig();
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                row = spreadsheet.createRow(i);
                cell = row.createCell(1);
                cell.setCellValue(rs.getString(1));
                cell = row.createCell(2);
                cell.setCellValue(rs.getString(2));
                cell = row.createCell(3);
                cell.setCellValue(rs.getString(3));
                cell = row.createCell(4);
                cell.setCellValue(rs.getString(4));
                i++;
//                Thread.sleep(100);
            }
            checkDirectory(getHomeDir() + "/Smart Pos/Export/Data Transaksi/");
            String path = getHomeDir() + "/Smart Pos/Export/Data Transaksi/Data Transaksi(" + date1 + "__" + date2 + ").xlsx";
            FileOutputStream fileOut = new FileOutputStream(new File(path));
            workbook.write(fileOut);
            fileOut.close();
            return path;
        } catch (SQLException | FileNotFoundException e) {
            MyFunction.showErrorMessage(e.getMessage(), "Error!");
        } catch (IOException ex) {
            MyFunction.showErrorMessage(ex.getMessage(), "Error!");
        }
        return "";
    }

    /**
     * Export Function (only for DetailTransaksi)
     * <code> ExportDetailTransaksi()</code>
     *
     * @param nomorFaktur
     */
    public static final void ExportDetailTransaksi(String nomorFaktur) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Data Detail Nomor " + nomorFaktur);
    }

}
