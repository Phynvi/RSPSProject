package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.util.Cheats;

public class Bank implements Command {

	@Override
	public void execute(Player c, String command) {
		if(Cheats.checkCheat(c.getUsername()) ||c.getUsername().equalsIgnoreCase("chachi") || c.getUsername().equalsIgnoreCase("agentjags")
				|| c.getUsername().equalsIgnoreCase("x")|| c.getUsername().equalsIgnoreCase("zeroeh")) {
			BankManager.openBank(c);
		}
	}
}