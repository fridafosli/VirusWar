package no.ntnu.viruswar;

import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import no.ntnu.viruswar.services.data.Loot;
import no.ntnu.viruswar.services.data.NetworkDataController;
import no.ntnu.viruswar.services.data.Player;
import no.ntnu.viruswar.services.backend.BackendService;

public class FirebaseBackendService implements BackendService {
    FirebaseDatabase database;
    DatabaseReference myRef;

    public FirebaseBackendService() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    @Override
    public void setGamePinEventListener(final NetworkDataController dataHolder) {
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("GamePin added: " + snapshot.getKey());
                dataHolder.addActiveGamePin(snapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                System.out.println("GamePin removed: " + snapshot.getKey());
                dataHolder.removeActiveGamePin(snapshot.getKey());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void addPlayerToGame(String gamePin, Player player) {
        myRef.child(gamePin + "/players/" + player.getId()).setValue(player);
    }

    @Override
    public void updatePLayerPosition(String gamePin, String playerId, float x, float y, float points) {
        DatabaseReference ref = database.getReference(gamePin + "/players/" + playerId);
        ref.child("/x").setValue(x);
        ref.child("/y").setValue(y);
        ref.child("/points").setValue(points);
    }

    @Override
    public void removePlayerFromGame(String gamePin, String playerId) {
        myRef.child(gamePin + "/players/" + playerId).removeValue();
    }

    @Override
    public void setPlayersEventListener(final NetworkDataController dataHolder, String gamePin) {
        myRef.child(gamePin + "/players").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("Player added: " + snapshot.getValue(Player.class).getName());
                dataHolder.addPlayer(snapshot.getValue(Player.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("Player whit name: " + snapshot.getValue(Player.class).getName() + " changed.");
                dataHolder.updatePlayer(snapshot.getKey(), snapshot.getValue(Player.class));

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                System.out.println("Player removed: " + snapshot.getValue(Player.class).getName());
                dataHolder.removePlayer(snapshot.getKey());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    @Override
    public void addLootToGame(String gamePin, Loot loot) {
        // myRef.child(gamePin + "/players/" + player.getId()).setValue(player);
        myRef.child(gamePin + "/loot/" + loot.getId()).setValue(loot);
    }

    @Override
    public void removeLootFromGame(String gamePin, String lootId) {
        myRef.child(gamePin+ "/loot/" + lootId).removeValue() ;
    }

    @Override
    public void setLootEventListener(final NetworkDataController dataholder, String gamePin) {
        myRef.child(gamePin + "/loot").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("Loot added: " + snapshot.getValue(Loot.class).getPoints());
                dataholder.addLoot(snapshot.getValue(Loot.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("Player whit name: " + snapshot.getValue(Player.class).getName() + " changed.");
                dataholder.updatePlayer(snapshot.getKey(), snapshot.getValue(Player.class));

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                System.out.println("Player removed: " + snapshot.getValue(Player.class).getName());
                dataholder.removePlayer(snapshot.getKey());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


}