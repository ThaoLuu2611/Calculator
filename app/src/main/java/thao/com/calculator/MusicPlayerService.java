package thao.com.calculator;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class  MusicPlayerService extends Service {
	MediaPlayer mp;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d("MY TAG","onCreate()");
		 mp = MediaPlayer.create(getApplicationContext(), R.raw.stay);
		
	}
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("MY TAG","onStartCommand()");
		mp.start();
		return super.onStartCommand(intent, flags, startId);
	}
	public void onDestroy() {
		super.onDestroy();
		Log.d("My tag","onDestroy()");
		mp.stop();
		mp.release();
		
	}



}
