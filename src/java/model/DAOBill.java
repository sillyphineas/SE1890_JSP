/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Bill;
import java.util.Vector;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author HP
 */
public class DAOBill extends DBConnection{
    public Vector<Bill> getBill(String sql) {
        Vector<Bill> vector = new Vector<Bill>();
        try {
            //note: in case login must be used PreparedStatement
            //  default ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            //  rs.previous()
            while (rs.next()) {
                int OrderID = rs.getInt(1);
                // int ProductID=rs.getInt("ProductID");
                String OrderDate = rs.getString(2);
                String RequiredDate = rs.getString(3);
                String ContactName = rs.getString(4);
                String LastName = rs.getString(5);
                int ProductID = rs.getInt(6);
                String ProductName = rs.getString(7);
                double UnitPrice = rs.getDouble(8);
                int Quantity = rs.getInt(9);
                double Discount = rs.getDouble(10);
                Bill b = new Bill(OrderID, OrderDate, RequiredDate, ContactName, LastName, ProductID, ProductName, UnitPrice, Quantity, Discount);
                vector.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOBill.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}
