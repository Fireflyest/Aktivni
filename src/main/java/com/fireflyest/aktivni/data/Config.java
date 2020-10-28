package com.fireflyest.aktivni.data;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.Set;

public class Config {

    public FileConfiguration getConfig() {
        return config;
    }

    private FileConfiguration config;

    public static String VERSION;
    public static boolean SQL;
    public static String URL;
    public static String USER;
    public static String PASSWORD;

    public static boolean DISPLAY_SKIN;
    public static boolean TASK_PROGRESS;
    public static int DEFAULT_CHANCE;
    public static int QUIZ_ACTIVITY;
    public static List<String> REWARDS;
    public static List<String> WEEKEND;
    public static String WEEKEND_ITEM;
    public static Set<String> FESTIVAL;
    public static Set<String>ACTIVITY;
    public static Set<String>SIGN_IN;
    public static Set<String>PLAYTIME;
    public static Set<String>TASK;
    public static Set<String>OBJECT;

    public Config(FileConfiguration config){
        this.config = config;
        this.setUp();
    }

    private void setUp(){
        VERSION = config.getString("Version");
        SQL = config.getBoolean("Sql");
        URL = config.getString("Url");
        USER = config.getString("User");
        PASSWORD = config.getString("Password");

        DISPLAY_SKIN = config.getBoolean("DisplaySkin");
        TASK_PROGRESS = config.getBoolean("TaskProgress");
        DEFAULT_CHANCE = config.getInt("DefaultChance");
        QUIZ_ACTIVITY = config.getInt("QuizActivity");
        REWARDS = config.getStringList("Rewards");
        WEEKEND = config.getStringList("Weekend");

//        FESTIVAL = config.getConfigurationSection("Festival").getKeys(false);
//        ACTIVITY = config.getConfigurationSection("Activity").getKeys(false);
//        SIGN_IN = config.getConfigurationSection("Signin").getKeys(false);
//        PLAYTIME = config.getConfigurationSection("Playtime").getKeys(false);
//        TASK = config.getConfigurationSection("Task").getKeys(false);
//        OBJECT = config.getConfigurationSection("Object").getKeys(false);
    }

    public void setKey(String key, Object value) {
        config.set(key, value);
    }

}
