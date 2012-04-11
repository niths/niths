package no.niths.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import no.niths.common.AppConstants;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Cascade;

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

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Console.class)
    @JoinTable(name = "games_consoles",
            joinColumns = @JoinColumn(name = "games_id"),
            inverseJoinColumns = @JoinColumn(name = "consoles_id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Console console;

    /*@ManyToOne(fetch = FetchType.LAZY, targetEntity = Student.class)
    @JoinTable(name = "loans_games",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "loan_id"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Student loanedBy;*/

    public Game(){
        this(null, null, null);
        setConsole(null);
        //setLoanedBy(null);
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

    @JsonSerialize(as=Console.class)
    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

   /* @JsonSerialize(as=Student.class)
    public Student getLoanedBy() {
        return loanedBy;
    }

    public void setLoanedBy(Student loanedBy) {
        this.loanedBy = loanedBy;
    }*/

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
