package com.sommer.tokyocore.admin.straf;

import com.sommer.tokyocore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class Kick implements CommandExecutor {
    private final Main plugin;

    public Kick(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("Kick").setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("kick")){
            String pf = Main.getInstance().getString("config", "prefixfejl");
            String prefix = Main.getInstance().getString("config", "prefix");
            if (p.hasPermission(Main.instance.getString("config", "adminperm"))){
                if (args.length == 0){
                    p.sendMessage(pf + " §cKorrekt brug: §n/" + command.getName() + " (Spiller) (Grund)");
                } else if (args.length == 1){
                    p.sendMessage(pf + " §cDu skal skrive en grund!");
                } else {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))){
                        Player target = Bukkit.getPlayer(args[0]);
                        if (args.length >= 3){
                            String reason = Arrays.asList(args).subList(2, args.length).toString();
                            StrafManager.kickPlayer(target, (Player) p, reason);
                        } else {
                            StrafManager.kickPlayer(target, (Player) p, args[1]);
                        }
                    } else {
                        p.sendMessage(pf + " §4" + Bukkit.getPlayer(args[0]) + "§c er ikke online!");
                    }
                }
            } else {
                p.sendMessage(pf + " §cDu har ikke adgang til at kick folk!");
            }
        }
        return false;
    }
}
