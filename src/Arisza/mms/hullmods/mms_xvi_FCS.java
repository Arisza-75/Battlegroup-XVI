package Arisza.mms.hullmods;

import java.util.HashMap;
import java.util.Map;
import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

import java.awt.Color;

public class mms_xvi_FCS extends BaseHullMod {
    private static final Map mag = new HashMap();
    static {
        mag.put(HullSize.FIGHTER, 55f);
        mag.put(HullSize.FRIGATE, 35f);
        mag.put(HullSize.DESTROYER, 45f);
        mag.put(HullSize.CRUISER, 55f);
        mag.put(HullSize.CAPITAL_SHIP, 60f);
        mag.put(HullSize.DEFAULT, 55f);
    }
    private static final Map refire = new HashMap();
    static {
        refire.put(HullSize.FIGHTER, 55f);
        refire.put(HullSize.FRIGATE, 20f);
        refire.put(HullSize.DESTROYER, 40f);
        refire.put(HullSize.CRUISER, 55f);
        refire.put(HullSize.CAPITAL_SHIP, 75f);
        refire.put(HullSize.DEFAULT, 55f);
    }
	//This above is kinda important, you have to define HullSize.FIGHTER and HullSize.DEFAULT because for some reason people are spawning old precursor fighters and the mod is randomly summoning these cringe gargoyles and CTDing the game. If you don't want them to get the bonus, I would just set it to 0f or something...


    @Override
    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getEnergyWeaponRangeBonus().modifyPercent(id, (Float) mag.get(hullSize));
        stats.getEnergyAmmoBonus().modifyPercent(id, (Float) refire.get(hullSize));
        stats.getEnergyAmmoRegenMult().modifyPercent(id, (Float) refire.get(hullSize));
        stats.getEnergyRoFMult().modifyPercent(id, (Float) refire.get(hullSize));
    }

    @Override
    public String getDescriptionParam(int index, HullSize hullSize) {
        if (index == 0) return "" + ((Float) mag.get(HullSize.FRIGATE)).intValue() + "%";
        if (index == 1) return "" + ((Float) mag.get(HullSize.DESTROYER)).intValue() + "%";
        if (index == 2) return "" + ((Float) mag.get(HullSize.CRUISER)).intValue() + "%";
        if (index == 3) return "" + ((Float) mag.get(HullSize.CAPITAL_SHIP)).intValue() + "%";
        return null;
    }

    public boolean isApplicableToShip(ShipAPI ship) {
        return !ship.getVariant().getHullMods().contains("dedicated_targeting_core");

    }

    public String getUnapplicableReason(ShipAPI ship) {
        if (ship.getVariant().getHullMods().contains("dedicated_targeting_core")) {
            return "Incompatible with Dedicated Targeting Core";
        }

        return null;
    }
	    //these are cool colors below introduced in 0.95a, to match with your tech type and stuff. Just nice to have!

    @Override
    public Color getBorderColor() {
        return new Color(86,214,239, 255);
    }
}
