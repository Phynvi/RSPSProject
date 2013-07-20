require 'java'
java_import "com.rs2.model.npcs.NpcCombatDef"
java_import "com.rs2.model.content.combat.AttackScript"
java_import "com.rs2.model.content.combat.attacks.BasicAttack"
java_import "com.rs2.model.content.combat.HealersCombatScript"
java_import "com.rs2.model.content.combat.weapon.AttackStyle"
java_import "com.rs2.model.content.combat.AttackType"
java_import "com.rs2.model.content.combat.weapon.Weapon"
java_import "com.rs2.model.content.combat.projectile.ProjectileTrajectory"
java_import "com.rs2.model.Graphic"
java_import "com.rs2.model.content.skills.magic.Spell"

class Man < NpcCombatDef
    def attackScripts attacker, victim
        return [
        BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, 2, Weapon::FISTS)]
    end
end

class ChaosDruid < NpcCombatDef
    def attackScripts attacker, victim
        return [
        		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, 3, Weapon::FISTS),
              
        ];
    end
end

class MonkOfZam < NpcCombatDef
    def attackScripts attacker, victim
        return [
        		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, 8, Weapon::FISTS),
        ];
    end
end

class Walla < NpcCombatDef
    def attackScripts attacker, victim
        return [
                BasicAttack.magicAttack(attacker, victim, Spell::WATER_WAVE),
        ];
    end
end

class DarkWizard < NpcCombatDef
    def attackScripts attacker, victim
        return [
                BasicAttack.magicAttack(attacker, victim, Spell::WATER_STRIKE),
                BasicAttack.magicAttack(attacker, victim, Spell::CONFUSE)
        ];
    end
end

class InfernalMage < NpcCombatDef
    def attackScripts attacker, victim
        return [
                BasicAttack.magicAttack(attacker, victim, Spell::FIRE_BLAST),
        ];
    end
end

class ZamMage < NpcCombatDef
    def attackScripts attacker, victim
        return [
                BasicAttack.magicAttack(attacker, victim, Spell::FLAMES_OF_ZAMORAK),
        ];
    end
end

class ClueZamMage < NpcCombatDef
    def attackScripts attacker, victim
        return [
                BasicAttack.magicAttack(attacker, victim, Spell::FLAMES_OF_ZAMORAK),
        ];
    end
end

class GuthMage < NpcCombatDef
    def attackScripts attacker, victim
        return [
                BasicAttack.magicAttack(attacker, victim, Spell::CLAWS_OF_GUTHIX),
        ];
    end
end

class SaraMage < NpcCombatDef
    def attackScripts attacker, victim
        return [
                BasicAttack.magicAttack(attacker, victim, Spell::SARADOMIN_STRIKE),
        ];
    end
end

class ClueSaraMage < NpcCombatDef
    def attackScripts attacker, victim
        return [
                BasicAttack.magicAttack(attacker, victim, Spell::SARADOMIN_STRIKE),
        ];
    end
end
class TokXil < NpcCombatDef
    def attackScripts attacker, victim
        return [
                BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 4, 2628),
                BasicAttack.projectileAttack(attacker, victim, AttackType::RANGED, AttackStyle::Mode::LONGRANGE, 17, 4, 2633, Graphic.new(-1, 0), Graphic.new(-1, 0), 443, ProjectileTrajectory.ARROW)
        ];
    end
end

class MejKot < NpcCombatDef
    def attackScripts attacker, victim
        return [
                BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 28, 5, 2637)
        ];
    end
end

class Dagas < NpcCombatDef
    def attackScripts attacker, victim
        return [
        BasicAttack.projectileAttack(attacker, victim, AttackType::RANGED, AttackStyle::Mode::LONGRANGE, 8, 2, 1341, Graphic.new(-1, 0), Graphic.new(328, 0), 443, ProjectileTrajectory.ARROW),
        
        ];
    end
end

class Dagasupreme < NpcCombatDef
    def attackScripts attacker, victim
        return [
        BasicAttack.projectileAttack(attacker, victim, AttackType::RANGED, AttackStyle::Mode::LONGRANGE, 30, 2, 2855, Graphic.new(-1, 0), Graphic.new(328, 0), 443, ProjectileTrajectory.ARROW),
        
        ];
    end
end

class Dagaprime < NpcCombatDef
    def attackScripts attacker, victim
        return [
			BasicAttack.magicAttack(attacker, victim, Spell::WATER_WAVE),        
        ];
    end
end

class KetZek < NpcCombatDef
    def attackScripts attacker, victim
        return [
                BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 54, 5, 2644),
                BasicAttack.projectileAttack(attacker, victim, AttackType::MAGIC, AttackStyle::Mode::MAGIC, 49, 6, 2647, Graphic.new(-1, 0), Graphic.new(446, 0), 445, ProjectileTrajectory.SPELL)
        ];
    end
end

class Jad < NpcCombatDef
    def attackScripts attacker, victim
        return [
                BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 1, 8, 2655),
                BasicAttack.projectileAttack(attacker, victim, AttackType::RANGED, AttackStyle::Mode::LONGRANGE, 1, 8, 2652, Graphic.new(-1, 0), Graphic.new(451, 0), 411, ProjectileTrajectory.JAD_RANGE),
                BasicAttack.projectileAttack(attacker, victim, AttackType::MAGIC, AttackStyle::Mode::MAGIC, 1, 8, 2656, Graphic.new(447, 250), Graphic.new(157, 0), 448, ProjectileTrajectory.JAD_SPELL)
        ];
    end
end


class YtHurkot < NpcCombatDef
    def attackScripts attacker, victim
        return [
                BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 8, 2637),

        ];
    end
end

class Karil < NpcCombatDef
    def attackScripts attacker, victim
        return [
        BasicAttack.projectileAttack(attacker, victim, AttackType::RANGED, AttackStyle::Mode::LONGRANGE, 20, 4, 2075, Graphic.new(-1, 0), Graphic.new(-1, 0), 28, ProjectileTrajectory.ARROW),

        ];
    end
end

class IceQueen < NpcCombatDef
    def attackScripts attacker, victim
        return [
        
		BasicAttack.magicAttack(attacker, victim, Spell::ICE_BARRAGE),
		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 8, 91),
		BasicAttack.magicAttack(attacker, victim, Spell::ICE_BLITZ),
		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 8, 91),
		BasicAttack.magicAttack(attacker, victim, Spell::ICE_BURST),
		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 8, 91),
		
        ];
    end
end

class Ahrims < NpcCombatDef
    def attackScripts attacker, victim
        return [
                BasicAttack.magicAttack(attacker, victim, Spell::FIRE_WAVE),
                BasicAttack.magicAttack(attacker, victim, Spell::CONFUSE),
                BasicAttack.magicAttack(attacker, victim, Spell::CURSE)
        ];
    end
end

class ColoredDragon < NpcCombatDef
    def attackScripts attacker, victim
        return [
        		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 8, 91),
        		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 8, 91),
                BasicAttack.projectileAttack(attacker, victim, AttackType::MAGIC, AttackStyle::Mode::DRAGONFIRE, 1, 8, 81, Graphic.new(1, 0), Graphic.new(-1, 0), 1, ProjectileTrajectory.ARROW),

        ];
    end
end

class MetalDragon < NpcCombatDef
    def attackScripts attacker, victim
        return [
        		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 8, 91),
        		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 8, 91),
                BasicAttack.projectileAttack(attacker, victim, AttackType::MAGIC, AttackStyle::Mode::DRAGONFIRE, 1, 8, 81, Graphic.new(1, 0), Graphic.new(-1, 0), 1, ProjectileTrajectory.ARROW),
        		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 8, 91),
        		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 8, 91),
                BasicAttack.projectileAttack(attacker, victim, AttackType::MAGIC, AttackStyle::Mode::DRAGONFIRE_FAR, 1, 8, 81, Graphic.new(1, 0), Graphic.new(-1, 0), 1, ProjectileTrajectory.ARROW)

        ];
    end
end

class KBD < NpcCombatDef
    def attackScripts attacker, victim
        return [
        		BasicAttack.projectileAttack(attacker, victim, AttackType::MAGIC, AttackStyle::Mode::DRAGONFIRE, 1, 8, 81, Graphic.new(1, 0), Graphic.new(-1, 0), 1, ProjectileTrajectory.ARROW),
        		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 8, 91),
        		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 8, 91),
                BasicAttack.projectileAttack(attacker, victim, AttackType::MAGIC, AttackStyle::Mode::MAGIC, 20, 8, 81, Graphic.new(-1, 0), Graphic.new(131, 0), 393, ProjectileTrajectory.SPELL),
                BasicAttack.projectileAttack(attacker, victim, AttackType::MAGIC, AttackStyle::Mode::MAGIC, 20, 8, 81, Graphic.new(-1, 0), Graphic.new(140, 0), 394, ProjectileTrajectory.SPELL),
                BasicAttack.projectileAttack(attacker, victim, AttackType::MAGIC, AttackStyle::Mode::MAGIC, 20, 8, 81, Graphic.new(-1, 0), Graphic.new(134, 0), 395, ProjectileTrajectory.SPELL),
                BasicAttack.projectileAttack(attacker, victim, AttackType::MAGIC, AttackStyle::Mode::MAGIC, 20, 8, 81, Graphic.new(-1, 0), Graphic.new(137, 0), 396, ProjectileTrajectory.SPELL),
        		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 8, 91),
        		BasicAttack.meleeAttack(attacker, victim, AttackStyle::Mode::MELEE_ACCURATE, AttackStyle::Bonus::SLASH, 14, 8, 91)

        ];
    end
end

NpcCombatDef.add([2028], Karil.new.bonusDef(50, 50, 50, 50, 150))
NpcCombatDef.add([2025], Ahrims.new.bonusDef(50, 50, 50, 100, 50))
NpcCombatDef.add([2746], YtHurkot.new.bonusDef(100, 100, 100, 100, 60))
NpcCombatDef.add([2631], TokXil.new.bonusDef(60, 60, 60, 60, 30))
NpcCombatDef.add([2741], MejKot.new.bonusDef(100, 100, 100, 100, 60))
NpcCombatDef.add([2743], KetZek.new.bonusDef(130, 130, 130, 130, 70))
NpcCombatDef.add([2745], Jad.new.bonusDef(2000, 2000, 2000, 2000, 1700))
NpcCombatDef.add([1, 2, 3, 4], Man.new.respawnSeconds(10))
NpcCombatDef.add([174], DarkWizard.new.respawnSeconds(10))
NpcCombatDef.add([2457,2884], Walla.new.respawnSeconds(20))
NpcCombatDef.add([2882], Dagaprime.new.respawnSeconds(60))
NpcCombatDef.add([2881], Dagasupreme.new.respawnSeconds(60))
NpcCombatDef.add([1338,1339,1340,1341,1342,1343,1344,1345,1346,1347,2455,2456], Dagas.new.respawnSeconds(20))
NpcCombatDef.add([912], ZamMage.new.respawnSeconds(20))
NpcCombatDef.add([913], SaraMage.new.respawnSeconds(20))
NpcCombatDef.add([914], GuthMage.new.respawnSeconds(20))
NpcCombatDef.add([795], IceQueen.new.respawnSeconds(60))
NpcCombatDef.add([181], ChaosDruid.new.respawnSeconds(20))
NpcCombatDef.add([188,189], MonkOfZam.new.respawnSeconds(20))
NpcCombatDef.add([1643,1644,1645,1646,1647], InfernalMage.new.respawnSeconds(10))
NpcCombatDef.add([1007], ClueZamMage.new.bonusDef(100, 100, 100, 100, 100))
NpcCombatDef.add([1264], ClueSaraMage.new.bonusDef(100, 100, 100, 100, 100))
NpcCombatDef.add([941,53,54,55,742], ColoredDragon.new.bonusDef(70, 70, 70, 100, 70))
NpcCombatDef.add([1590,1591,1592], MetalDragon.new.bonusDef(100, 100, 100, 50, 50))
NpcCombatDef.add([50], KBD.new.bonusDef(500, 500, 500, 500, 1100))