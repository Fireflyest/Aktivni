package com.fireflyest.aktivni.listener;

import com.fireflyest.aktivni.bean.User;
import com.fireflyest.aktivni.core.AktivniPage;
import com.fireflyest.aktivni.core.AktivniPlay;
import com.fireflyest.aktivni.core.AktivniReward;
import com.fireflyest.aktivni.core.AktivniSign;
import com.fireflyest.aktivni.data.DataManager;
import com.fireflyest.aktivni.data.Language;
import com.fireflyest.aktivni.util.TimeUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class PlayerEventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String name = event.getPlayer().getName();
        User user = DataManager.getUser(name);
        user.setOnline(true);
        if("".equals(user.getUuid())) user.setUuid(event.getPlayer().getUniqueId().toString());
        AktivniPage.updatePage(name);
        AktivniPlay.playerJoin(name);

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        String name = event.getPlayer().getName();
        DataManager.getUser(name).setOnline(false);
        AktivniPlay.playerQuit(name);
        AktivniPlay.removePlayer(name);
        AktivniPage.removePage(name);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if(!event.getView().getTitle().contains(Language.PLUGIN_NAME)) return;
        event.setCancelled(true);
        if(event.getCurrentItem() == null) return;
        ItemStack item = event.getCurrentItem();
        Player player = (Player)event.getWhoClicked();
        ItemMeta meta = item.getItemMeta();
        String type = "";
        if(meta != null && meta.getLore() != null)type = meta.getLore().get(0).substring(5);
        switch (type){
            case "credit":
                player.closeInventory();
                player.openInventory(AktivniPage.getCreditPage());
                return;
            case "sign":
                player.closeInventory();
                player.openInventory(AktivniPage.getSignPage());
                return;
            case "playtime":
                player.closeInventory();
                player.openInventory(AktivniPage.getPlaytimePage());
                return;
            case "task":
                player.closeInventory();
                player.openInventory(AktivniPage.getTaskPage());
                return;
            case "close":
                player.closeInventory();
                return;
            case "back":
                player.closeInventory();
                player.openInventory(AktivniPage.getPage(player.getName()));
                return;
            case "refresh":
                AktivniPage.updateCalendar(player.getName());
                return;
            case "buy":
                AktivniReward.creditBuy(player, meta.getDisplayName().substring(2));
                return;
            default:
        }

        Map<Integer, Integer> place = DataManager.getPlace(TimeUtils.getMonth());
        String finalType = type;
        place.forEach((d, p) -> {
            if(p != event.getRawSlot()) return;
            AktivniSign.playerSign(player, finalType, d);
        });
    }

}
