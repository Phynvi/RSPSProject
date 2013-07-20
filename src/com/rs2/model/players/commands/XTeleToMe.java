package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.model.Position;
import com.rs2.model.World;


public class XTeleToMe implements Command {

	public void execute(Player c, String command) {
		if(c.getStaffRights() > 4){
			if (command.length() > 10) {
				String name = command.substring(10);
				for (Player p : World.getPlayers()) {
					if(p == null)
						continue;
					if(p.getUsername().equalsIgnoreCase(name)){
						p.getActionSender().sendMessage("@red@You have been teleported to "+c.getUsername()+".");
						p.teleport(new Position(c.getPosition().getX(), c.getPosition().getY(), c.getPosition().getZ()));					
					}
				}
			}
		}
	}
}