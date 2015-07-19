package fr.epita.sigl.mepa.front.model.search;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by emeline on 12/07/2015.
 */
public class SearchForm {
    @NotBlank
    private String search;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
}
