package org.example.com.craftofthrones.events;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OnDeath implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        e.getDrops().removeIf(next -> (next.getType() == Material.COMPASS));
    }
}