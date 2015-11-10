package com.inventec.iMobile.baseClass;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.iMobile_AppGlobalVar;

public class myNaviDlg
  extends Dialog
{
  myActivity mAct = null;
  
  public myNaviDlg(myActivity parammyActivity)
  {
    super(parammyActivity, 2131230720);
    this.mAct = parammyActivity;
    Object localObject = parammyActivity.getLayoutInflater().inflate(2130903100, null);
    setContentView((View)localObject);
    ((ViewGroup.MarginLayoutParams)((View)localObject).getLayoutParams()).topMargin = myActivity.statusBarHeight;
    localObject = ((View)localObject).getLayoutParams();
    ((ViewGroup.LayoutParams)localObject).height = (iMobile_AppGlobalVar.screenH - myActivity.statusBarHeight);
    ((ViewGroup.LayoutParams)localObject).width = iMobile_AppGlobalVar.screenW;
    getWindow().setGravity(80);
    localObject = (TextView)findViewById(2131296627);
    this.mAct.enlargeViewToFitScreen((View)localObject, true);
    localObject = (MyButton)findViewById(2131296262);
    if (localObject != null)
    {
      this.mAct.enlargeViewToFitScreen((View)localObject, true);
      ((MyButton)localObject).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          myActivity.backTop = true;
          myNaviDlg.this.mAct.onBackPressed();
        }
      });
    }
    parammyActivity.setEarth((ImageView)findViewById(2131296257));
  }
  
  public void onBackPressed() {}
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseClass\myNaviDlg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */