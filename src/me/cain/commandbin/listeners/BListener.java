package me.cain.commandbin.listeners;

import me.cain.commandbin.CommandBin;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BListener extends BlockListener {
	
	public void onBlockPlace(BlockPlaceEvent e)
	{
		if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".unlimited") != null)
		{
			if(CommandBin.plugin.getConfig().get(e.getPlayer().getName() + ".unlimited").equals(true))
			{
				e.getPlayer().setItemInHand(new ItemStack(e.getPlayer().getItemInHand().getType(), 64));
			}
		}
		
		if(CommandBin.plugin.getConfig().get("settings.block-placing-tnt") != null)
		{
			if(CommandBin.plugin.getConfig().get("settings.block-placing-tnt").equals(true))
			{
				if(e.getBlock().getType() == Material.TNT)
				{
					if(CommandBin.plugin.pCheck(e.getPlayer(), "CommandBin.general.tntbypass"))
					{
						return;
					}
					else
					{
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "Your administrator has disabled the placement of TNT.");
					}
				}
			}
		}
		
		if(CommandBin.plugin.getConfig().get("settings.block-placing-lava") != null)
		{
			if(CommandBin.plugin.getConfig().get("settings.block-placing-lava").equals(true))
			{
				if(e.getBlock().getType() == Material.LAVA || e.getBlock().getType() == Material.LAVA_BUCKET)
				{
					if(CommandBin.plugin.pCheck(e.getPlayer(), "CommandBin.general.lavabypass"))
					{
						return;
					}
					else
					{
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "Your administrator has disabled the placement of LAVA.");
					}
				}
			}
		}
		
		
		return;
	}

}
