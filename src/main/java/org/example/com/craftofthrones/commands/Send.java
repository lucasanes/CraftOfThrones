package org.example.com.craftofthrones.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Send implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player plr = (Player) sender;
            if (args.length > 0) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (p.getPlayerListName().equals(args[0])) {
                        ItemStack item = plr.getInventory().getItemInMainHand();

                        plr.getInventory().removeItem(item);
                        plr.sendMessage(ChatColor.GREEN + "Item enviado com sucesso.");

                        p.getInventory().addItem(item);
                        p.sendMessage(ChatColor.GREEN + "Você recebeu " + item.getAmount() + " " + item.getType() + " de " + plr.getName() + ".");
                        return true;
                    }
                }
                plr.sendMessage(ChatColor.RED + "Este player não está online.");
                return true;
            } else {
                plr.sendMessage(ChatColor.RED + "Você não mencionou nenhum Player.");
                return false;
            }
        }
        return false;
    }

}
