package kankan.wheel.widget;

import android.view.View;
import android.widget.LinearLayout;
import java.util.LinkedList;
import java.util.List;
import kankan.wheel.widget.adapters.WheelViewAdapter;

public class WheelRecycle
{
  private List<View> emptyItems;
  private List<View> items;
  private WheelView wheel;
  
  public WheelRecycle(WheelView paramWheelView)
  {
    this.wheel = paramWheelView;
  }
  
  private List<View> addView(View paramView, List<View> paramList)
  {
    Object localObject = paramList;
    if (paramList == null) {
      localObject = new LinkedList();
    }
    ((List)localObject).add(paramView);
    return (List<View>)localObject;
  }
  
  private View getCachedView(List<View> paramList)
  {
    View localView;
    if ((paramList != null) && (paramList.size() > 0))
    {
      localView = (View)paramList.get(0);
      paramList.remove(0);
    }
    for (paramList = localView;; paramList = null) {
      return paramList;
    }
  }
  
  private void recycleView(View paramView, int paramInt)
  {
    int j = this.wheel.getViewAdapter().getItemsCount();
    int i;
    if (paramInt >= 0)
    {
      i = paramInt;
      if (paramInt < j) {}
    }
    else
    {
      i = paramInt;
      if (!this.wheel.isCyclic()) {
        this.emptyItems = addView(paramView, this.emptyItems);
      }
    }
    for (;;)
    {
      return;
      do
      {
        i += j;
      } while (i < 0);
      this.items = addView(paramView, this.items);
    }
  }
  
  public void clearAll()
  {
    if (this.items != null) {
      this.items.clear();
    }
    if (this.emptyItems != null) {
      this.emptyItems.clear();
    }
  }
  
  public View getEmptyItem()
  {
    return getCachedView(this.emptyItems);
  }
  
  public View getItem()
  {
    return getCachedView(this.items);
  }
  
  public int recycleItems(LinearLayout paramLinearLayout, int paramInt, ItemsRange paramItemsRange)
  {
    int i = paramInt;
    int j = 0;
    if (j >= paramLinearLayout.getChildCount()) {
      return paramInt;
    }
    int k;
    int m;
    if (!paramItemsRange.contains(i))
    {
      recycleView(paramLinearLayout.getChildAt(j), i);
      paramLinearLayout.removeViewAt(j);
      k = j;
      m = paramInt;
      if (j == 0)
      {
        m = paramInt + 1;
        k = j;
      }
    }
    for (;;)
    {
      i++;
      j = k;
      paramInt = m;
      break;
      k = j + 1;
      m = paramInt;
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\kankan\wheel\widget\WheelRecycle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */