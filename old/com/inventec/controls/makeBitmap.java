package com.inventec.controls;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class makeBitmap
{
  int colorShadow = -16777216;
  int colorText = -1;
  Bitmap m_bmp;
  Canvas m_canvas;
  Paint m_paint;
  Resources m_res;
  
  public void AddImgData(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((this.m_canvas == null) || (this.m_bmp == null)) {}
    for (;;)
    {
      return;
      init();
      Bitmap localBitmap = readBitMap(this.m_res, paramInt1);
      this.m_canvas.drawBitmap(localBitmap, paramInt2, paramInt3, this.m_paint);
    }
  }
  
  public void AddImgData(int paramInt, Rect paramRect)
  {
    if ((this.m_canvas == null) || (this.m_bmp == null)) {}
    for (;;)
    {
      return;
      init();
      Bitmap localBitmap = readBitMap(this.m_res, paramInt);
      this.m_canvas.drawBitmap(localBitmap, null, paramRect, this.m_paint);
    }
  }
  
  public int AddTxtData(int paramInt1, int paramInt2, int paramInt3)
  {
    return AddTxtData(this.m_res.getString(paramInt1), paramInt2, paramInt3);
  }
  
  public int AddTxtData(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return AddTxtData(this.m_res.getString(paramInt1), paramInt2, paramInt3, paramInt4);
  }
  
  public int AddTxtData(String paramString, int paramInt1, int paramInt2)
  {
    int j = 0;
    int i = j;
    if (this.m_canvas != null) {
      if (this.m_bmp != null) {
        break label28;
      }
    }
    for (i = j;; i = (int)this.m_paint.measureText(paramString, 0, paramString.length()))
    {
      return i;
      label28:
      init();
      drawShadowText(paramString, paramInt1, paramInt2);
    }
  }
  
  public int AddTxtData(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    int j = 0;
    int i = j;
    if (this.m_canvas != null) {
      if (this.m_bmp != null) {
        break label28;
      }
    }
    for (i = j;; i = (int)this.m_paint.measureText(paramString, 0, paramString.length()))
    {
      return i;
      label28:
      init();
      if (this.m_paint.measureText(paramString, 0, paramString.length()) > paramInt3) {
        smallfont(paramString, paramInt3);
      }
      drawShadowText(paramString, paramInt1, paramInt2);
    }
  }
  
  public void AddTxtDataCenter(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    AddTxtDataCenter(this.m_res.getString(paramInt1), paramInt2, paramInt3, paramInt4);
  }
  
  public void AddTxtDataCenter(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((this.m_canvas == null) || (this.m_bmp == null)) {}
    for (;;)
    {
      return;
      init();
      float f2 = this.m_paint.measureText(paramString, 0, paramString.length());
      float f1 = f2;
      if (f2 > paramInt3) {
        f1 = smallfont(paramString, paramInt3);
      }
      drawShadowText(paramString, (int)(paramInt1 + (paramInt3 - f1) / 2.0F), paramInt2);
    }
  }
  
  public void AddTxtDataRight(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    AddTxtDataRight(this.m_res.getString(paramInt1), paramInt2, paramInt3, paramInt4);
  }
  
  public void AddTxtDataRight(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((this.m_canvas == null) || (this.m_bmp == null)) {}
    for (;;)
    {
      return;
      init();
      float f2 = this.m_paint.measureText(paramString, 0, paramString.length());
      float f1 = f2;
      if (f2 > paramInt3) {
        f1 = smallfont(paramString, paramInt3);
      }
      drawShadowText(paramString, (int)(paramInt1 + (paramInt3 - f1)), paramInt2);
    }
  }
  
  public void InitBgBmp(Resources paramResources, int paramInt)
  {
    InitBgBmp(paramResources, readBitMap(paramResources, paramInt));
  }
  
  public void InitBgBmp(Resources paramResources, Bitmap paramBitmap)
  {
    init();
    this.m_bmp = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    this.m_canvas = new Canvas(this.m_bmp);
    this.m_canvas.drawBitmap(paramBitmap, 0.0F, 0.0F, this.m_paint);
    this.m_res = paramResources;
  }
  
  public void drawShadowText(String paramString, int paramInt1, int paramInt2)
  {
    if (this.colorShadow != 0)
    {
      this.m_paint.setColor(this.colorShadow);
      this.m_canvas.drawText(paramString, paramInt1 + 1, paramInt2 + 1, this.m_paint);
    }
    this.m_paint.setColor(this.colorText);
    this.m_canvas.drawText(paramString, paramInt1, paramInt2, this.m_paint);
  }
  
  public boolean freeBmp()
  {
    if ((this.m_bmp != null) && (!this.m_bmp.isRecycled()))
    {
      this.m_bmp.recycle();
      this.m_bmp = null;
    }
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public Bitmap getImage()
  {
    return this.m_bmp;
  }
  
  void init()
  {
    if (this.m_paint == null)
    {
      this.m_paint = new Paint();
      this.m_paint.setAntiAlias(true);
    }
  }
  
  public Bitmap readBitMap(Resources paramResources, int paramInt)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
    localOptions.inPurgeable = true;
    localOptions.inInputShareable = true;
    return BitmapFactory.decodeStream(paramResources.openRawResource(paramInt), null, localOptions);
  }
  
  public void setColor(int paramInt1, int paramInt2)
  {
    this.colorText = paramInt1;
    this.colorShadow = paramInt2;
  }
  
  public void setFont(int paramInt, boolean paramBoolean)
  {
    this.m_paint.setTextSize(paramInt);
    this.m_paint.setFakeBoldText(paramBoolean);
  }
  
  float smallfont(String paramString, int paramInt)
  {
    float f2 = this.m_paint.getTextSize() - 1.0F;
    this.m_paint.setTextSize(f2);
    for (float f1 = this.m_paint.measureText(paramString, 0, paramString.length());; f1 = this.m_paint.measureText(paramString, 0, paramString.length()))
    {
      if ((f1 <= paramInt) || (f2 <= 10.0F)) {
        return f1;
      }
      f2 -= 1.0F;
      this.m_paint.setTextSize(f2);
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\controls\makeBitmap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */