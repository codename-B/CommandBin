package me.iffa.trashcan.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import me.iffa.trashcan.TrashCan;

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
			if(args.length < 2)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{

				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.kick"))
				{
					if(target != null)
					{
						target.kickPlayer(args[1].toString());
						if(TrashCan.plugin.getConfig().get("settings.broadcastkick").equals(true))
						{
							Bukkit.getServer().broadcastMessage(ChatColor.RED + sender.getName() + " has kicked " + target.getName());
						}
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
		
		if(l.equalsIgnoreCase("ban"))
		{
			if(args.length < 2)
			{
				sender.sendMessage("/" + l.toString() + " [player] [reason]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.ban"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						sender.sendMessage("You banned " + target.getName());
						target.kickPlayer(args[1]);
						TrashCan.plugin.getConfig().set(target.getName() + ".banned", true);
						TrashCan.plugin.getConfig().set(target.getName() + ".banreason", args[1]);
						TrashCan.plugin.saveConfig();
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
		
		if(l.equalsIgnoreCase("unban"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.unban"))
				{
					sender.sendMessage(ChatColor.GREEN + sender.getName() + " successfully unbanned " + args[0] + "!");
					TrashCan.plugin.getConfig().set(args[0] + ".banned", false);
					TrashCan.plugin.getConfig().set(args[0] + ".banreason", null);
					TrashCan.plugin.saveConfig();
				}
				else
				{
					sender.sendMessage(TrashCan.plugin.NoPermission);
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
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.freeze"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						TrashCan.plugin.getConfig().set(target.getName() + ".frozen", true);
						sender.sendMessage(ChatColor.GREEN + target.getName() + " has been frozen!");
						target.sendMessage(ChatColor.GREEN + "You have been frozen by " + sender.getName());
						TrashCan.plugin.saveConfig();
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
		
		if(l.equalsIgnoreCase("unfreeze"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.freeze"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						TrashCan.plugin.getConfig().set(target.getName() + ".frozen", false);
						sender.sendMessage(ChatColor.GREEN + "You have unfrozen " + target.getName());
						target.sendMessage(ChatColor.GREEN + sender.getName() + " has unfrozen you.");
						TrashCan.plugin.saveConfig();
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
		
		if(l.equalsIgnoreCase("handicap"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.handicap"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						TrashCan.plugin.getConfig().set(target.getName() + ".handicapped", true);
						TrashCan.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + target.getName() + " has been handicapped!");
						target.sendMessage(ChatColor.GREEN + sender.getName() + " handicapped you!");
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
		
		if(l.equalsIgnoreCase("unhandicap"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.handicap"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						TrashCan.plugin.getConfig().set(target.getName() + ".handicapped", false);
						TrashCan.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + target.getName() + " has been unhandicapped!");
						target.sendMessage(ChatColor.GREEN + sender.getName() + " unhanddicapped you!");
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
		
		if(l.equalsIgnoreCase("mute"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.mute"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						TrashCan.plugin.getConfig().set(target.getName() + ".muted", true);
						sender.sendMessage(ChatColor.GREEN + "You have muted " + target.getName());
						target.sendMessage(ChatColor.GREEN + sender.getName() + " has muted you!");
						TrashCan.plugin.saveConfig();
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
		
		if(l.equalsIgnoreCase("unmute"))
		{
			
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.unmute"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						TrashCan.plugin.getConfig().set(target.getName() + ".muted", false);
						TrashCan.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "You have unmuted " + target.getName());
						target.sendMessage(ChatColor.GREEN + sender.getName() + " has unmuted you!");
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
		
		if(l.equalsIgnoreCase("who"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.who"))
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
						if(!TrashCan.plugin.getServer().getVersion().contains("1337"))
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
						sender.sendMessage(TrashCan.plugin.PlayerOffline);
					}
				}
				else
				{
					sender.sendMessage(TrashCan.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("paid"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.paid"))
				{
					sender.sendMessage(ChatColor.GREEN + args[0] + " has paid: ");
					hasPaid(sender, args[0].toString());
				}
				else
				{
					sender.sendMessage(TrashCan.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("banip"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.banip"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						String ip2 = target.getAddress().getAddress().getHostAddress().toString();
						String ipworking = ip2.replace(".", "");
						
						TrashCan.plugin.getConfig().set("bannedips." + ipworking, true);
						TrashCan.plugin.saveConfig();
						target.kickPlayer("Your IP address has been banned!");
						sender.sendMessage(ChatColor.GREEN + "IP " + target.getAddress() + " has been banned!");
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
		
		if(l.equalsIgnoreCase("unbanip"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [ip]");
			}
			else
			{
				if(TrashCan.plugin.pCheck(sender, "CommandBin.general.unbanip"))
				{
					TrashCan.plugin.getConfig().set("bannedips." + args[0], false);
					sender.sendMessage(ChatColor.GREEN + "IP " + args[0] + " unbanned!");
					TrashCan.plugin.saveConfig();
				}
				else
				{
					sender.sendMessage(TrashCan.plugin.NoPermission);
				}
			}
		}
		
		return false;
	}
	
	public void hasPaid(Player p, String user)
	{
		try {
		    URL url = new URL("http://minecraft.net/haspaid.jsp?user=" + user);

		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    String str;
		    while ((str = in.readLine()) != null) {
		      p.sendMessage(str);
		    }
		    in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
	}

}
