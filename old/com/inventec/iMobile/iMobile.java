package com.inventec.iMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.inventec.iMobile.baseOP.AppDealWifi;

public class iMobile
  extends Activity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    AppDealWifi.LogMsg("on iMobile create");
    if (!iMobile_AppGlobalVar.noLoad) {
      AppDealWifi.LogMsg("Application not loaded");
    }
    try
    {
      Thread.sleep(150L);
      iMobile_AppGlobalVar.noLoad = true;
      paramBundle = new Intent();
      iMobile_AppGlobalVar.needRestart = false;
      paramBundle.setClass(this, PasswordInput.class);
      startActivity(paramBundle);
      finish();
      return;
    }
    catch (InterruptedException paramBundle)
    {
      for (;;)
      {
        paramBundle.printStackTrace();
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\iMobile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */