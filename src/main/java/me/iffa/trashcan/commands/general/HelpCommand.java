// Package Declaration
package me.iffa.trashcan.commands.general;

// Java Imports
import java.util.HashMap;
import java.util.Map;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.HelpPage;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Represents /help.
 * 
 * @author iffamies
 */
public class HelpCommand extends TrashCommand {
    // Variables
    private Map<Integer, HelpPage> pages = new HashMap<Integer, HelpPage>();
    /**
     * Constructor of HelpCommand.
     * 
     * @param label 
     */
    public HelpCommand(String label) {
        super(label);
    }
    
    /**
     * Sets up help pages.
     */
    public void setupHelp() {
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        // TODO: Implement /help
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /help [page]");
    }
    
}
