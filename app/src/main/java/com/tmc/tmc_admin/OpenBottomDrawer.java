package com.tmc.tmc_admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class OpenBottomDrawer extends BottomSheetDialogFragment {

    private DatabaseReference Mdatabase;
    private String UID;
    private DatabaseReference Minfo_database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Mdatabase = FirebaseDatabase.getInstance().getReference().child("Drawer_Info");
        Minfo_database = FirebaseDatabase.getInstance().getReference().child("WineCoin");

        View view = inflater.inflate(R.layout.bottom_layout, container, false);

        final MaterialTextView username = view.findViewById(R.id.Usernametext);
        final MaterialTextView coin = view.findViewById(R.id.CoinTextID);
        final MaterialTextView type = view.findViewById(R.id.TypeText);
        final MaterialTextView paymentid = view.findViewById(R.id.PaymentID);
        final TextView emailtext =view.findViewById(R.id.EmailAddressID);
        final TextView wincoin = view.findViewById(R.id.WineCoin);

        Mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                     UID = dataSnapshot.child("UID").getValue().toString();

                     Minfo_database.child(UID)
                             .addValueEventListener(new ValueEventListener() {
                                 @Override
                                 public void onDataChange(DataSnapshot dataSnapshot) {
                                     if(dataSnapshot.exists()){
                                         if(dataSnapshot.hasChild("Bkash_ID")){
                                             type.setText("Bkash");
                                             String id = dataSnapshot.child("Bkash_ID").getValue().toString();
                                             paymentid.setText(id);
                                         }
                                         if(dataSnapshot.hasChild("Paytm_ID")){
                                             type.setText("Paytm");
                                             String id = dataSnapshot.child("Paytm_ID").getValue().toString();
                                             paymentid.setText(id);
                                         }
                                         if(dataSnapshot.hasChild("Mobile_Recharge")){
                                             type.setText("Mobile Recharge");
                                             String id = dataSnapshot.child("Mobile_Recharge").getValue().toString();
                                             paymentid.setText(id);
                                         }
                                         if(dataSnapshot.hasChild("GooglePay_ID")){
                                             type.setText("GoolePay");
                                             String id = dataSnapshot.child("GooglePay_ID").getValue().toString();
                                             paymentid.setText(id);
                                         }
                                         if(dataSnapshot.hasChild("Easypisa_ID")){
                                             type.setText("Easypisa");
                                             String id = dataSnapshot.child("Easypisa_ID").getValue().toString();
                                             paymentid.setText(id);
                                         }
                                         if(dataSnapshot.hasChild("Paypal_ID")){
                                             type.setText("Paypal");
                                             String id = dataSnapshot.child("Paypal_ID").getValue().toString();
                                             paymentid.setText(id);
                                         }
                                         if(dataSnapshot.hasChild("Paytm_ID")){
                                             type.setText("Paytm");
                                             String id = dataSnapshot.child("Paytm_ID").getValue().toString();
                                             paymentid.setText(id);
                                         }
                                         if(dataSnapshot.hasChild("Roket_ID")){
                                             type.setText("Roket");
                                             String id = dataSnapshot.child("Roket_ID").getValue().toString();
                                             paymentid.setText(id);
                                         }
                                         if(dataSnapshot.hasChild("coinget")){
                                             String coinget = dataSnapshot.child("coinget").getValue().toString();
                                             coin.setText(coinget);
                                             wincoin.setText(coinget);
                                         }
                                         if(dataSnapshot.hasChild("username")){
                                             String nameget = dataSnapshot.child("username").getValue().toString();
                                             username.setText(nameget);
                                         }

                                         if(dataSnapshot.hasChild("email")){
                                             String emailget = dataSnapshot.child("email").getValue().toString();
                                             emailtext.setText(emailget);
                                         }
                                     }
                                     else {
                                         Toast.makeText(getContext(), "no data found", Toast.LENGTH_LONG).show();
                                     }
                                 }

                                 @Override
                                 public void onCancelled(DatabaseError databaseError) {

                                 }
                             });
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
