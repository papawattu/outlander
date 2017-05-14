package com.inventec.iMobile;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
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

public class BxChangePsw
  extends myActivity
{
  EditText et;
  Handler myhd;
  String passWord;
  TextView passcodeTitle;
  int state = 0;
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
    this.passWord = DealMsg.getPassword();
    if (this.passWord.length() == 0) {
      finish();
    }
    for (;;)
    {
      return;
      hideTabBar();
      SetMainView(new MvChangePsw(this));
      SetTitleText(getString(2131165410));
      hideUpdateBtn();
    }
  }
  
  protected void onPause()
  {
    this.myhd.removeMessages(1);
    super.onPause();
  }
  
  protected void onResume()
  {
    super.onResume();
    this.myhd.sendEmptyMessageDelayed(1, 100L);
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
    String newpass;
    
    public MvChangePsw(myActivity parammyActivity)
    {
      super(2130903101);
      BxChangePsw.this.setEarth((ImageView)this.mView.findViewById(2131296257));
      BxChangePsw.this.passcodeTitle = ((TextView)this.mView.findViewById(2131296629));
      parammyActivity.enlargeViewToFitScreen(BxChangePsw.this.passcodeTitle, true);
      BxChangePsw.this.passcodeTitle.setText(2131165426);
      BxChangePsw.this.tv1 = ((TextView)this.mView.findViewById(2131296627));
      BxChangePsw.this.tv2 = ((TextView)this.mView.findViewById(2131296264));
      BxChangePsw.this.tv3 = ((TextView)this.mView.findViewById(2131296631));
      BxChangePsw.this.tv4 = ((TextView)this.mView.findViewById(2131296632));
      parammyActivity.enlargeViewToFitScreen(BxChangePsw.this.tv1, true);
      parammyActivity.enlargeViewToFitScreen(BxChangePsw.this.tv2, true);
      parammyActivity.enlargeViewToFitScreen(BxChangePsw.this.tv3, true);
      parammyActivity.enlargeViewToFitScreen(BxChangePsw.this.tv4, true);
      BxChangePsw.this.et = ((EditText)this.mView.findViewById(2131296628));
      BxChangePsw.this.et.setInputType(2);
      BxChangePsw.this.et.setFilters(new InputFilter[] { new InputFilter.LengthFilter(4) });
      BxChangePsw.this.et.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable)
        {
          int i = BxChangePsw.this.et.getText().toString().length();
          BxChangePsw.this.setTextViewBGs(i);
          if (i > 3) {
            BxChangePsw.this.myhd.sendEmptyMessageDelayed(2, 150L);
          }
        }
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      });
      BxChangePsw.this.myhd = new Handler()
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          super.handleMessage(paramAnonymousMessage);
          if (paramAnonymousMessage.what == 1) {
            BxChangePsw.this.showIME();
          }
          for (;;)
          {
            return;
            if (paramAnonymousMessage.what == 2)
            {
              BxChangePsw.MvChangePsw.this.doTestPassword();
            }
            else if (paramAnonymousMessage.what == 5)
            {
              BxChangePsw.this.et.setText("", TextView.BufferType.EDITABLE);
              BxChangePsw.this.setTextViewBGs(0);
            }
          }
        }
      };
    }
    
    void doTestPassword()
    {
      BxChangePsw.this.myhd.sendEmptyMessageDelayed(5, 150L);
      if (BxChangePsw.this.state == 1) {
        BxChangePsw.this.passWord = BxChangePsw.this.et.getText().toString();
      }
      Object localObject;
      if (BxChangePsw.this.et.getText().toString().endsWith(BxChangePsw.this.passWord))
      {
        localObject = BxChangePsw.this;
        ((BxChangePsw)localObject).state += 1;
        if (BxChangePsw.this.state == 1) {
          BxChangePsw.this.passcodeTitle.setText(2131165427);
        }
        if (BxChangePsw.this.state == 2) {
          BxChangePsw.this.passcodeTitle.setText(2131165428);
        }
        if (BxChangePsw.this.state == 3)
        {
          DealMsg.setPassword(BxChangePsw.this.passWord);
          DealMsg.savePASSWORD(this.mAct);
          BxChangePsw.this.finish();
        }
      }
      for (;;)
      {
        return;
        localObject = new myAlertDlg(this.mAct);
        ((myAlertDlg)localObject).setDlgContent(2131165206, 2131165207, true);
        ((myAlertDlg)localObject).setOkCallback(new myAlertDlg.CloseCallback()
        {
          public void DoAction()
          {
            BxChangePsw.this.myhd.sendEmptyMessageDelayed(5, 150L);
          }
        });
        ((myAlertDlg)localObject).show();
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\BxChangePsw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */