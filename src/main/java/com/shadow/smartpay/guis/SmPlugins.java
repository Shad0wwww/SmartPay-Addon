package com.shadow.smartpay.guis;

import com.shadow.smartpay.SmartPay;
import net.labymod.gui.elements.Scrollbar;
import net.labymod.main.LabyMod;
import net.labymod.utils.ModColor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class SmPlugins extends GuiScreen {
    private Scrollbar scrollbar = new Scrollbar(18);
    private SmartPay addon;

    private Boolean interacted = false;




    public SmPlugins(SmartPay addon) {
        this.addon = addon;
    }

    public void updateScreen() {

    }

    public void initGui() {
        this.buttonList.clear();

        this.scrollbar.init();
        this.scrollbar.setPosition(this.width / 2 + 122, 44, this.width / 2 + 126, this.height - 32 - 3);

        this.buttonList.add(new GuiButton(9914, this.width / 3, this.height - 300, this.width / 3, 20, "Serverlist"));
        this.buttonList.add(new GuiButton(9913, this.width / 3, this.height - 280, this.width / 3, 20, "Fjern plugins"));
        //this.buttonList.add(new GuiButton(9913, this.width / 3, this.height - 50, this.width / 3, 20, "Afvis"));


    }




    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        System.out.println("ACTION PERFOMED!!!!");
        System.out.println("Clicked: Button with id " + button.id);
        if (button.id == 9914) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/sm serverlist");
        }
        if (button.id == 9913) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/sm delallplugins");
        }
        Minecraft.getMinecraft().thePlayer.closeScreen();
    }
    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        LabyMod.getInstance().getDrawUtils().drawAutoDimmedBackground(this.scrollbar.getScrollY());
        double yPos = 45.0D + this.scrollbar.getScrollY() + 3.0D;

        LabyMod.getInstance().getDrawUtils().drawCenteredString(ModColor.cl("8")+ModColor.cl("l")+"["+ModColor.cl("a")+ModColor.cl("l")+" SMARTPAY "+ModColor.cl("8")+ModColor.cl("l")+"]", this.width / 2, 20, 2);
        LabyMod.getInstance().getDrawUtils().drawCenteredString(ModColor.cl("f")+"Klik her for at fjerne & tilføje plugins.", this.width / 2, 50, 1);


        this.scrollbar.draw();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.scrollbar.mouseAction(mouseX, mouseY, Scrollbar.EnumMouseAction.CLICKED);
    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        this.scrollbar.mouseAction(mouseX, mouseY, Scrollbar.EnumMouseAction.DRAGGING);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        this.scrollbar.mouseAction(mouseX, mouseY, Scrollbar.EnumMouseAction.RELEASED);
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.scrollbar.mouseInput();
    }
}
