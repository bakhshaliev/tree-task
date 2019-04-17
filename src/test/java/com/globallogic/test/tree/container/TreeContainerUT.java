package com.globallogic.test.tree.container;

import com.globallogic.test.tree.TreeNode;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class TreeContainerUT {
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

    @Before
    public void before() {
        one.setChildren(Arrays.asList(two, three));
        two.setChildren(Arrays.asList(four, five));
        three.setChildren(Arrays.asList(six));
        four.setChildren(null);
        five.setChildren(Arrays.asList(seven, eight));
        six.setChildren(Arrays.asList(nine));
        seven.setChildren(null);
        eight.setChildren(null);
        nine.setChildren(null);
    }

    @Test
    public void testSearch() {
        Predicate<Integer> isEven = integer -> integer % 2 == 0;
        Predicate<Integer> isOdd = integer -> integer % 2 != 0;
        Predicate<Integer> isGreaterThanFive = integer -> integer > 5;

        Assert.assertEquals(Arrays.asList(two, four, eight, six), treeContainer.search(isEven));
        Assert.assertEquals(Arrays.asList(one, five, seven, three, nine), treeContainer.search(isOdd));
        Assert.assertEquals(Arrays.asList(eight, six), treeContainer.search(isEven.and(isGreaterThanFive)));
        Assert.assertEquals(Arrays.asList(seven, nine), treeContainer.search(isOdd.and(isGreaterThanFive)));
    }

    @Test
    public void testGetSubTrees() {
        Assert.assertEquals(Arrays.asList(one, two, four, five, seven, eight, three, six, nine),
                treeContainer.getSubTrees(one));
        Assert.assertEquals(Arrays.asList(two, four, five, seven, eight),
                treeContainer.getSubTrees(two));
        Assert.assertEquals(Arrays.asList(three, six, nine),
                treeContainer.getSubTrees(three));
    }

    @Test
    public void testAddChildren() {
        TreeNode<Integer> ten = new TreeNode<>(10);
        TreeNode<Integer> eleven = new TreeNode<>(11);
        TreeNode<Integer> twelve = new TreeNode<>(12);
        List<TreeNode<Integer>> children = Arrays.asList(ten, eleven, twelve);

        treeContainer.addChildren(nine, Arrays.asList(ten, eleven, twelve));

        Assert.assertEquals(children, nine.getChildren());
    }

    @Test
    public void testRemoveChildren() {
        treeContainer.removeChildren(three);
        Assert.assertNull(three.getChildren());

        treeContainer.removeChildren(two);
        Assert.assertNull(two.getChildren());

        treeContainer.removeChildren(one);
        Assert.assertNull(one.getChildren());
    }
}
