package com.globallogic.test.tree.manager;

import com.globallogic.test.tree.TreeNode;
import com.globallogic.test.tree.container.TreeContainer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class TreeManagerUT {
    private TreeNode<Integer> one = new TreeNode<>(1);
    private TreeNode<Integer> two = new TreeNode<>(2);
    private TreeNode<Integer> three = new TreeNode<>(3);
    private TreeNode<Integer> four = new TreeNode<>(4);
    private TreeNode<Integer> five = new TreeNode<>(5);
    private TreeNode<Integer> six = new TreeNode<>(6);
    private TreeNode<Integer> seven = new TreeNode<>(7);
    private TreeNode<Integer> eight = new TreeNode<>(8);
    private TreeNode<Integer> nine = new TreeNode<>(9);
    private TreeContainer<Integer> treeContainer = new TreeContainer<>(one);
    private TreeManager<Integer> treeManager = new TreeManager<>(treeContainer);

    @Before
    public void before() {
        treeContainer.addChildren(one, Arrays.asList(two, three));
        treeContainer.addChildren(two, Arrays.asList(four, five));
        treeContainer.addChildren(three, Arrays.asList(six));
        treeContainer.addChildren(five, Arrays.asList(seven, eight));
        treeContainer.addChildren(six, Arrays.asList(nine));
    }

    @Test
    public void testGetMaxChildCount() {
        Assert.assertEquals(2, treeManager.getMaxChildCount(one));
        Assert.assertEquals(2, treeManager.getMaxChildCount(two));
        Assert.assertEquals(1, treeManager.getMaxChildCount(three));
    }
}
