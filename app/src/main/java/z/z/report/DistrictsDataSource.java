package z.z.report;

import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Ashley on 10/4/14.
 */
public class DistrictsDataSource {

    private HashMap<SFDistrict, Location> districtLocations;
    private HashMap<SFDistrict, String> districtNames;

    public enum SFDistrict {
        FINANCIAL, MARINA, FISHERMAN_WHARF, NORTH_BEACH, RUSSIAN_HILL, NOB_HILL, CHINATOWN, SOMA,
        TENDERLOIN, PACIFIC_HEIGHTS, WESTERN_ADDITION, HAIGHT, CASTRO, MISSION, PORTERO, NOE_VALLEY,
        PRESIDIO, RICHMOND, DOGPATCH, GOLDEN_GATE_PARK, SUNSET, TWIN_PEAKS, DISTRICT_NONE
    }

    public DistrictsDataSource() {
        buildDistricts();
    }

    public SFDistrict getRandomDistrict() {
        SFDistrict[] districtsArray = new SFDistrict[] {SFDistrict.FINANCIAL, SFDistrict.MARINA, SFDistrict.FISHERMAN_WHARF, SFDistrict.NOB_HILL, SFDistrict.RUSSIAN_HILL, SFDistrict.NOB_HILL, SFDistrict.CHINATOWN,
        SFDistrict.SOMA, SFDistrict.TENDERLOIN, SFDistrict.PACIFIC_HEIGHTS, SFDistrict.WESTERN_ADDITION, SFDistrict.HAIGHT, SFDistrict.CASTRO, SFDistrict.MISSION,
        SFDistrict.PORTERO, SFDistrict.NOE_VALLEY, SFDistrict.PRESIDIO, SFDistrict.RICHMOND, SFDistrict.DOGPATCH, SFDistrict.GOLDEN_GATE_PARK, SFDistrict.SUNSET, SFDistrict.TWIN_PEAKS};
        int idx = new Random().nextInt(districtsArray.length);
        return districtsArray[idx];
    }

    public SFDistrict getNearestDistrict(Location location) {
        float minDistance = Float.MAX_VALUE;
        SFDistrict currentDistrict = SFDistrict.DISTRICT_NONE;
        for (SFDistrict district : districtLocations.keySet()) {
            float distance = districtLocations.get(district).distanceTo(location);
            Log.d("ASHLEY", "District: " + districtNames.get(district));
            Log.d("ASHLEY", "distance: " + distance);
            if (distance < minDistance) {
                minDistance = distance;
                currentDistrict = district;
            }
        }
        return currentDistrict;
    }

    public String getDistrictName(SFDistrict sfDistrict) {
        return districtNames.get(sfDistrict);
    }

    private void buildDistricts() {
        districtLocations = new HashMap<SFDistrict, Location>();
        districtNames = new HashMap<SFDistrict, String>();
        Location financialDistrict = new Location(LocationManager.NETWORK_PROVIDER);
        financialDistrict.setLatitude(37.7952);
        financialDistrict.setLongitude(-122.4029);
        districtLocations.put(SFDistrict.FINANCIAL, financialDistrict);
        districtNames.put(SFDistrict.FINANCIAL, "Financial District");
        Location marinaDistrict = new Location(LocationManager.NETWORK_PROVIDER);
        marinaDistrict.setLatitude(37.8030);
        marinaDistrict.setLongitude(-122.4360);
        districtLocations.put(SFDistrict.MARINA, marinaDistrict);
        districtNames.put(SFDistrict.MARINA, "Marina District");
        Location fishermanWharfDistrict = new Location(LocationManager.NETWORK_PROVIDER);
        fishermanWharfDistrict.setLatitude(37.8083);
        fishermanWharfDistrict.setLongitude(-122.4156);
        districtLocations.put(SFDistrict.FISHERMAN_WHARF, fishermanWharfDistrict);
        districtNames.put(SFDistrict.FISHERMAN_WHARF, "Fisherman's Wharf");
        Location northBeachDistrict = new Location(LocationManager.NETWORK_PROVIDER);
        northBeachDistrict.setLatitude(37.8003);
        northBeachDistrict.setLongitude(-122.4102);
        districtLocations.put(SFDistrict.NORTH_BEACH, northBeachDistrict);
        districtNames.put(SFDistrict.NORTH_BEACH, "North Beach");
        Location russianHill = new Location(LocationManager.NETWORK_PROVIDER);
        russianHill.setLatitude(37.8018);
        russianHill.setLongitude(-122.4198);
        districtLocations.put(SFDistrict.RUSSIAN_HILL,russianHill);
        districtNames.put(SFDistrict.RUSSIAN_HILL, "Russian Hill");
        Location nobHill = new Location(LocationManager.NETWORK_PROVIDER);
        nobHill.setLatitude(37.7932);
        nobHill.setLongitude(-122.4145);
        districtLocations.put(SFDistrict.NOB_HILL, nobHill);
        districtNames.put(SFDistrict.NOB_HILL, "Nob Hill");
        Location chinatown = new Location(LocationManager.NETWORK_PROVIDER);
        chinatown.setLatitude(37.7947);
        chinatown.setLongitude(-122.4072);
        districtLocations.put(SFDistrict.CHINATOWN, chinatown);
        districtNames.put(SFDistrict.CHINATOWN, "Chinatown");
        Location soma = new Location(LocationManager.NETWORK_PROVIDER);
        soma.setLatitude(37.7772);
        soma.setLongitude(-122.4111);
        districtLocations.put(SFDistrict.SOMA, soma);
        districtNames.put(SFDistrict.SOMA, "SOMA");
        Location tenderloin = new Location(LocationManager.NETWORK_PROVIDER);
        tenderloin.setLatitude(37.7833);
        tenderloin.setLongitude(-122.4167);
        districtLocations.put(SFDistrict.TENDERLOIN, tenderloin);
        districtNames.put(SFDistrict.TENDERLOIN, "Tenderloin");
        Location pacificHeights = new Location(LocationManager.NETWORK_PROVIDER);
        pacificHeights.setLatitude(37.7917);
        pacificHeights.setLongitude(-122.4356);
        districtLocations.put(SFDistrict.PACIFIC_HEIGHTS, pacificHeights);
        districtNames.put(SFDistrict.PACIFIC_HEIGHTS, "Pacific Heights");
        Location westernAddition = new Location(LocationManager.NETWORK_PROVIDER);
        westernAddition.setLatitude(37.7825);
        westernAddition.setLongitude(-122.4282);
        districtLocations.put(SFDistrict.WESTERN_ADDITION, westernAddition);
        districtNames.put(SFDistrict.WESTERN_ADDITION, "Western Addition");
        Location haight = new Location(LocationManager.NETWORK_PROVIDER);
        haight.setLatitude(37.7700);
        haight.setLongitude(-122.4469);
        districtLocations.put(SFDistrict.HAIGHT, haight);
        districtNames.put(SFDistrict.HAIGHT, "Haight-Ashbury");
        Location castro = new Location(LocationManager.NETWORK_PROVIDER);
        castro.setLatitude(37.7617);
        castro.setLongitude(-122.4351);
        districtLocations.put(SFDistrict.CASTRO, castro);
        districtNames.put(SFDistrict.CASTRO, "Castro District");
        Location mission = new Location(LocationManager.NETWORK_PROVIDER);
        mission.setLatitude(37.7600);
        mission.setLongitude(-122.4200);
        districtLocations.put(SFDistrict.MISSION, mission);
        districtNames.put(SFDistrict.MISSION, "Mission District");
        Location portero = new Location(LocationManager.NETWORK_PROVIDER);
        portero.setLatitude(37.7572);
        portero.setLongitude(-122.3999);
        districtLocations.put(SFDistrict.PORTERO, portero);
        districtNames.put(SFDistrict.PORTERO, "Portero");
        Location noeValley = new Location(LocationManager.NETWORK_PROVIDER);
        noeValley.setLatitude(37.7514);
        noeValley.setLongitude(-122.4319);
        districtLocations.put(SFDistrict.NOE_VALLEY, noeValley);
        districtNames.put(SFDistrict.NOE_VALLEY, "Noe Valley");
        Location presidio = new Location(LocationManager.NETWORK_PROVIDER);
        presidio.setLatitude(37.7981);
        presidio.setLongitude(-122.4658);
        districtLocations.put(SFDistrict.PRESIDIO, presidio);
        districtNames.put(SFDistrict.PRESIDIO, "Presidio");
        Location richmond = new Location(LocationManager.NETWORK_PROVIDER);
        richmond.setLatitude(37.7778);
        richmond.setLongitude(-122.4828);
        districtLocations.put(SFDistrict.RICHMOND, richmond);
        districtNames.put(SFDistrict.RICHMOND, "Richmond");
        Location dogpatch = new Location(LocationManager.NETWORK_PROVIDER);
        dogpatch.setLatitude(37.7606);
        dogpatch.setLongitude(-122.3911);
        districtLocations.put(SFDistrict.DOGPATCH, dogpatch);
        districtNames.put(SFDistrict.DOGPATCH, "Dogpatch");
        Location goldenGatePark = new Location(LocationManager.NETWORK_PROVIDER);
        goldenGatePark.setLatitude(37.7697);
        goldenGatePark.setLongitude(-122.4769);
        districtLocations.put(SFDistrict.GOLDEN_GATE_PARK, goldenGatePark);
        districtNames.put(SFDistrict.GOLDEN_GATE_PARK, "Golden Gate Park");
        Location sunset = new Location(LocationManager.NETWORK_PROVIDER);
        sunset.setLatitude(37.7500);
        sunset.setLongitude(-122.4900);
        districtLocations.put(SFDistrict.SUNSET, sunset);
        districtNames.put(SFDistrict.SUNSET, "Sunset District");
        Location twinPeaks = new Location(LocationManager.NETWORK_PROVIDER);
        twinPeaks.setLatitude(37.7500);
        twinPeaks.setLongitude(-122.4900);
        districtLocations.put(SFDistrict.TWIN_PEAKS, twinPeaks);
        districtNames.put(SFDistrict.TWIN_PEAKS, "Twin Peaks");
    }
}
