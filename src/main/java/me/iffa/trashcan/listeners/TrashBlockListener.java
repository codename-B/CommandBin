// Package Declaration
package me.iffa.trashcan.listeners;

// TrashCan Imports
import me.iffa.trashcan.TrashCan;

// Bukkit Imports
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

/**
 * BlockListener for several features of TrashCan, including unlimited drops,
 * basic protection and ore broadcasting.
 * 
 * @author iffamies
 */
public class TrashBlockListener extends BlockListener {
    // Variables
    private TrashCan plugin;

    /**
     * Constructor of TrashBlockListener.
     * 
     * @param plugin TrashCan instance
     */
    public TrashBlockListener(TrashCan plugin) {
        this.plugin = plugin;
    }

    /**
     * Called when a player attempts to place a block.
     * 
     * @param e Event data
     */
    @Override
    public void onBlockPlace(BlockPlaceEvent e) {
        if (e.isCancelled()) {
            return;
        }
        Player player = e.getPlayer();
        if (TrashCan.getConfigHandler().getUnlimited(player)) {
            player.setItemInHand(new ItemStack(e.getPlayer().getItemInHand().getType(), 64));
        }
        if (TrashCan.getConfigHandler().getBlockTNT() && e.getBlock().getType() == Material.TNT && !player.hasPermission("trashcan.protection.tnt-bypass")) {
            e.setCancelled(true);
            player.sendMessage(ChatColor.RED + "The admin has blocked TNT placement.");
        }
        if (TrashCan.getConfigHandler().getBlockLava() && (e.getBlock().getType() == Material.LAVA || e.getBlock().getType() == Material.LAVA_BUCKET) && !player.hasPermission("trashcan.protection.lava-bypass")) {
            if (e.getBlock().getType() == Material.LAVA || e.getBlock().getType() == Material.LAVA_BUCKET) {
                e.setCancelled(true);
                player.sendMessage(ChatColor.RED + "The admin has blocked lava placement.");
            }
        }
    }

    /**
     * Called when a player attempts to break a block.
     * 
     * @param e Event data
     */
    @Override
    public void onBlockBreak(BlockBreakEvent e) {
        if (e.isCancelled()) {
            return;
        }
        Block block = e.getBlock();
        Player player = e.getPlayer();
        
        if (TrashCan.getConfigHandler().getMineSpawners() && player.getItemInHand().getType() == Material.DIAMOND_PICKAXE && block.getType() == Material.MOB_SPAWNER) {
            e.getPlayer().getWorld().dropItemNaturally(block.getLocation(), new ItemStack(Material.MOB_SPAWNER, 1));
        }
        if (TrashCan.getConfigHandler().getCoal() && block.getType() == Material.COAL_ORE) {
            Bukkit.getServer().broadcastMessage(ChatColor.GRAY + player.getName() + " mined some coal ore!");
        }
        if (TrashCan.getConfigHandler().getIron() && block.getType() == Material.IRON_ORE) {
            Bukkit.getServer().broadcastMessage(ChatColor.GRAY + player.getName() + " mined some iron ore!");
        }
        if (TrashCan.getConfigHandler().getGold() && block.getType() == Material.GOLD_ORE) {
            Bukkit.getServer().broadcastMessage(ChatColor.GRAY + player.getName() + " mined some gold ore!");
        }
        if (TrashCan.getConfigHandler().getDiamond() && block.getType() == Material.DIAMOND_ORE) {
            Bukkit.getServer().broadcastMessage(ChatColor.GRAY + player.getName() + " mined some diamond ore!");
        }
        if (TrashCan.getConfigHandler().getRedstone() && block.getType() == Material.REDSTONE_ORE) {
            Bukkit.getServer().broadcastMessage(ChatColor.GRAY + player.getName() + " mined some redstone ore!");
        }
        if (TrashCan.getConfigHandler().getLapis() && block.getType() == Material.LAPIS_ORE) {
            Bukkit.getServer().broadcastMessage(ChatColor.GRAY + player.getName() + " mined some lapis ore!");
        }
    }
}
