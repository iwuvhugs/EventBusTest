package com.iwuvhugs.eventbustest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ChargingReciever extends BroadcastReceiver {

    private EventBus bus = EventBus.getDefault();

    @Override
    public void onReceive(Context context, Intent intent) {
        ChargingEvent event = null;

        Calendar calendar = Calendar.getInstance();
        final String timeOfEvent = new SimpleDateFormat("hh:mm:ss").format(calendar.getTime());
        final String eventData = "@" + timeOfEvent + " this device started ";
        if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)) {
            event = new ChargingEvent(eventData + " charging");
        } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            event = new ChargingEvent(eventData + " discharging");
        }

        bus.post(event);
    }
}
