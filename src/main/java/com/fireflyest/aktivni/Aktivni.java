package com.fireflyest.aktivni;

import com.fireflyest.aktivni.command.AktadminCommand;
import com.fireflyest.aktivni.command.AktadminTab;
import com.fireflyest.aktivni.command.AktivniCommand;
import com.fireflyest.aktivni.command.AktivniTab;
import com.fireflyest.aktivni.core.AktivniItem;
import com.fireflyest.aktivni.core.AktivniPlay;
import com.fireflyest.aktivni.data.DataManager;
import com.fireflyest.aktivni.listener.PlayerEventListener;
import com.fireflyest.aktivni.util.YamlUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.annotation.command.Command;
import org.bukkit.plugin.java.annotation.plugin.ApiVersion;
import org.bukkit.plugin.java.annotation.plugin.Plugin;
import org.bukkit.plugin.java.annotation.plugin.author.Author;

@Plugin(name="Aktivni", version="1.0.0")
@Author(value = "Fireflyest")
@Command(name = "aktivni", usage = "/aktivni <reload|help|default>")
@Command(name = "akt")
@Command(name = "aktadmin", permission = "aktivni.admin", permissionMessage = "Place use /aktivni open the page")
@ApiVersion(ApiVersion.Target.v1_15)

public class Aktivni extends JavaPlugin {

    @Override
    public void onEnable() {
        YamlUtils.iniYamlManager(this);

        DataManager.loadData();

        AktivniItem.getInstance().init();

        AktivniPlay.startPlaytime();

        if(Bukkit.getOnlinePlayers().size() > 0){
            Bukkit.getOnlinePlayers().forEach(player -> AktivniPlay.playerJoin(player.getName()));
        }

        this.getServer().getPluginManager().registerEvents(new PlayerEventListener(), this );

        PluginCommand aktivni = this.getCommand("aktivni");
        PluginCommand akt = this.getCommand("akt");
        if(null != aktivni && null != akt){
            aktivni.setExecutor(new AktivniCommand());
            aktivni.setTabCompleter(new AktivniTab());
            akt.setExecutor(new AktivniCommand());
            akt.setTabCompleter(new AktivniTab());
        }
        PluginCommand aktadmin = this.getCommand("aktadmin");
        if(null != aktadmin){
            aktadmin.setExecutor(new AktadminCommand());
            aktadmin.setTabCompleter(new AktadminTab());
        }
    }

    @Override
    public void onDisable() {
        AktivniPlay.getOnlineList().forEach(AktivniPlay::playerQuit);
        DataManager.saveData();
    }

}
