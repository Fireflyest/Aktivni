package com.fireflyest.aktivni.bean;

public class Task {

    private String name;

    private String item;

    private String type;

    private String target;

    private Integer need;

    private Integer activity;

    private Boolean auto;

    private Boolean once;

    private String desc;

    private String info;

    private String next;

    public Task() {
    }

    public Task(String name, String item, String type, String target, Integer need, Integer activity, Boolean auto, Boolean once, String desc, String info, String next) {
        this.name = name;
        this.item = item;
        this.type = type;
        this.target = target;
        this.need = need;
        this.activity = activity;
        this.auto = auto;
        this.once = once;
        this.desc = desc;
        this.info = info;
        this.next = next;
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

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getNeed() {
        return need;
    }

    public void setNeed(Integer need) {
        this.need = need;
    }

    public Integer getActivity() {
        return activity;
    }

    public void setActivity(Integer activity) {
        this.activity = activity;
    }

    public Boolean getAuto() {
        return auto;
    }

    public void setAuto(Boolean auto) {
        this.auto = auto;
    }

    public Boolean getOnce() {
        return once;
    }

    public void setOnce(Boolean once) {
        this.once = once;
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

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
