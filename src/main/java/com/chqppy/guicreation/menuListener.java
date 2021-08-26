package com.chqppy.guicreation;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class menuListener implements Listener {

    private Guicreation main;

    public menuListener(Guicreation main) {
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.BLACK + "ELYTRA MENU")) {
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);

                switch (e.getCurrentItem().getType()) {
                    case COBWEB:
                        player.setVelocity(new Vector(0, 200, 0));
                        Bukkit.getScheduler().runTaskLaterAsynchronously(main, new Runnable() {
                            @Override
                            public void run() {
                                player.setGliding(true);
                            }
                        }, 20L);


                        player.sendMessage(ChatColor.GREEN + "You were launched into the air! wheeeeeeeeeee");
                        break;
                    case EMERALD_BLOCK:
                        if (player.getInventory().getChestplate() != null) {
                            main.chestSlot.put(player, player.getInventory().getChestplate());
                        }

                        player.getInventory().setChestplate(new ItemStack(Material.ELYTRA));
                        player.getInventory().addItem(new ItemStack(Material.FIREWORK_ROCKET, 128));

                        player.sendMessage(ChatColor.GREEN + "Equipped Elytra!");

                        break;
                    case REDSTONE_BLOCK:
                        if (main.chestSlot.containsKey(player)) {
                            player.getInventory().setChestplate(new ItemStack(main.chestSlot.get(player)));
                        } else {
                            player.getInventory().setChestplate(null);
                        }

                        if (player.getInventory().contains(Material.FIREWORK_ROCKET)) {
                            player.getInventory().remove(Material.FIREWORK_ROCKET);
                        }

                        player.sendMessage("Unequipped Elytra");

                        break;
                    default:
                        return;
                }
            }

            player.closeInventory();
        }

//        if (e.getView().getTitle().contains("ELYTRA MENU")) {} (This is easier but has less security as in any chest with the name ELYTRA MENU will be disabled)
    }
}
