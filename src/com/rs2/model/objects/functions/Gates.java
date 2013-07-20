package com.rs2.model.objects.functions;

import com.rs2.Constants;
import com.rs2.model.Position;
import com.rs2.model.content.skills.SkillHandler;
import com.rs2.model.objects.GameObject;
import com.rs2.model.players.ObjectHandler;
import com.rs2.model.players.Player;
import com.rs2.model.tick.CycleEvent;
import com.rs2.model.tick.CycleEventContainer;
import com.rs2.model.tick.CycleEventHandler;

public class Gates {
    public enum GatesData {
        GATE1(new int[]{1153, 1151}, new Position[]{new Position(3253, 3267, 0), new Position(3253, 3266, 0)}, new Position[]{new Position(3254, 3267, 0), new Position(3253, 3267)}),
        GATE2(new int[]{2309}, new Position[]{new Position(2998, 3916, 0), new Position(2998, 3917, 0)}, new Position[]{new Position(2998, 3917, 0), new Position(2998, 3917, 0)}),
        GATE3(new int[]{2608, 2607}, new Position[]{new Position(2847, 9637, 0), new Position(2847, 9636, 0)}, new Position[]{new Position(2846, 9636, 0), new Position(2846, 9637)});

        int[] ids;
        Position[] unchangedPositions;
        Position[] changedPositions;

        GatesData(int[] ids, Position[] unchangedPosition, Position[] changedPosition) {
            this.ids = ids;
            this.unchangedPositions = unchangedPosition;
            this.changedPositions = changedPosition;
        }

        public static GatesData getGate(Position position){
            for(GatesData gate : GatesData.values()){
                for(Position pos : gate.unchangedPositions)
                    if(pos.equals(position)) return gate;
                for(Position pos : gate.changedPositions)
                    if(pos.equals(position)) return gate;
            }
            return null;
        }

    }
	public static void handlePassThroughGate(final Player player, final int id, final int x, final int y, final int z, final int destX, final int destY) {
		GatesData gatesData = GatesData.getGate(new Position(x, y, z));
		if (gatesData == null) {
			player.setStopPacket(false);
			return;
		}
		handleGate1(id, x, y, z);
		//final Gate gate = GateData;
		player.getActionSender().walkTo(destX, destY, false);
		CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				handleGate1(id, x, y, z);
				container.stop();
			}

			@Override
			public void stop() {
			}
		}, 1);
	} 
	public static void handlePassThroughGate2(final Player player, final int id, final int x, final int y, final int z, final int destX, final int destY) {
		GatesData gatesData = GatesData.getGate(new Position(x, y, z));
		if (gatesData == null) {
			player.setStopPacket(false);
			return;
		}
		handleGate(id, x, y, z);
		//final Gate gate = GateData;
		player.getActionSender().walkTo(destX, destY, false);
		CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
			@Override
			public void execute(CycleEventContainer container) {
				handleGate(id, x, y, z);
				container.stop();
			}

			@Override
			public void stop() {
			}
		}, 1);
	} 	
    public static void handleGate(int id, int x, int y, int z){
        GatesData gatesData = GatesData.getGate(new Position(x, y, z));
        if(gatesData == null) return;
        Position finalPosition1, finalPosition2;
        if(gateChanged(x, y, z)){
            finalPosition1 = gatesData.unchangedPositions[0];
            finalPosition2 = gatesData.unchangedPositions[1];
            removeObject(gatesData.ids[0], gatesData.changedPositions[0].getX(), gatesData.changedPositions[0].getY(), gatesData.changedPositions[0].getZ());
            removeObject(gatesData.ids[1], gatesData.changedPositions[1].getX(), gatesData.changedPositions[1].getY(), gatesData.changedPositions[1].getZ());

        } else {
            finalPosition1 = gatesData.changedPositions[0];
            finalPosition2 = gatesData.changedPositions[1];
            removeObject(gatesData.ids[0], gatesData.unchangedPositions[0].getX(), gatesData.unchangedPositions[0].getY(), gatesData.unchangedPositions[0].getZ());
            removeObject(gatesData.ids[1], gatesData.unchangedPositions[1].getX(), gatesData.unchangedPositions[1].getY(), gatesData.unchangedPositions[1].getZ());
        }
        new GameObject(gatesData.ids[0], finalPosition1.getX(), finalPosition1.getY(), finalPosition1.getZ(), Doors.getNextFace(new Doors(gatesData.ids[0], finalPosition1.getX(), finalPosition1.getY(), finalPosition1.getZ())), 0, 0, 999999999);
        new GameObject(gatesData.ids[1], finalPosition2.getX(), finalPosition2.getY(), finalPosition2.getZ(), Doors.getNextFace(new Doors(gatesData.ids[1], finalPosition2.getX(), finalPosition2.getY(), finalPosition2.getZ())), 0, 0, 999999999);
    }
    public static void handleGate1(int id, int x, int y, int z){
        GatesData gatesData = GatesData.getGate(new Position(x, y, z));
        if(gatesData == null) return;
        Position finalPosition1, finalPosition2;
        if(gateChanged(x, y, z)){
            finalPosition1 = gatesData.unchangedPositions[0];
            finalPosition2 = gatesData.unchangedPositions[1];
            removeObject(gatesData.ids[0], gatesData.changedPositions[0].getX(), gatesData.changedPositions[0].getY(), gatesData.changedPositions[0].getZ());

        } else {
            finalPosition1 = gatesData.changedPositions[0];
            finalPosition2 = gatesData.changedPositions[1];
            removeObject(gatesData.ids[0], gatesData.unchangedPositions[0].getX(), gatesData.unchangedPositions[0].getY(), gatesData.unchangedPositions[0].getZ());
        }
        new GameObject(gatesData.ids[0], finalPosition1.getX(), finalPosition1.getY(), finalPosition1.getZ(), Doors.getNextFace(new Doors(gatesData.ids[0], finalPosition1.getX(), finalPosition1.getY(), finalPosition1.getZ())), 0, 0, 999999999);
    }
    public static boolean gateChanged(int x, int y, int z){
        GatesData gatesData = GatesData.getGate(new Position(x, y, z));
        for(Position pos : gatesData.changedPositions)
            if(pos.equals(new Position(x, y, z))) return true;
        return false;
    }

    public static void removeObject(int id, int x, int y, int z){
        if(ObjectHandler.getInstance().getObject(x, y, z) != null){
            ObjectHandler.getInstance().removeObject(x, y, z, 0);
        } else {
            new GameObject(Constants.EMPTY_OBJECT, x, y, z, SkillHandler.getFace(id, x, y, z), SkillHandler.getType(id, x, y, z), Constants.EMPTY_OBJECT, 999999999);
        }
    }

}
