/**
 * Created by Joseph on 7/1/16.
 */
public class CollisionBox {
    public int top;
    public int bottom;
    public int left;
    public int right;

    public CollisionBox(){
        setBounds(0, 0, 0, 0);
    }

    public CollisionBox(int x, int y, int width, int height){
        setBounds(x, y, width, height);
    }

    public void setBounds(int x, int y, int width, int height){
        this.left = x;
        this.top = y;
        this.right = left + width;
        this.bottom = top + height;
    }

    /**
     * Returns a boolean indicating whether or not two bounding boxes
     * are overlapping.
     * @param box the bounding box being checked
     * @return    a boolean indicating whether an overlap exists
     */
    public boolean intersects(CollisionBox box){
        CollisionBox box1 = this;
        CollisionBox box2 = box;

        if(box1.bottom < box2.top) return false;
        if(box1.top > box2.bottom) return false;
        if(box1.left > box2.right) return false;
        if(box1.right < box2.left) return false;

        return true;
    }

    /**
     * Formats the bounding box as a string.
     * @return a string representation of the bounding box
     */
    public String toString(){
        return "Top: " + top + ", Bottom: " + bottom + ", Left " + left + ", Right: " + right;
    }

}
