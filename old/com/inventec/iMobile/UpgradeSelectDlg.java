package com.inventec.iMobile;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseOP.DataGetter;
import com.inventec.iMobile.baseOP.MsgReceiver;
import com.inventec.iMobile.baseOP.MsgUpdateTran;

public class UpgradeSelectDlg
  extends Dialog
{
  myActivity mAct;
  
  public UpgradeSelectDlg(myActivity parammyActivity)
  {
    super(parammyActivity, 2131230720);
    this.mAct = parammyActivity;
    setContentView(parammyActivity.getLayoutInflater().inflate(2130903114, null));
    Object localObject = getWindow().getAttributes();
    ((ViewGroup.LayoutParams)localObject).width = -1;
    getWindow().setAttributes((WindowManager.LayoutParams)localObject);
    parammyActivity.setEarth((ImageView)findViewById(2131296257));
    parammyActivity = DataGetter.g_recdatas((short)181, 10);
    localObject = (char)parammyActivity[0] + "." + (char)parammyActivity[1] + (char)parammyActivity[2] + (char)parammyActivity[3] + "." + (char)parammyActivity[4] + "." + (char)parammyActivity[5] + "." + (char)parammyActivity[6] + "." + (char)parammyActivity[7] + (char)parammyActivity[8] + (char)parammyActivity[9] + "\n▼\n" + iMobile_AppGlobalVar.getVersion();
    parammyActivity = (TextView)findViewById(2131296657);
    this.mAct.enlargeViewToFitScreen(parammyActivity, true);
    parammyActivity.setText((CharSequence)localObject);
    parammyActivity = (TextView)findViewById(2131296266);
    this.mAct.enlargeViewToFitScreen(parammyActivity, true);
    parammyActivity = (MyButton)findViewById(2131296262);
    this.mAct.enlargeViewToFitScreen(parammyActivity, true);
    parammyActivity.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        MsgReceiver.BroadcastMsgAction(30, 0);
        UpgradeSelectDlg.this.dismiss();
      }
    });
    parammyActivity = (MyButton)findViewById(2131296618);
    this.mAct.enlargeViewToFitScreen(parammyActivity, true);
    parammyActivity.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UpgradeSelectDlg.this.setContentView(2130903115);
        UpgradeSelectDlg.this.mAct.setEarth((ImageView)UpgradeSelectDlg.this.findViewById(2131296257));
        paramAnonymousView = (ImageView)UpgradeSelectDlg.this.findViewById(2131296260);
        UpgradeSelectDlg.this.mAct.enlargeViewToFitScreen(paramAnonymousView, false);
        paramAnonymousView = (TextView)UpgradeSelectDlg.this.findViewById(2131296266);
        UpgradeSelectDlg.this.mAct.enlargeViewToFitScreen(paramAnonymousView, true);
        paramAnonymousView = (MyButton)UpgradeSelectDlg.this.findViewById(2131296262);
        UpgradeSelectDlg.this.mAct.enlargeViewToFitScreen(paramAnonymousView, true);
        paramAnonymousView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymous2View)
          {
            UpgradeSelectDlg.this.dismiss();
            if (MsgReceiver.upgradeProcess != null) {
              MsgReceiver.upgradeProcess.dismissDlg();
            }
          }
        });
      }
    });
  }
  
  public void onBackPressed() {}
  
  public void showAsBeging()
  {
    ((TextView)findViewById(2131296266)).setText(this.mAct.getText(2131165450));
    iMobile_AppGlobalVar.needUpgrade = false;
    ((TextView)findViewById(2131296657)).setVisibility(8);
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\UpgradeSelectDlg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */