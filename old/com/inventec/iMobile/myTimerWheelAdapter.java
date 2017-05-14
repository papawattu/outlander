package com.inventec.iMobile;

import android.content.Context;
import kankan.wheel.widget.adapters.AbstractWheelTextAdapter;

public class myTimerWheelAdapter
  extends AbstractWheelTextAdapter
{
  public static final int DEFAULT_MAX_VALUE = 9;
  private static final int DEFAULT_MIN_VALUE = -1;
  private static final String NullValueString = "ー";
  private String format;
  private int maxValue;
  private int minValue = -1;
  
  public myTimerWheelAdapter(Context paramContext, int paramInt)
  {
    this(paramContext, paramInt, null);
  }
  
  public myTimerWheelAdapter(Context paramContext, int paramInt, String paramString)
  {
    super(paramContext);
    this.maxValue = paramInt;
    this.format = paramString;
  }
  
  public CharSequence getItemText(int paramInt)
  {
    String str;
    if ((paramInt >= 0) && (paramInt < getItemsCount()))
    {
      paramInt = this.minValue + paramInt;
      if (paramInt < 0) {
        str = "ー";
      }
    }
    for (;;)
    {
      return str;
      if (this.format != null)
      {
        str = String.format(this.format, new Object[] { Integer.valueOf(paramInt) });
      }
      else
      {
        str = Integer.toString(paramInt);
        continue;
        str = null;
      }
    }
  }
  
  public int getItemsCount()
  {
    return this.maxValue - this.minValue + 1;
  }
  
  public void markUsed()
  {
    this.minValue = 0;
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\com\inventec\iMobile\myTimerWheelAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */