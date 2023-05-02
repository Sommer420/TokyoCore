package com.sommer.tokyocore.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static com.sommer.tokyocore.Main.mainConfigYML;

public class ChatListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerChatEvent(AsyncPlayerChatEvent e){
        if (mainConfigYML.getBoolean("chatEnabled")){
            //Chat chat = Main.instance.getChat();
            //System.out.println("chatEnabled == true");
            e.setFormat(e.getPlayer().getName() + " §8» §f" + e.getMessage().replace("&", "§").replace("%", "procent"));
        } else {
            //System.out.println("chatEnabled !== true");
        }
    }
}
