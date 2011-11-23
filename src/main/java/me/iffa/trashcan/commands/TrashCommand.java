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
import me.iffa.trashcan.commands.admin.PaidCommand;
import me.iffa.trashcan.commands.admin.WhoCommand;
import me.iffa.trashcan.commands.fun.CrossbowCommand;
import me.iffa.trashcan.commands.fun.ExplosionBowCommand;
import me.iffa.trashcan.commands.fun.FacepalmCommand;
import me.iffa.trashcan.commands.fun.JoinCommand;
import me.iffa.trashcan.commands.fun.LeaveCommand;
import me.iffa.trashcan.commands.fun.TorchbowCommand;
import me.iffa.trashcan.commands.general.CreativeCommand;
import me.iffa.trashcan.commands.general.MOTDCommand;
import me.iffa.trashcan.commands.general.MeCommand;
import me.iffa.trashcan.commands.general.SpawnmobCommand;
import me.iffa.trashcan.commands.general.SurvivalCommand;
import me.iffa.trashcan.commands.moderator.BanCommand;
import me.iffa.trashcan.commands.moderator.BanIPCommand;
import me.iffa.trashcan.commands.moderator.FreezeCommand;
import me.iffa.trashcan.commands.moderator.KickCommand;
import me.iffa.trashcan.commands.moderator.MuteCommand;
import me.iffa.trashcan.commands.moderator.UnbanCommand;
import me.iffa.trashcan.commands.moderator.UnbanIPCommand;

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
        commands.put("paid", new PaidCommand("paid"));
        commands.put("who", new WhoCommand("who"));
        
        // General commands
        commands.put("motd", new MOTDCommand("motd"));
        commands.put("me", new MeCommand("me"));
        commands.put("creative", new CreativeCommand("creative"));
        commands.put("survival", new SurvivalCommand("survival"));
        commands.put("spawnmob", new SpawnmobCommand("spawnmob"));
        
        // Fun commands
        commands.put("crossbow", new CrossbowCommand("crossbow"));
        commands.put("explosionbow", new ExplosionBowCommand("explosionbow"));
        commands.put("torchbow", new TorchbowCommand("torchbow"));
        commands.put("join", new JoinCommand("join"));
        commands.put("leave", new LeaveCommand("leave"));
        commands.put("facepalm", new FacepalmCommand("facepalm"));
        
        // Moderating commands
        commands.put("kick", new KickCommand("kick"));
        commands.put("ban", new BanCommand("ban"));
        commands.put("mute", new MuteCommand("mute"));
        commands.put("unban", new UnbanCommand("unban"));
        commands.put("unbanip", new UnbanIPCommand("unbanip"));
        commands.put("freeze", new FreezeCommand("freeze"));
        commands.put("banip", new BanIPCommand("banip"));
        
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
