package com.sommer.tokyocore.gamemode;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class GameModeManager extends JavaPlugin {
    public static void setGameMode(Player p, String gm){
        if (gm == "1"||gm.equalsIgnoreCase("creative")||gm.equalsIgnoreCase( "kreativ")){
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage("§8[ §a⚒ §8] §fDu satte din gamemode til §akreativ§f!");
        } else if (gm == "0"||gm.equalsIgnoreCase("survival")||gm.equalsIgnoreCase( "overlevlse")){
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage("§8[ §a⚒ §8] §fDu satte din gamemode til §asurvival§f!");
        } else if (gm == "2"||gm.equalsIgnoreCase("adventure")||gm.equalsIgnoreCase( "eventyr")){
            p.setGameMode(GameMode.ADVENTURE);
            p.sendMessage("§8[ §a⚒ §8] §fDu satte din gamemode til §aeventyr§f!");
        } else if (gm == "3"||gm.equalsIgnoreCase("spectator")||gm.equalsIgnoreCase( "tilskuer")){
            p.setGameMode(GameMode.SPECTATOR);
            p.sendMessage("§8[ §a⚒ §8] §fDu satte din gamemode til §aspectator§f!");
        } else {
            p.sendMessage("§8[ §c⚒ §8] §cForstod ikke §c§n" + gm + "§c!");
        }
    }
}
