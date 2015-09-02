package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.WrappedException;
import kawa.lang.Syntax;

public class FieldLocation
  extends ClassMemberLocation
{
  static final int CONSTANT = 4;
  static final int INDIRECT_LOCATION = 2;
  public static final int KIND_FLAGS_SET = 64;
  public static final int PROCEDURE = 16;
  static final int SETUP_DONE = 1;
  public static final int SYNTAX = 32;
  static final int VALUE_SET = 8;
  Declaration decl;
  private int flags;
  Object value;
  
  public FieldLocation(Object paramObject, ClassType paramClassType, String paramString)
  {
    super(paramObject, paramClassType, paramString);
  }
  
  public FieldLocation(Object paramObject, String paramString1, String paramString2)
  {
    super(paramObject, ClassType.make(paramString1), paramString2);
  }
  
  public FieldLocation(Object paramObject, java.lang.reflect.Field paramField)
  {
    super(paramObject, paramField);
    this.type = ((ClassType)Type.make(paramField.getDeclaringClass()));
  }
  
  private Object getFieldValue()
  {
    super.setup();
    try
    {
      Object localObject = this.rfield.get(this.instance);
      return localObject;
    }
    catch (Throwable localThrowable)
    {
      throw WrappedException.wrapIfNeeded(localThrowable);
    }
  }
  
  public static FieldLocation make(Object paramObject, Declaration paramDeclaration)
  {
    gnu.bytecode.Field localField = paramDeclaration.field;
    paramObject = new FieldLocation(paramObject, localField.getDeclaringClass(), localField.getName());
    ((FieldLocation)paramObject).setDeclaration(paramDeclaration);
    return (FieldLocation)paramObject;
  }
  
  public static FieldLocation make(Object paramObject, String paramString1, String paramString2)
  {
    return new FieldLocation(paramObject, ClassType.make(paramString1), paramString2);
  }
  
  public Object get(Object paramObject)
  {
    Object localObject2;
    label99:
    Location localLocation;
    do
    {
      do
      {
        try
        {
          setup();
          if ((this.flags & 0x8) != 0)
          {
            localObject3 = this.value;
            Object localObject1 = localObject3;
            if ((this.flags & 0x4) == 0) {
              break label99;
            }
            return localObject3;
          }
        }
        catch (Throwable localThrowable)
        {
          return paramObject;
        }
        localObject3 = getFieldValue();
        localObject2 = localObject3;
        if ((this.type.getDeclaredField(this.mname).getModifiers() & 0x10) != 0)
        {
          this.flags |= 0x8;
          if ((this.flags & 0x2) == 0) {
            this.flags |= 0x4;
          }
          this.value = localObject3;
          localObject2 = localObject3;
        }
        localObject3 = localObject2;
      } while ((this.flags & 0x2) == 0);
      Object localObject3 = Location.UNBOUND;
      localLocation = (Location)localObject2;
      localObject2 = localLocation.get(localObject3);
      if (localObject2 == localObject3) {
        return paramObject;
      }
      localObject3 = localObject2;
    } while (!localLocation.isConstant());
    this.flags |= 0x4;
    this.value = localObject2;
    return localObject2;
  }
  
  public Declaration getDeclaration()
  {
    label133:
    label138:
    for (;;)
    {
      try
      {
        if ((this.flags & 0x40) == 0) {
          setKindFlags();
        }
        Object localObject3 = this.decl;
        Object localObject1 = localObject3;
        if (localObject3 != null) {
          break label138;
        }
        localObject3 = getMemberName();
        localObject1 = getDeclaringClass();
        gnu.bytecode.Field localField = ((ClassType)localObject1).getDeclaredField((String)localObject3);
        if (localField == null)
        {
          localObject1 = null;
          return (Declaration)localObject1;
        }
        localObject1 = ModuleInfo.find((ClassType)localObject1).getModuleExp().firstDecl();
        if ((localObject1 == null) || ((((Declaration)localObject1).field != null) && (((Declaration)localObject1).field.getName().equals(localObject3))))
        {
          if (localObject1 != null) {
            break label133;
          }
          throw new RuntimeException("no field found for " + this);
        }
      }
      finally {}
      Declaration localDeclaration = ((Declaration)localObject2).nextDecl();
      continue;
      this.decl = localDeclaration;
    }
  }
  
  public Type getFType()
  {
    return this.type.getDeclaredField(this.mname).getType();
  }
  
  public gnu.bytecode.Field getField()
  {
    return this.type.getDeclaredField(this.mname);
  }
  
  public boolean isBound()
  {
    if ((this.flags & 0x40) == 0) {
      setKindFlags();
    }
    if (((this.flags & 0x4) != 0) || ((this.flags & 0x2) == 0)) {
      return true;
    }
    Object localObject;
    if ((this.flags & 0x8) != 0) {
      localObject = this.value;
    }
    for (;;)
    {
      return ((Location)localObject).isBound();
      try
      {
        setup();
        localObject = getFieldValue();
        this.flags |= 0x8;
        this.value = localObject;
      }
      catch (Throwable localThrowable) {}
    }
    return false;
  }
  
  public boolean isConstant()
  {
    boolean bool = false;
    if ((this.flags & 0x40) == 0) {
      setKindFlags();
    }
    if ((this.flags & 0x4) != 0) {
      bool = true;
    }
    while (!isIndirectLocation()) {
      return bool;
    }
    Object localObject;
    if ((this.flags & 0x8) != 0) {
      localObject = this.value;
    }
    for (;;)
    {
      return ((Location)localObject).isConstant();
      try
      {
        setup();
        localObject = getFieldValue();
        this.flags |= 0x8;
        this.value = localObject;
      }
      catch (Throwable localThrowable) {}
    }
    return false;
  }
  
  public boolean isIndirectLocation()
  {
    return (this.flags & 0x2) != 0;
  }
  
  public boolean isProcedureOrSyntax()
  {
    if ((this.flags & 0x40) == 0) {
      setKindFlags();
    }
    return (this.flags & 0x30) != 0;
  }
  
  public void set(Object paramObject)
  {
    setup();
    if ((this.flags & 0x2) == 0) {
      try
      {
        this.rfield.set(this.instance, paramObject);
        return;
      }
      catch (Throwable paramObject)
      {
        throw WrappedException.wrapIfNeeded((Throwable)paramObject);
      }
    }
    Object localObject;
    if ((this.flags & 0x8) != 0) {
      localObject = this.value;
    }
    for (;;)
    {
      ((Location)localObject).set(paramObject);
      return;
      this.flags |= 0x8;
      localObject = getFieldValue();
      this.value = localObject;
    }
  }
  
  public void setDeclaration(Declaration paramDeclaration)
  {
    this.decl = paramDeclaration;
  }
  
  void setKindFlags()
  {
    Object localObject = getMemberName();
    localObject = getDeclaringClass().getDeclaredField((String)localObject);
    int i = ((gnu.bytecode.Field)localObject).getModifiers();
    localObject = ((gnu.bytecode.Field)localObject).getType();
    if (((Type)localObject).isSubtype(Compilation.typeLocation)) {
      this.flags |= 0x2;
    }
    if ((i & 0x10) != 0)
    {
      if ((this.flags & 0x2) != 0) {
        break label133;
      }
      this.flags |= 0x4;
      if (((Type)localObject).isSubtype(Compilation.typeProcedure)) {
        this.flags |= 0x10;
      }
      if (((localObject instanceof ClassType)) && (((ClassType)localObject).isSubclass("kawa.lang.Syntax"))) {
        this.flags |= 0x20;
      }
    }
    for (;;)
    {
      this.flags |= 0x40;
      return;
      label133:
      localObject = (Location)getFieldValue();
      if ((localObject instanceof FieldLocation))
      {
        localObject = (FieldLocation)localObject;
        if ((((FieldLocation)localObject).flags & 0x40) == 0) {
          ((FieldLocation)localObject).setKindFlags();
        }
        this.flags |= ((FieldLocation)localObject).flags & 0x34;
        if ((((FieldLocation)localObject).flags & 0x4) != 0)
        {
          if ((((FieldLocation)localObject).flags & 0x8) != 0)
          {
            this.value = ((FieldLocation)localObject).value;
            this.flags |= 0x8;
          }
        }
        else
        {
          this.value = localObject;
          this.flags |= 0x8;
        }
      }
      else if (((Location)localObject).isConstant())
      {
        localObject = ((Location)localObject).get(null);
        if ((localObject instanceof Procedure)) {
          this.flags |= 0x10;
        }
        if ((localObject instanceof Syntax)) {
          this.flags |= 0x20;
        }
        this.flags |= 0xC;
        this.value = localObject;
      }
    }
  }
  
  public void setProcedure()
  {
    this.flags |= 0x54;
  }
  
  public void setRestore(Object paramObject)
  {
    if ((this.flags & 0x2) == 0)
    {
      super.setRestore(paramObject);
      return;
    }
    ((Location)this.value).setRestore(paramObject);
  }
  
  public void setSyntax()
  {
    this.flags |= 0x64;
  }
  
  public Object setWithSave(Object paramObject)
  {
    if ((this.flags & 0x40) == 0) {
      setKindFlags();
    }
    if ((this.flags & 0x2) == 0) {
      return super.setWithSave(paramObject);
    }
    Object localObject;
    if ((this.flags & 0x8) != 0) {
      localObject = this.value;
    }
    for (;;)
    {
      return ((Location)localObject).setWithSave(paramObject);
      this.flags |= 0x8;
      localObject = getFieldValue();
      this.value = localObject;
    }
  }
  
  void setup()
  {
    try
    {
      if ((this.flags & 0x1) != 0) {
        return;
      }
      super.setup();
      if ((this.flags & 0x40) == 0) {
        setKindFlags();
      }
      this.flags |= 0x1;
      return;
    }
    finally {}
  }
  
  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("FieldLocation[");
    if (this.instance != null)
    {
      localStringBuffer.append(this.instance);
      localStringBuffer.append(' ');
    }
    if (this.type == null) {}
    for (String str = "(null)";; str = this.type.getName())
    {
      localStringBuffer.append(str);
      localStringBuffer.append('.');
      localStringBuffer.append(this.mname);
      localStringBuffer.append(']');
      return localStringBuffer.toString();
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\reflect\FieldLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */