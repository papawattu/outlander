package kankan.wheel.widget.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public abstract class AbstractWheelTextAdapter
  extends AbstractWheelAdapter
{
  public static final int DEFAULT_TEXT_COLOR = -15724528;
  public static final int DEFAULT_TEXT_SIZE = 24;
  public static final int LABEL_COLOR = -9437072;
  protected static final int NO_RESOURCE = 0;
  public static final int TEXT_VIEW_ITEM_RESOURCE = -1;
  protected Context context;
  protected int emptyItemResourceId;
  protected LayoutInflater inflater;
  protected int itemResourceId;
  protected int itemTextResourceId;
  private int textColor = -15724528;
  private int textSize = 24;
  
  protected AbstractWheelTextAdapter(Context paramContext)
  {
    this(paramContext, -1);
  }
  
  protected AbstractWheelTextAdapter(Context paramContext, int paramInt)
  {
    this(paramContext, paramInt, 0);
  }
  
  protected AbstractWheelTextAdapter(Context paramContext, int paramInt1, int paramInt2)
  {
    this.context = paramContext;
    this.itemResourceId = paramInt1;
    this.itemTextResourceId = paramInt2;
    this.inflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }
  
  /* Error */
  private TextView getTextView(View paramView, int paramInt)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: iload_2
    //   3: ifne +17 -> 20
    //   6: aload_1
    //   7: instanceof 64
    //   10: ifeq +10 -> 20
    //   13: aload_1
    //   14: checkcast 64	android/widget/TextView
    //   17: astore_3
    //   18: aload_3
    //   19: areturn
    //   20: iload_2
    //   21: ifeq -3 -> 18
    //   24: aload_1
    //   25: iload_2
    //   26: invokevirtual 70	android/view/View:findViewById	(I)Landroid/view/View;
    //   29: checkcast 64	android/widget/TextView
    //   32: astore_3
    //   33: goto -15 -> 18
    //   36: astore_1
    //   37: ldc 72
    //   39: ldc 74
    //   41: invokestatic 80	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   44: pop
    //   45: new 82	java/lang/IllegalStateException
    //   48: dup
    //   49: ldc 84
    //   51: aload_1
    //   52: invokespecial 87	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   55: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	this	AbstractWheelTextAdapter
    //   0	56	1	paramView	View
    //   0	56	2	paramInt	int
    //   1	32	3	localTextView	TextView
    // Exception table:
    //   from	to	target	type
    //   6	18	36	java/lang/ClassCastException
    //   24	33	36	java/lang/ClassCastException
  }
  
  private View getView(int paramInt, ViewGroup paramViewGroup)
  {
    switch (paramInt)
    {
    default: 
      paramViewGroup = this.inflater.inflate(paramInt, paramViewGroup, false);
    }
    for (;;)
    {
      return paramViewGroup;
      paramViewGroup = null;
      continue;
      paramViewGroup = new TextView(this.context);
    }
  }
  
  protected void configureTextView(TextView paramTextView)
  {
    paramTextView.setTextColor(this.textColor);
    paramTextView.setGravity(17);
    paramTextView.setTextSize(this.textSize);
    paramTextView.setLines(1);
    paramTextView.setTypeface(Typeface.SANS_SERIF, 1);
  }
  
  public View getEmptyItem(View paramView, ViewGroup paramViewGroup)
  {
    View localView = paramView;
    if (paramView == null) {
      localView = getView(this.emptyItemResourceId, paramViewGroup);
    }
    if ((this.emptyItemResourceId == -1) && ((localView instanceof TextView))) {
      configureTextView((TextView)localView);
    }
    return localView;
  }
  
  public int getEmptyItemResource()
  {
    return this.emptyItemResourceId;
  }
  
  public View getItem(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView;
    if ((paramInt >= 0) && (paramInt < getItemsCount()))
    {
      localView = paramView;
      if (paramView == null) {
        localView = getView(this.itemResourceId, paramViewGroup);
      }
      TextView localTextView = getTextView(localView, this.itemTextResourceId);
      if (localTextView != null)
      {
        paramViewGroup = getItemText(paramInt);
        paramView = paramViewGroup;
        if (paramViewGroup == null) {
          paramView = "";
        }
        localTextView.setText(paramView);
        if (this.itemResourceId == -1) {
          configureTextView(localTextView);
        }
      }
    }
    for (paramView = localView;; paramView = null) {
      return paramView;
    }
  }
  
  public int getItemResource()
  {
    return this.itemResourceId;
  }
  
  protected abstract CharSequence getItemText(int paramInt);
  
  public int getItemTextResource()
  {
    return this.itemTextResourceId;
  }
  
  public int getTextColor()
  {
    return this.textColor;
  }
  
  public int getTextSize()
  {
    return this.textSize;
  }
  
  public void setEmptyItemResource(int paramInt)
  {
    this.emptyItemResourceId = paramInt;
  }
  
  public void setItemResource(int paramInt)
  {
    this.itemResourceId = paramInt;
  }
  
  public void setItemTextResource(int paramInt)
  {
    this.itemTextResourceId = paramInt;
  }
  
  public void setTextColor(int paramInt)
  {
    this.textColor = paramInt;
  }
  
  public void setTextSize(int paramInt)
  {
    this.textSize = paramInt;
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\kankan\wheel\widget\adapters\AbstractWheelTextAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */