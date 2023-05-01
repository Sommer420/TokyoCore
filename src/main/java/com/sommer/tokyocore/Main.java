package com.sommer.tokyocore;

import com.sommer.tokyocore.admin.CoreCommand;
import com.sommer.tokyocore.admin.straf.Kick;
import com.sommer.tokyocore.coins.CoinCommand;
import com.sommer.tokyocore.gamemode.*;
import com.sommer.tokyocore.gems.GemCommand;
import com.sommer.tokyocore.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin implements Listener {
    public static Config coinConfig, gemConfig, mainConfig;
    public static FileConfiguration coinConfigYML, gemConfigYML, mainConfigYML;
    public static Main instance;
    @Override
    public void onEnable() {
        //Opretter configs:
        instance = this;
        //Config fil til coins:
        if (!(new File(getDataFolder(), "coins.yml")).exists())saveResource("coins.yml", false);
        coinConfig = new Config(this, null, "coins.yml");
        coinConfigYML = coinConfig.getConfig();
        //Config fil til gems:
        if (!(new File(getDataFolder(), "gems.yml")).exists())saveResource("gems.yml", false);
        gemConfig = new Config(this, null, "gems.yml");
        gemConfigYML = gemConfig.getConfig();
        //Config fil til main config:
        if (!(new File(getDataFolder(), "config.yml")).exists())saveResource("config.yml", false);
        mainConfig = new Config(this, null, "config.yml");
        mainConfigYML = mainConfig.getConfig();
        //new GuiManager();
        //Bukkit.getPluginManager().registerEvents(this, (Plugin) this);
        getCommand("Gamemode").setExecutor(this);
        System.out.println("Pluginet starter..");
        new GemCommand(this);
        new CoinCommand(this);
        new CoreCommand(this);

        new Kick(this);

        new GameModeCommand(this);
        new Gma(this);
        new Gmc(this);
        new Gms(this);
        new Gmsp(this);

    }


    public String getString(String path, String value){
        if (path == "config"){
            return mainConfigYML.getString(value).replace("&", "§");
        } else if (path == "gems"){
            return gemConfigYML.getString(value).replace("&", "§");
        } else if (path == "coins"){
            return coinConfigYML.getString(value).replace("&", "§");
        } else {
            return "Forstod ikke pathen..";
        }
    }

    public static Main getInstance(){return instance;}

    @Override
    public void onDisable() {System.out.println("Pluginet slået fra..");}





}
