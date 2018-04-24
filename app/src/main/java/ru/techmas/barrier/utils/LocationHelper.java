package ru.techmas.barrier.utils;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Date: 03.09.2017
 * Time: 12:20
 * Project: BurgerKing
 *
 * @author Alex Bykov
 *         You can contact me at me@alexbykov.ru
 */
public class LocationHelper implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    private static final String TAG = "LocationHelper";

    private Context context;
    private GoogleApiClient client;
    private Location location;
    private OnLocationListener onLocationListener;

    public LocationHelper(Context context) {
        this.context = context;
        client = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    public LocationHelper setOnLocationListener(OnLocationListener onLocationListener) {
        this.onLocationListener = onLocationListener;
        return null;
    }

    public void connect() {
        if (client != null) {
            if (!client.isConnected())
                client.connect();
        }
    }

    public void disconnect() {
        if (client != null && client.isConnected()) {
            client.disconnect();
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.d(TAG, "onConnectionFailed: ");

        this.location = LocationServices.FusedLocationApi.getLastLocation(client);
        if (location != null && onLocationListener != null) {
            onLocationListener.onLocationSuccess(location.getLatitude(), location.getLongitude());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "onConnectionSuspended: " + i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed: " + connectionResult.getErrorMessage() + connectionResult.getErrorCode());
    }


    public interface OnLocationListener {
        void onLocationSuccess(double lat, double lon);
    }
}
