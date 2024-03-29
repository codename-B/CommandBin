// Package Declaration
package me.iffa.trashcan.commands;

// Bukkit Imports
import org.bukkit.command.CommandSender;

/**
 * An example command. Not used anywhere.
 * 
 * @author iffamies
 */
public class ExampleCommand extends TrashCommand {
    /**
     * Constructor of ExampleCommand.
     * 
     * @param label Command label
     */
    public ExampleCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
    }
    
}
