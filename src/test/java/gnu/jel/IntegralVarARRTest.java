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

import gnu.jel.tests.VariableProvider;

public class IntegralVarARRTest extends TestingUtils {
  public IntegralVarARRTest(String name) {
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
    lib=new Library(staticLib,dynamicLib,null,null,null);
  }
  
  public void test1() throws Throwable {
    simExpression("5*xvar-66",new Double(25-66),Double.TYPE,rtp,lib,null);
  }

  public void test2() throws Throwable {
    simExpression("5*arr[1]-66",new Double(25-66),Double.TYPE,rtp,lib,null);
  }

  public void test3() throws Throwable {
    simExpression("5*(arrs[1]+arrs[2])-66",new Double(25-66),Double.TYPE,rtp,lib,null);
  }

  public void test4() throws Throwable {
    simExpression("5*(arrs[1]+arrs[(int)round(arr[0]-arrs[2])+1])-66",new Double(25-66),Double.TYPE,rtp,lib,null);
  }

  public void test5() throws Throwable {
    simExpression("arrDoubleJELObj1[0]+arrDoubleJELObj1[1]",
                   new Double(3.0), null, rtp, lib,null);
  }

  public void test6() throws Throwable {    
    simExpression("arrDoubleJELObj", vp.arrDoubleJELObj, null, rtp, lib,null);
  }
  
}
