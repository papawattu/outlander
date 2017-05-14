package com.inventec.controls;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.inventec.iMobile.baseOP.AppDealWifi;

public class myProgressBar
  extends ProgressBar
{
  private boolean autoProgress = false;
  private int leftStep = 0;
  @SuppressLint({"HandlerLeak"})
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 1) {
        myProgressBar.this.updateProgress();
      }
    }
  };
  private Paint mPaint;
  private int pastPercent = 0;
  private int stepPercent = 0;
  private int stepTime = 20;
  
  public myProgressBar(Context paramContext)
  {
    super(paramContext);
    initText();
  }
  
  public myProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initText();
  }
  
  public myProgressBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initText();
  }
  
  private void initText()
  {
    this.mPaint = new Paint();
    this.mPaint.setAntiAlias(true);
    this.mPaint.setColor(-1);
  }
  
  private void updateProgress()
  {
    this.pastPercent += this.stepPercent;
    AppDealWifi.LogMsg("update progress " + this.pastPercent);
    super.setProgress(this.pastPercent);
    if ((this.pastPercent < 100) && (this.autoProgress)) {
      updateProgressAfter(this.stepTime - 20);
    }
    for (;;)
    {
      return;
      this.autoProgress = false;
    }
  }
  
  private void updateProgressAfter(int paramInt)
  {
    this.mHandler.sendEmptyMessageDelayed(1, paramInt);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    try
    {
      super.onDraw(paramCanvas);
      return;
    }
    finally
    {
      paramCanvas = finally;
      throw paramCanvas;
    }
  }
  
  public void setLeftSecond(int paramInt)
  {
    this.stepTime = (paramInt * 1000 / (100 - this.pastPercent));
    this.stepPercent = 1;
    AppDealWifi.LogMsg("stepPercent = " + this.stepPercent);
    AppDealWifi.LogMsg("leftStep = " + this.leftStep);
  }
  
  public void setProgress(int paramInt)
  {
    super.setProgress(paramInt);
  }
  
  public void startAutoProgress(int paramInt)
  {
    setLeftSecond(paramInt);
    if (!this.autoProgress)
    {
      this.pastPercent = 0;
      this.autoProgress = true;
      updateProgressAfter(this.stepTime);
    }
  }
  
  public void stopAutoProgress()
  {
    this.autoProgress = false;
    this.pastPercent = 0;
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\controls\myProgressBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */