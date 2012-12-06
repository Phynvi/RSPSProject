package com.rs2.model.region.Sound;


public class SoundSystem {
    public enum SoundData {
    	/* soundid, animation */
    	

    	WHIPSPECIAL(1081, 341, SoundType.GRAPHIC, 0),
        WHIP(1080, 1658, SoundType.ANIMATION, 0),
        BOW(370, 426, SoundType.ANIMATION, 0),
        SCIMTAR(396, 390, SoundType.ANIMATION, 0),
        BATTLEAXE(399, 1833, SoundType.ANIMATION, 0),
        BATTLEAXE2(399, 401, SoundType.ANIMATION, 0),
        BATTLEAXE3(399, 395, SoundType.ANIMATION, 0),
        LONGSWORD(396, 451, SoundType.ANIMATION, 0),
        DAGGER(398, 402, SoundType.ANIMATION, 0),
        SWORD(398, 412, SoundType.ANIMATION, 0),
        DHAROK(1057, 2067, SoundType.ANIMATION, 0),
        DDSSPEC(385, 1062, SoundType.ANIMATION, 0),
        MSBSPEC(386, 1074, SoundType.ANIMATION, 0),
        DLONGSPEC(390, 1058, SoundType.ANIMATION, 0),
        DSCIMSPEC(396, 1872, SoundType.ANIMATION, 0),
        DMACESPEC(387, 1060, SoundType.ANIMATION, 0),
        DSPEARSPEC(388, 1064, SoundType.ANIMATION, 0),
        DSPEAR(388, 1064, SoundType.ANIMATION, 0),
        DHALLYSPEC(392, 1203, SoundType.ANIMATION, 0),
        DHALLY(420, 440, SoundType.ANIMATION, 0),
        D2HSPEC(388, 3157, SoundType.ANIMATION, 0),
        DBAXESPEC(389, 1056, SoundType.ANIMATION, 0),
        DBAXE(382, 1056, SoundType.ANIMATION, 0),
        GMAULSPEC(1082, 1667, SoundType.ANIMATION, 0),
        GMAUL(1079, 1665, SoundType.ANIMATION, 0),
        HIGHALCH(223, 713, SoundType.ANIMATION, 0),
    	LOWALCH(224, 712, SoundType.ANIMATION, 0),
    	TELEPORT(202, 714, SoundType.ANIMATION, 0),
    	SHIELDBLOCK(463, 1156, SoundType.ANIMATION, 0),
    	NOSHIELDBLOCK(463, 816, SoundType.ANIMATION, 0),
    	TWOHHIT(426, 406, SoundType.ANIMATION, 0),
    	TWOHHIT2(425, 407, SoundType.ANIMATION, 0),
    	SPLASH(940, 85, SoundType.GRAPHIC, 0),
    	COOKING(240, 883, SoundType.ANIMATION, 0),    	
    	COOKING2(240, 897, SoundType.ANIMATION, 0),
    	OVEN(352, 899, SoundType.ANIMATION, 0),
    	RCING(481, 791, SoundType.ANIMATION, 0),
    	THROWING(369, 806, SoundType.ANIMATION, 0),
    	WCING1(471, 867, SoundType.ANIMATION, 0),
    	WCING2(471, 869, SoundType.ANIMATION, 0),
    	WCING3(471, 871, SoundType.ANIMATION, 0),
    	WCING4(471, 875, SoundType.ANIMATION, 0),
    	WCING5(471, 877, SoundType.ANIMATION, 0),
    	WCING6(471, 879, SoundType.ANIMATION, 0),
    	WCING7(471, 2846, SoundType.ANIMATION, 0),
    	WCING11(472, 867, SoundType.ANIMATION, 50),
    	WCING21(472, 869, SoundType.ANIMATION, 50),
    	WCING31(472, 871, SoundType.ANIMATION, 50),
    	WCING41(472, 875, SoundType.ANIMATION, 50),
    	WCING51(472, 877, SoundType.ANIMATION, 50),
    	WCING61(472, 879, SoundType.ANIMATION, 50),
    	WCING71(472, 2846, SoundType.ANIMATION, 50),
    	MINING1(1230, 624, SoundType.ANIMATION, 0),
    	MINING2(1230, 628, SoundType.ANIMATION, 0),
    	MINING3(1230, 629, SoundType.ANIMATION, 0),
    	MINING4(1230, 625, SoundType.ANIMATION, 0),
    	MINING5(1230, 627, SoundType.ANIMATION, 0),
    	MINING6(1230, 626, SoundType.ANIMATION, 0),
    	FLETCHING(375, 1248, SoundType.ANIMATION, 0),
    	BOWSTRING(375, 1248, SoundType.ANIMATION, 0),
    	FISHING1(379, 618, SoundType.ANIMATION, 0),
    	FISHING2(379, 619, SoundType.ANIMATION, 0),
    	FISHING3(379, 621, SoundType.ANIMATION, 0),
    	FISHING4(379, 622, SoundType.ANIMATION, 0),
    	TELEOTHER(1184, 1818, SoundType.ANIMATION, 0),
    	TELEBLOCK(1185, 1819, SoundType.ANIMATION, 0),
    	TELEBLOCKHIT(1183, 345, SoundType.GRAPHIC, 0),
    	ANCIENTTELEPORT(1048, 392, SoundType.GRAPHIC, 8),
    	WINDSTRIKECAST(994, 90, SoundType.GRAPHIC, 0),
    	WINDSTRIKEHIT(995, 92, SoundType.GRAPHIC, 0),    	
    	WATERSTRIKECAST(1023, 93, SoundType.GRAPHIC, 0),
    	WATERSTRIKEHIT(1022, 95, SoundType.GRAPHIC, 0),
    	EARTHSTRIKECAST(1002, 96, SoundType.GRAPHIC, 0),
    	EARTHSTRIKEHIT(1004, 98, SoundType.GRAPHIC, 0),
    	FIRESTRIKECAST(1017, 99, SoundType.GRAPHIC, 0),
    	FIRESTRIKEHIT(1018, 101, SoundType.GRAPHIC, 0),
    	AIRBOLTCAST(1031, 117, SoundType.GRAPHIC, 0),
    	AIRBOLTHIT(1032, 119, SoundType.GRAPHIC, 0),
    	WATERBOLTCAST(1026, 120, SoundType.GRAPHIC, 0),
    	WATERBOLTHIT(1025, 122, SoundType.GRAPHIC, 0),
    	EARTHBOLTCAST(1003, 123, SoundType.GRAPHIC, 0),
    	EARTHBOLTHIT(1006, 125, SoundType.GRAPHIC, 0),
    	FIREBOLTCAST(1015, 126, SoundType.GRAPHIC, 0),
    	FIREBOLTHIT(1016, 128, SoundType.GRAPHIC, 0),
    	AIRBLASTCAST(1030, 132, SoundType.GRAPHIC, 0),
    	AIRBLASTHIT(1033, 134, SoundType.GRAPHIC, 0),
    	WATERBLASTCAST(1026, 135, SoundType.GRAPHIC, 0),
    	WATERBLASTHIT(1027, 137, SoundType.GRAPHIC, 0),
    	EARTHBLASTCAST(1007, 138, SoundType.GRAPHIC, 0),
    	EARTHBLASTHIT(1005, 140, SoundType.GRAPHIC, 0),
    	FIREBLASTCAST(1020, 129, SoundType.GRAPHIC, 0),
    	FIREBLASTHIT(1019, 131, SoundType.GRAPHIC, 0),
    	AIRWAVECAST(1034, 158, SoundType.GRAPHIC, 0),
    	AIRWAVEHIT(1036, 160, SoundType.GRAPHIC, 0),
    	WATERWAVECAST(1028, 161, SoundType.GRAPHIC, 0),
    	WATERWAVEHIT(1029, 163, SoundType.GRAPHIC, 0),
    	EARTHWAVECAST(1009, 164, SoundType.GRAPHIC, 0),
    	EARTHWAVEHIT(1008, 166, SoundType.GRAPHIC, 0),
    	FIREWAVECAST(1014, 155, SoundType.GRAPHIC, 0),
    	FIREWAVEHIT(1021, 157, SoundType.GRAPHIC, 0),
    	IBANCAST(-1, 87, SoundType.GRAPHIC, 0),
    	IBANHIT(-1, 89, SoundType.GRAPHIC, 0),
    	TELEGRABCAST(200, 142, SoundType.GRAPHIC, 0),
    	CONFUSECAST(1000, 102, SoundType.GRAPHIC, 0),
    	CONFUSEHIT(1001, 104, SoundType.GRAPHIC, 0),
    	/*WEAKENCAST(, 105, SoundType.GRAPHIC, 0),
    	//WEAKENTRAVEL(, 106, SoundType.GRAPHIC, 0),
    	WEAKENHIT(, 107, SoundType.GRAPHIC, 0),
    	CURSECAST(, 108, SoundType.GRAPHIC, 0),
    	//CURSETRAVEL(, 109, SoundType.GRAPHIC, 0),
    	CURSEHIT(, 110, SoundType.GRAPHIC, 0),
    	VULNERABILITYCAST(, 167, SoundType.GRAPHIC, 0),
    	//VULNERABILITYTRAVEL(, 168, SoundType.GRAPHIC, 0),
    	VULNERABILITYHIT(, 169, SoundType.GRAPHIC, 0),
    	ENFEEBLECAST(, 170, SoundType.GRAPHIC, 0),
    	//ENFEEBLETRAVEL(, 171, SoundType.GRAPHIC, 0),
    	ENFEEBLEHIT(, 172, SoundType.GRAPHIC, 0),
    	STUNCAST(, 174, SoundType.GRAPHIC, 0),
    	//STUNTRAVEL(, 175, SoundType.GRAPHIC, 0),
    	STUNHIT(, 107, SoundType.GRAPHIC, 0),
    	BINDCAST(, 177, SoundType.GRAPHIC, 0),
    	//BINDTRAVEL(, 178, SoundType.GRAPHIC, 0),
    	BINDHIT(, 181, SoundType.GRAPHIC, 0),
    	SNARECAST(, 177, SoundType.GRAPHIC, 0),
    	//SNARETRAVEL(, 178, SoundType.GRAPHIC, 0),
    	SNAREHIT(, 180, SoundType.GRAPHIC, 0),
    	ENTANGLECAST(, 177, SoundType.GRAPHIC, 0),
    	//ENTANGLETRAVEL(, 178, SoundType.GRAPHIC, 0),
    	ENTANGLEHIT(, 179, SoundType.GRAPHIC, 0),
    	CRUMBLECAST(, 146, SoundType.GRAPHIC, 0),
    	//CRUMBLETRAVEL(, 146, SoundType.GRAPHIC, 0),
    	CRUMBLEHIT(, 147, SoundType.GRAPHIC, 0),
    	MAGICDARTCAST(, 327, SoundType.GRAPHIC, 0),
    	//MAGICDARTTRAVEL(, 328, SoundType.GRAPHIC, 0),
    	MAGICDARTHIT(, 329, SoundType.GRAPHIC, 0),
    	SARAHIT(, 76, SoundType.GRAPHIC, 0),
    	GUTHHIT(, 77, SoundType.GRAPHIC, 0),
    	ZAMHIT(, 78, SoundType.GRAPHIC, 0),
    	CHARGECAST(, 301, SoundType.GRAPHIC, 0),
    	WATERCHARGECAST(, 149, SoundType.GRAPHIC, 0),
    	EARTHCHARGECAST(, 150, SoundType.GRAPHIC, 0),
    	FIRECHARGECAST(, 151, SoundType.GRAPHIC, 0),
    	AIRCHARGECAST(, 152, SoundType.GRAPHIC, 0),
    	BONESTOBANCAST(, 141, SoundType.GRAPHIC, 0),
    	SUPERHEATCAST(, 148, SoundType.GRAPHIC, 0),
    	BONESTOPEACAST(, 311, SoundType.GRAPHIC, 0),
    	ENCHANTLVL1CAST(, 114, SoundType.GRAPHIC, 0),
    	ENCHANTLVL2CAST(, 114, SoundType.GRAPHIC, 0),
    	ENCHANTLVL3CAST(, 115, SoundType.GRAPHIC, 0),
    	ENCHANTLVL4CAST(, 115, SoundType.GRAPHIC, 0),
    	ENCHANTLVL5CAST(, 116, SoundType.GRAPHIC, 0),
    	ENCHANTLVL6CAST(, 452, SoundType.GRAPHIC, 0),*/
    	/*** Ancient Spells ***/
    	/*SMOKERUSHTRAVEL(, 386, SoundType.GRAPHIC, 0),
    	SMOKERUSHHIT(, 387, SoundType.GRAPHIC, 0),
    	SHADOWRUSHTRAVEL(, 378, SoundType.GRAPHIC, 0),
    	SHADOWRUSHHIT(, 379, SoundType.GRAPHIC, 0),
    	BLOODRUSHTRAVEL(, 372, SoundType.GRAPHIC, 0),
    	BLOODRUSHHIT(, 373, SoundType.GRAPHIC, 0),
    	ICERUSHTRAVEL(, 360, SoundType.GRAPHIC, 0),
    	ICERUSHHIT(, 361, SoundType.GRAPHIC, 0),
    	SMOKEBURSTHIT(, 390, SoundType.GRAPHIC, 0),
    	SHADOWBURSTHIT(, 382, SoundType.GRAPHIC, 0),
    	BLOODBURSTHIT(, 376, SoundType.GRAPHIC, 0),
    	SMOKEBLITZTRAVEL(, 386, SoundType.GRAPHIC, 0),
    	SMOKEBLITZHIT(, 387, SoundType.GRAPHIC, 0),
    	SHADOWBLITZTRAVEL(, 380, SoundType.GRAPHIC, 0),
    	SHADOWBLITZHIT(, 381, SoundType.GRAPHIC, 0),
    	BLOODBLITZTRAVEL(, 374, SoundType.GRAPHIC, 0),
    	BLOODBLITZHIT(, 375, SoundType.GRAPHIC, 0),
    	ICEBLITZCAST(, 366, SoundType.GRAPHIC, 0),
    	ICEBLITZHIT(, 367, SoundType.GRAPHIC, 0),
    	SMOKEBARRAGEHIT(, 391, SoundType.GRAPHIC, 0),
    	SHADOWBARRAGEHIT(, 383, SoundType.GRAPHIC, 0),
    	BLOODBARRAGEHIT(, 377, SoundType.GRAPHIC, 0),
    	ICEBARRAGEHIT(1125, 369, SoundType.GRAPHIC, 0),
    	ICEBARRAGESTART(1111, 368, SoundType.GRAPHIC, 0),
    	ICEBLITZSTART(1111, 366, SoundType.GRAPHIC, 0),
    	ICEBBURSTHIT(1123, 363, SoundType.GRAPHIC, 0),
*/

        ;
    	/*
    	 * 
    	CAST(, , SoundType.GRAPHIC, 0),
    	TRAVEL(, , SoundType.GRAPHIC, 0),
    	HIT(, , SoundType.GRAPHIC, 0),
    	*/
    	 
        private int soundId;
        private int activateOnId;
        private SoundType soundType;
        private int delay;

        SoundData(int soundId, int activateOnId, SoundType soundType, int delay) {
            this.soundId = soundId;
            this.activateOnId = activateOnId;
            this.soundType = soundType;
            this.delay = delay;
        }

        public static SoundData forId(int id, SoundType type){
            for(SoundData soundData : SoundData.values())
                if(soundData.activateOnId == id && type == soundData.soundType)
                    return soundData;
            return null;
        }

        public int getSoundId() {
            return soundId;
        }
        public int getSoundDelay() {
            return delay;
        }
    }

    public enum SoundType {
        ANIMATION, GRAPHIC
    }
}
