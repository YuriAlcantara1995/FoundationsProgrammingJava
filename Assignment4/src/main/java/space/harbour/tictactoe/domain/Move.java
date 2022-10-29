package space.harbour.tictactoe.domain;
import lombok.*;

@Data
@AllArgsConstructor
public class Move {
    int row;
    int column;
    public Move() {
        row = -1;
        column = -1;
    }
}
