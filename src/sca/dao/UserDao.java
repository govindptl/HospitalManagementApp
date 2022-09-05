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
import java.util.HashMap;
import sca.dbutil.DBConnection;
import sca.pojo.UserPojo;

/**
 *
 * @author HP
 */
public class UserDao {
    public static String validateUser(UserPojo user) throws SQLException
    {

        PreparedStatement ps=DBConnection.getConnection().prepareStatement("select username from users where userid=? and password=? and usertype=? and Active ='Y'");
        ps.setString(1, user.getUserid());
        ps.setString(2,user.getPassword());
        ps.setString(3, user.getUserType());
        ResultSet rs= ps.executeQuery();
        String username=null;
        if(rs.next())
        {
            username=rs.getString(1);
        }

        return username;
    }
  
    public static boolean changePassword(String userid, String pwd) throws SQLException
    {
        PreparedStatement ps= DBConnection.getConnection().prepareStatement("update users set password=? where userid=?");
        ps.setString(1,pwd);
        ps.setString(2,userid);
        return (ps.executeUpdate()!=0);
    }
    
    public static HashMap<String,String> getRegisteredReceptionist() throws SQLException
        {
            Connection conn=DBConnection.getConnection();
            String qry="select  userid,username from users where UserType='Receptionist' and Active='Y'";
            Statement st= conn.createStatement();
            ResultSet rs=st.executeQuery(qry);
            HashMap<String,String> receptionist=new HashMap<>();
            while (rs.next())
              {
                String userid=rs.getString(1);
                String name=rs.getString(2);
                
                receptionist.put(userid , name);
              }
            return receptionist;
        }
    
    public static HashMap<String,String> getRegisteredDoctor() throws SQLException
        {
            Connection conn=DBConnection.getConnection();
            String qry="select userid,username from users where UserType='Doctor' and Active= 'Y'";
            Statement st= conn.createStatement();
            ResultSet rs=st.executeQuery(qry);
            HashMap<String,String> doc=new HashMap<>();
            while (rs.next())
              {
                String userid=rs.getString(1);
                String name=rs.getString(2);
                
                doc.put(userid , name);
              }
            return doc;
        }
}
