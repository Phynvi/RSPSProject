package com.rs2.model.players.commands;


import com.rs2.model.npcs.NpcDefinition;
import com.rs2.model.players.*;

public class NpcSpawn implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("chachi")) {
			int newNPC = Integer.parseInt(command.substring(6));
			NpcDefinition npc = NpcDefinition.forId(newNPC);
			c.appendToAutoSpawn(npc);			
		}
	}
}