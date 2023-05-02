package com.sommer.tokyocore.coins;

import com.sommer.tokyocore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.sommer.tokyocore.Main.*;

public class CoinCommand implements CommandExecutor {

    public CoinCommand(Main plugin){
        plugin.getCommand("coins").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("coins")){
            if (player instanceof Player){

                if (args.length == 0) {
                    Integer coins = coinConfigYML.getInt("coins." + player.getUniqueId());
                    player.sendMessage(" §a");
                    player.sendMessage(CoinManager.prefix());
                    player.sendMessage(" " + CoinManager.mainFarve() + "Du§f har " + CoinManager.mainFarve() + "" + coins + "§f coins.");
                    player.sendMessage(" §b");
                } else {
                    if (args[0].equalsIgnoreCase("indsæt")) {
                        if (args.length == 1) {
                            CoinGui.openCoinsGui(player);
                        } else if (args.length == 2) {
                            player.sendMessage(" §a");
                            player.sendMessage(CoinManager.prefix());
                            player.sendMessage(" " + CoinManager.mainFarve() + "Du§f har anmodet om at købe " + CoinManager.mainFarve() + "" + args[1] + "§f coins.");
                            player.sendMessage(" §a");
                            Bukkit.getOnlinePlayers().stream().forEach(p-> {
                                if (p.isOp()){
                                    p.sendMessage(" §a");
                                    p.sendMessage(CoinManager.prefix());
                                    p.sendMessage(" " + CoinManager.mainFarve() + "" + player.getName() + "§f ønsker at købe " + CoinManager.mainFarve() + "" + args[1] + "§f coins");
                                    p.sendMessage(" §a");
                                }
                            });
                        }
                    } else if (args[0].equalsIgnoreCase("set")) {
                        if (player.hasPermission("admin")){
                            if (args.length == 3) {
                                coinConfigYML.set("coins." + Bukkit.getOfflinePlayer(args[1]).getUniqueId(), Integer.valueOf(args[2]));
                                coinConfig.saveConfig();
                                player.sendMessage(" §a");
                                player.sendMessage(CoinManager.prefix());
                                player.sendMessage(" §fDu satte " + CoinManager.mainFarve() + "" + args[1] + "§fs coins til " + CoinManager.mainFarve() + "" + args[2] + "§f.");
                                player.sendMessage(" §b");
                            } else {
                                player.sendMessage(" §a");
                                player.sendMessage("§8[§4§lCOIN §f§lSYSTEM§8]");
                                player.sendMessage(" §cDu skal skrive §4§n/Coins Set (Spiller) (Antal)");
                                player.sendMessage(" §b");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("tjek")) {
                        if (args.length == 2) {
                            Integer coins = coinConfigYML.getInt("coins." + Bukkit.getOfflinePlayer(args[1]).getUniqueId());
                            player.sendMessage(" §a");
                            player.sendMessage(CoinManager.prefix());
                            player.sendMessage(" " + CoinManager.mainFarve() + "" + Bukkit.getOfflinePlayer(args[1]).getName() + "§f har " + CoinManager.mainFarve() + "" + coins + "§f coins.");
                            player.sendMessage(" §b");
                        } else {
                            player.sendMessage(" §a");
                            player.sendMessage("§8[§4§lCOIN §f§lSYSTEM§8]");
                            player.sendMessage(" §cDu skal skrive §4§n/Coins Tjek (Spiller)");
                            player.sendMessage(" §b");
                        }

                    } else if (args[0].equalsIgnoreCase("add")) {
                        if (player.hasPermission("admin")){
                            if (args.length == 3) {
                                int coins = coinConfigYML.getInt("coins." + Bukkit.getOfflinePlayer(args[1]).getUniqueId());
                                Integer coinsNy = coins + Integer.parseInt(args[2]);
                                coinConfigYML.set("coins." + Bukkit.getOfflinePlayer(args[1]).getUniqueId(), coinsNy);
                                coinConfig.saveConfig();
                                player.sendMessage(" §a");
                                player.sendMessage(CoinManager.prefix());
                                player.sendMessage(" §fDu gav " + CoinManager.mainFarve() + "" + args[1] + "§f, " + CoinManager.mainFarve() + ""+ args[2] + "§f coins.");
                                player.sendMessage(" §b");
                            } else {
                                player.sendMessage(" §a");
                                player.sendMessage("§8[§4§lCOIN §f§lSYSTEM§8]");
                                player.sendMessage(" §cDu skal skrive §4§n/Coins Add (Spiller) (Antal)");
                                player.sendMessage(" §b");
                            }
                        }

                    } else if (args[0].equalsIgnoreCase("pay")) {
                        if (coinConfigYML.getBoolean("pay.enabled")){
                            if (args.length == 3) {
                                int coinsS = coinConfigYML.getInt("coins." + player.getUniqueId());
                                int coinsT = coinConfigYML.getInt("coins." + Bukkit.getOfflinePlayer(args[1]).getUniqueId());
                                int ant = Integer.valueOf(args[2]);
                                int minPay = coinConfigYML.getInt("pay.min");
                                if (ant >= minPay) {
                                    if (coinsS+1 >= ant+1){
                                        player.sendMessage(" §a");
                                        player.sendMessage(CoinManager.prefix());
                                        player.sendMessage(" §fDu gav " + CoinManager.mainFarve() + "" + args[1] + "§f, " + CoinManager.mainFarve() + ""+ args[2] + "§f coins.");
                                        player.sendMessage(" §b");
                                        coinConfigYML.set("coins." + player.getUniqueId(), coinsS - ant);
                                        coinConfigYML.set("coins." + Bukkit.getOfflinePlayer(args[1]).getUniqueId(), ant + coinsT);
                                        coinConfig.saveConfig();
                                    } else {
                                        player.sendMessage(" §a");
                                        player.sendMessage("§8[§4§lCOIN §f§lSYSTEM§8]");
                                        player.sendMessage(" §cDu har ikke nok coins! §8(" + ant + "§8)");
                                        player.sendMessage(" §b");
                                    }
                                } else {
                                    player.sendMessage(" §a");
                                    player.sendMessage("§8[§4§lCOIN §f§lSYSTEM§8]");
                                    player.sendMessage(" §cDu skal sende over §4§n" + minPay + "§c coins!");
                                    player.sendMessage(" §b");
                                }

                            } else {
                                player.sendMessage(" §a");
                                player.sendMessage("§8[§4§lCOIN §f§lSYSTEM§8]");
                                player.sendMessage(" §cDu skal skrive §4§n/Coins pay (Spiller) (Antal)");
                                player.sendMessage(" §b");
                            }
                        } else {
                            player.sendMessage("§b");
                            player.sendMessage("§8[§4§lCOIN §f§lSYSTEM§8]");
                            player.sendMessage(" §cPay er slået fra!");
                            player.sendMessage("§8");
                        }

                    } else if (args[0].equalsIgnoreCase("remove")) {
                        if (player.hasPermission("admin")){
                            if (args.length == 3) {
                                int coins = coinConfigYML.getInt("coins." + Bukkit.getOfflinePlayer(args[1]).getUniqueId());
                                Integer coinsNy = coins - Integer.parseInt(args[2]);
                                coinConfigYML.set("coins." + Bukkit.getOfflinePlayer(args[1]).getUniqueId(), coinsNy);
                                coinConfig.saveConfig();
                                player.sendMessage(" §a");
                                player.sendMessage(CoinManager.prefix());
                                player.sendMessage(" §fDu fjernede " + CoinManager.mainFarve() + args[2] + "§f coins fra " + CoinManager.mainFarve() + ""+ args[1] + "§f.");
                                player.sendMessage(" §b");
                            } else {
                                player.sendMessage(" §a");
                                player.sendMessage("§8[§4§lCOIN §f§lSYSTEM§8]");
                                player.sendMessage(" §cDu skal skrive §4§n/Coins Remove (Spiller) (Antal)");
                                player.sendMessage(" §b");
                            }
                        }
                    } else if (args[0].equalsIgnoreCase("reload")) {
                        if (player.hasPermission("admin")) {
                            coinConfig.reloadConfig();
                            coinConfigYML = coinConfig.getConfig();
                            player.sendMessage(" §a");
                            player.sendMessage(CoinManager.prefix());
                            player.sendMessage(" §fDu reloadede config filen.");
                            player.sendMessage(" §b");
                        } else {
                            player.sendMessage(" §a");
                            player.sendMessage("§8[§4§lCOIN §f§lSYSTEM§8]");
                            player.sendMessage(" §cDu har ikke adgang til denne kommando!");
                            player.sendMessage(" §b");
                        }

                    } else if (args[0].equalsIgnoreCase("info")) {
                        player.sendMessage(" §a");
                        player.sendMessage(CoinManager.prefix());
                        player.sendMessage(" §fCoinsystem lavet af " + CoinManager.mainFarve() + "Sommer§f!");
                        player.sendMessage(" §fDiscord: " + CoinManager.mainFarve() + "Sommer#4445");
                        player.sendMessage(" §fKontakt for custom plugins, til " + CoinManager.mainFarve() + "din§f server!");
                        player.sendMessage(" §b");
                    } else if (args[0].equalsIgnoreCase("help")){
                        player.sendMessage(" §a");
                        player.sendMessage(CoinManager.prefix());
                        player.sendMessage(" §cDu kan gøre brug af følgende kommandoer:");
                        player.sendMessage(" §c/coins ..");
                        player.sendMessage(" §8» §c.. tjek (spiller)");
                        player.sendMessage(" §8» §c.. indsæt");
                        player.sendMessage(" §8» §c.. info");
                        if (coinConfigYML.getBoolean("pay.enabled")){
                            player.sendMessage(" §8» §c.. pay (spiller) (antal)");
                        }
                        if (player.hasPermission("admin")) {
                            player.sendMessage(" §6§nAdmin kommandoer:");
                            player.sendMessage(" §8» §c.. add (spiller) (antal)");
                            player.sendMessage(" §8» §c.. remove (spiller) (antal)");
                            player.sendMessage(" §8» §c.. set (spiller) (antal)");
                            player.sendMessage(" §8» §creload");
                        }
                        player.sendMessage(" §b");
                    } else {
                        player.sendMessage(" §a");
                        player.sendMessage(CoinManager.prefix());
                        player.sendMessage(" §cDu kan gøre brug af følgende kommandoer:");
                        player.sendMessage(" §c/coins ..");
                        player.sendMessage(" §8» §c.. tjek (spiller)");
                        player.sendMessage(" §8» §c.. indsæt");
                        player.sendMessage(" §8» §c.. info");
                        if (coinConfigYML.getBoolean("pay.enabled")){
                            player.sendMessage(" §8» §c.. pay (spiller) (antal)");
                        }
                        if (player.hasPermission("admin")) {
                            player.sendMessage(" §6§nAdmin kommandoer:");
                            player.sendMessage(" §8» §c.. add (spiller) (antal)");
                            player.sendMessage(" §8» §c.. remove (spiller) (antal)");
                            player.sendMessage(" §8» §c.. set (spiller) (antal)");
                            player.sendMessage(" §8» §c.. reload");
                        }
                        player.sendMessage(" §b");
                    }
                }
            }
        }
        return false;
    }
}
