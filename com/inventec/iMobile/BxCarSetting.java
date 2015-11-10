package com.inventec.iMobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myAlertDlg;
import com.inventec.iMobile.baseClass.myAlertDlg.CloseCallback;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseOP.CmdMake;
import com.inventec.iMobile.baseOP.MsgReceiver;
import com.inventec.iMobile.baseOP.MsgSender;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.mySettingItem.ItemOnClick;
import com.inventec.iMobile.mySettingItem.SettingItem;
import com.inventec.iMobile.mySettingItem.SettingItem_Normal;
import com.inventec.iMobile.mySettingItem.SettingListAdapter;
import java.util.ArrayList;

public class BxCarSetting
  extends myActivity
{
  myAlertDlg dialog;
  
  public void DoUpdate()
  {
    this.dialog = new myAlertDlg(this);
    this.dialog.setDlgContent(0, 2131165441, false);
    this.dialog.setOkCallback(new myAlertDlg.CloseCallback()
    {
      public void DoAction()
      {
        MsgSender.Send(CmdMake.KO_WF_FN_SEL_ADJ_VAL_R_SP(, (byte)0));
        MsgReceiver.waitECUMessage((byte)66, (byte)0);
      }
    });
    this.dialog.setCancelCallback(new myAlertDlg.CloseCallback()
    {
      public void DoAction()
      {
        BxCarSetting.this.dialog.dismiss();
      }
    });
    this.dialog.noDoDismiss();
    this.dialog.show();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    hideTabBar();
    SetMainView(new MvCarSetting(this));
    SetTitleText(getString(2131165413));
  }
  
  public void updateTitleBarBtn()
  {
    this.btnUpdate.chengeBg(2130837608, -1, 2130837609, -12303292);
    this.btnUpdate.setText(2131165439);
  }
  
  class MvCarSetting
    extends myMainView
  {
    private ArrayList<SettingItem> mListSetting;
    
    public MvCarSetting(myActivity parammyActivity)
    {
      super(2130903045);
    }
    
    private void fillList()
    {
      if (this.mListSetting != null)
      {
        SettingListAdapter localSettingListAdapter = new SettingListAdapter(this.mAct, this.mListSetting);
        ListView localListView = (ListView)BxCarSetting.this.findViewById(2131296280);
        localListView.setAdapter(localSettingListAdapter);
        localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
          public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
          {
            ((SettingItem)BxCarSetting.MvCarSetting.this.mListSetting.get(paramAnonymousInt)).Trigger();
          }
        });
      }
    }
    
    private void initListSetting()
    {
      this.mListSetting = new ArrayList();
      for (int i = 0;; i++)
      {
        if (i >= 10) {
          return;
        }
        if (DealMsg.isGroupCanShow(i))
        {
          SettingItem_Normal localSettingItem_Normal = new SettingItem_Normal(new int[] { 2131165501, 2131165530, 2131165546, 2131165572, 2131165626, 2131165646, 2131165661, 2131165674, 2131165692, 2131165731 }[i], i);
          localSettingItem_Normal.setTextSizeScale(this.mAct.getScrScale());
          localSettingItem_Normal.SetOnTrigger(new ItemOnClick()
          {
            public void OnClick(SettingItem paramAnonymousSettingItem)
            {
              Intent localIntent = new Intent(BxCarSetting.MvCarSetting.this.mAct, BxCarSetGroup.class);
              Bundle localBundle = new Bundle();
              localBundle.putInt("GRP_SEL", paramAnonymousSettingItem.GetID());
              localBundle.putString("Title", BxCarSetting.MvCarSetting.this.mAct.getString(paramAnonymousSettingItem.GetText()));
              localIntent.putExtras(localBundle);
              BxCarSetting.MvCarSetting.this.mAct.startActivityForResult(localIntent, 101);
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
        BxCarSetting.this.finish();
      }
      for (;;)
      {
        return;
        initListSetting();
        if ((this.mListSetting != null) && (this.mListSetting.size() > 0)) {
          fillList();
        } else {
          BxCarSetting.this.finish();
        }
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\BxCarSetting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */