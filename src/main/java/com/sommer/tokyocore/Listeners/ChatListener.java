package com.sommer.tokyocore.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.jetbrains.annotations.Async;

import static com.sommer.tokyocore.Main.mainConfigYML;

public class ChatListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerChatEvent(AsyncPlayerChatEvent e){
        if (mainConfigYML.getBoolean("chatEnabled")){
            //Chat chat = Main.instance.getChat();
            //System.out.println("chatEnabled == true");
            e.setFormat(e.getPlayer().getName() + " §8» §f" + e.getMessage().replace("&", "§").replace("%", ""));
        } else {
            //System.out.println("chatEnabled !== true");
        }
    }
}
