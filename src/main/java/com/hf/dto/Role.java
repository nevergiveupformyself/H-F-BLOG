package com.hf.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable {
    @Id
    private Integer rid;

    private String rname;

    @Transient
    private Set<User> users;

    @Transient
    private Set<Module> modules;

    public Set<Module> getModules() {
        return modules;
    }

    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * @return rid
     */
    public Integer getRid() {
        return rid;
    }

    /**
     * @param rid
     */
    public void setRid(Integer rid) {
        this.rid = rid;
    }

    /**
     * @return rname
     */
    public String getRname() {
        return rname;
    }

    /**
     * @param rname
     */
    public void setRname(String rname) {
        this.rname = rname;
    }
}