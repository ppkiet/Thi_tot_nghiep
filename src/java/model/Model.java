/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Model {

    private Connection conn;
    private ResultSet rs;
    private PreparedStatement stmt;

    public ArrayList<Employee> listCity() {
        try {
            Connect a = new Connect();
            conn = a.getSQLServerConnection();
            String sql = "SELECT     ID, Name\n"
                    + "FROM         City";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            ArrayList<Employee> l = new ArrayList();
            while (rs.next()) {
                Employee us = new Employee();
                us.setId(rs.getString(1));
                us.setCity(rs.getString(2));
                l.add(us);
            }
            return l;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public ArrayList<Employee> listEmp() {
        try {
            Connect a = new Connect();
            conn = a.getSQLServerConnection();
            String sql = "SELECT     id, name, sal, city, phone, image\n"
                    + "FROM         Employee";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            ArrayList<Employee> l = new ArrayList();
            while (rs.next()) {
                Employee us = new Employee();
                us.setId(rs.getString(1));
                us.setName(rs.getString(2));
                us.setSalary(rs.getString(3));
                us.setCity(rs.getString(4));
                us.setPhone(rs.getString(5));
                us.setImage(rs.getString(6));
                l.add(us);
            }
            return l;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Employee getEmp(String id) {
        try {
            Connect a = new Connect();
            conn = a.getSQLServerConnection();
            String sql = "SELECT     id, name, sal, city, phone, image\n"
                    + "FROM         Employee WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                Employee us = new Employee();
                us.setId(rs.getString(1));
                us.setName(rs.getString(2));
                us.setSalary(rs.getString(3));
                us.setCity(rs.getString(4));
                us.setPhone(rs.getString(5));
                us.setImage(rs.getString(6));
                return us;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }



    public void insertEmp(String name, String salary, String city, String phone, String image) {
        try {
            Connect a = new Connect();
            conn = a.getSQLServerConnection();
            String sql = "Insert Employee values (?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, salary);
            stmt.setString(3, city);
            stmt.setString(4, phone);
            stmt.setString(5, image);
            stmt.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void updateEmp(String name, String city, String salary, String phone, String image, String id) {
        try {
            Connect a = new Connect();
            conn = a.getSQLServerConnection();
            String sql = "Update Employee set name=?, sal=?, city=?, phone=?, image=? Where ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, salary);
            stmt.setString(3, city);
            stmt.setString(4, phone);
            stmt.setString(5, image);
            stmt.setString(6, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteEmp(String id) {
        try {
            Connect a = new Connect();
            conn = a.getSQLServerConnection();
            String sql = "Delete from Employee Where ID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Model m = new Model();
        System.out.println(m.getEmp("5"));
    }
}
