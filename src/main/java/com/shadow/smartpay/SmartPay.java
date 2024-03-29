package com.shadow.smartpay;


import com.shadow.smartpay.guis.Bal;
import com.shadow.smartpay.guis.CpuModule;
import com.shadow.smartpay.guis.SmPlugins;
import com.shadow.smartpay.listeners.*;

import java.util.List;

import com.shadow.smartpay.plugins.Pluginlist;
import com.shadow.smartpay.plugins.Plugins;
import com.shadow.smartpay.settingelements.DescribedBooleanElement;
import net.labymod.api.LabyModAddon;


import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.ModuleCategoryRegistry;
import net.labymod.main.LabyMod;
import net.labymod.settings.elements.*;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;
import net.labymod.utils.ModColor;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;


public class SmartPay extends LabyModAddon {
    public static final ModuleCategory CATEGORY_SMARTPAY = new ModuleCategory("SmartPay", true, new ControlElement.IconData(Material.EMERALD_BLOCK));

    public static boolean connectedToSuperawesome;

    public static boolean enabled;

    public double antalKr;

    public double startKr;

    public static boolean gemmerbal;

    public static boolean saserver;

    public static boolean isValidVersion;

    public static SmartPay addon;

    private Integer smAddplugKeybind;

    private static boolean smAddPlug;

    public static double saveMoney;

    public static boolean autojoin;

    public int executeState;

    public boolean smartpayaddplugins;

    private Integer timer = 0;

    public static boolean dineems;

    public  static boolean typedEms;

    public static String discordweb = "Din Discord Webhook skal være! Husk ikke vis den til andre!";

    public String emeralder = "4,143,784.98";

    public static String lobby = "Tak for ems";

    private boolean executeCommands;

    private int delay;

    public Plugins plugins;
    private Boolean checkplugins;

    public Boolean getCheckPlugins(){
        return this.checkplugins;
    }
    public Plugins getPlugins(){
        return this.plugins;
    }

    @Override
    public void onEnable() {

        ModuleCategoryRegistry.loadCategory(CATEGORY_SMARTPAY);
        isValidVersion = false;
        addon = this;
        plugins = new Plugins();

        System.out.println("============================================");
        System.out.println("    Activate SmartPay Addon for LabyMod     ");
        System.out.println("============================================");
        this.getApi().registerForgeListener(this);
        this.getApi().getEventManager().registerOnJoin(new JoinListener());
        this.getApi().getEventManager().registerOnQuit(new QuitListener());
        LabyMod.getInstance().getEventManager().register(new MessageSendListener(this));

        LabyMod.getInstance().getEventManager().register(new OnMessageRecive(this));

        LabyMod.getInstance().getEventManager().register(new MessageRecive(this));



        getApi().registerModule(new CpuModule(this));
        getApi().registerModule(new Bal(this));
    }



    public static SmartPay getAddon() {
        return addon;
    }
    @Override
    public void loadConfig() {
        this.emeralder = getConfig().has("emsBal") ? getConfig().get("emsBal").getAsString() : "4,143,784.98";
        lobby = getConfig().has("Beskeden der skal sende:") ? getConfig().get("Beskeden der skal sende:").getAsString() : "Tak for du sendte ems";

        discordweb = getConfig().has("Discord webhook") ? getConfig().get("Discord webhook").getAsString() : "Din Discord Webhook skal være her! Husk ikke vis den til andre!";

        dineems = getConfig().has("Logger ems") ? Boolean.parseBoolean(getConfig().get("Logger ems").getAsString()) : dineems;
        enabled = (!getConfig().has("enabled") || getConfig().get("enabled").getAsBoolean());
        autojoin = getConfig().has("Slå autobesked til") && getConfig().get("Slå autobesked til").getAsBoolean();
        gemmerbal = getConfig().has("Registere antal ems!") ? Boolean.parseBoolean(getConfig().get("Registere antal ems!").getAsString()) : gemmerbal;

        smAddPlug = getConfig().has("Sm addplugin") && getConfig().get("Sm addplugin").getAsBoolean();
        this.smAddplugKeybind = getConfig().has("lobbySelecterKeybind") ? getConfig().get("lobbySelecterKeybind").getAsInt() : 21;


        this.antalKr = getConfig().has("savebal") ? getConfig().get("savebal").getAsDouble() : 0;
    }

    private Boolean getPluginConfig(String plugin){
        return getConfig().has( plugin ) ? getConfig().get( plugin  ).getAsBoolean() : true;
    }
    public void configSave() {
        getConfig().addProperty("lobbySelecterKeybind", this.smAddplugKeybind);
        getConfig().addProperty("savebal", antalKr);
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        //Overskrift
        list.add(new HeaderElement(ModColor.cl("a") + ModColor.cl("l") + "SMARTPAY " + ModColor.cl("7") + ModColor.cl("l") + "SETTINGS"));//Overskrift ^^

        BooleanElement booleanElement = new BooleanElement("Fake Ems", new ControlElement.IconData(Material.EMERALD), enabled -> {
            SmartPay.enabled = enabled;
            getConfig().addProperty("enabled", enabled);
            saveConfig();
        }, enabled);




        StringElement customChat3 = new StringElement("Antal ems", new ControlElement.IconData(Material.PAPER), this.emeralder, accepted -> {
            this.emeralder = accepted;
            getConfig().addProperty("emsBal", accepted);

        });
        list.add(booleanElement);
        list.add(customChat3);
        list.add(new HeaderElement(ModColor.cl("a") + " "));



        ListContainerElement AutoMessage = new ListContainerElement(ModColor.cl("f") + "Sender automatisk en besked", new ControlElement.IconData(Material.BOOK_AND_QUILL));
        AutoMessage.getSubSettings().add(new HeaderElement(ModColor.cl("a") + "Sender automatisk en besked"));

        BooleanElement autotak = new BooleanElement("Slå autobesked til", new ControlElement.IconData(Material.STONE_BUTTON), autojoin -> {
            SmartPay.autojoin = autojoin;
            getConfig().addProperty("Slå autobesked til", autojoin);
            saveConfig();
        }, autojoin);
        AutoMessage.getSubSettings().add((SettingsElement)autotak);

        StringElement customChat4 = new StringElement("Beskeden der skal sende:", new ControlElement.IconData(Material.PAPER), lobby, lobby -> {
            SmartPay.lobby = lobby;
            getConfig().addProperty("Beskeden der skal sendes:", lobby);

        });
        AutoMessage.getSubSettings().add((SettingsElement)customChat4);

        ListContainerElement Webhooklist = new ListContainerElement(ModColor.cl("f") + "Discord Webhook", new ControlElement.IconData(Material.BOOK));
        Webhooklist.getSubSettings().add(new HeaderElement(ModColor.cl("2") + "Discord Webhook"));


        BooleanElement checkerems = new BooleanElement("Logger ems", new ControlElement.IconData(Material.LEVER), dineems -> {
            SmartPay.dineems = dineems;
            getConfig().addProperty("Logger ems", dineems);
            saveConfig();
        }, dineems);
        Webhooklist.getSubSettings().add((SettingsElement)checkerems);

        StringElement customChat2 = new StringElement("Discord Webhook", new ControlElement.IconData(Material.PAPER), discordweb, discordweb -> {
            SmartPay.discordweb = discordweb;
            getConfig().addProperty("Discord Webhook", discordweb);

        });
        Webhooklist.getSubSettings().add((SettingsElement)customChat2);


        ListContainerElement Superbal = new ListContainerElement(ModColor.cl("f") + "Registere", new ControlElement.IconData(Material.BOOK));
        Superbal.getSubSettings().add(new HeaderElement(ModColor.cl("f") + "Registere antal ems du har"));
        Superbal.getSubSettings().add(new HeaderElement(ModColor.cl("f") + "Og gemmer det, lige som fps"));


        BooleanElement Gemmerems = new BooleanElement("Registere antal ems!", new ControlElement.IconData(Material.LEVER), gemmerbal -> {
            SmartPay.gemmerbal = gemmerbal;
            getConfig().addProperty("Registere antal ems!", gemmerbal);
            saveConfig();
        }, gemmerbal);
        Superbal.getSubSettings().add((SettingsElement)Gemmerems);


        ListContainerElement Smadd = new ListContainerElement(ModColor.cl("a") + ModColor.cl("l") + "Add plugin!", new ControlElement.IconData(Material.BOOK));
        Smadd.getSubSettings().add(new HeaderElement(ModColor.cl("f") + "Skriver automatisk alle commands"));
        Smadd.getSubSettings().add(new HeaderElement(ModColor.cl("f") + "Som du skal bruge på din sa server"));

        BooleanElement Addplugins = new BooleanElement("Add plugin!", new ControlElement.IconData(Material.DIAMOND_PICKAXE), addplugins -> {
            SmartPay.smAddPlug = addplugins;
            getConfig().addProperty("Add plugin!", addplugins);
            saveConfig();
        }, smAddPlug);

        KeyElement SmAddPluginKeyElement = new KeyElement("Keybind", new ControlElement.IconData(Material.WOOD_BUTTON), this.smAddplugKeybind, new Consumer<Integer>() {
            public void accept(Integer accepted) {
                if (accepted < 0) {
                    System.out.println("Set new key to NONE");
                    SmartPay.this.smAddplugKeybind = -1;
                    SmartPay.this.configSave();
                    return;
                }
                System.out.println("Set new key to " + Keyboard.getKeyName(accepted));
                SmartPay.this.smAddplugKeybind = accepted;
                SmartPay.this.configSave();
            }
        });
        Smadd.getSubSettings().add((SettingsElement)Addplugins);
        Smadd.getSubSettings().add((SettingsElement)SmAddPluginKeyElement);
        Smadd.getSubSettings().add(new HeaderElement(ModColor.cl("a") + " "));
        Pluginlist plugins[] = Pluginlist.values();


        for(Pluginlist plugin : plugins) {
            Boolean bool = getPluginConfig(plugin.toString());
            ControlElement.IconData iconData = this.plugins.getIconData(plugin);
            String name = this.plugins.getName(plugin);

            DescribedBooleanElement itemElement = new DescribedBooleanElement(name, this, iconData, plugin.toString(), bool, "Slå denne til " + name + " for at adde plugines.");
            Smadd.getSubSettings().add(itemElement);
        }



        list.add(Smadd);
        list.add(Superbal);
        list.add(AutoMessage);
        list.add(Webhooklist);


    }





    private Integer saveTimer=0;

    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) throws Exception {
        saveTimer++;
        if(saveTimer >= 1000){
            saveTimer = 0;
            saveMoney = Double.parseDouble(String.valueOf(addon.antalKr));
            saveConfig();
        }


        if(!saserver){executeCommands = false; LabyMod.getInstance().displayMessageInChat(ModColor.cl("c") + "Vi stoppede med at tilføje plugins."); saserver = true; return;}
        if(!connectedToSuperawesome){ return; }
        if(!isValidVersion){ return; }


        if (smartpayaddplugins){
            if(!executeCommands) {
                executeCommands = true;
                timer = 0;
                executeState = 0;
            }
        }
        if (executeCommands) {
            if (executeState >= 35) {
                executeCommands = false;
            }
            timer++;
            if (timer >= delay) {
                timer = 0;
                Integer temp = -1;
                Pluginlist plugins[] = Pluginlist.values();
                if(plugins[executeState] != null){
                    while (!getPluginConfig(plugins[executeState].toString())) {
                        if (executeState <= 34) {
                            if (executeState == 1) {
                                Minecraft.getMinecraft().thePlayer.sendChatMessage("/sm addplugin skbee.jar");
                            } else {
                                Minecraft.getMinecraft().thePlayer.sendChatMessage("/sm addplugin " + plugins[executeState].toString());
                            }
                        }
                    }
                }
                if(executeState < 35) {
                    while (!getPluginConfig(plugins[executeState].toString())) {
                        executeState++;
                        if (executeState >= 35) {
                            break;
                        }
                    }
                }else{
                    executeCommands = false;
                }
                if(temp == executeState){
                    executeState++;
                }
            }else{
                executeState++;
            }


            if (executeState >= 35) {
                executeCommands = false;
            }

        }

    }
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        try {
            if (!connectedToSuperawesome)
                return;
            if (!smAddPlug)
                return;
            if (smAddplugKeybind >= 0) {
                if (Keyboard.isKeyDown(smAddplugKeybind)) {
                    Minecraft.getMinecraft().displayGuiScreen(new SmPlugins(addon, executeCommands));
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
