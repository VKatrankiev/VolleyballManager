package com.example.user1.volleyballmanager20;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user1.volleyballmanager20.cmn.DrawingView;

public class TacticsActivity extends Activity implements View.OnClickListener {

    private DrawingView drawView;
    private Button btnUndo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tactics);

        drawView = (DrawingView) findViewById(R.id.drawing);
        drawView.setBackgroundResource(R.drawable.volleyball_court_original);
        btnUndo = (Button) findViewById(R.id.btn_undo);
        btnUndo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_undo) {
            drawView.onClickUndo();
        }
    }
}
