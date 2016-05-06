package eu.torchwood.seshat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import eu.torchwood.seshat.db.TournamentManager;
import eu.torchwood.seshat.entity.Tournament;

public class NewTournamentActivity extends AppCompatActivity {
    private static Logger logger = Logger.getLogger(NewTournamentActivity.class.getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tournament);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        Button createTournamentButton = (Button) findViewById(R.id.buttonCreateTournament);
        createTournamentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTournament(view.getContext());
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void createTournament(Context context){
        final EditText descriptionField= (EditText) findViewById(R.id.tournamentDescription);
        logger.info("Description field = " + descriptionField);
        String description = descriptionField.getText().toString();
        logger.log(Level.INFO, "Description: " + description);
        Tournament t = new Tournament(description);

        TournamentManager tm = new TournamentManager(context);
        tm.createTournament(t);
        super.finish();
    }
}
