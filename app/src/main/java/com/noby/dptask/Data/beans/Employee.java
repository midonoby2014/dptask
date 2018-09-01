package com.noby.dptask.Data.beans;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by A.Noby on 8/29/2018.
 */
public class Employee  implements Serializable {

    @SerializedName("name")
    private
    String name;
    @SerializedName("email")
    private
    String email;
    @SerializedName("phone_numbers")
    private
    List<String>  phone_numbers;
    @SerializedName("profile_pictures")
    private
    List<String> profile_pictures;
    @SerializedName("role")
    private
    String role;
    @SerializedName("coords")
       Coordinate coords ;

    public   Employee (String name,String email , List<String> phone_numbers , List<String> profile_pictures , String role ,  Coordinate coords ){
        this.setName(name);
        this.setEmail(email);
        this.setPhone_numbers(phone_numbers);
        this.setProfile_pictures(profile_pictures);
        this.setRole(role);
        this.coords =  coords ;
    }

    public  Coordinate getCoords() {
        return coords;
    }

    public  void setCoords(Coordinate coords) {
     this.coords = coords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPhone_numbers() {
        return phone_numbers;
    }

    public void setPhone_numbers(List<String> phone_numbers) {
        this.phone_numbers = phone_numbers;
    }

    public List<String> getProfile_pictures() {
        return profile_pictures;
    }

    public void setProfile_pictures(List<String> profile_pictures) {
        this.profile_pictures = profile_pictures;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public  Employee (String name ,  String email,  List<String> phone_numbers, List<String> profile_pictures,String role ,Coordinate coordinate ){
//        this
//    }

}
