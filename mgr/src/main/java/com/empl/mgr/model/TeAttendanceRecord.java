package com.empl.mgr.model;

public class TeAttendanceRecord {
    private Long id;

    private Long emId;

    private Integer inDays;

    private Integer outDays;

    public TeAttendanceRecord() {

    }

    public TeAttendanceRecord(Long id, Long emId, Integer inDays, Integer outDays) {
        this.id = id;
        this.emId = emId;
        this.inDays = inDays;
        this.outDays = outDays;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmId() {
        return emId;
    }

    public void setEmId(Long emId) {
        this.emId = emId;
    }

    public Integer getInDays() {
        return inDays;
    }

    public void setInDays(Integer inDays) {
        this.inDays = inDays;
    }

    public Integer getOutDays() {
        return outDays;
    }

    public void setOutDays(Integer outDays) {
        this.outDays = outDays;
    }
}