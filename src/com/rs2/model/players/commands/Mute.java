package com.rs2.model.players.commands;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.rs2.model.World;
import com.rs2.model.players.*;
import com.rs2.util.PunishmentManager;

public class Mute implements Command {

	@Override
	public void execute(Player c, String command) {
		if(c.getStaffRights() >= 5) {
		String args[] = command.split(" ");
        if (args.length < 2) {
            c.getActionSender().sendMessage("::mute hours username");
            return;
        }
        int hours = Integer.parseInt(args[1]);
        int maxHours = c.getStaffRights() == 1 ? 48 : 100;
        if (hours <= 0 || hours > maxHours) {
            c.getActionSender().sendMessage("Mute between 0 and "+maxHours+" hours");
            return;
        }
        String name = args[2];
        for (int i = 1; i < args.length; i++) {
            name += args[i];
        }
        Player player = World.getPlayerByName(name);
        if (player == null) {
            c.getActionSender().sendMessage("Could not find "+name);
            return;
        }
        if (player.isMuted()) {
            c.getActionSender().sendMessage("Player is already muted");
            return;
        }
        try {
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("./data/modlog.out", true));
            out.write(player.getUsername()+" was muted by "+c.getUsername()+" for "+hours+" hours.");
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
        c.getActionSender().sendMessage("Muted "+player.getUsername()+" for "+hours+" hours.");
        player.getActionSender().sendMessage("You have been muted for "+hours+" hours");
        player.setMuteExpire(System.currentTimeMillis()+(hours*60*60*1000));
    }
	}
}
