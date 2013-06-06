package com.gildorymrp.bookshelves;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class GildorymBookshelves extends JavaPlugin {
	
	private Map<Block, Inventory> bookshelfInventories = new HashMap<Block, Inventory>();
	
	public void onEnable() {
		if (this.getDataFolder().exists()) {
			SaveDataManager.load(this);
		}
		this.registerListeners(new PlayerInteractListener(this), new BlockBreakListener(this));
	}
	
	public void onDisable() {
		SaveDataManager.save(this);
	}
	
	private void registerListeners(Listener... listeners) {
		for (Listener listener : listeners) {
			this.getServer().getPluginManager().registerEvents(listener, this);
		}
	}
	
	public Map<Block, Inventory> getBookshelfInventories() {
		return bookshelfInventories;
	}
	
	public Inventory getBookshelfInventory(Block block) {
		return bookshelfInventories.get(block);
	}

	public void createBookshelfInventory(Block block) {
		bookshelfInventories.put(block, this.getServer().createInventory(null, 9, "Bookshelf"));
	}

}
