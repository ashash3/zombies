package z.z;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Ashley on 10/4/14.
 */
public class ZombieApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "RaTlItppJJXzwbKm7i42msKQ9CAJMBEjQzvYTNlV", "8affkam1NxUU4SeUr1cUYzueqQZz7eueLPvMbxlx");
    }
}
