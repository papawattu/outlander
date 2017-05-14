package com.inventec.iMobile;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import com.inventec.iMobile.baseClass.myActivityBase;
import com.inventec.iMobile.baseOP.AppDealWifi;
import com.inventec.iMobile.baseOP.ConnSSID;
import com.inventec.iMobile.baseOP.MsgReceiver;
import com.inventec.iMobile.baseStruct.DealMsg;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"InlinedApi"})
public class iMobile_AppGlobalVar
  extends Application
{
  static byte[] AC_EX_version;
  private static final String WifiLockTag = "com.inventec.iMobile.wifilock";
  public static boolean canShowUpgrade;
  private static AppDealWifi g_appDealWifi;
  public static boolean isPad;
  private static boolean mDoingConn = false;
  private static String mLastWifiSSID;
  private static WifiManager.WifiLock mWifiLock;
  private static boolean mbConnOk;
  public static boolean needRestart;
  public static boolean needShowSSIDErr;
  public static boolean needUpgrade;
  public static boolean noLoad;
  public static float scaledDensity;
  public static int screenH;
  public static int screenW;
  public static boolean showRegOK = false;
  private static iMobile_AppGlobalVar thisApp = null;
  static byte[] version;
  
  static
  {
    needRestart = true;
    version = new byte[] { 48, 48, 49, 68, 48, 49, 56, 48, 48, 48 };
    AC_EX_version = new byte[] { 48, 48, 49, 68, 48, 49, 56, 48, 48, 48 };
    scaledDensity = 1.0F;
    screenW = 320;
    screenH = 480;
    isPad = false;
    canShowUpgrade = false;
    needUpgrade = false;
    needShowSSIDErr = false;
    noLoad = true;
    g_appDealWifi = null;
    mLastWifiSSID = "";
    mbConnOk = false;
  }
  
  static int ASCIIBytesToData(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    int j = (paramArrayOfByte[(paramInt1 + 0)] & 0xFF) - 48;
    for (int i = 1;; i++)
    {
      if (i >= paramInt2) {
        return j;
      }
      j = j * 10 + ((paramArrayOfByte[(paramInt1 + i)] & 0xFF) - 48);
    }
  }
  
  public static void BackgroundReConn()
  {
    String str = DealMsg.getSSID();
    if (g_appDealWifi == null)
    {
      g_appDealWifi = new AppDealWifi();
      g_appDealWifi.initTimer();
    }
    myActivityBase localmyActivityBase = myActivityBase.GetCurForegroundActivity();
    if ((!str.equals("")) && (localmyActivityBase != null) && (ConnSSID.isCurSSIDError(localmyActivityBase, str)))
    {
      AppDealWifi.LogMsg("ssid mismatch:needShowSSIDErr = " + needShowSSIDErr);
      if (needShowSSIDErr)
      {
        MsgReceiver.BroadcastMsgAction(50, 0);
        needShowSSIDErr = false;
      }
    }
    for (;;)
    {
      return;
      if ((mbConnOk) || (mDoingConn) || (localmyActivityBase == null) || ((!ConnSSID.checkCurConnOk(localmyActivityBase, str)) && (!DealMsg.g_isDemo)))
      {
        AppDealWifi.LogMsg("mbConnOk = " + mbConnOk + " mDoingConn = " + mDoingConn + " act = " + localmyActivityBase);
      }
      else
      {
        AppDealWifi.LogMsg("Do Background ReConn");
        new Thread()
        {
          public void run()
          {
            int i = 0;
            iMobile_AppGlobalVar.mDoingConn = true;
            for (;;)
            {
              if ((iMobile_AppGlobalVar.mbConnOk) || (i >= 600))
              {
                if (!iMobile_AppGlobalVar.mbConnOk) {
                  AppDealWifi.LogMsg("Socket connection failed");
                }
                iMobile_AppGlobalVar.mDoingConn = false;
                return;
              }
              iMobile_AppGlobalVar.StartConn();
              int j = i + 1;
              i = j;
              if (iMobile_AppGlobalVar.mbConnOk)
              {
                AppDealWifi.LogMsg("Socket connection succeed,tested " + j + " times");
                i = 0;
              }
            }
          }
        }.start();
      }
    }
  }
  
  public static void CloseConn()
  {
    if (mbConnOk)
    {
      mbConnOk = false;
      if (g_appDealWifi != null) {
        g_appDealWifi.myCloseConn();
      }
    }
  }
  
  public static iMobile_AppGlobalVar GetThisApp()
  {
    return thisApp;
  }
  
  public static boolean HasExACFunction(byte[] paramArrayOfByte, int paramInt)
  {
    boolean bool = false;
    int i = ASCIIBytesToData(AC_EX_version, 1, 3);
    if (paramArrayOfByte[paramInt] == 0) {
      return bool;
    }
    if (ASCIIBytesToData(paramArrayOfByte, paramInt + 1, 3) < i) {}
    for (bool = true;; bool = false) {
      break;
    }
  }
  
  public static void LockWifi(Context paramContext)
  {
    if (mWifiLock == null) {
      mWifiLock = ((WifiManager)paramContext.getSystemService("wifi")).createWifiLock("com.inventec.iMobile.wifilock");
    }
    if (mWifiLock != null) {
      mWifiLock.acquire();
    }
  }
  
  public static String RevertToLastWifi(Context paramContext)
  {
    finishWifi();
    AppDealWifi.LogMsg("Try reverting last wifi");
    String str = mLastWifiSSID;
    mLastWifiSSID = null;
    if ((str == null) || (str.equals("")))
    {
      AppDealWifi.LogMsg("SSID of last wifi is invalid:" + str);
      paramContext = null;
    }
    for (;;)
    {
      return paramContext;
      if (!ConnSSID.isSSIDCanConnect(paramContext, str))
      {
        AppDealWifi.LogMsg("ssid of last wifi(" + str + ") is not being broadcasted");
        paramContext = null;
      }
      else
      {
        AppDealWifi.LogMsg("reconnet to wifi:" + str);
        WifiManager localWifiManager = ConnSSID.getWifi(paramContext);
        if (localWifiManager == null)
        {
          paramContext = null;
        }
        else
        {
          paramContext = localWifiManager.getConnectionInfo();
          if ((paramContext != null) && (paramContext.getSSID() != null) && (ConnSSID.tripQuot(paramContext.getSSID()).equals(str)) && (paramContext.getSupplicantState() == SupplicantState.COMPLETED))
          {
            paramContext = null;
          }
          else
          {
            Object localObject = null;
            Iterator localIterator = localWifiManager.getConfiguredNetworks().iterator();
            label180:
            if (!localIterator.hasNext()) {
              paramContext = (Context)localObject;
            }
            for (;;)
            {
              if (paramContext != null) {
                break label239;
              }
              Log.e("iMobile", "can't find the config");
              paramContext = null;
              break;
              paramContext = (WifiConfiguration)localIterator.next();
              if (!str.equals(tripQuot(paramContext.SSID))) {
                break label180;
              }
            }
            label239:
            int i = paramContext.networkId;
            if (i == -1)
            {
              paramContext = null;
            }
            else if (localWifiManager.enableNetwork(i, true))
            {
              Log.e("iMobile", "open last wifi OK");
              paramContext = str;
            }
            else
            {
              paramContext = null;
            }
          }
        }
      }
    }
  }
  
  public static void SetThisApp(iMobile_AppGlobalVar paramiMobile_AppGlobalVar)
  {
    thisApp = paramiMobile_AppGlobalVar;
  }
  
  static void StartConn()
  {
    AppDealWifi.LogMsg("StartConn,g_appDealWifi = " + g_appDealWifi);
    if (g_appDealWifi != null)
    {
      if (g_appDealWifi.myOpenConn()) {
        mbConnOk = true;
      }
      if (mbConnOk)
      {
        MsgReceiver.openRegistration();
        g_appDealWifi.myStartRecv();
      }
    }
  }
  
  public static void UnlockWifi()
  {
    if ((mWifiLock != null) && (!mWifiLock.isHeld())) {
      mWifiLock.release();
    }
  }
  
  public static void finishWifi()
  {
    AppDealWifi.LogMsg("stop socket connect.");
    if (g_appDealWifi != null)
    {
      g_appDealWifi.cancelTimer();
      CloseConn();
      g_appDealWifi = null;
    }
  }
  
  private static int getDeviceHeight(Activity paramActivity)
  {
    paramActivity.getWindow().getDecorView().getHeight();
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }
  
  public static void getDeviceInfo(Activity paramActivity)
  {
    AppDealWifi.LogMsg("on getDeviceInfo");
    DisplayMetrics localDisplayMetrics1 = paramActivity.getResources().getDisplayMetrics();
    scaledDensity = localDisplayMetrics1.scaledDensity;
    screenW = getDeviceWidth(paramActivity);
    screenH = getDeviceHeight(paramActivity);
    Log.e("", "screen width:" + screenW + " height:" + screenH + " scale:" + scaledDensity + " density:" + localDisplayMetrics1.density);
    AppDealWifi.LogMsg("screen width:" + screenW + " height:" + screenH + " scale:" + scaledDensity + " density:" + localDisplayMetrics1.density);
    if ((paramActivity.getResources().getConfiguration().screenLayout & 0xF) >= 3) {}
    for (int i = 1;; i = 0)
    {
      if (i != 0)
      {
        DisplayMetrics localDisplayMetrics2 = new DisplayMetrics();
        paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics2);
        if ((localDisplayMetrics2.densityDpi != 160) && (localDisplayMetrics2.densityDpi != 240) && (localDisplayMetrics2.densityDpi != 160) && (localDisplayMetrics2.densityDpi != 213) && (localDisplayMetrics2.densityDpi != 320)) {}
      }
      try
      {
        float f1 = localDisplayMetrics1.widthPixels / localDisplayMetrics1.xdpi;
        float f2 = localDisplayMetrics1.heightPixels / localDisplayMetrics1.ydpi;
        double d = Math.sqrt(Math.pow(f1, 2.0D) + Math.pow(f2, 2.0D));
        if (d > 6.0D)
        {
          isPad = true;
          paramActivity = new java/lang/StringBuilder;
          paramActivity.<init>("xdpi:");
          AppDealWifi.LogMsg(localDisplayMetrics1.xdpi + " ydpi:" + localDisplayMetrics1.ydpi);
          paramActivity = new java/lang/StringBuilder;
          paramActivity.<init>("is Pad: screenWidth:");
          AppDealWifi.LogMsg(f1 + " screenHeight:" + f2 + "\r\n size:" + d);
        }
      }
      catch (Throwable paramActivity)
      {
        for (;;)
        {
          Log.e("iMobile", "Failed to compute screen size", paramActivity);
        }
      }
      noLoad = false;
      AppDealWifi.LogMsg("Application loaded");
      return;
    }
  }
  
  private static int getDeviceWidth(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }
  
  public static float getTrueSizePix(float paramFloat)
  {
    return scaledDensity * paramFloat;
  }
  
  public static String getVersion()
  {
    return (char)version[0] + "." + (char)version[1] + (char)version[2] + (char)version[3] + "." + (char)version[4] + "." + (char)version[5] + "." + (char)version[6] + "." + (char)version[7] + (char)version[8] + (char)version[9];
  }
  
  public static boolean isConn()
  {
    return mbConnOk;
  }
  
  public static boolean isConnecting()
  {
    return mDoingConn;
  }
  
  public static Resources myGetResources()
  {
    if (thisApp != null) {}
    for (Resources localResources = thisApp.getResources();; localResources = null) {
      return localResources;
    }
  }
  
  public static boolean needReprogramming(byte[] paramArrayOfByte, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool = true;
    int i1 = ASCIIBytesToData(version, 0, 1);
    int i = ASCIIBytesToData(version, 1, 3);
    int j = ASCIIBytesToData(version, 4, 1);
    int m = ASCIIBytesToData(version, 5, 1);
    int k = ASCIIBytesToData(version, 6, 1);
    int n = ASCIIBytesToData(version, 7, 3);
    int i2 = ASCIIBytesToData(paramArrayOfByte, paramInt + 0, 1);
    if (paramArrayOfByte[paramInt] == 0)
    {
      bool = false;
      return bool;
    }
    int i3;
    if (i2 == i1)
    {
      i3 = ASCIIBytesToData(paramArrayOfByte, paramInt + 1, 3);
      int i4 = ASCIIBytesToData(paramArrayOfByte, paramInt + 4, 1);
      i2 = ASCIIBytesToData(paramArrayOfByte, paramInt + 5, 1);
      i1 = ASCIIBytesToData(paramArrayOfByte, paramInt + 6, 1);
      paramInt = ASCIIBytesToData(paramArrayOfByte, paramInt + 7, 3);
      if (!paramBoolean1) {
        break label195;
      }
      if ((i3 != i) || (i4 != j) || (i2 != m) || (i1 != k) || (paramInt != n)) {
        break label189;
      }
    }
    label189:
    for (bool = false;; bool = true) {
      break;
    }
    label195:
    if (paramBoolean2)
    {
      if (i3 <= i) {}
      for (bool = true;; bool = false) {
        break;
      }
    }
    if (i3 < i) {}
    for (bool = true;; bool = false) {
      break;
    }
  }
  
  public static boolean resendKMsg(byte[] paramArrayOfByte)
  {
    boolean bool = false;
    if (!mbConnOk) {}
    for (;;)
    {
      return bool;
      if (g_appDealWifi != null) {
        bool = g_appDealWifi.mySend(paramArrayOfByte);
      }
    }
  }
  
  public static void setLastWifi(String paramString)
  {
    mLastWifiSSID = paramString;
  }
  
  public static void setReprogrammingTimeStamp(byte[] paramArrayOfByte)
  {
    Calendar localCalendar = Calendar.getInstance();
    paramArrayOfByte[0] = toBCD((byte)(localCalendar.get(1) - 2000));
    paramArrayOfByte[1] = toBCD((byte)(localCalendar.get(2) + 1));
    paramArrayOfByte[2] = toBCD((byte)localCalendar.get(5));
    paramArrayOfByte[3] = toBCD((byte)localCalendar.get(11));
    paramArrayOfByte[4] = toBCD((byte)localCalendar.get(12));
    paramArrayOfByte[5] = -1;
    paramArrayOfByte[6] = version[0];
    paramArrayOfByte[7] = version[1];
    paramArrayOfByte[8] = version[2];
    paramArrayOfByte[9] = version[3];
  }
  
  public static void setWebContent(WebView paramWebView, String paramString, int paramInt)
  {
    if (paramWebView == null) {}
    for (;;)
    {
      return;
      paramWebView.setBackgroundColor(0);
      if (Build.VERSION.SDK_INT > 10) {
        paramWebView.setLayerType(1, null);
      }
      paramInt = (int)(16.5F * screenW / 320.0F / paramWebView.getScale());
      String str = "<div style=\"font-size:" + paramInt + "px;color:white;text-shadow:1px 1px #111;line-height:1.42\">";
      str = "<html><head><meta content=\"text/html; charset=utf-8; initial-scale=1.0; user-scalable=no\"><style>img{margin:0px;padding:0px;}tr{margin:0px;padding:0px;}td{font-size:" + paramInt + "px;color:white;text-shadow:1px 1px #111;vertical-align:middle;margin:0px;padding:0px;}" + "P{margin:5px 0px 9px;padding:0px 0px}" + "a{font-size:" + paramInt * 1.2D + "px;text-decoration:none;background:url(file:///android_asset/underline.png) repeat-x 100%% 100%% ;padding-bottom:4px;word-wrap:break-word;word-break:normal;}" + "</style></head>" + "<body style=\"background-color:transparent;\">" + str + paramString + "</div></body></html>";
      paramWebView.loadDataWithBaseURL(null, str, "text/html", "utf-8", null);
      AppDealWifi.LogMsg("scale of web:" + paramWebView.getScale());
      AppDealWifi.LogMsg("web content:" + paramString);
      AppDealWifi.LogMsg("web string:" + str);
    }
  }
  
  static byte toBCD(byte paramByte)
  {
    return (byte)(paramByte / 10 * 16 + paramByte % 10);
  }
  
  private static String tripQuot(String paramString)
  {
    if (paramString == null) {}
    for (paramString = "";; paramString = paramString.replaceAll("^\"(.*)\"$", "$1")) {
      return paramString;
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\iMobile_AppGlobalVar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */