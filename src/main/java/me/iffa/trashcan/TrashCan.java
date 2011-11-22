// Package Declaration
package me.iffa.trashcan;

// Java Imports
import java.util.logging.Level;

// TrashCan Imports
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.listeners.TrashBlockListener;
import me.iffa.trashcan.listeners.TrashEntityListener;
import me.iffa.trashcan.listeners.TrashPlayerListener;
import me.iffa.trashcan.utils.ConfigHandler;
import me.iffa.trashcan.utils.LoggerUtil;

// Bukkit Imports
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


/**
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Main class of TrashCan, a (Craft)Bukkit plugin originally made by "CainFool", 
 * who does not know any proper Java and will never learn that either. Consider 
 * this plugin a Craftbukkit for CommandBin. Every time CommandBin receives a
 * useful update, TrashCan will update ASAP to include the new features, and 
 * probably some other fixes.
 * 
 * @author iffamies
 * @author Cain (because I feel sorry for you)
 */
public class TrashCan extends JavaPlugin {
    // Variables
    private static ConfigHandler configHandler;
    private static PluginDescriptionFile description;
    private static String prefix = "[TrashCan]";
    private boolean panicDisabled = false;
    private final TrashBlockListener blockListener = new TrashBlockListener(this);
    private final TrashEntityListener entityListener = new TrashEntityListener(this);
    private final TrashPlayerListener playerListener = new TrashPlayerListener();

    /**
     * @see org.bukkit.plugin.Plugin
     */
    @Override
    public void onEnable() {
        // Checking for CommandBin on the server and panic disabling if it's found.
        if (getServer().getPluginManager().getPlugin("CommandBin") != null) {
            LoggerUtil.log(Level.WARNING, "TrashCan does not want CommandBin running on the server. Disabling.");
            panicDisabled = true;
            return;
        }
        // Continuing normal enabling process.
        initializeVariables();
        // Loading the configuration file(s).
        configHandler.load();
        // Registering events & commands (TrashCommand).
        registerEvents();
        TrashCommand.initializeCommands();
        // We're done here! Wohoo!
        LoggerUtil.log(Level.INFO, "Enabled version " + description.getVersion() + ".");
        
    }
    
    /**
     * @see org.bukkit.plugin.Plugin
     */
    @Override
    public void onDisable() {
        // Checking if we had to panic disable because of CommandBin.
        if (panicDisabled) {
            return;
        }
        // Continuing normal disabling process.
        LoggerUtil.log(Level.INFO, "Disabled version " + description.getVersion() + ".");
    }
    
    /**
     * Initializes variables for the plugin.
     */
    private void initializeVariables() {
        description = getDescription();
        configHandler = new ConfigHandler(this);
    }
    
    /**
     * Registers events for the plugin.
     */
    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        
        // Block listener
        pm.registerEvent(Event.Type.BLOCK_PLACE, blockListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.BLOCK_BREAK, blockListener, Event.Priority.Normal, this);
        
        // Entity listener
        pm.registerEvent(Event.Type.ENTITY_DAMAGE, entityListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.ENTITY_EXPLODE, entityListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PROJECTILE_HIT, entityListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.ENDERMAN_PICKUP, entityListener, Event.Priority.Normal, this);
        
        // Player listener
        pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_CHAT, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_LOGIN, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_JOIN, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_QUIT, playerListener, Event.Priority.Normal, this);
        pm.registerEvent(Event.Type.PLAYER_EGG_THROW, playerListener, Event.Priority.Normal, this);
    }
    
    /* Efficiency methods */
    
    /**
     * Gets a command of TrashCan by looking for it in the Map containing all
     * commands.
     * 
     * @param command Command to look for
     * 
     * @return TrashCommand object or null if the command was not found
     */
    public static TrashCommand matchCommand(String command) {
        if (TrashCommand.getCommands().containsKey(command)) {
            return TrashCommand.getCommands().get(command);
        }
        return null;
    }
    
    /**
     * Gets the plugin's prefix to be used in log messages etc.
     * 
     * @return Plugin's prefix
     */
    public static String getPrefix() {
        return prefix;
    }
    
    /**
     * Gets the ConfigHandler object.
     * 
     * @return Config handler
     */
    public static ConfigHandler getConfigHandler() {
        return configHandler;
    }
    
    /**
     * Gets the description file of TrashCan. Can be used to get things such as
     * version and authors.
     * 
     * @return Plugin description file
     */
    public static PluginDescriptionFile getDescriptionFile() {
        return description;
    }
}