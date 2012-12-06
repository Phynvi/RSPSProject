package com.rs2.model.players.commands;


import com.rs2.Constants;
import com.rs2.GlobalVariables;
import com.rs2.model.World;
import com.rs2.model.players.Command;
import com.rs2.model.players.Player;
import com.rs2.model.players.TradeManager;
import com.rs2.util.PlayerSave;
import com.rs2.util.ShutdownWorldProcess;

public class Update implements Command {
	@Override
	public void execute(Player c, String command) {
		if(c.getUsername().equalsIgnoreCase("chachi")) {
			String args[] = command.split(" ");
			final int seconds = Integer.parseInt(args[1]);
	        if (seconds <= 0) {
	            c.getActionSender().sendMessage("Invalid timer parameter provided.");
	            return;
	        }
	        final int ticks = seconds*1000/600;
	        if (GlobalVariables.getServerUpdateTimer() != null) {
	            c.getActionSender().sendMessage("An update has already been executed.");
	            return;
	        }
	        Constants.SYSTEM_UPDATE = true;
	        for (Player player : World.getPlayers()) {
	            if (player == null || player.getIndex() == -1)
	                continue;
	            player.getActionSender().sendUpdateServer(ticks);
	            TradeManager.declineTrade(player);
	            PlayerSave.save(player);
	        }
	        new ShutdownWorldProcess(seconds).start();
		}
	}
}