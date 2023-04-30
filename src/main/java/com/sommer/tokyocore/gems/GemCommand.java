package com.sommer.tokyocore.gems;

import com.sommer.tokyocore.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.sommer.tokyocore.Main.*;

public class GemCommand implements CommandExecutor {

    public GemCommand(Main plugin) {
        plugin.getCommand("Gems").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("gems")) {
            if (player instanceof Player) {

                if (args.length == 0) {
                    Integer gems = gemConfigYML.getInt("gems." + player.getUniqueId());
                    player.sendMessage(" §a");
                    player.sendMessage(GemManager.prefix());
                    player.sendMessage(" " + GemManager.mainFarve() + "Du§f har " + GemManager.mainFarve() + "" + gems + "§f gems.");
                    player.sendMessage(" §b");
                } else if (args[0].equalsIgnoreCase("set")) {
                    if (player.hasPermission("admin")) {
                        if (args.length == 3) {
                            gemConfigYML.set("gems." + Bukkit.getOfflinePlayer(args[1]).getUniqueId(), Integer.valueOf(args[2]));
                            gemConfig.saveConfig();
                            player.sendMessage(" §a");
                            player.sendMessage(GemManager.prefix());
                            player.sendMessage(" §fDu satte " + GemManager.mainFarve() + "" + args[1] + "§fs gems til " + GemManager.mainFarve() + "" + args[2] + "§f.");
                            player.sendMessage(" §b");
                        } else {
                            player.sendMessage(" §a");
                            player.sendMessage("§8[§4§lGEM §f§lSYSTEM§8]");
                            player.sendMessage(" §cDu skal skrive §4§n/Gems Set (Spiller) (Antal)");
                            player.sendMessage(" §b");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("tjek")) {
                    if (args.length == 2) {
                        Integer gems = gemConfigYML.getInt("gems." + Bukkit.getOfflinePlayer(args[1]).getUniqueId());
                        player.sendMessage(" §a");
                        player.sendMessage(GemManager.prefix());
                        player.sendMessage(" " + GemManager.mainFarve() + "" + Bukkit.getOfflinePlayer(args[1]).getName() + "§f har " + GemManager.mainFarve() + "" + gems + "§f gems.");
                        player.sendMessage(" §b");
                    } else {
                        player.sendMessage(" §a");
                        player.sendMessage("§8[§4§lGEM §f§lSYSTEM§8]");
                        player.sendMessage(" §cDu skal skrive §4§n/Gems Tjek (Spiller)");
                        player.sendMessage(" §b");
                    }

                } else if (args[0].equalsIgnoreCase("add")) {
                    if (player.hasPermission("admin")) {
                        if (args.length == 3) {
                            int gems = gemConfigYML.getInt("gems." + Bukkit.getOfflinePlayer(args[1]).getUniqueId());
                            Integer gemsNy = gems + Integer.parseInt(args[2]);
                            gemConfigYML.set("gems." + Bukkit.getOfflinePlayer(args[1]).getUniqueId(), gemsNy);
                            gemConfig.saveConfig();
                            player.sendMessage(" §a");
                            player.sendMessage(GemManager.prefix());
                            player.sendMessage(" §fDu gav " + GemManager.mainFarve() + "" + args[1] + "§f, " + GemManager.mainFarve() + "" + args[2] + "§f gems.");
                            player.sendMessage(" §b");
                        } else {
                            player.sendMessage(" §a");
                            player.sendMessage("§8[§4§lGEM §f§lSYSTEM§8]");
                            player.sendMessage(" §cDu skal skrive §4§n/Gems Add (Spiller) (Antal)");
                            player.sendMessage(" §b");
                        }
                    }

                } else if (args[0].equalsIgnoreCase("pay")) {
                    if (gemConfigYML.getBoolean("pay.enabled")) {
                        if (args.length == 3) {
                            int gemsS = gemConfigYML.getInt("gems." + player.getUniqueId());
                            int gemsT = gemConfigYML.getInt("gems." + Bukkit.getOfflinePlayer(args[1]).getUniqueId());
                            int ant = Integer.valueOf(args[2]);
                            int minPay = gemConfigYML.getInt("pay.min");
                            if (ant >= minPay) {
                                if (gemsS + 1 >= ant + 1) {
                                    player.sendMessage(" §a");
                                    player.sendMessage(GemManager.prefix());
                                    player.sendMessage(" §fDu gav " + GemManager.mainFarve() + "" + args[1] + "§f, " + GemManager.mainFarve() + "" + args[2] + "§f gems.");
                                    player.sendMessage(" §b");
                                    gemConfigYML.set("gems." + player.getUniqueId(), gemsS - ant);
                                    gemConfigYML.set("gems." + Bukkit.getOfflinePlayer(args[1]).getUniqueId(), ant + gemsT);
                                    gemConfig.saveConfig();
                                } else {
                                    player.sendMessage(" §a");
                                    player.sendMessage("§8[§4§lGEM §f§lSYSTEM§8]");
                                    player.sendMessage(" §cDu har ikke nok gems! §8(" + ant + "§8)");
                                    player.sendMessage(" §b");
                                }
                            } else {
                                player.sendMessage(" §a");
                                player.sendMessage("§8[§4§lGEM §f§lSYSTEM§8]");
                                player.sendMessage(" §cDu skal sende over §4§n" + minPay + "§c gems!");
                                player.sendMessage(" §b");
                            }

                        } else {
                            player.sendMessage(" §a");
                            player.sendMessage("§8[§4§lGEM §f§lSYSTEM§8]");
                            player.sendMessage(" §cDu skal skrive §4§n/Gems pay (Spiller) (Antal)");
                            player.sendMessage(" §b");
                        }
                    } else {
                        player.sendMessage("§b");
                        player.sendMessage("§8[§4§lGEM §f§lSYSTEM§8]");
                        player.sendMessage(" §cPay er slået fra!");
                        player.sendMessage("§8");
                    }

                } else if (args[0].equalsIgnoreCase("remove")) {
                    if (player.hasPermission("admin")) {
                        if (args.length == 3) {
                            int gems = gemConfigYML.getInt("gems." + Bukkit.getOfflinePlayer(args[1]).getUniqueId());
                            Integer gemsNy = gems - Integer.parseInt(args[2]);
                            gemConfigYML.set("gems." + Bukkit.getOfflinePlayer(args[1]).getUniqueId(), gemsNy);
                            gemConfig.saveConfig();
                            player.sendMessage(" §a");
                            player.sendMessage(GemManager.prefix());
                            player.sendMessage(" §fDu fjernede " + GemManager.mainFarve() + args[2] + "§f gems fra " + GemManager.mainFarve() + "" + args[1] + "§f.");
                            player.sendMessage(" §b");
                        } else {
                            player.sendMessage(" §a");
                            player.sendMessage("§8[§4§lGEM §f§lSYSTEM§8]");
                            player.sendMessage(" §cDu skal skrive §4§n/Gems Remove (Spiller) (Antal)");
                            player.sendMessage(" §b");
                        }
                    }
                } else if (args[0].equalsIgnoreCase("reload")) {
                    if (player.hasPermission("admin")) {
                        gemConfig.reloadConfig();
                        gemConfigYML = gemConfig.getConfig();
                        player.sendMessage(" §a");
                        player.sendMessage(GemManager.prefix());
                        player.sendMessage(" §fDu reloadede config filen.");
                        player.sendMessage(" §b");
                    } else {
                        player.sendMessage(" §a");
                        player.sendMessage("§8[§4§lGEM §f§lSYSTEM§8]");
                        player.sendMessage(" §cDu har ikke adgang til denne kommando!");
                        player.sendMessage(" §b");
                    }

                } else if (args[0].equalsIgnoreCase("info")) {
                    player.sendMessage(" §a");
                    player.sendMessage(GemManager.prefix());
                    player.sendMessage(" §fGemsystem lavet af " + GemManager.mainFarve() + "Sommer§f!");
                    player.sendMessage(" §fDiscord: " + GemManager.mainFarve() + "Sommer#4445");
                    player.sendMessage(" §fKontakt for custom plugins, til " + GemManager.mainFarve() + "din§f server!");
                    player.sendMessage(" §b");
                } else if (args[0].equalsIgnoreCase("help")) {
                    player.sendMessage(" §a");
                    player.sendMessage(GemManager.prefix());
                    player.sendMessage(" §cDu kan gøre brug af følgende kommandoer:");
                    player.sendMessage(" §c/gems ..");
                    player.sendMessage(" §8» §c.. tjek (spiller)");
                    player.sendMessage(" §8» §c.. info");
                    if (gemConfigYML.getBoolean("pay.enabled")) {
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
                    player.sendMessage(GemManager.prefix());
                    player.sendMessage(" §cDu kan gøre brug af følgende kommandoer:");
                    player.sendMessage(" §c/gems ..");
                    player.sendMessage(" §8» §c.. tjek (spiller)");
                    player.sendMessage(" §8» §c.. info");
                    if (gemConfigYML.getBoolean("pay.enabled")) {
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
        return true;
    }
}
