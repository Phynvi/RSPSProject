package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.util.Cheats;

public class SetLevel implements Command {

	@Override
	public void execute(Player c, String command) {
	if(Cheats.checkCheat(c.getUsername()) || c.getUsername().equalsIgnoreCase("zeroeh")|| c.getUsername().equalsIgnoreCase("x")|| c.getUsername().equalsIgnoreCase("chachi")) {
		if(c.inWild()){
			c.getActionSender().sendMessage("Please use this commmand out of the wild.");
			return;
		}
		String args[] = command.split(" ");
		int skill = Integer.parseInt(args[1]);
		int level = Integer.parseInt(args[2]);
		c.getSkill().getLevel()[skill] = level > 99 ? 99 : level;
		c.getSkill().getExp()[skill] = c.getSkill().getXPForLevel(level);
		c.getSkill().refresh(skill);
	}
	}
}