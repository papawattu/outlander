package com.inventec.iMobile;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myAlertDlg;
import com.inventec.iMobile.baseClass.myAlertDlg.CloseCallback;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseStruct.DealMsg;
import java.util.ArrayList;
import java.util.List;

public class BxShowAlarmHistory
  extends myActivity
{
  private ListView listView;
  RelativeLayout titlebar;
  Button titlebutton;
  TextView titletxt;
  
  public void DoUpdate()
  {
    myAlertDlg localmyAlertDlg = new myAlertDlg(this);
    localmyAlertDlg.setDlgContent(0, 2131165449, false);
    localmyAlertDlg.setOkCallback(new myAlertDlg.CloseCallback()
    {
      public void DoAction()
      {
        BxShowAlarmHistory.MvShowHistory localMvShowHistory = (BxShowAlarmHistory.MvShowHistory)BxShowAlarmHistory.this.mCurMainView;
        if (localMvShowHistory != null)
        {
          localMvShowHistory.doClear();
          BxShowAlarmHistory.this.btnUpdate.setVisibility(4);
        }
      }
    });
    localmyAlertDlg.show();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    hideTabBar();
    SetMainView(new MvShowHistory(this));
    SetTitleText(getString(2131165416));
    if (DealMsg.g_TheftHistory[0][0] == 0) {
      this.btnUpdate.setVisibility(4);
    }
  }
  
  public void updateTitleBarBtn()
  {
    this.btnUpdate.chengeBg(2130837608, -1, 2130837609, -12303292);
    this.btnUpdate.setText(2131165448);
  }
  
  class MvShowHistory
    extends myMainView
  {
    public MvShowHistory(myActivity parammyActivity)
    {
      super(2130903048);
      fillList();
    }
    
    private List<String> getData()
    {
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      if (i >= 20) {
        return localArrayList;
      }
      byte[] arrayOfByte = DealMsg.g_TheftHistory[i];
      if (i >= 9) {}
      for (String str = "";; str = " ")
      {
        localArrayList.add(str + (i + 1) + ". " + DealMsg.getTheftDate(this.mAct, arrayOfByte));
        i++;
        break;
      }
    }
    
    public void Refresh()
    {
      fillList();
    }
    
    public void doClear()
    {
      DealMsg.resetVector_D2(DealMsg.g_TheftHistory, 20, 7);
      fillList();
    }
    
    public void fillList()
    {
      BxShowAlarmHistory.this.listView = ((ListView)this.mView.findViewById(2131296288));
      BxShowAlarmHistory.this.listView.setAdapter(new ArrayAdapter(this.mAct, 17367043, getData()));
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\BxShowAlarmHistory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */