package org.example.com.craftofthrones.events;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.example.com.craftofthrones.CraftOfThrones;

public class OnDrop implements Listener {
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (event.getItemDrop().getItemStack().getType() == Material.COMPASS) {
            event.setCancelled(true);
            event.getPlayer().sendMessage("§cVocê não pode dropar a bússola.");
        }
    }
}
