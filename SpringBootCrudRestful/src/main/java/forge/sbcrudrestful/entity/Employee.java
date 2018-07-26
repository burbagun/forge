package forge.sbcrudrestful.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
 
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Column(nullable=false)
    private String Name;
	
	@Column(nullable=false)
    private String position;
 
    public Employee() {
 
    }
 
    public Employee(Long id, String name, String position) {
        this.id = id;
        this.Name = name;
        this.position = position;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        this.Name = name;
    }
 
    public String getPosition() {
        return position;
    }
 
    public void setPosition(String position) {
        this.position = position;
    }
 
}