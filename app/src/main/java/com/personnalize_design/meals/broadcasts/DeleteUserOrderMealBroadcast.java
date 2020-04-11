package com.personnalize_design.meals.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.personnalize_design.meals.services.DeleteBillService;

import static com.personnalize_design.meals.constants.Mutils.ACTION_TIME_DELETE_USER_ORDER;

public class DeleteUserOrderMealBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION_TIME_DELETE_USER_ORDER)){
            if(context != null){
                Intent deleteMealOrdeService = new Intent(context, DeleteBillService.class);
                context.startService(deleteMealOrdeService);
            }
        }
    }
}
