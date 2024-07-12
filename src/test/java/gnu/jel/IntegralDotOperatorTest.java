/* -*- Mode: Java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 2 -*-
*   This file is part of the Java Expressions Library (JEL).
*   For more information about JEL visit : http://fti.dn.ua/JEL/
*
*   Copyright (C) 1998, 1999, 2000, 2001, 2003, 2006, 2007, 2009 Konstantin L. Metlov
*
*   This program is free software: you can redistribute it and/or modify
*   it under the terms of the GNU General Public License as published by
*   the Free Software Foundation, either version 3 of the License, or
*   (at your option) any later version.
*
*   This program is distributed in the hope that it will be useful,
*   but WITHOUT ANY WARRANTY; without even the implied warranty of
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*   GNU General Public License for more details.
*
*   You should have received a copy of the GNU General Public License
*   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package gnu.jel;

import gnu.jel.tests.*;
import java.io.PrintStream;
import java.util.Stack;

public class IntegralDotOperatorTest extends TestingUtils {
  public IntegralDotOperatorTest(String name) {
    super(name);
  }

  Library lib;
  Object[] rtp;
  VariableProvider vp;

  public void setUp() throws Exception {
    Class[] dynamicLib=new Class<?>[1];
    rtp=new Object[1];
    vp=new VariableProvider();
    Class[] staticLib=new Class<?>[2];
    staticLib[0]=Class.forName("java.lang.Math");
    // next line makes also static functions from VariablePrivider available
    staticLib[1]=vp.getClass();  
    vp.xvar=5.0;
    vp.strVar="strVar";
    rtp[0]=vp;
    dynamicLib[0]=vp.getClass();

    Class[] dotLib=new Class<?>[5];
    dotLib[0]=Class.forName("java.lang.String");
    dotLib[1]=Class.forName("java.lang.Double");
    dotLib[2]=Class.forName("gnu.jel.reflect.Double");
    dotLib[3]=IntegerObject.class;
    dotLib[4]=DoubleObject.class;
    lib=new Library(staticLib,dynamicLib,dotLib,null,null);
  }
  
  
  public void test1() throws Throwable {
    simExpression("(\"abc\").length()", new Integer(3), null, rtp, lib,null);
  }

  public void test2() throws Throwable {
    simExpression("\"abc\".length()", new Integer(3), null, rtp, lib,null);
  }

  public void test3() throws Throwable {
    simExpression("strVar.length()", new Integer(6), null, rtp, lib,null);
  }

  public void test4() throws Throwable {
    simExpression("\"abc\".endsWith(\"bc\")", Boolean.TRUE, null, rtp, lib,null);
  }

  public void test5() throws Throwable {
    simExpression("\"abc\".compareTo(\"bc\")", new Integer(-1), null, rtp, lib,null);
  }

  public void test6() throws Throwable {
    simExpression("\"abcdbc\".indexOf(\"bc\")", new Integer(1), null, rtp, lib,null);
  }

  public void test7() throws Throwable {
    simExpression("\"abcdbc\".indexOf(\"abc\".substring(1),2)", new Integer(4), null, rtp, lib,null);
  }

  public void test8() throws Throwable {
    simError("\"abc\".nomethod()",null,lib,7,null);
  }

  public void test9() throws Throwable {
    simError("\"abc\".length(666)",null,lib,7,null);
  }

  public void test10() throws Throwable {
    simError("2.0.doubleValue()",null,lib,4,null);
  }

  public void test11() throws Throwable {
    simExpression("intObj+1", new Integer(556), null, rtp, lib,null);
  }

  public void test12() throws Throwable {
    simExpression("(int)intObj", new Integer(555), null, rtp, lib,null);
  }

  public void test13() throws Throwable {
    simExpression("arr[intObj-554]", new Double(5.0), null, rtp, lib,null);
  }

  public void test14() throws Throwable {
    simExpression("arr[max(intObj-554,0)]", new Double(5.0), null, rtp, lib,null);
  }

  public void test15() throws Throwable {
    simExpression("arr[max(intObj,0)-554]", new Double(5.0), null, rtp, lib,null);
  }

  public void test16() throws Throwable {
    simExpression("arr[byteObj]", new Double(6.0), null, rtp, lib,null);
  }

  public void test17() throws Throwable {
    simExpression("byteObj+1.0", new Double(3.0), null, rtp, lib,null);
  }

  public void test18() throws Throwable {
    simExpression("arrDoubleJELObj1[0].getValue()+"+
                   "arrDoubleJELObj1[1].getValue()",
                   new Double(3.0), null, rtp, lib,null);
  }
  
}
