package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.model.dto.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名：com.atguigu.spzx.manager.service.impl
 *
 * @author lct
 * 日期: 2023-10-18   20:24
 */
public class MenuHelper {
    public static List<SysMenu> buildTree(List<SysMenu> allNodes) {

        List<SysMenu> treeNodes = new ArrayList<>();

        for (SysMenu allNode : allNodes) {
            if (allNode.getParentId()==0){
                treeNodes.add(findChildren(allNode,allNodes));
            }
        }

        return treeNodes;
    }

    private static SysMenu findChildren(SysMenu allNode, List<SysMenu> allNodes) {

        for (SysMenu node : allNodes) {
            if (allNode.getId()==node.getParentId()){//所有人中找到等于getParentId的
                allNode.getChildren().add(findChildren(node,allNodes));
            }
        }
        return allNode;
    }
}
