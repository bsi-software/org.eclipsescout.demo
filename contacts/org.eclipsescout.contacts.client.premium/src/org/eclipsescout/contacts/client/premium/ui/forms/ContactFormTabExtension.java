/**
 *
 */
package org.eclipsescout.contacts.client.premium.ui.forms;

import org.eclipse.scout.commons.annotations.Data;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.rt.client.extension.ui.form.fields.tabbox.AbstractTabBoxExtension;
import org.eclipse.scout.rt.client.ui.basic.table.ITable;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipsescout.contacts.client.ui.forms.ContactForm;
import org.eclipsescout.contacts.client.ui.forms.ContactForm.MainBox.DetailsBox;
import org.eclipsescout.contacts.shared.premium.ui.forms.ContactFormTabExtensionData;

/**
 * @author mzi
 */
@Data(ContactFormTabExtensionData.class)
public class ContactFormTabExtension extends AbstractTabBoxExtension<ContactForm.MainBox.DetailsBox> {

  public ContactFormTabExtension(DetailsBox owner) {
    super(owner);
  }

  @Order(3000)
  public class EventBox extends AbstractGroupBox {

    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("Events");
    }

    @Order(1000)
    public class EventsField extends AbstractTableField<ITable> {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("NextTask");
      }
    }
  }
}
