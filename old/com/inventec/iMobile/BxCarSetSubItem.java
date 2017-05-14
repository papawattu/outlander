package com.inventec.iMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseOP.CmdMake;
import com.inventec.iMobile.baseOP.DataGetter;
import com.inventec.iMobile.baseOP.MsgReceiver;
import com.inventec.iMobile.baseOP.MsgSender;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.mySettingItem.SettingItem;
import com.inventec.iMobile.mySettingItem.SettingItem_Normal;
import com.inventec.iMobile.mySettingItem.SettingListAdapter;
import java.util.ArrayList;

public class BxCarSetSubItem
  extends myActivity
{
  int FUC_ID;
  int FUC_Sel;
  private int last_Sel = -1;
  String subtitlename;
  String titlename;
  
  protected void btnOkOnClick()
  {
    MsgSender.Send(CmdMake.KO_WF_FN_SEL_ADJ_VAL_R_SP((byte)this.FUC_ID, (byte)this.FUC_Sel));
    MsgReceiver.waitECUMessage((byte)this.FUC_ID, (byte)this.FUC_Sel);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getExtras();
    if (paramBundle != null)
    {
      this.FUC_ID = paramBundle.getInt("FUC_SEL");
      this.titlename = paramBundle.getString("Title");
      this.subtitlename = paramBundle.getString("SubTitle");
    }
    this.last_Sel = DataGetter.g_FUNC_CurSel(this.FUC_ID);
    this.FUC_Sel = this.last_Sel;
    hideTabBar();
    hideUpdateBtn();
    SetMainView(new MvCarSetSubItem(this));
    SetTitleText(this.titlename);
    paramBundle = (MyButton)findViewById(2131296262);
    enlargeViewToFitScreen(paramBundle, true);
    paramBundle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BxCarSetSubItem.this.btnOkOnClick();
      }
    });
  }
  
  class MvCarSetSubItem
    extends myMainView
  {
    Handler handler = new Handler();
    ListView lv;
    private ArrayList<SettingItem> mListSetting;
    
    public MvCarSetSubItem(myActivity parammyActivity)
    {
      super(2130903046);
      TextView localTextView = (TextView)this.mView.findViewById(2131296281);
      parammyActivity.enlargeViewToFitScreen(localTextView, true);
      localTextView.setText(BxCarSetSubItem.this.subtitlename);
    }
    
    private void initListSetting(Activity paramActivity)
    {
      this.mListSetting = new ArrayList();
      int i;
      switch (BxCarSetSubItem.this.FUC_ID)
      {
      case 18: 
      case 30: 
      case 31: 
      case 37: 
      case 38: 
      case 39: 
      case 40: 
      case 44: 
      case 45: 
      case 46: 
      case 47: 
      case 48: 
      case 49: 
      case 52: 
      case 59: 
      default: 
        i = this.mListSetting.size() - 1;
        label282:
        if (i < 0)
        {
          if ((this.mListSetting == null) || (this.mListSetting.size() <= 0)) {
            break label1596;
          }
          paramActivity = new SettingListAdapter(this.mAct, this.mListSetting);
          this.lv = ((ListView)this.mView.findViewById(2131296280));
          this.lv.setAdapter(paramActivity);
          this.lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
          {
            public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
              BxCarSetSubItem.MvCarSetSubItem.this.checkItem(paramAnonymousAdapterView, paramAnonymousView, paramAnonymousInt);
            }
            
            public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView) {}
          });
          this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
          {
            public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
            {
              BxCarSetSubItem.MvCarSetSubItem.this.checkItem(paramAnonymousAdapterView, paramAnonymousView, paramAnonymousInt);
            }
          });
          SetChecked();
        }
        break;
      }
      for (;;)
      {
        return;
        AddItem(2131165504, 0);
        AddItem(2131165505, 1);
        AddItem(2131165506, 2);
        AddItem(2131165507, 3);
        AddItem(2131165508, 4);
        AddItem(2131165509, 5);
        AddItem(2131165510, 6);
        break;
        AddItem(2131165513, 0);
        AddItem(2131165514, 1);
        AddItem(2131165515, 2);
        AddItem(2131165516, 3);
        AddItem(2131165517, 4);
        break;
        AddItem(2131165520, 0);
        AddItem(2131165521, 1);
        break;
        AddItem(2131165524, 0);
        AddItem(2131165525, 1);
        AddItem(2131165526, 2);
        AddItem(2131165527, 3);
        AddItem(2131165528, 4);
        AddItem(2131165529, 5);
        break;
        AddItem(2131165544, 0);
        AddItem(2131165545, 1);
        break;
        AddItem(2131165533, 0);
        AddItem(2131165534, 1);
        AddItem(2131165535, 2);
        AddItem(2131165536, 3);
        break;
        AddItem(2131165539, 0);
        AddItem(2131165540, 1);
        AddItem(2131165541, 2);
        break;
        AddItem(2131165549, 0);
        AddItem(2131165550, 1);
        AddItem(2131165551, 2);
        AddItem(2131165552, 3);
        break;
        AddItem(2131165555, 0);
        AddItem(2131165556, 1);
        AddItem(2131165557, 2);
        break;
        AddItem(2131165560, 0);
        AddItem(2131165561, 1);
        break;
        AddItem(2131165564, 0);
        AddItem(2131165565, 1);
        AddItem(2131165566, 2);
        AddItem(2131165567, 3);
        break;
        AddItem(2131165570, 0);
        AddItem(2131165571, 1);
        break;
        AddItem(2131165575, 0);
        AddItem(2131165576, 1);
        AddItem(2131165577, 2);
        AddItem(2131165578, 3);
        AddItem(2131165579, 4);
        AddItem(2131165580, 5);
        break;
        AddItem(2131165583, 0);
        AddItem(2131165584, 1);
        AddItem(2131165585, 2);
        AddItem(2131165586, 3);
        AddItem(2131165587, 4);
        break;
        AddItem(2131165590, 0);
        AddItem(2131165591, 1);
        break;
        AddItem(2131165594, 0);
        AddItem(2131165595, 1);
        AddItem(2131165596, 2);
        AddItem(2131165597, 3);
        AddItem(2131165598, 4);
        break;
        AddItem(2131165601, 0);
        AddItem(2131165602, 1);
        AddItem(2131165603, 2);
        break;
        AddItem(2131165606, 0);
        AddItem(2131165607, 1);
        AddItem(2131165608, 2);
        AddItem(2131165609, 3);
        break;
        AddItem(2131165612, 0);
        AddItem(2131165613, 1);
        AddItem(2131165614, 2);
        AddItem(2131165615, 3);
        AddItem(2131165616, 4);
        AddItem(2131165617, 5);
        AddItem(2131165618, 6);
        break;
        AddItem(2131165621, 0);
        AddItem(2131165622, 1);
        AddItem(2131165623, 2);
        AddItem(2131165624, 3);
        AddItem(2131165625, 4);
        break;
        AddItem(2131165629, 0);
        AddItem(2131165630, 1);
        break;
        AddItem(2131165633, 0);
        AddItem(2131165634, 1);
        AddItem(2131165635, 2);
        AddItem(2131165636, 3);
        break;
        AddItem(2131165639, 0);
        AddItem(2131165640, 1);
        AddItem(2131165641, 2);
        break;
        AddItem(2131165644, 0);
        AddItem(2131165645, 1);
        break;
        AddItem(2131165649, 0);
        AddItem(2131165650, 1);
        AddItem(2131165651, 2);
        AddItem(2131165652, 3);
        break;
        AddItem(2131165655, 0);
        AddItem(2131165656, 1);
        break;
        AddItem(2131165659, 0);
        AddItem(2131165660, 1);
        break;
        AddItem(2131165664, 0);
        AddItem(2131165665, 1);
        break;
        AddItem(2131165668, 0);
        AddItem(2131165669, 1);
        break;
        AddItem(2131165672, 0);
        AddItem(2131165673, 1);
        break;
        AddItem(2131165677, 0);
        AddItem(2131165678, 1);
        AddItem(2131165679, 2);
        AddItem(2131165680, 3);
        break;
        AddItem(2131165683, 0);
        AddItem(2131165684, 1);
        break;
        AddItem(2131165687, 0);
        AddItem(2131165688, 1);
        AddItem(2131165689, 2);
        AddItem(2131165690, 3);
        AddItem(2131165691, 4);
        break;
        AddItem(2131165695, 0);
        AddItem(2131165696, 1);
        break;
        AddItem(2131165699, 0);
        AddItem(2131165700, 1);
        break;
        AddItem(2131165703, 0);
        AddItem(2131165704, 1);
        break;
        AddItem(2131165707, 0);
        AddItem(2131165708, 1);
        AddItem(2131165709, 2);
        break;
        AddItem(2131165712, 0);
        AddItem(2131165713, 1);
        AddItem(2131165714, 2);
        break;
        AddItem(2131165717, 0);
        AddItem(2131165718, 1);
        break;
        AddItem(2131165721, 0);
        AddItem(2131165722, 1);
        break;
        AddItem(2131165725, 0);
        AddItem(2131165726, 1);
        break;
        AddItem(2131165729, 0);
        AddItem(2131165730, 1);
        break;
        AddItem(2131165734, 0);
        AddItem(2131165735, 1);
        AddItem(2131165736, 2);
        if (DealMsg.isGroupCanShow(0))
        {
          AddItem(2131165737, 3);
          break;
        }
        AddItem(2131165738, 3);
        break;
        AddItem(2131165741, 0);
        AddItem(2131165742, 1);
        break;
        AddItem(2131165745, 0);
        AddItem(2131165746, 1);
        AddItem(2131165747, 2);
        break;
        if (!DataGetter.g_FUNC_Sel_CanShow(BxCarSetSubItem.this.FUC_ID, i)) {
          this.mListSetting.remove(i);
        }
        i--;
        break label282;
        label1596:
        BxCarSetSubItem.this.finish();
      }
    }
    
    void AddItem(int paramInt1, int paramInt2)
    {
      SettingItem_Normal localSettingItem_Normal = new SettingItem_Normal(paramInt1, paramInt2);
      localSettingItem_Normal.setTextSizeScale(this.mAct.getScrScale());
      this.mListSetting.add(localSettingItem_Normal);
    }
    
    public void Refresh()
    {
      int i = com.inventec.iMobile.baseStruct.DefMsg.g_recdatas[60];
      if ((i == 1) || (i == 3)) {
        BxCarSetSubItem.this.finish();
      }
      for (;;)
      {
        return;
        BxCarSetSubItem.this.last_Sel = DataGetter.g_FUNC_CurSel(BxCarSetSubItem.this.FUC_ID);
        BxCarSetSubItem.this.FUC_Sel = BxCarSetSubItem.this.last_Sel;
        initListSetting(this.mAct);
      }
    }
    
    void SetChecked()
    {
      this.handler.postDelayed(new Runnable()
      {
        public void run()
        {
          int i = 0;
          if (i >= BxCarSetSubItem.MvCarSetSubItem.this.mListSetting.size()) {}
          for (;;)
          {
            return;
            SettingItem localSettingItem = (SettingItem)BxCarSetSubItem.MvCarSetSubItem.this.mListSetting.get(i);
            View localView = localSettingItem.GetSelfView();
            if (localView != null)
            {
              ImageView localImageView = (ImageView)localView.findViewById(2131296547);
              if (localSettingItem.GetID() == BxCarSetSubItem.this.FUC_Sel)
              {
                localImageView.setImageResource(2130837610);
                if (BxCarSetSubItem.this.FUC_Sel != BxCarSetSubItem.this.last_Sel)
                {
                  localSettingItem.setItemSeled(true);
                  label105:
                  localView.setSelected(true);
                }
              }
              for (;;)
              {
                i++;
                break;
                localSettingItem.setItemSeled(false);
                break label105;
                localImageView.setImageResource(2130837611);
                localSettingItem.setItemSeled(false);
                localView.setSelected(false);
              }
            }
            BxCarSetSubItem.MvCarSetSubItem.this.SetChecked();
          }
        }
      }, 10L);
    }
    
    void checkItem(AdapterView<?> paramAdapterView, View paramView, int paramInt)
    {
      paramAdapterView = (SettingItem)this.mListSetting.get(paramInt);
      BxCarSetSubItem.this.FUC_Sel = paramAdapterView.GetID();
      paramAdapterView.Trigger();
      SetChecked();
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\BxCarSetSubItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */