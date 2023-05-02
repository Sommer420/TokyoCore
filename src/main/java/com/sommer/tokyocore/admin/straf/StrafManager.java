package com.sommer.tokyocore.admin.straf;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static com.sommer.tokyocore.Main.msgConfigYML;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StrafManager extends JavaPlugin {

    public static void kickPlayer(Player target, Player sender, String grund){
        List<String> reason = msgConfigYML.getStringList("kickReason").stream().map(rsn -> rsn.replace("&", "§").replace("(KICKER)", sender.getName()).replace("(GRUND)", grund)).collect(Collectors.toList());
        target.kickPlayer(String.join("\n", reason));
        //Discord log, at sender kicker target
    }

    public static void banPlayer(Player target, Player sender, String grund, Date tid){
        Bukkit.getBanList(BanList.Type.IP).addBan(target.getName(), grund, tid, "Staff medlem");
        //Discord log, at sender banner target
    }
}
