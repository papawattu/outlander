package com.inventec.iMobile.mySettingItem;

import android.content.res.Resources;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import com.inventec.controls.MyTextView;
import com.inventec.iMobile.baseClass.mySwitch;
import com.inventec.iMobile.baseClass.mySwitch.OnSwitchStateChanged;
import com.inventec.iMobile.iMobile_AppGlobalVar;
import java.util.ArrayList;

public class SettingItem_Check
  extends SettingItem
{
  private ItemOnCheckChange mCheckChg;
  boolean m_bChecked = false;
  mySwitch myCheck = null;
  String swText;
  int swType = 0;
  
  public SettingItem_Check(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }
  
  private void checkChg(boolean paramBoolean)
  {
    if (this.mCheckChg != null) {
      this.mCheckChg.OnCheckChange(paramBoolean);
    }
  }
  
  public View GetConvertView(LayoutInflater paramLayoutInflater, View paramView, ArrayList<SettingItem> paramArrayList, int paramInt)
  {
    View localView = reuseView(paramLayoutInflater, paramView, paramArrayList, paramInt);
    this.myCheck = ((mySwitch)localView.findViewById(2131296268));
    MyTextView localMyTextView = (MyTextView)localView.findViewById(2131296546);
    localMyTextView.setLeft();
    localMyTextView.setTextSize(0, 17.0F * this.textScale);
    localMyTextView.getPaint().setFakeBoldText(true);
    paramLayoutInflater = (SettingItem)paramArrayList.get(paramInt);
    if (paramLayoutInflater.GetID() == -2)
    {
      localMyTextView.setVisibility(8);
      this.myCheck.setVisibility(8);
      localView.setMinimumHeight((int)iMobile_AppGlobalVar.getTrueSizePix(10.0F));
      suitSelfStatus();
      localView.requestLayout();
      return localView;
    }
    paramInt = paramLayoutInflater.GetText();
    paramView = localView.getResources().getString(paramInt);
    paramLayoutInflater = paramView;
    if (iMobile_AppGlobalVar.isPad) {
      paramLayoutInflater = paramView.replace("\n", " ");
    }
    localMyTextView.setText(paramLayoutInflater);
    localMyTextView.setVisibility(0);
    if (this.swType == 0)
    {
      this.myCheck.setCheckState(this.m_bChecked);
      this.myCheck.setEnabled(true);
      this.myCheck.showAsItem(0);
      this.myCheck.SetOnSwitchStateChanged(new mySwitch.OnSwitchStateChanged()
      {
        public void onswitchclose()
        {
          SettingItem_Check.this.checkChg(false);
        }
        
        public void onswitchopen()
        {
          SettingItem_Check.this.checkChg(true);
        }
      });
    }
    for (;;)
    {
      this.myCheck.setVisibility(0);
      break;
      this.myCheck.showAsItem(this.swType);
      if (this.swType == 2)
      {
        this.myCheck.SetText(this.swText);
        this.myCheck.setEnabled(false);
      }
    }
  }
  
  public int GetXmlResID()
  {
    return 2130903079;
  }
  
  public void SetOnCheckChg(ItemOnCheckChange paramItemOnCheckChange)
  {
    this.mCheckChg = paramItemOnCheckChange;
  }
  
  public void SetText(String paramString)
  {
    this.swType = 2;
    this.swText = paramString;
    if (this.myCheck != null)
    {
      this.myCheck.SetText(this.swText);
      this.myCheck.setEnabled(false);
    }
  }
  
  public void setCheckState(boolean paramBoolean)
  {
    this.m_bChecked = paramBoolean;
    if ((this.myCheck != null) && (this.swType == 0)) {
      this.myCheck.setCheckState(this.m_bChecked);
    }
  }
  
  public void setSWType(int paramInt)
  {
    this.swType = paramInt;
  }
  
  protected void suitSelfStatus()
  {
    super.suitSelfStatus();
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\mySettingItem\SettingItem_Check.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */