package com.fireflyest.aktivni.util;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Fireflyest
 */
public class ItemUtils {

    private ItemUtils(){

    }

    /**
     * 设置物品名称
     * @param item 物品
     * @param name 名称
     */
    public static void setDisplayName(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            meta.setDisplayName(name.replace("&", "§"));
            item.setItemMeta(meta);
        }
    }

    /**
     * 设置皮革甲颜色
     * @param item 物品
     * @param color 颜色
     */
    public static void setColor(ItemStack item, Color color) {
        LeatherArmorMeta meta = (LeatherArmorMeta)item.getItemMeta();
        if(meta != null){
            meta.setColor(color);
            item.setItemMeta(meta);
        }
    }

    /**
     * 设置物品注释值
     * @param item 物品
     * @param line 行
     * @param value 值
     */
    public static void setItemValue(ItemStack item, int line, Object value) {
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            List<String> lores = meta.getLore();
            if(lores == null)return;
            String[] text = lores.get(line).split(" ");
            lores.set(line, text[0]+" "+value);
            meta.setLore(lores);
            item.setItemMeta(meta);
        }
    }

    /**
     * 获取值
     * @param lore 注释
     * @return String
     */
    public static String getItemValue(String lore) {
        String[] value = lore.split(" ");
        return value[1];
    }

    public static void addLore(ItemStack item, String lore){
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            List<String> lores = item.getItemMeta().getLore();
            if (lores == null) {
                lores = new ArrayList<>();
            }
            lores.add(lore);
            meta.setLore(lores);
            item.setItemMeta(meta);
        }
    }

    /**
     * 设置物品注释
     * @param item 物品
     * @param line 行
     * @param lore 注释
     */
    public static void setLore(ItemStack item, int line, String lore) {
        List<String> lores;
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            lores = meta.hasLore() ? meta.getLore() : new ArrayList<>();
            if(lores == null)return;
            if(line <= lores.size()-1){
                lores.set(line, lore);
            }else{
                int j = line-lores.size();
                for(int i = 0; i < j; i++){
                    lores.add("");
                }
                lores.add(lore);
            }
            meta.setLore(lores);
            item.setItemMeta(meta);
        }
    }

    /**
     * 自定义物品
     * @param item 物品
     * HIDE_ENCHANTS, 隐藏附魔
     * HIDE_ATTRIBUTES, 隐藏攻击属性
     * HIDE_UNBREAKABLE, 隐藏是否可损坏
     * HIDE_DESTROYS, 隐藏损坏值
     * HIDE_PLACED_ON, 隐藏是否可放置
     * HIDE_POTION_EFFECTS; 隐藏药水属性
     */
    public static void addItemFlags(ItemStack item){
        ItemMeta meta = item.getItemMeta();
        if(meta == null)return;
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);
    }

    /**
     * 自定义物品
     * @param material 材料类型
     * @return ItemStack
     * HIDE_ENCHANTS, 隐藏附魔
     * HIDE_ATTRIBUTES, 隐藏攻击属性
     * HIDE_UNBREAKABLE, 隐藏是否可损坏
     * HIDE_DESTROYS, 隐藏损坏值
     * HIDE_PLACED_ON, 隐藏是否可放置
     * HIDE_POTION_EFFECTS; 隐藏药水属性
     */
    public static ItemStack createCustomItem(Material material, ItemFlag... flags){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            meta.setUnbreakable(true);
            meta.addItemFlags(flags);
            item.setItemMeta(meta);
        }
        return item;
    }

    /**
     * 自定义药水
     * @param hide 是否隐藏药水属性
     * @param color 药水颜色
     * @return ItemStack
     */
    public static ItemStack createPotion(boolean hide, Color color){
        ItemStack item = new ItemStack(Material.POTION);
        ItemMeta meta = item.getItemMeta();
        if(meta != null){
            if(hide)meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            PotionMeta potion = (PotionMeta)meta;
            potion.setColor(color);
            item.setItemMeta(potion);
        }
        return item;
    }

    /**
     * 获取玩家头颅
     * @param player 玩家
     * @return ItemStack
     */
    public static ItemStack createSkull(OfflinePlayer player){
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        if(meta != null){
            meta.setOwningPlayer(player);
            item.setItemMeta(meta);
        }
        return item;
    }

}
