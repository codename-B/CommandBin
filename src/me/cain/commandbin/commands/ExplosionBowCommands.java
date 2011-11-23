package me.cain.commandbin.commands;

import me.cain.commandbin.CommandBin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ExplosionBowCommands implements CommandExecutor
{
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		Player sender = (Player) s;
		
		if(l.equalsIgnoreCase("explosionbow"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [on/off]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.explosionbow"))
				{
					if(args[0].equalsIgnoreCase("on"))
					{
						CommandBin.plugin.getConfig().set(sender.getName() + ".explosionbow", true);
						sender.sendMessage(ChatColor.AQUA + "Explosion Bow Enabled!");
						sender.sendMessage("Shoot with a bow as normal!");
						CommandBin.plugin.saveConfig();
					}
					
					if(args[0].equalsIgnoreCase("off"))
					{
						CommandBin.plugin.getConfig().set(sender.getName() + ".explosionbow", false);
						sender.sendMessage(ChatColor.AQUA + "Explosion Bow Disabled!");
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
