package com.fireflyest.aktivni.bean;

public class Sign {

    private Integer id;

    private String name;

    private Long create;

    private Integer year;

    private Integer month;

    private Integer day;

    private Boolean remedy;

    public Sign() {
    }

    public Sign(Integer id, String name, Long create, Integer year, Integer month, Integer day, Boolean remedy) {
        this.id = id;
        this.name = name;
        this.create = create;
        this.year = year;
        this.month = month;
        this.day = day;
        this.remedy = remedy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCreate() {
        return create;
    }

    public void setCreate(Long create) {
        this.create = create;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Boolean getRemedy() {
        return remedy;
    }

    public void setRemedy(Boolean remedy) {
        this.remedy = remedy;
    }
}
