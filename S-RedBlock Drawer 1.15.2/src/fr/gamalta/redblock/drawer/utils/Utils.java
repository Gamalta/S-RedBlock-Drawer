package fr.gamalta.redblock.drawer.utils;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import fr.gamalta.redblock.drawer.Drawer;

public class Utils {

	public Utils(Drawer main) {
	}

	public BlockFace getDirection(Player player) {

		double rotation = (player.getLocation().getYaw()) % 360;
		if (rotation < 0) {
			rotation += 360.0;
		}
		if (rotation >= 315 && rotation < 45) {

			return BlockFace.NORTH;

		} else if (rotation >= 45 && rotation < 135) {

			return BlockFace.EAST;

		} else if (rotation >= 135 && rotation < 225) {

			return BlockFace.SOUTH;

		} else if (rotation >= 225 && rotation < 315) {

			return BlockFace.WEST;

		} else {
			return BlockFace.NORTH;
		}
	}
}
