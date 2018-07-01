package com.mccutech.journalpro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Edwards on 6/29/2018.
 */

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_view_profile);
    }
    public void view(View view){
        startActivity(new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(getString(R.string.dev_fb))));
    }

}
