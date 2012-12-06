package com.rs2.model.players.commands;

import com.rs2.model.World;
import com.rs2.model.players.*;

public class SetOtherLevel implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("chachi")) {
	
			String args[] = command.split(" ");
			String name = args[1];
			int skill = Integer.parseInt(args[2]);
			int level = Integer.parseInt(args[3]);
			Player p = World.getPlayerByName(name);
			p.getSkill().getLevel()[skill] = level > 99 ? 99 : level;
			p.getSkill().getExp()[skill] = p.getSkill().getXPForLevel(level);
			p.getSkill().refresh(skill);
		}
	}
}
