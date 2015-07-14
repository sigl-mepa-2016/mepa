package fr.epita.sigl.mepa.core.domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@NamedQueries({
        @NamedQuery(name = "DataSet.findById", query = "FROM DataSet o WHERE o.id=:id"),
        @NamedQuery(name = "DataSet.findAll", query = "FROM DataSet o") })

public class DataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "theme", nullable = false)
    private String theme;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified", nullable = false)
    private Date last_modified;

    @NotNull
    private String data;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Date getLastModifed() {
        return this.last_modified;
    }

    public void setLastModified(Date last_modified) {
        this.last_modified = last_modified;
    }

    /**
     * @return the data
     */
    public String getData() {
        return this.data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
