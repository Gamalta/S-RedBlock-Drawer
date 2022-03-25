package fr.gamalta.redblock.drawer.listeners;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import fr.gamalta.redblock.drawer.Drawer;
import fr.gamalta.redblock.drawer.utils.Utils;

public class onPlayerInteractEvent implements Listener {

	private Drawer main;
	private Utils utils;

	public onPlayerInteractEvent(Drawer main) {

		this.main = main;
		utils = new Utils(main);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {

		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack item = event.getItem();
		Block block = event.getClickedBlock();
		Location location = block.getLocation();

		if (item != null && event.getHand() == EquipmentSlot.HAND) {

			if (action == Action.LEFT_CLICK_BLOCK) {

				// DataDrawer dataDrawer = DataDrawer.getByLocation(location);

			} else if (action == Action.RIGHT_CLICK_BLOCK) {

				// DataDrawer dataDrawer = DataDrawer.getByLocation(location);
			}
		}
	}
}
