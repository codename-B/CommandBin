package me.iffa.trashcan.commands;

import me.iffa.trashcan.TrashCan;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WeatherCommands implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		Player sender = (Player) s;
		if(l.equalsIgnoreCase("sun"))
		{
			if(TrashCan.plugin.pCheck(sender, "CommandBin.general.weather"))
			{
				sender.getWorld().setStorm(false);
				Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + sender.getName() + " has made the sun shine!");
			}
			else
			{
				sender.sendMessage(TrashCan.plugin.NoPermission);
			}
		}
		
		if(l.equalsIgnoreCase("rain"))
		{
			if(TrashCan.plugin.pCheck(sender, "CommandBin.general.weather"))
			{
				sender.getWorld().setStorm(true);
				sender.getWorld().setThundering(true);
				Bukkit.getServer().broadcastMessage(ChatColor.GRAY + sender.getName() + " made it begin to rain!");
			}
			else
			{
				sender.sendMessage(TrashCan.plugin.NoPermission);
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

}
