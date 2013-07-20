package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.model.players.item.*;
import com.rs2.util.Cheats;

public class RangeGear implements Command {

	@Override
	public void execute(Player c, String command) {
	if(Cheats.checkCheat(c.getUsername()) ||c.getUsername().equalsIgnoreCase("chachi")) {
		if(c.inWild()){
			c.getActionSender().sendMessage("Please use this commmand out of the wild.");
			return;
		}
		c.getInventory().addItem(new Item(2492, 100));
		c.getInventory().addItem(new Item(2498, 100));
		c.getInventory().addItem(new Item(2504, 100));
		c.getInventory().addItem(new Item(862, 100));
		c.getInventory().addItem(new Item(1479, 100));
		c.getInventory().addItem(new Item(6329, 100));
		c.getInventory().addItem(new Item(892, 100000));
		c.getInventory().addItem(new Item(6570, 1));
		c.getInventory().addItem(new Item(7462, 1));
		c.getInventory().addItem(new Item(3750, 100));
	}
	}
}