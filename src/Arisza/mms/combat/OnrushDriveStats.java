package Arisza.mms.combat;

import java.awt.Color;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.CombatEngineLayers;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.graphics.SpriteAPI;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import com.fs.starfarer.api.plugins.ShipSystemStatsScript;
import com.fs.starfarer.api.util.IntervalUtil;
import org.lwjgl.util.vector.Vector2f;
import org.magiclib.util.MagicRender;

public class OnrushDriveStats extends BaseShipSystemScript {

	public static float SPEED_BONUS = 125f;
	public static float TURN_BONUS = 20f;
	
	//private Color color = new Color(89, 218, 255,255);
	private Color color = new Color(30, 235, 235,255);

	public static final Color JITTER_COLOR = new Color(54, 100, 249, 55);
	public static final Color AfterImageColor = new Color(11, 235, 255, 105);
	public static final Color JITTER_UNDER_COLOR = new Color(11, 235, 255, 155);



	
	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		ShipAPI ship = (ShipAPI) stats.getEntity();

		if (state == ShipSystemStatsScript.State.OUT) {
			stats.getMaxSpeed().unmodify(id); // to slow down ship to its regular top speed while powering drive down
			stats.getMaxTurnRate().unmodify(id);

		} else {
			stats.getMaxSpeed().modifyFlat(id, SPEED_BONUS);
			stats.getAcceleration().modifyPercent(id, SPEED_BONUS * 5f * effectLevel);
			stats.getDeceleration().modifyPercent(id, SPEED_BONUS * 5f * effectLevel);
			stats.getTurnAcceleration().modifyFlat(id, TURN_BONUS * effectLevel);
			stats.getTurnAcceleration().modifyPercent(id, TURN_BONUS * 5f * effectLevel);
			stats.getMaxTurnRate().modifyFlat(id, 15f);
			stats.getMaxTurnRate().modifyPercent(id, 100f);
			stats.getEnergyDamageTakenMult().modifyPercent(id, -10f);
			stats.getHighExplosiveDamageTakenMult().modifyPercent(id, 25f);
			stats.getBeamDamageTakenMult().modifyPercent(id, -25f);
			createAfterimages((ShipAPI) stats.getEntity(), effectLevel);
		}
		
		if (stats.getEntity() instanceof ShipAPI) {
			ship.getEngineController().fadeToOtherColor(this, color, new Color(0,0,0,0), effectLevel, 0.67f);
			ship.getEngineController().extendFlame(this, 2f * effectLevel, 0f * effectLevel, 0f * effectLevel);
		}
	}

	private final IntervalUtil interval= new IntervalUtil(0.2f, 0.2f); //it can get used to get random intervals picked between first and second number

	void createAfterimages(ShipAPI ship, float effectLevel){ //it get called in efs
		float amount = Global.getCombatEngine().getElapsedInLastFrame();

		interval.advance(amount);
		if (interval.intervalElapsed()) {

			SpriteAPI ship_sprite = Global.getSettings().getSprite(ship.getHullSpec().getSpriteName());
			Vector2f speed = new Vector2f(0, 0);
			Vector2f size = new Vector2f(ship.getSpriteAPI().getWidth(), ship.getSpriteAPI().getHeight());
			Vector2f pos = new Vector2f(ship.getLocation());
			float angle = ship.getFacing() - 90f;

			MagicRender.battlespace(ship_sprite, pos, speed, size, null, angle, 0, AfterImageColor, true, 0, 0, 0.0f, 0, 0, 0.2f, 0.6f, 0.4f, CombatEngineLayers.BELOW_SHIPS_LAYER);

		}
	}


	public void applyGfx(MutableShipStatsAPI stats, State state, float effectLevel, float amount) {





//		ShipAPI ship = null;
//		if (stats.getEntity() instanceof ShipAPI) {
//			ship = (ShipAPI) stats.getEntity();
//		} else {
//			return;
//		}
//
//		// temporal shell copypasta
//		float jitterLevel = effectLevel;
//		float jitterRangeBonus = 100;
//		float maxRangeBonus = 10f;
//		if (state == State.IN) {
//			jitterLevel = effectLevel / (1f / ship.getSystem().getChargeUpDur());
//			if (jitterLevel > 1) {
//				jitterLevel = 1f;
//			}
//			jitterRangeBonus = jitterLevel * maxRangeBonus;
//		} else if (state == State.ACTIVE) {
//			jitterLevel = 1f;
//			jitterRangeBonus = maxRangeBonus;
//		} else if (state == State.OUT) {
//			jitterRangeBonus = jitterLevel * maxRangeBonus;
//		}
//		jitterLevel = (float) Math.sqrt(jitterLevel);

//		ship.setJitter(this, JITTER_COLOR, jitterLevel, 3, 0, 0 + jitterRangeBonus);
//		ship.setJitterUnder(this, JITTER_UNDER_COLOR, jitterLevel, 25, 0f, 20f + jitterRangeBonus);
	}


	public void unapply(MutableShipStatsAPI stats, String id) {
		stats.getMaxSpeed().unmodify(id);
		stats.getMaxTurnRate().unmodify(id);
		stats.getTurnAcceleration().unmodify(id);
		stats.getAcceleration().unmodify(id);
		stats.getEnergyDamageTakenMult().unmodify(id);
		stats.getHighExplosiveDamageTakenMult().unmodify(id);
		stats.getDeceleration().unmodify(id);

	}
	
	public StatusData getStatusData(int index, State state, float effectLevel) {
		if (index == 0) {
			return new StatusData("+" + (int)TURN_BONUS + " improved maneuverability", false);
		} else if (index == 1) {
			return new StatusData("+" + (int)SPEED_BONUS + " top speed", false);
		}
		return null;
	}
}
