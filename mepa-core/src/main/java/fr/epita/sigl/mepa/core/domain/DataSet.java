package fr.epita.sigl.mepa.core.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "isCarto", nullable = false)
    private Boolean isCarto;

    @Column(name = "isGraphic", nullable = false)
    private Boolean isGraphic;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastModified", nullable = false)
    private Date lastModified;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getIsCarto() {
        return isCarto;
    }

    public void setIsCarto(Boolean isCarto) {
        this.isCarto = isCarto;
    }

    public Boolean getIsGraphic() {
        return isGraphic;
    }

    public void setIsGraphic(Boolean isGraphic) {
        this.isGraphic = isGraphic;
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

    public Date getLastModified() {
        return this.lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
