
package Arisza.mms.hullmods;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import org.lwjgl.util.vector.Vector2f;

public class droneEMP extends BaseHullMod {

    CombatEngineAPI engine;

    @Override
    public void advanceInCombat(ShipAPI ship, float amount) {


        super.advanceInCombat(ship, amount);
        this.engine = Global.getCombatEngine();

        if (engine==null) return;
        if (ship==null) return;
        //check to make sure ship is alive, and Combat not paused
        if (!ship.isAlive() || Global.getCombatEngine().isPaused()) {
            return;
        }

        if (ship.getFullTimeDeployed() > 60f) {
            Global.getCombatEngine().applyDamage(ship,
                    ship.getLocation(),
                    2000f,
                    DamageType.HIGH_EXPLOSIVE, //swap damage type, mostly because it maybe makes more sence, lol
                    0f,
                    true,
                    true,
                    ship);
        }
    }
}
