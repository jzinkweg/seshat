package eu.torchwood.seshat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.List;

import eu.torchwood.seshat.db.TournamentManager;
import eu.torchwood.seshat.entity.Tournament;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.new_tournament_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newTournament = new Intent(Home.this, NewTournamentActivity.class);
                startActivity(newTournament);
            }
        });
    }

    private void deleteTournament(Long id){
        new TournamentManager(this).deleteTournament(id);
        displayTournaments();
    }
    private void displayTournaments() {
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.tournamentRecords);
        linearLayoutRecords.removeAllViews();

        List<Tournament> tournaments = new TournamentManager(this).getTournaments();

        if (tournaments.size() > 0) {
            for (Tournament t : tournaments) {

                TextView tournamentItem = new TextView(this);
                tournamentItem.setPadding(0, 10, 0, 10);
                tournamentItem.setText("Bla: " + t.getDescription());
                tournamentItem.setTag(t.getId());
                tournamentItem.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        deleteTournament((Long)v.getTag());
                        return true;
                    }
                });
                linearLayoutRecords.addView(tournamentItem);
            }

        } else {
            TextView noTournaments = new TextView(this);
            noTournaments.setPadding(8, 8, 8, 8);
            noTournaments.setText("No tournaments found.");
            linearLayoutRecords.addView(noTournaments);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        displayTournaments();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
