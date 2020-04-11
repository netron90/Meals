package com.personnalize_design.meals.broadcasts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.personnalize_design.meals.constants.Mutils;
import com.personnalize_design.meals.services.NotifyUserService;

public class StartNotifyUserService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Mutils.ACTION_SEND_BROADCAST)){
            Intent startCheckTimeService = new Intent(context, NotifyUserService.class);
            context.startService(startCheckTimeService);
        }
    }
}
