package com.mccutech.journalpro;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by Edwards on 6/29/2018.
 */

public class SocialActivity extends AppCompatActivity implements View.OnClickListener {
RelativeLayout fbImageView, linkdnImageView, twitterImgView, youtubeImgView, instagramImView;
    private String fbColor = "#3b5998";
    private String linkedInColor = "#077bb7";
    private String twitterColor = "#07b0ef";
    private String youtubeColor = "#ce2726";
    private String instagramColor = "#077bb7";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_social);

        fbImageView = (RelativeLayout) findViewById(R.id.btnFB);
        fbImageView.setOnClickListener(this);
        linkdnImageView = (RelativeLayout) findViewById(R.id.btnLinkedIn);
        linkdnImageView.setOnClickListener(this);
        twitterImgView = (RelativeLayout) findViewById(R.id.btnTwitter);
        twitterImgView.setOnClickListener(this);
        instagramImView = (RelativeLayout) findViewById(R.id.btnInstagram);
        instagramImView.setOnClickListener(this);
        youtubeImgView = (RelativeLayout) findViewById(R.id.btnYoutube);
        youtubeImgView.setOnClickListener(this);
    }



        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnFB:
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(getString(R.string.dev_fb))));
                    return;
                case R.id.btnLinkedIn:
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(getString(R.string.dev_linked_in))));
                    return;
                case R.id.btnTwitter:
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(getString(R.string.dev_twitter))));
                    return;
                case R.id.btnInstagram:
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(getString(R.string.dev_instagram))));
                    return;
                case R.id.btnYoutube:
                    startActivity(new Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(getString(R.string.dev_youtube))));
                    return;

            }
    }
}
