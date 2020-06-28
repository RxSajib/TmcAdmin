package com.tmc.tmc_admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class WinnerFragement extends Fragment {


    private RecyclerView recyclerView;
    private String CurrentuserID;
    private String coinget, usernameget;
    private String Bkash_IDget;
    private ProgressDialog Mprogress;

    private DatabaseReference Mwinerdatabase;
    private RelativeLayout nouser;
    private DatabaseReference MopenDrawerSave_data;

    public WinnerFragement() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_winner_fragement, container, false);

        recyclerView = view.findViewById(R.id.WinnerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        MopenDrawerSave_data = FirebaseDatabase.getInstance().getReference().child("Drawer_Info");


        Mprogress = new ProgressDialog(getContext());
        nouser = view.findViewById(R.id.NouserAccountID);

        Mwinerdatabase = FirebaseDatabase.getInstance().getReference().child("WineCoin");

        getmy_view();

        return view;
    }


    private void getmy_view(){

        Query firebasequry = Mwinerdatabase.orderByChild("short");

        FirebaseRecyclerAdapter<WinnerPogo, WinerHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<WinnerPogo, WinerHolder>(
                WinnerPogo.class,
                R.layout.sample_layout,
                WinerHolder.class,
                firebasequry
        ) {
            @Override
            protected void populateViewHolder(final WinerHolder winerHolder, WinnerPogo winnerPogo, int i) {

                final String UID = getRef(i).getKey();
                Mwinerdatabase.child(UID)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    nouser.setVisibility(View.INVISIBLE);

                                    if(dataSnapshot.hasChild("coinget")){
                                        coinget = dataSnapshot.child("coinget").getValue().toString();
                                        winerHolder.setWinecoinset(coinget);
                                    }
                                    if(dataSnapshot.hasChild("username")){
                                        usernameget = dataSnapshot.child("username").getValue().toString();
                                        winerHolder.setUsernameset(usernameget);
                                    }
                                    if(dataSnapshot.hasChild("Bkash_ID")){
                                        Bkash_IDget = dataSnapshot.child("Bkash_ID").getValue().toString();
                                    }
                                    if(dataSnapshot.hasChild("uri")){
                                        String uriget = dataSnapshot.child("uri").getValue().toString();
                                        winerHolder.setProfileimageset(uriget);
                                    }
                                    if(dataSnapshot.hasChild("email")){
                                        String email_addressget = dataSnapshot.child("email").getValue().toString();
                                        winerHolder.setEmailaddressset(email_addressget);
                                    }


                                    winerHolder.Mview.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {

                                            Mprogress.setMessage("geting user info");
                                            Mprogress.setTitle("Please wait ...");
                                            Mprogress.setCanceledOnTouchOutside(false);
                                            Mprogress.show();

                                            Map<String, Object> data = new HashMap<String, Object>();
                                            data.put("UID", UID);


                                            MopenDrawerSave_data.updateChildren(data)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful()){
                                                                Mprogress.dismiss();
                                                                OpenBottomDrawer openBottomDrawer = new OpenBottomDrawer();
                                                                openBottomDrawer.show(getFragmentManager(), "open");
                                                            }
                                                            else {
                                                                Mprogress.dismiss();
                                                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Mprogress.dismiss();
                                                            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                                        }
                                                    });

                                        }
                                    });
                                }
                                else {
                                    nouser.setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }


    public static class WinerHolder extends RecyclerView.ViewHolder{

        private View Mview;
        private Context context;
        private CircleImageView profileimage;
        private MaterialTextView username, emailaddress, winecoin;

        public WinerHolder(@NonNull View itemView) {
            super(itemView);

            Mview = itemView;
            context = Mview.getContext();
            profileimage = Mview.findViewById(R.id.WinnrImageID);
            username = Mview.findViewById(R.id.UsernameID);
            emailaddress = Mview.findViewById(R.id.userEmailAddressID);
            winecoin = Mview.findViewById(R.id.WineCoinTextID);
        }

        public void setUsernameset(String nam){
            username.setText(nam);
        }

        public void setEmailaddressset(String email){
            emailaddress.setText(email);
        }

        public void setWinecoinset(String coin){
            winecoin.setText(coin);
        }

        public void setProfileimageset(String img){
            Picasso.with(context).load(img).placeholder(R.drawable.circle_image).into(profileimage);
        }
    }
}