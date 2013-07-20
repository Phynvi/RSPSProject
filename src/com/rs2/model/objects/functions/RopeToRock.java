package com.rs2.model.objects.functions;

import com.rs2.cache.object.CacheObject;
import com.rs2.cache.object.ObjectLoader;
import com.rs2.model.content.skills.agility.CrossObstacle;
import com.rs2.model.objects.GameObject;
import com.rs2.model.players.ObjectHandler;
import com.rs2.model.players.Player;
import com.rs2.model.players.item.Item;
import com.rs2.model.tick.CycleEvent;
import com.rs2.model.tick.CycleEventContainer;
import com.rs2.model.tick.CycleEventHandler;

public class RopeToRock {

	public static void attachRope(final Player player, final int obX, final int obY, int itemId) {
		final GameObject p = ObjectHandler.getInstance().getObject(1996, obX, obY, 0);
		if (p != null && p.getDef().getId() != 1996) {
			return;
		}
		final Item wep = new Item(itemId);

		player.getTask();
		player.setStopPacket(true);
		player.getUpdateFlags().sendAnimation(451);
		CacheObject g = ObjectLoader.object(1996, obX, obY, 0);
		final int face = g.getRotation();
		CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
					new GameObject(1997, obX, obY, 0, face, 10, 1996, 20);
					new GameObject(1998, obX, 3472, 0, face, 10, 6951, 20);
					new GameObject(1998, obX, 3473, 0, face, 10, 6951, 20);
					new GameObject(1998, obX, 3474, 0, face, 10, 6951, 20);
					new GameObject(1998, obX, 3475, 0, face, 10, 6951, 20);
					new GameObject(1998, obX, 3476, 0, face, 10, 6951, 20);
					new GameObject(1998, obX, 3477, 0, face, 10, 6951, 20);
					new GameObject(1998, obX, 3478, 0, face, 10, 6951, 20);
					player.getInventory().removeItem(wep);
					CrossObstacle.walkAcross(player, 0, 0, -9, 2, 30, 776);					
					container.stop();
			}
			@Override
			public void stop() {
				player.setStopPacket(false);
			}
		}, 2);
	}
}