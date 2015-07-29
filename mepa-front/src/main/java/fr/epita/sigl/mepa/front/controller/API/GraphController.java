package fr.epita.sigl.mepa.front.controller.API;

import fr.epita.sigl.mepa.core.domain.Graph;
import fr.epita.sigl.mepa.core.service.GraphService;
import fr.epita.sigl.mepa.front.APIpojo.Impl.ErrorMessage;
import fr.epita.sigl.mepa.front.APIpojo.Impl.GraphInput;
import fr.epita.sigl.mepa.front.APIpojo.Impl.SuccessMessage;
import fr.epita.sigl.mepa.front.APIpojo.Pojo;
import org.bson.types.ObjectId;
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

    @RequestMapping(value = "/get/{dataSetID}", method = RequestMethod.GET)
    public Graph getGraphConf(@PathVariable String dataSetID) {
        return this.graphService.getById(dataSetID  );
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Pojo setGraphConf(@RequestBody GraphInput graphInput, @RequestHeader(value = "Authorization", defaultValue = "") String authorization) {
        Pojo resultAuthorization = new UserController().checkToken(authorization);
        if (resultAuthorization instanceof ErrorMessage)
            return resultAuthorization;
        LOG.debug("", graphInput);

        this.graphService.create(new Graph(new ObjectId(graphInput.getId()), graphInput.getGrapheType(), graphInput.getGrapheColor1(), graphInput.getGrapheColor2(), graphInput.getGrapheJson()));

        return new SuccessMessage("success add");

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Pojo updateGraphConf(@RequestBody GraphInput graphInput, @RequestHeader(value = "Authorization", defaultValue = "") String authorization) {
        Pojo resultAuthorization = new UserController().checkToken(authorization);
        if (resultAuthorization instanceof ErrorMessage)
            return resultAuthorization;
        LOG.debug("", graphInput);

        this.graphService.update(new Graph(new ObjectId(graphInput.getId()), graphInput.getGrapheType(), graphInput.getGrapheColor1(), graphInput.getGrapheColor2(), graphInput.getGrapheJson()));

        return new SuccessMessage("success update");

    }
}
