/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package za.co.carols_boutique.models;

import java.io.Serializable;

/**
 *
 * @author muaad
 */
public class Employee implements Serializable{
    
    private String id;
    private String name;
    private String surname;
    private String password;
    private String storeID;
    private Boolean isManager;

    public Employee(String id, String name, String surname, String password, String storeID, Boolean isManager) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.storeID = storeID;
        this.isManager = isManager;
    }

    public Employee(String name, String surname, String password, String storeID, Boolean isManager) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.storeID = storeID;
        this.isManager = isManager;
    }

    public Employee(String id, String password, String storeID) {
        this.id = id;
        this.password = password;
        this.storeID = storeID;
    }
    
    

    public Employee() {
    }

    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }
    
    public void createIBT(String storeID, String password){}
    
    public void viewReport(){}

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", isManager=" + isManager + '}';
    }
    
    
}
