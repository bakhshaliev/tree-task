package com.globallogic.test.tree.event.log;

import com.globallogic.test.tree.TreeNode;
import com.globallogic.test.tree.event.ChildrenAddedListener;
import lombok.extern.java.Log;

import java.util.logging.Level;

@Log
public class LogChildrenAddedListener<T> implements ChildrenAddedListener<T> {

    @Override
    public void childrenAdded(TreeNode<T> parent) {
        log.log(Level.INFO, "Children of node with value = " + parent.getValue() + " were added.");
    }
}
