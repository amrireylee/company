package com.empl.mgr.model;

import java.util.Date;

public class TeLogistics {
    private Long id;

    private String name;

    private String logisticsDesc;

    private Integer useNum;

    private Integer notUseNum;

    private Integer total;

    private Date timestamp;

    public TeLogistics(Long id, String name, String logisticsDesc, Integer useNum, Integer notUseNum, Integer total, Date timestamp) {
        this.id = id;
        this.name = name;
        this.logisticsDesc = logisticsDesc;
        this.useNum = useNum;
        this.notUseNum = notUseNum;
        this.total = total;
        this.timestamp = timestamp;
    }

    public TeLogistics() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLogisticsDesc() {
        return logisticsDesc;
    }

    public void setLogisticsDesc(String logisticsDesc) {
        this.logisticsDesc = logisticsDesc == null ? null : logisticsDesc.trim();
    }

    public Integer getUseNum() {
        return useNum;
    }

    public void setUseNum(Integer useNum) {
        this.useNum = useNum;
    }

    public Integer getNotUseNum() {
        return notUseNum;
    }

    public void setNotUseNum(Integer notUseNum) {
        this.notUseNum = notUseNum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}