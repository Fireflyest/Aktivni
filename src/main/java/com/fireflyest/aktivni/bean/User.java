package com.fireflyest.aktivni.bean;

public class User {

    /**
     * 游戏名
     */
    private String name;

    /**
     * 唯一id
     */
    private String uuid;

    /**
     * 何时加入
     */
    private Long join;

    /**
     * 积分
     */
    private Double credit;

    /**
     * 补签次数
     */
    private Integer chance;

    /**
     * 签到总数
     */
    private Integer total;
    private Integer total_reward;

    /**
     * 最后一次签到日期
     */
    private Integer last;

    /**
     * 连续签到次数
     */
    private Integer series;
    private Integer series_reward;

    /**
     * 是否在线
     */
    private Boolean online;

    /**
     * 总共在时长
     */
    private Long playing;
    private Long playing_reward;

    /**
     * 总共在时长
     */
    private Long played;
    private Long played_reward;

    /**
     * 进行中任务
     */
    private Integer task;

    /**
     * 完成的任务
     */
    private Integer finish;

    /**
     * 暂存备用
     */
    private String data;

    public User() {
    }

    public User(String name, String uuid, Long join, Integer chance) {
        this.name = name;
        this.uuid = uuid;
        this.join = join;
        this.chance = chance;
        this.series = 0;
        this.series_reward = 0;
        this.total = 0;
        this.total_reward = 0;
        this.last = 0;
        this.credit = 0.0;
        this.played = 0L;
        this.played_reward = 0L;
        this.playing = 0L;
        this.playing_reward = 0L;
        this.online = false;
        this.task = 0;
        this.finish = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getJoin() {
        return join;
    }

    public void setJoin(Long join) {
        this.join = join;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Integer getChance() {
        return chance;
    }

    public void setChance(Integer chance) {
        this.chance = chance;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Boolean getOnline() {
        return online;
    }

    public void setOnline(Boolean online) {
        this.online = online;
    }

    public Long getPlaying() {
        return playing;
    }

    public void setPlaying(Long playing) {
        this.playing = playing;
    }

    public Long getPlayed() {
        return played;
    }

    public void setPlayed(Long played) {
        this.played = played;
    }

    public Integer getTask() {
        return task;
    }

    public void setTask(Integer task) {
        this.task = task;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getTotal_reward() {
        return total_reward;
    }

    public void setTotal_reward(Integer total_reward) {
        this.total_reward = total_reward;
    }

    public Integer getSeries_reward() {
        return series_reward;
    }

    public void setSeries_reward(Integer series_reward) {
        this.series_reward = series_reward;
    }

    public Long getPlaying_reward() {
        return playing_reward;
    }

    public void setPlaying_reward(Long playing_reward) {
        this.playing_reward = playing_reward;
    }

    public Long getPlayed_reward() {
        return played_reward;
    }

    public void setPlayed_reward(Long played_reward) {
        this.played_reward = played_reward;
    }
}
