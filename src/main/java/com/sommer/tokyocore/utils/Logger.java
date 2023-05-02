package com.sommer.tokyocore.utils;

import com.sommer.tokyocore.Main;

import java.util.Arrays;

public class Logger {

    public static void info(String... string) {
        Arrays.asList(string).forEach(s -> info(s));
    }

    public static void info(String string) {
        Main.getInstance().getLogger().info(string);
    }

    public static void warn(String string) {
        Main.getInstance().getLogger().warning(string);
    }

    public static void severe(String string) {
        Main.getInstance().getLogger().severe(string);
    }

}