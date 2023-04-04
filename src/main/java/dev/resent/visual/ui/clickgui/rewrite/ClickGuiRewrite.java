package dev.resent.visual.ui.clickgui.rewrite;

import java.util.ArrayList;

import dev.resent.client.Resent;
import dev.resent.module.base.Mod;
import dev.resent.module.base.setting.BooleanSetting;
import dev.resent.module.base.setting.Setting;
import dev.resent.util.misc.GlUtils;
import dev.resent.util.render.Color;
import dev.resent.util.render.RenderUtils;
import dev.resent.visual.ui.Theme;
import dev.resent.visual.ui.animation.Animation;
import dev.resent.visual.ui.animation.Direction;
import dev.resent.visual.ui.clickgui.rewrite.comp.Comp;
import dev.resent.visual.ui.clickgui.rewrite.comp.impl.CompCheck;
import net.lax1dude.eaglercraft.v1_8.internal.KeyboardConstants;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;

public class ClickGuiRewrite extends GuiScreen{

	public FontRenderer fr;
    public ArrayList<Comp> comps = new ArrayList<>();
    public float x, y, width, height, moduleOffset;
    public Animation introAnimation;
    public ScaledResolution sr;
    public boolean closing;
    public Mod selectedMod;
    public String searchString = "";
    public int backgroundColor = new Color(18, 18, 18).getRGB(), primaryColor = 0xFF000000, secondaryColor = new Color(33, 33, 33).getRGB(), secondaryFontColor = new Color(187, 134, 252).getRGB();

    @Override
    public void drawScreen(int mouseX, int mouseY, float var3) {
    	
    	int offset = 0;
    	
        GlUtils.startScale((this.x + this.width) / 2, (this.y + this.height) / 2, introAnimation != null ? (float) introAnimation.getValue() : 1);

        //Navigation bar
        RenderUtils.drawRoundedRect(x, y, x+width-60, y+height, 32, secondaryColor);
        
        //Background overlay
        RenderUtils.drawRoundedRect(x+60, y, x+width, y+height, 32, backgroundColor);
        Gui.drawRect(x+60, y, x+102, y+height, backgroundColor);
        
        //Seperating line
        Gui.drawRect(x, y+90, x+width, y+95, secondaryColor);
        
        //Search
        RenderUtils.drawRoundedRect(x+width-300, y+25, x+width-50, y+65, 9, secondaryColor);
        GlStateManager.pushMatrix();
        GlStateManager.translate(x+width-295, y+36, 0);
        GlStateManager.scale(2, 2, 1);
        GlStateManager.translate(-(x+width-295), -(y+36), 0);
        if(searchString.length() > 0) {
        	fr.drawString(searchString, x+width-290, y+36, secondaryFontColor, false);
        }else {
        	fr.drawString("Search", x+width-290, y+36, new Color(97, 97, 97).getRGB(), false);
        }
        GlStateManager.popMatrix();
        
        GlStateManager.pushMatrix();
        GlStateManager.translate(x+80, y+36, 0);
        GlStateManager.scale(3, 3, 1);
        GlStateManager.translate(-(x+80), -(y+36), 0);
        fr.drawString("Resent", x+80, y+36, -1, false);
        GlStateManager.popMatrix();
        
        //Navigation icons
        GlStateManager.color(1,  1,  1);
        ResourceLocation icon = new ResourceLocation("/resent/house.png");
        mc.getTextureManager().bindTexture(icon);
        Gui.drawModalRectWithCustomSizedTexture(x+20, (int)y+120, 0, 0, 20, 20, 20, 20);
        
        //Draw module button
        for(Mod m : Resent.INSTANCE.modManager.modules){
        	if(selectedMod == null && y+170+offset < y+height && !m.isAdmin() || selectedMod == null && y+170+offset < y+height && EntityRenderer.test) {
        		RenderUtils.drawRoundedRect(x+80, y+120+offset, x+width-20, y+180+offset, 8, secondaryColor);
        		GlUtils.startScale(x+90, y+140+offset, 3);
        		fr.drawString(m.getName(), x+90, y+140+offset, -1, false);
        		GlStateManager.popMatrix();
            	
            	offset+= 80;
        	}
        }

        GlUtils.stopScale();

        if(selectedMod != null){
            for (Comp comp : comps) {
                comp.drawScreen(mouseX, mouseY);
            }
        }

        if (closing) {
            comps.clear();
        	if(introAnimation == null) {
        		mc.displayGuiScreen(null);
        		return;
        	}
        		
            introAnimation.setDirection(Direction.BACKWARDS);
            if (introAnimation.isDone(Direction.BACKWARDS)) {
                mc.displayGuiScreen(null);
            }
        }
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {

        for(Mod m : Resent.INSTANCE.modManager.modules){

            //replace params with gear icon pos
            if(isMouseInside(mouseX, mouseY, width-20, y+20, width-40, y+40) && mouseButton == 0){
                for(Setting s : m.settings){
                    if(s instanceof BooleanSetting){
                        comps.add(new CompCheck(4, 4, selectedMod, s));
                    }
                }
            }

        }

        if(selectedMod != null){
            for(Comp c : comps){
                c.mouseClicked(mouseX, mouseY, mouseButton);
            }
        }

    }

    @Override
    public void initGui() {
        sr = new ScaledResolution(mc);
        x = sr.getScaledWidth()/10;
        y = sr.getScaledHeight()/10;
        width = sr.getScaledWidth()/1.25f;
        height = sr.getScaledHeight()/1.25f;
        introAnimation = Theme.getAnimation(500, 1, 3, 3.8f, 1.35f, false);
        fr = mc.uwuFont;
    }

    @Override
    protected void keyTyped(char par1, int key) {
        if (key == 0x01 || key == Minecraft.getMinecraft().gameSettings.keyBindClickGui.keyCode) {
            closing = true;
        }

        if(selectedMod != null){
            for(Comp c : comps){
                c.keyTyped(par1, key);
            }
        }
        
        // Search box stuff
        else if(key == KeyboardConstants.KEY_BACK) {
        	if(searchString.length() != 0) {
        		searchString = searchString.substring(0, searchString.length()-1);
        	}
        }else {
        	if(searchString.length() <= 18) {
        		String balls = ChatAllowedCharacters.filterAllowedCharacters(String.valueOf(par1));
        		if(balls != null && balls != "")
        		searchString += String.valueOf(par1);
        	}
        }
        
    }

    public boolean isMouseInside(double mouseX, double mouseY, double x, double y, double width, double height) {
        return (mouseX >= x && mouseX <= width) && (mouseY >= y && mouseY <= height);
    }
    
}
