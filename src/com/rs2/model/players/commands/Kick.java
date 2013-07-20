package com.rs2.model.players.commands;

import com.rs2.model.World;
import com.rs2.model.players.*;

public class Kick implements Command {

	@Override
	public void execute(Player c, String command) {
	//if(c.getUsername().equalsIgnoreCase("chachi")) {
		if(c.getStaffRights() >= 5) {
			String args[] = command.split(" ");
		Player player = World.getPlayerByName(args[1]);
        if (player == null)
            return;
        player.disconnect();
        c.getActionSender().sendMessage("You have kicked "+player.getUsername());
	}
	}
}
