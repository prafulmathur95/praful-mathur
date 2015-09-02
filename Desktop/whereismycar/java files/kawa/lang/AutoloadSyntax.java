package kawa.lang;

import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import gnu.mapping.Environment;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrongArguments;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;

public class AutoloadSyntax
  extends Syntax
  implements Externalizable
{
  String className;
  Environment env;
  Syntax loaded;
  
  public AutoloadSyntax() {}
  
  public AutoloadSyntax(String paramString1, String paramString2)
  {
    super(paramString1);
    this.className = paramString2;
  }
  
  public AutoloadSyntax(String paramString1, String paramString2, Environment paramEnvironment)
  {
    super(paramString1);
    this.className = paramString2;
    this.env = paramEnvironment;
  }
  
  private void throw_error(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder().append(paramString).append(this.className).append(" while autoloading ");
    if (getName() == null) {}
    for (paramString = "";; paramString = getName().toString()) {
      throw new GenericError(paramString);
    }
  }
  
  void load()
  {
    String str = getName();
    try
    {
      Object localObject = Class.forName(this.className).newInstance();
      if ((localObject instanceof Syntax))
      {
        this.loaded = ((Syntax)localObject);
        if ((str != null) && (this.loaded.getName() == null)) {
          this.loaded.setName(str);
        }
      }
      else
      {
        throw_error("failed to autoload valid syntax object ");
        return;
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw_error("failed to find class ");
      return;
    }
    catch (InstantiationException localInstantiationException)
    {
      throw_error("failed to instantiate class ");
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw_error("illegal access in class ");
      return;
    }
    catch (UnboundLocationException localUnboundLocationException)
    {
      throw_error("missing symbol '" + localUnboundLocationException.getMessage() + "' ");
      return;
    }
    catch (WrongArguments localWrongArguments)
    {
      throw_error("type error");
    }
  }
  
  public void print(PrintWriter paramPrintWriter)
  {
    paramPrintWriter.print(toString());
  }
  
  public void readExternal(ObjectInput paramObjectInput)
    throws IOException, ClassNotFoundException
  {
    setName((String)paramObjectInput.readObject());
    this.className = ((String)paramObjectInput.readObject());
  }
  
  /* Error */
  public gnu.expr.Expression rewriteForm(Pair paramPair, Translator paramTranslator)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 76	kawa/lang/AutoloadSyntax:loaded	Lkawa/lang/Syntax;
    //   4: ifnonnull +7 -> 11
    //   7: aload_0
    //   8: invokevirtual 123	kawa/lang/AutoloadSyntax:load	()V
    //   11: aload_2
    //   12: getfield 128	kawa/lang/Translator:currentSyntax	Lkawa/lang/Syntax;
    //   15: astore_3
    //   16: aload_2
    //   17: aload_0
    //   18: getfield 76	kawa/lang/AutoloadSyntax:loaded	Lkawa/lang/Syntax;
    //   21: putfield 128	kawa/lang/Translator:currentSyntax	Lkawa/lang/Syntax;
    //   24: aload_0
    //   25: getfield 76	kawa/lang/AutoloadSyntax:loaded	Lkawa/lang/Syntax;
    //   28: aload_1
    //   29: aload_2
    //   30: invokevirtual 130	kawa/lang/Syntax:rewriteForm	(Lgnu/lists/Pair;Lkawa/lang/Translator;)Lgnu/expr/Expression;
    //   33: astore_1
    //   34: aload_2
    //   35: aload_3
    //   36: putfield 128	kawa/lang/Translator:currentSyntax	Lkawa/lang/Syntax;
    //   39: aload_1
    //   40: areturn
    //   41: astore_1
    //   42: aload_2
    //   43: aload_1
    //   44: invokevirtual 131	kawa/lang/GenericError:getMessage	()Ljava/lang/String;
    //   47: invokevirtual 135	kawa/lang/Translator:syntaxError	(Ljava/lang/String;)Lgnu/expr/Expression;
    //   50: areturn
    //   51: astore_1
    //   52: aload_2
    //   53: aload_1
    //   54: invokevirtual 136	gnu/mapping/WrongType:getMessage	()Ljava/lang/String;
    //   57: invokevirtual 135	kawa/lang/Translator:syntaxError	(Ljava/lang/String;)Lgnu/expr/Expression;
    //   60: areturn
    //   61: astore_1
    //   62: aload_2
    //   63: aload_3
    //   64: putfield 128	kawa/lang/Translator:currentSyntax	Lkawa/lang/Syntax;
    //   67: aload_1
    //   68: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	69	0	this	AutoloadSyntax
    //   0	69	1	paramPair	Pair
    //   0	69	2	paramTranslator	Translator
    //   15	49	3	localSyntax	Syntax
    // Exception table:
    //   from	to	target	type
    //   7	11	41	kawa/lang/GenericError
    //   7	11	51	gnu/mapping/WrongType
    //   24	34	61	finally
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator)
  {
    if (this.loaded == null) {}
    try
    {
      load();
      this.loaded.scanForm(paramPair, paramScopeExp, paramTranslator);
      return;
    }
    catch (RuntimeException paramPair)
    {
      paramTranslator.syntaxError(paramPair.getMessage());
    }
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(100);
    localStringBuffer.append("#<syntax ");
    if (getName() != null)
    {
      localStringBuffer.append(getName());
      localStringBuffer.append(' ');
    }
    if (this.loaded != null) {
      localStringBuffer.append("autoloaded>");
    }
    for (;;)
    {
      return localStringBuffer.toString();
      localStringBuffer.append("autoload ");
      localStringBuffer.append(this.className);
      localStringBuffer.append(">");
    }
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput)
    throws IOException
  {
    paramObjectOutput.writeObject(getName());
    paramObjectOutput.writeObject(this.className);
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\kawa\lang\AutoloadSyntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */