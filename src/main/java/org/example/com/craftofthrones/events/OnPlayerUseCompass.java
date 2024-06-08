package org.example.com.craftofthrones.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import static org.example.com.craftofthrones.functions.cooldown.*;
import static org.example.com.craftofthrones.functions.getNearestPlayer.getNearestPlayer;
import static org.example.com.craftofthrones.functions.startTracking.startTracking;

public class OnPlayerUseCompass implements Listener {
    @EventHandler
    public static void onPlayerUseCompass(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (player.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
                if (isInCooldown(player)) {
                    String timeLeft = getCooldownTimeLeft(player);
                    player.sendMessage("§cVocê precisa esperar " + timeLeft + " antes de usar a bússola novamente.");
                    return;
                }

                Player nearestPlayer = getNearestPlayer(player);

                if (nearestPlayer != null) {
                    player.sendMessage("§aRastreando jogador mais próximo.");
                    nearestPlayer.sendMessage("§4Você está sendo rastreado.");
                    setCooldown(player);
                    startTracking(player, nearestPlayer);
                } else {
                    player.sendMessage("§cNenhum jogador próximo encontrado.");
                }
            }
        }
    }
}
