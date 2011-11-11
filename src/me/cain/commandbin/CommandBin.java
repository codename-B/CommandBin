package me.cain.commandbin;

import me.cain.commandbin.commands.AdministrationCommands;
import me.cain.commandbin.commands.DebugCommands;
import me.cain.commandbin.commands.FakeJoinLeaveCommands;
import me.cain.commandbin.commands.GameModeCommands;
import me.cain.commandbin.commands.HomeCommands;
import me.cain.commandbin.commands.MobCommands;
import me.cain.commandbin.commands.ModerationCommands;
import me.cain.commandbin.commands.PlayerCommands;
import me.cain.commandbin.commands.SnowmanCommands;
import me.cain.commandbin.commands.WarpCommands;
import me.cain.commandbin.commands.WeatherCommands;
import me.cain.commandbin.commands.WorldCommands;
import me.cain.commandbin.listeners.BListener;
import me.cain.commandbin.listeners.EListener;
import me.cain.commandbin.listeners.EnderListener;
import me.cain.commandbin.listeners.PListener;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class CommandBin extends JavaPlugin {
	
	public String Plugin = "[CommandBin] ";
	public static CommandBin plugin;
	public static PermissionHandler permissionHandler;
	
	public void onEnable()
	{
				System.out.println(Plugin + "Enabled successfully.");
				getServer().getPluginManager().registerEvent(Type.PLAYER_MOVE, new PListener(), Priority.Normal, this);
				getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, new PListener(), Priority.Normal, this);
				getServer().getPluginManager().registerEvent(Type.ENTITY_DAMAGE, new EListener(), Priority.Normal, this);
				getServer().getPluginManager().registerEvent(Type.PLAYER_COMMAND_PREPROCESS, new PListener(), Priority.Normal, this);
				getServer().getPluginManager().registerEvent(Type.PLAYER_CHAT, new PListener(), Priority.Normal, this);
				getServer().getPluginManager().registerEvent(Type.PLAYER_LOGIN, new PListener(), Priority.Normal, this);
				getServer().getPluginManager().registerEvent(Type.PLAYER_JOIN, new PListener(), Priority.Normal, this);
				getServer().getPluginManager().registerEvent(Type.PLAYER_QUIT, new PListener(), Priority.Normal, this);
				getServer().getPluginManager().registerEvent(Type.ENTITY_DEATH, new EListener(), Priority.Normal, this);
				getServer().getPluginManager().registerEvent(Type.ENDERMAN_PICKUP, new EnderListener(), Priority.Normal, this);
				getServer().getPluginManager().registerEvent(Type.ENDERMAN_PLACE, new EnderListener(), Priority.Normal, this);
				getServer().getPluginManager().registerEvent(Type.PLAYER_TOGGLE_SPRINT, new PListener(), Priority.Normal, this);
				getServer().getPluginManager().registerEvent(Type.PLAYER_RESPAWN, new PListener(), Priority.Normal, this);
				getServer().getPluginManager().registerEvent(Type.PLAYER_GAME_MODE_CHANGE, new PListener(), Priority.Normal, this);
				getServer().getPluginManager().registerEvent(Type.BLOCK_PLACE, new BListener(), Priority.Normal, this);
				setupPermissions();
				SetupCommands();
				plugin = this;
				ConfigurationDefaultSetup();
	}
	
	public void ConfigurationDefaultSetup()
	{
		getConfig().options().header(
				"###############################\n" + 
				"Default Configuration File for CommandBin \n" +
				"CommandBin was created by CainFoool\n" +
				"All the values generated below are default and are fully editable.\n" +
				"Feel free to edit them as you plead.\n" +
				"Every time this plugin starts up, this text\n" +
				"Will be generated every time (sorry :p)" +
				"\n\n" +
				"CainFoool\n" +
				"###############################\n\n");
		
		if(getConfig().getString("settings.shutdownmessage", null) == null)
		{
			getConfig().set("settings.shutdownmessage", "The server is going down for shutdown!");
			System.out.println(Plugin + "[Config] Setting default shutdown message");
			System.out.println(Plugin + "[Config] Current Shutdown Value: " + getConfig().getString("settings.shutdownmessage"));
			saveConfig();
			System.out.println(Plugin + "[Config] New Shutdown Value: " + getConfig().getString("settings.shutdownmessage"));
		}
		
		if(getConfig().get("settings.broadcastkick") == null)
		{
			getConfig().set("settings.broadcastkick", true);
			System.out.println(Plugin + "[Config] Setting default broadcast on kick");
			saveConfig();
		}
		
		if(getConfig().get("settings.broadcastban") == null)
		{
			getConfig().set("settings.broadcastban", true);
			System.out.println(Plugin + "[Config] Setting default broadcast on ban");
			saveConfig();
		}
		
		if(getConfig().getString("settings.playerismuted", null) == null)
		{
			getConfig().set("settings.playerismuted", "You are muted, shh!");
			System.out.println(Plugin + "[Config] Setting default Player is Muted message");
			saveConfig();
		}
		
		if(getConfig().getString("settings.playercannotusecommands", null) == null)
		{
			getConfig().set("settings.playercannotusecommands", "You cannot use commands. Stop trying!");
			System.out.println(Plugin + "[Config] Setting default player cannot use commands message");
			saveConfig();
		}
		
		if(getConfig().getString("settings.joinmessage", null) == null)
		{
			getConfig().set("settings.joinmessage", "has joined the game");
			System.out.println(Plugin + "[Config] Setting default Join Message");
			saveConfig();
		}
		
		if(getConfig().getString("settings.leavemessage", null) == null)
		{
			getConfig().set("settings.leavemessage", "has left the game");
			System.out.println(Plugin + "[Config] Setting default Leave Message");
			saveConfig();
		}
		
		if(getConfig().get("settings.dropxpondeath") == null)
		{
			getConfig().set("settings.dropxpondeath", false);
			System.out.println(Plugin + "[Config] Setting default Drop XP on Death");
			saveConfig();
		}
		
		if(getConfig().getString("settings.craftbukkitversion", null) == null)
		{
			getConfig().set("settings.craftbukkitversion", getServer().getVersion().toString());
			System.out.println(Plugin + "[Config] Setting default Craftbukkit Version");
			saveConfig();
		}
		
		if(getConfig().getString("settings.consolename", null) == null)
		{
			getConfig().set("settings.consolename", "Console");
			System.out.println(Plugin + "[Config] Setting default Console Name");
			saveConfig();
		}
		
		if(getConfig().get("settings.endermangriefing") == null)
		{
			getConfig().set("settings.endermangriefing", false);
			System.out.println(Plugin + "[Config] Setting default Enderman can grief to false");
			saveConfig();
		}
		
		if(getConfig().get("settings.allowsprinting") == null)
		{
			getConfig().set("settings.allowsprinting", false);
			System.out.println(Plugin + "[Config] Setting default allow sprinting [beta]");
			saveConfig();
		}
		
		if(getConfig().get("settings.block-placing-tnt") == null)
		{
			getConfig().set("settings.block-placing-tnt", true);
			System.out.println(Plugin + "[Config] Setting default block placing of tnt");
			saveConfig();
		}
		
		if(getConfig().get("settings.block-placing-lava") == null)
		{
			getConfig().set("settings.block-placing-lava", true);
			System.out.println(Plugin + "[Config] Setting default block placing of lava");
			saveConfig();
		}
		
	}
	public void SetupCommands()
	{
		
		// Teleportation Commands
		CommandExecutor TeleporterCommands = new PlayerCommands();
		getCommand("tp").setExecutor(TeleporterCommands);
		getCommand("tphere").setExecutor(TeleporterCommands);
		getCommand("tpall").setExecutor(TeleporterCommands);
		getCommand("tpworld").setExecutor(TeleporterCommands);
		getCommand("spawn").setExecutor(TeleporterCommands);
		getCommand("setspawn").setExecutor(TeleporterCommands);
		//
		
		// World Commands
		CommandExecutor WorldCommands = new WorldCommands();
		getCommand("createworld").setExecutor(WorldCommands);
		getCommand("unloadworld").setExecutor(WorldCommands);
		//
		
		// Administration Commands
		CommandExecutor AdminCommands = new AdministrationCommands();
		getCommand("shutdown").setExecutor(AdminCommands);
		getCommand("commandbin").setExecutor(AdminCommands);
		//
		
		// Fake Join/Leave Commands
		CommandExecutor FakeJL = new FakeJoinLeaveCommands();
		getCommand("join").setExecutor(FakeJL);
		getCommand("leave").setExecutor(FakeJL);
		//
		
		// Debug Commands
		CommandExecutor Debug = new DebugCommands();
		getCommand("ping").setExecutor(Debug);
		//
		
		// Game Mode Commands
		CommandExecutor GM = new GameModeCommands();
		getCommand("creative").setExecutor(GM);
		getCommand("survival").setExecutor(GM);
		//
		
		// Weather Control Commands
		CommandExecutor WeatherControl = new WeatherCommands();
		getCommand("rain").setExecutor(WeatherControl);
		getCommand("sun").setExecutor(WeatherControl);
		//
		
		// Player Commands
		CommandExecutor PlayerCommands = new PlayerCommands();
		getCommand("shoot").setExecutor(PlayerCommands);
		getCommand("strike").setExecutor(PlayerCommands);
		getCommand("heal").setExecutor(PlayerCommands);
		getCommand("feed").setExecutor(PlayerCommands);
		getCommand("god").setExecutor(PlayerCommands);
		getCommand("facepalm").setExecutor(PlayerCommands);
		getCommand("explode").setExecutor(PlayerCommands);
		getCommand("light").setExecutor(PlayerCommands);
		getCommand("roll").setExecutor(PlayerCommands);
		getCommand("smoke").setExecutor(PlayerCommands);
		getCommand("explosionstick").setExecutor(PlayerCommands);
		getCommand("lightningstick").setExecutor(PlayerCommands);
		getCommand("slap").setExecutor(PlayerCommands);
		getCommand("msg").setExecutor(PlayerCommands);
		getCommand("clear").setExecutor(PlayerCommands);
		getCommand("kill").setExecutor(PlayerCommands);
		getCommand("i").setExecutor(PlayerCommands);
		getCommand("time").setExecutor(PlayerCommands);
		getCommand("nick").setExecutor(PlayerCommands);
		getCommand("setxp").setExecutor(PlayerCommands);
		getCommand("up").setExecutor(PlayerCommands);
		getCommand("unlimited").setExecutor(PlayerCommands);
		getCommand("delunlimited").setExecutor(PlayerCommands);
		//
		
		// Snowman Commands
		CommandExecutor SnowmanCommands = new SnowmanCommands();
		getCommand("snowman").setExecutor(SnowmanCommands);
		getCommand("unsnowman").setExecutor(SnowmanCommands);
		//
		
		// Moderation Commands
		CommandExecutor ModCommands = new ModerationCommands();
		getCommand("kick").setExecutor(ModCommands);
		getCommand("ban").setExecutor(ModCommands);
		getCommand("unban").setExecutor(ModCommands);
		getCommand("freeze").setExecutor(ModCommands);
		getCommand("unfreeze").setExecutor(ModCommands);
		getCommand("handicap").setExecutor(ModCommands);
		getCommand("unhandicap").setExecutor(ModCommands);
		getCommand("mute").setExecutor(ModCommands);
		getCommand("unmute").setExecutor(ModCommands);
		getCommand("who").setExecutor(ModCommands);
		getCommand("say").setExecutor(ModCommands);
		//
		
		// Spawn Mob Commands
		CommandExecutor MobCommands = new MobCommands();
		getCommand("spawnmob").setExecutor(MobCommands);
		//
		
		// Home Commands
		CommandExecutor HomeCommands = new HomeCommands();
		getCommand("home").setExecutor(HomeCommands);
		getCommand("sethome").setExecutor(HomeCommands);
		//
		
		// Warp Commands
		CommandExecutor WarpCommands = new WarpCommands();
		getCommand("warp").setExecutor(WarpCommands);
		getCommand("setwarp").setExecutor(WarpCommands);
		getCommand("delwarp").setExecutor(WarpCommands);
		//
		
		
	}
	
	public void onDisable()
	{
		System.out.println(Plugin + "Disabled successfully.");
	}
	
	public boolean pCheck(Player player, String l)
	{
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
	    if (permissionsPlugin == null) {
	    	return player.isOp() || player.hasPermission(l);
	    }
	    else
	    {
	    	return permissionHandler.has(player, l);
	    }
	}
	
	private void setupPermissions() {
	    if (permissionHandler != null) {
	        return;
	    }
	    
	    Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
	    
	    if (permissionsPlugin == null) {
	        System.out.println(Plugin + "Permission system not detected, defaulting to OP");
	        return;
	    }
	    
	    permissionHandler = ((Permissions) permissionsPlugin).getHandler();
	    System.out.println(Plugin + "Found and will use plugin "+((Permissions)permissionsPlugin).getDescription().getFullName());
	}
	
	public String NoPermission = ChatColor.GRAY + "You have no permission to use this command.";
	public String PlayerOffline = ChatColor.RED + "This player is offline";

	
}