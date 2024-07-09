package com.nextoneday.second;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.nextoneday.libProcesses.ProcessesManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProcessesManager.getInstance().connect();
    }
}
