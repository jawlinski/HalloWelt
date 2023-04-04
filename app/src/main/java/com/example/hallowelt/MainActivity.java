package com.example.hallowelt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
                           implements OnClickListener {
private Button btnHi;
private Button btnHello;
private TextView lblGreeting;
private Button btnStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);btnStart.setOnClickListener (new OnClickListener()  {@Override
                public void onClick(View v)
        { Log.i("Demo", "Start-Button gedrückt);");}
                                     });
        btnHi = (Button) findViewById(R.id.btnHi);
        btnHi.setOnClickListener(this);
        btnHello = (Button) findViewById(R.id.btnHallo);
        btnHello.setOnClickListener(this);
        lblGreeting = (TextView) findViewById(R.id.lblGruss);

    }

    @Override
    public void onClick(View view) {
        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConnected = conMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected();

        Log.i("com.example.hallowelt","Wifi Connect is " + isWifiConnected);
        Thread.dumpStack();



        EditText nameField = (EditText) findViewById(R.id.txtName);
        String name = nameField.getText().toString().trim();
        if (name.length() == 0)
        {
            new AlertDialog.Builder(this)
                    .setMessage(R.string.errorNameMissing)
                    .setNeutralButton(R.string.errorOk, null)
                    .show();
            return;

        }
if ( view == btnHi || view == btnHello)
{
    int resourceId = 0;
    if ( view == btnHi)
    {
        resourceId = R.string.hiGreeting;

    } else {
        resourceId = R.string.helloGreeting;
    }
    String greeting = getResources().getString(resourceId,name);
    Toast.makeText(this,greeting, Toast.LENGTH_LONG).show();
    lblGreeting.setText(greeting);
}

   }
}