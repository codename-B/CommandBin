package me.cain.commandbin.listeners;

import me.cain.commandbin.CommandBin;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

public class EListener extends EntityListener {
	
	public void onEntityDamage(EntityDamageEvent e)
	{
		
		if(e.getEntity() instanceof Player)
		{
			Player player = (Player) (Player) e.getEntity();
			if(CommandBin.plugin.getConfig().getBoolean(player.getName() + ".godmode"))
			{
				e.setCancelled(true);				
			}
		}
		return;
	}
	
	public void onEntityDeath(EntityDeathEvent e)
	{
		if(CommandBin.plugin.getConfig().getBoolean("settings.dropxpondeath", false))
		{
			e.setDroppedExp(0);
		}
		return;
	}

}
