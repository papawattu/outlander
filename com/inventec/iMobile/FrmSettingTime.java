package com.inventec.iMobile;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseOP.DataGetter;
import com.inventec.iMobile.baseOP.DealFile;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.baseStruct.DefMsg;
import kankan.wheel.widget.OnWheelClickedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;
import kankan.wheel.widget.adapters.WheelViewAdapter;

public class FrmSettingTime
  extends myActivity
{
  byte bkchgMode;
  byte[] bkpresetFH = new byte[2];
  byte[] bkpresetFM = new byte[2];
  byte[] bkpresetSH = new byte[2];
  byte[] bkpresetSM = new byte[2];
  MyButton btnOk;
  byte chgMode;
  byte[] presetFH;
  byte[] presetFM;
  byte[] presetSH;
  byte[] presetSM;
  private LinearLayout tl_btnbar;
  
  protected void btnOkOnClick()
  {
    MvSettingTime localMvSettingTime = (MvSettingTime)this.mCurMainView;
    if (localMvSettingTime != null) {
      localMvSettingTime.btnOkOnClick();
    }
  }
  
  public boolean canShowNavi()
  {
    return true;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    DefMsg.memcpy(this.bkpresetSH, 0, DefMsg.g_datas, 110, 2);
    DefMsg.memcpy(this.bkpresetSM, 0, DefMsg.g_datas, 112, 2);
    DefMsg.memcpy(this.bkpresetFH, 0, DefMsg.g_datas, 114, 2);
    DefMsg.memcpy(this.bkpresetFM, 0, DefMsg.g_datas, 116, 2);
    this.bkchgMode = DataGetter.g_datas((short)96);
    paramBundle = getIntent().getExtras();
    this.presetSH = paramBundle.getByteArray("presetSH");
    this.presetSM = paramBundle.getByteArray("presetSM");
    this.presetFH = paramBundle.getByteArray("presetFH");
    this.presetFM = paramBundle.getByteArray("presetFM");
    this.chgMode = paramBundle.getByte("chgMode");
    this.tl_btnbar = ((LinearLayout)SetBtnBar(2130903088, 50));
    this.btnOk = ((MyButton)this.tl_btnbar.findViewById(2131296262));
    enlargeViewToFitScreen(this.btnOk, true);
    this.btnOk.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FrmSettingTime.this.btnOkOnClick();
      }
    });
    this.btnOk.setEnabled(false);
    SetMainView(new MvSettingTime(this));
  }
  
  protected void onResume()
  {
    super.onResume();
    ShowNavi();
  }
  
  class MvSettingTime
    extends myMainView
  {
    private static final int MaxValue_Hour = 23;
    private static final int MaxValue_Minute = 5;
    private NumericWheelAdapter NWA_Used_edHour;
    private NumericWheelAdapter NWA_Used_edMin;
    private NumericWheelAdapter NWA_Used_stHour;
    private NumericWheelAdapter NWA_Used_stMin;
    int[] bkset1 = { -1, -1, -1, -1 };
    int[] bkset2 = { -1, -1, -1, -1 };
    int[] bkset3 = { -1, -1, -1, -1 };
    int btn_choose = 0;
    MyButton button_on1;
    MyButton button_on2;
    MyButton button_on3;
    boolean dealChanged = false;
    WheelView ed_hours;
    WheelView ed_mins;
    boolean flag_enable = true;
    boolean flag_enable2 = true;
    boolean flag_enable3 = true;
    boolean invilate_b1 = true;
    boolean invilate_b2 = true;
    boolean invilate_b3 = true;
    LinearLayout layoutWheel;
    LinearLayout layoutbotom;
    private boolean mbUsed = false;
    private myTimerWheelAdapter myTWA_notUsed_edHour;
    private myTimerWheelAdapter myTWA_notUsed_edMin;
    private myTimerWheelAdapter myTWA_notUsed_stHour;
    private myTimerWheelAdapter myTWA_notUsed_stMin;
    MyHandler myhd = new MyHandler();
    int[] set1 = { -1, -1, -1, -1 };
    int[] set2 = { -1, -1, -1, -1 };
    int[] set3 = { -1, -1, -1, -1 };
    WheelView st_hours;
    WheelView st_mins;
    AutoResizeTextView textHour;
    AutoResizeTextView textLocCHG;
    AutoResizeTextView textMin;
    String[] timeData = { "", "", "" };
    boolean valueable1 = false;
    boolean valueable2 = false;
    boolean valueable3 = false;
    
    public MvSettingTime(myActivity parammyActivity)
    {
      super(2130903081);
      FrmSettingTime.this.SetTitleText(parammyActivity.getString(2131165355));
      int j = parammyActivity.getValidScreenHeight();
      LinearLayout localLinearLayout = (LinearLayout)this.mView.findViewById(2131296549);
      parammyActivity.enlargeViewToFitScreen(localLinearLayout, false);
      int i = getViewSpaceVertical(localLinearLayout);
      this.button_on1 = ((MyButton)this.mView.findViewById(2131296550));
      this.button_on2 = ((MyButton)this.mView.findViewById(2131296551));
      this.button_on3 = ((MyButton)this.mView.findViewById(2131296552));
      parammyActivity.enlargeViewToFitScreen(this.button_on1, true);
      parammyActivity.enlargeViewToFitScreen(this.button_on2, true);
      parammyActivity.enlargeViewToFitScreen(this.button_on3, true);
      int k = (int)(3.0F * FrmSettingTime.this.scrScale);
      this.button_on1.setPadding(k, 0, k, 0);
      this.button_on2.setPadding(k, 0, k, 0);
      this.button_on3.setPadding(k, 0, k, 0);
      this.button_on1.setSingle();
      this.button_on2.setSingle();
      this.button_on3.setSingle();
      if (iMobile_AppGlobalVar.scaledDensity >= 2.0D)
      {
        this.button_on1.chengeBg(2130837594, -16777216, 2130837595, -1);
        this.button_on2.chengeBg(2130837596, -16777216, 2130837597, -1);
        this.button_on3.chengeBg(2130837600, -16777216, 2130837601, -1);
      }
      for (;;)
      {
        String str = this.mAct.getResources().getString(2131165346);
        Object localObject = this.mAct.getResources().getString(2131165347);
        this$1 = this.mAct.getResources().getString(2131165348);
        this.button_on1.setText(str);
        this.button_on2.setText((CharSequence)localObject);
        this.button_on3.setText(FrmSettingTime.this);
        this.button_on1.setNumber(1);
        this.button_on2.setNumber(2);
        this.button_on3.setNumber(3);
        this$1 = (LinearLayout)this.mView.findViewById(2131296554);
        parammyActivity.enlargeViewToFitScreen(FrmSettingTime.this, false);
        int m = getViewSpaceVertical(FrmSettingTime.this);
        localObject = (AutoResizeTextView)this.mView.findViewById(2131296555);
        parammyActivity.enlargeViewToFitScreen((View)localObject, true);
        ((AutoResizeTextView)localObject).setSingle();
        ((AutoResizeTextView)localObject).setHVType(1, 1);
        localObject = (AutoResizeTextView)this.mView.findViewById(2131296556);
        parammyActivity.enlargeViewToFitScreen((View)localObject, true);
        ((AutoResizeTextView)localObject).setSingle();
        ((AutoResizeTextView)localObject).setHVType(1, 1);
        this.textHour = ((AutoResizeTextView)this.mView.findViewById(2131296564));
        parammyActivity.enlargeViewToFitScreen(this.textHour, true);
        this.textHour.setSingle();
        localObject = (AutoResizeTextView)this.mView.findViewById(2131296565);
        parammyActivity.enlargeViewToFitScreen((View)localObject, true);
        ((AutoResizeTextView)localObject).setSingle();
        this.textMin = ((AutoResizeTextView)this.mView.findViewById(2131296566));
        parammyActivity.enlargeViewToFitScreen(this.textMin, true);
        this.textMin.setSingle();
        localObject = (AutoResizeTextView)this.mView.findViewById(2131296567);
        parammyActivity.enlargeViewToFitScreen((View)localObject, true);
        ((AutoResizeTextView)localObject).setSingle();
        localObject = (AutoResizeTextView)this.mView.findViewById(2131296563);
        parammyActivity.enlargeViewToFitScreen((View)localObject, true);
        ((AutoResizeTextView)localObject).setSingle();
        this.textLocCHG = ((AutoResizeTextView)this.mView.findViewById(2131296569));
        parammyActivity.enlargeViewToFitScreen(this.textLocCHG, true);
        parammyActivity.enlargeViewToFitScreen((LinearLayout)this.mView.findViewById(2131296275), false);
        localObject = (LinearLayout)this.mView.findViewById(2131296559);
        parammyActivity.enlargeViewToFitScreen((View)localObject, false);
        int n = getViewSpaceVertical((View)localObject);
        parammyActivity.enlargeViewToFitScreen((WheelView)this.mView.findViewById(2131296557), false);
        parammyActivity.enlargeViewToFitScreen((WheelView)this.mView.findViewById(2131296558), false);
        parammyActivity.enlargeViewToFitScreen((WheelView)this.mView.findViewById(2131296560), false);
        parammyActivity.enlargeViewToFitScreen((WheelView)this.mView.findViewById(2131296561), false);
        localObject = (LinearLayout)this.mView.findViewById(2131296562);
        parammyActivity.enlargeViewToFitScreen((View)localObject, false);
        k = getViewSpaceVertical((View)localObject);
        this.layoutbotom = ((LinearLayout)this.mView.findViewById(2131296568));
        parammyActivity.enlargeViewToFitScreen(this.layoutbotom, false);
        i = j - i - m - n - k - getViewSpaceVertical(this.layoutbotom);
        if (i > 0)
        {
          enlargeViewHeight(localLinearLayout, GetViewParaHeight(localLinearLayout) + i / 4);
          enlargeViewHeight(FrmSettingTime.this, GetViewParaHeight(FrmSettingTime.this) + i / 4);
          enlargeViewHeight((View)localObject, GetViewParaHeight((View)localObject) + i / 8);
          addMyViewMargin((View)localObject, 0, i / 8, 0, 0);
          enlargeViewHeight(this.layoutbotom, GetViewParaHeight(this.layoutbotom) + i - i / 4 * 3);
        }
        this.layoutWheel = ((LinearLayout)this.mView.findViewById(2131296274));
        parammyActivity.enlargeViewToFitScreen(this.layoutWheel, false);
        this.button_on1.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            FrmSettingTime.MvSettingTime.this.refreshWheel(0, FrmSettingTime.MvSettingTime.this.set1);
          }
        });
        this.button_on2.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            FrmSettingTime.MvSettingTime.this.refreshWheel(1, FrmSettingTime.MvSettingTime.this.set2);
          }
        });
        this.button_on3.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            FrmSettingTime.MvSettingTime.this.refreshWheel(2, FrmSettingTime.MvSettingTime.this.set3);
          }
        });
        initWheels();
        loadDatas();
        return;
        this.button_on1.chengeBg(2130837602, -16777216, 2130837605, -1);
        this.button_on2.chengeBg(2130837603, -16777216, 2130837606, -1);
        this.button_on3.chengeBg(2130837604, -16777216, 2130837607, -1);
      }
    }
    
    private void doTimeTest(WheelView paramWheelView)
    {
      int k = this.st_hours.getCurrentItem();
      int j = this.st_mins.getCurrentItem();
      int i = this.ed_hours.getCurrentItem();
      int i1 = this.ed_mins.getCurrentItem();
      int n = this.st_hours.getViewAdapter().getItemsCount();
      int m = this.st_mins.getViewAdapter().getItemsCount();
      if ((k == i) && (j == i1))
      {
        if ((paramWheelView != this.st_hours) && (paramWheelView != this.st_mins)) {
          break label229;
        }
        if ((k != n - 1) || (j != m - 1)) {
          break label183;
        }
        this.ed_hours.setCurrentItem(0, true);
        this.ed_mins.setCurrentItem(0, true);
      }
      for (;;)
      {
        paramWheelView = getCurSet();
        paramWheelView[0] = this.st_hours.getCurrentItem();
        paramWheelView[1] = this.st_mins.getCurrentItem();
        paramWheelView[2] = this.ed_hours.getCurrentItem();
        paramWheelView[3] = this.ed_mins.getCurrentItem();
        this.myhd.sendEmptyMessageDelayed(1, 500L);
        return;
        label183:
        if (j == m - 1)
        {
          this.ed_hours.setCurrentItem(i + 1, true);
          this.ed_mins.setCurrentItem(0, true);
        }
        else
        {
          this.ed_mins.setCurrentItem(i1 + 1, true);
          continue;
          label229:
          if ((i == 0) && (i1 == 0))
          {
            this.st_hours.setCurrentItem(n - 1, true);
            this.st_mins.setCurrentItem(m - 1, true);
          }
          else if (i1 == 0)
          {
            this.st_hours.setCurrentItem(k - 1, true);
            this.st_mins.setCurrentItem(m - 1, true);
          }
          else
          {
            this.st_mins.setCurrentItem(j - 1, true);
          }
        }
      }
    }
    
    private void initWheels()
    {
      this.myTWA_notUsed_stHour = new myTimerWheelAdapter(this.mAct, 23);
      this.myTWA_notUsed_stMin = new myTimerWheelAdapter(this.mAct, 5, "%d0");
      this.myTWA_notUsed_edHour = new myTimerWheelAdapter(this.mAct, 23);
      this.myTWA_notUsed_edMin = new myTimerWheelAdapter(this.mAct, 5, "%d0");
      this.NWA_Used_stHour = new NumericWheelAdapter(this.mAct, 0, 23);
      this.NWA_Used_stMin = new NumericWheelAdapter(this.mAct, 0, 5, "%d0");
      this.NWA_Used_edHour = new NumericWheelAdapter(this.mAct, 0, 23);
      this.NWA_Used_edMin = new NumericWheelAdapter(this.mAct, 0, 5, "%d0");
      int i = (int)(this.mAct.px2dip(this.myTWA_notUsed_stHour.getTextSize()) * this.mAct.getScrScale());
      this.myTWA_notUsed_stHour.setTextSize(i);
      this.myTWA_notUsed_stMin.setTextSize(i);
      this.myTWA_notUsed_edHour.setTextSize(i);
      this.myTWA_notUsed_edMin.setTextSize(i);
      this.NWA_Used_stHour.setTextSize(i);
      this.NWA_Used_stMin.setTextSize(i);
      this.NWA_Used_edHour.setTextSize(i);
      this.NWA_Used_edMin.setTextSize(i);
      this.st_hours = ((WheelView)this.mView.findViewById(2131296557));
      this.st_hours.setCyclic(true);
      this.st_mins = ((WheelView)this.mView.findViewById(2131296558));
      this.st_mins.setCyclic(true);
      this.ed_hours = ((WheelView)this.mView.findViewById(2131296560));
      this.ed_hours.setCyclic(true);
      this.ed_mins = ((WheelView)this.mView.findViewById(2131296561));
      this.ed_mins.setCyclic(true);
      Object localObject = new OnWheelClickedListener()
      {
        public void onItemClicked(WheelView paramAnonymousWheelView, int paramAnonymousInt)
        {
          paramAnonymousWheelView.setCurrentItem(paramAnonymousInt, true);
        }
      };
      this.st_hours.addClickingListener((OnWheelClickedListener)localObject);
      this.st_mins.addClickingListener((OnWheelClickedListener)localObject);
      this.ed_hours.addClickingListener((OnWheelClickedListener)localObject);
      this.ed_mins.addClickingListener((OnWheelClickedListener)localObject);
      localObject = new OnWheelScrollListener()
      {
        public void onScrollingFinished(WheelView paramAnonymousWheelView)
        {
          FrmSettingTime.MvSettingTime.this.markWheelsUsed();
          FrmSettingTime.MvSettingTime.this.doTimeTest(paramAnonymousWheelView);
        }
        
        public void onScrollingStarted(WheelView paramAnonymousWheelView) {}
      };
      this.st_hours.addScrollingListener((OnWheelScrollListener)localObject);
      this.st_mins.addScrollingListener((OnWheelScrollListener)localObject);
      this.ed_hours.addScrollingListener((OnWheelScrollListener)localObject);
      this.ed_mins.addScrollingListener((OnWheelScrollListener)localObject);
    }
    
    private void markWheelsUsed()
    {
      if (this.mbUsed) {}
      for (;;)
      {
        return;
        this.mbUsed = true;
        int k = this.st_hours.getCurrentItem();
        int m = this.st_mins.getCurrentItem();
        int j = this.ed_hours.getCurrentItem();
        int i = this.ed_mins.getCurrentItem();
        this.st_hours.setViewAdapter(this.NWA_Used_stHour);
        this.st_mins.setViewAdapter(this.NWA_Used_stMin);
        this.ed_hours.setViewAdapter(this.NWA_Used_edHour);
        this.ed_mins.setViewAdapter(this.NWA_Used_edMin);
        if (k == 0)
        {
          this.st_hours.setCurrentItem(0);
          label102:
          if (m != 0) {
            break label155;
          }
          this.st_mins.setCurrentItem(0);
          label115:
          if (j != 0) {
            break label169;
          }
          this.ed_hours.setCurrentItem(0);
        }
        for (;;)
        {
          if (i != 0) {
            break label182;
          }
          this.ed_mins.setCurrentItem(0);
          break;
          this.st_hours.setCurrentItem(k - 1);
          break label102;
          label155:
          this.st_mins.setCurrentItem(m - 1);
          break label115;
          label169:
          this.ed_hours.setCurrentItem(j - 1);
        }
        label182:
        this.ed_mins.setCurrentItem(i - 1);
      }
    }
    
    private void resetWheels()
    {
      this.st_hours.setViewAdapter(this.myTWA_notUsed_stHour);
      this.st_mins.setViewAdapter(this.myTWA_notUsed_stMin);
      this.ed_hours.setViewAdapter(this.myTWA_notUsed_edHour);
      this.ed_mins.setViewAdapter(this.myTWA_notUsed_edMin);
      this.st_hours.setCurrentItem(0);
      this.st_mins.setCurrentItem(0);
      this.ed_hours.setCurrentItem(0);
      this.ed_mins.setCurrentItem(0);
      this.mbUsed = false;
    }
    
    private void setWheels(int[] paramArrayOfInt)
    {
      if (!this.mbUsed) {
        markWheelsUsed();
      }
      this.st_hours.setCurrentItem(paramArrayOfInt[0]);
      this.st_mins.setCurrentItem(paramArrayOfInt[1]);
      this.ed_hours.setCurrentItem(paramArrayOfInt[2]);
      this.ed_mins.setCurrentItem(paramArrayOfInt[3]);
    }
    
    public void PopupHelpInfo()
    {
      Intent localIntent = new Intent(this.mAct, BxShowHelp.class);
      Bundle localBundle = new Bundle();
      localBundle.putInt("CardNo", 24);
      localIntent.putExtras(localBundle);
      this.mAct.startActivityForResult(localIntent, 101);
    }
    
    public void Refresh()
    {
      loadDatas();
    }
    
    public void Update()
    {
      if ((DefMsg.memcmp(FrmSettingTime.this.bkpresetSH, 0, DefMsg.g_datas, 110, 2)) && (DefMsg.memcmp(FrmSettingTime.this.bkpresetSM, 0, DefMsg.g_datas, 112, 2)) && (DefMsg.memcmp(FrmSettingTime.this.bkpresetFH, 0, DefMsg.g_datas, 114, 2)) && (DefMsg.memcmp(FrmSettingTime.this.bkpresetFM, 0, DefMsg.g_datas, 116, 2)) && (FrmSettingTime.this.bkchgMode == DataGetter.g_datas((short)96))) {
        setcharttingTimer();
      }
      for (;;)
      {
        return;
        DefMsg.memcpy(FrmSettingTime.this.bkpresetSH, 0, DefMsg.g_datas, 110, 2);
        DefMsg.memcpy(FrmSettingTime.this.bkpresetSM, 0, DefMsg.g_datas, 112, 2);
        DefMsg.memcpy(FrmSettingTime.this.bkpresetFH, 0, DefMsg.g_datas, 114, 2);
        DefMsg.memcpy(FrmSettingTime.this.bkpresetFM, 0, DefMsg.g_datas, 116, 2);
        FrmSettingTime.this.bkchgMode = DataGetter.g_datas((short)96);
        DefMsg.memcpy(FrmSettingTime.this.presetSH, 0, FrmSettingTime.this.bkpresetSH, 0, 2);
        DefMsg.memcpy(FrmSettingTime.this.presetSM, 0, FrmSettingTime.this.bkpresetSM, 0, 2);
        DefMsg.memcpy(FrmSettingTime.this.presetFH, 0, FrmSettingTime.this.bkpresetFH, 0, 2);
        DefMsg.memcpy(FrmSettingTime.this.presetFM, 0, FrmSettingTime.this.bkpresetFM, 0, 2);
        FrmSettingTime.this.chgMode = FrmSettingTime.this.bkchgMode;
        Refresh();
      }
    }
    
    public void btnOkOnClick()
    {
      saveDatas();
      Object localObject = new byte['Ā'];
      if (DealFile.loaddata(this.mAct, FrmSetting_Charge.fnsav, (byte[])localObject))
      {
        DefMsg.memcpy((byte[])localObject, 45, FrmSettingTime.this.presetSH, 0, 2);
        DefMsg.memcpy((byte[])localObject, 47, FrmSettingTime.this.presetSM, 0, 2);
        DefMsg.memcpy((byte[])localObject, 49, FrmSettingTime.this.presetFH, 0, 2);
        DefMsg.memcpy((byte[])localObject, 51, FrmSettingTime.this.presetFM, 0, 2);
        localObject[53] = FrmSettingTime.this.chgMode;
        DealFile.savedata(this.mAct, FrmSetting_Charge.fnsav, (byte[])localObject);
      }
      localObject = new Intent();
      ((Intent)localObject).putExtra("presetSH", FrmSettingTime.this.presetSH);
      ((Intent)localObject).putExtra("presetSM", FrmSettingTime.this.presetSM);
      ((Intent)localObject).putExtra("presetFH", FrmSettingTime.this.presetFH);
      ((Intent)localObject).putExtra("presetFM", FrmSettingTime.this.presetFM);
      ((Intent)localObject).putExtra("chgMode", FrmSettingTime.this.chgMode);
      this.mAct.setResult(1, (Intent)localObject);
      this.mAct.finish();
    }
    
    int[] getCurSet()
    {
      int[] arrayOfInt = this.set1;
      if (this.btn_choose == 1) {
        arrayOfInt = this.set2;
      }
      if (this.btn_choose == 2) {
        arrayOfInt = this.set3;
      }
      return arrayOfInt;
    }
    
    void initTimeSet(int[] paramArrayOfInt)
    {
      paramArrayOfInt[3] = 0;
      paramArrayOfInt[2] = 0;
      paramArrayOfInt[1] = 0;
      paramArrayOfInt[0] = 0;
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
    
    void loadDatas()
    {
      loadDate(1, this.set1, this.bkset1);
      loadDate(2, this.set2, this.bkset2);
      loadDate(3, this.set3, this.bkset3);
      refreshWheel(this.btn_choose, getCurSet());
      this.myhd.sendEmptyMessageDelayed(1, 500L);
      FrmSettingTime.this.btnOk.setEnabled(false);
      setcharttingTimer();
    }
    
    void loadDate(int paramInt, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
    {
      int i = DealMsg.GetPmHValue(paramInt, FrmSettingTime.this.presetSH);
      paramArrayOfInt1[0] = i;
      paramArrayOfInt2[0] = i;
      i = DealMsg.GetPmMValue(paramInt, FrmSettingTime.this.presetSM);
      paramArrayOfInt1[1] = i;
      paramArrayOfInt2[1] = i;
      i = DealMsg.GetPmHValue(paramInt, FrmSettingTime.this.presetFH);
      paramArrayOfInt1[2] = i;
      paramArrayOfInt2[2] = i;
      paramInt = DealMsg.GetPmMValue(paramInt, FrmSettingTime.this.presetFM);
      paramArrayOfInt1[3] = paramInt;
      paramArrayOfInt2[3] = paramInt;
    }
    
    void refreshWheel(int paramInt, int[] paramArrayOfInt)
    {
      boolean bool2 = false;
      this.btn_choose = paramInt;
      MyButton localMyButton = this.button_on1;
      boolean bool1;
      if (this.btn_choose == 0)
      {
        bool1 = true;
        localMyButton.setToggle(bool1);
        localMyButton = this.button_on2;
        if (this.btn_choose != 1) {
          break label108;
        }
        bool1 = true;
        label45:
        localMyButton.setToggle(bool1);
        localMyButton = this.button_on3;
        bool1 = bool2;
        if (this.btn_choose == 2) {
          bool1 = true;
        }
        localMyButton.setToggle(bool1);
        if (isTimeRight(paramArrayOfInt)) {
          break label114;
        }
        resetWheels();
      }
      for (;;)
      {
        this.myhd.sendEmptyMessageDelayed(1, 50L);
        return;
        bool1 = false;
        break;
        label108:
        bool1 = false;
        break label45;
        label114:
        setWheels(paramArrayOfInt);
      }
    }
    
    void saveData(int paramInt, int[] paramArrayOfInt)
    {
      DealMsg.SetPmHValue(paramInt, (short)paramArrayOfInt[0], FrmSettingTime.this.presetSH);
      DealMsg.SetPmMValue(paramInt, (short)paramArrayOfInt[1], FrmSettingTime.this.presetSM);
      DealMsg.SetPmHValue(paramInt, (short)paramArrayOfInt[2], FrmSettingTime.this.presetFH);
      DealMsg.SetPmMValue(paramInt, (short)paramArrayOfInt[3], FrmSettingTime.this.presetFM);
    }
    
    void saveDatas()
    {
      saveData(1, this.set1);
      saveData(2, this.set2);
      saveData(3, this.set3);
    }
    
    void setcharttingTimer()
    {
      int i;
      if (DataGetter.g_datas() == 1)
      {
        this.textLocCHG.setVisibility(0);
        Object localObject = new StringBuilder(String.valueOf(this.mAct.getString(2131165361))).append(" ");
        myActivity localmyActivity = this.mAct;
        if (FrmSettingTime.this.chgMode == 3)
        {
          i = 2131165351;
          localObject = localmyActivity.getString(i);
          this.textLocCHG.setText((CharSequence)localObject);
        }
      }
      for (;;)
      {
        return;
        i = 2131165352;
        break;
        this.textLocCHG.setVisibility(4);
      }
    }
    
    public void settimelenth()
    {
      int i = 0;
      int j = 0;
      Object localObject = getCurSet();
      if (isTimeRight((int[])localObject))
      {
        int i1 = localObject[0];
        int n = localObject[1] * 10;
        int k = localObject[2];
        int m = localObject[3] * 10;
        i = k;
        j = m;
        if (m < n)
        {
          j = m + 60;
          i = k - 1;
        }
        k = i - i1;
        i = k;
        if (k < 0) {
          i = k + 24;
        }
        j -= n;
      }
      if (i > 9)
      {
        localObject = i;
        this.textHour.setText((CharSequence)localObject);
        if (j <= 9) {
          break label176;
        }
      }
      label176:
      for (localObject = j;; localObject = "0" + j)
      {
        this.textMin.setText((CharSequence)localObject);
        testEnable();
        return;
        localObject = "  " + i;
        break;
      }
    }
    
    void testEnable()
    {
      boolean bool = false;
      if ((this.bkset1[0] == this.set1[0]) && (this.bkset1[1] == this.set1[1]) && (this.bkset1[2] == this.set1[2]) && (this.bkset1[3] == this.set1[3]) && (this.bkset2[0] == this.set2[0]) && (this.bkset2[1] == this.set2[1]) && (this.bkset2[2] == this.set2[2]) && (this.bkset2[3] == this.set2[3]) && (this.bkset3[0] == this.set3[0]) && (this.bkset3[1] == this.set3[1]) && (this.bkset3[2] == this.set3[2]) && (this.bkset3[3] == this.set3[3])) {}
      for (;;)
      {
        FrmSettingTime.this.btnOk.setEnabled(bool);
        return;
        bool = true;
      }
    }
    
    class MyHandler
      extends Handler
    {
      MyHandler() {}
      
      public void handleMessage(Message paramMessage)
      {
        FrmSettingTime.MvSettingTime.this.settimelenth();
        FrmSettingTime.MvSettingTime.this.dealChanged = false;
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\FrmSettingTime.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */