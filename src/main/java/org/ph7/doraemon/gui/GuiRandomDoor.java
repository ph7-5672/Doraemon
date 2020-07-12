package org.ph7.doraemon.gui;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.ph7.doraemon.core.Doraemon;
import org.ph7.doraemon.entity.EntityRandomDoor;
import org.ph7.doraemon.network.TransPacket;

import java.io.IOException;

public class GuiRandomDoor extends GuiScreen
{
    private EntityRandomDoor randomDoor;

    public GuiRandomDoor(EntityRandomDoor randomDoor)
    {
        this.randomDoor = randomDoor;
    }

    private GuiTextField posXFiled;
    private GuiTextField posYFiled;
    private GuiTextField posZFiled;
    private boolean isError;
    private boolean cannotTrans;

    @Override
    public void initGui()
    {
        posXFiled = new GuiTextField(0, this.fontRenderer, this.width / 2 - 90, 95, 50, 20);
        posYFiled = new GuiTextField(1, this.fontRenderer, this.width / 2 - 20, 95, 50, 20);
        posZFiled = new GuiTextField(2, this.fontRenderer, this.width / 2 + 50, 95, 50, 20);
        BlockPos trans = randomDoor.getTrans();
        posXFiled.setText(String.valueOf(trans.getX()));
        posYFiled.setText(String.valueOf(trans.getY()));
        posZFiled.setText(String.valueOf(trans.getZ()));
        this.buttonList.clear();
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 4 + 120 + -16, I18n.format("random_door.pos.save")));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, I18n.format("random_door.pos.set"), this.width / 2, 40, 16777215);
        posXFiled.drawTextBox();
        posYFiled.drawTextBox();
        posZFiled.drawTextBox();
        this.drawString(this.fontRenderer, "x", this.width / 2 - 100, 100, 16777215);
        this.drawString(this.fontRenderer, "y", this.width / 2 - 30, 100, 16777215);
        this.drawString(this.fontRenderer, "z", this.width / 2 + 40, 100, 16777215);
        if (this.isError)
        {
            this.cannotTrans = false;
            this.drawCenteredString(this.fontRenderer, I18n.format("random_door.pos.error"), this.width / 2, 60, 0xfd0707);
        }
        if (this.cannotTrans)
        {
            this.isError = false;
            this.drawCenteredString(this.fontRenderer, I18n.format("random_door.pos.cannotTrans"), this.width / 2, 60, 0xfd0707);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.posXFiled.mouseClicked(mouseX, mouseY, mouseButton);
        this.posYFiled.mouseClicked(mouseX, mouseY, mouseButton);
        this.posZFiled.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        super.keyTyped(typedChar, keyCode);

        if (keyCode == 28) // enter
        {
            this.actionPerformed(this.buttonList.get(0));
        }
        else if (this.posXFiled.isFocused())
        {
            this.posXFiled.textboxKeyTyped(typedChar, keyCode);
        }
        else if (this.posYFiled.isFocused())
        {
            this.posYFiled.textboxKeyTyped(typedChar, keyCode);
        }
        else if (this.posZFiled.isFocused())
        {
            this.posZFiled.textboxKeyTyped(typedChar, keyCode);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.enabled)
        {
            if (button.id == 3)
            {
                String textX = this.posXFiled.getText();
                String textY = this.posYFiled.getText();
                String textZ = this.posZFiled.getText();

                try
                {
                    Integer x = Integer.valueOf(textX);
                    Integer y = Integer.valueOf(textY);
                    Integer z = Integer.valueOf(textZ);
                    BlockPos blockPos = new BlockPos(x, y, z);

                    World world = randomDoor.getEntityWorld();
                    IBlockState state1 = world.getBlockState(blockPos);
                    IBlockState state2 = world.getBlockState(blockPos.up());
                    Chunk chunk = world.getChunkFromBlockCoords(blockPos);

                    if (chunk.isLoaded() && state1.getMaterial() == Material.AIR && state2.getMaterial() == Material.AIR)
                    {
                        //dataManager属于server side, gui中调用需要数据同步
                        Doraemon.NETWORK.sendToServer(new TransPacket(randomDoor.getEntityId(), x, y, z));
                        /*randomDoor.getServer().addScheduledTask(() ->
                        {
                        });*/
                        this.mc.displayGuiScreen((GuiScreen)null);

                        if (this.mc.currentScreen == null)
                        {
                            this.mc.setIngameFocus();
                        }
                    }
                    else
                    {
                        this.cannotTrans = true;
                    }

                }
                catch (NumberFormatException e)
                {
                    this.isError = true;
                }
            }
        }
    }

}
