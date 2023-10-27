package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {

    //waits for incoming sms messages
    @Override
    public void onReceive(Context context, Intent intent) {
        //retrieves the bundle from the incoming intent
        final Bundle bundle = intent.getExtras();

        //checks to see if received intent matches the sms message received
        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){


            if(bundle != null){
                //gets the pdus from the extras
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                //gets format of the message
                String format = bundle.getString("format").toString();

                //process the pdu
                for (int i = 0; i < pdusObj.length; i++){
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                    String message = currentMessage.getMessageBody();


                    Intent activityIntent = new Intent(context, MainActivity.class);
                    //adds the message to the extras with sms key
                    activityIntent.putExtra("sms", message);
                    activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    context.startActivity(activityIntent);
                }
            }
        }
    }
}
