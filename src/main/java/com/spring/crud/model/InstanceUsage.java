package com.spring.crud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name="instance_usage_details")
public class InstanceUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sl_no;
    private int instance_id;
    private int employee_id;
    private String employee_name;
    private String purpose;
    private LocalDateTime time;

    public InstanceUsage() {
    }

    public InstanceUsage( int instance_id,int employee_id, String employee_name, String purpose, LocalDateTime time) {
        this.instance_id = instance_id;
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.purpose = purpose;
        this.time = time;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public int getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(int instance_id) {
        this.instance_id = instance_id;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
