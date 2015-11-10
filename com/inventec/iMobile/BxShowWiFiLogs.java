package com.inventec.iMobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseOP.AppDealWifi;

public class BxShowWiFiLogs
  extends myActivity
{
  RelativeLayout titlebar;
  Button titlebutton;
  TextView titletxt;
  
  public void DoUpdate()
  {
    MvShowHistory localMvShowHistory = (MvShowHistory)this.mCurMainView;
    if (localMvShowHistory != null) {
      localMvShowHistory.doClear();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    hideTabBar();
    SetMainView(new MvShowHistory(this));
    SetTitleText("WiFi Logs");
  }
  
  public void updateTitleBarBtn()
  {
    super.updateTitleBarBtn();
    this.btnUpdate.chengeBg(2130837608, -1, 2130837609, -12303292);
    this.btnUpdate.setText(2131165448);
  }
  
  class MvShowHistory
    extends myMainView
  {
    public MvShowHistory(myActivity parammyActivity)
    {
      super(2130903106);
      ((TextView)this.mView.findViewById(2131296651)).setText(AppDealWifi.GetLog());
      ((TextView)this.mView.findViewById(2131296650)).setText("192.168.8.46");
    }
    
    public void doClear()
    {
      AppDealWifi.ClearLog();
      ((TextView)this.mView.findViewById(2131296651)).setText("");
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\BxShowWiFiLogs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */