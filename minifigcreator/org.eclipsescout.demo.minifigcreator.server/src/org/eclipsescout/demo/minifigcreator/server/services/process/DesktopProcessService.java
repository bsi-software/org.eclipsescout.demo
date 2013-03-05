package org.eclipsescout.demo.minifigcreator.server.services.process;


import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;
import org.eclipse.scout.service.AbstractService;
import org.eclipsescout.demo.minifigcreator.shared.minifig.part.Part;
import org.eclipsescout.demo.minifigcreator.shared.services.lookup.HeadLookupCall;
import org.eclipsescout.demo.minifigcreator.shared.services.lookup.LegsLookupCall;
import org.eclipsescout.demo.minifigcreator.shared.services.lookup.TorsoLookupCall;
import org.eclipsescout.demo.minifigcreator.shared.services.process.DesktopFormData;
import org.eclipsescout.demo.minifigcreator.shared.services.process.FormState;
import org.eclipsescout.demo.minifigcreator.shared.services.process.IDesktopProcessService;

public class DesktopProcessService extends AbstractService implements IDesktopProcessService {

  @Override
  public DesktopFormData load(DesktopFormData formData) throws ProcessingException {
    FormState fs = new FormState();
    fs.setHeadEnabled(computePartState(new HeadLookupCall(), formData.getHead()));
    fs.setTorsoEnabled(computePartState(new TorsoLookupCall(), formData.getTorso()));
    fs.setLegsEnabled(computePartState(new LegsLookupCall(), formData.getLegs()));
    formData.setState(fs);
    return formData;
  }

  private boolean computePartState(LookupCall lookupCall, AbstractValueFieldData<Part> fieldData) throws ProcessingException {
    LookupRow[] rows = lookupCall.getDataByAll();
    if (rows == null || rows.length == 0) {
      return false;
    }
    else if (rows.length == 1) {
      fieldData.setValue((Part) rows[0].getKey());
      return false;
    }
    else {
      return true;
    }
  }
}
