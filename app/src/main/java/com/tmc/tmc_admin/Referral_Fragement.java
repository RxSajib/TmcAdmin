package com.tmc.tmc_admin;

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
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class Referral_Fragement extends Fragment {


    private DatabaseReference Muser_database;
    private DatabaseReference Mreferral_database;
    private RelativeLayout norefferlayout;
    private RecyclerView recyclerView;

    private SearchView searchView;

    public Referral_Fragement() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_referral__fragement, container, false);

        searchView = view.findViewById(R.id.EmailSearchID);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                start_searhing(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                start_searhing(newText);

                return false;
            }
        });

        norefferlayout = view.findViewById(R.id.Refferlayout);
        norefferlayout.setVisibility(View.INVISIBLE);
        recyclerView = view.findViewById(R.id.RefferViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Muser_database = FirebaseDatabase.getInstance().getReference().child("Users");
        Mreferral_database = FirebaseDatabase.getInstance().getReference().child("Referral_data");

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));



        return view;


    }


    private void start_searhing(String text){

        String email_address = text;


    }

    @Override
    public void onStart() {

        Query firebase_shortqry = Mreferral_database.orderByChild("short_data");

        FirebaseRecyclerAdapter<user_info, RefferHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<user_info, RefferHolder>(
                user_info.class,
                R.layout.reffer_layout,
                RefferHolder.class,
                firebase_shortqry
        ) {
            @Override
            protected void populateViewHolder(final RefferHolder refferHolder, user_info user_info, int i) {

                final String UID = getRef(i).getKey();
                Mreferral_database.child(UID)
                        .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                if (dataSnapshot.exists()) {
                                    norefferlayout.setVisibility(View.INVISIBLE);
                                    if (dataSnapshot.hasChild("_id")) {
                                        String user_id = dataSnapshot.child("_id").getValue().toString();

                                        if(dataSnapshot.hasChild("_refer_name")){
                                            String name = dataSnapshot.child("_refer_name").getValue().toString();
                                            refferHolder.setrefer_al("Referral: "+name);
                                        }


                                        Muser_database.child(user_id)
                                                .addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        if (dataSnapshot.exists()) {
                                                            if (dataSnapshot.hasChild("uri")) {
                                                                String uriget = dataSnapshot.child("uri").getValue().toString();
                                                                refferHolder.seruserpic(uriget);
                                                            }
                                                            if (dataSnapshot.hasChild("email_address")) {
                                                                String email_addressget = dataSnapshot.child("email_address").getValue().toString();
                                                                refferHolder.setUseremailset(email_addressget);
                                                            }
                                                            if (dataSnapshot.hasChild("name")) {
                                                                String nameget = dataSnapshot.child("name").getValue().toString();
                                                                refferHolder.setUsernameset(nameget);
                                                            }


                                                            refferHolder.Mview.setOnClickListener(new View.OnClickListener() {
                                                                @Override
                                                                public void onClick(View v) {

                                                                }
                                                            });

                                                        } else {
                                                            Toast.makeText(getContext(), "no data", Toast.LENGTH_LONG).show();
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });

                                    }
                                } else {
                                    norefferlayout.setVisibility(View.VISIBLE);

                                }


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

        super.onStart();
    }

    public static class RefferHolder extends RecyclerView.ViewHolder {

        private View Mview;
        private Context context;
        private CircleImageView profileimage;
        private MaterialTextView username, useremail, refer_name;

        public RefferHolder(@NonNull View itemView) {
            super(itemView);

            Mview = itemView;
            context = Mview.getContext();
            profileimage = Mview.findViewById(R.id.ReferImageViewID);
            useremail = Mview.findViewById(R.id.ReferEmailAddressID);
            username = Mview.findViewById(R.id.RefferUsernameId);
            refer_name = Mview.findViewById(R.id.ReferTextID);
        }


        public void seruserpic(String img) {
            Picasso.with(context).load(img).placeholder(R.drawable.circle_image).into(profileimage);
        }

        public void setUsernameset(String nam) {
            username.setText(nam);
        }

        public void setUseremailset(String email) {
            useremail.setText(email);
        }

        public void setrefer_al(String ref){
            refer_name.setText(ref);
        }
    }
}