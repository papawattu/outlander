package android.support.v4.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.widget.TextView;

public class PagerTitleStrip
  extends ViewGroup
  implements ViewPager.Decor
{
  private static final int[] ATTRS = { 16842804, 16842904, 16842901 };
  private static final int SIDE_ALPHA = 153;
  private static final int TEXT_SPACING = 16;
  private TextView mCurrText;
  private int mLastKnownCurrentPage = -1;
  private float mLastKnownPositionOffset = -1.0F;
  private TextView mNextText;
  private final PageListener mPageListener = new PageListener(null);
  ViewPager mPager;
  private TextView mPrevText;
  private int mScaledTextSpacing;
  private boolean mUpdatingPositions;
  private boolean mUpdatingText;
  
  public PagerTitleStrip(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public PagerTitleStrip(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TextView localTextView = new TextView(paramContext);
    this.mPrevText = localTextView;
    addView(localTextView);
    localTextView = new TextView(paramContext);
    this.mCurrText = localTextView;
    addView(localTextView);
    localTextView = new TextView(paramContext);
    this.mNextText = localTextView;
    addView(localTextView);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, ATTRS);
    int i = paramAttributeSet.getResourceId(0, 0);
    if (i != 0)
    {
      this.mPrevText.setTextAppearance(paramContext, i);
      this.mCurrText.setTextAppearance(paramContext, i);
      this.mNextText.setTextAppearance(paramContext, i);
    }
    if (paramAttributeSet.hasValue(1))
    {
      i = paramAttributeSet.getColor(1, 0);
      this.mPrevText.setTextColor(i);
      this.mCurrText.setTextColor(i);
      this.mNextText.setTextColor(i);
    }
    i = paramAttributeSet.getDimensionPixelSize(2, 0);
    if (i != 0)
    {
      this.mPrevText.setTextSize(0, i);
      this.mCurrText.setTextSize(0, i);
      this.mNextText.setTextSize(0, i);
    }
    paramAttributeSet.recycle();
    i = 0x99000000 | 0xFFFFFF & this.mPrevText.getTextColors().getDefaultColor();
    this.mPrevText.setTextColor(i);
    this.mNextText.setTextColor(i);
    this.mPrevText.setEllipsize(TextUtils.TruncateAt.END);
    this.mCurrText.setEllipsize(TextUtils.TruncateAt.END);
    this.mNextText.setEllipsize(TextUtils.TruncateAt.END);
    this.mPrevText.setSingleLine();
    this.mCurrText.setSingleLine();
    this.mNextText.setSingleLine();
    this.mScaledTextSpacing = ((int)(16.0F * paramContext.getResources().getDisplayMetrics().density));
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Object localObject = getParent();
    if (!(localObject instanceof ViewPager)) {
      throw new IllegalStateException("PagerTitleStrip must be a direct child of a ViewPager.");
    }
    localObject = (ViewPager)localObject;
    PagerAdapter localPagerAdapter = ((ViewPager)localObject).getAdapter();
    ((ViewPager)localObject).setInternalPageChangeListener(this.mPageListener);
    ((ViewPager)localObject).setOnAdapterChangeListener(this.mPageListener);
    this.mPager = ((ViewPager)localObject);
    updateAdapter(null, localPagerAdapter);
  }
  
  protected void onDetachedFromWindow()
  {
    updateAdapter(this.mPager.getAdapter(), null);
    this.mPager.setInternalPageChangeListener(null);
    this.mPager.setOnAdapterChangeListener(null);
    this.mPager = null;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.mPager != null) {
      updateTextPositions(this.mPager.getCurrentItem(), 0.0F);
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int k = View.MeasureSpec.getMode(paramInt1);
    int i = View.MeasureSpec.getMode(paramInt2);
    int j = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    if (k != 1073741824) {
      throw new IllegalStateException("Must measure with an exact width");
    }
    paramInt1 = 0;
    Drawable localDrawable = getBackground();
    if (localDrawable != null) {
      paramInt1 = localDrawable.getIntrinsicHeight();
    }
    int m = getPaddingTop() + getPaddingBottom();
    int n = View.MeasureSpec.makeMeasureSpec((int)(j * 0.8F), Integer.MIN_VALUE);
    k = View.MeasureSpec.makeMeasureSpec(paramInt2 - m, i);
    this.mPrevText.measure(n, k);
    this.mCurrText.measure(n, k);
    this.mNextText.measure(n, k);
    if (i == 1073741824) {
      setMeasuredDimension(j, paramInt2);
    }
    for (;;)
    {
      return;
      setMeasuredDimension(j, Math.max(paramInt1, this.mCurrText.getMeasuredHeight() + m));
    }
  }
  
  public void requestLayout()
  {
    if (!this.mUpdatingText) {
      super.requestLayout();
    }
  }
  
  void updateAdapter(PagerAdapter paramPagerAdapter1, PagerAdapter paramPagerAdapter2)
  {
    if (paramPagerAdapter1 != null) {
      paramPagerAdapter1.unregisterDataSetObserver(this.mPageListener);
    }
    if (paramPagerAdapter2 != null) {
      paramPagerAdapter2.registerDataSetObserver(this.mPageListener);
    }
    if (this.mPager != null)
    {
      this.mLastKnownCurrentPage = -1;
      this.mLastKnownPositionOffset = -1.0F;
      updateText(this.mPager.getCurrentItem(), paramPagerAdapter2);
      requestLayout();
    }
  }
  
  void updateText(int paramInt, PagerAdapter paramPagerAdapter)
  {
    int i;
    TextView localTextView;
    if (paramPagerAdapter != null)
    {
      i = paramPagerAdapter.getCount();
      this.mUpdatingText = true;
      localTextView = null;
      localObject = localTextView;
      if (paramInt >= 1)
      {
        localObject = localTextView;
        if (paramPagerAdapter != null) {
          localObject = paramPagerAdapter.getPageTitle(paramInt - 1);
        }
      }
      this.mPrevText.setText((CharSequence)localObject);
      localTextView = this.mCurrText;
      if (paramPagerAdapter == null) {
        break label245;
      }
    }
    label245:
    for (Object localObject = paramPagerAdapter.getPageTitle(paramInt);; localObject = null)
    {
      localTextView.setText((CharSequence)localObject);
      localTextView = null;
      localObject = localTextView;
      if (paramInt + 1 < i)
      {
        localObject = localTextView;
        if (paramPagerAdapter != null) {
          localObject = paramPagerAdapter.getPageTitle(paramInt + 1);
        }
      }
      this.mNextText.setText((CharSequence)localObject);
      int i1 = getWidth();
      int m = getPaddingLeft();
      int n = getPaddingRight();
      int j = getHeight();
      i = getPaddingTop();
      int k = getPaddingBottom();
      m = View.MeasureSpec.makeMeasureSpec((int)((i1 - m - n) * 0.8F), Integer.MIN_VALUE);
      i = View.MeasureSpec.makeMeasureSpec(j - i - k, 1073741824);
      this.mPrevText.measure(m, i);
      this.mCurrText.measure(m, i);
      this.mNextText.measure(m, i);
      this.mLastKnownCurrentPage = paramInt;
      if (!this.mUpdatingPositions) {
        updateTextPositions(paramInt, this.mLastKnownPositionOffset);
      }
      this.mUpdatingText = false;
      return;
      i = 0;
      break;
    }
  }
  
  void updateTextPositions(int paramInt, float paramFloat)
  {
    if (paramInt != this.mLastKnownCurrentPage) {
      updateText(paramInt, this.mPager.getAdapter());
    }
    for (;;)
    {
      this.mUpdatingPositions = true;
      int k = this.mPrevText.getMeasuredWidth();
      int i1 = this.mCurrText.getMeasuredWidth();
      paramInt = this.mNextText.getMeasuredWidth();
      int i2 = i1 / 2;
      int j = getWidth();
      int n = getPaddingLeft();
      int m = getPaddingRight();
      int i = getPaddingTop();
      int i3 = m + i2;
      float f2 = paramFloat + 0.5F;
      float f1 = f2;
      if (f2 > 1.0F) {
        f1 = f2 - 1.0F;
      }
      i2 = j - i3 - (int)((j - (n + i2) - i3) * f1) - i1 / 2;
      i1 = i2 + i1;
      this.mCurrText.layout(i2, i, i1, this.mCurrText.getMeasuredHeight() + i);
      n = Math.min(n, i2 - this.mScaledTextSpacing - k);
      this.mPrevText.layout(n, i, n + k, this.mPrevText.getMeasuredHeight() + i);
      j = Math.max(j - m - paramInt, this.mScaledTextSpacing + i1);
      this.mNextText.layout(j, i, j + paramInt, this.mNextText.getMeasuredHeight() + i);
      this.mLastKnownPositionOffset = paramFloat;
      this.mUpdatingPositions = false;
      for (;;)
      {
        return;
        if (paramFloat != this.mLastKnownPositionOffset) {
          break;
        }
      }
    }
  }
  
  private class PageListener
    extends DataSetObserver
    implements ViewPager.OnPageChangeListener, ViewPager.OnAdapterChangeListener
  {
    private int mScrollState;
    
    private PageListener() {}
    
    public void onAdapterChanged(PagerAdapter paramPagerAdapter1, PagerAdapter paramPagerAdapter2)
    {
      PagerTitleStrip.this.updateAdapter(paramPagerAdapter1, paramPagerAdapter2);
    }
    
    public void onChanged()
    {
      PagerTitleStrip.this.updateText(PagerTitleStrip.this.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
    }
    
    public void onPageScrollStateChanged(int paramInt)
    {
      this.mScrollState = paramInt;
    }
    
    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      paramInt2 = paramInt1;
      if (paramFloat > 0.5F) {
        paramInt2 = paramInt1 + 1;
      }
      PagerTitleStrip.this.updateTextPositions(paramInt2, paramFloat);
    }
    
    public void onPageSelected(int paramInt)
    {
      if (this.mScrollState == 0) {
        PagerTitleStrip.this.updateText(PagerTitleStrip.this.mPager.getCurrentItem(), PagerTitleStrip.this.mPager.getAdapter());
      }
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\android\support\v4\view\PagerTitleStrip.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */