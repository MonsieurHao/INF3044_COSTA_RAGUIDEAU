package org.esiea.costa_raguideau.projetmobile_costa_raguideau;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class telecharge_bieres extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String plus_de_bieres = "org.esiea.costa_raguideau.projetmobile_costa_raguideau.action.FOO";
    private static String tag ="telecharge_biere";


    public telecharge_bieres() {
        super("telecharge_bieres");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void  debut_plus_de_bieres(Context context) {
        Intent intent = new Intent(context, telecharge_bieres.class);
        intent.setAction( plus_de_bieres);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (plus_de_bieres.equals(action)) {
                handle_plus_de_bieres();
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handle_plus_de_bieres () {
        Log.i(tag,"handle_plus_de_bieres" +Thread.currentThread().getName());
        URL url =null;
        try {
            url = new URL ("http://binouze.fabrigli.fr/bieres.json");
            HttpURLConnection conn =(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            if (HttpURLConnection.HTTP_OK == conn.getResponseCode()){
                copyInputStreamToFile(conn.getInputStream(),
                        new File(getCacheDir(),"bieres.json"));
                Log.i(tag,"Biere json downloaded !");
                LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Petite_soif.maj_biere));
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private void copyInputStreamToFile(InputStream in, File file) {
        try{
            OutputStream out =new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
