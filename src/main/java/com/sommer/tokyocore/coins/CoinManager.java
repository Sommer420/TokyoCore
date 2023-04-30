package com.sommer.tokyocore.coins;

import com.sommer.tokyocore.Main;
import org.bukkit.OfflinePlayer;

import static com.sommer.tokyocore.Main.coinConfig;
import static com.sommer.tokyocore.Main.coinConfigYML;

public class CoinManager {

    public static String prefix(){
        return coinConfigYML.getString("prefix").replace("&", "§");
    }
    public static String mainFarve(){
        return coinConfigYML.getString("mainFarve").replace("&", "§");
    }
    public static int getCoins(OfflinePlayer p) {
        return Main.coinConfigYML.getInt("coins." + p.getUniqueId());
    }

    public static void addCoins(OfflinePlayer p, Integer amount) {
        int coins = coinConfigYML.getInt("coins." + p.getUniqueId());
        Integer coinsNy = coins + amount;
        coinConfigYML.set("coins." + p.getUniqueId(), coinsNy);
        coinConfig.saveConfig();
    }

    public static void removeCoins(OfflinePlayer p, Integer amount) {
        int coins = coinConfigYML.getInt("coins." + p.getUniqueId());
        Integer coinsNy = coins - amount;
        if (coinsNy >= 0){
            coinConfigYML.set("coins." + p.getUniqueId(), coinsNy);
        } else {
            coinConfigYML.set("coins." + p.getUniqueId(), 0);
        }
        coinConfig.saveConfig();
    }

    public static void setCoins(OfflinePlayer p, Integer amount) {
        coinConfigYML.set("coins." + p.getUniqueId(), amount);
        coinConfig.saveConfig();
    }

}
