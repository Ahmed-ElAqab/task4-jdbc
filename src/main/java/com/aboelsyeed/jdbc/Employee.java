package com.aboelsyeed.jdbc;

import java.sql.PreparedStatement;

public class Employee {



    private Integer ID;
    private String fname;
    private String mname;
    private String lname;
    private String email;
    private String phone;

    public Employee(Integer ID, String fname, String mname, String lname, String email, String phone) {
        this.ID = ID;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
    }

}
