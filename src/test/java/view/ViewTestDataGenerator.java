package view;

import com.google.common.collect.Lists;
import domain.SalaryInfo;

import java.util.List;

public class ViewTestDataGenerator {

  public static String[] columnNames = {"כיתה", "אולם", "תאריך", "משתתפים", "תעריף"};

  public static List<SalaryInfo> salaries = Lists.newArrayList(
    new SalaryInfo(1, "teacher1", "עברית", "room1", "01/09", 5, 80, null),
    new SalaryInfo(2, "teacher2", "class2", "room2", "02/09", 6, 90, null),
    new SalaryInfo(3, "teacher3", "class3", "room3", "03/09", 7, 100, null)
  );

  public static void main(String[] args) {
    Lists.newArrayList(columnNames).forEach(s -> System.out.print("\"" + s + "\","));
    System.out.println();
    for (int i=0; i<columnNames.length / 2 ; i++) {
      String curr = columnNames[i];
      int mirrorIdx = columnNames.length - 1 - i;
      String mirror = columnNames[mirrorIdx];

      columnNames[i] = mirror;
      columnNames[mirrorIdx] = curr;
    }

    Lists.newArrayList(columnNames).forEach(s -> System.out.print("\"" + s + "\","));
  }
}