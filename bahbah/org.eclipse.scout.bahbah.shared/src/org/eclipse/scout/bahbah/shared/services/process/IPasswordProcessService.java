package org.eclipse.scout.bahbah.shared.services.process;

import org.eclipse.scout.rt.shared.services.common.pwd.IPasswordManagementService;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface IPasswordProcessService extends IPasswordManagementService {

}
