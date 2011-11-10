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
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.home"))
				{
					double x = sender.getLocation().getX();
					double y = sender.getLocation().getY();
					double z = sender.getLocation().getZ();
					World world = sender.getWorld();
					
					CommandBin.plugin.getConfig().set(sender.getName() + ".home.x", x);
					CommandBin.plugin.getConfig().set(sender.getName() + ".home.y", y);
					CommandBin.plugin.getConfig().set(sender.getName() + ".home.z", z);
					CommandBin.plugin.getConfig().set(sender.getName() + ".home.world", world.getName());
					
					sender.sendMessage(ChatColor.GREEN + "Your new home is set.");
					sender.sendMessage(ChatColor.YELLOW + "Type /home to teleport");
					
					CommandBin.plugin.saveConfig();
					
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("home"))
		{
			if(args.length < 1)
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.home"))
				{
					if(CommandBin.plugin.getConfig().get(sender.getName() + ".home") != null)
					{
						
						double x = CommandBin.plugin.getConfig().getDouble(sender.getName() + ".home.x");
						double y = CommandBin.plugin.getConfig().getDouble(sender.getName() + ".home.y");
						double z = CommandBin.plugin.getConfig().getDouble(sender.getName() + ".home.z");
						String world = (String) CommandBin.plugin.getConfig().get(sender.getName() + ".home.world");
						
						sender.teleport(new Location(Bukkit.getServer().getWorld(world), x, y, z));
						sender.sendMessage(ChatColor.GREEN + "Teleported to your home!");
					}
					else
					{
						sender.sendMessage(ChatColor.RED + "You have no home! Type /sethome to set it!");
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
