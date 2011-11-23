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
 *                   BANANACODE PUBLIC LICENSE
 *                     Version 1, July 2011
 *
 * Copyright (C) 2011 Ben L. <nightgunner5@llamaslayers.net>
 *
 * Everyone is permitted to copy and distribute verbatim or modified
 * copies of this license document, and changing it is allowed as long
 * as the name is changed.
 *
 *                   BANANACODE PUBLIC LICENSE
 *  TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
 *
 * 0. Any binary distribution of this software or software derived from this
 *    software must be accompanied by source code. For example, this can be
 *    a link to a GIT repository, the inclusion of source code in the JAR,
 *    or a "source code" link on the same page as a "download" link.
 * 1. This license may not be removed from projects directly or indirectly
 *    derived from a project containing this license, unless it is replaced
 *    with a newer version of the BANANACODE PUBLIC LICENSE.
 * 2. All JavaDoc @author tags must be retained when Java code is copied from a
 *    file containing such tags. You may add your name in an additional @author
 *    tag, but not change the contents of existing @author tags.
 * 3. Releasing this software and claiming you are the original author is a
 *    violation of this license. Any such violations will be dealt with by
 *    one or more of the following: Murder, pulling your toenails out in slow
 *    motion, politely asking you to stop, stealing your first born, and/or
 *    legal action.
 * 
 * NOTE TO CAIN: I am not breaking your license. I have only taken the commands
 * from CommandBin, and implemented them myself.
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