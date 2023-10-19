package com.music;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.apache.commons.io.IOUtils;

public class Transform {
  public static String rightPadZeros(String str, int num) {
    return String.format("%-" + num + "s", new Object[] { str }).replace(' ', '0');
  }
  
  public static String leftPadZeros(String str, int num) {
    return String.format("%0" + num + "d", new Object[] { Integer.valueOf((int)Float.parseFloat(str)) });
  }
  
  public static void main(String[] args) {
    try {
      String aa = "halo,";
      System.out.println(aa.substring(0, aa.length() - 1));
      System.out.println("leftPad-" + leftPadZeros("51", 20) + "-");
      System.out.println("righPad-" + rightPadZeros("51", 20) + "-");
      String fileName = args[0];
      String indexRow = args[1];
      System.out.println("hello" + fileName + " <> " + indexRow);
      File file = new File(fileName);
      FileInputStream inputStream = new FileInputStream(file);
      String data = IOUtils.toString(inputStream, "UTF-8");
      inputStream.close();
      String[] elements = data.split("\\}\\,\\{");
      System.out.println("size: " + elements.length);
      System.out.println("Data: ");
      byte b;
      int j;
      String[] arrayOfString1;
      for (j = (arrayOfString1 = elements).length, b = 0; b < j; ) {
        String element = arrayOfString1[b];
        System.out.println(element);
        b++;
      } 
      String result = String.valueOf(indexRow) + "\n" + elements[0].substring(1) + "}\n";
      for (int i = 1; i < elements.length - 1; i++)
        result = String.valueOf(result) + indexRow + "\n" + "{" + elements[i] + "}\n"; 
      result = String.valueOf(result) + indexRow + "\n" + "{" + elements[elements.length - 1].substring(0, elements[elements.length - 1].length() - 1);
      Path targetPath = Paths.get(String.valueOf(fileName) + "_", new String[0]);
      byte[] bytes = result.getBytes(StandardCharsets.UTF_8);
      Files.write(targetPath, bytes, new OpenOption[] { StandardOpenOption.CREATE });
    } catch (Exception e) {
      e.printStackTrace();
    } 
  }
}
