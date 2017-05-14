package com.inventec.iMobile.baseClass;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.inventec.iMobile.baseOP.AppDealWifi;
import com.inventec.iMobile.baseOP.ConnSSID;
import com.inventec.iMobile.baseOP.MsgReceiver;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.baseStruct.DefMsg;
import com.inventec.iMobile.iMobile_AppGlobalVar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class myActivityBase
  extends Activity
{
  protected static boolean background = false;
  protected static myActivityBase mCurForegroundActivity = null;
  
  public static myActivityBase GetCurForegroundActivity()
  {
    return mCurForegroundActivity;
  }
  
  public void doLoadAll()
  {
    iMobile_AppGlobalVar.SetThisApp((iMobile_AppGlobalVar)getApplication());
    iMobile_AppGlobalVar.getDeviceInfo(this);
    DealMsg.loadAll(this);
    DefMsg.loadAll(this);
    AppDealWifi.LogMsg("language is " + Locale.getDefault().getLanguage());
    if (!DealMsg.g_isDemo)
    {
      if (iMobile_AppGlobalVar.isConn()) {
        break label74;
      }
      AppDealWifi.LogMsg(" connect socket in doLoadAll");
      iMobile_AppGlobalVar.CloseConn();
      iMobile_AppGlobalVar.BackgroundReConn();
    }
    for (;;)
    {
      MsgReceiver.loadAll(this);
      return;
      label74:
      AppDealWifi.LogMsg(" socket is already connected in doLoadAll");
    }
  }
  
  public void doSaveAll()
  {
    DealMsg.saveAll(this);
    DefMsg.saveAll(this);
    MsgReceiver.saveAll(this);
  }
  
  public void finishApp()
  {
    setResult(101);
    finish();
  }
  
  public void hideWaitDlg() {}
  
  public boolean isAppOnForeground()
  {
    boolean bool2 = false;
    Object localObject = (ActivityManager)getApplicationContext().getSystemService("activity");
    String str = getApplicationContext().getPackageName();
    localObject = ((ActivityManager)localObject).getRunningAppProcesses();
    boolean bool1;
    if (localObject == null) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      Iterator localIterator = ((List)localObject).iterator();
      do
      {
        bool1 = bool2;
        if (!localIterator.hasNext()) {
          break;
        }
        localObject = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      } while ((!((ActivityManager.RunningAppProcessInfo)localObject).processName.equals(str)) || (((ActivityManager.RunningAppProcessInfo)localObject).importance != 100));
      bool1 = true;
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == 101) {
      finishApp();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    AppDealWifi.LogMsg(getClass() + " created");
    if (iMobile_AppGlobalVar.noLoad)
    {
      AppDealWifi.LogMsg("Application not loaded");
      doLoadAll();
    }
  }
  
  protected void onPause()
  {
    AppDealWifi.LogMsg(getClass() + " Paused");
    super.onPause();
  }
  
  protected void onResume()
  {
    AppDealWifi.LogMsg(getClass() + " Resumed");
    if (iMobile_AppGlobalVar.noLoad)
    {
      AppDealWifi.LogMsg("Application not loaded");
      doLoadAll();
    }
    super.onResume();
    mCurForegroundActivity = this;
    if ((background) && (MsgReceiver.upgradeProcess == null))
    {
      startConnWifiThread();
      background = false;
    }
  }
  
  protected void onStop()
  {
    AppDealWifi.LogMsg(getClass() + " Stoped");
    super.onStop();
    if (!isAppOnForeground())
    {
      mCurForegroundActivity = null;
      doSaveAll();
      iMobile_AppGlobalVar.needShowSSIDErr = false;
      AppDealWifi.LogMsg("Going to background!needShowSSIDErr = " + iMobile_AppGlobalVar.needShowSSIDErr);
      ConnSSID.cancelConnecting();
      background = true;
    }
  }
  
  public void setEarth(ImageView paramImageView)
  {
    if (paramImageView == null) {}
    ViewGroup.LayoutParams localLayoutParams;
    do
    {
      return;
      localLayoutParams = paramImageView.getLayoutParams();
    } while (localLayoutParams == null);
    int i = iMobile_AppGlobalVar.screenW;
    int j = iMobile_AppGlobalVar.screenH;
    if (i > j) {
      i = j;
    }
    for (;;)
    {
      i -= i / 10;
      localLayoutParams.width = i;
      localLayoutParams.height = i;
      paramImageView.setLayoutParams(localLayoutParams);
      break;
    }
  }
  
  public boolean showLogin()
  {
    return false;
  }
  
  protected void startConnWifiThread()
  {
    final String str1 = DealMsg.getSSID();
    final String str2 = DealMsg.getSSIDPwd();
    if (DealMsg.g_VINNum[0] != 0)
    {
      AppDealWifi.LogMsg(" -------VIN:" + DealMsg.g_VINNum.toString());
      AppDealWifi.LogMsg(" -------Get Saved SSID:" + str1 + "\n ------- PassWord:" + str2);
      if (!ConnSSID.checkCurConnOk(this, str1))
      {
        AppDealWifi.LogMsg(" ------- reset wifi ------- ");
        new Thread(new Runnable()
        {
          public void run()
          {
            iMobile_AppGlobalVar.CloseConn();
            ConnSSID.connectToMyWifi(myActivityBase.mCurForegroundActivity, str1, str2);
          }
        }).start();
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseClass\myActivityBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */