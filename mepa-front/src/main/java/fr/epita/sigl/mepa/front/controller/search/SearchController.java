package fr.epita.sigl.mepa.front.controller.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by emeline on 12/07/2015.
 */
@Controller
@RequestMapping("/search/core")
public class SearchController {
    private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);

    @RequestMapping(value = { "/", "/search" })
    public String showSearch(HttpServletRequest request) {
        return "/search/core/search";
    }
}
