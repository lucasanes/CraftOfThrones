package org.example.com.craftofthrones.functions;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.example.com.craftofthrones.CraftOfThrones;

import java.text.SimpleDateFormat;
import java.util.Date;

public class giveDailyXP {

    public static void giveDailyXP(Player player, FileConfiguration config) {
        String playerPath = "players." + player.getUniqueId().toString();
        String lastLoginDate = config.getString(playerPath, "");

        String todayDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        if (!lastLoginDate.equals(todayDate)) {
            config.set(playerPath, todayDate);

            // Dar XP ao jogador
            player.giveExpLevels(20);
            player.sendMessage("§aVocê recebeu 20 XP Levels por entrar hoje!");
        }
    }
}
