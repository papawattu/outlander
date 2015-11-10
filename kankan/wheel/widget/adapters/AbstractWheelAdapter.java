package kankan.wheel.widget.adapters;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractWheelAdapter
  implements WheelViewAdapter
{
  private List<DataSetObserver> datasetObservers;
  
  public View getEmptyItem(View paramView, ViewGroup paramViewGroup)
  {
    return null;
  }
  
  protected void notifyDataChangedEvent()
  {
    Iterator localIterator;
    if (this.datasetObservers != null) {
      localIterator = this.datasetObservers.iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      ((DataSetObserver)localIterator.next()).onChanged();
    }
  }
  
  protected void notifyDataInvalidatedEvent()
  {
    Iterator localIterator;
    if (this.datasetObservers != null) {
      localIterator = this.datasetObservers.iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return;
      }
      ((DataSetObserver)localIterator.next()).onInvalidated();
    }
  }
  
  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    if (this.datasetObservers == null) {
      this.datasetObservers = new LinkedList();
    }
    this.datasetObservers.add(paramDataSetObserver);
  }
  
  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    if (this.datasetObservers != null) {
      this.datasetObservers.remove(paramDataSetObserver);
    }
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\kankan\wheel\widget\adapters\AbstractWheelAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */