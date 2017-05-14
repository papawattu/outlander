package com.inventec.iMobile.baseOP;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import android.util.Log;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.iMobile_AppGlobalVar;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

public class ConnSSID
{
  public static String curConnPass;
  public static String curConnSSID;
  static IntentFilter i;
  static WifiManager.WifiLock lock = null;
  private static boolean mbNeedCheckConnOk;
  static BroadcastReceiver scanResultReceiver;
  static boolean scanning;
  private static boolean wifiConnecting = false;
  
  static
  {
    i = new IntentFilter();
    scanning = false;
    scanResultReceiver = new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        AppDealWifi.LogMsg("Scanning over");
        ConnSSID.scanning = false;
        paramAnonymousContext.unregisterReceiver(this);
      }
    };
    curConnSSID = "";
    curConnPass = "";
    mbNeedCheckConnOk = true;
  }
  
  public static void LockWifi(Context paramContext)
  {
    paramContext = getWifi(paramContext);
    if (paramContext == null) {}
    for (;;)
    {
      return;
      if (lock == null)
      {
        lock = paramContext.createWifiLock("iMobile");
        lock.acquire();
        AppDealWifi.LogMsg("LockWifi");
      }
    }
  }
  
  public static void UnlockWifi(Context paramContext)
  {
    if (lock == null) {}
    for (;;)
    {
      return;
      lock.release();
      lock = null;
      AppDealWifi.LogMsg("UnlockWifi");
    }
  }
  
  public static void cancelConnecting()
  {
    AppDealWifi.LogMsg("cancel connecting wifi");
    mbNeedCheckConnOk = false;
  }
  
  public static boolean checkCurConnOk(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    WifiInfo localWifiInfo = getWifiInfo(paramContext);
    boolean bool1 = DealMsg.g_isDemo;
    if (localWifiInfo == null)
    {
      AppDealWifi.LogMsg("failed to get wifi info");
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      if (wifiConnecting)
      {
        AppDealWifi.LogMsg("wifiConnecting");
        bool1 = bool2;
      }
      else
      {
        AppDealWifi.LogMsg("current SSID: " + tripQuot(localWifiInfo.getSSID()));
        AppDealWifi.LogMsg("expecting SSID: " + paramString);
        if (tripQuot(localWifiInfo.getSSID()).equals(paramString))
        {
          int j = localWifiInfo.getIpAddress() & 0xFFFFFF;
          if (j == 567488)
          {
            AppDealWifi.LogMsg("cur wifi info: " + localWifiInfo.toString());
            bool1 = true;
          }
          else
          {
            if (j != 0) {
              curConnSSID = "";
            }
            AppDealWifi.LogMsg("ip not correct: " + j);
            bool1 = bool2;
            if ((j & 0xFFFF) == 65193)
            {
              paramContext = getWifi(paramContext);
              bool1 = bool2;
              if (paramContext != null) {
                try
                {
                  paramContext.setWifiEnabled(false);
                  Thread.sleep(3000L);
                  paramContext.setWifiEnabled(true);
                  Thread.sleep(3000L);
                  bool1 = bool2;
                }
                catch (InterruptedException paramContext)
                {
                  paramContext.printStackTrace();
                  bool1 = bool2;
                }
              }
            }
          }
        }
        else
        {
          AppDealWifi.LogMsg("SSID mismatch");
          bool1 = bool2;
        }
      }
    }
  }
  
  public static int connectToMyWifi(Context paramContext, String paramString1, String paramString2)
  {
    AppDealWifi.LogMsg("start connecting to wifi->ssid:" + paramString1 + " pwd:" + paramString2);
    if (wifiConnecting) {
      cancelConnecting();
    }
    try
    {
      Thread.sleep(500L);
      wifiConnecting = true;
      WifiManager localWifiManager = getWifi(paramContext);
      if (localWifiManager == null)
      {
        AppDealWifi.LogMsg("Wifi not enabled");
        wifiConnecting = false;
        k = 1;
        return k;
      }
    }
    catch (InterruptedException localInterruptedException1)
    {
      for (;;)
      {
        int k;
        AppDealWifi.LogExceptionMsg(localInterruptedException1);
        AppDealWifi.LogMsg("sleep failed");
        continue;
        Object localObject1 = localInterruptedException1.getConnectionInfo();
        if (localObject1 == null)
        {
          wifiConnecting = false;
          k = 2;
        }
        else
        {
          int i8 = 0;
          int i1 = -1;
          int n = 0;
          int i5 = 0;
          int m = 20;
          int i4 = 20;
          mbNeedCheckConnOk = true;
          k = i1;
          int i6 = i5;
          int i2 = i4;
          int i7 = n;
          int i3 = m;
          int j = i8;
          if (curConnSSID.equals(paramString1))
          {
            k = i1;
            i6 = i5;
            i2 = i4;
            i7 = n;
            i3 = m;
            j = i8;
            if (curConnPass.equals(paramString2))
            {
              k = i1;
              i6 = i5;
              i2 = i4;
              i7 = n;
              i3 = m;
              j = i8;
              if (((WifiInfo)localObject1).getSSID() != null)
              {
                k = i1;
                i6 = i5;
                i2 = i4;
                i7 = n;
                i3 = m;
                j = i8;
                if (tripQuot(((WifiInfo)localObject1).getSSID()).equals(paramString1))
                {
                  AppDealWifi.LogMsg("Wifi seems to be already connected. Check ip Address");
                  j = 2;
                  i3 = m;
                  i7 = n;
                  i2 = i4;
                  i6 = i5;
                  k = i1;
                }
              }
            }
          }
          for (;;)
          {
            if (j == 5)
            {
              if (k == 0)
              {
                curConnSSID = paramString1;
                curConnPass = paramString2;
              }
              wifiConnecting = false;
              if (k != 0) {
                break label1117;
              }
              AppDealWifi.LogMsg("wifi Connecting finished");
              break;
            }
            m = k;
            if (!mbNeedCheckConnOk)
            {
              j = 5;
              m = 3;
            }
            switch (j)
            {
            default: 
              k = m;
              break;
            case 0: 
              if (tryConnectWifi(paramContext, paramString1, paramString2))
              {
                j = 1;
                k = m;
              }
              else
              {
                j = 5;
                k = 3;
              }
              break;
            case 1: 
              try
              {
                Thread.sleep(500L);
                localObject1 = getCurrentSupplicantState(paramContext);
                k = m;
                i4 = i6;
                n = i7;
                i5 = i3;
                i1 = j;
                if (localObject1 != null)
                {
                  AppDealWifi.LogMsg(((SupplicantState)localObject1).toString());
                  if (!((SupplicantState)localObject1).equals(SupplicantState.COMPLETED)) {
                    break label576;
                  }
                  i6 = 0;
                  i7++;
                  k = m;
                  i4 = i6;
                  n = i7;
                  i5 = i3;
                  i1 = j;
                  if (i7 >= 8)
                  {
                    i1 = 2;
                    i5 = i3;
                    n = i7;
                    i4 = i6;
                    k = m;
                  }
                }
                if (i5 == 0)
                {
                  localInterruptedException1.setWifiEnabled(false);
                  j = 3;
                  AppDealWifi.LogMsg("Disable Wifi");
                  i6 = i4;
                  i7 = n;
                  i3 = i5;
                }
              }
              catch (InterruptedException localInterruptedException2)
              {
                for (;;)
                {
                  AppDealWifi.LogExceptionMsg(localInterruptedException2);
                  AppDealWifi.LogMsg("sleep failed");
                  continue;
                  if ((localInterruptedException2.equals(SupplicantState.INACTIVE)) || (localInterruptedException2.equals(SupplicantState.SCANNING)))
                  {
                    i4 = i6 + 1;
                    n = 0;
                    k = m;
                    if (i4 >= 40)
                    {
                      j = 5;
                      k = 3;
                      AppDealWifi.LogMsg("Cannot find " + paramString1 + " cancel connecting");
                    }
                    i5 = i3 - 1;
                    i1 = j;
                  }
                  else
                  {
                    n = 0;
                    i5 = i3 - 1;
                    k = m;
                    i4 = i6;
                    i1 = j;
                  }
                }
                AppDealWifi.LogMsg("TimeOut:" + i5);
                i6 = i4;
                i7 = n;
                i3 = i5;
                j = i1;
              }
              break;
            case 2: 
              Object localObject2 = localInterruptedException1.getConnectionInfo();
              k = ((WifiInfo)localObject2).getIpAddress();
              localObject2 = tripQuot(((WifiInfo)localObject2).getSSID());
              if (!((String)localObject2).equals(paramString1))
              {
                AppDealWifi.LogMsg("connected to wrong SSID, try again,connected:" + (String)localObject2 + " expected SSID:" + paramString1);
                AppDealWifi.LogMsg("Disable Wifi");
                localInterruptedException1.setWifiEnabled(false);
                j = 3;
                k = m;
              }
              else if ((0xFFFFFF & k) == 567488)
              {
                AppDealWifi.LogMsg("ip check: OK, ip:" + k + " submask:" + localInterruptedException1.getDhcpInfo().netmask + " getway:" + localInterruptedException1.getDhcpInfo().gateway);
                j = 5;
                k = 0;
              }
              else if (k == 0)
              {
                AppDealWifi.LogMsg("ip not correct: " + k);
                n = i2 - 1;
                try
                {
                  Thread.sleep(500L);
                  k = m;
                  i2 = n;
                  if (n > 0) {
                    continue;
                  }
                  localInterruptedException1.setWifiEnabled(false);
                  j = 3;
                  AppDealWifi.LogMsg("Disable Wifi");
                  i2 = 60;
                  k = m;
                }
                catch (InterruptedException localInterruptedException3)
                {
                  for (;;)
                  {
                    AppDealWifi.LogExceptionMsg(localInterruptedException3);
                    AppDealWifi.LogMsg("sleep failed");
                  }
                }
              }
              else
              {
                curConnSSID = "";
                AppDealWifi.LogMsg("ip not correct: " + k);
                localInterruptedException1.setWifiEnabled(false);
                j = 3;
                AppDealWifi.LogMsg("Disable Wifi");
                k = m;
              }
              break;
            case 3: 
              try
              {
                Thread.sleep(500L);
                k = m;
                if (localInterruptedException1.getWifiState() == 1)
                {
                  localInterruptedException1.setWifiEnabled(true);
                  j = 4;
                  AppDealWifi.LogMsg("Enable Wifi");
                  k = m;
                }
              }
              catch (InterruptedException localInterruptedException4)
              {
                for (;;)
                {
                  AppDealWifi.LogExceptionMsg(localInterruptedException4);
                  AppDealWifi.LogMsg("sleep failed");
                }
              }
            case 4: 
              try
              {
                label576:
                Thread.sleep(500L);
                k = m;
                if (localInterruptedException1.getWifiState() == 3)
                {
                  AppDealWifi.LogMsg("Wifi enabled");
                  j = 0;
                  i3 = 60;
                  k = m;
                }
              }
              catch (InterruptedException localInterruptedException5)
              {
                for (;;)
                {
                  AppDealWifi.LogExceptionMsg(localInterruptedException5);
                  AppDealWifi.LogMsg("sleep failed");
                }
              }
            }
          }
          label1117:
          if (k == 3) {
            AppDealWifi.LogMsg("wifi Connecting canceled");
          } else {
            AppDealWifi.LogMsg("wifi Connecting failed");
          }
        }
      }
    }
  }
  
  public static void enableWifi(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    paramContext = (WifiManager)paramContext.getSystemService("wifi");
    if ((localConnectivityManager != null) && (paramContext != null)) {
      paramContext.setWifiEnabled(true);
    }
  }
  
  public static int genSSIDList(Context paramContext, ArrayList<mySSID> paramArrayList, mySSID parammySSID)
  {
    paramArrayList.clear();
    paramContext = getWifi(paramContext);
    int j;
    if (paramContext == null)
    {
      j = -1;
      return j;
    }
    Object localObject1 = paramContext.getScanResults();
    Object localObject2;
    if (localObject1 != null)
    {
      localObject2 = ((List)localObject1).iterator();
      label36:
      if (((Iterator)localObject2).hasNext()) {}
    }
    else
    {
      localObject1 = paramContext.getConfiguredNetworks();
      if (localObject1 != null)
      {
        label61:
        localObject1 = ((List)localObject1).iterator();
        break label250;
      }
    }
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        paramContext = paramContext.getConnectionInfo();
        if (paramContext != null)
        {
          paramContext = paramContext.getSSID();
          if ((paramContext != null) && (parammySSID.ssid.length() == 0)) {
            parammySSID.ssid = tripQuot(paramContext);
          }
        }
        int k = -1;
        j = k;
        if (parammySSID.ssid.length() == 0) {
          break;
        }
        localObject1 = paramArrayList.iterator();
        do
        {
          j = k;
          if (!((Iterator)localObject1).hasNext()) {
            break;
          }
          paramContext = (mySSID)((Iterator)localObject1).next();
        } while (!paramContext.ssid.equals(parammySSID.ssid));
        j = paramArrayList.indexOf(paramContext);
        parammySSID.prePwd = paramContext.prePwd;
        break;
        localObject1 = (ScanResult)((Iterator)localObject2).next();
        localObject3 = new mySSID();
        ((mySSID)localObject3).ssid = ((ScanResult)localObject1).SSID;
        paramArrayList.add(localObject3);
        break label36;
      }
      Object localObject3 = (WifiConfiguration)((Iterator)localObject1).next();
      String str = tripQuot(((WifiConfiguration)localObject3).SSID);
      Iterator localIterator = paramArrayList.iterator();
      label250:
      if (localIterator.hasNext())
      {
        localObject2 = (mySSID)localIterator.next();
        if (!((mySSID)localObject2).ssid.equals(str)) {
          break label61;
        }
        localObject3 = tripQuot(((WifiConfiguration)localObject3).preSharedKey);
        if ((localObject3 != null) && (!((String)localObject3).equals("*"))) {
          ((mySSID)localObject2).prePwd = ((String)localObject3);
        }
      }
    }
  }
  
  public static String getCurConnSSID(Context paramContext)
  {
    paramContext = getWifiInfo(paramContext);
    if (paramContext != null) {}
    for (paramContext = tripQuot(paramContext.getSSID());; paramContext = null) {
      return paramContext;
    }
  }
  
  public static SupplicantState getCurrentSupplicantState(Context paramContext)
  {
    paramContext = getWifi(paramContext);
    if (paramContext != null)
    {
      paramContext = paramContext.getConnectionInfo();
      if (paramContext == null) {}
    }
    for (paramContext = paramContext.getSupplicantState();; paramContext = null) {
      return paramContext;
    }
  }
  
  public static WifiManager getWifi(Context paramContext)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramContext.getSystemService("connectivity");
    paramContext = (WifiManager)paramContext.getSystemService("wifi");
    if ((localConnectivityManager != null) && (paramContext != null)) {
      if (paramContext.getWifiState() != 3) {}
    }
    for (;;)
    {
      return paramContext;
      AppDealWifi.LogMsg("wifi function not enabled.");
      paramContext = null;
    }
  }
  
  public static WifiInfo getWifiInfo(Context paramContext)
  {
    Object localObject = getWifi(paramContext);
    paramContext = null;
    if (localObject != null)
    {
      localObject = ((WifiManager)localObject).getConnectionInfo();
      paramContext = (Context)localObject;
      if (localObject != null)
      {
        paramContext = (Context)localObject;
        if (((WifiInfo)localObject).getSSID() == null) {
          paramContext = null;
        }
      }
    }
    return paramContext;
  }
  
  public static byte[] getWifiMacAddr(Context paramContext)
  {
    Object localObject2 = getWifi(paramContext);
    Object localObject1 = "";
    paramContext = (Context)localObject1;
    if (localObject2 != null)
    {
      localObject2 = ((WifiManager)localObject2).getConnectionInfo();
      paramContext = (Context)localObject1;
      if (localObject2 != null) {
        paramContext = ((WifiInfo)localObject2).getMacAddress();
      }
    }
    paramContext = paramContext.split(":");
    localObject1 = new byte[6];
    for (int j = 0;; j++)
    {
      if (j >= paramContext.length) {
        return (byte[])localObject1;
      }
      localObject1[j] = ((byte)Integer.parseInt(paramContext[j], 16));
    }
  }
  
  public static boolean isCurSSIDError(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = getWifi(paramContext);
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      bool1 = bool2;
      if (paramString != null)
      {
        paramContext = paramContext.getConnectionInfo();
        bool1 = bool2;
        if (paramContext != null)
        {
          bool1 = bool2;
          if (paramContext.getSSID() != null)
          {
            bool1 = bool2;
            if (paramContext.getSupplicantState() == SupplicantState.COMPLETED)
            {
              bool1 = bool2;
              if (!tripQuot(paramContext.getSSID()).equals(paramString)) {
                bool1 = true;
              }
            }
          }
        }
      }
    }
    return bool1;
  }
  
  public static boolean isIPCorrect(Context paramContext)
  {
    boolean bool2 = false;
    paramContext = getWifi(paramContext);
    boolean bool1 = bool2;
    if (paramContext != null)
    {
      paramContext = paramContext.getConnectionInfo();
      bool1 = bool2;
      if (paramContext != null)
      {
        bool1 = bool2;
        if ((paramContext.getIpAddress() & 0xFFFFFF) == 567488) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static boolean isSSIDCanConnect(Context paramContext, String paramString)
  {
    boolean bool2 = false;
    paramContext = getWifi(paramContext);
    boolean bool1;
    if (paramContext == null) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      paramContext = paramContext.getScanResults();
      bool1 = bool2;
      if (paramContext != null)
      {
        Log.e("iMobile", "scan size:" + paramContext.size());
        Iterator localIterator = paramContext.iterator();
        do
        {
          bool1 = bool2;
          if (!localIterator.hasNext()) {
            break;
          }
          paramContext = (ScanResult)localIterator.next();
          Log.e("iMobile", paramContext.SSID);
        } while (!paramContext.SSID.equals(paramString));
        bool1 = true;
      }
    }
  }
  
  public static void saveCurWifi(Context paramContext)
  {
    if ((curConnSSID != null) && (curConnSSID.length() > 0) && (curConnPass != null) && (curConnPass.length() > 0))
    {
      AppDealWifi.LogMsg(" -------Save SSID:" + curConnSSID + "\n ------- PassWord:" + curConnPass);
      DealMsg.setSSID(curConnSSID);
      DealMsg.setSSIDPwd(curConnPass);
      DealMsg.saveSSID(paramContext);
      DealMsg.saveSSIDPwd(paramContext);
    }
  }
  
  public static void saveLastWifi(Context paramContext)
  {
    paramContext = getWifi(paramContext);
    if (paramContext != null)
    {
      paramContext = paramContext.getConnectionInfo();
      if (paramContext != null) {
        break label19;
      }
    }
    for (;;)
    {
      return;
      label19:
      String str = tripQuot(paramContext.getSSID());
      if ((str != null) && (paramContext.getSupplicantState() == SupplicantState.COMPLETED))
      {
        curConnSSID = str;
        if (!DealMsg.g_SSID.equals(str)) {
          iMobile_AppGlobalVar.setLastWifi(str);
        }
      }
    }
  }
  
  public static void scanAccessPoint(Context paramContext)
  {
    i.addAction("android.net.wifi.SCAN_RESULTS");
    paramContext.registerReceiver(scanResultReceiver, i);
    paramContext = getWifi(paramContext);
    boolean bool;
    if (paramContext != null)
    {
      if (scanning) {
        break label105;
      }
      StringBuilder localStringBuilder = new StringBuilder("start scanning AP,wifi lock is held?");
      if (lock != null) {
        break label95;
      }
      bool = false;
      AppDealWifi.LogMsg(bool);
      bool = paramContext.startScan();
      scanning = true;
      AppDealWifi.LogMsg("scan Access Point result = " + bool);
    }
    for (;;)
    {
      return;
      label95:
      bool = lock.isHeld();
      break;
      label105:
      AppDealWifi.LogMsg("do not start scanning AP because,scanning is still in progress");
    }
  }
  
  public static void stopCurSSID(Context paramContext)
  {
    WifiManager localWifiManager = getWifi(paramContext);
    AppDealWifi.LogMsg("stop Current SSID!!");
    if (localWifiManager == null) {}
    label49:
    label120:
    for (;;)
    {
      return;
      paramContext = localWifiManager.getConnectionInfo();
      if (paramContext != null)
      {
        String str2 = paramContext.getSSID();
        if (str2 != null)
        {
          paramContext = null;
          Iterator localIterator = localWifiManager.getConfiguredNetworks().iterator();
          if (!localIterator.hasNext()) {}
          for (;;)
          {
            if (paramContext == null) {
              break label120;
            }
            int j = localWifiManager.updateNetwork(paramContext);
            if (j == -1) {
              break;
            }
            localWifiManager.disableNetwork(j);
            break;
            WifiConfiguration localWifiConfiguration = (WifiConfiguration)localIterator.next();
            String str1 = tripQuot(localWifiConfiguration.SSID);
            if (!tripQuot(str2).equals(str1)) {
              break label49;
            }
            paramContext = localWifiConfiguration;
          }
        }
      }
    }
  }
  
  public static String tripQuot(String paramString)
  {
    String str;
    if (paramString == null) {
      str = "";
    }
    for (;;)
    {
      return str;
      str = paramString;
      if (paramString.length() >= 3) {
        str = paramString.replaceAll("^\"(.*)\"$", "$1");
      }
    }
  }
  
  private static boolean tryConnectWifi(Context paramContext, String paramString1, String paramString2)
  {
    WifiManager localWifiManager = getWifi(paramContext);
    paramContext = null;
    boolean bool2 = false;
    boolean bool1;
    if (localWifiManager == null)
    {
      bool1 = false;
      return bool1;
    }
    Iterator localIterator = localWifiManager.getConfiguredNetworks().iterator();
    label34:
    label44:
    int j;
    if (!localIterator.hasNext())
    {
      if (paramContext != null) {
        break label279;
      }
      j = 1;
      label51:
      if (j != 0) {
        paramContext = new WifiConfiguration();
      }
      paramContext.SSID = ("\"" + paramString1 + "\"");
      paramContext.preSharedKey = ("\"" + paramString2 + "\"");
      paramContext.hiddenSSID = true;
      paramContext.status = 2;
      paramContext.allowedGroupCiphers.set(2);
      paramContext.allowedGroupCiphers.set(3);
      paramContext.allowedKeyManagement.set(1);
      paramContext.allowedPairwiseCiphers.set(1);
      paramContext.allowedPairwiseCiphers.set(2);
      paramContext.allowedProtocols.set(0);
      paramContext.allowedProtocols.set(1);
      if (j == 0) {
        break label285;
      }
      j = localWifiManager.addNetwork(paramContext);
      AppDealWifi.LogMsg("wifi.addNetwork res = " + j);
    }
    for (;;)
    {
      bool1 = bool2;
      if (j > -1)
      {
        bool1 = bool2;
        if (localWifiManager.enableNetwork(j, true)) {
          bool1 = true;
        }
      }
      break;
      WifiConfiguration localWifiConfiguration = (WifiConfiguration)localIterator.next();
      if (!paramString1.equals(tripQuot(localWifiConfiguration.SSID))) {
        break label34;
      }
      paramContext = localWifiConfiguration;
      break label44;
      label279:
      j = 0;
      break label51;
      label285:
      j = localWifiManager.updateNetwork(paramContext);
      AppDealWifi.LogMsg("wifi.updateNetwork res = " + j);
    }
  }
  
  public static class mySSID
  {
    public String prePwd = "";
    public String ssid = "";
    
    public String toString()
    {
      return this.ssid;
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseOP\ConnSSID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */