/*
 * Copyright (C) 2012 Google Inc.
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

package interactivespaces.domain.pojo;

import java.util.UUID;

/**
 * A simple object base class.
 *
 * @author Keith M. Hughes
 */
public class SimpleObject {

  /**
   * An ID that we can reference from a webapp.
   */
  private String id = UUID.randomUUID().toString();

  /**
   * Get the ID of the object.
   *
   * <p>
   * This is the persistence ID and should be used for nothing else.
   *
   * @return the ID for the object
   */
  public String getId() {
    return id;
  }

  /**
   * Set the ID of the object.
   *
   * <p>
   * This is the persistence ID and should be used for nothing else.
   *
   * @param id
   *          the ID of the object
   */
  public void setId(String id) {
    this.id = id;
  }
}