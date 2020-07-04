package com.adhikasa.deviceadminapp;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.widget.Toast;

public class DeviceAdmnReceiverActivity extends DeviceAdminReceiver {
    void showToast(Context context, String msg) {
        String status = msg;
        Toast.makeText(context, status, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        showToast(context, "Device Admin Enabled...");
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "Requested for Disable";
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        showToast(context, "Disabled");
    }

    @Override
    public void onPasswordChanged(Context context, Intent intent, UserHandle userHandle) {
        showToast(context, "Password Chnaged");
    }
}
