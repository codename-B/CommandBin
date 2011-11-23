package me.iffa.trashcan.commands;

import me.iffa.trashcan.TrashCan;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World.Environment;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WorldCommands implements CommandExecutor
{

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		Player sender = (Player) s;
		
		if(l.equalsIgnoreCase("createworld"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [worldname]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.world.create"))
				{
					sender.sendMessage(ChatColor.RED + "World creation in progress.. please wait.");
					Bukkit.getServer().createWorld(args[0], Environment.NORMAL);
					sender.sendMessage(ChatColor.GREEN + "New world '" + args[0] + "' created!");
				}
				else
				{
					sender.sendMessage(TrashCan.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("unloadworld"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [worldname]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.world.unload"))
				{
					Bukkit.getServer().unloadWorld(args[0], true);
					sender.sendMessage(ChatColor.GREEN + "'" + args[0] + "' unloaded successfully!");
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