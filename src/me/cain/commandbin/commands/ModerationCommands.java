package me.cain.commandbin.commands;

import me.cain.commandbin.CommandBin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ModerationCommands implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		Player sender = (Player) s;
		
		if(l.equalsIgnoreCase("kick"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{

				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.kick"))
				{
					if(target != null)
					{
						target.kickPlayer(args[1].toString());
						if(CommandBin.plugin.getConfig().get("settings.broadcastkick").equals(true))
						{
							Bukkit.getServer().broadcastMessage(ChatColor.RED + sender.getName() + " has kicked " + target.getName());
						}
					}
					else
					{
						sender.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("ban"))
		{
			if(args.length < 2)
			{
				sender.sendMessage("/" + l.toString() + " [player] [reason]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.ban"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						sender.sendMessage("You banned " + target.getName());
						target.kickPlayer(args[1]);
						CommandBin.plugin.getConfig().set(target.getName() + ".banned", true);
						CommandBin.plugin.getConfig().set(target.getName() + ".banreason", args[1]);
						CommandBin.plugin.saveConfig();
					}
					else
					{
						sender.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("unban"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.unban"))
				{
					sender.sendMessage(ChatColor.GREEN + args[0] + " successfully unbanned " + args[0] + "!");
					CommandBin.plugin.getConfig().set(args[0] + ".banned", false);
					CommandBin.plugin.getConfig().set(args[0] + ".banreason", null);
					CommandBin.plugin.saveConfig();
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("freeze"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.freeze"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						CommandBin.plugin.getConfig().set(target.getName() + ".frozen", true);
						sender.sendMessage(ChatColor.GREEN + target.getName() + " has been frozen!");
						target.sendMessage(ChatColor.GREEN + "You have been frozen by " + sender.getName());
						CommandBin.plugin.saveConfig();
					}
					else
					{
						sender.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("unfreeze"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.freeze"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						CommandBin.plugin.getConfig().set(target.getName() + ".frozen", false);
						sender.sendMessage(ChatColor.GREEN + "You have unfrozen " + target.getName());
						target.sendMessage(ChatColor.GREEN + sender.getName() + " has unfrozen you.");
						CommandBin.plugin.saveConfig();
					}
					else
					{
						sender.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("handicap"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.handicap"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						CommandBin.plugin.getConfig().set(target.getName() + ".handicapped", true);
						CommandBin.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + target.getName() + " has been handicapped!");
						target.sendMessage(ChatColor.GREEN + sender.getName() + " handicapped you!");
					}
					else
					{
						sender.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("unhandicap"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.handicap"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						CommandBin.plugin.getConfig().set(target.getName() + ".handicapped", false);
						CommandBin.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + target.getName() + " has been unhandicapped!");
						target.sendMessage(ChatColor.GREEN + sender.getName() + " unhanddicapped you!");
					}
					else
					{
						sender.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("mute"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.mute"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						CommandBin.plugin.getConfig().set(target.getName() + ".muted", true);
						sender.sendMessage(ChatColor.GREEN + "You have muted " + target.getName());
						target.sendMessage(ChatColor.GREEN + sender.getName() + " has muted you!");
						CommandBin.plugin.saveConfig();
					}
					else
					{
						sender.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("unmute"))
		{
			
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.unmute"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						CommandBin.plugin.getConfig().set(target.getName() + ".muted", false);
						CommandBin.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "You have unmuted " + target.getName());
						target.sendMessage(ChatColor.GREEN + sender.getName() + " has unmuted you!");
					}
					else
					{
						sender.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("who"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.who"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						sender.sendMessage(ChatColor.AQUA + "Information on: " + target.getDisplayName());
						sender.sendMessage(ChatColor.GREEN + "Real Name: " + ChatColor.WHITE + target.getName());
						sender.sendMessage(ChatColor.GREEN + "Current World: " + ChatColor.WHITE + target.getWorld().getName());
						sender.sendMessage(ChatColor.GREEN + "IP Address: " + ChatColor.WHITE + target.getAddress());
						sender.sendMessage(ChatColor.GREEN + "Hostname: " + ChatColor.WHITE + target.getAddress().getHostName());
						sender.sendMessage(ChatColor.GREEN + "Co-Ordinates: " + ChatColor.RED + target.getLocation().getX() + ", " + target.getLocation().getY() + ", " + target.getLocation().getZ());
						if(!CommandBin.plugin.getServer().getVersion().contains("1337"))
						{
							sender.sendMessage("This server is not using the RB version of CraftBukkit. (1337)");
						}
						else
						{
							sender.sendMessage("This server is using the RB version of CraftBukkit (1337)");
						}
					}
					else
					{
						sender.sendMessage(CommandBin.plugin.PlayerOffline);
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("say"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [message]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.say"))
				{
					StringBuilder x = new StringBuilder();
					int x2;
					for (x2=0; x2 < args.length; x2++ ) {
						x.append(args[x2]).append(" ");
					}
					{
						Bukkit.getServer().broadcastMessage("<" + ChatColor.RED + CommandBin.plugin.getConfig().get("settings.consolename").toString() + ChatColor.WHITE + "> " + x.toString().trim());
					}
				}
			}
		}
		
		return false;
	}

}
