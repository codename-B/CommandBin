// Package Declaration
package me.iffa.trashcan.commands.general;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;
import me.iffa.trashcan.commands.TrashCommand;
import me.iffa.trashcan.utils.MessageUtil;

// Bukkit Imports
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Player;

/**
 * Represents /spawnmob.
 * 
 * @author iffamies
 */
public class SpawnmobCommand extends TrashCommand {
    /**
     * Constructor of SpawnmobCommand.
     * 
     * @param label Command label
     */
    public SpawnmobCommand(String label) {
        super(label);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (!(cs instanceof Player)) {
            MessageUtil.sendMessage(cs, "Sorry, only players can use this command.");
            return true;
        }
        if (!cs.hasPermission("trashcan.general.spawnmob")) {
            MessageUtil.sendMessage(cs, ChatColor.RED + "You don't have permission.");
            return true;
        }
        if (args.length < 1) {
            return false;
        }
        Player player = (Player) cs;
        Location spawn = player.getLocation();
        spawn.setY(spawn.getBlockY() + 1);
        if (args.length == 1) {
            CreatureType mob = CreatureType.fromName(args[0]);
            if (mob == null) {
                MessageUtil.sendMessage(player, ChatColor.RED + "'" + args[0] + "' is not a mob.");
                return true;
            }
            player.getWorld().spawnCreature(spawn, mob);
            MessageUtil.sendMessage(player, ChatColor.GREEN + "Spawned 1 " + args[0] + ".");
        } else {
            CreatureType mob = CreatureType.fromName(args[0]);
            int amount = 1;
            if (mob == null) {
                MessageUtil.sendMessage(player, ChatColor.RED + "'" + args[0] + "' is not a mob.");
                return true;
            }
            try {
                amount = Integer.parseInt(args[1]);
            } catch (NumberFormatException ex) {
                return false;
            }
            if (amount > TrashCan.getConfigHandler().getMaxMobAmount()) {
                MessageUtil.sendMessage(player, ChatColor.RED + "You can only spawn " + TrashCan.getConfigHandler().getMaxMobAmount() + " mobs at once.");
                return true;
            }
            for(int spawned = 0; spawned < amount; spawned++) {
                player.getWorld().spawnCreature(spawn, mob);
            }
            MessageUtil.sendMessage(player, ChatColor.GREEN + "Spawned " + amount + " " + args[0] + "(s).");
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sendUsage(CommandSender cs) {
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Usage: /spawnmob <mob> [amount]");
        MessageUtil.sendMessage(cs, ChatColor.GRAY + "Mobs: chicken, cow, pig, sheep, squid, enderman, pigman, wolf, cavespider, creeper, ghast, silverfish, skeleton, slime, spider, zombie, enderdragon, villager");
    }
    
}
