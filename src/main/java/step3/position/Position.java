package step3.position;

import step3.output.OutputView;

import java.util.Objects;

public final class Position {

    private static final int DISTANCE_TO_MOVE = 1;

    private final int position;

    private final OutputView outputView;

    public Position(int position, OutputView outputView) {
        this.position = position;
        this.outputView = outputView;
    }

    public Position movedPosition() {
        return new Position(position + DISTANCE_TO_MOVE, outputView);
    }

    public void printPosition() {
        outputView.print(position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return position == position1.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

}