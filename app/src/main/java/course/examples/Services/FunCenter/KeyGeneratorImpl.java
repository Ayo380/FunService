//Name: Ayokunle Olugboyo


package course.examples.Services.FunCenter;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import course.examples.Services.KeyCommon.KeyGenerator;
import course.examples.Services.KeyService.R;

//KeyGeneratorServiceApp
public class KeyGeneratorImpl extends Service {

    // Set of already assigned ID
    // and restarted.

    @Override
    public void onCreate() {

        super.onCreate();
        Log.i("GeneratorService", "Servive was created!") ;
    }

    MediaPlayer mediaPlayer;
    Context context = this;
    Bitmap bitmap = null;

    // Implement the Stub for this Object
    private final KeyGenerator.Stub mBinder = new KeyGenerator.Stub() {

        @Override
        public Bitmap getBitmaps(int id) throws RemoteException {

            if (id == 1){
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hazard);
            }
            else if (id == 2){
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.drogba);
            }
            else if (id == 3){
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.champions2);
            }


            synchronized (bitmap) {

                return bitmap;
            }


        }
        // the API that plays the clip
        @Override
        public void playclip(int id) throws RemoteException {


            // if mediaPlayer is not null stop whatever it is doing
            stopclip();

            if (id == 1){
                mediaPlayer = MediaPlayer.create(context, R.raw.davido);
            }
            else if (id == 2){
                mediaPlayer = MediaPlayer.create(context, R.raw.rema);
            }
            else if (id ==3){
                mediaPlayer = MediaPlayer.create(context, R.raw.zino);
            }

            mediaPlayer.start();
        }

        @Override
        public void pauseclip() throws RemoteException {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        }

        @Override
        public void resumeclip() throws RemoteException {
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        }

        @Override
        public void stopclip() throws RemoteException {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
    };

    // Return the Stub defined above
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy() ;
        Log.i("GeneratorService", "Service went away!") ;
    }

}
