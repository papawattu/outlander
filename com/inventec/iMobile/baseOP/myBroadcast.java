package com.inventec.iMobile.baseOP;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class myBroadcast
{
  public static void BroadcastMsg(Activity paramActivity, String paramString, Bundle paramBundle)
  {
    if (paramActivity == null) {}
    for (;;)
    {
      return;
      Intent localIntent = new Intent();
      if (paramBundle != null) {
        localIntent.putExtras(paramBundle);
      }
      localIntent.setAction(paramString);
      paramActivity.sendBroadcast(localIntent);
    }
  }
  
  public static void RegisterMsg(Activity paramActivity, String paramString, BroadcastReceiver paramBroadcastReceiver)
  {
    if ((paramActivity == null) || (paramBroadcastReceiver == null)) {}
    for (;;)
    {
      return;
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction(paramString);
      paramActivity.registerReceiver(paramBroadcastReceiver, localIntentFilter);
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseOP\myBroadcast.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */