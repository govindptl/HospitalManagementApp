/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sca.pojo;

import java.sql.Date;


public class PatientPojo {

    public PatientPojo() {
    }

    public PatientPojo(String pid, String fname, String sname, int age, String opd, String gender, String mstatus, Date pdate, String address, String city, String phoneno, String docid) {
        this.pid = pid;
        this.fname = fname;
        this.sname = sname;
        this.age = age;
        this.opd = opd;
        this.gender = gender;
        this.mstatus = mstatus;
        this.pdate = pdate;
        this.address = address;
        this.city = city;
        this.phoneno = phoneno;
        this.docid = docid;
    }

    @Override
    public String toString() {
        return "PatientPojo{" + "pid=" + pid + ", fname=" + fname + ", sname=" + sname + ", age=" + age + ", opd=" + opd + ", gender=" + gender + ", mstatus=" + mstatus + ", pdate=" + pdate + ", address=" + address + ", city=" + city + ", phoneno=" + phoneno + ", docid=" + docid + '}';
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getOpd() {
        return opd;
    }

    public void setOpd(String opd) {
        this.opd = opd;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMstatus() {
        return mstatus;
    }

    public void setMstatus(String mstatus) {
        this.mstatus = mstatus;
    }

    public Date getPdate() {
        return pdate;
    }

    public void setPdate(Date pdate) {
        this.pdate = pdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getDocid() {
        return docid;
    }

    public void setDocid(String docid) {
        this.docid = docid;
    }

  
private String pid;
private String fname;
private String sname;
private int age;
private String opd;
private String gender;
private String mstatus;
private Date pdate;
private String address;
private String city;
private String phoneno;
private String docid;
}
