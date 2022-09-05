/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sca.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DBConnection {
    private static Connection conn;

    static {
            try{
               if(conn==null)
               {
               //Class.forName("oracle.jdbc.OracleDriver");
               conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sanjeevani","root","root");
               //JOptionPane.showMessageDialog(null, "Connected successfully!","Success",JOptionPane.INFORMATION_MESSAGE);
               }
            }
//           catch(ClassNotFoundException cle){
//               JOptionPane.showMessageDialog(null, "Driver Not Loaded!"+cle);
//               cle.printStackTrace();
//           }
           catch(SQLException sqle)
           {
               JOptionPane.showMessageDialog(null, "Problems in DB"+sqle);
               sqle.printStackTrace();
           }
        }
    public static Connection getConnection()
    {
        return conn;
    }
    
    public static void closeConnection()
    {
        try
        {
            if(conn!=null)
            {
                conn.close();
                //JOptionPane.showMessageDialog(null, "Connection closed successfully!","Success",JOptionPane.INFORMATION_MESSAGE);
            }
        }
       catch(SQLException sqle)
       {
           JOptionPane.showMessageDialog(null, "Problems in Closing a db!"+sqle);
           sqle.printStackTrace();
       }
    }
    
}


