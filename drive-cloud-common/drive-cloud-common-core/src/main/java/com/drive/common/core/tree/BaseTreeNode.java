package com.drive.common.core.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : xiaoguo
 * @createdDate : 2019/8/12
 * @updatedDate
 */
public  class BaseTreeNode {
    /**
     * 子Id
     */
    private String id;
    /**
     * 父ID
     */
    private String pId;

    private List<BaseTreeNode> children;

    public BaseTreeNode() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public List<BaseTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<BaseTreeNode> children) {
        this.children = children;
    }

    public void addChild(BaseTreeNode baseTreeNode) {
        if (this.children == null) {
            this.setChildren(new ArrayList());
        }

        this.getChildren().add(baseTreeNode);
    }





}