package backdropv1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backdropv1.exception.ResourceNotFoundException;
import backdropv1.model.NewUser;
import backdropv1.repository.NewUserRepository;

@RestController
@RequestMapping("/backdrop/v1")
public class NewUserController {
	@Autowired
	private NewUserRepository nuRepository;
	
	
	//get user
	@GetMapping("queryall")
	public List<NewUser> getAllUsers() {
		return this.nuRepository.findAll();
	}
	
	//get user by id
	@GetMapping("queryuser/{userid}")
	public ResponseEntity<NewUser> getUserById(@PathVariable(value = "userid") String usrid) throws ResourceNotFoundException   {
		NewUser nwuser = nuRepository.findById(usrid).orElseThrow(() -> new ResourceNotFoundException("User not found for this id : "+usrid));
		
		return ResponseEntity.ok().body(nwuser);
	  }
	
	//insert new User data
	@PostMapping("usercreate")
	public NewUser createNewUser(@Valid @RequestBody NewUser nuser) {
		return this.nuRepository.save(nuser);
	}
	//update user data
	@PutMapping("updateuser/{userid}")
	public ResponseEntity<NewUser> updateUser(@PathVariable(value = "userid") String usrid, @Valid @RequestBody NewUser nuserDetails) throws ResourceNotFoundException {
		NewUser nuser = this.nuRepository.findById(usrid).orElseThrow(() -> new ResourceNotFoundException("User not found for this id : "+usrid));
		nuser.setEmailid(nuserDetails.getEmailid());
		nuser.setFirstName(nuserDetails.getFirstName());
		nuser.setLastName(nuserDetails.getLastName());
		nuser.setPassWord(nuserDetails.getPassWord());
		
		return ResponseEntity.ok().body(nuser);
	}
	//delete user data
	@DeleteMapping("deleteuser/{userid}")
	public Map<String, Boolean> deleteUser(@PathVariable (value = "userid") String usrid) throws ResourceNotFoundException {
		NewUser nuser = this.nuRepository.findById(usrid).orElseThrow(() -> new ResourceNotFoundException("User not found for this id : "+usrid));
		this.nuRepository.delete(nuser);
		Map<String, Boolean> responseData = new HashMap();
		responseData.put("deleted", Boolean.TRUE);
        return responseData;
	}

}
