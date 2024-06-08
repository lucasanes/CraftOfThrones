package org.example.com.craftofthrones.functions;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class cooldown {

    public static final Map<UUID, Long> cooldowns = new HashMap<>();
    public static final long COOLDOWN_TIME = 180000;

    public static boolean isInCooldown(Player player) {
        if (!cooldowns.containsKey(player.getUniqueId())) {
            return false;
        }
        long lastUse = cooldowns.get(player.getUniqueId());
        return (System.currentTimeMillis() - lastUse) < COOLDOWN_TIME;
    }

    public static String getCooldownTimeLeft(Player player) {
        long lastUse = cooldowns.get(player.getUniqueId());
        long timeLeft = COOLDOWN_TIME - (System.currentTimeMillis() - lastUse);

        long minutes = TimeUnit.MILLISECONDS.toMinutes(timeLeft);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timeLeft) - TimeUnit.MINUTES.toSeconds(minutes);

        return String.format("%d minutos e %d segundos", minutes, seconds);
    }

    public static void setCooldown(Player player) {
        cooldowns.put(player.getUniqueId(), System.currentTimeMillis());
    }
}
