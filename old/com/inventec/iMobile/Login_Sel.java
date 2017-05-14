package com.inventec.iMobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseOP.AppDealWifi;
import com.inventec.iMobile.baseStruct.DealMsg;

public class Login_Sel
  extends myActivity
{
  public boolean canShowErrorDlg()
  {
    return false;
  }
  
  void doGetSSID()
  {
    AppDealWifi.LogMsg("Login_Sel : Login_SSID");
    Intent localIntent = new Intent();
    localIntent.setClass(this, Login_SSID.class);
    startActivity(localIntent);
    finish();
  }
  
  public void onBackPressed()
  {
    System.gc();
    AppDealWifi.closeFile();
    System.exit(0);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903070);
    statusBarHeight = 0;
    setEarth((ImageView)findViewById(2131296257));
    enlargeViewToFitScreen((TextView)findViewById(2131296264), true);
    AppDealWifi.LogMsg("Login_Sel Create");
    MyButton localMyButton = (MyButton)findViewById(2131296456);
    enlargeViewToFitScreen(localMyButton, true);
    paramBundle = (MyButton)findViewById(2131296452);
    enlargeViewToFitScreen(paramBundle, true);
    localMyButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Login_Sel.this.doGetSSID();
      }
    });
    paramBundle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Login_Sel.this.startFrmMain_Demo();
      }
    });
    enlargeViewToFitScreen((TextView)findViewById(2131296455), true);
  }
  
  protected void onResume()
  {
    super.onResume();
    iMobile_AppGlobalVar.finishWifi();
  }
  
  void startFrmMain_Demo()
  {
    AppDealWifi.LogMsg("Login_Sel : startFrmMain_Demo");
    DealMsg.initDemoValue();
    DealMsg.g_isDemo = true;
    iMobile_AppGlobalVar.finishWifi();
    Intent localIntent = new Intent();
    localIntent.setClass(this, FrmMain.class);
    startActivity(localIntent);
    finish();
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\Login_Sel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */