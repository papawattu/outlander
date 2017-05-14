package com.inventec.iMobile.baseClass;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;

public class TimerMenuList
  extends RelativeLayout
{
  private final int FP = -1;
  AutoResizeTextView b1;
  AutoResizeTextView b2;
  AutoResizeTextView b3;
  AutoResizeTextView b4;
  boolean flag_listOn = true;
  public LinearLayout list;
  OnButtonListcloseListener listcloselistener;
  
  public TimerMenuList(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    addMyview(2130903067);
  }
  
  public TimerMenuList(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    addMyview(2130903067);
  }
  
  public void addMyview(int paramInt)
  {
    Object localObject = getResources();
    String str2 = ((Resources)localObject).getString(2131165346).toString();
    String str3 = ((Resources)localObject).getString(2131165347).toString();
    String str1 = ((Resources)localObject).getString(2131165348).toString();
    String str4 = ((Resources)localObject).getString(2131165349).toString();
    LinearLayout localLinearLayout = (LinearLayout)((LayoutInflater)getContext().getSystemService("layout_inflater")).inflate(paramInt, null);
    localObject = new LinearLayout.LayoutParams(-1, -1);
    removeAllViews();
    addView(localLinearLayout, (ViewGroup.LayoutParams)localObject);
    this.list = ((LinearLayout)localLinearLayout.findViewById(2131296450));
    this.b1 = ((AutoResizeTextView)localLinearLayout.findViewById(2131296451));
    this.b2 = ((AutoResizeTextView)localLinearLayout.findViewById(2131296452));
    this.b3 = ((AutoResizeTextView)localLinearLayout.findViewById(2131296453));
    this.b4 = ((AutoResizeTextView)localLinearLayout.findViewById(2131296454));
    this.b1.setSingle();
    this.b2.setSingle();
    this.b3.setSingle();
    this.b4.setSingle();
    this.b1.setPadding(2, 2, 2, 2);
    this.b2.setPadding(2, 2, 2, 2);
    this.b3.setPadding(2, 2, 2, 2);
    this.b4.setPadding(2, 2, 2, 2);
    this.b1.setText(str2);
    this.b2.setText(str3);
    this.b3.setText(str1);
    this.b4.setText(str4);
    this.b1.setOnTouchListener(new myonTouch());
    this.b2.setOnTouchListener(new myonTouch());
    this.b3.setOnTouchListener(new myonTouch());
    this.b4.setOnTouchListener(new myonTouch());
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
    for (int i = 2130903067;; i = 2130903068)
    {
      addMyview(i);
      return;
    }
  }
  
  public void setBtnEnable(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt)
  {
    this.b1.setEnabled(paramBoolean1);
    if (paramBoolean1)
    {
      i = 1;
      setState(1, i);
      this.b2.setEnabled(paramBoolean2);
      if (!paramBoolean2) {
        break label86;
      }
      i = 1;
      label37:
      setState(2, i);
      this.b3.setEnabled(paramBoolean3);
      if (!paramBoolean3) {
        break label92;
      }
    }
    label86:
    label92:
    for (int i = 1;; i = 0)
    {
      setState(3, i);
      setState(0, 1);
      setState(paramInt, 2);
      return;
      i = 0;
      break;
      i = 0;
      break label37;
    }
  }
  
  public void setOnbuttonlistlistener(OnButtonListcloseListener paramOnButtonListcloseListener)
  {
    if (paramOnButtonListcloseListener != null) {
      this.listcloselistener = paramOnButtonListcloseListener;
    }
  }
  
  public void setState(int paramInt1, int paramInt2)
  {
    AutoResizeTextView localAutoResizeTextView = this.b4;
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
    this.b4.setTextSize(paramInt, paramFloat);
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
          TimerMenuList.this.b1.setColors(-1, -7829368);
        }
        for (;;)
        {
          return true;
          if (paramView.getId() == 2131296452) {
            TimerMenuList.this.b2.setColors(-1, -7829368);
          } else if (paramView.getId() == 2131296453) {
            TimerMenuList.this.b3.setColors(-1, -7829368);
          } else if (paramView.getId() == 2131296454) {
            TimerMenuList.this.b4.setColors(-1, -7829368);
          }
        }
      }
      int i;
      if (TimerMenuList.this.listcloselistener != null)
      {
        i = 0;
        if (paramView.getId() != 2131296451) {
          break label172;
        }
        i = 1;
      }
      for (;;)
      {
        TimerMenuList.this.listcloselistener.onclosed(TimerMenuList.this, i);
        TimerMenuList.this.setVisibility(8);
        break;
        label172:
        if (paramView.getId() == 2131296452) {
          i = 2;
        } else if (paramView.getId() == 2131296453) {
          i = 3;
        } else if (paramView.getId() == 2131296454) {
          i = 0;
        }
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseClass\TimerMenuList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */