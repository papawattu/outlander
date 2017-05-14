package com.inventec.iMobile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.inventec.OnMyMessageSenderListener;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyViewPager;
import com.inventec.controls.iSlider;
import com.inventec.controls.iSlider.onSliderEnd;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myAlertDlg;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseOP.AppDealWifi;
import com.inventec.iMobile.baseOP.CmdMake;
import com.inventec.iMobile.baseOP.DataGetter;
import com.inventec.iMobile.baseOP.MsgReceiver;
import com.inventec.iMobile.baseOP.MsgSender;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.baseStruct.DefMsg;
import java.util.ArrayList;
import java.util.List;

public class MvControl
  extends myMainView
{
  AutoResizeTextView Card1pic;
  AutoResizeTextView Card1txt;
  AutoResizeTextView Card3pic_alarmSts;
  AutoResizeTextView Card3pic_lockSts;
  AutoResizeTextView Card3txt;
  boolean bBlink = true;
  boolean bNOIGN = true;
  boolean bNoAlarm = true;
  boolean bShowAlarm = false;
  boolean[] bShowPage = { 1, 1, 1 };
  int blinkTimeOut;
  int c1 = 8;
  int c2 = 8;
  int c3 = 8;
  RadioButton card1;
  RadioButton card2;
  RelativeLayout card2_r1;
  RelativeLayout card2_r2;
  RelativeLayout card2_r3;
  RadioButton card3;
  CheckBox cb_head;
  CheckBox cb_horn;
  CheckBox cb_lock;
  CheckBox cb_position;
  CheckBox cb_unlock;
  private iSlider ivaniAlarm;
  private iSlider ivaniHead;
  private iSlider ivaniHorn;
  private iSlider ivaniLock;
  private iSlider ivaniPostion;
  private iSlider ivaniUnlock;
  View linearlayout1;
  View linearlayout2;
  View linearlayout3;
  private Handler mHandler = null;
  private LayoutInflater mInflater;
  private List<View> mListViews;
  private MyViewPager myViewPager;
  final int waitTimer = 15000;
  
  public MvControl(myActivity parammyActivity)
  {
    super(parammyActivity, 2130903073);
    this.mInflater = parammyActivity.getLayoutInflater();
    this.linearlayout1 = this.mInflater.inflate(2130903064, null);
    this.linearlayout2 = this.mInflater.inflate(2130903065, null);
    this.linearlayout3 = this.mInflater.inflate(2130903066, null);
    this.mListViews = new ArrayList();
    this.myViewPager = ((MyViewPager)this.mView.findViewById(2131296328));
    this.card1 = ((RadioButton)this.mView.findViewById(2131296400));
    this.card2 = ((RadioButton)this.mView.findViewById(2131296401));
    this.card3 = ((RadioButton)this.mView.findViewById(2131296402));
    this.card2_r1 = ((RelativeLayout)this.linearlayout2.findViewById(2131296425));
    this.card2_r2 = ((RelativeLayout)this.linearlayout2.findViewById(2131296429));
    this.card2_r3 = ((RelativeLayout)this.linearlayout2.findViewById(2131296433));
    this.cb_head = ((CheckBox)this.linearlayout2.findViewById(2131296426));
    this.cb_position = ((CheckBox)this.linearlayout2.findViewById(2131296430));
    this.cb_horn = ((CheckBox)this.linearlayout2.findViewById(2131296434));
    this.cb_lock = ((CheckBox)this.linearlayout3.findViewById(2131296439));
    this.cb_unlock = ((CheckBox)this.linearlayout3.findViewById(2131296442));
    if (iMobile_AppGlobalVar.isPad)
    {
      this.cb_head.setBackgroundResource(2130837618);
      this.cb_position.setBackgroundResource(2130837624);
      this.cb_horn.setBackgroundResource(2130837620);
      this.cb_lock.setBackgroundResource(2130837622);
      this.cb_unlock.setBackgroundResource(2130837626);
    }
    parammyActivity.enlargeViewToFitScreen(this.cb_head, false);
    parammyActivity.enlargeViewToFitScreen(this.cb_position, false);
    parammyActivity.enlargeViewToFitScreen(this.cb_horn, false);
    parammyActivity.enlargeViewToFitScreen(this.cb_lock, false);
    parammyActivity.enlargeViewToFitScreen(this.cb_unlock, false);
    this.cb_head.setFocusable(false);
    this.cb_position.setFocusable(false);
    this.cb_horn.setFocusable(false);
    this.cb_lock.setFocusable(false);
    this.cb_unlock.setFocusable(false);
    this.ivaniAlarm = ((iSlider)this.linearlayout1.findViewById(2131296421));
    parammyActivity.enlargeViewToFitScreen(this.ivaniAlarm, true);
    this.ivaniAlarm.setText(2131165377);
    this.ivaniAlarm.startSlider();
    this.ivaniAlarm.SetMyMessageSenderListener(new OnMyMessageSenderListener()
    {
      public void dealmsg()
      {
        myAlertDlg localmyAlertDlg = new myAlertDlg(MvControl.this.mAct);
        localmyAlertDlg.setDlgContent(0, 2131165381, true);
        localmyAlertDlg.show();
      }
    });
    this.ivaniHead = ((iSlider)this.linearlayout2.findViewById(2131296427));
    parammyActivity.enlargeViewToFitScreen(this.ivaniHead, true);
    this.ivaniHead.setText(2131165387);
    this.ivaniHead.SetMyButtonEnable(false);
    this.ivaniHead.SetMyMessageSenderListener(new OnMyMessageSenderListener()
    {
      public void dealmsg()
      {
        if (MvControl.this.headCanOn()) {}
        for (;;)
        {
          return;
          myAlertDlg localmyAlertDlg = new myAlertDlg(MvControl.this.mAct);
          localmyAlertDlg.setDlgContent(0, 2131165395, true);
          localmyAlertDlg.show();
        }
      }
    });
    this.ivaniPostion = ((iSlider)this.linearlayout2.findViewById(2131296431));
    parammyActivity.enlargeViewToFitScreen(this.ivaniPostion, true);
    this.ivaniPostion.setText(2131165390);
    this.ivaniPostion.SetMyButtonEnable(false);
    this.ivaniPostion.SetMyMessageSenderListener(new OnMyMessageSenderListener()
    {
      public void dealmsg()
      {
        if (MvControl.this.posCanOn()) {}
        for (;;)
        {
          return;
          myAlertDlg localmyAlertDlg = new myAlertDlg(MvControl.this.mAct);
          localmyAlertDlg.setDlgContent(0, 2131165395, true);
          localmyAlertDlg.show();
        }
      }
    });
    this.ivaniHorn = ((iSlider)this.linearlayout2.findViewById(2131296435));
    parammyActivity.enlargeViewToFitScreen(this.ivaniHorn, true);
    this.ivaniHorn.setText(2131165393);
    this.ivaniHorn.SetMyButtonEnable(false);
    this.ivaniHorn.SetMyMessageSenderListener(new OnMyMessageSenderListener()
    {
      public void dealmsg()
      {
        if (MvControl.this.hornCanOn()) {}
        for (;;)
        {
          return;
          myAlertDlg localmyAlertDlg = new myAlertDlg(MvControl.this.mAct);
          localmyAlertDlg.setDlgContent(0, 2131165394, true);
          localmyAlertDlg.show();
        }
      }
    });
    this.ivaniLock = ((iSlider)this.linearlayout3.findViewById(2131296440));
    parammyActivity.enlargeViewToFitScreen(this.ivaniLock, true);
    this.ivaniLock.setText(2131165397);
    this.ivaniLock.SetMyButtonEnable(false);
    this.ivaniLock.SetMyMessageSenderListener(new OnMyMessageSenderListener()
    {
      public void dealmsg()
      {
        if ((MvControl.this.isDoorClose()) && (MvControl.this.canDoDoorLock())) {}
        for (;;)
        {
          return;
          myAlertDlg localmyAlertDlg = new myAlertDlg(MvControl.this.mAct);
          localmyAlertDlg.setDlgContent(0, 2131165407, true);
          localmyAlertDlg.show();
        }
      }
    });
    this.ivaniUnlock = ((iSlider)this.linearlayout3.findViewById(2131296443));
    parammyActivity.enlargeViewToFitScreen(this.ivaniUnlock, true);
    this.ivaniUnlock.setText(2131165398);
    this.ivaniUnlock.SetMyButtonEnable(false);
    this.ivaniUnlock.SetMyMessageSenderListener(new OnMyMessageSenderListener()
    {
      public void dealmsg()
      {
        if (MvControl.this.canDoDoorLock()) {}
        for (;;)
        {
          return;
          myAlertDlg localmyAlertDlg = new myAlertDlg(MvControl.this.mAct);
          localmyAlertDlg.setDlgContent(0, 2131165407, true);
          localmyAlertDlg.show();
        }
      }
    });
    this.myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramAnonymousInt)
      {
        if (paramAnonymousInt == 2) {
          MvControl.this.setPagePoint(MvControl.this.myViewPager.getCurrentItem());
        }
      }
      
      public void onPageScrolled(int paramAnonymousInt1, float paramAnonymousFloat, int paramAnonymousInt2) {}
      
      public void onPageSelected(int paramAnonymousInt) {}
    });
    this.Card1pic = ((AutoResizeTextView)this.linearlayout1.findViewById(2131296423));
    parammyActivity.enlargeViewToFitScreen(this.Card1pic, true);
    this.Card1txt = ((AutoResizeTextView)this.linearlayout1.findViewById(2131296424));
    parammyActivity.enlargeViewToFitScreen(this.Card1txt, true);
    this.Card3pic_alarmSts = ((AutoResizeTextView)this.linearlayout3.findViewById(2131296446));
    parammyActivity.enlargeViewToFitScreen(this.Card3pic_alarmSts, true);
    this.Card3pic_lockSts = ((AutoResizeTextView)this.linearlayout3.findViewById(2131296447));
    parammyActivity.enlargeViewToFitScreen(this.Card3pic_lockSts, true);
    this.Card3txt = ((AutoResizeTextView)this.linearlayout3.findViewById(2131296448));
    parammyActivity.enlargeViewToFitScreen(this.Card3txt, true);
    TextView localTextView = (TextView)this.linearlayout2.findViewById(2131296428);
    parammyActivity.enlargeViewToFitScreen(localTextView, true);
    localTextView.setGravity(16);
    localTextView = (TextView)this.linearlayout2.findViewById(2131296432);
    parammyActivity.enlargeViewToFitScreen(localTextView, true);
    localTextView.setGravity(16);
    localTextView = (TextView)this.linearlayout2.findViewById(2131296436);
    parammyActivity.enlargeViewToFitScreen(localTextView, true);
    localTextView.setGravity(16);
    this.cb_head.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          MvControl.this.mHandler.removeMessages(6);
          paramAnonymousCompoundButton = MvControl.this.cb_head;
          if (iMobile_AppGlobalVar.isPad) {}
          paramAnonymousCompoundButton.setBackgroundResource(2130837774);
        }
        for (;;)
        {
          MvControl.this.PageLockerCheck();
          boolean bool = paramAnonymousBoolean;
          if (paramAnonymousBoolean)
          {
            MvControl.this.setChecked(false, 2);
            MvControl.this.cb_position.setChecked(false);
            MvControl.this.setChecked(false, 3);
            MvControl.this.cb_horn.setChecked(false);
            bool = MvControl.this.headCanOn();
          }
          MvControl.this.setChecked(bool, 1);
          return;
          if (MvControl.this.cb_head.isSelected())
          {
            MvControl.this.mHandler.sendEmptyMessageDelayed(6, 500L);
          }
          else
          {
            paramAnonymousCompoundButton = MvControl.this.cb_head;
            if (iMobile_AppGlobalVar.isPad) {}
            paramAnonymousCompoundButton.setBackgroundResource(2130837770);
          }
        }
      }
    });
    this.cb_position.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          MvControl.this.mHandler.removeMessages(7);
          paramAnonymousCompoundButton = MvControl.this.cb_position;
          if (iMobile_AppGlobalVar.isPad) {}
          paramAnonymousCompoundButton.setBackgroundResource(2130837786);
        }
        for (;;)
        {
          MvControl.this.PageLockerCheck();
          boolean bool = paramAnonymousBoolean;
          if (paramAnonymousBoolean)
          {
            MvControl.this.setChecked(false, 1);
            MvControl.this.cb_head.setChecked(false);
            MvControl.this.setChecked(false, 3);
            MvControl.this.cb_horn.setChecked(false);
            bool = MvControl.this.posCanOn();
          }
          MvControl.this.setChecked(bool, 2);
          return;
          if (MvControl.this.cb_position.isSelected())
          {
            MvControl.this.mHandler.sendEmptyMessageDelayed(7, 500L);
          }
          else
          {
            paramAnonymousCompoundButton = MvControl.this.cb_position;
            if (iMobile_AppGlobalVar.isPad) {}
            paramAnonymousCompoundButton.setBackgroundResource(2130837782);
          }
        }
      }
    });
    this.cb_horn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        MvControl.this.PageLockerCheck();
        boolean bool = paramAnonymousBoolean;
        if (paramAnonymousBoolean)
        {
          MvControl.this.setChecked(false, 1);
          MvControl.this.cb_head.setChecked(false);
          MvControl.this.setChecked(false, 2);
          MvControl.this.cb_position.setChecked(false);
          bool = MvControl.this.hornCanOn();
        }
        MvControl.this.setChecked(bool, 3);
      }
    });
    this.cb_lock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        MvControl.this.PageLockerCheck();
        boolean bool = paramAnonymousBoolean;
        if (paramAnonymousBoolean)
        {
          MvControl.this.setChecked(false, 5);
          MvControl.this.cb_unlock.setChecked(false);
          if ((!MvControl.this.isDoorClose()) || (!MvControl.this.canDoDoorLock())) {
            break label65;
          }
        }
        label65:
        for (bool = true;; bool = false)
        {
          MvControl.this.setChecked(bool, 4);
          return;
        }
      }
    });
    this.cb_unlock.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        MvControl.this.PageLockerCheck();
        boolean bool = paramAnonymousBoolean;
        if (paramAnonymousBoolean)
        {
          MvControl.this.setChecked(false, 4);
          MvControl.this.cb_lock.setChecked(false);
          bool = MvControl.this.canDoDoorLock();
        }
        MvControl.this.setChecked(bool, 5);
      }
    });
    this.ivaniAlarm.setSliderEnd(new iSlider.onSliderEnd()
    {
      public void dealSliderEnd()
      {
        MvControl.this.card1slidersender();
      }
    });
    this.ivaniHead.setSliderEnd(new iSlider.onSliderEnd()
    {
      public void dealSliderEnd()
      {
        MvControl.this.card2headsender();
      }
    });
    this.ivaniPostion.setSliderEnd(new iSlider.onSliderEnd()
    {
      public void dealSliderEnd()
      {
        MvControl.this.card2positionsender();
      }
    });
    this.ivaniHorn.setSliderEnd(new iSlider.onSliderEnd()
    {
      public void dealSliderEnd()
      {
        MvControl.this.card2hornsender();
      }
    });
    this.ivaniLock.setSliderEnd(new iSlider.onSliderEnd()
    {
      public void dealSliderEnd()
      {
        MvControl.this.mHandler.sendEmptyMessage(14);
        MvControl.this.mHandler.removeMessages(4);
      }
    });
    this.ivaniUnlock.setSliderEnd(new iSlider.onSliderEnd()
    {
      public void dealSliderEnd()
      {
        MvControl.this.mHandler.sendEmptyMessage(15);
        MvControl.this.mHandler.removeMessages(5);
      }
    });
    this.mHandler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        super.handleMessage(paramAnonymousMessage);
        int i = paramAnonymousMessage.what;
        if (i == 1) {
          MvControl.this.cb_head.setChecked(false);
        }
        for (;;)
        {
          return;
          if (i == 2) {
            MvControl.this.cb_position.setChecked(false);
          } else if (i == 3) {
            MvControl.this.cb_horn.setChecked(false);
          } else if (i == 4) {
            MvControl.this.cb_lock.setChecked(false);
          } else if (i == 5) {
            MvControl.this.cb_unlock.setChecked(false);
          } else if (i == 6) {
            MvControl.this.setHeadBlink();
          } else if (i == 7) {
            MvControl.this.setPosBlink();
          } else if (i == 14) {
            MvControl.this.card3locksender(1);
          } else if (i == 15)
          {
            if (MvControl.this.getLockMode() == 2) {
              MvControl.this.card3locksender(4);
            } else {
              MvControl.this.card3locksender(2);
            }
          }
          else if (i == 20) {
            MvControl.this.CarAlarmReciver();
          } else if (i == 30) {
            MvControl.this.PageLockerCheck();
          }
        }
      }
    };
  }
  
  public void CarAlarmReciver()
  {
    if (!DataGetter.canDealTheftAlerm()) {}
    for (;;)
    {
      return;
      this.bShowAlarm = true;
      this.Card3pic_alarmSts.setVisibility(0);
      this.Card3pic_lockSts.setVisibility(4);
      this.Card3txt.setVisibility(0);
      if ((DataGetter.g_datas((short)126) == 4) || (DataGetter.g_datas((short)126) == 5))
      {
        this.Card3txt.setColors(-65536, 0);
        this.Card3txt.setText(2131165406);
        this.Card3pic_alarmSts.setBackgroundResource(2130837794);
      }
      else if ((DataGetter.g_datas((short)126) == 1) || (DataGetter.g_datas((short)126) == 6) || (DataGetter.g_datas((short)126) == 7))
      {
        this.Card3txt.setColors(Color.parseColor("#11D9FF"), 0);
        this.Card3txt.setText(2131165405);
        this.Card3pic_alarmSts.setBackgroundResource(2130837796);
      }
      else
      {
        this.Card3txt.setColors(-1, 0);
        this.Card3txt.setText(2131165404);
        this.Card3pic_alarmSts.setBackgroundResource(2130837795);
      }
    }
  }
  
  public void Get_point(int paramInt)
  {
    if (paramInt == this.linearlayout1.getId()) {
      this.card1.setChecked(true);
    }
    for (;;)
    {
      showTitle();
      return;
      if (paramInt == this.linearlayout2.getId()) {
        this.card2.setChecked(true);
      } else if (paramInt == this.linearlayout3.getId()) {
        this.card3.setChecked(true);
      }
    }
  }
  
  public void PageLockerCheck()
  {
    if ((this.cb_head.isChecked()) || (this.cb_horn.isChecked()) || (this.cb_lock.isChecked()) || (this.cb_position.isChecked()) || (this.cb_unlock.isChecked())) {
      this.myViewPager.setPagingEnabled(false);
    }
    for (;;)
    {
      return;
      this.myViewPager.setPagingEnabled(true);
    }
  }
  
  public void PopupHelpInfo()
  {
    Intent localIntent = new Intent(this.mAct, BxShowHelp.class);
    Bundle localBundle = new Bundle();
    if (this.card1.isChecked()) {
      localBundle.putInt("CardNo", 41);
    }
    for (;;)
    {
      localIntent.putExtras(localBundle);
      this.mAct.startActivityForResult(localIntent, 101);
      return;
      if (this.card2.isChecked()) {
        localBundle.putInt("CardNo", 42);
      } else if (this.card3.isChecked()) {
        localBundle.putInt("CardNo", 43);
      }
    }
  }
  
  public void Refresh()
  {
    int i;
    boolean bool;
    if (myStaterefresh())
    {
      Object localObject = new ViewPagAdapter(this.mAct, this.mListViews);
      this.myViewPager.setAdapter((PagerAdapter)localObject);
      this.myViewPager.setAllowChildMovement(2131296328);
      i = this.mListViews.size();
      localObject = this.myViewPager;
      if (i != 2) {
        break label91;
      }
      bool = true;
      ((MyViewPager)localObject).setTwoPage(bool);
      if (i != 1) {
        break label96;
      }
      this.myViewPager.setPagingEnabled(false);
      setPagePoint(0);
    }
    for (;;)
    {
      showTitle();
      return;
      label91:
      bool = false;
      break;
      label96:
      this.myViewPager.setPagingEnabled(true);
      i = i + 1000 - 1000 % i;
      this.myViewPager.setCurrentItem(i);
      setPagePoint(i);
    }
  }
  
  boolean canDoDoorLock()
  {
    boolean bool2 = true;
    boolean bool1 = bool2;
    if (DataGetter.g_datas((short)130) != 1) {
      if (DataGetter.g_datas((short)130) == 2)
      {
        bool1 = bool2;
        if (this.bNOIGN) {}
      }
      else
      {
        bool1 = false;
      }
    }
    return bool1;
  }
  
  public void card1slidersender()
  {
    int i = 1;
    if (DataGetter.g_datas((short)127) == 1) {
      i = 2;
    }
    byte b = (byte)i;
    MsgSender.Send(CmdMake.KO_WF_P_ALARM_RQ_SP(b));
    if (DealMsg.g_isDemo) {
      DefMsg.g_recdatas[127] = b;
    }
    doSend();
  }
  
  public void card2headsender()
  {
    byte[] arrayOfByte;
    if (this.cb_head.isSelected())
    {
      i = 2;
      byte b = (byte)i;
      MsgSender.Send(CmdMake.KO_WF_H_LAMP_CONT_SP(b));
      if (DealMsg.g_isDemo)
      {
        arrayOfByte = DefMsg.g_recdatas;
        if (b != 2) {
          break label69;
        }
      }
    }
    label69:
    for (int i = 0;; i = 1)
    {
      arrayOfByte[''] = ((byte)i);
      doSend();
      this.mHandler.sendEmptyMessageDelayed(1, 500L);
      return;
      i = 1;
      break;
    }
  }
  
  public void card2hornsender()
  {
    MsgSender.Send(CmdMake.KO_WF_R_HORN_CONT_SP());
    doSend();
    this.mHandler.sendEmptyMessageDelayed(3, 500L);
  }
  
  public void card2positionsender()
  {
    int j = 1;
    if (this.cb_position.isSelected()) {}
    for (int i = 2;; i = 1)
    {
      byte b = (byte)i;
      MsgSender.Send(CmdMake.KO_WF_P_LAMP_CONT_SP(b));
      if (DealMsg.g_isDemo)
      {
        byte[] arrayOfByte = DefMsg.g_recdatas;
        i = j;
        if (b == 2) {
          i = 0;
        }
        arrayOfByte[101] = ((byte)i);
      }
      doSend();
      this.mHandler.sendEmptyMessageDelayed(2, 500L);
      return;
    }
  }
  
  public void card3locksender(int paramInt)
  {
    int i = 2;
    byte b = (byte)paramInt;
    MsgSender.Send(CmdMake.KO_WF_D_LOCK_RQ_SP(b));
    this.cb_lock.setChecked(false);
    this.cb_unlock.setChecked(false);
    if (DealMsg.g_isDemo)
    {
      if ((b != 1) && (b != 3)) {
        break label89;
      }
      DefMsg.g_recdatas[''] = 1;
      arrayOfByte = DefMsg.g_recdatas;
      if (b != 3) {
        break label84;
      }
    }
    label84:
    for (paramInt = i;; paramInt = 1)
    {
      b = (byte)paramInt;
      arrayOfByte[''] = b;
      MsgReceiver.g_doorLook = b;
      doSend();
      return;
    }
    label89:
    DefMsg.g_recdatas[''] = 2;
    byte[] arrayOfByte = DefMsg.g_recdatas;
    if (b == 2) {}
    for (paramInt = 4;; paramInt = 5)
    {
      b = (byte)paramInt;
      arrayOfByte[''] = b;
      MsgReceiver.g_doorLook = b;
      break;
    }
  }
  
  void doSend()
  {
    this.myViewPager.setPagingEnabled(false);
    this.mHandler.sendEmptyMessageDelayed(30, 2L);
  }
  
  int getLockMode()
  {
    int i = 1;
    if (DealMsg.g_recvFUNC[17][2] == 0) {}
    for (;;)
    {
      return i;
      if (DealMsg.g_recvFUNC[19][2] == 0)
      {
        if (DealMsg.g_recvFUNC[17][1] == 1) {
          i = 2;
        }
      }
      else {
        i = 3;
      }
    }
  }
  
  boolean headCanOn()
  {
    boolean bool2 = false;
    int i = DataGetter.g_datas((short)102);
    boolean bool1 = bool2;
    if (DataGetter.g_datas((short)127) != 3)
    {
      if (i != 6) {
        break label29;
      }
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      label29:
      if ((DataGetter.g_datas((short)139) != 0) && (i != 0))
      {
        bool1 = bool2;
        if (i != 3) {}
      }
      else
      {
        bool1 = this.bNoAlarm;
      }
    }
  }
  
  boolean hornCanOn()
  {
    int j = DataGetter.g_datas();
    int i = DataGetter.g_datas((short)127);
    if ((j == 6) || (i == 1) || (DataGetter.g_datas((short)126) == 4)) {}
    for (boolean bool = false;; bool = this.bNoAlarm) {
      return bool;
    }
  }
  
  boolean isDoorClose()
  {
    if ((DataGetter.g_datas() == 0) && (DataGetter.g_datas((short)133) == 0) && (DataGetter.g_datas((short)134) == 0) && (DataGetter.g_datas((short)135) == 0) && (DataGetter.g_datas((short)136) == 0) && (DataGetter.g_datas((short)137) == 0) && (DataGetter.g_datas((short)130) < 3)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public boolean myStaterefresh()
  {
    int k = DataGetter.g_datas();
    int i = DefMsg.g_recdatas[94];
    int n;
    int j;
    label166:
    label182:
    label189:
    label319:
    label357:
    Object localObject;
    if ((i != 3) && (i != 4))
    {
      bool = true;
      this.bNOIGN = bool;
      n = DataGetter.g_FUNC(27, 0);
      i = DataGetter.g_FUNC(27, 1);
      DataGetter.g_datas((short)12);
      int m = DataGetter.g_datas((short)126);
      j = DataGetter.g_datas((short)127);
      this.mListViews.remove(this.linearlayout1);
      if ((n != 3) || (i != 1)) {
        break label716;
      }
      this.mListViews.add(this.linearlayout1);
      this.card1.setVisibility(0);
      if ((j != 1) || (m == 4)) {
        break label669;
      }
      this.Card1pic.setBackgroundResource(2130837759);
      this.Card1txt.setText(2131165380);
      this.Card1txt.setColors(-65536, 0);
      this.ivaniAlarm.setText(2131165378);
      if ((m == 4) || (!this.bNOIGN)) {
        break label710;
      }
      bool = true;
      setChecked(bool, 0);
      this.cb_head.setChecked(false);
      this.cb_position.setChecked(false);
      this.cb_horn.setChecked(false);
      this.ivaniHead.stopSlider();
      this.ivaniPostion.stopSlider();
      this.ivaniHorn.stopSlider();
      n = DataGetter.g_datas((short)15);
      this.mListViews.remove(this.linearlayout2);
      if ((1 != 0) || (1 != 0) || (n != 0)) {
        break label728;
      }
      this.card2.setVisibility(8);
      if ((DataGetter.g_datas((short)102) == 6) || ((j == 1) && ((!DealMsg.g_isDemo) || (j == 1))) || (m == 4) || (!this.bNOIGN)) {
        break label825;
      }
      bool = true;
      this.bNoAlarm = bool;
      this.mHandler.removeMessages(6);
      bool = this.bNoAlarm;
      i = DataGetter.g_datas((short)102);
      if ((i == 0) || (i == 3)) {
        break label831;
      }
      i = 0;
      if (bool)
      {
        if (DataGetter.g_datas((short)139) != 0) {
          break label839;
        }
        this.cb_head.setSelected(false);
        localObject = this.cb_head;
        if (!iMobile_AppGlobalVar.isPad) {
          break label836;
        }
        label390:
        ((CheckBox)localObject).setBackgroundResource(2130837770);
        this.ivaniHead.setText(2131165387);
      }
      label406:
      this.mHandler.removeMessages(7);
      if ((!this.bNoAlarm) || (DataGetter.g_datas((short)139) != 0)) {
        break label883;
      }
      j = 1;
      label433:
      if (j != 0)
      {
        if (DataGetter.g_datas((short)101) != 0) {
          break label891;
        }
        this.cb_position.setSelected(false);
        localObject = this.cb_position;
        if (!iMobile_AppGlobalVar.isPad) {
          break label888;
        }
        label464:
        ((CheckBox)localObject).setBackgroundResource(2130837782);
        this.ivaniPostion.setText(2131165390);
      }
      label481:
      this.cb_horn.setSelected(false);
      this.cb_lock.setChecked(false);
      this.cb_unlock.setChecked(false);
      this.ivaniLock.stopSlider();
      this.ivaniUnlock.stopSlider();
      i = DataGetter.g_datas((short)11);
      this.mListViews.remove(this.linearlayout3);
      if ((i == 1) && (k == 1)) {
        break label935;
      }
      this.card3.setVisibility(8);
      label559:
      i = MsgReceiver.g_doorLook;
      if ((!DataGetter.canDealTheftAlerm()) || ((i != 0) && (i != -1))) {
        break label960;
      }
      CarAlarmReciver();
      if ((this.c1 == this.card1.getVisibility()) && (this.c2 == this.card2.getVisibility()) && (this.c3 == this.card3.getVisibility())) {
        break label1179;
      }
      this.c1 = this.card1.getVisibility();
      this.c2 = this.card2.getVisibility();
      this.c3 = this.card3.getVisibility();
    }
    label669:
    label710:
    label716:
    label728:
    label762:
    label779:
    label815:
    label820:
    label825:
    label831:
    label836:
    label839:
    label883:
    label888:
    label891:
    label935:
    label960:
    label1179:
    for (boolean bool = true;; bool = false)
    {
      return bool;
      bool = false;
      break;
      this.Card1pic.setBackgroundResource(2130837760);
      this.Card1txt.setText(2131165379);
      this.Card1txt.setColors(-1, 0);
      this.ivaniAlarm.setText(2131165377);
      break label166;
      bool = false;
      break label182;
      this.card1.setVisibility(8);
      break label189;
      this.mListViews.add(this.linearlayout2);
      this.card2.setVisibility(0);
      localObject = this.card2_r1;
      if (1 == 0)
      {
        i = 8;
        ((RelativeLayout)localObject).setVisibility(i);
        localObject = this.card2_r2;
        if (1 != 0) {
          break label815;
        }
        i = 8;
        ((RelativeLayout)localObject).setVisibility(i);
        localObject = this.card2_r3;
        if ((n != 0) && (k != 0)) {
          break label820;
        }
      }
      for (i = 8;; i = 0)
      {
        ((RelativeLayout)localObject).setVisibility(i);
        break;
        i = 0;
        break label762;
        i = 0;
        break label779;
      }
      bool = false;
      break label319;
      i = 1;
      break label357;
      break label390;
      if (i == 0) {
        break label406;
      }
      this.cb_head.setSelected(true);
      this.ivaniHead.setText(2131165388);
      this.blinkTimeOut = 60;
      this.mHandler.sendEmptyMessageDelayed(6, 500L);
      break label406;
      j = 0;
      break label433;
      break label464;
      if (i == 0) {
        break label481;
      }
      this.cb_position.setSelected(true);
      this.ivaniPostion.setText(2131165391);
      this.blinkTimeOut = 60;
      this.mHandler.sendEmptyMessageDelayed(7, 500L);
      break label481;
      this.mListViews.add(this.linearlayout3);
      this.card3.setVisibility(0);
      break label559;
      this.Card3pic_alarmSts.setVisibility(4);
      this.Card3txt.setVisibility(0);
      this.bShowAlarm = false;
      this.Card3txt.setColors(-1, 0);
      if (i == 1)
      {
        this.Card3pic_lockSts.setVisibility(0);
        this.Card3pic_lockSts.setBackgroundResource(2130837797);
        this.Card3txt.setText(2131165399);
      }
      for (;;)
      {
        MsgReceiver.g_doorLook = -1;
        this.mHandler.removeMessages(20);
        this.mHandler.sendEmptyMessageDelayed(20, 2000L);
        break;
        if (i == 2)
        {
          this.Card3pic_lockSts.setVisibility(0);
          this.Card3pic_lockSts.setBackgroundResource(2130837797);
          this.Card3txt.setText(2131165400);
        }
        else if (i == 4)
        {
          this.Card3pic_lockSts.setVisibility(0);
          this.Card3pic_lockSts.setBackgroundResource(2130837798);
          this.Card3txt.setText(2131165401);
        }
        else if (i == 5)
        {
          this.Card3pic_lockSts.setVisibility(0);
          this.Card3pic_lockSts.setBackgroundResource(2130837798);
          this.Card3txt.setText(2131165402);
        }
        else
        {
          this.Card3pic_alarmSts.setVisibility(4);
          this.Card3txt.setVisibility(4);
        }
      }
    }
  }
  
  boolean posCanOn()
  {
    boolean bool2 = false;
    int i = DataGetter.g_datas((short)102);
    boolean bool1 = bool2;
    if (DataGetter.g_datas((short)127) != 3)
    {
      if (i != 6) {
        break label29;
      }
      bool1 = bool2;
    }
    for (;;)
    {
      return bool1;
      label29:
      bool1 = bool2;
      if (DataGetter.g_datas((short)139) == 0) {
        if ((DataGetter.g_datas((short)101) != 0) && (i != 0))
        {
          bool1 = bool2;
          if (i != 3) {}
        }
        else
        {
          bool1 = this.bNoAlarm;
        }
      }
    }
  }
  
  void setChecked(boolean paramBoolean, int paramInt)
  {
    iSlider[] arrayOfiSlider = new iSlider[6];
    arrayOfiSlider[0] = this.ivaniAlarm;
    arrayOfiSlider[1] = this.ivaniHead;
    arrayOfiSlider[2] = this.ivaniPostion;
    arrayOfiSlider[3] = this.ivaniHorn;
    arrayOfiSlider[4] = this.ivaniLock;
    arrayOfiSlider[5] = this.ivaniUnlock;
    this.mHandler.removeMessages(paramInt);
    if (paramBoolean)
    {
      arrayOfiSlider[paramInt].startSlider();
      this.mHandler.sendEmptyMessageDelayed(paramInt, 15000L);
    }
    for (;;)
    {
      return;
      arrayOfiSlider[paramInt].stopSlider();
    }
  }
  
  void setHeadBlink()
  {
    int j = 2130837770;
    if (this.blinkTimeOut <= 0)
    {
      DefMsg.g_datas[''] = 0;
      DefMsg.g_recdatas[''] = 0;
      this.cb_head.setSelected(false);
      this.mHandler.removeMessages(6);
      this.mHandler.sendEmptyMessageDelayed(1, 50L);
      return;
    }
    CheckBox localCheckBox = this.cb_head;
    int i;
    if (this.bBlink) {
      if (iMobile_AppGlobalVar.isPad)
      {
        i = 2130837772;
        label79:
        localCheckBox.setBackgroundResource(i);
        if (!this.bBlink) {
          break label136;
        }
      }
    }
    label136:
    for (boolean bool = false;; bool = true)
    {
      this.bBlink = bool;
      this.mHandler.sendEmptyMessageDelayed(6, 500L);
      break;
      i = 2130837772;
      break label79;
      i = j;
      if (!iMobile_AppGlobalVar.isPad) {
        break label79;
      }
      i = j;
      break label79;
    }
  }
  
  void setPagePoint(int paramInt)
  {
    int i = this.mListViews.size();
    Get_point(((View)this.mListViews.get(paramInt % i)).getId());
  }
  
  void setPosBlink()
  {
    int i = 2130837784;
    if (this.blinkTimeOut <= 0)
    {
      DefMsg.g_datas[101] = 0;
      DefMsg.g_recdatas[101] = 0;
      this.cb_position.setSelected(false);
      this.mHandler.removeMessages(7);
      this.mHandler.sendEmptyMessageDelayed(2, 50L);
      return;
    }
    CheckBox localCheckBox = this.cb_position;
    if (this.bBlink)
    {
      if (iMobile_AppGlobalVar.isPad) {}
      label73:
      localCheckBox.setBackgroundResource(i);
      if (!this.bBlink) {
        break label128;
      }
    }
    label128:
    for (boolean bool = false;; bool = true)
    {
      this.bBlink = bool;
      this.mHandler.sendEmptyMessageDelayed(7, 500L);
      break;
      if (iMobile_AppGlobalVar.isPad)
      {
        i = 2130837782;
        break label73;
      }
      i = 2130837782;
      break label73;
    }
  }
  
  public void showTitle()
  {
    if (this.card1.isChecked()) {
      this.mAct.SetTitleText(this.mAct.getString(2131165376));
    }
    for (;;)
    {
      return;
      if (this.card2.isChecked()) {
        this.mAct.SetTitleText(this.mAct.getString(2131165385));
      } else if (this.card3.isChecked()) {
        this.mAct.SetTitleText(this.mAct.getString(2131165396));
      } else {
        this.mAct.SetTitleText(this.mAct.getString(2131165317));
      }
    }
  }
  
  public class ViewPagAdapter
    extends PagerAdapter
  {
    Context mcContext;
    private List<View> mlstview;
    
    public ViewPagAdapter(List<View> paramList)
    {
      List localList;
      this.mlstview = localList;
      this.mcContext = paramList;
    }
    
    public void destroyItem(View paramView, int paramInt, Object paramObject)
    {
      if (MvControl.this.mListViews.size() > 3) {
        ((ViewPager)paramView).removeView((View)paramObject);
      }
    }
    
    public void finishUpdate(View paramView) {}
    
    public int getCount()
    {
      int i = 1;
      if (MvControl.this.mListViews.size() == 1) {}
      for (;;)
      {
        return i;
        i = 2000;
      }
    }
    
    public Object instantiateItem(View paramView, int paramInt)
    {
      paramView = (ViewPager)paramView;
      try
      {
        View localView = (View)MvControl.this.mListViews.get(paramInt % MvControl.this.mListViews.size());
        if (localView.getParent() == paramView) {
          paramView.removeView(localView);
        }
        paramView.addView(localView, 0, null);
      }
      catch (Exception paramView)
      {
        for (;;)
        {
          AppDealWifi.LogExceptionMsg(paramView);
        }
      }
      return MvControl.this.mListViews.get(paramInt % MvControl.this.mListViews.size());
    }
    
    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      if (paramView == paramObject) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
    
    public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader) {}
    
    public Parcelable saveState()
    {
      return null;
    }
    
    public void startUpdate(View paramView) {}
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\MvControl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */