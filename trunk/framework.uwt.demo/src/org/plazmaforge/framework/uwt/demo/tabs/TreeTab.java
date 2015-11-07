/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package org.plazmaforge.framework.uwt.demo.tabs;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.provider.TreeProvider;
import org.plazmaforge.framework.uwt.demo.model.Group;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.widget.LabelProvider;
import org.plazmaforge.framework.uwt.widget.panel.Panel;
import org.plazmaforge.framework.uwt.widget.tree.Tree;

public class TreeTab extends AbstractTab {

    public TreeTab() {
    }

    @Override
    protected void createUI() {
	setLayout(new FitLayout());
	Panel panel = createTreePanel();
	add(panel);
    }
    
    
    private Panel createTreePanel() {
	Panel panel = new Panel();
	panel.setLayout(new FitLayout());
	
	Tree<Group> tree = new Tree<Group>();
	tree.setToolTip("Tree tooltip");
	
	/*
	List<Object> treeList = new ArrayList<Object>();
	
	treeList.add(new Dictionary("100", "Type 1"));
	treeList.add(new Dictionary("200", "Type 2"));
	treeList.add(new Dictionary("300", "Type 3"));
	treeList.add(new Dictionary("400", "Type 4"));
	treeList.add(new Dictionary("500", "Type 5"));
	
	tree.setDataList(treeList);
	tree.setDisplayProperty("name");
	*/

	tree.setDisplayProperty("name");
	final List<Group> groupList = new ArrayList<Group>();

	// Group 1
	Group group = new Group();
	group.setName("Group 1");
	groupList.add(group);

	Group group1_1 = new Group();
	group1_1.setName("Group 1.1");
	group.addChildren(group1_1);

	Group group1_2 = new Group();
	group1_2.setName("Group 1.2");
	group.addChildren(group1_2);

	
	// Group 2
	group = new Group();
	group.setName("Group 2");
	groupList.add(group);
	
	Group group2_1 = new Group();
	group2_1.setName("Group 2.1");
	group.addChildren(group2_1);

	Group group2_2 = new Group();
	group2_2.setName("Group 2.2");
	group.addChildren(group2_2);
	
	// Group 3
	group = new Group();
	group.setName("Group 3");
	groupList.add(group);

	TreeProvider<Group> dataProvider = new TreeProvider<Group>() {
	    
	    @Override
	    public List<Group> getList() {
		return groupList;
	    }
	    
	    @Override
	    public boolean hasChildren(Group element) {
		return element.hasChildren();
	    }
	    
	    @Override
	    public Group getParent(Group element) {
		return null;
	    }
	    
	    @Override
	    public List<Group> getChildren(Group parent) {
		return parent.getChildren();
	    }
	};
	
	tree.setDataProvider(dataProvider, true);
	
	
	final Image folderImage = new Image("widget/folder.gif");
	final Image folderCloseImage = new Image("widget/folder.gif");
	final Image folderOpenImage = new Image("widget/folder-open.gif");
	final Image leafImage = new Image("widget/leaf.gif"); 
	
	tree.setLeafIcon(leafImage);
	//tree.setNodeIcon(folderOpenImage);
	
	tree.setOpenIcon(folderOpenImage);
	tree.setCloseIcon(folderCloseImage);
	
	/*
	tree.setLabelProvider(new LabelProvider() {

	    @Override
	    public Image getImage(Object element) {
		return leafImage;
	    }

	    @Override
	    public String getText(Object element) {
		return element == null ? null : ((Group) element).getName();
	    }
	    
	});
	*/
	
	panel.add(tree);
	
	return panel;
    }

   
}
