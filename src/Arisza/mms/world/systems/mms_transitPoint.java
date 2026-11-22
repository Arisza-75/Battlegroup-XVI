package Arisza.mms.world.systems;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.impl.campaign.ids.*;
import com.fs.starfarer.api.impl.campaign.procgen.NebulaEditor;
import com.fs.starfarer.api.impl.campaign.procgen.StarAge;
import com.fs.starfarer.api.impl.campaign.procgen.StarSystemGenerator;
import com.fs.starfarer.api.impl.campaign.terrain.HyperspaceTerrainPlugin;
import com.fs.starfarer.api.util.Misc;

import java.awt.*;



public class mms_transitPoint {
    public void generate(SectorAPI sector) {


        float planet1Dist= 4000f; //this is the distance the planet is from the star

                StarSystemAPI system = sector.createStarSystem("Transit Point Delta");

        //system.getLocation().set(-19500, 15300); //position of the system on the map
        system.setBackgroundTextureFilename("graphics/backgrounds/mms_transitPoint.jpg");

        // create the star and generate the hyperspace anchor for this system
        PlanetAPI TP_star = system.initStar("TP1_Star", // unique id for this star
                "star_orange", // id in planets.json
                        450f,		// radius (in pixels at default zoom)
                        450);
        system.setLightColor(new Color(255, 215, 200, 255)); // light color in entire system, affects all entities
        TP_star.getSpec().setGlowTexture(Global.getSettings().getSpriteName("", "aurorae"));
        TP_star.getSpec().setGlowColor(new Color(255, 138, 70, 218));
        TP_star.getSpec().setUseReverseLightForGlow(true);

        SectorEntityToken gate = system.addCustomEntity("TP1_gate", // unique id
                "Persean Transfer Gate Delta", // name - if null, defaultName from custom_entities.json will be used
                "inactive_gate", // type of object, defined in custom_entities.json
                "neutral"); // faction
        gate.setCircularOrbit(system.getEntityById("TP1_Star"), 160, 1200, 350);


        SectorEntityToken relay = system.addCustomEntity("TP1_relay", // unique id
                "Transit Point Relay", // name - if nul-l, defaultName from custom_entities.json will be used
                "comm_relay", // type of object, defined in custom_entities.json
                "MMS_XVI"); // faction
        relay.setCircularOrbitPointingDown( system.getEntityById("TP1_Star"), 320, 5750, 220);


        PlanetAPI aroriya = system.addPlanet("planet_aroriya",

                TP_star, //what it's orbiting
                "Aroriya", //name
                "tundra", //the planet type (look in planets.json for more)
                170, //-
                120f, //radius
                8000, //distance from star
                500f); //how many days to orbit
        aroriya.setFaction("independent");
        aroriya.setCustomDescriptionId("mms_planet_aroriya"); //for custom descriptions




        SectorEntityToken AroriyaOrbitalStation = system.addCustomEntity(
                "mms_aroriyaOrbitalStation",
                "Aroriya Orbital Station",
                "station_side07", //id in custom_entities.json
                "independent");
        AroriyaOrbitalStation.setCircularOrbitPointingDown(system.getEntityById("planet_aroriya"), 45 + 180, 150, 200);
        AroriyaOrbitalStation.setCustomDescriptionId("mms_base_aroriya");


        PlanetAPI ignatia = system.addPlanet("mms_ignatia",

                TP_star, //what it's orbiting
                "Ignatia", //name
                "lava", //the planet type (look in planets.json for more)
                300, //-
                520f, //radius
                5900, //distance from star
                400f); //how many days to orbit
        ignatia.setFaction("MMS_XVI");
        ignatia.setCustomDescriptionId("planet_ignatia"); //for custom descriptions


        PlanetAPI Maria = system.addPlanet("mms_babylon",

                ignatia, //what it's orbiting
                "Outpost Babylon", //name
                "rocky_metallic", //the planet type (look in planets.json for more)
                100, //-
                50f, //radius
                900, //distance from star
                70f); //how many days to orbit
        Maria.setFaction("MMS_XVI");
        Maria.setCustomDescriptionId("mms_planet_babylon"); //for custom descriptions
        Maria.getSpec().setGlowTexture(Global.getSettings().getSpriteName("hab_glows", "aurorae"));
        Maria.getSpec().setGlowColor(new Color(255, 162, 86, 255));


        JumpPointAPI jumpPoint = Global.getFactory().createJumpPoint("blockade_jump", "Blockade Point Alpha");
        jumpPoint.setCircularOrbit(system.getEntityById("TP1_Star"), 150+60, 7500, 200);
        jumpPoint.setStandardWormholeToHyperspaceVisual();
        system.addEntity(jumpPoint);

        SectorEntityToken HegStation = system.addCustomEntity(
                "mms_hegFoB",
                "Hegemony Forward Operations Base",
                "station_side02", //id in custom_entities.json
                "hegemony");
        HegStation.setCircularOrbitPointingDown(system.getEntityById("blockade_jump"), 45 + 180, 1000, 900);
        HegStation.setCustomDescriptionId("mms_base_HegFoB");


        SectorEntityToken HegStation2 = system.addCustomEntity(
                "mms_hegStation2",
                "Blockade Point Alpha",
                "station_side02", //id in custom_entities.json
                "hegemony");
        HegStation2.setCircularOrbitPointingDown(system.getEntityById("blockade_jump"), 45 + 180, 500, 200);
        HegStation2.setCustomDescriptionId("mms_base_HegPoint");



        system.addRingBand(TP_star, "misc", "rings_dust0", 256f, 1, new Color(255, 162, 86,255), 230f, 2100, 100, Terrain.RING, "Ring System");

        system.addRingBand(ignatia, "misc", "rings_asteroids0", 256f, 3, new Color(255, 255, 255,255), 330f, 1500, 100, Terrain.RING, "Ignatia Ring System");
        system.addRingBand(ignatia, "misc", "rings_asteroids0", 256f, 2, new Color(255, 255, 255,255), 330f, 1170, 70, Terrain.RING, "Ignatia Ring System");
        system.addRingBand(ignatia, "misc", "rings_dust0", 256f, 0, new Color(255, 255, 255, 255), 330f, 1350, 20, Terrain.RING, "Ignatia Ring System");
        system.addRingBand(ignatia, "misc", "rings_dust0", 256f, 1, new Color(255, 255, 255, 255), 330f, 830, 50, Terrain.RING, "Ignatia Ring System");
        system.addRingBand(ignatia, "misc", "rings_special0", 256f, 1, new Color(255, 50, 4, 255), 390f, 780, 60, Terrain.RING, "Ignatia Ring System");
        system.addRingBand(ignatia, "misc", "rings_asteroids0", 256f, 1, new Color(255, 50, 4, 255), 330f, 620, 70, Terrain.RING, "Ignatia Ring System");
        system.addRingBand(ignatia, "misc", "rings_special0", 256f, 0, new Color(255, 50, 4, 255), 330f, 550, 70, Terrain.RING, "Ignatia Ring System");

        system.addRadioChatter(ignatia,200);
        system.addRadioChatter(HegStation,200);

        system.addRadioChatter(AroriyaOrbitalStation,200);
        system.autogenerateHyperspaceJumpPoints(true, true, true); //generates jump points

        HyperspaceTerrainPlugin plugin = (HyperspaceTerrainPlugin) Misc.getHyperspaceTerrain().getPlugin(); //these lines clear the hyperspace clouds around the system
        NebulaEditor editor = new NebulaEditor(plugin);
        StarSystemGenerator.addSystemwideNebula(system, StarAge.OLD);
        float minRadius = plugin.getTileSize() * 2f;

        float radius = system.getMaxRadiusInHyperspace();
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f);
        editor.clearArc(system.getLocation().x, system.getLocation().y, 0, radius + minRadius, 0, 360f, 0.25f);



    }
}

