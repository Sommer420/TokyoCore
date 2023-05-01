package com.sommer.tokyocore.admin;

import com.sommer.tokyocore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.System;

import static com.sommer.tokyocore.Main.mainConfig;

public class CoreCommand extends JavaPlugin implements CommandExecutor {
    public CoreCommand(Main plugin){
        plugin.getCommand("TokyoCore").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("tokyocore")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                String pf = Main.instance.getString("config", "prefixfejl");
                String p = Main.getInstance().getString("config", "prefix");
                if (player.hasPermission(Main.instance.getString("config", "adminperm"))) {
                    if (args.length == 0) {
                        player.sendMessage("§aTokyoCore§f version §a" + "x" + "§f, udviklet af §aSommer");
                        player.sendMessage("§fTilgængelige kommandoer:");
                        player.sendMessage("§a/TokyoCore §f...");
                        player.sendMessage("§8» §freload");
                        player.sendMessage("§8» §finfo");
                    } else if (args[0].equalsIgnoreCase("reload")) {
                        try {
                            long tidBefore = System.currentTimeMillis();
                            mainConfig.reloadConfig();
                            player.sendMessage(p + "§fDu reloadede configen! §7(" + (System.currentTimeMillis() - tidBefore) + "ms)");
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    } else if (args[0].equalsIgnoreCase("info")){
                        player.sendMessage("§fInformation omkring serveren:");
                        player.sendMessage("§8» §fVersion: §a" + Bukkit.getServer().getVersion());
                        player.sendMessage("§8» §fOnline: §a" + Bukkit.getServer().getOnlinePlayers().size() + "§7/§a" + Bukkit.getServer().getMaxPlayers());
                        player.sendMessage("§8» §fIp: §a" + Bukkit.getIp() + ":" + Bukkit.getPort());
                        if (Bukkit.getServer().getOnlineMode()){
                            player.sendMessage("§8» §fOnlinemode: §a" + Bukkit.getServer().getOnlineMode());
                        } else {
                            player.sendMessage("§8» §fOnlinemode: §c" + Bukkit.getServer().getOnlineMode());
                        }
                        //player.sendMessage("§8» §f" + Bukkit.getWorlds());
                    } else {
                        player.performCommand("/tokyocore");
                    }
                } else {
                    player.sendMessage(pf + " §cDu har ikke tilladelse til dette! §7(" + Main.instance.getString("config", "adminperm") + ")");
                }
            } else {
                sender.sendMessage("Kommandoen kan kun blive udført af en spiller.");
            }
        }
        return false;
    }
}
