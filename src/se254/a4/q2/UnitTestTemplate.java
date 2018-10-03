package se254.a4.q2;

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
  protected final String TEXT_1 = "// Your code goes here!";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}
