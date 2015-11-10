package com.inventec.iMobile;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myAlertDlg;
import com.inventec.iMobile.baseClass.myAlertDlg.CloseCallback;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseStruct.DealMsg;

public class BxPswOffOn
  extends myActivity
{
  EditText et;
  Handler myhd;
  byte pType = 0;
  String passWord;
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
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    hideTabBar();
    SetMainView(new MvChangePsw(this));
    SetTitleText(getString(2131165409).replace("\n", " "));
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
    public MvChangePsw(myActivity parammyActivity)
    {
      super(2130903101);
      BxPswOffOn.this.setEarth((ImageView)this.mView.findViewById(2131296257));
      BxPswOffOn.this.passWord = DealMsg.getPassword();
      TextView localTextView = (TextView)this.mView.findViewById(2131296629);
      parammyActivity.enlargeViewToFitScreen(localTextView, true);
      BxPswOffOn.this.pType = DealMsg.getPasswordType();
      if (BxPswOffOn.this.pType == 0) {}
      for (int i = 2131165427;; i = 2131165422)
      {
        localTextView.setText(i);
        BxPswOffOn.this.tv1 = ((TextView)this.mView.findViewById(2131296627));
        BxPswOffOn.this.tv2 = ((TextView)this.mView.findViewById(2131296264));
        BxPswOffOn.this.tv3 = ((TextView)this.mView.findViewById(2131296631));
        BxPswOffOn.this.tv4 = ((TextView)this.mView.findViewById(2131296632));
        parammyActivity.enlargeViewToFitScreen(BxPswOffOn.this.tv1, true);
        parammyActivity.enlargeViewToFitScreen(BxPswOffOn.this.tv2, true);
        parammyActivity.enlargeViewToFitScreen(BxPswOffOn.this.tv3, true);
        parammyActivity.enlargeViewToFitScreen(BxPswOffOn.this.tv4, true);
        BxPswOffOn.this.et = ((EditText)this.mView.findViewById(2131296628));
        BxPswOffOn.this.et.setInputType(2);
        BxPswOffOn.this.et.addTextChangedListener(new TextWatcher()
        {
          public void afterTextChanged(Editable paramAnonymousEditable)
          {
            int i = 3;
            int j = BxPswOffOn.this.et.getText().toString().length();
            BxPswOffOn.this.setTextViewBGs(j);
            if (j > 3)
            {
              paramAnonymousEditable = BxPswOffOn.this.myhd;
              if (BxPswOffOn.this.pType == 1) {
                i = 2;
              }
              paramAnonymousEditable.sendEmptyMessageDelayed(i, 150L);
            }
          }
          
          public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
          
          public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        });
        BxPswOffOn.this.myhd = new Handler()
        {
          public void handleMessage(Message paramAnonymousMessage)
          {
            super.handleMessage(paramAnonymousMessage);
            if (paramAnonymousMessage.what == 1) {
              BxPswOffOn.this.showIME();
            }
            for (;;)
            {
              return;
              if (paramAnonymousMessage.what == 2)
              {
                BxPswOffOn.MvChangePsw.this.doTestPasswordOn();
              }
              else if (paramAnonymousMessage.what == 3)
              {
                BxPswOffOn.MvChangePsw.this.doTestPasswordOff();
              }
              else if (paramAnonymousMessage.what == 5)
              {
                BxPswOffOn.this.et.setText("", TextView.BufferType.EDITABLE);
                BxPswOffOn.this.setTextViewBGs(0);
              }
            }
          }
        };
        return;
      }
    }
    
    void doTestPasswordOff()
    {
      if (BxPswOffOn.this.passWord.length() == 0)
      {
        BxPswOffOn.this.passWord = BxPswOffOn.this.et.getText().toString();
        BxPswOffOn.this.et.setText("", TextView.BufferType.EDITABLE);
        BxPswOffOn.this.setTextViewBGs(0);
        ((TextView)this.mView.findViewById(2131296629)).setText(2131165428);
      }
      for (;;)
      {
        return;
        myAlertDlg localmyAlertDlg;
        if (BxPswOffOn.this.et.getText().toString().endsWith(BxPswOffOn.this.passWord))
        {
          DealMsg.setPasswordType((byte)1);
          DealMsg.setPassword(BxPswOffOn.this.passWord);
          DealMsg.savePASSWORD(this.mAct);
          localmyAlertDlg = new myAlertDlg(this.mAct);
          localmyAlertDlg.setDlgContent(0, 2131165424, true);
          localmyAlertDlg.setOkCallback(new myAlertDlg.CloseCallback()
          {
            public void DoAction()
            {
              BxPswOffOn.this.finish();
            }
          });
          localmyAlertDlg.show();
        }
        else
        {
          localmyAlertDlg = new myAlertDlg(this.mAct);
          localmyAlertDlg.setDlgContent(2131165206, 2131165207, true);
          localmyAlertDlg.show();
          BxPswOffOn.this.myhd.sendEmptyMessageDelayed(5, 150L);
        }
      }
    }
    
    void doTestPasswordOn()
    {
      myAlertDlg localmyAlertDlg;
      if (BxPswOffOn.this.et.getText().toString().endsWith(BxPswOffOn.this.passWord))
      {
        DealMsg.setPasswordType((byte)2);
        DealMsg.savePASSWORD(this.mAct);
        localmyAlertDlg = new myAlertDlg(this.mAct);
        localmyAlertDlg.setDlgContent(0, 2131165423, true);
        localmyAlertDlg.setOkCallback(new myAlertDlg.CloseCallback()
        {
          public void DoAction()
          {
            BxPswOffOn.this.finish();
          }
        });
        localmyAlertDlg.show();
      }
      for (;;)
      {
        return;
        localmyAlertDlg = new myAlertDlg(this.mAct);
        localmyAlertDlg.setDlgContent(2131165206, 2131165207, true);
        localmyAlertDlg.show();
        BxPswOffOn.this.myhd.sendEmptyMessageDelayed(5, 150L);
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\BxPswOffOn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */