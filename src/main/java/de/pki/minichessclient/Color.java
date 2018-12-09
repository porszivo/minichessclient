package de.pki.minichessclient;

public enum Color {
    WHITE('W'), BLACK('B'), EMPTY('e');

    private final char colorCode;

    Color(char colorCode) {
        this.colorCode = colorCode;
    }

    public char getColorCode() {
        return colorCode;
    }
}
