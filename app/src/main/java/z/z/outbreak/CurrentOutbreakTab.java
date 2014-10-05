package z.z.outbreak;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
            if (count > 0) {
                // Color it red
                colorDistrictRed(district);
            } else {
                // Leave gray
            }
        }
    }

    private void colorDistrictRed(DistrictsDataSource.SFDistrict district) {
        switch (district) {
            case FINANCIAL:
                mainView.findViewById(R.id.financial_district).setBackgroundResource(R.drawable.financial_district_red);
            case MARINA:
                mainView.findViewById(R.id.marina).setBackgroundResource(R.drawable.marina_red);
            case FISHERMAN_WHARF:
                mainView.findViewById(R.id.fishermans_wharf).setBackgroundResource(R.drawable.fishermans_wharf_red);
            case NORTH_BEACH:
                mainView.findViewById(R.id.north_beach).setBackgroundResource(R.drawable.north_beach_red);
            case RUSSIAN_HILL:
                mainView.findViewById(R.id.russian_hill).setBackgroundResource(R.drawable.russian_hill_red);
            case NOB_HILL:
                mainView.findViewById(R.id.nob_hill).setBackgroundResource(R.drawable.nob_hill_red);
            case CHINATOWN:
                mainView.findViewById(R.id.chinatown).setBackgroundResource(R.drawable.chinatown_red);
            case SOMA:
                mainView.findViewById(R.id.soma).setBackgroundResource(R.drawable.soma_red);
            case TENDERLOIN:
                mainView.findViewById(R.id.tenderloin).setBackgroundResource(R.drawable.tenderloin_red);
            case PACIFIC_HEIGHTS:
                mainView.findViewById(R.id.pacific_heights).setBackgroundResource(R.drawable.pacific_heights_red);
            case WESTERN_ADDITION:
                mainView.findViewById(R.id.western_addition).setBackgroundResource(R.drawable.western_addition_red);
            case HAIGHT:
                mainView.findViewById(R.id.haight).setBackgroundResource(R.drawable.haight_red);
            case CASTRO:
                mainView.findViewById(R.id.castro).setBackgroundResource(R.drawable.castro_red);
            case MISSION:
                mainView.findViewById(R.id.mission).setBackgroundResource(R.drawable.mission_red);
            case PORTERO:
                mainView.findViewById(R.id.portero).setBackgroundResource(R.drawable.portrero_hill_red);
            case NOE_VALLEY:
                mainView.findViewById(R.id.noe_valley).setBackgroundResource(R.drawable.noe_valley_red);
            case PRESIDIO:
                mainView.findViewById(R.id.presidio).setBackgroundResource(R.drawable.presidio_red);
            case RICHMOND:
                mainView.findViewById(R.id.richmond).setBackgroundResource(R.drawable.richmond_red);
            case DOGPATCH:
                mainView.findViewById(R.id.dogpatch).setBackgroundResource(R.drawable.dogpatch_red);
            case GOLDEN_GATE_PARK:
                mainView.findViewById(R.id.golden_gate).setBackgroundResource(R.drawable.golden_gate_red);
            case SUNSET:
                mainView.findViewById(R.id.sunset).setBackgroundResource(R.drawable.sunset_red);
            case TWIN_PEAKS:
            default:
                mainView.findViewById(R.id.twin_peaks).setBackgroundResource(R.drawable.twin_peaks_red);
        }
    }

    private void colorDistrictOrange(DistrictsDataSource.SFDistrict district) {
        switch (district) {
            case FINANCIAL:
            case MARINA:
            case FISHERMAN_WHARF:
            case NORTH_BEACH:
            case RUSSIAN_HILL:
            case NOB_HILL:
            case CHINATOWN:
            case SOMA:
            case TENDERLOIN:
            case PACIFIC_HEIGHTS:
            case WESTERN_ADDITION:
            case HAIGHT:
            case CASTRO:
            case MISSION:
            case PORTERO:
            case NOE_VALLEY:
            case PRESIDIO:
            case RICHMOND:
            case DOGPATCH:
            case GOLDEN_GATE_PARK:
            case SUNSET:
            case TWIN_PEAKS:
            default:
        }
    }

    private void colorDistrictYellow(DistrictsDataSource.SFDistrict district) {
        switch (district) {
            case FINANCIAL:
            case MARINA:
            case FISHERMAN_WHARF:
            case NORTH_BEACH:
            case RUSSIAN_HILL:
            case NOB_HILL:
            case CHINATOWN:
            case SOMA:
            case TENDERLOIN:
            case PACIFIC_HEIGHTS:
            case WESTERN_ADDITION:
            case HAIGHT:
            case CASTRO:
            case MISSION:
            case PORTERO:
            case NOE_VALLEY:
            case PRESIDIO:
            case RICHMOND:
            case DOGPATCH:
            case GOLDEN_GATE_PARK:
            case SUNSET:
            case TWIN_PEAKS:
            default:
        }
    }

    private void districtClicked(DistrictsDataSource.SFDistrict district) {
        if (zombieCountByDistrict.isEmpty()) return;
        currentDistrict.setText(((MyActivity) getActivity()).getDistrictsDataSource().getDistrictName(district));
        Log.d("ASHLEY", "zombie country by district: " + zombieCountByDistrict);
        int zombieCount = zombieCountByDistrict.get(district);
        ((TextView) mainView.findViewById(R.id.zombie_count)).setText(String.valueOf(zombieCount));
        ((TextView) mainView.findViewById(R.id.reported_cases)).setText(zombieCount == 1 ? "reported case" : "reported cases");
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
