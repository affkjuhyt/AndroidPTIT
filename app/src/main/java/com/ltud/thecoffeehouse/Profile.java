package com.ltud.thecoffeehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {

    TextView name;
    TextView logout;
    ImageView imgGoogle;
    String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logout = findViewById(R.id.txtSignOut);
        name = findViewById(R.id.txtName);
        imgGoogle = findViewById(R.id.imgGoogle);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null) {
            name.setText(signInAccount.getDisplayName());
            imgUrl = (signInAccount.getPhotoUrl()).toString();

            Glide.with(this)
                    .load(imgUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imgGoogle);
        }

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}