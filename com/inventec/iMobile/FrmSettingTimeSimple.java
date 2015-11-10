package com.inventec.iMobile;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseOP.DealFile;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.baseStruct.DefMsg;
import kankan.wheel.widget.OnWheelClickedListener;
import kankan.wheel.widget.OnWheelScrollListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;

public class FrmSettingTimeSimple
  extends myActivity
{
  byte[] bkpresetSH = new byte[2];
  byte[] bkpresetSM = new byte[2];
  MyButton btnOk;
  byte[] presetSH;
  byte[] presetSM;
  private LinearLayout tl_btnbar;
  
  protected void btnOkOnClick()
  {
    MvSettingTimeSimple localMvSettingTimeSimple = (MvSettingTimeSimple)this.mCurMainView;
    if (localMvSettingTimeSimple != null) {
      localMvSettingTimeSimple.btnOkOnClick();
    }
  }
  
  public boolean canShowNavi()
  {
    return true;
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    DefMsg.memcpy(this.bkpresetSH, 0, DefMsg.g_datas, 121, 2);
    DefMsg.memcpy(this.bkpresetSM, 0, DefMsg.g_datas, 123, 2);
    paramBundle = getIntent().getExtras();
    this.presetSH = paramBundle.getByteArray("presetSH");
    this.presetSM = paramBundle.getByteArray("presetSM");
    this.tl_btnbar = ((LinearLayout)SetBtnBar(2130903088, 50));
    this.btnOk = ((MyButton)this.tl_btnbar.findViewById(2131296262));
    enlargeViewToFitScreen(this.btnOk, true);
    this.btnOk.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FrmSettingTimeSimple.this.btnOkOnClick();
      }
    });
    this.btnOk.setEnabled(false);
    SetMainView(new MvSettingTimeSimple(this));
  }
  
  protected void onResume()
  {
    super.onResume();
    ShowNavi();
  }
  
  class MvSettingTimeSimple
    extends myMainView
  {
    private static final int MaxValue_Hour = 23;
    private static final int MaxValue_Minute = 5;
    private NumericWheelAdapter NWA_Used_stHour;
    private NumericWheelAdapter NWA_Used_stMin;
    int[] bkset1 = new int[2];
    int[] bkset2 = new int[2];
    int[] bkset3 = new int[2];
    int btn_choose = 0;
    MyButton button_on1;
    MyButton button_on2;
    MyButton button_on3;
    boolean flag_enable = true;
    boolean flag_enable2 = true;
    boolean flag_enable3 = true;
    boolean invilate_b1 = true;
    boolean invilate_b2 = true;
    boolean invilate_b3 = true;
    RelativeLayout layoutWheel;
    LinearLayout layoutbt;
    LinearLayout layoutinter;
    private boolean mbUsed = false;
    private myTimerWheelAdapter myTWA_notUsed_stHour;
    private myTimerWheelAdapter myTWA_notUsed_stMin;
    int[] set1 = new int[2];
    int[] set2 = new int[2];
    int[] set3 = new int[2];
    WheelView st_hours;
    WheelView st_mins;
    TextView text_start;
    String[] timeData = { "", "", "" };
    
    public MvSettingTimeSimple(myActivity parammyActivity)
    {
      super(2130903082);
      FrmSettingTimeSimple.this.SetTitleText(parammyActivity.getString(2131165368));
      int i = parammyActivity.getValidScreenHeight();
      this.layoutbt = ((LinearLayout)this.mView.findViewById(2131296549));
      parammyActivity.enlargeViewHeightWidth(this.layoutbt);
      int j = getViewSpaceVertical(this.layoutbt);
      this.button_on1 = ((MyButton)this.mView.findViewById(2131296550));
      this.button_on2 = ((MyButton)this.mView.findViewById(2131296551));
      this.button_on3 = ((MyButton)this.mView.findViewById(2131296552));
      parammyActivity.enlargeViewToFitScreen(this.button_on1, true);
      parammyActivity.enlargeViewToFitScreen(this.button_on2, true);
      parammyActivity.enlargeViewToFitScreen(this.button_on3, true);
      int k = (int)(3.0F * FrmSettingTimeSimple.this.scrScale);
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
        String str2 = this.mAct.getResources().getString(2131165346);
        String str1 = this.mAct.getResources().getString(2131165347);
        this$1 = this.mAct.getResources().getString(2131165348);
        this.button_on1.setText(str2);
        this.button_on2.setText(str1);
        this.button_on3.setText(FrmSettingTimeSimple.this);
        this.button_on1.setNumber(1);
        this.button_on2.setNumber(2);
        this.button_on3.setNumber(3);
        this$1 = (LinearLayout)this.mView.findViewById(2131296554);
        parammyActivity.enlargeViewToFitScreen(FrmSettingTimeSimple.this, false);
        k = getViewSpaceVertical(FrmSettingTimeSimple.this);
        this.text_start = ((TextView)this.mView.findViewById(2131296555));
        parammyActivity.enlargeViewToFitScreen(this.text_start, true);
        this.layoutWheel = ((RelativeLayout)this.mView.findViewById(2131296274));
        this.layoutinter = ((LinearLayout)this.mView.findViewById(2131296275));
        parammyActivity.enlargeViewToFitScreen(this.layoutinter, false);
        parammyActivity.enlargeViewToFitScreen(this.layoutWheel, false);
        i = i - j - k - getViewSpaceVertical(this.layoutWheel);
        enlargeViewHeight(this.layoutbt, GetViewParaHeight(this.layoutbt) + i / 4);
        addMyViewMargin(FrmSettingTimeSimple.this, i / 4, 0, 0, 0);
        addMyViewMargin(this.layoutWheel, 0, i / 4, 0, 0);
        addMyViewMargin((LinearLayout)this.mView.findViewById(2131296553), 0, i / 4, 0, 0);
        this.button_on1.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            FrmSettingTimeSimple.MvSettingTimeSimple.this.refreshWheel(0, FrmSettingTimeSimple.MvSettingTimeSimple.this.set1);
          }
        });
        this.button_on2.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            FrmSettingTimeSimple.MvSettingTimeSimple.this.refreshWheel(1, FrmSettingTimeSimple.MvSettingTimeSimple.this.set2);
          }
        });
        this.button_on3.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            FrmSettingTimeSimple.MvSettingTimeSimple.this.refreshWheel(2, FrmSettingTimeSimple.MvSettingTimeSimple.this.set3);
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
    
    private void doTimeTest()
    {
      int[] arrayOfInt = getCurSet();
      arrayOfInt[0] = this.st_hours.getCurrentItem();
      arrayOfInt[1] = this.st_mins.getCurrentItem();
      if ((this.bkset1[0] == this.set1[0]) && (this.bkset1[1] == this.set1[1]) && (this.bkset2[0] == this.set2[0]) && (this.bkset2[1] == this.set2[1]) && (this.bkset3[0] == this.set3[0]) && (this.bkset3[1] == this.set3[1])) {
        FrmSettingTimeSimple.this.btnOk.setEnabled(false);
      }
      for (;;)
      {
        return;
        FrmSettingTimeSimple.this.btnOk.setEnabled(true);
      }
    }
    
    private void initWheels()
    {
      this.myTWA_notUsed_stHour = new myTimerWheelAdapter(this.mAct, 23);
      this.myTWA_notUsed_stMin = new myTimerWheelAdapter(this.mAct, 5, "%d0");
      this.NWA_Used_stHour = new NumericWheelAdapter(this.mAct, 0, 23);
      this.NWA_Used_stMin = new NumericWheelAdapter(this.mAct, 0, 5, "%d0");
      this.st_hours = ((WheelView)this.mView.findViewById(2131296276));
      this.st_hours.setCyclic(true);
      this.st_mins = ((WheelView)this.mView.findViewById(2131296277));
      this.st_mins.setCyclic(true);
      this.mAct.enlargeViewToFitScreen(this.st_hours, false);
      this.mAct.enlargeViewToFitScreen(this.st_mins, false);
      int i = (int)(this.mAct.px2dip(this.myTWA_notUsed_stHour.getTextSize()) * this.mAct.getScrScale());
      this.myTWA_notUsed_stHour.setTextSize(i);
      this.myTWA_notUsed_stMin.setTextSize(i);
      this.NWA_Used_stHour.setTextSize(i);
      this.NWA_Used_stMin.setTextSize(i);
      Object localObject = new OnWheelClickedListener()
      {
        public void onItemClicked(WheelView paramAnonymousWheelView, int paramAnonymousInt)
        {
          paramAnonymousWheelView.setCurrentItem(paramAnonymousInt, true);
        }
      };
      this.st_hours.addClickingListener((OnWheelClickedListener)localObject);
      this.st_mins.addClickingListener((OnWheelClickedListener)localObject);
      localObject = new OnWheelScrollListener()
      {
        public void onScrollingFinished(WheelView paramAnonymousWheelView)
        {
          FrmSettingTimeSimple.MvSettingTimeSimple.this.markWheelsUsed();
          FrmSettingTimeSimple.MvSettingTimeSimple.this.doTimeTest();
        }
        
        public void onScrollingStarted(WheelView paramAnonymousWheelView) {}
      };
      this.st_hours.addScrollingListener((OnWheelScrollListener)localObject);
      this.st_mins.addScrollingListener((OnWheelScrollListener)localObject);
    }
    
    private void markWheelsUsed()
    {
      if (this.mbUsed) {}
      for (;;)
      {
        return;
        this.mbUsed = true;
        int j = this.st_hours.getCurrentItem();
        int i = this.st_mins.getCurrentItem();
        this.st_hours.setViewAdapter(this.NWA_Used_stHour);
        this.st_mins.setViewAdapter(this.NWA_Used_stMin);
        if (j == 0) {
          this.st_hours.setCurrentItem(0);
        }
        for (;;)
        {
          if (i != 0) {
            break label91;
          }
          this.st_mins.setCurrentItem(0);
          break;
          this.st_hours.setCurrentItem(j - 1);
        }
        label91:
        this.st_mins.setCurrentItem(i - 1);
      }
    }
    
    private void resetWheels()
    {
      this.st_hours.setViewAdapter(this.myTWA_notUsed_stHour);
      this.st_mins.setViewAdapter(this.myTWA_notUsed_stMin);
      this.st_hours.setCurrentItem(0);
      this.st_mins.setCurrentItem(0);
      this.mbUsed = false;
    }
    
    private void setWheels(int[] paramArrayOfInt)
    {
      if (!this.mbUsed) {
        markWheelsUsed();
      }
      this.st_hours.setCurrentItem(paramArrayOfInt[0]);
      this.st_mins.setCurrentItem(paramArrayOfInt[1]);
    }
    
    public void PopupHelpInfo()
    {
      Intent localIntent = new Intent(this.mAct, BxShowHelp.class);
      Bundle localBundle = new Bundle();
      localBundle.putInt("CardNo", 28);
      localIntent.putExtras(localBundle);
      this.mAct.startActivityForResult(localIntent, 101);
    }
    
    public void Refresh()
    {
      if ((DefMsg.memcmp(FrmSettingTimeSimple.this.bkpresetSH, 0, DefMsg.g_datas, 121, 2)) && (DefMsg.memcmp(FrmSettingTimeSimple.this.bkpresetSM, 0, DefMsg.g_datas, 123, 2))) {}
      for (;;)
      {
        return;
        DefMsg.memcpy(FrmSettingTimeSimple.this.bkpresetSH, 0, DefMsg.g_datas, 121, 2);
        DefMsg.memcpy(FrmSettingTimeSimple.this.bkpresetSM, 0, DefMsg.g_datas, 123, 2);
        DefMsg.memcpy(FrmSettingTimeSimple.this.presetSH, 0, FrmSettingTimeSimple.this.bkpresetSH, 0, 2);
        DefMsg.memcpy(FrmSettingTimeSimple.this.presetSM, 0, FrmSettingTimeSimple.this.bkpresetSM, 0, 2);
        loadDatas();
      }
    }
    
    public void btnOkOnClick()
    {
      saveDatas();
      Object localObject = new byte['Ā'];
      if (DealFile.loaddata(this.mAct, FrmSetting_AirConditioning.fnsav, (byte[])localObject))
      {
        DefMsg.memcpy((byte[])localObject, 25, FrmSettingTimeSimple.this.presetSH, 0, 2);
        DefMsg.memcpy((byte[])localObject, 27, FrmSettingTimeSimple.this.presetSM, 0, 2);
        DealFile.savedata(this.mAct, FrmSetting_AirConditioning.fnsav, (byte[])localObject);
      }
      localObject = new Intent();
      ((Intent)localObject).putExtra("presetSH", FrmSettingTimeSimple.this.presetSH);
      ((Intent)localObject).putExtra("presetSM", FrmSettingTimeSimple.this.presetSM);
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
    
    void loadDatas()
    {
      int[] arrayOfInt1 = this.bkset1;
      int[] arrayOfInt2 = this.set1;
      int i = DealMsg.GetPmHValue(1, FrmSettingTimeSimple.this.presetSH);
      arrayOfInt2[0] = i;
      arrayOfInt1[0] = i;
      arrayOfInt1 = this.bkset1;
      arrayOfInt2 = this.set1;
      i = DealMsg.GetPmMValue(1, FrmSettingTimeSimple.this.presetSM);
      arrayOfInt2[1] = i;
      arrayOfInt1[1] = i;
      arrayOfInt2 = this.bkset2;
      arrayOfInt1 = this.set2;
      i = DealMsg.GetPmHValue(2, FrmSettingTimeSimple.this.presetSH);
      arrayOfInt1[0] = i;
      arrayOfInt2[0] = i;
      arrayOfInt2 = this.bkset2;
      arrayOfInt1 = this.set2;
      i = DealMsg.GetPmMValue(2, FrmSettingTimeSimple.this.presetSM);
      arrayOfInt1[1] = i;
      arrayOfInt2[1] = i;
      arrayOfInt2 = this.bkset3;
      arrayOfInt1 = this.set3;
      i = DealMsg.GetPmHValue(3, FrmSettingTimeSimple.this.presetSH);
      arrayOfInt1[0] = i;
      arrayOfInt2[0] = i;
      arrayOfInt2 = this.bkset3;
      arrayOfInt1 = this.set3;
      i = DealMsg.GetPmMValue(3, FrmSettingTimeSimple.this.presetSM);
      arrayOfInt1[1] = i;
      arrayOfInt2[1] = i;
      refreshWheel(this.btn_choose, getCurSet());
      FrmSettingTimeSimple.this.btnOk.setEnabled(false);
    }
    
    void refreshWheel(int paramInt, int[] paramArrayOfInt)
    {
      boolean bool2 = true;
      this.btn_choose = paramInt;
      MyButton localMyButton = this.button_on1;
      boolean bool1;
      if (this.btn_choose == 0)
      {
        bool1 = true;
        localMyButton.setToggle(bool1);
        localMyButton = this.button_on2;
        if (this.btn_choose != 1) {
          break label93;
        }
        bool1 = true;
        label45:
        localMyButton.setToggle(bool1);
        localMyButton = this.button_on3;
        if (this.btn_choose != 2) {
          break label99;
        }
        bool1 = bool2;
        label68:
        localMyButton.setToggle(bool1);
        if (isTimeRight(paramArrayOfInt)) {
          break label105;
        }
        resetWheels();
      }
      for (;;)
      {
        return;
        bool1 = false;
        break;
        label93:
        bool1 = false;
        break label45;
        label99:
        bool1 = false;
        break label68;
        label105:
        setWheels(paramArrayOfInt);
      }
    }
    
    void saveData(int paramInt, int[] paramArrayOfInt)
    {
      DealMsg.SetPmHValue(paramInt, (short)paramArrayOfInt[0], FrmSettingTimeSimple.this.presetSH);
      DealMsg.SetPmMValue(paramInt, (short)paramArrayOfInt[1], FrmSettingTimeSimple.this.presetSM);
    }
    
    public void saveDatas()
    {
      saveData(1, this.set1);
      saveData(2, this.set2);
      saveData(3, this.set3);
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\FrmSettingTimeSimple.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */