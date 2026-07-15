package Arisza.mms;

import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.EveryFrameScript;
import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import exerelin.campaign.SectorManager;


import Arisza.mms.world.MMSGen;

import java.util.ArrayList;

public class MMSPlugin extends BaseModPlugin {
    public void onGameLoad(boolean newGame) {
        Global.getSector().addTransientScript(new EveryFrameScript() {
            int f = 0;

            @Override
            public void advance(float dt) {
                if (++f > 1) {
                    StarSystemAPI mms_nagazora = Global.getSector().getStarSystem("Nagazora");
                    for (SectorEntityToken entity : new ArrayList<>(mms_nagazora.getAllEntities())) {
                        if (entity.getOrbitFocus() instanceof PlanetAPI)
                            Global.getLogger(this.getClass()).info(String.valueOf(entity.getCustomEntityType() + " " + String.valueOf(entity.getClass())));
                        if ("orbital_junk".equals(entity.getCustomEntityType()) && entity.getOrbitFocus() instanceof PlanetAPI) {
                            mms_nagazora.removeEntity(entity);

                        }
                    }

                    Global.getSector().removeTransientScript(this);
                }
            }

            @Override
            public boolean isDone() {
                return f > 1;
            }

            @Override
            public boolean runWhilePaused() {
                return true;
            }
        });
    }

    @Override
    public void onNewGame() {


//        boolean haveNexerelin = Global.getSettings().getModManager().isModEnabled("nexerelin");
//
//        if (!haveNexerelin || SectorManager.getCorvusMode()) {
//            SectorAPI sector = Global.getSector();

        new MMSGen().generate(Global.getSector());

    }
}
