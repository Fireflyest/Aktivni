package com.fireflyest.aktivni.bean;

public class Reward {

    private String name;

    private String item;

    private String type;

    private Long need;

    private String command;

    private String desc;

    private String info;

    public Reward() {
    }

    public Reward(String name, String item, String type, Long need, String command, String desc, String info) {
        this.name = name;
        this.item = item;
        this.type = type;
        this.need = need;
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getNeed() {
        return need;
    }

    public void setNeed(Long need) {
        this.need = need;
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
