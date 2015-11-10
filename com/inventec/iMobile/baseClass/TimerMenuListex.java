package com.inventec.iMobile.baseClass;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;

public class TimerMenuListex
  extends RelativeLayout
{
  private final int FP = -1;
  AutoResizeTextView b1;
  AutoResizeTextView b2;
  AutoResizeTextView b3;
  boolean flag_listOn = true;
  public LinearLayout list;
  OnButtonListcloseListenerEx listcloselistener;
  
  public TimerMenuListex(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    addMyview(2130903069);
  }
  
  public TimerMenuListex(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    addMyview(2130903069);
  }
  
  public void addMyview(int paramInt)
  {
    String str3 = getResources().getString(2131165375);
    String str1 = "10" + str3;
    String str2 = "20" + str3;
    str3 = "30" + str3;
    LinearLayout localLinearLayout = (LinearLayout)((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(paramInt, null);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
    removeAllViews();
    addView(localLinearLayout, localLayoutParams);
    this.list = ((LinearLayout)localLinearLayout.findViewById(2131296450));
    this.b1 = ((AutoResizeTextView)localLinearLayout.findViewById(2131296451));
    this.b2 = ((AutoResizeTextView)localLinearLayout.findViewById(2131296452));
    this.b3 = ((AutoResizeTextView)localLinearLayout.findViewById(2131296453));
    this.b1.setSingle();
    this.b2.setSingle();
    this.b3.setSingle();
    this.b1.setPadding(2, 2, 2, 2);
    this.b2.setPadding(2, 2, 2, 2);
    this.b3.setPadding(2, 2, 2, 2);
    this.b1.setText(str1);
    this.b2.setText(str2);
    this.b3.setText(str3);
    this.b1.setOnTouchListener(new myonTouch());
    this.b2.setOnTouchListener(new myonTouch());
    this.b3.setOnTouchListener(new myonTouch());
  }
  
  public void changeState(AutoResizeTextView paramAutoResizeTextView, int paramInt)
  {
    if (paramInt == 0)
    {
      paramAutoResizeTextView.setBackgroundResource(2130837697);
      paramAutoResizeTextView.setColors(-7829368, 0);
    }
    for (;;)
    {
      return;
      if (paramInt == 1)
      {
        paramAutoResizeTextView.setBackgroundResource(2130837697);
        paramAutoResizeTextView.setColors(MyButton.colNor, -1);
      }
      else if (paramInt == 2)
      {
        paramAutoResizeTextView.setBackgroundResource(2130837698);
        paramAutoResizeTextView.setColors(-1, -7829368);
      }
    }
  }
  
  public float getTextSize()
  {
    return this.b1.getTextSize();
  }
  
  public void listDirecton(boolean paramBoolean)
  {
    if (paramBoolean) {}
    addMyview(2130903069);
  }
  
  public void setBtnEnable(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    int j = 1;
    if (paramBoolean1)
    {
      i = 1;
      setState(1, i);
      if (!paramBoolean2) {
        break label53;
      }
      i = 1;
      label24:
      setState(2, i);
      if (!paramBoolean3) {
        break label59;
      }
    }
    label53:
    label59:
    for (int i = j;; i = 0)
    {
      setState(3, i);
      return;
      i = 0;
      break;
      i = 0;
      break label24;
    }
  }
  
  public void setOnbuttonlistlistener(OnButtonListcloseListenerEx paramOnButtonListcloseListenerEx)
  {
    if (paramOnButtonListcloseListenerEx != null) {
      this.listcloselistener = paramOnButtonListcloseListenerEx;
    }
  }
  
  public void setState(int paramInt1, int paramInt2)
  {
    AutoResizeTextView localAutoResizeTextView = this.b1;
    if (paramInt1 == 1) {
      localAutoResizeTextView = this.b1;
    }
    for (;;)
    {
      changeState(localAutoResizeTextView, paramInt2);
      return;
      if (paramInt1 == 2) {
        localAutoResizeTextView = this.b2;
      } else if (paramInt1 == 3) {
        localAutoResizeTextView = this.b3;
      }
    }
  }
  
  public void setTextSize(int paramInt, float paramFloat)
  {
    this.b1.setTextSize(paramInt, paramFloat);
    this.b2.setTextSize(paramInt, paramFloat);
    this.b3.setTextSize(paramInt, paramFloat);
  }
  
  class myonTouch
    implements View.OnTouchListener
  {
    myonTouch() {}
    
    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      if ((paramMotionEvent.getAction() == 0) || (paramMotionEvent.getAction() == 2))
      {
        paramView.setBackgroundResource(2130837698);
        if (paramView.getId() == 2131296451) {
          TimerMenuListex.this.b1.setColors(-1, -7829368);
        }
        for (;;)
        {
          return true;
          if (paramView.getId() == 2131296452) {
            TimerMenuListex.this.b2.setColors(-1, -7829368);
          } else if (paramView.getId() == 2131296453) {
            TimerMenuListex.this.b3.setColors(-1, -7829368);
          }
        }
      }
      int i;
      if (TimerMenuListex.this.listcloselistener != null)
      {
        i = 0;
        if (paramView.getId() != 2131296451) {
          break label147;
        }
        i = 1;
      }
      for (;;)
      {
        TimerMenuListex.this.listcloselistener.onclosed(TimerMenuListex.this, i);
        TimerMenuListex.this.setVisibility(8);
        break;
        label147:
        if (paramView.getId() == 2131296452) {
          i = 2;
        } else if (paramView.getId() == 2131296453) {
          i = 3;
        }
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseClass\TimerMenuListex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */