package com.hf.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

public class Module implements Serializable{
    @Id
    private Integer mid;

    private String mname;

    @Transient
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /**
     * @return mid
     */
    public Integer getMid() {
        return mid;
    }

    /**
     * @param mid
     */
    public void setMid(Integer mid) {
        this.mid = mid;
    }

    /**
     * @return mname
     */
    public String getMname() {
        return mname;
    }

    /**
     * @param mname
     */
    public void setMname(String mname) {
        this.mname = mname;
    }
}