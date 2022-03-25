package com.shadow.smartpay.listeners;

import com.shadow.smartpay.SmartPay;
import com.shadow.smartpay.api.DiscordWebhook;
import com.shadow.smartpay.utils.GetUUID;

import net.labymod.api.events.MessageReceiveEvent;
import net.labymod.main.LabyMod;
import net.minecraft.client.Minecraft;

import java.awt.*;
import java.io.IOException;


import static com.shadow.smartpay.SmartPay.*;



public class MessageRecive implements MessageReceiveEvent {
    SmartPay smartPay;
    private boolean skrevet;



    public MessageRecive(SmartPay smartPay) {
        this.smartPay = smartPay;

    }


    public boolean onReceive(String s, String s1) {

        if (!SmartPay.dineems)
            return false;
        String[] splitList = s1.split(" ", 99999);

        String uuid1 = GetUUID.getUUID(splitList[1]);


        System.out.println("Nydt1");
        if (s.toLowerCase().contains("god disabled by *console*.")) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/bal");
            smartPay.antalKr = 0;

        }


        if (s1.startsWith("[Money] Balance:")) {
            if (!gemmerbal) {
                return false;
            }
            if (!typedEms) {
                return false;
            }
            String[] splitList2 = s1.split(" ", 99999);
            double a = Math.round(Float.parseFloat(splitList2[2]));
            double b = smartPay.antalKr + a;
            smartPay.antalKr = b;
            saveMoney = Double.parseDouble(String.valueOf(smartPay.antalKr));
            this.smartPay.configSave();
            return false;
        }
        if (s1.startsWith("[Money] You have sent ")) {
            String[] splitList1 = s1.split(" ", 99999);
            LabyMod.getInstance().displayMessageInChat("§8§l[  §a§lSmartPay §8§l] §fDu sendste" + " " + splitList1[4] + " " + "§ftil" + " " + splitList1[7]);
            double a = Math.round(Float.parseFloat(splitList1[4]));
            double b = smartPay.antalKr - a;
            smartPay.antalKr = b;
            saveMoney = Double.parseDouble(String.valueOf(smartPay.antalKr));
            this.smartPay.configSave();
            System.out.println("Penge " + b); // Penge 1.0
            System.out.println("SaveMoney " + saveMoney); // Penge 1.0
            System.out.println("Penge " + smartPay.antalKr); //Penge 0 fra
            return true;
        }

        if (s1.startsWith("[Money]")) {


            System.out.println("Nydt2");
            System.out.println("Penge: " + discordweb);
            System.out.println("Player: " + splitList[1]);
            System.out.println("/msg " + splitList[1] + " " + lobby);
            System.out.println( splitList[5]);
            if (!SmartPay.autojoin) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/msg " + splitList[1] + " " + lobby);
                LabyMod.getInstance().displayMessageInChat("§8§l[  §a§lSmartPay §8§l] §fDu modtog" + " " + splitList[5] + " " + "§ffra" + " " + splitList[1]);
                return false;
            }
            LabyMod.getInstance().displayMessageInChat("§8§l[  §a§lSmartPay §8§l] §fDu modtog" + " " + splitList[5] + " " + "§ffra" + " " + splitList[1]);
            //Fix

            System.out.println("Den er nået så langt");



            double a = Math.round(Float.parseFloat(splitList[5]));

            double b = smartPay.antalKr + a;

            smartPay.antalKr = b;
            saveMoney = Double.parseDouble(String.valueOf(smartPay.antalKr));
            this.smartPay.configSave();
            System.out.println("Penge " + b); // Penge 1.0
            System.out.println("SaveMoney " + saveMoney); // Penge 1.0
            System.out.println("Penge " + smartPay.antalKr + " fra " + splitList[2]); //Penge 0 fra



            //fix ^^
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/msg " + splitList[1] + " " + lobby);

            DiscordWebhook webhook = new DiscordWebhook(discordweb);
            webhook.setTts(false);
            webhook.addEmbed((new DiscordWebhook.EmbedObject()).setTitle("**Smartpay - System**").setColor(Color.RED).addField("**UUID**", uuid1, true).addField("**Ems**", s1, false).setThumbnail("https://mc-heads.net/body/" + splitList[1]));
            try {
                webhook.execute();
            } catch (IOException var9) {
                var9.printStackTrace();
            }
            return true;
        }


        return false;
    }




}
