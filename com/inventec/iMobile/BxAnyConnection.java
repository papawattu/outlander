package com.inventec.iMobile;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseClass.mySwitch;
import com.inventec.iMobile.baseClass.mySwitch.OnSwitchStateChanged;
import com.inventec.iMobile.baseOP.CmdMake;
import com.inventec.iMobile.baseOP.DataGetter;
import com.inventec.iMobile.baseOP.MsgSender;

public class BxAnyConnection
  extends myActivity
{
  mySwitch anysw;
  MyButton btnOk;
  
  protected void btnOkOnClick()
  {
    byte b = 0;
    if (this.anysw.getState()) {
      b = 1;
    }
    MsgSender.Send(CmdMake.KO_WF_ANY_CONNECT_SP(b));
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.btnOk = ((MyButton)((LinearLayout)SetBtnBar(2130903088, 50)).findViewById(2131296262));
    this.btnOk.setText(2131165438);
    this.btnOk.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BxAnyConnection.this.btnOkOnClick();
      }
    });
    SetMainView(new MvAnyConnection(this));
    SetTitleText(getString(2131165412));
  }
  
  class MvAnyConnection
    extends myMainView
  {
    public MvAnyConnection(myActivity parammyActivity)
    {
      super(2130903043);
      BxAnyConnection.this.anysw = ((mySwitch)this.mView.findViewById(2131296268));
      BxAnyConnection.this.anysw.SetOnSwitchStateChanged(new mySwitch.OnSwitchStateChanged()
      {
        public void onswitchclose()
        {
          BxAnyConnection.MvAnyConnection.this.setOkStatuse();
        }
        
        public void onswitchopen()
        {
          BxAnyConnection.MvAnyConnection.this.setOkStatuse();
        }
      });
    }
    
    public void Refresh()
    {
      if (DataGetter.g_datas() == 1) {
        BxAnyConnection.this.anysw.SetState(true);
      }
      for (;;)
      {
        setOkStatuse();
        return;
        BxAnyConnection.this.anysw.SetState(false);
      }
    }
    
    void setOkStatuse()
    {
      boolean bool2 = true;
      boolean bool1 = true;
      int i = DataGetter.g_datas((short)144);
      if (BxAnyConnection.this.anysw.getState())
      {
        localMyButton = BxAnyConnection.this.btnOk;
        if (i != 1) {}
        for (;;)
        {
          localMyButton.setEnabled(bool1);
          return;
          bool1 = false;
        }
      }
      MyButton localMyButton = BxAnyConnection.this.btnOk;
      if (i == 1) {}
      for (bool1 = bool2;; bool1 = false)
      {
        localMyButton.setEnabled(bool1);
        break;
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\BxAnyConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */