package com.gildorymrp.bookshelves;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockBreakListener implements Listener {
	
	private GildorymBookshelves plugin;
	
	public BlockBreakListener(GildorymBookshelves plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (plugin.getBookshelfInventory(event.getBlock()) != null) {
			for (ItemStack itemStack : plugin.getBookshelfInventory(event.getBlock())) {
				if (itemStack != null) {
					event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), itemStack);
				}
			}
			plugin.getBookshelfInventories().remove(event.getBlock());
			plugin.getConfig().set(event.getBlock().getWorld().getName() + "." + event.getBlock().getX() + "." + event.getBlock().getY() + "." + event.getBlock().getZ(), null);
			plugin.saveConfig();
		}
	}

}
