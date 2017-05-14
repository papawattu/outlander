package kankan.wheel.widget;

public class ItemsRange
{
  private int count;
  private int first;
  
  public ItemsRange()
  {
    this(0, 0);
  }
  
  public ItemsRange(int paramInt1, int paramInt2)
  {
    this.first = paramInt1;
    this.count = paramInt2;
  }
  
  public boolean contains(int paramInt)
  {
    if ((paramInt >= getFirst()) && (paramInt <= getLast())) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public int getCount()
  {
    return this.count;
  }
  
  public int getFirst()
  {
    return this.first;
  }
  
  public int getLast()
  {
    return getFirst() + getCount() - 1;
  }
}


/* Location:              C:\temp\dex2jar\dex-tools\target\dex2jar-2.1-SNAPSHOT\com.inventec.iMobile-dex2jar.jar!\kankan\wheel\widget\ItemsRange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */