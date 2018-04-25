package com.empl.mgr.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

public class TeProfit {
    private Long id;

    private BigDecimal principal;

    private Integer type;

    private BigDecimal income;

    private BigDecimal profit;

    private BigDecimal allIncome;

    private BigDecimal allExpense;

    private String remark;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date timestamp;

    public TeProfit(Long id, BigDecimal principal, Integer type, BigDecimal income, BigDecimal profit, BigDecimal allIncome, BigDecimal allExpense, String remark, Date timestamp) {
        this.id = id;
        this.principal = principal;
        this.type = type;
        this.income = income;
        this.profit = profit;
        this.allIncome = allIncome;
        this.allExpense = allExpense;
        this.remark = remark;
        this.timestamp = timestamp;
    }

    public TeProfit() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getAllIncome() {
        return allIncome;
    }

    public void setAllIncome(BigDecimal allIncome) {
        this.allIncome = allIncome;
    }

    public BigDecimal getAllExpense() {
        return allExpense;
    }

    public void setAllExpense(BigDecimal allExpense) {
        this.allExpense = allExpense;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}