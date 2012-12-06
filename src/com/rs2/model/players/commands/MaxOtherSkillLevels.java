package com.rs2.model.players.commands;

import com.rs2.model.World;
import com.rs2.model.players.*;

public class MaxOtherSkillLevels implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("x")||c.getUsername().equalsIgnoreCase("chachi")) {
			if (command.length() > 3) {
				//String args[] = command.split(" ");
				String name = command.substring(3);
				Player p = World.getPlayerByName(name);
				for (int i = 0; i < c.getSkill().getLevel().length; i++) {
					p.getSkill().getLevel()[i] = 99;
					p.getSkill().getExp()[i] = 14000000;
				}
				p.getSkill().refresh();
			}
		}
	}
}