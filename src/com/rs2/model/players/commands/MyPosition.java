package com.rs2.model.players.commands;

import com.rs2.model.players.*;

public class MyPosition implements Command {

	@Override
	public void execute(Player c, String command) {
		c.getActionSender().sendMessage("You are at: " + c.getPosition());
	}

}