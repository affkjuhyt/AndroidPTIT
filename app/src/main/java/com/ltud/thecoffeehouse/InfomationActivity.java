package com.ltud.thecoffeehouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class InfomationActivity extends AppCompatActivity {
    private ImageView imgCancelInfo, imgInfo;
    private TextView txtNameInfo, txtNameDetail, txtPhoneDetail, txtEmailDetail;
    String imgUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_infomation);

        imgCancelInfo = findViewById(R.id.imgCancelInfo);
        txtNameInfo = findViewById(R.id.txtNameInfo);
        imgInfo = findViewById(R.id.imgInfo);
        txtNameDetail = findViewById(R.id.txtNameDetail);
        txtPhoneDetail = findViewById(R.id.txtDetailPhone);
        txtEmailDetail = findViewById(R.id.txtEmailDetail);

        imgCancelInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(InfomationActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null) {
            txtNameInfo.setText(signInAccount.getDisplayName());
            txtNameDetail.setText(signInAccount.getDisplayName());
            txtEmailDetail.setText(signInAccount.getEmail());
            txtPhoneDetail.setText("0376214299");
            imgUrl = (signInAccount.getPhotoUrl()).toString();

            Glide.with(this)
                    .load(imgUrl)
                    .circleCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imgInfo);
        }

    }
}
