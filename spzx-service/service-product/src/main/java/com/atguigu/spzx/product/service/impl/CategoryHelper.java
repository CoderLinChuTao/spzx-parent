package com.atguigu.spzx.product.service.impl;

import com.atguigu.spzx.model.dto.system.SysMenu;
import com.atguigu.spzx.model.entity.product.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 *
 * @author lct
 * 日期: 2023-10-18   20:24
 */
public class CategoryHelper {
    public static List<Category> buildTree(List<Category> allNodes) {
        List<Category> treeNodes = new ArrayList<>();
        for (Category allNode : allNodes) {
            if(allNode.getParentId().longValue()==0){
                treeNodes.add(findChildren(allNode,allNodes));
            }
        }
        return treeNodes;
    }

    private static Category findChildren(Category allNode, List<Category> allNodes) {
        List<Category> childrenList = new ArrayList<Category>();
        for (Category node : allNodes) {
            if(allNode.getId().longValue()==node.getParentId().longValue()){
                childrenList.add(findChildren(node,allNodes));
                allNode.setChildren(childrenList);
            }
        }
        return allNode;
    }
}
