package com.chqppy.guicreation;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class onInteractListener implements Listener {

    private Guicreation main;

    public onInteractListener(Guicreation main) {
        this.main= main;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (e.getPlayer().getInventory().getItemInMainHand() != null && e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.COMPASS)) {

                main.applyElytraUI(e.getPlayer());

            }
        }
    }

}
