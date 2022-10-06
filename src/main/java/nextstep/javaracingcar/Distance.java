package nextstep.javaracingcar;

import java.util.Iterator;

public class Distance implements Iterable<Integer> {
    public static final Distance ZERO = new Distance(0);
    public static final Distance ONE = new Distance(1);
    private final int value;

    public Distance(final int value) {
        if (value < 0) {
            throw new RuntimeException("음수 값은 처리할 수 없습니다.");
        }
        this.value = value;
    }

    public Distance plus(final Distance other) {
        return new Distance(this.value + other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Distance distance = (Distance) o;

        return value == distance.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new SizeIterator(value);
    }
}