package org.eclipse.scout.rt.demo.client.ui.forms;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.activitymap.AbstractActivityMap;
import org.eclipse.scout.rt.client.ui.basic.activitymap.IActivityMap;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.plannerfield.AbstractPlannerField;
import org.eclipse.scout.rt.demo.client.ui.forms.PlannerFieldForm.MainBox.PlannerField;
import org.eclipse.scout.rt.shared.TEXTS;

public class PlannerFieldForm extends AbstractForm implements IPageForm {

  public PlannerFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("PlannerField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public PlannerField getPlannerField() {
    return getFieldByClass(PlannerField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class PlannerField extends AbstractPlannerField<PlannerField.ResourceTable, PlannerField.ActivityMap> {

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
      }
    }

  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}
