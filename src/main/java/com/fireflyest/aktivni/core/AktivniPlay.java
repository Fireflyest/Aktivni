package com.fireflyest.aktivni.core;

import com.fireflyest.aktivni.Aktivni;
import com.fireflyest.aktivni.bean.User;
import com.fireflyest.aktivni.data.DataManager;
import com.fireflyest.aktivni.util.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class AktivniPlay {

    private static int num = 0;

    private static List<String> onlineList = new ArrayList<>();

    private AktivniPlay(){
    }

    public static void playerJoin(String name){
        DataManager.getUser(name).setJoin(TimeUtils.getDate());
        onlineList.add(name);
    }

    public static void playerQuit(String name){
        User user = DataManager.getUser(name);
        user.setPlayed(user.getPlayed() + user.getPlaying());
        user.setJoin(0L);
    }

    public static void startPlaytime(){
        new BukkitRunnable() {
            @Override
            public void run() {
                onlineList.forEach(name -> {
                    User user = DataManager.getUser(name);
                    user.setPlaying(TimeUtils.getDate() - user.getJoin());
                    user.setCredit(user.getCredit() + 0.002);
                    if(AktivniPage.getPage(name).getViewers().size() > 0) {
                        AktivniPage.updatePlaytime(name);
                        AktivniPage.updateCredit(name);
                    }
                    if(num == 10){
                        Player player = Bukkit.getPlayer(user.getUuid());
                        if(player != null) AktivniReward.playReward(player);
                    }
                });
                num = num == 8 ? 0 : num++;
            }
        }.runTaskTimer(Aktivni.getPlugin(Aktivni.class), 60, 20);
    }

    public static List<String> getOnlineList() {
        return onlineList;
    }

    public static void removePlayer(String name){
        onlineList.remove(name);
    }

}
