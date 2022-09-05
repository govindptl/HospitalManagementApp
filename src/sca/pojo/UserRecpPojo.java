/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sca.pojo;

/**
 *
 * @author HP
 */
public class UserRecpPojo {

    @Override
    public String toString() {
        return "UserRecpPojo{" + "userid=" + userid + ", password=" + password + ", userType=" + userType + ", username=" + username + ", empId=" + empId + '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }
    private String userid;
    private String password;
    private String userType;
    private String username;
    private String empId;
            
}
