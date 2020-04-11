package com.personnalize_design.meals.services;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.util.Log;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.data.model.ServerResponse;
import com.personnalize_design.meals.di.App;
import com.personnalize_design.meals.di.ServiceComponent;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.personnalize_design.meals.constants.Mutils.EVERY_15_SEC;
import static com.personnalize_design.meals.constants.Mutils.MIN_IN_MIL_SEC_CONSTANT;

public class NotifyUserService extends IntentService {

    @Inject
    DataManager mDataManager;
    private ServiceComponent mServiceComponent;
    private Disposable disposable;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    public NotifyUserService() {
        super("Notify User Service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mServiceComponent = ((App)getApplication ()).getServiceComponent ();
        mServiceComponent.inject ( this );
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d("SERVICE NOTIFY USER", "Service Notify User Start");
        if(isNetworkAvailable()){
            //TODO: CHECK IF IT IS HOUR TO PASS ORDER
            mDataManager.reqCheckTime()
                    .subscribe(new SingleObserver<ServerResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onSuccess(ServerResponse serverResponse) {
                            if(disposable != null){
                                disposable.dispose();
                                disposable = null;

                            }

                            if(serverResponse.getMessage().equals("Il n'est pas encore l'heure")){
                                Log.d("SERVICE NOTIFY USER", "It's not time. Restart  Service");
                                restartService();
                            }else{
                                Log.d("SERVICE NOTIFY USER", "It's time. Send Notification");
                                mDataManager.setServiceCheckOrderTime(false);
                                sendNotificationToPassOrder();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            restartService();
                        }
                    });
        }else{
            //TODO: RETRY THIS SERVICE
            restartService15sec();
        }

    }


    private boolean isNetworkAvailable ( ) {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private void restartService15sec() {

        //TODO: RESTART SERVICE EVERY 15 sec
        alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent restartService = new Intent(this, NotifyUserService.class);
        alarmIntent = PendingIntent.getService (this, 0, restartService, 0);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        EVERY_15_SEC, alarmIntent);
    }

    //TODO: RESTART SERVICE EVERY 20 min
    private void restartService() {

        //TODO: RESTART SERVICE EVERY 15 sec
        alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent restartService = new Intent(this, NotifyUserService.class);
        alarmIntent = PendingIntent.getService (this, 0, restartService, 0);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                      MIN_IN_MIL_SEC_CONSTANT, alarmIntent);
    }

    private void sendNotificationToPassOrder(){
        Intent intent = new Intent(this, MainScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getResources ().getString( R.string.channel_id))
                .setSmallIcon( R.drawable.notification)
                .setContentTitle(getResources ().getString (R.string.it_is_time))
                .setContentText( getResources ( ).getString ( R.string.it_is_time_text ))
                .setPriority( NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }
}
