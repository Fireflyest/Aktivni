package com.fireflyest.aktivni.core;

import com.cryptomorin.xseries.XMaterial;
import com.fireflyest.aktivni.util.ItemUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AktivniItem {

    private static AktivniItem aktivniItem = new AktivniItem();

    public static AktivniItem getInstance() {return aktivniItem;}

    private AktivniItem(){
    }

    public static ItemStack CLOSE;
    public static ItemStack BACK;
    public static ItemStack BLANK;
    public static ItemStack REFRESH;
    public static ItemStack CREDIT;
    public static ItemStack SIGN;
    public static ItemStack PLAYTIME;
    public static ItemStack TASK;

    public static ItemStack MISS;
    public static ItemStack TODAY;
    public static ItemStack FUTURE;
    public static ItemStack FINISH;

    public void init(){

        Material activity = XMaterial.PLAYER_HEAD.parseMaterial();
        if(null != activity){
            CREDIT = new ItemStack(activity);
            ItemUtils.setDisplayName(CREDIT, "§e§l活跃信息");
            ItemUtils.addLore(CREDIT, "§6@§9credit");
        }

        Material sign = XMaterial.WRITABLE_BOOK.parseMaterial();
        if(null != sign){
            SIGN = new ItemStack(sign);
            ItemUtils.setDisplayName(SIGN, "§e§l签到信息");
            ItemUtils.addLore(SIGN, "§6@§9sign");
        }

        Material playtime = XMaterial.CLOCK.parseMaterial();
        if(null != playtime){
            PLAYTIME = new ItemStack(playtime);
            ItemUtils.setDisplayName(PLAYTIME, "§e§l在线信息");
            ItemUtils.addLore(PLAYTIME, "§6@§9playtime");

        }

        Material task = XMaterial.BOOKSHELF.parseMaterial();
        if(null != task){
            TASK = new ItemStack(task);
            ItemUtils.setDisplayName(TASK, "§e§l任务列表");
            ItemUtils.addLore(TASK, "§6@§9task");

        }

        Material close = XMaterial.BARRIER.parseMaterial();
        if(null != close){
            CLOSE = new ItemStack(close);
            ItemUtils.setDisplayName(CLOSE, "§c§l关闭");
            ItemUtils.addLore(CLOSE, "§6@§9close");
            ItemUtils.addLore(CLOSE, "");
            ItemUtils.addLore(CLOSE, "§6*§f点击关闭");
        }

        Material back = XMaterial.BARRIER.parseMaterial();
        if(null != back){
            BACK = new ItemStack(back);
            ItemUtils.setDisplayName(BACK, "§c§l返回");
            ItemUtils.addLore(BACK, "§6@§9back");
            ItemUtils.addLore(BACK, "");
            ItemUtils.addLore(BACK, "§6*§f点击返回");
        }

        Material blank = XMaterial.WHITE_STAINED_GLASS_PANE.parseMaterial();
        if(null != blank){
            BLANK = new ItemStack(blank);
            ItemUtils.setDisplayName(BLANK, " ");
        }

        Material refresh = XMaterial.WHITE_STAINED_GLASS_PANE.parseMaterial();
        if(null != refresh){
            REFRESH = new ItemStack(refresh);
            ItemUtils.setDisplayName(REFRESH, "§e§l刷新");
            ItemUtils.addLore(REFRESH, "§6@§9refresh");
            ItemUtils.addLore(REFRESH, "");
            ItemUtils.addLore(REFRESH, "§6*§f点击刷新");
        }

        Material today = XMaterial.FEATHER.parseMaterial();
        if(null != today){
            TODAY = new ItemStack(today);
            ItemUtils.setDisplayName(TODAY, "§f[§a§l签到§f]");
        }

        Material miss = XMaterial.PAPER.parseMaterial();
        if(null != miss){
            MISS = new ItemStack(miss);
            ItemUtils.setDisplayName(MISS, "§f[§c§l补签§f]");
        }

        Material future = XMaterial.PAPER.parseMaterial();
        if(null != future){
            FUTURE = new ItemStack(future);
            ItemUtils.setDisplayName(FUTURE, "§f[§2§l签到§f]");
        }

        Material finish = XMaterial.FILLED_MAP.parseMaterial();
        if(null != finish){
            FINISH = new ItemStack(finish);
            ItemUtils.addItemFlags(FINISH);
            ItemUtils.setDisplayName(FINISH, "§f[§6§l已签§f]");
        }

    }

}
