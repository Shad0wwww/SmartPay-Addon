package com.shadow.smartpay.listeners;

import com.shadow.smartpay.SmartPay;
import net.labymod.api.events.MessageReceiveEvent;

import java.util.concurrent.TimeUnit;


public class OnMessageRecive implements MessageReceiveEvent {

    SmartPay smartPay;

    public OnMessageRecive(SmartPay smartPay) {
        this.smartPay = smartPay;
    }



    public boolean onReceive(String s, String s1) {
        if (s.toLowerCase().contains("god disabled by *console*.")) {
            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            SmartPay.saserver = true;
            return false;

        }

        if (s.toLowerCase().contains("sender dig til ")) {
            SmartPay.saserver = false;
            return false;
        }
        return false;
    }
}
