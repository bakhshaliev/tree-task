package com.globallogic.test.tree.event.log;

import com.globallogic.test.tree.TreeNode;
import com.globallogic.test.tree.event.ChildrenRemovedListener;
import lombok.extern.java.Log;

import java.util.List;
import java.util.logging.Level;

@Log
public class LogChildrenRemovedListener<T> implements ChildrenRemovedListener<T> {

    @Override
    public void childrenRemoved(TreeNode<T> parent, List<TreeNode<T>> children) {
        log.log(Level.INFO, "Children of node with value = " + parent.getValue() + " were removed.");
    }
}
