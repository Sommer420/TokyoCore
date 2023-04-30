package com.sommer.tokyocore.gems;

import com.sommer.tokyocore.Main;
import org.bukkit.OfflinePlayer;

import static com.sommer.tokyocore.Main.gemConfig;
import static com.sommer.tokyocore.Main.gemConfigYML;

public class GemManager {

    public static String prefix(){
        return gemConfigYML.getString("prefix").replace("&", "§");
    }
    public static String mainFarve(){
        return gemConfigYML.getString("mainFarve").replace("&", "§");
    }
    public static int getGems(OfflinePlayer p) {
        return Main.gemConfigYML.getInt("gems." + p.getUniqueId());
    }

    public static void addGems(OfflinePlayer p, Integer amount) {
        int gems = gemConfigYML.getInt("gems." + p.getUniqueId());
        Integer gemsNy = gems + amount;
        gemConfigYML.set("gems." + p.getUniqueId(), gemsNy);
        gemConfig.saveConfig();
    }

    public static void removeGems(OfflinePlayer p, Integer amount) {
        int gems = gemConfigYML.getInt("gems." + p.getUniqueId());
        Integer gemsNy = gems - amount;
        if (gemsNy >= 0){
            gemConfigYML.set("gems." + p.getUniqueId(), gemsNy);
        } else {
            gemConfigYML.set("gems." + p.getUniqueId(), 0);
        }
        gemConfig.saveConfig();
    }

    public static void setGems(OfflinePlayer p, Integer amount) {
        gemConfigYML.set("gems." + p.getUniqueId(), amount);
        gemConfig.saveConfig();
    }

}
