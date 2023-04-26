package com.google.ar.core.examples.java.ww;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.ar.core.examples.java.augmentedimage.AugmentedImageActivity;
import com.google.ar.core.examples.java.augmentedimage.R;
import com.google.ar.core.examples.java.models.EmployeeList;
import com.google.ar.core.examples.java.utils.DataUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button scanButton;
    private Button scanFaceButton;

    public static EmployeeList employeeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListener();
        initData();
    }

    private void initData() {
        employeeList = DataUtils.getEmployeeData(MainActivity.this);
    }

    private void initViews() {
        scanButton = findViewById(R.id.scan_button);
        scanFaceButton = findViewById(R.id.scan_face_button);
    }

    private void initListener() {
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(MainActivity.this, AugmentedImageActivity.class);
                startActivity(intent);
            }
        });

        scanFaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(MainActivity.this, AugmentedImageActivity.class);
                startActivity(intent);
            }
        });
    }
}