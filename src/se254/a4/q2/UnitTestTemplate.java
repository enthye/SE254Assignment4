package se254.a4.q2;

import java.lang.reflect.*;
import java.util.*;

public class UnitTestTemplate
{
  protected static String nl;
  public static synchronized UnitTestTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    UnitTestTemplate result = new UnitTestTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "import static org.junit.Assert.*;" + NL + "import org.junit.Test;" + NL + "" + NL + "public class ";
  protected final String TEXT_2 = "Test {";
  protected final String TEXT_3 = NL + "\t@Test" + NL + "\tpublic void test";
  protected final String TEXT_4 = "() {" + NL + "\t\tfail(\"Not yet implemented\");" + NL + "\t}\t";
  protected final String TEXT_5 = NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     Class<?> c = (Class<?>) argument; 
     HashSet<String> methodNames = new HashSet<>(); 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(c.getSimpleName());
    stringBuffer.append(TEXT_2);
     for(Method m : c.getDeclaredMethods()) { 
	if (!methodNames.contains(m.getName())) { 
		methodNames.add(m.getName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(m.getName().substring(0,1).toUpperCase() + m.getName().substring(1) );
    stringBuffer.append(TEXT_4);
    }}
    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}
