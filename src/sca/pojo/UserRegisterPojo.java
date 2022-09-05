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
public class UserRegisterPojo {

    @Override
    public String toString() {
        return "UserRecpPojo{" + "userid=" + userid + ", username=" + username + ", empid=" + empid + ", password=" + password + ", UserType=" + UserType + '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    private String userid;
    private String username;
    private String empid;
    private String password;
    private String UserType;       
}
