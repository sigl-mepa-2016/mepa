package fr.epita.sigl.mepa.front.controller.API;

import fr.epita.sigl.mepa.core.domain.Graph;
import fr.epita.sigl.mepa.core.service.GraphService;
import fr.epita.sigl.mepa.front.APIpojo.Impl.ErrorMessage;
import fr.epita.sigl.mepa.front.APIpojo.Impl.GraphInput;
import fr.epita.sigl.mepa.front.APIpojo.Impl.SuccessMessage;
import fr.epita.sigl.mepa.front.APIpojo.Pojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/graph")
public class GraphController {
    private static final Logger LOG = LoggerFactory.getLogger(GraphController.class);

    @Autowired
    private GraphService graphService;

    @RequestMapping(value = "/{DataSetID}", method = RequestMethod.GET)
    public Graph getGraphConf(@PathVariable String DataSetId)
    {
        return this.graphService.getById(DataSetId);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Pojo setGraphConf(@RequestBody GraphInput graphInput, @RequestHeader(value = "Authorization", defaultValue = "") String authorization)
    {
        Pojo resultAuthorization = new UserController().checkToken(authorization);
        if (resultAuthorization instanceof ErrorMessage)
            return resultAuthorization;
        LOG.debug("", graphInput);

        this.graphService.create(new Graph(graphInput.getId(), graphInput.getGrapheColor1(), graphInput.getGrapheColor2(), graphInput.getGrapheJson()));

        return new SuccessMessage("success add");

    }
}
