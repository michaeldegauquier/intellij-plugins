/*
 * Copyright 2011 The authors
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.intellij.struts2.dom.struts;

import com.intellij.codeInspection.LocalInspectionTool;
import com.intellij.struts2.BasicHighlightingTestCase;
import com.intellij.struts2.dom.inspection.Struts2ModelInspection;
import com.intellij.testFramework.builders.JavaModuleFixtureBuilder;
import org.jetbrains.annotations.NonNls;

/**
 * Base class for struts.xml highlighting tests.
 *
 * @author Yann C&eacute;bron
 */
public abstract class BasicStrutsHighlightingTestCase<T extends JavaModuleFixtureBuilder> extends BasicHighlightingTestCase<T> {

  @Override
  protected LocalInspectionTool[] getHighlightingInspections() {
    return new LocalInspectionTool[]{new Struts2ModelInspection()};
  }

  /**
   * Performs highlighting test for the given struts.xml file(s).
   *
   * @param strutsXmlFileNames Filename(s) of struts.xml to check.
   * @throws Throwable On any errors.
   */
  protected void performHighlightingTest(@NonNls final String... strutsXmlFileNames) throws Throwable {
    createStrutsFileSet(strutsXmlFileNames);
    final long duration = myFixture.testHighlighting(true, false, false, strutsXmlFileNames);
//    System.out.println(strutsXmlFileNames[0] + " = " + duration);
  }

  /**
   * Performs completion variants test for the given struts.xml file.
   *
   * @param strutsXmlFileName Filename of struts.xml to check.
   * @param expectedItems     Expected completion variants.
   * @throws Throwable On any errors.
   */
  protected void performCompletionVariantTest(@NonNls final String strutsXmlFileName,
                                              @NonNls final String... expectedItems) throws Throwable {
    createStrutsFileSet(strutsXmlFileName);
    myFixture.testCompletionVariants(strutsXmlFileName, expectedItems);
  }

}