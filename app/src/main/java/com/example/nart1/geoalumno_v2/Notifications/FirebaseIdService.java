package com.example.nart1.geoalumno_v2.Notifications;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by nart1 on 23/11/2017.
 */

public class FirebaseIdService extends FirebaseInstanceIdService {

    private static final String TAG = "Firebase InstanceID";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshedToken = FirebaseInstanceId.getInstance().getToken ();
        Log.d (TAG, "Refreshed token: " + refreshedToken);
    }
}
