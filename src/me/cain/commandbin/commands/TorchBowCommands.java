package me.cain.commandbin.commands;

import me.cain.commandbin.CommandBin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TorchBowCommands implements CommandExecutor {
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		Player sender = (Player) s;
		
		if(l.equalsIgnoreCase("torchbow"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [on/off]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "Commandbin.general.torchbow"))
				{
					if(args[0].equalsIgnoreCase("on"))
					{
						CommandBin.plugin.getConfig().set(sender.getName() + ".torchbow", true);
						CommandBin.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "Torchbow has been enabled.");
						sender.sendMessage(ChatColor.GREEN + "Shoot with a bow as normal!");
					}
					
					if(args[0].equalsIgnoreCase("off"))
					{
						CommandBin.plugin.getConfig().set(sender.getName() + ".torchbow", false);
						CommandBin.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "Torchbow has been disabled.");
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
