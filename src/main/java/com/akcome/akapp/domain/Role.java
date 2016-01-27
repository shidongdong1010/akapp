package com.akcome.akapp.domain;

import javax.persistence.*;

/**
 * 角色实体
 * Created by dongdongshi on 16/1/12.
 */
@Entity
@Table(name = "authorities", catalog = "test")
public class Role implements java.io.Serializable {

    private Integer id;
    private User user;
    private String name;

    public Role() {
    }

    public Role(User user) {
        this.user = user;
    }

    public Role(User user, String name) {
        this.user = user;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User SUser) {
        this.user = SUser;
    }

    @Column(name = "name", length = 20)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
