package com.shadow.smartpay.listeners;


import com.shadow.smartpay.SmartPay;

import net.labymod.api.events.MessageSendEvent;
import net.labymod.main.LabyMod;
import net.labymod.utils.ModColor;


public class MessageSendListener implements MessageSendEvent {
    public static SmartPay addon;

    public MessageSendListener(SmartPay addon) {
        MessageSendListener.addon = addon;
    }

    public boolean onSend(String s) {
        if (s.toLowerCase().contains("/server") || s.toLowerCase().contains("/hub") || s.toLowerCase().contains("/start")) {
            if (!s.toLowerCase().contains("larmelobby") || !s.toLowerCase().contains("shoppylobby") || !s.toLowerCase().contains("byggelobby") || !s.toLowerCase().contains("creepylobby") || !s.toLowerCase().contains("maskinrummet") || !s.toLowerCase().contains("maskinrummetlight") || !s.toLowerCase().contains("limbo"))
                SmartPay.saserver = false;
            return false;
        }

        if (s.equalsIgnoreCase("/bal") || s.equalsIgnoreCase("/money")) {
            if (!SmartPay.enabled)
                return false;
            if (!SmartPay.connectedToSuperawesome)
                return false;
            if (!SmartPay.saserver)
                return false;
            LabyMod.getInstance().displayMessageInChat(ModColor.DARK_GREEN + "[" + ModColor.WHITE + "Money" + ModColor.DARK_GREEN + "] Balance: " + ModColor.WHITE + addon.emeralder + " Emeralds");
            return true;
        }

        return false;
    }
}