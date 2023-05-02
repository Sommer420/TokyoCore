package com.sommer.tokyocore.admin.fly;

import com.sommer.tokyocore.Main;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FlyManager extends JavaPlugin {

    public static void flyPlayer(Player player, Player target){
        if (target.getAllowFlight()){
            target.setAllowFlight(false);
            if (player == target){
                target.sendMessage(Main.getInstance().getString("config", "prefix") + "§f Du slog din flytilstand §cfra§f!");
            } else {
                target.sendMessage(Main.getInstance().getString("config", "prefix") + "§a " + player + "§f slog din flytilstand §cfra§f!");
            }
        } else {
            target.setAllowFlight(true);
            if (player == target){
                target.sendMessage(Main.getInstance().getString("config", "prefix") + "§f Du slog din flytilstand §atil§f!");
            } else {
                target.sendMessage(Main.getInstance().getString("config", "prefix") + "§a " + player + "§f slog din flytilstand §atil§f!");
            }
        }
    }

}
