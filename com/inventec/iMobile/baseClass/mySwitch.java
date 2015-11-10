package com.inventec.iMobile.baseClass;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.inventec.OnMyMessageSenderListener;
import com.inventec.controls.makeBitmap;

public class mySwitch
  extends LinearLayout
  implements View.OnTouchListener
{
  static makeBitmap bmpDisable;
  static makeBitmap bmpEnable = null;
  static makeBitmap bmpOffDisable = null;
  static makeBitmap bmpOffEnable;
  static makeBitmap bmpOnDisable;
  static makeBitmap bmpOnEnable;
  boolean b_enablestate = true;
  private int currentTv_x = 64536;
  int itemType = 0;
  private int left = 0;
  private boolean mbLatestStateOn = true;
  Handler mhandle = new Handler();
  OnMyMessageSenderListener msgListener = null;
  OnSwitchStateChanged onswitchchange;
  private int part_with2 = 188;
  private int slideText_with = 294;
  private float switch_height = 54.0F;
  private float switch_with = 188.0F;
  TextView tv = null;
  private int x_down1 = 0;
  private int x_move = 0;
  
  static
  {
    bmpDisable = null;
    bmpOnEnable = null;
    bmpOnDisable = null;
    bmpOffEnable = null;
  }
  
  public mySwitch(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if ((bmpEnable == null) || (bmpDisable == null))
    {
      paramContext = getResources();
      bmpEnable = new makeBitmap();
      bmpEnable.InitBgBmp(paramContext, 2130837748);
      bmpEnable.setFont(32, true);
      bmpEnable.AddTxtDataCenter(2131165351, 4, 42, 96);
      bmpEnable.setColor(-7829368, 0);
      bmpEnable.AddTxtDataCenter(2131165352, 192, 42, 96);
      bmpDisable = new makeBitmap();
      bmpDisable.InitBgBmp(paramContext, bmpEnable.getImage());
      bmpDisable.AddImgData(2130837749, 0, 0);
      bmpOnEnable = new makeBitmap();
      bmpOnEnable.InitBgBmp(paramContext, 2130837747);
      bmpOnEnable.setFont(32, true);
      bmpOnEnable.AddTxtDataCenter(2131165351, 4, 42, 96);
      bmpOnDisable = new makeBitmap();
      bmpOnDisable.InitBgBmp(paramContext, bmpOnEnable.getImage());
      bmpOnDisable.AddImgData(2130837745, 0, 0);
      bmpOffEnable = new makeBitmap();
      bmpOffEnable.InitBgBmp(paramContext, 2130837746);
      bmpOffEnable.setFont(32, true);
      bmpEnable.setColor(-7829368, 0);
      bmpOffEnable.AddTxtDataCenter(2131165352, 82, 42, 96);
      bmpOffDisable = new makeBitmap();
      bmpOffDisable.InitBgBmp(paramContext, bmpOffEnable.getImage());
      bmpOffDisable.AddImgData(2130837745, 0, 0);
    }
    findXmlAttribute(paramAttributeSet);
    this.tv = new TextView(getContext());
    paramContext = new LinearLayout.LayoutParams(this.slideText_with, -1);
    paramContext.bottomMargin = 0;
    paramContext.topMargin = -4;
    paramContext.leftMargin = 0;
    paramContext.rightMargin = 0;
    addView(this.tv, paramContext);
    setOnTouchListener(this);
  }
  
  private void findXmlAttribute(AttributeSet paramAttributeSet)
  {
    paramAttributeSet = String.valueOf(paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/res/android", "layout_width"));
    float f = getResources().getDisplayMetrics().density;
    if (paramAttributeSet.endsWith("dip")) {
      this.switch_with = (Float.valueOf(paramAttributeSet.substring(0, paramAttributeSet.length() - "dip".length())).floatValue() * f);
    }
    for (;;)
    {
      this.switch_height = (this.switch_with / 80.0F * 24.0F);
      this.slideText_with = ((int)this.switch_with * 294 / 188);
      this.part_with2 = (this.slideText_with * 106 / 294);
      return;
      if (paramAttributeSet.endsWith("px")) {
        this.switch_with = Float.valueOf(paramAttributeSet.substring(0, paramAttributeSet.length() - "px".length())).floatValue();
      }
    }
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
    showBackground();
  }
  
  public void SetMyMessageSenderListener(OnMyMessageSenderListener paramOnMyMessageSenderListener)
  {
    this.msgListener = paramOnMyMessageSenderListener;
  }
  
  public void SetOnSwitchStateChanged(OnSwitchStateChanged paramOnSwitchStateChanged)
  {
    this.onswitchchange = paramOnSwitchStateChanged;
  }
  
  public void SetState(boolean paramBoolean)
  {
    if ((setCheckState(paramBoolean)) && (this.onswitchchange != null))
    {
      if (!this.mbLatestStateOn) {
        break label32;
      }
      this.onswitchchange.onswitchopen();
    }
    for (;;)
    {
      return;
      label32:
      this.onswitchchange.onswitchclose();
    }
  }
  
  public void SetText(String paramString)
  {
    this.itemType = 2;
    this.tv.getPaint().setFakeBoldText(true);
    this.tv.setTextSize(16.0F);
    this.tv.setTextColor(Color.rgb(41, 64, 115));
    this.tv.setGravity(17);
    this.tv.setText(paramString);
    setBackgroundDrawable(null);
  }
  
  void changeState(boolean paramBoolean)
  {
    if (paramBoolean) {
      moveTV(0);
    }
    for (;;)
    {
      return;
      moveTV(-this.part_with2);
    }
  }
  
  public boolean getState()
  {
    return this.mbLatestStateOn;
  }
  
  void moveTV(int paramInt)
  {
    if (this.currentTv_x != paramInt)
    {
      this.currentTv_x = paramInt;
      this.tv.layout(this.currentTv_x, 0, this.currentTv_x + this.slideText_with, (int)this.switch_height);
      this.mhandle.postDelayed(new Runnable()
      {
        public void run()
        {
          mySwitch.this.tv.layout(mySwitch.this.currentTv_x, 0, mySwitch.this.currentTv_x + mySwitch.this.slideText_with, (int)mySwitch.this.switch_height);
          mySwitch.this.showBackground();
        }
      }, 100L);
    }
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    if (!IsMyButtonEnable())
    {
      DoListener();
      return bool;
    }
    if (paramMotionEvent.getAction() == 0)
    {
      this.x_down1 = ((int)paramMotionEvent.getX());
      this.left = this.tv.getLeft();
    }
    for (;;)
    {
      bool = true;
      break;
      if (paramMotionEvent.getAction() == 2)
      {
        this.x_move = ((int)paramMotionEvent.getX());
        this.x_move -= this.x_down1;
        if (this.left == -this.part_with2) {
          if (this.x_move < 0) {
            this.x_move = 0;
          }
        }
        for (;;)
        {
          moveTV(this.x_move + this.left);
          break;
          if (this.x_move > this.part_with2)
          {
            this.x_move = this.part_with2;
            continue;
            if (this.left == 0) {
              if (this.x_move > 0) {
                this.x_move = 0;
              } else if (this.x_move < -this.part_with2) {
                this.x_move = (-this.part_with2);
              }
            }
          }
        }
      }
      int i = (int)paramMotionEvent.getX() - this.x_down1;
      if (i > 10) {
        moveTV(0);
      }
      for (;;)
      {
        if (this.currentTv_x == 0)
        {
          SetState(true);
          break;
          if (i < -10)
          {
            moveTV(-this.part_with2);
          }
          else
          {
            if (this.left < 0) {}
            for (bool = true;; bool = false)
            {
              changeState(bool);
              break;
            }
          }
        }
      }
      SetState(false);
    }
  }
  
  public boolean setCheckState(boolean paramBoolean)
  {
    boolean bool = false;
    if (this.mbLatestStateOn != paramBoolean)
    {
      this.mbLatestStateOn = paramBoolean;
      bool = true;
    }
    changeState(this.mbLatestStateOn);
    return bool;
  }
  
  public void setenabled(boolean paramBoolean)
  {
    setEnabled(paramBoolean);
    this.tv.setEnabled(paramBoolean);
    this.b_enablestate = paramBoolean;
    showBackground();
  }
  
  public void showAsItem(int paramInt)
  {
    this.itemType = paramInt;
    if (this.itemType == 1) {
      setOnTouchListener(null);
    }
    for (;;)
    {
      showBackground();
      return;
      setOnTouchListener(this);
    }
  }
  
  void showBackground()
  {
    if (this.itemType == 1)
    {
      this.tv.getPaint().setFakeBoldText(true);
      this.tv.setTextSize(24.0F);
      this.tv.setTextColor(-16777216);
      this.tv.setGravity(1);
      this.tv.setText("  ›");
      setBackgroundDrawable(null);
      this.tv.setVisibility(0);
      return;
    }
    if (this.itemType == 0)
    {
      if (!IsMyButtonEnable()) {
        break label133;
      }
      if (!this.mbLatestStateOn) {
        break label126;
      }
    }
    label126:
    for (makeBitmap localmakeBitmap = bmpOnEnable;; localmakeBitmap = bmpOffEnable)
    {
      setBackgroundDrawable(new BitmapDrawable(getResources(), localmakeBitmap.getImage()));
      this.tv.setVisibility(8);
      break;
      break;
    }
    label133:
    if (this.mbLatestStateOn) {}
    for (localmakeBitmap = bmpOnDisable;; localmakeBitmap = bmpOffDisable) {
      break;
    }
  }
  
  public static abstract interface OnSwitchStateChanged
  {
    public abstract void onswitchclose();
    
    public abstract void onswitchopen();
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseClass\mySwitch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */