package com.example.user1.volleyballmanager20.cmn;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.NotificationCompat;
import android.support.v4.graphics.ColorUtils;
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

import java.util.ArrayList;

/**
 * Created by user1 on 26.6.2016 г..
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";


    private ArrayList<Player> players;
    static ArrayList<Player> adapterTeam;
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
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                }
            });
            playerName = (TextView) v.findViewById(R.id.player_name);
            playerHeight = (TextView) v.findViewById(R.id.player_height);
            playerPosition = (TextView) v.findViewById(R.id.player_position);
            playerAge = (TextView) v.findViewById(R.id.player_age);
            playerName.setOnClickListener(this);
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
            Log.e("pesho", (String) playerName.getText());

                adapterTeam = new ArrayList<>();
                adapterPlayer = new Player();
                for (Player player : adapterTeam) {
                    if (player.getName().equals(playerName.getText()) && player.getPosition().equals(playerPosition.getText())) {
                        adapterPlayer = player;
                    }
                }
            if (MainActivity.isLogged == true) {
                new AlertDialog.Builder(FragmentOne.context)
                        .setTitle("Are you sure?")
                        .setMessage("You are attempting to get player " + "\"" + playerName.getText() + "\"" + " in your team")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (adapterPlayer.getName().equals(playerName.getText()) && adapterPlayer.isTaken() == false) {
                                    adapterTeam.add(adapterPlayer);
                                    LoggedInActivity.loggedTeam.getAllPlayers().add(adapterPlayer);
                                } else {
                                    Toast.makeText(FragmentOne.context, "Player already taken!", Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
            }else{
                Toast.makeText(FragmentOne.context,"You must login first!",Toast.LENGTH_LONG);
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

    // Create new views (invoked by the layout manager)
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


