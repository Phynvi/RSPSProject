package com.rs2.model.content.minigames.barrows;

import com.rs2.model.Position;
import com.rs2.model.content.dialogue.Dialogues;
import com.rs2.model.npcs.Npc;
import com.rs2.model.npcs.NpcLoader;
import com.rs2.model.players.Player;
import com.rs2.model.players.item.Item;
import com.rs2.model.players.item.ItemManager;
import com.rs2.util.Misc;

public class Barrows {

	public static final int []degradedBarrows = {4856,4857,4858,4859,4860,4862,4863,4864,4865,4866,4868,4869,4870,
		4871,4872,4874,4876,4877,4878,4880,4881,4882,4883,4884,4886,4887,4888,4889,4890,4892,4893,4894,4895,4896,
		4898,4899,4900,4901,4902,4904,4905,4906,4907,4908,4910,4911,4912,4913,4914,4916,4917,4918,4919,4920,4922,
		4923,4924,4925,4926,4928,4929,4930,4931,4932,4934,4935,4936,4937,4938,4940,4941,4942,4943,4944,4946,4947,4948,
		4949,4950,4952,4953,4954,4955,4956,4958,4959,4960,4961,4962,4964,4965,4966,4967,4968,4970,4971,4972,4973,
		4974,4976,4977,4978,4979,4980,4982,4983,4984,4985,4986,4988,4989,4990,4991,4992,4994,4995,4996,4997,4998
	};
	private static final Object[][] DATA = {
		// Fixed id, broken id, cost, name
		{4753, 4980, 60000, "Verac helm"},
		{4755, 4986, 100000, "Verac flail"},
		{4757, 4992, 90000, "Verac brassard"},
		{4759, 4998, 80000, "Verac plateskirt"},
		{4745, 4956, 60000, "Torag helm"},
		{4747, 4962, 100000, "Torag hammers"},
		{4749, 4968, 90000, "Torag platebody"},
		{4751, 4974, 80000, "Torag platelegs"},
		{4716, 4884, 60000, "Dharok helm"},
		{4718, 4890, 100000, "Dharok greataxe"},
		{4720, 4896, 90000, "Dharok platebody"},
		{4722, 4902, 80000, "Dharok platelegs"},
		{4708, 4860, 60000, "Ahrim hood"},
		{4710, 4866, 100000, "Ahrim staff"},
		{4712, 4872, 90000, "Ahrim robetop"},
		{4714, 4878, 80000, "Ahrim robeskirt"},
		{4732, 4932, 60000, "Karil coif"},
		{4734, 4938, 100000, "Karil crossbow"},
		{4736, 4944, 90000, "Karil leathertop"},
		{4738, 4950, 80000, "Karil leatherskirt"},
		{4724, 4908, 60000, "Guthan helm"},
		{4726, 4914, 100000, "Guthan warspear"},
		{4728, 4920, 90000, "Guthan platebody"},
		{4730, 4926, 80000, "Guthan chainskirt"},
	};
	
	public static int getRepairPrice(int itemId) {
		String itemName = ItemManager.getInstance().getItemName(itemId).toLowerCase();
		if (itemName.contains("ahrim")) {
			if (itemName.contains("100")) {
				if (itemName.contains("top")) {
					return 5000;
				}
				if (itemName.contains("skirt")) {
					return 6000;
				}
				if (itemName.contains("staff")) {
					return 10000;
				}
				if (itemName.contains("hood")) {
					return 3000;
				}
			}
			if (itemName.contains("75")) {
				if (itemName.contains("top")) {
					return 23000;
				}
				if (itemName.contains("skirt")) {
					return 20000;
				}
				if (itemName.contains("staff")) {
					return 25000;
				}
				if (itemName.contains("hood")) {
					return 15000;
				}				
			}
			if (itemName.contains("50")) {
				if (itemName.contains("top")) {
					return 45000;
				}
				if (itemName.contains("skirt")) {
					return 40000;
				}
				if (itemName.contains("staff")) {
					return 50000;
				}
				if (itemName.contains("hood")) {
					return 30000;
				}				
			}
			if (itemName.contains("25")) {
				if (itemName.contains("top")) {
					return 70000;
				}
				if (itemName.contains("skirt")) {
					return 65000;
				}
				if (itemName.contains("staff")) {
					return 75000;
				}
				if (itemName.contains("hood")) {
					return 45000;
				}				
			}
			if (itemName.contains("0")) {
				if (itemName.contains("top")) {
					return 90000;
				}
				if (itemName.contains("skirt")) {
					return 80000;
				}
				if (itemName.contains("staff")) {
					return 100000;
				}
				if (itemName.contains("hood")) {
					return 60000;
				}				
			}

		}
		if (itemName.contains("torag")) {
			if (itemName.contains("100")) {
				if (itemName.contains("body")) {
					return 5000;
				}
				if (itemName.contains("legs")) {
					return 6000;
				}
				if (itemName.contains("hammer")) {
					return 10000;
				}
				if (itemName.contains("helm")) {
					return 3000;
				}
			}
			if (itemName.contains("75")) {
				if (itemName.contains("body")) {
					return 23000;
				}
				if (itemName.contains("legs")) {
					return 20000;
				}
				if (itemName.contains("hammer")) {
					return 25000;
				}
				if (itemName.contains("helm")) {
					return 15000;
				}				
			}
			if (itemName.contains("50")) {
				if (itemName.contains("body")) {
					return 45000;
				}
				if (itemName.contains("legs")) {
					return 40000;
				}
				if (itemName.contains("hammer")) {
					return 50000;
				}
				if (itemName.contains("helm")) {
					return 30000;
				}				
			}
			if (itemName.contains("25")) {
				if (itemName.contains("body")) {
					return 70000;
				}
				if (itemName.contains("legs")) {
					return 65000;
				}
				if (itemName.contains("hammer")) {
					return 75000;
				}
				if (itemName.contains("helm")) {
					return 45000;
				}				
			}
			if (itemName.contains("0")) {
				if (itemName.contains("body")) {
					return 90000;
				}
				if (itemName.contains("legs")) {
					return 80000;
				}
				if (itemName.contains("hammer")) {
					return 100000;
				}
				if (itemName.contains("helm")) {
					return 60000;
				}				
			}

		}
		if (itemName.contains("veracs")) {
			if (itemName.contains("100")) {
				if (itemName.contains("body")) {
					return 5000;
				}
				if (itemName.contains("skirt")) {
					return 6000;
				}
				if (itemName.contains("flail")) {
					return 10000;
				}
				if (itemName.contains("helm")) {
					return 3000;
				}
			}
			if (itemName.contains("75")) {
				if (itemName.contains("body")) {
					return 23000;
				}
				if (itemName.contains("skirt")) {
					return 20000;
				}
				if (itemName.contains("flail")) {
					return 25000;
				}
				if (itemName.contains("helm")) {
					return 15000;
				}				
			}
			if (itemName.contains("50")) {
				if (itemName.contains("body")) {
					return 45000;
				}
				if (itemName.contains("skirt")) {
					return 40000;
				}
				if (itemName.contains("flail")) {
					return 50000;
				}
				if (itemName.contains("helm")) {
					return 30000;
				}				
			}
			if (itemName.contains("25")) {
				if (itemName.contains("body")) {
					return 70000;
				}
				if (itemName.contains("skirt")) {
					return 65000;
				}
				if (itemName.contains("flail")) {
					return 75000;
				}
				if (itemName.contains("helm")) {
					return 45000;
				}				
			}
			if (itemName.contains("0")) {
				if (itemName.contains("body")) {
					return 90000;
				}
				if (itemName.contains("skirt")) {
					return 80000;
				}
				if (itemName.contains("flail")) {
					return 100000;
				}
				if (itemName.contains("helm")) {
					return 60000;
				}				
			}
		}
		if (itemName.contains("dharok")) {
			if (itemName.contains("100")) {
				if (itemName.contains("body")) {
					return 5000;
				}
				if (itemName.contains("legs")) {
					return 6000;
				}
				if (itemName.contains("axe")) {
					return 10000;
				}
				if (itemName.contains("helm")) {
					return 3000;
				}
			}
			if (itemName.contains("75")) {
				if (itemName.contains("body")) {
					return 23000;
				}
				if (itemName.contains("legs")) {
					return 20000;
				}
				if (itemName.contains("axe")) {
					return 25000;
				}
				if (itemName.contains("helm")) {
					return 15000;
				}				
			}
			if (itemName.contains("50")) {
				if (itemName.contains("body")) {
					return 45000;
				}
				if (itemName.contains("legs")) {
					return 40000;
				}
				if (itemName.contains("axe")) {
					return 50000;
				}
				if (itemName.contains("helm")) {
					return 30000;
				}				
			}
			if (itemName.contains("25")) {
				if (itemName.contains("body")) {
					return 70000;
				}
				if (itemName.contains("legs")) {
					return 65000;
				}
				if (itemName.contains("axe")) {
					return 75000;
				}
				if (itemName.contains("helm")) {
					return 45000;
				}				
			}
			if (itemName.contains("0")) {
				if (itemName.contains("body")) {
					return 90000;
				}
				if (itemName.contains("legs")) {
					return 80000;
				}
				if (itemName.contains("axe")) {
					return 100000;
				}
				if (itemName.contains("helm")) {
					return 60000;
				}				
			}
		}
		if (itemName.contains("karils")) {
			if (itemName.contains("100")) {
				if (itemName.contains("top")) {
					return 5000;
				}
				if (itemName.contains("skirt")) {
					return 6000;
				}
				if (itemName.contains("x-bow")) {
					return 10000;
				}
				if (itemName.contains("coif")) {
					return 3000;
				}
			}
			if (itemName.contains("75")) {
				if (itemName.contains("top")) {
					return 23000;
				}
				if (itemName.contains("skirt")) {
					return 20000;
				}
				if (itemName.contains("x-bow")) {
					return 25000;
				}
				if (itemName.contains("coif")) {
					return 15000;
				}				
			}
			if (itemName.contains("50")) {
				if (itemName.contains("top")) {
					return 45000;
				}
				if (itemName.contains("skirt")) {
					return 40000;
				}
				if (itemName.contains("x-bow")) {
					return 50000;
				}
				if (itemName.contains("coif")) {
					return 30000;
				}				
			}
			if (itemName.contains("25")) {
				if (itemName.contains("top")) {
					return 70000;
				}
				if (itemName.contains("skirt")) {
					return 65000;
				}
				if (itemName.contains("x-bow")) {
					return 75000;
				}
				if (itemName.contains("coif")) {
					return 45000;
				}				
			}
			if (itemName.contains("0")) {
				if (itemName.contains("top")) {
					return 90000;
				}
				if (itemName.contains("skirt")) {
					return 80000;
				}
				if (itemName.contains("x-bow")) {
					return 100000;
				}
				if (itemName.contains("coif")) {
					return 60000;
				}				
			}
		}
		if (itemName.contains("guthan")) {
			if (itemName.contains("100")) {
				if (itemName.contains("body")) {
					return 5000;
				}
				if (itemName.contains("skirt")) {
					return 6000;
				}
				if (itemName.contains("spear")) {
					return 10000;
				}
				if (itemName.contains("helm")) {
					return 3000;
				}
			}
			if (itemName.contains("75")) {
				if (itemName.contains("body")) {
					return 23000;
				}
				if (itemName.contains("skirt")) {
					return 20000;
				}
				if (itemName.contains("spear")) {
					return 25000;
				}
				if (itemName.contains("helm")) {
					return 15000;
				}				
			}
			if (itemName.contains("50")) {
				if (itemName.contains("body")) {
					return 45000;
				}
				if (itemName.contains("skirt")) {
					return 40000;
				}
				if (itemName.contains("spear")) {
					return 50000;
				}
				if (itemName.contains("helm")) {
					return 30000;
				}				
			}
			if (itemName.contains("25")) {
				if (itemName.contains("body")) {
					return 70000;
				}
				if (itemName.contains("skirt")) {
					return 65000;
				}
				if (itemName.contains("spear")) {
					return 75000;
				}
				if (itemName.contains("helm")) {
					return 45000;
				}				
			}
			if (itemName.contains("0")) {
				if (itemName.contains("body")) {
					return 90000;
				}
				if (itemName.contains("skirt")) {
					return 80000;
				}
				if (itemName.contains("spear")) {
					return 100000;
				}
				if (itemName.contains("helm")) {
					return 60000;
				}				
			}
		}
		return -1;
	}
	public static int getRepairedItem(int itemId) {
		String itemName = ItemManager.getInstance().getItemName(itemId).toLowerCase();
		if (itemName.contains("ahrim")) {
			if (itemName.contains("100")) {
				if (itemName.contains("top")) {
					return 4712;
				}
				if (itemName.contains("bottom")) {
					return 4714;
				}
				if (itemName.contains("staff")) {
					return 4710;
				}
				if (itemName.contains("hood")) {
					return 4708;
				}
			}
			if (itemName.contains("75")) {
				if (itemName.contains("top")) {
					return 4712;
				}
				if (itemName.contains("bottom")) {
					return 4714;
				}
				if (itemName.contains("staff")) {
					return 4710;
				}
				if (itemName.contains("hood")) {
					return 4708;
				}				
			}
			if (itemName.contains("50")) {
				if (itemName.contains("top")) {
					return 4712;
				}
				if (itemName.contains("bottom")) {
					return 4714;
				}
				if (itemName.contains("staff")) {
					return 4710;
				}
				if (itemName.contains("hood")) {
					return 4708;
				}				
			}
			if (itemName.contains("25")) {
				if (itemName.contains("top")) {
					return 4712;
				}
				if (itemName.contains("bottom")) {
					return 4714;
				}
				if (itemName.contains("staff")) {
					return 4710;
				}
				if (itemName.contains("hood")) {
					return 4708;
				}				
			}
			if (itemName.contains("0")) {
				if (itemName.contains("top")) {
					return 4712;
				}
				if (itemName.contains("bottom")) {
					return 4714;
				}
				if (itemName.contains("staff")) {
					return 4710;
				}
				if (itemName.contains("hood")) {
					return 4708;
				}				
			}

		}
		if (itemName.contains("torag")) {
			if (itemName.contains("100")) {
				if (itemName.contains("body")) {
					return 4749;
				}
				if (itemName.contains("legs")) {
					return 4751;
				}
				if (itemName.contains("hammer")) {
					return 4747;
				}
				if (itemName.contains("helm")) {
					return 4745;
				}
			}
			if (itemName.contains("75")) {
				if (itemName.contains("body")) {
					return 4749;
				}
				if (itemName.contains("legs")) {
					return 4751;
				}
				if (itemName.contains("hammer")) {
					return 4747;
				}
				if (itemName.contains("helm")) {
					return 4745;
				}				
			}
			if (itemName.contains("50")) {
				if (itemName.contains("body")) {
					return 4749;
				}
				if (itemName.contains("legs")) {
					return 4751;
				}
				if (itemName.contains("hammer")) {
					return 4747;
				}
				if (itemName.contains("helm")) {
					return 4745;
				}				
			}
			if (itemName.contains("25")) {
				if (itemName.contains("body")) {
					return 4749;
				}
				if (itemName.contains("legs")) {
					return 4751;
				}
				if (itemName.contains("hammer")) {
					return 4747;
				}
				if (itemName.contains("helm")) {
					return 4745;
				}				
			}
			if (itemName.contains("0")) {
				if (itemName.contains("body")) {
					return 4749;
				}
				if (itemName.contains("legs")) {
					return 4751;
				}
				if (itemName.contains("hammer")) {
					return 4747;
				}
				if (itemName.contains("helm")) {
					return 4745;
				}				
			}

		}
		if (itemName.contains("veracs")) {
			if (itemName.contains("100")) {
				if (itemName.contains("top")) {
					return 4757;
				}
				if (itemName.contains("skirt")) {
					return 4759;
				}
				if (itemName.contains("flail")) {
					return 4755;
				}
				if (itemName.contains("helm")) {
					return 4753;
				}
			}
			if (itemName.contains("75")) {
				if (itemName.contains("top")) {
					return 4757;
				}
				if (itemName.contains("skirt")) {
					return 4759;
				}
				if (itemName.contains("flail")) {
					return 4755;
				}
				if (itemName.contains("helm")) {
					return 4753;
				}				
			}
			if (itemName.contains("50")) {
				if (itemName.contains("top")) {
					return 4757;
				}
				if (itemName.contains("skirt")) {
					return 4759;
				}
				if (itemName.contains("flail")) {
					return 4755;
				}
				if (itemName.contains("helm")) {
					return 4753;
				}				
			}
			if (itemName.contains("25")) {
				if (itemName.contains("top")) {
					return 4757;
				}
				if (itemName.contains("skirt")) {
					return 4759;
				}
				if (itemName.contains("flail")) {
					return 4755;
				}
				if (itemName.contains("helm")) {
					return 4753;
				}				
			}
			if (itemName.contains("0")) {
				if (itemName.contains("top")) {
					return 4757;
				}
				if (itemName.contains("skirt")) {
					return 4759;
				}
				if (itemName.contains("flail")) {
					return 4755;
				}
				if (itemName.contains("helm")) {
					return 4753;
				}				
			}
		}
		if (itemName.contains("dharok")) {
			if (itemName.contains("100")) {
				if (itemName.contains("body")) {
					return 4720;
				}
				if (itemName.contains("legs")) {
					return 4722;
				}
				if (itemName.contains("axe")) {
					return 4718;
				}
				if (itemName.contains("helm")) {
					return 4716;
				}
			}
			if (itemName.contains("75")) {
				if (itemName.contains("body")) {
					return 4720;
				}
				if (itemName.contains("legs")) {
					return 4722;
				}
				if (itemName.contains("axe")) {
					return 4718;
				}
				if (itemName.contains("helm")) {
					return 4716;
				}				
			}
			if (itemName.contains("50")) {
				if (itemName.contains("body")) {
					return 4720;
				}
				if (itemName.contains("legs")) {
					return 4722;
				}
				if (itemName.contains("axe")) {
					return 4718;
				}
				if (itemName.contains("helm")) {
					return 4716;
				}				
			}
			if (itemName.contains("25")) {
				if (itemName.contains("body")) {
					return 4720;
				}
				if (itemName.contains("legs")) {
					return 4722;
				}
				if (itemName.contains("axe")) {
					return 4718;
				}
				if (itemName.contains("helm")) {
					return 4716;
				}				
			}
			if (itemName.contains("0")) {
				if (itemName.contains("body")) {
					return 4720;
				}
				if (itemName.contains("legs")) {
					return 4722;
				}
				if (itemName.contains("axe")) {
					return 4718;
				}
				if (itemName.contains("helm")) {
					return 4716;
				}				
			}
		}
		if (itemName.contains("karils")) {
			if (itemName.contains("100")) {
				if (itemName.contains("top")) {
					return 4736;
				}
				if (itemName.contains("skirt")) {
					return 4738;
				}
				if (itemName.contains("x-bow")) {
					return 4734;
				}
				if (itemName.contains("coif")) {
					return 4732;
				}
			}
			if (itemName.contains("75")) {
				if (itemName.contains("top")) {
					return 4736;
				}
				if (itemName.contains("skirt")) {
					return 4738;
				}
				if (itemName.contains("x-bow")) {
					return 4734;
				}
				if (itemName.contains("coif")) {
					return 4732;
				}				
			}
			if (itemName.contains("50")) {
				if (itemName.contains("top")) {
					return 4736;
				}
				if (itemName.contains("skirt")) {
					return 4738;
				}
				if (itemName.contains("x-bow")) {
					return 4734;
				}
				if (itemName.contains("coif")) {
					return 4732;
				}				
			}
			if (itemName.contains("25")) {
				if (itemName.contains("top")) {
					return 4736;
				}
				if (itemName.contains("skirt")) {
					return 4738;
				}
				if (itemName.contains("x-bow")) {
					return 4734;
				}
				if (itemName.contains("coif")) {
					return 4732;
				}				
			}
			if (itemName.contains("0")) {
				if (itemName.contains("top")) {
					return 4736;
				}
				if (itemName.contains("skirt")) {
					return 4738;
				}
				if (itemName.contains("x-bow")) {
					return 4734;
				}
				if (itemName.contains("coif")) {
					return 4732;
				}				
			}
		}
		if (itemName.contains("guthan")) {
			if (itemName.contains("100")) {
				if (itemName.contains("body")) {
					return 4728;
				}
				if (itemName.contains("skirt")) {
					return 4730;
				}
				if (itemName.contains("spear")) {
					return 4726;
				}
				if (itemName.contains("helm")) {
					return 4724;
				}
			}
			if (itemName.contains("75")) {
				if (itemName.contains("body")) {
					return 4728;
				}
				if (itemName.contains("skirt")) {
					return 4730;
				}
				if (itemName.contains("spear")) {
					return 4726;
				}
				if (itemName.contains("helm")) {
					return 4724;
				}				
			}
			if (itemName.contains("50")) {
				if (itemName.contains("body")) {
					return 4728;
				}
				if (itemName.contains("skirt")) {
					return 4730;
				}
				if (itemName.contains("spear")) {
					return 4726;
				}
				if (itemName.contains("helm")) {
					return 4724;
				}				
			}
			if (itemName.contains("25")) {
				if (itemName.contains("body")) {
					return 4728;
				}
				if (itemName.contains("skirt")) {
					return 4730;
				}
				if (itemName.contains("spear")) {
					return 4726;
				}
				if (itemName.contains("helm")) {
					return 4724;
				}				
			}
			if (itemName.contains("0")) {
				if (itemName.contains("body")) {
					return 4728;
				}
				if (itemName.contains("skirt")) {
					return 4730;
				}
				if (itemName.contains("spear")) {
					return 4726;
				}
				if (itemName.contains("helm")) {
					return 4724;
				}				
			}
		}
		return -1;
	}	
	public static boolean getDegradedId(int itemId) {
		for (int i = 0; i < degradedBarrows.length; i++) {
			if (itemId == degradedBarrows[i]) {
				return true;
			}
		}
		return false;
	}
	
	public static final int[][] barrowsBrothers = {{6823, 2030}, {6772, 2029}, {6822, 2028}, {6773, 2027}, {6771, 2026}, {6821, 2025}};

	public static final int[] Items = {4708, 4710, 4712, 4714, 4716, 4718, 4720, 4722, 4724, 4726, 4728, 4730, 4732, 4734, 4736, 4738, 4745, 4747, 4749, 4751, 4753, 4755, 4757, 4759, 1149, 165, 117, 141, 129, 385};
	public static final int[][] Stackables = {{4740, 50}, {558, 100}, {562, 100}, {560, 100}, {565, 100}, {995, 3000}};

	public static boolean barrowsObject(Player player, int objectId) {
		switch (objectId) {
			case 10284 : // chest
				if (player.getKillCount() < 1) {
					player.getActionSender().sendMessage("You search the chest but don't find anything.");
					return true;
				}
				for (int x = 0; x < barrowsBrothers.length; x++) {
					if (x == player.getRandomGrave()) {
						//if (NpcLoader.checkSpawn(player, barrowsBrothers[x][1])) {
						if(player.getSpawnedNpc() != null && !player.getSpawnedNpc().isDead() && player.getSpawnedNpc().getNpcId() == x){	
							player.getActionSender().sendMessage("You must kill the brother before searching this.");
							return true;
						}
						if (!player.getBarrowsNpcDead()[x]) {
							NpcLoader.spawnNpc(player, new Npc(barrowsBrothers[x][1]), true, true);
							return true;
						}
						break;
					}
				}
				getReward(player);
				return true;
			case 6823 :
			case 6772 :
			case 6821 :
			case 6771 :
			case 6773 :
			case 6822 :
				for (int x = 0; x < barrowsBrothers.length; x++) {
					if (objectId == barrowsBrothers[x][0]) {
						if (x == player.getRandomGrave()) {
							Dialogues.startDialogue(player, 10001);
							return true;
						}
						if (NpcLoader.checkSpawn(player, barrowsBrothers[x][1])) {
						//if(player.getSpawnedNpc() != null && !player.getSpawnedNpc().isDead() && player.getSpawnedNpc().getNpcId() == x){	
	
							player.getActionSender().sendMessage("You must kill the brother before searching this.");
							return true;
						}
						if (player.getBarrowsNpcDead()[x]) {
							player.getActionSender().sendMessage("You have already searched this sarcophagus.");
							return true;
						}
						NpcLoader.spawnNpc(player, new Npc(barrowsBrothers[x][1]), true, true);
						if (x != player.getRandomGrave()) {
							player.getActionSender().sendMessage("You don't find anything.");
						}
						return true;
					}
				}
				return true;
			case 6707 : // verac stairs
				player.teleport(new Position(3556, 3298, 0));
				return true;
			case 6706 : // torag stairs
				player.teleport(new Position(3553, 3283, 0));
				break;
			case 6705 : // karil stairs
				player.teleport(new Position(3565, 3276, 0));
				return true;
			case 6704 : // guthan stairs
				player.teleport(new Position(3578, 3284, 0));
				return true;
			case 6703 : // dharok stairs
				player.teleport(new Position(3574, 3298, 0));
				return true;
			case 6702 : // ahrim stairs
				player.teleport(new Position(3565, 3290, 0));
				return true;
		}
		return false;
	}

	public static boolean digCrypt(Player player) {
		if (player.Area(3553, 3561, 3294, 3301)) {
			player.teleport(new Position(3578, 9706, 3));
			player.getActionSender().sendMessage("You've broken into a crypt!");
	        player.getActionSender().sendMapState(2);
			return true;
		} else if (player.Area(3550, 3557, 3278, 3287)) {
			player.teleport(new Position(3568, 9683, 3));
			player.getActionSender().sendMessage("You've broken into a crypt!");
	        player.getActionSender().sendMapState(2);
			return true;
		} else if (player.Area(3561, 3568, 3285, 3292)) {
			player.teleport(new Position(3557, 9703, 3));
			player.getActionSender().sendMessage("You've broken into a crypt!");
	        player.getActionSender().sendMapState(2);
			return true;
		} else if (player.Area(3570, 3579, 3293, 3302)) {
			player.teleport(new Position(3556, 9718, 3));
			player.getActionSender().sendMessage("You've broken into a crypt!");
	        player.getActionSender().sendMapState(2);
			return true;
		} else if (player.Area(3571, 3582, 3278, 3285)) {
			player.teleport(new Position(3534, 9704, 3));
			player.getActionSender().sendMessage("You've broken into a crypt!");
	        player.getActionSender().sendMapState(2);
			return true;
		} else if (player.Area(3562, 3569, 3273, 3279)) {
			player.teleport(new Position(3546, 9684, 3));
			player.getActionSender().sendMessage("You've broken into a crypt!");
	        player.getActionSender().sendMapState(2);
			return true;
		}
		return false;
	}

	public static void getReward(Player player) {
		final int number = Misc.randomMinusOne(Stackables.length);
		final int rune = Stackables[number][0];
		final int amount = Stackables[number][1];
		final int reward = Items[Misc.randomMinusOne(Items.length)];
		int kills = brotherKillCount(player);
		if(kills > 6)
			kills = 6;
		if (kills < 1) {
			player.getActionSender().sendMessage("You can only loot the chest after killing at least 1 brother.");
			return;
		}
		boolean getBarrows = Misc.random(812 - (kills * 100)) == 0;
		if (getBarrows) {
			if (player.getInventory().getItemContainer().freeSlots() == 1) {
				if (!player.getInventory().playerHasItem(rune)) {
					player.getActionSender().sendMessage("You must have three empty spaces in order to take this loot.");
					return;
				}
			} else if (player.getInventory().getItemContainer().freeSlots() < 1) {
				player.getActionSender().sendMessage("You must have three empty spaces in order to take this loot.");
				return;
			}
			player.getInventory().addItem(new Item(rune, Misc.random(amount * kills) + 1));
			player.getInventory().addItem(new Item(reward, 1));
		} else {
			final int number2 = Misc.randomMinusOne(Stackables.length);
			final int rune2 = Stackables[number2][0];
			final int amount2 = Stackables[number2][1];
			if (player.getInventory().getItemContainer().freeSlots() < 1) {
				if (!player.getInventory().playerHasItem(rune) || !player.getInventory().playerHasItem(rune2)) {
					player.getActionSender().sendMessage("You must have three empty spaces in order to take this loot.");
					return;
				}
			}
			if (player.getInventory().getItemContainer().freeSlots() == 1) {
				if (!player.getInventory().playerHasItem(rune) && !player.getInventory().playerHasItem(rune2)) {
					player.getActionSender().sendMessage("You must have three empty spaces in order to take this loot.");
					return;
				}
			}
			player.getInventory().addItem(new Item(rune, Misc.random(amount * kills) + 1));
			player.getInventory().addItem(new Item(rune2, Misc.random(amount2 * kills) + 1));
		}
		player.getUpdateFlags().sendAnimation(714);
		player.getUpdateFlags().sendHighGraphic(301);
		player.getTeleportation().teleport(3565, 3308, 0, true);
		player.getActionSender().sendMessage("You grab the loot and teleport away.");
		resetBarrows(player);
		return;
	}

	private static int brotherKillCount(Player player) {
		int brotherKillCount = 0;
		for (boolean kill : player.getBarrowsNpcDead()) {
			if (kill) {
				brotherKillCount++;
			}
		}
		return brotherKillCount;
	}

	public static void resetBarrows(Player player) {
		for (int x = 0; x < Barrows.barrowsBrothers.length; x++) {
			player.setBarrowsNpcDead(x, false);
		}
		player.setKillCount(0);
		player.setRandomGrave(Misc.random(5));
	}

	public static void handleDeath(Player player, Npc npc) {
		for (int x = 0; x < barrowsBrothers.length; x++) {
			if (npc.getNpcId() == barrowsBrothers[x][1]) {
				player.setKillCount(player.getKillCount() + 1);
				player.getActionSender().sendString("Kill count: " + player.getKillCount(), 4536);
				player.setBarrowsNpcDead(x, true);
				npc.isDead();
				break;
			}
		}
	}
}
