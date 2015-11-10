package com.inventec.iMobile.mySettingItem;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.inventec.controls.MyButton;
import com.inventec.controls.MyTextView;
import java.util.ArrayList;

public abstract class SettingItem
{
  public static final int BlankItemID = -2;
  public static final int NoTextResID = 0;
  private int mID;
  private ItemOnClick mOnclickListener;
  private int mTextResID;
  protected boolean m_bEnabled;
  protected boolean m_bSeled;
  protected boolean mbNeedBottom = false;
  protected boolean mbNeedTop = false;
  protected View selfView = null;
  protected float textScale = 1.0F;
  
  public SettingItem(int paramInt1, int paramInt2)
  {
    this.mTextResID = paramInt1;
    this.mID = paramInt2;
    this.m_bEnabled = true;
    this.m_bSeled = false;
  }
  
  public abstract View GetConvertView(LayoutInflater paramLayoutInflater, View paramView, ArrayList<SettingItem> paramArrayList, int paramInt);
  
  public int GetID()
  {
    return this.mID;
  }
  
  public View GetSelfView()
  {
    return this.selfView;
  }
  
  public int GetText()
  {
    return this.mTextResID;
  }
  
  public abstract int GetXmlResID();
  
  public void SetOnTrigger(ItemOnClick paramItemOnClick)
  {
    this.mOnclickListener = paramItemOnClick;
  }
  
  public void Trigger()
  {
    if (this.mOnclickListener != null) {
      this.mOnclickListener.OnClick(this);
    }
  }
  
  int getBgResID()
  {
    int i;
    if ((this.mbNeedTop) && (this.mbNeedBottom)) {
      if (!this.m_bEnabled) {
        i = 2130837687;
      }
    }
    for (;;)
    {
      return i;
      if (this.m_bSeled)
      {
        i = 2130837688;
      }
      else
      {
        i = 2130837686;
        continue;
        if (this.mbNeedTop)
        {
          if (!this.m_bEnabled) {
            i = 2130837690;
          } else if (this.m_bSeled) {
            i = 2130837691;
          } else {
            i = 2130837689;
          }
        }
        else if (this.mbNeedBottom)
        {
          if (!this.m_bEnabled) {
            i = 2130837681;
          } else if (this.m_bSeled) {
            i = 2130837682;
          } else {
            i = 2130837680;
          }
        }
        else if (!this.m_bEnabled) {
          i = 2130837684;
        } else if (this.m_bSeled) {
          i = 2130837685;
        } else {
          i = 2130837683;
        }
      }
    }
  }
  
  protected View reuseView(LayoutInflater paramLayoutInflater, View paramView, ArrayList<SettingItem> paramArrayList, int paramInt)
  {
    if (this.selfView == null) {
      this.selfView = ((RelativeLayout)paramLayoutInflater.inflate(GetXmlResID(), null));
    }
    int i = paramArrayList.size();
    boolean bool1 = false;
    boolean bool2 = false;
    if (paramInt == 0)
    {
      bool1 = true;
      if (paramInt != i - 1) {
        break label97;
      }
      bool2 = true;
    }
    for (;;)
    {
      this.mbNeedTop = bool1;
      this.mbNeedBottom = bool2;
      return this.selfView;
      if (((SettingItem)paramArrayList.get(paramInt - 1)).GetID() != -2) {
        break;
      }
      bool1 = true;
      break;
      label97:
      if (((SettingItem)paramArrayList.get(paramInt + 1)).GetID() == -2) {
        bool2 = true;
      }
    }
  }
  
  public void setItemEnabled(boolean paramBoolean)
  {
    this.m_bEnabled = paramBoolean;
    suitSelfStatus();
  }
  
  public void setItemSeled(boolean paramBoolean)
  {
    this.m_bSeled = paramBoolean;
    suitSelfStatus();
  }
  
  public void setTextSizeScale(float paramFloat)
  {
    this.textScale = paramFloat;
  }
  
  protected void suitSelfStatus()
  {
    if (this.selfView == null) {}
    for (;;)
    {
      return;
      suitSelfTextColor();
      if ((RelativeLayout)this.selfView.findViewById(2131296545) != null)
      {
        RelativeLayout localRelativeLayout = (RelativeLayout)this.selfView.findViewById(2131296545);
        if (this.mID != -2) {
          localRelativeLayout.setBackgroundResource(getBgResID());
        } else {
          localRelativeLayout.setBackgroundDrawable(null);
        }
      }
    }
  }
  
  protected void suitSelfTextColor()
  {
    if (this.selfView == null) {}
    for (;;)
    {
      return;
      MyTextView localMyTextView = (MyTextView)this.selfView.findViewById(2131296546);
      if (localMyTextView != null) {
        if (this.m_bEnabled) {
          localMyTextView.setColors(MyButton.colNor, -1);
        } else {
          localMyTextView.setColors(-7829368, 0);
        }
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\mySettingItem\SettingItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */