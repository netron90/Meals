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
import com.personnalize_design.meals.data.model.UserOrderModel;
import com.personnalize_design.meals.di.App;
import com.personnalize_design.meals.di.ServiceComponent;
import com.personnalize_design.meals.ui.day_menu.MainScreenActivity;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import io.reactivex.functions.Consumer;

import static com.personnalize_design.meals.constants.Mutils.ACTION_TIME_DELETE_USER_ORDER;
import static com.personnalize_design.meals.constants.Mutils.HOUR_IN_MIL_SEC_CONSTANT;
import static com.personnalize_design.meals.constants.Mutils.MIN_IN_MIL_SEC_CONSTANT;

public class CheckMealBillTimeService extends IntentService {

    @Inject
    DataManager mDataManager;

    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;
    private ServiceComponent mServiceComponent;

    public CheckMealBillTimeService() {
        super("MyDeleteBillOrderService");
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mServiceComponent = ((App)getApplication ()).getServiceComponent ();
        mServiceComponent.inject ( this );

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
//        if(!mDataManager.isMealOrderExist()){
//            Log.d("SERVICE CHECK DELETE", "Restart Service Launch");
//            restartService();
//        }else{
//            deleteUserMealOrder();
//        }

        if(mDataManager.isMealOrderExist()){
            deleteUserMealOrder();
        }


    }

    private void restartService() {

        //TODO: RESTART SERVICE EVERY 20 min
        alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        Intent restartService = new Intent(this, CheckMealBillTimeService.class);
        alarmIntent = PendingIntent.getService (this, 0, restartService, 0);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() +
                        MIN_IN_MIL_SEC_CONSTANT, alarmIntent);
    }


    private void deleteUserMealOrder ( ) {


        Date calendar = Calendar.getInstance().getTime();
//        alarmManager = (AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
//        Intent intent1 = new Intent(this, DeleteBillService.class);
//        alarmIntent = PendingIntent.getService (this, 0, intent1, 0);
//        //HOUR_IN_MIL_SEC_CONSTANT

        mDataManager.getUserOrderInfo()
                .subscribe(new Consumer<UserOrderModel.UserOrder>() {
                    @Override
                    public void accept(UserOrderModel.UserOrder userOrder) throws Exception {

                        //Log.d("CURRENT TIME ORDER DEL", "Current time: " + calendar + "\n Time save: " + userOrder.getCurrentMealOrderTime());
                        if(calendar == userOrder.getCurrentMealOrderTime() || calendar.after(userOrder.getCurrentMealOrderTime())){
                           Intent timeToDeleteOrder = new Intent(ACTION_TIME_DELETE_USER_ORDER);
                           sendBroadcast(timeToDeleteOrder);
                        }else{
                            restartService();
                        }
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                    restartService();
                });


    }


}
