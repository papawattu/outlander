package com.inventec.iMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseOP.DataGetter;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.mySettingItem.ItemOnClick;
import com.inventec.iMobile.mySettingItem.SettingItem;
import com.inventec.iMobile.mySettingItem.SettingItem_Normal;
import com.inventec.iMobile.mySettingItem.SettingListAdapter;
import java.util.ArrayList;

public class BxCarSetGroup
  extends myActivity
{
  int GRP_ID;
  String titlename;
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getExtras();
    if (paramBundle != null)
    {
      this.GRP_ID = paramBundle.getInt("GRP_SEL");
      this.titlename = paramBundle.getString("Title");
    }
    hideTabBar();
    SetMainView(new MvCarSetGroup(this));
    SetTitleText(this.titlename);
  }
  
  class MvCarSetGroup
    extends myMainView
  {
    private final int[] g_GroupFUNCNameList;
    private final int[] g_GroupFUNCName_titleList;
    private ArrayList<SettingItem> mListSetting;
    
    public MvCarSetGroup(myActivity parammyActivity)
    {
      super(2130903045);
      this$1 = new int[61];
      BxCarSetGroup.this[1] = 2131165662;
      BxCarSetGroup.this[2] = 2131165666;
      BxCarSetGroup.this[3] = 2131165502;
      BxCarSetGroup.this[4] = 2131165547;
      BxCarSetGroup.this[5] = 2131165553;
      BxCarSetGroup.this[6] = 2131165562;
      BxCarSetGroup.this[7] = 2131165619;
      BxCarSetGroup.this[8] = 2131165732;
      BxCarSetGroup.this[9] = 2131165581;
      BxCarSetGroup.this[10] = 2131165588;
      BxCarSetGroup.this[11] = 2131165610;
      BxCarSetGroup.this[12] = 2131165573;
      BxCarSetGroup.this[13] = 2131165604;
      BxCarSetGroup.this[14] = 2131165670;
      BxCarSetGroup.this[15] = 2131165558;
      BxCarSetGroup.this[16] = 2131165685;
      BxCarSetGroup.this[17] = 2131165681;
      BxCarSetGroup.this[19] = 2131165542;
      BxCarSetGroup.this[20] = 2131165511;
      BxCarSetGroup.this[21] = 2131165531;
      BxCarSetGroup.this[22] = 2131165675;
      BxCarSetGroup.this[23] = 2131165627;
      BxCarSetGroup.this[24] = 2131165647;
      BxCarSetGroup.this[25] = 2131165637;
      BxCarSetGroup.this[26] = 2131165522;
      BxCarSetGroup.this[27] = 2131165642;
      BxCarSetGroup.this[28] = 2131165518;
      BxCarSetGroup.this[29] = 2131165568;
      BxCarSetGroup.this[32] = 2131165537;
      BxCarSetGroup.this[33] = 2131165739;
      BxCarSetGroup.this[34] = 2131165653;
      BxCarSetGroup.this[35] = 2131165657;
      BxCarSetGroup.this[36] = 2131165743;
      BxCarSetGroup.this[41] = 2131165631;
      BxCarSetGroup.this[42] = 2131165592;
      BxCarSetGroup.this[43] = 2131165599;
      BxCarSetGroup.this[50] = 2131165693;
      BxCarSetGroup.this[51] = 2131165697;
      BxCarSetGroup.this[53] = 2131165701;
      BxCarSetGroup.this[54] = 2131165705;
      BxCarSetGroup.this[55] = 2131165710;
      BxCarSetGroup.this[56] = 2131165715;
      BxCarSetGroup.this[57] = 2131165719;
      BxCarSetGroup.this[58] = 2131165723;
      BxCarSetGroup.this[60] = 2131165727;
      this.g_GroupFUNCNameList = BxCarSetGroup.this;
      this$1 = new int[61];
      BxCarSetGroup.this[1] = 2131165663;
      BxCarSetGroup.this[2] = 2131165667;
      BxCarSetGroup.this[3] = 2131165503;
      BxCarSetGroup.this[4] = 2131165548;
      BxCarSetGroup.this[5] = 2131165554;
      BxCarSetGroup.this[6] = 2131165563;
      BxCarSetGroup.this[7] = 2131165620;
      BxCarSetGroup.this[8] = 2131165733;
      BxCarSetGroup.this[9] = 2131165582;
      BxCarSetGroup.this[10] = 2131165589;
      BxCarSetGroup.this[11] = 2131165611;
      BxCarSetGroup.this[12] = 2131165574;
      BxCarSetGroup.this[13] = 2131165605;
      BxCarSetGroup.this[14] = 2131165671;
      BxCarSetGroup.this[15] = 2131165559;
      BxCarSetGroup.this[16] = 2131165686;
      BxCarSetGroup.this[17] = 2131165682;
      BxCarSetGroup.this[19] = 2131165543;
      BxCarSetGroup.this[20] = 2131165512;
      BxCarSetGroup.this[21] = 2131165532;
      BxCarSetGroup.this[22] = 2131165676;
      BxCarSetGroup.this[23] = 2131165628;
      BxCarSetGroup.this[24] = 2131165648;
      BxCarSetGroup.this[25] = 2131165638;
      BxCarSetGroup.this[26] = 2131165523;
      BxCarSetGroup.this[27] = 2131165643;
      BxCarSetGroup.this[28] = 2131165519;
      BxCarSetGroup.this[29] = 2131165569;
      BxCarSetGroup.this[32] = 2131165538;
      BxCarSetGroup.this[33] = 2131165740;
      BxCarSetGroup.this[34] = 2131165654;
      BxCarSetGroup.this[35] = 2131165658;
      BxCarSetGroup.this[36] = 2131165744;
      BxCarSetGroup.this[41] = 2131165632;
      BxCarSetGroup.this[42] = 2131165593;
      BxCarSetGroup.this[43] = 2131165600;
      BxCarSetGroup.this[50] = 2131165694;
      BxCarSetGroup.this[51] = 2131165698;
      BxCarSetGroup.this[53] = 2131165702;
      BxCarSetGroup.this[54] = 2131165706;
      BxCarSetGroup.this[55] = 2131165711;
      BxCarSetGroup.this[56] = 2131165716;
      BxCarSetGroup.this[57] = 2131165720;
      BxCarSetGroup.this[58] = 2131165724;
      BxCarSetGroup.this[60] = 2131165728;
      this.g_GroupFUNCName_titleList = BxCarSetGroup.this;
    }
    
    private void fillList()
    {
      if (this.mListSetting != null)
      {
        SettingListAdapter localSettingListAdapter = new SettingListAdapter(this.mAct, this.mListSetting);
        ListView localListView = (ListView)this.mView.findViewById(2131296280);
        localListView.setAdapter(localSettingListAdapter);
        localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            ((SettingItem)BxCarSetGroup.MvCarSetGroup.this.mListSetting.get(paramAnonymousInt)).Trigger();
          }
        });
      }
    }
    
    private void initListSetting(final Activity paramActivity)
    {
      this.mListSetting = new ArrayList();
      byte[] arrayOfByte = DealMsg.GetGroupFuncList(BxCarSetGroup.this.GRP_ID);
      for (int i = 0;; i++)
      {
        if (i >= 10) {}
        int j;
        do
        {
          return;
          j = arrayOfByte[i];
        } while (j == 0);
        if (DataGetter.g_FUNC_CanShow(j))
        {
          SettingItem_Normal localSettingItem_Normal = new SettingItem_Normal(this.g_GroupFUNCNameList[j], j);
          localSettingItem_Normal.setTextSizeScale(this.mAct.getScrScale());
          localSettingItem_Normal.SetOnTrigger(new ItemOnClick()
          {
            public void OnClick(SettingItem paramAnonymousSettingItem)
            {
              int i = BxCarSetGroup.MvCarSetGroup.this.mListSetting.indexOf(paramAnonymousSettingItem);
              if ((i >= 0) && (i < 10))
              {
                Intent localIntent = new Intent(paramActivity, BxCarSetSubItem.class);
                Bundle localBundle = new Bundle();
                i = paramAnonymousSettingItem.GetID();
                localBundle.putInt("FUC_SEL", i);
                localBundle.putString("Title", BxCarSetGroup.this.titlename);
                localBundle.putString("SubTitle", BxCarSetGroup.MvCarSetGroup.this.mAct.getString(BxCarSetGroup.MvCarSetGroup.this.g_GroupFUNCName_titleList[i]));
                localIntent.putExtras(localBundle);
                paramActivity.startActivityForResult(localIntent, 101);
              }
            }
          });
          this.mListSetting.add(localSettingItem_Normal);
        }
      }
    }
    
    public void Refresh()
    {
      int i = com.inventec.iMobile.baseStruct.DefMsg.g_recdatas[60];
      if ((i == 1) || (i == 3)) {
        BxCarSetGroup.this.finish();
      }
      for (;;)
      {
        return;
        initListSetting(this.mAct);
        if ((this.mListSetting != null) && (this.mListSetting.size() > 0)) {
          fillList();
        } else {
          BxCarSetGroup.this.finish();
        }
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\BxCarSetGroup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */