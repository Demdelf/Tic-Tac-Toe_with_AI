package tictactoe;

import java.util.List;

public class Row {
    private List<Point> points;

    public Row(List<Point>points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
