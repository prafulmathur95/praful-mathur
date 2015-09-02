package gnu.kawa.lispexpr;

import gnu.mapping.Namespace;

class NamespaceUse
{
  Namespace imported;
  Namespace importing;
  NamespaceUse nextImported;
  NamespaceUse nextImporting;
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\lispexpr\NamespaceUse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */