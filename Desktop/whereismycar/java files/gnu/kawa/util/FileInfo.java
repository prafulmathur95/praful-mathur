package gnu.kawa.util;

import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.text.Path;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FileInfo
{
  static final Pattern childPat;
  static Hashtable fileMap = new Hashtable();
  static final Pattern linkPat = Pattern.compile(" href=['\"]([^'\"]*)['\"]");
  static final Pattern parentPat;
  StringBuffer beforeNavbarText;
  ByteArrayOutputStream bout;
  String[] childLinkText;
  OutPort cout;
  File file;
  FileInputStream fin;
  InPort in;
  int nchildren;
  StringBuffer newNavbarText;
  StringBuffer oldNavbarText;
  FileInfo parent;
  String parentName;
  String path;
  boolean scanned;
  boolean writeNeeded;
  
  static
  {
    childPat = Pattern.compile("<a .*</a>");
    parentPat = Pattern.compile("<ul[^>]* parent=['\"]([^'\"]*)['\"]");
  }
  
  public static FileInfo find(File paramFile)
    throws Throwable
  {
    String str = paramFile.getCanonicalPath();
    FileInfo localFileInfo2 = (FileInfo)fileMap.get(str);
    FileInfo localFileInfo1 = localFileInfo2;
    if (localFileInfo2 == null)
    {
      localFileInfo1 = new FileInfo();
      localFileInfo1.file = paramFile;
      fileMap.put(str, localFileInfo1);
    }
    return localFileInfo1;
  }
  
  public void scan()
    throws Throwable
  {
    if (this.scanned) {
      return;
    }
    this.scanned = true;
    this.fin = new FileInputStream(this.file);
    this.in = new InPort(new BufferedInputStream(this.fin));
    this.oldNavbarText = new StringBuffer();
    this.newNavbarText = new StringBuffer();
    if (this.writeNeeded)
    {
      this.bout = new ByteArrayOutputStream();
      this.cout = new OutPort(this.bout);
    }
    int k = 0;
    int i = 0;
    Object localObject1 = new Vector();
    Object localObject2 = this.in.readLine();
    if (localObject2 == null) {}
    do
    {
      localObject2 = new String[((Vector)localObject1).size()];
      this.nchildren = localObject2.length;
      ((Vector)localObject1).copyInto((Object[])localObject2);
      this.childLinkText = ((String[])localObject2);
      if (!this.writeNeeded) {
        this.in.close();
      }
      if (this.parentName == null) {
        break;
      }
      localObject1 = find(new File(this.file.toURI().resolve(this.parentName)));
      ((FileInfo)localObject1).scan();
      this.parent = ((FileInfo)localObject1);
      return;
      if (k == 0) {
        break label405;
      }
    } while (((String)localObject2).indexOf("<!--end-generated-navbar-->") >= 0);
    this.oldNavbarText.append((String)localObject2);
    this.oldNavbarText.append('\n');
    int j = i;
    label268:
    int m;
    if (i != 0)
    {
      if (((String)localObject2).indexOf("<!--end-children-toc-->") >= 0) {
        j = 0;
      }
    }
    else
    {
      m = k;
      if (((String)localObject2).indexOf("<!--start-children-toc-->") >= 0)
      {
        j = 1;
        m = k;
      }
    }
    for (;;)
    {
      i = j;
      k = m;
      if (!this.writeNeeded) {
        break;
      }
      i = j;
      k = m;
      if (m != 0) {
        break;
      }
      this.cout.println((String)localObject2);
      i = j;
      k = m;
      break;
      Matcher localMatcher = childPat.matcher((CharSequence)localObject2);
      if (localMatcher.find()) {
        ((Vector)localObject1).add(localMatcher.group());
      }
      localMatcher = parentPat.matcher((CharSequence)localObject2);
      j = i;
      if (!localMatcher.find()) {
        break label268;
      }
      j = i;
      if (this.parentName != null) {
        break label268;
      }
      this.parentName = localMatcher.group(1);
      j = i;
      break label268;
      label405:
      j = i;
      m = k;
      if (((String)localObject2).indexOf("<!--start-generated-navbar-->") >= 0)
      {
        m = 1;
        j = i;
      }
    }
  }
  
  public void write()
    throws Throwable
  {
    int i = 0;
    Object localObject = this;
    while (((FileInfo)localObject).parent != null)
    {
      localObject = ((FileInfo)localObject).parent;
      i += 1;
    }
    this.cout.println("<!--start-generated-navbar-->");
    writeLinks(i, this.newNavbarText);
    this.cout.print(this.newNavbarText);
    this.cout.println("<!--end-generated-navbar-->");
    for (;;)
    {
      localObject = this.in.readLine();
      if (localObject == null)
      {
        new StringBuffer();
        this.in.close();
        if (!this.oldNavbarText.toString().equals(this.newNavbarText.toString())) {
          break;
        }
        System.err.println("fixup " + this.file + " - no change");
        return;
      }
      this.cout.println((String)localObject);
    }
    localObject = new FileOutputStream(this.file);
    ((FileOutputStream)localObject).write(this.bout.toByteArray());
    ((FileOutputStream)localObject).close();
    System.err.println("fixup " + this.file + " - updated");
  }
  
  public void writeLinks(int paramInt, StringBuffer paramStringBuffer)
    throws Throwable
  {
    FileInfo localFileInfo1 = this;
    FileInfo localFileInfo2 = null;
    int i = paramInt;
    for (;;)
    {
      i -= 1;
      if (i < 0) {
        break;
      }
      localFileInfo2 = localFileInfo1;
      localFileInfo1 = localFileInfo1.parent;
    }
    if (paramInt == 0) {
      paramStringBuffer.append("<!--start-children-toc-->\n");
    }
    int j;
    label99:
    int k;
    Object localObject1;
    if ((paramInt == 0) && (this.parentName != null))
    {
      paramStringBuffer.append("<ul parent=\"");
      paramStringBuffer.append(this.parentName);
      paramStringBuffer.append("\">\n");
      URI localURI1 = this.file.toURI();
      URI localURI2 = localFileInfo1.file.toURI();
      j = 0;
      if (j >= localFileInfo1.nchildren) {
        break label357;
      }
      String str = localFileInfo1.childLinkText[j];
      k = 0;
      localObject1 = str;
      if (paramInt <= 0) {
        break label311;
      }
      localObject1 = linkPat.matcher(str);
      ((Matcher)localObject1).find();
      str = ((Matcher)localObject1).group(1);
      Object localObject2 = localURI2.resolve(str);
      localObject2 = ((Matcher)localObject1).replaceFirst(" href=\"" + Path.relativize(((URI)localObject2).toString(), localURI1.toString()) + "\"");
      i = str.indexOf('#');
      localObject1 = str;
      if (i >= 0) {
        localObject1 = str.substring(0, i);
      }
      if (find(new File(localURI2.resolve((String)localObject1))) != localFileInfo2) {
        break label305;
      }
      i = 1;
      label259:
      k = i;
      localObject1 = localObject2;
      if (i != 0) {
        break label311;
      }
      k = i;
      localObject1 = localObject2;
      if (paramInt <= 1) {
        break label311;
      }
    }
    for (;;)
    {
      j += 1;
      break label99;
      paramStringBuffer.append("<ul>\n");
      break;
      label305:
      i = 0;
      break label259;
      label311:
      paramStringBuffer.append("<li>");
      paramStringBuffer.append((String)localObject1);
      if (k != 0)
      {
        paramStringBuffer.append('\n');
        writeLinks(paramInt - 1, paramStringBuffer);
      }
      paramStringBuffer.append("</li>\n");
    }
    label357:
    paramStringBuffer.append("</ul>\n");
    if (paramInt == 0) {
      paramStringBuffer.append("<!--end-children-toc-->\n");
    }
  }
}


/* Location:              C:\Users\User\Desktop\dex2jar-2.0\classes-dex2jar.jar!\gnu\kawa\util\FileInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */