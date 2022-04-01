package com.shadow.smartpay.plugins;

import net.labymod.utils.Material;

public class Plugin {
    private String name;
    private final Pluginlist plugin;
    private Material material;
    private String saName;

    public Plugin(){
        name = "Skbee";
        plugin = Pluginlist.SKBEE;
        material = Material.PAPER;
        saName = "Skbee.jar";
    }
    public Plugin(Pluginlist plugin,Material material, String name,String saName) {
        this.name = String.valueOf(name);
        this.material = material;
        this.plugin = plugin;
        this.saName = saName;
    }


    public Pluginlist getPlugin(){
        return this.plugin;
    }
    public Material getMaterial(){
        return this.material;
    }

    public void setMaterial(Material material){
        this.material = material;
    }



    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSaName(){
        return this.saName;
    }

    public void setSaName(String saName){
        this.saName = saName;
    }
}
