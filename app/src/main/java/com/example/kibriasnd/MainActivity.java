package com.example.kibriasnd;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.sndTextView);
        button = (Button) findViewById(R.id.sndBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PackageManager packageManager;
                packageManager = getPackageManager();
                try {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo("com.example.kibriafast", packageManager.GET_META_DATA);
                    String fulldir = applicationInfo.dataDir + "/files/kibria.txt";
                    loadData(fulldir);

                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    private void loadData(String fulldir) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(fulldir));
            int startline = -1;
            StringBuffer stringBuffer = new StringBuffer();
            while ((startline = fileInputStream.read()) != -1) {
                stringBuffer.append((char) startline);
                textView.setText(stringBuffer);
                textView.setTextColor(R.color.colorAccent);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
