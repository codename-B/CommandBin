// Package Declaration
package me.iffa.trashcan.utils;

// Java Imports
import java.util.HashMap;
import java.util.Map;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Represents a page of /help.
 * NOTE: It is suggested that a single help page has 5 or 6 commands, so they all
 * fit into the chat box nicely.
 * 
 * @author iffamies
 */
public class HelpPage {
    // Variables
    private int number = 0;
    private Map<String, String> commands = new HashMap<String, String>();
    
    /**
     * Constructor of HelpPage.
     * 
     * @param number Page number
     */
    public HelpPage(int number) {
        this.number = number;
    }
    
    /**
     * Gets the commands in this help page.
     * 
     * @return Commands as Map
     */
    public Map<String, String> getCommands() {
        return commands;
    }
    
    /**
     * Sends this help page to a command sender.
     * 
     * @param cs Command sender 
     */
    public void sendHelpPage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GOLD + "/help - page: " + number);
        for (String command : commands.keySet()) {
            MessageUtil.sendMessage(cs, ChatColor.GRAY + "/" + command + ChatColor.GOLD + " - " + ChatColor.GRAY + commands.get(command));
        }
    }
    
}
