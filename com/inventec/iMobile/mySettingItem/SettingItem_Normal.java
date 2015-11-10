package com.inventec.iMobile.mySettingItem;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import com.inventec.controls.MyButton;
import com.inventec.controls.MyTextView;
import java.util.ArrayList;

public class SettingItem_Normal
  extends SettingItem
{
  public SettingItem_Normal(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  public View GetConvertView(LayoutInflater paramLayoutInflater, View paramView, ArrayList<SettingItem> paramArrayList, int paramInt)
  {
    paramView = reuseView(paramLayoutInflater, paramView, paramArrayList, paramInt);
    paramArrayList = (SettingItem)paramArrayList.get(paramInt);
    paramLayoutInflater = "";
    paramInt = paramArrayList.GetText();
    if (paramInt != 0) {
      paramLayoutInflater = paramView.getResources().getString(paramInt);
    }
    paramArrayList = (MyTextView)paramView.findViewById(2131296546);
    paramArrayList.setPadding(0, 3, 0, 0);
    paramArrayList.setLeft();
    paramArrayList.setTextSize(0, 18.0F * this.textScale);
    paramArrayList.setText(paramLayoutInflater);
    paramLayoutInflater = (ImageView)paramView.findViewById(2131296547);
    if (paramInt == 0) {
      paramLayoutInflater.setVisibility(4);
    }
    for (;;)
    {
      suitSelfStatus();
      return paramView;
      paramLayoutInflater.setVisibility(0);
    }
  }
  
  public int GetXmlResID()
  {
    return 2130903080;
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


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\mySettingItem\SettingItem_Normal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */