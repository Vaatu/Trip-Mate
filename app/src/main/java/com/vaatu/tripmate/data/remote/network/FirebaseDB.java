package com.vaatu.tripmate.data.remote.network;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vaatu.tripmate.utils.CardviewModel;

public class FirebaseDB {
    DatabaseReference myRef;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    private static FirebaseDB singleton;


    private FirebaseDB() {

        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        //myRef = database.getReference(currentUser.getUid());
        myRef = database.getReference();


    }

    public static FirebaseDB getInstance() {
        if (singleton == null) {
            synchronized (FirebaseDB.class) {
                if (singleton == null) {
                    singleton = new FirebaseDB();
                }
            }
            return singleton;
        } else {
            return singleton;
        }
    }

    public void saveTripToDatabase(CardviewModel cvm) {
//myRef.push().getKey()
        myRef.child("trip-mate").child(currentUser.getUid()).child("trips").child(myRef.push().getKey()).setValue(cvm);
        Log.i("Firebase Database", "I'm fired :)");

    }

    public void saveUserToFirebase(String email) {

        myRef.child("trip-mate").child(currentUser.getUid()).child("userinfo").child("email").setValue(email);

    }
}