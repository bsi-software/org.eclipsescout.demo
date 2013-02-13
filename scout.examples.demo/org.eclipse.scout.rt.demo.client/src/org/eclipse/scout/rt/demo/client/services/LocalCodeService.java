package org.eclipse.scout.rt.demo.client.services;

import java.util.ArrayList;

import org.eclipse.core.runtime.Platform;
import org.eclipse.scout.commons.annotations.Priority;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.holders.Holder;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.commons.osgi.BundleClassDescriptor;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.rt.shared.services.common.code.ICodeService;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.common.code.ICodeVisitor;
import org.eclipse.scout.rt.shared.services.common.exceptionhandler.IExceptionHandlerService;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

/**
 * delegates to {@link CodeTypeStore}
 */
@Priority(1)
public class LocalCodeService extends AbstractService implements ICodeService {
  private static final IScoutLogger LOG = ScoutLogManager.getLogger(LocalCodeService.class);

  @Override
  public <T extends ICodeType> T getCodeType(Class<T> type) {
    T instance = null;
    try {
      instance = type.newInstance();
    }
    catch (Throwable t) {
      SERVICES.getService(IExceptionHandlerService.class).handleException(new ProcessingException("create " + type.getName(), t));
    }
    return instance;
  }

  @Override
  public <T extends ICodeType> T getCodeType(Long partitionId, Class<T> type) {
    return getCodeType(type);
  }

  @Override
  public ICodeType findCodeTypeById(Object id) {
    if (id == null) {
      return null;
    }
    for (ICodeType ct : getAllCodeTypes("")) {
      if (id.equals(ct.getId())) {
        return ct;
      }
    }
    return null;
  }

  @Override
  public ICodeType findCodeTypeById(Long partitionId, Object id) {
    return findCodeTypeById(id);
  }

  @SuppressWarnings("unchecked")
  @Override
  public ICodeType[] getCodeTypes(Class... types) {
    ICodeType[] instances = new ICodeType[types.length];
    for (int i = 0; i < instances.length; i++) {
      instances[i] = getCodeType(types[i]);
    }
    return instances;
  }

  @Override
  public ICodeType[] getCodeTypes(Long partitionId, Class... types) {
    return getCodeTypes(types);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T extends ICode> T getCode(final Class<T> type) {
    if (type == null) {
      return null;
    }
    Class declaringCodeTypeClass = null;
    if (type.getDeclaringClass() != null) {
      // code is inner type of code type or another code
      Class c = type.getDeclaringClass();
      while (c != null && !(ICodeType.class.isAssignableFrom(c))) {
        c = c.getDeclaringClass();
      }
      declaringCodeTypeClass = c;
    }
    if (declaringCodeTypeClass == null) {
      try {
        declaringCodeTypeClass = type.newInstance().getCodeType().getClass();
      }
      catch (Throwable t) {
        LOG.error("find code " + type, t);
      }
    }
    ICodeType codeType = getCodeType(declaringCodeTypeClass);
    final Holder<ICode> codeHolder = new Holder<ICode>(ICode.class);
    ICodeVisitor v = new ICodeVisitor() {
      @Override
      public boolean visit(ICode code, int treeLevel) {
        if (code.getClass() == type) {
          codeHolder.setValue(code);
          return false;
        }
        return true;
      }
    };
    codeType.visit(v);
    return (T) codeHolder.getValue();
  }

  @Override
  public <T extends ICode> T getCode(Long partitionId, Class<T> type) {
    return getCode(type);
  }

  @Override
  public <T extends ICodeType> T reloadCodeType(Class<T> type) {
    if (type == null) {
      return null;
    }
    return getCodeType(type);
  }

  @Override
  public ICodeType[] reloadCodeTypes(Class... types) {
    if (types == null) {
      return null;
    }
    return getCodeTypes(types);
  }

  @Override
  public BundleClassDescriptor[] getAllCodeTypeClasses(String classPrefix) {
    // There is no classPrefix integration  
    return new BundleClassDescriptor[0];
  }

  @Override
  public ICodeType[] getAllCodeTypes(String classPrefix) {
    ArrayList<Class> list = new ArrayList<Class>();
    for (BundleClassDescriptor d : getAllCodeTypeClasses(classPrefix)) {
      try {
        list.add(Platform.getBundle(d.getBundleSymbolicName()).loadClass(d.getClassName()));
      }
      catch (Throwable t) {
        LOG.warn("Loading " + d.getClassName() + " of bundle " + d.getBundleSymbolicName(), t);
        continue;
      }
    }
    return getCodeTypes(list.toArray(new Class[list.size()]));
  }

  @Override
  public ICodeType[] getAllCodeTypes(String classPrefix, Long partitionId) {
    return getAllCodeTypes(classPrefix);
  }

}
