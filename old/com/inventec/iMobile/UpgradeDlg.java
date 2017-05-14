package com.inventec.iMobile;

import android.app.Dialog;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseOP.AppDealWifi;
import com.inventec.iMobile.baseOP.CmdMake;
import com.inventec.iMobile.baseOP.MsgReceiver;
import com.inventec.iMobile.baseOP.MsgSender;
import com.inventec.iMobile.baseOP.MsgUpdateTran;

public class UpgradeDlg
  extends Dialog
{
  Handler handler = new Handler();
  myActivity mAct;
  ProgressBar proc = null;
  MsgUpdateTran process = null;
  AutoResizeTextView txtPercent = null;
  
  public UpgradeDlg(myActivity parammyActivity)
  {
    super(parammyActivity, 2131230720);
    this.mAct = parammyActivity;
    this.process = new MsgUpdateTran();
    showSending();
  }
  
  public void HideProgress()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        UpgradeDlg.this.proc.setVisibility(4);
        UpgradeDlg.this.txtPercent.setVisibility(4);
      }
    });
  }
  
  public void ShowProgress()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        UpgradeDlg.this.setProgress(0.0F);
        UpgradeDlg.this.proc.setVisibility(0);
        UpgradeDlg.this.txtPercent.setVisibility(0);
      }
    });
  }
  
  public void dismiss()
  {
    this.process.StopUpgrade();
    super.dismiss();
  }
  
  public void hideCancelButton()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        ((MyButton)UpgradeDlg.this.findViewById(2131296262)).setVisibility(4);
      }
    });
    Log.e("iMobile", "hideCancelButton");
  }
  
  public void onBackPressed() {}
  
  void setDlgContent(int paramInt)
  {
    setContentView(this.mAct.getLayoutInflater().inflate(paramInt, null));
    WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
    localLayoutParams.width = -1;
    getWindow().setAttributes((WindowManager.LayoutParams)localLayoutParams);
    this.mAct.setEarth((ImageView)findViewById(2131296257));
  }
  
  public void setProgress(final float paramFloat)
  {
    int i = this.proc.getProgress();
    if ((i < (int)paramFloat) || ((i == 0) && ((int)paramFloat == 0))) {
      this.handler.post(new Runnable()
      {
        public void run()
        {
          UpgradeDlg.this.proc.setProgress((int)paramFloat);
          UpgradeDlg.this.txtPercent.setText(String.format("%d%%", new Object[] { Integer.valueOf((int)paramFloat) }));
          float f = UpgradeDlg.this.proc.getLeft() + UpgradeDlg.this.proc.getWidth() * paramFloat / 100.0F - UpgradeDlg.this.txtPercent.getWidth() / 2;
          LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)UpgradeDlg.this.txtPercent.getLayoutParams();
          if (localLayoutParams != null)
          {
            AppDealWifi.LogMsg(paramFloat + "%" + " indicator move to " + f);
            localLayoutParams.leftMargin = ((int)f);
            UpgradeDlg.this.txtPercent.setLayoutParams(localLayoutParams);
          }
        }
      });
    }
  }
  
  public void setProgressColor(int paramInt)
  {
    this.txtPercent.setColors(paramInt, -16777216);
  }
  
  void setWebView(int paramInt1, int paramInt2)
  {
    WebView localWebView = (WebView)findViewById(paramInt1);
    if (localWebView == null) {}
    for (;;)
    {
      return;
      iMobile_AppGlobalVar.setWebContent(localWebView, this.mAct.getString(paramInt2), 2);
    }
  }
  
  public void showHowToSPModel()
  {
    showOKCancelDlg(2130903112);
  }
  
  public void showNoNeedUpgrade()
  {
    Log.e("iMobile", "Newer Version already Installed");
    showOKDlg(2130903109);
  }
  
  void showOKCancelDlg(final int paramInt)
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        try
        {
          Thread.sleep(100L);
          UpgradeDlg.this.setDlgContent(paramInt);
          Object localObject = (TextView)UpgradeDlg.this.findViewById(2131296266);
          UpgradeDlg.this.mAct.enlargeViewToFitScreen((View)localObject, true);
          localObject = (MyButton)UpgradeDlg.this.findViewById(2131296262);
          if (localObject != null)
          {
            UpgradeDlg.this.mAct.enlargeViewToFitScreen((View)localObject, true);
            ((MyButton)localObject).setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                UpgradeDlg.this.showSending();
              }
            });
          }
          localObject = (MyButton)UpgradeDlg.this.findViewById(2131296618);
          if (localObject != null)
          {
            UpgradeDlg.this.mAct.enlargeViewToFitScreen((View)localObject, true);
            ((MyButton)localObject).setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramAnonymous2View)
              {
                UpgradeDlg.this.dismiss();
              }
            });
          }
          UpgradeDlg.this.setWebView(2131296652, 2131165466);
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;)
          {
            localInterruptedException.printStackTrace();
          }
        }
      }
    });
  }
  
  void showOKDlg(final int paramInt)
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        UpgradeDlg.this.setDlgContent(paramInt);
        if (paramInt == 2130903108) {
          ((AutoResizeTextView)UpgradeDlg.this.findViewById(2131296266)).setColors(-65536, -12303292);
        }
        Object localObject = (TextView)UpgradeDlg.this.findViewById(2131296266);
        UpgradeDlg.this.mAct.enlargeViewToFitScreen((View)localObject, true);
        localObject = (TextView)UpgradeDlg.this.findViewById(2131296625);
        UpgradeDlg.this.mAct.enlargeViewToFitScreen((View)localObject, true);
        localObject = (TextView)UpgradeDlg.this.findViewById(2131296626);
        UpgradeDlg.this.mAct.enlargeViewToFitScreen((View)localObject, true);
        localObject = (MyButton)UpgradeDlg.this.findViewById(2131296262);
        if (localObject != null)
        {
          UpgradeDlg.this.mAct.enlargeViewToFitScreen((View)localObject, true);
          ((MyButton)localObject).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              UpgradeDlg.this.dismiss();
            }
          });
        }
      }
    });
  }
  
  public void showReupgrade()
  {
    showOKCancelDlg(2130903111);
  }
  
  public void showSelect()
  {
    MsgReceiver.BroadcastMsgAction(32, 0);
  }
  
  public void showSendError()
  {
    showOKDlg(2130903096);
  }
  
  public void showSending()
  {
    setDlgContent(2130903095);
    AutoResizeTextView localAutoResizeTextView = (AutoResizeTextView)findViewById(2131296266);
    this.mAct.enlargeViewToFitScreen(localAutoResizeTextView, true);
    this.process.StartUpgrade(this);
  }
  
  public void showUpgradeFinish()
  {
    showOKDlg(2130903110);
  }
  
  public void showUpgradeProcess()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        UpgradeDlg.this.setDlgContent(2130903113);
        UpgradeDlg.this.proc = ((ProgressBar)UpgradeDlg.this.findViewById(2131296656));
        UpgradeDlg.this.proc.setVisibility(0);
        UpgradeDlg.this.proc.setMax(100);
        UpgradeDlg.this.mAct.enlargeViewToFitScreen(UpgradeDlg.this.proc, false);
        UpgradeDlg.this.txtPercent = ((AutoResizeTextView)UpgradeDlg.this.findViewById(2131296655));
        Object localObject = (AutoResizeTextView)UpgradeDlg.this.findViewById(2131296653);
        UpgradeDlg.this.mAct.enlargeViewToFitScreen((View)localObject, true);
        localObject = (AutoResizeTextView)UpgradeDlg.this.findViewById(2131296270);
        UpgradeDlg.this.mAct.enlargeViewToFitScreen((View)localObject, true);
        localObject = (AutoResizeTextView)UpgradeDlg.this.findViewById(2131296266);
        UpgradeDlg.this.mAct.enlargeViewToFitScreen((View)localObject, true);
        localObject = (MyButton)UpgradeDlg.this.findViewById(2131296262);
        if (localObject != null)
        {
          UpgradeDlg.this.mAct.enlargeViewToFitScreen((View)localObject, true);
          ((MyButton)localObject).setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              MsgSender.resendKMsg(CmdMake.makeOneCmd(, (byte)0));
              if (MsgReceiver.upgradeProcess != null) {
                MsgReceiver.upgradeProcess.dismissDlg();
              }
            }
          });
        }
        UpgradeDlg.this.setProgress(0.0F);
      }
    });
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\UpgradeDlg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */