package com.fireflyest.aktivni.core;

import com.fireflyest.aktivni.Aktivni;
import com.fireflyest.aktivni.bean.Reward;
import com.fireflyest.aktivni.bean.User;
import com.fireflyest.aktivni.data.DataManager;
import com.fireflyest.aktivni.data.Language;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AktivniReward {

    private AktivniReward(){
    }

    public static void commandReward(String name, String command){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("%player%", name));
    }

    public static void creditBuy(Player player, String reward){
        new BukkitRunnable() {
            @Override
            public void run() {
                DataManager.getCredit().forEach(c -> {
                    if(c.getName().equals(reward)){
                        String name = player.getName();
                        User user = DataManager.getUser(name);
                        if(user.getCredit() < c.getNeed()){
                            player.sendMessage(Language.NOT_ENOUGH_CREDIT);
                            return;
                        }

                        user.setCredit(user.getCredit() - c.getNeed());
                        player.sendMessage(Language.TITLE + c.getInfo());

                        commandReward(name, c.getCommand());
                    }
                });
                cancel();
            }
        }.runTask(Aktivni.getPlugin(Aktivni.class));

    }

    public static void signReward(Player player){
        String name = player.getName();
        User user = DataManager.getUser(name);
        DataManager.getSign().forEach(reward -> {
            if(reward.getType().equals("sign_total")){
                if(user.getTotal() > reward.getNeed() && user.getTotal_reward() < reward.getNeed()){
                    player.sendMessage(Language.TITLE + reward.getInfo());
                    commandReward(name, reward.getCommand());
                    user.setTotal_reward(reward.getNeed().intValue());
                }
            }else if(reward.getType().equals("sign_series")){
                if(user.getSeries() > reward.getNeed() && user.getSeries_reward() < reward.getNeed()){
                    player.sendMessage(Language.TITLE + reward.getInfo());
                    commandReward(name, reward.getCommand());
                    user.setSeries_reward(reward.getNeed().intValue());
                }
            }
        });
    }

    public static void playReward(Player player){
        String name = player.getName();
        User user = DataManager.getUser(name);
        DataManager.getPlaytime().forEach(reward -> {
            if(reward.getType().equals("playtime_total")){
                if(user.getPlayed() > reward.getNeed() && user.getPlayed_reward() < reward.getNeed()){
                    player.sendMessage(Language.TITLE + reward.getInfo());
                    commandReward(name, reward.getCommand());
                    user.setPlayed_reward(reward.getNeed());
                }
            }else if(reward.getType().equals("playtime_series")){
                if(user.getPlaying() > reward.getNeed() && user.getPlaying_reward() < reward.getNeed()){
                    player.sendMessage(Language.TITLE + reward.getInfo());
                    commandReward(name, reward.getCommand());
                    user.setPlaying_reward(reward.getNeed());
                }
            }
        });
    }

}
