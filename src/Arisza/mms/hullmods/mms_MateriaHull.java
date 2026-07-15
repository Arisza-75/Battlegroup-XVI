package Arisza.mms.hullmods;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;

public class mms_MateriaHull extends BaseHullMod {

	public static float MANEUVER_PENALTY = 10f;
	
	public static float SMOD_MANEUVER_PENALTY = 25f;
	

	private static Map mag = new HashMap();
	static {
//		mag.put(HullSize.FRIGATE, 100f);
//		mag.put(HullSize.DESTROYER, 200f);
//		mag.put(HullSize.CRUISER, 300f);
//		mag.put(HullSize.CAPITAL_SHIP, 400f);
		mag.put(HullSize.FRIGATE, -10f);
		mag.put(HullSize.DESTROYER, -15f);
		mag.put(HullSize.CRUISER, -25f);
		mag.put(HullSize.CAPITAL_SHIP, -50f);
	}


 public float DMG_REDUCTION = 25f ;
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getEnergyDamageTakenMult().modifyPercent(id, (Float) mag.get(hullSize));
		stats.getArmorBonus().modifyPercent(id, DMG_REDUCTION);
	}

	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + ((Float) mag.get(HullSize.FRIGATE)).intValue() + "%";
		if (index == 1) return "" + ((Float) mag.get(HullSize.DESTROYER)).intValue() + "%";
		if (index == 2) return "" + ((Float) mag.get(HullSize.CRUISER)).intValue() + "%";
		if (index == 3) return "" + ((Float) mag.get(HullSize.CAPITAL_SHIP)).intValue() + "%";
		return null;
	}
	

	
	@Override
	public boolean isSModEffectAPenalty() {
		return true;
	}
	
	

	public boolean isApplicableToShip(ShipAPI ship) {
		//if (ship.getMutableStats().getCargoMod().computeEffective(ship.getHullSpec().getCargo()) < 70) return false;

		return true;
	}
	@Override
	public Color getBorderColor() {
		return new Color(86,214,239, 0);
	}

}
