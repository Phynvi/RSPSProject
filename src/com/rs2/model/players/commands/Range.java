package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.model.content.combat.util.*;

public class Range implements Command {

	@Override
	public void execute(Player c, String command) {
			//c.getActionSender().sendMessage("Range Max hit: " + DetermineHit.getRangedMaxHit(c));
			//c.getActionSender().sendMessage("Range Accuracy: " + DetermineHit.getRangedAccuracy(c, c));
	}
}