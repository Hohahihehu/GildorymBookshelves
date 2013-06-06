package com.gildorymrp.bookshelves;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SaveDataManager {
	
	public static void save(GildorymBookshelves plugin) {
		for (Block block : plugin.getBookshelfInventories().keySet()) {
			if (block.getType() == Material.BOOKSHELF) {
				plugin.getConfig().set(block.getWorld().getName() + "." + block.getX() + "." + block.getY() + "." + block.getZ(), Arrays.asList(plugin.getBookshelfInventory(block).getContents()));
			} else {
				plugin.getConfig().set(block.getWorld().getName() + "." + block.getX() + "." + block.getY() + "." + block.getZ(), null);
			}
		}
		plugin.saveConfig();
	}
	
	@SuppressWarnings("unchecked")
	public static void load(GildorymBookshelves plugin) {
		for (String world : plugin.getConfig().getConfigurationSection("").getKeys(false)) {
			for (String x : plugin.getConfig().getConfigurationSection(world).getKeys(false)) {
				for (String y : plugin.getConfig().getConfigurationSection(world + "." + x).getKeys(false)) {
					for (String z : plugin.getConfig().getConfigurationSection(world + "." + x + "." + y).getKeys(false)) {
						Inventory inventory = plugin.getServer().createInventory(null, 9, "Bookshelf");
						for (ItemStack itemStack : (List<ItemStack>) plugin.getConfig().get(world + "." + x + "." + y + "." + z)) {
							if (itemStack != null) {
								inventory.addItem(itemStack);
							}
						}
						plugin.getBookshelfInventories().put(plugin.getServer().getWorld(world).getBlockAt(Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(z)), inventory);
					}
				}
			}
		}
	}

}
