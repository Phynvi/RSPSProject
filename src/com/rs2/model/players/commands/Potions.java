package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.model.players.item.*;
import com.rs2.util.Cheats;


public class Potions implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.inWild()){
			c.getActionSender().sendMessage("Please use this commmand out of the wild.");
			return;
		}
	if(Cheats.checkCheat(c.getUsername()) ||c.getUsername().equalsIgnoreCase("chachi")) {
		c.getInventory().addItem(new Item(2437, 1000));
		c.getInventory().addItem(new Item(2441, 1000));
		c.getInventory().addItem(new Item(2443, 1000));
		c.getInventory().addItem(new Item(2435, 1000));
		c.getInventory().addItem(new Item(392, 1000));
		c.getInventory().addItem(new Item(2445, 1000));
	}
	}
}