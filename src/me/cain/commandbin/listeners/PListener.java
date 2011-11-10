package me.cain.commandbin.listeners;

import me.cain.commandbin.CommandBin;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;

public class PListener extends PlayerListener {

	static CommandBin derp;
	
	public void onPlayerMove(PlayerMoveEvent e)
	{
		if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".frozen") != null)
		{
			if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".frozen").equals(true))
			{
				e.setCancelled(true);
			}
			
		}
		
		if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".smoke") != null)
		{
			if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".smoke").equals(true))
			{
				e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.SMOKE, 5);
			}
		}
		
			if(CommandBin.plugin.getConfig().getBoolean(e.getPlayer().getName() + ".snowman"))
			{
				if(!(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN, 1).getType() == Material.AIR))
				{
				e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN, 0).setType(Material.SNOW);
				}
				
				if(e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN, 1).getType() == Material.SNOW)
				{
					e.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN, 1).setType(Material.SNOW_BLOCK);
				}
			}
		return;
	}
	
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e)
	{
		if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".handicapped") != null)
		{
			if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".handicapped").equals(true))
			{
				e.getPlayer().sendMessage(ChatColor.RED + CommandBin.plugin.getConfig().getString("settings.playercannotusecommands"));
				e.setCancelled(true);
			}
		}
		return;
	}
	
	public void onPlayerChat(PlayerChatEvent e)
	{
		if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".muted") != null)
		{
			if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".muted").equals(true))
			{
				e.getPlayer().sendMessage(ChatColor.RED + CommandBin.plugin.getConfig().getString("settings.playerismuted"));
				e.setCancelled(true);
			}
		}
	}
	
	public void onPlayerInteract(PlayerInteractEvent e)
	{
		if(e.getAction() == Action.RIGHT_CLICK_AIR)
		{
			if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".explosionstick") != null)
			{
				if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".explosionstick").equals(true))
				{
					if(e.getPlayer().getItemInHand().getType() == Material.STICK)
					{
						Location block = e.getPlayer().getTargetBlock(null, 0).getLocation();
						e.getPlayer().getWorld().createExplosion(block, 8);
					}
				}
			}
			
			if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".lightningstick") != null)
			{
				if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".lightningstick").equals(true))
				{
					if(e.getPlayer().getItemInHand().getType() == Material.STICK)
					{
						Location block = e.getPlayer().getTargetBlock(null, 0).getLocation();
						e.getPlayer().getWorld().strikeLightning(block);
					}
				}
			}
		}
		return;
	}
	
	public void onPlayerLogin(PlayerLoginEvent e)
	{
		if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".banned") != null)
		{
			if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".banned").equals(true))
			{
				e.setKickMessage(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".banreason").toString());
				e.disallow(Result.KICK_BANNED, "You were banned for: " + ChatColor.GRAY + CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".banreason").toString());
			}
		}
		return;
	}
	
	public void onPlayerJoin(PlayerJoinEvent e)
	{
		if(CommandBin.plugin.getConfig().get("settings.joinmessage") != null)
		{
			e.setJoinMessage(ChatColor.YELLOW + e.getPlayer().getName() + " " + CommandBin.plugin.getConfig().get("settings.joinmessage").toString());
		}
		return;
	}
	
	public void onPlayerQuit(PlayerQuitEvent e)
	{
		if(CommandBin.plugin.getConfig().get("settings.leavemessage") != null)
		{
			e.setQuitMessage(ChatColor.YELLOW + e.getPlayer().getName() + " " + CommandBin.plugin.getConfig().get("settings.leavemessage").toString());
		}
		return;
	}
	
	public void onPlayerToggleSprint(PlayerToggleSprintEvent e)
	{
		if(CommandBin.plugin.getConfig().get("settings.allowsprinting").equals(false))
		{
			e.setCancelled(true);
		}
		return;
	}
	
	public void onPlayerRespawn(PlayerRespawnEvent e)
	{
		e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
		return;
	}

}
