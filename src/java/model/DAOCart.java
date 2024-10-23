/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Cart;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DAOCart extends DBConnection{
    public Cart getCart(int pid) {
        Cart cart = null;
        String sql = """
                     select a.ProductID, a.ProductName, a.UnitPrice, b.Quantity, b.Discount
                     from Products as a join [dbo].[Order Details] as b on a.ProductID = b.ProductID
                     where b.ProductID = 
                     """ + pid;
        ResultSet rs;
        try {
            rs = conn.createStatement().executeQuery(sql);
            if (rs.next()) {
                cart = new Cart(rs.getInt(1), rs.getString(2), rs.getDouble(3), 0, 0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cart;
    }
    
    public static void main(String[] args) {
        DAOCart dao = new DAOCart();
        Cart cart = dao.getCart(1);
        System.out.println(cart.getProductID() + " " + cart.getProductName()+ " " + cart.getQuantity()+ " " + cart.getUnitPrice()+ " " + cart.getDiscount()+ " ");
    }
}
