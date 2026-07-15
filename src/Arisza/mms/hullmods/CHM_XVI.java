package Arisza.mms.hullmods;

import java.util.HashMap;
import java.util.Map;
import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import java.awt.Color;

public class CHM_XVI extends BaseHullMod {
    private static final Map mag = new HashMap();
    static {
        mag.put(HullSize.FIGHTER, 0f);
        mag.put(HullSize.FRIGATE, 15f);
        mag.put(HullSize.DESTROYER, 20f);
        mag.put(HullSize.CRUISER, 35f);
        mag.put(HullSize.CAPITAL_SHIP, 40f);
        mag.put(HullSize.DEFAULT, 0f);
    }
	//This above is kinda important, you have to define HullSize.FIGHTER and HullSize.DEFAULT because for some reason people are spawning old precursor fighters and the mod is randomly summoning these cringe gargoyles and CTDing the game. If you don't want them to get the bonus, I would just set it to 0f or something...

    @Override
    public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
        stats.getBallisticWeaponRangeBonus().modifyPercent(id, (Float) mag.get(hullSize));
        stats.getEnergyWeaponRangeBonus().modifyPercent(id, (Float) mag.get(hullSize));
    }

    
    @Override
    public String getDescriptionParam(int index, HullSize hullSize) {
        if (index == 0) return "" + ((Float) mag.get(HullSize.FRIGATE)).intValue() + "%";
        if (index == 1) return "" + ((Float) mag.get(HullSize.DESTROYER)).intValue() + "%";
        if (index == 2) return "" + ((Float) mag.get(HullSize.CRUISER)).intValue() + "%";
        if (index == 3) return "" + ((Float) mag.get(HullSize.CAPITAL_SHIP)).intValue() + "%";
        return null;
    }

	//Oh these are cool colors below introduced in 0.95a, to match with your tech type and stuff. Just nice to have!

    @Override
    public Color getBorderColor() {
        return new Color(86,214,239, 0);
    }

    @Override
    public Color getNameColor() {
        return new Color(86,214,239, 255);
    }
}
