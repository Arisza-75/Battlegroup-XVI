package Arisza.mms.combat.weaponeffects;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.combat.*;
import com.fs.starfarer.api.combat.listeners.ApplyDamageResultAPI;
import com.fs.starfarer.api.loading.DamagingExplosionSpec;
import org.lazywizard.lazylib.MathUtils;
import org.lwjgl.util.vector.Vector2f;
import org.magiclib.util.MagicRender;

import java.awt.*;


public class explosionImpact implements OnHitEffectPlugin {

	public void onHit(DamagingProjectileAPI projectile, CombatEntityAPI target, Vector2f point, boolean shieldHit, ApplyDamageResultAPI damageResult, CombatEngineAPI engine) {


			MagicRender.battlespace(
					Global.getSettings().getSprite("graphics/fx/explosion_wave.png"),
					point,
					new Vector2f(30, 30),
					new Vector2f(500, 500),
					new Vector2f(1000, 1000),
					MathUtils.getRandomNumberInRange(0, 360),
					50,
					Color.WHITE,
					true,
					0.1f,
					5f,
					5f
			);

		}

		public DamagingExplosionSpec createExplosionSpec() {
			float damage = 5000f;
			float emp = 2000f;

			DamagingExplosionSpec spec = new DamagingExplosionSpec(
					5f, // duration
					145f, // radius
					125f, // coreRadius
					damage, // maxDamage
					damage / 2f, // minDamage

					CollisionClass.PROJECTILE_FF, // collisionClass
					CollisionClass.PROJECTILE_FIGHTER, // collisionClassByFighter
					3f, // particleSizeMin
					3f, // particleSizeRange
					0.5f, // particleDuration
					150, // particleCount
					new Color(69,225,255,0), // particleColor
					new Color(0,0,155,0)  // explosionColor
			);

			spec.setDamageType(DamageType.HIGH_EXPLOSIVE);
			spec.setUseDetailedExplosion(true);
			spec.setSoundSetId("explosion_guardian");
			return spec;
		}


	//engine.spawnProjectile(null, null, "plasma", point, 0, new Vector2f(0, 0));
		}







