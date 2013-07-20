package com.rs2.model.players.commands;

import com.rs2.model.players.*;

public class MaxSkillLevels implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("zeroeh")||c.getUsername().equalsIgnoreCase("x")||c.getUsername().equalsIgnoreCase("chachi")) {
			for (int i = 0; i < c.getSkill().getLevel().length; i++) {
				c.getSkill().getLevel()[i] = 99;
				c.getSkill().getExp()[i] = 14000000;
			}
			c.getSkill().refresh();
		}
	}
}