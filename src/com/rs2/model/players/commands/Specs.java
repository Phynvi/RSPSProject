package com.rs2.model.players.commands;

import com.rs2.model.players.*;

public class Specs implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("chachi")) {
			c.setSpecialAmount(100);
			//c.getActionSender().updateSpecialBar();
		}
	}
}