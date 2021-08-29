package lotto.gameresult;

import lotto.game.LottoGame;
import lotto.number.WinningNumbers;
import lotto.ticket.Prize;

import java.util.Arrays;

public class GameResult {

    private final LottoGame lottoGame;
    private final WinningNumbers winningNumbers;

    public GameResult(LottoGame lottoGame, WinningNumbers winningNumbers) {
        this.lottoGame = lottoGame;
        this.winningNumbers = winningNumbers;
    }

    public int countTicketsWinning(Prize prize) {
        return (int) lottoGame.getLottoTickets().stream()
                .filter(ticket -> ticket.prize(winningNumbers).equals(prize))
                .count();
    }

    private int totalPrizeMoney() {
        return Arrays.stream(Prize.values())
                .mapToInt(prize -> prize.prizeMoney() * countTicketsWinning(prize))
                .sum();
    }

    public double profitRate() {
        return new ProfitRate(totalPrizeMoney(), lottoGame.payments()).value();
    }

}
