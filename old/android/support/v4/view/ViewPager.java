package android.support.v4.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import android.support.v4.os.ParcelableCompat;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;
import android.support.v4.widget.EdgeEffectCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SoundEffectConstants;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ViewPager
  extends ViewGroup
{
  private static final Comparator<ItemInfo> COMPARATOR = new Comparator()
  {
    public int compare(ViewPager.ItemInfo paramAnonymousItemInfo1, ViewPager.ItemInfo paramAnonymousItemInfo2)
    {
      return paramAnonymousItemInfo1.position - paramAnonymousItemInfo2.position;
    }
  };
  private static final boolean DEBUG = false;
  private static final int DEFAULT_OFFSCREEN_PAGES = 1;
  private static final int INVALID_POINTER = -1;
  private static final int[] LAYOUT_ATTRS = { 16842931 };
  private static final int MAX_SETTLE_DURATION = 600;
  private static final int MIN_DISTANCE_FOR_FLING = 25;
  public static final int SCROLL_STATE_DRAGGING = 1;
  public static final int SCROLL_STATE_IDLE = 0;
  public static final int SCROLL_STATE_SETTLING = 2;
  private static final String TAG = "ViewPager";
  private static final boolean USE_CACHE = false;
  private static final Interpolator sInterpolator = new Interpolator()
  {
    public float getInterpolation(float paramAnonymousFloat)
    {
      paramAnonymousFloat -= 1.0F;
      return paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat * paramAnonymousFloat + 1.0F;
    }
  };
  boolean bDragLeft = false;
  boolean bTwoPage = false;
  private int mActivePointerId = -1;
  private PagerAdapter mAdapter;
  private OnAdapterChangeListener mAdapterChangeListener;
  private int mBottomPageBounds;
  private boolean mCalledSuper;
  private int mChildHeightMeasureSpec;
  private int mChildWidthMeasureSpec;
  private int mCurItem;
  private int mDecorChildCount;
  private long mFakeDragBeginTime;
  private boolean mFakeDragging;
  private boolean mFirstLayout = true;
  private int mFlingDistance;
  private boolean mInLayout;
  private float mInitialMotionX;
  private OnPageChangeListener mInternalPageChangeListener;
  private boolean mIsBeingDragged;
  private boolean mIsUnableToDrag;
  private final ArrayList<ItemInfo> mItems = new ArrayList();
  private float mLastMotionX;
  private float mLastMotionY;
  private EdgeEffectCompat mLeftEdge;
  private Drawable mMarginDrawable;
  private int mMaximumVelocity;
  private int mMinimumVelocity;
  private PagerObserver mObserver;
  private int mOffscreenPageLimit = 1;
  private OnPageChangeListener mOnPageChangeListener;
  private int mPageMargin;
  private boolean mPopulatePending;
  private Parcelable mRestoredAdapterState = null;
  private ClassLoader mRestoredClassLoader = null;
  private int mRestoredCurItem = -1;
  private EdgeEffectCompat mRightEdge;
  private int mScrollState = 0;
  private Scroller mScroller;
  private boolean mScrolling;
  private boolean mScrollingCacheEnabled;
  private int mTopPageBounds;
  private int mTouchSlop;
  private VelocityTracker mVelocityTracker;
  
  public ViewPager(Context paramContext)
  {
    super(paramContext);
    initViewPager();
  }
  
  public ViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initViewPager();
  }
  
  private void completeScroll()
  {
    boolean bool = this.mScrolling;
    if (bool)
    {
      setScrollingCacheEnabled(false);
      this.mScroller.abortAnimation();
      int m = getScrollX();
      int k = getScrollY();
      int i = this.mScroller.getCurrX();
      int j = this.mScroller.getCurrY();
      if ((m != i) || (k != j)) {
        scrollTo(i, j);
      }
      setScrollState(0);
    }
    this.mPopulatePending = false;
    this.mScrolling = false;
    if (bool) {
      populate();
    }
  }
  
  private int determineTargetPage(int paramInt1, float paramFloat, int paramInt2, int paramInt3)
  {
    if ((Math.abs(paramInt3) > this.mFlingDistance) && (Math.abs(paramInt2) > this.mMinimumVelocity)) {
      if (paramInt2 <= 0) {}
    }
    for (;;)
    {
      return paramInt1;
      paramInt1++;
      continue;
      paramInt1 = (int)(paramInt1 + paramFloat + 0.5F);
    }
  }
  
  private void endDrag()
  {
    this.mIsBeingDragged = false;
    this.mIsUnableToDrag = false;
    if (this.mVelocityTracker != null)
    {
      this.mVelocityTracker.recycle();
      this.mVelocityTracker = null;
    }
  }
  
  private void onSecondaryPointerUp(MotionEvent paramMotionEvent)
  {
    int i = MotionEventCompat.getActionIndex(paramMotionEvent);
    if (MotionEventCompat.getPointerId(paramMotionEvent, i) == this.mActivePointerId) {
      if (i != 0) {
        break label56;
      }
    }
    label56:
    for (i = 1;; i = 0)
    {
      this.mLastMotionX = MotionEventCompat.getX(paramMotionEvent, i);
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, i);
      if (this.mVelocityTracker != null) {
        this.mVelocityTracker.clear();
      }
      return;
    }
  }
  
  private void pageScrolled(int paramInt)
  {
    int i = getWidth() + this.mPageMargin;
    int j = paramInt / i;
    paramInt %= i;
    float f = paramInt / i;
    this.mCalledSuper = false;
    onPageScrolled(j, f, paramInt);
    if (!this.mCalledSuper) {
      throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }
  }
  
  private void recomputeScrollPosition(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 += paramInt3;
    if (paramInt2 > 0)
    {
      paramInt3 = getScrollX();
      paramInt4 = paramInt2 + paramInt4;
      paramInt2 = paramInt3 / paramInt4;
      float f = paramInt3 % paramInt4 / paramInt4;
      paramInt3 = (int)((paramInt2 + f) * paramInt1);
      scrollTo(paramInt3, getScrollY());
      if (!this.mScroller.isFinished())
      {
        paramInt4 = this.mScroller.getDuration();
        paramInt2 = this.mScroller.timePassed();
        this.mScroller.startScroll(paramInt3, 0, this.mCurItem * paramInt1, 0, paramInt4 - paramInt2);
      }
    }
    for (;;)
    {
      return;
      paramInt1 = this.mCurItem * paramInt1;
      if (paramInt1 != getScrollX())
      {
        completeScroll();
        scrollTo(paramInt1, getScrollY());
      }
    }
  }
  
  private void removeNonDecorViews()
  {
    int j;
    for (int i = 0;; i = j + 1)
    {
      if (i >= getChildCount()) {
        return;
      }
      j = i;
      if (!((LayoutParams)getChildAt(i).getLayoutParams()).isDecor)
      {
        removeViewAt(i);
        j = i - 1;
      }
    }
  }
  
  private void setScrollState(int paramInt)
  {
    if (this.mScrollState == paramInt) {}
    for (;;)
    {
      return;
      this.mScrollState = paramInt;
      if (this.mOnPageChangeListener != null) {
        this.mOnPageChangeListener.onPageScrollStateChanged(paramInt);
      }
    }
  }
  
  private void setScrollingCacheEnabled(boolean paramBoolean)
  {
    if (this.mScrollingCacheEnabled != paramBoolean) {
      this.mScrollingCacheEnabled = paramBoolean;
    }
  }
  
  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2)
  {
    int k = paramArrayList.size();
    int j = getDescendantFocusability();
    int i;
    if (j != 393216)
    {
      i = 0;
      if (i < getChildCount()) {}
    }
    else
    {
      if (((j != 262144) || (k == paramArrayList.size())) && (isFocusable())) {
        break label112;
      }
    }
    for (;;)
    {
      return;
      View localView = getChildAt(i);
      if (localView.getVisibility() == 0)
      {
        ItemInfo localItemInfo = infoForChild(localView);
        if ((localItemInfo != null) && (localItemInfo.position == this.mCurItem)) {
          localView.addFocusables(paramArrayList, paramInt1, paramInt2);
        }
      }
      i++;
      break;
      label112:
      if ((((paramInt2 & 0x1) != 1) || (!isInTouchMode()) || (isFocusableInTouchMode())) && (paramArrayList != null)) {
        paramArrayList.add(this);
      }
    }
  }
  
  void addNewItem(int paramInt1, int paramInt2)
  {
    ItemInfo localItemInfo = new ItemInfo();
    localItemInfo.position = paramInt1;
    localItemInfo.object = this.mAdapter.instantiateItem(this, paramInt1);
    if (paramInt2 < 0) {
      this.mItems.add(localItemInfo);
    }
    for (;;)
    {
      return;
      this.mItems.add(paramInt2, localItemInfo);
    }
  }
  
  public void addTouchables(ArrayList<View> paramArrayList)
  {
    for (int i = 0;; i++)
    {
      if (i >= getChildCount()) {
        return;
      }
      View localView = getChildAt(i);
      if (localView.getVisibility() == 0)
      {
        ItemInfo localItemInfo = infoForChild(localView);
        if ((localItemInfo != null) && (localItemInfo.position == this.mCurItem)) {
          localView.addTouchables(paramArrayList);
        }
      }
    }
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    ViewGroup.LayoutParams localLayoutParams = paramLayoutParams;
    if (!checkLayoutParams(paramLayoutParams)) {
      localLayoutParams = generateLayoutParams(paramLayoutParams);
    }
    paramLayoutParams = (LayoutParams)localLayoutParams;
    paramLayoutParams.isDecor |= paramView instanceof Decor;
    if (this.mInLayout)
    {
      if ((paramLayoutParams != null) && (paramLayoutParams.isDecor)) {
        throw new IllegalStateException("Cannot add pager decor view during layout");
      }
      addViewInLayout(paramView, paramInt, localLayoutParams);
      paramView.measure(this.mChildWidthMeasureSpec, this.mChildHeightMeasureSpec);
    }
    for (;;)
    {
      return;
      super.addView(paramView, paramInt, localLayoutParams);
    }
  }
  
  public boolean arrowScroll(int paramInt)
  {
    View localView2 = findFocus();
    View localView1 = localView2;
    if (localView2 == this) {
      localView1 = null;
    }
    boolean bool = false;
    localView2 = FocusFinder.getInstance().findNextFocus(this, localView1, paramInt);
    if ((localView2 != null) && (localView2 != localView1)) {
      if (paramInt == 17) {
        if ((localView1 != null) && (localView2.getLeft() >= localView1.getLeft())) {
          bool = pageLeft();
        }
      }
    }
    for (;;)
    {
      if (bool) {
        playSoundEffect(SoundEffectConstants.getContantForFocusDirection(paramInt));
      }
      return bool;
      bool = localView2.requestFocus();
      continue;
      if (paramInt == 66) {
        if ((localView1 != null) && (localView2.getLeft() <= localView1.getLeft()))
        {
          bool = pageRight();
        }
        else
        {
          bool = localView2.requestFocus();
          continue;
          if ((paramInt == 17) || (paramInt == 1)) {
            bool = pageLeft();
          } else if ((paramInt == 66) || (paramInt == 2)) {
            bool = pageRight();
          }
        }
      }
    }
  }
  
  public boolean beginFakeDrag()
  {
    boolean bool = false;
    if (this.mIsBeingDragged) {
      return bool;
    }
    this.mFakeDragging = true;
    setScrollState(1);
    this.mLastMotionX = 0.0F;
    this.mInitialMotionX = 0.0F;
    if (this.mVelocityTracker == null) {
      this.mVelocityTracker = VelocityTracker.obtain();
    }
    for (;;)
    {
      long l = SystemClock.uptimeMillis();
      MotionEvent localMotionEvent = MotionEvent.obtain(l, l, 0, 0.0F, 0.0F, 0);
      this.mVelocityTracker.addMovement(localMotionEvent);
      localMotionEvent.recycle();
      this.mFakeDragBeginTime = l;
      bool = true;
      break;
      this.mVelocityTracker.clear();
    }
  }
  
  protected boolean canScroll(View paramView, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    ViewGroup localViewGroup;
    int j;
    int k;
    int i;
    if ((paramView instanceof ViewGroup))
    {
      localViewGroup = (ViewGroup)paramView;
      j = paramView.getScrollX();
      k = paramView.getScrollY();
      i = localViewGroup.getChildCount() - 1;
      if (i >= 0) {}
    }
    else
    {
      if ((!paramBoolean) || (!ViewCompat.canScrollHorizontally(paramView, -paramInt1))) {
        break label161;
      }
      paramBoolean = true;
    }
    for (;;)
    {
      return paramBoolean;
      View localView = localViewGroup.getChildAt(i);
      if ((paramInt2 + j >= localView.getLeft()) && (paramInt2 + j < localView.getRight()) && (paramInt3 + k >= localView.getTop()) && (paramInt3 + k < localView.getBottom()) && (canScroll(localView, true, paramInt1, paramInt2 + j - localView.getLeft(), paramInt3 + k - localView.getTop())))
      {
        paramBoolean = true;
      }
      else
      {
        i--;
        break;
        label161:
        paramBoolean = false;
      }
    }
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if (((paramLayoutParams instanceof LayoutParams)) && (super.checkLayoutParams(paramLayoutParams))) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public void computeScroll()
  {
    if (!this.mScroller.isFinished())
    {
      if (!this.mScroller.computeScrollOffset()) {
        break label79;
      }
      int k = getScrollX();
      int m = getScrollY();
      int i = this.mScroller.getCurrX();
      int j = this.mScroller.getCurrY();
      if ((k != i) || (m != j))
      {
        scrollTo(i, j);
        pageScrolled(i);
      }
      invalidate();
    }
    for (;;)
    {
      return;
      this.bDragLeft = false;
      label79:
      completeScroll();
    }
  }
  
  void dataSetChanged()
  {
    if ((this.mItems.size() < 3) && (this.mItems.size() < this.mAdapter.getCount())) {}
    int i;
    int j;
    int n;
    for (int k = 1;; k = 0)
    {
      i = -1;
      j = 0;
      n = 0;
      if (n < this.mItems.size()) {
        break;
      }
      if (j != 0) {
        this.mAdapter.finishUpdate(this);
      }
      Collections.sort(this.mItems, COMPARATOR);
      if (i >= 0)
      {
        setCurrentItemInternal(i, false, true);
        k = 1;
      }
      if (k != 0)
      {
        populate();
        requestLayout();
      }
      return;
    }
    ItemInfo localItemInfo = (ItemInfo)this.mItems.get(n);
    int i3 = this.mAdapter.getItemPosition(localItemInfo.object);
    int m;
    int i1;
    int i2;
    if (i3 == -1)
    {
      m = i;
      i1 = j;
      i2 = n;
    }
    for (;;)
    {
      n = i2 + 1;
      j = i1;
      i = m;
      break;
      if (i3 == -2)
      {
        this.mItems.remove(n);
        i3 = n - 1;
        n = j;
        if (j == 0)
        {
          this.mAdapter.startUpdate(this);
          n = 1;
        }
        this.mAdapter.destroyItem(this, localItemInfo.position, localItemInfo.object);
        j = 1;
        i2 = i3;
        i1 = n;
        k = j;
        m = i;
        if (this.mCurItem == localItemInfo.position)
        {
          m = Math.max(0, Math.min(this.mCurItem, this.mAdapter.getCount() - 1));
          i2 = i3;
          i1 = n;
          k = j;
        }
      }
      else
      {
        i2 = n;
        i1 = j;
        m = i;
        if (localItemInfo.position != i3)
        {
          if (localItemInfo.position == this.mCurItem) {
            i = i3;
          }
          localItemInfo.position = i3;
          k = 1;
          i2 = n;
          i1 = j;
          m = i;
        }
      }
    }
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((!super.dispatchKeyEvent(paramKeyEvent)) && (!executeKeyEvent(paramKeyEvent))) {}
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    int j = getChildCount();
    for (int i = 0;; i++)
    {
      if (i >= j) {}
      for (boolean bool = false;; bool = true)
      {
        return bool;
        View localView = getChildAt(i);
        if (localView.getVisibility() != 0) {
          break;
        }
        ItemInfo localItemInfo = infoForChild(localView);
        if ((localItemInfo == null) || (localItemInfo.position != this.mCurItem) || (!localView.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent))) {
          break;
        }
      }
    }
  }
  
  float distanceInfluenceForSnapDuration(float paramFloat)
  {
    return (float)Math.sin((float)((paramFloat - 0.5F) * 0.4712389167638204D));
  }
  
  public void draw(Canvas paramCanvas)
  {
    int m = 1;
    super.draw(paramCanvas);
    int k = 0;
    int i = 0;
    int n = ViewCompat.getOverScrollMode(this);
    boolean bool;
    if ((n == 0) || ((n == 1) && (this.mAdapter != null) && (this.mAdapter.getCount() > 1)))
    {
      int j;
      if (!this.mLeftEdge.isFinished())
      {
        k = paramCanvas.save();
        i = getHeight() - getPaddingTop() - getPaddingBottom();
        paramCanvas.rotate(270.0F);
        paramCanvas.translate(-i + getPaddingTop(), 0.0F);
        this.mLeftEdge.setSize(i, getWidth());
        j = false | this.mLeftEdge.draw(paramCanvas);
        paramCanvas.restoreToCount(k);
      }
      k = j;
      if (!this.mRightEdge.isFinished())
      {
        n = paramCanvas.save();
        int i3 = getWidth();
        int i4 = getHeight();
        int i1 = getPaddingTop();
        int i2 = getPaddingBottom();
        k = m;
        if (this.mAdapter != null) {
          k = this.mAdapter.getCount();
        }
        paramCanvas.rotate(90.0F);
        paramCanvas.translate(-getPaddingTop(), -k * (this.mPageMargin + i3) + this.mPageMargin);
        this.mRightEdge.setSize(i4 - i1 - i2, i3);
        bool = j | this.mRightEdge.draw(paramCanvas);
        paramCanvas.restoreToCount(n);
      }
    }
    for (;;)
    {
      if (bool) {
        invalidate();
      }
      return;
      this.mLeftEdge.finish();
      this.mRightEdge.finish();
    }
  }
  
  protected void drawableStateChanged()
  {
    super.drawableStateChanged();
    Drawable localDrawable = this.mMarginDrawable;
    if ((localDrawable != null) && (localDrawable.isStateful())) {
      localDrawable.setState(getDrawableState());
    }
  }
  
  public void endFakeDrag()
  {
    if (!this.mFakeDragging) {
      throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }
    VelocityTracker localVelocityTracker = this.mVelocityTracker;
    localVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
    int m = (int)VelocityTrackerCompat.getYVelocity(localVelocityTracker, this.mActivePointerId);
    this.mPopulatePending = true;
    int j = (int)(this.mLastMotionX - this.mInitialMotionX);
    int k = getScrollX();
    int i = getWidth() + this.mPageMargin;
    setCurrentItemInternal(determineTargetPage(k / i, k % i / i, m, j), true, true, m);
    endDrag();
    this.mFakeDragging = false;
  }
  
  public boolean executeKeyEvent(KeyEvent paramKeyEvent)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramKeyEvent.getAction() == 0) {
      switch (paramKeyEvent.getKeyCode())
      {
      default: 
        bool1 = bool2;
      }
    }
    for (;;)
    {
      return bool1;
      bool1 = arrowScroll(17);
      continue;
      bool1 = arrowScroll(66);
      continue;
      bool1 = bool2;
      if (Build.VERSION.SDK_INT >= 11) {
        if (KeyEventCompat.hasNoModifiers(paramKeyEvent))
        {
          bool1 = arrowScroll(2);
        }
        else
        {
          bool1 = bool2;
          if (KeyEventCompat.hasModifiers(paramKeyEvent, 1)) {
            bool1 = arrowScroll(1);
          }
        }
      }
    }
  }
  
  public void fakeDragBy(float paramFloat)
  {
    if (!this.mFakeDragging) {
      throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
    }
    this.mLastMotionX += paramFloat;
    float f1 = getScrollX() - paramFloat;
    int i = getWidth() + this.mPageMargin;
    paramFloat = Math.max(0, (this.mCurItem - 1) * i);
    float f2 = Math.min(this.mCurItem + 1, this.mAdapter.getCount() - 1) * i;
    if (f1 < paramFloat) {}
    for (;;)
    {
      this.mLastMotionX += paramFloat - (int)paramFloat;
      scrollTo((int)paramFloat, getScrollY());
      pageScrolled((int)paramFloat);
      long l = SystemClock.uptimeMillis();
      MotionEvent localMotionEvent = MotionEvent.obtain(this.mFakeDragBeginTime, l, 2, this.mLastMotionX, 0.0F, 0);
      this.mVelocityTracker.addMovement(localMotionEvent);
      localMotionEvent.recycle();
      return;
      paramFloat = f1;
      if (f1 > f2) {
        paramFloat = f2;
      }
    }
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    return new LayoutParams();
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return generateDefaultLayoutParams();
  }
  
  public PagerAdapter getAdapter()
  {
    return this.mAdapter;
  }
  
  public int getCurrentItem()
  {
    return this.mCurItem;
  }
  
  public int getOffscreenPageLimit()
  {
    return this.mOffscreenPageLimit;
  }
  
  public int getPageMargin()
  {
    return this.mPageMargin;
  }
  
  ItemInfo infoForAnyChild(View paramView)
  {
    for (;;)
    {
      ViewParent localViewParent = paramView.getParent();
      if (localViewParent == this) {}
      for (paramView = infoForChild(paramView);; paramView = null)
      {
        return paramView;
        if ((localViewParent != null) && ((localViewParent instanceof View))) {
          break;
        }
      }
      paramView = (View)localViewParent;
    }
  }
  
  ItemInfo infoForChild(View paramView)
  {
    for (int i = 0;; i++)
    {
      Object localObject;
      if (i >= this.mItems.size()) {
        localObject = null;
      }
      ItemInfo localItemInfo;
      do
      {
        return (ItemInfo)localObject;
        localItemInfo = (ItemInfo)this.mItems.get(i);
        localObject = localItemInfo;
      } while (this.mAdapter.isViewFromObject(paramView, localItemInfo.object));
    }
  }
  
  void initViewPager()
  {
    setWillNotDraw(false);
    setDescendantFocusability(262144);
    setFocusable(true);
    Context localContext = getContext();
    this.mScroller = new Scroller(localContext, sInterpolator);
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(localContext);
    this.mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(localViewConfiguration);
    this.mMinimumVelocity = localViewConfiguration.getScaledMinimumFlingVelocity();
    this.mMaximumVelocity = localViewConfiguration.getScaledMaximumFlingVelocity();
    this.mLeftEdge = new EdgeEffectCompat(localContext);
    this.mRightEdge = new EdgeEffectCompat(localContext);
    this.mFlingDistance = ((int)(25.0F * localContext.getResources().getDisplayMetrics().density));
  }
  
  public boolean isFakeDragging()
  {
    return this.mFakeDragging;
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.mFirstLayout = true;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((this.mPageMargin > 0) && (this.mMarginDrawable != null))
    {
      int j = getScrollX();
      int k = getWidth();
      int i = j % (this.mPageMargin + k);
      if (i != 0)
      {
        i = j - i + k;
        this.mMarginDrawable.setBounds(i, this.mTopPageBounds, this.mPageMargin + i, this.mBottomPageBounds);
        this.mMarginDrawable.draw(paramCanvas);
      }
    }
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction() & 0xFF;
    boolean bool;
    if ((i == 3) || (i == 1))
    {
      this.mIsBeingDragged = false;
      this.mIsUnableToDrag = false;
      this.mActivePointerId = -1;
      if (this.mVelocityTracker != null)
      {
        this.mVelocityTracker.recycle();
        this.mVelocityTracker = null;
      }
      bool = false;
    }
    for (;;)
    {
      return bool;
      if (i == 0) {
        break;
      }
      if (this.mIsBeingDragged)
      {
        bool = true;
      }
      else
      {
        if (!this.mIsUnableToDrag) {
          break;
        }
        bool = false;
      }
    }
    switch (i)
    {
    }
    for (;;)
    {
      if (!this.mIsBeingDragged)
      {
        if (this.mVelocityTracker == null) {
          this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(paramMotionEvent);
      }
      bool = this.mIsBeingDragged;
      break;
      i = this.mActivePointerId;
      if (i != -1)
      {
        i = MotionEventCompat.findPointerIndex(paramMotionEvent, i);
        float f1 = MotionEventCompat.getX(paramMotionEvent, i);
        float f5 = f1 - this.mLastMotionX;
        float f3 = Math.abs(f5);
        float f2 = MotionEventCompat.getY(paramMotionEvent, i);
        float f4 = Math.abs(f2 - this.mLastMotionY);
        if (canScroll(this, false, (int)f5, (int)f1, (int)f2))
        {
          this.mLastMotionX = f1;
          this.mInitialMotionX = f1;
          this.mLastMotionY = f2;
          bool = false;
          break;
        }
        if ((f3 > this.mTouchSlop) && (f3 > f4))
        {
          this.mIsBeingDragged = true;
          setScrollState(1);
          this.mLastMotionX = f1;
          setScrollingCacheEnabled(true);
        }
        else if (f4 > this.mTouchSlop)
        {
          this.mIsUnableToDrag = true;
          continue;
          f1 = paramMotionEvent.getX();
          this.mInitialMotionX = f1;
          this.mLastMotionX = f1;
          this.mLastMotionY = paramMotionEvent.getY();
          this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
          if (this.mScrollState == 2)
          {
            this.mIsBeingDragged = true;
            this.mIsUnableToDrag = false;
            setScrollState(1);
          }
          else
          {
            completeScroll();
            this.mIsBeingDragged = false;
            this.mIsUnableToDrag = false;
            continue;
            onSecondaryPointerUp(paramMotionEvent);
          }
        }
      }
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.bDragLeft) {}
    int i4;
    int i6;
    int i;
    int i5;
    int m;
    int k;
    for (;;)
    {
      return;
      this.mInLayout = true;
      populate();
      this.mInLayout = false;
      int i3 = getChildCount();
      i4 = paramInt3 - paramInt1;
      i6 = paramInt4 - paramInt2;
      paramInt2 = getPaddingLeft();
      paramInt1 = getPaddingTop();
      i = getPaddingRight();
      paramInt4 = getPaddingBottom();
      i5 = getScrollX();
      m = 0;
      k = 0;
      if (k < i3) {
        break;
      }
      this.mTopPageBounds = paramInt1;
      this.mBottomPageBounds = (i6 - paramInt4);
      this.mDecorChildCount = m;
      this.mFirstLayout = false;
    }
    View localView = getChildAt(k);
    int i2 = m;
    int i1 = paramInt4;
    int j = paramInt2;
    int n = i;
    paramInt3 = paramInt1;
    Object localObject;
    if (localView.getVisibility() != 8)
    {
      localObject = (LayoutParams)localView.getLayoutParams();
      if (!((LayoutParams)localObject).isDecor) {
        break label455;
      }
      paramInt3 = ((LayoutParams)localObject).gravity;
      n = ((LayoutParams)localObject).gravity;
      switch (paramInt3 & 0x7)
      {
      case 2: 
      case 4: 
      default: 
        paramInt3 = paramInt2;
        j = paramInt2;
        label222:
        switch (n & 0x70)
        {
        default: 
          paramInt2 = paramInt1;
          label262:
          paramInt3 += i5;
          i2 = m + 1;
          localView.layout(paramInt3, paramInt2, localView.getMeasuredWidth() + paramInt3, localView.getMeasuredHeight() + paramInt2);
          paramInt3 = paramInt1;
          n = i;
          i1 = paramInt4;
        }
        break;
      }
    }
    for (;;)
    {
      k++;
      m = i2;
      paramInt4 = i1;
      paramInt2 = j;
      i = n;
      paramInt1 = paramInt3;
      break;
      paramInt3 = paramInt2;
      j = paramInt2 + localView.getMeasuredWidth();
      break label222;
      paramInt3 = Math.max((i4 - localView.getMeasuredWidth()) / 2, paramInt2);
      j = paramInt2;
      break label222;
      paramInt3 = i4 - i - localView.getMeasuredWidth();
      i += localView.getMeasuredWidth();
      j = paramInt2;
      break label222;
      paramInt2 = paramInt1;
      paramInt1 += localView.getMeasuredHeight();
      break label262;
      paramInt2 = Math.max((i6 - localView.getMeasuredHeight()) / 2, paramInt1);
      break label262;
      paramInt2 = i6 - paramInt4 - localView.getMeasuredHeight();
      paramInt4 += localView.getMeasuredHeight();
      break label262;
      label455:
      localObject = infoForChild(localView);
      i2 = m;
      i1 = paramInt4;
      j = paramInt2;
      n = i;
      paramInt3 = paramInt1;
      if (localObject != null)
      {
        paramInt3 = paramInt2 + (this.mPageMargin + i4) * ((ItemInfo)localObject).position;
        localView.layout(paramInt3, paramInt1, localView.getMeasuredWidth() + paramInt3, localView.getMeasuredHeight() + paramInt1);
        i2 = m;
        i1 = paramInt4;
        j = paramInt2;
        n = i;
        paramInt3 = paramInt1;
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(getDefaultSize(0, paramInt1), getDefaultSize(0, paramInt2));
    paramInt2 = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    int i = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
    int i2 = getChildCount();
    paramInt1 = 0;
    if (paramInt1 >= i2)
    {
      this.mChildWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824);
      this.mChildHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
      this.mInLayout = true;
      populate();
      this.mInLayout = false;
      paramInt2 = getChildCount();
    }
    for (paramInt1 = 0;; paramInt1++)
    {
      LayoutParams localLayoutParams;
      if (paramInt1 >= paramInt2)
      {
        return;
        localView = getChildAt(paramInt1);
        int m = i;
        int j = paramInt2;
        int k;
        int i1;
        int n;
        if (localView.getVisibility() != 8)
        {
          localLayoutParams = (LayoutParams)localView.getLayoutParams();
          m = i;
          j = paramInt2;
          if (localLayoutParams != null)
          {
            m = i;
            j = paramInt2;
            if (localLayoutParams.isDecor)
            {
              k = localLayoutParams.gravity & 0x7;
              j = localLayoutParams.gravity & 0x70;
              Log.d("ViewPager", "gravity: " + localLayoutParams.gravity + " hgrav: " + k + " vgrav: " + j);
              i1 = Integer.MIN_VALUE;
              n = Integer.MIN_VALUE;
              if ((j == 48) || (j == 80)) {
                break label335;
              }
              j = 0;
              label263:
              if ((k == 3) || (k == 5)) {
                break label341;
              }
              k = 0;
              label278:
              if (j == 0) {
                break label347;
              }
              m = 1073741824;
              label288:
              localView.measure(View.MeasureSpec.makeMeasureSpec(paramInt2, m), View.MeasureSpec.makeMeasureSpec(i, n));
              if (j == 0) {
                break label368;
              }
              m = i - localView.getMeasuredHeight();
              j = paramInt2;
            }
          }
        }
        for (;;)
        {
          paramInt1++;
          i = m;
          paramInt2 = j;
          break;
          label335:
          j = 1;
          break label263;
          label341:
          k = 1;
          break label278;
          label347:
          m = i1;
          if (k == 0) {
            break label288;
          }
          n = 1073741824;
          m = i1;
          break label288;
          label368:
          m = i;
          j = paramInt2;
          if (k != 0)
          {
            j = paramInt2 - localView.getMeasuredWidth();
            m = i;
          }
        }
      }
      View localView = getChildAt(paramInt1);
      if (localView.getVisibility() != 8)
      {
        localLayoutParams = (LayoutParams)localView.getLayoutParams();
        if ((localLayoutParams == null) || (!localLayoutParams.isDecor)) {
          localView.measure(this.mChildWidthMeasureSpec, this.mChildHeightMeasureSpec);
        }
      }
    }
  }
  
  protected void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    int i3;
    int i;
    int k;
    int i1;
    int i2;
    int m;
    if (this.mDecorChildCount > 0)
    {
      i3 = getScrollX();
      i = getPaddingLeft();
      k = getPaddingRight();
      i1 = getWidth();
      i2 = getChildCount();
      m = 0;
    }
    View localView;
    LayoutParams localLayoutParams;
    int j;
    int n;
    for (;;)
    {
      if (m >= i2)
      {
        if (this.mOnPageChangeListener != null) {
          this.mOnPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
        }
        if (this.mInternalPageChangeListener != null) {
          this.mInternalPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
        }
        this.mCalledSuper = true;
        return;
      }
      localView = getChildAt(m);
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      if (localLayoutParams.isDecor) {
        break;
      }
      j = k;
      n = i;
      m++;
      i = n;
      k = j;
    }
    switch (localLayoutParams.gravity & 0x7)
    {
    case 2: 
    case 4: 
    default: 
      j = i;
    }
    for (;;)
    {
      int i4 = j + i3 - localView.getLeft();
      n = i;
      j = k;
      if (i4 == 0) {
        break;
      }
      localView.offsetLeftAndRight(i4);
      n = i;
      j = k;
      break;
      j = i;
      i += localView.getWidth();
      continue;
      j = Math.max((i1 - localView.getMeasuredWidth()) / 2, i);
      continue;
      j = i1 - k - localView.getMeasuredWidth();
      k += localView.getMeasuredWidth();
    }
  }
  
  protected boolean onRequestFocusInDescendants(int paramInt, Rect paramRect)
  {
    int k = getChildCount();
    int i;
    int j;
    if ((paramInt & 0x2) != 0)
    {
      i = 0;
      j = 1;
    }
    for (;;)
    {
      if (i == k) {}
      for (boolean bool = false;; bool = true)
      {
        return bool;
        i = k - 1;
        j = -1;
        k = -1;
        break;
        View localView = getChildAt(i);
        if (localView.getVisibility() != 0) {
          break label100;
        }
        ItemInfo localItemInfo = infoForChild(localView);
        if ((localItemInfo == null) || (localItemInfo.position != this.mCurItem) || (!localView.requestFocus(paramInt, paramRect))) {
          break label100;
        }
      }
      label100:
      i += j;
    }
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
    }
    for (;;)
    {
      return;
      paramParcelable = (SavedState)paramParcelable;
      super.onRestoreInstanceState(paramParcelable.getSuperState());
      if (this.mAdapter != null)
      {
        this.mAdapter.restoreState(paramParcelable.adapterState, paramParcelable.loader);
        setCurrentItemInternal(paramParcelable.position, false, true);
      }
      else
      {
        this.mRestoredCurItem = paramParcelable.position;
        this.mRestoredAdapterState = paramParcelable.adapterState;
        this.mRestoredClassLoader = paramParcelable.loader;
      }
    }
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    localSavedState.position = this.mCurItem;
    if (this.mAdapter != null) {
      localSavedState.adapterState = this.mAdapter.saveState();
    }
    return localSavedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramInt1 != paramInt3) {
      recomputeScrollPosition(paramInt1, paramInt3, this.mPageMargin, this.mPageMargin);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mFakeDragging) {
      bool1 = true;
    }
    for (;;)
    {
      return bool1;
      if ((paramMotionEvent.getAction() == 0) && (paramMotionEvent.getEdgeFlags() != 0))
      {
        bool1 = false;
      }
      else
      {
        if ((this.mAdapter != null) && (this.mAdapter.getCount() != 0)) {
          break;
        }
        bool1 = false;
      }
    }
    if (this.mVelocityTracker == null) {
      this.mVelocityTracker = VelocityTracker.obtain();
    }
    this.mVelocityTracker.addMovement(paramMotionEvent);
    int i = paramMotionEvent.getAction();
    boolean bool4 = false;
    boolean bool2 = false;
    boolean bool5 = false;
    boolean bool3 = false;
    boolean bool1 = bool3;
    switch (i & 0xFF)
    {
    default: 
      bool1 = bool3;
    }
    for (;;)
    {
      if (bool1) {
        invalidate();
      }
      bool1 = true;
      break;
      completeScroll();
      float f1 = paramMotionEvent.getX();
      this.mInitialMotionX = f1;
      this.mLastMotionX = f1;
      this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, 0);
      bool1 = bool3;
      continue;
      float f2;
      float f3;
      if (!this.mIsBeingDragged)
      {
        i = MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId);
        f2 = MotionEventCompat.getX(paramMotionEvent, i);
        f1 = Math.abs(f2 - this.mLastMotionX);
        f3 = Math.abs(MotionEventCompat.getY(paramMotionEvent, i) - this.mLastMotionY);
        if ((f1 > this.mTouchSlop) && (f1 > f3))
        {
          this.mIsBeingDragged = true;
          this.mLastMotionX = f2;
          setScrollState(1);
          setScrollingCacheEnabled(true);
        }
      }
      bool1 = bool3;
      if (this.mIsBeingDragged)
      {
        float f4 = MotionEventCompat.getX(paramMotionEvent, MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId));
        f1 = this.mLastMotionX;
        this.mLastMotionX = f4;
        f2 = getScrollX() + (f1 - f4);
        int k = getWidth();
        i = k + this.mPageMargin;
        int j = this.mAdapter.getCount() - 1;
        f1 = Math.max(0, (this.mCurItem - 1) * i);
        f3 = Math.min(this.mCurItem + 1, j) * i;
        if (f2 < f1)
        {
          bool1 = bool4;
          if (f1 == 0.0F)
          {
            f2 = -f2;
            bool1 = this.mLeftEdge.onPull(f2 / k);
          }
        }
        for (;;)
        {
          this.mLastMotionX += f1 - (int)f1;
          scrollTo((int)f1, getScrollY());
          pageScrolled((int)f1);
          break;
          if (f2 > f3)
          {
            bool1 = bool5;
            if (f3 == j * i) {
              bool1 = this.mRightEdge.onPull((f2 - f3) / k);
            }
            f1 = f3;
          }
          else
          {
            bool1 = bool2;
            f1 = f2;
            if (this.bTwoPage)
            {
              paramMotionEvent = (View)((ItemInfo)this.mItems.get(0)).object;
              j = ((View)((ItemInfo)this.mItems.get(1)).object).getLeft();
              i = getWidth();
              if (this.mInitialMotionX > f4)
              {
                paramMotionEvent.offsetLeftAndRight(j + i - paramMotionEvent.getLeft());
                bool1 = bool2;
                f1 = f2;
              }
              else
              {
                bool1 = bool2;
                f1 = f2;
                if (this.mInitialMotionX < f4)
                {
                  paramMotionEvent.offsetLeftAndRight(j - i - paramMotionEvent.getLeft());
                  bool1 = bool2;
                  f1 = f2;
                }
              }
            }
          }
        }
        bool1 = bool3;
        if (this.mIsBeingDragged)
        {
          VelocityTracker localVelocityTracker = this.mVelocityTracker;
          localVelocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
          i = (int)VelocityTrackerCompat.getXVelocity(localVelocityTracker, this.mActivePointerId);
          this.mPopulatePending = true;
          j = getWidth() + this.mPageMargin;
          k = getScrollX();
          j = determineTargetPage(k / j, k % j / j, i, (int)(MotionEventCompat.getX(paramMotionEvent, MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId)) - this.mInitialMotionX));
          if ((this.bTwoPage) && (j != this.mCurItem)) {}
          for (bool1 = true;; bool1 = false)
          {
            this.bDragLeft = bool1;
            setCurrentItemInternal(j, true, true, i);
            this.mActivePointerId = -1;
            endDrag();
            bool1 = this.mLeftEdge.onRelease() | this.mRightEdge.onRelease();
            break;
          }
          bool1 = bool3;
          if (this.mIsBeingDragged)
          {
            setCurrentItemInternal(this.mCurItem, true, true);
            this.mActivePointerId = -1;
            endDrag();
            bool1 = this.mLeftEdge.onRelease() | this.mRightEdge.onRelease();
            continue;
            i = MotionEventCompat.getActionIndex(paramMotionEvent);
            this.mLastMotionX = MotionEventCompat.getX(paramMotionEvent, i);
            this.mActivePointerId = MotionEventCompat.getPointerId(paramMotionEvent, i);
            bool1 = bool3;
            continue;
            onSecondaryPointerUp(paramMotionEvent);
            this.mLastMotionX = MotionEventCompat.getX(paramMotionEvent, MotionEventCompat.findPointerIndex(paramMotionEvent, this.mActivePointerId));
            bool1 = bool3;
          }
        }
      }
    }
  }
  
  boolean pageLeft()
  {
    boolean bool = true;
    if (this.mCurItem > 0) {
      setCurrentItem(this.mCurItem - 1, true);
    }
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  boolean pageRight()
  {
    boolean bool = true;
    if ((this.mAdapter != null) && (this.mCurItem < this.mAdapter.getCount() - 1)) {
      setCurrentItem(this.mCurItem + 1, true);
    }
    for (;;)
    {
      return bool;
      bool = false;
    }
  }
  
  void populate()
  {
    if (this.mAdapter == null) {
      break label7;
    }
    label7:
    label116:
    label131:
    label141:
    label152:
    label171:
    label272:
    label438:
    label443:
    label449:
    label461:
    label503:
    label508:
    label511:
    for (;;)
    {
      return;
      if ((!this.mPopulatePending) && (getWindowToken() != null))
      {
        this.mAdapter.startUpdate(this);
        int i = this.mOffscreenPageLimit;
        int n = Math.max(0, this.mCurItem - i);
        int i1 = Math.min(this.mAdapter.getCount() - 1, this.mCurItem + i);
        int k = -1;
        i = 0;
        Object localObject2;
        if (i >= this.mItems.size())
        {
          if (this.mItems.size() <= 0) {
            break label438;
          }
          i = ((ItemInfo)this.mItems.get(this.mItems.size() - 1)).position;
          if (i < i1)
          {
            i++;
            if (i <= n) {
              break label443;
            }
            if (i <= i1) {
              break label449;
            }
          }
          localObject1 = null;
          i = 0;
          if (i < this.mItems.size()) {
            break label461;
          }
          localObject2 = this.mAdapter;
          i = this.mCurItem;
          if (localObject1 == null) {
            break label503;
          }
          localObject1 = ((ItemInfo)localObject1).object;
          ((PagerAdapter)localObject2).setPrimaryItem(this, i, localObject1);
          this.mAdapter.finishUpdate(this);
          if (!hasFocus()) {
            break;
          }
          localObject1 = findFocus();
          if (localObject1 == null) {
            break label508;
          }
        }
        for (Object localObject1 = infoForAnyChild((View)localObject1);; localObject1 = null)
        {
          if ((localObject1 != null) && (((ItemInfo)localObject1).position == this.mCurItem)) {
            break label511;
          }
          for (i = 0;; i++)
          {
            if (i >= getChildCount()) {
              break label272;
            }
            localObject2 = getChildAt(i);
            localObject1 = infoForChild((View)localObject2);
            if ((localObject1 != null) && (((ItemInfo)localObject1).position == this.mCurItem) && (((View)localObject2).requestFocus(2))) {
              break;
            }
          }
          break label7;
          localObject1 = (ItemInfo)this.mItems.get(i);
          if ((((ItemInfo)localObject1).position < n) || (((ItemInfo)localObject1).position > i1))
          {
            this.mItems.remove(i);
            j = i - 1;
            this.mAdapter.destroyItem(this, ((ItemInfo)localObject1).position, ((ItemInfo)localObject1).object);
          }
          do
          {
            do
            {
              k = ((ItemInfo)localObject1).position;
              i = j + 1;
              break;
              j = i;
            } while (k >= i1);
            j = i;
          } while (((ItemInfo)localObject1).position <= n);
          int j = k + 1;
          k = i;
          int m = j;
          if (j < n) {
            m = n;
          }
          for (k = i;; k++)
          {
            j = k;
            if (m > i1) {
              break;
            }
            j = k;
            if (m >= ((ItemInfo)localObject1).position) {
              break;
            }
            addNewItem(m, k);
            m++;
          }
          i = -1;
          break label116;
          i = n;
          break label131;
          addNewItem(i, -1);
          i++;
          break label131;
          if (((ItemInfo)this.mItems.get(i)).position == this.mCurItem)
          {
            localObject1 = (ItemInfo)this.mItems.get(i);
            break label152;
          }
          i++;
          break label141;
          localObject1 = null;
          break label171;
        }
      }
    }
  }
  
  public void setAdapter(PagerAdapter paramPagerAdapter)
  {
    int i;
    Object localObject;
    if (this.mAdapter != null)
    {
      this.mAdapter.unregisterDataSetObserver(this.mObserver);
      this.mAdapter.startUpdate(this);
      i = 0;
      if (i >= this.mItems.size())
      {
        this.mAdapter.finishUpdate(this);
        this.mItems.clear();
        removeNonDecorViews();
        this.mCurItem = 0;
        scrollTo(0, 0);
      }
    }
    else
    {
      localObject = this.mAdapter;
      this.mAdapter = paramPagerAdapter;
      if (this.mAdapter != null)
      {
        if (this.mObserver == null) {
          this.mObserver = new PagerObserver(null);
        }
        this.mAdapter.registerDataSetObserver(this.mObserver);
        this.mPopulatePending = false;
        if (this.mRestoredCurItem < 0) {
          break label227;
        }
        this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
        setCurrentItemInternal(this.mRestoredCurItem, false, true);
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
      }
    }
    for (;;)
    {
      if ((this.mAdapterChangeListener != null) && (localObject != paramPagerAdapter)) {
        this.mAdapterChangeListener.onAdapterChanged((PagerAdapter)localObject, paramPagerAdapter);
      }
      return;
      localObject = (ItemInfo)this.mItems.get(i);
      this.mAdapter.destroyItem(this, ((ItemInfo)localObject).position, ((ItemInfo)localObject).object);
      i++;
      break;
      label227:
      populate();
    }
  }
  
  public void setCurrentItem(int paramInt)
  {
    this.mPopulatePending = false;
    if (this.mFirstLayout) {}
    for (boolean bool = false;; bool = true)
    {
      setCurrentItemInternal(paramInt, bool, false);
      return;
    }
  }
  
  public void setCurrentItem(int paramInt, boolean paramBoolean)
  {
    this.mPopulatePending = false;
    setCurrentItemInternal(paramInt, paramBoolean, false);
  }
  
  void setCurrentItemInternal(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    setCurrentItemInternal(paramInt, paramBoolean1, paramBoolean2, 0);
  }
  
  void setCurrentItemInternal(int paramInt1, boolean paramBoolean1, boolean paramBoolean2, int paramInt2)
  {
    if ((this.mAdapter == null) || (this.mAdapter.getCount() <= 0)) {
      setScrollingCacheEnabled(false);
    }
    for (;;)
    {
      return;
      if ((!paramBoolean2) && (this.mCurItem == paramInt1) && (this.mItems.size() != 0))
      {
        setScrollingCacheEnabled(false);
      }
      else
      {
        int i;
        if (paramInt1 < 0)
        {
          i = 0;
          label60:
          paramInt1 = this.mOffscreenPageLimit;
          if ((i <= this.mCurItem + paramInt1) && (this.mCurItem == i)) {
            break label199;
          }
        }
        int j;
        label199:
        for (paramInt1 = 1;; paramInt1 = 0)
        {
          this.mCurItem = i;
          populate();
          j = (getWidth() + this.mPageMargin) * i;
          if (!paramBoolean1) {
            break label204;
          }
          smoothScrollTo(j, 0, paramInt2);
          if ((paramInt1 != 0) && (this.mOnPageChangeListener != null)) {
            this.mOnPageChangeListener.onPageSelected(i);
          }
          if ((paramInt1 == 0) || (this.mInternalPageChangeListener == null)) {
            break;
          }
          this.mInternalPageChangeListener.onPageSelected(i);
          break;
          i = paramInt1;
          if (paramInt1 < this.mAdapter.getCount()) {
            break label60;
          }
          i = this.mAdapter.getCount() - 1;
          break label60;
        }
        label204:
        if ((paramInt1 != 0) && (this.mOnPageChangeListener != null)) {
          this.mOnPageChangeListener.onPageSelected(i);
        }
        if ((paramInt1 != 0) && (this.mInternalPageChangeListener != null)) {
          this.mInternalPageChangeListener.onPageSelected(i);
        }
        completeScroll();
        scrollTo(j, 0);
      }
    }
  }
  
  OnPageChangeListener setInternalPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    OnPageChangeListener localOnPageChangeListener = this.mInternalPageChangeListener;
    this.mInternalPageChangeListener = paramOnPageChangeListener;
    return localOnPageChangeListener;
  }
  
  public void setOffscreenPageLimit(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 1)
    {
      Log.w("ViewPager", "Requested offscreen page limit " + paramInt + " too small; defaulting to " + 1);
      i = 1;
    }
    if (i != this.mOffscreenPageLimit)
    {
      this.mOffscreenPageLimit = i;
      populate();
    }
  }
  
  void setOnAdapterChangeListener(OnAdapterChangeListener paramOnAdapterChangeListener)
  {
    this.mAdapterChangeListener = paramOnAdapterChangeListener;
  }
  
  public void setOnPageChangeListener(OnPageChangeListener paramOnPageChangeListener)
  {
    this.mOnPageChangeListener = paramOnPageChangeListener;
  }
  
  public void setPageMargin(int paramInt)
  {
    int i = this.mPageMargin;
    this.mPageMargin = paramInt;
    int j = getWidth();
    recomputeScrollPosition(j, j, paramInt, i);
    requestLayout();
  }
  
  public void setPageMarginDrawable(int paramInt)
  {
    setPageMarginDrawable(getContext().getResources().getDrawable(paramInt));
  }
  
  public void setPageMarginDrawable(Drawable paramDrawable)
  {
    this.mMarginDrawable = paramDrawable;
    if (paramDrawable != null) {
      refreshDrawableState();
    }
    if (paramDrawable == null) {}
    for (boolean bool = true;; bool = false)
    {
      setWillNotDraw(bool);
      invalidate();
      return;
    }
  }
  
  public void setTwoPage(boolean paramBoolean)
  {
    this.bTwoPage = paramBoolean;
  }
  
  void smoothScrollTo(int paramInt1, int paramInt2)
  {
    smoothScrollTo(paramInt1, paramInt2, 0);
  }
  
  void smoothScrollTo(int paramInt1, int paramInt2, int paramInt3)
  {
    if (getChildCount() == 0) {
      setScrollingCacheEnabled(false);
    }
    int i;
    int j;
    int k;
    for (;;)
    {
      return;
      i = getScrollX();
      j = getScrollY();
      k = paramInt1 - i;
      paramInt2 -= j;
      if ((k != 0) || (paramInt2 != 0)) {
        break;
      }
      completeScroll();
      setScrollState(0);
    }
    setScrollingCacheEnabled(true);
    this.mScrolling = true;
    setScrollState(2);
    paramInt1 = getWidth();
    int m = paramInt1 / 2;
    float f3 = Math.min(1.0F, 1.0F * Math.abs(k) / paramInt1);
    float f2 = m;
    float f1 = m;
    f3 = distanceInfluenceForSnapDuration(f3);
    paramInt3 = Math.abs(paramInt3);
    if (paramInt3 > 0) {}
    for (paramInt1 = Math.round(1000.0F * Math.abs((f2 + f1 * f3) / paramInt3)) * 4;; paramInt1 = (int)((1.0F + Math.abs(k) / (this.mPageMargin + paramInt1)) * 100.0F))
    {
      paramInt1 = Math.min(paramInt1, 600);
      this.mScroller.startScroll(i, j, k, paramInt2, paramInt1);
      invalidate();
      break;
    }
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable)
  {
    if ((!super.verifyDrawable(paramDrawable)) && (paramDrawable != this.mMarginDrawable)) {}
    for (boolean bool = false;; bool = true) {
      return bool;
    }
  }
  
  static abstract interface Decor {}
  
  static class ItemInfo
  {
    Object object;
    int position;
  }
  
  public static class LayoutParams
    extends ViewGroup.LayoutParams
  {
    public int gravity;
    public boolean isDecor;
    
    public LayoutParams()
    {
      super(-1);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
      paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, ViewPager.LAYOUT_ATTRS);
      this.gravity = paramContext.getInteger(0, 0);
      paramContext.recycle();
    }
  }
  
  static abstract interface OnAdapterChangeListener
  {
    public abstract void onAdapterChanged(PagerAdapter paramPagerAdapter1, PagerAdapter paramPagerAdapter2);
  }
  
  public static abstract interface OnPageChangeListener
  {
    public abstract void onPageScrollStateChanged(int paramInt);
    
    public abstract void onPageScrolled(int paramInt1, float paramFloat, int paramInt2);
    
    public abstract void onPageSelected(int paramInt);
  }
  
  private class PagerObserver
    extends DataSetObserver
  {
    private PagerObserver() {}
    
    public void onChanged()
    {
      ViewPager.this.dataSetChanged();
    }
    
    public void onInvalidated()
    {
      ViewPager.this.dataSetChanged();
    }
  }
  
  public static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = ParcelableCompat.newCreator(new ParcelableCompatCreatorCallbacks()
    {
      public ViewPager.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new ViewPager.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }
      
      public ViewPager.SavedState[] newArray(int paramAnonymousInt)
      {
        return new ViewPager.SavedState[paramAnonymousInt];
      }
    });
    Parcelable adapterState;
    ClassLoader loader;
    int position;
    
    SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super();
      ClassLoader localClassLoader = paramClassLoader;
      if (paramClassLoader == null) {
        localClassLoader = getClass().getClassLoader();
      }
      this.position = paramParcel.readInt();
      this.adapterState = paramParcel.readParcelable(localClassLoader);
      this.loader = localClassLoader;
    }
    
    public SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public String toString()
    {
      return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.position + "}";
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(this.position);
      paramParcel.writeParcelable(this.adapterState, paramInt);
    }
  }
  
  public static class SimpleOnPageChangeListener
    implements ViewPager.OnPageChangeListener
  {
    public void onPageScrollStateChanged(int paramInt) {}
    
    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2) {}
    
    public void onPageSelected(int paramInt) {}
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\android\support\v4\view\ViewPager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */