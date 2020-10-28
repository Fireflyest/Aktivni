package com.fireflyest.aktivni.bean;

public class Playtime {

    private Integer id;

    private String name;

    private Long create;

    private Long time;

    private Boolean weekend;

    public Playtime() {
    }

    public Playtime(Integer id, String name, Long create, Long time, Boolean weekend) {
        this.id = id;
        this.name = name;
        this.create = create;
        this.time = time;
        this.weekend = weekend;
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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Boolean getWeekend() {
        return weekend;
    }

    public void setWeekend(Boolean weekend) {
        this.weekend = weekend;
    }
}
