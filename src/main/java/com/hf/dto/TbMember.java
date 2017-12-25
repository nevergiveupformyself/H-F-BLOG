package com.hf.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "tb_member")
public class TbMember implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ???
     */
    private String mobile;

    /**
     * ??
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * ??
     */
    private String introduce;

    /**
     * ????
     */
    private String status;

    /**
     * ???
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * ????
     */
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取???
     *
     * @return mobile - ???
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置???
     *
     * @param mobile ???
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取??
     *
     * @return real_name - ??
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置??
     *
     * @param realName ??
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 获取??
     *
     * @return introduce - ??
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置??
     *
     * @param introduce ??
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    /**
     * 获取????
     *
     * @return status - ????
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置????
     *
     * @param status ????
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取???
     *
     * @return created_by - ???
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置???
     *
     * @param createdBy ???
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取????
     *
     * @return created_date - ????
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * 设置????
     *
     * @param createdDate ????
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return last_modified_by
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * @param lastModifiedBy
     */
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * @return last_modified_date
     */
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * @param lastModifiedDate
     */
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}