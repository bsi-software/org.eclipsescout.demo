package org.eclipse.scout.bahbah.shared.services.process;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.IService;

public interface IChatProcessService extends IService {

  ChatFormData prepareCreate(ChatFormData formData) throws ProcessingException;

  ChatFormData create(ChatFormData formData) throws ProcessingException;

  ChatFormData load(ChatFormData formData) throws ProcessingException;

  ChatFormData store(ChatFormData formData) throws ProcessingException;
}
