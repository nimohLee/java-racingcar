package view;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RacingInputView {

    public static RacingInput getInputFromUser() {
        final String carNames = getCarNamesFromUser();
        final int trialCount = getTrialCountFromUser();

        return new RacingInput(carNames, trialCount);
    }

    private static String getCarNamesFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
        return scanner.next();
    }

    private static int getTrialCountFromUser() {
        return getPositiveIntegerInputAndRetryWhenException(
                "시도할 횟수는 몇 회인가요?",
                "시도할 횟수는 양의 정수만 입력 가능합니다.\n"
        );
    }

    private static int getPositiveIntegerInputAndRetryWhenException(final String firstTryMessage, final String retryMessage) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(firstTryMessage);
        try {
            return getPositiveIntegerInput(scanner);
        } catch (NoSuchElementException e) {
            System.out.println(retryMessage);
            return getPositiveIntegerInputAndRetryWhenException(firstTryMessage, retryMessage);
        }
    }

    // 쓰임새만 보면 private 처리하고 싶은데, 테스트코드에서 사용해야 해서 public 처리했습니다.
    public static int getPositiveIntegerInput(final Scanner scanner) throws InputMismatchException {
        int input = scanner.nextInt();
        if (input <= 0) {
            throw new InputMismatchException();
        }
        return input;
    }
}