package com.fireflyest.aktivni.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * @author Fireflyest
 * ❤✦✧@❀❁❂☻☠☽⚒⚓⚔⚜⚝✈✂✄✘♯¶♩♪♫♬
 */
public class ConvertUtils {

    private final static String BAR = "▎";

    private ConvertUtils(){

    }

    /**
     * 获取一定数量的符号条
     * @param amount 数量
     * @return String
     */
    public static String convertBar(double amount) {
        String bars = "";
        for(int i = (int)amount ; i > 0 ; i--) { bars = bars.concat(BAR); }
        return bars;
    }

    /**
     * 将long类型转化为时间
     * @param time 时间数据
     * @return String
     */
    public static String convertTime(long time) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;
        long day = time / dd;
        long hour = (time - day * dd)/ hh;
        long minute = (time - day * dd - hour * hh )/ mi;
        long second = (time - day * dd - hour * hh - minute * mi )/ ss;
        if(time < 60000)return second+"秒";
        if(time < 3600000)return minute+"分"+second+"秒";
        if(time < 86400000)return hour+"时"+minute+"分"+second+"秒";
        return day+"天"+hour+"时"+minute+"分"+second+"秒";
    }

    /**
     * 将位置转化为字符串
     * @param loc 位置
     * @return String
     */
    public static String convertLocation(Location loc){
        String worldName = "world";
        World world = loc.getWorld();
        if(world != null) worldName = world.getName();
        return worldName + "," + loc.getX() + "," + loc.getY() + "," + loc.getZ() + "," + loc.getYaw() + "," + loc.getPitch();
    }

    /**
     * 将字符串数据转化为位置
     * @param loc 字符串位置数据
     * @return Location
     */
    public static Location convertLocation(String loc){
        String[] l = loc.split(",");
        return new Location(Bukkit.getWorld(l[0]), Double.parseDouble(l[1]), Double.parseDouble(l[2]), Double.parseDouble(l[3]), Float.parseFloat(l[4]), Float.parseFloat(l[5]));
    }

    /**
     * 将java数据类型转化为sql数据类型
     * @param type java数据类型
     * @return sql数据类型
     */
    public static String javaType2SQLType(String type){
        switch (type){
            case "int":
                return "int";
            case "long":
            case "java.lang.Long":
                return "bigint";
            case "boolean":
            case "java.lang.Boolean":
                return "bit";
            case "short":
                return "tinyint";
            case "java.lang.String":
                return "varchar(31)";
            case "java.sql.Date":
                return "datetime";
            case "java.lang.Double":
                return "double";
            case "java.lang.Float":
                return "float";
            case "java.lang.Integer":
                return "integer";
            default:
        }
        return "varchar(31)";
    }

}
