package com.akcome.akapp.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体
 * Created by dongdongshi on 16/1/12.
 */
@Entity
@Table(name = "s_user")//code11
public class User implements java.io.Serializable {

    /** 主键ID **/
    private Long id;

    /** 用户名 **/
    private String username;

    /** 密码 **/
    private String password;

    /** 密码是否过期(0-正常,1-已过期) **/
    private Integer expired = 0;

    /** 是否锁定(0-正常,1-锁定) **/
    private Integer locked = 0;

    /** 是否禁用(0-否,1-是) **/
    private Integer enabled = 0;

    /** 手机号 **/
    private String mobile;

    /** 用户全称 **/
    private String fullname;

    /** 最后登陆时间 **/
    private Date lastLoginTime;

    /** 登录错误次数 **/
    private Integer loginErrorCount;

    /** 创建时间 **/
    private Date createTime;

    private Set<Role> roles = new HashSet<Role>(0);// Code12

    public User() {
    }

    public User(String username, String mobile, String password, Date createTime, Set<Role> roles) {
        this.username = username;
        this.mobile = mobile;
        this.password = password;
        this.createTime = createTime;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username", length = 20)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "mobile", length = 20)
    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Column(name = "password", length = 60)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "expired", length = 1)
    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    @Column(name = "locked", length = 1)
    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    @Column(name = "enabled", length = 1)
    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Column(name = "fullname", length = 45)
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login_time", length = 10)
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Column(name = "login_error_count", length = 2)
    public Integer getLoginErrorCount() {
        return loginErrorCount;
    }

    public void setLoginErrorCount(Integer loginErrorCount) {
        this.loginErrorCount = loginErrorCount;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createTime", length = 10)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @OneToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="s_user_authority",
            inverseJoinColumns={@JoinColumn(name="a_id")},
            joinColumns={@JoinColumn(name="u_id")}
    )
    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
