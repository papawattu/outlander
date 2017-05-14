package com.inventec.controls;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager
  extends ViewPager
{
  private boolean enabled = true;
  private int parentId;
  
  public MyViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.enabled) {
      if (this.parentId > 0)
      {
        ViewPager localViewPager = (ViewPager)findViewById(this.parentId);
        if (localViewPager != null) {
          localViewPager.requestDisallowInterceptTouchEvent(true);
        }
      }
    }
    for (boolean bool = super.onInterceptTouchEvent(paramMotionEvent);; bool = false) {
      return bool;
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.enabled) {}
    for (boolean bool = super.onTouchEvent(paramMotionEvent);; bool = false) {
      return bool;
    }
  }
  
  public void setAllowChildMovement(int paramInt)
  {
    this.parentId = paramInt;
  }
  
  public void setPagingEnabled(boolean paramBoolean)
  {
    this.enabled = paramBoolean;
  }
  
  void setSelItem(int paramInt)
  {
    scrollTo(getWidth() * paramInt, 0);
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\controls\MyViewPager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */