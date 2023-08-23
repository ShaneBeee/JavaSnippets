package com.shanebeestudios.snippets.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class with shortcut methods
 */
public class Utils {

    private static final String PREFIX = "&7[&bTest&3Plugin&7] ";
    private static final Pattern HEX_PATTERN = Pattern.compile("<#([A-Fa-f\\d]){6}>");

    /**
     * Colorize a string which may contain ampersand or hex codes
     *
     * @param string String to colorize
     * @return Colorized string
     */
    @SuppressWarnings("deprecation") // Paper deprecation
    public static String getColString(String string) {
        Matcher matcher = HEX_PATTERN.matcher(string);
        while (matcher.find()) {
            final net.md_5.bungee.api.ChatColor hexColor = net.md_5.bungee.api.ChatColor.of(matcher.group().substring(1, matcher.group().length() - 1));
            final String before = string.substring(0, matcher.start());
            final String after = string.substring(matcher.end());
            string = before + hexColor + after;
            matcher = HEX_PATTERN.matcher(string);
        }
        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void log(String format, Object... objects) {
        ConsoleCommandSender consoleSender = Bukkit.getConsoleSender();
        String message = getColString(PREFIX + String.format(format, objects));
        consoleSender.sendMessage(message);
    }

}
