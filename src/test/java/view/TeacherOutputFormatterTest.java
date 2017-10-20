package view;

import domain.SalaryInfo;
import domain.TeacherOutput;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class TeacherOutputFormatterTest {

  @Test
  public void test() throws Exception {
    TeacherOutputFormatter formatter = new TeacherOutputFormatter();
    Map<String, Collection<SalaryInfo>> map = Collections.singletonMap("class", ViewTestDataGenerator.salaries);
    int sum = map.values().stream().flatMap(Collection::stream).mapToInt(SalaryInfo::getPayment).sum();
    TeacherOutput teacherOutput = new TeacherOutput(map, sum);
    FormattedOutput formattedOutput = formatter.formatTeacherOutput("teach", teacherOutput);

    System.out.println(formattedOutput.toString());
  }
}