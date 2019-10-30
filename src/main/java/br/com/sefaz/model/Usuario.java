

package br.com.sefaz.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//Usuario do Sistema , tem 2 Namedquery com selects por email ou usuario , o id é chave primaria e telefones é uma lista.
@Entity
@NamedQueries({
		@NamedQuery(name = "Usuario.todos", query = "select u from Usuario u"),//NameDquery Opcionais para busca
		@NamedQuery(name = "Usuario.buscarEmail", query = "select u from Usuario u where u.email = :email")})
@Table(name="Usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false, unique = true)	
	private String email;
	
	@Column(nullable = false)
	private String senha;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Telefone> telefones;

	public void setTelefoneUsers(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<Telefone> getTelefoneUsers() {
		return telefones;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email
				+ ", senha=" + senha + "]";
	}

}
