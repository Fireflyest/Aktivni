package com.fireflyest.aktivni.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class AktadminTab implements TabCompleter {

    private static List<String> command_1 = new ArrayList<>();
    private static List<String> command_2 = new ArrayList<>();

    public AktadminTab(){
        command_1.add("sign_total");
        command_1.add("sign_series");
        command_1.add("credit");
        command_1.add("playtime_total");
        command_1.add("playtime_series");
        command_1.add("task");
        command_1.add("festival");

        command_2.add("add");
        command_2.add("edit");
        command_2.add("remove");
        command_2.add("load");
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){
        if(command.getName().equalsIgnoreCase("aktadmin")){
            List<String> tab = new ArrayList<>();
            if(args.length == 1){
                for(String sub : command_1){
                    if(sub.contains(args[0]))tab.add(sub);
                }
            }else if(args.length == 2){
                for(String sub : command_2){
                    if(sub.contains(args[1]))tab.add(sub);
                }
            }else if(args.length == 3){
                if(!args[2].equalsIgnoreCase("load")) tab.add("[name]");
            }
            return tab;
        }
        return null;
    }

}
