package com.shadow.smartpay.plugins;

public class Plugin {
    private String name;
    private Pluginlist plugin;

    public Plugin(){
        name = "Skbee";
        plugin = Pluginlist.SKBEE;
    }
    public Plugin(Pluginlist plugin,String name) {
        this.name = String.valueOf(name);
        this.plugin = plugin;
    }


    public Pluginlist getItem(){
        return this.plugin;
    }

    public String getName(){
        return this.name;
    }
}
