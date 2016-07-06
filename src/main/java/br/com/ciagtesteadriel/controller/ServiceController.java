package br.com.ciagtesteadriel.controller;

//Importando as classes padrao do Java para trabalhar com vetores
import java.util.ArrayList;
import java.util.List;

//Importando as classes de comunicacao para as criacao de cada API do CRUD
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

//importando as classes do projeto para comunicar com os metodos da API
import br.com.ciagtesteadriel.http.User;
import br.com.ciagtesteadriel.repository.UserRepository;
import br.com.ciagtesteadriel.repository.entity.UserEntity;

@Path("/service")
public class ServiceController {
	private UserRepository userRepository = new UserRepository();
	
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/new")
	public String New(User user){
		UserEntity userEntity = new UserEntity();
		try{
			userEntity.setName(user.getName());
			userEntity.setEmail(user.getEmail());
			userEntity.setPassword(user.getPassword());
			userEntity.setSex(user.getSex());
			this.userRepository.save(userEntity);
			return "Usuário adicionado com sucesso!";
		}catch(Exception e){
			return "Erro ao adicionar um novo usuário "+e.getMessage();
		}
	}
	
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")
	@Path("/alter")
	public String Alter(User user){
		UserEntity userEntity = new UserEntity();
		try{
			userEntity.setCode(user.getCode());
			userEntity.setName(user.getName());
			userEntity.setEmail(user.getEmail());
			userEntity.setPassword(user.getPassword());
			userEntity.setSex(user.getSex());
			this.userRepository.alter(userEntity);
			return "Usuário atualizado com sucesso!";
		}catch(Exception e){
			return "Erro ao atualizar o usuário "+e.getMessage();
		}
	}
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/allUsers")
	public List<User> AllUsers(){
		List<User> users = new ArrayList<User>();
		List<UserEntity> usersEntity = this.userRepository.findAll();
		for(UserEntity userEntity : usersEntity){
			users.add(new User(userEntity.getCode(),userEntity.getName(),userEntity.getEmail(),userEntity.getPassword(),userEntity.getSex()));
		}
		return users;
	}
	
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getUser/{codigo}")
	public User GetUser(@PathParam("codigo") Integer code){
		UserEntity userEntity = this.userRepository.getByCode(code);			
		
		if (userEntity != null){
			return new User(userEntity.getCode(),userEntity.getName(),userEntity.getEmail(),userEntity.getPassword(),userEntity.getSex());
		}
		return null;
	}
	
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/remove/{codigo}")
	public String Remove(@PathParam("codigo") Integer code){
		try{
			this.userRepository.removeByCode(code);			
			return "Usuário removido com sucesso!";
		}catch(Exception e){
			return "Erro ao remover o usuário! "+e.getMessage();
		}
	}
}
