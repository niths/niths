package no.niths.domain.battlestation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.constants.DomainConstantNames;
import no.niths.common.constants.ValidationConstants;
import no.niths.domain.Domain;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
/**
 * Domain class for Game
 *
 * <p>
 * Game has these variables:
 * title = example Super Mario,
 * category = example PLATFORM
 * </p>
 * <p>
 * And relations too:
 * Console
 * </p>
 */
@XmlRootElement
@Entity
@Table(name = DomainConstantNames.GAMES)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Game implements Domain {

    @Transient
    private static final long serialVersionUID = 3498065037797521916L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Pattern(
            regexp  = ValidationConstants.REGULAR,
            message = "Invalid name (should be 2 - 50 alphanumeric letters)")
    private String title;

    @Column
    @Enumerated(EnumType.STRING)
    private GameCategory category;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Console.class)
    @JoinTable(name = "games_consoles",
            joinColumns = @JoinColumn(name = "games_id"),
            inverseJoinColumns = @JoinColumn(name = "consoles_id"))
    @Cascade(CascadeType.ALL)
    private Console console;

    public Game(){
        this(null, null);
        setConsole(null);
    }

    public Game(String name){
        setTitle(name);
    }

    public Game(String name, GameCategory category){
        setTitle(name);
        setCategory(category);
    }

    public Game(Long id){
        setId(id);
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getTitle() {
        return title;
    }

    public void setCategory(GameCategory category) {
        this.category = category;
    }

    public GameCategory getCategory() {
        return category;
    }

    @JsonSerialize(as=Console.class)
    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    @Override
    public boolean equals(Object that) {
        if(!(that instanceof Game)) return false;
        Game game = (Game) that;

        return game == this || game.getId().equals(id);
    }

    @JsonIgnore
    public boolean isEmpty(){
        return (id == null && title == null && category == null);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s][%s]", id, title, category);
    }
}
