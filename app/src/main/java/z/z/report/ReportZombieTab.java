package z.z.report;

import android.app.Fragment;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.parse.ParseObject;

import java.lang.ref.WeakReference;

import z.z.MyActivity;
import z.z.R;

/**
 * Created by Ashley on 10/4/14.
 */

public class ReportZombieTab extends Fragment {

    private Button reportZombieButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.report_zombie_siting, container, false);
        reportZombieButton = (Button) mainView.findViewById(R.id.report_zombie_button);
        reportZombieButton.setOnClickListener(new ReportZombieClick(this));
        return mainView;
    }

    private void reportZombie() {
        DistrictsDataSource districtsDataSource = ((MyActivity) getActivity()).getDistrictsDataSource();
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Log.d("ASHLEY", "location: " + location);
        ParseObject zombieReport = new ParseObject("ZombieReport");
        DistrictsDataSource.SFDistrict nearestDistrict = districtsDataSource.getNearestDistrict(location);
        zombieReport.put("district_id", nearestDistrict.ordinal());
        zombieReport.put("district_name", districtsDataSource.getDistrictName(nearestDistrict));
        zombieReport.saveInBackground();
    }

    private static class ReportZombieClick implements View.OnClickListener {

        private WeakReference<ReportZombieTab> reportZombieTabRef;

        public ReportZombieClick(ReportZombieTab reportZombieTab) {
            this.reportZombieTabRef = new WeakReference<ReportZombieTab>(reportZombieTab);
        }

        @Override
        public void onClick(View v) {
            if (reportZombieTabRef.get() != null) {
                reportZombieTabRef.get().reportZombie();
            }
        }
    }
}
