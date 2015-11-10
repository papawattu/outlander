package com.inventec.iMobile;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myAlertDlg;
import com.inventec.iMobile.baseClass.myAlertDlg.CloseCallback;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseOP.CmdMake;
import com.inventec.iMobile.baseOP.ConnSSID;
import com.inventec.iMobile.baseOP.DataGetter;
import com.inventec.iMobile.baseOP.MsgSender;
import com.inventec.iMobile.baseStruct.DefMsg;
import java.util.Timer;
import java.util.TimerTask;

public class BxChangeSSID
  extends myActivity
{
  EditText ed1;
  EditText ed2;
  String ssid;
  
  public void DoUpdate()
  {
    Object localObject2 = this.ed2.getText().toString();
    int j = 0;
    Object localObject1 = new byte[32];
    DefMsg.memsetZero((byte[])localObject1, 0, 32);
    int i = 0;
    if ((i >= ((String)localObject2).length()) || (j != 0))
    {
      if ((j == 0) && (((String)localObject2).length() != 0)) {
        break label135;
      }
      localObject1 = new myAlertDlg(this, 2130903094);
      ((myAlertDlg)localObject1).setDlgContent(2131165434, 2131165435, true);
      ((myAlertDlg)localObject1).setOwnerActivity(this);
      ((myAlertDlg)localObject1).show();
    }
    for (;;)
    {
      return;
      int k = ((String)localObject2).charAt(i);
      if ((k < 33) || (k > 127) || (k == 34) || (k == 44)) {
        j = 1;
      }
      localObject1[i] = ((byte)k);
      i++;
      break;
      label135:
      localObject2 = new myAlertDlg(this);
      ((myAlertDlg)localObject2).setDlgContent(0, 2131165433, false);
      ((myAlertDlg)localObject2).setOkCallback(new myAlertDlg.CloseCallback()
      {
        public void DoAction()
        {
          byte[] arrayOfByte = new byte[32];
          for (int i = 0;; i++)
          {
            if (i >= 32)
            {
              MsgSender.Send(CmdMake.KO_WF_SSID_CHANGE_SP(arrayOfByte));
              return;
            }
            arrayOfByte[i] = DefMsg.g_datas[(145 + i)];
          }
        }
      });
      ((myAlertDlg)localObject2).show();
      for (i = 0; i < 32; i++) {
        DefMsg.g_datas[(145 + i)] = localObject1[i];
      }
    }
  }
  
  public void notifySendResult(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      ConnSSID.stopCurSSID(this);
      ConnSSID.curConnSSID = this.ed2.getText().toString();
      ConnSSID.saveCurWifi(this);
      startConnWifiThread();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    hideTabBar();
    SetMainView(new MvChangeSSID(this));
    SetTitleText(getString(2131165411));
  }
  
  public void updateTitleBarBtn()
  {
    this.btnUpdate.chengeBg(2130837608, -1, 2130837609, -12303292);
    this.btnUpdate.setText(2131165438);
  }
  
  class MvChangeSSID
    extends myMainView
  {
    public MvChangeSSID(myActivity parammyActivity)
    {
      super(2130903047);
      AutoResizeTextView localAutoResizeTextView = (AutoResizeTextView)this.mView.findViewById(2131296283);
      localAutoResizeTextView.setSingle();
      parammyActivity.enlargeViewToFitScreen(localAutoResizeTextView, true);
      localAutoResizeTextView.setColors(MyButton.colNor, -3355444);
      localAutoResizeTextView.setSingle();
      localAutoResizeTextView = (AutoResizeTextView)this.mView.findViewById(2131296286);
      parammyActivity.enlargeViewToFitScreen(localAutoResizeTextView, true);
      localAutoResizeTextView.setColors(MyButton.colNor, -3355444);
      localAutoResizeTextView.setSingle();
      parammyActivity.enlargeViewToFitScreen((AutoResizeTextView)this.mView.findViewById(2131296266), true);
      parammyActivity.enlargeViewToFitScreen((RelativeLayout)this.mView.findViewById(2131296282), false);
      parammyActivity.enlargeViewToFitScreen((RelativeLayout)this.mView.findViewById(2131296285), false);
      BxChangeSSID.this.ed1 = ((EditText)this.mView.findViewById(2131296284));
      parammyActivity.enlargeViewToFitScreen(BxChangeSSID.this.ed1, true);
      BxChangeSSID.this.ed2 = ((EditText)this.mView.findViewById(2131296287));
      parammyActivity.enlargeViewToFitScreen(BxChangeSSID.this.ed2, true);
      BxChangeSSID.this.ed2.setFilters(new InputFilter[] { new InputFilter.LengthFilter(30) });
      BxChangeSSID.this.ed2.requestFocus();
      keyup();
    }
    
    public void PopupHelpInfo()
    {
      Intent localIntent = new Intent(this.mAct, BxShowHelp.class);
      Bundle localBundle = new Bundle();
      localBundle.putInt("CardNo", 52);
      localIntent.putExtras(localBundle);
      this.mAct.startActivityForResult(localIntent, 101);
    }
    
    public void Refresh()
    {
      byte[] arrayOfByte = DataGetter.g_recdatas(, 33);
      BxChangeSSID.this.ssid = "";
      for (int i = 0;; i++)
      {
        if (i >= 32) {}
        char c;
        do
        {
          BxChangeSSID.this.ed1.setText(BxChangeSSID.this.ssid);
          BxChangeSSID.this.ed1.setEnabled(false);
          return;
          c = (char)arrayOfByte[i];
        } while ((c <= ' ') || (c >= ''));
        BxChangeSSID localBxChangeSSID = BxChangeSSID.this;
        localBxChangeSSID.ssid += c;
      }
    }
    
    public void keyup()
    {
      new Timer().schedule(new TimerTask()
      {
        public void run()
        {
          InputMethodManager localInputMethodManager = (InputMethodManager)BxChangeSSID.this.getSystemService("input_method");
          if (BxChangeSSID.this.ed2 != null) {
            localInputMethodManager.showSoftInput(BxChangeSSID.this.ed2, 1);
          }
        }
      }, 1000L);
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\BxChangeSSID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */