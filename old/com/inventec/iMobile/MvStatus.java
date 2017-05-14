package com.inventec.iMobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.makeBitmap;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseOP.DataGetter;

public class MvStatus
  extends myMainView
{
  boolean blink = false;
  makeBitmap ibmp = null;
  makeBitmap ibmp2 = null;
  ImageView image1 = (ImageView)this.mView.findViewById(2131296290);
  RelativeLayout layout1;
  LinearLayout layout2;
  RelativeLayout layout_left;
  RelativeLayout layout_right;
  ImageView mainPower;
  Handler myhd = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      boolean bool;
      ImageView localImageView;
      if (paramAnonymousMessage.what == 1)
      {
        paramAnonymousMessage = MvStatus.this;
        if (!MvStatus.this.blink) {
          break label74;
        }
        bool = false;
        paramAnonymousMessage.blink = bool;
        localImageView = MvStatus.this.image1;
        if (!MvStatus.this.blink) {
          break label79;
        }
      }
      label74:
      label79:
      for (paramAnonymousMessage = MvStatus.this.ibmp.getImage();; paramAnonymousMessage = MvStatus.this.ibmp2.getImage())
      {
        localImageView.setImageBitmap(paramAnonymousMessage);
        sendEmptyMessageDelayed(1, 500L);
        return;
        bool = true;
        break;
      }
    }
  };
  AutoResizeTextView t7 = (AutoResizeTextView)this.mView.findViewById(2131296576);
  AutoResizeTextView t8;
  ImageView theftAlarm;
  RelativeLayout theftalarmArea;
  
  public MvStatus(myActivity parammyActivity)
  {
    super(parammyActivity, 2130903083);
    parammyActivity.enlargeViewToFitScreen(this.t7, true);
    this.t7.setSingle();
    this.t8 = ((AutoResizeTextView)this.mView.findViewById(2131296570));
    this.t8.setSingle();
    parammyActivity.enlargeViewToFitScreen(this.t8, true);
    this.layout1 = ((RelativeLayout)this.mView.findViewById(2131296265));
    this.layout_right = ((RelativeLayout)this.mView.findViewById(2131296577));
    this.layout_left = ((RelativeLayout)this.mView.findViewById(2131296571));
    this.layout2 = ((LinearLayout)this.mView.findViewById(2131296291));
    if (iMobile_AppGlobalVar.isPad)
    {
      this.layout_left.setBackgroundResource(2130837808);
      this.layout_right.setBackgroundResource(2130837808);
    }
    this.mainPower = ((ImageView)this.mView.findViewById(2131296574));
    this.theftAlarm = ((ImageView)this.mView.findViewById(2131296580));
    setAutosizeView();
  }
  
  public void MarginTop(View paramView, int paramInt)
  {
    RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)paramView.getLayoutParams();
    localLayoutParams.topMargin = paramInt;
    paramView.setLayoutParams(localLayoutParams);
  }
  
  public void MarginViewSize(View paramView, int paramInt1, int paramInt2)
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)paramView.getLayoutParams();
    localLayoutParams.rightMargin = paramInt1;
    localLayoutParams.leftMargin = paramInt2;
    paramView.setLayoutParams(localLayoutParams);
  }
  
  public void PopupHelpInfo()
  {
    Intent localIntent = new Intent(this.mAct, BxShowHelp.class);
    Bundle localBundle = new Bundle();
    localBundle.putInt("CardNo", 31);
    localIntent.putExtras(localBundle);
    this.mAct.startActivityForResult(localIntent, 101);
  }
  
  public void Refresh()
  {
    this.mAct.SetTitleText(this.mAct.getString(2131165382));
    setStatus();
  }
  
  public void setAutosizeView()
  {
    this.mAct.enlargeViewToFitScreen(this.mainPower, false);
    this.mAct.enlargeViewToFitScreen(this.theftAlarm, false);
    AutoResizeTextView localAutoResizeTextView3 = (AutoResizeTextView)this.mView.findViewById(2131296572);
    AutoResizeTextView localAutoResizeTextView2 = (AutoResizeTextView)this.mView.findViewById(2131296573);
    AutoResizeTextView localAutoResizeTextView1 = (AutoResizeTextView)this.mView.findViewById(2131296578);
    AutoResizeTextView localAutoResizeTextView4 = (AutoResizeTextView)this.mView.findViewById(2131296579);
    this.mAct.enlargeViewToFitScreen(localAutoResizeTextView3, true);
    this.mAct.enlargeViewToFitScreen(localAutoResizeTextView2, true);
    this.mAct.enlargeViewToFitScreen(localAutoResizeTextView1, true);
    this.mAct.enlargeViewToFitScreen(localAutoResizeTextView4, true);
  }
  
  public void setLayoutWeight(View paramView, float paramFloat)
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)paramView.getLayoutParams();
    localLayoutParams.weight = paramFloat;
    paramView.setLayoutParams(localLayoutParams);
  }
  
  public void setStatus()
  {
    this.myhd.removeMessages(1);
    makeBitmap localmakeBitmap = this.ibmp;
    this.ibmp = new makeBitmap();
    this.ibmp.InitBgBmp(this.mAct.getResources(), 2130837727);
    int n = DataGetter.g_datas((short)126);
    int j = DataGetter.g_datas((short)139);
    int i = DataGetter.g_datas((short)101);
    int m = DataGetter.g_datas((short)128);
    if (n != 4)
    {
      if (j == 1) {
        this.ibmp.AddImgData(2130837736, 62, 1);
      }
      if (i == 1) {
        this.ibmp.AddImgData(2130837741, 79, 42);
      }
    }
    int i7 = DataGetter.g_datas((short)179);
    int i6 = DataGetter.g_datas((short)133);
    int i5 = DataGetter.g_datas((short)134);
    int i3 = DataGetter.g_datas((short)135);
    int i4 = DataGetter.g_datas((short)136);
    int i2 = DataGetter.g_datas((short)137);
    int i1 = DataGetter.g_datas((short)138);
    j = 0;
    int k = 0;
    i = 0;
    if (i7 == 1)
    {
      j = 1;
      if (i6 == 1)
      {
        k = 1;
        if (i5 != 1) {
          break label584;
        }
        i = 1;
        label197:
        if (k != 0) {
          this.ibmp.AddImgData(2130837729, 0, 202);
        }
        if (i != 0) {
          this.ibmp.AddImgData(2130837732, 243, 201);
        }
        if ((k == 0) && (i == 0)) {
          this.ibmp.AddImgData(2130837740, 62, 201);
        }
        if ((i3 == 1) && (j != 0)) {
          this.ibmp.AddImgData(2130837731, 239, 326);
        }
        if ((i4 == 1) && (j != 0)) {
          this.ibmp.AddImgData(2130837728, 5, 326);
        }
        if (i2 == 1) {
          this.ibmp.AddImgData(2130837734, 100, 468);
        }
        if (i1 == 1) {
          this.ibmp.AddImgData(2130837737, 92, 57);
        }
        i = DataGetter.g_datas((short)129);
        if ((i == 1) || (i == 3)) {
          this.ibmp.AddImgData(2130837742, 96, 230);
        }
        if (this.ibmp2 != null)
        {
          this.ibmp2.freeBmp();
          this.ibmp2 = null;
        }
        if ((n != 4) && (m == 1))
        {
          this.ibmp2 = new makeBitmap();
          this.ibmp2.InitBgBmp(this.mAct.getResources(), this.ibmp.getImage());
          this.ibmp.AddImgData(2130837735, 32, 37);
          this.myhd.sendEmptyMessageDelayed(1, 360L);
          this.blink = true;
        }
        this.image1.setImageBitmap(this.ibmp.getImage());
        if ((localmakeBitmap != null) && (localmakeBitmap.freeBmp())) {
          System.gc();
        }
        if (DataGetter.g_datas((short)94) != 0) {
          break label628;
        }
        this.mainPower.setImageResource(2130837738);
        label515:
        this.theftalarmArea = ((RelativeLayout)this.mView.findViewById(2131296575));
        if (!DataGetter.canDealTheftAlerm()) {
          break label687;
        }
        this.theftalarmArea.setVisibility(0);
        setLayoutWeight(this.theftalarmArea, 1.0F);
        if ((n != 4) && (n != 5)) {
          break label641;
        }
        this.theftAlarm.setImageResource(2130837724);
      }
    }
    for (;;)
    {
      return;
      k = 0;
      break;
      label584:
      i = 0;
      break label197;
      if (i7 != 2) {
        break label197;
      }
      j = 1;
      if (i6 == 1)
      {
        i = 1;
        label605:
        if (i5 != 1) {
          break label622;
        }
      }
      label622:
      for (k = 1;; k = 0)
      {
        break;
        i = 0;
        break label605;
      }
      label628:
      this.mainPower.setImageResource(2130837739);
      break label515;
      label641:
      if ((n == 1) || (n == 6) || (n == 7))
      {
        this.theftAlarm.setImageResource(2130837726);
      }
      else
      {
        this.theftAlarm.setImageResource(2130837725);
        continue;
        label687:
        setLayoutWeight(this.theftalarmArea, 0.0F);
        this.theftalarmArea.setVisibility(8);
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\MvStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */