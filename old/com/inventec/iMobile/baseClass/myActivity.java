package com.inventec.iMobile.baseClass;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.Login_SSID;
import com.inventec.iMobile.Login_Sel;
import com.inventec.iMobile.UpgradeDlg;
import com.inventec.iMobile.UpgradeSelectDlg;
import com.inventec.iMobile.baseOP.AppDealWifi;
import com.inventec.iMobile.baseOP.CmdMake;
import com.inventec.iMobile.baseOP.ConnSSID;
import com.inventec.iMobile.baseOP.MsgReceiver;
import com.inventec.iMobile.baseOP.MsgSender;
import com.inventec.iMobile.baseOP.MsgUpdateTran;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.baseStruct.DefMsg;
import com.inventec.iMobile.iMobile_AppGlobalVar;
import java.util.ArrayList;

public class myActivity
  extends myActivityBase
{
  public static boolean backTop = false;
  static ArrayList<Integer> erridlist;
  protected static int lastConSts = 0;
  public static int statusBarHeight = 0;
  static ArrayList<Integer> thefterridlist;
  TextView barmask;
  BroadcastReceiver batteryReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      int j = paramAnonymousIntent.getIntExtra("level", 0);
      int i = paramAnonymousIntent.getIntExtra("status", 1);
      myActivity.this.doTestBatteryLevel(j, i);
    }
  };
  BroadcastReceiver br_recv = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      int i = 0;
      int j = 0;
      if (paramAnonymousIntent != null)
      {
        paramAnonymousContext = paramAnonymousIntent.getExtras();
        i = paramAnonymousContext.getInt("actID", 0);
        j = paramAnonymousContext.getInt("actData", 0);
      }
      myActivity.this.DoRecvAction(i, j);
    }
  };
  BroadcastReceiver br_send = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      int i = 0;
      if (paramAnonymousIntent != null) {
        i = paramAnonymousIntent.getExtras().getInt("sendType", 0);
      }
      myActivity.this.DoSendAction(i);
    }
  };
  protected MyButton btnReg;
  public MyButton btnUpdate;
  private TextView connSts;
  private ImageView iconConn;
  private ImageView iconDiscon;
  private ImageView iconUnstable;
  private LinearLayout ll_btnbar;
  private LinearLayout ll_mainview;
  private RelativeLayout ll_statusbar;
  private LinearLayout ll_titlebar;
  protected myMainView mCurMainView;
  myErrorDlg mDialog = null;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      myActivity.this.doHandlerAct(paramAnonymousMessage.what);
    }
  };
  private LayoutInflater mInflater;
  myNaviDlg naviDlg = null;
  protected myAlertDlg remoteDlg = null;
  public RelativeLayout rl_titlebar;
  protected float scrScale;
  myAlertDlg ssidDlg = null;
  float titleTextSize;
  TextView titlemask;
  protected int validScrHeight = 0;
  private myWaitDlg waitDlg;
  myWaitDlg waitdlg = null;
  
  static
  {
    erridlist = new ArrayList();
    thefterridlist = new ArrayList();
  }
  
  private void calcScreenPar()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    int i = GetWindowHeight();
    int j = GetWindowWidth();
    float f = i / localDisplayMetrics.density;
    if ((i / j >= 1.5D) || (f < 480.0F)) {}
    for (this.scrScale = (j / 320.0F);; this.scrScale = (i / 480.0F)) {
      return;
    }
  }
  
  private void calcValidScreenHeight()
  {
    ViewGroup.LayoutParams localLayoutParams1 = this.ll_titlebar.getLayoutParams();
    ViewGroup.LayoutParams localLayoutParams2 = this.ll_btnbar.getLayoutParams();
    ViewGroup.LayoutParams localLayoutParams3 = this.ll_statusbar.getLayoutParams();
    int j = GetWindowHeight();
    int i = j;
    if (j < this.scrScale * 480.0F) {
      i = (int)(this.scrScale * 480.0F);
    }
    this.validScrHeight = (i - localLayoutParams1.height - localLayoutParams2.height - localLayoutParams3.height);
  }
  
  private void reconnToEVRWifi(myActivity parammyActivity, final String paramString1, final String paramString2)
  {
    this.waitDlg = myWaitDlg.MyShowWaitDlg(parammyActivity, 600000);
    this.waitDlg.setViewType(2, paramString1);
    this.waitDlg.SetBtnClickListener(new myWaitDlg.BtnClickListener()
    {
      public void doAction() {}
    });
    this.waitDlg.setNoShowOk();
    myWaitDlg.setWaitResult(0);
    new Thread()
    {
      public void run()
      {
        for (;;)
        {
          try
          {
            myActivity localmyActivity = myActivity.this;
            iMobile_AppGlobalVar.CloseConn();
            int i = ConnSSID.connectToMyWifi(localmyActivity, paramString1, paramString2);
            if (myActivity.this.waitDlg != null)
            {
              if (i == 0) {
                myActivity.this.waitDlg.dismiss();
              }
            }
            else {
              return;
            }
          }
          catch (Exception localException)
          {
            AppDealWifi.LogExceptionMsg(localException);
            continue;
          }
          myActivity.this.waitDlg.resetMsgTimer(100);
        }
      }
    }.start();
  }
  
  private void updateConnectSts(int paramInt)
  {
    lastConSts = paramInt;
    if (paramInt >= 50)
    {
      this.iconConn.setVisibility(0);
      this.iconDiscon.setVisibility(4);
      this.iconUnstable.setVisibility(4);
    }
    for (;;)
    {
      this.connSts.setVisibility(8);
      return;
      if (paramInt >= 10)
      {
        this.iconConn.setVisibility(4);
        this.iconDiscon.setVisibility(4);
        this.iconUnstable.setVisibility(0);
      }
      else
      {
        this.iconConn.setVisibility(4);
        this.iconDiscon.setVisibility(0);
        this.iconUnstable.setVisibility(4);
      }
    }
  }
  
  public void DoRecvAction(int paramInt1, int paramInt2)
  {
    if (mCurForegroundActivity == this)
    {
      if (paramInt1 != 0) {
        break label17;
      }
      PopupErrorDlg(paramInt2);
    }
    for (;;)
    {
      return;
      label17:
      if (paramInt1 == 1)
      {
        AppDealWifi.LogMsg("Get Msg ShowRemoteError");
        ShowRemoteError();
      }
      else if (paramInt1 == 6)
      {
        resetWaitTimer(paramInt2);
      }
      else if (paramInt1 == 10)
      {
        ShowNavi();
      }
      else if (paramInt1 == 30)
      {
        DoReprogramming(false);
      }
      else if (paramInt1 == 31)
      {
        DoReprogramming(true);
      }
      else if (paramInt1 == 32)
      {
        UpgradeSelectDlg localUpgradeSelectDlg = new UpgradeSelectDlg(this);
        if (MsgUpdateTran.g_curA0Status != 1) {
          localUpgradeSelectDlg.showAsBeging();
        }
        localUpgradeSelectDlg.show();
      }
      else if (paramInt1 == 40)
      {
        showDemoBar();
      }
      else if (paramInt1 == 50)
      {
        ShowSSIDError();
      }
      else if (paramInt1 == 60)
      {
        updateConnectSts(paramInt2);
      }
      else if (paramInt1 == 70)
      {
        this.connSts.setText(AppDealWifi.logMsg);
      }
      else if (paramInt1 == 80)
      {
        dealWaiting();
      }
      else if (paramInt1 == 90)
      {
        if (this.mCurMainView != null) {
          this.mCurMainView.Update();
        }
      }
      else if (paramInt1 == 100)
      {
        doShowACtim();
      }
      else if (paramInt1 == 101)
      {
        doShowACWork();
      }
    }
  }
  
  void DoReprogramming(boolean paramBoolean)
  {
    if (MsgReceiver.upgradeProcess == null) {
      if (iMobile_AppGlobalVar.needReprogramming(DefMsg.g_recdatas, 181, false, paramBoolean)) {
        registerReceiver(this.batteryReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
      }
    }
    for (;;)
    {
      return;
      if (paramBoolean)
      {
        myAlertDlg localmyAlertDlg = new myAlertDlg(this);
        localmyAlertDlg.setDlgContent(0, 2131165467, true);
        localmyAlertDlg.show();
        continue;
        MsgReceiver.upgradeProcess.showStep();
      }
    }
  }
  
  public void DoSendAction(int paramInt)
  {
    if (mCurForegroundActivity == this) {
      ShowWaitDlg(paramInt);
    }
  }
  
  public void DoUpdate()
  {
    MsgSender.Send(CmdMake.KO_WF_EV_UPDATE_SP());
  }
  
  public int GetWindowHeight()
  {
    Rect localRect = new Rect();
    getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    return localRect.height();
  }
  
  public int GetWindowWidth()
  {
    return iMobile_AppGlobalVar.screenW;
  }
  
  public void PopupErrorDlg(int paramInt)
  {
    if (erridlist.size() == 0)
    {
      erridlist.add(Integer.valueOf(paramInt));
      showdlg(paramInt);
    }
    label39:
    label129:
    label146:
    for (;;)
    {
      return;
      int j = erridlist.size();
      Object localObject = null;
      int i = 0;
      if (i >= j)
      {
        if (localObject != null) {
          break label129;
        }
        erridlist.add(0, Integer.valueOf(paramInt));
      }
      for (;;)
      {
        if ((erridlist.size() <= 1) || (!canShowErrorDlg())) {
          break label146;
        }
        this.mHandler.removeMessages(2);
        this.mHandler.sendEmptyMessageDelayed(2, 2000L);
        break;
        Integer localInteger = (Integer)erridlist.get(i);
        if (paramInt == localInteger.intValue()) {
          localObject = localInteger;
        }
        i++;
        break label39;
        erridlist.remove(localObject);
        erridlist.add(0, localObject);
      }
    }
  }
  
  protected View SetBtnBar(int paramInt1, int paramInt2)
  {
    return SetBtnBar(this.mInflater.inflate(paramInt1, null), paramInt2);
  }
  
  protected View SetBtnBar(View paramView, int paramInt)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    if (localLayoutParams != null)
    {
      localLayoutParams.height = ((int)(paramInt * this.scrScale));
      localLayoutParams.width = -1;
      paramView.setLayoutParams(localLayoutParams);
    }
    for (;;)
    {
      this.ll_btnbar.removeAllViews();
      this.ll_btnbar.addView(paramView);
      localLayoutParams = this.ll_btnbar.getLayoutParams();
      localLayoutParams.height = ((int)(paramInt * this.scrScale));
      this.ll_btnbar.setLayoutParams(localLayoutParams);
      localLayoutParams = this.barmask.getLayoutParams();
      localLayoutParams.height = ((int)(paramInt * this.scrScale));
      this.barmask.setLayoutParams(localLayoutParams);
      calcValidScreenHeight();
      return paramView;
      paramView.setLayoutParams(new LinearLayout.LayoutParams(-1, (int)(paramInt * this.scrScale)));
    }
  }
  
  public void SetBtnBarEnable(boolean paramBoolean)
  {
    this.ll_btnbar.setEnabled(paramBoolean);
  }
  
  protected View SetMainView(int paramInt)
  {
    return SetMainView(new myMainView(this, paramInt));
  }
  
  protected View SetMainView(myMainView parammyMainView)
  {
    this.mCurMainView = parammyMainView;
    this.ll_mainview.removeAllViews();
    this.ll_mainview.addView(parammyMainView.GetView());
    showDemoBar();
    return parammyMainView.GetView();
  }
  
  protected View SetTitleBar(int paramInt)
  {
    return SetTitleBar(this.mInflater.inflate(paramInt, null));
  }
  
  protected View SetTitleBar(View paramView)
  {
    this.ll_titlebar.removeAllViews();
    this.btnUpdate.setVisibility(0);
    this.ll_titlebar.addView(paramView);
    return paramView;
  }
  
  public void SetTitleText(String paramString)
  {
    if (this.rl_titlebar != null) {
      ((TextView)this.rl_titlebar.findViewById(2131296546)).setText(paramString);
    }
    showDemoBar();
  }
  
  public void ShowNavi()
  {
    if (!canShowNavi()) {}
    for (;;)
    {
      return;
      if ((DefMsg.g_recdatas[''] == 1) && (DefMsg.g_recdatas[94] > 0) && (DefMsg.g_recdatas[94] < 7))
      {
        if (this.naviDlg == null) {
          this.naviDlg = new myNaviDlg(this);
        }
        this.mHandler.removeMessages(3);
        this.mHandler.sendEmptyMessageDelayed(3, 1000L);
        this.naviDlg.show();
      }
    }
  }
  
  public void ShowRemoteError()
  {
    this.remoteDlg = new myAlertDlg(this);
    this.remoteDlg.setDlgContent(2131165485, 2131165486, true);
    this.remoteDlg.setWorn();
    this.remoteDlg.setOkCallback(new myAlertDlg.CloseCallback()
    {
      public void DoAction()
      {
        iMobile_AppGlobalVar.finishWifi();
        myActivity.this.remoteDlg.dismiss();
        myActivity.this.remoteDlg = null;
        AppDealWifi.LogMsg(" ------- remoteDlg close ------- ");
        if (DealMsg.g_VINNum[0] != 0) {
          myActivity.this.finishApp();
        }
        for (;;)
        {
          return;
          AppDealWifi.LogMsg(" ------- to reg 4 ------- ");
          Intent localIntent = new Intent();
          localIntent.setClass(myActivity.this, Login_Sel.class);
          myActivity.this.startActivity(localIntent);
          myActivity.this.finish();
        }
      }
    });
    this.remoteDlg.show();
  }
  
  public void ShowSSIDError()
  {
    this.ssidDlg = new myAlertDlg(this);
    this.ssidDlg.setDlgContent(0, 2131165214, false);
    this.ssidDlg.setOkCallback(new myAlertDlg.CloseCallback()
    {
      public void DoAction()
      {
        String str1 = DealMsg.getSSID();
        String str2 = DealMsg.getSSIDPwd();
        myActivity.this.reconnToEVRWifi(myActivity.this, str1, str2);
      }
    });
    AppDealWifi.LogMsg("Show SSID Error Dialog");
    this.ssidDlg.show();
  }
  
  void ShowWaitDlg(int paramInt)
  {
    String str = DealMsg.getSSID();
    AppDealWifi.LogMsg("Showing sending/refreshing dialog");
    if ((!str.equals("")) && (ConnSSID.isCurSSIDError(this, str)) && (!DealMsg.g_isDemo)) {
      ShowSSIDError();
    }
    for (;;)
    {
      return;
      this.waitdlg = myWaitDlg.MyShowWaitDlg(this, myWaitDlg.m_WaitingTime);
      this.mHandler.sendEmptyMessageDelayed(1, myWaitDlg.m_WaitingTime + myWaitDlg.m_okTime);
      if (paramInt == 1) {
        this.waitdlg.setViewType(1, "");
      }
    }
  }
  
  public boolean canShowErrorDlg()
  {
    return true;
  }
  
  public boolean canShowNavi()
  {
    return false;
  }
  
  public void dealErrorOk()
  {
    int j = ((Integer)erridlist.get(0)).intValue();
    int i = j;
    if (j > 10) {
      i = 1;
    }
    switch (i)
    {
    }
    for (;;)
    {
      return;
      MsgSender.Send(CmdMake.KO_WF_THEFT_DISP_SP());
      removeErrID();
      continue;
      MsgSender.Send(CmdMake.KO_WF_AC_ERROR_DISP_SP());
      removeErrID();
      continue;
      MsgSender.Send(CmdMake.KO_WF_CHG_ERROR_DISP_SP());
      removeErrID();
      continue;
      MsgSender.Send(CmdMake.KO_WF_BUP_ERROR_DISP_SP());
      removeErrID();
      continue;
      erridlist.removeAll(erridlist);
      this.mDialog.dismiss();
      iMobile_AppGlobalVar.finishWifi();
      Intent localIntent = new Intent();
      localIntent.setClass(this, Login_SSID.class);
      startActivity(localIntent);
      finish();
      continue;
      erridlist.removeAll(erridlist);
      this.mDialog.dismiss();
      iMobile_AppGlobalVar.finishWifi();
      localIntent = new Intent();
      localIntent.setClass(this, Login_Sel.class);
      startActivity(localIntent);
      finish();
    }
  }
  
  void dealHideNaviDlg()
  {
    if ((DefMsg.g_recdatas[''] != 1) || (DefMsg.g_recdatas[94] == 0) || (DefMsg.g_recdatas[94] == 7))
    {
      this.naviDlg.dismiss();
      this.naviDlg = null;
    }
    for (;;)
    {
      return;
      this.mHandler.sendEmptyMessageDelayed(3, 1000L);
    }
  }
  
  public void dealShowErrDlg()
  {
    if (erridlist.size() > 0)
    {
      Integer localInteger = (Integer)erridlist.remove(0);
      erridlist.add(localInteger);
      showdlg(((Integer)erridlist.get(0)).intValue());
    }
    if (erridlist.size() > 1)
    {
      this.mHandler.removeMessages(2);
      this.mHandler.sendEmptyMessageDelayed(2, 2000L);
    }
  }
  
  void dealWaiting()
  {
    DefMsg.updateAll();
    MsgReceiver.testECUDone();
    if (this.mCurMainView != null)
    {
      showDemoBar();
      this.mCurMainView.Update();
    }
  }
  
  public int dip2px(float paramFloat)
  {
    return (int)(paramFloat * getResources().getDisplayMetrics().density + 0.5F);
  }
  
  public void doHandlerAct(int paramInt)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      return;
      if (MsgReceiver.isCommSucceed())
      {
        dealWaiting();
        continue;
        dealShowErrDlg();
        continue;
        dealHideNaviDlg();
      }
    }
  }
  
  void doShowACWork()
  {
    myAlertDlg localmyAlertDlg = new myAlertDlg(this);
    localmyAlertDlg.setDlgContent(0, 2131165373, true);
    localmyAlertDlg.show();
  }
  
  void doShowACtim()
  {
    myAlertDlg localmyAlertDlg = new myAlertDlg(this);
    localmyAlertDlg.setDlgContent(0, 2131165372, true);
    localmyAlertDlg.show();
  }
  
  void doTestBatteryLevel(int paramInt1, int paramInt2)
  {
    unregisterReceiver(this.batteryReceiver);
    if (paramInt1 < 21)
    {
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setMessage(getString(2131165468));
      localBuilder.setNegativeButton(getString(2131165200), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
        }
      });
      localBuilder.setCancelable(false);
      localBuilder.create().show();
    }
    for (;;)
    {
      return;
      startReprogramming();
    }
  }
  
  public void enlargeViewHeight(View paramView)
  {
    if (paramView != null)
    {
      ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
      if (localLayoutParams.height > 0)
      {
        localLayoutParams.height = ((int)(px2dip(localLayoutParams.height) * this.scrScale));
        paramView.setLayoutParams(localLayoutParams);
      }
    }
  }
  
  public void enlargeViewHeightWidth(View paramView)
  {
    if (paramView != null)
    {
      paramView = paramView.getLayoutParams();
      if (paramView.height > 0) {
        paramView.height = ((int)(px2dip(paramView.height) * this.scrScale));
      }
      if (paramView.width > 0) {
        paramView.width = ((int)(px2dip(paramView.width) * this.scrScale));
      }
    }
  }
  
  public void enlargeViewToFitScreen(View paramView, boolean paramBoolean)
  {
    if (paramView != null)
    {
      Object localObject = paramView.getLayoutParams();
      if (((ViewGroup.LayoutParams)localObject).height > 0) {
        ((ViewGroup.LayoutParams)localObject).height = ((int)(px2dip(((ViewGroup.LayoutParams)localObject).height) * this.scrScale));
      }
      if (((ViewGroup.LayoutParams)localObject).width > 0) {
        ((ViewGroup.LayoutParams)localObject).width = ((int)(px2dip(((ViewGroup.LayoutParams)localObject).width) * this.scrScale));
      }
      localObject = (ViewGroup.MarginLayoutParams)paramView.getLayoutParams();
      if (((ViewGroup.MarginLayoutParams)localObject).leftMargin > 0) {
        ((ViewGroup.MarginLayoutParams)localObject).leftMargin = ((int)(px2dip(((ViewGroup.MarginLayoutParams)localObject).leftMargin) * this.scrScale));
      }
      if (((ViewGroup.MarginLayoutParams)localObject).rightMargin > 0) {
        ((ViewGroup.MarginLayoutParams)localObject).rightMargin = ((int)(px2dip(((ViewGroup.MarginLayoutParams)localObject).rightMargin) * this.scrScale));
      }
      if (((ViewGroup.MarginLayoutParams)localObject).topMargin > 0) {
        ((ViewGroup.MarginLayoutParams)localObject).topMargin = ((int)(px2dip(((ViewGroup.MarginLayoutParams)localObject).topMargin) * this.scrScale));
      }
      if (((ViewGroup.MarginLayoutParams)localObject).bottomMargin > 0) {
        ((ViewGroup.MarginLayoutParams)localObject).bottomMargin = ((int)(px2dip(((ViewGroup.MarginLayoutParams)localObject).bottomMargin) * this.scrScale));
      }
      paramView.setPadding((int)(px2dip(paramView.getPaddingLeft()) * this.scrScale), (int)(px2dip(paramView.getPaddingTop()) * this.scrScale), (int)(px2dip(paramView.getPaddingRight()) * this.scrScale), (int)(px2dip(paramView.getPaddingBottom()) * this.scrScale));
      if (paramBoolean)
      {
        float f1 = px2dip(((TextView)paramView).getTextSize());
        float f2 = this.scrScale;
        ((TextView)paramView).setTextSize(0, f1 * f2);
      }
    }
  }
  
  public void enlargeViewWidth(View paramView)
  {
    if (paramView != null)
    {
      ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
      if (localLayoutParams.width > 0)
      {
        localLayoutParams.width = ((int)(px2dip(localLayoutParams.width) * this.scrScale));
        paramView.setLayoutParams(localLayoutParams);
      }
    }
  }
  
  public int getBtnBarHeight()
  {
    return this.ll_btnbar.getLayoutParams().height;
  }
  
  public float getScrScale()
  {
    return this.scrScale;
  }
  
  public int getValidScreenHeight()
  {
    return this.validScrHeight;
  }
  
  protected void hideTabBar()
  {
    this.ll_btnbar.setVisibility(8);
  }
  
  public void hideUpdateBtn()
  {
    this.btnUpdate.setVisibility(4);
  }
  
  public void notifySendResult(boolean paramBoolean) {}
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == 102)
    {
      AppDealWifi.LogMsg("recieved code 102, close myself");
      setResult(102);
      finish();
    }
    for (;;)
    {
      return;
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903085);
    setEarth((ImageView)findViewById(2131296257));
    calcScreenPar();
    this.ll_titlebar = ((LinearLayout)findViewById(2131296590));
    enlargeViewHeight(this.ll_titlebar);
    enlargeViewHeight((LinearLayout)findViewById(2131296584));
    this.ll_statusbar = ((RelativeLayout)findViewById(2131296585));
    enlargeViewHeight(this.ll_statusbar);
    statusBarHeight = this.ll_statusbar.getLayoutParams().height;
    this.iconConn = ((ImageView)findViewById(2131296586));
    this.iconDiscon = ((ImageView)findViewById(2131296587));
    this.iconUnstable = ((ImageView)findViewById(2131296588));
    enlargeViewToFitScreen(this.iconConn, false);
    enlargeViewToFitScreen(this.iconDiscon, false);
    enlargeViewToFitScreen(this.iconUnstable, false);
    this.iconConn.setVisibility(4);
    this.iconDiscon.setVisibility(4);
    this.iconUnstable.setVisibility(4);
    this.connSts = ((TextView)findViewById(2131296589));
    enlargeViewToFitScreen(this.connSts, true);
    this.ll_btnbar = ((LinearLayout)findViewById(2131296591));
    enlargeViewHeight(this.ll_btnbar);
    this.ll_mainview = ((LinearLayout)findViewById(2131296592));
    this.titlemask = ((TextView)findViewById(2131296593));
    enlargeViewHeight(this.titlemask);
    this.titlemask.setVisibility(8);
    this.titlemask.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        return true;
      }
    });
    this.barmask = ((TextView)findViewById(2131296594));
    enlargeViewHeight(this.barmask);
    this.barmask.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        return true;
      }
    });
    this.barmask.setVisibility(8);
    calcValidScreenHeight();
    this.mInflater = LayoutInflater.from(this);
    this.rl_titlebar = ((RelativeLayout)this.mInflater.inflate(2130903089, null));
    paramBundle = (AutoResizeTextView)this.rl_titlebar.findViewById(2131296546);
    paramBundle.setSingle();
    enlargeViewToFitScreen(paramBundle, true);
    enlargeViewToFitScreen((TextView)this.rl_titlebar.findViewById(2131296610), true);
    this.btnUpdate = ((MyButton)this.rl_titlebar.findViewById(2131296609));
    this.btnReg = ((MyButton)this.rl_titlebar.findViewById(2131296612));
    enlargeViewToFitScreen(this.btnReg, true);
    this.btnReg.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        myActivity.this.showLoginScreen();
      }
    });
    enlargeViewToFitScreen(this.btnUpdate, true);
    this.btnUpdate.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        myActivity.this.DoUpdate();
      }
    });
    SetTitleBar(this.rl_titlebar);
    updateConnectSts(lastConSts);
  }
  
  protected void onDestroy()
  {
    this.mHandler.removeMessages(1);
    this.mHandler.removeMessages(2);
    this.mHandler.removeMessages(3);
    this.mHandler.removeMessages(4);
    super.onDestroy();
    AppDealWifi.LogMsg(getClass() + " destroied");
  }
  
  protected void onPause()
  {
    unregisterReceiver(this.br_send);
    unregisterReceiver(this.br_recv);
    super.onPause();
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    showHelp();
    return true;
  }
  
  protected void onResume()
  {
    super.onResume();
    MsgSender.RegisterMsgSend(this, this.br_send);
    MsgReceiver.RegisterMsgAction(this, this.br_recv);
    updateTitleBarBtn();
    if (this.mCurMainView != null)
    {
      showDemoBar();
      this.mCurMainView.Refresh();
    }
  }
  
  public int px2dip(float paramFloat)
  {
    return (int)(paramFloat / getResources().getDisplayMetrics().density + 0.5F);
  }
  
  void removeErrID()
  {
    erridlist.remove(0);
    if (erridlist.size() <= 0)
    {
      this.mDialog.dismiss();
      this.mDialog = null;
    }
  }
  
  void resetWaitTimer(int paramInt)
  {
    if (mCurForegroundActivity == this)
    {
      if (this.waitdlg == null) {
        ShowWaitDlg(0);
      }
      this.waitdlg.resetMsgTimer(paramInt);
      this.mHandler.removeMessages(1);
      this.mHandler.sendEmptyMessageDelayed(1, myWaitDlg.m_okTime + paramInt);
    }
  }
  
  public void sendHandlerMsg(int paramInt1, int paramInt2)
  {
    this.mHandler.sendEmptyMessageDelayed(paramInt1, paramInt2);
  }
  
  public void showAppMask(boolean paramBoolean)
  {
    int j = 0;
    TextView localTextView = this.titlemask;
    if (paramBoolean)
    {
      i = 0;
      localTextView.setVisibility(i);
      localTextView = this.barmask;
      if (!paramBoolean) {
        break label43;
      }
    }
    label43:
    for (int i = j;; i = 8)
    {
      localTextView.setVisibility(i);
      return;
      i = 8;
      break;
    }
  }
  
  public void showDemoBar()
  {
    TextView localTextView;
    Object localObject;
    if (this.rl_titlebar != null)
    {
      localTextView = (TextView)this.rl_titlebar.findViewById(2131296610);
      localObject = (MyButton)this.rl_titlebar.findViewById(2131296612);
      if (localTextView != null)
      {
        if (!DealMsg.g_isDemo) {
          break label85;
        }
        localTextView.setVisibility(0);
        ((MyButton)localObject).setVisibility(0);
      }
      localObject = (TextView)this.rl_titlebar.findViewById(2131296613);
      if (localObject != null)
      {
        if (!MsgReceiver.showErrBar) {
          break label98;
        }
        ((TextView)localObject).setVisibility(0);
      }
    }
    for (;;)
    {
      return;
      label85:
      localTextView.setVisibility(4);
      ((MyButton)localObject).setVisibility(4);
      break;
      label98:
      ((TextView)localObject).setVisibility(4);
    }
  }
  
  protected void showHelp()
  {
    if (this.mCurMainView != null) {
      this.mCurMainView.PopupHelpInfo();
    }
  }
  
  protected void showLoginScreen()
  {
    setResult(102);
    finish();
  }
  
  public void showdlg(int paramInt)
  {
    int i = 2130903042;
    if ((!canShowErrorDlg()) && (paramInt < 5)) {}
    label31:
    label494:
    for (;;)
    {
      return;
      if (this.mDialog == null) {
        if (showLogin())
        {
          this.mDialog = new myErrorDlg(this, i);
          this.mDialog.SetOnCloseCallback(new myErrorDlg.CloseCallback()
          {
            public void DoAction()
            {
              myActivity.this.dealErrorOk();
            }
          });
          int j = paramInt;
          i = j;
          if (j > 10) {
            i = 1;
          }
          this.mDialog.setDateText("");
          switch (i)
          {
          }
        }
      }
      for (;;)
      {
        if (this.mDialog.isShowing()) {
          break label494;
        }
        this.mDialog.show();
        break;
        i = 2130903041;
        break label31;
        Object localObject = this.mDialog;
        if (showLogin()) {}
        for (;;)
        {
          ((myErrorDlg)localObject).reLoadView(i);
          break;
          i = 2130903041;
        }
        localObject = new int[7];
        localObject[1] = 2131165490;
        localObject[2] = 2131165491;
        localObject[3] = 2131165492;
        localObject[4] = 2131165489;
        localObject[5] = 2131165493;
        localObject[6] = 2131165494;
        paramInt -= 10;
        if ((paramInt > 0) && (paramInt < 7))
        {
          this.mDialog.SetTextContent(getString(localObject[paramInt]), 20.0F);
          this.mDialog.setDateText(DealMsg.getTheftDate(this, DealMsg.theftAlertOn[paramInt]));
        }
        this.mDialog.SetTextTitle(getString(2131165487), 20.0F);
        continue;
        this.mDialog.SetTextContent(getString(2131165498), 20.0F);
        this.mDialog.SetTextTitle(getString(2131165497), 20.0F);
        this.mDialog.hideAlertMark();
        this.mDialog.hideDate();
        this.mDialog.AdjustTitleSize(110, 100);
        continue;
        this.mDialog.SetTextContent(getString(2131165496), 20.0F);
        this.mDialog.SetTextTitle(getString(2131165495), 20.0F);
        continue;
        this.mDialog.SetTextContent(getString(2131165500), 20.0F);
        this.mDialog.SetTextTitle(getString(2131165499), 20.0F);
        continue;
        this.mDialog.SetTextContent(getString(2131165475), 20.0F);
        this.mDialog.SetTextTitle(getString(2131165474), 20.0F);
        continue;
        this.mDialog.SetTextContent(getString(2131165484), 20.0F);
        this.mDialog.SetTextTitle(getString(2131165483), 20.0F);
      }
    }
  }
  
  void startReprogramming()
  {
    if (ConnSSID.isCurSSIDError(this, DealMsg.getSSID())) {
      ShowSSIDError();
    }
    for (;;)
    {
      return;
      new UpgradeDlg(this).show();
    }
  }
  
  public void updateTitleBarBtn()
  {
    if (this.btnUpdate != null) {
      this.btnUpdate.chengeBg(2130837571, -1, 2130837569, -12303292);
    }
    if (this.btnReg != null) {
      this.btnReg.chengeBg(2130837565, -1, 2130837566, -12303292);
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseClass\myActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */