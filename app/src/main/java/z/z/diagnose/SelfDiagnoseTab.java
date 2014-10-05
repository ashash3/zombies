package z.z.diagnose;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import z.z.R;

/**
 * Created by Ashley on 10/5/14.
 */

public class SelfDiagnoseTab extends Fragment {

    private View mainView;
    private HashMap<Integer, String> pageToTitleMap;
    private HashMap<Integer, String> pageToSubtitleMap;
    private View beginButton;
    private View yesNoButtons;
    private View yesButton;
    private View noButton;
    private View percentChanceView;
    private int currentPage;
    private TextView title;
    private TextView subtitle;
    private int yesCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.self_diagnose_screen, container, false);
        loadPages();
        title = (TextView) mainView.findViewById(R.id.title);
        subtitle = (TextView) mainView.findViewById(R.id.subtitle);
        percentChanceView = mainView.findViewById(R.id.percent_chance_view);
        beginButton = mainView.findViewById(R.id.begin_button);
        yesNoButtons = mainView.findViewById(R.id.yes_no_buttons);
        yesButton = mainView.findViewById(R.id.yes_button);
        noButton = mainView.findViewById(R.id.no_button);

        beginButton.setOnClickListener(new NextButtonClick(this));
        yesButton.setOnClickListener(new NextButtonClick(this));
        noButton.setOnClickListener(new NextButtonClick(this));

        currentPage = 0;
        updatePage();

        return mainView;
    }

    private void loadPages() {
        pageToTitleMap = new HashMap<Integer, String>();
        pageToSubtitleMap = new HashMap<Integer, String>();
        pageToTitleMap.put(0, "Diagnose yourself");
        pageToSubtitleMap.put(0, "Answer a series of questions to determine your likelihood of zombie infection");
        pageToTitleMap.put(1, "01");
        pageToSubtitleMap.put(1, "Were you recently bitten by any seemingly rabid humans or animals?");
        pageToTitleMap.put(2, "02");
        pageToSubtitleMap.put(2, "Do you have any open wounds?");
        pageToTitleMap.put(3, "03");
        pageToSubtitleMap.put(3, "Have you had close contact with an infected person or their bodily fluids in the last 28 days?");
        pageToTitleMap.put(4, "04");
        pageToSubtitleMap.put(4, "Is your body temperature higher than 100F?");
        pageToTitleMap.put(5, "05");
        pageToSubtitleMap.put(5, "Are you experiencing unnatural and strong urges of aggression or violence?");
    }

    private void updatePage() {
        title.setText(pageToTitleMap.get(currentPage));
        subtitle.setText(pageToSubtitleMap.get(currentPage));
        if (currentPage > 0) {
            beginButton.setVisibility(View.GONE);
            yesNoButtons.setVisibility(View.VISIBLE);
        } else {
            beginButton.setVisibility(View.VISIBLE);
            yesNoButtons.setVisibility(View.GONE);
        }
    }

    private void incrementYesCount() {
        yesCount++;
    }

    private void showResultsPage() {
        double percentChance = (yesCount/5.0)*100;
        percentChanceView.setVisibility(View.VISIBLE);
        mainView.findViewById(R.id.prevention_icon).setVisibility(View.VISIBLE);
        mainView.findViewById(R.id.prevention_tips_header).setVisibility(View.VISIBLE);
        mainView.findViewById(R.id.prevention_tips).setVisibility(View.VISIBLE);
        ((TextView) mainView.findViewById(R.id.percent_chance)).setText(String.format("%d%%", (int) percentChance));
        currentPage = 0;
        yesCount = 0;
    }

    private void resetSurvey() {
        currentPage = 0;
        yesCount = 0;
        percentChanceView.setVisibility(View.GONE);
        mainView.findViewById(R.id.prevention_icon).setVisibility(View.GONE);
        mainView.findViewById(R.id.prevention_tips_header).setVisibility(View.GONE);
        mainView.findViewById(R.id.prevention_tips).setVisibility(View.GONE);
        updatePage();
    }

    private static class NextButtonClick implements View.OnClickListener {

        private WeakReference<SelfDiagnoseTab> selfDiagnoseTabRef;

        public NextButtonClick(SelfDiagnoseTab selfDiagnoseTab) {
            this.selfDiagnoseTabRef = new WeakReference<SelfDiagnoseTab>(selfDiagnoseTab);
        }

        @Override
        public void onClick(View v) {
            SelfDiagnoseTab selfDiagnoseTab = selfDiagnoseTabRef.get();
            if (selfDiagnoseTab != null) {
                if (v == selfDiagnoseTab.yesButton) {
                    selfDiagnoseTab.incrementYesCount();
                }
                if (selfDiagnoseTab.currentPage < 5) {
                    selfDiagnoseTab.currentPage++;
                    selfDiagnoseTab.updatePage();
                } else {
                    selfDiagnoseTab.showResultsPage();
                }
            }
        }
    }
}
