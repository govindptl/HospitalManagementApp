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
import sca.pojo.PatientPojo;
import sca.pojo.ViewPojo;

/**
 *
 * @author HP
 */
public class PatientDao {
    public static ArrayList<PatientPojo> getAllPatient() throws SQLException
    {
      Statement st=DBConnection.getConnection().createStatement();
      ArrayList<PatientPojo> pList=new ArrayList<>();
      ResultSet rs= st.executeQuery("Select *from patient");
      
      while(rs.next())
      {
          PatientPojo p=new PatientPojo();
          p.setPid(rs.getString(1));
          p.setFname(rs.getString(2));
          p.setSname(rs.getString(3));
          p.setAge(rs.getInt(4));
          p.setOpd(rs.getString(5));
          p.setGender(rs.getString(6));
          p.setMstatus(rs.getString(7));
          p.setPdate(rs.getDate(8));
          p.setAddress(rs.getString(9));
          p.setCity(rs.getString(10));
          p.setPhoneno(rs.getString(11));
          p.setDocid(rs.getString(12));
          pList.add(p);
      }
     return pList; 
    }
    public static boolean addPatient(PatientPojo p) throws SQLException
    {
        PreparedStatement ps= DBConnection.getConnection().prepareStatement("Insert into patient values(?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, p.getPid());
        ps.setString(2,p.getFname());
        ps.setString(3,p.getSname());
        ps.setInt(4, p.getAge());
        ps.setString(5, p.getOpd());
        ps.setString(6, p.getGender());
        ps.setString(7, p.getMstatus());
        ps.setDate(8, p.getPdate());
        ps.setString(9, p.getAddress());
        ps.setString(10, p.getCity());
        ps.setString(11,p.getPhoneno());
        ps.setString(12, p.getDocid());
        int x= ps.executeUpdate();
        return x==1;
    }
    
    public static String getNewId() throws SQLException 
    {
      Statement st=DBConnection.getConnection().createStatement();
      ResultSet rs= st.executeQuery("select max(p_id) from patient");
      int id= 1;
      //String pid="P101";
      if( rs.next() )
      { 
        String pid= rs.getString(1);
        System.out.println(pid);
        if(pid == null)
            return "P101";
        int pno = Integer.parseInt(pid.substring(1));
        id= id + pno;
        String sr= "P" + id;
        System.out.println(sr);
        return sr;
      }
      return "P101";
    }
    
   public static ArrayList<ViewPojo> getPatientInfo() throws SQLException
   {
      Statement st=DBConnection.getConnection().createStatement();
      ArrayList<ViewPojo> pList= new ArrayList<>();
      ResultSet rs= st.executeQuery("Select p_id,f_name,s_name,opd from patient");
      
      while(rs.next())
      {
          ViewPojo vp=new ViewPojo();
          vp.setPid(rs.getString(1));
          vp.setPname(rs.getString(2)+" "+rs.getString(3));;
          vp.setOpd(rs.getString(4));
          pList.add(vp);  
      }
      return pList;
   }
   public static ArrayList<String> getPId() throws SQLException
    {
         Statement st= DBConnection.getConnection().createStatement();
         ResultSet rs=st.executeQuery("select p_id from patient");
         ArrayList<String> id=new ArrayList<>();
         while(rs.next())
         {
             id.add(rs.getString(1)); // col_pos
         }
         return id;
    }
   public static boolean removePatient(String id) throws SQLException
   {
        Statement st= DBConnection.getConnection().createStatement();
        int x=st.executeUpdate("delete patient where p_id='"+id+"'");
        return x==1;
   }
   
   public static PatientPojo getPatientDetails(String pid) throws SQLException
    {
      Statement st=DBConnection.getConnection().createStatement();
      ResultSet rs= st.executeQuery("Select *from patient where p_id='"+pid+"'");
      PatientPojo p=new PatientPojo();
      if(rs.next())
      {
          p.setPid(rs.getString(1));
          p.setFname(rs.getString(2));
          p.setSname(rs.getString(3));
          p.setAge(rs.getInt(4));
          p.setOpd(rs.getString(5));
          p.setGender(rs.getString(6));
          p.setMstatus(rs.getString(7));
          p.setPdate(rs.getDate(8));
          p.setAddress(rs.getString(9));
          p.setCity(rs.getString(10));
          p.setPhoneno(rs.getString(11));
          p.setDocid(rs.getString(12));
      }
     return p; 
    }
   public static boolean updatePatientInfo(PatientPojo p) throws SQLException
   {
       String qry="Update patient set p_id=?,f_name=?,s_name=?,age=?, opd=?,gender=?,m_status=?,p_date=?,address=?,city=?,phone_no=?,doctor_id=? where p_id=?";
      PreparedStatement ps=DBConnection.getConnection().prepareStatement(qry);
        ps.setString(1, p.getPid());
        ps.setString(2,p.getFname());
        ps.setString(3,p.getSname());
        ps.setInt(4, p.getAge());
        ps.setString(5, p.getOpd());
        ps.setString(6, p.getGender());
        ps.setString(7, p.getMstatus());
        ps.setDate(8, p.getPdate());
        ps.setString(9, p.getAddress());
        ps.setString(10, p.getCity());
        ps.setString(11,p.getPhoneno());
        ps.setString(12, p.getDocid());
       
        ps.setString(13, p.getPid());
        
        return ps.executeUpdate()==1;
   }
}

