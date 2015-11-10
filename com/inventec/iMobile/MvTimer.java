package com.inventec.iMobile;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseOP.DealFile;

public class MvTimer
  extends myMainView
{
  MyButton bt1 = (MyButton)this.mView.findViewById(2131296581);
  MyButton bt2;
  boolean flag_viable = true;
  LinearLayout layout1;
  LinearLayout layout2;
  private byte myBATTERY_CHRG;
  private byte myPRE_AC_CONT;
  
  public MvTimer(myActivity parammyActivity)
  {
    super(parammyActivity, 2130903084);
    this.bt1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DealFile.deleteFile(MvTimer.this.mAct, FrmSetting_Charge.fnsav);
        paramAnonymousView = new Intent();
        paramAnonymousView.setClass(MvTimer.this.mAct, FrmSetting_Charge.class);
        MvTimer.this.mAct.startActivityForResult(paramAnonymousView, 101);
      }
    });
    this.bt2 = ((MyButton)this.mView.findViewById(2131296582));
    this.bt2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        DealFile.deleteFile(MvTimer.this.mAct, FrmSetting_AirConditioning.fnsav);
        paramAnonymousView = new Intent();
        paramAnonymousView.setClass(MvTimer.this.mAct, FrmSetting_AirConditioning.class);
        MvTimer.this.mAct.startActivityForResult(paramAnonymousView, 101);
      }
    });
    parammyActivity.enlargeViewToFitScreen(this.bt1, true);
    parammyActivity.enlargeViewToFitScreen(this.bt2, true);
  }
  
  public void Refresh()
  {
    FrmMain localFrmMain = (FrmMain)this.mAct;
    localFrmMain.SetTitleText(this.mAct.getString(2131165334));
    localFrmMain.showDemoBar();
    controlbutton();
  }
  
  public void controlbutton()
  {
    this.myBATTERY_CHRG = 1;
    this.myPRE_AC_CONT = 1;
    if (this.myBATTERY_CHRG == 0)
    {
      this.mView.findViewById(2131296265).setVisibility(8);
      if (this.myPRE_AC_CONT != 0) {
        break label69;
      }
      this.mView.findViewById(2131296291).setVisibility(8);
    }
    for (;;)
    {
      return;
      this.mView.findViewById(2131296265).setVisibility(0);
      break;
      label69:
      this.mView.findViewById(2131296291).setVisibility(0);
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\MvTimer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */