package com.personnalize_design.meals.services;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.util.Log;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.data.model.ServerResponse;
import com.personnalize_design.meals.di.App;
import com.personnalize_design.meals.di.ServiceComponent;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

import static com.personnalize_design.meals.constants.Mutils.EVERY_15_SEC;
import static com.personnalize_design.meals.constants.Mutils.MIN_IN_MIL_SEC_CONSTANT;
import static com.personnalize_design.meals.constants.Mutils.ONE_HOUR_IN_MIL_SEC_CONSTANT;

public class CheckSuggestionTime extends IntentService {

    @Inject
    DataManager mDataManager;
    private ServiceComponent mServiceComponent;
    private Disposable disposable;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    private Calendar c;
    private long today;

    public CheckSuggestionTime() {
        super("Notify User Service");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mServiceComponent = ((App)getApplication ()).getServiceComponent ();
        mServiceComponent.inject ( this );
        c = Calendar.getInstance();
        // and get that as a Date
        today = c.getTimeInMillis();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.d("SERVICE NOTIFY USER", "Service Notify User Start");

        //TODO: CHECK IF IT IS TOMOROW



        Log.d("IS TODAY FUNCTION CHECK", "Verification... Encore aujourdhui");
        //TODO: IF IT IS TODAY DATE
        if(DateUtils.isToday(today)){
            Log.d("IS TODAY FUNCTION", "Oui c'est encore aujourdhui");
            //TODO: RESTART SERVICE AFTER ONE HOUR
            restartService();
        }else{
            mDataManager.setSendSuggestion(false);
            if(isNetworkAvailable()){
                mDataManager.reqCheckSuggestionTime()
                        .subscribe(new SingleObserver<ServerResponse>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                disposable = d;
                            }

                            @Override
                            public void onSuccess(ServerResponse serverResponse) {
                                if(serverResponse.getMessage().equals("Il n'est pas encore l'heure")){
                                    Log.d("SERVICE NOTIFY USER", "It's not time. Restart  Service");
                                    restartService();
                                }else{
                                    Log.d("SERVICE NOTIFY USER", "It's time. Send Notification");
                                    mDataManager.setSendSuggestion(false);
                                    sendNotificationToSuggestMeal();
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
        Intent restartService = new Intent(this, CheckSuggestionTime.class);
        alarmIntent = PendingIntent.getService (this, 0, restartService, 0);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        EVERY_15_SEC, alarmIntent);
    }

    //TODO: RESTART SERVICE EVERY 1 hour
    private void restartService() {

        //TODO: RESTART SERVICE EVERY 1 hour
        alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent restartService = new Intent(this, CheckSuggestionTime.class);
        alarmIntent = PendingIntent.getService (this, 0, restartService, 0);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                      ONE_HOUR_IN_MIL_SEC_CONSTANT, alarmIntent);
    }

    private void sendNotificationToSuggestMeal(){
        mDataManager.setFragmentStateToShow(4);
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
