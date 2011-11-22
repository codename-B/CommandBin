package me.iffa.trashcan.commands;

import me.iffa.trashcan.TrashCan;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommands implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		Player sender = (Player) s;
		
		if(l.equalsIgnoreCase("creative"))
		{
			if(args.length < 1)
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.creative"))
				{
					sender.setGameMode(GameMode.CREATIVE);
					sender.sendMessage(ChatColor.GREEN + "You gamemode is now creative!");
				}
				else
				{
					sender.sendMessage(TrashCan.plugin.NoPermission);
				}
			}
			else
			{

				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.creative.others"))
				{
					if(target != null)
					{
						target.setGameMode(GameMode.CREATIVE);
						sender.sendMessage(ChatColor.GREEN + target.getName() + "'s gamemode is now Creative!");
					}
					else
					{
						sender.sendMessage(TrashCan.plugin.PlayerOffline);
					}
				}
				else
				{
					sender.sendMessage(TrashCan.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("survival"))
		{
			if(args.length < 1)
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.survival"))
				{
					sender.setGameMode(GameMode.SURVIVAL);
					sender.sendMessage(ChatColor.GREEN + "Your gamemode is now survival!");
				}
				else
				{
					sender.sendMessage(TrashCan.plugin.NoPermission);
				}
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.survival.others"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						target.setGameMode(GameMode.SURVIVAL);
						sender.sendMessage(ChatColor.GREEN + target.getName() + "'s gamemode is now Survival!");
					}
					else
					{
						sender.sendMessage(TrashCan.plugin.PlayerOffline);
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
