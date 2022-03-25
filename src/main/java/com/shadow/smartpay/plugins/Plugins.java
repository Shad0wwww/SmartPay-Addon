package com.shadow.smartpay.plugins;


public class Plugins {
    private Plugin[] allItems = new Plugin[35];


    public Plugins() {
        try {
            allItems[0] = new Plugin(Pluginlist.SKBEE, "Skbee");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}