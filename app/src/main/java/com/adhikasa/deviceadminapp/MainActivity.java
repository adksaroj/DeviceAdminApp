package com.adhikasa.deviceadminapp;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.Settings;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button btnActivateDeviceAdmin;
    TextView lblAdminStatus;
    boolean isAdmin = false;

    DevicePolicyManager devicePolicyManager;
    ComponentName deviceAdminComp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        btnActivateDeviceAdmin = findViewById(R.id.btn_activateAdmin);
        lblAdminStatus = findViewById(R.id.lbl_isAdmin);


        if(!isAdmin)
            lblAdminStatus.setText("Not Running As Admin");

        devicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        deviceAdminComp = new ComponentName(MainActivity.this, DeviceAdmnReceiverActivity.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void activateDeviceAdmin(View view){

        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,deviceAdminComp);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                "Additional text explaining why this needs to be added.");
        startActivityForResult(intent,47);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        //lblAdminStatus.setText("Running As Admin Now...");
        switch (requestCode) {
            case 47:
                if (resultCode == Activity.RESULT_OK) {
                        lblAdminStatus.setText("Administration ENABLED...!");
                } else {
                   lblAdminStatus.setText("Administration enable FAILED !");
                }
                return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
