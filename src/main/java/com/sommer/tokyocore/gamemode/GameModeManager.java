package com.sommer.tokyocore.gamemode;

import com.sommer.tokyocore.Main;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class GameModeManager extends JavaPlugin{
    public static void setGameMode(final Player p, final String gm){
        if (gm.equalsIgnoreCase("1")||gm.equalsIgnoreCase("creative")||gm.equalsIgnoreCase( "kreativ")){
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage(Main.instance.getString("config", "prefix") + " §fDin gamemode blev sat til §akreativ§f!");
        } else if (gm.equalsIgnoreCase("0")||gm.equalsIgnoreCase("survival")||gm.equalsIgnoreCase( "overlevlse")){
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage(Main.instance.getString("config", "prefix") + " §fDin gamemode blev sat til §asurvival§f!");
        } else if (gm.equalsIgnoreCase("2")||gm.equalsIgnoreCase("adventure")||gm.equalsIgnoreCase( "eventyr")){
            p.setGameMode(GameMode.ADVENTURE);
            p.sendMessage(Main.instance.getString("config", "prefix") + " §fDin gamemode blev sat til §aeventyr§f!");
        } else if (gm.equalsIgnoreCase("3")||gm.equalsIgnoreCase("spectator")||gm.equalsIgnoreCase( "tilskuer")){
            p.setGameMode(GameMode.SPECTATOR);
            p.sendMessage(Main.instance.getString("config", "prefix") + " §fDin gamemode blev sat til §aspectator§f!");
        } else {
            p.sendMessage(Main.instance.getString("config", "prefixfejl") + " §cForstod ikke §c§n" + gm + "§c!");
        }
    }
}
