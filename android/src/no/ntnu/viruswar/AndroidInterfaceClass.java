package no.ntnu.viruswar;


import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import no.ntnu.viruswar.Data.Player;

public class AndroidInterfaceClass implements FireBaseInterface {
    FirebaseDatabase database;
    DatabaseReference myRef;

    public AndroidInterfaceClass()
    {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
    }

    @Override
    public void SomeFunction() {
        System.out.println("Just some function");
    }

    @Override
    public void FirstFireBaseTest() {
        if(myRef != null){
            myRef.setValue("Hello, World!!!");
        }
        else{
            System.out.println("Databasereference was not set -> therefore could not write to DB");
        }
    }



    @Override
    public void SetOnValueChangedListener(final DataHolderClass dataholder) {
        myRef.addValueEventListener(new ValueEventListener() {
            // Read from the database

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                dataholder.someValue = value;
                dataholder.PrintSomeValue();
//                dataholder.addPlayer(1,2,3);
//                System.out.println(dataholder.getPlayers());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void SetValueInDb(String target, String value) {
        myRef = database.getReference(target);
        myRef.setValue(value);
    }

    @Override
    public void AddPlayerToGame(String gamePin, Player player) {
        myRef = database.getReference( gamePin + "/players");
        myRef.child(player.getId()).setValue(player);
    }

    @Override
    public void RemovePlayerFromGame(String gamePin, String playerId) {
        myRef = database.getReference(gamePin + "/players");
        myRef.child(playerId).removeValue();
    }


    @Override
    public void SetChildEventListener(final DataHolderClass dataholder, String gamePin) {
        myRef = database.getReference(gamePin+ "/players");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("child added: " + snapshot.getValue(Player.class).getName());
                dataholder.addPlayer(snapshot.getValue(Player.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("child whit name: " + snapshot.getValue(Player.class).getName() + " changed.");
                dataholder.updatePlayer(snapshot.getKey(), snapshot.getValue(Player.class));

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                System.out.println("child removed: " + snapshot.getValue(Player.class).getName());
                dataholder.removePlayer(snapshot.getKey());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("child moved " + previousChildName);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("error: ");
            }
        });
    }


}