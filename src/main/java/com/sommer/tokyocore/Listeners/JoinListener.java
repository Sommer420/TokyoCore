package com.sommer.tokyocore.Listeners;

import com.sommer.tokyocore.Main;
import com.sommer.tokyocore.update.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    public void onPlayerJoin(PlayerJoinEvent e){
        if (e.getPlayer().isOp()){
            if (!UpdateChecker.isUpdateAvailable()){
                return;
            }
            Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {

                @Override
                public void run() {
                    e.getPlayer().sendMessage(Main.instance.getString("config", "prefix") + "§aTokyo§fCore§f skal opdatere! §7(" + UpdateChecker.getPluginVersion() + " -> " + UpdateChecker.getNewestVersion() + ")");
                }
            });
        }
    }
}
