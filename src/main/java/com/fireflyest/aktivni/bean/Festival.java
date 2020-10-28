package com.fireflyest.aktivni.bean;

public class Festival {

    private String name;

    private Integer month;

    private Integer day;

    private String item;

    private String command;

    private String desc;

    private String info;

    public Festival() {
    }

    public Festival(String name, Integer month, Integer day, String item, String command, String desc, String info) {
        this.name = name;
        this.month = month;
        this.day = day;
        this.item = item;
        this.command = command;
        this.desc = desc;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
