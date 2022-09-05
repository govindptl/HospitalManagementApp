/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import sca.dbutil.DBConnection;
import sca.pojo.EmpPojo;


public class EmpDao {
    public static  String getNewid() throws SQLException
    {
      Statement st=DBConnection.getConnection().createStatement();
      ResultSet rs= st.executeQuery("Select max(empid) from employees");
      int id= 1;
      if(rs.next())
      {
          String empid = rs.getString(1);
          int eno = Integer.parseInt(empid.substring(1));
          id = id + eno;
      }
      String sr= "E"+id;
      System.out.println(sr);
      return sr;  
    }
    public static boolean addEmployee(EmpPojo e) throws SQLException
    {       
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into employees values(?,?,?,?,'Y')");
        ps.setString(1,e.getEmpid());
        ps.setString(2,e.getEmpName());
        ps.setString(3,e.getJob());
        ps.setDouble(4,e.getSal());
        int x= ps.executeUpdate();
        return x==1;
    }
    public static ArrayList<EmpPojo> getAllEmp() throws SQLException
    {
      Statement st = DBConnection.getConnection().createStatement();
      ArrayList<EmpPojo> emplist = new ArrayList<>();
      ResultSet rs = st.executeQuery("Select * from employees where Active='Y'");
      
      while(rs.next())
      {
          EmpPojo e=new EmpPojo();
          e.setEmpid(rs.getString(1));
          e.setEmpName(rs.getString(2));
          e.setJob(rs.getString(3));
          e.setSal(rs.getDouble(4));
          emplist.add(e);
      }
     return emplist; 
    }
    
    public static ArrayList<String> getEmpId() throws SQLException
    {
         Statement st= DBConnection.getConnection().createStatement();
         ResultSet rs=st.executeQuery("select empid from employees where Active ='Y'");
         ArrayList<String> empid=new ArrayList<>();
         while(rs.next())
         {
             empid.add(rs.getString(1)); // col_pos
         }
         return empid;
    }
    public static EmpPojo searchEmp(String id) throws SQLException
    {
        EmpPojo e=new EmpPojo();
        PreparedStatement ps= DBConnection.getConnection().prepareStatement("select * from employees where Empid=?");
        ps.setString(1,id);
        ResultSet rs=ps.executeQuery();
        if(rs.next())
        {
        e.setEmpName(rs.getString(2));
        e.setJob(rs.getString(3));
        e.setSal(Double.parseDouble(rs.getString(4)));
        }
        return e;
    }
    public static boolean updateEmp(EmpPojo e) throws SQLException
    {
        PreparedStatement ps= DBConnection.getConnection().prepareStatement("update employees set empname=?,role=?,sal=? where empid=? ");
        ps.setString(1,e.getEmpName());
        ps.setString(2,e.getJob());
        ps.setDouble(3,e.getSal());
        ps.setString(4,e.getEmpid());
        int x= ps.executeUpdate();
        return x==1; 
    }
   public static boolean removeEmp(String id) throws SQLException
    {
       String qry = "Update employees set Active='N' Where Empid='"+id+"'";
       String qry1 = "Update users set Active='N' Where Empid='"+id+"'";
       String qry2 = "Update doctors set Active='N' Where userid=(select users.userid from users,doctors where users.userid= doctors.userid and users.empid='"+id+"')";
       Statement st= DBConnection.getConnection().createStatement();
       int x= st.executeUpdate(qry);
       int y= st.executeUpdate(qry1);
       int z=st.executeUpdate(qry2);
       System.out.println(y==1);
        System.out.println(z==1);
       return x==1;
        
        /* String qry1="delete from users where empid=?";
        String qry2="delete from employees where empid=?";

        Connection con=DBConnection.getConnection();
        PreparedStatement ps= con.prepareStatement(qry1);
        ps.setString(1,id);
        int y=ps.executeUpdate();
        if(y==1)
        {
            JOptionPane.showMessageDialog(null, "Warning! Now This Employees is No longer to use the Software!", "Aleart",JOptionPane.WARNING_MESSAGE);
        }
        ps= con.prepareStatement(qry2);
        ps.setString(1,id);
        int z=ps.executeUpdate();
        return z==1;
        /*ps.setString(2,id);
        int x= ps.executeUpdate();
        if(x==1)
              return true;
        else
        {
           int ans=JOptionPane.showConfirmDialog(null, "Warning! This Employees has The right to Operate the Software! Would you like to continue?", "Warning!", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
           int y=0; 
           if(ans==JOptionPane.YES_OPTION)
            {
               String qry1="delete users where empid=?";
               ps= con.prepareStatement(qry1);
               ps.setString(1,id);
               ps.executeUpdate();
               String qry2="delete employees where empid=?";
               ps= con.prepareStatement(qry2);
               ps.setString(1,id);
               y= ps.executeUpdate();
            }
           return y==1;
            
        }*/
       
    }
  public static HashMap<String,String> getNonRegisteredReceptionist() throws SQLException
        {
            Connection conn=DBConnection.getConnection();
            String qry="select empid,empname from employees where role='Receptionist' and empid not in( select empid from users where usertype='Receptionist') and active='Y'  ";
            Statement st= conn.createStatement();
            ResultSet rs=st.executeQuery(qry);
            HashMap<String,String> receptionist=new HashMap<>();
            while (rs.next())
              {
                String id=rs.getString(1);
                String name=rs.getString(2);
                receptionist.put(id,name);
              }
            return receptionist;
        }
  
    public static HashMap<String,String> getNonRegisteredDoctor() throws SQLException
        {
            Connection conn=DBConnection.getConnection();
            String qry="select empid,empname from employees where role='Doctor' and Active='Y' and empid not in( select empid from users where usertype='Doctor' )";
            Statement st= conn.createStatement();
            ResultSet rs=st.executeQuery(qry);
            HashMap<String,String> doctorlist=new HashMap<>();
            while (rs.next())
              {
                String id=rs.getString(1);
                String name=rs.getString(2);
                doctorlist.put(id,name);
              }
            return doctorlist;
        }
  
}
