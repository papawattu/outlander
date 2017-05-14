package kankan.wheel.widget.adapters;

import android.content.Context;

public class NumericWheelAdapter
  extends AbstractWheelTextAdapter
{
  public static final int DEFAULT_MAX_VALUE = 9;
  private static final int DEFAULT_MIN_VALUE = 0;
  private String format;
  private int maxValue;
  private int minValue;
  
  public NumericWheelAdapter(Context paramContext)
  {
    this(paramContext, 0, 9);
  }
  
  public NumericWheelAdapter(Context paramContext, int paramInt1, int paramInt2)
  {
    this(paramContext, paramInt1, paramInt2, null);
  }
  
  public NumericWheelAdapter(Context paramContext, int paramInt1, int paramInt2, String paramString)
  {
    super(paramContext);
    this.minValue = paramInt1;
    this.maxValue = paramInt2;
    this.format = paramString;
  }
  
  public CharSequence getItemText(int paramInt)
  {
    String str;
    if ((paramInt >= 0) && (paramInt < getItemsCount()))
    {
      paramInt = this.minValue + paramInt;
      if (this.format != null) {
        str = String.format(this.format, new Object[] { Integer.valueOf(paramInt) });
      }
    }
    for (;;)
    {
      return str;
      str = Integer.toString(paramInt);
      continue;
      str = null;
    }
  }
  
  public int getItemsCount()
  {
    return this.maxValue - this.minValue + 1;
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\kankan\wheel\widget\adapters\NumericWheelAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */