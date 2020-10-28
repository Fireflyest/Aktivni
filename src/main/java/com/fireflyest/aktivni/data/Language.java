package com.fireflyest.aktivni.data;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Language {

    private FileConfiguration lang;

    public static List<String>HELP = new ArrayList<>();
    public static String VERSION;
    public static String TITLE;
    public static String PLUGIN_NAME;
    public static String PLAYER_COMMAND;
    public static String RELOADING;
    public static String RELOADED;
    public static String DISPLAY_DAY;
    public static String NOT_ENOUGH_CHANCE;
    public static String SIGN_IN_FUTURE;
    public static String SIGN_IN_SUCCESS;
    public static String SIGN_IN_ALREADY;
    public static String SIGN_IN_REMIND;
    public static String UNLUCK_TODAY;
    public static String SIGN_IN_FESTIVAL;
    public static String NOT_ENOUGH_CREDIT;
    public static String NOT_ENOUGH_SIGNIN;
    public static String NOT_ENOUGH_PLAYTIME;
    public static String EXCHANGE_SUCCESS;
    public static String FAULT_SIGN_IN;
    public static String REWARD_TASK;
    public static String ADD_CHANCE;
    public static String FINISH_TASK;
    public static String ADD_TASK;
    public static String SIGN_IN_WEEKEND;
    public static String ACTIVITY_RANK;
    public static String DAY_ACTIVITY;
    public static String SELECT_OPTION;
    public static String RIGHT_OPTION;
    public static String FALSE_OPTION;
    public static String WIN_PLAYER;
    public static String SELECTED;
    public static String BUTTON_SIGNIN;
    public static String BUTTON_PLAYTIME;
    public static String BUTTON_TASK;
    public static String MAIN_GUI;
    public static String CREDIT_PAGE;
    public static String SIGN_PAGE;
    public static String PLAYTIME_PAGE;
    public static String TASK_PAGE;

    public static String DATA_EDIT;
    public static String DATA_EMPTY;
    public static String DATA_LOADED;
    public static String DATA_REMOVE;

    public Language(FileConfiguration lang){
        this.lang = lang;
        this.setUp();
    }

    private void setUp(){
        VERSION = lang.getString("Version");
        HELP = lang.getStringList("Help");
        TITLE = this.parseColor(lang.getString("Title"));
        PLUGIN_NAME = this.parseColor(lang.getString("PluginName"));
        PLAYER_COMMAND = TITLE + this.parseColor(lang.getString("PlayerCommand"));
        RELOADING = TITLE + this.parseColor(lang.getString("Reloading"));
        RELOADED = TITLE + this.parseColor(lang.getString("Reloaded"));

        DISPLAY_DAY = this.parseColor(lang.getString("DisplayDay"));

        MAIN_GUI = this.parseColor(lang.getString("MainGui"));
        CREDIT_PAGE = this.parseColor(lang.getString("CreditPage"));
        SIGN_PAGE = this.parseColor(lang.getString("SignPage"));
        PLAYTIME_PAGE = this.parseColor(lang.getString("PlaytimePage"));
        TASK_PAGE = this.parseColor(lang.getString("TaskPage"));
        
        NOT_ENOUGH_CHANCE = TITLE + this.parseColor(lang.getString("NotEnoughChance"));
        SIGN_IN_FUTURE = TITLE + this.parseColor(lang.getString("SignInFuture"));
        SIGN_IN_SUCCESS = TITLE + this.parseColor(lang.getString("SignInSuccess"));
        SIGN_IN_ALREADY = TITLE + this.parseColor(lang.getString("SignInAlready"));
        SIGN_IN_REMIND = TITLE + this.parseColor(lang.getString("SignInRemind"));
        UNLUCK_TODAY = TITLE + this.parseColor(lang.getString("UnluckToday"));
        SIGN_IN_FESTIVAL = TITLE + this.parseColor(lang.getString("SignInFestival"));
        NOT_ENOUGH_CREDIT = TITLE + this.parseColor(lang.getString("NotEnoughCredit"));
        EXCHANGE_SUCCESS = TITLE + this.parseColor(lang.getString("ExchangeSuccess"));
        NOT_ENOUGH_SIGNIN  = TITLE + this.parseColor(lang.getString("NotEnoughSignin"));
        NOT_ENOUGH_PLAYTIME = TITLE + this.parseColor(lang.getString("NotEnoughPlaytime"));
        FAULT_SIGN_IN = TITLE + this.parseColor(lang.getString("FaultSignIn"));
        REWARD_TASK = TITLE + this.parseColor(lang.getString("RewardTask"));
        ADD_CHANCE = TITLE + this.parseColor(lang.getString("AddChance"));
        FINISH_TASK  = TITLE + this.parseColor(lang.getString("FinishTask"));
        ADD_TASK  = TITLE + this.parseColor(lang.getString("AddTask"));
        SIGN_IN_WEEKEND = TITLE + this.parseColor(lang.getString("SignInWeekend"));
        ACTIVITY_RANK  = TITLE + this.parseColor(lang.getString("ActivityRank"));
        DAY_ACTIVITY  = TITLE + this.parseColor(lang.getString("DayActivity"));
        SELECT_OPTION  = TITLE + this.parseColor(lang.getString("SelectOption"));
        RIGHT_OPTION  = TITLE + this.parseColor(lang.getString("RightOption"));
        FALSE_OPTION  = TITLE + this.parseColor(lang.getString("FalseOption"));
        WIN_PLAYER  = TITLE + this.parseColor(lang.getString("WinPlayer"));
        SELECTED  = TITLE + this.parseColor(lang.getString("Selected"));
        BUTTON_SIGNIN =  this.parseColor(lang.getString("ButtonSignIn"));
        BUTTON_PLAYTIME =  this.parseColor(lang.getString("ButtonPlaytime"));
        BUTTON_TASK =  this.parseColor(lang.getString("ButtonTask"));

        DATA_EDIT = TITLE + this.parseColor(lang.getString("DataEdit"));
        DATA_EMPTY = TITLE + this.parseColor(lang.getString("DataEmpty"));
        DATA_LOADED = TITLE + this.parseColor(lang.getString("DataLoaded"));
        DATA_REMOVE = TITLE + this.parseColor(lang.getString("DataRemove"));
    }

    private String parseColor(String str){
        if(null != str && !"".equals(str))return str.replace("&", "§");
        return str;
    }

}
