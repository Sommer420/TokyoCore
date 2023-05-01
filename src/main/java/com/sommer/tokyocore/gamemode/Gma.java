package com.sommer.tokyocore.gamemode;

import com.sommer.tokyocore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.sommer.tokyocore.Main.mainConfigYML;

public class Gma implements CommandExecutor {
    private final Main plugin;
    public Gma(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("gma").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (p instanceof Player) {
            if (command.getName().equalsIgnoreCase("gma")) {
                String pf = Main.getInstance().getString("config", "prefixfejl");
                String prefix = Main.getInstance().getString("config", "prefix");
                if (p.hasPermission(mainConfigYML.getString("adminperm"))) {
                    if (args.length == 0){
                        GameModeManager.setGameMode(p, "adventure");
                    } else if (args.length == 1){
                        GameModeManager.setGameMode(Bukkit.getPlayer(args[0]), "adventure");
                        p.sendMessage(prefix + "§f Du satte §a" + Bukkit.getPlayer(args[0]).getName() + "§fs gamemode til §aeventyr§f!");
                    } else {
                        p.sendMessage(pf + " §cDer skete en fejl..");
                    }
                } else if (!p.hasPermission(mainConfigYML.getString("adminperm"))) {
                    p.sendMessage(pf + " §cDet har du ikke tilladelse til!");
                }
            }
        }
        return false;
    }
}
