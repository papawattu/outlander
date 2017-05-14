package com.inventec.iMobile;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.OnButtonListcloseListener;
import com.inventec.iMobile.baseClass.TimerMenuList;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myAlertDlg;
import com.inventec.iMobile.baseClass.myAlertDlg.CloseCallback;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseClass.mySwitch;
import com.inventec.iMobile.baseClass.mySwitch.OnSwitchStateChanged;
import com.inventec.iMobile.baseOP.DataGetter;
import com.inventec.iMobile.baseOP.DealFile;
import com.inventec.iMobile.baseOP.MsgSender;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.baseStruct.DefMsg;

public class FrmSetting_Charge
  extends myActivity
{
  public static String fnsav = "setCHG.sav";
  byte bkchgMode;
  byte bkfullCharge;
  byte[] bkpresetFH = new byte[2];
  byte[] bkpresetFM = new byte[2];
  byte[] bkpresetMode = new byte[2];
  byte[] bkpresetSH = new byte[2];
  byte[] bkpresetSM = new byte[2];
  byte[] bktimeFH = new byte[5];
  byte[] bktimeFM = new byte[3];
  byte[] bktimeSH = new byte[5];
  byte[] bktimeSM = new byte[3];
  private MyButton btnSend;
  private MyButton btnSet;
  byte chgMode;
  boolean flagListSetChange = false;
  byte fullCharge;
  byte[] presetFH = new byte[2];
  byte[] presetFM = new byte[2];
  byte[] presetMode = new byte[2];
  byte[] presetSH = new byte[2];
  byte[] presetSM = new byte[2];
  byte[] timeFH = new byte[5];
  byte[] timeFM = new byte[3];
  byte[] timeSH = new byte[5];
  byte[] timeSM = new byte[3];
  
  public void SetBtnBarEnable(boolean paramBoolean)
  {
    this.btnSend.setEnabled(paramBoolean);
  }
  
  protected void btnSendClick()
  {
    MvSettingCharge localMvSettingCharge = (MvSettingCharge)this.mCurMainView;
    if (localMvSettingCharge != null) {
      localMvSettingCharge.btnSendClick();
    }
  }
  
  protected void btnSetOnClick()
  {
    MvSettingCharge localMvSettingCharge = (MvSettingCharge)this.mCurMainView;
    if (localMvSettingCharge != null) {
      localMvSettingCharge.btnSetOnClick();
    }
  }
  
  public boolean canShowNavi()
  {
    return true;
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramIntent != null)
    {
      byte[] arrayOfByte = paramIntent.getByteArrayExtra("presetSH");
      if ((arrayOfByte != null) && (arrayOfByte.length == 2))
      {
        this.presetSH[0] = arrayOfByte[0];
        this.presetSH[1] = arrayOfByte[1];
      }
      arrayOfByte = paramIntent.getByteArrayExtra("presetSM");
      if ((arrayOfByte != null) && (arrayOfByte.length == 2))
      {
        this.presetSM[0] = arrayOfByte[0];
        this.presetSM[1] = arrayOfByte[1];
      }
      arrayOfByte = paramIntent.getByteArrayExtra("presetFH");
      if ((arrayOfByte != null) && (arrayOfByte.length == 2))
      {
        this.presetFH[0] = arrayOfByte[0];
        this.presetFH[1] = arrayOfByte[1];
      }
      arrayOfByte = paramIntent.getByteArrayExtra("presetFM");
      if ((arrayOfByte != null) && (arrayOfByte.length == 2))
      {
        this.presetFM[0] = arrayOfByte[0];
        this.presetFM[1] = arrayOfByte[1];
      }
      this.chgMode = paramIntent.getByteExtra("chgMode", this.chgMode);
    }
  }
  
  public void onBackPressed()
  {
    if (((MvSettingCharge)this.mCurMainView != null) && (setSentEable()))
    {
      myAlertDlg localmyAlertDlg = new myAlertDlg(this);
      localmyAlertDlg.setDlgContent(0, 2131165370, false);
      localmyAlertDlg.setOkCallback(new myAlertDlg.CloseCallback()
      {
        public void DoAction()
        {
          FrmSetting_Charge.this.ShowNavi();
        }
      });
      localmyAlertDlg.setCancelCallback(new myAlertDlg.CloseCallback()
      {
        public void DoAction()
        {
          FrmSetting_Charge.this.finish();
        }
      });
      localmyAlertDlg.show();
    }
    for (;;)
    {
      return;
      super.onBackPressed();
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = (LinearLayout)SetBtnBar(2130903087, 50);
    this.btnSet = ((MyButton)paramBundle.findViewById(2131296606));
    this.btnSet.setText(2131165353);
    this.btnSend = ((MyButton)paramBundle.findViewById(2131296607));
    this.btnSet.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FrmSetting_Charge.this.btnSetOnClick();
      }
    });
    this.btnSend.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FrmSetting_Charge.this.btnSendClick();
      }
    });
    SetMainView(new MvSettingCharge(this));
  }
  
  protected void onPause()
  {
    MvSettingCharge localMvSettingCharge = (MvSettingCharge)this.mCurMainView;
    if (localMvSettingCharge != null) {
      localMvSettingCharge.saveAll();
    }
    super.onPause();
  }
  
  protected void onResume()
  {
    MvSettingCharge localMvSettingCharge = (MvSettingCharge)this.mCurMainView;
    if (localMvSettingCharge != null) {
      localMvSettingCharge.loadAll();
    }
    super.onResume();
    if (myActivity.backTop) {
      onBackPressed();
    }
    for (;;)
    {
      return;
      ShowNavi();
    }
  }
  
  public boolean setSentEable()
  {
    boolean bool2 = true;
    boolean bool1;
    if (this.flagListSetChange) {
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      bool1 = bool2;
      if (DefMsg.memcmp(this.timeSH, 0, this.bktimeSH, 0, 5))
      {
        bool1 = bool2;
        if (DefMsg.memcmp(this.timeSM, 0, this.bktimeSM, 0, 3))
        {
          bool1 = bool2;
          if (DefMsg.memcmp(this.timeFH, 0, this.bktimeFH, 0, 5))
          {
            bool1 = bool2;
            if (DefMsg.memcmp(this.timeFM, 0, this.bktimeFM, 0, 3))
            {
              bool1 = bool2;
              if (DefMsg.memcmp(this.presetMode, 0, this.bkpresetMode, 0, 2))
              {
                bool1 = bool2;
                if (DefMsg.memcmp(this.presetSH, 0, this.bkpresetSH, 0, 2))
                {
                  bool1 = bool2;
                  if (DefMsg.memcmp(this.presetSM, 0, this.bkpresetSM, 0, 2))
                  {
                    bool1 = bool2;
                    if (DefMsg.memcmp(this.presetFH, 0, this.bkpresetFH, 0, 2))
                    {
                      bool1 = bool2;
                      if (DefMsg.memcmp(this.presetFM, 0, this.bkpresetFM, 0, 2))
                      {
                        bool1 = bool2;
                        if (this.chgMode == this.bkchgMode)
                        {
                          bool1 = bool2;
                          if (this.fullCharge == this.bkfullCharge) {
                            bool1 = false;
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  class MvSettingCharge
    extends myMainView
  {
    int[][] Preset = { new int[4], new int[4], new int[4] };
    int[] TimerNum = new int[7];
    buttonlistlistener bll = new buttonlistlistener();
    private String[] dataPreset = { "", "", "", "", "", "", "" };
    int[][] daySet = { new int[4], new int[4], new int[4], new int[4], new int[4], new int[4], new int[4], new int[4] };
    AutoResizeTextView duration_text1 = (AutoResizeTextView)this.mView.findViewById(2131296494);
    AutoResizeTextView duration_text2 = (AutoResizeTextView)this.mView.findViewById(2131296496);
    AutoResizeTextView duration_text3 = (AutoResizeTextView)this.mView.findViewById(2131296498);
    AutoResizeTextView duration_text4 = (AutoResizeTextView)this.mView.findViewById(2131296500);
    AutoResizeTextView duration_text5 = (AutoResizeTextView)this.mView.findViewById(2131296502);
    AutoResizeTextView duration_text6 = (AutoResizeTextView)this.mView.findViewById(2131296504);
    AutoResizeTextView duration_text7 = (AutoResizeTextView)this.mView.findViewById(2131296506);
    TextView fullChargSwitch_mask = (TextView)this.mView.findViewById(2131296544);
    ImageView fullCharge_frame = (ImageView)this.mView.findViewById(2131296534);
    mySwitch fullCharge_switch1 = (mySwitch)this.mView.findViewById(2131296537);
    mySwitch fullCharge_switch2 = (mySwitch)this.mView.findViewById(2131296538);
    mySwitch fullCharge_switch3 = (mySwitch)this.mView.findViewById(2131296539);
    mySwitch fullCharge_switch4 = (mySwitch)this.mView.findViewById(2131296540);
    mySwitch fullCharge_switch5 = (mySwitch)this.mView.findViewById(2131296541);
    mySwitch fullCharge_switch6 = (mySwitch)this.mView.findViewById(2131296542);
    mySwitch fullCharge_switch7 = (mySwitch)this.mView.findViewById(2131296543);
    AutoResizeTextView fullCharge_title = (AutoResizeTextView)this.mView.findViewById(2131296536);
    private boolean[] preSwitch = new boolean[7];
    int[][] set = { new int[4], new int[4], new int[4] };
    boolean set1_flag = false;
    boolean set2_flag = false;
    boolean set3_flag = false;
    mySwitch.OnSwitchStateChanged swChgTst = new mySwitch.OnSwitchStateChanged()
    {
      public void onswitchclose()
      {
        FrmSetting_Charge.MvSettingCharge.this.setSW();
        FrmSetting_Charge.this.SetBtnBarEnable(FrmSetting_Charge.this.setSentEable());
      }
      
      public void onswitchopen()
      {
        FrmSetting_Charge.MvSettingCharge.this.setSW();
        FrmSetting_Charge.this.SetBtnBarEnable(FrmSetting_Charge.this.setSentEable());
      }
    };
    TextView timerSelector_mask = (TextView)this.mView.findViewById(2131296508);
    TimerMenuList timer_list1 = (TimerMenuList)this.mView.findViewById(2131296510);
    TimerMenuList timer_list2 = (TimerMenuList)this.mView.findViewById(2131296512);
    TimerMenuList timer_list3 = (TimerMenuList)this.mView.findViewById(2131296514);
    TimerMenuList timer_list4 = (TimerMenuList)this.mView.findViewById(2131296516);
    TimerMenuList timer_list5 = (TimerMenuList)this.mView.findViewById(2131296518);
    TimerMenuList timer_list6 = (TimerMenuList)this.mView.findViewById(2131296520);
    TimerMenuList timer_list7 = (TimerMenuList)this.mView.findViewById(2131296522);
    AutoResizeTextView timer_selector1 = (AutoResizeTextView)this.mView.findViewById(2131296495);
    AutoResizeTextView timer_selector2 = (AutoResizeTextView)this.mView.findViewById(2131296497);
    AutoResizeTextView timer_selector3 = (AutoResizeTextView)this.mView.findViewById(2131296499);
    AutoResizeTextView timer_selector4 = (AutoResizeTextView)this.mView.findViewById(2131296501);
    AutoResizeTextView timer_selector5 = (AutoResizeTextView)this.mView.findViewById(2131296503);
    AutoResizeTextView timer_selector6 = (AutoResizeTextView)this.mView.findViewById(2131296505);
    AutoResizeTextView timer_selector7 = (AutoResizeTextView)this.mView.findViewById(2131296507);
    TextView weeday1 = (TextView)this.mView.findViewById(2131296485);
    TextView weeday2 = (TextView)this.mView.findViewById(2131296486);
    TextView weeday3 = (TextView)this.mView.findViewById(2131296487);
    TextView weeday4 = (TextView)this.mView.findViewById(2131296488);
    TextView weeday5 = (TextView)this.mView.findViewById(2131296489);
    TextView weeday6 = (TextView)this.mView.findViewById(2131296490);
    TextView weeday7 = (TextView)this.mView.findViewById(2131296491);
    LinearLayout weekDayName = (LinearLayout)this.mView.findViewById(2131296484);
    TextView weekdayMask = (TextView)this.mView.findViewById(2131296492);
    
    public MvSettingCharge(myActivity parammyActivity)
    {
      super(2130903077);
      this.fullCharge_switch1.SetOnSwitchStateChanged(this.swChgTst);
      this.fullCharge_switch2.SetOnSwitchStateChanged(this.swChgTst);
      this.fullCharge_switch3.SetOnSwitchStateChanged(this.swChgTst);
      this.fullCharge_switch4.SetOnSwitchStateChanged(this.swChgTst);
      this.fullCharge_switch5.SetOnSwitchStateChanged(this.swChgTst);
      this.fullCharge_switch6.SetOnSwitchStateChanged(this.swChgTst);
      this.fullCharge_switch7.SetOnSwitchStateChanged(this.swChgTst);
      this.timer_selector1.setColors(MyButton.colNor, -1);
      this.timer_selector2.setColors(MyButton.colNor, -1);
      this.timer_selector3.setColors(MyButton.colNor, -1);
      this.timer_selector4.setColors(MyButton.colNor, -1);
      this.timer_selector5.setColors(MyButton.colNor, -1);
      this.timer_selector6.setColors(MyButton.colNor, -1);
      this.timer_selector7.setColors(MyButton.colNor, -1);
      this.timer_selector1.setSingle();
      this.timer_selector2.setSingle();
      this.timer_selector3.setSingle();
      this.timer_selector4.setSingle();
      this.timer_selector5.setSingle();
      this.timer_selector6.setSingle();
      this.timer_selector7.setSingle();
      this.fullCharge_title.setSingle();
      FrmSetting_Charge.this.flagListSetChange = true;
      this.duration_text1.setSingle();
      this.duration_text2.setSingle();
      this.duration_text3.setSingle();
      this.duration_text4.setSingle();
      this.duration_text5.setSingle();
      this.duration_text6.setSingle();
      this.duration_text7.setSingle();
      this.timer_list5.listDirecton(false);
      this.timer_list6.listDirecton(false);
      this.timer_list7.listDirecton(false);
      this.timer_list1.setVisibility(8);
      this.timer_list2.setVisibility(8);
      this.timer_list3.setVisibility(8);
      this.timer_list4.setVisibility(8);
      this.timer_list5.setVisibility(8);
      this.timer_list6.setVisibility(8);
      this.timer_list7.setVisibility(8);
      this.timer_list1.setOnbuttonlistlistener(this.bll);
      this.timer_list2.setOnbuttonlistlistener(this.bll);
      this.timer_list3.setOnbuttonlistlistener(this.bll);
      this.timer_list4.setOnbuttonlistlistener(this.bll);
      this.timer_list5.setOnbuttonlistlistener(this.bll);
      this.timer_list6.setOnbuttonlistlistener(this.bll);
      this.timer_list7.setOnbuttonlistlistener(this.bll);
      this.weekdayMask.setVisibility(8);
      this.weekdayMask.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          return true;
        }
      });
      this.timerSelector_mask.setVisibility(8);
      this.timerSelector_mask.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          return true;
        }
      });
      this.fullChargSwitch_mask.setVisibility(8);
      this.fullChargSwitch_mask.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          return true;
        }
      });
      this.timer_selector1.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (paramAnonymousMotionEvent.getAction() == 0) {
            FrmSetting_Charge.MvSettingCharge.this.timer_selector1.setBackgroundResource(2130837629);
          }
          for (;;)
          {
            return true;
            if (paramAnonymousMotionEvent.getAction() == 1)
            {
              FrmSetting_Charge.MvSettingCharge.this.timer_selector1.setBackgroundResource(2130837628);
              FrmSetting_Charge.MvSettingCharge.this.timer_list1.setVisibility(0);
              FrmSetting_Charge.MvSettingCharge.this.showMask(true);
            }
          }
        }
      });
      this.timer_selector2.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (paramAnonymousMotionEvent.getAction() == 0) {
            FrmSetting_Charge.MvSettingCharge.this.timer_selector2.setBackgroundResource(2130837629);
          }
          for (;;)
          {
            return true;
            if (paramAnonymousMotionEvent.getAction() == 1)
            {
              FrmSetting_Charge.MvSettingCharge.this.timer_list2.setVisibility(0);
              FrmSetting_Charge.MvSettingCharge.this.timer_selector2.setBackgroundResource(2130837628);
              FrmSetting_Charge.MvSettingCharge.this.showMask(true);
            }
          }
        }
      });
      this.timer_selector3.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (paramAnonymousMotionEvent.getAction() == 0) {
            FrmSetting_Charge.MvSettingCharge.this.timer_selector3.setBackgroundResource(2130837629);
          }
          for (;;)
          {
            return true;
            if (paramAnonymousMotionEvent.getAction() == 1)
            {
              FrmSetting_Charge.MvSettingCharge.this.timer_list3.setVisibility(0);
              FrmSetting_Charge.MvSettingCharge.this.timer_selector3.setBackgroundResource(2130837628);
              FrmSetting_Charge.MvSettingCharge.this.showMask(true);
            }
          }
        }
      });
      this.timer_selector4.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (paramAnonymousMotionEvent.getAction() == 0) {
            FrmSetting_Charge.MvSettingCharge.this.timer_selector4.setBackgroundResource(2130837629);
          }
          for (;;)
          {
            return true;
            if (paramAnonymousMotionEvent.getAction() == 1)
            {
              FrmSetting_Charge.MvSettingCharge.this.timer_list4.setVisibility(0);
              FrmSetting_Charge.MvSettingCharge.this.timer_selector4.setBackgroundResource(2130837628);
              FrmSetting_Charge.MvSettingCharge.this.showMask(true);
            }
          }
        }
      });
      this.timer_selector5.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (paramAnonymousMotionEvent.getAction() == 0) {
            FrmSetting_Charge.MvSettingCharge.this.timer_selector5.setBackgroundResource(2130837629);
          }
          for (;;)
          {
            return true;
            if (paramAnonymousMotionEvent.getAction() == 1)
            {
              FrmSetting_Charge.MvSettingCharge.this.timer_list5.setVisibility(0);
              FrmSetting_Charge.MvSettingCharge.this.timer_selector5.setBackgroundResource(2130837628);
              FrmSetting_Charge.MvSettingCharge.this.showMask(true);
            }
          }
        }
      });
      this.timer_selector6.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (paramAnonymousMotionEvent.getAction() == 0) {
            FrmSetting_Charge.MvSettingCharge.this.timer_selector6.setBackgroundResource(2130837629);
          }
          for (;;)
          {
            return true;
            if (paramAnonymousMotionEvent.getAction() == 1)
            {
              FrmSetting_Charge.MvSettingCharge.this.timer_list6.setVisibility(0);
              FrmSetting_Charge.MvSettingCharge.this.timer_selector6.setBackgroundResource(2130837628);
              FrmSetting_Charge.MvSettingCharge.this.showMask(true);
            }
          }
        }
      });
      this.timer_selector7.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if (paramAnonymousMotionEvent.getAction() == 0) {
            FrmSetting_Charge.MvSettingCharge.this.timer_selector7.setBackgroundResource(2130837629);
          }
          for (;;)
          {
            return true;
            if (paramAnonymousMotionEvent.getAction() == 1)
            {
              FrmSetting_Charge.MvSettingCharge.this.timer_list7.setVisibility(0);
              FrmSetting_Charge.MvSettingCharge.this.timer_selector7.setBackgroundResource(2130837628);
              FrmSetting_Charge.MvSettingCharge.this.showMask(true);
            }
          }
        }
      });
      setAutosizeView();
      FrmSetting_Charge.this.SetTitleText(FrmSetting_Charge.this.getString(2131165337));
      getPreDatas();
    }
    
    public byte[] KO_WF_TM_CHG_SP()
    {
      byte[] arrayOfByte = new byte[33];
      arrayOfByte[0] = -10;
      arrayOfByte[1] = 31;
      arrayOfByte[2] = 0;
      arrayOfByte[3] = 1;
      arrayOfByte[4] = FrmSetting_Charge.this.presetMode[0];
      arrayOfByte[5] = FrmSetting_Charge.this.presetMode[1];
      arrayOfByte[6] = FrmSetting_Charge.this.fullCharge;
      arrayOfByte[7] = FrmSetting_Charge.this.presetSH[0];
      arrayOfByte[8] = FrmSetting_Charge.this.presetSH[1];
      arrayOfByte[9] = FrmSetting_Charge.this.presetSM[0];
      arrayOfByte[10] = FrmSetting_Charge.this.presetSM[1];
      arrayOfByte[11] = FrmSetting_Charge.this.presetFH[0];
      arrayOfByte[12] = FrmSetting_Charge.this.presetFH[1];
      arrayOfByte[13] = FrmSetting_Charge.this.presetFM[0];
      arrayOfByte[14] = FrmSetting_Charge.this.presetFM[1];
      int j = 0;
      int i = 0;
      if (i >= 7)
      {
        i = j;
        label174:
        if (i == 0) {
          break label502;
        }
        arrayOfByte[15] = FrmSetting_Charge.this.chgMode;
        if (arrayOfByte[15] < 2) {
          arrayOfByte[15] = DefMsg.g_recdatas[97];
        }
      }
      for (;;)
      {
        arrayOfByte[16] = FrmSetting_Charge.this.timeSH[0];
        arrayOfByte[17] = FrmSetting_Charge.this.timeSH[1];
        arrayOfByte[18] = FrmSetting_Charge.this.timeSH[2];
        arrayOfByte[19] = FrmSetting_Charge.this.timeSH[3];
        arrayOfByte[20] = FrmSetting_Charge.this.timeSH[4];
        arrayOfByte[21] = FrmSetting_Charge.this.timeSM[0];
        arrayOfByte[22] = FrmSetting_Charge.this.timeSM[1];
        arrayOfByte[23] = FrmSetting_Charge.this.timeSM[2];
        arrayOfByte[24] = FrmSetting_Charge.this.timeFH[0];
        arrayOfByte[25] = FrmSetting_Charge.this.timeFH[1];
        arrayOfByte[26] = FrmSetting_Charge.this.timeFH[2];
        arrayOfByte[27] = FrmSetting_Charge.this.timeFH[3];
        arrayOfByte[28] = FrmSetting_Charge.this.timeFH[4];
        arrayOfByte[29] = FrmSetting_Charge.this.timeFM[0];
        arrayOfByte[30] = FrmSetting_Charge.this.timeFM[1];
        arrayOfByte[31] = FrmSetting_Charge.this.timeFM[2];
        arrayOfByte[32] = DefMsg.calcCheckSum(arrayOfByte);
        return arrayOfByte;
        int m = this.daySet[i][0];
        int n = this.daySet[i][1];
        int i1 = this.daySet[i][2];
        int k = this.daySet[i][3];
        if ((m <= 23) && (n <= 5) && (i1 <= 23) && (k <= 5))
        {
          i = 1;
          break label174;
        }
        i++;
        break;
        label502:
        arrayOfByte[15] = 1;
      }
    }
    
    public void PopupHelpInfo()
    {
      Intent localIntent = new Intent(this.mAct, BxShowHelp.class);
      Bundle localBundle = new Bundle();
      localBundle.putInt("CardNo", 21);
      localIntent.putExtras(localBundle);
      this.mAct.startActivityForResult(localIntent, 101);
    }
    
    public void Refresh()
    {
      resetScreen();
    }
    
    public void Update()
    {
      getPreDatas();
      Refresh();
    }
    
    protected void btnSendClick()
    {
      int j = 1;
      int m = 0;
      byte[] arrayOfByte1;
      byte[] arrayOfByte2;
      if (FrmSetting_Charge.this.setSentEable())
      {
        arrayOfByte1 = KO_WF_TM_CHG_SP();
        DefMsg.memcpy(DefMsg.g_CHGSendBuf, 0, arrayOfByte1, 16, 16);
        MsgSender.Send(arrayOfByte1);
        if (DealMsg.g_isDemo)
        {
          if (arrayOfByte1[15] == 1) {
            break label206;
          }
          byte[] arrayOfByte3 = DefMsg.g_datas;
          arrayOfByte2 = DefMsg.g_recdatas;
          int i = arrayOfByte1[15];
          arrayOfByte2[96] = i;
          arrayOfByte3[96] = i;
          arrayOfByte2 = DefMsg.g_recdatas;
          if (j == 0) {
            break label212;
          }
          k = 150;
          label91:
          arrayOfByte2[106] = ((byte)k);
          arrayOfByte2 = DefMsg.g_recdatas;
          if (j == 0) {
            break label219;
          }
          k = 0;
          label110:
          arrayOfByte2[107] = ((byte)k);
          arrayOfByte2 = DefMsg.g_recdatas;
          if (j == 0) {
            break label226;
          }
        }
      }
      label206:
      label212:
      label219:
      label226:
      for (int k = 2;; k = 0)
      {
        arrayOfByte2[103] = ((byte)k);
        arrayOfByte2 = DefMsg.g_recdatas;
        k = m;
        if (j != 0) {
          k = 2;
        }
        arrayOfByte2[2] = ((byte)k);
        DefMsg.g_recdatas[20] = arrayOfByte1[6];
        DefMsg.memcpy(DefMsg.g_recdatas, 108, arrayOfByte1, 4, 2);
        DefMsg.memcpy(DefMsg.g_recdatas, 69, arrayOfByte1, 16, 16);
        DefMsg.memcpy(DefMsg.g_recdatas, 110, arrayOfByte1, 7, 8);
        return;
        j = 0;
        break;
        k = 112;
        break label91;
        k = 23;
        break label110;
      }
    }
    
    protected void btnSetOnClick()
    {
      Intent localIntent = new Intent(this.mAct, FrmSettingTime.class);
      Bundle localBundle = new Bundle();
      localBundle.putByteArray("presetSH", FrmSetting_Charge.this.presetSH);
      localBundle.putByteArray("presetSM", FrmSetting_Charge.this.presetSM);
      localBundle.putByteArray("presetFH", FrmSetting_Charge.this.presetFH);
      localBundle.putByteArray("presetFM", FrmSetting_Charge.this.presetFM);
      localBundle.putByte("chgMode", FrmSetting_Charge.this.chgMode);
      localIntent.putExtras(localBundle);
      this.mAct.startActivityForResult(localIntent, 1);
    }
    
    public void getPreDatas()
    {
      if ((DefMsg.memcmp(FrmSetting_Charge.this.bktimeSH, 0, DefMsg.g_datas, 69, 5)) && (DefMsg.memcmp(FrmSetting_Charge.this.bktimeSM, 0, DefMsg.g_datas, 74, 3)) && (DefMsg.memcmp(FrmSetting_Charge.this.bktimeFM, 0, DefMsg.g_datas, 77, 3)) && (DefMsg.memcmp(FrmSetting_Charge.this.bktimeFM, 0, DefMsg.g_datas, 82, 3)) && (DefMsg.memcmp(FrmSetting_Charge.this.bkpresetMode, 0, DefMsg.g_datas, 108, 2)) && (DefMsg.memcmp(FrmSetting_Charge.this.bkpresetSH, 0, DefMsg.g_datas, 110, 2)) && (DefMsg.memcmp(FrmSetting_Charge.this.bkpresetSM, 0, DefMsg.g_datas, 112, 2)) && (DefMsg.memcmp(FrmSetting_Charge.this.bkpresetFH, 0, DefMsg.g_datas, 114, 2)) && (DefMsg.memcmp(FrmSetting_Charge.this.bkpresetFM, 0, DefMsg.g_datas, 116, 2)) && (FrmSetting_Charge.this.bkchgMode == DataGetter.g_datas((short)96)) && (FrmSetting_Charge.this.bkfullCharge == DataGetter.g_datas((short)20))) {}
      for (;;)
      {
        return;
        DefMsg.memcpy(FrmSetting_Charge.this.bktimeSH, 0, DefMsg.g_datas, 69, 5);
        DefMsg.memcpy(FrmSetting_Charge.this.bktimeSM, 0, DefMsg.g_datas, 74, 3);
        DefMsg.memcpy(FrmSetting_Charge.this.bktimeFH, 0, DefMsg.g_datas, 77, 5);
        DefMsg.memcpy(FrmSetting_Charge.this.bktimeFM, 0, DefMsg.g_datas, 82, 3);
        DefMsg.memcpy(FrmSetting_Charge.this.bkpresetMode, 0, DefMsg.g_datas, 108, 2);
        DefMsg.memcpy(FrmSetting_Charge.this.bkpresetSH, 0, DefMsg.g_datas, 110, 2);
        DefMsg.memcpy(FrmSetting_Charge.this.bkpresetSM, 0, DefMsg.g_datas, 112, 2);
        DefMsg.memcpy(FrmSetting_Charge.this.bkpresetFH, 0, DefMsg.g_datas, 114, 2);
        DefMsg.memcpy(FrmSetting_Charge.this.bkpresetFM, 0, DefMsg.g_datas, 116, 2);
        FrmSetting_Charge.this.bkchgMode = DataGetter.g_datas((short)96);
        FrmSetting_Charge.this.bkfullCharge = DataGetter.g_datas((short)20);
        DefMsg.memcpy(FrmSetting_Charge.this.timeSH, 0, FrmSetting_Charge.this.bktimeSH, 0, 5);
        DefMsg.memcpy(FrmSetting_Charge.this.timeSM, 0, FrmSetting_Charge.this.bktimeSM, 0, 3);
        DefMsg.memcpy(FrmSetting_Charge.this.timeFH, 0, FrmSetting_Charge.this.bktimeFH, 0, 5);
        DefMsg.memcpy(FrmSetting_Charge.this.timeFM, 0, FrmSetting_Charge.this.bktimeFM, 0, 3);
        DefMsg.memcpy(FrmSetting_Charge.this.presetMode, 0, FrmSetting_Charge.this.bkpresetMode, 0, 2);
        DefMsg.memcpy(FrmSetting_Charge.this.presetSH, 0, FrmSetting_Charge.this.bkpresetSH, 0, 2);
        DefMsg.memcpy(FrmSetting_Charge.this.presetSM, 0, FrmSetting_Charge.this.bkpresetSM, 0, 2);
        DefMsg.memcpy(FrmSetting_Charge.this.presetFH, 0, FrmSetting_Charge.this.bkpresetFH, 0, 2);
        DefMsg.memcpy(FrmSetting_Charge.this.presetFM, 0, FrmSetting_Charge.this.bkpresetFM, 0, 2);
        FrmSetting_Charge.this.chgMode = FrmSetting_Charge.this.bkchgMode;
        FrmSetting_Charge.this.fullCharge = FrmSetting_Charge.this.bkfullCharge;
        FrmSetting_Charge.this.showDemoBar();
        loadPreData(1, this.Preset[0]);
        loadPreData(2, this.Preset[1]);
        loadPreData(3, this.Preset[2]);
        setSwitchState();
      }
    }
    
    boolean isTimeRight(int[] paramArrayOfInt)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (paramArrayOfInt[0] >= 0)
      {
        if (paramArrayOfInt[0] <= 23) {
          break label22;
        }
        bool1 = bool2;
      }
      for (;;)
      {
        return bool1;
        label22:
        bool1 = bool2;
        if (paramArrayOfInt[1] >= 0)
        {
          bool1 = bool2;
          if (paramArrayOfInt[1] <= 5)
          {
            bool1 = bool2;
            if (paramArrayOfInt[2] >= 0)
            {
              bool1 = bool2;
              if (paramArrayOfInt[2] <= 23)
              {
                bool1 = bool2;
                if (paramArrayOfInt[3] >= 0)
                {
                  bool1 = bool2;
                  if (paramArrayOfInt[3] <= 5) {
                    if (paramArrayOfInt[0] == paramArrayOfInt[2])
                    {
                      bool1 = bool2;
                      if (paramArrayOfInt[1] == paramArrayOfInt[3]) {}
                    }
                    else
                    {
                      bool1 = true;
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    
    public void loadAll()
    {
      byte[] arrayOfByte = new byte['Ĭ'];
      if (DealFile.loaddata(this.mAct, FrmSetting_Charge.fnsav, arrayOfByte)) {
        parseData(arrayOfByte);
      }
    }
    
    void loadData(int paramInt, int[] paramArrayOfInt)
    {
      paramArrayOfInt[0] = DealMsg.GetHValue(paramInt, FrmSetting_Charge.this.timeSH);
      paramArrayOfInt[1] = DealMsg.GetMValue(paramInt, FrmSetting_Charge.this.timeSM);
      paramArrayOfInt[2] = DealMsg.GetHValue(paramInt, FrmSetting_Charge.this.timeFH);
      paramArrayOfInt[3] = DealMsg.GetMValue(paramInt, FrmSetting_Charge.this.timeFM);
    }
    
    void loadPreData(int paramInt, int[] paramArrayOfInt)
    {
      paramArrayOfInt[0] = DealMsg.GetPmHValue(paramInt, FrmSetting_Charge.this.presetSH);
      paramArrayOfInt[1] = DealMsg.GetPmMValue(paramInt, FrmSetting_Charge.this.presetSM);
      paramArrayOfInt[2] = DealMsg.GetPmHValue(paramInt, FrmSetting_Charge.this.presetFH);
      paramArrayOfInt[3] = DealMsg.GetPmMValue(paramInt, FrmSetting_Charge.this.presetFM);
    }
    
    public byte[] makeData()
    {
      int j = 1;
      byte[] arrayOfByte = new byte['Ĭ'];
      DefMsg.memcpy(arrayOfByte, 0, FrmSetting_Charge.this.bktimeSH, 0, 5);
      DefMsg.memcpy(arrayOfByte, 5, FrmSetting_Charge.this.bktimeSM, 0, 3);
      DefMsg.memcpy(arrayOfByte, 8, FrmSetting_Charge.this.bktimeFH, 0, 5);
      DefMsg.memcpy(arrayOfByte, 13, FrmSetting_Charge.this.bktimeFM, 0, 3);
      DefMsg.memcpy(arrayOfByte, 16, FrmSetting_Charge.this.bkpresetMode, 0, 2);
      DefMsg.memcpy(arrayOfByte, 18, FrmSetting_Charge.this.bkpresetSH, 0, 2);
      DefMsg.memcpy(arrayOfByte, 20, FrmSetting_Charge.this.bkpresetSM, 0, 2);
      DefMsg.memcpy(arrayOfByte, 22, FrmSetting_Charge.this.bkpresetFH, 0, 2);
      DefMsg.memcpy(arrayOfByte, 24, FrmSetting_Charge.this.bkpresetFM, 0, 2);
      arrayOfByte[26] = FrmSetting_Charge.this.bkfullCharge;
      DefMsg.memcpy(arrayOfByte, 27, FrmSetting_Charge.this.timeSH, 0, 5);
      DefMsg.memcpy(arrayOfByte, 32, FrmSetting_Charge.this.timeSM, 0, 3);
      DefMsg.memcpy(arrayOfByte, 35, FrmSetting_Charge.this.timeFH, 0, 5);
      DefMsg.memcpy(arrayOfByte, 40, FrmSetting_Charge.this.timeFM, 0, 3);
      DefMsg.memcpy(arrayOfByte, 43, FrmSetting_Charge.this.presetMode, 0, 2);
      DefMsg.memcpy(arrayOfByte, 45, FrmSetting_Charge.this.presetSH, 0, 2);
      DefMsg.memcpy(arrayOfByte, 47, FrmSetting_Charge.this.presetSM, 0, 2);
      DefMsg.memcpy(arrayOfByte, 49, FrmSetting_Charge.this.presetFH, 0, 2);
      DefMsg.memcpy(arrayOfByte, 51, FrmSetting_Charge.this.presetFM, 0, 2);
      arrayOfByte[53] = FrmSetting_Charge.this.chgMode;
      arrayOfByte[54] = FrmSetting_Charge.this.fullCharge;
      if (FrmSetting_Charge.this.flagListSetChange)
      {
        i = 1;
        arrayOfByte[55] = ((byte)i);
        DealMsg.IA2ToB(arrayOfByte, 56, this.set, 3, 4);
        DealMsg.IA2ToB(arrayOfByte, 104, this.daySet, 7, 4);
        DealMsg.IA2ToB(arrayOfByte, 216, this.Preset, 3, 4);
        if (!this.set1_flag) {
          break label445;
        }
        i = 1;
        label374:
        arrayOfByte['Ĉ'] = ((byte)i);
        if (!this.set2_flag) {
          break label450;
        }
        i = 1;
        label390:
        arrayOfByte['ĉ'] = ((byte)i);
        if (!this.set3_flag) {
          break label455;
        }
      }
      label445:
      label450:
      label455:
      for (int i = j;; i = 0)
      {
        arrayOfByte['Ċ'] = ((byte)i);
        DealMsg.IAToB(arrayOfByte, 267, this.TimerNum, 7);
        arrayOfByte['ħ'] = FrmSetting_Charge.this.bkchgMode;
        return arrayOfByte;
        i = 0;
        break;
        i = 0;
        break label374;
        i = 0;
        break label390;
      }
    }
    
    public void parseData(byte[] paramArrayOfByte)
    {
      boolean bool2 = true;
      DefMsg.memcpy(FrmSetting_Charge.this.bktimeSH, 0, paramArrayOfByte, 0, 5);
      DefMsg.memcpy(FrmSetting_Charge.this.bktimeSM, 0, paramArrayOfByte, 5, 3);
      DefMsg.memcpy(FrmSetting_Charge.this.bktimeFH, 0, paramArrayOfByte, 8, 5);
      DefMsg.memcpy(FrmSetting_Charge.this.bktimeFM, 0, paramArrayOfByte, 13, 3);
      DefMsg.memcpy(FrmSetting_Charge.this.bkpresetMode, 0, paramArrayOfByte, 16, 2);
      DefMsg.memcpy(FrmSetting_Charge.this.bkpresetSH, 0, paramArrayOfByte, 18, 2);
      DefMsg.memcpy(FrmSetting_Charge.this.bkpresetSM, 0, paramArrayOfByte, 20, 2);
      DefMsg.memcpy(FrmSetting_Charge.this.bkpresetFH, 0, paramArrayOfByte, 22, 2);
      DefMsg.memcpy(FrmSetting_Charge.this.bkpresetFM, 0, paramArrayOfByte, 24, 2);
      FrmSetting_Charge.this.bkfullCharge = paramArrayOfByte[26];
      DefMsg.memcpy(FrmSetting_Charge.this.timeSH, 0, paramArrayOfByte, 27, 5);
      DefMsg.memcpy(FrmSetting_Charge.this.timeSM, 0, paramArrayOfByte, 32, 3);
      DefMsg.memcpy(FrmSetting_Charge.this.timeFH, 0, paramArrayOfByte, 35, 5);
      DefMsg.memcpy(FrmSetting_Charge.this.timeFM, 0, paramArrayOfByte, 40, 3);
      DefMsg.memcpy(FrmSetting_Charge.this.presetMode, 0, paramArrayOfByte, 43, 2);
      DefMsg.memcpy(FrmSetting_Charge.this.presetSH, 0, paramArrayOfByte, 45, 2);
      DefMsg.memcpy(FrmSetting_Charge.this.presetSM, 0, paramArrayOfByte, 47, 2);
      DefMsg.memcpy(FrmSetting_Charge.this.presetFH, 0, paramArrayOfByte, 49, 2);
      DefMsg.memcpy(FrmSetting_Charge.this.presetFM, 0, paramArrayOfByte, 51, 2);
      FrmSetting_Charge.this.chgMode = paramArrayOfByte[53];
      FrmSetting_Charge.this.fullCharge = paramArrayOfByte[54];
      FrmSetting_Charge localFrmSetting_Charge = FrmSetting_Charge.this;
      if (paramArrayOfByte[55] == 1)
      {
        bool1 = true;
        localFrmSetting_Charge.flagListSetChange = bool1;
        DealMsg.BToIA2(paramArrayOfByte, 56, this.set, 3, 4);
        DealMsg.BToIA2(paramArrayOfByte, 104, this.daySet, 7, 4);
        DealMsg.BToIA2(paramArrayOfByte, 216, this.Preset, 3, 4);
        if (paramArrayOfByte['Ĉ'] != 1) {
          break label446;
        }
        bool1 = true;
        label373:
        this.set1_flag = bool1;
        if (paramArrayOfByte['ĉ'] != 1) {
          break label451;
        }
        bool1 = true;
        label389:
        this.set2_flag = bool1;
        if (paramArrayOfByte['Ċ'] != 1) {
          break label456;
        }
      }
      label446:
      label451:
      label456:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        this.set3_flag = bool1;
        DealMsg.BToIA(paramArrayOfByte, 267, this.TimerNum, 7);
        FrmSetting_Charge.this.bkchgMode = paramArrayOfByte['ħ'];
        setSwitchState();
        return;
        bool1 = false;
        break;
        bool1 = false;
        break label373;
        bool1 = false;
        break label389;
      }
    }
    
    public void resetScreen()
    {
      loadPreData(1, this.set[0]);
      loadPreData(2, this.set[1]);
      loadPreData(3, this.set[2]);
      this.set1_flag = isTimeRight(this.set[0]);
      this.set2_flag = isTimeRight(this.set[1]);
      this.set3_flag = isTimeRight(this.set[2]);
      FrmSetting_Charge.this.flagListSetChange = false;
      int i = 0;
      if (i >= 3) {
        if (!FrmSetting_Charge.this.flagListSetChange) {}
      }
      for (i = 0;; i++)
      {
        if (i >= 7)
        {
          Resources localResources = this.mAct.getResources();
          String[] arrayOfString = new String[4];
          arrayOfString[0] = localResources.getString(2131165349);
          arrayOfString[1] = localResources.getString(2131165346);
          arrayOfString[2] = localResources.getString(2131165347);
          arrayOfString[3] = localResources.getString(2131165348);
          setPresetText(this.timer_selector1, arrayOfString[this.TimerNum[0]], this.TimerNum[0]);
          setPresetText(this.timer_selector2, arrayOfString[this.TimerNum[1]], this.TimerNum[1]);
          setPresetText(this.timer_selector3, arrayOfString[this.TimerNum[2]], this.TimerNum[2]);
          setPresetText(this.timer_selector4, arrayOfString[this.TimerNum[3]], this.TimerNum[3]);
          setPresetText(this.timer_selector5, arrayOfString[this.TimerNum[4]], this.TimerNum[4]);
          setPresetText(this.timer_selector6, arrayOfString[this.TimerNum[5]], this.TimerNum[5]);
          setPresetText(this.timer_selector7, arrayOfString[this.TimerNum[6]], this.TimerNum[6]);
          this.timer_list1.setBtnEnable(this.set1_flag, this.set2_flag, this.set3_flag, this.TimerNum[0]);
          this.timer_list2.setBtnEnable(this.set1_flag, this.set2_flag, this.set3_flag, this.TimerNum[1]);
          this.timer_list3.setBtnEnable(this.set1_flag, this.set2_flag, this.set3_flag, this.TimerNum[2]);
          this.timer_list4.setBtnEnable(this.set1_flag, this.set2_flag, this.set3_flag, this.TimerNum[3]);
          this.timer_list5.setBtnEnable(this.set1_flag, this.set2_flag, this.set3_flag, this.TimerNum[4]);
          this.timer_list6.setBtnEnable(this.set1_flag, this.set2_flag, this.set3_flag, this.TimerNum[5]);
          this.timer_list7.setBtnEnable(this.set1_flag, this.set2_flag, this.set3_flag, this.TimerNum[6]);
          FrmSetting_Charge.this.SetBtnBarEnable(FrmSetting_Charge.this.setSentEable());
          return;
          for (j = 0;; j++)
          {
            if (j >= 4)
            {
              i++;
              break;
            }
            if (this.Preset[i][j] != this.set[i][j]) {
              FrmSetting_Charge.this.flagListSetChange = true;
            }
          }
        }
        int j = this.TimerNum[i];
        if (j > 0) {
          setDay(i, j);
        }
      }
    }
    
    public void saveAll()
    {
      DealFile.savedata(this.mAct, FrmSetting_Charge.fnsav, makeData());
    }
    
    public void setAutosizeView()
    {
      int m = this.mAct.getValidScreenHeight();
      int j = 0;
      View[] arrayOfView1 = new View[43];
      arrayOfView1[0] = this.weekDayName;
      arrayOfView1[1] = this.weeday1;
      arrayOfView1[2] = this.weeday2;
      arrayOfView1[3] = this.weeday3;
      arrayOfView1[4] = this.weeday4;
      arrayOfView1[5] = this.weeday5;
      arrayOfView1[6] = this.weeday6;
      arrayOfView1[7] = this.weeday7;
      arrayOfView1[8] = this.fullCharge_frame;
      arrayOfView1[9] = this.fullCharge_title;
      arrayOfView1[10] = this.fullCharge_switch1;
      arrayOfView1[11] = this.fullCharge_switch2;
      arrayOfView1[12] = this.fullCharge_switch3;
      arrayOfView1[13] = this.fullCharge_switch4;
      arrayOfView1[14] = this.fullCharge_switch5;
      arrayOfView1[15] = this.fullCharge_switch6;
      arrayOfView1[16] = this.fullCharge_switch7;
      arrayOfView1[17] = this.timer_selector1;
      arrayOfView1[18] = this.timer_selector2;
      arrayOfView1[19] = this.timer_selector3;
      arrayOfView1[20] = this.timer_selector4;
      arrayOfView1[21] = this.timer_selector5;
      arrayOfView1[22] = this.timer_selector6;
      arrayOfView1[23] = this.timer_selector7;
      arrayOfView1[24] = this.duration_text1;
      arrayOfView1[25] = this.duration_text2;
      arrayOfView1[26] = this.duration_text3;
      arrayOfView1[27] = this.duration_text4;
      arrayOfView1[28] = this.duration_text5;
      arrayOfView1[29] = this.duration_text6;
      arrayOfView1[30] = this.duration_text7;
      arrayOfView1[31] = this.timer_list1;
      arrayOfView1[32] = this.timer_list2;
      arrayOfView1[33] = this.timer_list3;
      arrayOfView1[34] = this.timer_list4;
      arrayOfView1[35] = this.timer_list5;
      arrayOfView1[36] = this.timer_list6;
      arrayOfView1[37] = this.timer_list7;
      arrayOfView1[38] = this.weekdayMask;
      arrayOfView1[39] = this.timerSelector_mask;
      arrayOfView1[40] = this.fullChargSwitch_mask;
      arrayOfView1[41] = FrmSetting_Charge.this.btnSet;
      arrayOfView1[42] = FrmSetting_Charge.this.btnSend;
      boolean[] arrayOfBoolean = new boolean[43];
      arrayOfBoolean[1] = true;
      arrayOfBoolean[2] = true;
      arrayOfBoolean[3] = true;
      arrayOfBoolean[4] = true;
      arrayOfBoolean[5] = true;
      arrayOfBoolean[6] = true;
      arrayOfBoolean[7] = true;
      arrayOfBoolean[9] = true;
      arrayOfBoolean[17] = true;
      arrayOfBoolean[18] = true;
      arrayOfBoolean[19] = true;
      arrayOfBoolean[20] = true;
      arrayOfBoolean[21] = true;
      arrayOfBoolean[22] = true;
      arrayOfBoolean[23] = true;
      arrayOfBoolean[24] = true;
      arrayOfBoolean[25] = true;
      arrayOfBoolean[26] = true;
      arrayOfBoolean[27] = true;
      arrayOfBoolean[28] = true;
      arrayOfBoolean[29] = true;
      arrayOfBoolean[30] = true;
      arrayOfBoolean[41] = true;
      arrayOfBoolean[42] = true;
      View[] arrayOfView2 = new View[7];
      arrayOfView2[0] = this.weeday1;
      arrayOfView2[1] = this.weeday2;
      arrayOfView2[2] = this.weeday3;
      arrayOfView2[3] = this.weeday4;
      arrayOfView2[4] = this.weeday5;
      arrayOfView2[5] = this.weeday6;
      arrayOfView2[6] = this.weeday7;
      AutoResizeTextView localAutoResizeTextView4 = this.duration_text1;
      AutoResizeTextView localAutoResizeTextView2 = this.duration_text2;
      AutoResizeTextView localAutoResizeTextView6 = this.duration_text3;
      AutoResizeTextView localAutoResizeTextView5 = this.duration_text4;
      AutoResizeTextView localAutoResizeTextView3 = this.duration_text5;
      AutoResizeTextView localAutoResizeTextView7 = this.duration_text6;
      AutoResizeTextView localAutoResizeTextView1 = this.duration_text7;
      mySwitch localmySwitch5 = this.fullCharge_switch1;
      mySwitch localmySwitch6 = this.fullCharge_switch2;
      mySwitch localmySwitch7 = this.fullCharge_switch3;
      mySwitch localmySwitch4 = this.fullCharge_switch4;
      mySwitch localmySwitch3 = this.fullCharge_switch5;
      mySwitch localmySwitch2 = this.fullCharge_switch6;
      mySwitch localmySwitch1 = this.fullCharge_switch7;
      int i = 0;
      int k;
      label719:
      int n;
      if (i >= arrayOfView1.length)
      {
        k = 0;
        i = j;
        j = k;
        if (j < arrayOfView2.length) {
          break label1400;
        }
        i = m - i;
        if (i > 0)
        {
          i /= 6;
          addMyViewMargin(this.weeday2, i, 0, 0, 0);
          addMyViewMargin(this.weeday3, i, 0, 0, 0);
          addMyViewMargin(this.weeday4, i, 0, 0, 0);
          addMyViewMargin(this.weeday5, i, 0, 0, 0);
          addMyViewMargin(this.weeday6, i, 0, 0, 0);
          addMyViewMargin(this.weeday7, i, 0, 0, 0);
        }
        k = getViewSpaceVertical(this.weeday2);
        n = this.timer_selector2.getLayoutParams().height;
        m = this.duration_text2.getLayoutParams().height;
        j = this.fullCharge_switch2.getLayoutParams().height;
      }
      for (i = 1;; i++)
      {
        if (i >= 7)
        {
          i = this.mAct.getValidScreenHeight();
          j = ((ViewGroup.MarginLayoutParams)this.fullCharge_frame.getLayoutParams()).topMargin;
          enlargeViewHeight(this.fullCharge_frame, i - j);
          enlargeViewHeight(this.weekdayMask, this.mAct.getValidScreenHeight());
          enlargeViewHeight(this.timerSelector_mask, this.mAct.getValidScreenHeight());
          enlargeViewHeight(this.fullChargSwitch_mask, this.mAct.getValidScreenHeight());
          this.timer_selector1.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.timer_selector2.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.timer_selector3.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.timer_selector4.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.timer_selector5.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.timer_selector6.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.timer_selector7.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          float f = this.timer_list1.getTextSize();
          this.timer_list1.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          this.timer_list2.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          this.timer_list3.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          this.timer_list4.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          this.timer_list5.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          this.timer_list6.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          this.timer_list7.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          return;
          this.mAct.enlargeViewToFitScreen(arrayOfView1[i], arrayOfBoolean[i]);
          i++;
          break;
          label1400:
          i += getViewSpaceVertical(arrayOfView2[j]);
          j++;
          break label719;
        }
        setMyViewMargin(new View[] { localAutoResizeTextView4, localAutoResizeTextView2, localAutoResizeTextView6, localAutoResizeTextView5, localAutoResizeTextView3, localAutoResizeTextView7, localAutoResizeTextView1 }[i], k - n - m, 0);
        setMyViewMargin(new View[] { localmySwitch5, localmySwitch6, localmySwitch7, localmySwitch4, localmySwitch3, localmySwitch2, localmySwitch1 }[i], k - j, 0);
      }
    }
    
    void setDay(int paramInt1, int paramInt2)
    {
      int[] arrayOfInt = this.set[(paramInt2 - 1)];
      this.daySet[paramInt1][0] = arrayOfInt[0];
      this.daySet[paramInt1][1] = arrayOfInt[1];
      this.daySet[paramInt1][2] = arrayOfInt[2];
      this.daySet[paramInt1][3] = arrayOfInt[3];
      setString(paramInt1);
    }
    
    void setDayPreset(int paramInt1, int paramInt2)
    {
      setDayTime(paramInt1, paramInt2);
      DealMsg.SetPValue((paramInt1 + 1) % 7, (short)paramInt2, FrmSetting_Charge.this.presetMode);
      this.TimerNum[paramInt1] = paramInt2;
      new TimerMenuList[] { this.timer_list1, this.timer_list2, this.timer_list3, this.timer_list4, this.timer_list5, this.timer_list6, this.timer_list7 }[paramInt1].setBtnEnable(this.set1_flag, this.set2_flag, this.set3_flag, paramInt2);
      AutoResizeTextView localAutoResizeTextView3 = this.timer_selector1;
      AutoResizeTextView localAutoResizeTextView2 = this.timer_selector2;
      Object localObject = this.timer_selector3;
      AutoResizeTextView localAutoResizeTextView4 = this.timer_selector4;
      AutoResizeTextView localAutoResizeTextView5 = this.timer_selector5;
      AutoResizeTextView localAutoResizeTextView1 = this.timer_selector6;
      AutoResizeTextView localAutoResizeTextView6 = this.timer_selector7;
      Resources localResources = this.mAct.getResources();
      setPresetText(new AutoResizeTextView[] { localAutoResizeTextView3, localAutoResizeTextView2, localObject, localAutoResizeTextView4, localAutoResizeTextView5, localAutoResizeTextView1, localAutoResizeTextView6 }[paramInt1], localResources.getString(new int[] { 2131165349, 2131165346, 2131165347, 2131165348 }[paramInt2]), paramInt2);
      localObject = new mySwitch[] { this.fullCharge_switch1, this.fullCharge_switch2, this.fullCharge_switch3, this.fullCharge_switch4, this.fullCharge_switch5, this.fullCharge_switch6, this.fullCharge_switch7 }[paramInt1];
      if (paramInt2 != 0) {}
      for (boolean bool = true;; bool = false)
      {
        ((mySwitch)localObject).setenabled(bool);
        return;
      }
    }
    
    void setDayTime(int paramInt1, int paramInt2)
    {
      if ((paramInt2 > 0) && (paramInt2 <= 3))
      {
        int[] arrayOfInt = this.set[(paramInt2 - 1)];
        this.daySet[paramInt1][0] = arrayOfInt[0];
        this.daySet[paramInt1][1] = arrayOfInt[1];
        this.daySet[paramInt1][2] = arrayOfInt[2];
        this.daySet[paramInt1][3] = arrayOfInt[3];
      }
      for (;;)
      {
        setString(paramInt1);
        return;
        this.daySet[paramInt1][0] = 31;
        this.daySet[paramInt1][1] = 7;
        this.daySet[paramInt1][2] = 31;
        this.daySet[paramInt1][3] = 7;
      }
    }
    
    void setPresetText(AutoResizeTextView paramAutoResizeTextView, String paramString, int paramInt)
    {
      paramAutoResizeTextView.setText(paramString);
      paramAutoResizeTextView.setNumber(paramInt);
    }
    
    void setSW()
    {
      byte[] arrayOfByte = new byte[1];
      DealMsg.SetFValue(1, this.fullCharge_switch1.getState(), arrayOfByte);
      DealMsg.SetFValue(2, this.fullCharge_switch2.getState(), arrayOfByte);
      DealMsg.SetFValue(3, this.fullCharge_switch3.getState(), arrayOfByte);
      DealMsg.SetFValue(4, this.fullCharge_switch4.getState(), arrayOfByte);
      DealMsg.SetFValue(5, this.fullCharge_switch5.getState(), arrayOfByte);
      DealMsg.SetFValue(6, this.fullCharge_switch6.getState(), arrayOfByte);
      DealMsg.SetFValue(0, this.fullCharge_switch7.getState(), arrayOfByte);
      FrmSetting_Charge.this.fullCharge = arrayOfByte[0];
    }
    
    void setString(int paramInt)
    {
      int i = (paramInt + 1) % 7;
      DealMsg.SetHValue(i, (short)this.daySet[paramInt][0], FrmSetting_Charge.this.timeSH);
      DealMsg.SetMValue(i, (short)this.daySet[paramInt][1], FrmSetting_Charge.this.timeSM);
      DealMsg.SetHValue(i, (short)this.daySet[paramInt][2], FrmSetting_Charge.this.timeFH);
      DealMsg.SetMValue(i, (short)this.daySet[paramInt][3], FrmSetting_Charge.this.timeFM);
      String str = " " + this.mAct.getString(2131165202) + " ";
      if (isTimeRight(this.daySet[paramInt])) {
        this.dataPreset[paramInt] = (DealMsg.getTimeString(this.daySet[paramInt][0], this.daySet[paramInt][1] * 10) + str + DealMsg.getTimeString(this.daySet[paramInt][2], this.daySet[paramInt][3] * 10));
      }
      for (;;)
      {
        new TextView[] { this.duration_text1, this.duration_text2, this.duration_text3, this.duration_text4, this.duration_text5, this.duration_text6, this.duration_text7 }[paramInt].setText(this.dataPreset[paramInt]);
        return;
        if ((this.daySet[paramInt][0] == 31) && (this.daySet[paramInt][1] == 7) && (this.daySet[paramInt][2] == 31) && (this.daySet[paramInt][3] == 7)) {
          this.dataPreset[paramInt] = " ";
        } else {
          this.dataPreset[paramInt] = ("-- : --" + str + "-- :  -- ");
        }
      }
    }
    
    void setSwitchState()
    {
      mySwitch[] arrayOfmySwitch;
      for (int i = 0;; i++)
      {
        if (i >= 7)
        {
          arrayOfmySwitch = new mySwitch[7];
          arrayOfmySwitch[0] = this.fullCharge_switch1;
          arrayOfmySwitch[1] = this.fullCharge_switch2;
          arrayOfmySwitch[2] = this.fullCharge_switch3;
          arrayOfmySwitch[3] = this.fullCharge_switch4;
          arrayOfmySwitch[4] = this.fullCharge_switch5;
          arrayOfmySwitch[5] = this.fullCharge_switch6;
          arrayOfmySwitch[6] = this.fullCharge_switch7;
          i = 0;
          if (i < 7) {
            break;
          }
          return;
        }
        int j = (i + 1) % 7;
        loadData(j, this.daySet[i]);
        this.preSwitch[i] = DealMsg.GetFValue(j, FrmSetting_Charge.this.fullCharge);
        setString(i);
      }
      this.TimerNum[i] = DealMsg.GetPValue((i + 1) % 7, FrmSetting_Charge.this.presetMode);
      mySwitch localmySwitch = arrayOfmySwitch[i];
      if (this.TimerNum[i] != 0) {}
      for (boolean bool = true;; bool = false)
      {
        localmySwitch.setenabled(bool);
        arrayOfmySwitch[i].setCheckState(this.preSwitch[i]);
        i++;
        break;
      }
    }
    
    public void showMask(boolean paramBoolean)
    {
      int j = 0;
      FrmSetting_Charge.this.showAppMask(paramBoolean);
      TextView localTextView = this.weekdayMask;
      if (paramBoolean)
      {
        i = 0;
        localTextView.setVisibility(i);
        localTextView = this.timerSelector_mask;
        if (!paramBoolean) {
          break label67;
        }
        i = 0;
        label38:
        localTextView.setVisibility(i);
        localTextView = this.fullChargSwitch_mask;
        if (!paramBoolean) {
          break label73;
        }
      }
      label67:
      label73:
      for (int i = j;; i = 8)
      {
        localTextView.setVisibility(i);
        return;
        i = 8;
        break;
        i = 8;
        break label38;
      }
    }
    
    public class buttonlistlistener
      implements OnButtonListcloseListener
    {
      public buttonlistlistener() {}
      
      public void onclosed(TimerMenuList paramTimerMenuList, int paramInt)
      {
        if (paramTimerMenuList.getId() == 2131296510) {
          FrmSetting_Charge.MvSettingCharge.this.setDayPreset(0, paramInt);
        }
        for (;;)
        {
          FrmSetting_Charge.MvSettingCharge.this.showMask(false);
          FrmSetting_Charge.this.SetBtnBarEnable(FrmSetting_Charge.this.setSentEable());
          return;
          if (paramTimerMenuList.getId() == 2131296512) {
            FrmSetting_Charge.MvSettingCharge.this.setDayPreset(1, paramInt);
          } else if (paramTimerMenuList.getId() == 2131296514) {
            FrmSetting_Charge.MvSettingCharge.this.setDayPreset(2, paramInt);
          } else if (paramTimerMenuList.getId() == 2131296516) {
            FrmSetting_Charge.MvSettingCharge.this.setDayPreset(3, paramInt);
          } else if (paramTimerMenuList.getId() == 2131296518) {
            FrmSetting_Charge.MvSettingCharge.this.setDayPreset(4, paramInt);
          } else if (paramTimerMenuList.getId() == 2131296520) {
            FrmSetting_Charge.MvSettingCharge.this.setDayPreset(5, paramInt);
          } else {
            FrmSetting_Charge.MvSettingCharge.this.setDayPreset(6, paramInt);
          }
        }
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\FrmSetting_Charge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */