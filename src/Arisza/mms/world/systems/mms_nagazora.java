package Arisza.mms.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.PlanetAPI;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.campaign.SectorEntityToken;
import com.fs.starfarer.api.campaign.StarSystemAPI;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.impl.campaign.ids.*;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.StarAge;
import com.fs.starfarer.api.impl.campaign.procgen.StarSystemGenerator;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;


public class mms_nagazora {
    public void generate(SectorAPI sector) {


        float planet1Dist= 4000f; //this is the distance the planet is from the star

                StarSystemAPI system = sector.createStarSystem("Nagazora");
            system.getLocation().set(-27500, 16500); //position of the system on the map

        system.setBackgroundTextureFilename("graphics/backgrounds/background5.jpg");

        // create the star and generate the hyperspace anchor for this system
        PlanetAPI nagazora_star = system.initStar("nagazora", // unique id for this star
                "star_blue_giant", // id in planets.json
                        750f,		// radius (in pixels at default zoom)
                        750);
        system.setLightColor(new Color(166, 241, 255, 255)); // light color in entire system, affects all entities
        nagazora_star.getSpec().setGlowTexture(Global.getSettings().getSpriteName("", "aurorae"));
        nagazora_star.getSpec().setGlowColor(new Color(60, 255, 232, 218));
        nagazora_star.getSpec().setUseReverseLightForGlow(true);

        SectorEntityToken gate = system.addCustomEntity("nagazora_gate", // unique id
                "Nagazora Gate", // name - if null, defaultName from custom_entities.json will be used
                "inactive_gate", // type of object, defined in custom_entities.json
                "MMS_XVI"); // faction
        gate.setCircularOrbit(system.getEntityById("nagazora"), 160, 9000, 350);

        SectorEntityToken gatehauler = system.addCustomEntity("ancient_gate_hauler", // unique id
                "Deralict_Gate_Hauler", // name - if null, defaultName from custom_entities.json will be used
                "derelict_gatehauler", // type of object, defined in custom_entities.json
                "MMS_XVI"); // faction
        gatehauler.setCircularOrbit(system.getEntityById("nagazora_gate"), 160, 2000, 250);

        SectorEntityToken relay = system.addCustomEntity("nagazora_relay", // unique id
                "Nagazora Relay", // name - if null, defaultName from custom_entities.json will be used
                "comm_relay", // type of object, defined in custom_entities.json
                "MMS_XVI"); // faction
        relay.setCircularOrbitPointingDown( system.getEntityById("nagazora"), 320, 5750, 220);


        PlanetAPI sennaya_ix = system.addPlanet("sennaya_IX",

                nagazora_star, //what it's orbiting
                "Sennaya-IX", //name
                "mms_sennaya", //the planet type (look in planets.json for more)
                900, //-
                130f, //radius
                12000, //distance from star
                700f); //how many days to orbit
        sennaya_ix.setFaction("MMS_XVI");
        sennaya_ix.setCustomDescriptionId("planet_sennaya"); //for custom descriptions

        PlanetAPI azuren_v = system.addPlanet("azuren_V",

                nagazora_star, //what it's orbiting
                "Azuren V", //name
                "mms_azuren", //the planet type (look in planets.json for more)
                170, //-
                120f, //radius
                5000, //distance from star
                500f); //how many days to orbit
        azuren_v.setFaction("MMS_XVI");
        azuren_v.setCustomDescriptionId("planet_azuren"); //for custom descriptions

        PlanetAPI kasumi = system.addPlanet("kasumi",

                nagazora_star, //what it's orbiting
                "Kasumi III", //name
                "water", //the planet type (look in planets.json for more)
                20, //-
                220f, //radius
                3900, //distance from star
                400f); //how many days to orbit
        kasumi.setFaction("MMS_XVI");
        kasumi.setCustomDescriptionId("planet_kasumi"); //for custom descriptions

        SectorEntityToken XVIstation = system.addCustomEntity(
                "Tower_of_Transcendence",
                "Tower of Transcendence",
                "MMS_XVIstation",
                "MMS_XVI"
               );
        XVIstation.setCircularOrbitPointingDown(system.getEntityById("nagazora"), 45 + 180, 3700, 200);
        XVIstation.setCustomDescriptionId("base_XVIstation");

        PlanetAPI malkuth = system.addPlanet("malkuth",

                azuren_v, //what it's orbiting
                "Malkuth", //name
                "barren-bombarded", //the planet type (look in planets.json for more)
                100, //-
                50f, //radius
                600, //distance from star
                100f); //how many days to orbit
        malkuth.setFaction("MMS_XVI");
        malkuth.setCustomDescriptionId(""); //for custom descriptions
        malkuth.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "aurorae"));
        malkuth.getSpec().setGlowColor(new Color(60, 86, 255, 218));
        malkuth.getSpec().setUseReverseLightForGlow(true);

        system.addRingBand(nagazora_star, "misc", "rings_ice0", 256f, 4, new Color(108, 248, 215, 255), 230f, 3350, 300, Terrain.RING, "Stellar Energy Band");
        system.addRingBand(nagazora_star, "misc", "rings_ice0", 256f, 3, new Color(21, 178, 242,255), 230f, 3250, 100, Terrain.RING, "Stellar Energy Band2");
        system.addRingBand(nagazora_star, "misc", "rings_ice0", 256f, 2, new Color(21, 178, 242,255), 330f, 2200, 100, Terrain.RING, "Stellar Energy Band2");
        system.addRingBand(nagazora_star, "misc", "rings_ice0", 256f, 3, new Color(21, 178, 242,255), 230f, 3250, 100, Terrain.RING, "Stellar Energy Band2");
        system.addRingBand(nagazora_star, "misc", "rings_ice0", 256f, 2, new Color(21, 178, 242,255), 230f, 3290, 100, Terrain.RING, "Stellar Energy Band2");
        system.addRingBand(nagazora_star, "misc", "rings_ice0", 256f, 1, new Color(21, 178, 242,255), 230f, 3590, 100, Terrain.RING, "Stellar Energy Band2");
        system.addRingBand(nagazora_star, "misc", "rings_dust0", 256f, 3, new Color(21, 178, 242,255), 230f, 7350, 100, Terrain.RING, "Stellar Energy Band2");
        system.addRingBand(nagazora_star, "misc", "rings_dust0", 256f, 2, new Color(21, 178, 242,255), 230f, 6250, 100, Terrain.RING, "Stellar Energy Band2");
        system.addRingBand(nagazora_star, "misc", "rings_dust0", 256f, 1, new Color(21, 178, 242,255), 230f, 7100, 100, Terrain.RING, "Stellar Energy Band2");
        system.addRingBand(nagazora_star, "misc", "rings_dust0", 256f, 3, new Color(21, 178, 242,255), 230f, 6550, 100, Terrain.RING, "Stellar Energy Band2");

        system.addRingBand(kasumi, "misc", "rings_ice0", 256f, 2, new Color(135, 241, 255,255), 330f, 500, 300, Terrain.RING, "Kasumi Star Ring");
        system.addRingBand(kasumi, "misc", "rings_dust0", 256f, 2, new Color(166, 241, 255,255), 330f, 570, 330, Terrain.RING, "Kasumi Star Ring");
        system.addRingBand(kasumi, "misc", "rings_ice0", 256f, 1, new Color(21, 178, 242, 166), 330f, 520, 350, Terrain.RING, "Kasumi Star Ring");

        system.addRadioChatter(azuren_v,200);
        system.addRadioChatter(XVIstation,200);

        system.addRadioChatter(sennaya_ix,200);
        system.autogenerateHyperspaceJumpPoints(true, true, true); //generates jump points

        HyperspaceTerrainPlugin plugin = (HyperspaceTerrainPlugin) Misc.getHyperspaceTerrain().getPlugin(); //these lines clear the hyperspace clouds around the system
        NebulaEditor editor = new NebulaEditor(plugin);
        StarSystemGenerator.addSystemwideNebula(system, StarAge.YOUNG);
        float minRadius = plugin.getTileSize() * 2f;

        float radius = system.getMaxRadiusInHyperspace();
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f);
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f, 0.25f);



    }
}

