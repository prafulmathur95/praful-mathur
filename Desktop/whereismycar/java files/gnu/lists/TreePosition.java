package gnu.lists;

import java.io.PrintStream;

public class TreePosition
  extends SeqPosition
  implements Cloneable
{
  int depth;
  int[] istack;
  AbstractSequence[] sstack;
  int start;
  private Object xpos;
  
  public TreePosition()
  {
    this.depth = -1;
  }
  
  public TreePosition(AbstractSequence paramAbstractSequence, int paramInt)
  {
    super(paramAbstractSequence, paramInt, false);
  }
  
  public TreePosition(TreePosition paramTreePosition)
  {
    set(paramTreePosition);
  }
  
  public TreePosition(Object paramObject)
  {
    this.xpos = paramObject;
    this.depth = -1;
  }
  
  public Object clone()
  {
    return new TreePosition(this);
  }
  
  public void dump()
  {
    System.err.println("TreePosition dump depth:" + this.depth + " start:" + this.start);
    int i = 0;
    if (i <= this.depth)
    {
      Object localObject;
      label59:
      StringBuilder localStringBuilder;
      if (i == 0)
      {
        localObject = this.sequence;
        System.err.print("#" + i + " seq:" + localObject);
        localObject = System.err;
        localStringBuilder = new StringBuilder().append(" ipos:");
        if (i != 0) {
          break label155;
        }
      }
      label155:
      for (int j = this.ipos;; j = this.istack[(this.depth - i)])
      {
        ((PrintStream)localObject).println(j);
        i += 1;
        break;
        localObject = this.sstack[(this.depth - i)];
        break label59;
      }
    }
  }
  
  public Object getAncestor(int paramInt)
  {
    if (paramInt == 0) {
      return this.sequence.getPosNext(this.ipos);
    }
    paramInt = this.depth - paramInt;
    if (paramInt <= 0) {
      return getRoot();
    }
    paramInt += this.start;
    return this.sstack[paramInt].getPosNext(this.istack[paramInt]);
  }
  
  public int getDepth()
  {
    return this.depth + 1;
  }
  
  public Object getPosNext()
  {
    if (this.sequence == null) {
      return this.xpos;
    }
    return this.sequence.getPosNext(this.ipos);
  }
  
  public AbstractSequence getRoot()
  {
    if (this.depth == 0) {
      return this.sequence;
    }
    return this.sstack[this.start];
  }
  
  public boolean gotoAttributesStart()
  {
    if (this.sequence == null)
    {
      if ((this.xpos instanceof AbstractSequence)) {}
      return false;
    }
    return this.sequence.gotoAttributesStart(this);
  }
  
  public boolean gotoChildrenStart()
  {
    if (this.sequence == null)
    {
      if (!(this.xpos instanceof AbstractSequence)) {
        return false;
      }
      this.depth = 0;
      this.sequence = ((AbstractSequence)this.xpos);
      setPos(this.sequence.startPos());
    }
    while (this.sequence.gotoChildrenStart(this)) {
      return true;
    }
    return false;
  }
  
  public final boolean gotoParent()
  {
    if (this.sequence == null) {
      return false;
    }
    return this.sequence.gotoParent(this);
  }
  
  public void pop()
  {
    this.sequence.releasePos(this.ipos);
    popNoRelease();
  }
  
  public void popNoRelease()
  {
    int i = this.depth - 1;
    this.depth = i;
    if (i < 0)
    {
      this.xpos = this.sequence;
      this.sequence = null;
      return;
    }
    this.sequence = this.sstack[(this.start + this.depth)];
    this.ipos = this.istack[(this.start + this.depth)];
  }
  
  public void push(AbstractSequence paramAbstractSequence, int paramInt)
  {
    int i = this.depth + this.start;
    if (i >= 0)
    {
      if (i != 0) {
        break label81;
      }
      this.istack = new int[8];
      this.sstack = new AbstractSequence[8];
    }
    for (;;)
    {
      this.sstack[i] = this.sequence;
      this.istack[i] = this.ipos;
      this.depth += 1;
      this.sequence = paramAbstractSequence;
      this.ipos = paramInt;
      return;
      label81:
      if (i >= this.istack.length)
      {
        int j = i * 2;
        int[] arrayOfInt = new int[j];
        Object localObject = new Object[j];
        localObject = new AbstractSequence[j];
        System.arraycopy(this.istack, 0, arrayOfInt, 0, this.depth);
        System.arraycopy(this.sstack, 0, localObject, 0, this.depth);
        this.istack = arrayOfInt;
        this.sstack = ((AbstractSequence[])localObject);
      }
    }
  }
  
  public void release()
  {
    while (this.sequence != null)
    {
      this.sequence.releasePos(this.ipos);
      pop();
    }
    this.xpos = null;
  }
  
  public void set(TreePosition paramTreePosition)
  {
    release();
    int i = paramTreePosition.depth;
    this.depth = i;
    if (i < 0)
    {
      this.xpos = paramTreePosition.xpos;
      return;
    }
    if ((this.sstack == null) || (this.sstack.length <= i)) {
      this.sstack = new AbstractSequence[i + 10];
    }
    if ((this.istack == null) || (this.istack.length <= i)) {
      this.istack = new int[i + 10];
    }
    i = 0;
    while (i < this.depth)
    {
      int j = i + paramTreePosition.start;
      localAbstractSequence = paramTreePosition.sstack[j];
      this.sstack[(this.depth - 1)] = localAbstractSequence;
      this.istack[(this.depth - i)] = localAbstractSequence.copyPos(paramTreePosition.istack[j]);
      i += 1;
    }
    AbstractSequence localAbstractSequence = paramTreePosition.sequence;
    this.sequence = localAbstractSequence;
    this.ipos = localAbstractSequence.copyPos(paramTreePosition.ipos);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\lists\TreePosition.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */