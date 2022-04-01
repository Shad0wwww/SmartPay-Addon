package com.shadow.smartpay.plugins;


import net.labymod.settings.elements.ControlElement;
import net.labymod.utils.Material;

public class Plugins{
    private final Plugin[] allPlugin = new Plugin[35];


    public Plugins() {
        try {
            allPlugin[0] = new Plugin(Pluginlist.SKBEE, Material.PAPER, "Skbee.jar", "Skbee.jar");
            allPlugin[1] = new Plugin(Pluginlist.SKRIPT, Material.BOOK_AND_QUILL, "Skript-1.8", "Skript-1-8.jar");
            allPlugin[2] = new Plugin(Pluginlist.WORLDEDIT, Material.WOOD_BUTTON, "Worldedit.jar", "Worldedit.jar");
            allPlugin[3] = new Plugin(Pluginlist.WORLDGUARD, Material.PAPER, "Worldguard.jar", "Worldguard.jar");
            allPlugin[4] = new Plugin(Pluginlist.skriptreflect, Material.PAPER, "skript-reflect.jar", "skript-reflect.jar");
            allPlugin[5] = new Plugin(Pluginlist.EssentialsGroupManager, Material.PAPER, "EssentialsGroupManager.jar", "EssentialsGroupManager.jar");
            allPlugin[6] = new Plugin(Pluginlist.EssentialsSpawn, Material.PAPER, "EssentialsSpawn.jar", "EssentialsSpawn.jar");
            allPlugin[7] = new Plugin(Pluginlist.FastAsyncWorldEdit, Material.PAPER, "FastAsyncWorldEdit.jar", "FastAsyncWorldEdit.jar");
            allPlugin[8] = new Plugin(Pluginlist.HamsterAPI, Material.PAPER, "HamsterAPI.jar", "HamsterAPI.jar");
            allPlugin[9] = new Plugin(Pluginlist.HeadDatabase, Material.PAPER, "HeadDatabase.jar", "HeadDatabase.jar");
            allPlugin[10] = new Plugin(Pluginlist.HolographicDisplaysv2, Material.PAPER, "Holographicdisplays.jar", "Holographicdisplays.jar");
            allPlugin[11] = new Plugin(Pluginlist.LabyApi, Material.PAPER, "LabyApi.jar", "LabyApi.jar");
            allPlugin[12] = new Plugin(Pluginlist.LabymodSK, Material.PAPER, "LabymodSK.jar", "LabymodSK.jar");

        } catch (Exception err) {
            System.out.println(err);
        }
    }
    public Material getMaterial(Pluginlist plugin){
        for(Plugin i : allPlugin) {
            if(i.getPlugin().toString().equals(plugin.toString())){
                return i.getMaterial();
            }
        }
        return Material.BARRIER;
    }

    public String getName(Pluginlist plugin){
        for(Plugin i : allPlugin) {
            if(i.getPlugin().toString().equals(plugin.toString())){
                return i.getName();
            }
        }
        return "Fejl";
    }
    public ControlElement.IconData getIconData(Pluginlist Plugin){
        Material material = getMaterial(Plugin);
        return new ControlElement.IconData( material );
    }


    public Plugin[] getAllPlugin(){
        return this.allPlugin;
    }

}