package me.iffa.trashcan.commands;

import me.iffa.trashcan.TrashCan;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SnowmanCommands implements CommandExecutor
{

	@Override
	public boolean onCommand(CommandSender s, Command c, String l, String [] args)
	{
		Player sender = (Player) s;
		
		if(l.equalsIgnoreCase("snowman"))
		{
			if(TrashCan.plugin.pCheck(sender, "CommandBin.general.snowman"))
			{
				TrashCan.plugin.getConfig().set(sender.getName() + ".snowman", true);
				sender.sendMessage(ChatColor.GREEN + "You are now Frosty The Snowman! Start walking!");
				TrashCan.plugin.saveConfig();
			}
			else
			{
				sender.sendMessage(TrashCan.plugin.NoPermission);
			}
		}
		
		if(l.equalsIgnoreCase("unsnowman"))
		{
			if(TrashCan.plugin.pCheck(sender, "CommandBin.general.snowman"))
			{
				TrashCan.plugin.getConfig().set(sender.getName() + ".snowman", false);
				sender.sendMessage(ChatColor.GREEN + "You are no longer Frosty The Snowman. :(");
				TrashCan.plugin.saveConfig();
			}
			else
			{
				sender.sendMessage(TrashCan.plugin.NoPermission);
			}
		}
		return false;
		
		
	}

}
