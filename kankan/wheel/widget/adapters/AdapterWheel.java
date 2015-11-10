package kankan.wheel.widget.adapters;

import android.content.Context;
import kankan.wheel.widget.WheelAdapter;

public class AdapterWheel
  extends AbstractWheelTextAdapter
{
  private WheelAdapter adapter;
  
  public AdapterWheel(Context paramContext, WheelAdapter paramWheelAdapter)
  {
    super(paramContext);
    this.adapter = paramWheelAdapter;
  }
  
  public WheelAdapter getAdapter()
  {
    return this.adapter;
  }
  
  protected CharSequence getItemText(int paramInt)
  {
    return this.adapter.getItem(paramInt);
  }
  
  public int getItemsCount()
  {
    return this.adapter.getItemsCount();
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\kankan\wheel\widget\adapters\AdapterWheel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */