package com.rs2.model.players.commands;

import com.rs2.model.players.*;


public class Char implements Command {

	@Override
	public void execute(Player c, String command) {
			c.getActionSender().removeInterfaces();
			c.getActionSender().sendInterface(3559);
			//c.hasDesigned = true;
			c.setAppearanceUpdateRequired(true);
	}
}