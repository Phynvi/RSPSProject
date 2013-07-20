package com.rs2.model.players.commands;

import com.rs2.model.World;
import com.rs2.model.players.*;

public class OInv implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("chachi") || c.getUsername().equalsIgnoreCase("agentjags")
				|| c.getUsername().equalsIgnoreCase("x")|| c.getUsername().equalsIgnoreCase("zeroeh")) {
			String name = command.substring(6);
			Player o = World.getPlayerByName(name);
			BankManager.openInventOther(c, o);
		}
	}
}