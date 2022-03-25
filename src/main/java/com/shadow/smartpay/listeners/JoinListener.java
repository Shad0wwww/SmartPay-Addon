package com.shadow.smartpay.listeners;


import com.shadow.smartpay.SmartPay;
import com.shadow.smartpay.api.validateVersion;
import net.labymod.main.LabyMod;
import net.labymod.utils.Consumer;
import net.labymod.utils.ModColor;
import net.labymod.utils.ServerData;
import net.minecraft.client.Minecraft;

import static com.shadow.smartpay.SmartPay.addon;
import static com.shadow.smartpay.SmartPay.lobby;




public class JoinListener implements Consumer<ServerData> {



    public void accept(ServerData serverData) {
        if (!SmartPay.isValidVersion)
            try {
                SmartPay.isValidVersion = validateVersion.isValidVersion("1.1");
            } catch (Exception e) {
                e.printStackTrace();
            }

        if (serverData.getIp().toLowerCase().contains("superawesome")) {
            SmartPay.connectedToSuperawesome = true;
            LabyMod.getInstance().displayMessageInChat(ModColor.cl("8") + ModColor.cl("l") + "[ " + ModColor.cl("a") + ModColor.cl("l") + "SmartPay " + ModColor.cl("8") + ModColor.cl("l") + "]" + ModColor.cl("a") + " Tilsluter superawesome, aktivere...");




        } else {
            SmartPay.connectedToSuperawesome = false;
            LabyMod.getInstance().displayMessageInChat(ModColor.cl("8") + ModColor.cl("l") + "[ " + ModColor.cl("a") + ModColor.cl("l") + "SmartPay " + ModColor.cl("8") + ModColor.cl("l") + "]" + ModColor.cl("c") + " Tilsluter " + serverData.getIp() + ", deaktivere...");
        }
        if (!SmartPay.isValidVersion)
            LabyMod.getInstance().displayMessageInChat(ModColor.cl("8") + ModColor.cl("l") + "[ " + ModColor.cl("a") + ModColor.cl("l") + "SmartPay " + ModColor.cl("8") + ModColor.cl("l") + "]" + ModColor.cl("c") + " Din nuvversion af transporter addon er udldownload den seneste version fra vores discord. https://addonsmartpay.netlify.app/ ");
        }
    }

