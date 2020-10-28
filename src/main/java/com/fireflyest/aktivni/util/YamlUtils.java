package com.fireflyest.aktivni.util;

import com.fireflyest.aktivni.data.Config;
import com.fireflyest.aktivni.data.Language;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Fireflyest
 * Yaml数据管理
 */
public class YamlUtils {

    private static JavaPlugin plugin;
    private static Map<String, Map<String, FileConfiguration>> data = new HashMap<>();;
    private static Config config;

    private YamlUtils(){
    }

    /**
     * 初始化
     * @param javaPlugin 插件class
     */
    public static void iniYamlManager(JavaPlugin javaPlugin){
        plugin = javaPlugin;
        loadConfig();
    }

    /**
     * 添加/加载一个yml文件
     * @param table 文件夹
     * @param ymlName 不带后缀文件名
     * @return FileConfiguration
     */
    public static FileConfiguration setup(String table, String ymlName) {
        File file = new File(plugin.getDataFolder()+"/"+table, ymlName+".yml");
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
                if("".equals(table))plugin.saveResource(ymlName+".yml", true);
            } catch (IOException e) {
                e.printStackTrace();
                Bukkit.getServer().getLogger().severe(String.format("无法创建文件 %s!", ymlName+".yml"));
            }
        }

        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        if(!"".equals(table))data.get(table).put(ymlName, yml);
        return yml;
    }

    /**
     * 保存一个玩家的数据
     * @param name 玩家游戏名
     */
    public static void savePlayerData(String table ,String name) {
        File file = new File(plugin.getDataFolder()+"/"+table, name+".yml");

        try {
            data.get(table).get(name).save(file);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(String.format("无法保存玩家数据 %s!", name+".yml"));
        }
    }

    /**
     * 获取玩家的数据
     * @param name 玩家游戏名
     * @return FileConfiguration
     */
    public static FileConfiguration getPlayerData(String table, String name){
        if(!data.get(table).containsKey(name))setup(table, name);
        return data.get(table).get(name);
    }

    /**
     * @return table的所有yml
     */
    public static Set<String> getPlayerDataKeys(String table){
        return data.get(table).keySet();
    }

    public static void deletePlayerData(String table, String name){
        File file = new File(plugin.getDataFolder()+"/"+table, name+".yml");
        file.delete();
    }

    /**
     * 写入玩家数据
     * @param name 玩家游戏名
     * @param key 数据键值
     * @param value 数据值
     */
    public static void setPlayerData(String table, String name, String key, Object value){
        if(!data.get(table).containsKey(name)) setup(table, name);
        data.get(table).get(name).set(key, value);
        savePlayerData(table, name);
    }

    public static boolean containsData(String table, String name){
        addTable(table);
        return data.get(table).containsKey(name);
    }

    public static void addTable(String table){
        if(!data.containsKey(table)){
            Map<String, FileConfiguration>tableMap = new HashMap<>();
            data.put(table, tableMap);
        }
        File file = new File(plugin.getDataFolder()+"/"+table);
        if(file.getParentFile().mkdirs()){
            setup(table, "###");
        }else {
            String[]list = file.list();
            if(list == null)return;
            for(String f : list){
                if(f.contains("###"))continue;
                setup(table, f.replace(".yml", ""));
            }
        }
    }

    /**
     * 保存配置数据
     * @param key 据键值
     * @param value 数据值
     */
    public static void setConfigData(String key, Object value) {
        config.setKey(key, value);
        File file = new File(plugin.getDataFolder(), "config.yml");

        try {
            config.getConfig().save(file);
        } catch (IOException e) {
            Bukkit.getServer().getLogger().severe(String.format("无法保存数据 %s!", "config.yml"));
        }
    }

    /**
     * 加载配置文件
     */
    public static void loadConfig() {
        config = new Config(setup("", "config"));
        new Language(setup("", "language"));
    }

    /**
     * 更新配置文件
     */
    public static void upDateConfig(){
        plugin.saveResource("config.yml", true);
        plugin.saveResource("language.yml", true);
    }

}
