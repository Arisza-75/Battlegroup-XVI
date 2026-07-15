package Arisza.mms.hullmods;


import java.awt.*;

import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.loading.DamagingExplosionSpec;
import org.lwjgl.util.vector.Vector2f;

public class empexplosion extends BaseHullMod {

    public static final float RADIUS_MULT = 0.25f;
    public static final float DAMAGE_MULT = 0.1f;
    public ShipAPI ship;
    CombatEngineAPI engine;


    float EMPDMG = 200;

    public  DamagingExplosionSpec spec = new DamagingExplosionSpec(
            10,
            600,
            400,
            0,
            0,
            CollisionClass.HITS_SHIPS_ONLY_NO_FF,
            CollisionClass.PROJECTILE_FF,
            10,
            20,
            20,
            20,
            new Color(0x6CF8D7),
            new Color(0x6CF8D7)

    );

    public void applyEffectsBeforeShipCreation(ShipAPI.HullSize hullSize, MutableShipStatsAPI stats, String id) {
        boolean isDead = ship.getHitpoints() == 0;
        if (ship.isFighter() && isDead) {
            engine.spawnDamagingExplosion(spec, ship, new Vector2f(0, 0));
        }
        else {

        }
    }

    public String getDescriptionParam(int index, ShipAPI.HullSize hullSize) {
        return null;
    }


}
