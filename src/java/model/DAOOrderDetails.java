/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.OrderDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DAOOrderDetails extends DBConnection{
    public int addOrderDetails(OrderDetails od) {
        int n = 0;
        String sql = """
                     INSERT INTO [dbo].[Order Details]
                                ([OrderID]
                                ,[ProductID]
                                ,[UnitPrice]
                                ,[Quantity]
                                ,[Discount])
                          VALUES
                                (?
                                ,?
                                ,?
                                ,?
                                ,?)""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, od.getOrderID());
            pre.setInt(2, od.getProductID());
            pre.setDouble(3, od.getUnitPrice());
            pre.setInt(4, od.getQuantity());
            pre.setDouble(5, od.getDiscount());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateOrderDetails(OrderDetails od) {
        int n = 0;
        String sql = """
                     UPDATE [dbo].[Order Details]
                        SET [OrderID] = ?
                           ,[ProductID] = ?
                           ,[UnitPrice] = ?
                           ,[Quantity] = ?
                           ,[Discount] = ?
                      WHERE [OrderID] = ?""";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setInt(1, od.getOrderID());
            pre.setInt(2, od.getProductID());
            pre.setDouble(3, od.getUnitPrice());
            pre.setInt(4, od.getQuantity());
            pre.setDouble(5, od.getDiscount());
            pre.setInt(6, 10261);
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }

    
    public int deleteOrderDetails(int OrderID) {
        int n = 0;
        //case 1: delete from foreign key --> primary key
        //case 2: select foreign key --> is exist --> disable primary key
        String sql = "DELETE FROM [dbo].[OrderDetails]\n" +
"      WHERE [OrderID] = " + OrderID;
        try {
            Statement state = conn.createStatement();           
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Vector<OrderDetails> getOrderDetails(String sql) {
        Vector<OrderDetails> vector = new Vector<OrderDetails>();
        try {
            //note: in case login must be used PreparedStatement để tránh tấn công SQL Injection
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            //TYPE_SCROLL_SENSITIVE (Có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            //TYPE_SCROLL_INSENSITIVE (Không có thread-safe): Con trỏ chuột chạy theo 2 hướng được(Scroll up và scroll down)
            ResultSet rs = state.executeQuery(sql);
            //rs.previous()
            while(rs.next()) {
                int OrderID = rs.getInt("OrderID");
                int ProductID = rs.getInt("ProductID");
                double UnitPrice = rs.getDouble("UnitPrice");
                int Quantity = rs.getInt("Quantity");
                double Discount = rs.getDouble("Discount");
                int OrderDetailStatus = rs.getInt("OrderDetailStatus");
                
                //OrderDetails od = new OrderDetails(OrderID, ProductID, UnitPrice, Quantity, Discount, OrderDetailStatus);
                vector.add(new OrderDetails(OrderID, ProductID, UnitPrice, Quantity, Discount, OrderDetailStatus));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
    public static void main(String[] args) {
        DAOOrderDetails od = new DAOOrderDetails();
//        int n = p.insertProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Inserted");
//        }
        
//        int n = p.updateProduct(new Product("PName",1,1,"QuantityPerUnit",100.1,10,11,1,true));
//        if (n>0) {
//            System.out.println("Updated");
//        }
        
        Vector<OrderDetails> vector = od.getOrderDetails("SELECT * FROM OrderDetails");
        for (OrderDetails o : vector) {
            System.out.println(o);
        }
    }
}
