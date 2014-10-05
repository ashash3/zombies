package z.z.outbreak;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import z.z.MyActivity;
import z.z.R;
import z.z.report.DistrictsDataSource;

/**
 * Created by Ashley on 10/4/14.
 */

public class CurrentOutbreakTab extends Fragment {

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
    private TextView currentDistrict;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.current_outbreak_map, container, false);
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
        presidioTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.PORTERO));
        marinaTap.setOnClickListener(new DistrictClick(this, DistrictsDataSource.SFDistrict.PRESIDIO));
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

        currentDistrict = (TextView) mainView.findViewById(R.id.current_district);

        return mainView;
    }

    private void districtClicked(DistrictsDataSource.SFDistrict district) {
        currentDistrict.setText(((MyActivity) getActivity()).getDistrictsDataSource().getDistrictName(district));
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
