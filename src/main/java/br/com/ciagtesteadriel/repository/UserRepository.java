package br.com.ciagtesteadriel.repository;

//Importando a classe de listas
import java.util.List;

//Importando a classes de comunicacao e persistencia com o banco de dados 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//import javax.persistence.PersistenceContext;

//Importando a classe de usuarios 
import br.com.ciagtesteadriel.repository.entity.UserEntity;

public class UserRepository {
	//@PersistenceContext(unitName="uniqueName2")
	private EntityManagerFactory entityManagerFactory;
	
	private EntityManager entityManager;
	
	//Metodo construtor da classe para criar a conexao com o banco de dados
	public UserRepository(){
		this.entityManagerFactory = Persistence.createEntityManagerFactory("CiagTesteAdrielRest");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}
	
	//Metodo para salvar um novo cadastro no banco de dados
	public void save(UserEntity userEntity){
		try{
			this.entityManager.getTransaction().begin();
			this.entityManager.persist(userEntity);
			this.entityManager.getTransaction().commit();
		}catch (Exception e){
			e.printStackTrace();
			this.entityManager.getTransaction().rollback();
		}
	}
	
	//Metodo para alterar o cadastro de um usuario salvo no banco de dados
	public void alter (UserEntity userEntity){
		try{
			this.entityManager.getTransaction().begin();
			this.entityManager.merge(userEntity);
			this.entityManager.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			this.entityManager.getTransaction().rollback();
		}
	}
	
	//Metodo para buscar um unico usuario salvo no banco de dados
	public UserEntity getByCode (int code){
		return this.entityManager.find(UserEntity.class, code);
	}
	
	//Metodo para listar todos os usuarios que estão salvos no banco de dados
	@SuppressWarnings("unchecked")
	public List<UserEntity> findAll(){
		return this.entityManager.createQuery("SELECT U FROM UserEntity U ORDER BY U.name").getResultList();
	}
	
	//Metodo para remoção de um usuario no banco de dados 
	public void remove(UserEntity userEntity){
		try{
			this.entityManager.getTransaction().begin();
			userEntity = this.getByCode(userEntity.getCode());
			this.entityManager.remove(userEntity);
			this.entityManager.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			this.entityManager.getTransaction().rollback();
		}
	}
	
	public void removeByCode(int code){
		try{
			UserEntity user = this.getByCode(code);
			this.remove(user);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
