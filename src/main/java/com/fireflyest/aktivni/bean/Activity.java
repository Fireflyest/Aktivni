package com.fireflyest.aktivni.bean;

public class Activity {

    private Integer id;

    private String name;

    private Long create;

    private Integer amount;

    private String type;

    public Activity() {
    }

    public Activity(Integer id, String name, Long create, Integer amount, String type) {
        this.id = id;
        this.name = name;
        this.create = create;
        this.amount = amount;
        this.type = type;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
