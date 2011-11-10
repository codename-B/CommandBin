package me.cain.commandbin.commands;

import me.cain.commandbin.CommandBin;

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
			if(CommandBin.plugin.pCheck(sender, "CommandBin.general.snowman"))
			{
				CommandBin.plugin.getConfig().set(sender.getName() + ".snowman", true);
				sender.sendMessage(ChatColor.GREEN + "You are now Frosty The Snowman! Start walking!");
				CommandBin.plugin.saveConfig();
			}
			else
			{
				sender.sendMessage(CommandBin.plugin.NoPermission);
			}
		}
		
		if(l.equalsIgnoreCase("unsnowman"))
		{
			if(CommandBin.plugin.pCheck(sender, "CommandBin.general.snowman"))
			{
				CommandBin.plugin.getConfig().set(sender.getName() + ".snowman", false);
				sender.sendMessage(ChatColor.GREEN + "You are no longer Frosty The Snowman. :(");
				CommandBin.plugin.saveConfig();
			}
			else
			{
				sender.sendMessage(CommandBin.plugin.NoPermission);
			}
		}
		return false;
		
		
	}

}
