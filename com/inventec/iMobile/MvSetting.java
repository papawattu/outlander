package com.inventec.iMobile;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myActivityBase;
import com.inventec.iMobile.baseClass.myAlertDlg;
import com.inventec.iMobile.baseClass.myAlertDlg.CloseCallback;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseClass.myWaitDlg;
import com.inventec.iMobile.baseOP.CmdMake;
import com.inventec.iMobile.baseOP.MsgReceiver;
import com.inventec.iMobile.baseOP.MsgSender;
import com.inventec.iMobile.baseStruct.DealMsg;
import com.inventec.iMobile.baseStruct.DefMsg;
import com.inventec.iMobile.mySettingItem.ItemOnCheckChange;
import com.inventec.iMobile.mySettingItem.ItemOnClick;
import com.inventec.iMobile.mySettingItem.SettingItem;
import com.inventec.iMobile.mySettingItem.SettingItem_Check;
import com.inventec.iMobile.mySettingItem.SettingListAdapter;
import java.util.ArrayList;

public class MvSetting
  extends myMainView
{
  myAlertDlg dialog;
  private ArrayList<SettingItem> mListSetting;
  Handler myhd = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      super.handleMessage(paramAnonymousMessage);
      if (paramAnonymousMessage.what == 4) {
        MvSetting.this.mAct.finish();
      }
    }
  };
  
  public MvSetting(myActivity parammyActivity)
  {
    super(parammyActivity, 2130903075);
    initListSetting(parammyActivity);
    fillList();
  }
  
  private void fillList()
  {
    if (this.mListSetting != null)
    {
      SettingListAdapter localSettingListAdapter = new SettingListAdapter(this.mAct, this.mListSetting);
      ListView localListView = (ListView)this.mView.findViewById(2131296480);
      localListView.setAdapter(localSettingListAdapter);
      localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          ((SettingItem)MvSetting.this.mListSetting.get(paramAnonymousInt)).Trigger();
        }
      });
    }
  }
  
  private void initListSetting(final myActivity parammyActivity)
  {
    boolean bool = false;
    this.mListSetting = new ArrayList();
    int i = DealMsg.getPasswordType();
    SettingItem_Check localSettingItem_Check = new SettingItem_Check(2131165409, 1);
    localSettingItem_Check.setSWType(0);
    localSettingItem_Check.setTextSizeScale(parammyActivity.getScrScale());
    if ((i != 2) && (i != 0)) {}
    for (;;)
    {
      localSettingItem_Check.setCheckState(bool);
      localSettingItem_Check.SetOnCheckChg(new ItemOnCheckChange()
      {
        public void OnCheckChange(boolean paramAnonymousBoolean)
        {
          if (DealMsg.g_isDemo) {}
          for (;;)
          {
            return;
            Intent localIntent = new Intent(parammyActivity, BxPswOffOn.class);
            parammyActivity.startActivityForResult(localIntent, 101);
          }
        }
      });
      this.mListSetting.add(localSettingItem_Check);
      localSettingItem_Check = new SettingItem_Check(2131165410, 2);
      localSettingItem_Check.setTextSizeScale(parammyActivity.getScrScale());
      localSettingItem_Check.setSWType(1);
      localSettingItem_Check.SetOnTrigger(new ItemOnClick()
      {
        public void OnClick(SettingItem paramAnonymousSettingItem)
        {
          if (DealMsg.g_isDemo) {}
          for (;;)
          {
            return;
            if (DealMsg.getPasswordType() == 1)
            {
              paramAnonymousSettingItem = new Intent(parammyActivity, BxChangePsw.class);
              parammyActivity.startActivityForResult(paramAnonymousSettingItem, 101);
            }
            else
            {
              paramAnonymousSettingItem = new myAlertDlg(MvSetting.this.mAct);
              paramAnonymousSettingItem.setDlgContent(0, 2131165470, true);
              paramAnonymousSettingItem.show();
            }
          }
        }
      });
      this.mListSetting.add(localSettingItem_Check);
      localSettingItem_Check = new SettingItem_Check(2131165411, 3);
      localSettingItem_Check.setTextSizeScale(parammyActivity.getScrScale());
      localSettingItem_Check.setSWType(1);
      localSettingItem_Check.SetOnTrigger(new ItemOnClick()
      {
        public void OnClick(SettingItem paramAnonymousSettingItem)
        {
          if (DealMsg.g_isDemo) {}
          for (;;)
          {
            return;
            paramAnonymousSettingItem = new Intent(parammyActivity, BxChangeSSID.class);
            parammyActivity.startActivityForResult(paramAnonymousSettingItem, 101);
          }
        }
      });
      this.mListSetting.add(localSettingItem_Check);
      localSettingItem_Check = new SettingItem_Check(2131165415, 6);
      localSettingItem_Check.setTextSizeScale(parammyActivity.getScrScale());
      localSettingItem_Check.setSWType(1);
      localSettingItem_Check.SetOnTrigger(new ItemOnClick()
      {
        public void OnClick(SettingItem paramAnonymousSettingItem)
        {
          if (DealMsg.g_isDemo) {}
          for (;;)
          {
            return;
            int i = DealMsg.getPasswordType();
            if ((i == 2) || (i == 0))
            {
              MvSetting.this.doAskReset0();
            }
            else
            {
              paramAnonymousSettingItem = new Intent(parammyActivity, BxRemoveVin.class);
              parammyActivity.startActivityForResult(paramAnonymousSettingItem, 101);
            }
          }
        }
      });
      this.mListSetting.add(localSettingItem_Check);
      addBlankItem();
      localSettingItem_Check = new SettingItem_Check(2131165413, 7);
      localSettingItem_Check.setTextSizeScale(parammyActivity.getScrScale());
      localSettingItem_Check.setSWType(1);
      localSettingItem_Check.SetOnTrigger(new ItemOnClick()
      {
        public void OnClick(SettingItem paramAnonymousSettingItem)
        {
          if (DealMsg.g_isDemo) {}
          for (;;)
          {
            return;
            if (MvSetting.this.useEVSet())
            {
              paramAnonymousSettingItem = new Intent(parammyActivity, BxCarSetting.class);
              parammyActivity.startActivityForResult(paramAnonymousSettingItem, 101);
            }
          }
        }
      });
      this.mListSetting.add(localSettingItem_Check);
      addBlankItem();
      localSettingItem_Check = new SettingItem_Check(2131165416, 8);
      localSettingItem_Check.setTextSizeScale(parammyActivity.getScrScale());
      localSettingItem_Check.setSWType(1);
      localSettingItem_Check.SetOnTrigger(new ItemOnClick()
      {
        public void OnClick(SettingItem paramAnonymousSettingItem)
        {
          if (DealMsg.g_isDemo) {}
          for (;;)
          {
            return;
            paramAnonymousSettingItem = new Intent(parammyActivity, BxShowAlarmHistory.class);
            parammyActivity.startActivityForResult(paramAnonymousSettingItem, 101);
          }
        }
      });
      this.mListSetting.add(localSettingItem_Check);
      addBlankItem();
      localSettingItem_Check = new SettingItem_Check(2131165417, 9);
      localSettingItem_Check.setSWType(1);
      localSettingItem_Check.setTextSizeScale(parammyActivity.getScrScale());
      localSettingItem_Check.SetOnTrigger(new ItemOnClick()
      {
        public void OnClick(SettingItem paramAnonymousSettingItem)
        {
          if (DealMsg.g_isDemo) {}
          for (;;)
          {
            return;
            com.inventec.iMobile.baseOP.MsgUpdateTran.g_curA0Status = 1;
            MsgReceiver.BroadcastMsgAction(31, 0);
          }
        }
      });
      this.mListSetting.add(localSettingItem_Check);
      addBlankItem();
      localSettingItem_Check = new SettingItem_Check(2131165418, 10);
      localSettingItem_Check.setTextSizeScale(parammyActivity.getScrScale());
      localSettingItem_Check.setSWType(1);
      localSettingItem_Check.SetOnTrigger(new ItemOnClick()
      {
        public void OnClick(SettingItem paramAnonymousSettingItem)
        {
          if (DealMsg.g_isDemo) {}
          for (;;)
          {
            return;
            paramAnonymousSettingItem = new Intent(parammyActivity, BxShowAllVersion.class);
            parammyActivity.startActivityForResult(paramAnonymousSettingItem, 101);
          }
        }
      });
      this.mListSetting.add(localSettingItem_Check);
      addBlankItem();
      localSettingItem_Check = new SettingItem_Check(2131165419, 11);
      localSettingItem_Check.setTextSizeScale(parammyActivity.getScrScale());
      i = DefMsg.g_recdatas[58];
      parammyActivity = "";
      if (i < 3) {
        parammyActivity = i + this.mAct.getString(2131165420);
      }
      localSettingItem_Check.SetText(parammyActivity);
      this.mListSetting.add(localSettingItem_Check);
      return;
      bool = true;
    }
  }
  
  public void PopupHelpInfo()
  {
    Intent localIntent = new Intent(this.mAct, BxShowHelp.class);
    Bundle localBundle = new Bundle();
    localBundle.putInt("CardNo", 51);
    localIntent.putExtras(localBundle);
    this.mAct.startActivityForResult(localIntent, 101);
  }
  
  public void Refresh()
  {
    boolean bool2 = false;
    ((FrmMain)this.mAct).SetTitleText(this.mAct.getString(2131165408));
    int i = DealMsg.getPasswordType();
    Object localObject = (SettingItem_Check)this.mListSetting.get(0);
    if ((i != 2) && (i != 0))
    {
      bool1 = false;
      ((SettingItem_Check)localObject).setCheckState(bool1);
      localObject = (SettingItem_Check)this.mListSetting.get(1);
      if (i != 1) {
        break label184;
      }
      bool1 = true;
      label76:
      ((SettingItem_Check)localObject).setItemEnabled(bool1);
      localObject = (SettingItem_Check)this.mListSetting.get(5);
      if ((DealMsg.g_isDemo) || (useEVSet())) {
        break label190;
      }
    }
    label184:
    label190:
    for (boolean bool1 = bool2;; bool1 = true)
    {
      ((SettingItem_Check)localObject).setItemEnabled(bool1);
      SettingItem_Check localSettingItem_Check = (SettingItem_Check)this.mListSetting.get(13);
      i = DefMsg.g_recdatas[58];
      localObject = "";
      if (i < 3) {
        localObject = i + this.mAct.getString(2131165420);
      }
      localSettingItem_Check.SetText((String)localObject);
      return;
      bool1 = true;
      break;
      bool1 = false;
      break label76;
    }
  }
  
  void addBlankItem()
  {
    SettingItem_Check localSettingItem_Check = new SettingItem_Check(0, -2);
    localSettingItem_Check.setSWType(1);
    this.mListSetting.add(localSettingItem_Check);
  }
  
  void doAskReset()
  {
    this.dialog = new myAlertDlg(this.mAct);
    this.dialog.setDlgContent(0, 2131165447, false);
    this.dialog.noDoDismiss();
    this.dialog.setOkCallback(new myAlertDlg.CloseCallback()
    {
      public void DoAction()
      {
        MsgSender.Send(CmdMake.KO_WF_INIT_RQ_SP());
        DefMsg.memsetZero(DealMsg.g_VINNum, 0, 17);
        DefMsg.memsetZero(DefMsg.g_recdatas, 40, 17);
        myActivityBase localmyActivityBase = myActivity.GetCurForegroundActivity();
        DealMsg.saveVIN(localmyActivityBase);
        DealMsg.SaveAPPSet(localmyActivityBase);
        DealMsg.setSSID("");
        DealMsg.saveSSID(localmyActivityBase);
        DefMsg.memsetZero(DealMsg.g_PASSWORD, 0, 6);
        DealMsg.savePASSWORD(localmyActivityBase);
        MvSetting.this.myhd.sendEmptyMessageDelayed(4, 10000L);
        myWaitDlg.setShowOk();
      }
    });
    this.dialog.setCancelCallback(new myAlertDlg.CloseCallback()
    {
      public void DoAction()
      {
        MvSetting.this.dialog.dismiss();
      }
    });
    this.dialog.show();
  }
  
  void doAskReset0()
  {
    this.dialog = new myAlertDlg(this.mAct);
    this.dialog.setDlgContent(0, 2131165446, false);
    this.dialog.noDoDismiss();
    if (DefMsg.g_recdatas[58] == 2) {
      this.dialog.setOkCallback(new myAlertDlg.CloseCallback()
      {
        public void DoAction()
        {
          MsgSender.Send(CmdMake.KO_WF_INIT_RQ_SP());
          DefMsg.memsetZero(DealMsg.g_VINNum, 0, 17);
          DefMsg.memsetZero(DefMsg.g_recdatas, 40, 17);
          myActivityBase localmyActivityBase = myActivity.GetCurForegroundActivity();
          DealMsg.saveVIN(localmyActivityBase);
          DealMsg.SaveAPPSet(localmyActivityBase);
          DealMsg.setSSID("");
          DealMsg.saveSSID(localmyActivityBase);
          DefMsg.memsetZero(DealMsg.g_PASSWORD, 0, 6);
          DealMsg.savePASSWORD(localmyActivityBase);
          MvSetting.this.myhd.sendEmptyMessageDelayed(4, 10000L);
          myWaitDlg.setShowOk();
        }
      });
    }
    for (;;)
    {
      this.dialog.setCancelCallback(new myAlertDlg.CloseCallback()
      {
        public void DoAction()
        {
          MvSetting.this.dialog.dismiss();
        }
      });
      this.dialog.show();
      return;
      this.dialog.setOkCallback(new myAlertDlg.CloseCallback()
      {
        public void DoAction()
        {
          MvSetting.this.doAskReset();
        }
      });
    }
  }
  
  public boolean useEVSet()
  {
    boolean bool = true;
    int j = 0;
    int i = 0;
    if (i >= 10)
    {
      i = j;
      label14:
      j = DefMsg.g_recdatas[60];
      if ((i == 0) || (j == 1) || (j == 3)) {
        break label55;
      }
    }
    for (;;)
    {
      return bool;
      if (DealMsg.isGroupCanShow(i))
      {
        i = 1;
        break label14;
      }
      i++;
      break;
      label55:
      bool = false;
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\MvSetting.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */