package com.akcome.akapp.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色实体
 * Created by dongdongshi on 16/1/12.
 */
@Entity
@Table(name = "s_authoritie")
public class Role implements java.io.Serializable {

    private Integer id;
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "name", length = 20)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
