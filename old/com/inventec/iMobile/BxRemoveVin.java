package com.inventec.iMobile;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myActivityBase;
import com.inventec.iMobile.baseClass.myAlertDlg;
import com.inventec.iMobile.baseClass.myAlertDlg.CloseCallback;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseClass.myWaitDlg;
import com.inventec.iMobile.baseOP.CmdMake;
import com.inventec.iMobile.baseOP.MsgSender;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.baseStruct.DefMsg;
import java.util.Timer;

public class BxRemoveVin
  extends myActivity
{
  EditText et;
  boolean flag = false;
  InputMethodManager imm;
  int lenth = 0;
  Handler myhd;
  int num = 0;
  String passWord;
  TextView passcodeTitle;
  Timer timer;
  RelativeLayout titlebar;
  Button titlebutton;
  TextView titletxt;
  TextView tv1;
  TextView tv2;
  TextView tv3;
  TextView tv4;
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 1) {
      showIME();
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }
  
  public void hideWaitDlg()
  {
    finishApp();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    hideTabBar();
    SetMainView(new MvChangePsw(this));
    SetTitleText(getString(2131165415));
    hideUpdateBtn();
  }
  
  protected void onPause()
  {
    this.myhd.removeMessages(1);
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.myhd.sendEmptyMessageDelayed(1, 1000L);
  }
  
  void setTextViewBG(TextView paramTextView, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramTextView.setBackgroundResource(2130837707);
    }
    for (;;)
    {
      return;
      paramTextView.setBackgroundColor(-1);
    }
  }
  
  void setTextViewBGs(int paramInt)
  {
    boolean bool2 = true;
    TextView localTextView = this.tv1;
    if (paramInt > 0)
    {
      bool1 = true;
      setTextViewBG(localTextView, bool1);
      localTextView = this.tv2;
      if (paramInt <= 1) {
        break label81;
      }
      bool1 = true;
      label32:
      setTextViewBG(localTextView, bool1);
      localTextView = this.tv3;
      if (paramInt <= 2) {
        break label86;
      }
      bool1 = true;
      label50:
      setTextViewBG(localTextView, bool1);
      localTextView = this.tv4;
      if (paramInt <= 3) {
        break label91;
      }
    }
    label81:
    label86:
    label91:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      setTextViewBG(localTextView, bool1);
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label32;
      bool1 = false;
      break label50;
    }
  }
  
  void showIME()
  {
    this.myhd.removeMessages(1);
    if (this.et != null) {
      ((InputMethodManager)getSystemService("input_method")).showSoftInput(this.et, 1);
    }
    this.myhd.sendEmptyMessageDelayed(1, 10000L);
  }
  
  class MvChangePsw
    extends myMainView
  {
    myAlertDlg dialog;
    
    public MvChangePsw(myActivity parammyActivity)
    {
      super(2130903101);
      BxRemoveVin.this.setEarth((ImageView)this.mView.findViewById(2131296257));
      BxRemoveVin.this.passWord = DealMsg.getPassword();
      int i = DealMsg.getPasswordType();
      if ((i == 2) || (i == 0)) {
        doAskReset0();
      }
      BxRemoveVin.this.myhd = new Handler()
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          super.handleMessage(paramAnonymousMessage);
          if (paramAnonymousMessage.what == 1) {
            BxRemoveVin.this.showIME();
          }
          for (;;)
          {
            return;
            if (paramAnonymousMessage.what == 2)
            {
              BxRemoveVin.MvChangePsw.this.doTestPassword();
            }
            else if (paramAnonymousMessage.what == 4)
            {
              BxRemoveVin.this.finishApp();
            }
            else if (paramAnonymousMessage.what == 5)
            {
              BxRemoveVin.this.et.setText("", TextView.BufferType.EDITABLE);
              BxRemoveVin.this.setTextViewBGs(0);
            }
          }
        }
      };
      BxRemoveVin.this.tv1 = ((TextView)this.mView.findViewById(2131296627));
      BxRemoveVin.this.tv2 = ((TextView)this.mView.findViewById(2131296264));
      BxRemoveVin.this.tv3 = ((TextView)this.mView.findViewById(2131296631));
      BxRemoveVin.this.tv4 = ((TextView)this.mView.findViewById(2131296632));
      parammyActivity.enlargeViewToFitScreen(BxRemoveVin.this.tv1, true);
      parammyActivity.enlargeViewToFitScreen(BxRemoveVin.this.tv2, true);
      parammyActivity.enlargeViewToFitScreen(BxRemoveVin.this.tv3, true);
      parammyActivity.enlargeViewToFitScreen(BxRemoveVin.this.tv4, true);
      BxRemoveVin.this.et = ((EditText)this.mView.findViewById(2131296628));
      BxRemoveVin.this.passcodeTitle = ((TextView)this.mView.findViewById(2131296629));
      parammyActivity.enlargeViewToFitScreen(BxRemoveVin.this.passcodeTitle, true);
      BxRemoveVin.this.passcodeTitle.setText(2131165422);
      BxRemoveVin.this.et.setInputType(2);
      BxRemoveVin.this.et.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable)
        {
          int i = BxRemoveVin.this.et.getText().toString().length();
          BxRemoveVin.this.setTextViewBGs(i);
          if (i > 3) {
            BxRemoveVin.this.myhd.sendEmptyMessageDelayed(2, 150L);
          }
        }
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      });
    }
    
    public void doAskReset()
    {
      this.dialog = new myAlertDlg(this.mAct);
      this.dialog.setDlgContent(0, 2131165447, false);
      this.dialog.noDoDismiss();
      this.dialog.setOkCallback(new myAlertDlg.CloseCallback()
      {
        public void DoAction()
        {
          BxRemoveVin.MvChangePsw.this.doInitAct();
        }
      });
      this.dialog.setCancelCallback(new myAlertDlg.CloseCallback()
      {
        public void DoAction()
        {
          BxRemoveVin.MvChangePsw.this.dialog.dismiss();
          BxRemoveVin.this.finish();
        }
      });
      this.dialog.show();
    }
    
    void doAskReset0()
    {
      this.dialog = new myAlertDlg(this.mAct);
      this.dialog.setDlgContent(0, 2131165446, false);
      this.dialog.noDoDismiss();
      this.dialog.setOkCallback(new myAlertDlg.CloseCallback()
      {
        public void DoAction()
        {
          if (DefMsg.g_recdatas[58] < 2) {
            BxRemoveVin.MvChangePsw.this.doAskReset();
          }
          for (;;)
          {
            return;
            BxRemoveVin.MvChangePsw.this.doInitAct();
          }
        }
      });
      this.dialog.setCancelCallback(new myAlertDlg.CloseCallback()
      {
        public void DoAction()
        {
          BxRemoveVin.MvChangePsw.this.dialog.dismiss();
          BxRemoveVin.this.finish();
        }
      });
      this.dialog.show();
    }
    
    void doInitAct()
    {
      MsgSender.Send(CmdMake.KO_WF_INIT_RQ_SP());
      DefMsg.memsetZero(DealMsg.g_VINNum, 0, 17);
      DefMsg.memsetZero(DefMsg.g_recdatas, 40, 17);
      myActivityBase localmyActivityBase = myActivity.GetCurForegroundActivity();
      DealMsg.saveVIN(localmyActivityBase);
      DealMsg.SaveAPPSet(localmyActivityBase);
      DealMsg.setSSID("");
      DealMsg.saveSSID(localmyActivityBase);
      DefMsg.memsetZero(DealMsg.g_PASSWORD, 0, 6);
      DealMsg.savePASSWORD(localmyActivityBase);
      BxRemoveVin.this.myhd.sendEmptyMessageDelayed(4, 10000L);
      myWaitDlg.setShowOk();
    }
    
    void doTestPassword()
    {
      if (BxRemoveVin.this.et.getText().toString().endsWith(BxRemoveVin.this.passWord)) {
        doAskReset0();
      }
      for (;;)
      {
        return;
        myAlertDlg localmyAlertDlg = new myAlertDlg(this.mAct);
        localmyAlertDlg.setDlgContent(2131165206, 2131165207, true);
        localmyAlertDlg.show();
        BxRemoveVin.this.myhd.sendEmptyMessageDelayed(5, 150L);
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\BxRemoveVin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */