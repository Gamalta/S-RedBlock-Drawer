package fr.gamalta.redblock.drawer.utils;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;

import fr.gamalta.redblock.customitems.lib.CustomItemStack;
import fr.gamalta.redblock.drawer.Drawer;

public class DataDrawer {

	private static Drawer main;
	private Location location;
	private String id;
	private CustomItemStack customItemStack;
	private int amount;

	public DataDrawer(Location location, String id, CustomItemStack customItemStack, int amount) {

		main = Drawer.getInstance();
		this.location = location;
		this.id = id;
		this.customItemStack = customItemStack;
		this.amount = amount;
	}

	public DataDrawer(Location location, String id) {

		this(location, id, null, 0);
	}

	public DataDrawer(String id) {

		if (main.drawersCFG.contains("Drawers." + id)) {

			World world = Bukkit.getWorld(main.drawersCFG.getString("Drawers." + id + ".World"));
			int x = main.drawersCFG.getInt("Drawers." + id + ".X");
			int y = main.drawersCFG.getInt("Drawers." + id + ".Y");
			int z = main.drawersCFG.getInt("Drawers." + id + ".Z");
			location = new Location(world, x, y, z);

			if (main.drawersCFG.contains("Drawers." + id + ".Item") && main.drawersCFG.contains("Drawers." + id + ".Amount")) {

				amount = main.drawersCFG.getInt("Drawers." + id + ".Amount");
				customItemStack = new CustomItemStack(deserializeLine(main.drawersCFG.getString("Drawers." + id + ".Item")));
			}
		}
	}

	public void save() {

		main.drawersCFG.set("Drawers." + id + ".World", location.getWorld().getName());
		main.drawersCFG.set("Drawers." + id + ".X", location.getBlockX());
		main.drawersCFG.set("Drawers." + id + ".Y", location.getBlockY());
		main.drawersCFG.set("Drawers." + id + ".Z", location.getBlockZ());

		if (customItemStack != null && amount != 0) {

			main.drawersCFG.set("Drawers." + id + ".Item", customItemStack);
			main.drawersCFG.set("Drawers." + id + ".Amount", amount);

		}
	}

	public void remove() {

	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CustomItemStack getCustomItemStack() {
		return customItemStack;
	}

	public void setCustomItemStack(CustomItemStack customItemStack) {
		this.customItemStack = customItemStack;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ItemStack deserializeLine(String line) {

		String[] strings = line.split(" ");

		Map<String, Object> args = new HashMap<>();

		for (String string : strings)
			args.put(string.split(":")[0], string.split(":")[1]);

		return ItemStack.deserialize(args);
	}
}