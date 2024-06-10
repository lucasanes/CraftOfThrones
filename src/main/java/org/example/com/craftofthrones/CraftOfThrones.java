package org.example.com.craftofthrones;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.com.craftofthrones.commands.Send;
import org.example.com.craftofthrones.events.*;

import java.util.ArrayList;

import static org.example.com.craftofthrones.functions.giveDailyXP.giveDailyXP;

public final class CraftOfThrones extends JavaPlugin implements Listener {

    public static CraftOfThrones plugin;

    private FileConfiguration config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new OnPlayerUseCompass(), this);
        getServer().getPluginManager().registerEvents(new OnDrop(), this);
        getServer().getPluginManager().registerEvents(new OnDeath(), this);

        getCommand("enviar").setExecutor(new Send());

        getLogger().info("Craft of Thrones iniciou com sucesso!");

        config = this.getConfig();
        config.options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("Craft of Thrones desligou com sucesso!");
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        ensureSingleCompass(player);
        giveDailyXP(player, config);
        saveConfig();
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        ensureSingleCompass(player);
    }

    private void ensureSingleCompass(Player player) {
        player.getInventory().remove(Material.COMPASS);
        player.getInventory().addItem(new ItemStack(Material.COMPASS));
    }
}
