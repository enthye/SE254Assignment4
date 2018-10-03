package se254.a4.q3;

import java.lang.reflect.*;
import java.util.*;

public class ExtractorTemplate
{
  protected static String nl;
  public static synchronized ExtractorTemplate create(String lineSeparator)
  {
    nl = lineSeparator;
    ExtractorTemplate result = new ExtractorTemplate();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t" + NL + "public class ";
  protected final String TEXT_2 = " {" + NL + "\t";
  protected final String TEXT_3 = "protected ";
  protected final String TEXT_4 = " ";
  protected final String TEXT_5 = ";" + NL + "\t";
  protected final String TEXT_6 = NL + "} " + NL + "" + NL + "public class ";
  protected final String TEXT_7 = " extends ";
  protected final String TEXT_8 = "  {" + NL + "\t";
  protected final String TEXT_9 = "private ";
  protected final String TEXT_10 = NL + "}" + NL + "" + NL + "public class ";
  protected final String TEXT_11 = NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     try { 
     Class<?> c = (Class<?>) argument.getClass(); 
	Class<?> a = (Class<?>) c.getDeclaredField("a").get(argument); 
	Class<?> b = (Class<?>) c.getDeclaredField("b").get(argument); 
	List<String> list = new ArrayList<>();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(c.getDeclaredField("className").get(argument));
    stringBuffer.append(TEXT_2);
     for (Field fa : a.getFields()) { 
		for (Field fb : b.getFields()) {
			if (fa.getType().equals(fb.getType()) && fa.getName().equals(fb.getName())) {
				
    stringBuffer.append(TEXT_3);
    stringBuffer.append(fa.getType());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(fa.getName());
    stringBuffer.append(TEXT_5);
    list.add(fa.getName());
			}
		 }
	} 
    stringBuffer.append(TEXT_6);
    stringBuffer.append(a.getSimpleName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(c.getDeclaredField("className").get(argument));
    stringBuffer.append(TEXT_8);
     for (Field fa : a.getFields()) { 
		if (!list.contains(fa.getName())) {
    stringBuffer.append(TEXT_9);
    stringBuffer.append(fa.getType());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(fa.getName());
    stringBuffer.append(TEXT_5);
    }
	}
    stringBuffer.append(TEXT_10);
    stringBuffer.append(b.getSimpleName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(c.getDeclaredField("className").get(argument));
    stringBuffer.append(TEXT_2);
     for (Field fb : b.getFields()) {
	if (!list.contains(fb.getName())) {
    stringBuffer.append(TEXT_9);
    stringBuffer.append(fb.getType());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(fb.getName());
    stringBuffer.append(TEXT_5);
    }
	}
    stringBuffer.append(TEXT_11);
     } catch (Exception e) {}
    return stringBuffer.toString();
  }
}
