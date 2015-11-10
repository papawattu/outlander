package com.inventec.iMobile.popup;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import com.inventec.iMobile.baseClass.myActivity;

public class QuickAction
  extends PopupWindows
{
  protected static final int ANIM_AUTO = 5;
  protected static final int ANIM_GROW_FROM_CENTER = 3;
  protected static final int ANIM_GROW_FROM_LEFT = 1;
  protected static final int ANIM_GROW_FROM_RIGHT = 2;
  protected static final int ANIM_REFLECT = 4;
  private int animStyle;
  private LayoutInflater inflater;
  private myActivity mAct;
  private ImageView mArrowDown;
  private ImageView mArrowUp;
  private int mChildPos;
  private OnActionItemClickListener mListener;
  private View mRootView;
  private ScrollView mScroller;
  private ViewGroup mTrack;
  
  public QuickAction(Context paramContext)
  {
    super(paramContext);
    this.mAct = ((myActivity)paramContext);
    this.inflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    setRootViewId(2130903102);
    this.animStyle = 5;
    this.mChildPos = 0;
  }
  
  private void setAnimationStyle(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int i = 2131230729;
    int k = 2131230728;
    int j = 2131230725;
    paramInt2 -= this.mArrowUp.getMeasuredWidth() / 2;
    switch (this.animStyle)
    {
    }
    for (;;)
    {
      return;
      PopupWindow localPopupWindow = this.mWindow;
      if (paramBoolean) {}
      for (;;)
      {
        localPopupWindow.setAnimationStyle(i);
        break;
        i = 2131230724;
      }
      localPopupWindow = this.mWindow;
      if (paramBoolean) {}
      for (paramInt1 = 2131230730;; paramInt1 = 2131230725)
      {
        localPopupWindow.setAnimationStyle(paramInt1);
        break;
      }
      localPopupWindow = this.mWindow;
      if (paramBoolean) {}
      for (paramInt1 = 2131230728;; paramInt1 = 2131230723)
      {
        localPopupWindow.setAnimationStyle(paramInt1);
        break;
      }
      localPopupWindow = this.mWindow;
      if (paramBoolean) {}
      for (paramInt1 = 2131230731;; paramInt1 = 2131230726)
      {
        localPopupWindow.setAnimationStyle(paramInt1);
        break;
      }
      if (paramInt2 <= paramInt1 / 4)
      {
        localPopupWindow = this.mWindow;
        if (paramBoolean) {}
        for (;;)
        {
          localPopupWindow.setAnimationStyle(i);
          break;
          i = 2131230724;
        }
      }
      if ((paramInt2 > paramInt1 / 4) && (paramInt2 < paramInt1 / 4 * 3))
      {
        localPopupWindow = this.mWindow;
        if (paramBoolean) {}
        for (paramInt1 = k;; paramInt1 = 2131230723)
        {
          localPopupWindow.setAnimationStyle(paramInt1);
          break;
        }
      }
      localPopupWindow = this.mWindow;
      paramInt1 = j;
      if (paramBoolean) {
        paramInt1 = 2131230730;
      }
      localPopupWindow.setAnimationStyle(paramInt1);
    }
  }
  
  private void showArrow(int paramInt1, int paramInt2)
  {
    ImageView localImageView1;
    if (paramInt1 == 2131296635)
    {
      localImageView1 = this.mArrowUp;
      if (paramInt1 != 2131296635) {
        break label66;
      }
    }
    label66:
    for (ImageView localImageView2 = this.mArrowDown;; localImageView2 = this.mArrowUp)
    {
      paramInt1 = this.mArrowUp.getMeasuredWidth();
      localImageView1.setVisibility(0);
      ((ViewGroup.MarginLayoutParams)localImageView1.getLayoutParams()).leftMargin = (paramInt2 - paramInt1 / 2);
      localImageView2.setVisibility(4);
      return;
      localImageView1 = this.mArrowDown;
      break;
    }
  }
  
  public void addActionItem(ActionItem paramActionItem)
  {
    String str = paramActionItem.getTitle();
    View localView = this.inflater.inflate(2130903040, null);
    paramActionItem = (TextView)localView.findViewById(2131296256);
    this.mAct.enlargeViewToFitScreen(paramActionItem, true);
    if (str != null) {
      paramActionItem.setText(this.mChildPos + 1 + ". " + str);
    }
    for (;;)
    {
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (QuickAction.this.mListener != null) {
            QuickAction.this.mListener.onItemClick(this.val$pos);
          }
          QuickAction.this.dismiss();
        }
      });
      localView.setFocusable(true);
      localView.setClickable(true);
      this.mTrack.addView(localView, this.mChildPos);
      this.mChildPos += 1;
      return;
      paramActionItem.setVisibility(8);
    }
  }
  
  public void setAnimStyle(int paramInt)
  {
    this.animStyle = paramInt;
  }
  
  public void setOnActionItemClickListener(OnActionItemClickListener paramOnActionItemClickListener)
  {
    this.mListener = paramOnActionItemClickListener;
  }
  
  public void setRootViewId(int paramInt)
  {
    this.mRootView = ((ViewGroup)this.inflater.inflate(paramInt, null));
    this.mTrack = ((ViewGroup)this.mRootView.findViewById(2131296634));
    this.mAct.enlargeViewToFitScreen(this.mTrack, false);
    this.mArrowDown = ((ImageView)this.mRootView.findViewById(2131296636));
    this.mAct.enlargeViewToFitScreen(this.mArrowDown, false);
    this.mArrowUp = ((ImageView)this.mRootView.findViewById(2131296635));
    this.mAct.enlargeViewToFitScreen(this.mArrowUp, false);
    this.mScroller = ((ScrollView)this.mRootView.findViewById(2131296633));
    this.mAct.enlargeViewToFitScreen(this.mScroller, false);
    this.mRootView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    setContentView(this.mRootView);
  }
  
  public void show(View paramView)
  {
    preShow();
    Object localObject = new int[2];
    paramView.getLocationOnScreen((int[])localObject);
    localRect = new Rect(localObject[0], localObject[1], localObject[0] + paramView.getWidth(), localObject[1] + paramView.getHeight());
    try
    {
      ViewGroup.LayoutParams localLayoutParams = this.mRootView.getLayoutParams();
      localObject = localLayoutParams;
      if (localLayoutParams == null)
      {
        localObject = new android/view/ViewGroup$LayoutParams;
        ((ViewGroup.LayoutParams)localObject).<init>(-2, -2);
      }
      ((ViewGroup.LayoutParams)localObject).width = -2;
      ((ViewGroup.LayoutParams)localObject).height = -2;
      this.mRootView.setLayoutParams((ViewGroup.LayoutParams)localObject);
      this.mRootView.measure(-2, -2);
    }
    catch (Exception localException)
    {
      for (;;)
      {
        int n;
        int i;
        int m;
        int j;
        int k;
        int i1;
        boolean bool;
        Log.e("QA err", localException.toString());
        continue;
        if (paramView.getWidth() > i)
        {
          i = localRect.centerX() - i / 2;
        }
        else
        {
          i = localRect.left;
          continue;
          bool = false;
          continue;
          j = localRect.top - n;
          continue;
          k = localRect.bottom;
          j = k;
          if (n > i1)
          {
            this.mScroller.getLayoutParams().height = i1;
            j = k;
            continue;
            k = 2131296635;
            continue;
            TextView localTextView = (TextView)this.mTrack.getChildAt(k).findViewById(2131296256);
            localTextView.getLayoutParams().width = m;
            localTextView.setMaxLines(1);
            k++;
          }
        }
      }
    }
    n = this.mRootView.getMeasuredHeight();
    i = this.mRootView.getMeasuredWidth();
    localObject = this.mRootView.getResources().getDisplayMetrics();
    m = ((DisplayMetrics)localObject).widthPixels;
    j = ((DisplayMetrics)localObject).heightPixels;
    if (localRect.left + i > m)
    {
      i = localRect.left - (i - paramView.getWidth());
      k = localRect.top;
      i1 = j - localRect.bottom;
      if (k <= i1) {
        break label365;
      }
      bool = true;
      if (!bool) {
        break label384;
      }
      if (n <= k) {
        break label371;
      }
      j = 15;
      this.mScroller.getLayoutParams().height = (k - paramView.getHeight());
      if (!bool) {
        break label421;
      }
      k = 2131296636;
      showArrow(k, localRect.centerX() - i);
      setAnimationStyle(m, localRect.centerX(), bool);
      n = this.mTrack.getChildCount();
      m = this.mScroller.getMeasuredWidth();
      this.mScroller.getMeasuredHeight();
      k = 0;
      if (k < n) {
        break label428;
      }
      this.mWindow.showAtLocation(paramView, 0, i, j);
    }
  }
  
  public static abstract interface OnActionItemClickListener
  {
    public abstract void onItemClick(int paramInt);
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\popup\QuickAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */