package me.cain.commandbin.commands;

import me.cain.commandbin.CommandBin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdministrationCommands implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		Player sender = (Player) s;
		
		if(l.equalsIgnoreCase("cmdbin"))
		{
			if(args.length < 1)
			{
				sender.sendMessage(ChatColor.YELLOW + "+++++++++CommandBin++++++++++");
				sender.sendMessage(ChatColor.GREEN + "+ Over 60 commands in CommandBin!");
				sender.sendMessage(ChatColor.RED + "+ Version: " + CommandBin.plugin.getDescription().getVersion());
				sender.sendMessage(ChatColor.LIGHT_PURPLE + "+ Developer: " + CommandBin.plugin.getDescription().getAuthors());
				sender.sendMessage(ChatColor.YELLOW + "+++++++++++++++++++++++++++++");
			}
			else
			{
				if(args[0].equalsIgnoreCase("reload"))
				{
					if(CommandBin.plugin.pCheck(sender, "CommandBin.admin.reload"))
					{
						CommandBin.plugin.reloadConfig();
						sender.sendMessage(ChatColor.GREEN + "CommandBin Configuration Reloaded!");
					}

					else
					{
						sender.sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				
				if(args[0].equalsIgnoreCase("credits"))
				{
					sender.sendMessage(ChatColor.YELLOW + " - CommandBin Credits");
					sender.sendMessage(ChatColor.GREEN + "Author/Developer/Coder: CainFoool");
					sender.sendMessage(ChatColor.RED + "Tester/Debugger: vicente947");
				}
				
				if(args[0].equalsIgnoreCase("debug"))
				{
					if(CommandBin.plugin.pCheck(sender, "CommandBin.admin.debug"))
					{
						Runtime rt = Runtime.getRuntime();
						double max = Math.floor(rt.maxMemory() / 1024.0 / 1024.0);
						double free = Math.floor(rt.freeMemory() / 1024.0 / 1024.0);
						sender.sendMessage(ChatColor.RED + "" + free + " / " + max + " memory available"); // Don't ask about the ""
					}
					else
					{
						sender.sendMessage(CommandBin.plugin.NoPermission);
					}
				}
			}
		}
		return false;
	}

}
