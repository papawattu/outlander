package com.inventec.iMobile;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myAlertDlg;
import com.inventec.iMobile.baseClass.myAlertDlg.CloseCallback;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseClass.myWaitDlg;
import com.inventec.iMobile.baseClass.myWaitDlg.BtnClickListener;
import com.inventec.iMobile.baseOP.AppDealWifi;
import com.inventec.iMobile.baseOP.ConnSSID;
import com.inventec.iMobile.baseOP.MsgReceiver;
import com.inventec.iMobile.baseStruct.DealMsg;
import java.util.ArrayList;

public class FrmMain
  extends myActivity
{
  private Button btnControl;
  private Button btnHome;
  private MyButton btnRegForHome;
  private Button btnSetting;
  private Button btnStatus;
  private Button btnTimer;
  private MyButton btnUpdateForHome;
  private Drawable homeIcon;
  private Drawable homeIconSel;
  MvControl mvControl;
  MvHome mvHome;
  MvSetting mvSetting;
  ArrayList<myMainView> mvSettingList;
  MvStatus mvStatus;
  MvTimer mvTimer;
  private Drawable operationIcon;
  private Drawable operationIconSel;
  private RelativeLayout rl_titlebarForHome;
  private RelativeLayout rl_titlebarForOther;
  private Drawable settingIcon;
  private Drawable settingIconSel;
  private Drawable statusIcon;
  private Drawable statusIconSel;
  int taboldnum = 1;
  private Drawable timerIcon;
  private Drawable timerIconSel;
  private LinearLayout tl_btnbar;
  
  private void StopMvHomeAnim()
  {
    if (this.mvHome != null) {
      this.mvHome.StopAnim();
    }
  }
  
  private void setBtnEvent()
  {
    this.btnHome.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (FrmMain.this.changepictrue(1))
        {
          if (!DealMsg.g_isDemo) {
            break label25;
          }
          FrmMain.this.setMvHome();
        }
        for (;;)
        {
          return;
          label25:
          FrmMain.this.DoUpdate();
          FrmMain.this.sendHandlerMsg(4, 2000);
        }
      }
    });
    this.btnTimer.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (FrmMain.this.changepictrue(2)) {
          FrmMain.this.setMvTimer();
        }
      }
    });
    this.btnStatus.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (FrmMain.this.changepictrue(3)) {
          FrmMain.this.setMvStatus();
        }
      }
    });
    this.btnControl.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (FrmMain.this.changepictrue(4)) {
          FrmMain.this.setMvControl();
        }
      }
    });
    this.btnSetting.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (FrmMain.this.changepictrue(5)) {
          FrmMain.this.setMvSetting();
        }
      }
    });
  }
  
  private void setMvControl()
  {
    setTitleBarForOther();
    StopMvHomeAnim();
    if (this.mvControl == null) {
      this.mvControl = new MvControl(this);
    }
    SetMainView(this.mvControl);
    this.mvControl.Refresh();
  }
  
  private void setMvHome()
  {
    setTitleBarForHome();
    if (this.mvHome == null) {
      this.mvHome = new MvHome(this);
    }
    SetMainView(this.mvHome);
    if (DealMsg.g_isDemo) {
      this.mvHome.Refresh();
    }
    this.mvHome.StartAnim();
  }
  
  private void setMvSetting()
  {
    setTitleBarForOther();
    StopMvHomeAnim();
    if (this.mvSettingList == null) {
      this.mvSettingList = new ArrayList();
    }
    if (this.mvSetting == null) {
      this.mvSetting = new MvSetting(this);
    }
    SetMainView(this.mvSetting);
    this.mvSetting.Refresh();
  }
  
  private void setMvStatus()
  {
    setTitleBarForOther();
    StopMvHomeAnim();
    if (this.mvStatus == null) {
      this.mvStatus = new MvStatus(this);
    }
    SetMainView(this.mvStatus);
    this.mvStatus.Refresh();
  }
  
  private void setMvTimer()
  {
    setTitleBarForOther();
    hideUpdateBtn();
    StopMvHomeAnim();
    if (this.mvTimer == null) {
      this.mvTimer = new MvTimer(this);
    }
    SetMainView(this.mvTimer);
    this.mvTimer.Refresh();
  }
  
  private void setTitleBarForHome()
  {
    SetTitleBar(this.rl_titlebarForHome);
    this.rl_titlebar = this.rl_titlebarForHome;
  }
  
  private void setTitleBarForOther()
  {
    SetTitleBar(this.rl_titlebarForOther);
    this.rl_titlebar = this.rl_titlebarForOther;
  }
  
  void DoFinish()
  {
    ConnSSID.UnlockWifi(this);
    if (DealMsg.g_isDemo)
    {
      finish();
      System.gc();
      AppDealWifi.closeFile();
      System.exit(0);
    }
    for (;;)
    {
      return;
      final String str = iMobile_AppGlobalVar.RevertToLastWifi(this);
      if (str != null)
      {
        myWaitDlg localmyWaitDlg = myWaitDlg.MyShowWaitDlg(this, 7000);
        myWaitDlg.setWaitResult(3);
        localmyWaitDlg.setViewType(2, str);
        localmyWaitDlg.SetBtnClickListener(new myWaitDlg.BtnClickListener()
        {
          public void doAction()
          {
            FrmMain.this.finish();
          }
        });
        localmyWaitDlg.setOnCancelListener(new DialogInterface.OnCancelListener()
        {
          public void onCancel(DialogInterface paramAnonymousDialogInterface)
          {
            FrmMain.this.finish();
          }
        });
        new Thread()
        {
          public void run()
          {
            int i = 70;
            for (;;)
            {
              if (i <= 0)
              {
                label7:
                AppDealWifi.LogMsg("revert succeed");
                FrmMain.this.finish();
                label19:
                return;
              }
              try
              {
                if (str.equals(ConnSSID.getCurConnSSID(FrmMain.this))) {
                  break label7;
                }
                sleep(100L);
                i--;
              }
              catch (Exception localException)
              {
                AppDealWifi.LogExceptionMsg(localException);
                AppDealWifi.LogMsg("revert succeed");
                FrmMain.this.finish();
                break label19;
              }
              finally
              {
                AppDealWifi.LogMsg("revert succeed");
                FrmMain.this.finish();
              }
            }
          }
        }.start();
      }
      else
      {
        ConnSSID.stopCurSSID(this);
        finish();
      }
    }
  }
  
  public void SetTitleText(String paramString)
  {
    if (this.rl_titlebar != null) {
      ((TextView)this.rl_titlebar.findViewById(2131296546)).setText(paramString);
    }
  }
  
  public void SetTitleUpdateTimeText(String paramString)
  {
    AutoResizeTextView localAutoResizeTextView;
    if (this.rl_titlebarForHome != null)
    {
      localAutoResizeTextView = (AutoResizeTextView)this.rl_titlebarForHome.findViewById(2131296614);
      if (localAutoResizeTextView != null)
      {
        localAutoResizeTextView.setText(paramString);
        if (com.inventec.iMobile.baseStruct.DefMsg.g_recdatas['Â'] != 0) {
          break label60;
        }
      }
    }
    label60:
    for (int i = -1;; i = -65536)
    {
      localAutoResizeTextView.setColors(i, 0);
      SetTitleText(getString(2131165304));
      return;
    }
  }
  
  void ShowRegOk()
  {
    myAlertDlg localmyAlertDlg = new myAlertDlg(this, 2130903092);
    localmyAlertDlg.setDlgContent(2131165473, 0, true);
    localmyAlertDlg.setOkCallback(new myAlertDlg.CloseCallback()
    {
      public void DoAction()
      {
        if (FrmMain.this.mCurMainView != null)
        {
          FrmMain.this.showDemoBar();
          FrmMain.this.mCurMainView.Update();
          MsgReceiver.dealECUVer();
        }
      }
    });
    localmyAlertDlg.show();
  }
  
  public boolean changepictrue(int paramInt)
  {
    boolean bool = true;
    if (this.taboldnum != paramInt) {
      if (this.taboldnum == 1)
      {
        this.btnHome.setCompoundDrawables(null, this.homeIcon, null, null);
        if (paramInt != 1) {
          break label158;
        }
        this.btnHome.setCompoundDrawables(null, this.homeIconSel, null, null);
        label51:
        this.taboldnum = paramInt;
      }
    }
    for (;;)
    {
      return bool;
      if (this.taboldnum == 2)
      {
        this.btnTimer.setCompoundDrawables(null, this.timerIcon, null, null);
        break;
      }
      if (this.taboldnum == 3)
      {
        this.btnStatus.setCompoundDrawables(null, this.statusIcon, null, null);
        break;
      }
      if (this.taboldnum == 4)
      {
        this.btnControl.setCompoundDrawables(null, this.operationIcon, null, null);
        break;
      }
      if (this.taboldnum != 5) {
        break;
      }
      this.btnSetting.setCompoundDrawables(null, this.settingIcon, null, null);
      break;
      label158:
      if (paramInt == 2)
      {
        this.btnTimer.setCompoundDrawables(null, this.timerIconSel, null, null);
        break label51;
      }
      if (paramInt == 3)
      {
        this.btnStatus.setCompoundDrawables(null, this.statusIconSel, null, null);
        break label51;
      }
      if (paramInt == 4)
      {
        this.btnControl.setCompoundDrawables(null, this.operationIconSel, null, null);
        break label51;
      }
      if (paramInt != 5) {
        break label51;
      }
      this.btnSetting.setCompoundDrawables(null, this.settingIconSel, null, null);
      break label51;
      bool = false;
    }
  }
  
  public void doHandlerAct(int paramInt)
  {
    if (paramInt == 4) {
      setMvHome();
    }
    for (;;)
    {
      return;
      super.doHandlerAct(paramInt);
    }
  }
  
  void enlargeBtnTxt(int paramInt)
  {
    enlargeViewToFitScreen((TextView)this.tl_btnbar.findViewById(paramInt), true);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == 101) {
      DoFinish();
    }
    for (;;)
    {
      return;
      if (paramInt2 == 102) {
        showLoginScreen();
      }
    }
  }
  
  public void onBackPressed()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Quit?");
    localBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
        FrmMain.this.DoFinish();
      }
    });
    localBuilder.setNegativeButton("No", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localBuilder.setCancelable(false);
    localBuilder.create().show();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    MsgReceiver.closeRegistration();
    this.rl_titlebarForOther = this.rl_titlebar;
    paramBundle = LayoutInflater.from(this);
    this.rl_titlebarForHome = ((RelativeLayout)paramBundle.inflate(2130903090, null));
    AutoResizeTextView localAutoResizeTextView = (AutoResizeTextView)this.rl_titlebarForHome.findViewById(2131296546);
    localAutoResizeTextView.setSingle();
    enlargeViewToFitScreen(localAutoResizeTextView, true);
    enlargeViewToFitScreen((TextView)this.rl_titlebarForHome.findViewById(2131296610), true);
    this.btnUpdateForHome = ((MyButton)this.rl_titlebarForHome.findViewById(2131296609));
    enlargeViewToFitScreen(this.btnUpdateForHome, false);
    this.btnRegForHome = ((MyButton)this.rl_titlebarForHome.findViewById(2131296612));
    enlargeViewToFitScreen(this.btnRegForHome, true);
    this.btnRegForHome.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FrmMain.this.showLoginScreen();
      }
    });
    localAutoResizeTextView = (AutoResizeTextView)this.rl_titlebarForHome.findViewById(2131296614);
    enlargeViewToFitScreen(localAutoResizeTextView, true);
    localAutoResizeTextView.setSingle();
    this.btnUpdateForHome.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FrmMain.this.DoUpdate();
      }
    });
    this.tl_btnbar = ((LinearLayout)paramBundle.inflate(2130903086, null));
    SetBtnBar(this.tl_btnbar, 45);
    this.btnHome = ((Button)this.tl_btnbar.findViewById(2131296596));
    this.btnTimer = ((Button)this.tl_btnbar.findViewById(2131296598));
    this.btnStatus = ((Button)this.tl_btnbar.findViewById(2131296600));
    this.btnControl = ((Button)this.tl_btnbar.findViewById(2131296602));
    this.btnSetting = ((Button)this.tl_btnbar.findViewById(2131296604));
    setBtnEvent();
    if (DealMsg.g_isDemo)
    {
      com.inventec.iMobile.baseStruct.DefMsg.g_recdatas[99] = 75;
      com.inventec.iMobile.baseStruct.DefMsg.g_recdatas['³'] = 1;
      com.inventec.iMobile.baseStruct.DefMsg.g_recdatas[''] = 1;
      com.inventec.iMobile.baseStruct.DefMsg.g_recdatas[''] = 1;
      com.inventec.iMobile.baseStruct.DefMsg.g_recdatas[''] = 1;
      com.inventec.iMobile.baseStruct.DefMsg.g_recdatas[''] = 1;
      com.inventec.iMobile.baseStruct.DefMsg.g_recdatas[''] = 1;
    }
    setMvHome();
    resetTableButton();
    if (iMobile_AppGlobalVar.showRegOK)
    {
      iMobile_AppGlobalVar.showRegOK = false;
      if (MsgReceiver.noVINbeforREG)
      {
        MsgReceiver.noVINbeforREG = false;
        ShowRegOk();
      }
    }
    dealShowErrDlg();
    iMobile_AppGlobalVar.canShowUpgrade = true;
    if (!DealMsg.g_isDemo) {
      ConnSSID.LockWifi(this);
    }
  }
  
  protected void onDestroy()
  {
    AppDealWifi.LogMsg("FrmMain is destroied");
    if (DealMsg.g_isDemo) {
      super.onDestroy();
    }
    for (;;)
    {
      return;
      try
      {
        ConnSSID.UnlockWifi(this);
        if (iMobile_AppGlobalVar.RevertToLastWifi(this) != null) {
          Thread.sleep(1500L);
        }
        super.onDestroy();
        System.gc();
        AppDealWifi.closeFile();
        System.exit(0);
      }
      catch (InterruptedException localInterruptedException)
      {
        localInterruptedException.printStackTrace();
        super.onDestroy();
        System.gc();
        AppDealWifi.closeFile();
        System.exit(0);
      }
      finally
      {
        super.onDestroy();
        System.gc();
        AppDealWifi.closeFile();
        System.exit(0);
      }
    }
  }
  
  protected void onResume()
  {
    super.onResume();
    myActivity.backTop = false;
    if (iMobile_AppGlobalVar.needUpgrade)
    {
      iMobile_AppGlobalVar.needUpgrade = false;
      MsgReceiver.BroadcastMsgAction(32, 0);
    }
  }
  
  void resetTableButton()
  {
    if (this.btnUpdateForHome != null) {
      this.btnUpdateForHome.chengeBg(2130837571, -1, 2130837569, -12303292);
    }
    if (this.btnRegForHome != null) {
      this.btnRegForHome.chengeBg(2130837565, -1, 2130837566, -12303292);
    }
    int j = getBtnBarHeight();
    int i = (int)(j / 90.0F * 116.0F);
    if (iMobile_AppGlobalVar.scaledDensity >= 2.0D)
    {
      this.homeIcon = this.btnHome.getContext().getResources().getDrawable(2130837578);
      this.homeIcon.setBounds(0, 0, i, j);
      this.timerIcon = this.btnTimer.getContext().getResources().getDrawable(2130837590);
      this.timerIcon.setBounds(0, 0, i, j);
      this.statusIcon = this.btnStatus.getContext().getResources().getDrawable(2130837586);
      this.statusIcon.setBounds(0, 0, i, j);
      this.operationIcon = this.btnControl.getContext().getResources().getDrawable(2130837574);
      this.operationIcon.setBounds(0, 0, i, j);
      this.settingIcon = this.btnSetting.getContext().getResources().getDrawable(2130837582);
      this.settingIcon.setBounds(0, 0, i, j);
      this.homeIconSel = this.btnHome.getContext().getResources().getDrawable(2130837580);
      this.homeIconSel.setBounds(0, 0, i, j);
      this.timerIconSel = this.btnTimer.getContext().getResources().getDrawable(2130837592);
      this.timerIconSel.setBounds(0, 0, i, j);
      this.statusIconSel = this.btnStatus.getContext().getResources().getDrawable(2130837588);
      this.statusIconSel.setBounds(0, 0, i, j);
      this.operationIconSel = this.btnControl.getContext().getResources().getDrawable(2130837576);
      this.operationIconSel.setBounds(0, 0, i, j);
      this.settingIconSel = this.btnSetting.getContext().getResources().getDrawable(2130837584);
      this.settingIconSel.setBounds(0, 0, i, j);
      enlargeBtnTxt(2131296597);
      enlargeBtnTxt(2131296599);
      enlargeBtnTxt(2131296601);
      enlargeBtnTxt(2131296603);
      enlargeBtnTxt(2131296605);
    }
    for (;;)
    {
      this.btnHome.setCompoundDrawables(null, this.homeIconSel, null, null);
      this.btnTimer.setCompoundDrawables(null, this.timerIcon, null, null);
      this.btnStatus.setCompoundDrawables(null, this.statusIcon, null, null);
      this.btnControl.setCompoundDrawables(null, this.operationIcon, null, null);
      this.btnSetting.setCompoundDrawables(null, this.settingIcon, null, null);
      return;
      this.homeIcon = this.btnHome.getContext().getResources().getDrawable(2130837577);
      this.homeIcon.setBounds(0, 0, i, j);
      this.timerIcon = this.btnTimer.getContext().getResources().getDrawable(2130837589);
      this.timerIcon.setBounds(0, 0, i, j);
      this.statusIcon = this.btnStatus.getContext().getResources().getDrawable(2130837585);
      this.statusIcon.setBounds(0, 0, i, j);
      this.operationIcon = this.btnControl.getContext().getResources().getDrawable(2130837573);
      this.operationIcon.setBounds(0, 0, i, j);
      this.settingIcon = this.btnSetting.getContext().getResources().getDrawable(2130837581);
      this.settingIcon.setBounds(0, 0, i, j);
      this.homeIconSel = this.btnHome.getContext().getResources().getDrawable(2130837579);
      this.homeIconSel.setBounds(0, 0, i, j);
      this.timerIconSel = this.btnTimer.getContext().getResources().getDrawable(2130837591);
      this.timerIconSel.setBounds(0, 0, i, j);
      this.statusIconSel = this.btnStatus.getContext().getResources().getDrawable(2130837587);
      this.statusIconSel.setBounds(0, 0, i, j);
      this.operationIconSel = this.btnControl.getContext().getResources().getDrawable(2130837575);
      this.operationIconSel.setBounds(0, 0, i, j);
      this.settingIconSel = this.btnSetting.getContext().getResources().getDrawable(2130837583);
      this.settingIconSel.setBounds(0, 0, i, j);
    }
  }
  
  protected void showLoginScreen()
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, Login_SSID.class);
    startActivity(localIntent);
    finish();
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\FrmMain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */