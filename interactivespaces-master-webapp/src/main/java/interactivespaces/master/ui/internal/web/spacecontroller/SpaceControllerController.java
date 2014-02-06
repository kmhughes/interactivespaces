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

package interactivespaces.master.ui.internal.web.spacecontroller;

import interactivespaces.domain.basic.SpaceController;
import interactivespaces.master.api.MasterApiControllerManager;
import interactivespaces.master.api.MasterApiLiveActivity;
import interactivespaces.master.api.MasterApiMessageSupport;
import interactivespaces.master.api.MasterApiUtilities;
import interactivespaces.master.server.services.ActiveControllerManager;
import interactivespaces.master.server.services.ActiveSpaceController;
import interactivespaces.master.server.services.ControllerRepository;
import interactivespaces.master.ui.internal.web.BaseActiveSpaceMasterController;
import interactivespaces.master.ui.internal.web.UiUtilities;

import com.google.common.collect.Lists;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * A controller for Interactive Spaces space controller operations.
 *
 * @author Keith M. Hughes
 */
@Controller
public class SpaceControllerController extends BaseActiveSpaceMasterController {

  /**
   * Repository for controller entities.
   */
  private ControllerRepository controllerRepository;

  /**
   * Manager for controller operations.
   */
  private ActiveControllerManager activeControllerManager;

  /**
   * Display a list of all controllers.
   *
   * @return model and view for controller list display
   */
  @RequestMapping("/spacecontroller/all.html")
  public ModelAndView listControllers() {
    List<ActiveSpaceController> controllers =
        activeControllerManager.getActiveSpaceControllers(controllerRepository.getAllSpaceControllers());
    Collections.sort(controllers, MasterApiUtilities.ACTIVE_CONTROLLER_BY_NAME_COMPARATOR);

    ModelAndView mav = getModelAndView();
    mav.setViewName("spacecontroller/SpaceControllerViewAll");
    mav.addObject("lspacecontrollers", controllers);

    return mav;
  }

  @RequestMapping(value = "/spacecontroller/{id}/view.html", method = RequestMethod.GET)
  public ModelAndView viewController(@PathVariable String id) {
    ModelAndView mav = getModelAndView();

    SpaceController controller = controllerRepository.getSpaceControllerById(id);
    if (controller != null) {
      ActiveSpaceController lcontroller = activeControllerManager.getActiveSpaceController(controller);

      List<MasterApiLiveActivity> liveactivities =
          masterApiControllerManager.getAllUiLiveActivitiesByController(controller);
      Collections.sort(liveactivities, MasterApiUtilities.UI_LIVE_ACTIVITY_BY_NAME_COMPARATOR);
      mav.addObject("metadata", UiUtilities.getMetadataView(controller.getMetadata()));

      mav.setViewName("spacecontroller/SpaceControllerView");

      mav.addObject("spacecontroller", controller);
      mav.addObject("lspacecontroller", lcontroller);
      mav.addObject("liveactivities", liveactivities);
    } else {
      mav.setViewName("spacecontroller/SpaceControllerNonexistent");
    }

    return mav;
  }

  @RequestMapping(value = "/spacecontroller/{id}/delete.html", method = RequestMethod.GET)
  public ModelAndView deleteController(@PathVariable String id) {
    ModelAndView mav = getModelAndView();
    Map<String, Object> response = masterApiControllerManager.deleteController(id);

    if (MasterApiMessageSupport.isSuccessResponse(response)) {
      mav.clear();
      mav.setViewName("redirect:/spacecontroller/all.html");
    } else if (MasterApiMessageSupport.isResponseReason(response,
        MasterApiControllerManager.MESSAGE_SPACE_DOMAIN_CONTROLLER_UNKNOWN)) {
      mav.setViewName("spacecontroller/SpaceControllerNonexistent");
    }

    return mav;
  }

  @RequestMapping(value = "/spacecontroller/all.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> listAllControllersJson() {
    return masterApiControllerManager.getSpaceControllerAllView();
  }

  @RequestMapping(value = "/spacecontroller/{id}/view.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> viewControllerJson(@PathVariable String id) {
    return masterApiControllerManager.getSpaceControllerView(id);
  }

  @RequestMapping(value = "/spacecontroller/{id}/connect.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> connectController(@PathVariable String id) {
    return masterApiControllerManager.connectToControllers(Collections.singletonList(id));
  }

  @RequestMapping(value = "/spacecontroller/{id}/disconnect.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> disconnectController(@PathVariable String id) {
    return masterApiControllerManager.disconnectFromControllers(Collections.singletonList(id));
  }

  @RequestMapping(value = "/spacecontroller/{id}/cleantmpdata.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> cleanTempData(@PathVariable String id) {
    return masterApiControllerManager.cleanControllerTempData(id);
  }

  @RequestMapping(value = "/spacecontroller/{id}/cleanactivitiestmpdata.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> cleanActivitiesTempData(@PathVariable String id) {
    return masterApiControllerManager.cleanControllerActivitiesTempData(id);
  }

  @RequestMapping(value = "/spacecontroller/{id}/cleanpermanentdata.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> cleanPermanentData(@PathVariable String id) {
    return masterApiControllerManager.cleanControllerPermanentData(id);
  }

  @RequestMapping(value = "/spacecontroller/{id}/cleanactivitiespermanentdata.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> cleanActivitiesPermanentData(@PathVariable String id) {
    return masterApiControllerManager.cleanControllerActivitiesPermanentData(id);
  }

  @RequestMapping(value = "/spacecontroller/{id}/restoredata.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> restoreData(@PathVariable String id) {
    return masterApiControllerManager.restoreControllerDataBundle(id);
  }

  @RequestMapping(value = "/spacecontroller/{id}/capturedata.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> captureData(@PathVariable String id) {
    return masterApiControllerManager.captureControllerDataBundle(id);
  }

  @RequestMapping(value = "/spacecontroller/{id}/shutdown.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> shutdownController(@PathVariable String id) {
    return masterApiControllerManager.shutdownControllers(Lists.newArrayList(id));
  }

  @RequestMapping(value = "/spacecontroller/{id}/activities/shutdown.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> shutdownAllAppsController(@PathVariable String id) {
    return masterApiControllerManager.shutdownAllActivities(id);
  }

  @RequestMapping(value = "/spacecontroller/{id}/status.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> statusController(@PathVariable String id) {
    return masterApiControllerManager.statusControllers(Collections.singletonList(id));
  }

  @RequestMapping(value = "/spacecontroller/{id}/deploy.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> deployLiveActivities(@PathVariable String id) {
    return masterApiControllerManager.deployAllControllerActivityInstances(id);
  }

  @RequestMapping(value = "/spacecontroller/all/connect.html", method = RequestMethod.GET)
  public String connectAllControllers() {
    masterApiControllerManager.connectToAllControllers();

    return "redirect:/spacecontroller/all.html";
  }

  @RequestMapping(value = "/spacecontroller/all/disconnect.html", method = RequestMethod.GET)
  public String disconnectAllControllers() {
    masterApiControllerManager.disconnectFromAllControllers();

    return "redirect:/spacecontroller/all.html";
  }

  @RequestMapping(value = "/spacecontroller/all/shutdown.html", method = RequestMethod.GET)
  public String shutdownAllControllers() {
    masterApiControllerManager.shutdownAllControllers();

    return "redirect:/spacecontroller/all.html";
  }

  @RequestMapping(value = "/spacecontroller/all/cleantmpdata.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> cleanTempDataAllControllers() {
    return masterApiControllerManager.cleanControllerTempDataAllControllers();
  }

  @RequestMapping(value = "/spacecontroller/all/cleanactivitiestmpdata.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> cleanActivitiesTempDataAllControllers() {
    return masterApiControllerManager.cleanControllerActivitiesTempDataAllControllers();
  }

  @RequestMapping(value = "/spacecontroller/all/cleanpermanentdata.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> cleanPermanentDataAllControllers() {
    return masterApiControllerManager.cleanControllerPermanentDataAllControllers();
  }

  @RequestMapping(value = "/spacecontroller/all/cleanactivitiespermanentdata.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> cleanActivitiesPermanentDataAllControllers() {
    return masterApiControllerManager.cleanControllerActivitiesPermanentDataAllControllers();
  }

  @RequestMapping(value = "/spacecontroller/all/capturedata.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> captureDataAllControllers() {
    return masterApiControllerManager.captureDataAllControllers();
  }

  @RequestMapping(value = "/spacecontroller/all/restoredata.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> restoreDataAllControllers() {
    return masterApiControllerManager.restoreDataAllControllers();
  }

  @RequestMapping(value = "/spacecontroller/all/status.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> statusAllControllers() {
    return masterApiControllerManager.statusFromAllControllers();
  }

  @RequestMapping(value = "/spacecontroller/all/forcestatus.json", method = RequestMethod.GET)
  public @ResponseBody
  Map<String, ? extends Object> forceStatusAllControllers() {
    return masterApiControllerManager.forceStatusFromAllControllers();
  }

  @RequestMapping(value = "/spacecontroller/all/activities/shutdown.html", method = RequestMethod.GET)
  public String shutdownAllActivitiesAllControllers() {
    masterApiControllerManager.shutdownAllActivitiesAllControllers();

    return "redirect:/spacecontroller/all.html";
  }

  /**
   * @param controllerRepository
   *          the controllerRepository to set
   */
  public void setControllerRepository(ControllerRepository controllerRepository) {
    this.controllerRepository = controllerRepository;
  }

  /**
   * @param activeControllerManager
   *          the activeControllerManager to set
   */
  @Override
  public void setActiveControllerManager(ActiveControllerManager activeControllerManager) {
    this.activeControllerManager = activeControllerManager;
  }
}
