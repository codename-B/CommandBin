package me.cain.commandbin.commands;

import me.cain.commandbin.CommandBin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;

public class MobCommands implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		Player sender = (Player) s;
		if(l.equalsIgnoreCase("spawnmob"))
		{
			if(args.length < 2)
			{
				sender.sendMessage("/" + l.toString() + " [mobname] [amount]");
				sender.sendMessage("Mobs: chicken, cow, pig, sheep, squid, enderman, pigman, wolf, cavespider, creeper, ghast, silverfish, skeleton, slime, spider, zombie, enderdragon");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.mob"))
				{
					if(CreatureType.valueOf(args[0].toUpperCase()) != null)
					{
							int mob = Integer.parseInt(args[1]);
							for(int i = 0; i < mob; i++)
							{
								sender.getWorld().spawnCreature(sender.getTargetBlock(null, 0).getLocation(), CreatureType.valueOf(args[0].toUpperCase()));
							}
							sender.sendMessage(ChatColor.GREEN + "Spawned a " + args[0]);
					}
					else
					{
						sender.sendMessage(ChatColor.RED + "This creature does not exist. (" + args[0] + ")");
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
