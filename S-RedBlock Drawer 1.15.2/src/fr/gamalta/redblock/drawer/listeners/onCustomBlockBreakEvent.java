package fr.gamalta.redblock.drawer.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.gamalta.redblock.customitems.api.event.CustomBlockBreakEvent;
import fr.gamalta.redblock.drawer.Drawer;
import fr.gamalta.redblock.drawer.utils.Utils;

public class onCustomBlockBreakEvent implements Listener {

	Drawer main;
	Utils utils;

	public onCustomBlockBreakEvent(Drawer main) {

		this.main = main;
		utils = new Utils(main);

	}

	@EventHandler
	public void onCustomBlockBreak(CustomBlockBreakEvent event) {

		if (!main.drawers.isEmpty()) {

			Location location = event.getBlock().getLocation();

			if (main.drawers.containsKey(location)) {

				main.drawers.get(location).remove();
				main.drawers.remove(location);

			}
		}
	}
}