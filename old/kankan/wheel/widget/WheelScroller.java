package kankan.wheel.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class WheelScroller
{
  public static final int MIN_DELTA_FOR_SCROLLING = 1;
  private static final int SCROLLING_DURATION = 400;
  private final int MESSAGE_JUSTIFY = 1;
  private final int MESSAGE_SCROLL = 0;
  private Handler animationHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      WheelScroller.this.scroller.computeScrollOffset();
      int j = WheelScroller.this.scroller.getCurrY();
      int i = WheelScroller.this.lastScrollY - j;
      WheelScroller.this.lastScrollY = j;
      if (i != 0) {
        WheelScroller.this.listener.onScroll(i);
      }
      if (Math.abs(j - WheelScroller.this.scroller.getFinalY()) < 1)
      {
        WheelScroller.this.scroller.getFinalY();
        WheelScroller.this.scroller.forceFinished(true);
      }
      if (!WheelScroller.this.scroller.isFinished()) {
        WheelScroller.this.animationHandler.sendEmptyMessage(paramAnonymousMessage.what);
      }
      for (;;)
      {
        return;
        if (paramAnonymousMessage.what == 0) {
          WheelScroller.this.justify();
        } else {
          WheelScroller.this.finishScrolling();
        }
      }
    }
  };
  private Context context;
  private GestureDetector gestureDetector = new GestureDetector(paramContext, this.gestureListener);
  private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener()
  {
    public boolean onFling(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      WheelScroller.this.lastScrollY = 0;
      WheelScroller.this.scroller.fling(0, WheelScroller.this.lastScrollY, 0, (int)-paramAnonymousFloat2, 0, 0, -2147483647, Integer.MAX_VALUE);
      WheelScroller.this.setNextMessage(0);
      return true;
    }
    
    public boolean onScroll(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      return true;
    }
  };
  private boolean isScrollingPerformed;
  private int lastScrollY;
  private float lastTouchedY;
  private ScrollingListener listener;
  private Scroller scroller;
  
  public WheelScroller(Context paramContext, ScrollingListener paramScrollingListener)
  {
    this.gestureDetector.setIsLongpressEnabled(false);
    this.scroller = new Scroller(paramContext);
    this.listener = paramScrollingListener;
    this.context = paramContext;
  }
  
  private void clearMessages()
  {
    this.animationHandler.removeMessages(0);
    this.animationHandler.removeMessages(1);
  }
  
  private void justify()
  {
    this.listener.onJustify();
    setNextMessage(1);
  }
  
  private void setNextMessage(int paramInt)
  {
    clearMessages();
    this.animationHandler.sendEmptyMessage(paramInt);
  }
  
  private void startScrolling()
  {
    if (!this.isScrollingPerformed)
    {
      this.isScrollingPerformed = true;
      this.listener.onStarted();
    }
  }
  
  void finishScrolling()
  {
    if (this.isScrollingPerformed)
    {
      this.listener.onFinished();
      this.isScrollingPerformed = false;
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      if ((!this.gestureDetector.onTouchEvent(paramMotionEvent)) && (paramMotionEvent.getAction() == 1)) {
        justify();
      }
      return true;
      this.lastTouchedY = paramMotionEvent.getY();
      this.scroller.forceFinished(true);
      clearMessages();
      continue;
      int i = (int)(paramMotionEvent.getY() - this.lastTouchedY);
      if (i != 0)
      {
        startScrolling();
        this.listener.onScroll(i);
        this.lastTouchedY = paramMotionEvent.getY();
      }
    }
  }
  
  public void scroll(int paramInt1, int paramInt2)
  {
    this.scroller.forceFinished(true);
    this.lastScrollY = 0;
    Scroller localScroller = this.scroller;
    if (paramInt2 != 0) {}
    for (;;)
    {
      localScroller.startScroll(0, 0, 0, paramInt1, paramInt2);
      setNextMessage(0);
      startScrolling();
      return;
      paramInt2 = 400;
    }
  }
  
  public void setInterpolator(Interpolator paramInterpolator)
  {
    this.scroller.forceFinished(true);
    this.scroller = new Scroller(this.context, paramInterpolator);
  }
  
  public void stopScrolling()
  {
    this.scroller.forceFinished(true);
  }
  
  public static abstract interface ScrollingListener
  {
    public abstract void onFinished();
    
    public abstract void onJustify();
    
    public abstract void onScroll(int paramInt);
    
    public abstract void onStarted();
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\kankan\wheel\widget\WheelScroller.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */