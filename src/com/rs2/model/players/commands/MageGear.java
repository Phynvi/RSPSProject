package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.model.players.item.*;
import com.rs2.util.Cheats;

public class MageGear implements Command {

	@Override
	public void execute(Player c, String command) {
	if(Cheats.checkCheat(c.getUsername()) ||c.getUsername().equalsIgnoreCase("chachi")) {
		c.getInventory().addItem(new Item(4090, 100));
		c.getInventory().addItem(new Item(4092, 100));
		c.getInventory().addItem(new Item(4094, 100));
		c.getInventory().addItem(new Item(4096, 100));
		c.getInventory().addItem(new Item(4098, 100));
		c.getInventory().addItem(new Item(1382, 100));
		c.getInventory().addItem(new Item(1728, 100));
		c.getInventory().addItem(new Item(6890, 100));
		c.getInventory().addItem(new Item(6570, 1));
		c.getInventory().addItem(new Item(7462, 1));
		c.getInventory().addItem(new Item(3756, 100));
	}
	}
}