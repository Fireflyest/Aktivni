package com.fireflyest.aktivni.bean;

public class Calendar {

    private Integer id;

    private Integer year;

    private Integer month;

    private Integer first;

    private Integer max;

    public Calendar() {
    }

    public Calendar(Integer id, Integer year, Integer month, Integer first, Integer max) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.first = first;
        this.max = max;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}
