package me.cain.commandbin.commands;

import me.cain.commandbin.CommandBin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommands implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		Player sender = (Player) s;
		
		if(l.equalsIgnoreCase("setwarp"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [warpname]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.setwarp"))
				{
					double x = sender.getLocation().getX();
					double y = sender.getLocation().getY();
					double z = sender.getLocation().getZ();
					World world = sender.getWorld();
					
					if(!((CommandBin.plugin.getConfig().getString("settings.warps." + args[0])) != null))
					{
						CommandBin.plugin.getConfig().set("settings.warps." + args[0] + ".x", x);
						CommandBin.plugin.getConfig().set("settings.warps." + args[0] + ".y", y);
						CommandBin.plugin.getConfig().set("settings.warps." + args[0] + ".z", z);
						CommandBin.plugin.getConfig().set("settings.warps." + args[0] + ".world", world.getName());
						CommandBin.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "Warp '" + args[0] + "' created.");
						sender.sendMessage(ChatColor.GREEN + "Use /warp " + args[0] + " to teleport!");
					}
					else
					{
						sender.sendMessage(ChatColor.RED + "Warp '" + args[0] + "' already exists!");
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("warp"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [warpname]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.warp"))
				{
					if(CommandBin.plugin.getConfig().get("settings.warps." + args[0]) != null)
					{
						double x = CommandBin.plugin.getConfig().getDouble("settings.warps." + args[0] + ".x");
						double y = CommandBin.plugin.getConfig().getDouble("settings.warps." + args[0] + ".y");
						double z = CommandBin.plugin.getConfig().getDouble("settings.warps." + args[0] + ".z");
						String world = (String) CommandBin.plugin.getConfig().get("settings.warps." + args[0] + ".world");
						
						sender.teleport(new Location(Bukkit.getServer().getWorld(world), x,y,z));
						sender.sendMessage(ChatColor.GREEN + "Teleported to warp '" + args[0] + "' successfully!");
					}
					else
					{
						sender.sendMessage(ChatColor.RED + "This warp does not exist.");
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("delwarp"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [warpname]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.delwarp"))
				{
					if(!(CommandBin.plugin.getConfig().get("settings.warps." + args[0]) == null))
					{
						CommandBin.plugin.getConfig().set("settings.warps." + args[0], null);
						sender.sendMessage(ChatColor.GREEN + "Warp '" + args[0] + "' removed!");
						CommandBin.plugin.saveConfig();
					}
					else
					{
						sender.sendMessage(ChatColor.RED + "This warp does not exist");
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		return false;
	}

}
