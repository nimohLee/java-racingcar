package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.dto.RoundResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("자동차 경주 게임 테스트")
public class RacingCarTest {

    @Test
    @DisplayName("주어진 횟수는 0 이상이어야 한다.")
    void roundCountTest() {
        assertThatThrownBy(() -> new RacingCar(1, -1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("주어진 횟수는 0 이상이어야 합니다.");
    }

    @Test
    @DisplayName("사용자가 입력한 자동차는 1대 이상이어야 한다.")
    void carCountTest() {
        assertThatThrownBy(() -> new RacingCar(0, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자동차는 1대 이상이어야 합니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5})
    @DisplayName("자동차의 현재 상태는 주어진 횟수값을 넘지 않는다.")
    void carsStateTest(int count) {
        RacingCar racingCar = new RacingCar(1, count);

        List<RoundResult> playResult = racingCar.play(new RandomMovingStrategy());

        List<PositiveInteger> lastGameResult = playResult.get(playResult.size() - 1).getStates();
        for (PositiveInteger result : lastGameResult) {
            assertThat(result.value()).isLessThanOrEqualTo(count);
        }
    }

    @Test
    @DisplayName("모두 movable하다면 Car의 상태는 round횟수와 같다.")
    void allMovableTest() {
        RacingCar racingCar = new RacingCar(3, 5);

        List<RoundResult> playResult = racingCar.play(new MovingStrategy() {
            @Override
            public boolean movable() {
                return true;
            }
        });

        for (int round = 1; round <= playResult.size(); round++) {
            int index = round - 1;
            List<PositiveInteger> states = playResult.get(index).getStates();
            for (PositiveInteger state : states) {
                assertThat(state.value()).isEqualTo(round);
            }
        }
    }

    @Test
    @DisplayName("모두 unmovable하다면 Car의 상태는 항상 0이다.")
    void allunMovableTest() {
        RacingCar racingCar = new RacingCar(1, 3);

        List<RoundResult> playResult = racingCar.play(new MovingStrategy() {
            @Override
            public boolean movable() {
                return false;
            }
        });

        for (int round = 1; round <= playResult.size(); round++) {
            int index = round - 1;
            List<PositiveInteger> states = playResult.get(index).getStates();
            for (PositiveInteger state : states) {
                assertThat(state.value()).isZero();
            }
        }
    }
}
