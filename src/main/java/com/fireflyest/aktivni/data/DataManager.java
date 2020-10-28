package com.fireflyest.aktivni.data;

import com.fireflyest.aktivni.bean.*;
import com.fireflyest.aktivni.bean.Calendar;
import com.fireflyest.aktivni.core.AktivniPage;
import com.fireflyest.aktivni.util.TimeUtils;

import java.sql.Time;
import java.util.*;

public class DataManager {

    private static DataDriver driver;

    private static Map<Integer, Calendar> calendarMap = new HashMap<>();

    private static Map<String , User> userMap = new HashMap<>();

    private static Map<Integer, Integer> place = new HashMap<>();

    private static Map<String , List<Sign>> signMap = new HashMap<>();

    private static List<Reward> credit = new ArrayList<>();

    private static List<Reward> sign = new ArrayList<>();

    private static List<Reward> playtime = new ArrayList<>();

    private static List<Task> task = new ArrayList<>();

    private static List<Festival> festivals = new ArrayList<>();

    private static int month = 0;

    private DataManager(){
    }

    public static void loadData(){
        driver = Config.SQL ?
                new JdbcDriver(Config.URL, Config.USER, Config.PASSWORD) :
                new YamlDriver();

        driver.initTable(Calendar.class);
        driver.initTable(Activity.class);
        driver.initTable(User.class);
        driver.initTable(Festival.class);
        driver.initTable(Playtime.class);
        driver.initTable(Reward.class);
        driver.initTable(Task.class);
        driver.initTable(Sign.class);

        month = TimeUtils.getMonth();

        calendarMap.clear();
        userMap.clear();
        place.clear();
        signMap.clear();
        credit.clear();
        sign.clear();
        playtime.clear();
        task.clear();

        //日历
        driver.queryList(Calendar.class, String.valueOf(TimeUtils.getYear()), "year").forEach(c -> {
            Calendar calendar = (Calendar)c;
            calendarMap.put(calendar.getMonth(), calendar);
        });
        if(!calendarMap.containsKey(TimeUtils.getMonth())) createCalendar();

        //签到
        driver.queryList(Sign.class, String.valueOf(TimeUtils.getYear()), "year").forEach(s -> {
            Sign sign = (Sign) s;
            getSignList(sign.getName()).add(sign);
        });

        //奖励
        loadReward("all");

        //任务
        loadTask();
    }

    public static void loadReward(String type){
        switch (type){
            case "all":
                credit.clear();
                sign.clear();
                playtime.clear();
                festivals.clear();
                driver.queryList(Reward.class, "credit", "type").forEach(c -> credit.add((Reward)c));
                driver.queryList(Reward.class, "sign_total", "type").forEach(s -> sign.add((Reward)s));
                driver.queryList(Reward.class, "sign_series", "type").forEach(s -> sign.add((Reward)s));
                driver.queryList(Reward.class, "playtime_total", "type").forEach(p -> playtime.add((Reward)p));
                driver.queryList(Reward.class, "playtime_series", "type").forEach(p -> playtime.add((Reward)p));
                driver.queryList(Festival.class, String.valueOf(TimeUtils.getMonth()), "month").forEach(f -> festivals.add((Festival)f));
                AktivniPage.updateSubPage(type);
                break;
            case "credit":
                credit.clear();
                driver.queryList(Reward.class, "credit", "type").forEach(c -> credit.add((Reward)c));
                AktivniPage.updateSubPage(type);
                break;
            case "sign_total":
            case "sign_series":
                sign.clear();
                driver.queryList(Reward.class, "sign_total", "type").forEach(s -> sign.add((Reward)s));
                driver.queryList(Reward.class, "sign_series", "type").forEach(s -> sign.add((Reward)s));
                AktivniPage.updateSubPage(type);
                break;
            case "playtime_total":
            case "playtime_series":
                playtime.clear();
                driver.queryList(Reward.class, "playtime_total", "type").forEach(p -> playtime.add((Reward)p));
                driver.queryList(Reward.class, "playtime_series", "type").forEach(p -> playtime.add((Reward)p));
                AktivniPage.updateSubPage(type);
                break;
            case "festival":
                festivals.clear();
                driver.queryList(Reward.class, TimeUtils.getMonth(), "month").forEach(f -> festivals.add((Festival) f));
                break;
            default:
        }
    }

    public static void loadTask(){
        task.clear();
        driver.queryList(Task.class, "daily", "type").forEach(t -> task.add((Task)t));
        AktivniPage.updateSubPage("task");
    }

    public static void createCalendar(){
        Calendar calendar = new Calendar(
                0,
                TimeUtils.getYear(),
                TimeUtils.getMonth(),
                TimeUtils.getFirstDay(),
                TimeUtils.getMaxDay()
        );
        calendarMap.put(calendar.getMonth(), calendar);
        driver.insert(calendar);
    }

    public static Map<Integer, Integer> getPlace(int m){
        if(!calendarMap.containsKey(m)) createCalendar();
        if(month != m || place.size() == 0){
            place.clear();
            Calendar calendar = calendarMap.get(m);
            int p = calendar.getFirst()-1;
            for(int i = 1; i <= calendar.getMax(); i++){
                place.put(i, p);
                p += (p-6)%9 == 0 ? 3 : 1;
            }
            month = m;
        }
        return place;
    }

    public static User getUser(String name){
        if(userMap.containsKey(name)){
            return userMap.get(name);
        }else {
            User user;
            if(driver.contain(User.class, name, "name")){
                user = (User) driver.query(User.class, name, "name");
            }else {
                user = new User(name, "", TimeUtils.getDate(), Config.DEFAULT_CHANCE);
                driver.insert(user);
            }
            userMap.put(name, user);
            return user;
        }
    }

    public static List<Sign> getSignList(String name){
        if(signMap.containsKey(name)){
            return signMap.get(name);
        }else {
            List<Sign> signList = new ArrayList<>();
            signMap.put(name, signList);
            return signList;
        }
    }

    public static void addSign(String name, int day, boolean remedy){
        Sign sign = new Sign(0, name, TimeUtils.getDate(), TimeUtils.getYear(), TimeUtils.getMonth(), day, remedy);
        getSignList(name).add(sign);
        driver.insert(sign);
    }

    public static List<Reward> getCredit() {
        return credit;
    }

    public static List<Reward> getSign() {
        return sign;
    }

    public static List<Reward> getPlaytime() {
        return playtime;
    }

    public static List<Task> getTask() {
        return task;
    }

    public static List<Festival> getFestivals() {
        return festivals;
    }

    public static void saveData(){
        userMap.values().forEach(driver::update);
    }

    public static DataDriver dataDriver(){
        return driver;
    }

}
