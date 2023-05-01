package com.sommer.tokyocore.admin.straf;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Date;

public class StrafManager extends JavaPlugin {

    public static void kickPlayer(Player target, Player sender, String grund){
        target.kickPlayer("§fDu blev kicket af §a" + sender + "§f med følgende grund; §a" + grund + "§f.");
        //Discord log, at sender kicker target
    }

    public static void banPlayer(Player target, Player sender, String grund, Date tid){
        Bukkit.getBanList(BanList.Type.IP).addBan(target.getName(), grund, tid, "Staff medlem");
        //Discord log, at sender banner target
    }
}
