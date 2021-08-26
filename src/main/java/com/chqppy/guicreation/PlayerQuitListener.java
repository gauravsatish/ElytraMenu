package com.chqppy.guicreation;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerQuitListener implements Listener {

    private Guicreation main;
    public PlayerQuitListener(Guicreation main) {
        this.main = main;
    }

    @EventHandler
    public void onQuitReturnChestplate(PlayerQuitEvent e) {

        Player player = e.getPlayer();

        if (player.getInventory().getChestplate() != null && player.getInventory().getChestplate().getType().equals(Material.ELYTRA) && player.getInventory().contains(Material.FIREWORK_ROCKET)) {
            player.getInventory().remove(Material.FIREWORK_ROCKET);
        }

        if (main.chestSlot.containsKey(player)) {
            player.getInventory().setChestplate(new ItemStack(main.chestSlot.get(player)));
        } else {
            player.getInventory().setChestplate(null);
        }

    }
}
