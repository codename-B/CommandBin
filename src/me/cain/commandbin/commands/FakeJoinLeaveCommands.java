package me.cain.commandbin.commands;

import me.cain.commandbin.CommandBin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FakeJoinLeaveCommands implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		Player sender = (Player) s;
		if(l.equalsIgnoreCase("join"))
		{
			if(CommandBin.plugin.pCheck(sender, "CommandBin.general.join"))
			{
				Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + sender.getName() + " has joined the game");
			}
			else
			{
				sender.sendMessage(CommandBin.plugin.NoPermission);
			}
		}
		
		if(l.equalsIgnoreCase("leave"))
		{
			if(CommandBin.plugin.pCheck(sender, "CommandBin.general.leave"))
			{
				Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + sender.getName() + " has left the game");
			}
			else
			{
				sender.sendMessage(CommandBin.plugin.NoPermission);
			}
		}
		return false;
	}

}
