package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

/**+
 * This portion of EaglercraftX contains deobfuscated Minecraft 1.8 source code.
 *
 * Minecraft 1.8.8 bytecode is (c) 2015 Mojang AB. "Do not distribute!"
 * Mod Coder Pack v9.18 deobfuscation configs are (c) Copyright by the MCP Team
 *
 * EaglercraftX 1.8 patch files are (c) 2022-2023 LAX1DUDE. All Rights Reserved.
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
public class S2EPacketCloseWindow implements Packet<INetHandlerPlayClient> {

    private int windowId;

    public S2EPacketCloseWindow() {}

    public S2EPacketCloseWindow(int windowIdIn) {
        this.windowId = windowIdIn;
    }

    /**+
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
        inethandlerplayclient.handleCloseWindow(this);
    }

    /**+
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
        this.windowId = parPacketBuffer.readUnsignedByte();
    }

    /**+
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
        parPacketBuffer.writeByte(this.windowId);
    }
}
