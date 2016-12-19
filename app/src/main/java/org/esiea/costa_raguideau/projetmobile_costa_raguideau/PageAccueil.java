
package org.esiea.costa_raguideau.projetmobile_costa_raguideau;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class PageAccueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_acceuil);
        TextView view = (TextView) findViewById(R.id.Intro);
        view.setText(R.string.Intro);
        view = (TextView) findViewById(R.id.BoutonVie);
        view.setText(R.string.BoutonVie);
        view = (TextView) findViewById(R.id.Avis);
        view.setText(R.string.Avis);
        view = (TextView) findViewById(R.id.Next);
        view.setText(R.string.Next);
        this.setTitle(R.string.titre_acceuil);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public void petite_soif (View view){
        Intent intent = new Intent (this, Petite_soif.class);
        startActivity(intent);
    }

    public void arrogant(View v) {
        Toast.makeText(getApplicationContext(), getString(R.string.Arrogant), Toast.LENGTH_LONG).show();
    }

    public void video(View v) {

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=idF0xEMiOvI")));
        Log.i("Video", "Video Playing....");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Donation:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.dialog_message);
                builder.setTitle(R.string.dialog_title);
                builder.setPositiveButton(R.string.ok1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), getString(R.string.merci), Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNeutralButton(R.string.ok3, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), getString(R.string.merci), Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton(R.string.ok2, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(), getString(R.string.merci), Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = builder.create();
                builder.show();
                break;
            default:
                break;
        }
        return true;
    }
}
