package com.shian.shianlife;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.shian.shianlife.TestClass.unicode2String;
import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void testJava() throws Exception {
      System.out.print(unicode2String("\\u5f20\\u5b8f\\u4e1a\\u55e8\\u6f02\\u4eae"));
    }

}