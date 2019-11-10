package es.urjccode.mastercloudapps.adcs.draughts.models;

class Turn {

    private Color color;

    Turn() {
        this.color = Color.WHITE;
    }

    void change() {
        this.color = Color.values()[(this.color.ordinal() + 1) % 2];
    }

    Color getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return this.color.name();
    }

    Error isTurnValid(Color colorToCheck) {
        if (colorToCheck == null) {
            return Error.OUT_COORDINATE;
        }
        
        if (getColor() != colorToCheck) {
            return Error.OPPOSITE_PIECE;
        }
        return null;
    }

}