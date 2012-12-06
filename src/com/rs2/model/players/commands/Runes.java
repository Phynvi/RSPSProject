package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.model.players.item.*;

public class Runes implements Command {

	@Override
	public void execute(Player c, String command) {
	if(c.getUsername().equalsIgnoreCase("chachi")) {
		if(c.inWild()){
			c.getActionSender().sendMessage("Please use this commmand out of the wild.");
			return;
		}		
		for (int i = 0; i < ((566 - 554) + 1); i++)
			c.getInventory().addItem(new Item(554 + i, 1000));
			c.getInventory().addItem(new Item(1381, 1));
			c.getInventory().addItem(new Item(4675, 1));
	}
	}
}