package org.esiea.costa_raguideau.projetmobile_costa_raguideau;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.esiea.costa_raguideau.projetmobile_costa_raguideau.R.id.recycler;


public class Petite_soif extends AppCompatActivity {
    public static final String maj_biere = "com.octip.cours.inf4042_11.BIERS_UPDATE";
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petite_soif);
        TextView view = (TextView) findViewById(R.id.titre);
        view.setText(R.string.titre);
        this.setTitle(R.string.titre_recycler);
        telecharge_bieres.debut_plus_de_bieres(this);
        IntentFilter intentFilter = new IntentFilter(maj_biere);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(), intentFilter);
        rv= (RecyclerView) findViewById(recycler);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv.setAdapter(new ElementAdapter(getElementsFromFile()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
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

    public class BierUpdate extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            Log.i("BierUpdate",intent.getAction());
            notification();
            ElementAdapter adapter = (ElementAdapter) rv.getAdapter();
            adapter.setNewElement(getElementsFromFile());
        }
    }

    public void notification(){
        NotificationCompat.Builder notif_t =(NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext()).setSmallIcon(R.drawable.ic_stat_name).setContentText(this.getString(R.string.downloaded)).setContentTitle("download");
        NotificationManager nm=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0,notif_t.build());
    }

    public JSONArray getElementsFromFile(){
        try {
            InputStream is = new FileInputStream(getCacheDir() + "/" + "bieres.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer, "UTF-8"));
        }catch (IOException e){
            e.printStackTrace();
            return new JSONArray();
        }catch(JSONException e){
            e.printStackTrace();
            return new JSONArray();
        }
    }

    private class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.ElementHolder>{

        JSONArray biers;

        public ElementAdapter (JSONArray biers){
            this.biers=biers;
        }

        @Override
        public ElementHolder onCreateViewHolder(ViewGroup parent, int viewType) {
          return new ElementHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_bier_element,parent,false));
        }

        @Override
        public void onBindViewHolder(ElementHolder holder, int position) {
            try {
                holder.name.setText(biers.getJSONObject(position).getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public int getItemCount() {
            return biers.length();
        }

        public  class ElementHolder extends RecyclerView.ViewHolder{

            public TextView name;
            public ElementHolder(View itemView) {
                super(itemView);
                this.name=(TextView) itemView.findViewById(R.id.rv_element_name);
            }
        }

        public void setNewElement(JSONArray biers){
            notifyDataSetChanged();
        }
    }
}


