package com.fireflyest.aktivni.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

/**
 * @author Fireflyest
 */
public class BoardUtils {

    private static ScoreboardManager manager = Bukkit.getScoreboardManager();

    private BoardUtils(){

    }

    /**
     * 给玩家添加一个积分榜
     * @param player 玩家
     * @param title 积分榜标题
     */
    public static void addScoreboard(Player player, String title) {
        Objective obj = manager.getNewScoreboard().registerNewObjective(player.getName(), "", title);
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Scoreboard scoreboard = obj.getScoreboard();
        if(scoreboard != null) player.setScoreboard(obj.getScoreboard());
    }

    /**
     * 设置玩家积分榜标题
     * @param player 玩家
     * @param title 积分榜标题
     */
    public static void setBoardTitle(Player player, String title) {
        Objective obj = player.getScoreboard().getObjective(player.getName());
        if(obj != null) obj.setDisplayName(title);
    }

    /**
     * 设置玩家积分榜某一条目
     * @param player 玩家
     * @param key 键
     * @param score 值
     */
    public static void setScoreboardValue(Player player, String key, int score) {
        Objective obj = player.getScoreboard().getObjective(player.getName());
        player.getScoreboard().resetScores(key);
        if(obj != null) obj.getScore(key).setScore(score);
    }

    /**
     * 删除玩家积分榜某一条目
     * @param player 玩家
     * @param key 键
     */
    public static void removeScoreboardValue(Player player, String key){
        player.getScoreboard().resetScores(key);
    }

    /**
     * 清理一个玩家的积分榜
     * @param player 玩家
     */
    public static void clearScoreboard(Player player) {
        player.setScoreboard(manager.getNewScoreboard());
    }



}
