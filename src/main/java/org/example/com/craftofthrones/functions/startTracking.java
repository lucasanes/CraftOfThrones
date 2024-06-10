package org.example.com.craftofthrones.functions;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.com.craftofthrones.CraftOfThrones;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.example.com.craftofthrones.functions.cooldown.*;

public class startTracking {

    private static final Map<UUID, UUID> trackingMap = new HashMap<>();
    private static final long UPDATE_INTERVAL = 20L; // Atualização a cada 1 segundo (20 ticks)

    public static void startTracking(Player tracker, Player target) {
        UUID trackerId = tracker.getUniqueId();
        UUID targetId = target.getUniqueId();
        trackingMap.put(trackerId, targetId);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!tracker.isOnline() || !target.isOnline() || !trackingMap.containsKey(trackerId)) {
                    trackingMap.remove(trackerId);
                    cancel();
                    return;
                }

                long lastUse = cooldowns.get(tracker.getUniqueId());
                if ((System.currentTimeMillis() - lastUse) >= USE_TIME) {
                    tracker.sendMessage("§4O tempo de rastreamento expirou.");
                    trackingMap.remove(trackerId);
                    Location spawnLocation = new Location(tracker.getWorld(), 0, 70, 0);
                    tracker.setCompassTarget(spawnLocation);
                    cancel();
                    return;
                }

                Location targetLocation = target.getLocation();
                tracker.setCompassTarget(targetLocation);
            }
        }.runTaskTimer(CraftOfThrones.plugin, 0L, UPDATE_INTERVAL);
    }

}
