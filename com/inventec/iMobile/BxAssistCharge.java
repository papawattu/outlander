package com.inventec.iMobile;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseClass.mySwitch;
import com.inventec.iMobile.baseClass.mySwitch.OnSwitchStateChanged;
import com.inventec.iMobile.baseOP.CmdMake;
import com.inventec.iMobile.baseOP.DataGetter;
import com.inventec.iMobile.baseOP.MsgSender;
import kankan.wheel.widget.OnWheelClickedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;

public class BxAssistCharge
  extends myActivity
{
  MyButton btnOk;
  byte hour = 0;
  byte min = 0;
  byte onoff = 1;
  WheelView st_hours;
  WheelView st_mins;
  
  protected void btnOkOnClick()
  {
    this.hour = ((byte)this.st_hours.getCurrentItem());
    this.min = ((byte)this.st_mins.getCurrentItem());
    MsgSender.Send(CmdMake.KO_WF_12BATT_SCH_SP(this.hour, this.min, this.onoff));
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
        BxAssistCharge.this.btnOkOnClick();
      }
    });
    SetMainView(new viewAssistCharge(this));
    SetTitleText(getString(2131165414));
  }
  
  class viewAssistCharge
    extends myMainView
  {
    private static final int MaxValue_Hour = 23;
    private static final int MaxValue_Minute = 5;
    private NumericWheelAdapter NWA_Used_stHour;
    private NumericWheelAdapter NWA_Used_stMin;
    ImageButton imgshelter = (ImageButton)this.mView.findViewById(2131296278);
    InputMethodManager imm;
    private boolean mbUsed = false;
    mySwitch myCheck = (mySwitch)this.mView.findViewById(2131296268);
    private myTimerWheelAdapter myTWA_notUsed_stHour;
    private myTimerWheelAdapter myTWA_notUsed_stMin;
    boolean swChanged = false;
    
    public viewAssistCharge(myActivity parammyActivity)
    {
      super(2130903044);
      this.myCheck.SetState(false);
      if (this.myCheck != null) {
        this.myCheck.SetOnSwitchStateChanged(new mySwitch.OnSwitchStateChanged()
        {
          public void onswitchclose()
          {
            boolean bool = true;
            BxAssistCharge.viewAssistCharge.this.imgshelter.setVisibility(0);
            BxAssistCharge.this.onoff = 0;
            BxAssistCharge.viewAssistCharge localviewAssistCharge = BxAssistCharge.viewAssistCharge.this;
            if (DataGetter.g_datas((short)142) == 1) {}
            for (;;)
            {
              localviewAssistCharge.swChanged = bool;
              BxAssistCharge.this.btnOk.setEnabled(BxAssistCharge.viewAssistCharge.this.swChanged);
              return;
              bool = false;
            }
          }
          
          public void onswitchopen()
          {
            boolean bool = true;
            BxAssistCharge.viewAssistCharge.this.imgshelter.setVisibility(4);
            BxAssistCharge.this.onoff = 1;
            BxAssistCharge.viewAssistCharge localviewAssistCharge = BxAssistCharge.viewAssistCharge.this;
            if (DataGetter.g_datas((short)142) == 0) {}
            for (;;)
            {
              localviewAssistCharge.swChanged = bool;
              BxAssistCharge.this.btnOk.setEnabled(BxAssistCharge.viewAssistCharge.this.swChanged);
              return;
              bool = false;
            }
          }
        });
      }
      initWheels();
      loadDatas();
    }
    
    private void doTimeTest() {}
    
    private void initWheels()
    {
      this.myTWA_notUsed_stHour = new myTimerWheelAdapter(this.mAct, 23);
      this.myTWA_notUsed_stMin = new myTimerWheelAdapter(this.mAct, 5, "%d0");
      this.NWA_Used_stHour = new NumericWheelAdapter(this.mAct, 0, 23);
      this.NWA_Used_stMin = new NumericWheelAdapter(this.mAct, 0, 5, "%d0");
      BxAssistCharge.this.st_hours = ((WheelView)this.mView.findViewById(2131296276));
      BxAssistCharge.this.st_hours.setCyclic(true);
      BxAssistCharge.this.st_mins = ((WheelView)this.mView.findViewById(2131296277));
      BxAssistCharge.this.st_mins.setCyclic(true);
      Object localObject = new OnWheelClickedListener()
      {
        public void onItemClicked(WheelView paramAnonymousWheelView, int paramAnonymousInt)
        {
          paramAnonymousWheelView.setCurrentItem(paramAnonymousInt, true);
        }
      };
      BxAssistCharge.this.st_hours.addClickingListener((OnWheelClickedListener)localObject);
      BxAssistCharge.this.st_mins.addClickingListener((OnWheelClickedListener)localObject);
      localObject = new OnWheelScrollListener()
      {
        public void onScrollingFinished(WheelView paramAnonymousWheelView)
        {
          BxAssistCharge.viewAssistCharge.this.markWheelsUsed();
          BxAssistCharge.viewAssistCharge.this.doTimeTest();
          int k = DataGetter.g_datas((short)140);
          int i = DataGetter.g_datas((short)141);
          int j = BxAssistCharge.this.st_hours.getCurrentItem();
          int m = BxAssistCharge.this.st_mins.getCurrentItem();
          paramAnonymousWheelView = BxAssistCharge.this.btnOk;
          if ((k == j) && (i == m) && (!BxAssistCharge.viewAssistCharge.this.swChanged)) {}
          for (boolean bool = false;; bool = true)
          {
            paramAnonymousWheelView.setEnabled(bool);
            return;
          }
        }
        
        public void onScrollingStarted(WheelView paramAnonymousWheelView) {}
      };
      BxAssistCharge.this.st_hours.addScrollingListener((OnWheelScrollListener)localObject);
      BxAssistCharge.this.st_mins.addScrollingListener((OnWheelScrollListener)localObject);
    }
    
    private void markWheelsUsed()
    {
      if (this.mbUsed) {}
      for (;;)
      {
        return;
        this.mbUsed = true;
        int i = BxAssistCharge.this.st_hours.getCurrentItem();
        int j = BxAssistCharge.this.st_mins.getCurrentItem();
        BxAssistCharge.this.st_hours.setViewAdapter(this.NWA_Used_stHour);
        BxAssistCharge.this.st_mins.setViewAdapter(this.NWA_Used_stMin);
        if (i == 0) {
          BxAssistCharge.this.st_hours.setCurrentItem(0);
        }
        for (;;)
        {
          if (j != 0) {
            break label112;
          }
          BxAssistCharge.this.st_mins.setCurrentItem(0);
          break;
          BxAssistCharge.this.st_hours.setCurrentItem(i - 1);
        }
        label112:
        BxAssistCharge.this.st_mins.setCurrentItem(j - 1);
      }
    }
    
    private void resetWheels()
    {
      BxAssistCharge.this.st_hours.setViewAdapter(this.myTWA_notUsed_stHour);
      BxAssistCharge.this.st_mins.setViewAdapter(this.myTWA_notUsed_stMin);
      BxAssistCharge.this.st_hours.setCurrentItem(0);
      BxAssistCharge.this.st_mins.setCurrentItem(0);
      this.mbUsed = false;
    }
    
    private void setWheels()
    {
      if (!this.mbUsed) {
        markWheelsUsed();
      }
      BxAssistCharge.this.st_hours.setCurrentItem(BxAssistCharge.this.hour);
      BxAssistCharge.this.st_mins.setCurrentItem(BxAssistCharge.this.min);
    }
    
    public void Refresh()
    {
      loadDatas();
    }
    
    boolean isTimeRight()
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (BxAssistCharge.this.hour >= 0)
      {
        if (BxAssistCharge.this.hour <= 23) {
          break label30;
        }
        bool1 = bool2;
      }
      for (;;)
      {
        return bool1;
        label30:
        bool1 = bool2;
        if (BxAssistCharge.this.min >= 0)
        {
          bool1 = bool2;
          if (BxAssistCharge.this.min <= 5) {
            bool1 = true;
          }
        }
      }
    }
    
    void loadDatas()
    {
      boolean bool = true;
      BxAssistCharge.this.hour = DataGetter.g_datas((short)140);
      BxAssistCharge.this.min = DataGetter.g_datas((short)141);
      BxAssistCharge.this.onoff = DataGetter.g_datas((short)142);
      refreshWheel();
      mySwitch localmySwitch = this.myCheck;
      if (BxAssistCharge.this.onoff == 1) {}
      for (;;)
      {
        localmySwitch.SetState(bool);
        BxAssistCharge.this.btnOk.setEnabled(false);
        return;
        bool = false;
      }
    }
    
    void refreshWheel()
    {
      if (!isTimeRight()) {
        resetWheels();
      }
      for (;;)
      {
        return;
        setWheels();
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\BxAssistCharge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */