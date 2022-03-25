package fr.gamalta.redblock.drawer.listeners;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import fr.gamalta.redblock.customitems.api.event.CustomBlockPlaceEvent;
import fr.gamalta.redblock.customitems.lib.CustomBlockFacing;
import fr.gamalta.redblock.customitems.lib.CustomItemStack;
import fr.gamalta.redblock.customitems.lib.CustomItemStack.CustomAttribute;
import fr.gamalta.redblock.drawer.Drawer;
import fr.gamalta.redblock.drawer.utils.DataDrawer;
import fr.gamalta.redblock.drawer.utils.Utils;

public class onCustomBlockPlaceEvent implements Listener {

	private Drawer main;
	private Utils utils;

	public onCustomBlockPlaceEvent(Drawer main) {

		this.main = main;
		utils = new Utils(main);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCustomBlockPlace(CustomBlockPlaceEvent event) {

		Block block = event.getBlock();

		CustomItemStack customItemStack = event.getCustomItemStack();

		if (((String) customItemStack.getCustomAttribue(CustomAttribute.UNLOCALIZED_NAME)).equalsIgnoreCase("drawer")) {

			event.setCancelled(true);
			CustomBlockFacing customBlockFacing = main.facings.get(utils.getDirection(event.getPlayer()));

			block.setType(Material.getMaterial((String) customItemStack.getCustomAttribue(CustomAttribute.BLOCK_MATERIAL)));
			MultipleFacing multiFacing = (MultipleFacing) block.getBlockData();
			multiFacing.setFace(BlockFace.UP, customBlockFacing.getUp());
			multiFacing.setFace(BlockFace.DOWN, customBlockFacing.getDown());
			multiFacing.setFace(BlockFace.NORTH, customBlockFacing.getNorth());
			multiFacing.setFace(BlockFace.SOUTH, customBlockFacing.getSouth());
			multiFacing.setFace(BlockFace.EAST, customBlockFacing.getEast());
			multiFacing.setFace(BlockFace.WEST, customBlockFacing.getWest());
			block.setBlockData(multiFacing);

			Location location = event.getBlock().getLocation();
			DataDrawer dataDrawer = new DataDrawer(location, UUID.randomUUID().toString());
			dataDrawer.save();

			main.drawers.put(location, dataDrawer);

		}
	}
}