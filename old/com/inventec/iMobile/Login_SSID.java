package com.inventec.iMobile;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings.System;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;
import com.inventec.controls.myProgressBar;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myWaitDlg;
import com.inventec.iMobile.baseOP.AppDealWifi;
import com.inventec.iMobile.baseOP.CmdMake;
import com.inventec.iMobile.baseOP.ConnSSID;
import com.inventec.iMobile.baseOP.ConnSSID.mySSID;
import com.inventec.iMobile.baseOP.MsgReceiver;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.popup.ActionItem;
import com.inventec.iMobile.popup.QuickAction;
import com.inventec.iMobile.popup.QuickAction.OnActionItemClickListener;
import java.util.ArrayList;

public class Login_SSID
  extends myActivity
{
  final int TIMEOUT_SOCKET = 60;
  final int TIME_COMM_INIT = 5000;
  boolean hasFrame = false;
  boolean isVirtualMachine = false;
  ConnSSID.mySSID mCurSel = null;
  private Handler mHandler = new Handler();
  private String m_CurSSID = "";
  private String m_CurSSIDPwd = "";
  private Spinner m_SpinnerSel;
  private myWaitDlg m_WaitDlg = null;
  private EditText m_etPwd;
  private EditText m_etssid;
  ArrayList<ConnSSID.mySSID> mlist = new ArrayList();
  WebView myWebview;
  QuickAction quickAction;
  int testTimes = 0;
  final int timeout = 125000;
  int visiblePassword = -1;
  final int waitTimeOut = 55;
  boolean webviewLoaded = false;
  private Runnable wifiConnTimedout = new Runnable()
  {
    public void run() {}
  };
  final int wifiConnTimeout = 60000;
  
  private void checkVinSSIDPwd()
  {
    try
    {
      if (iMobile_AppGlobalVar.isConn())
      {
        if (DealMsg.g_VINNum[0] != 0) {
          startFrmMain();
        }
        for (;;)
        {
          return;
          AppDealWifi.LogMsg("socket seems not to be connected, show error");
          iMobile_AppGlobalVar.finishWifi();
          ConnSSID.stopCurSSID(this);
          if (this.m_WaitDlg != null)
          {
            this.m_WaitDlg.dismiss();
            this.m_WaitDlg = null;
          }
          PopupErrorDlg(5);
        }
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        showError();
        AppDealWifi.LogExceptionMsg(localException);
        continue;
        showError();
      }
    }
  }
  
  private void doConnWifi()
  {
    this.m_CurSSID = this.m_etssid.getText().toString();
    this.m_CurSSIDPwd = this.m_etPwd.getText().toString();
    this.m_WaitDlg = myWaitDlg.MyShowWaitDlg(this, 125000);
    myWaitDlg.setWaitResult(2);
    this.m_WaitDlg.setViewType(2, this.m_CurSSID);
    this.m_WaitDlg.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        
        if (DealMsg.g_VINNum[0] != 0) {
          Login_SSID.this.startFrmMain();
        }
        for (;;)
        {
          return;
          if (Login_SSID.this.m_WaitDlg != null)
          {
            Login_SSID.this.m_WaitDlg.dismiss();
            Login_SSID.this.m_WaitDlg = null;
          }
        }
      }
    });
    this.m_WaitDlg.setNoShowOk();
    startCheckingThread();
  }
  
  private void fillSSIDList()
  {
    Object localObject = new ConnSSID.mySSID();
    ((ConnSSID.mySSID)localObject).ssid = this.m_CurSSID;
    ((ConnSSID.mySSID)localObject).prePwd = this.m_CurSSIDPwd;
    int j = ConnSSID.genSSIDList(this, this.mlist, (ConnSSID.mySSID)localObject);
    this.m_CurSSID = ((ConnSSID.mySSID)localObject).ssid;
    this.m_CurSSIDPwd = ((ConnSSID.mySSID)localObject).prePwd;
    this.quickAction = new QuickAction(this);
    this.quickAction.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener()
    {
      public void onItemClick(int paramAnonymousInt)
      {
        Login_SSID.this.mCurSel = ((ConnSSID.mySSID)Login_SSID.this.mlist.get(paramAnonymousInt));
        Login_SSID.this.m_etssid.setText(Login_SSID.this.mCurSel.ssid);
        Login_SSID.this.m_etPwd.setText(Login_SSID.this.mCurSel.prePwd);
        Login_SSID.this.m_etPwd.requestFocus();
      }
    });
    this.quickAction.setOnDismissListener(new PopupWindow.OnDismissListener()
    {
      public void onDismiss()
      {
        ((RelativeLayout)Login_SSID.this.findViewById(2131296457)).setBackgroundResource(2130837630);
        ((ImageView)Login_SSID.this.findViewById(2131296461)).setBackgroundResource(2130837632);
      }
    });
    localObject = new ActionItem();
    for (int i = 0;; i++)
    {
      if (i >= this.mlist.size())
      {
        if (j != -1)
        {
          this.mCurSel = ((ConnSSID.mySSID)this.mlist.get(j));
          this.m_etssid.setText(this.mCurSel.ssid);
          this.m_etPwd.setText(this.mCurSel.prePwd);
        }
        return;
      }
      ((ActionItem)localObject).setTitle(((ConnSSID.mySSID)this.mlist.get(i)).ssid);
      ((ActionItem)localObject).setIcon(null);
      this.quickAction.addActionItem((ActionItem)localObject);
    }
  }
  
  private void startCheckingThread()
  {
    new Thread()
    {
      public void run()
      {
        localmyProgressBar = null;
        if (Login_SSID.this.m_WaitDlg != null) {
          localmyProgressBar = Login_SSID.this.m_WaitDlg.getProgressBar();
        }
        for (;;)
        {
          try
          {
            if (!Login_SSID.this.isVirtualMachine)
            {
              if (localmyProgressBar != null)
              {
                localmyProgressBar.setVisibility(0);
                localmyProgressBar.startAutoProgress(125);
              }
              Login_SSID.this.mHandler.postDelayed(Login_SSID.this.wifiConnTimedout, 60000L);
              i = ConnSSID.connectToMyWifi(Login_SSID.this, Login_SSID.this.m_CurSSID, Login_SSID.this.m_CurSSIDPwd);
              Login_SSID.this.mHandler.removeCallbacks(Login_SSID.this.wifiConnTimedout);
              if (i != 0) {
                continue;
              }
              Login_SSID.this.testTimes = 0;
              Object localObject2 = Login_SSID.this.mHandler;
              Object localObject1 = new com/inventec/iMobile/Login_SSID$5$1;
              ((1)localObject1).<init>(this);
              ((Handler)localObject2).post((Runnable)localObject1);
              int j = 60;
              i = j;
              if (localmyProgressBar != null)
              {
                localmyProgressBar.setLeftSecond(65);
                i = j;
              }
              if ((!iMobile_AppGlobalVar.isConn()) && (i > 0)) {
                continue;
              }
              if (!iMobile_AppGlobalVar.isConn()) {
                continue;
              }
              AppDealWifi.LogMsg("Socket connect succeed, check registration result after 5s");
              if (localmyProgressBar != null) {
                localmyProgressBar.setLeftSecond(5);
              }
              localObject1 = Login_SSID.this.mHandler;
              localObject2 = new com/inventec/iMobile/Login_SSID$5$2;
              ((2)localObject2).<init>(this);
              ((Handler)localObject1).postDelayed((Runnable)localObject2, 5000L);
              return;
            }
          }
          catch (Exception localException)
          {
            int i;
            if (localmyProgressBar == null) {
              continue;
            }
            localmyProgressBar.stopAutoProgress();
            AppDealWifi.LogExceptionMsg(localException);
            iMobile_AppGlobalVar.finishWifi();
            ConnSSID.stopCurSSID(Login_SSID.this);
            continue;
            if (localmyProgressBar == null) {
              continue;
            }
            localmyProgressBar.stopAutoProgress();
            AppDealWifi.LogMsg("10s timeout for connecting socekt, show error");
            Login_SSID.this.showError();
            continue;
            if (localmyProgressBar == null) {
              continue;
            }
            localmyProgressBar.stopAutoProgress();
            AppDealWifi.LogMsg("connecting wifi failed, show error");
            Login_SSID.this.showError();
            continue;
          }
          i = 0;
          continue;
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
    }.start();
  }
  
  private void startFrmMain()
  {
    if (!iMobile_AppGlobalVar.isConn())
    {
      iMobile_AppGlobalVar.BackgroundReConn();
      if (!this.hasFrame) {
        break label37;
      }
      AppDealWifi.LogMsg("Login_SSID : startFrmMain finish");
      finish();
    }
    for (;;)
    {
      return;
      MsgReceiver.sendAddToArray(CmdMake.KO_WF_EV_UPDATE_SP());
      break;
      label37:
      if (this.remoteDlg == null)
      {
        this.hasFrame = true;
        AppDealWifi.LogMsg("Login_SSID : startFrmMain");
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
  
  public void finishApp()
  {
    System.gc();
    AppDealWifi.closeFile();
    System.exit(0);
  }
  
  public void onBackPressed()
  {
    if (this.hasFrame) {}
    for (;;)
    {
      return;
      this.hasFrame = true;
      Intent localIntent = new Intent();
      localIntent.setClass(this, Login_Sel.class);
      startActivity(localIntent);
      finish();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Object localObject = getLayoutInflater().inflate(2130903071, null);
    setWebView((View)localObject, 2131296266, 2131165481);
    try
    {
      Thread.sleep(100L);
      setContentView((View)localObject);
      statusBarHeight = 0;
      setEarth((ImageView)findViewById(2131296257));
      enlargeViewToFitScreen((TextView)findViewById(2131296264), true);
      enlargeViewToFitScreen((RelativeLayout)findViewById(2131296457), false);
      enlargeViewToFitScreen((RelativeLayout)findViewById(2131296462), false);
      paramBundle = (MyButton)findViewById(2131296262);
      enlargeViewToFitScreen(paramBundle, true);
      this.m_SpinnerSel = ((Spinner)findViewById(2131296460));
      enlargeViewToFitScreen(this.m_SpinnerSel, false);
      this.m_etssid = ((EditText)findViewById(2131296459));
      enlargeViewToFitScreen(this.m_etssid, true);
      this.m_etPwd = ((EditText)findViewById(2131296464));
      enlargeViewToFitScreen(this.m_etPwd, true);
      this.m_CurSSID = DealMsg.getSSID();
      this.m_CurSSIDPwd = DealMsg.getSSIDPwd();
      localObject = (ImageView)findViewById(2131296461);
      enlargeViewToFitScreen((View)localObject, false);
      ((ImageView)localObject).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Login_SSID.this.fillSSIDList();
          ConnSSID.scanAccessPoint(Login_SSID.this);
          ((RelativeLayout)Login_SSID.this.findViewById(2131296457)).setBackgroundResource(2130837631);
          ((ImageView)Login_SSID.this.findViewById(2131296461)).setBackgroundResource(2130837633);
          if (Login_SSID.this.quickAction != null)
          {
            if (Login_SSID.this.m_etssid == null) {
              break label86;
            }
            Login_SSID.this.quickAction.show(Login_SSID.this.m_etssid);
          }
          for (;;)
          {
            return;
            label86:
            Login_SSID.this.quickAction.show(paramAnonymousView);
          }
        }
      });
      localObject = (AutoResizeTextView)findViewById(2131296458);
      ((AutoResizeTextView)localObject).setHVType(2, 1);
      ((AutoResizeTextView)localObject).setColors(MyButton.colNor, -1);
      enlargeViewToFitScreen((View)localObject, true);
      localObject = (AutoResizeTextView)findViewById(2131296463);
      ((AutoResizeTextView)localObject).setHVType(2, 1);
      ((AutoResizeTextView)localObject).setColors(MyButton.colNor, -1);
      enlargeViewToFitScreen((View)localObject, true);
      ConnSSID.stopCurSSID(this);
      paramBundle.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Login_SSID.this.doConnWifi();
        }
      });
      if ((this.mCurSel != null) && (DealMsg.getSSID().equals(this.mCurSel.ssid)))
      {
        this.m_etssid.setText(this.mCurSel.ssid);
        if ((this.mCurSel.prePwd != null) && (this.mCurSel.prePwd.length() > 0)) {
          this.m_etPwd.setText(this.mCurSel.prePwd);
        }
      }
      else
      {
        MsgReceiver.openRegistration();
        return;
      }
    }
    catch (InterruptedException paramBundle)
    {
      for (;;)
      {
        paramBundle.printStackTrace();
        continue;
        this.m_etPwd.setText(DealMsg.getSSIDPwd());
      }
    }
  }
  
  protected void onPause()
  {
    if (this.visiblePassword == 0) {
      Settings.System.putInt(getContentResolver(), "show_password", 0);
    }
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.visiblePassword = Settings.System.getInt(getContentResolver(), "show_password", 1);
    if (this.visiblePassword == 0) {
      Settings.System.putInt(getContentResolver(), "show_password", 1);
    }
  }
  
  void setWebView(View paramView, int paramInt1, int paramInt2)
  {
    paramView = (WebView)paramView.findViewById(paramInt1);
    if (paramView == null) {}
    for (;;)
    {
      return;
      iMobile_AppGlobalVar.setWebContent(paramView, getString(paramInt2), 0);
    }
  }
  
  void showError()
  {
    iMobile_AppGlobalVar.finishWifi();
    ConnSSID.stopCurSSID(this);
    if (this.m_WaitDlg != null) {
      this.m_WaitDlg.resetMsgTimer(100);
    }
  }
  
  public boolean showLogin()
  {
    return true;
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\Login_SSID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */