package org.eclipse.scout.bahbah.client.services;

import org.eclipse.scout.service.IService;

public interface IBuddyIconProviderService extends IService {
  /**
   * the default buddy icon used when the user has not uploaded an icon yet. icon must be located in client plugin
   * under resources/icons
   */
  final static String BUDDY_DEFAULT_ICON = "default_buddy_icon";
}
