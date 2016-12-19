
package org.esiea.costa_raguideau.projetmobile_costa_raguideau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PageAccueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_accueil);
        TextView view = (TextView) findViewById(R.id.Intro);
        view.setText(R.string.Intro);
        view = (TextView) findViewById(R.id.BoutonVie);
        view.setText(R.string.BoutonVie);
        view = (TextView) findViewById(R.id.Avis);
        view.setText(R.string.Avis);
        view = (TextView) findViewById(R.id.Next);
        view.setText(R.string.Next);
    }

    public void petite_soif (View view){
        Intent intent = new Intent (this, Petite_soif.class);
        startActivity(intent);
    }

    public void arrogant(View v) {
        Toast.makeText(getApplicationContext(), getString(R.string.Arrogant), Toast.LENGTH_LONG).show();
    }
}
