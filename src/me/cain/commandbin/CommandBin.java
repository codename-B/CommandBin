package me.cain.commandbin;

import me.cain.commandbin.commands.CommandRegistration;
import me.cain.commandbin.config.ConfigSetup;
import me.cain.commandbin.listeners.EventRegistration;
import me.cain.commandbin.statistics.Statistics;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class CommandBin extends JavaPlugin {
	
	public String Plugin = "[CommandBin] ";
	public static CommandBin plugin;
	public static PermissionHandler permissionHandler;
	public String NoPermission = ChatColor.GRAY + "You have no permission to use this command.";
	public String PlayerOffline = ChatColor.RED + "This player is offline";
	
/*	This is a public source however sharing is not allowed.
	This plugin will come from one link, and one link only:
		http://bit.ly/commandbin
	If you see any other link distributing this plugin,
	report it to me immediately.
	
	Thank you for using my plugin,
	- Cain Donaghey (CainFoool)
	
	CommandBin is a project created by CainFoool which provides
	Essentials Commands for Bukkit Servers. Copyright 2011-2012.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    */
	
	public void onEnable()
	{
		plugin = this;
		setupCommandBin();
		System.out.println(Plugin + "Enabled successfully.");
	}
	
	public void setupCommandBin()
	{
		EventRegistration.setup();
		setupPermissions();
		CommandRegistration.SetupCommands();
		ConfigSetup.start();
		Statistics.StartStats(); // Stats!
	}
	
	public void onDisable()
	{
		System.out.println(Plugin + "Disabled successfully.");
	}
	
	public boolean pCheck(Player player, String l)
	{
		Plugin permissionsPlugin = this.getServer().getPluginManager().getPlugin("Permissions");
	    if (permissionsPlugin == null) {
	    	return player.hasPermission(l) || player.isOp();
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
	        System.out.println(Plugin + "I could not detect any Permissions on your server. Defaulting to OP!");
	        return;
	    }
	    
	    permissionHandler = ((Permissions) permissionsPlugin).getHandler();
	    System.out.println(Plugin + "Permissions Plugin Found and will use plugin "+((Permissions)permissionsPlugin).getDescription().getFullName() + "!");
	}
}