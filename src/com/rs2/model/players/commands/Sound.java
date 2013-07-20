package com.rs2.model.players.commands;

import com.rs2.model.players.*;

public class Sound implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("chachi")) {
			String args[] = command.split(" ");
			int soundId = Integer.parseInt(args[1]);
			c.getActionSender().sendSound(soundId, 0, 0);

		}
	}
}