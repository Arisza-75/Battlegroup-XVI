package Arisza.mms.world;

import Arisza.mms.world.systems.mms_alaska;

import Arisza.mms.world.systems.mms_nagazora;
import com.fs.starfarer.api.campaign.FactionAPI;
import com.fs.starfarer.api.campaign.RepLevel;
import com.fs.starfarer.api.campaign.SectorAPI;
import com.fs.starfarer.api.impl.campaign.ids.Factions;

public class  MMSGen {
    public static void initFactionRelationships(SectorAPI sector) {
        FactionAPI player = sector.getFaction(Factions.PLAYER);
        FactionAPI hegemony = sector.getFaction(Factions.HEGEMONY);
        FactionAPI tritachyon = sector.getFaction(Factions.TRITACHYON);
        FactionAPI pirates = sector.getFaction(Factions.PIRATES);
        FactionAPI independent = sector.getFaction(Factions.INDEPENDENT);
        FactionAPI church = sector.getFaction(Factions.LUDDIC_CHURCH);
        FactionAPI path = sector.getFaction(Factions.LUDDIC_PATH);
        FactionAPI diktat = sector.getFaction(Factions.DIKTAT);
        FactionAPI kol = sector.getFaction(Factions.KOL);
        FactionAPI persean = sector.getFaction(Factions.PERSEAN);
        FactionAPI guard = sector.getFaction(Factions.LIONS_GUARD);
        FactionAPI remnant = sector.getFaction(Factions.REMNANTS);
        FactionAPI derelict = sector.getFaction(Factions.DERELICT);
        FactionAPI udf = sector.getFaction("MMS_AL_UDF");
        FactionAPI fleetxvi = sector.getFaction("MMS_XVI");
        FactionAPI voidstar = sector.getFaction("VOIDSTAR");

        udf.setRelationship(path.getId(), RepLevel.HOSTILE);
        udf.setRelationship(hegemony.getId(), RepLevel.FAVORABLE);
        udf.setRelationship(pirates.getId(), RepLevel.HOSTILE);
        udf.setRelationship(tritachyon.getId(), RepLevel.SUSPICIOUS);
        udf.setRelationship(church.getId(), RepLevel.HOSTILE);
        udf.setRelationship(kol.getId(), RepLevel.HOSTILE);
        udf.setRelationship(persean.getId(), RepLevel.SUSPICIOUS);
        udf.setRelationship(independent.getId(), RepLevel.COOPERATIVE);

        fleetxvi.setRelationship(path.getId(), RepLevel.HOSTILE);
        fleetxvi.setRelationship(hegemony.getId(), RepLevel.NEUTRAL);
        fleetxvi.setRelationship(pirates.getId(), RepLevel.HOSTILE);
        fleetxvi.setRelationship(tritachyon.getId(), RepLevel.SUSPICIOUS);
        fleetxvi.setRelationship(church.getId(), RepLevel.INHOSPITABLE);
        fleetxvi.setRelationship(kol.getId(), RepLevel.HOSTILE);
        fleetxvi.setRelationship(persean.getId(), RepLevel.INHOSPITABLE);
        fleetxvi.setRelationship(independent.getId(), RepLevel.COOPERATIVE);
        fleetxvi.setRelationship("MMS_AL_UDF",RepLevel.COOPERATIVE);


        fleetxvi.setRelationship(path.getId(), RepLevel.HOSTILE);
        fleetxvi.setRelationship(hegemony.getId(), RepLevel.HOSTILE);
        fleetxvi.setRelationship(pirates.getId(), RepLevel.HOSTILE);
        fleetxvi.setRelationship(tritachyon.getId(), RepLevel.HOSTILE);
        fleetxvi.setRelationship(church.getId(), RepLevel.HOSTILE);
        fleetxvi.setRelationship(kol.getId(), RepLevel.HOSTILE);
        fleetxvi.setRelationship(persean.getId(), RepLevel.HOSTILE);
        fleetxvi.setRelationship(independent.getId(), RepLevel.HOSTILE);
        fleetxvi.setRelationship("MMS_AL_UDF",RepLevel.HOSTILE);


    }

    public void generate(SectorAPI sector) {

        initFactionRelationships(sector);
        new mms_nagazora().generate(sector);
        new mms_alaska().generate(sector);


    }
}
