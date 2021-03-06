package com.example.androidpanin.task711;

import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidpanin.R;
import com.example.androidpanin.ToolbarActivity;
import com.example.androidpanin.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class T_711 extends ToolbarActivity {

    private TextView mCurrentTimeView;
    private Button mSyncBtn;
    private Date date = new Date() ;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    private final static int MORNING_ID = 1;
    private final static int AFTERNOON_ID = 2;
    private final static int EVENING_ID = 3;
    private final static int ERROR_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_711);
        initView();
    }

    private void initView() {
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mCurrentTimeView = findViewById(R.id.currentTimeView);

        mCurrentTimeView.setText(dateFormat.format(date));

        mSyncBtn = findViewById(R.id.syncBtn);
        mSyncBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUri = "";

                switch (getTimeId(date)) {
                    case MORNING_ID:
                        strUri = "http://morning";
                        break;
                    case AFTERNOON_ID:
                        strUri = "http://afternoon";
                        break;
                    case EVENING_ID:
                        strUri = "http://evening";
                        break;
                    case ERROR_CODE:
                        showErrorMsg();
                        break;
                }

                Intent intent = new Intent(Intent.ACTION_SYNC);
                intent.setData(Uri.parse(strUri));
                startActivity(intent);
            }
        });
    }

    public int getTimeId(Date date) {
        try {
            if (dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("05:59")) &&
                    dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse("14:00"))) {
                return MORNING_ID;
            } else if (dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("13:59")) &&
                    dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse("15:00"))) {
                return AFTERNOON_ID;
            } else if (dateFormat.parse(dateFormat.format(date)).after(dateFormat.parse("14:59")) ||
                    dateFormat.parse(dateFormat.format(date)).before(dateFormat.parse("06:00"))) {
                return EVENING_ID;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return ERROR_CODE;
    }

    public void showErrorMsg() {
        Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show();
    }
}
