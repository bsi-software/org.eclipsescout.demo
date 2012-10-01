package org.eclipse.scout.bahbah.server.services.process;

import org.eclipse.scout.bahbah.shared.security.CreateChatPermission;
import org.eclipse.scout.bahbah.shared.security.ReadChatPermission;
import org.eclipse.scout.bahbah.shared.security.UpdateChatPermission;
import org.eclipse.scout.bahbah.shared.services.process.ChatFormData;
import org.eclipse.scout.bahbah.shared.services.process.IChatProcessService;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;

public class ChatProcessService extends AbstractService implements IChatProcessService {

  @Override
  public ChatFormData prepareCreate(ChatFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateChatPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [mzi] business logic here
    return formData;
  }

  @Override
  public ChatFormData create(ChatFormData formData) throws ProcessingException {
    if (!ACCESS.check(new CreateChatPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [mzi] business logic here.
    return formData;
  }

  @Override
  public ChatFormData load(ChatFormData formData) throws ProcessingException {
    if (!ACCESS.check(new ReadChatPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [mzi] business logic here
    return formData;
  }

  @Override
  public ChatFormData store(ChatFormData formData) throws ProcessingException {
    if (!ACCESS.check(new UpdateChatPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }
    //TODO [mzi] business logic here
    return formData;
  }
}
