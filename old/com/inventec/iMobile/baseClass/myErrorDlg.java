package com.inventec.iMobile.baseClass;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.iMobile_AppGlobalVar;

public class myErrorDlg
  extends Dialog
{
  ImageView imageView1;
  myActivity mAct;
  private CloseCallback m_WaitOverCallback = null;
  AutoResizeTextView txtcontent;
  AutoResizeTextView txtdate;
  AutoResizeTextView txttitle;
  
  public myErrorDlg(myActivity parammyActivity, int paramInt)
  {
    super(parammyActivity, 2131230720);
    this.mAct = parammyActivity;
    setView(parammyActivity.getLayoutInflater().inflate(paramInt, null));
  }
  
  public myErrorDlg(myActivity parammyActivity, View paramView)
  {
    super(parammyActivity, 2131230720);
    this.mAct = parammyActivity;
    setView(paramView);
  }
  
  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(paramFloat * paramContext.getResources().getDisplayMetrics().density + 0.5F);
  }
  
  private void setView(View paramView)
  {
    setContentView(paramView);
    ((ViewGroup.MarginLayoutParams)paramView.getLayoutParams()).topMargin = myActivity.statusBarHeight;
    paramView = paramView.getLayoutParams();
    paramView.height = (iMobile_AppGlobalVar.screenH - myActivity.statusBarHeight);
    paramView.width = iMobile_AppGlobalVar.screenW;
    getWindow().setGravity(80);
    this.mAct.setEarth((ImageView)findViewById(2131296257));
    paramView = (MyButton)findViewById(2131296262);
    this.txtdate = ((AutoResizeTextView)findViewById(2131296261));
    this.mAct.enlargeViewToFitScreen(this.txtdate, true);
    this.txtcontent = ((AutoResizeTextView)findViewById(2131296259));
    this.mAct.enlargeViewToFitScreen(this.txtcontent, true);
    this.txttitle = ((AutoResizeTextView)findViewById(2131296258));
    this.mAct.enlargeViewToFitScreen(this.txttitle, true);
    this.txtdate.setText("");
    this.imageView1 = ((ImageView)findViewById(2131296260));
    this.mAct.enlargeViewToFitScreen(this.imageView1, false);
    if (paramView != null)
    {
      this.mAct.enlargeViewToFitScreen(paramView, true);
      paramView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (myErrorDlg.this.m_WaitOverCallback != null) {
            myErrorDlg.this.m_WaitOverCallback.DoAction();
          }
        }
      });
    }
  }
  
  public void AdjustTitleSize(int paramInt1, int paramInt2)
  {
    ViewGroup.LayoutParams localLayoutParams = this.txttitle.getLayoutParams();
    localLayoutParams.width = ((int)(localLayoutParams.width * (paramInt1 / 100.0F)));
    localLayoutParams = this.txttitle.getLayoutParams();
    localLayoutParams.height = ((int)(localLayoutParams.height * (paramInt2 / 100.0F)));
  }
  
  public void SetOnCloseCallback(CloseCallback paramCloseCallback)
  {
    this.m_WaitOverCallback = paramCloseCallback;
  }
  
  public void SetText(TextView paramTextView, String paramString, float paramFloat)
  {
    paramTextView.setText(paramString);
  }
  
  public void SetTextColor(AutoResizeTextView paramAutoResizeTextView, int paramInt)
  {
    paramAutoResizeTextView.setColors(paramInt, 0);
  }
  
  public void SetTextContent(String paramString, float paramFloat)
  {
    this.txtcontent.setText(paramString);
  }
  
  public void SetTextDate(String paramString, float paramFloat)
  {
    this.txtdate.setText(paramString);
  }
  
  public void SetTextTitle(String paramString, float paramFloat)
  {
    this.txttitle.setText(paramString);
  }
  
  public void hideAlertMark()
  {
    this.imageView1.setVisibility(8);
  }
  
  public void hideDate()
  {
    this.txtdate.setVisibility(8);
  }
  
  public void onBackPressed() {}
  
  public void reLoadView(int paramInt)
  {
    setView(this.mAct.getLayoutInflater().inflate(paramInt, null));
  }
  
  public void setDateText(String paramString)
  {
    this.txtdate.setText(paramString);
  }
  
  public static abstract interface CloseCallback
  {
    public abstract void DoAction();
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseClass\myErrorDlg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */