package com.inventec.iMobile.baseOP;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.os.Bundle;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myWaitDlg;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.iMobile_AppGlobalVar;

public class MsgSender
{
  private static final String MsgSend = "com.inventec.iMobile.message.send";
  
  public static void BroadcastMsgSend(Activity paramActivity, int paramInt)
  {
    Bundle localBundle = new Bundle();
    localBundle.putInt("sendType", paramInt);
    myBroadcast.BroadcastMsg(paramActivity, "com.inventec.iMobile.message.send", localBundle);
  }
  
  public static void RegisterMsgSend(Activity paramActivity, BroadcastReceiver paramBroadcastReceiver)
  {
    myBroadcast.RegisterMsg(paramActivity, "com.inventec.iMobile.message.send", paramBroadcastReceiver);
  }
  
  public static boolean Send(byte[] paramArrayOfByte)
  {
    myWaitDlg.setWaitResult(0);
    boolean bool;
    int j;
    if (DealMsg.g_isDemo)
    {
      bool = true;
      j = 0;
      if (paramArrayOfByte[3] != 15) {
        break label93;
      }
      i = 6000;
      label26:
      myWaitDlg.m_WaitingTime = i;
      if (paramArrayOfByte[3] != 15) {
        break label100;
      }
    }
    label93:
    label100:
    for (int i = 4000;; i = 2000)
    {
      myWaitDlg.m_okTime = i;
      i = j;
      if (paramArrayOfByte[3] != 6) {
        i = 1;
      }
      BroadcastMsgSend(myActivity.GetCurForegroundActivity(), i);
      if (DealMsg.g_isDemo) {
        myWaitDlg.setWaitResult(1);
      }
      if (!bool) {
        myWaitDlg.setWaitResult(2);
      }
      return bool;
      bool = MsgReceiver.sendAddToArray(paramArrayOfByte);
      break;
      i = 7000;
      break label26;
    }
  }
  
  public static boolean SendError()
  {
    myWaitDlg.setWaitResult(0);
    myWaitDlg.m_WaitingTime = 6000;
    myWaitDlg.m_okTime = 4000;
    BroadcastMsgSend(myActivity.GetCurForegroundActivity(), 1);
    myWaitDlg.setWaitResult(2);
    return true;
  }
  
  public static boolean resendKMsg(byte[] paramArrayOfByte)
  {
    return iMobile_AppGlobalVar.resendKMsg(paramArrayOfByte);
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseOP\MsgSender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */