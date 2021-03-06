/*
 * Copyright (C) 2013 BEIJING UNION VOOLE TECHNOLOGY CO., LTD
 */
package xdata.etl.cinder.shared.entity.menu;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author XuehuiHe
 * @date 2013年8月8日
 */
@Entity
@Table(name = "menu_group")
public class MenuGroup extends MenuNode {
	private static final long serialVersionUID = 2138324039371528785L;
	
	private List<MenuNode> nodes;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
	public List<MenuNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<MenuNode> nodes) {
		this.nodes = nodes;
	}

}
