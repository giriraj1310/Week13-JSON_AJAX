package ca.sheridancollege.beans;

import java.io.Serializable;

import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person implements Serializable {
	
	
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String phone;
	private String address;
	private String email;
	
	@Transient
	private String arr[] = {"name", "email", "address", "phone"};
	@Transient
	private String selection;
	@Transient
	private String answer;
	
	public Person(String name, String phone, String address, String email) {
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.selection="";
		this.answer="";
		
	}

	public String getSelection() {
		// TODO Auto-generated method stub
		return this.selection;
	}
	
	
	
}
