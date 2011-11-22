// Package Declaration
package me.iffa.trashcan.commands;

// Java Imports
import java.util.HashMap;
import java.util.Map;

// Bukkit Imports
import org.bukkit.command.CommandSender;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.admin.AboutCommand;
import me.iffa.trashcan.commands.admin.DebugCommand;
import me.iffa.trashcan.commands.fun.CrossbowCommand;
import me.iffa.trashcan.commands.fun.ExplosionBowCommand;
import me.iffa.trashcan.commands.fun.JoinCommand;
import me.iffa.trashcan.commands.fun.LeaveCommand;
import me.iffa.trashcan.commands.fun.TorchbowCommand;

/**
 * Represents a command of TrashCan.
 * 
 * @author iffamies
 */
public abstract class TrashCommand {
    // Variables
    protected String label;
    private static Map<String, TrashCommand> commands = new HashMap<String, TrashCommand>();
    
    /**
     * Constructor of TrashCommand.
     * 
     * @param label Command label
     */
    public TrashCommand(String label) {
        this.label = label;
    }
    
    /**
     * Gets the Map containing all commands of TrashCan.
     * 
     * @return Map containing all commands
     */
    public static Map<String, TrashCommand> getCommands() {
        return commands;
    }
    
    /**
     * Initializes all commands and adds them to the Map containing each command.
     * This way no if-elseif-statements are needed in CommandExecutors, as they
     * will simply scan through the Map for a matching command.
     */
    public static void initializeCommands() {
        // Administration commands
        commands.put("debug", new DebugCommand("debug"));
        commands.put("trashcan", new AboutCommand("trashcan"));
        
        // Fun commands
        commands.put("crossbow", new CrossbowCommand("crossbow"));
        commands.put("explosionbow", new ExplosionBowCommand("explosionbow"));
        commands.put("torchbow", new TorchbowCommand("torchbow"));
        commands.put("join", new JoinCommand("join"));
        commands.put("leave", new LeaveCommand("leave"));
        
        // Need to investigate the mysterious .getCommands() a bit more.
        // TODO: Set TrashCommandExecutor as executor for all commands nicely.
        System.out.println(TrashCan.getDescriptionFile().getCommands());
        
    }
    
    /**
     * Executes the command.
     * 
     * @param cs Command sender
     * @param args Command arguments
     * 
     * @return True if no usage information should be sent to the command sender.
     */
    public abstract boolean executeCommand(CommandSender cs, String[] args);
    
    /**
     * Sends command usage information to the command sender. Called by default if 
     * executeCommand() returns false.
     * 
     * @param cs Command sender
     */
    public abstract void sendUsage(CommandSender cs);
}
