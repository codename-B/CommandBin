package me.cain.commandbin.commands;

import me.cain.commandbin.CommandBin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class PlayerCommands implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String[] args)
	{
		Player sender = (Player) s;
		if(l.equalsIgnoreCase("tp"))
		{
			if(args.length < 1)
			{
				if(s instanceof Player)
				{
					sender.sendMessage("/" + l.toString() + " [player]");
				}
				else
				{
					s.sendMessage("/" + l.toString() + " [player]");
				}
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.teleport.tp"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						sender.teleport(target.getLocation());
						sender.sendMessage(ChatColor.GREEN + "Teleported to " + target.getName());
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
		
		if(l.equalsIgnoreCase("tphere"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.teleport.tphere"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						target.teleport(sender.getLocation());
						sender.sendMessage(ChatColor.GREEN + "Teleported " + target.getName() + " to you!");
						target.sendMessage(sender.getName() + " teleported you!");
						for (Entity entity : sender.getWorld().getLivingEntities()) { if (entity instanceof EnderDragon) { entity.remove(); entity.getWorld().createExplosion(entity.getLocation(), 5); } }
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
		
		if(l.equalsIgnoreCase("tp2p"))
		{
			if(args.length < 2)
			{
				sender.sendMessage("/" + l.toString() + " [player] to [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.teleport.tp2p"))
				{
					Player target1 = Bukkit.getServer().getPlayer(args[0]);
					Player target2 = Bukkit.getServer().getPlayer(args[1]);
					if(target1 != null && target2 != null)
					{
						target1.teleport(target2.getLocation());
						sender.sendMessage(ChatColor.GREEN + "Teleported " + target1.getName() + " to " + target2.getName());
						target1.sendMessage(ChatColor.GREEN + sender.getName() + " teleported you to " + target2.getName());
						target2.sendMessage(ChatColor.GREEN + sender.getName() + " teleported " + target1.getName() + " to you!");
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
		
		if(l.equalsIgnoreCase("put"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.teleport.put"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						Location place = sender.getTargetBlock(null, 0).getRelative(BlockFace.UP, 2).getLocation();
						target.teleport(place);
						sender.sendMessage(ChatColor.GREEN + "Teleported " + target.getName() + " to where you're looking at!");
						target.sendMessage(ChatColor.GREEN + sender.getName() + " teleported you to where he/she was looking at!");
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
		
		if(l.equalsIgnoreCase("setspawn"))
		{
			if(CommandBin.plugin.pCheck(sender, "CommandBin.teleport.setspawn"))
			{
				sender.getWorld().setSpawnLocation((int) sender.getLocation().getX(), (int) sender.getLocation().getY(), (int) sender.getLocation().getZ());
				sender.sendMessage(ChatColor.GREEN + " World spawn set!");
			}
			else
			{
				sender.sendMessage(CommandBin.plugin.NoPermission);
			}
		}
		
		if(l.equalsIgnoreCase("spawn"))
		{
			if(CommandBin.plugin.pCheck(sender, "CommandBin.teleport.spawn"))
			{
				sender.teleport(sender.getWorld().getSpawnLocation());
				sender.sendMessage(ChatColor.GREEN + "Teleported to Spawn in '" + sender.getWorld().getName().toLowerCase() + "'");
			}
			else
			{
				sender.sendMessage(CommandBin.plugin.NoPermission);
			}
		}
		
		if(l.equalsIgnoreCase("tpall"))
		{
			if(CommandBin.plugin.pCheck(sender, "CommandBin.teleport.tpall"))
			{
				for(Player p : Bukkit.getServer().getOnlinePlayers())
				{
					p.teleport(sender.getLocation());
					p.sendMessage(ChatColor.GREEN + sender.getName() + " has teleported you!");
					sender.sendMessage(ChatColor.GREEN + "Teleported everyone to you!");
				}
			}
			else
			{
				sender.sendMessage(CommandBin.plugin.NoPermission);
			}
		}
		
		if(l.equalsIgnoreCase("tpworld"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [world]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.teleport.world"))
				{
					if(Bukkit.getServer().getWorld(args[0]) != null)
					{
						sender.teleport(Bukkit.getServer().getWorld(args[0]).getSpawnLocation());
						sender.sendMessage(ChatColor.GREEN + "Teleported to world '" + Bukkit.getServer().getWorld(args[0]).getName() + "'");
					}
					else
					{
						sender.sendMessage(ChatColor.RED + "World '" + args[0] + "' does not exist.");
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("shoot"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.shoot"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						target.setVelocity(new Vector(0, 64, 0));
						sender.sendMessage(ChatColor.GREEN + "You shot " + target.getName() + " into the air!");
						target.sendMessage(ChatColor.GREEN + sender.getName() + " shot you into the air!");
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
		
		if(l.equalsIgnoreCase("strike"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.strike"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						target.getWorld().strikeLightning(target.getLocation());
						sender.sendMessage(ChatColor.GREEN + "You striked " + target.getName());
						target.sendMessage(ChatColor.GREEN + sender.getName() + " striked you!");
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
		
		if(l.equalsIgnoreCase("time"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [day/night]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.time"))
				{
					if(args[0].equalsIgnoreCase("day"))
					{
						sender.sendMessage(ChatColor.GREEN + "You have set it to day-time!");
						sender.getWorld().setTime(0);
					}
					
					if(args[0].equalsIgnoreCase("night"))
					{
						sender.sendMessage(ChatColor.GREEN + "You have set it to night-time!");
						sender.getWorld().setTime(10000000);
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("facepalm"))
		{
			if(CommandBin.plugin.pCheck(sender, "CommandBin.general.facepalm"))
			{
				Bukkit.getServer().broadcastMessage(sender.getName() + " facepalm'd");
			}
			else
			{
				sender.sendMessage(CommandBin.plugin.NoPermission);
			}
		}
		
		if(l.equalsIgnoreCase("heal"))
		{
			if(args.length < 1)
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.heal.others"))
				{
					sender.setHealth(20);
					sender.sendMessage(ChatColor.GREEN + "Your health bar is now full!");
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.heal.others"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						target.setHealth(20);
						sender.sendMessage(ChatColor.GREEN + target.getName() + "'s health is now full!");
						target.sendMessage(ChatColor.GREEN + sender.getName() + " restored your health!");
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
		
		if(l.equalsIgnoreCase("feed"))
		{
			if(args.length < 1)
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.feed"))
				{
					sender.setFoodLevel(20);
					sender.sendMessage(ChatColor.GREEN + "Your food bar is now full!");
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.feed.others"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						target.setFoodLevel(20);
						sender.sendMessage(ChatColor.GREEN + target.getName() + "'s food bar is now full!");
						target.sendMessage(ChatColor.GREEN + sender.getName() + " set your food bar to full!");
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
		
		if(l.equalsIgnoreCase("god"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [on/off]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.god"))
				{
					if(args[0].equalsIgnoreCase("on"))
					{
						CommandBin.plugin.getConfig().set(sender.getName() + ".godmode", true);
						sender.sendMessage(ChatColor.GREEN + "Godmode enabled!");
						CommandBin.plugin.saveConfig();
					}
					
					if(args[0].equalsIgnoreCase("off"))
					{
						CommandBin.plugin.getConfig().set(sender.getName() + ".godmode", false);
						sender.sendMessage(ChatColor.GREEN + "Godmode disabled!");
						CommandBin.plugin.saveConfig();
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("explode"))
		{
			if(args.length < 2)
			{
				sender.sendMessage("/" + l.toString() + " [player] [radius]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.explode"))
				{
					
					float radius = Integer.parseInt(args[0]);
					
					Player target = Bukkit.getServer().getPlayer(args[0]);
					
					if(target != null)
					{
						target.getWorld().createExplosion(target.getLocation(), radius);
						sender.sendMessage(ChatColor.GREEN + "You created a explosion at " + target.getName() + "'s location!");
						target.sendMessage(ChatColor.GREEN + sender.getName() + " created a explosion at your location.");
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
		
		if(l.equalsIgnoreCase("light"))
		{
			if(args.length < 2)
			{
				sender.sendMessage("/" + l.toString() + " [player] [length in seconds]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.light"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					
					int i = Integer.parseInt(args[0]) * 10; // Always remember to multiply by 10, milliseconds ftw.
					
					if(target != null)
					{
						target.setFireTicks(i);
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
		
		if(l.equalsIgnoreCase("roll"))
		{
			if(args.length < 2)
			{
				sender.sendMessage("/" + l.toString() + " [min] [max]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.roll"))
				{
					Double rand = ((Math.random() * (Integer.parseInt(args[1]) - Integer.parseInt(args[0]))) + Integer.parseInt(args[0]));
					long rounded = Math.round(rand);
					Bukkit.getServer().broadcastMessage(ChatColor.GREEN + sender.getName() + " Rolled " + ChatColor.RED + args[0] + ChatColor.GREEN + " and " + ChatColor.RED + args[1] + ChatColor.GREEN + " and got: " + rounded);
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("smoke"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [on/off]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.smoke"))
				{
					if(args[0].equalsIgnoreCase("on"))
					{
						CommandBin.plugin.getConfig().set(sender.getName() + ".smoke", true);
						CommandBin.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "You are now a walking chimney! *giggles*");
					}
					
					if(args[0].equalsIgnoreCase("off"))
					{
						CommandBin.plugin.getConfig().set(sender.getName() + ".smoke", false);
						CommandBin.plugin.saveConfig();
						sender.sendMessage(ChatColor.GREEN + "You are no longer the walking chimney!");
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("explosionstick"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [on/off]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.explosionstick"))
				{
					if(args[0].equalsIgnoreCase("on"))
					{
						CommandBin.plugin.getConfig().set(sender.getName() + ".explosionstick", true);
						CommandBin.plugin.saveConfig();
						sender.sendMessage(ChatColor.AQUA + "Explosion stick enabled");
						sender.getInventory().addItem(new ItemStack(Material.STICK, 1));
					}
					
					if(args[0].equalsIgnoreCase("off"))
					{
						CommandBin.plugin.getConfig().set(sender.getName() + ".explosionstick", false);
						CommandBin.plugin.saveConfig();
						sender.sendMessage(ChatColor.AQUA + "Explosion stick disabled");
						sender.getInventory().remove(new ItemStack(Material.STICK, 1));
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("lightningstick"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [on/off]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.lightningstick"))
				{
					if(args[0].equalsIgnoreCase("on"))
					{
						CommandBin.plugin.getConfig().set(sender.getName() + ".lightningstick", true);
						sender.sendMessage(ChatColor.AQUA + "Lightning stick enabled");
						CommandBin.plugin.saveConfig();
						sender.getInventory().addItem(new ItemStack(Material.STICK, 1));
					}
					
					if(args[0].equalsIgnoreCase("off"))
					{
						CommandBin.plugin.getConfig().set(sender.getName() + ".lightningstick", false);
						sender.sendMessage(ChatColor.AQUA + "Lightning stick disabled");
						CommandBin.plugin.saveConfig();
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("slap"))
		{
			if(args.length < 2)
			{
				sender.sendMessage("/" + l.toString() + " [player] [hardness]");
			}
			
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.slap"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						double i = Integer.parseInt(args[0]) * 0.4;
						
						target.setVelocity(new Vector(i, i, 0));
						target.sendMessage(ChatColor.GREEN + sender.getName() + " slapped you!");
						sender.sendMessage(ChatColor.GREEN + "You slapped " + sender.getName());
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
		
		if(l.equalsIgnoreCase("clear"))
		{
			if(args.length < 1)
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.clear"))
				{
					sender.getInventory().clear();
					sender.sendMessage(ChatColor.GREEN + "Inventory cleared!");
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
			else
			{
				Player target = Bukkit.getServer().getPlayer(args[0]);
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.clear.others"))
				{
					if(target != null)
					{
						target.getInventory().clear();
						target.sendMessage(ChatColor.GREEN + sender.getName() + " cleared your inventory!");
						sender.sendMessage(ChatColor.GREEN + "Successfully cleared " + target.getName() + "'s inventory!");
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
		
		if (l.equalsIgnoreCase("i")) // Incredibly messy, must get a decent /i working. 
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [name] [amount]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.item"))
				{
				int id = -1;
				int amount = 1;
				try {
					id = Integer.parseInt(args[0]);
				} catch (NumberFormatException e) {
					Material mat = Material.getMaterial(args[0].toUpperCase().replace(" ", "_"));
					id = (mat != null ? mat.getId() : -1);
				}
				if (args.length > 1) {
					try {
						amount = Integer.parseInt(args[1]);
					} catch (NumberFormatException e) {
					}
				}
				if (id == -1) { sender.sendMessage(ChatColor.RED + "This item does not exist."); return false; }
				((Player)sender).getInventory().addItem(new ItemStack(id, amount));
				((Player)sender).sendMessage(ChatColor.GREEN + "You obtained " + id);
				return true;
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("msg"))
		{
			if(args.length < 2)
			{
				sender.sendMessage("/" + l.toString() + " [player] [message]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.msg"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{	
						StringBuilder x = new StringBuilder();
						int x2;
						for (x2=1; x2 < args.length; x2++ ) {
							x.append(args[x2]).append(" ");
						}
						String from = ChatColor.RED + "[FROM " + sender.getName() + "] " + ChatColor.WHITE + ": " + x.toString().trim();
						String to = ChatColor.RED + "[TO " + target.getName() + "] " + ChatColor.WHITE + ": " + x.toString().trim();
						String op = ChatColor.DARK_RED + "[" + sender.getName() + " > " + target.getName() + "] " + x.toString().trim();
						
						if(CommandBin.plugin.getConfig().getBoolean("settings.opscanseepms"))
						{
							for(Player p: Bukkit.getServer().getOnlinePlayers())
							{
								if(p.isOp())
								{
									p.sendMessage(op);
								}
							}
						}
						
						target.sendMessage(from);
						sender.sendMessage(to);
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
		
		if(l.equalsIgnoreCase("nick"))
		{
			if(args.length < 2)
			{
				sender.sendMessage("/" + l.toString() + " [player] [nickname]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.nick"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						if(args[1].length() < 20)
						{
							target.setDisplayName(args[1]);
							sender.sendMessage(ChatColor.GREEN + target.getName() + "'s name changed to " + args[1]);
							target.sendMessage(ChatColor.GREEN + sender.getName() + " changed your name to " + args[1]);
							CommandBin.plugin.getConfig().set(target.getName() + ".nickname", args[1]);
							CommandBin.plugin.saveConfig();
						}
						else
						{
							sender.sendMessage(ChatColor.RED + "That name is too long. It must be below 20 characters. Sorry :(");
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
		
		if(l.equalsIgnoreCase("setxp"))
		{
			if(args.length < 2)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.setxp"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						target.setExperience(Integer.parseInt(args[1]));
						sender.sendMessage(ChatColor.GREEN + target.getName() + "'s experience has been set to " + args[1]);
						target.sendMessage(ChatColor.GREEN + sender.getName() + " set your experience points to " + args[1]);
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
		
		if(l.equalsIgnoreCase("kill"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.kill"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						target.getWorld().strikeLightning(target.getLocation());
						target.setHealth(0);
						sender.sendMessage(ChatColor.GREEN + "You killed " + target.getName());
						target.sendMessage(ChatColor.GREEN + sender.getName() + " killed you!");
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
		
		if(l.equalsIgnoreCase("up"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [blocks]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.up"))
				{
						sender.getLocation().getBlock().getRelative(0, Integer.parseInt(args[0]), 0).setType(Material.GLASS);
						Location tpblock = sender.getLocation().getBlock().getRelative(0, Integer.parseInt(args[0])+2, 0).getLocation();
						sender.teleport(tpblock);
						sender.sendMessage(ChatColor.RED + "You went up " + args[0] + " blocks!");
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
				}
			}
		}
		
		if(l.equalsIgnoreCase("unlimited"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.unlimited"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						CommandBin.plugin.getConfig().set(target.getName() + ".unlimited", true);
						target.sendMessage(ChatColor.GREEN + sender.getName() + " has given you unlimited block usage!");
						sender.sendMessage(ChatColor.GREEN + "Successfully gave " + target.getName() + " unlimited block usage!");
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
		
		if(l.equalsIgnoreCase("delunlimited"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [player]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.unlimited"))
				{
					Player target = Bukkit.getServer().getPlayer(args[0]);
					if(target != null)
					{
						CommandBin.plugin.getConfig().set(target.getName() + ".unlimited", false);
						target.sendMessage(ChatColor.GREEN + sender.getName() + " has removed your unlimited block usage!");
						sender.sendMessage(ChatColor.GREEN + "Successfully removed " + target.getName() + "'s unlimited block usage!");
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
		
		if(l.equalsIgnoreCase("mytime"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [day/night/reset]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.mytime"))
				{
					if(args[0].equalsIgnoreCase("day"))
					{
						sender.setPlayerTime(0, false);
						sender.sendMessage(ChatColor.GREEN + "You set your time to day!");
					}
					
					if(args[0].equalsIgnoreCase("night"))
					{
						sender.setPlayerTime(100000000, false);
						sender.sendMessage(ChatColor.GREEN + "You set your time to night!");
					}
					
					if(args[0].equalsIgnoreCase("reset"))
					{
						sender.setPlayerTime(sender.getWorld().getTime(), true);
						sender.sendMessage(ChatColor.GREEN + "You reset your time to the server time!");
					}
				}
				else
				{
					sender.sendMessage(CommandBin.plugin.NoPermission);
					}
				}
			}
		
		if(l.equalsIgnoreCase("armour"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [leather, iron, diamond, gold, chainmail");
			}
			else
			{
				if(args[0].equalsIgnoreCase("leather"))
				{
					if(CommandBin.plugin.pCheck(sender, "CommandBin.general.armour.leather"))
					{
						sender.getInventory().setHelmet(new ItemStack(Material.LEATHER_HELMET, 1));
						sender.getInventory().setChestplate(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
						sender.getInventory().setLeggings(new ItemStack(Material.LEATHER_LEGGINGS, 1));
						sender.getInventory().setBoots(new ItemStack(Material.LEATHER_BOOTS, 1));
						sender.sendMessage(ChatColor.GREEN + "You received " + args[0] + " armour!");
					}
					else
					{
						sender.sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				
				if(args[0].equalsIgnoreCase("iron"))
				{
					if(CommandBin.plugin.pCheck(sender, "CommandBin.general.armour.iron"))
					{
						sender.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET, 1));
						sender.getInventory().setChestplate(new ItemStack(Material.IRON_CHESTPLATE, 1));
						sender.getInventory().setLeggings(new ItemStack(Material.IRON_LEGGINGS, 1));
						sender.getInventory().setBoots(new ItemStack(Material.IRON_BOOTS, 1));
						sender.sendMessage(ChatColor.GREEN + "You received " + args[0] + " armour!");

					}
					else
					{
						sender.sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				
				if(args[0].equalsIgnoreCase("diamond"))
				{
					if(CommandBin.plugin.pCheck(sender, "CommandBin.general.armour.diamond"))
					{
						sender.getInventory().setHelmet(new ItemStack(Material.DIAMOND_HELMET, 1));
						sender.getInventory().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
						sender.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
						sender.getInventory().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));
						sender.sendMessage(ChatColor.GREEN + "You received " + args[0] + " armour!");
					}
					else
					{
						sender.sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				
				if(args[0].equalsIgnoreCase("gold"))
				{
					if(CommandBin.plugin.pCheck(sender, "CommandBin.general.armour.gold"))
					{
						sender.getInventory().setHelmet(new ItemStack(Material.GOLD_HELMET, 1));
						sender.getInventory().setChestplate(new ItemStack(Material.GOLD_CHESTPLATE, 1));
						sender.getInventory().setLeggings(new ItemStack(Material.GOLD_LEGGINGS, 1));
						sender.getInventory().setBoots(new ItemStack(Material.GOLD_BOOTS, 1));
						sender.sendMessage(ChatColor.GREEN + "You received " + args[0] + " armour!");

					}
					else
					{
						sender.sendMessage(CommandBin.plugin.NoPermission);
					}
				}
				
				if(args[0].equalsIgnoreCase("chainmail"))
				{
					if(CommandBin.plugin.pCheck(sender, "CommandBin.general.armour.chainmail"))
					{
						sender.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET, 1));
						sender.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1));
						sender.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS, 1));
						sender.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS, 1));
						sender.sendMessage(ChatColor.GREEN + "You received " + args[0] + " armour!");

					}
					else
					{
						sender.sendMessage(CommandBin.plugin.NoPermission);
					}
				}
			}
		}
		
		if(l.equalsIgnoreCase("me"))
		{
			if(args.length < 1)
			{
				sender.sendMessage("/" + l.toString() + " [message]");
			}
			else
			{
				if(CommandBin.plugin.pCheck(sender, "CommandBin.general.me"))
				{
					StringBuilder x = new StringBuilder();
					
					int i;
					
					for(i = 0; i < args.length; i++)
					{
						x.append(args[i]).append(" ");
					}
					
					Bukkit.getServer().broadcastMessage(ChatColor.RED + sender.getName() + " : " + x.toString().trim());
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
