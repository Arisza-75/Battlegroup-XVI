package Arisza.mms.hullmods;

import java.util.HashMap;
import java.util.Map;
import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import com.fs.starfarer.api.combat.WeaponAPI;

import java.awt.Color;

public class mms_xvi_FCS extends BaseHullMod {
    public static float AMMO_BONUS = 100f;
    public static float FLUX_BONUS = 200f;
    private static final Map speed = new HashMap();
    static {
        speed.put(HullSize.FIGHTER, 75f);
        speed.put(HullSize.FRIGATE, 75f);
        speed.put(HullSize.DESTROYER, 30f);
        speed.put(HullSize.CRUISER, 60f);
        speed.put(HullSize.CAPITAL_SHIP, 55f);
        speed.put(HullSize.DEFAULT, 55f);
    }
	//This above is kinda important, you have to define HullSize.FIGHTER and HullSize.DEFAULT because for some reason people are spawning old precursor fighters and the mod is randomly summoning these cringe gargoyles and CTDing the game. If you don't want them to get the bonus, I would just set it to 0f or something...
    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {

        stats.getBallisticAmmoBonus().modifyPercent(id, AMMO_BONUS);
        stats.getEnergyAmmoBonus().modifyPercent(id, AMMO_BONUS);

    }
	    //these are cool colors below introduced in 0.95a, to match with your tech type and stuff. Just nice to have!

    @Override
    public Color getBorderColor() {
        return new Color(86,214,239, 255);
    }
}
