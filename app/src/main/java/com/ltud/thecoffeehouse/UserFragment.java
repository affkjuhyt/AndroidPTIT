package com.ltud.thecoffeehouse;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {
    private RelativeLayout relaInfo;
    private TextView txtSignIn, txtName, txtEmail, txtSignOut;
    private FirebaseAuth mAuth;
    private ImageView imgGoogle, imgIconSignIn;
    private String imgUrl;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        imgGoogle = view.findViewById(R.id.imgGoogle);
        txtName = view.findViewById(R.id.txtName);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtSignOut = view.findViewById(R.id.txtSignOut);
        imgIconSignIn = view.findViewById(R.id.imgIconSignIn);
        txtSignIn = view.findViewById(R.id.txtSignIn);

        relaInfo = view.findViewById(R.id.relaInfoAccount);
        relaInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), InfomationActivity.class);
                startActivity(i);
            }
        });

        txtSignIn = view.findViewById(R.id.txtSignIn);
        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), SignInActivity.class);
                startActivity(i);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            imgIconSignIn.setVisibility(View.GONE);
            txtSignIn.setVisibility(View.GONE);
        } else {
            imgGoogle.setVisibility(View.GONE);
            txtName.setVisibility(View.GONE);
            txtEmail.setVisibility(View.GONE);

            txtSignOut.setVisibility(View.GONE);
        }

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(getActivity().getApplicationContext());
        if(signInAccount != null) {
            txtName.setText(signInAccount.getDisplayName());
            imgUrl = (signInAccount.getPhotoUrl()).toString();

            Glide.with(this)
                    .load(imgUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(imgGoogle);
        }

        txtSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}