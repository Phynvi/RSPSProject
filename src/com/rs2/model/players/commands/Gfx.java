package com.rs2.model.players.commands;

import com.rs2.model.players.*;

public class Gfx implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("chachi")) {
			String args[] = command.split(" ");
			int gfxId = Integer.parseInt(args[1]);
			c.getUpdateFlags().sendGraphic(gfxId, 0);
			c.setAppearanceUpdateRequired(true);
		}
	}
}