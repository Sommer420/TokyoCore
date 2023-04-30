package com.sommer.tokyocore.coins;

import com.sommer.tokyocore.Main;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.components.GuiType;
import dev.triumphteam.gui.guis.Gui;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.atomic.AtomicReference;

public class CoinGui {

    @SuppressWarnings("deprecation")

    public static void openCoinsGui(Player player){
        //int coins = CoinMap.getOrDefault(player.getUniqueId(), 0);
        AtomicReference<Integer> coins = new AtomicReference<>(0);
        Gui gui = Gui.gui()
                .title(Component.text(CoinManager.mainFarve() + "§lCOINS §8(INDSÆT)"))
                //.rows(5) //Med .rows() er det en kiste,
                .type(GuiType.HOPPER)// sådan er det en dispenser. Det kræver dog, at der ik er nogle rows.
                .create();
        gui.disableAllInteractions();

        ItemStack fjern = new ItemStack(Material.INK_SACK, 1, (short) 1);
        ItemStack add = new ItemStack(Material.INK_SACK, 1, (short) 10);

        gui.setItem(0, ItemBuilder.from(add).name(Component.text("§a+100")).setLore("§fTryk for at tilføje:", "§8» §a100 Coins").asGuiItem(event -> {
            //int coins = CoinMap.getOrDefault(player.getUniqueId(), 0) + 100;
            coins.set(coins.get() + 100);
            gui.setItem(2, ItemBuilder.from(Material.EMPTY_MAP).name(Component.text(CoinManager.mainFarve() + "§nInformation:")).setLore("§fAntal coins valgt:", "§8» " + CoinManager.mainFarve() + coins.get()).asGuiItem(e -> {
                Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        e.getWhoClicked().closeInventory();
                    }
                });
                Bukkit.getOnlinePlayers().stream().forEach(p-> {
                    if (p.isOp()){
                        p.sendMessage(" §a");
                        p.sendMessage(CoinManager.prefix());
                        p.sendMessage(" " + CoinManager.mainFarve() + e.getWhoClicked().getName() + "§f ønsker at købe " + CoinManager.mainFarve() + coins.get() + "§f coins");
                        p.sendMessage(" §a");
                    }
                });
                e.getWhoClicked().sendMessage(" §a");
                e.getWhoClicked().sendMessage(CoinManager.prefix());
                e.getWhoClicked().sendMessage(" " + CoinManager.mainFarve() + "Du§f har anmodet om at købe " + CoinManager.mainFarve() + coins.get() + "§f coins");
                e.getWhoClicked().sendMessage(" §a");
            }));
            gui.update();
        }));

        gui.setItem(1, ItemBuilder.from(add).name(Component.text("§a+500")).setLore("§fTryk for at tilføje:", "§8» §a500 Coins").asGuiItem(event -> {
            //int coins = CoinMap.getOrDefault(player.getUniqueId(), 0) + 100;
            coins.set(coins.get() + 500);
            gui.setItem(2, ItemBuilder.from(Material.EMPTY_MAP).name(Component.text(CoinManager.mainFarve() + "§nInformation:")).setLore("§fAntal coins valgt:", "§8» " + CoinManager.mainFarve() + coins.get()).asGuiItem(e -> {
                Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
                    @Override
                    public void run() {
                        e.getWhoClicked().closeInventory();
                    }
                });
                Bukkit.getOnlinePlayers().stream().forEach(p-> {
                    if (p.isOp()){
                        p.sendMessage(" §a");
                        p.sendMessage(CoinManager.prefix());
                        p.sendMessage(" " + CoinManager.mainFarve() + e.getWhoClicked().getName() + "§f ønsker at købe " + CoinManager.mainFarve() + coins.get() + "§f coins");
                        p.sendMessage(" §a");
                    }
                });
                e.getWhoClicked().sendMessage(" §a");
                e.getWhoClicked().sendMessage(CoinManager.prefix());
                e.getWhoClicked().sendMessage(" " + CoinManager.mainFarve() + "Du§f har anmodet om at købe " + CoinManager.mainFarve() + coins.get() + "§f coins");
                e.getWhoClicked().sendMessage(" §a");
            }));
            gui.update();
        }));

        gui.setItem(4, ItemBuilder.from(fjern).name(Component.text("§c-100")).setLore("§fTryk for at fjerne:", "§8» §c100 Coins").asGuiItem(event -> {
            //int coins = CoinMap.getOrDefault(player.getUniqueId(), 0) + 100;
            if (coins.get() >= 100){
                coins.set(coins.get() - 100);
                gui.setItem(2, ItemBuilder.from(Material.EMPTY_MAP).name(Component.text(CoinManager.mainFarve() + "§nInformation:")).setLore("§fAntal coins valgt:", "§8» " + CoinManager.mainFarve() + coins.get()).asGuiItem(e -> {
                    Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            e.getWhoClicked().closeInventory();
                        }
                    });
                    Bukkit.getOnlinePlayers().stream().forEach(p-> {
                        if (p.isOp()){
                            p.sendMessage(" §a");
                            p.sendMessage(CoinManager.prefix());
                            p.sendMessage(" " + CoinManager.mainFarve() + e.getWhoClicked().getName() + "§f ønsker at købe " + CoinManager.mainFarve() + coins.get() + "§f coins");
                            p.sendMessage(" §a");
                        }
                    });
                    e.getWhoClicked().sendMessage(" §a");
                    e.getWhoClicked().sendMessage(CoinManager.prefix());
                    e.getWhoClicked().sendMessage(" " + CoinManager.mainFarve() + "Du§f har anmodet om at købe " + CoinManager.mainFarve() + coins.get() + "§f coins");
                    e.getWhoClicked().sendMessage(" §a");
                }));
            }
            gui.update();
        }));

        gui.setItem(3, ItemBuilder.from(fjern).name(Component.text("§c-500")).setLore("§fTryk for at fjerne:", "§8» §c500 Coins").asGuiItem(event -> {
            //int coins = CoinMap.getOrDefault(player.getUniqueId(), 0) + 100;
            if (coins.get() >= 500){
                coins.set(coins.get() - 500);
                gui.setItem(2, ItemBuilder.from(Material.EMPTY_MAP).name(Component.text(CoinManager.mainFarve() + "§nInformation:")).setLore("§fAntal coins valgt:", "§8» " + CoinManager.mainFarve() + coins.get()).asGuiItem(e -> {
                    Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            e.getWhoClicked().closeInventory();
                        }
                    });
                    Bukkit.getOnlinePlayers().stream().forEach(p-> {
                        if (p.isOp()){
                            p.sendMessage(" §a");
                            p.sendMessage(CoinManager.prefix());
                            p.sendMessage(" " + CoinManager.mainFarve() + e.getWhoClicked().getName() + "§f ønsker at købe " + CoinManager.mainFarve() + coins.get() + "§f coins");
                            p.sendMessage(" §a");
                        }
                    });
                    e.getWhoClicked().sendMessage(" §a");
                    e.getWhoClicked().sendMessage(CoinManager.prefix());
                    e.getWhoClicked().sendMessage(" " + CoinManager.mainFarve() + "Du§f har anmodet om at købe " + CoinManager.mainFarve() + coins.get() + "§f coins");
                    e.getWhoClicked().sendMessage(" §a");
                }));
            }
            gui.update();
        }));

        gui.setItem(2, ItemBuilder.from(Material.EMPTY_MAP).name(Component.text(CoinManager.mainFarve() + "§nInformation:")).setLore("§fAntal coins valgt:", "§8» " + CoinManager.mainFarve() + coins.get()).asGuiItem(event -> {
            Player p = (Player) event.getWhoClicked();
            p.closeInventory();
            //CoinGui.openCoinsGui((Player) player);
        }));

        gui.open((Player) player);
    }

}
