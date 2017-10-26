package com.sudoplay.mc.kor.spi.util;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

/**
 * Created by codetaylor on 12/1/2016.
 */
public class GuiUtils {

  public static void drawPartialTexturedModalRect(
      int xCoord,
      int yCoord,
      float zLevel,
      TextureAtlasSprite textureSprite,
      int width,
      int height
  ) {

    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferBuilder = tessellator.getBuffer();
    bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);

    double x;
    double y;
    double z;
    double u;
    double v;

    x = (double) (xCoord);
    y = (double) (yCoord + height);
    z = (double) zLevel;
    u = (double) textureSprite.getMinU();
    v = (double) textureSprite.getMaxV();
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord + height);
    z = (double) zLevel;
    u = (double) textureSprite.getMaxU();
    v = (double) textureSprite.getMaxV();
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMaxU();
    v = (double) textureSprite.getInterpolatedV(textureSprite.getIconHeight() - height);
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMinU();
    v = (double) textureSprite.getInterpolatedV(textureSprite.getIconHeight() - height);
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    tessellator.draw();
  }

  public static void drawTexturedModalRectRotate90(
      int xCoord,
      int yCoord,
      float zLevel,
      TextureAtlasSprite textureSprite,
      int width,
      int height
  ) {

    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferBuilder = tessellator.getBuffer();
    bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);

    double x;
    double y;
    double z;
    double u;
    double v;

    x = (double) (xCoord);
    y = (double) (yCoord + height);
    z = (double) zLevel;
    u = (double) textureSprite.getMaxU();
    v = (double) textureSprite.getMaxV();
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord + height);
    z = (double) zLevel;
    u = (double) textureSprite.getMaxU();
    v = (double) textureSprite.getMinV();
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMinU();
    v = (double) textureSprite.getMinV();
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMinU();
    v = (double) textureSprite.getMaxV();
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    tessellator.draw();
  }

  public static void drawTexturedModalRectRotate180(
      int xCoord,
      int yCoord,
      float zLevel,
      TextureAtlasSprite textureSprite,
      int width,
      int height
  ) {

    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferBuilder = tessellator.getBuffer();
    bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);

    double x;
    double y;
    double z;
    double u;
    double v;

    x = (double) (xCoord);
    y = (double) (yCoord + height);
    z = (double) zLevel;
    u = (double) textureSprite.getMaxU();
    v = (double) textureSprite.getMinV();
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord + height);
    z = (double) zLevel;
    u = (double) textureSprite.getMinU();
    v = (double) textureSprite.getMinV();
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMinU();
    v = (double) textureSprite.getMaxV();
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMaxU();
    v = (double) textureSprite.getMaxV();
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    tessellator.draw();
  }

  public static void drawTexturedModalRectRotate270(
      int xCoord,
      int yCoord,
      float zLevel,
      TextureAtlasSprite textureSprite,
      int width,
      int height
  ) {

    Tessellator tessellator = Tessellator.getInstance();
    BufferBuilder bufferBuilder = tessellator.getBuffer();
    bufferBuilder.begin(7, DefaultVertexFormats.POSITION_TEX);

    double x;
    double y;
    double z;
    double u;
    double v;

    x = (double) (xCoord);
    y = (double) (yCoord + height);
    z = (double) zLevel;
    u = (double) textureSprite.getMinU();
    v = (double) textureSprite.getMinV();
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord + height);
    z = (double) zLevel;
    u = (double) textureSprite.getMinU();
    v = (double) textureSprite.getMaxV();
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMaxU();
    v = (double) textureSprite.getMaxV();
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMaxU();
    v = (double) textureSprite.getMinV();
    bufferBuilder.pos(x, y, z).tex(u, v).endVertex();

    tessellator.draw();
  }

  private GuiUtils() {
    //
  }
}
