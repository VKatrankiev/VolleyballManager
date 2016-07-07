package com.example.user1.volleyballmanager20.cmn;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user1.volleyballmanager20.R;

import java.util.ArrayList;


/**
 * Created by User on 6/29/2016.
 */
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    Team team;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView playerName;
        private final TextView playerTitular;
        private final TextView playerPosition;;
        private final TextView playerCaptain;


        public ViewHolder(View v) {
            super(v);

            playerName = (TextView) v.findViewById(R.id.txt_player_name_loggedin);
            playerTitular = (TextView) v.findViewById(R.id.txt_player_titular_loggedin);
            playerPosition = (TextView) v.findViewById(R.id.txt_player_position_loggedin);
            playerCaptain = (TextView) v.findViewById(R.id.txt_player_captain_loggedin);

        }

        public TextView getPlayerName() {
            return playerName;
        }
        public TextView getPlayerTitular() {
            return playerTitular;
        }
        public TextView getPlayerPosition() {
            return playerPosition;
        }
        public TextView getPlayerCaptain() {return playerCaptain;}

    }

    public TeamAdapter (Team team){this.team = team;}
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_player, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("TeamAdapter", "Element " + position + " set.");
        Player currentPlayer = team.getAllPlayers().get(position);

        holder.playerName.setText(currentPlayer.getName());

        if(team.getStartingList().isEmpty()){
            holder.playerTitular.setText("");
        }else{
            if(team.getStartingList().contains(currentPlayer)){
                holder.playerTitular.setText("T");
            }else{
                holder.playerTitular.setText("");
            }
        }
        holder.playerPosition.setText(currentPlayer.getPosition());
        if(team.getCaptain().equals(currentPlayer)){
            holder.playerCaptain.setText("C");
        }else{
            holder.playerCaptain.setText("");
        }
    }

    @Override
    public int getItemCount() {
        if(team.getAllPlayers()==null){
            return 0;
        }else{
            return team.getAllPlayers().size();
        }
    }






}
