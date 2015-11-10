package com.inventec.iMobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseStruct.DealMsg;

public class PasswordInput
  extends myActivity
{
  private static final int PwdCheck = 2;
  private static final int PwdErr = 5;
  private static final int PwdInput = 1;
  EditText et;
  Handler myhd;
  String passWord;
  TextView tv1;
  TextView tv2;
  TextView tv3;
  TextView tv4;
  
  private void startLogin()
  {
    Intent localIntent = new Intent();
    localIntent.setClass(this, showversion.class);
    startActivity(localIntent);
    finish();
  }
  
  void doTestPassword()
  {
    if (this.et.getText().toString().endsWith(this.passWord)) {
      startLogin();
    }
    for (;;)
    {
      return;
      this.myhd.sendEmptyMessageDelayed(5, 150L);
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903101);
    statusBarHeight = 0;
    setEarth((ImageView)findViewById(2131296257));
    DealMsg.LoadAPPSet(this);
    this.passWord = DealMsg.getPassword();
    int i = DealMsg.getPasswordType();
    if ((i == 2) || (i == 0)) {
      startLogin();
    }
    for (;;)
    {
      return;
      enlargeViewToFitScreen((TextView)findViewById(2131296629), true);
      this.myhd = new Handler()
      {
        public void handleMessage(Message paramAnonymousMessage)
        {
          super.handleMessage(paramAnonymousMessage);
          if (paramAnonymousMessage.what == 1) {
            PasswordInput.this.showIME();
          }
          for (;;)
          {
            return;
            if (paramAnonymousMessage.what == 2)
            {
              PasswordInput.this.doTestPassword();
            }
            else if (paramAnonymousMessage.what == 5)
            {
              PasswordInput.this.et.setText("", TextView.BufferType.EDITABLE);
              PasswordInput.this.setTextViewBGs(0);
            }
          }
        }
      };
      this.tv1 = ((TextView)findViewById(2131296627));
      this.tv2 = ((TextView)findViewById(2131296264));
      this.tv3 = ((TextView)findViewById(2131296631));
      this.tv4 = ((TextView)findViewById(2131296632));
      enlargeViewToFitScreen(this.tv1, true);
      enlargeViewToFitScreen(this.tv2, true);
      enlargeViewToFitScreen(this.tv3, true);
      enlargeViewToFitScreen(this.tv4, true);
      this.et = ((EditText)findViewById(2131296628));
      this.et.setInputType(2);
      this.et.addTextChangedListener(new TextWatcher()
      {
        public void afterTextChanged(Editable paramAnonymousEditable)
        {
          int i = PasswordInput.this.et.getText().toString().length();
          PasswordInput.this.setTextViewBGs(i);
          if (i > 3) {
            PasswordInput.this.myhd.sendEmptyMessageDelayed(2, 150L);
          }
        }
        
        public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
        
        public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      });
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
    if (this.et != null) {
      ((InputMethodManager)getSystemService("input_method")).showSoftInput(this.et, 1);
    }
    this.myhd.sendEmptyMessageDelayed(1, 1000L);
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\PasswordInput.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */