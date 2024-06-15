/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Model;

public class Employee {
    private int id;
    private String name;
    private String email;
    private String position;
    
    public Employee() {
    }
    
    public Employee (String name, String email, String position){
        super();
        this.name = name;
        this.email = email;
        this.position = position;
    }
    
    public Employee (int id, String name , String email, String position){
        super();
        this.id = id;
        this.name = name;
        this.email = email;
        this.position = position;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
