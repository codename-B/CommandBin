package me.cain.commandbin.config;

import me.cain.commandbin.CommandBin;

import org.bukkit.Bukkit;


public class ConfigSetup
{

	public static void start()
	{
		CommandBin.plugin.getConfig().options().header(
				"###############################\n" + 
				"Default Configuration File for CommandBin \n" +
				"CommandBin was created by CainFoool\n" +
				"All the values generated below are default and are fully editable.\n" +
				"Feel free to edit them as you plead.\n" +
				"Every time this plugin starts up, this text\n" +
				"Will be generated every time (sorry :p)" +
				"\n\n" +
				"CainFoool\n" +
				"###############################\n");
		
		if(CommandBin.plugin.getConfig().getString("settings.shutdownmessage", null) == null)
		{
			CommandBin.plugin.getConfig().set("settings.shutdownmessage", "The server is going down for shutdown!");
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default shutdown message");
			CommandBin.plugin.saveConfig();
		}
		
		if(CommandBin.plugin.getConfig().get("settings.broadcastkick") == null)
		{
			CommandBin.plugin.getConfig().set("settings.broadcastkick", true);
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default broadcast on kick");
			CommandBin.plugin.saveConfig();
		}
		
		if(CommandBin.plugin.getConfig().get("settings.broadcastban") == null)
		{
			CommandBin.plugin.getConfig().set("settings.broadcastban", true);
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default broadcast on ban");
			CommandBin.plugin.saveConfig();
		}
		
		if(CommandBin.plugin.getConfig().getString("settings.playerismuted", null) == null)
		{
			CommandBin.plugin.getConfig().set("settings.playerismuted", "You are muted, shh!");
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default Player is Muted message");
			CommandBin.plugin.saveConfig();
		}
		
		if(CommandBin.plugin.getConfig().getString("settings.playercannotusecommands", null) == null)
		{
			CommandBin.plugin.getConfig().set("settings.playercannotusecommands", "You cannot use commands. Stop trying!");
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default player cannot use commands message");
			CommandBin.plugin.saveConfig();
		}
		
		if(CommandBin.plugin.getConfig().getString("settings.joinmessage", null) == null)
		{
			CommandBin.plugin.getConfig().set("settings.joinmessage", "has joined the game");
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default Join Message");
			CommandBin.plugin.saveConfig();
		}
		
		if(CommandBin.plugin.getConfig().getString("settings.leavemessage", null) == null)
		{
			CommandBin.plugin.getConfig().set("settings.leavemessage", "has left the game");
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default Leave Message");
			CommandBin.plugin.saveConfig();
		}
		
		if(CommandBin.plugin.getConfig().get("settings.dropxpondeath") == null)
		{
			CommandBin.plugin.getConfig().set("settings.dropxpondeath", false);
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default Drop XP on Death");
			CommandBin.plugin.saveConfig();
		}
		
		if(CommandBin.plugin.getConfig().getString("settings.craftbukkitversion", null) == null)
		{
			CommandBin.plugin.getConfig().set("settings.craftbukkitversion", Bukkit.getServer().getVersion().toString());
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default Craftbukkit Version");
			CommandBin.plugin.saveConfig();
		}
		
		if(CommandBin.plugin.getConfig().getString("settings.consolename", null) == null)
		{
			CommandBin.plugin.getConfig().set("settings.consolename", "Console");
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default Console Name");
			CommandBin.plugin.saveConfig();
		}
		
		if(CommandBin.plugin.getConfig().get("settings.endermangriefing") == null)
		{
			CommandBin.plugin.getConfig().set("settings.endermangriefing", false);
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default Enderman can grief to false");
			CommandBin.plugin.saveConfig();
		}
		
		if(CommandBin.plugin.getConfig().get("settings.block-placing-tnt") == null)
		{
			CommandBin.plugin.getConfig().set("settings.block-placing-tnt", true);
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default block placing of tnt");
			CommandBin.plugin.saveConfig();
		}
		
		if(CommandBin.plugin.getConfig().get("settings.block-placing-lava") == null)
		{
			CommandBin.plugin.getConfig().set("settings.block-placing-lava", true);
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default block placing of lava");
			CommandBin.plugin.saveConfig();
		}
		
		if(CommandBin.plugin.getConfig().get("settings.teleportonthrowegg") == null)
		{
			CommandBin.plugin.getConfig().set("settings.teleportonthrowegg", false);
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default teleport on throw egg");
			CommandBin.plugin.saveConfig();
		}
		
		if(CommandBin.plugin.getConfig().get("settings.blockcreeperexplosions") == null)
		{
			CommandBin.plugin.getConfig().set("settings.blockcreeperexplosions", false);
			System.out.println(CommandBin.plugin.Plugin + "[Config] Setting default block creeper explosions");
			CommandBin.plugin.saveConfig();
		}
		
		
		
	}
	
}
