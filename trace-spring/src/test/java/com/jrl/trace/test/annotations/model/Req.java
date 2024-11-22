package com.jrl.trace.test.annotations.model;

import java.io.Serializable;

/**
* @author JerryLong
* @version V1.0
*/
public class Req implements Serializable {
    private String name;
    private Integer age;

    public Req() {
    }

    public Req(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
