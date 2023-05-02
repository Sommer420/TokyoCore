package com.sommer.tokyocore.gamemode;

import com.sommer.tokyocore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static com.sommer.tokyocore.Main.mainConfigYML;

public class GameModeCommand extends JavaPlugin implements CommandExecutor {

    public GameModeCommand(Main plugin) {
        plugin.getCommand("Gamemode").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (p instanceof Player){
            if (command.getName().equalsIgnoreCase("gamemode")){
                String pf = "§8[ §c⚒ §8]";
                String prefix = "§8[ §a⚒ §8]";
                if (p.hasPermission(mainConfigYML.getString("adminperm"))){
                    if (args.length == 0) {
                        p.sendMessage(pf + "§cKorrekt brug: §c§n/" + command.getName() + " 0/1/2/3§c!");
                    } else if (args.length == 1){
                        GameModeManager.setGameMode(p, args[0]);
                    } else if (args.length == 2){
                        GameModeManager.setGameMode(Bukkit.getPlayer(args[0]), args[1]);
                    } else if (args.length >= 3){
                        p.sendMessage(pf + "§cDu skal skrive §n/" + command.getName() + " 0/1/2/3 (Spiller)");
                    }
                } else if (!p.hasPermission(mainConfigYML.getString("adminperm"))) {
                    p.sendMessage(pf + "§cDet har du ikke tilladelse til!");
                }
            } else {
                System.out.println("Kommandoen != gamemode");
            }
        } else {
            p.sendMessage("Denne kommando kan kun blive udført af en spiller!");
        }
        return true;
    }
}
