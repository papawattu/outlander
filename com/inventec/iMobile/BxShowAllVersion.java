package com.inventec.iMobile;

import android.os.Bundle;
import android.view.View;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.baseClass.myActivity;
import com.inventec.iMobile.baseClass.myMainView;
import com.inventec.iMobile.baseOP.DataGetter;
import com.inventec.iMobile.baseStruct.DealMsg;

public class BxShowAllVersion
  extends myActivity
{
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    hideTabBar();
    SetMainView(new MvShowHistory(this));
    SetTitleText(getString(2131165418));
    enlargeViewToFitScreen((AutoResizeTextView)findViewById(2131296637), true);
    enlargeViewToFitScreen((AutoResizeTextView)findViewById(2131296638), true);
    enlargeViewToFitScreen((AutoResizeTextView)findViewById(2131296639), true);
    enlargeViewToFitScreen((AutoResizeTextView)findViewById(2131296640), true);
    enlargeViewToFitScreen((AutoResizeTextView)findViewById(2131296641), true);
    enlargeViewToFitScreen((AutoResizeTextView)findViewById(2131296642), true);
  }
  
  class MvShowHistory
    extends myMainView
  {
    public MvShowHistory(myActivity parammyActivity)
    {
      super(2130903104);
    }
    
    public void Refresh()
    {
      ((AutoResizeTextView)this.mView.findViewById(2131296638)).setColors(MyButton.colNor, -3355444);
      AutoResizeTextView localAutoResizeTextView = (AutoResizeTextView)this.mView.findViewById(2131296640);
      localAutoResizeTextView.setColors(MyButton.colNor, -3355444);
      Object localObject = DataGetter.g_datas((short)181, 10);
      localAutoResizeTextView.setText((char)localObject[0] + "." + (char)localObject[1] + (char)localObject[2] + (char)localObject[3] + "." + (char)localObject[4] + "." + (char)localObject[5] + "." + (char)localObject[6] + "." + (char)localObject[7] + (char)localObject[8] + (char)localObject[9]);
      localObject = (AutoResizeTextView)this.mView.findViewById(2131296642);
      ((AutoResizeTextView)localObject).setColors(MyButton.colNor, -3355444);
      ((AutoResizeTextView)localObject).setText(DealMsg.getVIN());
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\BxShowAllVersion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */