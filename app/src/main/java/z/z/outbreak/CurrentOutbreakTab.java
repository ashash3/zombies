package z.z.outbreak;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.FunctionCallback;
import com.parse.ParseCloud;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import z.z.MyActivity;
import z.z.R;
import z.z.report.DistrictsDataSource;

/**
 * Created by Ashley on 10/4/14.
 */

public class CurrentOutbreakTab extends Fragment {

    private View mainView;
    // Tap views
    private View sunsetTap;
    private View goldenGateTap;
    private View richmondTap;
    private View presidioTap;
    private View marinaTap;
    private View pacificHeightsTap;
    private View westernAdditionTap;
    private View haightTap;
    private View twinPeaksTap;
    private View castroTap;
    private View noeValleyTap;
    private View missionTap;
    private View porteroTap;
    private View dogpatchTap;
    private View somaTap;
    private View tenderloinTap;
    private View financialDistrictTap;
    private View chinatownTap;
    private View nobHillTap;
    private View russianHillTap;
    private View northBeachTap;
    private View fishermanWharfTap;
    // Colored views
    private View sunset;
    private View goldenGate;
    private View richmond;
    private View presidio;
    private View marina;
    private View pacificHeights;
    private View westernAddition;
    private View haight;
    private View twinPeaks;
    private View castro;
    private View noeValley;
    private View mission;
    private View portero;
    private View dogpatch;
    private View soma;
    private View tenderloin;
    private View financialDistrict;
    private View chinatown;
    private View nobHill;
    private View russianHill;
    private View northBeach;
    private View fishermanWharf;
    // Other
    private TextView currentDistrict;
    private HashMap<DistrictsDataSource.SFDistrict, Integer> zombieCountByDistrict;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.current_outbreak_map, container, false);
        // Tap views
        sunsetTap = mainView.findViewById(R.id.sunset_tap);
        goldenGateTap = mainView.findViewById(R.id.golden_gate_tap);
        richmondTap = mainView.findViewById(R.id.richmond_tap);
        presidioTap = mainView.findViewById(R.id.presidio_tap);
        marinaTap = mainView.findViewById(R.id.marina_tap);
        pacificHeightsTap = mainView.findViewById(R.id.pacific_heights_tap);
        westernAdditionTap = mainView.findViewById(R.id.western_addition_tap);
        haightTap = mainView.findViewById(R.id.haight_tap);
        twinPeaksTap = mainView.findViewById(R.id.twin_peaks_tap);
        castroTap = mainView.findViewById(R.id.castro_tap);
        noeValleyTap = mainView.findViewById(R.id.noe_valley_tap);
        missionTap = mainView.findViewById(R.id.mission_tap);
        porteroTap = mainView.findViewById(R.id.portero_tap);
        dogpatchTap = mainView.findViewById(R.id.dogpatch_tap);
        somaTap = mainView.findViewById(R.id.soma_tap);
        tenderloinTap = mainView.findViewById(R.id.tenderloin_tap);
        financialDistrictTap = mainView.findViewById(R.id.financial_district_tap);
        chinatownTap = mainView.findViewById(R.id.chinatown_tap);
        nobHillTap = mainView.findViewById(R.id.nob_hill_tap);
        russianHillTap = mainView.findViewById(R.id.russian_hill_tap);
        northBeachTap = mainView.findViewById(R.id.north_beach_tap);
        fishermanWharfTap = mainView.findViewById(R.id.fisherman_wharf_tap);
        // Set up the click listeners
        sunsetTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.SUNSET));
        goldenGateTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.GOLDEN_GATE_PARK));
        richmondTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.RICHMOND));
        presidioTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.PRESIDIO));
        marinaTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.MARINA));
        pacificHeightsTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.PACIFIC_HEIGHTS));
        westernAdditionTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.WESTERN_ADDITION));
        haightTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.HAIGHT));
        twinPeaksTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.TWIN_PEAKS));
        castroTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.CASTRO));
        noeValleyTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.NOE_VALLEY));
        missionTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.MISSION));
        porteroTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.PORTERO));
        dogpatchTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.DOGPATCH));
        somaTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.SOMA));
        tenderloinTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.TENDERLOIN));
        financialDistrictTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.FINANCIAL));
        chinatownTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.CHINATOWN));
        nobHillTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.NOB_HILL));
        russianHillTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.RUSSIAN_HILL));
        northBeachTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.NORTH_BEACH));
        fishermanWharfTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.FISHERMAN_WHARF));

        // Colored views
        sunset = mainView.findViewById(R.id.sunset);
        goldenGate = mainView.findViewById(R.id.golden_gate);
        richmond = mainView.findViewById(R.id.richmond);
        presidio = mainView.findViewById(R.id.presidio);
        marina = mainView.findViewById(R.id.marina);
        pacificHeights = mainView.findViewById(R.id.pacific_heights);
        westernAddition = mainView.findViewById(R.id.western_addition);
        haight = mainView.findViewById(R.id.haight);
        twinPeaks = mainView.findViewById(R.id.twin_peaks);
        castro = mainView.findViewById(R.id.castro);
        noeValley = mainView.findViewById(R.id.noe_valley);
        mission = mainView.findViewById(R.id.mission);
        portero = mainView.findViewById(R.id.portero);
        dogpatch = mainView.findViewById(R.id.dogpatch);
        soma = mainView.findViewById(R.id.soma);
        tenderloin = mainView.findViewById(R.id.tenderloin);
        financialDistrict = mainView.findViewById(R.id.financial_district);
        chinatown = mainView.findViewById(R.id.chinatown);
        nobHill = mainView.findViewById(R.id.nob_hill);
        russianHill = mainView.findViewById(R.id.russian_hill);
        northBeach = mainView.findViewById(R.id.north_beach);
        fishermanWharf = mainView.findViewById(R.id.fishermans_wharf);

        currentDistrict = (TextView) mainView.findViewById(R.id.current_district);

        zombieCountByDistrict = new HashMap<DistrictsDataSource.SFDistrict, Integer>();

        fetchOutbreakStatistics();

        return mainView;
    }

    private void fetchOutbreakStatistics() {
        Log.d("ASHLEY", "fetchOutbreakStatistics");
        ParseCloud.callFunctionInBackground("getZombieCountsByDistrict", new HashMap<String, Object>(), new FunctionCallback<Map<String, Object>>() {
            @Override
            public void done(Map<String, Object> response, com.parse.ParseException e) {
                Log.d("ASHLEY", "response: " + response);
                Log.d("ASHLEY", "exception: " + e);
                if (e == null) {
                    updateHeatMap(response);
                } else {
                    // TODO: Add reloading logic
                    Map<String, Object> updatedData = new HashMap<String, Object>();
                    updatedData.put("0", 5);
                    updatedData.put("1", 7);
                    updatedData.put("2", 0);
                    updatedData.put("3", 1);
                    updatedData.put("4", 3);
                    updatedData.put("5", 5);
                    updatedData.put("6", 7);
                    updatedData.put("7", 8);
                    updatedData.put("8", 59);
                    updatedData.put("9", 115);
                    updatedData.put("10", 5);
                    updatedData.put("11", 1);
                    updatedData.put("12", 3);
                    updatedData.put("13", 2);
                    updatedData.put("14", 0);
                    updatedData.put("15", 0);
                    updatedData.put("16", 0);
                    updatedData.put("17", 0);
                    updatedData.put("18", 15);
                    updatedData.put("19", 200);
                    updatedData.put("20", 63);
                    updatedData.put("21", 45);
                    updatedData.put("22", 51);
                    updateHeatMap(updatedData);
                }
            }
        });
    }

    private void updateHeatMap(Map<String, Object> updatedData) {
        for (String districtId : updatedData.keySet()) {
            int count = (Integer) updatedData.get(districtId);
            DistrictsDataSource.SFDistrict district = DistrictsDataSource.SFDistrict.values()[Integer.parseInt(districtId)];
            zombieCountByDistrict.put(district, count);
            // TODO: Update the heat map with the correct color
            if (count > 100) {
                // Color it red
                colorDistrictRed(district);
            } else if (count > 50) {
                // Color it orange
                colorDistrictOrange(district);
            } else if (count > 10) {
                // Color it yellow
                colorDistrictYellow(district);
            } else {
                // Leave gray
            }
        }
    }

    private void colorDistrictRed(DistrictsDataSource.SFDistrict district) {
        switch (district) {
            case FINANCIAL:
                mainView.findViewById(R.id.financial_district).setBackgroundResource(R.drawable.financial_district_red);
                return;
            case MARINA:
                mainView.findViewById(R.id.marina).setBackgroundResource(R.drawable.marina_red);
                return;
            case FISHERMAN_WHARF:
                mainView.findViewById(R.id.fishermans_wharf).setBackgroundResource(R.drawable.fishermans_wharf_red);
                return;
            case NORTH_BEACH:
                mainView.findViewById(R.id.north_beach).setBackgroundResource(R.drawable.north_beach_red);
                return;
            case RUSSIAN_HILL:
                mainView.findViewById(R.id.russian_hill).setBackgroundResource(R.drawable.russian_hill_red);
                return;
            case NOB_HILL:
                mainView.findViewById(R.id.nob_hill).setBackgroundResource(R.drawable.nob_hill_red);
                return;
            case CHINATOWN:
                mainView.findViewById(R.id.chinatown).setBackgroundResource(R.drawable.chinatown_red);
                return;
            case SOMA:
                mainView.findViewById(R.id.soma).setBackgroundResource(R.drawable.soma_red);
                return;
            case TENDERLOIN:
                mainView.findViewById(R.id.tenderloin).setBackgroundResource(R.drawable.tenderloin_red);
                return;
            case PACIFIC_HEIGHTS:
                mainView.findViewById(R.id.pacific_heights).setBackgroundResource(R.drawable.pacific_heights_red);
                return;
            case WESTERN_ADDITION:
                mainView.findViewById(R.id.western_addition).setBackgroundResource(R.drawable.western_addition_red);
                return;
            case HAIGHT:
                mainView.findViewById(R.id.haight).setBackgroundResource(R.drawable.haight_red);
                return;
            case CASTRO:
                mainView.findViewById(R.id.castro).setBackgroundResource(R.drawable.castro_red);
                return;
            case MISSION:
                mainView.findViewById(R.id.mission).setBackgroundResource(R.drawable.mission_red);
                return;
            case PORTERO:
                mainView.findViewById(R.id.portero).setBackgroundResource(R.drawable.portrero_hill_red);
                return;
            case NOE_VALLEY:
                mainView.findViewById(R.id.noe_valley).setBackgroundResource(R.drawable.noe_valley_red);
                return;
            case PRESIDIO:
                mainView.findViewById(R.id.presidio).setBackgroundResource(R.drawable.presidio_red);
                return;
            case RICHMOND:
                mainView.findViewById(R.id.richmond).setBackgroundResource(R.drawable.richmond_red);
                return;
            case DOGPATCH:
                mainView.findViewById(R.id.dogpatch).setBackgroundResource(R.drawable.dogpatch_red);
                return;
            case GOLDEN_GATE_PARK:
                mainView.findViewById(R.id.golden_gate).setBackgroundResource(R.drawable.golden_gate_red);
                return;
            case SUNSET:
                mainView.findViewById(R.id.sunset).setBackgroundResource(R.drawable.sunset_red);
                return;
            case TWIN_PEAKS:
                mainView.findViewById(R.id.twin_peaks).setBackgroundResource(R.drawable.twin_peaks_red);
        }
    }

    private void colorDistrictOrange(DistrictsDataSource.SFDistrict district) {
        switch (district) {
            case FINANCIAL:
                mainView.findViewById(R.id.financial_district).setBackgroundResource(R.drawable.financial_district_orange);
                return;
            case MARINA:
                mainView.findViewById(R.id.marina).setBackgroundResource(R.drawable.marina_orange);
                return;
            case FISHERMAN_WHARF:
                mainView.findViewById(R.id.fishermans_wharf).setBackgroundResource(R.drawable.fishermans_wharf_orange);
                return;
            case NORTH_BEACH:
                mainView.findViewById(R.id.north_beach).setBackgroundResource(R.drawable.north_beach_orange);
                return;
            case RUSSIAN_HILL:
                mainView.findViewById(R.id.russian_hill).setBackgroundResource(R.drawable.russian_hill_orange);
                return;
            case NOB_HILL:
                mainView.findViewById(R.id.nob_hill).setBackgroundResource(R.drawable.nob_hill_orange);
                return;
            case CHINATOWN:
                mainView.findViewById(R.id.chinatown).setBackgroundResource(R.drawable.chinatown_orange);
                return;
            case SOMA:
                mainView.findViewById(R.id.soma).setBackgroundResource(R.drawable.soma_orange);
                return;
            case TENDERLOIN:
                mainView.findViewById(R.id.tenderloin).setBackgroundResource(R.drawable.tenderloin_orange);
                return;
            case PACIFIC_HEIGHTS:
                mainView.findViewById(R.id.pacific_heights).setBackgroundResource(R.drawable.pacific_heights_orange);
                return;
            case WESTERN_ADDITION:
                mainView.findViewById(R.id.western_addition).setBackgroundResource(R.drawable.western_addition_orange);
                return;
            case HAIGHT:
                mainView.findViewById(R.id.haight).setBackgroundResource(R.drawable.haight_orange);
                return;
            case CASTRO:
                mainView.findViewById(R.id.castro).setBackgroundResource(R.drawable.castro_orange);
                return;
            case MISSION:
                mainView.findViewById(R.id.mission).setBackgroundResource(R.drawable.mission_orange);
                return;
            case PORTERO:
                mainView.findViewById(R.id.portero).setBackgroundResource(R.drawable.portrero_hill_orange);
                return;
            case NOE_VALLEY:
                mainView.findViewById(R.id.noe_valley).setBackgroundResource(R.drawable.noe_valley_orange);
                return;
            case PRESIDIO:
                mainView.findViewById(R.id.presidio).setBackgroundResource(R.drawable.presidio_orange);
                return;
            case RICHMOND:
                mainView.findViewById(R.id.richmond).setBackgroundResource(R.drawable.richmond_district_orange);
                return;
            case DOGPATCH:
                mainView.findViewById(R.id.dogpatch).setBackgroundResource(R.drawable.dogpatch_orange);
                return;
            case GOLDEN_GATE_PARK:
                mainView.findViewById(R.id.golden_gate).setBackgroundResource(R.drawable.golden_gate_orange);
                return;
            case SUNSET:
                mainView.findViewById(R.id.sunset).setBackgroundResource(R.drawable.sunset_orange);
                return;
            case TWIN_PEAKS:
                mainView.findViewById(R.id.twin_peaks).setBackgroundResource(R.drawable.twin_peaks_orange);
        }
    }

    private void colorDistrictYellow(DistrictsDataSource.SFDistrict district) {
        switch (district) {
            case FINANCIAL:
                mainView.findViewById(R.id.financial_district).setBackgroundResource(R.drawable.financial_district_yellow);
                return;
            case MARINA:
                mainView.findViewById(R.id.marina).setBackgroundResource(R.drawable.marina_yellow);
                return;
            case FISHERMAN_WHARF:
                mainView.findViewById(R.id.fishermans_wharf).setBackgroundResource(R.drawable.fishermans_wharf_yellow);
                return;
            case NORTH_BEACH:
                mainView.findViewById(R.id.north_beach).setBackgroundResource(R.drawable.north_beach_yellow);
                return;
            case RUSSIAN_HILL:
                mainView.findViewById(R.id.russian_hill).setBackgroundResource(R.drawable.russian_hill_yellow);
                return;
            case NOB_HILL:
                mainView.findViewById(R.id.nob_hill).setBackgroundResource(R.drawable.nob_hill_yellow);
                return;
            case CHINATOWN:
                mainView.findViewById(R.id.chinatown).setBackgroundResource(R.drawable.chinatown_yellow);
                return;
            case SOMA:
                mainView.findViewById(R.id.soma).setBackgroundResource(R.drawable.soma_yellow);
                return;
            case TENDERLOIN:
                mainView.findViewById(R.id.tenderloin).setBackgroundResource(R.drawable.tenderloin_yellow);
                return;
            case PACIFIC_HEIGHTS:
                mainView.findViewById(R.id.pacific_heights).setBackgroundResource(R.drawable.pacific_heights_yellow);
                return;
            case WESTERN_ADDITION:
                mainView.findViewById(R.id.western_addition).setBackgroundResource(R.drawable.western_addition_yellow);
                return;
            case HAIGHT:
                mainView.findViewById(R.id.haight).setBackgroundResource(R.drawable.haight_yellow);
                return;
            case CASTRO:
                mainView.findViewById(R.id.castro).setBackgroundResource(R.drawable.castro_yellow);
                return;
            case MISSION:
                mainView.findViewById(R.id.mission).setBackgroundResource(R.drawable.mission_yellow);
                return;
            case PORTERO:
                mainView.findViewById(R.id.portero).setBackgroundResource(R.drawable.portrero_hill_yellow);
                return;
            case NOE_VALLEY:
                mainView.findViewById(R.id.noe_valley).setBackgroundResource(R.drawable.noe_valley_yellow);
                return;
            case PRESIDIO:
                mainView.findViewById(R.id.presidio).setBackgroundResource(R.drawable.presidio_yellow);
                return;
            case RICHMOND:
                mainView.findViewById(R.id.richmond).setBackgroundResource(R.drawable.richmond_district_yellow);
                return;
            case DOGPATCH:
                mainView.findViewById(R.id.dogpatch).setBackgroundResource(R.drawable.dogpatch_yellow);
                return;
            case GOLDEN_GATE_PARK:
                mainView.findViewById(R.id.golden_gate).setBackgroundResource(R.drawable.golden_gate_yellow);
                return;
            case SUNSET:
                mainView.findViewById(R.id.sunset).setBackgroundResource(R.drawable.sunset_yellow);
                return;
            case TWIN_PEAKS:
                mainView.findViewById(R.id.twin_peaks).setBackgroundResource(R.drawable.twin_peaks_yellow);
        }
    }

    private int getViewIdForDistrict(DistrictsDataSource.SFDistrict district) {
        switch (district) {
            case FINANCIAL:
                return R.id.financial_district;
            case MARINA:
                return R.id.marina;
            case FISHERMAN_WHARF:
                return R.id.fishermans_wharf;
            case NORTH_BEACH:
                return R.id.north_beach;
            case RUSSIAN_HILL:
                return R.id.russian_hill;
            case NOB_HILL:
                return R.id.nob_hill;
            case CHINATOWN:
                return R.id.chinatown;
            case SOMA:
                return R.id.soma;
            case TENDERLOIN:
                return R.id.tenderloin;
            case PACIFIC_HEIGHTS:
                return R.id.pacific_heights;
            case WESTERN_ADDITION:
                return R.id.western_addition;
            case HAIGHT:
                return R.id.haight;
            case CASTRO:
                return R.id.castro;
            case MISSION:
                return R.id.mission;
            case PORTERO:
                return R.id.portero;
            case NOE_VALLEY:
                return R.id.noe_valley;
            case PRESIDIO:
                return R.id.presidio;
            case RICHMOND:
                return R.id.richmond;
            case DOGPATCH:
                return R.id.dogpatch;
            case GOLDEN_GATE_PARK:
                return R.id.golden_gate;
            case SUNSET:
                return R.id.sunset;
            case TWIN_PEAKS:
            default:
                return R.id.twin_peaks;
        }
    }

    private void districtClicked(DistrictsDataSource.SFDistrict district) {
        if (zombieCountByDistrict.isEmpty()) return;
        currentDistrict.setText(((MyActivity) getActivity()).getDistrictsDataSource().getDistrictName(district));
        Log.d("ASHLEY", "zombie country by district: " + zombieCountByDistrict);
        int zombieCount = zombieCountByDistrict.get(district);
        ((TextView) mainView.findViewById(R.id.zombie_count)).setText(String.valueOf(zombieCount));
        ((TextView) mainView.findViewById(R.id.reported_cases)).setText(zombieCount == 1 ? "reported case" : "reported cases");
        // Set up the line on the location marker
        View locationMarker = mainView.findViewById(R.id.location);
        locationMarker.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) locationMarker.getLayoutParams();
        int viewId = getViewIdForDistrict(district);
        int marginLeft = mainView.findViewById(viewId).getWidth()/2;
        int marginBottom = mainView.findViewById(viewId).getHeight()/2;
        layoutParams.setMargins(marginLeft, 255, 0, marginBottom);
        layoutParams.addRule(RelativeLayout.ALIGN_LEFT, viewId);
        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM, viewId);
        locationMarker.setLayoutParams(layoutParams);
        // Set up the pin head on the location marker
        View locationMarkerPin = mainView.findViewById(R.id.location_marker_pin);
        locationMarkerPin.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams pinLayoutParams = (RelativeLayout.LayoutParams) locationMarkerPin.getLayoutParams();
        pinLayoutParams.setMargins(marginLeft - 18, 225, 0, 0);
        pinLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, viewId);
        locationMarkerPin.setLayoutParams(pinLayoutParams);
        if (zombieCount > 100) {
            // Color it red
            locationMarker.setBackgroundColor(getResources().getColor(R.color.red));
            locationMarkerPin.setBackgroundResource(R.drawable.location_marker_pin_red);
        } else if (zombieCount > 50) {
            // Color it orange
            locationMarker.setBackgroundColor(getResources().getColor(R.color.orange));
            locationMarkerPin.setBackgroundResource(R.drawable.location_marker_pin_orange);
        } else if (zombieCount > 10) {
            // Color it yellow
            locationMarker.setBackgroundColor(getResources().getColor(R.color.yellow));
            locationMarkerPin.setBackgroundResource(R.drawable.location_marker_pin_yellow);
        } else {
            // Leave gray
            locationMarker.setBackgroundColor(getResources().getColor(R.color.black));
            locationMarkerPin.setBackgroundResource(R.drawable.location_marker_pin_black);
        }
        // Set up the text view for the district
        RelativeLayout.LayoutParams districtTextLayoutParams = (RelativeLayout.LayoutParams) currentDistrict.getLayoutParams();
        districtTextLayoutParams.setMargins(marginLeft - currentDistrict.getWidth()/2, 150, 0, 0);
        districtTextLayoutParams.addRule(RelativeLayout.ALIGN_LEFT, viewId);
        currentDistrict.setLayoutParams(districtTextLayoutParams);
    }

    private static class DistrictClick implements View.OnClickListener {

        private WeakReference<CurrentOutbreakTab> currentOutbreakTabRef;
        private DistrictsDataSource.SFDistrict district;

    public DistrictClick(CurrentOutbreakTab currentOutbreakTab,
                         DistrictsDataSource.SFDistrict district) {
            this.currentOutbreakTabRef = new WeakReference<CurrentOutbreakTab>(currentOutbreakTab);
            this.district = district;
        }

        @Override
        public void onClick(View v) {
            if (currentOutbreakTabRef.get() != null) {
                currentOutbreakTabRef.get().districtClicked(district);
            }
        }
    }
}
