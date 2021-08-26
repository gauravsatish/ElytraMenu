package com.chqppy.guicreation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Guicreation extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("PLUGIN ENABLED");

//        getCommand("menu").setExecutor(new MenuCommand(this));

        Bukkit.getPluginManager().registerEvents(new onInteractListener(this), this);
        Bukkit.getPluginManager().registerEvents(new menuListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        getCommand("menu").setExecutor(new MenuCommand(this));

    }

    // ELYTRA
    public HashMap<Player, ItemStack> chestSlot = new HashMap<>();

    // ELYTRA UI

    public void applyElytraUI(Player player) {

        // THE BEGINNING
        Inventory gui = Bukkit.createInventory(null, 45, ChatColor.BLACK + "ELYTRA MENU");

        // LORE
        List<String> enableLore = new ArrayList<>();
        enableLore.add(ChatColor.GRAY + "Click to receive");
        enableLore.add(ChatColor.DARK_PURPLE + "an Elytra");

        List<String> disableLore = new ArrayList<>();
        disableLore.add(ChatColor.GRAY + "Click to remove");
        disableLore.add(ChatColor.DARK_PURPLE + "your Elytra");

        List<String> launchLore = new ArrayList<>();
        launchLore.add(ChatColor.GRAY + "Click to be launched");
        launchLore.add(ChatColor.GRAY + "up into the sky by " + ChatColor.AQUA + 200 + ChatColor.GRAY + " blocks");

        // ITEMSTACKS
        ItemStack toggle;
        ItemStack launch = new ItemStack(Material.COBWEB);

        // Elytra
        ItemMeta toggleMeta;
        if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals(Material.ELYTRA)) {
            toggle = new ItemStack(Material.REDSTONE_BLOCK);

            toggleMeta = toggle.getItemMeta();
            toggleMeta.setDisplayName(ChatColor.RED + "Disable Elytra!");
            toggleMeta.setLore(disableLore);
        } else {
            toggle = new ItemStack(Material.EMERALD_BLOCK);

            toggleMeta = toggle.getItemMeta();
            toggleMeta.setDisplayName(ChatColor.GREEN + "Enable Elytra!");
            toggleMeta.setLore(disableLore);
        }
        toggle.setItemMeta(toggleMeta);

        // Launch
        ItemMeta launchMeta = launch.getItemMeta();
        launchMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Get launched to the " + ChatColor.WHITE + "moon");
        launchMeta.setLore(launchLore);
        launch.setItemMeta(launchMeta);

        // ITEM SETTING
        gui.setItem(20, toggle);
        gui.setItem(24, launch);

        // FINAL
        player.openInventory(gui);


    }
}
