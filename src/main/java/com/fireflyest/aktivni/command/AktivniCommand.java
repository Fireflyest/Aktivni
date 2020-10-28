package com.fireflyest.aktivni.command;

import com.fireflyest.aktivni.core.AktivniPage;
import com.fireflyest.aktivni.data.Language;
import com.fireflyest.aktivni.util.YamlUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AktivniCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!cmd.getName().equalsIgnoreCase("aktivni") && !cmd.getName().equalsIgnoreCase("akt")) return true;
        Player player = (sender instanceof Player)? (Player)sender : null;
        if(args.length == 0) {
            if(player == null){
                return true;
            }
            player.openInventory(AktivniPage.getPage(player.getName()));
        }else if(args.length == 1){
            if(args[0].equalsIgnoreCase("help")) {
                for(String msg : Language.HELP){ sender.sendMessage(msg.replace("&", "§")); }
            }else if(args[0].equalsIgnoreCase("reload")){
                if(!sender.isOp())return true;
                sender.sendMessage(Language.RELOADING);
                YamlUtils.loadConfig();
                sender.sendMessage(Language.RELOADED);
            }else if(args[0].equalsIgnoreCase("default")){
                if(!sender.isOp())return true;
                YamlUtils.upDateConfig();
                sender.sendMessage(Language.RELOADING);
                YamlUtils.loadConfig();
                sender.sendMessage(Language.RELOADED);
            }else if(args[0].equalsIgnoreCase("test")){
                if(player == null) { sender.sendMessage(Language.PLAYER_COMMAND); return true; }

            }
        }else sender.sendMessage("正确用法§3" + cmd.getUsage());
        return true;
    }

    private void onCommand(Player player, String var1, String var2){

    }

    private void onCommand(Player player, String var){

    }

}
