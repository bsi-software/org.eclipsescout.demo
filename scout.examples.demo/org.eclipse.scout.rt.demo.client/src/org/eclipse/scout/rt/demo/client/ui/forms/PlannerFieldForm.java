package org.eclipse.scout.rt.demo.client.ui.forms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.eclipse.scout.commons.DateUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.activitymap.AbstractActivityMap;
import org.eclipse.scout.rt.client.ui.basic.activitymap.ActivityCell;
import org.eclipse.scout.rt.client.ui.basic.activitymap.IActivityMap;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.plannerfield.AbstractPlannerField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.demo.client.ui.forms.PlannerFieldForm.MainBox.TabBox;
import org.eclipse.scout.rt.demo.client.ui.forms.PlannerFieldForm.MainBox.TabBox.PlannerField1Box;
import org.eclipse.scout.rt.demo.client.ui.forms.PlannerFieldForm.MainBox.TabBox.PlannerField1Box.Planner1Field;
import org.eclipse.scout.rt.demo.client.ui.forms.PlannerFieldForm.MainBox.TabBox.PlannerField2Box;
import org.eclipse.scout.rt.demo.client.ui.forms.PlannerFieldForm.MainBox.TabBox.PlannerField2Box.DayPlannerField;
import org.eclipse.scout.rt.demo.client.ui.forms.PlannerFieldForm.MainBox.TabBox.PlannerField2Box.IntradayPlannerField;
import org.eclipse.scout.rt.demo.client.ui.forms.PlannerFieldForm.MainBox.TabBox.PlannerField2Box.WeekPlannerField;
import org.eclipse.scout.rt.shared.TEXTS;

public class PlannerFieldForm extends AbstractForm implements IPageForm {

  public PlannerFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("PlannerField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public DayPlannerField getDayPlannerField() {
    return getFieldByClass(DayPlannerField.class);
  }

  public IntradayPlannerField getIntradayPlannerField() {
    return getFieldByClass(IntradayPlannerField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public Planner1Field getPlanner1Field() {
    return getFieldByClass(Planner1Field.class);
  }

  public PlannerField1Box getPlannerField1Box() {
    return getFieldByClass(PlannerField1Box.class);
  }

  public PlannerField2Box getPlannerField2Box() {
    return getFieldByClass(PlannerField2Box.class);
  }

  public TabBox getTabBox() {
    return getFieldByClass(TabBox.class);
  }

  public WeekPlannerField getWeekPlannerField() {
    return getFieldByClass(WeekPlannerField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(20.0)
    public class TabBox extends AbstractTabBox {

      @Order(10.0)
      public class PlannerField1Box extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("PlannerField1");
        }

        @Order(10.0)
        public class Planner1Field extends AbstractPlannerField<Planner1Field.ResourceTable, Planner1Field.ActivityMap> {

          @Override
          protected int getConfiguredGridH() {
            return 15;
          }

          @Override
          protected int getConfiguredGridW() {
            return 8;
          }

          @Override
          protected void execInitField() throws ProcessingException {
            getActivityMap().setPlanningMode(IActivityMap.PLANNING_MODE_WEEK);
            ArrayList<Date> days = new ArrayList<Date>();
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < 26; i++) {
              days.add(cal.getTime());
              cal.add(Calendar.WEEK_OF_YEAR, 1);
            }
            getActivityMap().setDays(days.toArray(new Date[0]));
            loadResourceTableData();
          }

          @Override
          protected Object[][] execLoadActivityMapData(Long[] resourceIds, ITableRow[] resourceRows) throws ProcessingException {
            Object[][] data = new Object[resourceIds.length * 26][9];
            int dataIndex = 0;
            Random rnd = new Random(123);
            double maj = 0.5;
            double min = 0.5;
            for (int rowIndex = 0; rowIndex < resourceIds.length; rowIndex++) {
              Calendar cal = Calendar.getInstance();
              cal.setTime(getActivityMap().getBeginTime());
              for (int week = 0; week < 26; week++) {
                data[dataIndex][0] = resourceIds[rowIndex];
                data[dataIndex][1] = dataIndex;
                data[dataIndex][2] = cal.getTime();
                cal.add(Calendar.DATE, 5);
                data[dataIndex][3] = cal.getTime();
                cal.add(Calendar.DATE, 2);
                data[dataIndex][7] = maj;
                data[dataIndex][8] = min;
                // next
                dataIndex++;
                if (maj < 1) maj += rnd.nextDouble() / 5;
                if (maj > 0) maj -= rnd.nextDouble() / 5;
                if (min < 1) min += rnd.nextDouble() / 5;
                if (min > 0) min -= rnd.nextDouble() / 5;
              }
            }
            return data;
          }

          @Override
          protected Object[][] execLoadResourceTableData() throws ProcessingException {
            Object[][] data = new Object[100][2];
            for (int i = 0; i < data.length; i++) {
              data[i][0] = i;
              data[i][1] = "Name " + i;
            }
            return data;
          }

          @Order(10.0)
          public class ResourceTable extends AbstractTable {

            @Override
            protected boolean getConfiguredAutoResizeColumns() {
              return true;
            }

            @Override
            protected boolean getConfiguredSortEnabled() {
              return false;
            }

            @Override
            protected void execRowAction(ITableRow row) throws ProcessingException {
              loadActivityMapDataOfSelectedRecources();
            }

            public ResourceIdColumn getResourceIdColumn() {
              return getColumnSet().getColumnByClass(ResourceIdColumn.class);
            }

            public NameColumn getNameColumn() {
              return getColumnSet().getColumnByClass(NameColumn.class);
            }

            @Order(10.0f)
            public class ResourceIdColumn extends AbstractLongColumn {
              @Override
              protected boolean getConfiguredPrimaryKey() {
                return true;
              }
            }

            @Order(20.0f)
            public class NameColumn extends AbstractStringColumn {
            }
          }

          @Order(10.0)
          public class ActivityMap extends AbstractActivityMap {

            @Override
            protected void execDecorateActivityCell(ActivityCell cell) throws ProcessingException {
              cell.setMajorColor("FE9915");
              cell.setMinorColor("046989");
            }
          }
        }
      }

      @Order(20.0)
      public class PlannerField2Box extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("PlannerField2");
        }

        @Order(10.0)
        public class WeekPlannerField extends AbstractPlannerField<WeekPlannerField.ResourceTable, WeekPlannerField.ActivityMap> {

          private Date base = DateUtility.parse("20120717", "yyyyMMdd");

          private Object[][] weekTestcases = new Object[][]{
              {true, base, base, "start/end date same, at the beginning of the displayed range."},
              {true, DateUtility.addDays(base, 7), DateUtility.addDays(base, 7), "start/end date same, not at the beginning of the range."},
              {true, base, DateUtility.addDays(base, 7), "a whole week, including the first day of the next week."},
              {true, base, DateUtility.addDays(base, 6), "a whole week, excluding the first day of the next week."},
              {true, base, DateUtility.addDays(base, 8), "a whole week, including the first two days of the next week."},
              {true, DateUtility.addDays(base, 13), DateUtility.addDays(base, 14), "Two days ascribed to two different weeks."}
          };

          @Override
          protected int getConfiguredGridH() {
            return 5;
          }

          @Override
          protected int getConfiguredGridW() {
            return 2;
          }

          @Override
          protected int getConfiguredMiniCalendarCount() {
            return 0;
          }

          @Override
          protected void execInitField() throws ProcessingException {
            getActivityMap().setPlanningMode(IActivityMap.PLANNING_MODE_WEEK);
            Date[] weeks = new Date[26];
            Date d = base;
            for (int i = 0; i < weeks.length; i++) {
              weeks[i] = d;
              d = DateUtility.addDays(d, 7);
            }
            getActivityMap().setDays(weeks);
            loadResourceTableData();
          }

          @Override
          protected Object[][] execLoadActivityMapData(Long[] resourceIds, ITableRow[] resourceRows) throws ProcessingException {
            Object[][] data = new Object[weekTestcases.length][9];
            for (int i = 0; i < data.length; i++) {
              // resourceId
              data[i][0] = i;
              // activityId
              data[i][1] = i;
              // startTime
              data[i][2] = weekTestcases[i][1];
              // endTime
              data[i][3] = weekTestcases[i][2];
              // text
              data[i][4] = "";
              // tooltipText
              data[i][5] = "";
              // iconId
              data[i][6] = "";
              // majorValue
              data[i][7] = ((Boolean) weekTestcases[i][0]) ? 1f : 0f;
              // minorValue
              data[i][8] = null;
            }
            return data;
          }

          @Override
          protected Object[][] execLoadResourceTableData() throws ProcessingException {
            Object[][] data = new Object[weekTestcases.length][2];
            for (int i = 0; i < data.length; i++) {
              data[i][0] = i;
              data[i][1] = "Test " + i + ": " + weekTestcases[i][3]
                  + "; startDate: " + DateUtility.formatDate((Date) weekTestcases[i][1])
                  + "; endDate: " + DateUtility.formatDate((Date) weekTestcases[i][2]);
            }
            return data;
          }

          @Order(10.0)
          public class ResourceTable extends AbstractTable {

            @Override
            protected boolean getConfiguredAutoResizeColumns() {
              return true;
            }

            @Override
            protected void execDecorateRow(ITableRow row) throws ProcessingException {
              if ((Boolean) weekTestcases[getIdColumn().getValue(row).intValue()][0]) {
                row.setBackgroundColor("BBFFAA");
              }
              else {
                row.setBackgroundColor("FCDCFE");
              }
            }

            public IdColumn getIdColumn() {
              return getColumnSet().getColumnByClass(IdColumn.class);
            }

            public NameColumn getNameColumn() {
              return getColumnSet().getColumnByClass(NameColumn.class);
            }

            @Order(10.0)
            public class IdColumn extends AbstractLongColumn {

              @Override
              protected boolean getConfiguredPrimaryKey() {
                return true;
              }

              @Override
              protected boolean getConfiguredDisplayable() {
                return false;
              }

            }

            @Order(20.0)
            public class NameColumn extends AbstractStringColumn {

              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("Week");
              }

              @Override
              protected int getConfiguredWidth() {
                return 150;
              }
            }
          }

          @Order(20.0)
          public class ActivityMap extends AbstractActivityMap {
          }
        }

        @Order(20.0)
        public class DayPlannerField extends AbstractPlannerField<DayPlannerField.ResourceTable, DayPlannerField.ActivityMap> {

          private Date base = DateUtility.parse("20120717", "yyyyMMdd");

          private Object[][] dayTestcases = new Object[][]{
              {true, base, base, "start/end date same, at the beginning of the displayed range."},
              {true, DateUtility.addDays(base, 7), DateUtility.addDays(base, 7), "start/end date same, not at the beginning of the range."},
              {true, base, DateUtility.addDays(base, 7), "a whole week, including the first day of the next week."},
              {true, base, DateUtility.addDays(base, 6), "a whole week, excluding the first day of the next week."},
              {true, base, DateUtility.addDays(base, 8), "a whole week, including the first two days of the next week."},
              {true, DateUtility.addDays(base, 13), DateUtility.addDays(base, 14), "Two days ascribed to two different weeks."}
          };

          @Override
          protected int getConfiguredGridH() {
            return 5;
          }

          @Override
          protected int getConfiguredGridW() {
            return 2;
          }

          @Override
          protected int getConfiguredMiniCalendarCount() {
            return 0;
          }

          @Override
          protected void execInitField() throws ProcessingException {
            getActivityMap().setPlanningMode(IActivityMap.PLANNING_MODE_DAY);
            loadResourceTableData();
            Date[] days = new Date[26];
            Date d = base;
            for (int i = 0; i < days.length; i++) {
              days[i] = d;
              d = DateUtility.addDays(d, 1);
            }
            getActivityMap().setDays(days);
          }

          @Override
          protected Object[][] execLoadActivityMapData(Long[] resourceIds, ITableRow[] resourceRows) throws ProcessingException {
            Object[][] data = new Object[dayTestcases.length][9];
            for (int i = 0; i < data.length; i++) {
              // resourceId
              data[i][0] = i;
              // activityId
              data[i][1] = i;
              // startTime
              data[i][2] = (dayTestcases[i][1] != null) ? dayTestcases[i][1] : base;
              // endTime
              data[i][3] = dayTestcases[i][2];
              // text
              data[i][4] = null;
              // tooltipText
              data[i][5] = null;
              // iconId
              data[i][6] = null;
              // majorValue
              data[i][7] = ((Boolean) dayTestcases[i][0]) ? 1f : 0f;
              // minorValue
              data[i][8] = null;
            }
            return data;
          }

          @Override
          protected Object[][] execLoadResourceTableData() throws ProcessingException {
            Object[][] data = new Object[dayTestcases.length][2];
            for (int i = 0; i < data.length; i++) {
              data[i][0] = i;
              data[i][1] = "Test " + i + ": " + dayTestcases[i][3]
                  + "; startDate: " + DateUtility.formatDate((Date) dayTestcases[i][1])
                  + "; endDate: " + DateUtility.formatDate((Date) dayTestcases[i][2]);
            }
            return data;
          }

          @Order(10.0)
          public class ResourceTable extends AbstractTable {

            @Override
            protected boolean getConfiguredAutoResizeColumns() {
              return true;
            }

            @Override
            protected void execDecorateRow(ITableRow row) throws ProcessingException {
              if ((Boolean) dayTestcases[getIdColumn().getValue(row).intValue()][0]) {
                row.setBackgroundColor("BBFFAA");
              }
              else {
                row.setBackgroundColor("FCDCFE");
              }
            }

            public IdColumn getIdColumn() {
              return getColumnSet().getColumnByClass(IdColumn.class);
            }

            public NameColumn getNameColumn() {
              return getColumnSet().getColumnByClass(NameColumn.class);
            }

            @Order(10.0)
            public class IdColumn extends AbstractLongColumn {

              @Override
              protected boolean getConfiguredPrimaryKey() {
                return true;
              }

              @Override
              protected boolean getConfiguredDisplayable() {
                return false;
              }

            }

            @Order(20.0)
            public class NameColumn extends AbstractStringColumn {

              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("Day");
              }

              @Override
              protected int getConfiguredWidth() {
                return 150;
              }
            }
          }

          @Order(20.0)
          public class ActivityMap extends AbstractActivityMap {
          }
        }

        @Order(30.0)
        public class IntradayPlannerField extends AbstractPlannerField<IntradayPlannerField.ResourceTable, IntradayPlannerField.ActivityMap> {

          private Date base = DateUtility.parse("20120717", "yyyyMMdd");

          private Date intradayBase = DateUtility.parse("20120717 08:00", "yyyyMMdd HH:mm"); //a monday

          private Object[][] intradayTestcases = new Object[][]{
              {true, intradayBase, intradayBase, "start/end date same, at the beginning of the displayed range."},
              {true, DateUtility.addHours(intradayBase, 1), DateUtility.addHours(intradayBase, 1), "start/end date same, not at the beginning of the range."},
              {true, intradayBase, DateUtility.addHours(intradayBase, 1 + 24), "a whole day, including the first hour of the next day."},
              {true, intradayBase, DateUtility.addHours(intradayBase, 9), "a whole day, excluding the first hour of the next day."},
              {true, intradayBase, DateUtility.addHours(intradayBase, 2 + 24), "a whole day, including the first two hours of the next day."},
              {true, DateUtility.addHours(intradayBase, 24 + 2), DateUtility.addHours(intradayBase, 48), "Two hours ascribed to two different days."},
              {true, DateUtility.addHours(intradayBase, 24 + 2), new Date(DateUtility.addHours(intradayBase, 24 + 3).getTime() + 1000 * 60 * 30), "a range lasting 1.5h."},
              {true, DateUtility.addHours(intradayBase, 24 + 2), DateUtility.addHours(intradayBase, 24 + 4), "a range lasting 2h."},
              {true, DateUtility.addHours(intradayBase, 24 + 2), new Date(DateUtility.addHours(intradayBase, 24 + 4).getTime() + 1), "a range lasting 2h plus 1 ms."}
          };

          @Override
          protected int getConfiguredGridH() {
            return 5;
          }

          @Override
          protected int getConfiguredGridW() {
            return 2;
          }

          @Override
          protected int getConfiguredMiniCalendarCount() {
            return 0;
          }

          @Override
          protected void execInitField() throws ProcessingException {
            getActivityMap().setPlanningMode(IActivityMap.PLANNING_MODE_INTRADAY);
            loadResourceTableData();
            Date[] intradays = new Date[7];
            Date d = base;
            for (int i = 0; i < intradays.length; i++) {
              intradays[i] = d;
              d = DateUtility.addDays(d, 1);
            }
            getActivityMap().setDays(intradays);
          }

          @Override
          protected Object[][] execLoadActivityMapData(Long[] resourceIds, ITableRow[] resourceRows) throws ProcessingException {
            Object[][] data = new Object[intradayTestcases.length][9];
            for (int i = 0; i < data.length; i++) {
              // resourceId
              data[i][0] = i;
              // activityId
              data[i][1] = i;
              // startTime
              data[i][2] = intradayTestcases[i][1];
              // endTime
              data[i][3] = intradayTestcases[i][2];
              // text
              data[i][4] = null;
              // tooltipText
              data[i][5] = null;
              // iconId
              data[i][6] = null;
              // majorValue
              data[i][7] = ((Boolean) intradayTestcases[i][0]) ? 1f : 0f;
              // minorValue
              data[i][8] = null;
            }
            return data;
          }

          @Override
          protected Object[][] execLoadResourceTableData() throws ProcessingException {
            Object[][] data = new Object[intradayTestcases.length][2];
            for (int i = 0; i < data.length; i++) {
              data[i][0] = i;
              data[i][1] = "Test " + i + ": " + intradayTestcases[i][3]
                  + "; startDate: " + DateUtility.formatDateTime((Date) intradayTestcases[i][1])
                  + "; endDate: " + DateUtility.formatDateTime((Date) intradayTestcases[i][2]);
            }
            return data;
          }

          @Order(10.0)
          public class ResourceTable extends AbstractTable {

            @Override
            protected boolean getConfiguredAutoResizeColumns() {
              return true;
            }

            @Override
            protected void execDecorateRow(ITableRow row) throws ProcessingException {
              if ((Boolean) intradayTestcases[getIdColumn().getValue(row).intValue()][0]) {
                row.setBackgroundColor("BBFFAA");
              }
              else {
                row.setBackgroundColor("FCDCFE");
              }
            }

            public IdColumn getIdColumn() {
              return getColumnSet().getColumnByClass(IdColumn.class);
            }

            public NameColumn getNameColumn() {
              return getColumnSet().getColumnByClass(NameColumn.class);
            }

            @Order(10.0)
            public class IdColumn extends AbstractLongColumn {

              @Override
              protected boolean getConfiguredPrimaryKey() {
                return true;
              }

              @Override
              protected boolean getConfiguredDisplayable() {
                return false;
              }

            }

            @Order(20.0)
            public class NameColumn extends AbstractStringColumn {

              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("Intraday");
              }

              @Override
              protected int getConfiguredWidth() {
                return 150;
              }
            }
          }

          @Order(20.0)
          public class ActivityMap extends AbstractActivityMap {
          }
        }

      }
    }

  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
