package me.iffa.trashcan.commands;

import me.iffa.trashcan.TrashCan;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeCommands implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		Player sender = (Player) s;
		
		if(l.equalsIgnoreCase("sethome"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [home-name]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.home"))
				{
					double x = sender.getLocation().getX();
					double y = sender.getLocation().getY();
					double z = sender.getLocation().getZ();
					World world = sender.getWorld();
					
					TrashCan.plugin.getConfig().set(sender.getName() + ".home." + args[0] + ".x", x);
					TrashCan.plugin.getConfig().set(sender.getName() + ".home." + args[0] + ".y", y);
					TrashCan.plugin.getConfig().set(sender.getName() + ".home." + args[0] + ".z", z);
					TrashCan.plugin.getConfig().set(sender.getName() + ".home." + args[0] + ".world", world.getName());
					
					sender.sendMessage(ChatColor.GREEN + "Your new home '" + args[0] + "' is set.");
					sender.sendMessage(ChatColor.YELLOW + "Type '/home " + args[0] + "' to teleport");
					
					TrashCan.plugin.saveConfig();
					
				}
				else
				{
					sender.sendMessage(TrashCan.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("home"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [home-name]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.home"))
				{
					if(TrashCan.plugin.getConfig().get(sender.getName() + ".home") != null)
					{
						if(TrashCan.plugin.getConfig().get(sender.getName() + ".home." + args[0]) != null)
						{
							
							double x = TrashCan.plugin.getConfig().getDouble(sender.getName() + ".home." + args[0] + ".x");
							double y = TrashCan.plugin.getConfig().getDouble(sender.getName() + ".home." + args[0] + ".y");
							double z = TrashCan.plugin.getConfig().getDouble(sender.getName() + ".home." + args[0] + ".z");
							String world = (String) TrashCan.plugin.getConfig().get(sender.getName() + ".home." + args[0] + ".world");
							
							sender.teleport(new Location(Bukkit.getServer().getWorld(world), x, y, z));
							sender.sendMessage(ChatColor.GREEN + "Teleported to your home '" + args[0] + "' !");
						}
						else
						{
							sender.sendMessage(ChatColor.RED + "This home '" + args[0] + "' does not exist!");
						}
					}
					else
					{
						sender.sendMessage(ChatColor.RED + "You have no home! Type /sethome [homename] to set it!");
					}
				}
				else
				{
					sender.sendMessage(TrashCan.plugin.NoPermission);
				}
			}
		}
		return false;
	}

}
