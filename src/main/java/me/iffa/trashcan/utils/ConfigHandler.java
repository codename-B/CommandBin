// Package Declaration
package me.iffa.trashcan.utils;

// Java Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;

// Bukkit Imports
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

/**
 * Utility class to handle loading the configuration file(s) and getting/setting
 * values.
 * 
 * @author iffamies
 */
public class ConfigHandler {
    // Variables
    private TrashCan plugin;
    private Map<ConfigFile, YamlConfiguration> config = new EnumMap<ConfigFile, YamlConfiguration>(ConfigFile.class);
    private Map<ConfigFile, File> file = new EnumMap<ConfigFile, File>(ConfigFile.class);
    private Map<ConfigFile, Boolean> loaded = new EnumMap<ConfigFile, Boolean>(ConfigFile.class);

    /**
     * Constructor of ConfigHandler.
     * 
     * @param plugin TrashCan instance
     */
    public ConfigHandler(TrashCan plugin) {
        this.plugin = plugin;
    }

    /**
     * Gets a configuration file.
     * 
     * @param configFile Configuration file to get
     * 
     * @return YamlConfiguration object of the config file, or null if the file
     * was not loaded properly. (ie not in Map containing YamlConfigurations)
     */
    public YamlConfiguration getConfig(ConfigFile configFile) {
        if (config.containsKey(configFile)) {
            return config.get(configFile);
        }
        return null;
    }

    /**
     * Loads or creates the configuration file(s). If the process was successful,
     * the configuration file is added to the Map containing all (loaded) config
     * files.
     */
    public void load() {
        for (ConfigFile configfile : ConfigFile.values()) {
            file.put(configfile, new File(plugin.getDataFolder(), configfile.getFileName()));
            if (file.get(configfile).exists()) {
                config.put(configfile, new YamlConfiguration());
                try {
                    config.get(configfile).load(file.get(configfile));
                } catch (FileNotFoundException ex) {
                    LoggerUtil.log(Level.WARNING, ex.toString());
                    loaded.put(configfile, false);
                    return;
                } catch (IOException ex) {
                    LoggerUtil.log(Level.WARNING, ex.toString());
                    loaded.put(configfile, false);
                    return;
                } catch (InvalidConfigurationException ex) {
                    LoggerUtil.log(Level.WARNING, ex.toString());
                    loaded.put(configfile, false);
                    return;
                }
                loaded.put(configfile, true);
            } else {
                try {
                    plugin.getDataFolder().mkdir();
                    InputStream jarURL = ConfigHandler.class.getResourceAsStream("/" + configfile.getFileName());
                    copyFile(jarURL, file.get(configfile));
                    config.put(configfile, new YamlConfiguration());
                    config.get(configfile).load(file.get(configfile));
                    loaded.put(configfile, true);
                    LoggerUtil.log(Level.INFO, "Created default configuration file '" + configfile.name() + "'.");
                } catch (Exception e) {
                    LoggerUtil.log(Level.SEVERE, "Problem while creating default configuration file: " + e.toString());
                }
            }
        }
    }

    /**
     * Reloads a configuration file. NOTE: This is the most hacky solution ever,
     * don't expect this to work 10 times out of 10!
     * 
     * @param configFile Configuration file to reload
     * 
     * @return True if reload was successful, false if something went wrong
     */
    public boolean reload(ConfigFile configFile) {
        InputStream defConfigStream = plugin.getResource(configFile.getFileName());
        if (defConfigStream != null) {
            try {
                config.get(configFile).load(defConfigStream);
                return true;
            } catch (IOException ex) {
                LoggerUtil.log(Level.WARNING, "Problem reloading configuration file: '" + ex.toString());
            } catch (InvalidConfigurationException ex) {
                LoggerUtil.log(Level.WARNING, "Problem reloading configuration file: '" + ex.toString());
            }
        }
        return false;
    }

    /**
     * Copies a file to a new location.
     * 
     * @param in InputStream (resource to copy)
     * @param out File to save as
     * 
     * @throws Exception
     */
    private void copyFile(InputStream in, File out) throws Exception {
        InputStream fis = in;
        FileOutputStream fos = new FileOutputStream(out);
        try {
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * Enum containing all configuration files and their file names. This is used
     * for easy loading of configuration files. All you have to do is add the
     * file to this enum, and add the file itself to the .jar. load() will handle
     * the rest.
     */
    public enum ConfigFile {
        // Enums

        CONFIG("config.yml");
        // Variables
        private String file;

        /**
         * Constructor of ConfigFile.
         * 
         * @param file File name
         */
        ConfigFile(String file) {
            this.file = file;
        }

        /**
         * Gets the file associated with the enum.
         * 
         * @return File associated wiht the enum
         */
        public String getFileName() {
            return this.file;
        }
    }

    /* Getter methods */ // TODO: JavaDoc all of this down below
    public String getShutdownMessage() {
        return MessageUtil.parseColors(config.get(ConfigFile.CONFIG).getString("settings.shutdownmessage", "&cThe server is going down for shutdown!"));
    }

    public boolean getBroadcastKick() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.broadcastkick", true);
    }

    public boolean getBroadcastBan() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.broadcastban", true);
    }

    public String getMutedMessage() {
        return MessageUtil.parseColors(config.get(ConfigFile.CONFIG).getString("settings.playerismuted", "&cYou are muted, shh!"));
    }

    public String getNoCommandsMessage() {
        return MessageUtil.parseColors(config.get(ConfigFile.CONFIG).getString("settings.playercannotusecommands", "&cYou cannot use commands."));
    }

    public String getJoinMessage() {
        return MessageUtil.parseColors(config.get(ConfigFile.CONFIG).getString("settings.joinmessage", "&ehas joined the game"));
    }

    public String getLeaveMessage() {
        return MessageUtil.parseColors(config.get(ConfigFile.CONFIG).getString("settings.leavemessage", "&ehas left the game"));
    }

    public boolean getDropXPDeath() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.dropxpondeath", false);
    }

    public int getMaxMobAmount() {
        return config.get(ConfigFile.CONFIG).getInt("settings.spawn-mob-max-amount", 10);
    }

    public boolean getEnderGrief() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.endermangriefing", false);
    }

    public boolean getBlockTNT() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.protection.block-placing-tnt", true);
    }

    public boolean getBlockLava() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.protection.block-placing-lava", true);
    }

    public boolean getBlockCreeper() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.protection.block-creeper-explosions", true);
    }

    public boolean getCoal() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.orebroadcast.coalore", false);
    }

    public boolean getIron() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.orebroadcast.ironore", false);
    }

    public boolean getGold() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.orebroadcast.goldore", false);
    }

    public boolean getDiamond() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.orebroadcast.diamondore", false);
    }

    public boolean getRedstone() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.orebroadcast.redstoneore", false);
    }

    public boolean getLapis() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.orebroadcast.lapislazuliore", false);
    }

    public boolean getMineSpawners() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.mineablemobspawners", true);
    }

    public boolean getAdminPM() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.opscanseepms", true);
    }

    public String getMOTD() {
        return MessageUtil.parseColors(config.get(ConfigFile.CONFIG).getString("settings.message-of-the-day", "&7This is the default &6TrashCan&7 MOTD!"));
    }

    public boolean getCustomChat() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.customchat", true);
    }

    public int getBowExplosionRadius() {
        return config.get(ConfigFile.CONFIG).getInt("settings.bowexplosionradius", 3);
    }

    public boolean getEnderSpawnOnEgg() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.spawn-enderdragon-on-hitting-enderegg", true);
    }

    public boolean getTeleportOnThrow() {
        return config.get(ConfigFile.CONFIG).getBoolean("settings.teleport-on-egg-throw", true);
    }

    public boolean getExplosionBow(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".explosionbow", false);
    }

    public boolean getCrossbow(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".crossbow", false);
    }

    public boolean getUnlimited(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".unlimited", false);
    }

    public boolean getGodmode(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".godmode", false);
    }

    public boolean getTorchbow(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".torchbow", false);
    }

    public boolean getHandicapped(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".handicapped", false);
    }

    public boolean getMuted(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".muted", false);
    }

    public boolean getBanned(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".banned", false);
    }

    public boolean getSnowman(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".snowman", false);
    }

    public boolean getSmoke(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".smoke", false);
    }

    public boolean getFrozen(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".frozen", false);
    }

    public String getBanReason(Player player) {
        return config.get(ConfigFile.CONFIG).getString(player.getName() + ".banreason", "Unspecified");
    }

    public boolean getExplosionStick(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".explosionstick", false);
    }

    public boolean getLightningStick(Player player) {
        return config.get(ConfigFile.CONFIG).getBoolean(player.getName() + ".lightning", false);
    }

    public String getNick(Player player) {
        return config.get(ConfigFile.CONFIG).getString(player.getName() + ".nickname", null);
    }

    public boolean isIpBanned(Player player) {
        String ipStrip = player.getAddress().getAddress().getHostAddress().replace(".", "");
        if (config.get(ConfigFile.CONFIG).getBoolean("bannedips." + ipStrip, false)) {
            return true;
        }
        return false;
    }

    /* Setter methods */
    /**
     * Sets the explosionbow enabled-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setExplosionBow(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".explosionbow", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }
    
    public void setBanned(boolean banned, Player player, String reason) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".banned", banned);
        config.get(ConfigFile.CONFIG).set(player.getName() + ".banreason", reason);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }

    /**
     * Sets the crossbow enabled-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setCrossbow(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".crossbow", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }
    
    /**
     * Sets the torchbow enabled-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setTorchbow(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".torchbow", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }

    /**
     * Sets the unlimited drops enabled-state for a player.
     * 
     * @param enabled Enabled true/false
     * @param player Player to set
     */
    public void setUnlimited(boolean enabled, Player player) {
        config.get(ConfigFile.CONFIG).set(player.getName() + ".unlimited", enabled);
        try {
            config.get(ConfigFile.CONFIG).save(file.get(ConfigFile.CONFIG));
        } catch (IOException ex) {
            LoggerUtil.log(Level.WARNING, "Problem while toggling player specific setting: " + ex.toString());
        }
    }
}
