package com.example.user1.volleyballmanager20.Adapters;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user1.volleyballmanager20.LoggedInActivity;
import com.example.user1.volleyballmanager20.MainActivity;
import com.example.user1.volleyballmanager20.R;
import com.example.user1.volleyballmanager20.cmn.Config;
import com.example.user1.volleyballmanager20.cmn.FragmentOne;
import com.example.user1.volleyballmanager20.cmn.Player;
import com.example.user1.volleyballmanager20.cmn.Team;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by user1 on 26.6.2016 г..
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";


    private static ArrayList<Player> players;
    static Player adapterPlayer;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView playerName;
        private final TextView playerHeight;
        private final TextView playerPosition;
        private final TextView playerAge;


        public ViewHolder(View v) {
            super(v);
            playerName = (TextView) v.findViewById(R.id.player_name);
            playerHeight = (TextView) v.findViewById(R.id.player_height);
            playerPosition = (TextView) v.findViewById(R.id.player_position);
            playerAge = (TextView) v.findViewById(R.id.player_age);

            v.setOnClickListener(this);

        }

        public TextView getPlayerName() {
            return playerName;
        }

        public TextView getPlayerHeight() {
            return playerHeight;
        }

        public TextView getPlayerPosition() {
            return playerPosition;
        }

        @Override
        public void onClick(View v) {

            adapterPlayer = new Player();
//            adapterPlayer.setAge(Integer.valueOf(String.valueOf(playerAge.getText())));
//            adapterPlayer.setName(String.valueOf(playerName.getText()));
//            adapterPlayer.setPosition(String.valueOf(playerPosition.getText()));
//            adapterPlayer.setHeight(Integer.valueOf(String.valueOf(playerHeight.getText())));
            for (Player currPlayer : MainActivity.players) {
                if (currPlayer.getName().equals(String.valueOf(playerName.getText()))
                        && currPlayer.getPosition().equals(String.valueOf(playerPosition.getText()))) {
                    adapterPlayer = currPlayer;
                    break;
                }
            }

//            if (players != null) {
//                for (Player player : players) {
//                    if (player.getName().equals(adapterPlayer.getName()) && player.getAge() == adapterPlayer.getAge()) {
//                        adapterPlayer = player;
//                        break;
//                    }
//                }
//            }
//            if (LoggedInActivity.loggedTeam == null || LoggedInActivity.loggedTeam.getAllPlayers() == null) {
//                LoggedInActivity.loggedTeam = new Team();
//                LoggedInActivity.loggedTeam.setAllPlayers(new ArrayList<Player>());
//            }

            if (MainActivity.isLogged) {
                new AlertDialog.Builder(FragmentOne.context)
                        .setTitle("Are you sure?")
                        .setMessage("You are attempting to get player " + "\"" + playerName.getText() + "\"" + " in your team")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (!adapterPlayer.isTaken()) {
                                    adapterPlayer.setTaken(true);
                                    LoggedInActivity.loggedTeam.getAllPlayers().add(adapterPlayer);
                                    setPlayerTaken(adapterPlayer);
                                } else {
                                    Toast.makeText(FragmentOne.context, "Player already taken!", Toast.LENGTH_LONG).show();
                                }
                            }

                            private void setPlayerTaken(final Player adapterPlayer) {
                                Firebase playerRef = new Firebase(Config.FIREBASE_PLAYERS_URL);
                                playerRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                            Player currentPlayer = snapshot.getValue(Player.class);
                                            if(currentPlayer.getName().equals(adapterPlayer.getName())
                                                    && currentPlayer.getPosition().equals(adapterPlayer.getPosition())){
                                                Firebase tempRef = snapshot.getRef();
                                                tempRef.child("taken").setValue(true);
                                                break;
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(FirebaseError firebaseError) {

                                    }
                                });
                            }
                            //}
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            } else {
                Toast.makeText(FragmentOne.context, "You must login first!", Toast.LENGTH_LONG).show();
            }

        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param players String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        Player currentPlayer = players.get(position);
        viewHolder.playerName.setText(currentPlayer.getName());
        viewHolder.playerHeight.setText(String.valueOf(currentPlayer.getHeight()));
        viewHolder.playerPosition.setText(currentPlayer.getPosition());
        viewHolder.playerAge.setText(String.valueOf(currentPlayer.getAge()));


    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return players.size();
    }
}


