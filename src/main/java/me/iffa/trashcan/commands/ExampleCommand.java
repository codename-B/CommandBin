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
     * @see me.iffa.trashcan.commands.TrashCommand
     * 
     * @param cs Command sender
     * @param args Command arguments
     * 
     * @return True if no usage information should be sent
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        return true;
    }

    /**
     * @see me.iffa.trashcan.commands.TrashCommand
     * 
     * @param cs Command sender
     */
    @Override
    public void sendUsage(CommandSender cs) {
    }
    
}
