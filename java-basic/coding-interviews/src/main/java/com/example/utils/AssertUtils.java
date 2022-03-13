package com.example.utils;

public class AssertUtils {

  public static <T> void assertEqual(T o1, T o2) {
    if (o1 == o2) {
      return;
    }
    if (o1 == null || !o1.equals(o2)) {
      throwError(String.format("【%s】and【%s】are not equal", o1, o2));
    }
  }

  private static void throwError(String msg) {
    throw new RuntimeException(msg);
  }
}
