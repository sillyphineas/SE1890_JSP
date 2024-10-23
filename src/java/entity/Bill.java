/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author HP
 */
public class Bill {
    private int OrderID;
    private String OrderDate;
    private String RequiredDate;
    private String ContactName;
    private String LastName;
    private int ProductID;
    private String ProductName;
    private double UnitPrice;
    private int Quantity;
    private double Discount;

    public Bill(int OrderID, String OrderDate, String RequiredDate, String ContactName, String LastName, int ProductID, String ProductName, double UnitPrice, int Quantity, double Discount) {
        this.OrderID = OrderID;
        this.OrderDate = OrderDate;
        this.RequiredDate = RequiredDate;
        this.ContactName = ContactName;
        this.LastName = LastName;
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.UnitPrice = UnitPrice;
        this.Quantity = Quantity;
        this.Discount = Discount;
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(String OrderDate) {
        this.OrderDate = OrderDate;
    }

    public String getRequiredDate() {
        return RequiredDate;
    }

    public void setRequiredDate(String RequiredDate) {
        this.RequiredDate = RequiredDate;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String ContactName) {
        this.ContactName = ContactName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public double getDiscount() {
        return Discount;
    }

    public void setDiscount(double Discount) {
        this.Discount = Discount;
    }
    
    public double getTotal() {
        return this.UnitPrice * this.Quantity;
    }
    
}
