package ca.sheridancollege;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ca.sheridancollege.beans.Person;
import ca.sheridancollege.dao.DAO;

@Controller
public class HomeController {

	DAO dao = new DAO();

	@RequestMapping("/")
	public String goHome(Model model) {

		model.addAttribute("person", new Person());

		return "AddContact";
	}

	@RequestMapping("/generateRandom")
	public String generateRandom(Model model) {

		model.addAttribute("person", new Person());
		dao.generateRandom();
		return "AddContact";
	}

	@RequestMapping(value = "addContact", method = RequestMethod.POST)
	public String savePerson(Model model, @ModelAttribute Person person) {
		// This way it does not cause thread interference
		synchronized (Person.class) {
			dao.addPerson(person);
		}

		Person p = new Person();
		model.addAttribute("person", p);
		return "AddContact";
	}

	@RequestMapping("/view")
	public String viewContact(Model model) {

		model.addAttribute("person", dao.getPerson());
		return "ViewContacts";
	}

	@RequestMapping(value = "update/{id}", method = RequestMethod.GET)
	public String updateById(Model model, @PathVariable int id) {

		Person person = dao.queryByID(id);
		if (person.getId() != 0) {
			dao.deletePerson(id);
		}
		model.addAttribute("person", person);

		return "AddContact";
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String deleteById(Model model, @PathVariable int id) {

		dao.deletePerson(id);

		model.addAttribute("person", dao.getPerson());
		return "ViewContacts";
	}

	@RequestMapping("/searchContact")
	public String searchContact(Model model, @ModelAttribute Person person) {
		
		
		
		if (person.getSelection().equalsIgnoreCase("name")) {
			List<Person> personList = dao.queryByName(person.getAnswer());
			model.addAttribute("personList", personList);
		}
		
		else if (person.getSelection().equalsIgnoreCase("phone")) {
			List<Person> personList = dao.queryByNumber(person.getAnswer());
			model.addAttribute("personList", personList);
		}
		
		else if (person.getSelection().equalsIgnoreCase("address")) {
			List<Person> personList = dao.queryByAddress(person.getAnswer());
			model.addAttribute("personList", personList);
		}
		
		else if (person.getSelection().equalsIgnoreCase("email")) {
			List<Person> personList = dao.queryByEmail(person.getAnswer());
			model.addAttribute("personList", personList);
		}

		model.addAttribute("person", new Person());
		return "searchPage";
	}

	@RequestMapping("/doSearch")
	public String searchMovie(Model model) {

		model.addAttribute("person", new Person());
		return "search";
	}
	
	// JSON 
	@RequestMapping(value="/getPerson/{id}", produces="application/json")
	// return data as actual data  
	@ResponseBody
	public Map<String, Object> getPerson(Model model, @PathVariable int id) {
		
		Map<String, Object> data = new HashMap<String, Object>();
		Person p = dao.queryByID(id);
		data.put("person", p);
		
		
		return data;
	}

}
