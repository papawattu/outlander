package kankan.wheel.widget.adapters;

import android.content.Context;

public class ArrayWheelAdapter<T>
  extends AbstractWheelTextAdapter
{
  private T[] items;
  
  public ArrayWheelAdapter(Context paramContext, T[] paramArrayOfT)
  {
    super(paramContext);
    this.items = paramArrayOfT;
  }
  
  public CharSequence getItemText(int paramInt)
  {
    Object localObject;
    if ((paramInt >= 0) && (paramInt < this.items.length))
    {
      localObject = this.items[paramInt];
      if ((localObject instanceof CharSequence)) {
        localObject = (CharSequence)localObject;
      }
    }
    for (;;)
    {
      return (CharSequence)localObject;
      localObject = localObject.toString();
      continue;
      localObject = null;
    }
  }
  
  public int getItemsCount()
  {
    return this.items.length;
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\kankan\wheel\widget\adapters\ArrayWheelAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */