package Arisza.mms.combat;

import java.awt.Color;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.impl.combat.BaseShipSystemScript;
import org.lwjgl.util.vector.Vector2f;

import static com.fs.starfarer.api.impl.campaign.missions.academy.GAPZPostEncounters.id;

public class mms_flux_drain extends BaseShipSystemScript {
	ShipAPI ship;

	@Override
	public void apply(MutableShipStatsAPI stats, String id, State state, float effectLevel) {
		super.apply(stats, id, state, effectLevel);
	}

	public String modifyDamageTaken(CombatEntityAPI target, DamageAPI damage, Vector2f point, boolean shieldHit)
	{
		float multedDmg = damage.getDamage();
		damage.getModifier().modifyMult(id, 200f, "doesnt do much");
		ship.getFluxTracker().decreaseFlux(200f);
		Global.getCombatEngine().addFloatingText(point, "...",35f, Color.RED, target, 0f, 0f);

		return id;
	}
}
