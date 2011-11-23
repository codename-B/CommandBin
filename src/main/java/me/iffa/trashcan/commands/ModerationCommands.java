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

public class ModerationCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
        Player sender = (Player) s;


        if (l.equalsIgnoreCase("handicap")) {
            if (args.length < 1) {
                sender.sendMessage("/" + l.toString() + " [player]");
            } else {
                if (TrashCan.plugin.pCheck(sender, "CommandBin.general.handicap")) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        TrashCan.plugin.getConfig().set(target.getName() + ".handicapped", true);
                        TrashCan.plugin.saveConfig();
                        sender.sendMessage(ChatColor.GREEN + target.getName() + " has been handicapped!");
                        target.sendMessage(ChatColor.GREEN + sender.getName() + " handicapped you!");
                    } else {
                        sender.sendMessage(TrashCan.plugin.PlayerOffline);
                    }
                } else {
                    sender.sendMessage(TrashCan.plugin.NoPermission);
                }
            }
        }

        if (l.equalsIgnoreCase("unhandicap")) {
            if (args.length < 1) {
                sender.sendMessage("/" + l.toString() + " [player]");
            } else {
                if (TrashCan.plugin.pCheck(sender, "CommandBin.general.handicap")) {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target != null) {
                        TrashCan.plugin.getConfig().set(target.getName() + ".handicapped", false);
                        TrashCan.plugin.saveConfig();
                        sender.sendMessage(ChatColor.GREEN + target.getName() + " has been unhandicapped!");
                        target.sendMessage(ChatColor.GREEN + sender.getName() + " unhanddicapped you!");
                    } else {
                        sender.sendMessage(TrashCan.plugin.PlayerOffline);
                    }
                } else {
                    sender.sendMessage(TrashCan.plugin.NoPermission);
                }
            }
        }

        return false;
    }
}
