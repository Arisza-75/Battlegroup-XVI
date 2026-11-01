
package Arisza.mms.hullmods;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import org.lazywizard.lazylib.MathUtils;
import org.magiclib.util.MagicLensFlare;

import java.awt.*;

public class sparkeffect extends BaseHullMod {
    CombatEngineAPI engine = (CombatEngineAPI) Global.getCombatEngine();

    public void advanceInCombat (ShipAPI ship, float amount) {
if (ship == null) return;
final CombatEngineAPI engine;
if ((engine = Global.getCombatEngine()) == null) return;

        MagicLensFlare.createSharpFlare(
                engine,
                ship,
                MathUtils.getRandomPointInCircle(
                        ship.getLocation(),
                        ship.getCollisionRadius()
                ),
                5,
                250,
                0,
                new Color(50,175,255,255),
                new Color(200,200,255,255)
        );
    }
}
