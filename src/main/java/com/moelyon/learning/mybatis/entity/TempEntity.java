package com.moelyon.learning.mybatis.entity;

import java.io.Serializable;

/**
 * @author moelyon
 */
public class TempEntity implements Serializable {

    private  int id;
    private  String name;

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

    @Override
    public String toString() {
        return "TempEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
