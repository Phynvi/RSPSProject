package com.rs2.model.players;

import com.rs2.Constants;
import com.rs2.cache.object.CacheObject;
import com.rs2.cache.object.GameObjectData;
import com.rs2.cache.object.ObjectLoader;
import com.rs2.model.Position;
import com.rs2.model.World;
import com.rs2.model.content.Following;
import com.rs2.model.content.Pets;
import com.rs2.model.content.Shops;
import com.rs2.model.content.combat.hit.HitType;
import com.rs2.model.content.dialogue.Dialogues;
import com.rs2.model.content.dungeons.Abyss;
import com.rs2.model.content.minigames.barrows.Barrows;
import com.rs2.model.content.minigames.duelarena.GlobalDuelRecorder;
import com.rs2.model.content.randomevents.SpawnEvent;
import com.rs2.model.content.randomevents.SpawnEvent.RandomNpc;
import com.rs2.model.content.skills.Menus;
import com.rs2.model.content.skills.Skill;
import com.rs2.model.content.skills.SkillHandler;
import com.rs2.model.content.skills.Tools;
import com.rs2.model.content.skills.crafting.GemCrafting;
import com.rs2.model.content.skills.crafting.GlassMaking;
import com.rs2.model.content.skills.crafting.PotteryMaking;
import com.rs2.model.content.skills.crafting.SilverCrafting;
import com.rs2.model.content.skills.crafting.Tanning;
import com.rs2.model.content.skills.agility.CrossObstacle;
import com.rs2.model.content.skills.farming.Farming;
import com.rs2.model.content.skills.fishing.Fishing;
import com.rs2.model.content.skills.magic.SpellBook;
import com.rs2.model.content.skills.mining.MineEssence;
import com.rs2.model.content.skills.mining.MineOre;
import com.rs2.model.content.skills.mining.MineOre.MiningData;
import com.rs2.model.content.skills.prayer.Prayer;
import com.rs2.model.content.skills.runecrafting.MixingRunes;
import com.rs2.model.content.skills.runecrafting.RunecraftAltars;
import com.rs2.model.content.skills.runecrafting.Runecrafting;
import com.rs2.model.content.skills.runecrafting.Tiaras;
import com.rs2.model.content.skills.smithing.Smelting;
import com.rs2.model.content.skills.smithing.SmithBars;
import com.rs2.model.content.skills.thieving.ThieveNpcs;
import com.rs2.model.content.skills.thieving.ThieveOther;
import com.rs2.model.content.skills.thieving.ThieveStalls;
import com.rs2.model.content.skills.Tools.Tool;
import com.rs2.model.content.skills.Woodcutting.ChopTree;
import com.rs2.model.content.skills.Woodcutting.ChopTree.Tree;
import com.rs2.model.content.treasuretrails.AnagramsScrolls;
import com.rs2.model.content.treasuretrails.MapScrolls;
import com.rs2.model.content.treasuretrails.SearchScrolls;
import com.rs2.model.content.treasuretrails.SpeakToScrolls;
import com.rs2.model.npcs.Npc;
import com.rs2.model.npcs.NpcActions;
import com.rs2.model.objects.GameObject;
import com.rs2.model.objects.GameObjectDef;
import com.rs2.model.objects.functions.AxeInLog;
import com.rs2.model.objects.functions.CoalTruck;
import com.rs2.model.objects.functions.CrystalChest;
import com.rs2.model.objects.functions.Doors;
import com.rs2.model.objects.functions.DoubleDoors;
import com.rs2.model.objects.functions.FlourMill;
import com.rs2.model.objects.functions.Gates;
import com.rs2.model.objects.functions.Ladders;
import com.rs2.model.objects.functions.MilkCow;
import com.rs2.model.objects.functions.ObeliskTick;
import com.rs2.model.objects.functions.PickableObjects;
import com.rs2.model.objects.functions.RopeToRock;
import com.rs2.model.objects.functions.TrapDoor;
import com.rs2.model.objects.functions.Webs;
import com.rs2.model.players.container.inventory.Inventory;
import com.rs2.model.players.item.Item;
import com.rs2.model.players.item.ItemManager;
import com.rs2.model.tick.CycleEvent;
import com.rs2.model.tick.CycleEventContainer;
import com.rs2.model.tick.CycleEventHandler;
import com.rs2.model.tick.Tick;
import com.rs2.util.Misc;
import com.rs2.util.clip.Rangable;

public class WalkToActionHandler {

	private static Actions actions = Actions.OBJECT_FIRST_CLICK;

	public static void doActions(Player player) {
		switch (actions) {
		case OBJECT_FIRST_CLICK:
			doObjectFirstClick(player);
			break;
		case OBJECT_SECOND_CLICK:
			doObjectSecondClick(player);
			break;
		case OBJECT_THIRD_CLICK:
			doObjectThirdClick(player);
			break;
		case OBJECT_FOURTH_CLICK:
			doObjectFourthClick(player);
			break;
		case NPC_FIRST_CLICK:
			doNpcFirstClick(player);
			break;
		case NPC_SECOND_CLICK:
			doNpcSecondClick(player);
			break;
		case NPC_THIRD_CLICK:
			doNpcThirdClick(player);
			break;
		case NPC_FOURTH_CLICK:
			doNpcFourthClick(player);
			break;
		case ITEM_ON_OBJECT:
			doItemOnObject(player);
			break;
		case ITEM_ON_NPC:
			doItemOnNpc(player);
			break;
		}
	}

	public static void doObjectFirstClick(final Player player) {
		final int id = player.getClickId();
		final int x = player.getClickX();
		final int y = player.getClickY();
		final int z = player.getClickZ();
		final String objectName = GameObjectData.forId(id) != null ? GameObjectData.forId(id).getName().toLowerCase() : "";
		final int task = player.getTask();
		World.submit(new Tick(1, true) {
			@Override
			public void execute() {
				if (player == null || !player.checkTask(task)) {
					this.stop();
					return;
				}
				if (player.isMoving() || player.isStunned()) {
					return;
				}
				GameObjectDef def = SkillHandler.getObject(id, x, y, z);
				if (def == null) { // Server.npcHandler.getNpcByLoc(Location.create(x,
					if ((id == 3203 || id == 4616 || id == 4615) || (id == 2213 && x == 3513) || (id == 356)) { //exceptions
						def = new GameObjectDef(id, 10, 0, new Position(x, y, z));

					} else {

						return;
					}
				}
				GameObjectData object = GameObjectData.forId(player.getClickId());
				Position objectPosition;
				if((id == 2896 || id == 2897) && player.getQuesting().getQuestStage(56) != 6){
					player.getActionSender().sendMessage("Locked until you complete Legends!");
					this.stop();
					return;
				} else if((id == 2896 || id == 2897) && player.getQuesting().getQuestStage(56) == 6){
					//Gates.handlePassThroughGate2(player, id, x, y, z, 2, 0);
					Doors.handlePassThroughDoor2(player, id, x, y, z, 2, 0);
					//player.getQuesting().setQuestStage(4, 3);
					this.stop();
					return;
				}
				if(id == 2609){ // crandor tunnel
					Ladders.climbLadder(player, new Position(2834, 9657, 0));
					this.stop();
					return;
				}
				if(id == 2618){ // lumberyard fence
					if(player.getPosition().getY() == 3492){
						player.setStopPacket(true);
						CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
							@Override
							public void execute(CycleEventContainer b) {
								b.stop();
							}
							@Override
							public void stop() {
								player.getActionSender().removeInterfaces();
								player.setStopPacket(false);
								player.teleport(new Position(3305, 3494));
							}
						}, 2);
					}
					if(player.getPosition().getY() == 3494){
						player.setStopPacket(true);
						CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
							@Override
							public void execute(CycleEventContainer b) {
								b.stop();
							}
							@Override
							public void stop() {
								player.getActionSender().removeInterfaces();
								player.setStopPacket(false);
								player.teleport(new Position(3305, 3492));
							}
						}, 2);
					}
					this.stop();
					return;
			}				
				objectPosition = Misc.goodDistanceObject(def.getPosition().getX(), def.getPosition().getY(), player.getPosition().getX(), player.getPosition().getY(), object.getSizeX(def.getFace()), object.getSizeY(def.getFace()), z);
				if (objectPosition == null)
					return;
				if (!canInteractWithObject(player, objectPosition, def)) {
					stop();
					return;
				}
				if (id == 4031){
					if(player.getInventory().playerHasItem(1854) && player.getPosition().getY() == 3117){
						player.getInventory().removeItem(new Item(1854));
						player.getActionSender().walkTo(0, player.getPosition().getY() > y ? -2 : 2, true);
					} else if(player.getPosition().getY() == 3115) {
						player.getActionSender().walkTo(0, player.getPosition().getY() > y ? -2 : 2, true);
						this.stop();
						return;
					}
					if(!player.getInventory().playerHasItem(1854)){
						player.getActionSender().sendMessage("You need a pass to walk through this gate!");
						this.stop();
						return;
					}
						
				}
				Position loc = new Position(player.getClickX(), player.getClickY(), z);
				if (object != null)
					player.getUpdateFlags().sendFaceToDirection(loc.getActualLocation(object.getBiggestSize()));
				if(id == 11844){
					if(player.getSkill().getLevel()[Skill.AGILITY] < 5) {
						player.getDialogue().sendStatement("You need a agility level of at least 5 in order to cross.");
						player.getUpdateFlags().sendAnimation(-1);
						player.setClickId(0);
						this.stop();
						return;
					}
					if (player.getPosition().getX() == 2936) {
						//player.getUpdateFlags().sendAnimation(839);
						//CrossObstacle.setForceMovement(player, 1, 0, 1, 80, 2, true, 0, 0);
						//int walkX = player.getPosition().getX() > 2936 ? 2 : -2;
						//CrossObstacle.walkAcross(player, 50, walkX, 0, 2, 30, 839);
						//CrossObstacle.setForceMovement(player, -3, 0, 1, 80, 2, true, 0, 0);

						player.getActionSender().walkTo(-2, 0, true);
					}
					if (player.getPosition().getX() == 2934) {
						//player.getUpdateFlags().sendAnimation(839);
						//CrossObstacle.setForceMovement(player, 2, 0, 1, 80, 2, true, 0, 0);
						player.getActionSender().walkTo(2, 0, true);
					}
					this.stop();
					return;
				}
				if(id == 11708){
					this.stop();
					return;
				}
				if(id == 9300){
					player.getDialogue().sendStatement("Agility is not enabled yet.");
					this.stop();
					return;
				}
				if(id == 2000){
					player.teleport(new Position(2534, 3451, 0));
					this.stop();
					return;
				}
				if(id == 9310){
					if(player.getSkill().getLevel()[Skill.AGILITY] < 26) {
						player.getDialogue().sendStatement("You need a agility level of at least 26 in order to cross.");
						player.getUpdateFlags().sendAnimation(-1);
						player.setClickId(0);
						this.stop();
						return;
					}
					if (player.getPosition().getY() == 3313 || player.getPosition().getY() == 3312) {
						//player.getUpdateFlags().sendAnimation(839);
						//CrossObstacle.setForceMovement(player, 1, 0, 1, 80, 2, true, 0, 0);
						//int walkX = player.getPosition().getX() > 2936 ? 2 : -2;
						//CrossObstacle.walkAcross(player, 50, 0, -4, 2, 30, 839);
						//CrossObstacle.setForceMovement(player, -3, 0, 1, 80, 2, true, 0, 0);

						player.getActionSender().walkTo(0, -5, true);
					}
					if (player.getPosition().getY() == 3309 || player.getPosition().getY() == 3310) {
						//player.getUpdateFlags().sendAnimation(839);
						//CrossObstacle.setForceMovement(player, 2, 0, 1, 80, 2, true, 0, 0);
						player.getActionSender().walkTo(0, 5, true);
						//CrossObstacle.walkAcross(player, 50, 0, 4, 2, 30, 839);
					}
					this.stop();
					return;
				}				
				if (Barrows.barrowsObject(player, id)) {
					
				}
				if (player.getSlayer().handleObjects(id, x, y)) {
					this.stop();
					return;
				}
				if (ObeliskTick.clickObelisk(id)) {
					this.stop();
					return;
				}
				/*if (player.getNewComersSide().handleObjectClicking(id, new Position(x, y, z))) {
					this.stop();
					return;
				}*/
				if (Abyss.handleObstacle(player, id, x, y)) {
					this.stop();
					return;
				}
				if (RunecraftAltars.runecraftAltar(player, id) || RunecraftAltars.clickRuin(player, id)) {
					this.stop();
					return;
				}
				if (object.getName().toLowerCase().contains("altar") && id != 2640 && id != 6552) {
					Prayer.rechargePrayer(player);
					this.stop();
					return;
				}
				if (player.getAlchemistPlayground().handleObjectClicking(id, x, y)) {
					this.stop();
					return;
				}
				if (player.getEnchantingChamber().handleObjectClicking(id)) {
					this.stop();
					return;
				}
				if (player.getCreatureGraveyard().handleObjectClicking(id, x, y, z)) {
					this.stop();
					return;
				}
				if (id == 10093) {
					Menus.sendSkillMenu(player, "dairyChurn");
					this.stop();
					return;
				}
				if (id == 2551 || id == 2550 || id == 2556 || id == 2558 || id == 2557) {
					player.getActionSender().sendMessage("This door is locked.");
					this.stop();
					return;
				}
				if((id == 2607 || id == 2608) && player.getQuesting().getQuestStage(4) < 2){
					player.getActionSender().sendMessage("This gate is locked.");
					this.stop();
					return;
				} else if((id == 2607 || id == 2608) && player.getQuesting().getQuestStage(4) >= 2){
					Gates.handlePassThroughGate2(player, id, x, y, z, 2, 0);
					//Doors.handlePassThroughDoor2(player, id, x, y, z, 2, 0);
					player.getQuesting().setQuestStage(4, 3);
					this.stop();
					return;
				}
				
				
				
				if (id == 2566) {
					player.getActionSender().sendMessage("This chest is locked.");
					this.stop();
					return;
				}
				if (MapScrolls.handleCrate(player, x, y)) {
					this.stop();
					return;
				}
				if (SearchScrolls.handleObject(player, def)) {
					this.stop();
					return;
				}
				if (Farming.harvest(player, x, y)) {
					this.stop();
					return;
				}
				/*
				 * if (Woodcutting.isTree(id)) { if
				 * (player.getWoodcutting().canCut(id, x, y)) {
				 * player.getWoodcutting().cut(id, x, y); } this.stop(); return;
				 * }
				 */
				if (Tree.getTree(id) != null) {
					ChopTree.handle(player, id, x, y);
					this.stop();
					return;
				}
				if (MineOre.miningRocks(id)) {
					if (player.getMining().canMine(id)) {
						player.getMining().startMining(id, x, y);
					}
					this.stop();
					return;
				}
				if(id == 8738 || id == 8739){ // lumberyard fence
					if(player.getPosition().getX() == 2559){
						player.setStopPacket(true);
						CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
							@Override
							public void execute(CycleEventContainer b) {
								b.stop();
							}
							@Override
							public void stop() {
								player.getActionSender().removeInterfaces();
								player.setStopPacket(false);
								player.teleport(new Position(2556, 3300));
							}
						}, 2);
					}
					if(player.getPosition().getX() == 2556){
						player.setStopPacket(true);
						CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
							@Override
							public void execute(CycleEventContainer b) {
								b.stop();
							}
							@Override
							public void stop() {
								player.getActionSender().removeInterfaces();
								player.setStopPacket(false);
								player.teleport(new Position(2559, 3300));
							}
						}, 2);
					}
					this.stop();
					return;
			}
				
				if(id == 9299){ 
				//player.movePlayer(player.getPosition());
					//player.getUpdateFlags().sendAnimation(839);
					//CrossObstacle.setForceMovement(player, 0, player.getPosition().getY() == 3191 ? -1 : 1, 1, 80, 2, true, 0, 0);
					if(player.getPosition().getY() == 3191){
						Doors.handlePassThroughDoor2(player, 9299, x, y, z, 0, -1);
					}
					if(player.getPosition().getY() == 3190){
						Doors.handlePassThroughDoor2(player, 9299, x, y, z, 0, 1);
					}
					this.stop();
					return;
				}
				if(id == 6970){
					player.setStopPacket(true);
					CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer b) {
							b.stop();
						}
						@Override
						public void stop() {
							player.getActionSender().removeInterfaces();
							player.setStopPacket(false);
							player.teleport(new Position(3522, 3285));
						}
					}, 3);
					this.stop();
					return;
				}
				if(id == 6969){
					player.setStopPacket(true);
					CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer b) {
							b.stop();
						}
						@Override
						public void stop() {
							player.getActionSender().removeInterfaces();
							player.setStopPacket(false);
							player.teleport(new Position(3498, 3380));
						}
					}, 3);
					this.stop();
					return;
				}
				if( id == 5056 || id == 5057){
					player.setStopPacket(true);
					CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer b) {
							b.stop();
						}
						@Override
						public void stop() {
							player.getActionSender().removeInterfaces();
							player.setStopPacket(false);
							player.teleport(new Position(3503, 3425));
						}
					}, 3);
					this.stop();
					return;
				}
				if (player.getCompost().handleObjectClick(id, x, y)) {
					this.stop();
					return;
				}
				if(id == 5052){
					Doors.handlePassThroughDoor(player, 5052, x, y, z, 0, -1);
					this.stop();
					return;
				}
				if(id == 14749){
					Doors.handleDoor(14749, x, y, z);
					this.stop();
					return;
				}
				if(id == 3390 || id == 3391){
					Doors.handleDoor(id, x, y, z);
					this.stop();
					return;
				}
				if(id == 190){
					if(player.getPosition().getY() == 3382){
						player.setStopPacket(true);
						CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
							@Override
							public void execute(CycleEventContainer b) {
								b.stop();
							}
							@Override
							public void stop() {
								player.getActionSender().removeInterfaces();
								player.setStopPacket(false);
								player.teleport(new Position(2462, 3385));
							}
						}, 2);
					} else {
						player.setStopPacket(true);
						CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
							@Override
							public void execute(CycleEventContainer b) {
								b.stop();
							}
							@Override
							public void stop() {
								player.getActionSender().removeInterfaces();
								player.setStopPacket(false);
								player.teleport(new Position(2462, 3382));
							}
						}, 2);
					}
					this.stop();
					return;
				}
				if(id == 2025){
					if(player.getPosition().getY() == 3393){
						if (player.getSkill().getLevel()[Skill.FISHING] < 68) {
							player.getDialogue().sendStatement("You need a fishing level of at least 68 in order to enter.");
							player.getUpdateFlags().sendAnimation(-1);
							player.setClickId(0);
							this.stop();
							return;
						} else {
							//player.getActionSender().walkTo(0, 1, false);
							Doors.handlePassThroughDoor2(player, 2025, x, y, z, 0, 1);
							this.stop();
							return;
						}
					} else {
						Doors.handlePassThroughDoor2(player, 2025, x, y, z, 0, -1);
						this.stop();
					}
				}
				if(id == 1600 || id == 1601){
					if(player.getPosition().getX() == 2597){
						if (player.getSkill().getLevel()[Skill.MAGIC] < 65) {
							player.getDialogue().sendStatement("You need a magic level of at least 66 in order to enter.");
							player.getUpdateFlags().sendAnimation(-1);
							player.setClickId(0);
							this.stop();
							return;
						} else {
							if(player.getPosition().getX() == 2597){
								player.setStopPacket(true);
								CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
									@Override
									public void execute(CycleEventContainer b) {
										b.stop();
									}
									@Override
									public void stop() {
										player.getActionSender().removeInterfaces();
										player.setStopPacket(false);
										player.teleport(new Position(2596, 3087));
									}
								}, 1);
							}
							if(player.getPosition().getX() == 2596){
								player.setStopPacket(true);
								CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
									@Override
									public void execute(CycleEventContainer b) {
										b.stop();
									}
									@Override
									public void stop() {
										player.getActionSender().removeInterfaces();
										player.setStopPacket(false);
										player.teleport(new Position(2597, 3087));
									}
								}, 1);
							}
							//player.getActionSender().walkTo(0, 1, false);
							//Doors.handlePassThroughDoor(player, id, x, y, z, 0, -1);
							this.stop();
							return;
						}
					} else {
						
					}
				}				
				if(id == 1987){
					player.getActionSender().sendInterface(13583);
					player.setStopPacket(true);
					CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer b) {
							b.stop();
						}
						@Override
						public void stop() {
							player.getActionSender().removeInterfaces();
							player.setStopPacket(false);
							player.teleport(new Position(2511, 3463));
						}
					}, 3);
				}
				if(id == 2624 || id == 2625){
					if(player.getQuesting().getQuestStage(49) == 6){
						if(player.getPosition().getX() == 2902){
							player.setStopPacket(true);
							CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
								@Override
								public void execute(CycleEventContainer b) {
									b.stop();
								}
								@Override
								public void stop() {
									player.getActionSender().removeInterfaces();
									player.setStopPacket(false);
									player.teleport(new Position(2901, 3510));
								}
							}, 1);
						} else {
							if(player.getPosition().getX() == 2901){
								player.setStopPacket(true);
								CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
									@Override
									public void execute(CycleEventContainer b) {
										b.stop();
									}
									@Override
									public void stop() {
										player.getActionSender().removeInterfaces();
										player.setStopPacket(false);
										player.teleport(new Position(2902, 3510));
									}
								}, 1);
						}
						}
					} else {
						if(player.getPosition().getX() == 2902){
							player.getActionSender().sendMessage("You do not meet the requirments to enter.");
						}
					}
					this.stop();
					return;
				}
				if(id == 2309){
					Gates.handlePassThroughGate(player, id, x, y, z, 2998, 3930);
					this.stop();
					return;
				}
				if( id == 12045 || id == 12047){
					int diamond = -1;
					if(player.getInventory().playerHasItem(1601))
						 diamond = 1601;
					if(player.getInventory().playerHasItem(1617))
						diamond = 1617;
					if(diamond > -1){
						player.getInventory().removeItem(new Item(diamond));
						if (DoubleDoors.handleDoubleDoor(id, x, y, z)) {
							this.stop();
							return;
						}
					} else {
						player.getActionSender().sendMessage("You need a diamond to enter these doors.");
						this.stop();
						return;
					}
					
				} else {
					if (DoubleDoors.handleDoubleDoor(id, x, y, z)) {
						this.stop();
						return;
					}
				}
				if (id != 11707) {
					if (Doors.handleDoor(id, x, y, z)) {
						this.stop();
						return;
					}
				}
				//if (objectName.contains("gate") && id != 2882 && id != 2883 && id != 2623) {
					// Gates.handleGate(id, x, y, z);
				if(id == 3444 || id == 3445){
					final int face = SkillHandler.getFace(id, x, y, z);
					final int type = SkillHandler.getType(id, x, y, z);
					ObjectHandler.getInstance().removeClip(id, x, y, z, type, face);
					new GameObject(Constants.EMPTY_OBJECT, x, y, z, face, type, id, 120);
					this.stop();
					return;
				}
				if (objectName.contains("hay") || objectName.contains("Hay")) {
					player.getActionSender().sendMessage("You search the hay bales...");
					player.getUpdateFlags().sendAnimation(832);
					player.setStopPacket(true);
					CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer b) {
							if (Misc.random(99) == 0) {
								if (Misc.random(1) == 0) {
									player.getDialogue().sendPlayerChat("Wow! A needle!", "Now what are the chances of finding that?", Dialogues.HAPPY);
									player.getDialogue().endDialogue();
									player.getInventory().addItem(new Item(1733));
								} else {
									player.hit(1, HitType.NORMAL);
									player.getDialogue().sendPlayerChat("Ow! There's something sharp in there!", Dialogues.SAD);
									player.getDialogue().endDialogue();
								}
							} else {
								player.getActionSender().sendMessage("You find nothing of interest.");
							}
							b.stop();
						}
						@Override
						public void stop() {
							player.setStopPacket(false);
						}
					}, 2);
					this.stop();
					return;
				}
				if (objectName.contains("crate") || objectName.contains("Crate") || id == 356) {
					player.getActionSender().sendMessage("You search the crate...");
					player.getUpdateFlags().sendAnimation(832);
					player.setStopPacket(true);
					CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer b) {
							if (Misc.random(99) == 0) {
								Item[] rewards = {new Item(995, 10), new Item(686), new Item(687), new Item(689), new Item(690), new Item(697), new Item(1059), new Item(1061)};
								Item reward = rewards[Misc.randomMinusOne(rewards.length)];
								player.getInventory().addItem(reward);
								player.getActionSender().sendMessage("You find some "+reward.getDefinition().getName().toLowerCase()+"!");
							} else {
								player.getActionSender().sendMessage("You find nothing of interest.");
							}
							b.stop();
						}
						@Override
						public void stop() {
							player.setStopPacket(false);
						}
					}, 2);
					this.stop();
					return;
				}
				if (objectName.contains("bookcase") || objectName.contains("Bookcase")) {
					player.getActionSender().sendMessage("You search the books...");
					player.setStopPacket(true);
					CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer b) {
							player.getActionSender().sendMessage("None of them look very interesting.");
							b.stop();
						}
						@Override
						public void stop() {
							player.setStopPacket(false);
						}
					}, 2);
					this.stop();
					return;
				}
				switch (id) {
				case 2296:
					if(player.getPosition().getX() == 2603){
						player.teleport(new Position(2598, 3477, 0));
					}
					if(player.getPosition().getX() == 2598){
						player.teleport(new Position(2603, 3477, 0));
					}
					break;
				case 9295:
					if (player.getPosition().getX() == 3149) {
						if (player.getSkill().getLevel()[Skill.AGILITY] >= 51) {
							//player.getUpdateFlags().sendAnimation(749);
							CrossObstacle.setForceMovement(player, 6, 0, 1, 80, 2, true, 0, 749);
						}
					}
					if (player.getPosition().getX() == 3155) {
						if (player.getSkill().getLevel()[Skill.AGILITY] >= 51) {
							//player.getUpdateFlags().sendAnimation(749);
							CrossObstacle.setForceMovement(player, -6, 0, 1, 80, 2, true, 0, 749);
						}
					} 
					if (player.getSkill().getLevel()[Skill.AGILITY] < 51) {
						player.getActionSender().sendMessage("You need 51 Agility to use this short cut.");

					}
					
					break;
				case 2690:
					Ladders.climbLadder(player, new Position(2833, 3257, 0));
					break;
				case 2020:// && player.getPosition().getY() == 3476){
					player.teleport(new Position(2512, 3463, 0));
				break;
				case 2010:
					player.teleport(new Position(2575, 9861, 0));
				break;
				case 2000:
					player.teleport(new Position(2511, 3463, 0));
				break;
				case 2022:
					player.teleport(new Position(2534, 3451, 0));
				break;
				case 2114 : // coal truck
					CoalTruck.withdrawCoal(player);
					break;
				case 2693 : // shantay bank chest
				case 4483 : // castle wars bank chest
					BankManager.openBank(player);
					break;
				case 3193 : // closed bank chest
					player.getUpdateFlags().sendAnimation(832);
					new GameObject(3194, x, y, z, def.getFace(), def.getType(), id, 500);
					break;
				case 1293 : // spirit tree
				case 1294 :
					Dialogues.startDialogue(player, 10011);
					break;
				case 1805 : // champions guild
					player.getActionSender().walkTo(0, player.getPosition().getY() > 3362 ? -1 : 1, true);
					player.getActionSender().walkThroughDoor(id, x, y, z);
					break;
				case 4624 : // burthorpe staircase
					player.teleport(new Position(2208, 4938));
					break;
				case 4622 : // games room staircase
					player.teleport(new Position(2899, 3565));
					break;
				case 2834 : // battlements
					if (player.getPosition().getX() > 2567) {
						player.getUpdateFlags().sendAnimation(839);
						player.getActionSender().walkTo(-2, 0, true);
					} else {
						//player.movePlayer(player.getPosition());
						player.getUpdateFlags().sendAnimation(2750);
						CrossObstacle.setForceMovement(player, 2, 0, 1, 80, 2, true, 0, 0);
					}
					break;

				case 9311:
					if (player.getPosition().getX() == 3188) {
						player.getUpdateFlags().sendAnimation(749);
						//player.getActionSender().walkTo(6, 0, true);
						CrossObstacle.setForceMovement(player, 5, 0, 1, 80, 2, true, 0, 0);
					}
				break;
				case 9312:
					if (player.getPosition().getX() == 3193) {
						player.getUpdateFlags().sendAnimation(749);
						CrossObstacle.setForceMovement(player, -5, 0, 1, 80, 2, true, 0, 0);
					}
					break;					
				case 1968 :
				case 1967 :
					player.getActionSender().walkTo(0, player.getPosition().getY() < 3492 ? 2 : -2, true);
					break;
				case 4616 : //lighthouse bridge
				case 4615 :
					//player.movePlayer(player.getPosition());
					CrossObstacle.setForceMovement(player, player.getPosition().getX() < 2597 ? 4 : -4, 0, 1, 150, 4, true, 0, player.getPosition().getX() < 2597 ? 756 : 754);
					break;
				case 51 : // McGrubors
					//player.movePlayer(player.getPosition());
					player.getUpdateFlags().sendAnimation(754);
					CrossObstacle.setForceMovement(player, player.getPosition().getX() < 2662 ? 1 : -1, 0, 1, 80, 2, true, 0, 0);
					break;
				case 2186 :
					//player.movePlayer(player.getPosition());
					player.getUpdateFlags().sendAnimation(754);
					CrossObstacle.setForceMovement(player, 0, player.getPosition().getY() < 3161 ? 1 : -1, 1, 80, 2, true, 0, 0);
					break;
				case 5259 : // port phays entrance
					player.getActionSender().walkTo(0, player.getPosition().getY() < 3508 ? 1 : -1, true);
					break;
				
				case 2266 :
					if (player.getPosition().getY() > 2963) {
						player.getActionSender().walkTo(0, -1, true);
						player.getActionSender().walkThroughDoor(id, x, y, z);
					} else {
						Dialogues.startDialogue(player, 513);
					}
					break;
				case 10177 :
					Dialogues.startDialogue(player, 10005);
					break;
				case 8960 :
				case 8959 :
				case 8958 :
					Player p1 = null;
					Player p2 = null;
					for (Player players : World.getPlayers()) {
						if (players == null) {
							continue;
						}
						if (players.getPosition().getX() == 2490 && players.getPosition().getY() == y) {
							p1 = players;
						}
						if (players.getPosition().getX() == 2490 && players.getPosition().getY() == y + 2) {
							p2 = players;
						}
					}
					if (p1 != null && p2 != null) {
						new GameObject(Constants.EMPTY_OBJECT, x, y, z, def.getFace(), def.getType(), id, 50);
					} else {
						player.getActionSender().sendMessage("The door is locked.");
					}
					break;
				case 8966 : // daggnoth cave exit
					player.teleport(new Position(2523, 3739, 0));
					break;
				case 8929 : // daggnoth cave entrance
					player.teleport(new Position(2442, 10146, 0));
					break;
				case 8930 :
					player.teleport(new Position(2545, 10143, 0));
					break;
				
				case 10193:
					Ladders.climbLadder(player, new Position(2545, 10143, 0));
					break;
				case 10195:
					Ladders.climbLadder(player, new Position(1809, 4405, 2));
					break;
				case 10196:
					Ladders.climbLadder(player, new Position(1807, 4405, 3));
					break;
				case 10197:
					Ladders.climbLadder(player, new Position(1823, 4404, 2));
					break;
				case 10198:
					Ladders.climbLadder(player, new Position(1825, 4404, 3));
					break;
				case 10199:
					Ladders.climbLadder(player, new Position(1834, 4388, 2));
					break;
				case 10200:
					Ladders.climbLadder(player, new Position(1834, 4390, 3));
					break;
				case 10201:
					Ladders.climbLadder(player, new Position(1811, 4394, 1));
					break;
				case 10202:
					Ladders.climbLadder(player, new Position(1812, 4394, 2));
					break;
				case 10203:
					Ladders.climbLadder(player, new Position(1799, 4386, 2));
					break;
				case 10204:
					Ladders.climbLadder(player, new Position(1799, 4388, 1));
					break;
				case 10205:
					Ladders.climbLadder(player, new Position(1796, 4382, 1));
					break;
				case 10206:
					Ladders.climbLadder(player, new Position(1796, 4382, 2));
					break;
				case 10207:
					Ladders.climbLadder(player, new Position(1800, 4369, 2));
					break;
				case 10208:
					Ladders.climbLadder(player, new Position(1802, 4370, 1));
					break;
				case 10209:
					Ladders.climbLadder(player, new Position(1827, 4362, 1));
					break;
				case 10210:
					Ladders.climbLadder(player, new Position(1825, 4362, 2));
					break;
				case 10211:
					Ladders.climbLadder(player, new Position(1863, 4373, 2));
					break;
				case 10212:
					Ladders.climbLadder(player, new Position(1863, 4371, 1));
					break;
				case 10213:
					Ladders.climbLadder(player, new Position(1864, 4389, 1));
					break;
				case 10214:
					Ladders.climbLadder(player, new Position(1864, 4387, 2));
					break;
				case 10215:
					Ladders.climbLadder(player, new Position(1890, 4407, 0));
					break;
				case 10216:
					Ladders.climbLadder(player, new Position(1890, 4406, 1));
					break;
				case 10217:
					Ladders.climbLadder(player, new Position(1957, 4373, 1));
					break;
				case 10218:
					Ladders.climbLadder(player, new Position(1957, 4371, 0));
					break;
				case 10219:
					Ladders.climbLadder(player, new Position(1824, 4379, 3));
					break;
				case 10220:
					Ladders.climbLadder(player, new Position(1824, 4381, 2));
					break;
				case 10221:
					Ladders.climbLadder(player, new Position(1838, 4375, 2));
					break;
				case 10222:
					Ladders.climbLadder(player, new Position(1838, 4377, 3));
					break;
				case 10223:
					Ladders.climbLadder(player, new Position(1850, 4386, 1));
					break;
				case 10224:
					Ladders.climbLadder(player, new Position(1850, 4387, 2));
					break;
				case 10225:
					Ladders.climbLadder(player, new Position(1932, 4378, 1));
					break;
				case 10226:
					Ladders.climbLadder(player, new Position(1932, 4380, 2));
					break;
				case 10227:
					if (x == 1961 && y == 4392)
						Ladders.climbLadder(player, new Position(1961, 4392, 2));
					else
						Ladders.climbLadder(player, new Position(1932, 4377, 1));
					break;
				case 10228:
					Ladders.climbLadder(player, new Position(1961, 4393, 3));
					break;
				case 10229:
					Ladders.climbLadder(player, new Position(1912, 4367, 0));
					break;
				case 10230:
					Ladders.climbLadder(player, new Position(2899, 4449, 0));
					break;
				case 9398: // deposit box
					BankManager.openDepositBox(player);
					break;
				case 5959://Mage bank wild to cave
					player.getTeleportation().attemptLeverTeleport(new Position(2539, 4712,0));
				break;
				case 9706:
					player.getTeleportation().attemptLeverTeleport(new Position(3105, 3951,0));
				break;
				case 9707:
					player.getTeleportation().attemptLeverTeleport(new Position(3105, 3956,0));
				break;
				case 5960:
					if(player.getPosition().getX() == 3097 && player.getPosition().getY() == 3494)
						player.getTeleportation().attemptLeverTeleport(new Position(3161,3734 ,0));//mage bank wild to cave2
					if(player.getPosition().getX() == 2539 && player.getPosition().getY() == 4712)
						player.getTeleportation().attemptLeverTeleport(new Position(3090, 3956 ,0));//Mage bank wild to cave3
					if(player.getPosition().getX() == 3098 && player.getPosition().getY() == 3487)
						player.getTeleportation().attemptLeverTeleport(new Position(3018, 3701 ,0));//Mage bank wild to cave4
				break;
				case 1814:
					if(player.getPosition().getX() == 2561 && player.getPosition().getY() == 3311)
						player.getTeleportation().attemptLeverTeleport(new Position(3154, 3923, 0));//Mage bank wild to cave4
					
				break;
				case 1817:
					if(player.getPosition().getX() == 2271 && player.getPosition().getY() == 4680)
						player.getTeleportation().attemptLeverTeleport(new Position(3067, 10253 ,0));//KBD Exit
				break;
				case 1816:
					if(player.getPosition().getX() == 3067 && player.getPosition().getY() == 10253)
						player.getTeleportation().attemptLeverTeleport(new Position(2271, 4680 ,0));//KBD Entrance
				break;
				case 1815:
					if(player.getPosition().getX() == 3153 && player.getPosition().getY() == 3923)
						player.getTeleportation().attemptLeverTeleport(new Position(2561, 3311 ,0));//
				break;					
				case 2452:
					player.getTeleportation().attemptLeverTeleport(new Position(2841, 4829)); 
						player.getActionSender().sendMessage("You feel a poweful force take hold of you...");
					break;
				case 2465:
					player.getTeleportation().attemptLeverTeleport(new Position(2987, 3292)); 
				break;	
				/*case 1733:
					if((player.getPosition().getX() == 3045 && player.getPosition().getY() == 3927) ||
					(player.getPosition().getX() == 3044 && player.getPosition().getY() == 3927)){
						player.teleport(new Position(3045, 10322, 0));
						
					}
					break;
				case 1734:
					if((player.getPosition().getX() == 3045 && player.getPosition().getY() == 10323) ||
							(player.getPosition().getX() == 3044 && player.getPosition().getY() == 10323)){
								player.teleport(new Position(3044, 3927,0));
							}
					break;	*/				
				case 10596: // enter icy cavern
					player.teleport(new Position(3056, 9555));
					break;
				case 10595: // exit icy cavern
					player.teleport(new Position(3056, 9562));
					break;
				case 5949: // lumby jump
					if (player.getPosition().getY() > y) {
						player.teleport(new Position(3221, 9552));
					} else {
						player.teleport(new Position(3221, 9556));
					}
					break;
				case 2623: // taverly dungeon door
					if (!player.getInventory().playerHasItem(1590) && player.getPosition().getX() > 2923) {
						player.getActionSender().sendMessage("The door is locked, you need a dusty key to open it.");
					} else {
						player.getActionSender().walkThroughDoor(id, x, y, 0);
						player.getActionSender().walkTo(player.getPosition().getX() > 2923 ? -1 : 1, 0, true);
					}
					break;
				case 2631: // jailer door
					if (!player.getInventory().playerHasItem(1591) && player.getPosition().getY() > 9689) {
						player.getActionSender().sendMessage("The door is locked, you need a jail key to open it.");
					} else {
						player.getActionSender().walkThroughDoor(id, x, y, 0);
						player.getActionSender().walkTo(0, player.getPosition().getY() > 9689 ? -1 : 1, true);
					}
					break;
				case 2320:
					if (player.getPosition().getY() >= 9969)
						player.teleport(new Position(player.getPosition().getX(), 9964));
					else
						player.teleport(new Position(player.getPosition().getX(), 9969));
					break;
				case 5083:
					if (player.isBrimhavenDungeonOpen()) {
						player.fadeTeleport(new Position(2713, 9564));
						player.setBrimhavenDungeonOpen(false);
					} else {
						Dialogues.startDialogue(player, 1595);
					}
					break;
				case 5084:
					player.teleport(new Position(2744, 3152));
					break;
				case 4499:
					player.fadeTeleport(new Position(2808, 10002));
					player.getActionSender().sendMessage("You enter the cave.");
					break;
				case 4500:
					player.teleport(new Position(2796, 3615));
					break;
				case 5100:
					if (player.getPosition().getY() < y)
						player.teleport(new Position(2655, 9573));
					// player.getActionSender().walkTo(0, 7, true);
					else
						player.teleport(new Position(2655, 9566));
					// player.getActionSender().walkTo(0, -7, true);
					break;
				case 5111:
					player.teleport(new Position(2649, 9562));
					break;
				case 5110:
					player.teleport(new Position(2647, 9557));
					break;
				case 5088:
					System.out.println("Here 5088");
					if (x > player.getPosition().getX() && y == player.getPosition().getY())
						player.getActionSender().walkTo(-5, 0, true);
					else if (x < player.getPosition().getX() && y == player.getPosition().getY())
						player.getActionSender().walkTo(5, 0, true);
					break;
				case 6552:
					if(player.getQuesting().getQuestStage(25) == 6){
						player.getUpdateFlags().sendAnimation(645);
						if (player.getMagicBookType() == SpellBook.MODERN) {
							player.getActionSender().sendMessage(" You feel a strange wisdom fill your mid...");
							player.getActionSender().sendSidebarInterface(6, 12855);
							player.setMagicBookType(SpellBook.ANCIENT);
							player.getSkill().getLevel()[Skill.PRAYER] = 0;
							player.getSkill().refresh(Skill.PRAYER);
							player.getActionSender().sendMessage("You have ran out of prayer points;" + " you must recharge at an altar.");
	
						} else {
							player.getActionSender().sendMessage("You feel a strange drain upon your memory...");
							player.getActionSender().sendSidebarInterface(6, 1151);
							player.setMagicBookType(SpellBook.MODERN);
							player.getSkill().getLevel()[Skill.PRAYER] = 0;
							player.getSkill().refresh(Skill.PRAYER);
							player.getActionSender().sendMessage("You have ran out of prayer points;" + " you must recharge at an altar.");
	
						}
					}
					break;
				case 6481:
					player.getActionSender().sendMessage("You sneak into the back of the pyramid...");
					player.fadeTeleport(new Position(3229, 9310));
					break;
				case 6515:
					player.getActionSender().sendMessage("You search the sarcophagus, and sneak into it...");
					player.getActionSender().sendInterface(8677);

					CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
						@Override
						public void execute(CycleEventContainer container) {
							player.teleport(new Position(3233, 2887));
							container.stop();
						}

						@Override
						public void stop() {

						}
					}, 4);

					break;
				case 1742: // gnome tree stairs
					if (x == 2445 && y == 3434) {
						player.teleport(new Position(2445, 3433, 1));
					}
					if (x == 2444 && y == 3414) {
						player.teleport(new Position(2445, 3416, 1));
					}
					break;
				case 1744: // gnome tree stairs
					if (x == 2445 && y == 3434) {
						player.teleport(new Position(2445, 3436, 0));
					}
					if (x == 2445 && y == 3415) {
						player.teleport(new Position(2445, 3413, 0));
					}
					break;
				case 2406: // zanaris shed door
					if(player.getQuesting().getQuestStage(57) >= 1){
						if (player.getEquipment().getId(Constants.WEAPON) == 772) {
							if(player.getQuesting().getQuestStage(57) == 1){
								player.getQuesting().completeQuest(57, "3 Quest Points", "Access to Zarnaris", "The ability to wield Dragon Longsword & Dragon Daggers", "", "", 1215);
								player.getQuesting().setQuestStage(57, 6);
							} else if(player.getQuesting().getQuestStage(57) == 6){
								if (player.getTeleportation().attemptTeleportJewellery(new Position(2452, 4473, 0))) {
									player.getActionSender().sendMessage("You are suddenly teleported away.");
									
								}
							}
						} else {
							player.getActionSender().sendMessage("The door seems to be locked.");
						}
						
					} else {
						player.getActionSender().sendMessage("The door seems to be locked.");
					}
					break;
				case 2407: // entrana dungeon door
					if (x == 2874 && y == 9750) {
						player.setStopPacket(true);
						player.getActionSender().sendMessage("You feel the world around you dissolve...");
						player.getActionSender().walkThroughDoor(id, x, y, 0);
						CycleEventHandler.getInstance().addEvent(player, new CycleEvent() {
							@Override
							public void execute(CycleEventContainer container) {
								player.teleport(new Position(3071, 3609));
								player.setStopPacket(false);
								container.stop();
							}

							@Override
							public void stop() {
							}
						}, 3);
					}
					break;
				case 2408: // entrana dungeon
					Dialogues.startDialogue(player, 656);
					break;
				case 2640: // prayer guild altar
					Prayer.rechargePrayerGuild(player);
					break;
				case 2641: // prayer guild ladder
					if (player.getSkill().getPlayerLevel(Skill.PRAYER) < 31) {
						player.getDialogue().sendStatement("You need a Prayer level of 31 to enter the Prayer guild.");
					} else {
						Ladders.checkClimbLadder(player, "up");
					}
					break;
				case 1804: // brass key door
					if (player.getPosition().getY() < 3450 && !player.getInventory().playerHasItem(983)) {
						player.getActionSender().sendMessage("You need a brass key to enter here.");
					} else {
						player.getActionSender().walkThroughDoor(id, x, y, 0);
						player.getActionSender().walkTo(0, player.getPosition().getY() < 3450 ? 1 : -1, true);
					}
					break;
				case 733: // slash web
					Webs.slashWeb(player, x, y, player.getEquipment().getId(Constants.WEAPON));
					break;
				case 2606: // wall in karamja dungeon
					player.getActionSender().walkThroughDoor(id, x, y, 0);
					if (player.getPosition().getY() < 9600) {
						player.getActionSender().walkTo(0, 1, true);
					} else {
						player.getActionSender().walkTo(0, -1, true);
					}
					break;
				case 9334: // canifis gate
					player.getActionSender().sendMessage("Please use the trapdoor located a bit north of here.");
					break;
				case 3443: // holy barrier to canifis
					player.teleport(new Position(3423, 3485, 0));
					player.getActionSender().sendMessage("You step through the holy barrier and appear in Canifis.");
					break;
				case 5055:
					TrapDoor.handleTrapdoor(player, id, 6435, def);
					break;
				case 5054:
					Ladders.climbLadder(player, new Position(3494, 3465));
				break;
				case 5052:
					
				break;
				case 6434: // open trapdoor
					TrapDoor.handleTrapdoor(player, id, 6435, def);
					break;
				case 1568: // open trapdoor
					TrapDoor.handleTrapdoor(player, id, 1570, def);
					break;
				case 14879:
					TrapDoor.handleTrapdoor(player, id, 14880, def);
					break;
				case 1570: // climb down trapdoor
				case 14880:
				case 5947: // climb into lumby swamp
				case 6435: // climb down trapdoor
				case 882: // climb down manhole
				case 1754: // climb down ladder
				case 9472: // climb down port sarim dungeon
				case 1759: // taverly dungeon entrance
				case 11867: // dwarven mine entrance
					if(y == 3464){
						Ladders.climbLadder(player, new Position(3477, 9845, 0));
					} else {
						Ladders.climbLadder(player, new Position(player.getPosition().getX(), player.getPosition().getY() + 6400));
					}
					break;
				case 1755: // exit stairs
				case 2405:
				case 6436: // climb up ladder
				case 5946: // climb out of lumby swamp
				case 1757: // climb up ladder
				
					if (x == 3097 && y == 9867) { // up to edgeville
						Ladders.climbLadder(player, new Position(3096, 3468, 0));
					} else {
						Ladders.climbLadder(player, new Position(player.getPosition().getX(), player.getPosition().getY() - 6400));
					}
					break;
				case 3432: // open canifis trapdoor
					TrapDoor.handleTrapdoor(player, id, 3433, def);
					break;
				case 3433: // climb down canifis trapdoor
					Ladders.climbLadder(player, new Position(3440, 9887, 0));
					break;
				case 2492: // portal out of rune mines
					switch (player.getRunecraftNpc()) {
					case 462:
						player.teleport(new Position(2591, 3086));
						break;
					case 553:
						player.teleport(new Position(3253, 3401));
						break;
					case 300:
						player.teleport(new Position(3101, 9571));
						break;
					case 844:
						player.teleport(new Position(2684, 3322));
						break;
					}
					break;
				case 3044: // tutorial furnace
					if (player.getNewComersSide().isInTutorialIslandStage()) {
						player.getDialogue().sendStatement("This is a furnace for smelting metal. To use it simply click on the", "ore you wish to smelt then click on the furnace you would like to", "use.");
						player.setClickId(0);
					}
					break;
				case 3039: // range
					player.getDialogue().sendStatement("To cook something you need to use the item you would like to cook", "on the cooking range. Do this by clicking the item in your inventory", "and then clicking the range.");
					player.setClickId(0);
					break;
				case 3566: // brimhaven swing
					int walkX = player.getPosition().getX() > 2768 ? -5 : 5;
					// player.teleport(new Position(player.objectWalkX,
					// player.objectWalkY, 3));
					CrossObstacle.walkAcross(player, 50, walkX, 0, 2, 30, 751);
					break;
				case 5492: // H.A.M. trapdoor
					/*
					 * if (player.isHamTrapDoor()) { new GameObject(5491, x, y,
					 * z, 0, 10, 5492, 999999); }
					 */
					break;
				case 5581: // take axe from log
					AxeInLog.pullAxeFromLog(player, x, y);
					break;
				case 8689: // Milk cow
					MilkCow.milkCow(player);
					break;
				case 2718: // operate hopper
					FlourMill.operateHopper(player);
					break;
				case 1782: // grain bin
					FlourMill.takeFromBin(player);
					break;
				
				case 2610: // karamja rope
					Ladders.climbLadder(player, new Position(2833, 3257, 0));
					break;
				case 2147:// wizard tower ladder to sedridor
					Ladders.climbLadder(player, new Position(3104, 9576, 0));
					break;
				case 11511 : // draynor manor middle to top
				case 12536:// wizard tower ladder to upstairs up to height 1
				case 11732:// fally staircase
				case 11729:// fally staircase
					Ladders.checkClimbTallStaircase(player, "up");
					break;
				case 11733:// fally staircase to height 0
				case 11731:// fally staircase
				case 9584 : // draynor manor top floor
				case 12538:// wizard tower staircase down to height 1
				case 14737:	
					Ladders.checkClimbTallStaircase(player, "down");
					break;
				case 11736:// fally staircase
				case 11734:// fally staircase
				case 1722: // staircase up
				case 1725 : // varrock in bottom to middle
				case 9470: // rimmington staircase
				
					Ladders.checkClimbStaircase(player, 4, 4, "up");
					break;
				case 4493: // slayer tower
				case 4495: // slayer tower
				case 11498 : // draynor manor bottom to middle
					Ladders.checkClimbStaircase(player, 5, 5, "up");
					break;
				case 1723: // staircase down
				case 1726 : // varrock inn middle to bottom
				case 11737:// fally staircase
				case 11735:// fally staircase
				case 9471: // rimmington staircase
					Ladders.checkClimbStaircase(player, 4, 4, "down");
					break;
				case 4494: // slayer tower
				case 4496: // slayer tower
				case 11499 : // draynor manor middle to bottom
					Ladders.checkClimbStaircase(player, 5, 5, "down");
					break;
				case 7057 : // single staircase
					Ladders.checkClimbStaircaseBackwards(player, 4, 4, "up");
					break;
				case 7056 : // single staircase
					Ladders.checkClimbStaircaseBackwards(player, 4, 4, "down");
					break;
				case 9319: // climb up ladder
				case 8744:
				case 1747:
				case 1750:
				case 9558:
				case 11739:
				case 12964:// flour mill ladder to upstairs
					Ladders.checkClimbLadder(player, "up");
					break;
				case 9320: // climb down ladder
				case 8746:
				case 9559:
				case 11741:
				case 9560:
				case 12966:// flour mill staircase down
				case 1746: // climb down a height ladder
				case 1749:
				case 11742:
					Ladders.checkClimbLadder(player, "down");
					break;
				case 2148:// wizard tower ladder to sedridor
					Ladders.climbLadder(player, new Position(3105, 3162, 0));
					break;
				case 881: // open manhold
					TrapDoor.handleTrapdoor(player, id, 882, def);
					break;
				case 883: // close manhold
					TrapDoor.handleTrapdoor(player, id, 881, def);
					break;
				case 2112: // Mining guild door entrance
					if (player.getPosition().getY() > 9756) {
						if (SkillHandler.hasRequiredLevel(player, Skill.MINING, 60, "enter the Mining Guild")) {
							player.getActionSender().walkThroughDoor(id, x, y, z);
							player.getActionSender().walkTo(0, -1, true);
						} else {
							player.getDialogue().sendStatement("You need 60 mining to enter!");
						}
					} else {
						if (SkillHandler.hasRequiredLevel(player, Skill.MINING, 60, "enter the Mining Guild")) {
							player.getActionSender().walkThroughDoor(id, x, y, z);
							player.getActionSender().walkTo(0, 1, true);
						}
					}
					break;
				case 2618:
					
					break;
				case 11707: // Mining guild door entrance
					if (player.getPosition().getY() == 3374) {
							player.getActionSender().walkThroughDoor(id, x, y, z);
							player.getActionSender().walkTo(0, 1, true);
					} else if (player.getPosition().getY() == 3375) {
						player.getActionSender().walkThroughDoor(id, x, y, z);
						player.getActionSender().walkTo(0, -1, true);
					}
					break;					
				case 2113: // Mining guild ladder entrance
					if (SkillHandler.hasRequiredLevel(player, Skill.MINING, 60, "enter the Mining Guild")) {
						Ladders.climbLadder(player, new Position(player.getPosition().getX(), player.getPosition().getY() + 6400, 0));
					}
					break;
				case 5097:
					player.teleport(new Position(2636, 9510, 2));
					break;
				case 5096:
					player.teleport(new Position(2649, 9591, 0));
					break;
				case 5098:
					player.teleport(new Position(2636, 9517, 0));
					break;
				case 5094:
					player.teleport(new Position(2643, 9594, 2));
					break;	
				case 1733:
					Ladders.checkClimbStaircaseDungeon(player, 4, 4, "down");
					break;
				case 1734:
					Ladders.checkClimbStaircaseDungeon(player, 4, 4, "up");
					break;
				case 245:
					if(player.getPosition().getX() == 3019 && player.getPosition().getY() == 3958)
						Ladders.climbLadder(player, new Position(3019, 3960, 2));
					if(player.getPosition().getX() == 3017 && player.getPosition().getY() == 3958)
						Ladders.climbLadder(player, new Position(3017, 3960, 2));
					break;
				case 246:
					if(player.getPosition().getX() == 3019 && player.getPosition().getY() == 3960)
						Ladders.climbLadder(player, new Position(3019, 3958, 1));
					if(player.getPosition().getX() == 3017 && player.getPosition().getY() == 3960)
						Ladders.climbLadder(player, new Position(3017, 3958, 1));
					break;	
				case 272:
					if(player.getPosition().getX() == 3018)
						Ladders.climbLadder(player, new Position(3018, 3956, 1));
				break;
				case 273:
					if(player.getPosition().getX() == 3018)
						Ladders.climbLadder(player, new Position(3018, 3958, 0));
				break;
				case 492: // climb down karamja volcano
					Ladders.climbLadder(player, new Position(2857, 9569, 0));
					break;
				case 1764: // climb up karamja volcano
					Ladders.climbLadder(player, new Position(2856, 3167, 0));
					break;
				case 9358: // enter tzhaar cave
					player.sendTeleport(2480, 5175, 0);
					break;
				case 9359: // exit tzhaar cave
					if(player.getPosition().getY() == 5175){
						player.sendTeleport(2862, 9571, 0);
						break;
					}
					player.sendTeleport(2480, 5175, 0);
					break;
				case 3828: // climb into kalphite tunnel
					Ladders.climbLadder(player, new Position(3484, 9509, 2));
					break;
				case 3829: // climb out of kalphite tunnel
					Ladders.climbLadder(player, new Position(3229, 3108, 0));
					break;
				case 3831: // climb into kalphite boss
					Ladders.climbLadder(player, new Position(3507, 9494, 0));
					break;
				case 3832: // climb out of kalphite boss
					Ladders.climbLadder(player, new Position(3509, 9499, 2));
					break;
				case 10168:
					int height = z > 1 ? 1 : 0;
					Ladders.climbLadder(player, new Position(player.getPosition().getX(), player.getPosition().getY(), height));
					break;
				case 10167:
					int height2 = z < 1 ? 1 : 2;
					Ladders.climbLadder(player, new Position(player.getPosition().getX(), player.getPosition().getY(), height2));
					break;
				case 1765: // climb into KBD cave
					Ladders.climbLadder(player, new Position(3069, 10257, 0));
					break;
				case 1766: // climb out KBD cave
					Ladders.climbLadder(player, new Position(3017, 3850, 0));
					break;	
				case 1738: // staircase up
					Ladders.checkClimbTallStaircase(player, "up");
					break;
				case 14735:
					Ladders.checkClimbTallStaircase(player, "up");
					break;
				case 1740: // staircase down
					Ladders.checkClimbTallStaircase(player, "down");
					break;
				case 1739: // staircase mid
				case 12537:
				case 14736:
					Dialogues.startDialogue(player, 10007);
					break;
				case 1748: // stairs mid
				case 8745:
				case 12965:// flour mill
				case 2884:
					Dialogues.startDialogue(player, 10006);
					break;
				case 2882: // alkharid gate
				case 2883:
					Dialogues.startDialogue(player, 9999);
					break;
				case 3203:
					Dialogues.startDialogue(player, 10010);
					break;
				case 3192:
					GlobalDuelRecorder.displayScoreBoardInterface(player);
					break;
				case 2213: //bank booth
				case 11758:
					Dialogues.startDialogue(player, 494);
					break;
				case 2491: // mine rune/pure ess
					MineEssence.startMiningEss(player);
					break;
				default:
					player.getActionSender().sendMessage("Nothing interesting happens.");
					break;
				}
				this.stop();
			}
		});
	}

	public static void doObjectSecondClick(final Player player) {
		final int id = player.getClickId();
		final int x = player.getClickX();
		final int y = player.getClickY();
		final int z = player.getClickZ();
		final int task = player.getTask();
		World.submit(new Tick(1, true) {
			@Override
			public void execute() {
				if (player == null || !player.checkTask(task)) {
					this.stop();
					return;
				}
				if (player.isMoving() || player.isStunned()) {
					return;
				}
				GameObjectDef def = SkillHandler.getObject(id, x, y, z);
				if (def == null) { // Server.npcHandler.getNpcByLoc(Location.create(x,
					if (id == 2213 && x == 3513) { //exception
						def = new GameObjectDef(id, 10, 0, new Position(x, y, z));
					} else {
						return;
					}
				}
				GameObjectData object = GameObjectData.forId(player.getClickId());
				Position objectPosition;
				objectPosition = Misc.goodDistanceObject(def.getPosition().getX(), def.getPosition().getY(), player.getPosition().getX(), player.getPosition().getY(), object.getSizeX(def.getFace()), object.getSizeY(def.getFace()), z);
				if (objectPosition == null)
					return;
				if (!canInteractWithObject(player, objectPosition, def)) {
					stop();
					return;
				}
				Position loc = new Position(player.getClickX(), player.getClickY(), z);
				if (object != null)
					player.getUpdateFlags().sendFaceToDirection(loc.getActualLocation(object.getBiggestSize()));

				if (ThieveOther.handleObjectClick2(player, id, x, y)) {
					this.stop();
					return;
				}
				/* Thieving */
				if (ThieveStalls.handleThievingStall(player, id, x, y)) {
					this.stop();
					return;
				}
				if (Farming.inspectObject(player, x, y)) {
					this.stop();
					return;
				}
				if (player.getMining().prospect(id)) {
					this.stop();
					return;
				}
				if (PickableObjects.pickObject(player, id, x, y)) {
					this.stop();
					return;
				}
				switch (player.getClickId()) {
				case 2634:
					if (Tools.getTool(player, Skill.MINING) == null) {
						player.getActionSender().sendMessage("You do not have a pickaxe that you can use.");
						if (player.getNewComersSide().isInTutorialIslandStage()) {
							player.getDialogue().sendStatement("You do not have a pickaxe that you can use.");
							player.setClickId(0);
						}
						this.stop();
						return;
					}
					if (!SkillHandler.hasRequiredLevel(player, Skill.MINING, 50, "mine here")) {
						this.stop();
						return;
					}
					if (!SkillHandler.checkObject(player.getClickId(), x, y, player.getPosition().getZ())) {
						return;
					}
					final GameObject p = ObjectHandler.getInstance().getObject(x, y, player.getPosition().getZ());
					if (p != null) {
						player.getActionSender().sendMessage("There is currently no ores remaining in this rock.");
						if (player.getNewComersSide().isInTutorialIslandStage()) {
							player.getDialogue().sendStatement("There is currently no ores remaining in this rock.");
							player.setClickId(0);
						}
						return;
					}
					player.getActionSender().sendMessage("You swing your pick at the rock.");
					if (player.getNewComersSide().isInTutorialIslandStage()) {
						player.getDialogue().sendTutorialIslandWaitingInfo("", "Your character is now attempting to mine the rock.", "This should take only a few seconds.", "", "Please wait...");
					}
					final int task = player.getTask();
					final Tool pickaxe = Tools.getTool(player, Skill.MINING);
					if (pickaxe == null) {
						player.getActionSender().sendMessage("You do not have a pickaxe that you can use.");
						return;
					}
					player.resetAnimation();
					player.getUpdateFlags().sendAnimation(pickaxe.getAnimation());
					player.setSkilling(new CycleEvent() {
						@Override
						public void execute(CycleEventContainer container) {
							if (!player.checkTask(task)) {
								container.stop();
								return;
							}
							final GameObject p = ObjectHandler.getInstance().getObject(x, y, player.getPosition().getZ());
							if (p != null) {
								if (player.getNewComersSide().isInTutorialIslandStage()) {
									player.setClickId(0);
								}
								container.stop();
								return;
							}
							container.setTick(3);
						    player.getUpdateFlags().sendAnimation(pickaxe.getAnimation());
							if (SkillHandler.skillCheck(player.getSkill().getLevel()[Skill.MINING], 50, pickaxe.getBonus())) {
								player.getActionSender().sendMessage("You manage to clear the blockage.");
								try {
									int face = SkillHandler.getFace(player.getClickId(), x, y, player.getPosition().getZ());
									int type = SkillHandler.getType(player.getClickId(), x, y, player.getPosition().getZ());
									new GameObject(Constants.EMPTY_OBJECT, x, y, player.getPosition().getZ(), type == 22 ? player.getMining().getFace(face) : face, type == 11 ? 11 : 10, player.getClickId(), 350);
									ObjectHandler.getInstance().removeClip(player.getClickId(), x, y, z, type, face);
								} catch (Exception e) {
									e.printStackTrace();
								}
								container.stop();
							}
						}
						@Override
						public void stop() {
							player.getUpdateFlags().sendAnimation(-1);
						}
					});
					CycleEventHandler.getInstance().addEvent(player, player.getSkilling(), 3);
					break;
				case 2114 : // coal truck
					CoalTruck.checkCoal(player);
					break;
				case 3194 : // opened bank chest
					BankManager.openBank(player);
					break;
				case 8930: //waterbirth snow cave to daggnoth
					Ladders.climbLadder(player, new Position(2545, 10143, 0));
					break;
				case 10177 : //daggnoth to waterbirth snow cave
					Ladders.climbLadder(player, new Position(2544, 3741, 0));
					break;
				case 3433: // close canifis trapdoor
					TrapDoor.handleTrapdoor(player, id, 3432, def);
					break;
				case 1570: // close trapdoor
					TrapDoor.handleTrapdoor(player, id, 1568, def);
					break;
				case 1739: // staircase mid
				case 12537:
				case 14736:
					Ladders.checkClimbTallStaircase(player, "up");
					break;
				case 1748: // stairs mid
				case 8745:
				case 12965:// flour mill
				case 2884: // gnome tree
					Ladders.checkClimbLadder(player, "up");
					break;
				case 14921: // furnace
				case 11666:
				case 9390:
				case 2781:
				case 3044:
					Smelting.smeltInterface(player);
					// player.getSmithing().setUpSmelting();
					break;
				case 2644:
					Menus.sendSkillMenu(player, "spinning");
					break;
				case 12121: // entrana bank
				case 2213:
				case 11758:
					BankManager.openBank(player);
					break;
				case 8717:
					Menus.sendSkillMenu(player, "weaving");
					break;
				}
				this.stop();
			}
		});
	}

	public static void doObjectThirdClick(final Player player) {
		final int id = player.getClickId();
		final int x = player.getClickX();
		final int y = player.getClickY();
		final int z = player.getClickZ();
		final int task = player.getTask();

		World.submit(new Tick(1, true) {
			@Override
			public void execute() {
				if (player == null || !player.checkTask(task)) {
					this.stop();
					return;
				}
				if (player.isMoving() || player.isStunned()) {
					return;
				}
				GameObjectDef def = SkillHandler.getObject(id, x, y, z);
				if (def == null) { // Server.npcHandler.getNpcByLoc(Location.create(x,
					return;
				}
				GameObjectData object = GameObjectData.forId(player.getClickId());
				Position objectPosition;
				objectPosition = Misc.goodDistanceObject(def.getPosition().getX(), def.getPosition().getY(), player.getPosition().getX(), player.getPosition().getY(), object.getSizeX(def.getFace()), object.getSizeY(def.getFace()), z);
				if (objectPosition == null)
					return;
				if (!canInteractWithObject(player, objectPosition, def)) {
					stop();
					return;
				}
				Position loc = new Position(player.getClickX(), player.getClickY(), z);
				if (object != null)
					player.getUpdateFlags().sendFaceToDirection(loc.getActualLocation(object.getBiggestSize()));

				switch (player.getClickId()) {
				case 3194 : // opened bank chest
					final GameObject p = ObjectHandler.getInstance().getObject(id, x, y, z);
					if (p != null) {
						player.getUpdateFlags().sendAnimation(832);
						ObjectHandler.getInstance().removeObject(x, y, z, def.getType());
					}
					break;
				case 10177: // Dagganoth ladder 1st level
					Ladders.climbLadder(player, new Position(1798, 4407, 3));
					break;
				case 1739: //staircase
				case 12537:
				case 14736:
					Ladders.checkClimbTallStaircase(player, "down");
					break;
				case 1748: // stairs mid
				case 8745:
				case 12965:// flour mill
				case 2884: // gnome tree
					Ladders.checkClimbLadder(player, "down");
					break;
				default:
					player.getActionSender().sendMessage("Nothing interesting happens.");
					break;
				}
				this.stop();
			}
		});
	}

	public static void doObjectFourthClick(final Player player) {
		final int id = player.getClickId();
		final int x = player.getClickX();
		final int y = player.getClickY();
		final int z = player.getClickZ();
		final int task = player.getTask();
		World.submit(new Tick(1, true) {
			@Override
			public void execute() {
				if (player == null || !player.checkTask(task)) {
					this.stop();
					return;
				}
				if (player.isMoving() || player.isStunned()) {
					return;
				}
				GameObjectDef def = SkillHandler.getObject(id, x, y, z);
				if (def == null) { // Server.npcHandler.getNpcByLoc(Location.create(x,
					return;
				}
				GameObjectData object = GameObjectData.forId(player.getClickId());
				Position objectPosition;
				objectPosition = Misc.goodDistanceObject(def.getPosition().getX(), def.getPosition().getY(), player.getPosition().getX(), player.getPosition().getY(), object.getSizeX(def.getFace()), object.getSizeY(def.getFace()), z);
				if (objectPosition == null)
					return;
				if (!canInteractWithObject(player, objectPosition, def)) {
					stop();
					return;
				}
				Position loc = new Position(player.getClickX(), player.getClickY(), z);
				if (object != null)
					player.getUpdateFlags().sendFaceToDirection(loc.getActualLocation(object.getBiggestSize()));

				if (Farming.guide(player, x, y)) {
					this.stop();
					return;
				}
				switch (player.getClickId()) {

				}
				player.getActionSender().sendMessage("Nothing interesting happens.");
				this.stop();
			}
		});
	}

	private static void doNpcFirstClick(final Player player) {
		final Npc npc = World.getNpcs()[player.getNpcClickIndex()];
		final int task = player.getTask();
		if (npc == null || !npc.isRealNpc()) {
			return;
		}
		for (int[] element : Pets.PET_IDS) {
			if (player.getClickId() == element[1]) {
				player.getPets().unregisterPet();
				return;
			}
		}
		if (npc.getPlayerOwner() != null && (npc.getPlayerOwner() != player || npc.getCombatingEntity() != null)) {
			player.getActionSender().sendMessage("This npc is not interested in talking with you right now.");
			return;
		}
		npc.setInteractingEntity(player);
		World.submit(new Tick(1, true) {
			@Override
			public void execute() {
				if (player == null || !player.checkTask(task) || npc.isDead()) {
					this.stop();
					return;
				}
				if (npc.isBoothBanker()) {
					if (npc.getCorrectStandPosition(player.getPosition(), 2)) {
						npc.getUpdateFlags().faceEntity(player.getFaceIndex());
						player.setInteractingEntity(npc);
						player.getUpdateFlags().faceEntity(npc.getFaceIndex());
						Dialogues.startDialogue(player, player.getClickId());
						Following.resetFollow(player);
						this.stop();
					}
					return;
				}
				if (!player.goodDistanceEntity(npc, 1) || player.inEntity(npc)) {
					return;
				}
				final Fishing.FishingSpot spot = Fishing.FishingSpot.getSpot(npc.getDefinition().getId(), 1);
				if (player.getFishing().fish(spot, npc.getPosition())) {
					Following.resetFollow(player);
					player.setInteractingEntity(npc);
					player.getUpdateFlags().faceEntity(npc.getFaceIndex());
					this.stop();
					return;
				}
				if (!Misc.checkClip(player.getPosition(), npc.getPosition(), true)) {
					return;
				}
				Following.resetFollow(player);
				npc.getUpdateFlags().faceEntity(player.getFaceIndex());
				player.setInteractingEntity(npc);
				player.getUpdateFlags().faceEntity(npc.getFaceIndex());
				if (npc.getPlayerOwner() != null && npc.getPlayerOwner() != player) {
					player.getActionSender().sendMessage(npc.getDefinition().getName() + " is not interested in interacting with you right now.");
					this.stop();
					return;
				}
				if (player.getSlayer().doNpcSpecialEffect(npc)) {
					this.stop();
					return;
				}
				if (AnagramsScrolls.handleNpc(player, player.getClickId())) {
					this.stop();
					return;
				}
				if (SpeakToScrolls.handleNpc(player, player.getClickId())) {
					this.stop();
					return;
				}

				if (player.getNewComersSide().isInTutorialIslandStage() && player.getNewComersSide().sendGiveItemsInstructor()) {
					this.stop();
					return;
				}
				if (player.getNewComersSide().isInTutorialIslandStage() && player.getNewComersSide().sendDialogue()) {
					this.stop();
					return;
				}
				if (Dialogues.startDialogue(player, player.getClickId())) {
					this.stop();
					return;
				}
				if (Shops.findShop(player, player.getClickId()) > 0) {
					Dialogues.sendDialogue(player, 10008, 1, 0, player.getClickId());
					this.stop();
					return;
				}
				switch (player.getClickId()) {
					case 166 :
					case 494 :
					case 495 :
					case 496 :
					case 498 :
					case 499 :
					case 2619:
						npc.getUpdateFlags().faceEntity(player.getFaceIndex());
						player.setInteractingEntity(npc);
						player.getUpdateFlags().faceEntity(npc.getFaceIndex());
						Dialogues.startDialogue(player, player.getClickId());
						Following.resetFollow(player);
						break;
				}
				player.getActionSender().sendMessage("This npc is not interested in talking with you right now.");
				this.stop();
			}
		});
	}

	private static void doNpcSecondClick(final Player player) {
		final Npc npc = World.getNpcs()[player.getNpcClickIndex()];
		final int task = player.getTask();
		if (npc == null || !npc.isRealNpc()) {
			return;
		}
		World.submit(new Tick(1, true) {
			@Override
			public void execute() {
				if (player == null || !player.checkTask(task) || npc.isDead()) {
					this.stop();
					return;
				}
				if (npc.isBoothBanker()) {
					if (npc.getCorrectStandPosition(player.getPosition(), 2)) {
						npc.getUpdateFlags().faceEntity(player.getFaceIndex());
						player.setInteractingEntity(npc);
						player.getUpdateFlags().faceEntity(npc.getFaceIndex());
						BankManager.openBank(player);
						Following.resetFollow(player);
						this.stop();
					}
					return;
				}
				if (!player.goodDistanceEntity(npc, 1) || player.inEntity(npc)) {
					return;
				}
				final Fishing.FishingSpot spot = Fishing.FishingSpot.getSpot(npc.getDefinition().getId(), 2);
				if (player.getFishing().fish(spot, npc.getPosition())) {
					Following.resetFollow(player);
					player.getUpdateFlags().faceEntity(npc.getFaceIndex());
					this.stop();
					return;
				}
				if (!Misc.checkClip(player.getPosition(), npc.getPosition(), true)) {
					return;
				}
				Following.resetFollow(player);
				player.getUpdateFlags().faceEntity(npc.getFaceIndex());
				if (ThieveNpcs.handleThieveNpc(player, npc)) {
					this.stop();
					return;
				}
				npc.getUpdateFlags().faceEntity(player.getFaceIndex());
				player.setInteractingEntity(npc);
				if (Shops.openShop(player, npc.getNpcId())) {
					this.stop();
					return;
				}
				switch (player.getClickId()) {
				case 166 :
				case 494 :
				case 495 :
				case 496 :
				case 499 :
				case 2619:
					npc.getUpdateFlags().faceEntity(player.getFaceIndex());
					player.setInteractingEntity(npc);
					player.getUpdateFlags().faceEntity(npc.getFaceIndex());
					BankManager.openBank(player);
					Following.resetFollow(player);
					break;
				case 2437:
					Dialogues.sendDialogue(player, 2437, 4, 0);
					break;
				case 1595:
					Dialogues.sendDialogue(player, 1595, 3, 1);
					break;
				case 2824:
				case 804:
				case 1041:
					Tanning.tanningInterface(player);
					break;
				case 300:
				case 844:
				case 462:
				case 171:
					Runecrafting.teleportRunecraft(player, npc);
					break;
				case 960:
				case 961:
				case 962:
					player.getDuelMainData().healPlayer();
					break;
				case 3021:
					player.getFarmingTools().loadInterfaces();
					break;
				case 958:
					BankManager.openBank(player);
					break;
				case 569:
					ShopManager.openShop(player, 14);
					break;
				 case 572 :
					 ShopManager.openShop(player, 15);
				 break;
				}
				this.stop();
			}
		});
	}

	private static void doNpcThirdClick(final Player player) {
		final Npc npc = World.getNpcs()[player.getNpcClickIndex()];
		final int task = player.getTask();
		if (npc == null || !npc.isRealNpc()) {
			return;
		}
		World.submit(new Tick(1, true) {
			@Override
			public void execute() {
				if (player == null || !player.checkTask(task) || npc.isDead()) {
					this.stop();
					return;
				}
				if (!player.goodDistanceEntity(npc, 1) || player.inEntity(npc)) {
					return;
				}
				if (!Misc.checkClip(player.getPosition(), npc.getPosition(), true)) {
					return;
				}

				Following.resetFollow(player);
				Npc npc = World.getNpcs()[player.getNpcClickIndex()];
				player.getUpdateFlags().faceEntity(npc.getFaceIndex());
				npc.getUpdateFlags().faceEntity(player.getFaceIndex());
				switch (player.getClickId()) {
				case 553:
					Runecrafting.teleportRunecraft(player, npc);
					break;
				case 70:
				case 1596:
				case 1597:
				case 1598:
				case 1599:
					
					ShopManager.openShop(player, 162);
					break;
				case 569:
					ShopManager.openShop(player, 14);
					break;
				 case 572 :
					 ShopManager.openShop(player, 15);
				 break;
				case 2258:
				case 2259:
					Abyss.teleportToAbyss(player, npc);
					break;
				}
				this.stop();
			}
		});
	}

	private static void doNpcFourthClick(final Player player) {
		final Npc npc = World.getNpcs()[player.getNpcClickIndex()];
		final int task = player.getTask();
		if (npc == null || !npc.isRealNpc()) {
			return;
		}
		World.submit(new Tick(1, true) {
			@Override
			public void execute() {
				if (player == null || !player.checkTask(task) || npc.isDead()) {
					this.stop();
					return;
				}
				if (!player.goodDistanceEntity(npc, 1) || player.inEntity(npc)) {
					return;
				}
				if (!Misc.checkClip(player.getPosition(), npc.getPosition(), true)) {
					return;
				}
				Following.resetFollow(player);
				player.getUpdateFlags().faceEntity(npc.getFaceIndex());
				npc.getUpdateFlags().faceEntity(player.getFaceIndex());
				switch (player.getClickId()) {
				case 494:
					BankManager.openBank(player);
					break;
				}
				this.stop();
			}
		});
		/*
		 * player.setWalkAction(new WalkActions(player, x - 5, y - 5, sizeX + 5,
		 * sizeY + 5, player.getClickId()) {
		 * 
		 * @Override public void execute() { if
		 * (!player.getMovementHandler().walkToAction(new Position(x, y), 1)) {
		 * return; } Npc npc = World.getNpcs()[player.getNpcClickIndex()];
		 * player.getUpdateFlags().faceEntity(npc.getFaceIndex());
		 * npc.getUpdateFlags().faceEntity(player.getFaceIndex()); switch
		 * (player.getClickId()) { case 494 : BankManager.openBank(player);
		 * break; } actions = null; } });
		 */
	}

	private static void doItemOnObject(final Player player) {
		final int x = player.getClickX();
		final int y = player.getClickY();
		final int z = player.getClickZ();
		final int id = player.getClickId();
		final int item = player.getClickItem();
		final int task = player.getTask();
		final CacheObject obj = ObjectLoader.object(id, x, y, z);
		if (obj == null && ObjectHandler.getInstance().getObject(x, y, z) == null)
			return;
		World.submit(new Tick(1, true) {
			@Override
			public void execute() {
				if (player == null || !player.checkTask(task)) {
					this.stop();
					return;
				}
				if (player.isMoving() || player.isStunned()) {
					return;
				}
				GameObjectDef def = SkillHandler.getObject(id, x, y, z);
				if (def == null) { // Server.npcHandler.getNpcByLoc(Location.create(x,
					return;
				}
				GameObjectData object = GameObjectData.forId(player.getClickId());
				Position objectPosition;
				objectPosition = Misc.goodDistanceObject(def.getPosition().getX(), def.getPosition().getY(), player.getPosition().getX(), player.getPosition().getY(), object.getSizeX(def.getFace()), object.getSizeY(def.getFace()), z);
				if(id == 1996 && (player.getPosition().getY() == 3476 || player.getPosition().getY() == 3477)){
					if(item == 954){
						RopeToRock.attachRope(player, x, y, item);
						return;
					}
				}
				
				if (id != 2638) {
					if (objectPosition == null)
						return;
					if (!canInteractWithObject(player, objectPosition, def)) {
						stop();
						return;
					}
				} 

				Position loc = new Position(player.getClickX(), player.getClickY(), z);
				if (object != null)
					player.getUpdateFlags().sendFaceToDirection(loc.getActualLocation(object.getBiggestSize()));

				/*
				 * if(obj != null){
				 * if(obj.getDefinition().getName().toLowerCase(
				 * ).contains("table")){
				 * if(player.getInventory().getItemContainer().contains(item)){
				 * player.getUpdateFlags().sendAnimation(832);
				 * player.getInventory().removeItem(new Item(item)); Item
				 * itemDropped = new Item(item); if
				 * (itemDropped.getDefinition().isStackable()) {
				 * itemDropped.setCount
				 * (player.getInventory().getItemContainer().
				 * getCount(itemDropped.getId())); } else {
				 * itemDropped.setCount(1); } if
				 * (!player.getInventory().getItemContainer
				 * ().contains(itemDropped.getId())) { this.stop(); return; } if
				 * (
				 * player.getInventory().getItemContainer().contains(itemDropped
				 * .getId())) {
				 * ItemManager.getInstance().createGroundItem(player, new
				 * Item(item, itemDropped.getCount()), new Position(x, y, z)); }
				 * } } }
				 */
				/* cooking */
				if (player.getCooking().handleInterface(item, id, x, y)) {
					this.stop();
					return;
				}
				if (player.getCooking().handleFillingObjectWater(item, id)) {
					player.getInventory().refresh();
					this.stop();
					return;
				}
				// smithing

				if (id == 3044 && player.getNewComersSide().isInTutorialIslandStage() && (item == 438 || item == 436)) {
					Smelting.oreOnFurnace(player, item);
					// player.getSmithing().startSmelting(1, 0);
					this.stop();
					return;
				}

				// farming
				if (Farming.prepareCrop(player, item, id, x, y)) {
					this.stop();
					return;
				}

				if (RunecraftAltars.useTaliOnRuin(player, item, id)) {
					this.stop();
					return;
				}
				if (Tiaras.bindTiara(player, item, id)) {
					this.stop();
					return;
				}
				if (MixingRunes.combineRunes(player, item, id)) {
					this.stop();
					return;
				}
				if (item >= 3422 && item <= 3428 && id == 4090) {
					player.getInventory().removeItem(new Item(item));
					player.getInventory().addItem(new Item(item + 8));
					player.getUpdateFlags().sendAnimation(832);
					player.getActionSender().sendMessage("You put the olive oil on the fire, and turn it into sacred oil.");
					this.stop();
					return;
				}

				switch (id) {
				
				case 2114 : // coal truck
					if (item == 453) {
						CoalTruck.depositCoal(player);
					}
					break;
				case 172: // crystal chest
					if (item == 989) {
						CrystalChest.openCrystalChest(player);
					}
					break;
				case 733: // slash web
					Webs.slashWeb(player, x, y, item);
					break;
				case 2782: // anvil
				case 2783:
					SmithBars.smithInterface(player, item);
					// player.getSmithing().setUpSmithing(item);
					break;
				case 2714: // grain hopper
					FlourMill.putFlourInHopper(player);
					break;
				case 2638: // glory fountain
					if (item == 1704 || item == 1706 || item == 1708 || item == 1710) {
						player.getActionSender().sendMessage("You dip your amulet into the fountain...");
						player.getUpdateFlags().sendAnimation(827, 0);
						for (int i = 0; i < Inventory.SIZE; i++) {
							int[] glorys = { 1704, 1706, 1708, 1710 };
							for (int glory : glorys) {
								if (player.getInventory().getItemContainer().contains(glory)) {
									player.getInventory().addItemToSlot(new Item(1712, 1), player.getInventory().getItemContainer().getSlotById(glory));
								}
							}
						}
					}
					break;
				case 2645:// pile of sand
					if (item == GlassMaking.BUCKET) {
						GlassMaking.fillWithSand(player);
					}
					break;
				/*
				 * Furnaces.
				 */
				case 14921:
				case 9390:
				case 2781:
				case 2785:
				case 2966:
				case 3044:
				case 3294:
				case 3413:
				case 4304:
				case 4305:
				case 6189:
				case 6190:
				case 11009:
				case 11010:
				case 11666:
				case 12100:
				case 12809:
					if (item == GlassMaking.BUCKET_OF_SAND) {
						GlassMaking.makeMoltenGlass(player);
					} else if (item == GemCrafting.GOLD_BAR) {
						GemCrafting.openInterface(player);
					} else if (item == SilverCrafting.SILVER_BAR) {
						Menus.sendSkillMenu(player, "silverCrafting");
					} else if (ItemManager.getInstance().getItemName(item).toLowerCase().endsWith("ore")) {
						Smelting.smeltInterface(player);
						// player.getSmithing().setUpSmelting();
					}
					break;
				case 2642:// pottery unfire
					if (item == PotteryMaking.SOFT_CLAY) {
						Menus.sendSkillMenu(player, "potteryUnfired");
					}
					break;
				case 11601: // fire pottery
					Menus.sendSkillMenu(player, "potteryFired");
					break;
				default:
					player.getActionSender().sendMessage("Nothing interesting happens.");
					break;
				}
				this.stop();
			}
		});
	}

	private static void doItemOnNpc(final Player player) {
		// final int x = player.getClickX();
		// final int y = player.getClickY();
		final int item = player.getClickItem();
		final Npc npc = World.getNpcs()[player.getNpcClickIndex()];
		final int task = player.getTask();
		if (npc == null || !npc.isRealNpc()) {
			return;
		}
		World.submit(new Tick(1, true) {
			@Override
			public void execute() {
				if (player == null || !player.checkTask(task) || npc.isDead()) {
					this.stop();
					return;
				}
				if (!player.goodDistanceEntity(npc, 1) || player.inEntity(npc)) {
					return;
				}
				if (!Misc.checkClip(player.getPosition(), npc.getPosition(), true)) {
					return;
				}
				player.getSlayer().finishOffMonster(npc, item);
				switch (player.getClickId()) { // npc ids
					case 519:
						player.usedItemHolder = item;
						Dialogues.startDialogue(player, 519);
					break;
					case 3021:
						if (player.getFarmingTools().noteItem(item)) {
							this.stop();
							return;
						}
					break;
				}
				switch (item) { // item ids
				case 1735:
					if (player.getClickId() == 43 || player.getClickId() == 1765) {
						NpcActions.shearSheep(player);
						this.stop();
						return;
					}
					break;
				}
				this.stop();
			}
		});
	}

	private static boolean canInteractWithObject(Player player, Position objectPos, GameObjectDef def) {
		if (def.getId() == 2638) {
			return true;
		}
		Rangable.removeObjectAndClip(def.getId(), def.getPosition().getX(), def.getPosition().getY(), def.getPosition().getZ(), def.getFace(), def.getType());
		boolean canInteract = Misc.checkClip(player.getPosition(), objectPos, false);
		Rangable.addObject(def.getId(), def.getPosition().getX(), def.getPosition().getY(), def.getPosition().getZ(), def.getFace(), def.getType(), true);
		return canInteract;
	}

	public static void setActions(Actions actions) {
		WalkToActionHandler.actions = actions;
	}

	public static Actions getActions() {
		return actions;
	}

	public static enum Actions {

		OBJECT_FIRST_CLICK, OBJECT_SECOND_CLICK, OBJECT_THIRD_CLICK, OBJECT_FOURTH_CLICK, ITEM_ON_OBJECT,

		NPC_FIRST_CLICK, ITEM_ON_NPC, NPC_SECOND_CLICK, NPC_THIRD_CLICK, NPC_FOURTH_CLICK
	}

}
