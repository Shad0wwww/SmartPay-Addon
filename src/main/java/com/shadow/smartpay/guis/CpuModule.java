package com.shadow.smartpay.guis;


import com.shadow.smartpay.SmartPay;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;

import net.labymod.ingamegui.ModuleCategory;
import net.labymod.ingamegui.moduletypes.SimpleModule;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.utils.Material;


public class CpuModule extends SimpleModule {
    private final OperatingSystemMXBean os = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();

    public CpuModule(SmartPay smartPay) {
    }

    public String getDisplayName() {
        return "CPU";
    }

    public String getDisplayValue() {
        return (int)(this.os.getSystemCpuLoad() * 100.0D) + "%";
    }

    public String getDefaultValue() {
        return String.valueOf(0);
    }

    public IconData getIconData() {
        return new IconData(Material.PAPER);
    }

    public void loadSettings() {
    }

    public String getSettingName() {
        return "CPU Usage";
    }

    public String getDescription() {
        return "Dit cpu usage";
    }

    public int getSortingId() {
        return 0;
    }

    public String getControlName() {
        return "Cpu Usage";
    }

    public boolean isShown() {
        return true;
    }

    public ModuleCategory getCategory() {
        return SmartPay.CATEGORY_SMARTPAY;
    }
}

