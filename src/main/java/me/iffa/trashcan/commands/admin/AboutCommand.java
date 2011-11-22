// Package Declaration
package me.iffa.trashcan.commands.admin;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Represents /trashcan.
 * 
 * @author iffamies
 */
public class AboutCommand extends TrashCommand {
    /**
     * Constructor of AboutCommand.
     * 
     * @param label Command label
     */
    public AboutCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (args.length < 1) {
            MessageUtil.sendMessage(cs, ChatColor.DARK_GREEN + " TrashCan - " + TrashCan.getDescriptionFile().getVersion());
            MessageUtil.sendMessage(cs, ChatColor.DARK_GREEN + "   - Fixing " + ChatColor.GREEN + "CommandBin" + ChatColor.DARK_GREEN + " since 11.22.2011!");
            return true;
        } else {
            // Credits
            if (args[0].equalsIgnoreCase("credits")) {
                MessageUtil.sendMessage(cs, ChatColor.DARK_GREEN + " TrashCan Credits:");
                MessageUtil.sendMessage(cs, ChatColor.GRAY + "  - iffa - creator of TrashCan");
                MessageUtil.sendMessage(cs, ChatColor.GRAY + "  - CainFoool - creator of CommandBin");
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
    }
    
}
