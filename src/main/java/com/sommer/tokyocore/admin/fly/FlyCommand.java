package com.sommer.tokyocore.admin.fly;

import com.sommer.tokyocore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    public FlyCommand(Main plugin){
        plugin.getCommand("fly").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("fly")){
            Player player = (Player) sender;
            String pf = Main.instance.getString("config", "prefixfejl");
            String p = Main.getInstance().getString("config", "prefix");
            if (sender instanceof Player){
                if (player.hasPermission(Main.instance.getString("config", "adminperm"))){
                    if (args.length == 0){
                        FlyManager.flyPlayer(player, player);
                    } else {
                        if (Bukkit.getPlayer(args[0]).isOnline()){
                            Player target = Bukkit.getPlayer(args[0]);
                            FlyManager.flyPlayer(player, target);
                        } else {
                            player.sendMessage(pf + " §c§n" + Bukkit.getPlayer(args[0]).getName() + "§c er ikke online!");
                        }
                    }
                } else {
                    player.sendMessage(pf + "§c Du har ikke tilladelse til dette!");
                }
            } else {
                player.sendMessage("Kommandoen kan kun blive udført af spillere!");
            }
        }
        return false;
    }
}
