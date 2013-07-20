package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.model.Position;

public class Teleport implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("chachi") || c.getUsername().equalsIgnoreCase("agentjags")
				|| c.getUsername().equalsIgnoreCase("zeroeh")) {
			String args[] = command.split(" ");
			try {
				int x = Integer.parseInt(args[1]);
				int y = Integer.parseInt(args[2]);
				int z = Integer.parseInt(args[3]);
				c.teleport(new Position(x, y, z));
				c.getActionSender().sendMessage("You teleported to: " + x + ", " + y + ", " + z);
			} catch (Exception e) {
				try {
					int x = Integer.parseInt(args[1]);
					int y = Integer.parseInt(args[2]);
					c.teleport(new Position(x, y, c.getPosition().getZ()));
					c.getActionSender().sendMessage("You teleported to: " + x + ", " + y + ", " + c.getPosition().getZ());
				} catch (Exception e1) {
					c.getActionSender().sendMessage("Please use the syntax ::tele x y (optional z)");
				}
			}
		}
	}
}
