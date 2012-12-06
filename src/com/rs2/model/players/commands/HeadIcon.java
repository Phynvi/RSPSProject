package com.rs2.model.players.commands;

import com.rs2.model.players.*;

public class HeadIcon implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("chachi")) {
			String args[] = command.split(" ");
			int x = Integer.parseInt(args[1]);
			c.setSkullIcon(x);
			c.setAppearanceUpdateRequired(true);
		}
	}
}