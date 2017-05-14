package com.inventec.iMobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.inventec.controls.myProgressBar;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myWaitDlg;
import com.inventec.iMobile.baseClass.myWaitDlg.BtnClickListener;
import com.inventec.iMobile.baseOP.AppDealWifi;
import com.inventec.iMobile.baseOP.CmdMake;
import com.inventec.iMobile.baseOP.ConnSSID;
import com.inventec.iMobile.baseOP.MsgReceiver;
import com.inventec.iMobile.baseStruct.DealMsg;

public class showversion
  extends myActivity
{
  static boolean hasFrame = false;
  final int HIDE_OPENING = 1;
  final int SHOW_PROGRESS = 2;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      }
      for (;;)
      {
        return;
        showversion.this.opening.setVisibility(4);
        continue;
        showversion.this.progress.setVisibility(0);
      }
    }
  };
  LinearLayout opening;
  myProgressBar progress;
  int testTimes = 0;
  myWaitDlg waitDlg = null;
  final int waitTimeOut = 55;
  
  private void checkVinSSIDPwd()
  {
    this.testTimes += 1;
    try
    {
      if ((iMobile_AppGlobalVar.isConn()) && (DealMsg.g_VINNum[0] != 0)) {
        startFrmMain();
      }
      for (;;)
      {
        return;
        if (this.testTimes <= 55) {
          break label121;
        }
        if (DealMsg.g_VINNum[0] == 0) {
          break;
        }
        if (this.waitDlg == null)
        {
          this.waitDlg = myWaitDlg.MyShowWaitDlg(this, 4000);
          this.waitDlg.setNoShowOk();
        }
        myWaitDlg localmyWaitDlg = this.waitDlg;
        myWaitDlg.BtnClickListener local4 = new com/inventec/iMobile/showversion$4;
        local4.<init>(this);
        localmyWaitDlg.SetBtnClickListener(local4);
        myWaitDlg.setWaitResult(2);
        this.waitDlg.resetMsgTimer(100);
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        AppDealWifi.LogExceptionMsg(localException);
        continue;
        startLoginSel();
        continue;
        label121:
        doCheckVinSSIDAfter(1000);
      }
    }
  }
  
  private void startFrmMain()
  {
    if (!iMobile_AppGlobalVar.isConn())
    {
      iMobile_AppGlobalVar.BackgroundReConn();
      if (!hasFrame) {
        break label35;
      }
      AppDealWifi.LogMsg("showversion : startFrmMain finish");
      finish();
    }
    for (;;)
    {
      return;
      MsgReceiver.sendAddToArray(CmdMake.KO_WF_EV_UPDATE_SP());
      break;
      label35:
      if (this.remoteDlg == null)
      {
        AppDealWifi.LogMsg("showversion : startFrmMain");
        hasFrame = true;
        DealMsg.g_isDemo = false;
        Intent localIntent = new Intent();
        localIntent.setClass(this, FrmMain.class);
        startActivity(localIntent);
        finish();
      }
    }
  }
  
  public boolean canShowErrorDlg()
  {
    return false;
  }
  
  void doCheckVinSSIDAfter(int paramInt)
  {
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        showversion.this.checkVinSSIDPwd();
      }
    }, paramInt);
  }
  
  void doConnWifi(myActivity parammyActivity, final String paramString1, final String paramString2)
  {
    this.waitDlg = myWaitDlg.MyShowWaitDlg(parammyActivity, 60000);
    this.waitDlg.setViewType(2, paramString1);
    this.waitDlg.SetBtnClickListener(new myWaitDlg.BtnClickListener()
    {
      public void doAction()
      {
        ConnSSID.cancelConnecting();
        showversion.this.waitDlg = null;
      }
    });
    this.waitDlg.setNoShowOk();
    myWaitDlg.setWaitResult(3);
    this.progress = this.waitDlg.getProgressBar();
    this.progress.setVisibility(0);
    this.progress.startAutoProgress(125);
    new Thread()
    {
      public void run()
      {
        try
        {
          if (ConnSSID.connectToMyWifi(showversion.this, paramString1, paramString2) == 0) {
            showversion.this.waitConnect();
          }
          for (;;)
          {
            return;
            showversion.this.testTimes = 52;
            showversion.this.doCheckVinSSIDAfter(1000);
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            AppDealWifi.LogExceptionMsg(localException);
          }
        }
      }
    }.start();
  }
  
  public void finishApp()
  {
    System.gc();
    AppDealWifi.closeFile();
    System.exit(0);
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
    setContentView(2130903105);
    statusBarHeight = 0;
    setEarth((ImageView)findViewById(2131296257));
    enlargeViewToFitScreen((ImageView)findViewById(2131296643), false);
    enlargeViewToFitScreen((TextView)findViewById(2131296264), true);
    paramBundle = (TextView)findViewById(2131296644);
    paramBundle.setText("Ver." + paramBundle.getText());
    enlargeViewToFitScreen(paramBundle, true);
    this.progress = ((myProgressBar)findViewById(2131296624));
    enlargeViewToFitScreen(this.progress, false);
    enlargeViewToFitScreen((ImageView)findViewById(2131296646), false);
    startCheckingThread();
    this.opening = ((LinearLayout)findViewById(2131296645));
  }
  
  void startCheckingThread()
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          Thread.sleep(2000L);
          showversion.this.handler.sendEmptyMessage(1);
          Object localObject1 = showversion.this;
          if (DealMsg.g_VINNum[0] != 0)
          {
            String str1 = DealMsg.getSSID();
            String str2 = DealMsg.getSSIDPwd();
            Object localObject2 = new java/lang/StringBuilder;
            ((StringBuilder)localObject2).<init>(" -------VIN:");
            AppDealWifi.LogMsg(DealMsg.g_VINNum.toString());
            localObject2 = new java/lang/StringBuilder;
            ((StringBuilder)localObject2).<init>(" -------Get Saved SSID:");
            AppDealWifi.LogMsg(str1 + "\n ------- PassWord:" + str2);
            if (ConnSSID.checkCurConnOk((Context)localObject1, str1))
            {
              AppDealWifi.LogMsg(" ------- use current wifi ------- ");
              showversion.this.handler.sendEmptyMessage(2);
              showversion.this.waitConnect();
            }
            for (;;)
            {
              return;
              AppDealWifi.LogMsg(" ------- reset wifi ------- ");
              localObject2 = showversion.this.handler;
              localObject1 = new com/inventec/iMobile/showversion$2$1;
              ((1)localObject1).<init>(this, str1, str2);
              ((Handler)localObject2).postDelayed((Runnable)localObject1, 2000L);
            }
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            AppDealWifi.LogExceptionMsg(localException);
            continue;
            showversion.this.testTimes = 55;
            showversion.this.doCheckVinSSIDAfter(3000);
          }
        }
      }
    }.start();
  }
  
  void startLoginSel()
  {
    if (hasFrame)
    {
      AppDealWifi.LogMsg("showversion : Login_Sel finish");
      finish();
    }
    for (;;)
    {
      return;
      if (this.remoteDlg == null)
      {
        AppDealWifi.LogMsg("showversion : Login_Sel");
        hasFrame = true;
        Intent localIntent = new Intent();
        localIntent.setClass(this, Login_Sel.class);
        startActivity(localIntent);
        finish();
      }
    }
  }
  
  void waitConnect()
  {
    this.progress.startAutoProgress(65);
    int i = 60;
    for (;;)
    {
      if ((iMobile_AppGlobalVar.isConn()) || (i <= 0))
      {
        this.testTimes = 55;
        this.progress.setLeftSecond(5);
        doCheckVinSSIDAfter(5000);
        return;
      }
      i--;
      try
      {
        Thread.sleep(1000L);
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\showversion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */