package com.globallogic.test.tree;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TreeNode<T> {

    public TreeNode(T value) {
        this.value = value;
    }

    private T value;
    private List<TreeNode<T>> children = new ArrayList<>();
}
