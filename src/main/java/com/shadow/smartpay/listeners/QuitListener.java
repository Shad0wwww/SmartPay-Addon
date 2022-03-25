package com.shadow.smartpay.listeners;

import com.shadow.smartpay.SmartPay;
import net.labymod.utils.Consumer;
import net.labymod.utils.ServerData;


public class QuitListener implements Consumer<ServerData> {
    public void accept(ServerData serverData) {
        SmartPay.connectedToSuperawesome = false;
        SmartPay.saserver = false;
    }
}

