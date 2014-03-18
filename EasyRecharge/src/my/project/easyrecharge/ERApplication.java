package my.project.easyrecharge;

import android.app.Application;

public class ERApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		F.init(getApplicationContext());
	}

}
