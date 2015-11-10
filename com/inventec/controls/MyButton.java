package com.inventec.controls;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MyButton
  extends AutoResizeTextView
  implements View.OnTouchListener
{
  public static int colDwn;
  public static int colNor = -15592942;
  static NinePatch g_NinePathDis = null;
  static NinePatch g_NinePathDwn;
  static NinePatch g_NinePathNor;
  boolean bToggle = false;
  boolean b_enablestate = true;
  NinePatch bmpDwn;
  NinePatch bmpNor;
  int colDown = colDwn;
  int colNormal = colNor;
  NinePatch curBg;
  Drawable curDrawable = null;
  Drawable drawableDis = null;
  Drawable drawableOff = null;
  Drawable drawableOn = null;
  int paddingB;
  int paddingL;
  int paddingR;
  int paddingT;
  RectF rc = null;
  Resources resources;
  int visibleSetting;
  
  static
  {
    colDwn = -1;
    g_NinePathNor = null;
    g_NinePathDwn = null;
  }
  
  public MyButton(Context paramContext)
  {
    super(paramContext);
    initBtn();
  }
  
  public MyButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initBtn();
  }
  
  public MyButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initBtn();
  }
  
  public boolean IsMyButtonEnable()
  {
    return this.b_enablestate;
  }
  
  public void SetMyButtonEnable(boolean paramBoolean)
  {
    this.b_enablestate = paramBoolean;
    invalidate();
  }
  
  public void chengeBg(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.drawableOn = this.resources.getDrawable(paramInt3);
    this.drawableOff = this.resources.getDrawable(paramInt1);
    this.colNormal = paramInt2;
    this.colDown = paramInt4;
    setNormal();
  }
  
  NinePatch getNinePath(int paramInt)
  {
    Object localObject;
    if (paramInt == 2130837598) {
      localObject = g_NinePathNor;
    }
    for (;;)
    {
      return (NinePatch)localObject;
      if (paramInt == 2130837599)
      {
        localObject = g_NinePathDwn;
      }
      else
      {
        localObject = BitmapFactory.decodeResource(getResources(), paramInt);
        localObject = new NinePatch((Bitmap)localObject, ((Bitmap)localObject).getNinePatchChunk(), null);
      }
    }
  }
  
  void initBtn()
  {
    this.resources = getResources();
    setBackgroundDrawable(null);
    if (this.drawableOn == null) {
      this.drawableOn = this.resources.getDrawable(2130837599);
    }
    if (this.drawableOff == null) {
      this.drawableOff = this.resources.getDrawable(2130837598);
    }
    if (this.drawableDis == null) {
      this.drawableDis = this.resources.getDrawable(2130837593);
    }
    this.bmpNor = g_NinePathNor;
    this.bmpDwn = g_NinePathDwn;
    setGravity(17);
    this.paddingB = getPaddingBottom();
    this.paddingT = getPaddingTop();
    this.paddingL = getPaddingLeft();
    this.paddingR = getPaddingRight();
    setNormal();
    setOnTouchListener(this);
    setSingle();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.save();
    int i = getWidth();
    int j = getHeight();
    super.onDraw(paramCanvas);
    if (!IsMyButtonEnable())
    {
      this.drawableDis.setBounds(0, 0, i, j);
      this.drawableDis.draw(paramCanvas);
    }
    paramCanvas.restore();
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    if ((i == 0) || (i == 2)) {
      if (IsMyButtonEnable()) {
        setDown();
      }
    }
    for (;;)
    {
      return false;
      if (IsMyButtonEnable()) {
        setNormal();
      }
    }
  }
  
  void setBtnStyle(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.curBg = this.bmpNor;
      this.curDrawable = this.drawableOff;
      setColors(this.colNormal, this.colDown);
    }
    for (;;)
    {
      setBackgroundDrawable(this.curDrawable);
      setPadding(this.paddingL, this.paddingT, this.paddingR, this.paddingB);
      invalidate();
      return;
      this.curBg = this.bmpDwn;
      this.curDrawable = this.drawableOn;
      setColors(this.colDown, this.colNormal);
    }
  }
  
  void setDown()
  {
    setBtnStyle(this.bToggle);
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    this.b_enablestate = paramBoolean;
    invalidate();
  }
  
  void setNormal()
  {
    if (this.bToggle) {}
    for (boolean bool = false;; bool = true)
    {
      setBtnStyle(bool);
      return;
    }
  }
  
  public void setToggle(boolean paramBoolean)
  {
    this.bToggle = paramBoolean;
    setNormal();
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\controls\MyButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */