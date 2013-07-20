package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.util.PunishmentManager;

public class ClearAll implements Command {

	@Override
	public void execute(Player c, String command) {
	if(c.getUsername().equalsIgnoreCase("chachi")) {
		String args[] = command.split(" ");
		PunishmentManager.removeFromPunishmentList(args[0]);
	}
	}
}