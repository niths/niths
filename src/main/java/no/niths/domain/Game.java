package no.niths.domain;

import no.niths.common.AppConstants;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
@Table(name = AppConstants.GAMES)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Game implements Domain {

    @Transient
    private static final long serialVersionUID = 3498065037797521916L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Size(min = 3, max = 80, message ="The length of the name must be between 3 to 80 letters")
    private String name;

    @Column()
    @Size(min = 2, max = 30, message ="The length of the category must be between 2 to 10 letters")
    private String category;

    @Column
    private Integer locker;

    @JsonIgnore
    @XmlTransient
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Console.class)
    @JoinTable(name = "games_consoles",
            joinColumns = @JoinColumn(name = "games_id"),
            inverseJoinColumns = @JoinColumn(name = "consoles_id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Console console;

    public Game(){
        this(null, null, null);
        setConsole(null);
    }

    public Game(String name){
        setName(name);
    }

    public Game(String name, String category, Integer locker){
        setName(name);
        setCategory(category);
        setLocker(locker);
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setLocker(Integer locker) {
        this.locker = locker;
    }

    public Integer getLocker() {
        return locker;
    }

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
        return (id == null && name == null && category == null);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s][%s]", id, name, category);
    }
}
