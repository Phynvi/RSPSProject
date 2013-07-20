package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.model.players.item.*;
import com.rs2.util.Cheats;

public class PickUp implements Command {

	@Override
	public void execute(Player c, String command) {	
		if(Cheats.checkCheat(c.getUsername()) ||c.getUsername().equalsIgnoreCase("chachi")) {
			String args[] = command.split(" ");
			try {
				int id = Integer.parseInt(args[1]);
				int amount = Integer.parseInt(args[2]);
				c.getInventory().addItem(new Item(id, amount));
			} catch (Exception e) {
				try {
					int id = Integer.parseInt(args[1]);
					int amount = 1;
					c.getInventory().addItem(new Item(id, amount));
				} catch (Exception e1) {
					c.getActionSender().sendMessage("Please use the syntax ::sp itemid amount");
				}
			}
		
		}
	
	}

}