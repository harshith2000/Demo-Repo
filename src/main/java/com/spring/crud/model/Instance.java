package com.spring.crud.model;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="instance")
public class Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instance_id;
    private String instance_name;
    @Value("${some.key:false}")
    private boolean instance_state;

    public Instance() {
    }

    public Instance(int instance_id, String instance_name, boolean instance_state) {
        this.instance_id = instance_id;
        this.instance_name = instance_name;
        this.instance_state = instance_state;
    }

    public int getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(int instance_id) {
        this.instance_id = instance_id;
    }

    public String getInstance_name() {
        return instance_name;
    }

    public void setInstance_name(String instance_name) {
        this.instance_name = instance_name;
    }

    public boolean isInstance_state() {
        return instance_state;
    }

    public void setInstance_state(boolean instance_state) {
        this.instance_state = instance_state;
    }
}
