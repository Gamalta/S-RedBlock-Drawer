package fr.gamalta.redblock.drawer;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.gamalta.lib.config.Configuration;
import fr.gamalta.redblock.customitems.api.CustomItemAPI;
import fr.gamalta.redblock.customitems.lib.CustomBlockFacing;
import fr.gamalta.redblock.drawer.listeners.onCustomBlockBreakEvent;
import fr.gamalta.redblock.drawer.listeners.onCustomBlockPlaceEvent;
import fr.gamalta.redblock.drawer.listeners.onPlayerInteractEvent;
import fr.gamalta.redblock.drawer.utils.DataDrawer;

public class Drawer extends JavaPlugin {

	public HashMap<BlockFace, CustomBlockFacing> facings = new HashMap<>();
	public Configuration settingsCFG = new Configuration(this, "Drawer", "Settings");
	public Configuration drawersCFG = new Configuration(this, "Data", "Drawers");
	public Map<Location, DataDrawer> drawers = new HashMap<>();
	private static Drawer instance;
	ItemStack drawer;

	@Override
	public void onEnable() {

		instance = this;
		initRecipe();
		initListener();
		initOther();

	}

	private void initListener() {

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new onCustomBlockPlaceEvent(this), this);
		pm.registerEvents(new onCustomBlockBreakEvent(this), this);
		pm.registerEvents(new onPlayerInteractEvent(this), this);

	}

	private void initRecipe() {

		ItemStack drawer = CustomItemAPI.getCustomItemById(settingsCFG.getString("Drawer.CustomItemId"));

		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "drawer"), drawer);
		recipe.shape("C");
		recipe.setIngredient('C', Material.BARREL);
		Bukkit.addRecipe(recipe);

	}

	private void initOther() {

		for (String string : settingsCFG.getConfigurationSection("Drawer.Facing").getKeys(false)) {

			facings.put(BlockFace.valueOf(string), new CustomBlockFacing(settingsCFG.getConfigurationSection("Drawer.Facing." + string)));
		}

		if (drawersCFG.isConfigurationSection("Drawers")) {

			for (String string : drawersCFG.getConfigurationSection("Drawers").getKeys(false)) {

				DataDrawer dataDrawer = new DataDrawer(string);

				drawers.put(dataDrawer.getLocation(), dataDrawer);

			}
		}
	}

	@Override
	public void onDisable() {

	}

	public static Drawer getInstance() {

		return instance;
	}
}
