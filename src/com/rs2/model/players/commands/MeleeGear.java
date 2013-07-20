package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.model.players.item.*;
import com.rs2.util.Cheats;

public class MeleeGear implements Command {

	@Override
	public void execute(Player c, String command) {
	if(Cheats.checkCheat(c.getUsername()) ||c.getUsername().equalsIgnoreCase("chachi")) {
		if(c.inWild()){
			c.getActionSender().sendMessage("Please use this commmand out of the wild.");
			return;
		}
		c.getInventory().addItem(new Item(4152, 100));
		c.getInventory().addItem(new Item(1164, 100));
		c.getInventory().addItem(new Item(1128, 100));
		c.getInventory().addItem(new Item(1080, 100));
		c.getInventory().addItem(new Item(4132, 100));
		c.getInventory().addItem(new Item(1202, 100));
		c.getInventory().addItem(new Item(1732, 100));
		c.getInventory().addItem(new Item(1713, 100));
		c.getInventory().addItem(new Item(5699, 100));
		c.getInventory().addItem(new Item(6570, 1));
		c.getInventory().addItem(new Item(7462, 1));
		c.getInventory().addItem(new Item(3752, 100));
		c.getInventory().addItem(new Item(3754, 100));
	}
	}
}