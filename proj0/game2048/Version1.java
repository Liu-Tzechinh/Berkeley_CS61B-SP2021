package game2048;

public class Version1 {

    /*
    // merge all tilts that can be merged ->  move

    */
/**
 * merge all tiles that can be merged
 * @param col
 * @param row
 * @return
 *//*

    private boolean mergeHelper(int col, Board b) {
        int size = b.size();
        boolean changed;
        changed = false;
        for (int row = size - 1; row >= 0; row--) {
            Tile tile = b.tile(col, row);
            if (tile != null) {
                // determinate werther there is a tile can be merged with b.tile(col, row)
                for (int subRow = row - 1; subRow >= 0; subRow--) {
                    Tile subtile = b.tile(col, subRow);
                    if (subtile != null) {
                        if (tile.value() == subtile.value()) {
                            changed = b.move(col, row, subtile);
                            this.score += tile.value() * 2;
                            row = subRow;
                        } else {
                         row = subRow + 1;
                        }
                        break;
                    }
                }
            }
        }
        return changed;
    }

    private boolean moveHelper(int col, Board b) {
        int size = b.size();
        boolean changed;
        changed = false;
        for (int row = size - 1; row >= 0; row--) {
            Tile tile = b.tile(col, row);
            if (tile == null) {
                // determinate werther there is a tile can be moved toward b.tile(col, row)
                for (int subRow = row - 1; subRow >= 0; subRow--) {
                    Tile subtile = b.tile(col, subRow);
                    if (subtile != null) {
                        b.move(col, row, subtile);
                        changed = true;
                        break;
                    }
                }
            }
        }
        return changed;
    }
    */
/** Tilt the board toward SIDE. Return true iff this changes the board.
 *
 * 1. If two Tile objects are adjacent in the direction of motion and have
 *    the same value, they are merged into one Tile of twice the original
 *    value and that new value is added to the score instance variable
 * 2. A tile that is the result of a merge will not merge again on that
 *    tilt. So each move, every tile will only ever be part of at most one
 *    merge (perhaps zero).
 * 3. When three adjacent tiles in the direction of motion have the same
 *    value, then the leading two tiles in the direction of motion merge,
 *    and the trailing tile does not.
 * *//*

    public boolean tilt(Side side) {
        boolean changed, changedMerge, changedMove;
        changed = false;
        changedMerge = false;
        changedMove = false;
        int size = board.size();
        board.setViewingPerspective(side);
        // TODO: Modify this.board (and perhaps this.score) to account
        // for the tilt to the Side SIDE. If the board changed, set the
        // changed local variable to true.
        for (int col = 0; col < size; col++) {
            if (changedMerge || changedMove) {
                changed = true;
            }
            changedMerge = mergeHelper(col, board);
            changedMove =  moveHelper(col, board);
        }



        if (changedMerge || changedMove) {
            changed = true;
        }
        board.setViewingPerspective(Side.NORTH);
        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }
*/
}
