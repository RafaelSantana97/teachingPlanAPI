package edu.planner.security.permission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.planner.interfaces.IModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
	@CollectionTable(name = "permission_types", foreignKey = @ForeignKey(name = "FK_PERMISSION"))
	@JsonIgnore
	private Set<Short> permissionTypes = new HashSet<>();

	public Resource getResource() {
		return Resource.toEnum(resource);
	}
	
	public Set<PermissionType> getPermissionTypes() {
		return permissionTypes.stream().map(PermissionType::toEnum).collect(Collectors.toSet());
	}
}
