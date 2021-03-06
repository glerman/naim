package domain;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

@SuppressWarnings("unused")
public class SalaryInfo {

  private int teacherId;
  private String teacherName;
  private String className;
  private String room;
  private String date;
  private String hourMinute;
  private int attendees;
  private int payment;
  private Object[] salaryRow;

  public SalaryInfo(int teacherId, String teacherName, String className, String room,
                    String date, String hourMinute, int attendees, int payment, Object[] salaryRow) {
    this.teacherId = teacherId;
    this.teacherName = teacherName;
    this.className = className;
    this.room = room;
    this.date = date;
    this.hourMinute = hourMinute;
    this.attendees = attendees;
    this.payment = payment;
    this.salaryRow = salaryRow;
  }

  public SalaryInfo() {
  }

  public String getDisplayDate() {
    return hourMinute + " " + date;
  }

  public String getTeacherName() {
    return teacherName;
  }

  public void setTeacherName(String teacherName) {
    this.teacherName = teacherName;
  }

  public Object[] getSalaryRow() {
    return salaryRow;
  }

  public void setSalaryRow(Object[] salaryRow) {
    this.salaryRow = salaryRow;
  }

  public int getTeacherId() {
    return teacherId;
  }

  public void setTeacherId(int teacherId) {
    this.teacherId = teacherId;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getAttendees() {
    return attendees;
  }

  public void setAttendees(int attendees) {
    this.attendees = attendees;
  }

  public int getPayment() {
    return payment;
  }

  public void setPayment(int payment) {
    this.payment = payment;
  }

  public String getHourMinute() {
    return hourMinute;
  }

  public void setHourMinute(String hourMinute) {
    this.hourMinute = hourMinute;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
            .add("teacherId", teacherId)
            .add("teacherName", teacherName)
            .add("className", className)
            .add("room", room)
            .add("date", date)
            .add("hourMinute", hourMinute)
            .add("attendees", attendees)
            .add("payment", payment)
            .add("salaryRow", salaryRow)
            .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SalaryInfo that = (SalaryInfo) o;
    return teacherId == that.teacherId &&
            attendees == that.attendees &&
            payment == that.payment &&
            Objects.equal(teacherName, that.teacherName) &&
            Objects.equal(className, that.className) &&
            Objects.equal(room, that.room) &&
            Objects.equal(date, that.date) &&
            Objects.equal(hourMinute, that.hourMinute) &&
            Objects.equal(salaryRow, that.salaryRow);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(teacherId, teacherName, className, room, date, hourMinute, attendees, payment, salaryRow);
  }
}
