package racing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CarTest {

    Car car;

    @BeforeEach
    void before() {
        car = new Car();
    }

    @Test
    @DisplayName("Car 객체 생성 시 position은 0 && 파라미터와 Car의 위치가 일치하면 true")
    void Car_constructor() {
        assertThat(car.hasPosition(0)).isTrue();
        assertThat(car.hasPosition(1)).isFalse();
    }

    @Test
    @DisplayName("forward() 호출 시 position 1 증가")
    void forward() {
        int startPosition = 0;
        assertThat(car.hasPosition(startPosition)).isTrue();
        car.forward();
        assertThat(car.hasPosition(startPosition + 1)).isTrue();
    }

}