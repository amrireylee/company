package com.empl.mgr.model;

public class TeSalary {
    private Long id;

    private Float base;

    private Float post;

    private Float prix;

    private Float sum;

    private Long emId;

    public TeSalary() {

    }

    public TeSalary(Long id, Float base, Float post, Float prix, Float sum, Long emId) {
        this.id = id;
        this.base = base;
        this.post = post;
        this.prix = prix;
        this.sum = sum;
        this.emId = emId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getBase() {
        return base;
    }

    public void setBase(Float base) {
        this.base = base;
    }

    public Float getPost() {
        return post;
    }

    public void setPost(Float post) {
        this.post = post;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }

    public Long getEmId() {
        return emId;
    }

    public void setEmId(Long emId) {
        this.emId = emId;
    }
}