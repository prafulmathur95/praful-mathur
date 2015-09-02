package gnu.xml;

import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.FilterConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.mapping.Symbol;

public class NamedChildrenFilter
  extends FilterConsumer
{
  int level;
  String localName;
  int matchLevel;
  String namespaceURI;
  
  public NamedChildrenFilter(String paramString1, String paramString2, Consumer paramConsumer)
  {
    super(paramConsumer);
    this.namespaceURI = paramString1;
    this.localName = paramString2;
    this.skipping = true;
  }
  
  public static NamedChildrenFilter make(String paramString1, String paramString2, Consumer paramConsumer)
  {
    return new NamedChildrenFilter(paramString1, paramString2, paramConsumer);
  }
  
  public void endDocument()
  {
    this.level -= 1;
    super.endDocument();
  }
  
  public void endElement()
  {
    this.level -= 1;
    super.endElement();
    if ((!this.skipping) && (this.matchLevel == this.level)) {
      this.skipping = true;
    }
  }
  
  public void startDocument()
  {
    this.level += 1;
    super.startDocument();
  }
  
  public void startElement(Object paramObject)
  {
    String str;
    if ((this.skipping) && (this.level == 1))
    {
      if (!(paramObject instanceof Symbol)) {
        break label96;
      }
      localObject = (Symbol)paramObject;
      str = ((Symbol)localObject).getNamespaceURI();
    }
    for (Object localObject = ((Symbol)localObject).getLocalName();; localObject = paramObject.toString().intern())
    {
      if (((this.localName == localObject) || (this.localName == null)) && ((this.namespaceURI == str) || (this.namespaceURI == null)))
      {
        this.skipping = false;
        this.matchLevel = this.level;
      }
      super.startElement(paramObject);
      this.level += 1;
      return;
      label96:
      str = "";
    }
  }
  
  public void writeObject(Object paramObject)
  {
    if ((paramObject instanceof SeqPosition))
    {
      SeqPosition localSeqPosition = (SeqPosition)paramObject;
      if ((localSeqPosition.sequence instanceof TreeList))
      {
        ((TreeList)localSeqPosition.sequence).consumeNext(localSeqPosition.ipos, this);
        return;
      }
    }
    if ((paramObject instanceof Consumable))
    {
      ((Consumable)paramObject).consume(this);
      return;
    }
    super.writeObject(paramObject);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\xml\NamedChildrenFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */