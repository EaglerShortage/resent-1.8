package net.minecraft.item;

import net.minecraft.util.EnumChatFormatting;

/**+
 * This portion of EaglercraftX contains deobfuscated Minecraft 1.8 source code.
 * 
 * Minecraft 1.8.8 bytecode is (c) 2015 Mojang AB. "Do not distribute!"
 * Mod Coder Pack v9.18 deobfuscation configs are (c) Copyright by the MCP Team
 * 
 * EaglercraftX 1.8 patch files are (c) 2022 LAX1DUDE. All Rights Reserved.
 * 
 * WITH THE EXCEPTION OF PATCH FILES, MINIFIED JAVASCRIPT, AND ALL FILES
 * NORMALLY FOUND IN AN UNMODIFIED MINECRAFT RESOURCE PACK, YOU ARE NOT ALLOWED
 * TO SHARE, DISTRIBUTE, OR REPURPOSE ANY FILE USED BY OR PRODUCED BY THE
 * SOFTWARE IN THIS REPOSITORY WITHOUT PRIOR PERMISSION FROM THE PROJECT AUTHOR.
 * 
 * NOT FOR COMMERCIAL OR MALICIOUS USE
 * 
 * (please read the 'LICENSE' file this repo's root directory for more info) 
 * 
 */
public enum EnumRarity {
	COMMON(EnumChatFormatting.WHITE, "Common"), UNCOMMON(EnumChatFormatting.YELLOW, "Uncommon"),
	RARE(EnumChatFormatting.AQUA, "Rare"), EPIC(EnumChatFormatting.LIGHT_PURPLE, "Epic");

	public final EnumChatFormatting rarityColor;
	public final String rarityName;

	EnumRarity(EnumChatFormatting color, String name) {
        this.rarityColor = color;
        this.rarityName = name;
    }
}