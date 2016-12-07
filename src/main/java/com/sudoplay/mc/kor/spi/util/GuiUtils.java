package com.sudoplay.mc.kor.spi.util;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
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
    VertexBuffer vertexbuffer = tessellator.getBuffer();
    vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

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
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord + height);
    z = (double) zLevel;
    u = (double) textureSprite.getMaxU();
    v = (double) textureSprite.getMaxV();
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMaxU();
    v = (double) textureSprite.getInterpolatedV(textureSprite.getIconHeight() - height);
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMinU();
    v = (double) textureSprite.getInterpolatedV(textureSprite.getIconHeight() - height);
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

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
    VertexBuffer vertexbuffer = tessellator.getBuffer();
    vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

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
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord + height);
    z = (double) zLevel;
    u = (double) textureSprite.getMaxU();
    v = (double) textureSprite.getMinV();
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMinU();
    v = (double) textureSprite.getMinV();
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMinU();
    v = (double) textureSprite.getMaxV();
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

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
    VertexBuffer vertexbuffer = tessellator.getBuffer();
    vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

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
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord + height);
    z = (double) zLevel;
    u = (double) textureSprite.getMinU();
    v = (double) textureSprite.getMinV();
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMinU();
    v = (double) textureSprite.getMaxV();
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMaxU();
    v = (double) textureSprite.getMaxV();
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

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
    VertexBuffer vertexbuffer = tessellator.getBuffer();
    vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);

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
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord + height);
    z = (double) zLevel;
    u = (double) textureSprite.getMinU();
    v = (double) textureSprite.getMaxV();
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord + width);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMaxU();
    v = (double) textureSprite.getMaxV();
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

    x = (double) (xCoord);
    y = (double) (yCoord);
    z = (double) zLevel;
    u = (double) textureSprite.getMaxU();
    v = (double) textureSprite.getMinV();
    vertexbuffer.pos(x, y, z).tex(u, v).endVertex();

    tessellator.draw();
  }

  private GuiUtils() {
    //
  }
}
