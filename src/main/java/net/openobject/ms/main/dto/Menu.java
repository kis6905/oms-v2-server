package net.openobject.ms.main.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.openobject.ms.user.dto.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Menu")
@Table(name = "oms_menu")
public class Menu {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long seq;

	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private String iconUrl;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "oms_menu_role",
    		   joinColumns = @JoinColumn(name = "menu_seq"),
    		   inverseJoinColumns = @JoinColumn(name = "role_seq"))
	private List<Role> roleList;
	
}
