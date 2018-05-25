package jianzhioffer.tree;

import jianzhioffer.utils.BinaryTreeNode;

/**
 * 题目描述：根据二叉树的前序遍历序列和中序遍历序列重建该树
 *
 * 思路：在前序遍历序列中第一个元素是该二叉树的头结点
 *       根据头结点的值可以搜索到该值在中序遍历节点序列中的位置
 *       找到头结点在中序遍历节点的位置就可以通过该位置，把二叉树
 *       分为左子树和右子树两个新的二叉树，然后再用同样的方法构建
 *       左子树和右子树。
 */
public class ConstructTree {
    public static void main(String[] args) {
        int[] preorder = {1,2,4,7,3,5,6,8};
        int[] inorder = {4,7,2,1,5,3,8,6};
        BinaryTreeNode root = Construct(preorder,inorder);
        preOrder(root);
    }
    /**
     * 构建二叉树 根据前序遍历序列和中序遍历序列
     * @param preorder 前序遍历序列
     * @param inorder  中序遍历序列
     * @return
     */
    public static BinaryTreeNode Construct(int[] preorder,int[] inorder){
        if(preorder == null || inorder == null || preorder.length != inorder.length || preorder.length <= 0){
            return null;
        }
        //初始状态，起始位置都是0 结束位置都是length - 1;
        //也就是以整个一棵大树为参数传进来
        return ConstructCode(0,preorder.length - 1,0,inorder.length - 1,preorder,inorder);
    }
    /**
     *构建二叉树
     * @param startPre 先序遍历的起始下标
     * @param endPre   先序遍历的结束下标
     * @param startIn  中序遍历的起始下标
     * @param endIn    中序遍历的结束下标
     * @param preOrder  整棵树的先序遍历序列
     * @param inOrder   整棵树的中序遍历序列
     * @return
     */
    public static BinaryTreeNode ConstructCode(int startPre,int endPre,int startIn,int endIn,int[] preOrder,int[] inOrder) {
       //先序遍历的第一个节点是根节点
        int rootData = preOrder[startPre];
        BinaryTreeNode root = new BinaryTreeNode(rootData);
        //只要是递归，进来第一件事就是退出条件
        if(startPre == endPre){
            //当且仅当，子树中只有一个节点 并且它的先序遍历和中序遍历序列都是同一个数字
            //函数退出
            if(startIn == endIn && preOrder[startPre] == inOrder[startIn]){
                return root;
            }else{
                //否则，构建失败
                System.out.println("无效输入，构建失败");
                return null;
            }
        }
        //从先序遍历中拿到根节点的数值rootData，然后找到它在中序遍历序列中的存储位置
        int rootInorder = startIn;
        while(rootInorder <= endIn && inOrder[rootInorder] != rootData){
            rootInorder++;
        }
        //如果遍历完了整个中序遍历序列都没有找到 则构建失败
        if(rootInorder == endIn && inOrder[rootInorder] != rootData){
            System.out.println("输入的序列有错误");
            return null;
        }
        //左子树 中序遍历的节点个数
        int leftLength = rootInorder - startIn;
        //左子树 先序遍历序列的最后一个节点的下标
        int leftEndPre = startPre + leftLength;
        //左子树中序遍历序列长度大于0 则说明当前节点还有左子树
        if(leftLength > 0){
            root.setLeft(ConstructCode(startPre + 1,leftEndPre,startIn,rootInorder - 1,preOrder,inOrder));
        }
        //左右子树的总序列长度大于左子树长度，说明当前节点还有右子树
        if(leftLength < endPre - startPre){
            root.setRight(ConstructCode(leftEndPre + 1,endPre,rootInorder + 1,endIn,preOrder,inOrder));
        }
        return root;
    }

    /**
     * 先序遍历二叉树
     * @param root
     */
    public static void preOrder(BinaryTreeNode root){
        if(root == null){
            return ;
        }
        System.out.print(root.getData() + "->");
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }
}
