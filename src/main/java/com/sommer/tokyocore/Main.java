package com.sommer.tokyocore;

import com.sommer.tokyocore.coins.CoinCommand;
import com.sommer.tokyocore.gamemode.GameModeCommand;
import com.sommer.tokyocore.gems.GemCommand;
import com.sommer.tokyocore.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin implements Listener{
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

        new GameModeCommand(this);
        new CoinCommand(this);
        new GemCommand(this);

        System.out.println("Pluginet starter..");
    }


    public static Main getInstance(){return instance;}

    @Override
    public void onDisable() {System.out.println("Pluginet slået fra..");}





}
