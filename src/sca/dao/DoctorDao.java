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
import sca.dbutil.DBConnection;
import sca.pojo.UserDoctorPojo;
import sca.pojo.UserRegisterPojo;

/**
 *
 * @author HP
 */
public class DoctorDao {
    
    public static String getNewDocId()throws SQLException
    {
      Statement st=DBConnection.getConnection().createStatement();
      ResultSet rs= st.executeQuery("Select max(doctorid) from doctors");
      int id= 1;
      if(rs.next())
      {
          String completeid= rs.getString(1);
          int eno=Integer.parseInt(completeid.substring(3));
          id= id + eno;
      }
      String newDocId= "DOC"+id;
      System.out.println(newDocId);
      return newDocId;
    }
    public static boolean addDoctor(UserRegisterPojo u, UserDoctorPojo user ) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("insert into users values(?,?,?,?,?,'Y')");
        ps.setString(1, u.getUserid());
        ps.setString(2, u.getUsername());
        ps.setString(3,u.getEmpid());
        ps.setString(4, u.getPassword());
        ps.setString(5,u.getUserType());
        int x= ps.executeUpdate();
        //inserting into doctors table also
        ps = conn.prepareStatement("insert into doctors values(?,?,?,?,'Y')");
        ps.setString(1, user.getUserId());
        ps.setString(2,user.getDoctorId());
        ps.setString(3, user.getQualification());
        ps.setString(4, user.getSpecialist());
        int y= ps.executeUpdate();
        return x==1 && y==1;
    }
    public static ArrayList<UserDoctorPojo> getAllDoctor() throws SQLException
    {
      Statement st=DBConnection.getConnection().createStatement();
      ArrayList<UserDoctorPojo> docList=new ArrayList<>();
      ResultSet rs= st.executeQuery("Select *from doctors where Active='Y'");
      while(rs.next())
      {
          UserDoctorPojo e=new UserDoctorPojo();
          e.setUserId(rs.getString(1));
          e.setDoctorId(rs.getString(2));
          e.setQualification(rs.getString(3));
          e.setSpecialist(rs.getString(4));
          docList.add(e);
      }
     return docList; 
    }
   
    public static  ArrayList<String> getAllDocId() throws SQLException
    {
        ArrayList<String> docId =new ArrayList(); 
        Statement st= DBConnection.getConnection().createStatement();
        ResultSet rs= st.executeQuery("Select doctorid from doctors where active='Y'");
        while(rs.next())
        {
            docId.add(rs.getString(1));
        }
        return docId;
    }
    public static boolean removeDoc(String uid) throws SQLException
    {
        Statement st= DBConnection.getConnection().createStatement();
        int result= st.executeUpdate("update doctors set active= 'N' where userid='"+uid+"'");
        int result2= st.executeUpdate("update  users set active= 'N' where userid='"+uid+"'");
        return result==1 && result2==1; 
    }
}
