package com.rs2.model.players.commands;


import com.rs2.model.npcs.NpcDefinition;
import com.rs2.model.players.*;

public class GlobalItemSpawn implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("chachi")) {
			int itemId = Integer.parseInt(command.substring(7));
			c.appendToItemAutoSpawn(itemId);			
		}
	}
}