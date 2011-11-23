package me.cain.commandbin.commands;

import me.cain.commandbin.CommandBin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CrossBowCommands implements CommandExecutor
{
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		Player sender = (Player) s;
		
		if(l.equalsIgnoreCase("crossbow"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [on/off]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.crossbow"))
				{
					if(args[0].equalsIgnoreCase("on"))
					{
						CommandBin.plugin.getConfig().set(sender.getName() + ".crossbow", true);
						sender.sendMessage(ChatColor.AQUA + "Crossbow enabled!");
						sender.sendMessage("Shoot with a bow!");
						CommandBin.plugin.saveConfig();
					}
					if(args[0].equalsIgnoreCase("off"))
					{
						CommandBin.plugin.getConfig().set(sender.getName() + ".crossbow", false);
						sender.sendMessage(ChatColor.RED + "Crossbow disabled!");
						CommandBin.plugin.saveConfig();
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
