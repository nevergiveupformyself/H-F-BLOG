package com.hf.dto;

import java.util.Date;
import javax.persistence.*;

public class Databasechangeloglock {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LOCKED")
    private Boolean locked;

    @Column(name = "LOCKGRANTED")
    private Date lockgranted;

    @Column(name = "LOCKEDBY")
    private String lockedby;

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return LOCKED
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * @param locked
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     * @return LOCKGRANTED
     */
    public Date getLockgranted() {
        return lockgranted;
    }

    /**
     * @param lockgranted
     */
    public void setLockgranted(Date lockgranted) {
        this.lockgranted = lockgranted;
    }

    /**
     * @return LOCKEDBY
     */
    public String getLockedby() {
        return lockedby;
    }

    /**
     * @param lockedby
     */
    public void setLockedby(String lockedby) {
        this.lockedby = lockedby;
    }
}