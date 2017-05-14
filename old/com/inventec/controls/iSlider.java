package com.inventec.controls;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.inventec.OnMyMessageSenderListener;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.iMobile_AppGlobalVar;

public class iSlider
  extends AutoResizeTextView
{
  static final int BTN_BASE_HEIGHT = 44;
  static final int BTN_BASE_WIDTH = 68;
  static final int SLIDER_BASE_HEIGHT = 53;
  static NinePatch g_NinePathBG = null;
  static NinePatch g_NinePathDis = null;
  static Bitmap g_bmpbtn;
  static Bitmap g_bmpbtnOri;
  static Bitmap g_bmpmask = null;
  static int g_offset = 4;
  boolean bDoSlider = false;
  boolean b_enablestate = true;
  int bx = 0;
  private onSliderEnd dealEnd = null;
  int heightMask = 0;
  private myActivity mAct;
  Bitmap m_bmp;
  Handler m_handle = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      switch (paramAnonymousMessage.what)
      {
      }
      for (;;)
      {
        return;
        iSlider.this.doSlider();
        continue;
        iSlider.this.doScroll();
      }
    }
  };
  Matrix ma = new Matrix();
  int marginLeft = 90;
  int maskOffset = 1000;
  OnMyMessageSenderListener msgListener = null;
  int sPos = 0;
  double scrScale = 1.0D;
  boolean sliding = false;
  int xOffset = 0;
  int yOffset = 0;
  
  static
  {
    g_bmpbtnOri = null;
    g_bmpbtn = null;
  }
  
  public iSlider(Context paramContext)
  {
    super(paramContext);
    this.mAct = ((myActivity)paramContext);
    initBG();
  }
  
  public iSlider(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mAct = ((myActivity)paramContext);
    initBG();
  }
  
  public iSlider(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mAct = ((myActivity)paramContext);
    initBG();
  }
  
  public void DoListener()
  {
    if (this.msgListener != null) {
      this.msgListener.dealmsg();
    }
  }
  
  public boolean IsMyButtonEnable()
  {
    return this.b_enablestate;
  }
  
  public void SetMyButtonEnable(boolean paramBoolean)
  {
    this.b_enablestate = paramBoolean;
  }
  
  public void SetMyMessageSenderListener(OnMyMessageSenderListener paramOnMyMessageSenderListener)
  {
    this.msgListener = paramOnMyMessageSenderListener;
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((!IsMyButtonEnable()) && (paramMotionEvent.getAction() == 0)) {
      DoListener();
    }
    for (;;)
    {
      return true;
      int i = (int)paramMotionEvent.getX();
      if (paramMotionEvent.getAction() == 0)
      {
        if ((i > 0) && (i < g_bmpbtn.getWidth()))
        {
          this.bDoSlider = true;
          this.sPos = 0;
          this.bx = i;
          invalidate();
        }
      }
      else if (paramMotionEvent.getAction() == 2)
      {
        if (this.bDoSlider)
        {
          if (i - this.bx < getWidth() - g_bmpbtn.getWidth() - 10)
          {
            this.sPos = (i - this.bx);
            if (this.sPos < 0) {
              this.sPos = 0;
            }
          }
          for (;;)
          {
            invalidate();
            break;
            this.sPos = 0;
            this.bx = 0;
            this.bDoSlider = false;
            if (this.dealEnd != null) {
              this.dealEnd.dealSliderEnd();
            }
          }
        }
      }
      else if ((paramMotionEvent.getAction() == 1) || (paramMotionEvent.getAction() == 3))
      {
        this.bDoSlider = false;
        if (this.sPos > 0) {
          this.m_handle.sendEmptyMessageDelayed(20, 20L);
        }
      }
    }
  }
  
  void doScroll()
  {
    this.sPos = ((int)(this.sPos - 40.0D * this.scrScale));
    if (this.sPos > 0) {
      this.m_handle.sendEmptyMessageDelayed(20, 20L);
    }
    for (;;)
    {
      invalidate();
      return;
      this.sPos = 0;
    }
  }
  
  void doSlider()
  {
    if ((this.m_bmp != null) && (g_bmpmask != null))
    {
      if (this.maskOffset >= g_bmpmask.getWidth()) {
        this.maskOffset = (-g_bmpmask.getWidth());
      }
      this.maskOffset += 10;
      invalidate();
    }
    this.m_handle.sendEmptyMessageDelayed(10, 10L);
  }
  
  void initBG()
  {
    Object localObject;
    if (g_NinePathBG == null)
    {
      localObject = getResources();
      if (!iMobile_AppGlobalVar.isPad) {
        break label123;
      }
      i = 2130837718;
      localObject = BitmapFactory.decodeResource((Resources)localObject, i);
      g_NinePathBG = new NinePatch((Bitmap)localObject, ((Bitmap)localObject).getNinePatchChunk(), null);
      g_bmpmask = BitmapFactory.decodeResource(getResources(), 2130837717);
      localObject = getResources();
      if (iMobile_AppGlobalVar.isPad) {}
      g_bmpbtnOri = BitmapFactory.decodeResource((Resources)localObject, 2130837721);
      localObject = getResources();
      if (!iMobile_AppGlobalVar.isPad) {
        break label129;
      }
    }
    label123:
    label129:
    for (int i = 2130837716;; i = 2130837715)
    {
      localObject = BitmapFactory.decodeResource((Resources)localObject, i);
      g_NinePathDis = new NinePatch((Bitmap)localObject, ((Bitmap)localObject).getNinePatchChunk(), null);
      getResources().getDisplayMetrics();
      setSingle();
      return;
      i = 2130837719;
      break;
    }
  }
  
  Bitmap makeMask(float paramFloat)
  {
    Paint localPaint = new Paint();
    int i = this.m_bmp.getWidth();
    int j = this.m_bmp.getHeight();
    i = (int)(i * paramFloat);
    j = (int)(j * paramFloat);
    Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
    Object localObject1 = new Canvas(localBitmap);
    localPaint.setColor(Color.rgb(135, 135, 135));
    localPaint.setStyle(Paint.Style.FILL);
    ((Canvas)localObject1).drawRect(0.0F, 0.0F, i, j, localPaint);
    if (this.maskOffset != 1000) {
      ((Canvas)localObject1).drawBitmap(g_bmpmask, this.maskOffset, 0.0F, localPaint);
    }
    Object localObject2 = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
    int k = g_bmpbtn.getWidth() / 2;
    if (this.sPos > k)
    {
      int m = getWidth();
      k = (m + k - this.sPos) * 255 / m;
      localPaint.setColor(Color.argb(k, k, k, k));
      localPaint.setXfermode((Xfermode)localObject2);
      ((Canvas)localObject1).drawRect(0.0F, 0.0F, i, j, localPaint);
      localPaint.setXfermode(null);
    }
    localObject1 = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas((Bitmap)localObject1);
    localCanvas.drawBitmap(this.m_bmp, 0.0F, 0.0F, localPaint);
    localPaint.setXfermode((Xfermode)localObject2);
    localObject2 = new Rect(0, 0, localBitmap.getWidth(), localBitmap.getWidth());
    Rect localRect = new Rect(0, 0, localBitmap.getWidth(), localBitmap.getWidth());
    if (iMobile_AppGlobalVar.isPad) {
      localRect.bottom = ((int)(localRect.bottom * this.mAct.getScrScale()));
    }
    this.heightMask = localBitmap.getHeight();
    localCanvas.drawBitmap(localBitmap, (Rect)localObject2, localRect, localPaint);
    localPaint.setXfermode(null);
    return (Bitmap)localObject1;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.save();
    int m = getWidth();
    int i = getHeight();
    TextPaint localTextPaint = getPaint();
    this.scrScale = (i / 53.0D);
    g_bmpbtn = Bitmap.createScaledBitmap(g_bmpbtnOri, (int)(68.0D * this.scrScale), (int)(44.0D * this.scrScale), true);
    g_offset = (int)(4.0D * this.scrScale);
    this.marginLeft = (g_offset + g_bmpbtn.getWidth());
    int k = m - this.marginLeft;
    if (this.mNeedsResize)
    {
      j = resizeText(k, i);
      this.xOffset = (this.marginLeft + (k - j) / 2);
      this.yOffset = (((int)this.mTextSize + i) / 2);
      this.m_bmp = Bitmap.createBitmap(j, i, Bitmap.Config.ARGB_8888);
      localObject = new Canvas(this.m_bmp);
      String str = getText().toString();
      localTextPaint.setAntiAlias(true);
      localTextPaint.setColor(-1);
      ((Canvas)localObject).drawText(getText(), 0, str.length(), 0.0F, this.yOffset, localTextPaint);
    }
    Object localObject = new RectF(0.0F, 0.0F, m, i);
    g_NinePathBG.draw(paramCanvas, (RectF)localObject);
    int j = g_bmpbtn.getHeight();
    paramCanvas.drawBitmap(makeMask(1.0F), this.xOffset, 0.0F, localTextPaint);
    paramCanvas.save();
    paramCanvas.translate(this.sPos + g_offset, (i - j) / 2);
    paramCanvas.drawBitmap(g_bmpbtn, this.ma, localTextPaint);
    paramCanvas.restore();
    if (!IsMyButtonEnable()) {
      g_NinePathDis.draw(paramCanvas, (RectF)localObject);
    }
    paramCanvas.restore();
  }
  
  public void setSliderEnd(onSliderEnd paramonSliderEnd)
  {
    this.dealEnd = paramonSliderEnd;
  }
  
  public void setTextLeft(int paramInt)
  {
    this.marginLeft = paramInt;
  }
  
  public void startSlider()
  {
    if (!this.sliding)
    {
      SetMyButtonEnable(true);
      doSlider();
      this.sliding = true;
    }
  }
  
  public void stopSlider()
  {
    this.sliding = false;
    this.m_handle.removeMessages(10);
    this.maskOffset = 1000;
    SetMyButtonEnable(false);
    invalidate();
  }
  
  public static abstract interface onSliderEnd
  {
    public abstract void dealSliderEnd();
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\controls\iSlider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */