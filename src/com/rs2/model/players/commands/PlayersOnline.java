package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.model.World;

public class PlayersOnline implements Command {

	@Override
	public void execute(Player c, String command) {
		c.getActionSender().sendMessage("Players Online: " + World.playerAmount());
	
	}

}