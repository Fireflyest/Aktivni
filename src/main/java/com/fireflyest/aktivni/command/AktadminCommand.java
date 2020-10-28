package com.fireflyest.aktivni.command;

import com.fireflyest.aktivni.Aktivni;
import com.fireflyest.aktivni.bean.Festival;
import com.fireflyest.aktivni.bean.Reward;
import com.fireflyest.aktivni.bean.Task;
import com.fireflyest.aktivni.core.AktivniPage;
import com.fireflyest.aktivni.data.Config;
import com.fireflyest.aktivni.data.DataManager;
import com.fireflyest.aktivni.data.Language;
import com.fireflyest.aktivni.util.ChatUtils;
import com.fireflyest.aktivni.util.TimeUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AktadminCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!cmd.getName().equalsIgnoreCase("aktadmin") && !cmd.getName().equalsIgnoreCase("akt")) return true;
        Player player = (sender instanceof Player)? (Player)sender : null;
        if(args.length == 0) {

        }else if(args.length == 1){
            if(player == null) { sender.sendMessage(Language.PLAYER_COMMAND); return true; }
            this.onCommand(player, args[0]);
        }else if(args.length == 2){
            if(player == null) { sender.sendMessage(Language.PLAYER_COMMAND); return true; }
            this.onCommand(player, args[0], args[1]);
        }else if(args.length == 3){
            if(player == null) { sender.sendMessage(Language.PLAYER_COMMAND); return true; }
            this.onCommand(player, args[0], args[1], args[2]);
        }else sender.sendMessage("正确用法§3" + cmd.getUsage());
        return true;
    }

    /**
     * 添加一个自定义数据
     * @param player 玩家
     * @param var1 类型
     * @param var2 操作
     * @param var3 名称
     */
    private void onCommand(Player player, String var1, String var2, String var3){
        //手持物品
        String item = player.getInventory().getItemInMainHand().getType().name();
        //数据路径
        String folder;
        if(var1.equalsIgnoreCase("task") || var1.equalsIgnoreCase("festival")){
            folder = var1;
        }else {
            folder = "reward";
        }
        String url = Aktivni.getPlugin(Aktivni.class).getDataFolder().toString()+"\\"+folder+"\\"+var3+".yml";

        switch (var2){
            case "add":
                if("task".equalsIgnoreCase(var1)){

                }else if("festival".equalsIgnoreCase(var1)){
                    DataManager.dataDriver().insert(
                            new Festival(var3, TimeUtils.getMonth(), TimeUtils.getDay(), item, "/your reward command", "a reward description", "your reward info")
                    );
                }else {
                    DataManager.dataDriver().insert(
                            new Reward(var3, item, var1, 1L, "/your reward command", "a reward description", "your reward info")
                    );
                }
                this.openFile(url);
                player.sendMessage(String.format(Language.DATA_EDIT, var3));
                ChatUtils.sendCommandButton(player, var3, "GREEN", "点击将配置加载到服务器", String.format("/aktadmin %s load", var1));
                break;
            case "edit":
                this.openFile(url);
                player.sendMessage(String.format(Language.DATA_EDIT, var3));
                ChatUtils.sendCommandButton(player, var3, "GREEN", "点击将配置加载到服务器", String.format("/aktadmin %s load", var1));
                break;
            case "remove":
                if("task".equalsIgnoreCase(var1)){
                    DataManager.dataDriver().delete(Task.class, var3, "name");
                }else if("festival".equalsIgnoreCase(var1)){
                    DataManager.dataDriver().delete(Festival.class, var3, "name");
                }else {
                    DataManager.dataDriver().delete(Reward.class, var3, "name");
                }
                player.sendMessage(String.format(Language.DATA_REMOVE, var3));
                break;
            case "load":
                DataManager.loadReward(var1);
                player.sendMessage(Language.DATA_LOADED);
                break;
            default:
        }
    }

    private void onCommand(Player player, String var1, String var2){
        if("load".equalsIgnoreCase(var2)){
            DataManager.loadReward(var1);
            player.sendMessage(Language.DATA_LOADED);
        }
        if(var1.equalsIgnoreCase("credit")) {

        }else if(var1.equalsIgnoreCase("sign")){

        }else if(var1.equalsIgnoreCase("playtime")){

        }else if(var1.equalsIgnoreCase("task")){

        }
    }

    private void onCommand(Player player, String var){
        if(var.equalsIgnoreCase("credit")) {

        }else if(var.equalsIgnoreCase("sign")){

        }else if(var.equalsIgnoreCase("playtime")){

        }else if(var.equalsIgnoreCase("task")){

        }
    }

    private void openFile(String url){
        if(!Config.SQL){
            try {
                Desktop.getDesktop().open(new File(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
