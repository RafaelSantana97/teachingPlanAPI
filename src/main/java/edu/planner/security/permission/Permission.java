package edu.planner.security.permission;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.planner.interfaces.IModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable, IModel {

	private static final long serialVersionUID = -1995900791554126203L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Short resource;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERMISSION_TYPES", foreignKey = @ForeignKey(name = "FK_PERMISSION"))
	@JsonIgnore
	private Set<Short> permissionTypes = new HashSet<Short>();

	public Resource getResource() {
		return Resource.toEnum(resource);
	}
	
	public Set<PermissionType> getPermissionTypes() {
		return permissionTypes.stream().map(x -> PermissionType.toEnum(x)).collect(Collectors.toSet());
	}
}
