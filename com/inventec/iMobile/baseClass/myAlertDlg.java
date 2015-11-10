package com.inventec.iMobile.baseClass;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.inventec.controls.AutoResizeTextView;
import com.inventec.controls.MyButton;
import com.inventec.iMobile.iMobile_AppGlobalVar;

public class myAlertDlg
  extends Dialog
{
  boolean bDoDismiss = true;
  private myActivity mAct = null;
  private CloseCallback m_cancelCallback = null;
  private CloseCallback m_okCallback = null;
  Handler myhd = null;
  TextView txtContent;
  TextView txtdetail;
  LinearLayout txtdetialrLayout;
  LinearLayout upRelativeLayout;
  WebView wview;
  
  public myAlertDlg(myActivity parammyActivity)
  {
    super(parammyActivity, 2131230720);
    this.mAct = parammyActivity;
    setContent(parammyActivity.getLayoutInflater().inflate(2130903091, null));
  }
  
  public myAlertDlg(myActivity parammyActivity, int paramInt)
  {
    super(parammyActivity, 2131230720);
    this.mAct = parammyActivity;
    setContent(parammyActivity.getLayoutInflater().inflate(paramInt, null));
  }
  
  protected int GetWindowHeight()
  {
    return iMobile_AppGlobalVar.screenH;
  }
  
  public void hideContents(int paramInt)
  {
    findViewById(paramInt).setVisibility(8);
  }
  
  public void noDoDismiss()
  {
    this.myhd = new Handler();
    this.bDoDismiss = false;
  }
  
  public void onBackPressed() {}
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    Activity localActivity = getOwnerActivity();
    if (localActivity != null)
    {
      localActivity.onPrepareOptionsMenu(paramMenu);
      if (!this.bDoDismiss) {
        break label28;
      }
      dismiss();
    }
    for (;;)
    {
      return true;
      label28:
      this.myhd.postDelayed(new Runnable()
      {
        public void run()
        {
          myAlertDlg.this.dismiss();
        }
      }, 500L);
    }
  }
  
  public void setCancelCallback(CloseCallback paramCloseCallback)
  {
    this.m_cancelCallback = paramCloseCallback;
  }
  
  public void setContent(View paramView)
  {
    setContentView(paramView);
    ((ViewGroup.MarginLayoutParams)paramView.getLayoutParams()).topMargin = myActivity.statusBarHeight;
    paramView = paramView.getLayoutParams();
    paramView.height = (iMobile_AppGlobalVar.screenH - myActivity.statusBarHeight);
    paramView.width = iMobile_AppGlobalVar.screenW;
    getWindow().setGravity(80);
    this.mAct.setEarth((ImageView)findViewById(2131296257));
    this.txtContent = ((TextView)findViewById(2131296266));
    this.txtdetail = ((TextView)findViewById(2131296617));
    this.txtdetialrLayout = ((LinearLayout)findViewById(2131296616));
    this.upRelativeLayout = ((LinearLayout)findViewById(2131296615));
    this.wview = ((WebView)findViewById(2131296405));
    paramView = (MyButton)findViewById(2131296262);
    if (paramView != null) {
      this.mAct.enlargeViewToFitScreen(paramView, true);
    }
    paramView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (myAlertDlg.this.bDoDismiss) {
          myAlertDlg.this.dismiss();
        }
        for (;;)
        {
          if (myAlertDlg.this.m_okCallback != null) {
            myAlertDlg.this.m_okCallback.DoAction();
          }
          return;
          myAlertDlg.this.myhd.postDelayed(new Runnable()
          {
            public void run()
            {
              myAlertDlg.this.dismiss();
            }
          }, 500L);
        }
      }
    });
    paramView = (MyButton)findViewById(2131296618);
    if (paramView != null)
    {
      this.mAct.enlargeViewToFitScreen(paramView, true);
      paramView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (myAlertDlg.this.bDoDismiss) {
            myAlertDlg.this.dismiss();
          }
          for (;;)
          {
            if (myAlertDlg.this.m_cancelCallback != null) {
              myAlertDlg.this.m_cancelCallback.DoAction();
            }
            return;
            myAlertDlg.this.myhd.postDelayed(new Runnable()
            {
              public void run()
              {
                myAlertDlg.this.dismiss();
              }
            }, 500L);
          }
        }
      });
    }
  }
  
  public void setDlgContent(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    Object localObject = (TextView)findViewById(2131296266);
    if (localObject != null)
    {
      this.mAct.enlargeViewToFitScreen((View)localObject, true);
      if (paramInt1 != 0) {
        ((TextView)localObject).setText(paramInt1);
      }
    }
    else
    {
      localObject = (TextView)findViewById(2131296617);
      if (localObject != null)
      {
        this.mAct.enlargeViewToFitScreen((View)localObject, true);
        if (paramInt2 == 0) {
          break label135;
        }
        ((TextView)localObject).setText(paramInt2);
      }
    }
    for (;;)
    {
      localObject = (MyButton)findViewById(2131296618);
      if ((paramBoolean) && (localObject != null)) {
        ((MyButton)localObject).setVisibility(8);
      }
      this.mAct.setEarth((ImageView)findViewById(2131296257));
      return;
      ((TextView)localObject).setVisibility(8);
      this.upRelativeLayout.setVisibility(8);
      break;
      label135:
      this.txtdetialrLayout.setVisibility(8);
    }
  }
  
  public void setImage(int paramInt)
  {
    View localView = findViewById(paramInt);
    this.mAct.enlargeViewToFitScreen(localView, false);
  }
  
  public void setOkCallback(CloseCallback paramCloseCallback)
  {
    this.m_okCallback = paramCloseCallback;
  }
  
  public void setWorn()
  {
    AutoResizeTextView localAutoResizeTextView = (AutoResizeTextView)findViewById(2131296266);
    localAutoResizeTextView.setColors(-65536, -12303292);
    localAutoResizeTextView.setBackgroundResource(2130837811);
    localAutoResizeTextView.setPadding(20, 0, 20, 0);
    ((AutoResizeTextView)findViewById(2131296617)).setColors(-65536, -12303292);
  }
  
  public void showContents(int paramInt)
  {
    findViewById(paramInt).setVisibility(0);
  }
  
  public static abstract interface CloseCallback
  {
    public abstract void DoAction();
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\baseClass\myAlertDlg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */