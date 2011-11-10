package me.cain.commandbin.commands;

import me.cain.commandbin.CommandBin;

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
			if(CommandBin.plugin.pCheck(sender, "CommandBin.general.weather"))
			{
				sender.getWorld().setStorm(false);
				sender.sendMessage(ChatColor.GREEN + "It is now sunny!");
			}
			else
			{
				sender.sendMessage(CommandBin.plugin.NoPermission);
			}
		}
		
		if(l.equalsIgnoreCase("rain"))
		{
			if(CommandBin.plugin.pCheck(sender, "CommandBin.general.weather"))
			{
				sender.getWorld().setStorm(true);
				sender.getWorld().setThundering(true);
				sender.sendMessage(ChatColor.GREEN + "It is now raining!");
			}
			else
			{
				sender.sendMessage(CommandBin.plugin.NoPermission);
			}
		}
		// TODO Auto-generated method stub
		return false;
	}

}
