package com.rs2.model.content;

import com.rs2.model.players.Player;

/**
 *
 * @author Phillip
 */
public class Quests {
    
    public final static int activeQuests = 1;//basically, just how many quests we have that work, that need to be saved.
    
    //All non-member quests thanks to Harry.
    public static int configIds[] = { 130, 29, 222, 31, 176, 32, 62, 160, 122, 71, 273, 107, 144, 63, 179, 146, 178, 67 };
    public static int configCompleteValues[] = { 4, 2, 3, 100, 10, 3, 6, 2, 7, 4, 110, 5, 100, 6, 21, 4, 3, 3};
    
    public static void advanceQuest(Player player, int questId){
        player.questStage[questId]++;
    }
    public static void setQuestStage(Player player, int questId, int stage){
        player.questStage[questId] = stage;
    }
    
    public static int getQuestStage(Player player, int questId){
        return player.questStage[questId];
    }
    
    public static void refreshQuestConfigs(Player player) {
        for(int i = 0; i < activeQuests; i++){
            player.getActionSender().sendConfig(configIds[i], getQuestStage(player, i));
        }
    }
    
    //0 - Cooks Assistant?

    
}
