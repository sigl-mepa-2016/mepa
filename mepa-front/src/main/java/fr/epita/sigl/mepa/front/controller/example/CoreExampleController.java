package fr.epita.sigl.mepa.front.controller.example;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.epita.sigl.mepa.core.domain.Model;
import fr.epita.sigl.mepa.core.service.ModelService;
import fr.epita.sigl.mepa.front.model.example.AddCustomModelFormBean;

@Controller
@RequestMapping("/example/core")
@SessionAttributes({ CoreExampleController.MODELS_MODEL_ATTRIBUTE })
public class CoreExampleController {

    private static final Logger LOG = LoggerFactory.getLogger(CoreExampleController.class);

    protected static final String MODELS_MODEL_ATTRIBUTE = "models";
    private static final String ADD_CUSTOM_MODEL_FORM_BEAN_MODEL_ATTRIBUTE = "addCustomModelFormBean";

    @Autowired
    private ModelService modelService;

    @RequestMapping(value = { "/", "/form" })
    public String showForm(HttpServletRequest request, ModelMap modelMap) {

        this.createRandomModel();

        // Get models data from database
        List<Model> models = this.modelService.getAllModels();
        if (LOG.isDebugEnabled()) {
            LOG.debug("There are {} models in database", models.size());
        }

        // Update model attribute "models", to use it in JSP
        modelMap.addAttribute(MODELS_MODEL_ATTRIBUTE, models);

        return "/example/core/form";
    }

    /**
     * @param request
     * @param modelMap
     * @param addCustomModelFormBean
     * @param result
     * @return
     */
    @RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
    public String processForm(HttpServletRequest request, ModelMap modelMap,
            @Valid AddCustomModelFormBean addCustomModelFormBean, BindingResult result) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/example/core/form";
        }
        Model newModel = new Model();
        newModel.setData(addCustomModelFormBean.getEmail());
        this.modelService.createModel(newModel);

        modelMap.addAttribute("model", newModel);

        return "/example/core/result";
    }

    /**
     * Create a random model and add it in database.
     */
    private void createRandomModel() {
        Model newModel = new Model();
        newModel.setData(RandomStringUtils.randomAlphanumeric(42));
        this.modelService.createModel(newModel);
    }

    /**
     * Initialize "models" model attribute
     * 
     * @return an empty List of Models.
     */
    @ModelAttribute(MODELS_MODEL_ATTRIBUTE)
    public List<Model> initModels() {
        return new ArrayList<Model>();
    }

    /**
     * Initialize "addCustomModelFormBean" model attribute
     * 
     * @return a new AddCustomModelFormBean.
     */
    @ModelAttribute(ADD_CUSTOM_MODEL_FORM_BEAN_MODEL_ATTRIBUTE)
    public AddCustomModelFormBean initAddCustomModelFormBean() {
        return new AddCustomModelFormBean();
    }
}
