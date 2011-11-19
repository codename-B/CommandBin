package me.cain.commandbin.commands;

import me.cain.commandbin.CommandBin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HelpCommands implements CommandExecutor
{
	
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		Player sender = (Player) s;
		
		if(l.equalsIgnoreCase("cbhelp"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/cbhelp [page-number]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.help"))
				{
					if(args[0].equalsIgnoreCase("1"))
					{
						sender.sendMessage("/tp [player] - Teleport to a player");
						sender.sendMessage("/tphere [player] - Teleport a player to you");
						sender.sendMessage("/setspawn - Set the world spawn");
						sender.sendMessage("/tpworld [world] - Teleport to a world");
						sender.sendMessage("/spawn - Teleport to the world spawn");
						sender.sendMessage("/tpall - Teleport all players to you");
						sender.sendMessage("/createworld [world] - Create a new world");
						sender.sendMessage("/unloadworld [world] - Unload a world");
					}
					if(args[0].equalsIgnoreCase("2"))
					{
						sender.sendMessage("/shutdown - Shut the server down");
						sender.sendMessage("/commandbin - CommandBin");
						sender.sendMessage("/join - A fake join message");
						sender.sendMessage("/leave - A fake leave message");
						sender.sendMessage("/ping - Pong!");
						sender.sendMessage("/creative - Set your mode to creative");
						sender.sendMessage("/survival - Set your mode to survival");
						sender.sendMessage("/shoot [player] - Shoots a player into the air");
					}
					if(args[0].equalsIgnoreCase("3"))
					{
						sender.sendMessage("/time [day/night] - Set your server time");
						sender.sendMessage("/rain - Make it rain");
						sender.sendMessage("/sun - Make the sun shine");
						sender.sendMessage("/kick [player] [reason] - Self explanotory");
						sender.sendMessage("/ban [player] [reason] - Ban a player");
						sender.sendMessage("/unban [player] - Unban a player");
						sender.sendMessage("/feed - Feed a player to full food bar");
						sender.sendMessage("/heal - Give yourself/others full health");
					}
					if(args[0].equalsIgnoreCase("4"))
					{
						sender.sendMessage("/freeze [player] - Stop a player from moving");
						sender.sendMessage("/unfreeze [player] - Reallow a player to move");
						sender.sendMessage("/facepalm - Nuff said.");
						sender.sendMessage("/god [on/off] - Enable/disable godmode");
						sender.sendMessage("/snowman - Enable Snowman Mode!");
						sender.sendMessage("/unsnowman - Disable Snowman Mode");
						sender.sendMessage("/explode [player] - Explode a player");
						sender.sendMessage("/light [player] - Set a player on fire");
					}
					if(args[0].equalsIgnoreCase("5"))
					{
						sender.sendMessage("/roll [x] [y] - Roll a random number");
						sender.sendMessage("/handicap [player] - Stop a player from using commands");
						sender.sendMessage("/unhandicap [player] - Reallow a player to use command");
						sender.sendMessage("/mute [player] - Mute a plyer");
						sender.sendMessage("/unmute [player] - Unmute a player");
						sender.sendMessage("/smoke [on/off] - Smoke particles around you");
						sender.sendMessage("/explosionstick [on/off] - The explosion stick!");
						sender.sendMessage("/lightningstick [on/off] - The lightning stick!");
					}
					if(args[0].equalsIgnoreCase("6"))
					{
						sender.sendMessage("/slap [player] - Slap a player");
						sender.sendMessage("/who [player] - Information about a player");
						sender.sendMessage("/clear - Clear your inventory");
						sender.sendMessage("/spawnmob [mob] [amount] - Spawn mobs");
						sender.sendMessage("/i [item] [amount] - Give yourself items");
						sender.sendMessage("/msg [player] [message] - Message a player");
						sender.sendMessage("/sethome [homename] - Set your home");
						sender.sendMessage("/home [homename] - Teleport to your home");
					}
					if(args[0].equalsIgnoreCase("7"))
					{
						sender.sendMessage("/setwarp [warp] - Set a warp");
						sender.sendMessage("/warp [name] - Teleport to a warp");
						sender.sendMessage("/delwarp [name] - Remove a warp");
						sender.sendMessage("/nick [player] [nickname] - Set a player's nickname");
						sender.sendMessage("/setxp [player] [xp] - Set your experience points");
						sender.sendMessage("/armour [iron/gold/diamond/leather/chainmail] - Nuff said.");
						sender.sendMessage("/kill [player] - Kill a player");
						sender.sendMessage("/up [amount] - Go up [x] amount of blocks");
					}
					if(args[0].equalsIgnoreCase("8"))
					{
						sender.sendMessage("/unlimited - Place unlimited blocks");
						sender.sendMessage("/delunlimited - Stop placement of unlimited blocks");
						sender.sendMessage("/crossbow - The crossbow");
						sender.sendMessage("/tp2p - Teleport one player to another");
						sender.sendMessage("/put - Put a player in the location your looking at");
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
