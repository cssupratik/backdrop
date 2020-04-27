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
import backdropv1.supframework.HushHash;

@RestController
@RequestMapping("/backdrop/v1")
public class NewUserController {
	@Autowired
	private NewUserRepository nuRepository;
	private String rocksalt = "H@rdW@rkbe@tsT@lent";
	
	//get user
	@GetMapping("queryall")
	public List<NewUser> getAllUsers() {
		return this.nuRepository.findAll();
	}
	
	//get user by id
	@GetMapping("queryuser/{userid}")
	public Map<String, String> getUserById(@PathVariable(value = "userid") String usrid) throws ResourceNotFoundException   {
		NewUser nwuser = this.nuRepository.findById(usrid).orElseThrow(() -> new ResourceNotFoundException("User not found for this id : "+usrid));
		
		//return ResponseEntity.ok().body(nwuser);
		Map<String, String> responseData = new HashMap();
		responseData.put("userid", nwuser.getUserName());
		responseData.put("emailid", nwuser.getEmailid());
		responseData.put("fname", nwuser.getFirstName());
		responseData.put("lname", nwuser.getLastName());
        return responseData;
	  }
	
	//insert new User data
	@PostMapping("usercreate")
	public String createNewUser(@Valid @RequestBody NewUser nuser) throws Exception {
		
		String tmpwd = nuser.getPassWord();
		Optional<String> hpwd = HushHash.hashPassword(tmpwd, rocksalt);
		hpwd.ifPresent(g -> nuser.setPassWord(hpwd.get()));
		hpwd.orElseThrow(() -> new Exception("Empty Hash Password returned"));
		this.nuRepository.save(nuser);
		return "Record inserted";
	}
	//update user data
	@PutMapping("updateuser/{userid}")
	public Map<String, String> updateUser(@PathVariable(value = "userid") String usrid, @Valid @RequestBody NewUser nuserDetails) throws Exception {
		NewUser nuser = this.nuRepository.findById(usrid).orElseThrow(() -> new ResourceNotFoundException("User not found for this id : "+usrid));
		nuser.setEmailid(nuserDetails.getEmailid());
		nuser.setFirstName(nuserDetails.getFirstName());
		nuser.setLastName(nuserDetails.getLastName());
		nuser.setUserName(nuserDetails.getUserName());
		String tmpwd = nuserDetails.getPassWord();
		Optional<String> hpwd = HushHash.hashPassword(tmpwd, rocksalt);
		hpwd.ifPresent(g -> nuser.setPassWord(hpwd.get()));
		hpwd.orElseThrow(() -> new Exception("Empty Hash Password returned"));
		NewUser updatednuser = this.nuRepository.save(nuser);
		Map<String, String> responseData = new HashMap();
		responseData.put("userid", updatednuser.getUserName());
		responseData.put("emailid", updatednuser.getEmailid());
		responseData.put("fname", updatednuser.getFirstName());
		responseData.put("lname", updatednuser.getLastName());
        return responseData;
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
