package com.shadow.smartpay.guis;



import com.shadow.smartpay.SmartPay;
import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.utils.Material;







public class Bal extends SimpleModule {

    SmartPay smartPay;

    private boolean displayBal;

    public Bal(SmartPay smartPay) {
        this.smartPay = smartPay;
    }


    public String getDisplayName() {
        return "Bal";
    }

    public String getDisplayValue() {

        return smartPay.antalKr + " Ems";
    }

    public String getDefaultValue() {
        return String.valueOf(0);
    }

    public IconData getIconData() {
        return new IconData(Material.EMERALD);
    }

    public void loadSettings() {}

    public String getSettingName() {
        return null;
    }

    public String getDescription() {
        return "Viser din antal ems du har!";
    }

    public int getSortingId() {
        return 0;
    }

    public ModuleCategory getCategory() {
        return SmartPay.CATEGORY_SMARTPAY;
    }
    public String getControlName() {
        return "Din Balance!";
    }
    public boolean isShown() {
        return true;
    }
}


