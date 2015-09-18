/*
 * Copyright (C) 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package interactivespaces.controller.runtime;

import interactivespaces.domain.basic.SpaceController;
import interactivespaces.system.InteractiveSpacesEnvironment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * A {@link SpaceControllerInfoPersister} which uses the file system.
 *
 * @author Keith M. Hughes
 */
public class FileSystemSpaceControllerInfoPersister implements SpaceControllerInfoPersister {

  @Override
  public void
      persist(SpaceController controllerInfo, InteractiveSpacesEnvironment spaceEnvironment) {
    Properties props = new Properties();
    props.put(interactivespaces.controller.SpaceController.CONFIGURATION_CONTROLLER_UUID,
        controllerInfo.getUuid());
    props.put(interactivespaces.controller.SpaceController.CONFIGURATION_CONTROLLER_NAME,
        controllerInfo.getName());
    props.put(interactivespaces.controller.SpaceController.CONFIGURATION_CONTROLLER_DESCRIPTION,
        controllerInfo.getDescription());

    File controllerInfoFile =
        new File(spaceEnvironment.getFilesystem().getInstallDirectory(),
            "config/interactivespaces/controllerinfo.conf");
    FileWriter writer = null;
    try {
      writer = new FileWriter(controllerInfoFile);
      props.store(writer, "Autogenerated UUID");

      writer.flush();
      spaceEnvironment.getLog().info(
          String.format("Persisted new controller information to %s",
              controllerInfoFile.getAbsolutePath()));
    } catch (Exception e) {
      spaceEnvironment.getLog().error(
          String.format("Error while persisting %s", controllerInfoFile.getAbsolutePath()), e);
    } finally {
      if (writer != null) {
        try {
          writer.close();
        } catch (IOException e) {
          // Don't care
        }
      }
    }
  }

}