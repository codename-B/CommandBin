// Package Declaration
package me.iffa.trashcan.commands.fun;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Represents /crossbow.
 * 
 * @author iffamies
 */
public class CrossbowCommand extends TrashCommand {
    /**
     * Constructor of CrossbowCommand.
     * 
     * @param label Command label
     */
    public CrossbowCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (cs.hasPermission("trashcan.fun.crossbow")) {
            if (!(cs instanceof Player)) {
                MessageUtil.sendMessage(cs, TrashCan.getPrefix() + " Sorry, only players can use this command.");
                return true;
            }
            TrashCan.getConfigHandler().setCrossbow(!TrashCan.getConfigHandler().getCrossbow((Player)cs), (Player)cs);
            MessageUtil.sendMessage(cs, ChatColor.GOLD + "Toggled crossbow!");
        } else {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission!");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
    }
    
}