/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sca.pojo;


public class UserProfile {

    public static String getUserType() {
        return userType;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserProfile.username = username;
    }

    public static void setUserType(String userType) {
        UserProfile.userType = userType;
    }
    private static String username;
    private static String userType;

}
