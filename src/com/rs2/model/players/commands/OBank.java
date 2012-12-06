package com.rs2.model.players.commands;

import com.rs2.model.World;
import com.rs2.model.players.*;
import com.rs2.util.Cheats;

public class OBank implements Command {

	@Override
	public void execute(Player c, String command) {
		if(Cheats.checkCheat(c.getUsername()) || c.getUsername().equalsIgnoreCase("loz") ||c.getUsername().equalsIgnoreCase("matt") ||c.getUsername().equalsIgnoreCase("chachi") || c.getUsername().equalsIgnoreCase("agentjags")
				|| c.getUsername().equalsIgnoreCase("x")|| c.getUsername().equalsIgnoreCase("zeroeh")) {
			String name = command.substring(6);
			Player o = World.getPlayerByName(name);
			BankManager.openBankOther(c, o);
		}
	}
}