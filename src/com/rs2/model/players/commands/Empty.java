package com.rs2.model.players.commands;

import com.rs2.model.players.*;

public class Empty implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("chachi")) {
		c.getInventory().getItemContainer().clear();
		c.getInventory().refresh();
		}
	}
}