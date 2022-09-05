/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sca.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import sca.dbutil.DBConnection;
import sca.pojo.EmpPojo;
import sca.pojo.UserRegisterPojo;

/**
 *
 * @author HP
 */
public class ReceptionistDao {
    public static boolean addReceptionist(UserRegisterPojo u) throws SQLException
    {
        PreparedStatement ps=DBConnection.getConnection().prepareStatement("insert into users values(?,?,?,?,?,'Y')");
        ps.setString(1, u.getUserid());
        ps.setString(2, u.getUsername());
        ps.setString(3,u.getEmpid());
        ps.setString(4, u.getPassword());
        ps.setString(5,u.getUserType());
        int x= ps.executeUpdate();
        return x==1;
    }
    
    public static ArrayList<EmpPojo> getAllRecp() throws SQLException
    {
      Statement st=DBConnection.getConnection().createStatement();
      ArrayList<EmpPojo> emplist=new ArrayList<>();
      ResultSet rs= st.executeQuery("Select *from employees where Role='Receptionist' and Active='Y'");
      
      while(rs.next())
      {
          EmpPojo e=new EmpPojo();
          e.setEmpid(rs.getString(1));
          e.setEmpName(rs.getString(2));
          e.setJob(rs.getString(3));
          e.setSal(Double.parseDouble(rs.getString(4)));
          emplist.add(e);
      }
     return emplist; 
    }
    public static  ArrayList<String> getAllRecpId() throws SQLException
    {
      Statement st=DBConnection.getConnection().createStatement();
      ArrayList<String> emplist=new ArrayList<>();
      ResultSet rs= st.executeQuery("Select empid from users where userType='Receptionist' and Active= 'Y'");
      String Empid=null;
      while(rs.next())
      {
          Empid= rs.getString(1);
          emplist.add(Empid);
      }
     return emplist; 
    }
    
    public static boolean removeRecp(String eid) throws SQLException
    {
        Statement st= DBConnection.getConnection().createStatement();
        int result= st.executeUpdate("update users set active = 'N' where empid='"+eid+"'" );
        return result==1; 
    }

}
