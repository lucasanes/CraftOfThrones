package org.example.com.craftofthrones.functions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class getNearestPlayer {
    public static Player getNearestPlayer(Player player) {
        Player nearestPlayer = null;
        double nearestDistance = Double.MAX_VALUE;

        for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
            if (onlinePlayer.equals(player)) continue;

            double distance = player.getLocation().distance(onlinePlayer.getLocation());

            if (distance < nearestDistance) {
                nearestDistance = distance;
                nearestPlayer = onlinePlayer;
            }
        }

        if (nearestDistance <= 500) {
            return nearestPlayer;
        }

        return null;
    }
}
