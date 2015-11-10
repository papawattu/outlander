package com.inventec.iMobile.baseClass;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;
import com.inventec.controls.myProgressBar;
import com.inventec.iMobile.iMobile_AppGlobalVar;

public class myWaitDlg
  extends ProgressDialog
{
  static boolean alwaysShowOk = false;
  public static boolean isLogin = false;
  static myActivity mAct;
  public static int m_WaitingTime = 0;
  public static int m_okTime = 2000;
  static int waitResult = 0;
  int dlgType = 0;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 1) {
        myWaitDlg.this.testResult();
      }
    }
  };
  private BtnClickListener m_BtnClickListener = null;
  myProgressBar prog = null;
  boolean showOk = true;
  
  public myWaitDlg(myActivityBase parammyActivityBase)
  {
    super(parammyActivityBase, 2131230720);
  }
  
  public myWaitDlg(myActivityBase parammyActivityBase, int paramInt)
  {
    super(parammyActivityBase, 2131230720);
    if (paramInt > 0) {}
    for (m_WaitingTime = paramInt;; m_WaitingTime = 5000) {
      return;
    }
  }
  
  public static myWaitDlg MyShowWaitDlg(myActivity parammyActivity, int paramInt)
  {
    isLogin = parammyActivity.showLogin();
    mAct = parammyActivity;
    myWaitDlg localmyWaitDlg = new myWaitDlg(parammyActivity, paramInt);
    localmyWaitDlg.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface) {}
    });
    localmyWaitDlg.show();
    parammyActivity = mAct;
    if (isLogin) {}
    for (paramInt = 2130903098;; paramInt = 2130903095)
    {
      localmyWaitDlg.SetView(parammyActivity, paramInt);
      return localmyWaitDlg;
    }
  }
  
  public static void setShowOk()
  {
    alwaysShowOk = true;
  }
  
  public static void setWaitResult(int paramInt)
  {
    waitResult = paramInt;
  }
  
  public void SetBtnClickListener(BtnClickListener paramBtnClickListener)
  {
    this.m_BtnClickListener = paramBtnClickListener;
  }
  
  public void SetView(Activity paramActivity, int paramInt)
  {
    SetView(paramActivity.getLayoutInflater().inflate(paramInt, null));
  }
  
  public void SetView(View paramView)
  {
    int i = 0;
    int j = 0;
    int k = 0;
    setContentView(paramView, getWindow().getAttributes());
    ((ViewGroup.MarginLayoutParams)paramView.getLayoutParams()).topMargin = myActivity.statusBarHeight;
    paramView = paramView.getLayoutParams();
    paramView.height = (iMobile_AppGlobalVar.screenH - myActivity.statusBarHeight);
    paramView.width = iMobile_AppGlobalVar.screenW;
    getWindow().setGravity(80);
    mAct.setEarth((ImageView)findViewById(2131296257));
    paramView = (AutoResizeTextView)findViewById(2131296264);
    mAct.enlargeViewToFitScreen(paramView, true);
    paramView = (AutoResizeTextView)findViewById(2131296266);
    mAct.enlargeViewToFitScreen(paramView, true);
    paramView = (AutoResizeTextView)findViewById(2131296625);
    mAct.enlargeViewToFitScreen(paramView, true);
    Object localObject1;
    if (paramView != null)
    {
      localObject1 = paramView.getLayoutParams();
      paramView = (ViewGroup.MarginLayoutParams)localObject1;
      i = ((ViewGroup.LayoutParams)localObject1).height + ((ViewGroup.MarginLayoutParams)localObject1).topMargin + paramView.topMargin + paramView.bottomMargin;
    }
    paramView = (AutoResizeTextView)findViewById(2131296626);
    mAct.enlargeViewToFitScreen(paramView, true);
    if (paramView != null)
    {
      paramView.getPaint().setFakeBoldText(true);
      localObject1 = paramView.getLayoutParams();
      paramView = (ViewGroup.MarginLayoutParams)localObject1;
      j = ((ViewGroup.LayoutParams)localObject1).height + ((ViewGroup.MarginLayoutParams)localObject1).topMargin + paramView.topMargin + paramView.bottomMargin;
    }
    Object localObject2 = (MyButton)findViewById(2131296262);
    if (localObject2 != null)
    {
      mAct.enlargeViewToFitScreen((View)localObject2, true);
      localObject1 = ((MyButton)localObject2).getLayoutParams();
      paramView = (ViewGroup.MarginLayoutParams)localObject1;
      k = ((ViewGroup.LayoutParams)localObject1).height + ((ViewGroup.MarginLayoutParams)localObject1).topMargin + paramView.topMargin + paramView.bottomMargin;
      ((MyButton)localObject2).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          myWaitDlg.this.dismiss();
        }
      });
    }
    if (i + j + k > mAct.GetWindowHeight())
    {
      localObject1 = (AutoResizeTextView)findViewById(2131296626);
      localObject2 = ((AutoResizeTextView)localObject1).getLayoutParams();
      paramView = (ViewGroup.MarginLayoutParams)localObject2;
      ((ViewGroup.LayoutParams)localObject2).height = (mAct.GetWindowHeight() - i - k - paramView.topMargin - paramView.bottomMargin);
      ((AutoResizeTextView)localObject1).setLayoutParams((ViewGroup.LayoutParams)localObject2);
    }
    if (waitResult != 4) {
      this.mHandler.sendEmptyMessageDelayed(1, m_WaitingTime);
    }
  }
  
  public void dismiss()
  {
    myActivityBase localmyActivityBase = myActivity.GetCurForegroundActivity();
    if (localmyActivityBase != null) {
      localmyActivityBase.hideWaitDlg();
    }
    if (this.prog != null) {
      this.prog.stopAutoProgress();
    }
    super.dismiss();
    waitResult = 3;
    if (this.m_BtnClickListener != null) {
      this.m_BtnClickListener.doAction();
    }
  }
  
  public myProgressBar getProgressBar()
  {
    return this.prog;
  }
  
  public void onBackPressed()
  {
    if (this.dlgType == 2) {
      super.onBackPressed();
    }
  }
  
  public void resetMsgTimer(int paramInt)
  {
    this.mHandler.removeMessages(1);
    this.mHandler.sendEmptyMessageDelayed(1, paramInt);
  }
  
  public void setNoShowOk()
  {
    this.showOk = false;
  }
  
  public void setViewType(int paramInt, String paramString)
  {
    this.dlgType = paramInt;
    if (paramInt == 1) {
      ((TextView)findViewById(2131296266)).setText(getContext().getString(2131165212));
    }
    for (;;)
    {
      return;
      if (paramInt == 2)
      {
        ((AutoResizeTextView)findViewById(2131296266)).setText(getContext().getString(2131165194));
        this.prog = ((myProgressBar)findViewById(2131296624));
        mAct.enlargeViewToFitScreen(this.prog, false);
        AutoResizeTextView localAutoResizeTextView = (AutoResizeTextView)findViewById(2131296622);
        mAct.enlargeViewToFitScreen(localAutoResizeTextView, true);
        localAutoResizeTextView.setColors(-16777216, -3355444);
        localAutoResizeTextView.setVisibility(0);
        localAutoResizeTextView.setText(" SSID:" + paramString);
      }
    }
  }
  
  void testResult()
  {
    if (waitResult == 0) {
      waitResult = 2;
    }
    if ((waitResult == 1) || (alwaysShowOk)) {
      if (this.showOk)
      {
        m_WaitingTime = m_okTime;
        SetView(mAct, 2130903099);
        waitResult = 3;
        mAct.notifySendResult(true);
      }
    }
    for (;;)
    {
      return;
      if (waitResult == 3)
      {
        dismiss();
      }
      else
      {
        mAct.notifySendResult(false);
        waitResult = 4;
        if ((this.dlgType == 2) && (!isLogin))
        {
          SetView(mAct, 2130903097);
        }
        else
        {
          SetView(mAct, 2130903096);
          if (this.dlgType == 2)
          {
            ((TextView)findViewById(2131296625)).setText(2131165476);
            ((TextView)findViewById(2131296626)).setText(2131165477);
          }
        }
      }
    }
  }
  
  public static abstract interface BtnClickListener
  {
    public abstract void doAction();
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseClass\myWaitDlg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */