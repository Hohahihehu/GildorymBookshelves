package com.gildorymrp.bookshelves;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
	
	private GildorymBookshelves plugin;
	
	public PlayerInteractListener(GildorymBookshelves plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getClickedBlock().getType() == Material.BOOKSHELF) {
				if (!event.getPlayer().isSneaking()) {
					if (plugin.getBookshelfInventory(event.getClickedBlock()) == null) {
						plugin.createBookshelfInventory(event.getClickedBlock());
					}
					event.getPlayer().openInventory(plugin.getBookshelfInventory(event.getClickedBlock()));
					event.setCancelled(true);
				}
			}
		}
	}

}
