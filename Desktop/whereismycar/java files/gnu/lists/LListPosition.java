package gnu.lists;

class LListPosition
  extends ExtPosition
{
  Object xpos;
  
  public LListPosition(LList paramLList, int paramInt, boolean paramBoolean)
  {
    set(paramLList, paramInt, paramBoolean);
  }
  
  public LListPosition(LListPosition paramLListPosition)
  {
    this.sequence = paramLListPosition.sequence;
    this.ipos = paramLListPosition.ipos;
    this.xpos = paramLListPosition.xpos;
  }
  
  public SeqPosition copy()
  {
    return new LListPosition(this);
  }
  
  public Object getNext()
  {
    Pair localPair = getNextPair();
    if (localPair == null) {
      return LList.eofValue;
    }
    return localPair.car;
  }
  
  public Pair getNextPair()
  {
    Object localObject;
    if ((this.ipos & 0x1) > 0) {
      if (this.xpos == null)
      {
        AbstractSequence localAbstractSequence = this.sequence;
        localObject = localAbstractSequence;
        if (this.ipos >> 1 != 0) {
          localObject = ((Pair)localAbstractSequence).cdr;
        }
      }
    }
    while (localObject == LList.Empty)
    {
      return null;
      localObject = ((Pair)((Pair)this.xpos).cdr).cdr;
      continue;
      if (this.xpos == null) {
        localObject = this.sequence;
      } else {
        localObject = ((Pair)this.xpos).cdr;
      }
    }
    return (Pair)localObject;
  }
  
  public Object getPrevious()
  {
    Pair localPair = getPreviousPair();
    if (localPair == null) {
      return LList.eofValue;
    }
    return localPair.car;
  }
  
  public Pair getPreviousPair()
  {
    int i = this.ipos;
    Object localObject2 = this.xpos;
    Object localObject1;
    if ((i & 0x1) > 0) {
      if (localObject2 == null) {
        localObject1 = this.sequence;
      }
    }
    while (localObject1 == LList.Empty)
    {
      return null;
      localObject1 = ((Pair)localObject2).cdr;
      continue;
      localObject1 = localObject2;
      if (localObject2 == null) {
        return null;
      }
    }
    return (Pair)localObject1;
  }
  
  public boolean gotoNext()
  {
    if ((this.ipos & 0x1) != 0) {}
    Object localObject1;
    for (int i = 1;; i = 0)
    {
      int j = this.ipos;
      Object localObject2 = this.xpos;
      if (localObject2 == null) {
        break label79;
      }
      localObject1 = localObject2;
      if (i != 0) {
        localObject1 = ((Pair)localObject2).cdr;
      }
      if (((Pair)localObject1).cdr != LList.Empty) {
        break;
      }
      return false;
    }
    this.xpos = localObject1;
    this.ipos = ((this.ipos | 0x1) + 2);
    for (;;)
    {
      return true;
      label79:
      if (this.ipos >> 1 == 0)
      {
        if (this.sequence == LList.Empty) {
          break;
        }
        this.ipos = 3;
        continue;
      }
      localObject1 = this.sequence;
      if (((Pair)localObject1).cdr == LList.Empty) {
        break;
      }
      this.ipos = 5;
      this.xpos = localObject1;
    }
  }
  
  public boolean gotoPrevious()
  {
    if (this.ipos >>> 1 == 0) {
      return false;
    }
    if ((this.ipos & 0x1) != 0)
    {
      this.ipos -= 3;
      return true;
    }
    int i = nextIndex();
    set((LList)this.sequence, i - 1, false);
    return true;
  }
  
  public boolean hasNext()
  {
    boolean bool = true;
    if (this.xpos == null) {
      if (this.ipos >> 1 == 0) {
        if (this.sequence != LList.Empty) {
          bool = true;
        }
      }
    }
    Object localObject1;
    do
    {
      do
      {
        for (;;)
        {
          return bool;
          bool = false;
        }
      } while (((Pair)this.sequence).cdr != LList.Empty);
      return false;
      Object localObject2 = ((Pair)this.xpos).cdr;
      localObject1 = localObject2;
      if ((this.ipos & 0x1) > 0) {
        localObject1 = ((Pair)localObject2).cdr;
      }
    } while (localObject1 != LList.Empty);
    return false;
  }
  
  public boolean hasPrevious()
  {
    return this.ipos >>> 1 != 0;
  }
  
  public int nextIndex()
  {
    return this.ipos >> 1;
  }
  
  public void set(AbstractSequence paramAbstractSequence, int paramInt, boolean paramBoolean)
  {
    set((LList)paramAbstractSequence, paramInt, paramBoolean);
  }
  
  public void set(LList paramLList, int paramInt, boolean paramBoolean)
  {
    this.sequence = paramLList;
    int i;
    if (paramBoolean)
    {
      i = 1;
      this.ipos = (i | paramInt << 1);
      if (!paramBoolean) {
        break label59;
      }
      paramInt -= 2;
    }
    for (;;)
    {
      if (paramInt >= 0)
      {
        for (;;)
        {
          paramInt -= 1;
          if (paramInt < 0) {
            break;
          }
          paramLList = ((Pair)paramLList).cdr;
        }
        i = 0;
        break;
        label59:
        paramInt -= 1;
        continue;
        this.xpos = paramLList;
        return;
      }
    }
    this.xpos = null;
  }
  
  public void setNext(Object paramObject)
  {
    getNextPair().car = paramObject;
  }
  
  public void setPrevious(Object paramObject)
  {
    getPreviousPair().car = paramObject;
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("LListPos[");
    localStringBuffer.append("index:");
    localStringBuffer.append(this.ipos);
    if (isAfter()) {
      localStringBuffer.append(" after");
    }
    if (this.position >= 0)
    {
      localStringBuffer.append(" position:");
      localStringBuffer.append(this.position);
    }
    localStringBuffer.append(']');
    return localStringBuffer.toString();
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\LListPosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */