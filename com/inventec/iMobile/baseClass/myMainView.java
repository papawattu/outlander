package com.inventec.iMobile.baseClass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

public class myMainView
{
  protected myActivity mAct;
  protected View mView;
  
  public myMainView(myActivity parammyActivity)
  {
    this.mAct = parammyActivity;
  }
  
  public myMainView(myActivity parammyActivity, int paramInt)
  {
    this.mAct = parammyActivity;
    initView(paramInt);
  }
  
  public View GetView()
  {
    return this.mView;
  }
  
  protected int GetViewParaHeight(View paramView)
  {
    int i = 0;
    if (paramView != null) {
      i = paramView.getLayoutParams().height;
    }
    return i;
  }
  
  protected int GetViewParaWidth(View paramView)
  {
    int i = 0;
    if (paramView != null) {
      i = paramView.getLayoutParams().width;
    }
    return i;
  }
  
  protected int GetWindowHeight()
  {
    return this.mAct.GetWindowHeight();
  }
  
  protected int GetWindowWidth()
  {
    return this.mAct.GetWindowWidth();
  }
  
  public void PopupHelpInfo() {}
  
  public void Refresh() {}
  
  public void Update()
  {
    Refresh();
  }
  
  public void addMyViewMargin(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    localMarginLayoutParams.topMargin += paramInt1;
    localMarginLayoutParams.bottomMargin += paramInt2;
    localMarginLayoutParams.leftMargin += paramInt3;
    localMarginLayoutParams.rightMargin += paramInt4;
    paramView.setLayoutParams(localMarginLayoutParams);
  }
  
  public int dip2px(myActivity parammyActivity, float paramFloat)
  {
    return parammyActivity.px2dip(paramFloat);
  }
  
  public void enlargeViewHeight(View paramView)
  {
    if (paramView != null)
    {
      ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
      localLayoutParams.height = ((int)(this.mAct.px2dip(localLayoutParams.height) * this.mAct.getScrScale()));
      paramView.setLayoutParams(localLayoutParams);
    }
  }
  
  public void enlargeViewHeight(View paramView, int paramInt)
  {
    if (paramView != null)
    {
      ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
      localLayoutParams.height = paramInt;
      paramView.setLayoutParams(localLayoutParams);
    }
  }
  
  public void enlargeViewSize(View paramView, int paramInt1, int paramInt2)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    localLayoutParams.width = paramInt1;
    localLayoutParams.height = paramInt2;
    paramView.setLayoutParams(localLayoutParams);
  }
  
  public void enlargeViewWidth(View paramView, int paramInt)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    localLayoutParams.width = paramInt;
    paramView.setLayoutParams(localLayoutParams);
  }
  
  protected int getViewSpaceVertical(View paramView)
  {
    int j = 0;
    int i = 0;
    if (paramView != null)
    {
      ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
      j = i;
      if (localLayoutParams.height > 0) {
        j = 0 + localLayoutParams.height;
      }
      paramView = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
      i = j;
      if (paramView.topMargin > 0) {
        i = j + paramView.topMargin;
      }
      j = i;
      if (paramView.bottomMargin > 0) {
        j = i + paramView.bottomMargin;
      }
    }
    return j;
  }
  
  public void initView(int paramInt)
  {
    this.mView = LayoutInflater.from(this.mAct).inflate(paramInt, null);
  }
  
  public int px2dip(myActivity parammyActivity, float paramFloat)
  {
    return parammyActivity.px2dip(paramFloat);
  }
  
  public void setMyViewMargin(View paramView, int paramInt1, int paramInt2)
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
    localMarginLayoutParams.topMargin = paramInt1;
    localMarginLayoutParams.bottomMargin = paramInt2;
    paramView.setLayoutParams(localMarginLayoutParams);
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseClass\myMainView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */