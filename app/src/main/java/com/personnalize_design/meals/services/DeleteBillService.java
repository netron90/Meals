package com.personnalize_design.meals.services;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import com.personnalize_design.meals.R;
import com.personnalize_design.meals.data.DataManager;
import com.personnalize_design.meals.di.App;
import com.personnalize_design.meals.di.ServiceComponent;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;

import static com.personnalize_design.meals.constants.Mutils.ACTION_SEND_BROADCAST;

public class DeleteBillService extends IntentService {

    @Inject
    DataManager mDataManager;
    private ServiceComponent mServiceComponent;
    private Disposable disposable;
    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;


    public DeleteBillService() {
        super("MyDelelteBillOrder");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mServiceComponent = ((App)getApplication ()).getServiceComponent ();
        mServiceComponent.inject ( this );


    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("SERVICE DELETE", "Delete Service Start");
        if(!mDataManager.isDeleteListUserMeals()){
            mDataManager.deleteAllUserOrderMealList()
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            disposable = d;
                        }

                        @Override
                        public void onComplete() {
                            if(disposable != null)
                            {
                                disposable.dispose();
                                disposable = null;
                            }
                            mDataManager.setDeleteListUserMeals(true);
                            deleteAllUserData();
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                            startDeleteBillServiceAgain();
                        }
                    });
        }else {
            deleteAllUserData();
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(disposable != null && !disposable.isDisposed())
        {
            disposable.dispose();
        }
    }

    private void deleteAllUserData() {
        mDataManager.deleteAllUserOrder()
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onComplete() {
                        if(disposable != null)
                        {
                            disposable.dispose();
                            disposable = null;
                        }
                        mDataManager.setDeleteListUserMeals(false);
                        mDataManager.setDeliveryOrderState(true);
                        mDataManager.setMealOrderExist(false);
                        mDataManager.setFragmentStateToShow(0);
                        //TODO: SEND NOTIFICATION TO INFORM USER THAT MEAL QUITANCE HAS BEEN DELETED
                        sendNotification();

                        //TODO:// SEND BROADCAST RECEIVER TO START SERVICE CHECK TIME
                        if(!mDataManager.isServiceCheckOrderTime()){
                            Intent checkTimeService = new Intent(ACTION_SEND_BROADCAST);
                            sendBroadcast(checkTimeService);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        startDeleteBillServiceAgain();
                    }
                });
    }

    private void startDeleteBillServiceAgain ( ) {
        alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent intent1 = new Intent(this, DeleteBillService.class);
        alarmIntent = PendingIntent.getService (this, 0, intent1, 0);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        30, alarmIntent);
    }

    private void sendNotification(){
        Intent intent = new Intent(this, MainScreenActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, getResources ().getString( R.string.channel_id))
                .setSmallIcon( R.drawable.notification)
                .setContentTitle(getResources ().getString (R.string.validity_meal_order_title_notification))
                .setContentText( getResources ( ).getString ( R.string.validity_meal_order_body_notification ))
                .setPriority( NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1, builder.build());
    }
}
