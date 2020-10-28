package com.fireflyest.aktivni.core;

import com.cryptomorin.xseries.XMaterial;
import com.fireflyest.aktivni.Aktivni;
import com.fireflyest.aktivni.bean.Festival;
import com.fireflyest.aktivni.bean.Sign;
import com.fireflyest.aktivni.bean.User;
import com.fireflyest.aktivni.data.Config;
import com.fireflyest.aktivni.data.DataManager;
import com.fireflyest.aktivni.data.Language;
import com.fireflyest.aktivni.util.ConvertUtils;
import com.fireflyest.aktivni.util.ItemUtils;
import com.fireflyest.aktivni.util.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class AktivniPage {

    private AktivniPage(){
    }

    private static String[] week = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    private static Map<String, Inventory> mainPageMap = new HashMap<>();

    private static Map<String, Inventory> taskPageMap = new HashMap<>();

    private static Inventory playtimePage = Bukkit.createInventory(null, 45, Language.PLUGIN_NAME + Language.PLAYTIME_PAGE);

    private static Inventory creditPage = Bukkit.createInventory(null, 45, Language.PLUGIN_NAME + Language.CREDIT_PAGE);

    private static Inventory signPage = Bukkit.createInventory(null, 45, Language.PLUGIN_NAME + Language.SIGN_PAGE);

    private static Inventory taskPage = Bukkit.createInventory(null, 45, Language.PLUGIN_NAME + Language.TASK_PAGE);

    private static Inventory blankPage = Bukkit.createInventory(null, 9, Language.DATA_EMPTY);

    public static void updatePage(String name){
        Inventory page = mainPageMap.getOrDefault(name, getNewPage(name));
        if(!mainPageMap.containsKey(name)) mainPageMap.put(name, page);
        new BukkitRunnable() {
            @Override
            public void run() {
                updatePlayerData(page, name);

                updateCalendar(page, name);

                cancel();
            }
        }.runTask(Aktivni.getPlugin(Aktivni.class));
    }

    public static void updateCalendar(String name){
        new BukkitRunnable() {
            @Override
            public void run() {
                Inventory page = mainPageMap.getOrDefault(name, getNewPage(name));
                if(!mainPageMap.containsKey(name)) mainPageMap.put(name, page);
                updateCalendar(page, name);
                cancel();
            }
        }.runTask(Aktivni.getPlugin(Aktivni.class));
    }

    public static void updateCredit(String name){
        new BukkitRunnable() {
            @Override
            public void run() {
                Inventory page = mainPageMap.getOrDefault(name, getNewPage(name));
                if(!mainPageMap.containsKey(name)) mainPageMap.put(name, page);
                updateCredit(page, name);
                cancel();
            }
        }.runTask(Aktivni.getPlugin(Aktivni.class));
    }

    public static void updatePlaytime(String name){
        new BukkitRunnable() {
            @Override
            public void run() {
                Inventory page = mainPageMap.getOrDefault(name, getNewPage(name));
                if(!mainPageMap.containsKey(name)) mainPageMap.put(name, page);
                updatePlaytime(page, name);
                cancel();
            }
        }.runTask(Aktivni.getPlugin(Aktivni.class));
    }

    public static void updateTask(String name){
        new BukkitRunnable() {
            @Override
            public void run() {
                Inventory page = mainPageMap.getOrDefault(name, getNewPage(name));
                if(!mainPageMap.containsKey(name)) mainPageMap.put(name, page);
                updateTask(page, name);
                cancel();
            }
        }.runTask(Aktivni.getPlugin(Aktivni.class));
    }

    public static void updateSign(String name){
        new BukkitRunnable() {
            @Override
            public void run() {
                Inventory page = mainPageMap.getOrDefault(name, getNewPage(name));
                if(!mainPageMap.containsKey(name)) mainPageMap.put(name, page);
                updateSign(page, name);
                cancel();
            }
        }.runTask(Aktivni.getPlugin(Aktivni.class));
    }

    public static Inventory getPage(String name) {
        if(!mainPageMap.containsKey(name))updatePage(name);
        return mainPageMap.getOrDefault(name, blankPage);
    }

    public static Inventory getPlaytimePage(){
        return playtimePage;
    }

    public static Inventory getCreditPage() {
        return creditPage;
    }

    public static Inventory getTaskPage() {
        return taskPage;
    }

    public static Inventory getSignPage() {
        return signPage;
    }

    public static void updateSubPage(String type){
        new BukkitRunnable() {
            @Override
            public void run() {
                switch (type){
                    case "all":
                        updateCreditSubPage();
                        updateSignSubPage();
                        updatePlaytimeSubPage();
                        break;
                    case "credit":
                        updateCreditSubPage();
                        break;
                    case "sign_total":
                    case "sign_series":
                        updateSignSubPage();
                        break;
                    case "playtime_total":
                    case "playtime_series":
                        updatePlaytimeSubPage();
                        break;
                    case "task":
                        if(null == taskPage.getItem(44)){
                            for(int i = 36; i < 44; i++) taskPage.setItem(i, AktivniItem.BLANK.clone());
                            taskPage.setItem(44, AktivniItem.BACK.clone());
                        }
                        break;
                    default:
                }
                cancel();
            }
        }.runTask(Aktivni.getPlugin(Aktivni.class));
    }

    /**
     * 更新积分子界面
     */
    private static void updateCreditSubPage(){

        creditPage.clear();
        for(int i = 36; i < 44; i++) creditPage.setItem(i, AktivniItem.BLANK.clone());
        creditPage.setItem(44, AktivniItem.BACK.clone());
        DataManager.getCredit().forEach(c -> {
            if(XMaterial.matchXMaterial(c.getItem()).isPresent()){
                Material material = XMaterial.matchXMaterial(c.getItem()).get().parseMaterial();
                ItemStack item = material == null ? new ItemStack(Material.AIR): new ItemStack(material);
                ItemUtils.setDisplayName(item, "§e" + c.getName());
                ItemUtils.addLore(item, "§6@§9buy");
                ItemUtils.addLore(item, "§3兑换类型§7:§f 积分");
                ItemUtils.addLore(item, "§3消耗数量§7:§f " + c.getNeed());
                ItemUtils.addLore(item, "§f§m·                         ·");
                List<String> lore = new ArrayList<>(Arrays.asList(c.getDesc().replace("&", "§").split(";")));
                lore.forEach(l->ItemUtils.addLore(item, l));
                creditPage.addItem(item);
            }
        });
    }

    /**
     * 更新签到子界面
     */
    private static void updateSignSubPage(){
        signPage.clear();
        for(int i = 36; i < 44; i++) signPage.setItem(i, AktivniItem.BLANK.clone());
        signPage.setItem(44, AktivniItem.BACK.clone());
        DataManager.getSign().forEach(s -> {
            if(XMaterial.matchXMaterial(s.getItem()).isPresent()){
                Material material = XMaterial.matchXMaterial(s.getItem()).get().parseMaterial();
                ItemStack item = material == null ? new ItemStack(Material.AIR): new ItemStack(material);
                ItemUtils.setDisplayName(item, "§e" + s.getName());
                ItemUtils.addLore(item, "§3奖励类型§7:§f " + (s.getType().contains("total") ? "总共签到":"连续签到"));
                ItemUtils.addLore(item, "§3所需次数§7:§f " + s.getNeed());
                ItemUtils.addLore(item, "§f§m·                         ·");
                List<String> lore = new ArrayList<>(Arrays.asList(s.getDesc().replace("&", "§").split(";")));
                lore.forEach(l->ItemUtils.addLore(item, l));
                if(material != null) signPage.addItem(item);
            }
        });
    }

    /**
     * 更新在线子界面
     */
    private static void updatePlaytimeSubPage(){
        playtimePage.clear();
        for(int i = 36; i < 44; i++) playtimePage.setItem(i, AktivniItem.BLANK.clone());
        playtimePage.setItem(44, AktivniItem.BACK.clone());
        DataManager.getPlaytime().forEach(p -> {
            if(XMaterial.matchXMaterial(p.getItem()).isPresent()){
                Material material = XMaterial.matchXMaterial(p.getItem()).get().parseMaterial();
                ItemStack item = material == null ? new ItemStack(Material.AIR): new ItemStack(material);
                ItemUtils.setDisplayName(item, "§e" + p.getName());
                ItemUtils.addLore(item, "§3奖励类型§7:§f " + (p.getType().contains("total") ? "总共在线":"连续在线"));
                ItemUtils.addLore(item, "§3所需时长§7:§f " + p.getNeed());
                ItemUtils.addLore(item, "§f§m·                         ·");
                List<String> lore = new ArrayList<>(Arrays.asList(p.getDesc().replace("&", "§").split(";")));
                lore.forEach(l->ItemUtils.addLore(item, l));
                if(material != null) playtimePage.addItem(item);
            }
        });
    }

    private static void updatePlayerData(Inventory page, String name){
        updateCredit(page, name);

        updateSign(page, name);

        updatePlaytime(page, name);

        updateTask(page, name);
    }

    private static void updateCredit(Inventory page, String name){
        User user = DataManager.getUser(name);

        ItemStack credit;
        if(Config.DISPLAY_SKIN){
            credit = ItemUtils.createSkull(Bukkit.getOfflinePlayer(UUID.fromString(user.getUuid())));
            ItemUtils.addLore(credit, "§6@§9credit");
            ItemUtils.setDisplayName(credit, "§e§l活跃信息");
        }else {
            credit = AktivniItem.CREDIT.clone();
        }
        String c = String.valueOf(user.getCredit());
        int dot = c.indexOf(".");
        if(c.length() - dot > 3) {
            ItemUtils.addLore(credit, "§3活跃积分§7:§f "+ c.substring(0, dot+3));
        }else {
            ItemUtils.addLore(credit, "§3活跃积分§7:§f "+ c.substring(0, dot+c.length() - dot));
        }

        page.setItem(8, credit);
    }

    private static void updatePlaytime(Inventory page, String name){
        User user = DataManager.getUser(name);

        ItemStack playtime = AktivniItem.PLAYTIME.clone();
        ItemUtils.addLore(playtime, "§3连续在线§7:§f "+ ConvertUtils.convertTime(user.getPlaying()));
        ItemUtils.addLore(playtime, "§3总共在线§7:§f "+ ConvertUtils.convertTime(user.getPlayed()+user.getPlaying()));
        page.setItem(26, playtime);
    }

    private static void updateTask(Inventory page, String name){
        User user = DataManager.getUser(name);

        ItemStack task = AktivniItem.TASK.clone();
        ItemUtils.addLore(task, "§3接受任务§7:§f "+user.getTask());
        ItemUtils.addLore(task, "§3完成任务§7:§f "+user.getFinish());
        page.setItem(35, task);
    }

    private static void updateSign(Inventory page, String name){
        User user = DataManager.getUser(name);

        ItemStack sign = AktivniItem.SIGN.clone();
        ItemUtils.addLore(sign, "§3补签机会§7:§f "+user.getChance());
        ItemUtils.addLore(sign, "§3连续签到§7:§f "+user.getSeries());
        ItemUtils.addLore(sign, "§3总共签到§7:§f "+user.getTotal());
        page.setItem(17, sign);
    }

    /**
     * 刷新日历物品
     * @param page 页面
     * @param name 玩家名称
     */
    private static void updateCalendar(Inventory page, String name){
        int month = TimeUtils.getMonth(), day = TimeUtils.getDay();

        List<Sign> signList = DataManager.getSignList(name);
        List<Festival> festivalList = DataManager.getFestivals();
        Map<Integer, Integer> place = DataManager.getPlace(month);
        //放置已签
        signList.forEach(s -> {
            ItemStack item = AktivniItem.FINISH.clone();
            ItemUtils.addLore(item, "§6@§9finish");
            ItemUtils.addLore(item, "§f§m·                         ·");
            ItemUtils.addLore(item, "§3§l"+week[place.get(s.getDay())%9]+ "   §3"+TimeUtils.getYear()+"§7/§3"+month+"§7/§3"+s.getDay());
            page.setItem(place.get(s.getDay()), item);
        });
        //节日
        festivalList.forEach(f -> {
            Material material = XMaterial.valueOf(f.getItem()).parseMaterial();
            if(material != null){
                int p = place.get(f.getDay());
                if(page.getItem(p) != null)return;
                ItemStack item = new ItemStack(material);
                ItemUtils.setDisplayName(item, String.format("§f[§e§l%s§f]", f.getName()));
                ItemUtils.addLore(item, "§6@§9festival");
                ItemUtils.addLore(item, "§f§m·                         ·");
                List<String> lore = new ArrayList<>(Arrays.asList(f.getDesc().replace("&", "§").split(";")));
                lore.forEach(l->ItemUtils.addLore(item, l));
                page.setItem(p, item);
            }
        });
        //放置未签
        place.forEach((d, p) -> {
            if(page.getItem(p) != null)return;
            ItemStack item;
            if(d < day){
                item = AktivniItem.MISS.clone();
                ItemUtils.addLore(item, "§6@§9miss");
            }else if(d > day){
                item = AktivniItem.FUTURE.clone();
                ItemUtils.addLore(item, "§6@§9future");
            }else {
                item = AktivniItem.TODAY.clone();
                ItemUtils.addLore(item, "§6@§9today");
            }
            ItemUtils.addLore(item, "§f§m·                         ·");
            ItemUtils.addLore(item, "§3§l"+week[p%9]+ "   §3"+TimeUtils.getYear()+"§7/§3"+month+"§7/§3"+d);
            page.setItem(p, item);
        });
    }

    private static Inventory getNewPage(String name){
        Inventory inventory = Bukkit.createInventory(null, 54, Language.PLUGIN_NAME+"§9"+name);
        for(int i = 7 ; i <= 53 ; i+=9) { inventory.setItem(i, AktivniItem.BLANK.clone()); }
        inventory.setItem(44, AktivniItem.BLANK.clone());
        inventory.setItem(53, AktivniItem.CLOSE.clone());
        return inventory;
    }

    public static Inventory getNewTaskPage(){
        Inventory inventory = Bukkit.createInventory(null, 54, Language.PLUGIN_NAME + Language.TASK_PAGE);

        return inventory;
    }

    public static void removePage(String name){
        mainPageMap.remove(name);
    }

}
