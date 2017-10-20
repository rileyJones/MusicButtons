public class DimensionData {
    private int width;
    private int height;
    public DimensionData(){
        width=100;
        height=100;
    }
    public void set(int x, int y) {
        width = x;
        height = y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
