package no.ntnu.viruswar;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import no.ntnu.viruswar.services.backend.BackendModel;
import no.ntnu.viruswar.services.backend.BackendService;
import no.ntnu.viruswar.services.models.BaseEntity;
import no.ntnu.viruswar.services.models.Loot;
import no.ntnu.viruswar.services.models.Player;

public class FirebaseBackendService implements BackendService {
    FirebaseDatabase database;
    DatabaseReference myRef;

    public FirebaseBackendService() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();
    }

    private String buildPath(String gamePin, BaseEntity entity) {
        return String.format("%s/%s/%s", gamePin, entity.getPath(), entity.getId());
    }

    private DatabaseReference playerRef(String gamePin, String id) {
        return database.getReference(String.format("%s/%s/%s", gamePin, "players", id));
    }

    @Override
    public void setGamePinEventListener(final BackendModel dataHolder) {

        ChildEventListener listener = new ChildEventListener() {
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
        };

        myRef.addChildEventListener(listener);

    }


    @Override
    public void addEntityToGame(String gamePin, BaseEntity entity) {
        myRef.child(buildPath(gamePin, entity)).setValue(entity);
    }

    @Override
    public void updatePlayerPosition(String gamePin, String playerId, float x, float y, float points) {
        DatabaseReference ref = database.getReference(gamePin + "/players/" + playerId);
        ref.child("/x").setValue(x);
        ref.child("/y").setValue(y);
        ref.child("/points").setValue(points);
    }

    @Override
    public void startGame(String gamePin) {
        DatabaseReference ref = database.getReference(gamePin);
        ref.child("/started").setValue(true);
    }

    @Override
    public void setEntityConsumedState(String gamePin, String path, String id, boolean state) {
        DatabaseReference ref = database.getReference(String.format("%s/%s/%s", gamePin, path, id));
        ref.child("/consumed").setValue(state);
    }

    @Override
    public void setPlayerSkin(String gamePin, String id, int skinIndex) {
        playerRef(gamePin, id).child("/skinIndex").setValue(skinIndex);
    }

    @Override
    public void setNickname(String gamePin, String id, String nickname) {
        playerRef(gamePin, id).child("/name").setValue(nickname);
    }

    @Override
    public void removePlayerFromGame(String gamePin, String playerId) {
        myRef.child(gamePin + "/players/" + playerId).removeValue();
    }

    @Override
    public void setLobbyStateListener(final BackendModel dataHolder, String gamePin) {
        DatabaseReference ref = database.getReference(gamePin);
        ref.child("/started").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    dataHolder.isGameStarted = (boolean) snapshot.getValue();
                } catch (Exception e) {
                    dataHolder.isGameStarted = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void setPlayersEventListener(final BackendModel dataHolder, String gamePin) {
        myRef.child(gamePin + "/players").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("Player added: " + snapshot.getValue(Player.class).getPath());
                dataHolder.addPlayer(snapshot.getValue(Player.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("Player whit name: " + snapshot.getValue(Player.class).getPath() + " changed.");
                dataHolder.updatePlayer(snapshot.getKey(), snapshot.getValue(Player.class));

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                System.out.println("Player removed: " + snapshot.getValue(Player.class).getPath());
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
        myRef.child(gamePin + "/loot/" + lootId).removeValue();
    }

    @Override
    public void setLootEventListener(final BackendModel dataholder, String gamePin) {
        myRef.child(gamePin + "/loot").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("Loot added: " + snapshot.getValue(Loot.class).getPoints());
                dataholder.addLoot(snapshot.getValue(Loot.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                System.out.println("Player whit name: " + snapshot.getValue(Player.class).getPath() + " changed.");
                dataholder.updatePlayer(snapshot.getKey(), snapshot.getValue(Player.class));

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                System.out.println("Player removed: " + snapshot.getValue(Player.class).getPath());
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