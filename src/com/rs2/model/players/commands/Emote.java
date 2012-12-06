package com.rs2.model.players.commands;

import com.rs2.model.players.*;

public class Emote implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("chachi")) {
			String args[] = command.split(" ");
			int animationId = Integer.parseInt(args[1]);
			c.getUpdateFlags().sendAnimation(animationId, 0);
			c.setAppearanceUpdateRequired(true);
		}
	}
}