package com.inventec.iMobile;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.OnButtonListcloseListener;
import com.inventec.iMobile.baseClass.OnButtonListcloseListenerEx;
import com.inventec.iMobile.baseClass.TimerMenuList;
import com.inventec.iMobile.baseClass.TimerMenuListex;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myAlertDlg;
import com.inventec.iMobile.baseClass.myAlertDlg.CloseCallback;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseOP.AppDealWifi;
import com.inventec.iMobile.baseOP.DataGetter;
import com.inventec.iMobile.baseOP.DealFile;
import com.inventec.iMobile.baseOP.MsgReceiver;
import com.inventec.iMobile.baseOP.MsgSender;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.baseStruct.DefMsg;
import java.util.ArrayList;
import kankan.wheel.widget.OnWheelClickedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.AbstractWheelAdapter;

public class FrmSetting_AirConditioning
  extends myActivity
{
  public static String fnsav = "setAC.sav";
  byte acStart;
  byte acTime;
  byte b0 = 15;
  byte b1 = 16;
  byte b2 = 32;
  byte bkacStart;
  byte[] bkpresetMode = new byte[2];
  byte[] bkpresetSH = new byte[2];
  byte[] bkpresetSM = new byte[2];
  byte[] bktimeSH = new byte[5];
  byte[] bktimeSM = new byte[3];
  private MyButton btnSend;
  private MyButton btnSet;
  byte[] presetMode = new byte[2];
  byte[] presetSH = new byte[2];
  byte[] presetSM = new byte[2];
  Resources resEx;
  byte[] timeSH = new byte[5];
  byte[] timeSM = new byte[3];
  
  public void SetBtnBarEnable(boolean paramBoolean)
  {
    this.btnSend.setEnabled(paramBoolean);
  }
  
  protected void btnSendClick()
  {
    MvSettingAirConditioning localMvSettingAirConditioning = (MvSettingAirConditioning)this.mCurMainView;
    if (localMvSettingAirConditioning != null) {
      localMvSettingAirConditioning.btnSendClick();
    }
  }
  
  protected void btnSetOnClick()
  {
    MvSettingAirConditioning localMvSettingAirConditioning = (MvSettingAirConditioning)this.mCurMainView;
    if (localMvSettingAirConditioning != null) {
      localMvSettingAirConditioning.btnSetOnClick();
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
      paramIntent = paramIntent.getByteArrayExtra("presetSM");
      if ((paramIntent != null) && (paramIntent.length == 2))
      {
        this.presetSM[0] = paramIntent[0];
        this.presetSM[1] = paramIntent[1];
      }
    }
  }
  
  public void onBackPressed()
  {
    Object localObject = (MvSettingAirConditioning)this.mCurMainView;
    if ((localObject != null) && (((MvSettingAirConditioning)localObject).setSentEable()))
    {
      localObject = new myAlertDlg(this);
      ((myAlertDlg)localObject).setDlgContent(0, 2131165370, false);
      ((myAlertDlg)localObject).setOkCallback(new myAlertDlg.CloseCallback()
      {
        public void DoAction()
        {
          FrmSetting_AirConditioning.this.ShowNavi();
        }
      });
      ((myAlertDlg)localObject).setCancelCallback(new myAlertDlg.CloseCallback()
      {
        public void DoAction()
        {
          FrmSetting_AirConditioning.this.finish();
        }
      });
      ((myAlertDlg)localObject).show();
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
    this.resEx = getResources();
    this.btnSet.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FrmSetting_AirConditioning.this.btnSetOnClick();
      }
    });
    this.btnSend.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FrmSetting_AirConditioning.this.btnSendClick();
      }
    });
    SetMainView(new MvSettingAirConditioning(this));
  }
  
  protected void onPause()
  {
    MvSettingAirConditioning localMvSettingAirConditioning = (MvSettingAirConditioning)this.mCurMainView;
    if (localMvSettingAirConditioning != null) {
      localMvSettingAirConditioning.saveAll();
    }
    super.onPause();
  }
  
  protected void onResume()
  {
    MvSettingAirConditioning localMvSettingAirConditioning = (MvSettingAirConditioning)this.mCurMainView;
    if (localMvSettingAirConditioning != null) {
      localMvSettingAirConditioning.loadAll();
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
  
  class MvSettingAirConditioning
    extends myMainView
  {
    private BitmapTextAdapter BTA_NotUse;
    private BitmapTextAdapter BTA_Used;
    int[][] PredaySet = { new int[2], new int[2], new int[2], new int[2], new int[2], new int[2], new int[2], new int[2] };
    int[][] Preset = { new int[2], new int[2], new int[2] };
    int[] TimerNum = new int[7];
    buttonlistlistenerex bl2 = new buttonlistlistenerex();
    buttonlistlistener bll = new buttonlistlistener();
    private String[] dataPreset = { "", "", "", "", "", "", "" };
    int[][] daySet = { new int[2], new int[2], new int[2], new int[2], new int[2], new int[2], new int[2], new int[2] };
    AutoResizeTextView duration_text1;
    AutoResizeTextView duration_text2;
    AutoResizeTextView duration_text3;
    AutoResizeTextView duration_text4;
    AutoResizeTextView duration_text5;
    AutoResizeTextView duration_text6;
    AutoResizeTextView duration_text7;
    AutoResizeTextView duration_text8;
    boolean flagListSetChange = false;
    LinearLayout layer1;
    LinearLayout layer2;
    LinearLayout layer3;
    LinearLayout layer4;
    LinearLayout layer5;
    LinearLayout layer6;
    LinearLayout layer7;
    LinearLayout layer8;
    LinearLayout layoutwheel = (LinearLayout)this.mView.findViewById(2131296525);
    private boolean mbUsed = false;
    TextView modeSelector_Mask;
    TextView mode_title;
    int[] oldTimerNum = new int[7];
    int[][] set = { new int[2], new int[2], new int[2] };
    private boolean set1_flag = false;
    private boolean set2_flag = false;
    private boolean set3_flag = false;
    TextView text_cool;
    LinearLayout timelayer1;
    LinearLayout timelayer2;
    LinearLayout timelayer3;
    LinearLayout timelayer4;
    LinearLayout timelayer5;
    LinearLayout timelayer6;
    LinearLayout timelayer7;
    LinearLayout timelayer8;
    TextView timerSelector_mask;
    TimerMenuList timer_list1;
    TimerMenuList timer_list2;
    TimerMenuList timer_list3;
    TimerMenuList timer_list4;
    TimerMenuList timer_list5;
    TimerMenuList timer_list6;
    TimerMenuList timer_list7;
    TimerMenuListex timer_list8;
    AutoResizeTextView timer_selector1 = (AutoResizeTextView)this.mView.findViewById(2131296495);
    AutoResizeTextView timer_selector2 = (AutoResizeTextView)this.mView.findViewById(2131296497);
    AutoResizeTextView timer_selector3 = (AutoResizeTextView)this.mView.findViewById(2131296499);
    AutoResizeTextView timer_selector4 = (AutoResizeTextView)this.mView.findViewById(2131296501);
    AutoResizeTextView timer_selector5 = (AutoResizeTextView)this.mView.findViewById(2131296503);
    AutoResizeTextView timer_selector6 = (AutoResizeTextView)this.mView.findViewById(2131296505);
    AutoResizeTextView timer_selector7 = (AutoResizeTextView)this.mView.findViewById(2131296507);
    AutoResizeTextView timer_selector8;
    TextView weeday1;
    TextView weeday2;
    TextView weeday3;
    TextView weeday4;
    TextView weeday5;
    TextView weeday6;
    TextView weeday7;
    LinearLayout weekDayName = (LinearLayout)this.mView.findViewById(2131296484);
    TextView weekdayMask;
    private WheelView wheelStatus;
    
    public MvSettingAirConditioning(myActivity parammyActivity)
    {
      super(2130903076);
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
      this.timer_selector8 = ((AutoResizeTextView)this.mView.findViewById(2131296531));
      this.timer_selector8.setSingle();
      this.duration_text8 = ((AutoResizeTextView)this.mView.findViewById(2131296530));
      this.timer_selector8.setColors(MyButton.colNor, -1);
      if (DealMsg.g_isDemo) {
        if (DefMsg.g_recdatas[125] == 1) {
          this.timer_selector8.setBackgroundResource(2130837627);
        }
      }
      for (;;)
      {
        this.text_cool = ((AutoResizeTextView)this.mView.findViewById(2131296527));
        this.weekdayMask = ((TextView)this.mView.findViewById(2131296492));
        this.timerSelector_mask = ((TextView)this.mView.findViewById(2131296508));
        this.modeSelector_Mask = ((TextView)this.mView.findViewById(2131296528));
        this.duration_text1 = ((AutoResizeTextView)this.mView.findViewById(2131296494));
        this.duration_text2 = ((AutoResizeTextView)this.mView.findViewById(2131296496));
        this.duration_text3 = ((AutoResizeTextView)this.mView.findViewById(2131296498));
        this.duration_text4 = ((AutoResizeTextView)this.mView.findViewById(2131296500));
        this.duration_text5 = ((AutoResizeTextView)this.mView.findViewById(2131296502));
        this.duration_text6 = ((AutoResizeTextView)this.mView.findViewById(2131296504));
        this.duration_text7 = ((AutoResizeTextView)this.mView.findViewById(2131296506));
        this.duration_text8.setText(2131165374);
        this.duration_text1.setSingle();
        this.duration_text2.setSingle();
        this.duration_text3.setSingle();
        this.duration_text4.setSingle();
        this.duration_text5.setSingle();
        this.duration_text6.setSingle();
        this.duration_text7.setSingle();
        this.duration_text8.setTextSize(20.0F);
        this.duration_text8.setSingle();
        this.weeday1 = ((TextView)this.mView.findViewById(2131296485));
        this.weeday2 = ((TextView)this.mView.findViewById(2131296486));
        this.weeday3 = ((TextView)this.mView.findViewById(2131296487));
        this.weeday4 = ((TextView)this.mView.findViewById(2131296488));
        this.weeday5 = ((TextView)this.mView.findViewById(2131296489));
        this.weeday6 = ((TextView)this.mView.findViewById(2131296490));
        this.weeday7 = ((TextView)this.mView.findViewById(2131296491));
        this.mode_title = ((TextView)this.mView.findViewById(2131296524));
        this.mode_title.setText(2131165364);
        this.timer_list1 = ((TimerMenuList)this.mView.findViewById(2131296510));
        this.timer_list2 = ((TimerMenuList)this.mView.findViewById(2131296512));
        this.timer_list3 = ((TimerMenuList)this.mView.findViewById(2131296514));
        this.timer_list4 = ((TimerMenuList)this.mView.findViewById(2131296516));
        this.timer_list5 = ((TimerMenuList)this.mView.findViewById(2131296518));
        this.timer_list6 = ((TimerMenuList)this.mView.findViewById(2131296520));
        this.timer_list7 = ((TimerMenuList)this.mView.findViewById(2131296522));
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
        this.timer_list8 = ((TimerMenuListex)this.mView.findViewById(2131296533));
        this.timer_list8.setVisibility(8);
        this.timer_list8.setOnbuttonlistlistener(this.bl2);
        this.timer_list8.listDirecton(false);
        this.text_cool.setVisibility(4);
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
        this.modeSelector_Mask.setVisibility(8);
        this.modeSelector_Mask.setOnTouchListener(new View.OnTouchListener()
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
              FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector1.setBackgroundResource(2130837629);
            }
            for (;;)
            {
              return true;
              if (paramAnonymousMotionEvent.getAction() == 1)
              {
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_list1.setVisibility(0);
                FrmSetting_AirConditioning.this.SetBtnBarEnable(false);
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.showMask(true);
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector1.setBackgroundResource(2130837628);
              }
            }
          }
        });
        this.timer_selector2.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            if (paramAnonymousMotionEvent.getAction() == 0) {
              FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector2.setBackgroundResource(2130837629);
            }
            for (;;)
            {
              return true;
              if (paramAnonymousMotionEvent.getAction() == 1)
              {
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_list2.setVisibility(0);
                FrmSetting_AirConditioning.this.SetBtnBarEnable(false);
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.showMask(true);
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector2.setBackgroundResource(2130837628);
              }
            }
          }
        });
        this.timer_selector3.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            if (paramAnonymousMotionEvent.getAction() == 0) {
              FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector3.setBackgroundResource(2130837629);
            }
            for (;;)
            {
              return true;
              if (paramAnonymousMotionEvent.getAction() == 1)
              {
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_list3.setVisibility(0);
                FrmSetting_AirConditioning.this.SetBtnBarEnable(false);
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.showMask(true);
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector3.setBackgroundResource(2130837628);
              }
            }
          }
        });
        this.timer_selector4.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            if (paramAnonymousMotionEvent.getAction() == 0) {
              FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector4.setBackgroundResource(2130837629);
            }
            for (;;)
            {
              return true;
              if (paramAnonymousMotionEvent.getAction() == 1)
              {
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_list4.setVisibility(0);
                FrmSetting_AirConditioning.this.SetBtnBarEnable(false);
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.showMask(true);
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector4.setBackgroundResource(2130837628);
              }
            }
          }
        });
        this.timer_selector5.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            if (paramAnonymousMotionEvent.getAction() == 0) {
              FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector5.setBackgroundResource(2130837629);
            }
            for (;;)
            {
              return true;
              if (paramAnonymousMotionEvent.getAction() == 1)
              {
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_list5.setVisibility(0);
                FrmSetting_AirConditioning.this.SetBtnBarEnable(false);
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.showMask(true);
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector5.setBackgroundResource(2130837628);
              }
            }
          }
        });
        this.timer_selector6.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            if (paramAnonymousMotionEvent.getAction() == 0) {
              FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector6.setBackgroundResource(2130837629);
            }
            for (;;)
            {
              return true;
              if (paramAnonymousMotionEvent.getAction() == 1)
              {
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_list6.setVisibility(0);
                FrmSetting_AirConditioning.this.SetBtnBarEnable(false);
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.showMask(true);
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector6.setBackgroundResource(2130837628);
              }
            }
          }
        });
        this.timer_selector7.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            if (paramAnonymousMotionEvent.getAction() == 0) {
              FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector7.setBackgroundResource(2130837629);
            }
            for (;;)
            {
              return true;
              if (paramAnonymousMotionEvent.getAction() == 1)
              {
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_list7.setVisibility(0);
                FrmSetting_AirConditioning.this.SetBtnBarEnable(false);
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.showMask(true);
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector7.setBackgroundResource(2130837628);
              }
            }
          }
        });
        this.timer_selector8.setOnTouchListener(new View.OnTouchListener()
        {
          public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
          {
            if (paramAnonymousMotionEvent.getAction() == 0) {
              if ((DealMsg.g_isDemo) && (DefMsg.g_recdatas[125] == 1)) {
                MsgReceiver.BroadcastMsgAction(101, 0);
              }
            }
            for (;;)
            {
              return true;
              if ((DefMsg.g_datas[22] == 3) || (DefMsg.g_datas[22] == 2))
              {
                MsgReceiver.BroadcastMsgAction(101, 0);
              }
              else
              {
                FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector8.setBackgroundResource(2130837629);
                continue;
                if ((paramAnonymousMotionEvent.getAction() == 1) && ((!DealMsg.g_isDemo) || (DefMsg.g_recdatas[125] != 1)) && (DefMsg.g_datas[22] != 3) && (DefMsg.g_datas[22] != 2))
                {
                  FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector8.setBackgroundResource(2130837628);
                  if (iMobile_AppGlobalVar.HasExACFunction(DefMsg.g_recdatas, 181))
                  {
                    MsgReceiver.BroadcastMsgAction(32, 0);
                  }
                  else
                  {
                    FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_list8.setVisibility(0);
                    FrmSetting_AirConditioning.this.SetBtnBarEnable(false);
                    FrmSetting_AirConditioning.MvSettingAirConditioning.this.showMask(true);
                    FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector8.setBackgroundResource(2130837628);
                  }
                }
              }
            }
          }
        });
        initWheel();
        setAutosizeView();
        FrmSetting_AirConditioning.this.SetTitleText(this.mAct.getString(2131165363));
        getPreDatas();
        setWheelEable();
        return;
        this.timer_selector8.setBackgroundResource(2130837628);
        continue;
        if ((DefMsg.g_datas[22] == 3) || (DefMsg.g_datas[22] == 2)) {
          this.timer_selector8.setBackgroundResource(2130837627);
        } else {
          this.timer_selector8.setBackgroundResource(2130837628);
        }
      }
    }
    
    private int getWheelStatus()
    {
      int j = this.wheelStatus.getCurrentItem();
      int i = j;
      if (this.mbUsed) {
        i = j + 1;
      }
      return i;
    }
    
    private void initWheel()
    {
      int j = 2130837641;
      this.wheelStatus = ((WheelView)this.mView.findViewById(2131296526));
      this.mAct.enlargeViewToFitScreen(this.wheelStatus, false);
      Object localObject1 = new ArrayList();
      ((ArrayList)localObject1).add(new BitmapOrText(2131165367, true));
      ((ArrayList)localObject1).add(new BitmapOrText(2131165366, true));
      if (iMobile_AppGlobalVar.isPad) {}
      for (int i = 2130837642;; i = 2130837641)
      {
        ((ArrayList)localObject1).add(new BitmapOrText(i, false));
        Object localObject2 = new ArrayList();
        ((ArrayList)localObject2).add(new BitmapOrText(2131165365, true));
        ((ArrayList)localObject2).add(new BitmapOrText(2131165367, true));
        ((ArrayList)localObject2).add(new BitmapOrText(2131165366, true));
        i = j;
        if (iMobile_AppGlobalVar.isPad) {
          i = 2130837642;
        }
        ((ArrayList)localObject2).add(new BitmapOrText(i, false));
        this.BTA_Used = new BitmapTextAdapter(this.mAct, (ArrayList)localObject1);
        this.BTA_NotUse = new BitmapTextAdapter(this.mAct, (ArrayList)localObject2);
        float f = this.mAct.getScrScale();
        i = this.wheelStatus.getLayoutParams().width;
        localObject1 = new AutoResizeTextView(this.mAct);
        ((AutoResizeTextView)localObject1).setText(2131165365);
        f = ((AutoResizeTextView)localObject1).getFittingSizeOfWidth(i, 16.0F * f);
        ((AutoResizeTextView)localObject1).setText(2131165367);
        f = ((AutoResizeTextView)localObject1).getFittingSizeOfWidth(i, f);
        ((AutoResizeTextView)localObject1).setText(2131165366);
        f = ((AutoResizeTextView)localObject1).getFittingSizeOfWidth(i, f);
        this.BTA_Used.setTextSize(f);
        this.BTA_NotUse.setTextSize(f);
        this.wheelStatus.setViewAdapter(this.BTA_NotUse);
        this.wheelStatus.setCurrentItem(0);
        localObject2 = new OnWheelScrollListener()
        {
          public void onScrollingFinished(WheelView paramAnonymousWheelView)
          {
            FrmSetting_AirConditioning.MvSettingAirConditioning.this.markWheelsUsed();
            FrmSetting_AirConditioning.MvSettingAirConditioning.this.updateStatus();
          }
          
          public void onScrollingStarted(WheelView paramAnonymousWheelView) {}
        };
        localObject1 = new OnWheelClickedListener()
        {
          public void onItemClicked(WheelView paramAnonymousWheelView, int paramAnonymousInt)
          {
            paramAnonymousWheelView.setCurrentItem(paramAnonymousInt, true);
          }
        };
        this.wheelStatus.addClickingListener((OnWheelClickedListener)localObject1);
        this.wheelStatus.addScrollingListener((OnWheelScrollListener)localObject2);
        return;
      }
    }
    
    private void markWheelsUsed()
    {
      if (this.mbUsed) {}
      for (;;)
      {
        return;
        this.mbUsed = true;
        int i = this.wheelStatus.getCurrentItem();
        this.wheelStatus.setViewAdapter(this.BTA_Used);
        if (i == 0) {
          this.wheelStatus.setCurrentItem(0);
        } else {
          this.wheelStatus.setCurrentItem(i - 1);
        }
      }
    }
    
    private void setWheelStatus(int paramInt)
    {
      if ((!this.mbUsed) && (paramInt <= 0)) {
        this.wheelStatus.setCurrentItem(0);
      }
      for (;;)
      {
        return;
        markWheelsUsed();
        this.wheelStatus.setCurrentItem(paramInt - 1);
      }
    }
    
    private void updateStatus()
    {
      FrmSetting_AirConditioning.this.SetBtnBarEnable(setSentEable());
    }
    
    boolean ACSCHValueRight()
    {
      for (int i = 0;; i++)
      {
        if (i >= 7) {}
        for (boolean bool = false;; bool = true)
        {
          return bool;
          int k = DealMsg.GetHValue(i, FrmSetting_AirConditioning.this.timeSH);
          int j = DealMsg.GetMValue(i, FrmSetting_AirConditioning.this.timeSM);
          if ((k > 23) || (j > 5)) {
            break;
          }
        }
      }
    }
    
    public byte[] KO_WF_AC_SCH_SP()
    {
      byte[] arrayOfByte = new byte[20];
      arrayOfByte[0] = -10;
      arrayOfByte[1] = 18;
      arrayOfByte[2] = 0;
      arrayOfByte[3] = 2;
      arrayOfByte[4] = FrmSetting_AirConditioning.this.presetMode[0];
      arrayOfByte[5] = FrmSetting_AirConditioning.this.presetMode[1];
      arrayOfByte[6] = FrmSetting_AirConditioning.this.presetSH[0];
      arrayOfByte[7] = FrmSetting_AirConditioning.this.presetSH[1];
      arrayOfByte[8] = FrmSetting_AirConditioning.this.presetSM[0];
      arrayOfByte[9] = FrmSetting_AirConditioning.this.presetSM[1];
      if (DefMsg.g_recdatas[0] == 1) {
        arrayOfByte[10] = ((byte)((byte)(FrmSetting_AirConditioning.this.acStart & 0xF0) + (byte)getWheelStatus()));
      }
      for (;;)
      {
        arrayOfByte[11] = FrmSetting_AirConditioning.this.timeSH[0];
        arrayOfByte[12] = FrmSetting_AirConditioning.this.timeSH[1];
        arrayOfByte[13] = FrmSetting_AirConditioning.this.timeSH[2];
        arrayOfByte[14] = FrmSetting_AirConditioning.this.timeSH[3];
        arrayOfByte[15] = FrmSetting_AirConditioning.this.timeSH[4];
        arrayOfByte[16] = FrmSetting_AirConditioning.this.timeSM[0];
        arrayOfByte[17] = FrmSetting_AirConditioning.this.timeSM[1];
        arrayOfByte[18] = FrmSetting_AirConditioning.this.timeSM[2];
        arrayOfByte[19] = DefMsg.calcCheckSum(arrayOfByte);
        return arrayOfByte;
        arrayOfByte[10] = ((byte)((byte)(FrmSetting_AirConditioning.this.acStart & 0xF0) + 1));
      }
    }
    
    public void PopupHelpInfo()
    {
      Intent localIntent = new Intent(this.mAct, BxShowHelp.class);
      Bundle localBundle = new Bundle();
      localBundle.putInt("CardNo", 27);
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
      boolean bool = iMobile_AppGlobalVar.HasExACFunction(DefMsg.g_recdatas, 181);
      if ((!DealMsg.g_isDemo) && (bool) && ((FrmSetting_AirConditioning.this.acStart & 0xF0) != 0)) {
        MsgSender.SendError();
      }
      byte[] arrayOfByte;
      do
      {
        return;
        if (!setSentEable()) {
          break;
        }
        arrayOfByte = KO_WF_AC_SCH_SP();
        DefMsg.memcpy(DefMsg.g_ACSendBuf, 0, arrayOfByte, 11, 8);
        MsgSender.Send(arrayOfByte);
      } while (!DealMsg.g_isDemo);
      int i = 0;
      if (ACSCHValueRight()) {
        if (DefMsg.g_recdatas[85] == 2) {}
      }
      for (i = 2;; i = 1)
      {
        if ((i != 0) && (DealMsg.g_isDemo)) {
          DefMsg.g_recdatas[85] = i;
        }
        DefMsg.memcpy(DefMsg.g_recdatas, 119, arrayOfByte, 4, 2);
        DefMsg.g_recdatas[98] = arrayOfByte[10];
        DefMsg.memcpy(DefMsg.g_recdatas, 86, arrayOfByte, 11, 8);
        DefMsg.memcpy(DefMsg.g_recdatas, 121, arrayOfByte, 6, 4);
        break;
        break;
      }
    }
    
    protected void btnSetOnClick()
    {
      Intent localIntent = new Intent(this.mAct, FrmSettingTimeSimple.class);
      Bundle localBundle = new Bundle();
      localBundle.putByteArray("presetSH", FrmSetting_AirConditioning.this.presetSH);
      localBundle.putByteArray("presetSM", FrmSetting_AirConditioning.this.presetSM);
      localIntent.putExtras(localBundle);
      this.mAct.startActivityForResult(localIntent, 1);
    }
    
    public void getPreDatas()
    {
      if ((DefMsg.memcmp(FrmSetting_AirConditioning.this.bktimeSH, 0, DefMsg.g_datas, 86, 5)) && (DefMsg.memcmp(FrmSetting_AirConditioning.this.bktimeSM, 0, DefMsg.g_datas, 91, 3)) && (DefMsg.memcmp(FrmSetting_AirConditioning.this.bkpresetMode, 0, DefMsg.g_datas, 119, 2)) && (DefMsg.memcmp(FrmSetting_AirConditioning.this.bkpresetSH, 0, DefMsg.g_datas, 121, 2)) && (DefMsg.memcmp(FrmSetting_AirConditioning.this.bkpresetSM, 0, DefMsg.g_datas, 123, 2)) && (FrmSetting_AirConditioning.this.bkacStart == DataGetter.g_datas((short)98))) {}
      for (;;)
      {
        return;
        DefMsg.memcpy(FrmSetting_AirConditioning.this.bktimeSH, 0, DefMsg.g_datas, 86, 5);
        DefMsg.memcpy(FrmSetting_AirConditioning.this.bktimeSM, 0, DefMsg.g_datas, 91, 3);
        DefMsg.memcpy(FrmSetting_AirConditioning.this.bkpresetMode, 0, DefMsg.g_datas, 119, 2);
        DefMsg.memcpy(FrmSetting_AirConditioning.this.bkpresetSH, 0, DefMsg.g_datas, 121, 2);
        DefMsg.memcpy(FrmSetting_AirConditioning.this.bkpresetSM, 0, DefMsg.g_datas, 123, 2);
        FrmSetting_AirConditioning.this.bkacStart = DataGetter.g_datas((short)98);
        DefMsg.memcpy(FrmSetting_AirConditioning.this.timeSH, 0, FrmSetting_AirConditioning.this.bktimeSH, 0, 5);
        DefMsg.memcpy(FrmSetting_AirConditioning.this.timeSM, 0, FrmSetting_AirConditioning.this.bktimeSM, 0, 3);
        DefMsg.memcpy(FrmSetting_AirConditioning.this.presetMode, 0, FrmSetting_AirConditioning.this.bkpresetMode, 0, 2);
        DefMsg.memcpy(FrmSetting_AirConditioning.this.presetSH, 0, FrmSetting_AirConditioning.this.bkpresetSH, 0, 2);
        DefMsg.memcpy(FrmSetting_AirConditioning.this.presetSM, 0, FrmSetting_AirConditioning.this.bkpresetSM, 0, 2);
        FrmSetting_AirConditioning.this.acStart = FrmSetting_AirConditioning.this.bkacStart;
        FrmSetting_AirConditioning.this.showDemoBar();
        loadPreData(1, this.Preset[0]);
        loadPreData(2, this.Preset[1]);
        loadPreData(3, this.Preset[2]);
        int i = 0;
        label369:
        if (i >= 7) {
          setWheelStatus(FrmSetting_AirConditioning.this.acStart & 0xF);
        }
        Object localObject;
        for (i = 0;; i++)
        {
          if (i >= 7)
          {
            localObject = FrmSetting_AirConditioning.this.resEx.getString(2131165375);
            if ((FrmSetting_AirConditioning.this.acStart & 0xF0) != 0) {
              break label531;
            }
            setPresetText(this.timer_selector8, "10" + (String)localObject, 0);
            break;
            loadData((i + 1) % 7, this.daySet[i], this.PredaySet[i]);
            setString(i);
            i++;
            break label369;
          }
          localObject = this.oldTimerNum;
          int[] arrayOfInt = this.TimerNum;
          int j = DealMsg.GetPValue((i + 1) % 7, FrmSetting_AirConditioning.this.presetMode);
          arrayOfInt[i] = j;
          localObject[i] = j;
        }
        label531:
        if ((FrmSetting_AirConditioning.this.acStart & 0xF0) == 16) {
          setPresetText(this.timer_selector8, "20" + (String)localObject, 0);
        } else if ((FrmSetting_AirConditioning.this.acStart & 0xF0) == 32) {
          setPresetText(this.timer_selector8, "30" + (String)localObject, 0);
        }
      }
    }
    
    boolean isTimeRight(int[] paramArrayOfInt)
    {
      boolean bool = true;
      if ((paramArrayOfInt[0] < 0) || (paramArrayOfInt[0] > 23)) {}
      for (bool = false;; bool = false) {
        do
        {
          return bool;
        } while ((paramArrayOfInt[1] >= 0) && (paramArrayOfInt[1] <= 5));
      }
    }
    
    public void loadAll()
    {
      byte[] arrayOfByte = new byte['Ā'];
      if (DealFile.loaddata(this.mAct, FrmSetting_AirConditioning.fnsav, arrayOfByte)) {
        parseData(arrayOfByte);
      }
    }
    
    void loadData(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
    {
      int i = DealMsg.GetHValue(paramInt, FrmSetting_AirConditioning.this.timeSH);
      paramArrayOfInt1[0] = i;
      paramArrayOfInt2[0] = i;
      paramInt = DealMsg.GetMValue(paramInt, FrmSetting_AirConditioning.this.timeSM);
      paramArrayOfInt1[1] = paramInt;
      paramArrayOfInt2[1] = paramInt;
    }
    
    void loadPreData(int paramInt, int[] paramArrayOfInt)
    {
      paramArrayOfInt[0] = DealMsg.GetPmHValue(paramInt, FrmSetting_AirConditioning.this.presetSH);
      paramArrayOfInt[1] = DealMsg.GetPmMValue(paramInt, FrmSetting_AirConditioning.this.presetSM);
    }
    
    public byte[] makeData()
    {
      int j = 1;
      byte[] arrayOfByte = new byte['Ā'];
      DefMsg.memcpy(arrayOfByte, 0, FrmSetting_AirConditioning.this.bktimeSH, 0, 5);
      DefMsg.memcpy(arrayOfByte, 5, FrmSetting_AirConditioning.this.bktimeSM, 0, 3);
      DefMsg.memcpy(arrayOfByte, 8, FrmSetting_AirConditioning.this.bkpresetMode, 0, 2);
      DefMsg.memcpy(arrayOfByte, 10, FrmSetting_AirConditioning.this.bkpresetSH, 0, 2);
      DefMsg.memcpy(arrayOfByte, 12, FrmSetting_AirConditioning.this.bkpresetSM, 0, 2);
      arrayOfByte[14] = FrmSetting_AirConditioning.this.bkacStart;
      DefMsg.memcpy(arrayOfByte, 15, FrmSetting_AirConditioning.this.timeSH, 0, 5);
      DefMsg.memcpy(arrayOfByte, 20, FrmSetting_AirConditioning.this.timeSM, 0, 3);
      DefMsg.memcpy(arrayOfByte, 23, FrmSetting_AirConditioning.this.presetMode, 0, 2);
      DefMsg.memcpy(arrayOfByte, 25, FrmSetting_AirConditioning.this.presetSH, 0, 2);
      DefMsg.memcpy(arrayOfByte, 27, FrmSetting_AirConditioning.this.presetSM, 0, 2);
      arrayOfByte[29] = FrmSetting_AirConditioning.this.acStart;
      if (this.flagListSetChange)
      {
        i = 1;
        arrayOfByte[30] = ((byte)i);
        DealMsg.IA2ToB(arrayOfByte, 31, this.set, 3, 2);
        DealMsg.IA2ToB(arrayOfByte, 55, this.daySet, 7, 2);
        DealMsg.IA2ToB(arrayOfByte, 111, this.Preset, 3, 2);
        DealMsg.IA2ToB(arrayOfByte, 135, this.PredaySet, 7, 2);
        if (!this.set1_flag) {
          break label335;
        }
        i = 1;
        label253:
        arrayOfByte['¿'] = ((byte)i);
        if (!this.set2_flag) {
          break label340;
        }
        i = 1;
        label269:
        arrayOfByte['À'] = ((byte)i);
        if (!this.set3_flag) {
          break label345;
        }
      }
      label335:
      label340:
      label345:
      for (int i = j;; i = 0)
      {
        arrayOfByte['Á'] = ((byte)i);
        DealMsg.IAToB(arrayOfByte, 194, this.TimerNum, 7);
        DealMsg.IAToB(arrayOfByte, 222, this.oldTimerNum, 7);
        arrayOfByte['ú'] = ((byte)getWheelStatus());
        return arrayOfByte;
        i = 0;
        break;
        i = 0;
        break label253;
        i = 0;
        break label269;
      }
    }
    
    public void parseData(byte[] paramArrayOfByte)
    {
      boolean bool2 = true;
      DefMsg.memcpy(FrmSetting_AirConditioning.this.bktimeSH, 0, paramArrayOfByte, 0, 5);
      DefMsg.memcpy(FrmSetting_AirConditioning.this.bktimeSM, 0, paramArrayOfByte, 5, 3);
      DefMsg.memcpy(FrmSetting_AirConditioning.this.bkpresetMode, 0, paramArrayOfByte, 8, 2);
      DefMsg.memcpy(FrmSetting_AirConditioning.this.bkpresetSH, 0, paramArrayOfByte, 10, 2);
      DefMsg.memcpy(FrmSetting_AirConditioning.this.bkpresetSM, 0, paramArrayOfByte, 12, 2);
      FrmSetting_AirConditioning.this.bkacStart = paramArrayOfByte[14];
      DefMsg.memcpy(FrmSetting_AirConditioning.this.timeSH, 0, paramArrayOfByte, 15, 5);
      DefMsg.memcpy(FrmSetting_AirConditioning.this.timeSM, 0, paramArrayOfByte, 20, 3);
      DefMsg.memcpy(FrmSetting_AirConditioning.this.presetMode, 0, paramArrayOfByte, 23, 2);
      DefMsg.memcpy(FrmSetting_AirConditioning.this.presetSH, 0, paramArrayOfByte, 25, 2);
      DefMsg.memcpy(FrmSetting_AirConditioning.this.presetSM, 0, paramArrayOfByte, 27, 2);
      FrmSetting_AirConditioning.this.acStart = paramArrayOfByte[29];
      if (paramArrayOfByte[30] == 1)
      {
        bool1 = true;
        this.flagListSetChange = bool1;
        DealMsg.BToIA2(paramArrayOfByte, 31, this.set, 3, 2);
        DealMsg.BToIA2(paramArrayOfByte, 55, this.daySet, 7, 2);
        DealMsg.BToIA2(paramArrayOfByte, 111, this.Preset, 3, 2);
        DealMsg.BToIA2(paramArrayOfByte, 135, this.PredaySet, 7, 2);
        if (paramArrayOfByte['¿'] != 1) {
          break label327;
        }
        bool1 = true;
        label249:
        this.set1_flag = bool1;
        if (paramArrayOfByte['À'] != 1) {
          break label332;
        }
        bool1 = true;
        label265:
        this.set2_flag = bool1;
        if (paramArrayOfByte['Á'] != 1) {
          break label337;
        }
      }
      label327:
      label332:
      label337:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        this.set3_flag = bool1;
        DealMsg.BToIA(paramArrayOfByte, 194, this.TimerNum, 7);
        DealMsg.BToIA(paramArrayOfByte, 222, this.oldTimerNum, 7);
        setWheelStatus(paramArrayOfByte['ú']);
        return;
        bool1 = false;
        break;
        bool1 = false;
        break label249;
        bool1 = false;
        break label265;
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
      this.flagListSetChange = false;
      int i = 0;
      if (i >= 3)
      {
        if (this.flagListSetChange)
        {
          i = 0;
          label96:
          if (i < 7) {
            break label588;
          }
        }
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
        this.timer_list8.setBtnEnable(true, true, true);
        FrmSetting_AirConditioning.this.SetBtnBarEnable(setSentEable());
        if ((DefMsg.g_datas[22] != 3) && (DefMsg.g_datas[22] != 2)) {
          break label614;
        }
        this.timer_selector8.setBackgroundResource(2130837627);
      }
      for (;;)
      {
        return;
        label582:
        for (int j = 0;; j++)
        {
          if (j >= 2) {}
          for (;;)
          {
            i++;
            break;
            if (this.Preset[i][j] == this.set[i][j]) {
              break label582;
            }
            this.flagListSetChange = true;
          }
        }
        label588:
        j = this.TimerNum[i];
        if (j > 0) {
          setDay(i, j);
        }
        i++;
        break label96;
        label614:
        this.timer_selector8.setBackgroundResource(2130837628);
      }
    }
    
    public void saveAll()
    {
      DealFile.savedata(this.mAct, FrmSetting_AirConditioning.fnsav, makeData());
    }
    
    public void setAutosizeView()
    {
      int n = this.mAct.getValidScreenHeight();
      int j = 0;
      int k = 0;
      View[] arrayOfView3 = new View[39];
      arrayOfView3[0] = this.weekDayName;
      arrayOfView3[1] = this.weeday1;
      arrayOfView3[2] = this.weeday2;
      arrayOfView3[3] = this.weeday3;
      arrayOfView3[4] = this.weeday4;
      arrayOfView3[5] = this.weeday5;
      arrayOfView3[6] = this.weeday6;
      arrayOfView3[7] = this.weeday7;
      arrayOfView3[8] = this.timer_selector1;
      arrayOfView3[9] = this.timer_selector2;
      arrayOfView3[10] = this.timer_selector3;
      arrayOfView3[11] = this.timer_selector4;
      arrayOfView3[12] = this.timer_selector5;
      arrayOfView3[13] = this.timer_selector6;
      arrayOfView3[14] = this.timer_selector7;
      arrayOfView3[15] = this.timer_selector8;
      arrayOfView3[16] = this.duration_text1;
      arrayOfView3[17] = this.duration_text2;
      arrayOfView3[18] = this.duration_text3;
      arrayOfView3[19] = this.duration_text4;
      arrayOfView3[20] = this.duration_text5;
      arrayOfView3[21] = this.duration_text6;
      arrayOfView3[22] = this.duration_text7;
      arrayOfView3[23] = this.duration_text8;
      arrayOfView3[24] = this.timer_list1;
      arrayOfView3[25] = this.timer_list2;
      arrayOfView3[26] = this.timer_list3;
      arrayOfView3[27] = this.timer_list4;
      arrayOfView3[28] = this.timer_list5;
      arrayOfView3[29] = this.timer_list6;
      arrayOfView3[30] = this.timer_list7;
      arrayOfView3[31] = this.timer_list8;
      arrayOfView3[32] = this.weekdayMask;
      arrayOfView3[33] = this.timerSelector_mask;
      arrayOfView3[34] = this.modeSelector_Mask;
      arrayOfView3[35] = FrmSetting_AirConditioning.this.btnSet;
      arrayOfView3[36] = FrmSetting_AirConditioning.this.btnSend;
      arrayOfView3[37] = this.mode_title;
      arrayOfView3[38] = this.layoutwheel;
      boolean[] arrayOfBoolean = new boolean[39];
      arrayOfBoolean[1] = true;
      arrayOfBoolean[2] = true;
      arrayOfBoolean[3] = true;
      arrayOfBoolean[4] = true;
      arrayOfBoolean[5] = true;
      arrayOfBoolean[6] = true;
      arrayOfBoolean[7] = true;
      arrayOfBoolean[8] = true;
      arrayOfBoolean[9] = true;
      arrayOfBoolean[10] = true;
      arrayOfBoolean[11] = true;
      arrayOfBoolean[12] = true;
      arrayOfBoolean[13] = true;
      arrayOfBoolean[14] = true;
      arrayOfBoolean[15] = true;
      arrayOfBoolean[16] = true;
      arrayOfBoolean[17] = true;
      arrayOfBoolean[18] = true;
      arrayOfBoolean[19] = true;
      arrayOfBoolean[20] = true;
      arrayOfBoolean[21] = true;
      arrayOfBoolean[22] = true;
      arrayOfBoolean[23] = true;
      arrayOfBoolean[35] = true;
      arrayOfBoolean[36] = true;
      arrayOfBoolean[37] = true;
      View[] arrayOfView1 = new View[7];
      arrayOfView1[0] = this.weeday1;
      arrayOfView1[1] = this.weeday2;
      arrayOfView1[2] = this.weeday3;
      arrayOfView1[3] = this.weeday4;
      arrayOfView1[4] = this.weeday5;
      arrayOfView1[5] = this.weeday6;
      arrayOfView1[6] = this.weeday7;
      AutoResizeTextView localAutoResizeTextView4 = this.duration_text1;
      AutoResizeTextView localAutoResizeTextView7 = this.duration_text2;
      AutoResizeTextView localAutoResizeTextView3 = this.duration_text3;
      AutoResizeTextView localAutoResizeTextView6 = this.duration_text4;
      AutoResizeTextView localAutoResizeTextView2 = this.duration_text5;
      AutoResizeTextView localAutoResizeTextView1 = this.duration_text6;
      AutoResizeTextView localAutoResizeTextView5 = this.duration_text7;
      View[] arrayOfView2 = new View[4];
      arrayOfView2[0] = this.mode_title;
      arrayOfView2[1] = this.layoutwheel;
      arrayOfView2[2] = this.timer_selector8;
      arrayOfView2[3] = this.duration_text8;
      int i = 0;
      int m;
      if (i >= arrayOfView3.length)
      {
        m = 0;
        i = j;
        j = m;
        label668:
        if (j < arrayOfView1.length) {
          break label1611;
        }
        j = 0;
        label679:
        if (j < arrayOfView2.length) {
          break label1631;
        }
        j = this.mAct.getValidScreenHeight();
        addMyViewMargin(this.duration_text8, (j - k) / 2, 0, 0, 0);
        i = n - i;
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
        j = this.timer_selector2.getLayoutParams().height;
        m = this.duration_text2.getLayoutParams().height;
      }
      for (i = 1;; i++)
      {
        if (i >= 7)
        {
          enlargeViewHeight(this.weekdayMask, this.mAct.getValidScreenHeight());
          enlargeViewHeight(this.timerSelector_mask, this.mAct.getValidScreenHeight());
          this.timer_selector1.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.timer_selector2.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.timer_selector3.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.timer_selector4.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.timer_selector5.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.timer_selector6.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.timer_selector7.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.timer_selector8.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.duration_text1.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.duration_text2.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.duration_text3.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.duration_text4.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.duration_text5.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.duration_text6.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          this.duration_text7.setPadding((int)(3.0F * this.mAct.getScrScale()), 0, (int)(38.0F * this.mAct.getScrScale()), 0);
          float f = this.timer_list1.getTextSize();
          this.timer_list1.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          this.timer_list2.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          this.timer_list3.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          this.timer_list4.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          this.timer_list5.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          this.timer_list6.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          this.timer_list7.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          this.timer_list8.setTextSize(0, this.mAct.px2dip(f) * this.mAct.getScrScale());
          return;
          this.mAct.enlargeViewToFitScreen(arrayOfView3[i], arrayOfBoolean[i]);
          i++;
          break;
          label1611:
          i += getViewSpaceVertical(arrayOfView1[j]);
          j++;
          break label668;
          label1631:
          k += getViewSpaceVertical(arrayOfView2[j]);
          j++;
          break label679;
        }
        setMyViewMargin(new View[] { localAutoResizeTextView4, localAutoResizeTextView7, localAutoResizeTextView3, localAutoResizeTextView6, localAutoResizeTextView2, localAutoResizeTextView1, localAutoResizeTextView5 }[i], k - j - m, 0);
      }
    }
    
    void setDay(int paramInt1, int paramInt2)
    {
      this.daySet[paramInt1][0] = this.set[(paramInt2 - 1)][0];
      this.daySet[paramInt1][1] = this.set[(paramInt2 - 1)][1];
      setString(paramInt1);
    }
    
    void setDayPreset(int paramInt1, int paramInt2)
    {
      setDayTime(paramInt1, paramInt2);
      DealMsg.SetPValue((paramInt1 + 1) % 7, (short)paramInt2, FrmSetting_AirConditioning.this.presetMode);
      this.TimerNum[paramInt1] = paramInt2;
      new TimerMenuList[] { this.timer_list1, this.timer_list2, this.timer_list3, this.timer_list4, this.timer_list5, this.timer_list6, this.timer_list7 }[paramInt1].setBtnEnable(this.set1_flag, this.set2_flag, this.set3_flag, paramInt2);
      AutoResizeTextView localAutoResizeTextView2 = this.timer_selector1;
      AutoResizeTextView localAutoResizeTextView5 = this.timer_selector2;
      AutoResizeTextView localAutoResizeTextView6 = this.timer_selector3;
      AutoResizeTextView localAutoResizeTextView4 = this.timer_selector4;
      AutoResizeTextView localAutoResizeTextView3 = this.timer_selector5;
      AutoResizeTextView localAutoResizeTextView1 = this.timer_selector6;
      AutoResizeTextView localAutoResizeTextView7 = this.timer_selector7;
      Resources localResources = this.mAct.getResources();
      setPresetText(new AutoResizeTextView[] { localAutoResizeTextView2, localAutoResizeTextView5, localAutoResizeTextView6, localAutoResizeTextView4, localAutoResizeTextView3, localAutoResizeTextView1, localAutoResizeTextView7 }[paramInt1], localResources.getString(new int[] { 2131165349, 2131165346, 2131165347, 2131165348 }[paramInt2]), paramInt2);
    }
    
    void setDayTime(int paramInt1, int paramInt2)
    {
      int[] arrayOfInt;
      if (paramInt2 > 0)
      {
        arrayOfInt = this.set[2];
        if (paramInt2 == 1)
        {
          arrayOfInt = this.set[0];
          this.daySet[paramInt1][0] = arrayOfInt[0];
          this.daySet[paramInt1][1] = arrayOfInt[1];
        }
      }
      for (;;)
      {
        setString(paramInt1);
        return;
        if (paramInt2 != 2) {
          break;
        }
        arrayOfInt = this.set[1];
        break;
        this.daySet[paramInt1][0] = 31;
        this.daySet[paramInt1][1] = 7;
      }
    }
    
    void setPresetText(AutoResizeTextView paramAutoResizeTextView, String paramString, int paramInt)
    {
      paramAutoResizeTextView.setText(paramString);
      paramAutoResizeTextView.setNumber(paramInt);
    }
    
    public boolean setSentEable()
    {
      boolean bool2 = false;
      int i;
      boolean bool1;
      if (DefMsg.g_recdatas[0] == 1)
      {
        i = getWheelStatus();
        if (i == 0) {
          bool1 = bool2;
        }
      }
      for (;;)
      {
        return bool1;
        if ((FrmSetting_AirConditioning.this.acStart & 0xF) == 0) {
          markWheelsUsed();
        }
        if ((FrmSetting_AirConditioning.this.acStart & 0xF) != i)
        {
          bool1 = true;
        }
        else if (FrmSetting_AirConditioning.this.acStart != FrmSetting_AirConditioning.this.bkacStart)
        {
          bool1 = true;
        }
        else if (this.flagListSetChange)
        {
          bool1 = true;
        }
        else
        {
          i = 0;
          label98:
          bool1 = bool2;
          if (i < 7)
          {
            if (this.oldTimerNum[i] == this.TimerNum[i]) {
              break;
            }
            bool1 = true;
          }
        }
      }
      for (int j = 0;; j++)
      {
        if (j >= 2)
        {
          i++;
          break label98;
        }
        if (this.PredaySet[i][j] != this.daySet[i][j])
        {
          bool1 = true;
          break;
        }
      }
    }
    
    void setString(int paramInt)
    {
      String str = " " + this.mAct.getString(2131165202);
      if (isTimeRight(this.daySet[paramInt])) {
        this.dataPreset[paramInt] = (DealMsg.getTimeString(this.daySet[paramInt][0], this.daySet[paramInt][1] * 10) + str);
      }
      for (;;)
      {
        int i = (paramInt + 1) % 7;
        DealMsg.SetHValue(i, (short)this.daySet[paramInt][0], FrmSetting_AirConditioning.this.timeSH);
        DealMsg.SetMValue(i, (short)this.daySet[paramInt][1], FrmSetting_AirConditioning.this.timeSM);
        new TextView[] { this.duration_text1, this.duration_text2, this.duration_text3, this.duration_text4, this.duration_text5, this.duration_text6, this.duration_text7 }[paramInt].setText(this.dataPreset[paramInt]);
        return;
        if ((this.daySet[paramInt][0] == 31) && (this.daySet[paramInt][1] == 7)) {
          this.dataPreset[paramInt] = " ";
        } else {
          this.dataPreset[paramInt] = ("-- : --" + str);
        }
      }
    }
    
    void setWheelEable()
    {
      if (DefMsg.g_recdatas[0] == 1)
      {
        this.layoutwheel.setVisibility(0);
        this.text_cool.setVisibility(4);
      }
      for (;;)
      {
        return;
        this.layoutwheel.setVisibility(4);
        this.text_cool.setVisibility(0);
      }
    }
    
    public void showMask(boolean paramBoolean)
    {
      int j = 0;
      FrmSetting_AirConditioning.this.showAppMask(paramBoolean);
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
        localTextView = this.modeSelector_Mask;
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
    
    private class BitmapOrText
    {
      boolean IsText = true;
      int resid = 0;
      
      public BitmapOrText(int paramInt, boolean paramBoolean)
      {
        this.resid = paramInt;
        this.IsText = paramBoolean;
      }
    }
    
    private class BitmapTextAdapter
      extends AbstractWheelAdapter
    {
      int IMAGE_HEIGHT = 75;
      private Context context;
      private final ArrayList<FrmSetting_AirConditioning.MvSettingAirConditioning.BitmapOrText> items;
      private float textSize = 16.0F;
      
      public BitmapTextAdapter(ArrayList<FrmSetting_AirConditioning.MvSettingAirConditioning.BitmapOrText> paramArrayList)
      {
        this.context = paramArrayList;
        ArrayList localArrayList;
        this.items = localArrayList;
        if (FrmSetting_AirConditioning.MvSettingAirConditioning.this.GetWindowHeight() < 800) {
          this.IMAGE_HEIGHT = FrmSetting_AirConditioning.MvSettingAirConditioning.this.dip2px(FrmSetting_AirConditioning.MvSettingAirConditioning.this.mAct, 36.0F);
        }
      }
      
      public View getItem(int paramInt, View paramView, ViewGroup paramViewGroup)
      {
        if (paramView != null)
        {
          paramView = (AutoResizeTextView)paramView;
          if (paramInt == 3) {
            AppDealWifi.LogMsg("index 3");
          }
          paramView.setLayoutParams(new ViewGroup.LayoutParams(FrmSetting_AirConditioning.MvSettingAirConditioning.this.wheelStatus.getLayoutParams().width, this.IMAGE_HEIGHT));
          paramViewGroup = (FrmSetting_AirConditioning.MvSettingAirConditioning.BitmapOrText)this.items.get(paramInt);
          paramView.setPadding(0, 0, 0, 0);
          paramView.setSingle();
          paramView.setGravity(17);
          paramView.setBackgroundDrawable(null);
          paramView.setColors(MyButton.colNor, MyButton.colDwn);
          paramView.setTextSize(0, this.textSize);
          if (!paramViewGroup.IsText) {
            break label133;
          }
          paramView.setText(paramViewGroup.resid);
        }
        for (;;)
        {
          return paramView;
          paramView = new AutoResizeTextView(this.context);
          break;
          label133:
          paramView.setText("　　　　");
          paramView.setBackgroundResource(paramViewGroup.resid);
        }
      }
      
      public int getItemsCount()
      {
        return this.items.size();
      }
      
      public void setTextSize(float paramFloat)
      {
        this.textSize = paramFloat;
      }
    }
    
    public class buttonlistlistener
      implements OnButtonListcloseListener
    {
      public buttonlistlistener() {}
      
      public void onclosed(TimerMenuList paramTimerMenuList, int paramInt)
      {
        if (paramTimerMenuList.getId() == 2131296510) {
          FrmSetting_AirConditioning.MvSettingAirConditioning.this.setDayPreset(0, paramInt);
        }
        for (;;)
        {
          FrmSetting_AirConditioning.MvSettingAirConditioning.this.showMask(false);
          FrmSetting_AirConditioning.this.SetBtnBarEnable(FrmSetting_AirConditioning.MvSettingAirConditioning.this.setSentEable());
          return;
          if (paramTimerMenuList.getId() == 2131296512) {
            FrmSetting_AirConditioning.MvSettingAirConditioning.this.setDayPreset(1, paramInt);
          } else if (paramTimerMenuList.getId() == 2131296514) {
            FrmSetting_AirConditioning.MvSettingAirConditioning.this.setDayPreset(2, paramInt);
          } else if (paramTimerMenuList.getId() == 2131296516) {
            FrmSetting_AirConditioning.MvSettingAirConditioning.this.setDayPreset(3, paramInt);
          } else if (paramTimerMenuList.getId() == 2131296518) {
            FrmSetting_AirConditioning.MvSettingAirConditioning.this.setDayPreset(4, paramInt);
          } else if (paramTimerMenuList.getId() == 2131296520) {
            FrmSetting_AirConditioning.MvSettingAirConditioning.this.setDayPreset(5, paramInt);
          } else {
            FrmSetting_AirConditioning.MvSettingAirConditioning.this.setDayPreset(6, paramInt);
          }
        }
      }
    }
    
    public class buttonlistlistenerex
      implements OnButtonListcloseListenerEx
    {
      public buttonlistlistenerex() {}
      
      public void onclosed(TimerMenuListex paramTimerMenuListex, int paramInt)
      {
        String str = FrmSetting_AirConditioning.this.resEx.getString(2131165375);
        if ((paramInt == 2) || (paramInt == 3)) {
          MsgReceiver.BroadcastMsgAction(100, 0);
        }
        if (paramInt == 1)
        {
          FrmSetting_AirConditioning.MvSettingAirConditioning.this.setPresetText(FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector8, "10" + str, 0);
          FrmSetting_AirConditioning.this.acStart = ((byte)(FrmSetting_AirConditioning.this.acStart & FrmSetting_AirConditioning.this.b0));
        }
        for (;;)
        {
          paramTimerMenuListex.setBtnEnable(true, true, true);
          FrmSetting_AirConditioning.MvSettingAirConditioning.this.showMask(false);
          FrmSetting_AirConditioning.this.SetBtnBarEnable(FrmSetting_AirConditioning.MvSettingAirConditioning.this.setSentEable());
          return;
          if (paramInt == 2)
          {
            FrmSetting_AirConditioning.MvSettingAirConditioning.this.setPresetText(FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector8, "20" + str, 0);
            FrmSetting_AirConditioning.this.acStart = ((byte)(FrmSetting_AirConditioning.this.acStart & FrmSetting_AirConditioning.this.b0));
            FrmSetting_AirConditioning.this.acStart = ((byte)(FrmSetting_AirConditioning.this.acStart | 0x10));
          }
          else if (paramInt == 3)
          {
            FrmSetting_AirConditioning.MvSettingAirConditioning.this.setPresetText(FrmSetting_AirConditioning.MvSettingAirConditioning.this.timer_selector8, "30" + str, 0);
            FrmSetting_AirConditioning.this.acStart = ((byte)(FrmSetting_AirConditioning.this.acStart & FrmSetting_AirConditioning.this.b0));
            FrmSetting_AirConditioning.this.acStart = ((byte)(FrmSetting_AirConditioning.this.acStart | 0x20));
          }
        }
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\FrmSetting_AirConditioning.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */