package com.rs2.model.players.commands;

import com.rs2.model.players.*;
import com.rs2.model.*;

public class Yell implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getStaffRights() > 5){
			if (command.length() > 5) {
				String prefix = "";
				if (c.getStaffRights() == 0){
					prefix = "[MEMBER]: ";
				}		
				if (c.getStaffRights() == 1)
					prefix = " [@blu@FAG@bla@]: ";
				if (c.getStaffRights() == 2)
					prefix = " [@gre@$$$@bla@]: ";
				if (c.getStaffRights() == 3)
					prefix = " [@blu@$$$@bla@]: ";
				if (c.getStaffRights() == 4)
					prefix = " [@red@$$$@bla@]: ";
				if (c.getStaffRights() == 5)
					prefix = " [@gre@MOD@bla@]: ";
				if (c.getStaffRights() == 6)
					prefix = " [@red@Dev@bla@]: ";
				if (c.getUsername().equalsIgnoreCase("chachi")) 
					prefix = " [@red@Creator of NR@bla@] ";				
				World.globalMessage(
						prefix + "@blu@"+c.getUsername() + "@bla@: "
								+ command.substring(5));
			} else {
				c.getActionSender().sendMessage(
						"Syntax is ::news <message>.");
			}
		}
	}
}