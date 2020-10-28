package com.fireflyest.aktivni.core;

import com.fireflyest.aktivni.bean.Festival;
import com.fireflyest.aktivni.bean.User;
import com.fireflyest.aktivni.data.Config;
import com.fireflyest.aktivni.data.DataManager;
import com.fireflyest.aktivni.data.Language;
import com.fireflyest.aktivni.util.TimeUtils;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class AktivniSign {

    private AktivniSign(){
    }

    public static void playerSign(Player player, String type, int day){
        switch (type){
            case "future":
                player.sendMessage(Language.SIGN_IN_FUTURE);
                break;
            case "today":
                signIn(player, day, false);
                break;
            case "miss":
                signIn(player, day, true);
                break;
            case "festival":
                signFestival(player, day);
                break;
            case "":
            default:
        }
    }

    /**
     * 签到
     * @param player 玩家
     * @param day 日期
     * @param remedy 是否补签
     */
    private static void signIn(Player player, int day, boolean remedy){
        String name = player.getName();
        User user = DataManager.getUser(name);
        //补签次数
        if(remedy && user.getChance() < 1){
            player.sendMessage(Language.NOT_ENOUGH_CHANCE);
            return;
        }else if(remedy){
            user.setChance(user.getChance() - 1);
        }

        //计算连续签到
        user.setTotal(user.getTotal() + 1);
        if(!remedy){
            if(day == 1 || user.getLast() != day - 1){
                user.setSeries(1);
            }else {
                user.setSeries(user.getSeries() + 1);
            }
            user.setLast(day);
        }

        //获得活跃积分
        user.setCredit(user.getCredit()+10);

        //奖励
        int random = new Random().nextInt(Config.REWARDS.size()-1);
        AktivniReward.commandReward(name, Config.REWARDS.get(random));
        player.sendMessage(random == 0 ? Language.UNLUCK_TODAY : Language.SIGN_IN_SUCCESS);

        //数据处理、菜单刷新
        DataManager.addSign(name, day, true);
        AktivniPage.updateCalendar(name);
        AktivniPage.updateSign(name);
        AktivniPage.updateCredit(name);

        AktivniReward.signReward(player);

    }

    /**
     * 节日签到
     * @param player 玩家
     * @param day 日期
     */
    private static void signFestival(Player player, int day){
        if(day != TimeUtils.getDay()){
            player.sendMessage(Language.FAULT_SIGN_IN);
            return;
        }

        String name = player.getName();
        User user = DataManager.getUser(name);

        //获得活跃积分
        user.setCredit(user.getCredit()+10);

        //节日奖励
        List<Festival> festivalList = DataManager.getFestivals();
        festivalList.forEach(f -> {
            if(f.getDay() != day)return;
            AktivniReward.commandReward(name, f.getCommand());
            player.sendMessage(String.format(Language.SIGN_IN_FESTIVAL, f.getName()));
            player.sendMessage(Language.TITLE + f.getInfo());
        });

        signIn(player, day, false);

    }

}
